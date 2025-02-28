package org.example.bookpurchase.repository;

import org.example.bookpurchase.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    @Query("select m from Address m where m.user.user_id =:userId")
    List<Address> findAddressByUserId(Long userId);
}
