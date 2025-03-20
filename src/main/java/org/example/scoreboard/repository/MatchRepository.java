package org.example.scoreboard.repository;

import org.example.scoreboard.entity.Match;
import org.example.scoreboard.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Collection;
import java.util.Optional;

public class MatchRepository implements CrudRepository<Match, Long> {

    @Override
    public Optional<Match> findById(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return Optional.ofNullable(session.get(Match.class, id));
        }
    }

    @Override
    public Match save(Match entity) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(entity);
            transaction.commit();
            System.out.println("Удачно сохранилось Match");
            return entity;
        } catch (Exception e) {
            System.out.println("Ошибка при сохранении Match");
            System.out.println(e.getMessage());
            throw new RuntimeException("Ошибка при сохранении матча", e);
        }
    }

    @Override
    public Collection<Match> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM matches", Match.class).list();
        }
    }

    @Override
    public boolean delete(Match entity) {
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
    public Match update(Match entity) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Match updated = session.merge(entity);  // merge обновляет объект
            transaction.commit();
            return updated;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw new RuntimeException("Ошибка при обновлении матча", e);
        }
    }
}

