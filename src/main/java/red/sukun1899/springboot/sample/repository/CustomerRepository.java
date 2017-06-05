package red.sukun1899.springboot.sample.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import red.sukun1899.springboot.sample.entity.Customer;

/**
 * @author su-kun1899
 */
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}
