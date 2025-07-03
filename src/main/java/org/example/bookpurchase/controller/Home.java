package org.example.bookpurchase.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.example.bookpurchase.domain.*;
import org.example.bookpurchase.dto.*;
import org.example.bookpurchase.repository.CouponListRepository;
import org.example.bookpurchase.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor//final 이나 @NotNull인 필드 값만 파라미터로 받는 생성자를 생성
@Controller//프리젠테이션 개층
@Slf4j

public class Home {

    private final BookService bookService;
//    @Autowired
    private final UserService userService;
    private final CartService cartService;
    private final OrderService orderService;
    private final AddressService addressService;
    private final CardService cardService;
    private final CartListService cartListService;
    private final CouponService couponService;
    private final CouponListService couponListService;
    private final CouponListRepository couponListRepository;

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
        BookDto bookDto = BookDto.of(bookService.findById(book_number));

        model.addAttribute("bookD", bookDto);
        return "bookDetail";
    }
    // post controller
    // book 검색 -> findById
    // user 검색 -> findByUserId
    // service add Cart
    // return redirect:
    @PostMapping("/cart")//@RequestParam HashMap<String, Object> map=>Long bookId = Long.valueOf(map.get("bookId").toString());=>Long.valueOf(bookId)
    public String cart(HttpSession session,@RequestParam( value = "book_number" )Long book_number, Long count,Model model ){
        log.info("책수량 넣어왔나?{}",count);


        if(session.getAttribute("user_id")==null){
            model.addAttribute("message","로그인 후 이용해 주세요");
            model.addAttribute("searchUrl","/login");
            return "message";
        }
        Long userId = Long.valueOf(String.valueOf(session.getAttribute("user_id")));//세션으로 로그인 아이디 저장
        cartService.cart(userId, book_number , count);

        //여기서 책 수량 체크

        return  "redirect:/";
    }

    @GetMapping("/order")//주문내역보여주는것
    public String order(Model model,HttpSession httpSession){

        if(httpSession.getAttribute("user_id")==null){
            model.addAttribute("message","로그인 후 이용해 주세요");
            model.addAttribute("searchUrl","/login");
            return "message";
        }

        User user= userService.findByUserId(Long.valueOf(String.valueOf(httpSession.getAttribute("user_id"))));
        model.addAttribute("user", user);

        Long userId = Long.valueOf(String.valueOf(httpSession.getAttribute("user_id")));
        List<OrderList> orderLists =orderService.findOrder(userId);
        System.out.println(Arrays.toString(orderLists.stream().toArray()));
        model.addAttribute("order", orderLists);
        

        return "orderList";
    }


    @PostMapping("/order")//주문하기
