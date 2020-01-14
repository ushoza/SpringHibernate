package ru.usharik.liquibase.demo.controllers;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.usharik.liquibase.demo.persist.model.User;
import ru.usharik.liquibase.demo.persist.repo.UserRepository;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.springframework.data.jpa.provider.HibernateUtils;
import org.springframework.jdbc.object.SqlQuery;
import org.springframework.web.bind.annotation.PathVariable;
import ru.usharik.liquibase.demo.LiquibaseDemoApplication;

@RestController
public class UserController {

    private UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
        
    }

    @GetMapping("/user/all")
    public List<User> allUsers() {
        return userRepository.findAll();
    }
//    //@GetMapping("/user/tech/nextweek")
//    @GetMapping(path = "/user/{depId}/{daysCount}")
//    public List<User> TechUsers(@PathVariable String depId, @PathVariable int daysCount) {
//        List<User>  allUser = userRepository.findAll();
//        List<User> list = new ArrayList<User>(); 
//        for (int i = 0; i < allUser.size(); i++) {
//            User user = allUser.get(i);
//            Date userBirtDay = user.getBirthDay();
//            Calendar userBdThisYear = Calendar.getInstance();
//            userBdThisYear.setTime(userBirtDay);
//            userBdThisYear.set(Calendar.YEAR, 2019);
//            //Calendar userBdThisYear = new GregorianCalendar(2019, userBirtDay.getMonth() , userBirtDay.getDay());
//            Date ubdty = userBdThisYear.getTime();
//            Calendar today = Calendar.getInstance();
//            Date td = today.getTime();
//            Calendar todayPSomedays = Calendar.getInstance();
//            todayPSomedays.add(Calendar.DAY_OF_MONTH, daysCount);
//            Date tdSome = todayPSomedays.getTime();
//            if(ubdty.after(td) && ubdty.before(tdSome))
//            {
//                String dep = user.getDepartment();
//                if(dep.equalsIgnoreCase(depId) )
//                {
//                   list.add(user);
//                }
//            }
//                 
//        }
//        return list;
//    }
    
    @GetMapping(path = "/user/{depId}/{daysCount}")
    public List<ru.usharik.liquibase.demo.persist.model.User> GetUsersByBirthDay(@PathVariable long depId, @PathVariable int daysCount) throws Exception {
        List<ru.usharik.liquibase.demo.persist.model.User>  list = null;
        if(LiquibaseDemoApplication.sessionFactory == null)
            LiquibaseDemoApplication.setUp();
        Session session = LiquibaseDemoApplication.sessionFactory.openSession();
        
//        String queryStr = "SELECT * FROM [TestBaseForStudy].[dbo].[users]";
//        NativeQuery query = session.createSQLQuery(queryStr);
//        query.addEntity(ru.usharik.liquibase.demo.persist.model.User.class);
//        
        NativeQuery query = session.createSQLQuery(
	"EXECUTE [TestBaseForStudy].[dbo].[rep_getUsersByDepAndBirthDay]  :idDep, :days")
            .addEntity(User.class)
            .setParameter("idDep", depId)
            .setParameter("days", daysCount);
        
        list = query.list();
        session.close();
        LiquibaseDemoApplication.tearDown();
        return list;
    }
}
