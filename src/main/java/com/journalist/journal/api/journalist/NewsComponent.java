package com.journalist.journal.api.journalist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.text.ParseException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class NewsComponent {
    RestTemplate restTemplate = new RestTemplate();
    NewsConverter newsConverter = new NewsConverter();
    @Autowired
    private NewsRepository newsRepository;  //Интерфейс для работы с бд

    //@Value("${host}")
    String host;

    @PostConstruct
    void dataExtraction(){
        String fooResourceUrl = "https://polis.ua/api/wp/posts/?lang=ru";
        ResponseEntity<News[]> response = restTemplate.getForEntity(fooResourceUrl, News[].class);
        List<News> news = Arrays.asList(Objects.requireNonNull(response.getBody()).clone());
        newsRepository.saveAll(newsConverter.toListNewsEntity(news));

        System.out.println(response.hasBody());
    }

    public List<NewsDTO> extractNewsFromDb(){
        return newsConverter.toListDto(newsRepository.findAll());
    }

    public void deleteNewsFromDb(){
        newsRepository.deleteAll();
    }

    public NewsEntity extractNewsById(int id){
        Optional<NewsEntity> newsEntityOptional = newsRepository.findById(id);
        if(newsEntityOptional.isEmpty()){
            return null;
        }
        return newsEntityOptional.get();
    }

    public List<NewsEntity> extractNewsByName(String name){
        return newsRepository.findAllByTransliterateUrl(name);
    }

    public void deleteNewsById(int id){
        newsRepository.deleteById(id);
    }

    public void deleteNewsByName(String name){
        for(NewsEntity news : newsRepository.findAllByTransliterateUrl(name)){
            newsRepository.deleteById(news.getId());
        }
    }


  /*  public ResponseEntity<NewsEntity> getFirstDto(){
        *//*Поскольку мы теперь работаем с бд, мы получаем сразу много сущностей, если не ищем по MongoID,
        а конструкция ".findAll().stream().findFirst()" берет первый попавшийся элемент.
        Но может быть такое, что в бд пусто и она не сможет нам вернуть запрашиваемые данные. В таком случае
        база вернет нам "пусто". Это достигается с помощью обертки Optional<> :
         *//*
        Optional<ItemEntity> dtoOptional = itemRepository.findAll().stream().findFirst();

        if(dtoOptional.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        *//* с помощью dtoOptional.get() разворачиваем нашу обертку и получаем лежащую в ней ItemEntity, которую
        конвертируем в дто*//*
        return new ResponseEntity<>(converter.toDTO(dtoOptional.get()), HttpStatus.OK);
    }

    public MicroserviseBaseResponse saveDto(String name, String description){
        Item item = new Item(name, description);
        ItemEntity ie = converter.toEntity(item, LocalDateTime.now());

        itemRepository.save(ie);

        return new MicroserviseBaseResponse(true, null, 201);
    }

    public MicroserviseBaseResponse saveDtoFromItem(Item item){
        itemRepository.save(converter.toEntity(item, LocalDateTime.now()));

        return new MicroserviseBaseResponse(true, null, 201);
    }

    public MicroserviseBaseResponse deleteItems(){
        if(itemRepository.count() == 0){
            return new MicroserviseBaseResponse(false, "Mistake", 404);
        }

        itemRepository.deleteAll();

        return new MicroserviseBaseResponse(true, null, 201);
    }*/
}