package com.journalist.journal.api.journalist;

import java.util.ArrayList;
import java.util.List;

public class NewsConverter {

    public DTO toDto(NewsEntity news){
        DTO dtoEntity = new DTO();

        dtoEntity.setId(news.getId());
        dtoEntity.setTransliterateUrl(news.getTransliterateUrl());
        dtoEntity.setTitle(news.getTitle());
        dtoEntity.setExcerpt(news.getExcerpt());
        dtoEntity.setDate(news.getDate().substring(0,10));
        dtoEntity.setCategories(news.getCategories());
        dtoEntity.setTags(news.getTags());
        dtoEntity.setImages(news.getImages());
        dtoEntity.setAuthorsFullName(news.getAuthorsFullName());
        dtoEntity.setAuthorsPhotoUrl(news.getAuthorsPhotoUrl());
        dtoEntity.setViews(news.getViews());

        return dtoEntity;
    }

    public List<DTO> toListDto(List<NewsEntity> listNewsEntity){
        List<DTO> tmpList = new ArrayList<>();
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
        newsEntity.setDate(news.getDate());
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
