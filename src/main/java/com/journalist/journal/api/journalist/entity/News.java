package com.journalist.journal.api.journalist.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class News {
    private int id;
    private String transliterateUrl;
    private Title title;
    private Excerpt excerpt;
    private String date;
    private CategoriesTags[] categories;
    private CategoriesTags[] tags;
    private String[] images;
    private String authorsFullName;
    private String authorsPhotoUrl;
    private int views;


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Title{
        private String rendered;
    }
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Excerpt{
        private String rendered;
    }
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CategoriesTags{
        private String name;
        private int id;
    }
}
