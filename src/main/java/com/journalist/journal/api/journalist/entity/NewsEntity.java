package com.journalist.journal.api.journalist.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewsEntity {
    private int id;
    private String transliterateUrl;
    private String title;
    private String excerpt;
    private LocalDateTime date;
    private News.CategoriesTags[] categories;
    private News.CategoriesTags[] tags;
    private String[] images;
    private String authorsFullName;
    private String authorsPhotoUrl;
    private int views;
}
