package org.example.bookpurchase.dto;

import lombok.*;
import org.example.bookpurchase.domain.Address;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@AllArgsConstructor
public class AddressDTO {

    private Long address_id;
    private Long postNumber;
    private String basicAddress;
    private String detailedAddress;

    public static AddressDTO toEntity(Address address){
        return  AddressDTO.builder()
                .address_id(address.getAddress_id())
                .postNumber(address.getPostNumber())
                .basicAddress(address.getBasicAddress())
                .detailedAddress(address.getDetailedAddress())
                .build();
    }
//    public static List<AddressDTO> toList(List<Address> addressList ) {
//        List<AddressDTO> addressDTOList = new ArrayList<>();
//        for (Address address : addressList) {
//            addressDTOList.add(AddressDTO.toEntity(address));//배민
//        }
//        return addressDTOList;
//    }

}
