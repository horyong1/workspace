package com.ja.study.study1029.comment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ja.study.study1029.comment.dto.CommentDto;
import com.ja.study.study1029.comment.dto.RestResponseDto;
import com.ja.study.study1029.comment.service.CommentService;

@RestController
@RequestMapping("api/board")
public class CommentRestComtroller {

    @Autowired
    private CommentService commentService;

    @RequestMapping("registComment")
    public RestResponseDto registComment(@RequestBody CommentDto params){
        RestResponseDto responseDto = new RestResponseDto();
        System.out.println(params);
        commentService.addComment(params);

        return responseDto;
    }

    @RequestMapping("getCommentList")
    public RestResponseDto getCommentList(@RequestParam("articleId") int articleId){
        RestResponseDto responseDto = new RestResponseDto();
        responseDto.add("commentList", commentService.getList(articleId));
        return responseDto;
    }
}
