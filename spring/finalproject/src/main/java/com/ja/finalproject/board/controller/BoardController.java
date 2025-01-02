package com.ja.finalproject.board.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import com.ja.finalproject.board.service.BoardService;
import com.ja.finalproject.dto.ArticleImageDto;
import com.ja.finalproject.dto.UserDto;
import com.ja.finalproject.dto.articleDto;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j // 로그를 찍어주는 어노테이션, log.debug("content : {}", content); 이런식으로 사용
@Controller
@RequestMapping("board")
public class BoardController {

    @Autowired
    private BoardService boardService;
    @Value("${app.file.upload.rootPath}")
    static String rootPath;



    // 게시판 목록
    @RequestMapping("mainPage")
    public String mainPage(Model model,
    @RequestParam(value = "searchType", required = false) String searchType,
    @RequestParam(value = "searchWord", required = false) String searchWord,
    @RequestParam(value = "page", required = false, defaultValue = "1") int page){
        List<Map<String,Object>> list = boardService.getArticleList(searchType,searchWord,page);
        model.addAttribute("list", list);

        int totalCount = boardService.getTotalArticleCount(searchType, searchWord);
        int lastPageNumber = (int)Math.ceil(totalCount/10.0);
        int startPage =((page - 1) / 5) * 5 + 1;
        int endPage = ((page -1) / 5 + 1) * 5;

        if(endPage > lastPageNumber){
            endPage = lastPageNumber;
        }

        model.addAttribute("totalCount", totalCount);
        model.addAttribute("lastPageNumber", lastPageNumber);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("currentPage", page);


        return"board/mainPage";
    }

    /*
     * 글 상세 보기
     */
    @RequestMapping("articleDetailPage")
    public String articleDetailPage(@RequestParam("id") int id, Model model){
        boardService.addReadCount(id);
        Map<String,Object> map = boardService.getArticle(id);
        //html escape - 특수문자, 엔터 -> br로 변경
        //나중에 javascript 로 넘어가면 수행안해도됨.
        articleDto articledto = (articleDto)map.get("articleDto");
        String content = articledto.getContent();
        content = StringUtils.escapeXml(content);
        content = content.replaceAll("\n", "<br>");
        articledto.setContent(content);
        
        log.debug("content : {}", content);
        
        model.addAttribute("map", map);
        return"board/articleDetailPage";
    }
    /*
     * 글쓰기 페이지
     */
    @RequestMapping("writeArticlePage")
    public String writeArticlePage(){
        return "board/writeArticlePage";
    }

    /*
     * 글쓰기 프로세스
     */
    @RequestMapping("wirteArticleProcess")
    public String writeArticleProcess(articleDto params, HttpSession session,
        @RequestParam(value="uploadFiles")MultipartFile[] uploadFiles){
            List<ArticleImageDto> articleImagelist = new ArrayList<>();
            


        // 세션값 세팅 하는 방법
        for(MultipartFile uploadFile: uploadFiles){
            if(uploadFile.isEmpty()){
                continue;
            }
            
            String rootPath = "C:/uploadFiles/";
            //날짜별 폴더(디렉토리) 생성
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd/");
            // "C:/uploadFiles/yyyy/MM/dd/" 파일 경로
            String todayPath = sdf.format(new Date());
            File todayFolderForCreate = new File(rootPath+todayPath);

            // 해당 폴더/파일이 존재하지 않으면 생성코드
            if(!todayFolderForCreate.exists()){
                todayFolderForCreate.mkdirs();
            }

            // 파일명 충돌 회피 - 랜덤 + 시간 조합
            String originalFilename = uploadFile.getOriginalFilename();

            String uuid = UUID.randomUUID().toString();
            long currentTime = System.currentTimeMillis();

            String fileName = uuid +"_"+ currentTime;
            
            // 확장자명 추출
            String ext = originalFilename.substring(originalFilename.lastIndexOf("."));
            fileName += ext;

            try {
                uploadFile.transferTo(new File(rootPath+todayPath+fileName));
            } catch (Exception e) {
                e.printStackTrace();
            }

            // DB 작업용 Dto생성 
            ArticleImageDto articleImageDto = new ArticleImageDto();
            articleImageDto.setOriginalFilename(originalFilename);
            articleImageDto.setLocation(todayPath+fileName);
            articleImagelist.add(articleImageDto);

        }
        UserDto sessionUserInfo = (UserDto)session.getAttribute("sessionUserInfo");
        int userPk = sessionUserInfo.getId();
        params.setUserId(userPk);
        boardService.registerArticle(params, articleImagelist);
        return "redirect:./mainPage";
    }
    // 글 수정 페이지
    @RequestMapping("updateArticlePage")
    public String updateArticlePage(@RequestParam("id") int id,Model model){
        model.addAttribute("articleDetail", boardService.getArticle(id));
        return "board/updateArticlePage";
    }
    // 게시글 수정 프로세스
    @RequestMapping("updateArticleProcess")
    public String updateArticleProcess(articleDto articleDto){
        boardService.update(articleDto);
        return "redirect:/board/mainPage";
    }
    // 게시글 삭제 프로세스
    @RequestMapping("deleteArticleProcess")
    public String deleteArticleProcess(@RequestParam("id") int id){
        boardService.deleteArticle(id);
        return "redirect:./mainPage";
    }
}
