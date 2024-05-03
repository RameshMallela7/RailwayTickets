package com.springboot.RailwayTicket.excption;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.springboot.RailwayTicket.model.ErrorMessage;
import com.springboot.RailwayTicket.model.Problem;



@RestControllerAdvice
public class ControllerExceptionHandler {
	

	/**
	 * Note use base class if you wish to leverage its handling.
	 * Some code will need changing.
	 */
	private static final Logger logger = LoggerFactory.getLogger(ControllerExceptionHandler.class);
	
	@ExceptionHandler(RailwayException.class)
	public ResponseEntity<ProblemDetail> handleRailwayException(RailwayException exception){
		ErrorResponse error = ErrorResponse.create(exception,HttpStatusCode.valueOf(exception.getStatusCode()), exception.getMessage());
		return new ResponseEntity<>(error.getBody(), HttpStatusCode.valueOf(exception.getStatusCode()));
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ProblemDetail> handleException(Exception exception){
		ErrorResponse error = ErrorResponse.create(exception,HttpStatusCode.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), exception.getMessage());
		return new ResponseEntity<>(error.getBody(), HttpStatusCode.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()));
	}

	
	//UserExcption
	@ExceptionHandler(UserExcption.class)
	public ResponseEntity<ProblemDetail> handleRailwayException(UserExcption exception){
		ErrorResponse error = ErrorResponse.create(exception,HttpStatusCode.valueOf(exception.getStatusCode()), exception.getMessage());
		return new ResponseEntity<>(error.getBody(), HttpStatusCode.valueOf(exception.getStatusCode()));
	}

    
    @ExceptionHandler(Throwable.class) 
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity < Problem > problem(final Throwable e) {
        String message =e.getMessage();
		//might actually prefer to use a geeric mesasge
		
		message="Problem occured";
		UUID uuid = UUID.randomUUID();
		String logRef=uuid.toString();
		logger.error("logRef="+logRef, message, e);
		return new ResponseEntity <Problem> (new Problem(logRef, message), HttpStatus.INTERNAL_SERVER_ERROR);
    }
   
    
    
    @SuppressWarnings("unchecked")
	@ExceptionHandler(MethodArgumentNotValidException.class) 
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorMessage> handleMethodArgumentNotValid(MethodArgumentNotValidException ex
    		) {
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        List<ObjectError> globalErrors = ex.getBindingResult().getGlobalErrors();
        List<String> errors = new ArrayList<>(fieldErrors.size() + globalErrors.size());
        String error;
        for (FieldError fieldError : fieldErrors) {
            error = fieldError.getField() + ", " + fieldError.getDefaultMessage();
            errors.add(error);
        }
        for (ObjectError objectError : globalErrors) {
            error = objectError.getObjectName() + ", " + objectError.getDefaultMessage();
            errors.add(error);
        }
        ErrorMessage errorMessage = new ErrorMessage(errors);
      
        //Object result=ex.getBindingResult();//instead of above can also pass the more detailed bindingResult
        return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(ConstraintViolationException.class) 
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorMessage> handleConstraintViolatedException(ConstraintViolationException ex
    		) {
    	Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
    	
       
        List<String> errors = new ArrayList<>(constraintViolations.size() );
        String error;
        for (ConstraintViolation constraintViolation : constraintViolations) {
        	
            error =  constraintViolation.getMessage();
            errors.add(error);
        }
     
        ErrorMessage errorMessage = new ErrorMessage(errors);
        return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(MissingServletRequestParameterException.class) 
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorMessage> handleMissingServletRequestParameterException(MissingServletRequestParameterException ex
    		) {
    	
       List<String> errors = new ArrayList<>( );
        String error=ex.getParameterName()+", "+ex.getMessage();
       errors.add(error);
        ErrorMessage errorMessage = new ErrorMessage(errors);
        return new ResponseEntity<ErrorMessage>(errorMessage,  HttpStatus.BAD_REQUEST);
    }
}	
