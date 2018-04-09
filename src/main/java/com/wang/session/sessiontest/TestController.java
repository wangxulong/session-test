package com.wang.session.sessiontest;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/")
public class TestController {

    @RequestMapping(value="/getSessionId")
    public String getSessionId(HttpServletRequest request){

        Object o = request.getSession().getAttribute("springboot");
        if(o == null){
            o = "spring boot 牛逼了!!!有端口"+request.getLocalPort()+"生成";
            request.getSession().setAttribute("springboot", o);
        }

        return "端口=" + request.getLocalPort() +  " sessionId=" + request.getSession().getId() +"<br/>"+o;
    }
    @RequestMapping(value="/logout")
    public String logout(HttpSession session){
        session.removeAttribute("springboot");
        return "success";
    }
    @RequestMapping(value="/getinfo")
    public String getInfo(HttpSession session, HttpServletRequest request){

       Object s = session.getAttribute("springboot");
       String ss=( s == null?"nothing":(String)s);
       int port =  request.getLocalPort();
       return ss +port;
    }



}