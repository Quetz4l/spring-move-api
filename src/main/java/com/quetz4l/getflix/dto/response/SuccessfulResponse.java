package com.quetz4l.getflix.dto.response;

import com.quetz4l.getflix.model.Actor;
import com.quetz4l.getflix.model.Genre;
import com.quetz4l.getflix.model.Movie;
import com.quetz4l.getflix.util.Time;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SuccessfulResponse {
    private String timestamp;
    private String result = "Successful";
    private Object data;

    public SuccessfulResponse(Object data) {
        this.timestamp = Time.getTimeStamp();
        this.data = checkResponseModel(data);
    }


    private Object checkResponseModel(Object object) {
        return switch (object.getClass().getSimpleName()) {
            case "Actor" -> new ActorResponseDTO((Actor) object);
            case "Movie" -> new MovieResponseDTO((Movie) object);
            case "Genre" -> new GenreResponseDTO((Genre) object);
            default -> object;
        };

    }
}
