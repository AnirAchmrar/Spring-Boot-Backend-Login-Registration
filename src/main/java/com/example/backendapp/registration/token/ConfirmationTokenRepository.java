package com.example.backendapp.registration.token;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken,Long> {

    Optional<ConfirmationToken> findByToken(String token);

    //The @Modifying annotation is used to enhance the @Query annotation so that we can execute not only
    // SELECT queries, but also INSERT, UPDATE, DELETE, and even DDL queries.

    @Transactional
    @Modifying
    @Query("UPDATE ConfirmationToken c SET c.confirmedAt = ?2 WHERE c.token = ?1")//parameterized query
        // ?2== confirmedAt and  ?1==token
    int updateConfirmedAt(String token, LocalDateTime confirmedAt);
}
