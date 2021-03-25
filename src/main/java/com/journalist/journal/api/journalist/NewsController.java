package com.journalist.journal.api.journalist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/my-newsite")
public class NewsController {

    @Autowired
    private NewsComponent newsComponent;
    @GetMapping("/get-all")
    public List<NewsDTO> getAllNews(){
        return newsComponent.extractNewsFromDb();
    }

    @GetMapping("/get-by-id")
    public NewsEntity getNewsById(@RequestParam int id){
        return newsComponent.extractNewsById(id);
    }

    @GetMapping("/get-by-name")
    public List<NewsEntity> getNewsByName(String name){
        return newsComponent.extractNewsByName(name);
    }

    @DeleteMapping ("/delete-all")// TO DO - MICROSERVICE BASE RESPONSE
    public void deleteAllNews(){
        newsComponent.deleteNewsFromDb();
    }

    @DeleteMapping("/delete-by-id")
    public void deleteById(@RequestParam int id){
        newsComponent.deleteNewsById(id);
    }

    // NEW METHOD:

    @DeleteMapping("/delete-by-name")
    public void deleteByName(@RequestParam String name){
        newsComponent.deleteNewsByName(name);
    }
}
