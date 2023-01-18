package com.study.cloud.alibaba.config;

import com.study.cloud.alibaba.commons.ApiResultEnum;
import com.study.cloud.alibaba.commons.Result;
import javax.annotation.Priority;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;


/**
 * RestControllerAdvice 的注解里面组合了 @ControllerAdvice 和 @ResponseBody
 * 所以默认是返回 response body的，如果返回的是一个对象，他也会帮你把对象转换成 json, 同时默认的 ResponseStatus 是 HttpStatus.ok
 * 如果需要修改返回的类型，可以用以下的两种方式:
 * 1. 返回的类型是一个 ResponseEntity<T>的类型，这个可以帮忙设置 responseStatus
 * 2. 利用 @ResponseStatus的注解
 *
 *
 */
@RestControllerAdvice
@Order(99)
public class GlobalRestExceptionHandler {


	private Logger logger = LoggerFactory.getLogger(GlobalRestExceptionHandler.class);

	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<Result> NullPointer(NullPointerException ex){
		logger.error(ex.getMessage(),ex);
		return new ResponseEntity(Result.error(ApiResultEnum.FAILED), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(RuntimeException.class)

	public ResponseEntity<Result> RuntimeException(RuntimeException ex){
		try {
			logger.error(ex.getMessage(), ex);

			return new ResponseEntity(Result.error(ApiResultEnum.ERROR),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}finally {
			throw ex;
		}
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Result> exception(Exception ex){
		logger.error(ex.getMessage(),ex);
		return new ResponseEntity(Result.error(ApiResultEnum.ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
