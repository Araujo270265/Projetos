package br.com.araujo.didatico.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 8845279497339734576L;

    public ResourceNotFoundException(String message) {
        super(message);
    }

}
