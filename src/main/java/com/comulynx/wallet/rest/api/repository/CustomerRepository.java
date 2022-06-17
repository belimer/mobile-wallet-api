package com.comulynx.wallet.rest.api.repository;

import java.util.List;
import java.util.Optional;

import org.hibernate.query.NativeQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.comulynx.wallet.rest.api.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

	Optional<Customer> findByCustomerId(String customerId);

	// TODO : Implement the query and function below to delete a customer using Customer Id
	 @Query(value = "DELETE FROM customer WHERE customer_id = :customerId",nativeQuery = true)
	 int deleteCustomerByCustomerId(@Param("customerId") String customer_id);

//	// TODO : Implement the query and function below to update customer firstName using Customer Id

	 @Query(value="UPDATE customers SET first_name=:firstName WHERE customer_id=:customerId", nativeQuery=true)
	 int updateCustomerByCustomerId(@Param("firstName") String firstName, @Param("customerId") String customer_id);
	
//	 TODO : Implement the query and function below and to return all customers whose Email contains  'gmail'
	 @Query(value="SELECT * FROM customers where email like '%gmail%'", nativeQuery = true)
	 List<Customer> findAllCustomersWhoseEmailContainsGmail();
}
