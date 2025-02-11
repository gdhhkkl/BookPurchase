package org.example.bookpurchase.repository;

import org.example.bookpurchase.domain.User;
import org.example.bookpurchase.dto.UserDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByIdentificationAndPassword(String identification, String password);

    @Query("select m from User m where m.identification = :userId")
    User findByUserId(Long userId);
}
