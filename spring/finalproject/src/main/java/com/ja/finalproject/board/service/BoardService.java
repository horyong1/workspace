package com.ja.finalproject.board.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ja.finalproject.board.mapper.BoardSqlMapper;
import com.ja.finalproject.dto.ArticleImageDto;
import com.ja.finalproject.dto.CommentDto;
import com.ja.finalproject.dto.PostLikeDto;
import com.ja.finalproject.dto.ResponseArticleDto;
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
    @Transactional  // 트랜잭션 처리, 오류가 발생하면 롤백, 모두 성공하면 커밋
    public void registerArticle(articleDto articleDto, List<ArticleImageDto> articleImageList){
        boardSqlMapper.createBoard(articleDto);
        int articleId = articleDto.getId();
        for(ArticleImageDto articleImage : articleImageList){
            articleImage.setArticleId(articleId);
            boardSqlMapper.createArticleImage(articleImage);

        }
    }


    private Map<String,Object> getArticleMap(articleDto articleDto){
        int userPk = articleDto.getUserId();
        UserDto userDto = userSqlMapper.findById(userPk);
        Map<String,Object> map = new HashMap<>();
        map.put("articleDto", articleDto);
        map.put("userDto", userDto);

        return map;
    }

    private boolean idLessThan3(articleDto articleDto){
        return articleDto.getId() > 3;
    }

    // 게시판 전체 목록 가져오기
    public List<Map<String,Object>> getArticleList(String searchType,String searchWord, int page){
        // List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
        List<articleDto> articleDtoList = boardSqlMapper.findAll(searchType, searchWord,(page -1)*10);

        List<Map<String,Object>> result = articleDtoList.stream()
            // .filter(e -> idLessThan3(e)) // 3보다 큰 것만 가져오기
            .filter(this::idLessThan3) // 메서드 참조 방법
            // .map(e -> getArticleMap(e)) // articleDto를 Map으로 변환
            .map(this::getArticleMap) // 메서드 참조 방법
            .collect(Collectors.toList());  // List로 변환
        

        // articleDtoList.forEach( e -> {
        //     int userPk = e.getUserId();
        //     UserDto userDto = userSqlMapper.findById(userPk);
        //     Map<String,Object> map = new HashMap<>();
        //     map.put("articleDto", e);
        //     map.put("userDto", userDto);
        //     result.add(map);
        // });

        // for(articleDto articleDto : articleDtoList ){
        //     int userPk = articleDto.getUserId();
        //     UserDto userDto = userSqlMapper.findById(userPk);
            
        //     Map<String,Object> map = new HashMap<>();
        //     map.put("articleDto", articleDto);
        //     map.put("userDto", userDto);
        //     result.add(map);
        // }
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

    // @Builder 테스트 코드
    // @Builder는 생성자 대신 사용할 수 있는 빌더 패턴을 제공한다.
    // 빌더 패턴은 객체 생성시 매개변수가 많을 때 사용하면 좋다.
    // 빌더를 사용하면 어떤 필드에 어떤 값을 넣는지 명확하게 알 수 있다.
    public void test(){
        ResponseArticleDto test = ResponseArticleDto.builder()
                                .id(1)
                                .userId(1)
                                .title("test")
                                .content("test")
                                .readCount(1)
                                .createdAt(new Date())
                                .build();
    }

      // 게시판 전체 목록 가져오기
    public List<Map<String,Object>> getArticleListTest(String searchType,String searchWord, int page){
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
}
