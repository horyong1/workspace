package com.ja.study.study1029.comment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ja.study.study1029.comment.dto.CommentDto;
import com.ja.study.study1029.comment.service.CommentService;
import com.ja.study.study1029.user.dto.UserDto;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @RequestMapping("registerCommentProcess")
    public String registerCommentProcess(HttpSession session, CommentDto commentDto){
        UserDto sessionUserInfo = (UserDto)session.getAttribute("sessionUserInfo");
        commentDto.setUserId(sessionUserInfo.getId());
        commentDto.setNickname(sessionUserInfo.getNickname());
        commentService.addComment(commentDto);
        return "redirect:/board/detailPage/"+ commentDto.getArticleId();
    }
}
