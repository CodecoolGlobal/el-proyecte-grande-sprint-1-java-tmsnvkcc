package com.codecool.repository;

import com.codecool.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Integer> {
  @Query(
    value =
      """
        SELECT
          *
        FROM
          accounts
        WHERE
          accounts.user_id = :userId
      """, nativeQuery = true
  )
  Optional<List<Account>> findByUserId(@Param("userId") int userId);
}
