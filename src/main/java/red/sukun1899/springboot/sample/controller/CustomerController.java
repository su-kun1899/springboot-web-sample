package red.sukun1899.springboot.sample.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import red.sukun1899.springboot.sample.entity.Customer;
import red.sukun1899.springboot.sample.service.CustomerService;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * @author su-kun1899
 */
@Controller
@RequestMapping("customers")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @ModelAttribute
    CustomerForm setupForm() {
        return new CustomerForm();
    }

    @GetMapping
    public String list(Model model) {
        List<Customer> customers = customerService.findAll();
        model.addAttribute("customers", customers);
        return "customers/list";
    }

    @PostMapping(value = "create")
    public String create(@Validated CustomerForm customerForm,
                         BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return list(model);
        }
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerForm, customer);
        customerService.create(customer);

        return "redirect:/customers";
    }

    public class CustomerForm {
        @NotNull
        @Size(min = 1, max = 127)
        private String firstName;

        @NotNull
        @Size(min = 1, max = 127)
        private String lastName;

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }
    }
}
