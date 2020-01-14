package ru.usharik.liquibase.demo;

import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.List;
import org.h2.engine.User;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import ru.usharik.liquibase.demo.controllers.UserController;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class LiquibaseDemoApplicationTests {
//    private SessionFactory sessionFactory;
//    protected void setUp() throws Exception {
//        // A SessionFactory is set up once for an application!
//        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
//                        .configure() // configures settings from hibernate.cfg.xml
//                        .build();
//        try {
//                sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
//        }
//        catch (Exception e) {
//                // The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
//                // so destroy it manually.
//                StandardServiceRegistryBuilder.destroy( registry );
//        }
//    }
//    
//    protected void tearDown() throws Exception {
//        if ( sessionFactory != null ) {
//                sessionFactory.close();
//        }
//    }
    private MockMvc mvc;
    @Autowired
    private UserController userController;
     @Test
    public void getSomeUsers() throws Exception {
        
       List<ru.usharik.liquibase.demo.persist.model.User> actualUsers  = userController.GetUsersByBirthDay(11, 0);                     
    }
    
//    @Test
//    public void getNextWeekUsers() throws Exception {
//        ru.usharik.liquibase.demo.persist.model.User first = new ru.usharik.liquibase.demo.persist.model.User();
//        first.setFirstName("Nataliya");
//        first.setDepartment("Tech");
//        first.setLastName("Pyatak");
//        first.setBirthDay((new GregorianCalendar(1982,10,15).getTime()));
//        List<ru.usharik.liquibase.demo.persist.model.User> actualUsers = userController.TechUsers("tech", 7);
//        List<ru.usharik.liquibase.demo.persist.model.User> expectedUsers = Arrays.asList(first);
//        Assert.assertEquals(expectedUsers.get(0), actualUsers.get(0));
//                      
//    }
    
//    @Test
//    public void getNextMonthUsers() throws Exception {
//        ru.usharik.liquibase.demo.persist.model.User first = new ru.usharik.liquibase.demo.persist.model.User();
//        first.setFirstName("Peter");
//        first.setDepartment("Sale");
//        first.setLastName("Rubbit");
//        first.setBirthDay((new GregorianCalendar(1994,11,03).getTime()));
//        List<ru.usharik.liquibase.demo.persist.model.User> actualUsers = userController.TechUsers("sale", 30);
//        List<ru.usharik.liquibase.demo.persist.model.User> expectedUsers = Arrays.asList(first);
//        Assert.assertEquals(expectedUsers.get(0), actualUsers.get(0));
//                      
//    }
    
    

}
