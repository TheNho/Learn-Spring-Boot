package com.fukuda.Learn.Spring.Boot3.exception;

import com.fukuda.Learn.Spring.Boot3.dto.request.ApiRespone;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

// Annotation để thông báo nới tổng hợp tất cả exceptions
@ControllerAdvice
public class GlobalExceptionHandler {

    // Bắt exception còn lại
    @ExceptionHandler(value = Exception.class)
    ResponseEntity<ApiRespone> handlingRuntimeException(){
        ApiRespone apiRespone = new ApiRespone();
        apiRespone.setCode(ErrorCode.UNCATEGORIZED_EXCEPTION.getCode());
        apiRespone.setMessage(ErrorCode.UNCATEGORIZED_EXCEPTION.getMessage());
        return ResponseEntity.badRequest().body(apiRespone);
    }

    // MethodArgumentNotValidException throw exception của @Size dùng để Valid dữ liệu vào
    @ExceptionHandler( value = MethodArgumentNotValidException.class)
    ResponseEntity<ApiRespone> handlingValidation(MethodArgumentNotValidException exception){
        ErrorCode errorCode = ErrorCode.INVALID_KEY;

        // Bắt message key trong @Size sai
        String enumKey = exception.getFieldError().getDefaultMessage();
        try{
            errorCode = ErrorCode.valueOf(enumKey);
        } catch (IllegalArgumentException e) {

        }
        ApiRespone apiRespone = new ApiRespone();
        apiRespone.setCode(errorCode.getCode());
        apiRespone.setMessage(errorCode.getMessage());
        return ResponseEntity.badRequest().body(apiRespone);
    }

    @ExceptionHandler( value = AppException.class)
    ResponseEntity<ApiRespone> handlingAppException(AppException appException){
        ErrorCode errorCode = appException.getErrorCode();
        ApiRespone apiRespone  = new ApiRespone();
        apiRespone.setCode(errorCode.getCode());
        apiRespone.setMessage(errorCode.getMessage());
        return ResponseEntity.badRequest().body(apiRespone);
    }

}
