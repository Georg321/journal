package com.journalist.journal.api.journalist;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DTO {
    private int id;
    private String transliterateUrl;
    private String title;
    private String excerpt;
    private String date;
    private News.CategoriesTags[] categories;
    private News.CategoriesTags[] tags;
    private String[] images;
    private String authorsFullName;
    private String authorsPhotoUrl;
    private int views;
}
