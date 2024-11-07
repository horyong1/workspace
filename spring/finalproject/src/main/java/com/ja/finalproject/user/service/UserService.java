package com.ja.finalproject.user.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.ja.finalproject.dto.HobbyCategoryDto;
import com.ja.finalproject.dto.MailAuthDto;
import com.ja.finalproject.dto.UserDto;
import com.ja.finalproject.dto.UserHobbyDto;
import com.ja.finalproject.user.mapper.UserSqlMapper;

import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;

@Service
public class UserService {
    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private UserSqlMapper userSqlMapper;
    public void addUser(UserDto userDto,List<Integer> hobbyIdList){
        userSqlMapper.createUser(userDto);
        int lastUserPk = userDto.getId();
        for(int hobbyId : hobbyIdList){
            UserHobbyDto userHobbyDto = new UserHobbyDto();
            userHobbyDto.setUserId(lastUserPk);
            userHobbyDto.setHobbyId(hobbyId);
            userSqlMapper.createUserHobby(userHobbyDto);
        }

        // 인증번호 생성
        String authKey = UUID.randomUUID().toString();

        MailAuthDto mailAuthDto = new MailAuthDto();
        mailAuthDto.setUser_id(lastUserPk);
        mailAuthDto.setAuth_key(authKey);
        userSqlMapper.createMailAuth(mailAuthDto);
        String email = userDto.getEmail();

        
        new MailSendThead(javaMailSender, email , authKey);
        
    }

    public UserDto getUserByUserIdAndPassword(UserDto user){
        return userSqlMapper.findByUserIdAndPassword(user);
    }

    public List<HobbyCategoryDto> getHobbyList(){
        return userSqlMapper.findHobbyCategoryAll();
    }

    // 인증
    public void authenticateMail(String key){
        userSqlMapper.updateMailAuthComplete(key);
    }

    // 아이디 중복검사
    public boolean existsByUserId(String userId){
        return userSqlMapper.countUserByUserId(userId) > 0 ? true : false;
    }
}

@AllArgsConstructor
class MailSendThead extends Thread{
    
    private JavaMailSender javaMailSender;
    private String toMailadress;
    private String authKey;

    @Override
    public void run(){

        try{
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,"utf-8");
            
            mimeMessageHelper.setSubject("[FP]회원가입을 축하드립니다.");

            String text = "회원 가입을 축하드립니다.<br>";
            text += "아래 링크를 클릭하여 이메일 인증 완료 후 로그인 가능합니다.<br>";
            // text += "인증번호 : " + authkey + "<br>";
            text += "<a href='http:localhost:8888/user/mailAuthProcess?key="+authKey+"'>인증 하기</a>";

            mimeMessageHelper.setText(text,true);
            mimeMessageHelper.setTo(toMailadress);

            mimeMessageHelper.setFrom("admin","최종 예제 관리자");


            javaMailSender.send(mimeMessage);
            

        }catch(Exception e){
            e.printStackTrace();
        }


    }

    
}
