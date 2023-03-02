package com.serfinanzas.prestamos.web;

import com.serfinanzas.prestamos.service.exception.LendException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

public abstract class CommandWebController {

    @ExceptionHandler(LendException.class)
    public ModelAndView handleError(HttpServletRequest req, LendException ex) {

        ModelAndView mav = new ModelAndView();

        mav.addObject("statusCode", ex.getStatusCode());

        mav.addObject("message", ex.getMessage());

        mav.addObject("exception", ex);

        mav.setStatus(HttpStatus.valueOf(ex.getStatusCode()));

        mav.setViewName("views/error");

        return mav;
    }
}
