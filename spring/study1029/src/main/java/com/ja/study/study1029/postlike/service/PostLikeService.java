package com.ja.study.study1029.postlike.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ja.study.study1029.postlike.dto.PostLikeDto;
import com.ja.study.study1029.postlike.mapper.PostLikeSqlMapper;

@Service
public class PostLikeService {
    @Autowired
    private PostLikeSqlMapper postLikeSqlMapper;

    public int postLikeCount(int id){
        return postLikeSqlMapper.postLikeCount(id);
    }
    public PostLikeDto userLikeCount(PostLikeDto postLikeDto){
        return postLikeSqlMapper.userLikeCount(postLikeDto);
    }

    
    public void userLikeboolean(PostLikeDto params){
        PostLikeDto dto = postLikeSqlMapper.userLikeCount(params);
        
        if(dto == null){
            postLikeSqlMapper.addLike(params);
        }else if(dto.getArticleLike() == 1){
            params.setArticleLike(0);
            postLikeSqlMapper.updateLike(params);
        }else if(dto.getArticleLike() == 0){
            params.setArticleLike(1);
            postLikeSqlMapper.updateLike(params);
        }    
    }
}
