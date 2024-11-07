package com.ja.study.study1029.postlike.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ja.study.study1029.postlike.dto.PostLikeDto;
import com.ja.study.study1029.postlike.service.PostLikeService;
import com.ja.study.study1029.user.dto.UserDto;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("postlike")
public class PostLikeController {

    @Autowired
    private PostLikeService postLikeService;

    // @RequestMapping("articleLikeProcess")
    // public String thumbsUp(PostLikeDto params, HttpSession session){
    //     int id = params.getArticleId();
    //     UserDto userDto = (UserDto)session.getAttribute("sessionUserInfo");
    //     params.setUserId(userDto.getId());
        
    //     // postLikeService.userLikeboolean(params);
    //     return "redirect:/board/detailPage/"+ id;
    // }
    
}