//    public String order(HttpSession httpSession, @RequestParam(value = "book_number")Long book_number,@PathVariable String basicAddress){
        public String order(HttpSession httpSession, @RequestParam HashMap<String, Objects> data, @RequestParam("price")Long Price, @RequestParam("coupon")String couponType){
            log.info("주문가격:{}",Price);
            Long userId = Long.valueOf(String.valueOf(httpSession.getAttribute("user_id")));
            log.info("useId in to order post:{}", userId);
//            log.info("address:{}",data.get("basicAddress"));
//            Long book_number = Long.valueOf(String.valueOf(data.get("book_number")));

            String basicAddress = String.valueOf(data.get("basicAddress"));
            String cardNumber = String.valueOf(data.get("cardNumber"));
            if(couponType!=null){

                orderService.saveOrder(basicAddress,cardNumber,userId,Price,couponType);
                CouPonList couPonList = couponListService.findCoupon(couponType);
                couponListService.useCoupon(couPonList);


            }else{
                log.info("카드버호:{}",cardNumber);
                orderService.order(userId,basicAddress,cardNumber);

            }


            //쿠폰 사용여부 여기서 처리해줘야함

        return "redirect:/";

    }

    @GetMapping("/orderNow")
    public String orderNow(Model model ,HttpSession httpSession,@RequestParam("book_number")Long book_number,@RequestParam("bookCount")Long count){
        Long userId = Long.valueOf(String.valueOf(httpSession.getAttribute("user_id")));
        if(userId==null){
            model.addAttribute("message","로그인 후 이용해 주세요");
            model.addAttribute("searchUrl","/login");
            return "message";
        }

        List<Address> address = addressService.findById(userId);

        model.addAttribute("address", address);
        List<Card> cards = cardService.findCard(userId);
        model.addAttribute("card",cards);

        model.addAttribute("book_number", book_number);
        model.addAttribute("bookCount", count);

        List<Book> book =bookService.findBookId(book_number);
        Book bookCheck = bookService.findByBookId(book_number);
        log.info("바로주문 책 넘버:{}",bookCheck.getBook_number());
        Long price = bookCheck.getPrice()*count;
        log.info("총 가격:{}", price);
        model.addAttribute("price", price);

        model.addAttribute("book",book);

        User user = userService.findByUserId(userId);

        Coupon coupon = couponService.findCouponId(user.getUser_id());

        List<CouPonList> couPonLists = couponListService.findCouponList(coupon.getId());

        model.addAttribute("coupon", couPonLists);

        
        return "orderNow";//주소만 저장이되고 책은 저장이 안되세요;
    }

    @PostMapping("/orderNow")
    public String orderNow(HttpSession httpSession, @RequestParam HashMap<String, Objects> data, @RequestParam("book_number") Long bookNumber, @RequestParam("bookCount")Long count, @RequestParam("bookPrice")Long price){
        Long userId = Long.valueOf(String.valueOf(httpSession.getAttribute("user_id")));
//        log.info("!!! : {}", countNumber);
//        log.info("useId in to order post:{}", userId);
//            log.info("address:{}",data.get("basicAddress"));
//            Long book_number = Long.valueOf(String.valueOf(data.get("book_number")));

        String basicAddress = String.valueOf(data.get("basicAddress"));
        String cardNumber = String.valueOf(data.get("cardNumber"));

        orderService.orderNowBook(userId, basicAddress, cardNumber, bookNumber, count, price);
        return "redirect:/";
    }

    @GetMapping("/orders")//주문페이지
    public String orders(Model model, HttpSession httpSession ){
        Long userId = Long.valueOf(String.valueOf(httpSession.getAttribute("user_id")));

        List<Address> address = addressService.findById(userId);

        List<Card> cards = cardService.findCard(userId);
        model.addAttribute("address", address);
        model.addAttribute("card",cards);

        Cart cart = cartService.cart(userId);
        List<CartList> cartLists = cartListService.findCartList(cart.getCart_id());
        model.addAttribute("cartList", cartLists);

        User user = userService.findByUserId(userId);

        Coupon coupon = couponService.findCouponId(user.getUser_id());


        List<CouPonList> couPonLists = couponListService.findCouponList(coupon.getId());
        

        model.addAttribute("coupon", couPonLists);
        List<CartList> carts= cartService.findCart(userId);
        Long cartTotalPrice = cartService.cartTotalPrice(carts);
        model.addAttribute("price", cartTotalPrice);

        return "order";
    }

    @GetMapping("/cart")
    public String cart(Model model, HttpSession httpSession){
        if(httpSession.getAttribute("user_id")==null){
            model.addAttribute("message","로그인 후 이용해 주세요");
            model.addAttribute("searchUrl","/login");
            return "message";
        }
        Long userId = Long.valueOf(String.valueOf(httpSession.getAttribute("user_id")));;


        log.info("유저 들어왔니?:{}",userId);
        if(cartService.findCartId(userId) ==null){
            model.addAttribute("message","책을 담아주세요");
            model.addAttribute("searchUrl","/"); //왜 안먹니?
            return "message";
        }else {
            List<CartList> cart= cartService.findCart(userId);
            Long cartTotalPrice = cartService.cartTotalPrice(cart);
            model.addAttribute("cart",cart);
            model.addAttribute("price", cartTotalPrice);
        }


        return "cart";
    }


    @PostMapping ("/delete")
    public String delete(@RequestParam("cart_list_id") Long cart_id, @RequestParam("book_number")Long book_number ){//왜 책 아이디를 가져온겨? 카트리스트 번호는 안되나?
        log.info("삭제할것:{}",book_number.getClass());


        cartListService.delete(cart_id, book_number);
        return "redirect:/cart";
    }

    @GetMapping("/login")
    public String login(HttpServletRequest httpServletRequest){//
        httpServletRequest.getCookies();//(클라이언트가 요청과 함께 보낸 모든 객체를 포함한 배열을 반환한다.)
        return "login";
    }

    @PostMapping("/login")
    public String login(HttpSession httpSession, @RequestParam HashMap<String, Objects> hashMap, Model model){
        String id = String.valueOf(hashMap.get("identification"));// hashMap에서 objects로 던지기 때문에 .equals로 하면 비교가 안되서 걍 nullExessetion터지느겨..
        String pw = String.valueOf(hashMap.get("password"));
        List<User> userIdAndPw= userService.findUSer(id, pw);

        if(id.equals("") || pw.equals("")){
            model.addAttribute("message","제대로 입력되지 않았습니다. 다시 작성해주세요");
            model.addAttribute("searchUrl","/login");
            return "message";
        } else if (userIdAndPw.isEmpty()) {
            model.addAttribute("message","없는 해원입니다. 회원가입해주세요");
            model.addAttribute("searchUrl","/join");
            return "message";
        }
        User user = userService.login(hashMap);//헤시맵으로 던짐

         httpSession.setAttribute("user_id", user.getIdentification().toString());

        return "redirect:/";//파라미터 주소
    }

    @GetMapping("/logout")
    public String logout(HttpSession httpSession,Model model){
        httpSession.setAttribute("user_id", null);
        model.addAttribute("message","로그아웃 되었습니다. ");
        model.addAttribute("searchUrl","/");
        return "message";
    }





    @GetMapping("/join")
    public String join(){
        return "join";
    }

    @PostMapping("/join")
    public String joins(UserDto userDto,Model model){


        try {
            userService.checkUser(userDto);
            model.addAttribute("message","회원가입 성공!! 신규회원쿠폰 발급 완료!");
            model.addAttribute("searchUrl","/");
            User user = userService.creat(userDto);
            Coupon coupon = couponService.creatCoupon(user);
            couponListService.createCouponList(coupon);

            return "message";
        }catch (IllegalArgumentException e){
            model.addAttribute("message","다시 시도 해주세요.아이디어 또는 비번이 이미 있습니다."); //근데 뭐가 문제인지 알수가 없엇 답답하넨..이거 어떻게 고치냐?
            model.addAttribute("searchUrl","/join");
            return "message";
        }


    }



    @GetMapping("/myPage")
    public String myPage(Model model, HttpSession session){
        if(session.getAttribute("user_id")==null){
            model.addAttribute("message","로그인 후 이용해 주세요");
            model.addAttribute("searchUrl","/login");
            return "message";
        }
        User user= userService.findByUserId(Long.valueOf(String.valueOf(session.getAttribute("user_id"))));

        model.addAttribute("user", user);
        return "myPage";
    }

    @GetMapping("/address")
    public String address(){
        return "address";
    }

    @PostMapping("/address")
    public String address( AddressDTO addressDTO,HttpSession httpSession){
        Long userId = Long.valueOf(String.valueOf(httpSession.getAttribute("user_id")));

        log.info("주소:{}{}{}",addressDTO.getPostNumber(),addressDTO.getDetailedAddress(),addressDTO.getBasicAddress());
        addressService.addAddress(addressDTO, userId);
        return "address";
    }



    @GetMapping("/card")
    public String card(){

        return "card";
    }
    @GetMapping("/cardd")
    public String cardd(Model model,HttpSession httpSession){
        Long userId =Long.valueOf(String.valueOf(httpSession.getAttribute("user_id")));
        List<Card> card =cardService.findCard(userId);
        model.addAttribute("card", card);
        return "postCard";
    }

    @PostMapping("/card")
    public String card(HttpSession httpSession, CardDto cardDto){
        log.info("CardDto:{}",cardDto.getCardNumber());
        Long userId = Long.valueOf(String.valueOf(httpSession.getAttribute("user_id")));

        cardService.addCard(cardDto,userId);
        return "card";
    }
    @GetMapping("/memberInformation")
    public String memberInformation(){
        return "memberInformation";
    }

    @GetMapping("/addresss")
    public String addresss(Model model, HttpSession httpSession){
        Long userId = Long.valueOf(String.valueOf(httpSession.getAttribute("user_id")));
        List<Address> address = addressService.findById(userId);
//        log.info("주소 나와라:{}");
        model.addAttribute("address", address);
        return "postAddress";
    }

