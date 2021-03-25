package com.journalist.journal.api.journalist;

import com.journalist.journal.api.journalist.entity.News;
import com.journalist.journal.api.journalist.entity.NewsDTO;
import com.journalist.journal.api.journalist.entity.NewsEntity;
import com.journalist.journal.api.journalist.entity.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class NewsComponent {
    @Autowired
    private NewsRepository newsRepository;  //Интерфейс для работы с бд

    @Value("${host}")
    private String host;

    RestTemplate restTemplate = new RestTemplate();
    NewsConverter newsConverter = new NewsConverter();

    @PostConstruct
    void dataExtraction() {
        ResponseEntity<News[]> response = restTemplate.getForEntity(host+"/posts/?lang=ru", News[].class);
        List<News> news = Arrays.asList(Objects.requireNonNull(response.getBody()).clone());
        newsRepository.saveAll(newsConverter.toListNewsEntity(news));
    }

    public List<NewsDTO> extractNewsFromDb() {
        return newsConverter.toListDto(newsRepository.findAll());
    }

    public MicroserviceBaseResponse deleteNewsFromDb() {
        newsRepository.deleteAll();
        if (newsRepository.count() != 0) {
            return new MicroserviceBaseResponse(false, "was not deleted", 409);
        }
        return new MicroserviceBaseResponse(true, null, 200);
    }

    public ResponseEntity<NewsEntity> extractNewsById(int id) {
        Optional<NewsEntity> newsEntityOptional = newsRepository.findById(id);
        if (newsEntityOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(newsEntityOptional.get(), HttpStatus.OK);
    }

    public List<NewsEntity> extractNewsByName(String name) {
        return newsRepository.findAllByTransliterateUrl(name);
    }

    public MicroserviceBaseResponse deleteNewsById(int id) {
        newsRepository.deleteById(id);
        if (newsRepository.existsById(id)) {
            return new MicroserviceBaseResponse(false, "was not deleted", 409);
        }
        return new MicroserviceBaseResponse(true, null, 200);
    }

    //↓
    public void deleteNewsByName(String name) {
        for (NewsEntity news : newsRepository.findAllByTransliterateUrl(name)) {
            newsRepository.deleteById(news.getId());
        }
    }
    //↑
}
