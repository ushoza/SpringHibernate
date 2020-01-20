package ru.usharik.liquibase.demo;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
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
