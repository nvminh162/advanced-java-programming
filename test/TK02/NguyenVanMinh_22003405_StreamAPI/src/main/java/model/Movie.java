package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Movie {
    private int movie_id;
    private String plot;
    private List<String> genres;
    private int runtime;
    private List<String> cast;
    private int num_mflix_comments;
    private String title;
    private List<String> languages;
    private List<String> directors;
    private String rated;
    private Awards awards;
    private int year;
}
