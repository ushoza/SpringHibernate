package ru.usharik.liquibase.demo;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
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
    

//    @Test
//    public void addUsersItsNotTest() throws Exception {
//        //org.h2.tools.Server server = org.h2.tools.Server.createTcpServer().start();
//        Calendar calendar = new GregorianCalendar();
//        calendar.set(1996, 01, 02);
//        ru.usharik.liquibase.demo.persist.model.User user =
//                new ru.usharik.liquibase.demo.persist.model.User(5, "Иван", "Сидоров", 111, calendar.getTime());
//        userController.AddUser(user);
//        
//        Calendar calendar1 = new GregorianCalendar();
//        calendar1.set(1996, 02, 05);
//        ru.usharik.liquibase.demo.persist.model.User user1 =
//                new ru.usharik.liquibase.demo.persist.model.User(6, "Екатерина", "Иванова", 112, calendar1.getTime());
//        userController.AddUser(user1);
//        
//        Calendar calendar2 = new GregorianCalendar();
//        calendar2.set(1995, 0, 04);
//        ru.usharik.liquibase.demo.persist.model.User user2 =
//                new ru.usharik.liquibase.demo.persist.model.User(7, "Екатерина", "Иванова", 1121, calendar2.getTime());
//        userController.AddUser(user2);
//        
//        Calendar calendar3 = new GregorianCalendar();
//        calendar3.set(1996, 01, 05);
//        ru.usharik.liquibase.demo.persist.model.User user3 =
//                new ru.usharik.liquibase.demo.persist.model.User(8, "Наталья", "Костромина", 12, calendar3.getTime());
//        userController.AddUser(user3);
//        
//        Calendar calendar4 = new GregorianCalendar();
//        calendar4.set(1996, 0, 28);
//        ru.usharik.liquibase.demo.persist.model.User user4 =
//                new ru.usharik.liquibase.demo.persist.model.User(9, "Александр", "Костромин", 121, calendar4.getTime());
//        userController.AddUser(user4);
//        
//        Calendar calendar5 = new GregorianCalendar();
//        calendar5.set(1996, 0, 25);
//        ru.usharik.liquibase.demo.persist.model.User user5 =
//                new ru.usharik.liquibase.demo.persist.model.User(10, "Иван", "Беляев", 121, calendar5.getTime());
//        userController.AddUser(user5);
//        
//        Calendar calendar6 = new GregorianCalendar();
//        calendar6.set(1996, 0, 22);
//        ru.usharik.liquibase.demo.persist.model.User user6 =
//                new ru.usharik.liquibase.demo.persist.model.User(11, "Геннадий", "Деточкин", 122, calendar6.getTime());
//        userController.AddUser(user6);
//        
//        Calendar calendar7 = new GregorianCalendar();
//        calendar7.set(1996, 0, 24);
//        ru.usharik.liquibase.demo.persist.model.User user7 =
//                new ru.usharik.liquibase.demo.persist.model.User(12, "Елена", "Геннадьева", 122, calendar7.getTime());
//        userController.AddUser(user7);
//                              
//    }
    
    @Test
    public void getNextWeekUsers() throws Exception {
         List<ru.usharik.liquibase.demo.persist.model.User> actualUsers = userController.GetUserByDepAndDays(1, 7);
        Date dNow = new Date();
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.add(Calendar.DAY_OF_YEAR, 7);
        Date nextDate = calendar.getTime();
        boolean res = true;
        for (int i = 0; i < actualUsers.size(); i++) {
            ru.usharik.liquibase.demo.persist.model.User u = (ru.usharik.liquibase.demo.persist.model.User)actualUsers.get(i);
            if(!(u.getBirthDay().after(dNow) && u.getBirthDay().before(nextDate)))
                res = false;
        }
         Assert.assertTrue(res);
           
    }
    
    @Test
    public void getNextMonthUsers() throws Exception {
        List<ru.usharik.liquibase.demo.persist.model.User> actualUsers = userController.GetUserByDepAndDays(1, 30);
        Date dNow = new Date();
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.add(Calendar.DAY_OF_YEAR, 30);
        Date nextDate = calendar.getTime();
        boolean res = true;
        for (int i = 0; i < actualUsers.size(); i++) {
            ru.usharik.liquibase.demo.persist.model.User u = (ru.usharik.liquibase.demo.persist.model.User)actualUsers.get(i);
            if(!(u.getBirthDay().after(dNow) && u.getBirthDay().before(nextDate)))
                res = false;
        }
         Assert.assertTrue(res);
    }
    
    
    
   


}
