package com.leolee.reservation.Dao;

import com.leolee.reservation.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query(value = "SELECT * FROM `User` WHERE id = :id AND BINARY password = :password", nativeQuery = true)
    public User findUser(@Param("id") int id, @Param("password") String password);
}
