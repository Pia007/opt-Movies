package com.javaunit3.springmvc;
// Part 3: Hibernate
// P3-1: set up db
// P3-2: Hibernate setup - resources/hibernate.cfg.xml
// P3-3: SessionFactory bean
    // create HibernateConfig class with correct annotation for Spring to recognize beans defined in class
    // define a Spring bean method that returns hibernate SessionFactory
        // session should point to the hibernate.cfg.xml file
// P3-4: Create model pkg in com.javunit3.springmvc
    // a: create MovieEntity class - go to this class
    // f: In the HibernateConfig class, add MovieEntity as an annotated class to the SessionFactory.
// P3-5: Create an add movie page - in templates directory

import com.javaunit3.springmvc.model.MovieEntity;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HibernateConfig {

    @Bean
    public SessionFactory getFactory() {
        SessionFactory factory = new org.hibernate.cfg.Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(MovieEntity.class)
                .buildSessionFactory();

        return factory;
    }
}
