package com.project.pairdesksystem;

import com.project.pairdesksystem.User.models.ERole;
import com.project.pairdesksystem.User.models.Role;
import com.project.pairdesksystem.User.models.User;
import com.project.pairdesksystem.User.repository.UserRepository;
import com.project.pairdesksystem.datalayer.Feature.Feature;
import com.project.pairdesksystem.datalayer.Feature.FeaturesRepository;
import com.project.pairdesksystem.datalayer.Invoice.Invoice;
import com.project.pairdesksystem.datalayer.Invoice.InvoiceRepository;
import com.project.pairdesksystem.datalayer.Task.Task;
import com.project.pairdesksystem.datalayer.Task.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class InvoiceEntityTests {
    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FeaturesRepository featuresRepository;

    @Autowired
    private TaskRepository taskRepo;

    @BeforeEach
    public void setUpDb(){
        User user = new User();
        user.setId(65431L);
        user.setUserId(52342L);
        user.setEmail("test@gmail.com");
        user.setUsername("test");
        user.setPassword("password");
        Set<Role> role = new HashSet<>();
        role.add(new Role(ERole.ROLE_ADMIN));
        user.setRoles(role);
        userRepository.save(user);
        long l = user.getUserId();
        int id = (int)l;
        Invoice invoice = new Invoice();
        invoice.setUserId(id);
        invoiceRepository.save(invoice);
    }

    @Test
    void saveInvoice() {
        assertEquals( invoiceRepository.count(), 1);
    }

    @Test
    void get_By_Id() {
        Invoice invoice = new Invoice();
        invoice.setId(12345);
        invoiceRepository.save(invoice);
        assertEquals( invoice.getId(),12345);
    }

}
