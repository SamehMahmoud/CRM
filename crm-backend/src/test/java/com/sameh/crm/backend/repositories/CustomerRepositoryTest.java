package com.sameh.crm.backend.repositories;


import com.sameh.crm.backend.entities.Customer;
import com.sameh.crm.backend.entities.Tenant;
import com.sameh.crm.backend.entities.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TenantRepository tenantRepository;

    @Test
    void shouldSaveCustomer(){

        User detachedUser = new User("AhmedKamal02","002010369311004","testxx@gmail.com");
        detachedUser.setFirstName("Ahmed");
        detachedUser.setLastName("Ali");
        detachedUser.setPasswordHash("dasdasldjalskdjlakshashahs");
        User presistedUser = this.userRepository.save(detachedUser);

        Tenant detachedTenant = new Tenant("test tenant", "tenantxx@gmail.com", "0114151521152");
        detachedTenant.setCreatedBy(presistedUser.getId());
        Tenant presistedTenant = this.tenantRepository.save(detachedTenant);


        String customerName = "Hany Ali";
        Customer detachedCustomer = new Customer(presistedTenant.getId(),Customer.CustomerType.INDIVIDUAL, customerName, presistedUser.getId());
        Customer presistedCustomer = this.customerRepository.save(detachedCustomer);

        assertNotNull(presistedCustomer.getId());

        // load customer
        Customer foundCustomer = this.customerRepository.findById(presistedCustomer.getId()).orElseThrow();

        assertEquals(customerName, foundCustomer.getName());
        assertEquals(Customer.CustomerType.INDIVIDUAL, foundCustomer.getType());
        assertEquals(presistedTenant.getId(), foundCustomer.getTenantId());
        assertEquals(presistedUser.getId(), foundCustomer.getCreatedBy());

        this.customerRepository.delete(presistedCustomer);
        this.tenantRepository.delete(presistedTenant);
        this.userRepository.delete(presistedUser);

        out.println("CustomerRepository test done !");
    }

}
