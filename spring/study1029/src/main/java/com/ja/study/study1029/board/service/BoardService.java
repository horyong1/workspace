package com.ja.study.study1029.board.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ja.study.study1029.board.dto.BoardDto;
import com.ja.study.study1029.board.mapper.BoardSqlMapper;
import com.ja.study.study1029.comment.mapper.CommentSqlMapper;
import com.ja.study.study1029.postlike.dto.PostLikeDto;
import com.ja.study.study1029.postlike.mapper.PostLikeSqlMapper;
import com.ja.study.study1029.user.dto.UserDto;
import com.ja.study.study1029.user.mapper.UserSqlMapper;




@Service
public class BoardService {

    @Autowired
    private BoardSqlMapper boardSqlMapper;
    @Autowired
    private UserSqlMapper userSqlMapper;
    @Autowired
    private CommentSqlMapper commentSqlMapper;
    @Autowired 
    private PostLikeSqlMapper postLikeSqlMapper;

    // 게시글 전체 목록
    public List<Map<String,Object>> findAll(String searchType, String searchWord, int page){
        System.out.println("페이지 넘겨받기 " + page +" "+ (page -1)*10);
        List<BoardDto> boardDtoList = boardSqlMapper.findAll(searchType,searchWord,(page -1)*10);
        List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();

        for(BoardDto boardDto : boardDtoList){
            int userId = boardDto.getUserId();
            UserDto userDto = userSqlMapper.findById(userId);
            int commentCount = commentSqlMapper.commentsCount(boardDto.getId());
            int postLikeCount = postLikeSqlMapper.postLikeCount(boardDto.getId());

            Map<String, Object> map = new HashMap<>();
            map.put("boardDto", boardDto);
            map.put("userDto", userDto);
            map.put("commentCount", commentCount);
            map.put("postLikeCount",postLikeCount);

            result.add(map);
        }
        return result;
    }

    // 상세 페이지 
    public Map<String, Object> getFindById(int id){
        Map<String, Object> result = new HashMap<>();
        BoardDto boardDto = boardSqlMapper.findById(id);
        int userId = boardDto.getUserId();
        UserDto userDto = userSqlMapper.findById(userId);
        result.put("boardDto", boardDto);
        result.put("userDto", userDto);
        return result;
    }

    // 조회수 증가
    public void addReadCount(int id){
        boardSqlMapper.addReadCount(id);
    }

    // 게시글 등록 하기
    public void registerArticle(BoardDto boardDto){
        boardSqlMapper.addArticle(boardDto);
    }

    // 게시글 수정 하기
    public void updateArticle(BoardDto boardto){
        boardSqlMapper.updateArticle(boardto);
    }

    // 게시글 수정 하기
    public void deleteArticle(BoardDto boardto){
        boardSqlMapper.deleteArticle(boardto);
    }

    // 좋아요한 게시글 목록
    public List<Map<String,Object>> likeArticlePage(int id){
        List<Map<String,Object>> boardDtoList = new ArrayList<>();
        List<PostLikeDto> postLikeDtos = postLikeSqlMapper.likeArticleList(id);
        
        for(PostLikeDto dto : postLikeDtos ){
            int articleId = dto.getArticleId();
            int userId = dto.getUserId();
            BoardDto boardDto = boardSqlMapper.findById(articleId);
            UserDto userDto = userSqlMapper.findById(userId);
            int commentCount = commentSqlMapper.commentsCount(boardDto.getId());
            int postLikeCount = postLikeSqlMapper.postLikeCount(boardDto.getId());

            Map<String, Object> map = new HashMap<>();
            map.put("boardDto", boardDto);
            map.put("userDto", userDto);
            map.put("commentCount", commentCount);
            map.put("postLikeCount", postLikeCount);

            boardDtoList.add(map);
        }
        return boardDtoList;
    }

    public int getArticlePageCount(String searchType, String searchWord){
        return boardSqlMapper.getArticlePageCount(searchType, searchWord);
    }
}
