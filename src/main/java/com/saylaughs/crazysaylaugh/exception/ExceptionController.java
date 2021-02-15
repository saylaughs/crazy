package com.saylaughs.crazysaylaugh.exception;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.ShiroException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@Slf4j
public class ExceptionController {

    /**
     * 捕捉shiro的异常
     * */
    @ExceptionHandler(ShiroException.class)
    public ResponseMsg handle401() {
        return new ResponseMsg("401" , "您没有权限访问！");
    }

    /**
     * 捕捉其他所有异常
    * */
    @ExceptionHandler(Exception.class)
    public String globalException(HttpServletRequest request, Throwable ex, Model model) {
        Map<String,Object> map=new HashMap<>();
        if (getStatus(request).value()==404) {
            ex.printStackTrace();
            System.out.println("进入404..........");
            map.put("code",ex.getMessage());
            map.put("status",getStatus(request).value());
            model.addAttribute("map",map);
            return "/error/404";
        }
        else if(getStatus(request).value()==500){
            ex.printStackTrace();
            System.out.println("进入500..........");
            map.put("code",ex.getMessage());
            map.put("status",getStatus(request).value());
            return "/error/500";
        }
        return "redirect:/error/500";
    }

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }
}