package com.cloudintroteam.config;

import com.cloudintroteam.member.exception.MemberNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    // 입력 검증 실패 에러
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationException(MethodArgumentNotValidException ex) {
        String errorMessage = ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        log.error("입력 검증 실패로 인한 에러 발생");
        return getErrorResponse(HttpStatus.BAD_REQUEST, errorMessage);
    }

    // 팀원 not found 에러
    @ExceptionHandler(MemberNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleMemberNotFoundException(MemberNotFoundException ex) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        log.error("잘못된 팀원 조회 요청으로 인한 에러 발생");
        return getErrorResponse(status, ex.getMessage());
    }

    // 예상치 못한 나머지 예외
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleUnexpectedException(Exception ex) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        log.error("예상치 못한 에러 발생");
        return getErrorResponse(status, "서버 내부 오류가 발생했습니다.");
    }

    // 공통 에러 응답
    public ResponseEntity<Map<String, Object>> getErrorResponse(HttpStatus status, String message) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("status", status.name());
        errorResponse.put("code", status.value());
        errorResponse.put("message", message);

        return new ResponseEntity<>(errorResponse, status);
    }
}
