package com.dgaotech.dgfw.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dgaotech.dgfw.service.EmailService;

@ResponseBody
@RestController
public class EmailController {
    @Autowired
    private EmailService emailService;

    @ResponseBody
    @RequestMapping(value = "/sendMail", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean sendMail(String title, String content, String email) {
    	List s=emailService.sendMail("this is title", "hello ", null);
       return emailService.getEnabled();
    }
}