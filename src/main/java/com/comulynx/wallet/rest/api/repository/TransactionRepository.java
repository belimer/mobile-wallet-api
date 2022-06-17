package com.comulynx.wallet.rest.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.comulynx.wallet.rest.api.model.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

	Optional<List<Transaction>> findTransactionsByCustomerId(String customerId);

	Optional<List<Transaction>> findTransactionsByTransactionId(String transactionId);

	Optional<List<Transaction>> findTransactionsByCustomerIdOrTransactionId(String transactionId, String customerId);

	// TODO : Change below Query to return the last 5 transactions
	// TODO : Change below Query to use Named Parameters instead of indexed
	// parameters
	// TODO : Change below function to return Optional<List<Transaction>>
	@Query(value="SELECT t FROM Transaction t WHERE t.customer_id =:customerId AND  t.account_no =:accountNo ORDER BY id DESC LIMIT 5", nativeQuery=true)
	Optional<List<Transaction>> getMiniStatementUsingCustomerIdAndAccountNo(@Param("customerId") String customer_id, @Param("accountNo") String account_no);

}
