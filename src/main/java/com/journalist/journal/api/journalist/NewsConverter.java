package com.journalist.journal.api.journalist;

import com.journalist.journal.api.journalist.entity.News;
import com.journalist.journal.api.journalist.entity.NewsDTO;
import com.journalist.journal.api.journalist.entity.NewsEntity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class NewsConverter {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    public NewsDTO toDto(NewsEntity news){
        return new NewsDTO(
                news.getId(),
                news.getTransliterateUrl(),
                news.getTitle(),
                news.getExcerpt(),
                    news.getDate().toLocalDate(),
                news.getCategories(),
                news.getTags(),
                news.getImages(),
                news.getAuthorsFullName(),
                news.getAuthorsPhotoUrl(),
                news.getViews()
        );
    }

    public List<NewsDTO> toListDto(List<NewsEntity> listNewsEntity){
        //return listNewsEntity.stream().map(n->toDto(n)).collect(Collectors.toList()); // same but different syntax
        return listNewsEntity.stream().map(this::toDto).collect(Collectors.toList()); // stream() applied to incoming list so we can invoke methods which allow us to work with data using different methods after we apply each element of list toNewsEntity() method we put it back together in list using collections
    }

    public NewsEntity toNewsEntity(News news){
        return new NewsEntity(
                news.getId(),
                news.getTransliterateUrl(),
                    news.getTitle().getRendered(),
                    news.getExcerpt().getRendered(),
                    LocalDateTime.parse(news.getDate(), formatter),
                news.getCategories(),
                news.getTags(),
                news.getImages(),
                news.getAuthorsFullName(),
                news.getAuthorsPhotoUrl(),
                news.getId()
        );
    }

    public List<NewsEntity> toListNewsEntity(List<News> listNews){
        return listNews.stream().map(this::toNewsEntity).collect(Collectors.toList()); // stream() applied to incoming list so we can invoke methods which allow us to work with data using different methods after we apply each element of list toNewsEntity() method we put it back together in list using collections
    }

}
