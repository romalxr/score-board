package org.example.scoreboard.repository;

import org.example.scoreboard.entity.Match;
import org.example.scoreboard.entity.Player;
import org.example.scoreboard.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Collection;
import java.util.Optional;

public class PlayerRepository implements CrudRepository<Player, Long> {

    @Override
    public Optional<Player> findById(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return Optional.ofNullable(session.get(Player.class, id));
        }
    }

    public Optional<Player> findByName(String name) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Optional<Player> player = session.createQuery("FROM Player WHERE name = :name", Player.class)
                    .setParameter("name", name)
                    .uniqueResultOptional();
            int a = 1;
            System.out.println("find player work good ");
            return player;
        }
    }

    @Override
    public Player save(Player entity) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            System.out.println("save started");
            Transaction transaction = session.beginTransaction();
            System.out.println("transaction started");
            session.persist(entity);
            System.out.println("entity persisted");
            transaction.commit();
            System.out.println("Удачно сохранилось Player");
            return entity;
        } catch (Exception e) {
            System.out.println("Ошибка при сохранении Player");
            System.out.println(e.getMessage());
            throw new RuntimeException("Ошибка при сохранении Player", e);
        }
    }

    @Override
    public Collection<Player> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM players", Player.class).list();
        }
    }

    @Override
    public boolean delete(Player entity) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Match match = session.get(Match.class, entity.getId());
            if (match == null) {
                return false;
            }

            session.remove(match);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw new RuntimeException("Ошибка при удалении матча", e);
        }
    }

    @Override
    public Player update(Player entity) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Player updated = session.merge(entity);  // merge обновляет объект
            transaction.commit();
            return updated;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw new RuntimeException("Ошибка при обновлении матча", e);
        }
    }
}
