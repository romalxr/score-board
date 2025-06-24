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
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(entity);
            transaction.commit();
            return entity;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Failed to save Match entity", e);
        }
    }

    @Override
    public Collection<Match> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Match", Match.class).list();
        }
    }

    public Collection<Match> findAllPageable(int pageNumber, int pageSize) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Match", Match.class)
                    .setFirstResult((pageNumber - 1) * pageSize)
                    .setMaxResults(pageSize)
                    .list();
        }
    }

    public Collection<Match> findByPlayerNamePageable(int pageNumber, int pageSize, String playerName) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String searchTerm = "%" + playerName + "%";
            return session.createQuery("FROM Match WHERE player1.name LIKE :name OR player2.name LIKE :name", Match.class)
                    .setParameter("name", searchTerm)
                    .setFirstResult((pageNumber - 1) * pageSize)
                    .setMaxResults(pageSize)
                    .list();
        }
    }

    @Override
    public boolean delete(Match entity) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = null;
            transaction = session.beginTransaction();

            Match match = session.get(Match.class, entity.getId());
            if (match == null) {
                return false;
            }

            session.remove(match);
            transaction.commit();
            return true;
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при удалении матча", e);
        }
    }

    @Override
    public Match update(Match entity) {
        Transaction transaction = null;
        Match updatedMatch = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            updatedMatch = session.merge(entity);  // merge обновляет и возвращает управляемый объект
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Ошибка при обновлении матча", e);
        }
        return updatedMatch;
    }

}

