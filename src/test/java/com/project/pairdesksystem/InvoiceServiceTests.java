//package com.project.pairdesksystem;
//
//import com.project.pairdesksystem.User.models.ERole;
//import com.project.pairdesksystem.User.models.Role;
//import com.project.pairdesksystem.User.models.User;
//import com.project.pairdesksystem.User.models.UserService;
//import com.project.pairdesksystem.User.repository.UserRepository;
//import com.project.pairdesksystem.buinesslayer.Invoice.InvoiceService;
//import com.project.pairdesksystem.datalayer.Feature.FeaturesRepository;
//import com.project.pairdesksystem.datalayer.Invoice.Invoice;
//import com.project.pairdesksystem.datalayer.Invoice.InvoiceRepository;
//import com.project.pairdesksystem.datalayer.Task.TaskRepository;
//import javassist.NotFoundException;
//import org.junit.jupiter.api.AfterAll;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import java.util.HashSet;
//import java.util.Set;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@ExtendWith(SpringExtension.class)
//public class InvoiceServiceTests {
//    @Autowired
//    private InvoiceRepository invoiceRepository;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private FeaturesRepository featuresRepository;
//
//    @Autowired
//    private TaskRepository taskRepo;
//
//    @Autowired
//    private InvoiceService invoiceService;
//
//
//    @Autowired
//    private UserService userService;
//
//    @BeforeEach
//    public void setUpDb(){
//        User user = new User();
//        user.setId(65431L);
//        user.setUserId(52342L);
//        user.setEmail("test@gmail.com");
//        user.setUsername("test");
//        user.setPassword("password");
//        Set<Role> role = new HashSet<>();
//        role.add(new Role(ERole.ROLE_ADMIN));
//        user.setRoles(role);
//        userRepository.save(user);
//        long l = user.getUserId();
//        int id = (int)l;
//        Invoice invoice = new Invoice();
//        invoice.setUserId(id);
//        System.out.println(invoice);
//        invoiceRepository.save(invoice);
//    }
//
//    @AfterEach
//    void after(){
//        userRepository.deleteByUserId(52342L);
//        invoiceRepository.deleteAll();
//    }
//
//    @Test
//    void get_All_Invoices(){
//        assertEquals(invoiceService.getAllInvoices().size(), 1);
//    }
//    @Test
//    void get_Invoices_By_UserId() throws NotFoundException {
//        User user = new User();
//        user.setId(65431L);
//        user.setUserId(52342L);
//        user.setEmail("test@gmail.com");
//        user.setUsername("test");
//        user.setPassword("password");
//        Set<Role> role = new HashSet<>();
//        role.add(new Role(ERole.ROLE_ADMIN));
//        user.setRoles(role);
//        userRepository.save(user);
//        long l = user.getUserId();
//        int id = (int)l;
//        Invoice invoice = new Invoice();
//        invoice.setUserId(id);
//        invoice.setInvoiceId(12345);
//        System.out.println(invoice);
//        invoiceRepository.save(invoice);
//
//
//        Invoice i = invoiceService.getInvoiceByInvoiceId(12345);
//        invoiceRepository.save(i);
//        System.out.println(i);
//        assertEquals(i, invoiceService.getInvoiceByInvoiceId(i.getInvoiceId()));
//    }
//}
