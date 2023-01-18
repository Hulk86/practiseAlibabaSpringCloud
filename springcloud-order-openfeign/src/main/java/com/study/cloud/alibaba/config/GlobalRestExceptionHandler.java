package com.study.cloud.alibaba.config;

import com.study.cloud.alibaba.commons.ApiResultEnum;
import com.study.cloud.alibaba.commons.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * 全局异常捕获
 * @author rstyro
 *
 */
@RestControllerAdvice
public class GlobalRestExceptionHandler {

	private Logger logger = LoggerFactory.getLogger(GlobalRestExceptionHandler.class);

	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<Result> NullPointer(NullPointerException ex){
		logger.error(ex.getMessage(),ex);
		return new ResponseEntity(Result.error(ApiResultEnum.FAILED), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(RuntimeException.class)

	public ResponseEntity<Result> RuntimeException(RuntimeException ex){
		logger.error(ex.getMessage(),ex);
		return new ResponseEntity(Result.error(ApiResultEnum.ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Result> exception(Exception ex){
		logger.error(ex.getMessage(),ex);
		return new ResponseEntity(Result.error(ApiResultEnum.ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
	}


}
