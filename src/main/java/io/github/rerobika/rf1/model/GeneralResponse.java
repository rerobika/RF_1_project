package io.github.rerobika.rf1.model;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Created by Nandor Magyar on 9/30/17.
 */
public class GeneralResponse<T> {

    private T data;
    private HttpStatus status;

    public GeneralResponse() {
        this(HttpStatus.OK);
    }

    public GeneralResponse(final HttpStatus status) {
        this.setStatus(status);
    }

    public GeneralResponse(final T data) {
        this();
        this.setData(data);
    }

    public GeneralResponse(final T data, final HttpStatus status) {
        this(status);
        this.setData(data);
    }

    public final HttpStatus getStatus() {
        return status;
    }

    public final void setStatus(final HttpStatus status) {
        this.status = status;
    }

    public final T getData() {
        return data;
    }

    public final void setData(final T data) {
        this.data = data;
    }

    public final ResponseEntity<T> response() {
        return new ResponseEntity<>(data, this.getStatus());
    }
}
