package com.trantor.phonebookapi.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.http.HttpStatus;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class ErrorDetails {
    private Date timeStamp;
    private String message;
    private List<String> details;
    private HttpStatus status;
}
