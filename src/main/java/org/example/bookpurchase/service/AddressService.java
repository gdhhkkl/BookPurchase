package org.example.bookpurchase.service;

import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.bookpurchase.domain.Address;
import org.example.bookpurchase.domain.Order;
import org.example.bookpurchase.domain.User;
import org.example.bookpurchase.dto.AddressDTO;
import org.example.bookpurchase.repository.AddressRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@RequiredArgsConstructor
@Service
public class AddressService {
    private static final Logger log = LoggerFactory.getLogger(AddressService.class);
    private final AddressRepository addressRepository;

    private final UserService userService;


//    @Transactional
    public Address addAddress(AddressDTO addressDTO, Long userId) {
        User user = userService.findByUserId(userId);
        log.info("유저확인 : {}",user.getUser_id());

//        Address address = Address.toEntity(addressDTO, user);
//        save(address);

        return save( new Address(
                0L,
                addressDTO.getPostNumber(),
                addressDTO.getBasicAddress(),
                addressDTO.getDetailedAddress(),
                user
        ));
    }
//    @Transactional
    public Address save(Address address) {
        log.info("뭐가 들어왔니:{}", address);
        return addressRepository.save(address);
    }


    public List<Address> findById(Long userId){
        User user = userService.findByUserId(userId);
        List<Address> address = addressRepository.findAddressByUserId(user.getUser_id());
        return address;
    }

//    public Address saveToorder(AddressDTO addressDTO,Long userId){
//        User user =userService.findByUserId(userId);
//
//        return ;
//    }


}
