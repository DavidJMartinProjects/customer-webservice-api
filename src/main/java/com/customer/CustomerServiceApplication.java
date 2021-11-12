package com.customer;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.app.openapi.generated.model.Customer;
import com.customer.db.dao.model.entity.CustomerEntity;
import com.customer.db.dao.query.CustomerSpecification;
import com.customer.db.dao.query.SearchCriteria;
import com.customer.db.dao.query.SearchOperation;
import com.customer.db.dao.repository.CustomerRepository;

/**
 * @author davidjmartin
 */
@SpringBootApplication
@EnableTransactionManagement
public class CustomerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner specificationsDemo(CustomerRepository customerRepository) {
		return args -> {
			// search customer by `genre`
			CustomerSpecification msGenre = new CustomerSpecification();
			msGenre.add(new SearchCriteria("lastName", "Bloggs", SearchOperation.LIKE));
			List<CustomerEntity> msGenreList = customerRepository.findAll(msGenre);
			msGenreList.forEach(System.out::println);
		};
	}

}
