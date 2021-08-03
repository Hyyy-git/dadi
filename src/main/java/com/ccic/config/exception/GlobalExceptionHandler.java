package com.ccic.config.exception;

import com.AlarmCenterServerApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @version V1.0
 * @Title:
 * @Package
 * @Description: 全局异常处理
 */
@ControllerAdvice
@Component
public class GlobalExceptionHandler {

    private static Logger log = LoggerFactory.getLogger(AlarmCenterServerApplication.class);

    @ExceptionHandler(value = Exception.class )
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ModelAndView processUnauthenticatedException(HttpServletRequest request, Exception  e) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("exception", e);
        modelAndView.addObject("url", request.getRequestURL());
        modelAndView.setViewName("error");
        log.error("got exception: {}", e.getClass() + ":" + e.getMessage());
        return modelAndView;
    }
}
