package red.sukun1899.springboot.sample.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import red.sukun1899.springboot.sample.entity.Customer;
import red.sukun1899.springboot.sample.repository.CustomerRepository;

import java.util.List;

/**
 * @author su-kun1899
 */
@Service
@Transactional
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> findAll() {
        return customerRepository.findAllOrderByName();
    }

    public Customer findOne(Integer id) {
        return customerRepository.findOne(id);
    }

    public Customer create(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer update(Customer customer) {
        return customerRepository.save(customer);
    }

    public void delete(Integer id) {
        customerRepository.delete(id);
    }
}
