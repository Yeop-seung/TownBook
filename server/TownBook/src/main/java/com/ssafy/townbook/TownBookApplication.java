package com.ssafy.townbook;

import org.hibernate.SessionFactory;
import org.hibernate.jpa.HibernateEntityManagerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TownBookApplication {

    public static void main(String[] args) {
        SpringApplication.run(TownBookApplication.class, args);
    }

    @Bean // Need to expose SessionFactory to be able to work with BLOBs
    public SessionFactory sessionFactory(HibernateEntityManagerFactory hemf) {
        return hemf.getSessionFactory();
    }
}
