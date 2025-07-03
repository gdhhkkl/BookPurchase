package org.example.bookpurchase.domain;

import jakarta.persistence.*;
import lombok.*;
import org.example.bookpurchase.dto.UserDto;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class User {
    @Id
    @Column(name = "user_id", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;

    @Column(name = "NAME", nullable = false, length=5)//notnull제약조건에 길이는 5
    private String name;

    @Column(nullable = false)
    private String identification;

    @Column(nullable = false)
    private String password;

//    @OneToMany(mappedBy = "user")
//    private List<Cart> carts = new ArrayList<>();

//    @OneToMany(mappedBy = "user")
//    private List<Order> orders = new ArrayList<>();

//    @OneToMany(mappedBy = "user")
//    private List<Address> addresses = new ArrayList<>();

    public static User toEntity(UserDto userDto){
        return User.builder()
                .user_id(userDto.getUser_id())
                .name(userDto.getName())
                .identification(userDto.getIdentification())
                .password(userDto.getPassword())
                .build();
    }



}
