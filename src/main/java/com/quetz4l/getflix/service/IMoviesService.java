package com.quetz4l.getflix.service;

import com.quetz4l.getflix.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.Nullable;

import java.util.List;

public interface IMoviesService extends JpaRepository<Movie, Long> {
//    @Query("SELECT m FROM Movie m " +
//            "WHERE (:year IS NULL OR m.releaseYear = :year) " +
//            "AND  (:genre IS NULL OR m.genres = :genre) "
////            "AND (:actor IS NULL OR m.actors = :actor)"
//    )
//    List<Movie> findMoviesByFilter(@Param("year") short releaseYear, @Param("genre") Long genres);


//    @Query("select m from Movie m where m.releaseYear = ?1")
//    List<Movie> findMoviesByFilter(@Nullable short releaseYear);


    @Query("select m from Movie m inner join m.genres genres where " +
            "m.releaseYear = ?1 and " +
            "genres.id = ?2")
    List<Movie> findMoviesByFilter(@Nullable short releaseYear, @Nullable Long id);
}