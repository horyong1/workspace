package com.ja.study.study1029.postlike.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ja.study.study1029.postlike.dto.PostLikeDto;
import com.ja.study.study1029.postlike.mapper.PostLikeSqlMapper;

@Service
public class PostLikeService {
    @Autowired
    private PostLikeSqlMapper postLikeSqlMapper;

    public int postLikeCount(int articleId){
        return postLikeSqlMapper.postLikeCount(articleId);
    }

    public PostLikeDto userLikeCount(PostLikeDto postLikeDto){
        return postLikeSqlMapper.userLikeCount(postLikeDto);
    }

    
    // public void userLikeboolean(PostLikeDto params){
    //     PostLikeDto dto = postLikeSqlMapper.userLikeCount(params);
        
    //     if(dto == null){
    //         postLikeSqlMapper.addLike(params);
    //     }else if(dto.getArticleLike() == 1){
    //         params.setArticleLike(2);
    //         postLikeSqlMapper.updateLike(params);
    //     }else if(dto.getArticleLike() == 2){
    //         params.setArticleLike(1);
    //         postLikeSqlMapper.updateLike(params);
    //     }    
    // }

    public int likeProcess(PostLikeDto postLikeDto){
        PostLikeDto dataCheck = postLikeSqlMapper.userLikeCount(postLikeDto);
        System.out.println("좋아요 값 확인 : " + dataCheck);
        if(dataCheck == null){
            postLikeSqlMapper.addLike(postLikeDto);
            return 1;
        }
        
        postLikeSqlMapper.postLikeDelete(postLikeDto);
        return 0;
    }
}
