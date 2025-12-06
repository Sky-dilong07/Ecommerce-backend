package com.ai.ecommerce.authservice.repository;

import com.ai.ecommerce.authservice.entity.RefreshToken;
import com.ai.ecommerce.authservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    Optional<RefreshToken> findByToken(String token);

    void deleteByUser(User user);
}
