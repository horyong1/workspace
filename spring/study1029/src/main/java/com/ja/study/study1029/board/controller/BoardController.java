package com.ja.study.study1029.board.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.StringUtils;

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
    public String mainPage(Model model){   
        List<Map<String,Object>> boardlist = boardService.findAll();
        model.addAttribute("boardlist", boardlist);
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
    public String writeArticleProcess(BoardDto boardDto, HttpSession session){
        UserDto sessionUserInfo = (UserDto)session.getAttribute("sessionUserInfo");
        int pkId = sessionUserInfo.getId();
        boardDto.setUserId(pkId);
        boardService.registerArticle(boardDto);
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

    @RequestMapping("articleTitleSearchProcess")
    public String articleTitleSearchProcess(
        @RequestParam("select")String select,
        @RequestParam("search")String search,
        Model model){
            
        model.addAttribute("boardlist", boardService.findByContent(select,search));
        return "board/mainPage";
    }
    
    @RequestMapping("likeArticlePage")
    public String likeArticlePage(HttpSession session, Model model){
        UserDto sessionUserInfo = (UserDto)session.getAttribute("sessionUserInfo");
        model.addAttribute("boardlist", boardService.likeArticlePage(sessionUserInfo.getId()));
        return "board/likeArticlePage";
    }

}
