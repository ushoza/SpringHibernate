package ru.usharik.liquibase.demo;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication

public class LiquibaseDemoApplication {

    public static SessionFactory sessionFactory;
    public static void main(String[] args) throws Exception {
        SpringApplication.run(LiquibaseDemoApplication.class, args);
        setUp();
//        tearDown();
    }

    public static void setUp() throws Exception {
	// A SessionFactory is set up once for an application!
	final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
			.configure() // configures settings from hibernate.cfg.xml
			.build();
	try {
		sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
	}
	catch (Exception e) {
		// The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
		// so destroy it manually.
		StandardServiceRegistryBuilder.destroy( registry );
	}
    }
    
     public static void tearDown() throws Exception {
        if ( sessionFactory != null ) {
                sessionFactory.close();
        }
    }
}
