package org.example.scoreboard.repository;

import org.example.scoreboard.entity.OngoingMatch;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class OngoingMatchInMemoryRepository implements CrudRepository<OngoingMatch, String> {

    static Map<String, OngoingMatch> matchesCollection = new ConcurrentHashMap<>();

    @Override
    public Optional<OngoingMatch> findById(String id) {
        return Optional.ofNullable(matchesCollection.get(id));
    }

    @Override
    public OngoingMatch save(OngoingMatch entity) {
        String newId;
        do {
            newId = UUID.randomUUID().toString();
        } while (matchesCollection.containsKey(newId));

        entity.setId(newId);
        matchesCollection.put(newId, entity);
        return entity;
    }

    @Override
    public List<OngoingMatch> findAll() {
        return new ArrayList<>(matchesCollection.values());
    }

    @Override
    public boolean delete(OngoingMatch entity) {
        return matchesCollection.remove(entity.getId()) != null;
    }

    @Override
    public OngoingMatch update(OngoingMatch entity) {
        return null;
    }
}
