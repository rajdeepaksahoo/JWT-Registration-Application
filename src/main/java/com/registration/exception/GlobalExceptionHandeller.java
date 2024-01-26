package com.registration.exception;

import com.registration.constants.ApplicationConstants;
import com.registration.dto.TokenDto;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandeller {
    @ExceptionHandler(JwtAuthenticationException.class)
    public ResponseEntity<TokenDto> userNotRegisteredException(JwtAuthenticationException e){
        TokenDto tokenDto = TokenDto.builder()
                .status("FAILED")
                .msg(ApplicationConstants.USER_NOT_REGISTERED)
                .build();
        return new ResponseEntity<>(tokenDto, HttpStatus.UNAUTHORIZED);
    }
    @ExceptionHandler(TokenExpiredException.class)
    public ResponseEntity<String> runTimeException(TokenExpiredException e){
        return new ResponseEntity<>(e.getMessage()+" Token Expired", HttpStatus.UNAUTHORIZED);
    }
}
