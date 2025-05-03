package org.example.bookpurchase.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Address {
    @Id
    @Column(name = "address_id")
//    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동으로 증가한다 -> 데이터를 안넣어도됨//이것 때문에...selet 두번됨..ㅅㅂ
    private Long address_id;

    @Column(length = 8, nullable = false)
    private Long postNumber;

    @Column(length = 20, nullable = false)
    private String basicAddress;

    @Column(length = 20, nullable = false)
    private String detailedAddress;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private User user;

//    public static Address toEntity(AddressDTO addressDTO, User user){
//        return new Address(
//                0L,
//                addressDTO.getPostNumber(),
//                addressDTO.getBasicAddress(),
//                addressDTO.getDetailedAddress(),
//                user
//        );
//    }
    public void setAddress_id(){

    }

}
