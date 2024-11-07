package com.ja.study.study1029.postlike.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ja.study.study1029.postlike.dto.PostLikeDto;

@Mapper
public interface PostLikeSqlMapper {

    void addLike(PostLikeDto postLikeDto);
    void updateLike(PostLikeDto postLikeDto);
    int postLikeCount(int articleId);
    PostLikeDto userLikeCount(PostLikeDto postLikeDto);
    List<PostLikeDto> likeArticleList(int id);

    void postLikeDelete(PostLikeDto postLikeDto);
}
