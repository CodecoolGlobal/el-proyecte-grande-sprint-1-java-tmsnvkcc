package com.codecool.repository;

import com.codecool.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
  User save(User user);
  @Query(value =
    """
      SELECT
        *
      FROM
        users
      WHERE
        email = :email
      """, nativeQuery = true
  )
  Optional<User> findByEmail(@Param("email") String email);
}
