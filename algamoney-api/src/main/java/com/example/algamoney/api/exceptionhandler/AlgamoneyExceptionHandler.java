package com.example.algamoney.api.exceptionhandler;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class AlgamoneyExceptionHandler extends ResponseEntityExceptionHandler {
	
	@Autowired
	private MessageSource messageSource;
	
	// 400
	
	 @Override
	    protected ResponseEntity<Object> handleHttpMessageNotReadable(final HttpMessageNotReadableException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
	        String mensagemUsuario = messageSource.getMessage("mensagem.invalida", null, LocaleContextHolder.getLocale());
	        String mensagemDesenvolvedor = ex.getCause().toString();
	        return handleExceptionInternal(ex, new Erro(mensagemUsuario, mensagemDesenvolvedor), headers, HttpStatus.BAD_REQUEST, request);
	    }
	 
	 @ExceptionHandler({ ConstraintViolationException.class })
	    public ResponseEntity<Object> handleBadRequest(final ConstraintViolationException ex, final WebRequest request) {
	        final String bodyOfResponse = "This should be application specific";
	        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	    }

	    @ExceptionHandler({ DataIntegrityViolationException.class })
	    public ResponseEntity<Object> handleBadRequest(final DataIntegrityViolationException ex, final WebRequest request) {
	        String mensagemUsuario = messageSource.getMessage("campo.nulos", null, LocaleContextHolder.getLocale());
	        String mensagemDesenvolvedor = ExceptionUtils.getRootCauseMessage(ex);
	        return handleExceptionInternal(ex, new Erro(mensagemUsuario + mensagemDesenvolvedor, mensagemDesenvolvedor), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	    }
	    
	    @Override
	    protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
	        String mensagemUsuario = messageSource.getMessage("campo.nulos", null, LocaleContextHolder.getLocale());
	        String mensagemDesenvolvedor = ExceptionUtils.getRootCauseMessage(ex);
	        return handleExceptionInternal(ex, new Erro(mensagemUsuario + mensagemDesenvolvedor, mensagemDesenvolvedor), headers, HttpStatus.BAD_REQUEST, request);
	    }


	    // 404

	    @ExceptionHandler(value = { EntityNotFoundException.class })
	    protected ResponseEntity<Object> handleNotFound(final RuntimeException ex, final WebRequest request) {
	        final String bodyOfResponse = "This should be application specific";
	        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	    }

	   
	    // 500

	    @ExceptionHandler({ NullPointerException.class, IllegalArgumentException.class, IllegalStateException.class })
	    /*500*/public ResponseEntity<Object> handleInternal(final RuntimeException ex, final WebRequest request) {
	        logger.error("500 Status Code", ex);
	        final String bodyOfResponse = "This should be application specific";
	        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
	    }
	    
	    public static class Erro {
	    	
	    	private String mensagemUsuario;
	    	private String mensagemDesenvolvedor;
	    	
			public Erro(String mensagemUsuario, String mensagemDesenvolvedor) {
				super();
				this.mensagemUsuario = mensagemUsuario;
				this.mensagemDesenvolvedor = mensagemDesenvolvedor;
			}

			public String getMensagemUsuario() {
				return mensagemUsuario;
			}

			public String getMensagemDesenvolvedor() {
				return mensagemDesenvolvedor;
			}
	    	
			
	    	
	    }

		
}
