package com.ja.study.study1029.board.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import com.ja.study.study1029.board.dto.ArticleImageDto;
import com.ja.study.study1029.board.dto.BoardDto;
import com.ja.study.study1029.board.service.BoardService;
import com.ja.study.study1029.comment.service.CommentService;
import com.ja.study.study1029.postlike.dto.PostLikeDto;
import com.ja.study.study1029.postlike.service.PostLikeService;
import com.ja.study.study1029.user.dto.UserDto;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("board")
public class BoardController {

    @Autowired
    private BoardService boardService;
    
    @Autowired
    private CommentService commentService;

    @Autowired
    private PostLikeService postLikeService;

    
    // 게시판 페이지
    @RequestMapping("mainPage")
    public String mainPage(Model model,
        @RequestParam(value = "searchType", required = false )String searchType,
        @RequestParam(value = "searchWord", required = false)String searchWord,   
        @RequestParam(value = "page", required = false, defaultValue = "1")int page){   
        List<Map<String,Object>> boardlist = boardService.findAll(searchType, searchWord, page);
        model.addAttribute("boardlist", boardlist);

        int totalCount = boardService.getArticlePageCount(searchType,searchWord);
        int lastCount = (int)Math.ceil(totalCount/10.0);
        int startPage = ((page - 1) / 5) * 5 + 1;
        int endPage = ((page - 1) / 5 + 1) * 5;

        if(endPage > lastCount){
            endPage = lastCount;
        }


        model.addAttribute("totalCount", totalCount);
        model.addAttribute("lastCount", lastCount);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("currentPage", page);
        return "board/mainPage";
    }
    
    // 게시글 상세내용
    @RequestMapping("detailPage/{id}")
    public String detailPage(@PathVariable("id") int id, Model model, HttpSession session){
        boardService.addReadCount(id);
        Map<String,Object> boardMap = boardService.getFindById(id);
        UserDto sessionUserInfo = new UserDto();

        if(session.getAttribute("sessionUserInfo") == null){
            sessionUserInfo.setId(0);
        }else{
            sessionUserInfo = (UserDto)session.getAttribute("sessionUserInfo");
        }
        
        // 문자 변환
        BoardDto boardDto = (BoardDto)boardMap.get("boardDto");
        String content = boardDto.getContent();
        content = StringUtils.escapeXml(content);
        content = content.replaceAll("\n", "<br>");
        boardDto.setContent(content);

        
        PostLikeDto postLikeDto = new PostLikeDto();
        postLikeDto.setArticleId(id);
        postLikeDto.setUserId(sessionUserInfo.getId());

        model.addAttribute("boardMap", boardMap);
        model.addAttribute("commentList", commentService.getList(id));
        model.addAttribute("commentCount", commentService.commentsCount(id));
        model.addAttribute("postLikeCount", postLikeService.postLikeCount(id));
        model.addAttribute("postLikeDto", postLikeService.userLikeCount(postLikeDto));
        return "board/detailPage";
    }
    
    // 게시글 등록 페이지
    @RequestMapping("writePage")
    public String writePage(){
        return"board/writePage";
    }

    // 게시글 등록 프로세스
    @RequestMapping("writeArticleProcess")
    public String writeArticleProcess(BoardDto boardDto, HttpSession session,
    @RequestParam(value = "uploadFiles") MultipartFile[] uploadFiles){

        List<ArticleImageDto> articleImagelist = new ArrayList<>();

        for(MultipartFile uploadFile : uploadFiles){
            if(uploadFile.isEmpty()){
                continue;
            }

            String rootPath = "C:/uploadFiles/";
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd/");
            String todayPath = sdf.format(new Date());
            File todayFolderForCreate =new File(rootPath + todayPath);
            
            if(!todayFolderForCreate.exists()){
                todayFolderForCreate.mkdirs();
            }

            String originalFilename = uploadFile.getOriginalFilename();
            String uuid = UUID.randomUUID().toString();
            long currentTime = System.currentTimeMillis();

            String fileName = uuid +"_"+ currentTime;

            String ext = originalFilename.substring(originalFilename.lastIndexOf("."));
            fileName += ext;

            try {
                uploadFile.transferTo(new File(rootPath+todayPath+fileName));
            } catch (Exception e) {
                e.printStackTrace();
            }

            ArticleImageDto articleImageDto = new ArticleImageDto();
            articleImageDto.setLocation(rootPath+fileName);
            articleImageDto.setOriginalFilename(originalFilename);
            articleImagelist.add(articleImageDto);

        }

        UserDto sessionUserInfo = (UserDto)session.getAttribute("sessionUserInfo");
        int pkId = sessionUserInfo.getId();
        boardDto.setUserId(pkId);
        boardService.registerArticle(boardDto,articleImagelist);
        return "redirect:/board/mainPage";
    }

    // 게시글 수정 페이지
    @RequestMapping("updateArticlePage")
    public String updateArticlePage(@RequestParam("id") int id, Model model){
        Map<String,Object> boardMap = boardService.getFindById(id);
        model.addAttribute("boardMap", boardMap);
        return"board/updateArticlePage";
    }

    // 게시글 수정 프로세스
    @RequestMapping("updateArticleProcess")
    public String updateArticleProcess(BoardDto boardDto){
        boardService.updateArticle(boardDto);
        return"redirect:/board/mainPage";
    }

    //게시글 삭제 프로세스
    @RequestMapping("deleteArticleProcess")
    public String deleteArticleProcess(BoardDto boardDto, HttpSession session){
        UserDto userDto = (UserDto)session.getAttribute("sessionUserInfo");
        
        if(boardDto.getUserId() ==  userDto.getId()){
            boardService.deleteArticle(boardDto);
        }

        return"redirect:/board/mainPage";
        
    }
    
    // 유저가 좋아요한 게시글 모아보기
    @RequestMapping("likeArticlePage")
    public String likeArticlePage(HttpSession session, Model model){
        UserDto sessionUserInfo = (UserDto)session.getAttribute("sessionUserInfo");
        model.addAttribute("boardlist", boardService.likeArticlePage(sessionUserInfo.getId()));
        return "board/likeArticlePage";
    }

}
