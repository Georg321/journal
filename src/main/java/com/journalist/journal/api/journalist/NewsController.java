package com.journalist.journal.api.journalist;

import com.journalist.journal.api.MicroserviceBaseResponse;
import com.journalist.journal.api.journalist.entity.NewsDTO;
import com.journalist.journal.api.journalist.entity.NewsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<NewsEntity> getNewsById(@RequestParam int id){
        return newsComponent.extractNewsById(id);
    }

    @GetMapping("/get-by-name")
    public List<NewsEntity> getNewsByName(String name){
        return newsComponent.extractNewsByName(name);
    }

    @DeleteMapping ("/delete-all")
    public MicroserviceBaseResponse deleteAllNews(){
        return newsComponent.deleteNewsFromDb();
    } // now this method return MicrobaseResonse which is responsible for giving feedback if operation was successful or not

    @DeleteMapping("/delete-by-id")
    public MicroserviceBaseResponse deleteById(@RequestParam int id){
        return newsComponent.deleteNewsById(id);
    } // now this method return MicrobaseResonse which is responsible for giving feedback if operation was successful or not

    // NEW METHOD:
    //↓
    @DeleteMapping("/delete-by-transliterate-url")
    public void deleteByTransliterateUrl(@RequestParam String transliterateUrl){
        newsComponent.deleteNewsByTransliterateUrl(transliterateUrl);
    }
    //↑
}
