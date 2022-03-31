package br.com.araujo.didatico.error;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

public class StandarError implements Serializable {

    private static final long serialVersionUID = 4167293034787551108L;

    private Instant timestamp;
    private Integer status;
    private List<String> errors;
    private String message;
    private String path;

    public StandarError() {

    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public void setPath(String path) {
        this.path = path;
    }

}
