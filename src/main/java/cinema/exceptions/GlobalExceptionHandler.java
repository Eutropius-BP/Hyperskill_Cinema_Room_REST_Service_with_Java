
package cinema.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.ZonedDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(TicketAlreadyPurchased.class)
    public ResponseEntity<Map<String, Object>> handleTicketAlreadyPurchased(TicketAlreadyPurchased ex) {
        return buildErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST, "/purchase");
    }

    @ExceptionHandler(IncorrectRowOrColumn.class)
    public ResponseEntity<Map<String, Object>> handleIncorrectRowOrColumn(IncorrectRowOrColumn ex) {
        return buildErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST, "/purchase");
    }

    @ExceptionHandler(WrongToken.class)
    public ResponseEntity<Map<String, Object>> handleWrongToken(WrongToken ex) {
        return buildErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST, "/return");
    }

    @ExceptionHandler(WrongPassword.class)
    public ResponseEntity<Map<String, Object>> handleWrongPassword(WrongPassword ex) {
        return buildErrorResponse(ex.getMessage(), HttpStatus.UNAUTHORIZED, "/stats");
    }

    private ResponseEntity<Map<String, Object>> buildErrorResponse(String message, HttpStatus status, String path) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", ZonedDateTime.now());
        body.put("status", status.value());
        body.put("error", message);
        body.put("path", path);

        return new ResponseEntity<>(body, status);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("status", status.value());
        body.put("timestamp", ZonedDateTime.now());
        body.put("exception", ex.getClass());
        return new ResponseEntity<>(body, headers, status);
    }
}