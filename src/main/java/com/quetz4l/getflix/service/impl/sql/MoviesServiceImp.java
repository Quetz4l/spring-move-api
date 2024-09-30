//package com.quetz4l.getflix.service.impl.sql;
//
//import com.quetz4l.getflix.model.Movie;
//import com.quetz4l.getflix.service.IMoviesService;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;

//import com.quetz4l.getflix.model.Genre;
//import com.quetz4l.getflix.model.Movie;
//import com.quetz4l.getflix.model.dto.MovieRequestFilterDTO;
//import com.quetz4l.getflix.repository.IGenreRepository;
//import com.quetz4l.getflix.repository.IMovieRepository;
//import com.quetz4l.getflix.service.IMoviesService;
//import lombok.AllArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//
//@AllArgsConstructor
//@Service
//public class MoviesServiceImp implements IMoviesService {
//
//
//    @Override
//    public List<Movie> findMoviesByFilter(Long id, short releaseYear, Long actor) {
//        return new ArrayList<>();
//    }
//}
//    private IMovieRepository movieRepository;
//    private IGenreRepository genreRepository;
//
//    @Override
//    public List<Movie> findMoviesByFilter(String title, Integer releaseYear) {
//        movieRepository.getMovieBy
//        return List.of();
//    }


//    @Override
//    public List<Movie> findMoviesByFilter(MovieRequestFilterDTO filterDTO) {
//        if (filterDTO.getGenre() > 0 && filterDTO.getYear() > 0 && filterDTO.getActor() > 0) {
//            Optional<Genre> genre = genreRepository.findById(filterDTO.getGenre());
//            if(genre.isPresent()) {
//                return genre.get().getMovies();
//            }
//        }
//
//        return List.of();
//
//    }


//    private List<Movie> findMoviesByYear(MovieRequestFilterDTO filterDTO) {
//        movieRepository.findAll()
//    }


//}
