package org.example.bookpurchase.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.bookpurchase.domain.User;
import org.example.bookpurchase.dto.UserDto;
import org.example.bookpurchase.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;


    public User findByUseId(Long userId){
        User user = userRepository.findByUserId(userId);
//        log.info("유저찾기:{}",user.getUser_id());
        return user;

    }
    public List<User> findByUsers(){
        return userRepository.findAll();
    }

    public User creat(UserDto userDto){
        return userRepository.save(User.toEntity(userDto));
    }

    public User login(HashMap<String, Objects> hashMap){
//        log.info(String.valueOf(hashMap.get("identification")));
        User user = userRepository.findByIdentificationAndPassword(
                String.valueOf(hashMap.get("identification")),
                String.valueOf(hashMap.get("password"))
        ).orElseThrow(() -> new NullPointerException("해당 회원이 없습니다."));//옵션널 사용하는이유 만약 널이면 에러
//        log.info("user: {} ", user.getIdentification());
        return user;
    }


}
