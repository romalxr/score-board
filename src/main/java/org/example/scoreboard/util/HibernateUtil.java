package org.example.scoreboard.util;

import lombok.Getter;
import org.example.scoreboard.entity.Match;
import org.example.scoreboard.entity.Player;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

import org.h2.tools.Server;

public class HibernateUtil {
    @Getter
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            Configuration configuration = new Configuration();

            Properties properties = new Properties();
            properties.load(HibernateUtil.class.getClassLoader().getResourceAsStream("hibernate.properties"));
            configuration.setProperties(properties);

            configuration.addAnnotatedClass(Match.class);
            configuration.addAnnotatedClass(Player.class);

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties())
                    .build();

            createWebServer();
            return configuration.buildSessionFactory(serviceRegistry);
        } catch (IOException e) {
            throw new RuntimeException("Ошибка загрузки hibernate.properties", e);
        } catch (Exception ex) {
            throw new ExceptionInInitializerError("Ошибка инициализации Hibernate: " + ex);
        }
    }

    private static void createWebServer() {
        try {
            Server webServer = Server.createWebServer("-webPort", "8082", "-webAllowOthers").start();
            System.out.println("H2 Console started: -:8082");
        } catch (SQLException e) {
            System.out.println("error: H2 Console not started");
            System.out.println(e.getMessage());
        }
    }
}
