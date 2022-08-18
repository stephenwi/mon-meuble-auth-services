package cm.resources.irokotechservice.controller;

import cm.resources.irokotechservice.common.CustomerRegisterRequest;
import cm.resources.irokotechservice.common.RequestUpdateDetails;
import cm.resources.irokotechservice.common.RequestUpdatePassword;
import cm.resources.irokotechservice.domaine.Customer;
import cm.resources.irokotechservice.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/ss-resources/customers")
@Slf4j
@RequiredArgsConstructor
//@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;
    private static Logger logger;



    @GetMapping("/customer/{id}")
    public Customer getCustomer(@RequestParam("id") Long id) {
        log.info("get customer with id: {}", id);
        return customerService.getCustomerById(id);
    }

    @PostMapping("/new-customer")
    public boolean addNewCustomer(@RequestBody CustomerRegisterRequest request) {
        log.info("Create new customer {}", request);
        return customerService.saveCustomer(request);
    }

    @PatchMapping("/update/{id}/informations")
    public boolean customerUpadteSingleDetails(@RequestParam("id") Long id, @RequestBody RequestUpdateDetails request) {
        log.info("Update customer details {}, {}", id, request);
        return customerService.updateDetails(id, request);
    }

    @PatchMapping("/update/{id}/update-password")
    public  boolean updatePassword(@RequestParam("email") String email, @RequestBody RequestUpdatePassword request) {
        log.info("Updating customer password with email: {}", email);
        return customerService.customerUpdatedPassword(email, request.getOldPassword(), request.getNewPassword());
    }

    @PatchMapping("/request/reset-password/{email}/with-email")
    public boolean resetPassword(@RequestParam("email") String email) {
        log.info("Sending email for reset password to customer with email: {}", email);
        return customerService.customerResetPassword(email);
    }

    @PostMapping("/exec/request-password/{token}")
    public void resetPasswordExecution(@RequestParam("token") String token) {
        log.info("Execute reset password for customer with token: {}", token);
         customerService.confirmResetPassword(token);
    }

    @DeleteMapping("/delete/{id}/customer")
    public boolean deleteCustomer(@RequestParam("id") Long id) {
        log.info("Delete customer with id: {}", id);
        return customerService.deleteCustomer(id);
    }

    @PostMapping("/desactive-customer/{id}/customer")
    public boolean desactiveCustomer(@RequestParam("id") Long id) {
        log.info("Desactive customer with id: {}", id);
        return customerService.desactiveCustomer(id);
    }
}
