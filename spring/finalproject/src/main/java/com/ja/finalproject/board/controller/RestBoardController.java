package com.ja.finalproject.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ja.finalproject.board.service.BoardService;
import com.ja.finalproject.dto.CommentDto;
import com.ja.finalproject.dto.PostLikeDto;
import com.ja.finalproject.dto.RestResponseDto;
import com.ja.finalproject.dto.UserDto;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("api/board")
public class RestBoardController {

    @Autowired
    private BoardService boardService;

    

    @RequestMapping("like")
    public RestResponseDto like(PostLikeDto params,
    HttpSession session){
        RestResponseDto responseDto = new RestResponseDto();
        responseDto.setResult("success");
        
        UserDto userDto = (UserDto)session.getAttribute("sessionUserInfo");
        params.setUser_id(userDto.getId());
        
        boardService.like(params);

        return responseDto;

    }

    @RequestMapping("unlike")
    public RestResponseDto unlike(PostLikeDto params,
    HttpSession session){
        RestResponseDto responseDto = new RestResponseDto();
        responseDto.setResult("success");

        UserDto userDto = (UserDto)session.getAttribute("sessionUserInfo");
        params.setUser_id(userDto.getId());
        
        boardService.unlike(params);

        return responseDto;

    }
    @RequestMapping("getTotalLikeCount")
    public RestResponseDto getTotalLikeCount(@RequestParam("articleId")int article_id){
        RestResponseDto responseDto = new RestResponseDto();
        responseDto.setResult("success");

        responseDto.add("count", boardService.getTotalLikeCount(article_id));

        return responseDto;

    }
    @RequestMapping("isUserLike")
    public RestResponseDto isUserLike(PostLikeDto params,
    HttpSession session){
        RestResponseDto responseDto = new RestResponseDto();
        responseDto.setResult("success");
        
        UserDto userDto = (UserDto)session.getAttribute("sessionUserInfo");
        params.setUser_id(userDto.getId());

        responseDto.add("isLike", boardService.isUserLike(params));

        return responseDto;

    }


    // 댓글 api 
    @RequestMapping("registerComment")
    public RestResponseDto registerComment(HttpSession session,CommentDto params){
        RestResponseDto responseDto = new RestResponseDto();
        responseDto.setResult("success");

        UserDto sessionUserInfo = (UserDto)session.getAttribute("sessionUserInfo");
        params.setUser_id(sessionUserInfo.getId());

        boardService.registerComment(params);

        return responseDto;
    }
    
    
    @RequestMapping("deleteComment")
    public RestResponseDto deleteComment(@RequestParam("commentId") int commentId){
        RestResponseDto responseDto = new RestResponseDto();
        responseDto.setResult("success");

        boardService.deleteComment(commentId);

        return responseDto;
    }
    @RequestMapping("updateComment")
    public RestResponseDto updateComment(CommentDto params){
        RestResponseDto responseDto = new RestResponseDto();
        responseDto.setResult("success");

        System.out.println("정보 :: " + params);
        boardService.updateComment(params);

        return responseDto;
    }

    @RequestMapping("getCommentList")
    public RestResponseDto getCommentList(@RequestParam("articleId")int articleId){
        RestResponseDto responseDto = new RestResponseDto();
        responseDto.setResult("success");

        responseDto.add("commentList", boardService.getCommentList(articleId));

        return responseDto;
    }
}