//    @PostMapping("/addresss")
//    public String addresss(@RequestBody String address, HttpSession httpSession){
//        log.info("controller:{}", address);
//        Long userId = Long.valueOf(String.valueOf(httpSession.getAttribute("user_id")));
//
////        addressService.save(address);
//        return "redirect:/";
//    }//미완성이요

//    private String showMessage(final MessageDto params, Model model){
//        model.addAttribute("prrams",params);
//        return "messageDto";

    @GetMapping("/coupon")
    public String coupon(HttpSession httpSession,Model model){

        Long userId = Long.valueOf(String.valueOf(  httpSession.getAttribute("user_id")));
        User user = userService.findByUserId(userId);

        Coupon coupon = couponService.findCouponId(user.getUser_id());

        List<CouPonList> couPonLists = couponListService.findCouponList(coupon.getId());

        model.addAttribute("coupon", couPonLists);

        //유저의 id를 가지고와서 쿠폰list를 가져와서 model 로 넘기기
        return "coupon";
    }

    @PostMapping("/orderDelete")
    public String orderDelete(@RequestParam("orderID") Long orderListId ){//왜 책 아이디를 가져온겨? 카트리스트 번호는 안되나?
        log.info("삭제할것:{}",orderListId.getClass());

        orderService.delete(orderListId);
        return "redirect:/order";
    }

}
