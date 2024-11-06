package com.ja.study.study1029.postlike.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ja.study.study1029.postlike.dto.PostLikeDto;
import com.ja.study.study1029.postlike.service.PostLikeService;

@Controller
@ResponseBody
@RequestMapping("like")
public class PostLikeRestController {
    
    @Autowired
    private PostLikeService postLikeService;

    @RequestMapping("likeProcess")
    public Map<String,Integer> likeProcess(
        @RequestParam("userId") int userId,
        @RequestParam("articleId") int articleId){
        Map<String,Integer> map = new HashMap<>();

        
        // System.out.println("정보 >>> " + userId +" " +articleId);

        PostLikeDto postLikeDto = new PostLikeDto();
        postLikeDto.setUserId(userId);
        postLikeDto.setArticleId(articleId);
        
        map.put("likeResult", postLikeService.likeProcess(postLikeDto));
        map.put("likeCount",postLikeService.postLikeCount(articleId));
        return map;
    }
}
