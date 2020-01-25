package com.grocery.demo.Repository;

import com.grocery.demo.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {
User findByUname(String uname);
User findByEmailIgnoreCase(String email);

@Modifying
    @Query(value = "delete FROM User u  where  u.id= :id")
    void deleteUser (@Param("id") Long id);
}
