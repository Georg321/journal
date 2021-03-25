package com.journalist.journal.api.journalist;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface NewsRepository extends MongoRepository<NewsEntity, String> {
    @Query(value = "{}", sort = "{ 'id' : 1 }")// @Querry анотация над методм при вызове котогорого она будет исполнять команда в скобочках
    List<NewsEntity> findAll();

    Optional<NewsEntity> findById(int i);

   // @Query(value = "{transliterateUrl: ?0}")
    List<NewsEntity> findAllByTransliterateUrl(String transliterateUrl);

    void deleteById(int id);
}
