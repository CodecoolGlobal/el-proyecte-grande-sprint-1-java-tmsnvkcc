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
//  Optional<List<Account>> findByUserId(@Param("userId") int userId, @Param("year") int year, @Param("month") int month);

//    value =
//      """
//        SELECT
//          ACC.id,
//          ACC.name,
//          ACC.description,
//          ACC.actual_balance,
//          ACC.savings_balance,
//          ACC.currency,
//          ACC.user_id,
//          EXT.id,
//          EXT.account_id,
//          EXT.user_id,
//          EXT.description,
//          EXT.category_name,
//          EXT.amount,
//          EXT.date_of_transaction,
//          EXT.is_planned,
//          EXT.is_recurring,
//          LOC.id,
//          LOC.account_id,
//          LOC.user_id,
//          LOC.description,
//          LOC.amount,
//          LOC.date_of_transaction,
//          LOC.is_planned,
//          LOC.is_recurring
//        FROM
//          accounts AS ACC
//        INNER JOIN external_transactions AS EXT ON EXT.account_id = ACC.id
//        INNER JOIN local_transactions AS LOC ON EXT.account_id = ACC.id
//        WHERE
//          ACC.user_id = :userId AND
//          (EXTRACT(YEAR FROM EXT.date_of_transaction) = :year AND
//          EXTRACT(MONTH FROM EXT.date_of_transaction) = :month) OR
//          (EXTRACT(YEAR FROM LOC.date_of_transaction) = :year AND
//          EXTRACT(MONTH FROM LOC.date_of_transaction) = :month)
//      """, nativeQuery = true
