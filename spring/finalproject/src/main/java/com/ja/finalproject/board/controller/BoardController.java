package com.ja.finalproject.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
        List<articleDto> list = boardService.getBoardList();
        for(articleDto a: list){
            System.out.println("정보 >>> " + a);
        }
        model.addAttribute("list", list);
        return"board/mainPage";
    }

    /*
     * 글 상세 보기
     */
    @RequestMapping("detailPage/{id}")
    public String getBoardDetail(@PathVariable("id") int no, Model model){
        boardService.addReadCount(no);
        articleDto dto = boardService.findByNoContent(no);
        model.addAttribute("dto", dto);
        return"board/boardDetailPage";
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
        params.setUserid(userPk);
        boardService.registerArticle(params);
        return "redirect:/board/mainPage";
    }
}
