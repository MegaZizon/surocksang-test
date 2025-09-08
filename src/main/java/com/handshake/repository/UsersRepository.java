package com.handshake.repository;

import com.handshake.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
    
    Optional<Users> findByEmail(String email);
    
    boolean existsByEmail(String email);
    
    boolean existsByNickname(String nickname);
}