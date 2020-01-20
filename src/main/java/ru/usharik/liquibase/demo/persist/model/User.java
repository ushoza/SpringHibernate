package ru.usharik.liquibase.demo.persist.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    
    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "birthday")
    private Date birthday;
    
    @Column(name = "department")
    private int department;

    public User() {
    }
    public User(int id, String First_name, String Last_name, int dep, Date bithDay) {
        this.id = id;
        this.firstName= First_name;
        this.lastName = Last_name;
        this.department = dep;
        this.birthday = bithDay;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthDay() {
        return birthday;
    }

    public void setBirthDay(Date date) {
        this.birthday = date;
    }
    public long getDepartment()
    {
        return department;
    }
    
    public void setDepartment(int dep)
    {
        this.department = dep;
    }
    
    @Override
    public boolean equals(Object o) { 
  
       
  
        /* Check if o is an instance of Complex or not 
          "null instanceof [type]" also returns false */
        if (!(o instanceof User)) { 
            return false; 
        } 
          
        // typecast o to Complex so that we can compare data members  
        User c = (User) o; 
          
        // Compare the data members and return accordingly 
        boolean res = false;
        if(c.getFirstName().equalsIgnoreCase(this.getFirstName()) &&
                c.getLastName().equalsIgnoreCase(c.getLastName()) )
        {
            Calendar cBthd = Calendar.getInstance();
            cBthd.setTime(c.getBirthDay());
            
            Calendar thisBtd = Calendar.getInstance();
            thisBtd.setTime(this.getBirthDay());
            if(thisBtd.get(Calendar.YEAR) == cBthd.get(Calendar.YEAR) &&
               thisBtd.get(Calendar.MONTH) == cBthd.get(Calendar.MONTH)&&
               thisBtd.get(Calendar.DAY_OF_MONTH) == cBthd.get(Calendar.DAY_OF_MONTH) )
            {
                res = true;
            }
        }
        return res;
        }
    
        //return Double.compare(re, c.re) == 0
                //&& Double.compare(im, c.im) == 0; 
     
}