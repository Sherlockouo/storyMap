/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.storymap.util.exception;

import com.storymap.util.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * 异常处理器
 *
 * @author wdf
 * @mail wdf.coder@gmail.com
 */
@Slf4j
@RestControllerAdvice
public class VolExceptionHandler {

	/**
	 * 处理自定义异常
	 */
	@ExceptionHandler(VolException.class)
	public R handleVolException(VolException e){
		R r = new R();
		r.put("code", e.getCode());
		r.put("msg", e.getMessage());

		return r;
	}

	@ExceptionHandler(NoHandlerFoundException.class)
	public R handlerNoFoundException(Exception e) {
		log.error(e.getMessage(), e);
		return R.error(404, "路径不存在，请检查路径是否正确");
	}

	@ExceptionHandler(NullPointerException.class)
	public R handlerNULL(Exception e) {
		log.error(e.getMessage(), e);
		return R.error(400, "参数为空");
	}

	@ExceptionHandler(DuplicateKeyException.class)
	public R handleDuplicateKeyException(DuplicateKeyException e){
		log.error(e.getMessage(), e);
		return R.error("数据库中已存在该记录");
	}


	// might be some problems here
	@ExceptionHandler(AuthorizationServiceException.class)
	public R handleAuthorizationException(AuthorizationServiceException e){
		log.error(e.getMessage(), e);
		return R.error("没有权限，请联系管理员授权");
	}

	@ExceptionHandler(Exception.class)
	public R handleException(Exception e){
		log.error(e.getMessage(), e);
		return R.error("系统异常,请联系管理员处理");
	}
}
