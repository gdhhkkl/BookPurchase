package org.example.bookpurchase.dto;


import lombok.*;
import org.example.bookpurchase.domain.User;
import org.hibernate.annotations.processing.Pattern;

//@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserDto {
    private Long user_id;
    private String name;
    private String identification;
    private String password;




}
