package com.st_carollus.ticket_system.exception;

import com.st_carollus.ticket_system.constant.ConstantSQLState;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleNotFound(ResourceNotFoundException ex) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("status", 404);
        body.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidation(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new LinkedHashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(fe -> errors.put(fe.getField(), fe.getDefaultMessage()));

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("status", 400);
        body.put("message", "Validation failed");
        body.put("errors", errors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<Map<String, Object>> handleInvalidCredentials(InvalidCredentialsException ex) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("status", 401);
        body.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(body);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Map<String, Object>> handleDataIntegrity(DataIntegrityViolationException ex) {
        Throwable rootCause = ex.getMostSpecificCause();
        String sqlState = extractSqlState(rootCause);
        String rawMessage = rootCause.getMessage();

        String friendlyMessage;
        HttpStatus status;

        if (ConstantSQLState.UNIQUE_VIOLATION.equals(sqlState)) {
            friendlyMessage = extractDuplicateFieldMessage(rawMessage);
            status = HttpStatus.CONFLICT; // 409
        } else if (ConstantSQLState.FOREIGN_KEY_VIOLATION.equals(sqlState)) {
            friendlyMessage = "The reference data is invalid or could not be found";
            status = HttpStatus.BAD_REQUEST; // 400
        } else if (ConstantSQLState.NOT_NULL_VIOLATION.equals(sqlState)) {
            friendlyMessage = "A required field is missing";
            status = HttpStatus.BAD_REQUEST; // 400
        } else {
            friendlyMessage = "The data is invalid. Please check your input";
            status = HttpStatus.BAD_REQUEST; // 400
        }

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("status", status.value());
        body.put("message", friendlyMessage);
        return ResponseEntity.status(status).body(body);
    }

    private String extractSqlState(Throwable rootCause) {
        if (rootCause instanceof SQLException sqlEx) {
            return sqlEx.getSQLState();
        }
        return null;
    }

    private String extractDuplicateFieldMessage(String rawMessage) {
        if (rawMessage == null) {
            return "Duplicate data violates a unique constraint";
        }

        Matcher matcher = Pattern.compile("Key \\((.+?)\\)=\\((.+?)\\)").matcher(rawMessage);

        if (matcher.find()) {
            String field = matcher.group(1);
            String value = matcher.group(2);
            return String.format("%s '%s' is already in use", capitalize(field), value);
        }

        return "Duplicate data violates a unique constraint";
    }

    private String capitalize(String field) {
        if (field == null || field.isBlank()) return field;
        return field.substring(0, 1).toUpperCase() + field.substring(1);
    }
}
