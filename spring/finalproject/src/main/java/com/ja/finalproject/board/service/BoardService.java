package com.ja.finalproject.board.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.attoparser.dom.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ja.finalproject.board.mapper.BoardSqlMapper;
import com.ja.finalproject.dto.ArticleImageDto;
import com.ja.finalproject.dto.CommentDto;
import com.ja.finalproject.dto.PostLikeDto;
import com.ja.finalproject.dto.UserDto;
import com.ja.finalproject.dto.articleDto;
import com.ja.finalproject.user.mapper.UserSqlMapper;

@Service
public class BoardService {

    @Autowired
    private BoardSqlMapper boardSqlMapper;

    @Autowired
    private UserSqlMapper userSqlMapper;

    // 글 쓰기
    public void registerArticle(articleDto articleDto, List<ArticleImageDto> articleImageList){
        boardSqlMapper.createBoard(articleDto);
        int articleId = articleDto.getId();
        for(ArticleImageDto articleImage : articleImageList){
            articleImage.setArticleId(articleId);
            boardSqlMapper.createArticleImage(articleImage);

        }
    }

    // 게시판 전체 목록 가져오기
    public List<Map<String,Object>> getArticleList(String searchType,String searchWord, int page){
        List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
        List<articleDto> articleDtoList = boardSqlMapper.findAll(searchType, searchWord,(page -1)*10);
        
        for(articleDto articleDto : articleDtoList ){
            int userPk = articleDto.getUserId();
            UserDto userDto = userSqlMapper.findById(userPk);
            
            Map<String,Object> map = new HashMap<>();
            map.put("articleDto", articleDto);
            map.put("userDto", userDto);
            result.add(map);
        }
        return result;
    }

    public int getTotalArticleCount(String searchType,String searchWord){
        return boardSqlMapper.getTotalArticleCount(searchType, searchWord);
    }

    // 번호로 게시판 글 상세 목록 가져오기
    public Map<String,Object> getArticle(int id){
        articleDto articleDto = boardSqlMapper.selectFindByNoContent(id);
        int userPk = articleDto.getUserId();
        UserDto userDto = userSqlMapper.findById(userPk);
        List<ArticleImageDto> articleImageDtoList = boardSqlMapper.findImageByArticleId(id);

        Map<String,Object> map = new HashMap<>();
        map.put("articleDto", articleDto);
        map.put("userDto", userDto);
        map.put("articleImageDtoList",articleImageDtoList);

        return map;
    }

    // 조회수 증가
    public void addReadCount(int no){
        boardSqlMapper.increaseReadCount(no);
    }

    // 게시글 삭제
    public void deleteArticle(int id){
        boardSqlMapper.deleteById(id);
    }

    // 게시글 수정
    public void update(articleDto articleDto){
        boardSqlMapper.update(articleDto);
    }

    // 좋아요 등록
    public void like(PostLikeDto postLikeDto){
        boardSqlMapper.createLike(postLikeDto);
    }
    // 좋아요 삭제
    public void unlike(PostLikeDto postLikeDto){
        boardSqlMapper.deleteLike(postLikeDto);
    }
    // 게시글 좋아요 개수
    public int getTotalLikeCount(int articleId){
       return boardSqlMapper.getTotalLikeCount(articleId);
    }
    // 내가 누른 좋아요 확인
    public boolean isUserLike(PostLikeDto postLikeDto){
        return boardSqlMapper.getMyLikeCount(postLikeDto) > 0 ? true : false;
    }


    // 댓글 
    public void registerComment(CommentDto commentDto){
        boardSqlMapper.createComment(commentDto);
    }

    public void deleteComment(int id){
        boardSqlMapper.deleteCommentById(id);
    }
    public void updateComment(CommentDto commentDto){
        boardSqlMapper.updateCommentById(commentDto);
    }

    public List<Map<String,Object>> getCommentList(int articleId){
        List<Map<String,Object>> result =  new ArrayList<>();
        List<CommentDto> commentDtoList = boardSqlMapper.selectCommentByArticleId(articleId);

        for(CommentDto commentDto : commentDtoList){
            UserDto userDto =  userSqlMapper.findById(commentDto.getUser_id());
            Map<String, Object> map = new HashMap<>();
            map.put("commentDto", commentDto);
            map.put("userDto", userDto);
            result.add(map);

        }
        return result;   
    }
}
