package red.sukun1899.springboot.sample.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping(value = "edit", params = "form")
    public String editForm(@RequestParam Integer id, CustomerForm form) {
        Customer customer = customerService.findOne(id);
        BeanUtils.copyProperties(customer, form);
        return "customers/edit";
    }

    @PostMapping(value = "edit")
    public String edit(@RequestParam Integer id, @Validated CustomerForm form, BindingResult result) {
        if (result.hasErrors()) {
            return editForm(id, form);
        }
        Customer customer = new Customer();
        BeanUtils.copyProperties(form, customer);
        customer.setId(id);
        customerService.update(customer);

        return "redirect:/customers";
    }

    @RequestMapping(value = "edit", params = "goToTop")
    public String goToTop() {
        return "redirect:/customers";
    }

    @PostMapping(value = "delete")
    public String delete(@RequestParam Integer id) {
        customerService.delete(id);
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
