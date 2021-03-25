package com.journalist.journal.api.journalist;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class NewsConverter {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    public NewsDTO toDto(NewsEntity news){
        NewsDTO newsDtoEntity = new NewsDTO();

        newsDtoEntity.setId(news.getId());
        newsDtoEntity.setTransliterateUrl(news.getTransliterateUrl());
        newsDtoEntity.setTitle(news.getTitle());
        newsDtoEntity.setExcerpt(news.getExcerpt());
        newsDtoEntity.setDate(news.getDate().toLocalDate());
        newsDtoEntity.setCategories(news.getCategories());
        newsDtoEntity.setTags(news.getTags());
        newsDtoEntity.setImages(news.getImages());
        newsDtoEntity.setAuthorsFullName(news.getAuthorsFullName());
        newsDtoEntity.setAuthorsPhotoUrl(news.getAuthorsPhotoUrl());
        newsDtoEntity.setViews(news.getViews());

        return newsDtoEntity;
    }

    public List<NewsDTO> toListDto(List<NewsEntity> listNewsEntity){
        List<NewsDTO> tmpList = new ArrayList<>();
        for(NewsEntity newsEntity : listNewsEntity){
            tmpList.add(toDto(newsEntity));
        }
        return tmpList;
    }

    public NewsEntity toNewsEntity(News news){
        NewsEntity newsEntity = new NewsEntity();
        newsEntity.setId(news.getId());
        newsEntity.setTransliterateUrl(news.getTransliterateUrl());
        newsEntity.setTitle(news.getTitle().getRendered());
        newsEntity.setExcerpt(news.getExcerpt().getRendered());
        newsEntity.setDate(LocalDateTime.parse(news.getDate(), formatter));
        newsEntity.setCategories(news.getCategories());
        newsEntity.setTags(news.getTags());
        newsEntity.setImages(news.getImages());
        newsEntity.setAuthorsFullName(news.getAuthorsFullName());
        newsEntity.setAuthorsPhotoUrl(news.getAuthorsPhotoUrl());
        newsEntity.setViews(news.getViews());

        return newsEntity;
    }

    public List<NewsEntity> toListNewsEntity(List<News> listNews){
        List<NewsEntity> tmpList = new ArrayList<>();
        for(News news : listNews){
            tmpList.add(toNewsEntity(news));
        }
        return tmpList;
    }

}
