package org.example.bookpurchase.repository;

import jakarta.persistence.LockModeType;
import org.example.bookpurchase.domain.User;
import org.example.bookpurchase.dto.UserDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByIdentificationAndPassword(String identification, String password);

    @Query("select m from User m where m.identification = :userId")
    Optional<User> findByUserId(String userId);

    @Query("select m from User m where m.name = :name")
    User findByName(String name);
    @Query("select m from User m where m.identification = :id and m.password = :pw")
    List<User> findByIdANdPw(@Param("id") String id ,@Param("pw") String pw);

//    @Query("select m from  User m where m.identification = :id or m.password = :pw")
    List<User> findByIdentification(String id);

    List<User> findByPassword(String password);
//    @Query("select m from User m where m.identification = :id")
//    User findById(String id);
    @Query("select m from User m where  m.identification = :userIdentification")
    User findByUserIdentification (String userIdentification);
}
