package com.qks.controlleradvicedemo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.qks.controlleradvicedemo.handler.ServiceException;

import java.util.Date;

/**
 * @author 15998
 */
@RestController
@Slf4j
public class TestController {

    @RequestMapping(value = "test", method = RequestMethod.GET)
    public String test(Model model, Date date) throws ServiceException {
        log.info((String) model.getAttribute("words"));
        log.info(date.toString());
        throw new ServiceException("controller exception");
    }
}