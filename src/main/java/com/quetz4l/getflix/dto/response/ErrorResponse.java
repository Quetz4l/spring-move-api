package com.quetz4l.getflix.dto.response;

import com.quetz4l.getflix.util.Time;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse {
    private String timestamp;
    private String result = "Fail";
    private Object error;


    public ErrorResponse(Object error) {
        this.timestamp = Time.getTimeStamp();
        this.error = error;
    }

}
