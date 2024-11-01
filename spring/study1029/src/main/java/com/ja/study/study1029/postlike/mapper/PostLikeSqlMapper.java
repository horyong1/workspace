package com.ja.study.study1029.postlike.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ja.study.study1029.postlike.dto.PostLikeDto;

@Mapper
public interface PostLikeSqlMapper {

    void addLike(PostLikeDto postLikeDto);
    void updateLike(PostLikeDto postLikeDto);
    int postLikeCount(int id);
    PostLikeDto userLikeCount(PostLikeDto postLikeDto);

}
