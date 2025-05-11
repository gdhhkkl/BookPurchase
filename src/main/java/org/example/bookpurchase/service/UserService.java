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


    public User findByUserId(Long userId){
        User user = userRepository.findByUserId(String.valueOf(userId)).orElseThrow(() -> new NullPointerException("유저가 존재하지 않습니다."));
//        log.info("유저찾기:{}",user.getUser_id());
        return user;

    }
//    public List<User> findByUsers(){
//        return userRepository.findAll();
//    }

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
//    public User findByName(String name){
//        User user = userRepository.findByName(name);
//
//        return user;
//    }
//    public User findCart(Long cart_id){
//        User user = userRepository.findUserByCarts(cart_id);
//        log.info("findCarts:{}",user.getCarts());
//        return user;
//    }
    public User checkUser (UserDto userDto){
        String id = userDto.getIdentification();
        String  pw = userDto.getPassword();
        List<User> findSameUser = userRepository.findByIdANdPw(id, pw);
        List<User> findSomeUser = userRepository.findByIdentification(id);
        List<User> findByPw = userRepository.findByPassword(pw);
        if(!findSameUser.isEmpty()){
            throw new IllegalArgumentException("회당 아이와 비번은 이미 존재합니다.");
        } else if (!findSomeUser.isEmpty()) {
            throw new IllegalArgumentException("회당 아이디는 이미 존재합니다.");
        }else if(!findByPw.isEmpty()){
            throw new IllegalArgumentException("해당 비번은 이미 존재합니다.");
        }
        return User.toEntity(userDto);
    }
    public List<User> findUSer(String id, String pw){
        List<User> user = userRepository.findByIdANdPw(id, pw);
        return user;
    }

    public User finduser(String userIdentification){
        return userRepository.findByUserIdentification(userIdentification);
    }

}
