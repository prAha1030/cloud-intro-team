package com.cloudintroteam.config;

import com.cloudintroteam.member.exception.FileUploadFailedException;
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
        log.error("입력 검증 실패로 인한 에러 발생");
        String errorMessage = ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        return getErrorResponse(HttpStatus.BAD_REQUEST, errorMessage);
    }

    // 팀원 not found 에러
    @ExceptionHandler(MemberNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleMemberNotFoundException(MemberNotFoundException ex) {
        log.error("잘못된 팀원 조회 요청으로 인한 에러 발생");
        HttpStatus status = HttpStatus.NOT_FOUND;
        return getErrorResponse(status, ex.getMessage());
    }

    // 팀원 프로필 사진 업로드 실패 에러
    @ExceptionHandler(FileUploadFailedException.class)
    public ResponseEntity<Map<String, Object>> handleFileUploadFailedException(FileUploadFailedException ex) {
        log.error("이미지 파일 업로드 과정에서 에러 발생");
        HttpStatus status = HttpStatus.BAD_REQUEST;
        return getErrorResponse(status, ex.getMessage());
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
