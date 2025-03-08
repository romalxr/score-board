package org.example.scoreboard.repository;

import java.util.Collection;
import java.util.Optional;

public interface CrudRepository<T, U> {

    Optional<T> findById(U id);

    T save(T entity);

    Collection<T> findAll();

    boolean delete(T entity);

    T update(T entity);
}
