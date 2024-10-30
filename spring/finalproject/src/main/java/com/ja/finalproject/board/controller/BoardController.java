package com.ja.finalproject.board.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ja.finalproject.board.service.BoardService;
import com.ja.finalproject.dto.articleDto;
import com.ja.finalproject.dto.UserDto;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("board")
public class BoardController {

    @Autowired
    private BoardService boardService;

    // 게시판 목록
    @RequestMapping("mainPage")
    public String mainPage(Model model){
        List<Map<String,Object>> list = boardService.getArticleList();
        model.addAttribute("list", list);
        return"board/mainPage";
    }

    /*
     * 글 상세 보기
     */
    @RequestMapping("articleDetailPage")
    public String articleDetailPage(@RequestParam("id") int id, Model model){
        boardService.addReadCount(id);
        Map<String,Object> map = boardService.getArticle(id);
        model.addAttribute("map", map);
        System.out.println("정보 >>> " +map);
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
    public String writeArticleProcess(articleDto params, HttpSession session){
        // 세션값 세팅 하는 방법
        UserDto sessionUserInfo = (UserDto)session.getAttribute("sessionUserInfo");
        int userPk = sessionUserInfo.getId();
        params.setUserId(userPk);
        boardService.registerArticle(params);
        return "redirect:/board/mainPage";
    }
}
