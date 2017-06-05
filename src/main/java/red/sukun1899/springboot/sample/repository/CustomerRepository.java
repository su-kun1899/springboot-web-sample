package red.sukun1899.springboot.sample.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import red.sukun1899.springboot.sample.entity.Customer;

import java.util.List;

/**
 * @author su-kun1899
 */
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    @Query("select x from Customer x order by x.firstName, x.lastName")
    List<Customer> findAllOrderByName();
}
