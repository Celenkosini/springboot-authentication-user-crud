package com.nyawo.user_management_demo.repository;
import com.nyawo.user_management_demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
User findByUserName(String username);
User findByEmail(String email);
}