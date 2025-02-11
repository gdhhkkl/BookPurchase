package org.example.bookpurchase.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.Lombok;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.bookpurchase.domain.Cart;
import org.example.bookpurchase.domain.CartList;
import org.example.bookpurchase.domain.User;
import org.example.bookpurchase.dto.BookDto;
import org.example.bookpurchase.dto.CartDto;
import org.example.bookpurchase.dto.UserDto;
import org.example.bookpurchase.service.BookService;
import org.example.bookpurchase.service.CartService;
import org.example.bookpurchase.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor//final 이나 @NotNull인 필드 값만 파라미터로 받는 생성자를 생성
@Controller//프리젠테이션 개층
@Slf4j
//데이터를 받는것과 페이지를 보여주는게 주 역확
public class Home {
//    private static final Logger log = LoggerFactory.getLogger(Home.class);
//    @Autowired
    private final BookService bookService;
//    @Autowired
    private final UserService userService;
    private final CartService cartService;
//    @Autowired//
//    public Home(BookService bookService){
//        this.bookService = bookService;
//    }


    @GetMapping("/")//메인페이지
    public String list(HttpSession httpSession, Model model){
        List<BookDto> bookDto = bookService.findAll();
//        log.info("user:",session.getAttribute("user_id"));

        log.info("sesson : {}", httpSession.getAttribute("user_id"));

        model.addAttribute("book",bookDto);
        model.addAttribute("user_id", httpSession.getAttribute("user_id"));//로그아웃할때
        return "home";
    }

    @GetMapping("/book")
    public String DitailBook(Model model, @RequestParam(value = "book_number") Long book_number){
//        log.info("id : {}", book_number);
        BookDto bookDto = bookService.findById(book_number);
        model.addAttribute("bookD", bookDto);
        return "bookDetail";
    }

    @GetMapping("/order")
    public String order(){
        return "order";
    }

    @GetMapping("/cart")
    public String cart(Model model){

        model.addAttribute("cart",cartService.findAll());

        return "cart";
    }

    // post controller
    // book 검색 -> findById
    // user 검색 -> findByUserId
    // service add Cart
    // return redirect:
    @PostMapping("/cart")//@RequestParam HashMap<String, Object> map=>Long bookId = Long.valueOf(map.get("bookId").toString());=>Long.valueOf(bookId)
    public String cart(HttpSession session,@RequestParam( value = "book_number" )Long book_number ){
//        log.info("책이다:{}",book_number);
        Long userId = Long.valueOf(String.valueOf(session.getAttribute("user_id")));//세션으로 로그인 아이디 저장
//        log.info("유저들어온겨:{}",userId);
//        Long book_number = Long.valueOf(map.get("bookId").toString());
//        log.info("유저세션:{}",userId);

         cartService.cart(userId, book_number);

//        log.info("담겼니?:{}",id);


        return  "redirect:/";
    }


    @GetMapping("/login")
    public String login(HttpServletRequest httpServletRequest){//
        httpServletRequest.getCookies();//(클라이언트가 요청과 함께 보낸 모든 객체를 포함한 배열을 반환한다.)
        return "login";
    }

    @PostMapping("/login")
    public String login(HttpSession httpSession, @RequestParam HashMap<String, Objects> hashMap){
//        log.info("id왜 null나옴?:{}", hashMap.get("identification"));
        User user = userService.login(hashMap);//헤시맵으로 던짐
//        log.info("user_id: {}",user.getIdentification());
        httpSession.setAttribute("user_id", user.getIdentification().toString());
        log.info("sesson : {}", httpSession.getAttribute("user_id"));
        return "redirect:/";//파라미터 주소
    }

    @GetMapping("/logout")
    public String logout(HttpSession httpSession){
        httpSession.setAttribute("user_id", null);
        return "redirect:/";
    }




    @GetMapping("/join")
    public String join(){
        return "join";
    }
    @PostMapping("/join")
    public String joins(UserDto userDto){
//        log.info("내용 : {}",userDto);
        userService.creat(userDto);

        return "redirect:/";
    }
    @GetMapping("/myPage")
    public String myPage(){
        return "myPage";
    }
}
