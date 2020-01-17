package ru.usharik.liquibase.demo;

import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.List;
import org.h2.engine.User;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
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
    
    private MockMvc mvc;
    @Autowired
    private UserController userController;
    @Test
    public void getNextWeekUsers() throws Exception {
        ru.usharik.liquibase.demo.persist.model.User first = new ru.usharik.liquibase.demo.persist.model.User();
        first.setFirstName("Nataliya");
        first.setDepartment("Tech");
        first.setLastName("Pyatak");
        first.setBirthDay((new GregorianCalendar(1982,10,15).getTime()));
        List<ru.usharik.liquibase.demo.persist.model.User> actualUsers = userController.TechUsers("tech", 7);
        List<ru.usharik.liquibase.demo.persist.model.User> expectedUsers = Arrays.asList(first);
        Assert.assertEquals(expectedUsers.get(0), actualUsers.get(0));
                      
    }
    
    @Test
    public void getNextMonthUsers() throws Exception {
        ru.usharik.liquibase.demo.persist.model.User first = new ru.usharik.liquibase.demo.persist.model.User();
        first.setFirstName("Peter");
        first.setDepartment("Sale");
        first.setLastName("Rubbit");
        first.setBirthDay((new GregorianCalendar(1994,11,03).getTime()));
        List<ru.usharik.liquibase.demo.persist.model.User> actualUsers = userController.TechUsers("sale", 30);
        List<ru.usharik.liquibase.demo.persist.model.User> expectedUsers = Arrays.asList(first);
        Assert.assertEquals(expectedUsers.get(0), actualUsers.get(0));
                      
    }
    
    @Test
    public void getUsersByDepAndBirthDAy() throws Exception {
        List<ru.usharik.liquibase.demo.persist.model.User> actualUsers = userController.GetUserByDepAndBirthday("sale", 30);
                              
    }


}
