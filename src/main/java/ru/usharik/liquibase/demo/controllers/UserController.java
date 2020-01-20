package ru.usharik.liquibase.demo.controllers;

import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
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
import javax.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.hibernate.resource.transaction.spi.TransactionStatus;
import org.springframework.web.bind.annotation.PathVariable;
import ru.usharik.liquibase.demo.LiquibaseDemoApplication;

@RestController
public class UserController {

    private UserRepository userRepository;
    private SessionFactory sessionFactory;
    private Session session; 
    @Autowired
    public UserController(UserRepository userRepository) throws Exception {
        this.userRepository = userRepository;
        if(LiquibaseDemoApplication.sessionFactory == null)
        {
            LiquibaseDemoApplication.setUp();
            sessionFactory = LiquibaseDemoApplication.sessionFactory;
        }
    }
    private void OpenSession()
    {
        session = sessionFactory.openSession();
    }
    private void CloseSession()
    {
        session.close();
    }
    @GetMapping("/user/all")
    public List<User> allUsers() {
        return userRepository.findAll();
    }

    @GetMapping(path = "/user/{depId}/{daysCount}")
    public List<User> GetUserByDepAndDays(@PathVariable int depId, @PathVariable int daysCount) throws Exception
    {
        List<ru.usharik.liquibase.demo.persist.model.User>  list = null;
        OpenSession();
        String filePath = "src\\main\\resources\\db\\UsersWithDepAmdBirthDay.txt";
        //String filePath = "src\\main\\resources\\db\\all.txt";
        String queryStr = new String(Files.readAllBytes(Paths.get(filePath)));
        String queryStr1 = queryStr.replace("@dep", Integer.toString(depId));
        String queryStr2 = queryStr1.replace("@days", Integer.toString(daysCount) );
        NativeQuery query = session.createSQLQuery(queryStr2);
        List<Object[]> rows = query.getResultList();
        List<User> result = new ArrayList<>(rows.size());
        for (Object[] row : rows) {
            result.add(new User(((int)row[0]),
                                (String) row[1],
                                (String) row[2],
                                ((int)row[3]),
                                (Date) row[5]));
        }
       //list = query.list();
       CloseSession();
       return result;
    }
    
    public void AddUser(User user)
    {
        try
        {
            OpenSession();
            session.beginTransaction();
            userRepository.save(user);
            if(session.getTransaction().getStatus() != TransactionStatus.COMMITTED)
            {
                session.getTransaction().commit();
                CloseSession();
            }
        }
        catch(Exception e)
        {
            session.getTransaction().rollback();
            CloseSession();
            throw e;
        }
        
    }
}
