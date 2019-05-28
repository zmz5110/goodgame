package org.lastsurprise.goodgame.controller;

import org.lastsurprise.goodgame.model.Result;
import org.lastsurprise.goodgame.model.User;
import org.lastsurprise.goodgame.service.UserService;
import org.lastsurprise.goodgame.util.JwtUtil;
import org.lastsurprise.goodgame.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/login")
    public Result login(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String userName=request.getParameter("userName");
        String userPassword=request.getParameter("userPassword");
        Optional.ofNullable(userName)
                .orElseThrow(()->new Exception("用户名为空"));
        Optional.ofNullable(userPassword)
                .orElseThrow(()->new Exception("密码为空"));
        User user=userService.findUserByUserName(userName);
        String checkPassword= MD5Util.getMd5(userPassword,user.getSalt());
        if (checkPassword.equals(user.getUserPassword())){
            //token有效一周
            String token= JwtUtil.signJwt(user,1000L*3600L*24L*7L);
            Cookie cookie=new Cookie("token",token);
            cookie.setMaxAge(60*60*24*7);
            response.addCookie(cookie);
            HttpSession session=request.getSession();
            String referTo=session.getAttribute("referTo").toString();
            return Result.commonSuccessResult("登陆成功",referTo);
        } else {
            return Result.commonErrorResult("密码不正确");
        }
    }

    @PostMapping(value = "/register")
    public Result register(HttpServletRequest request,HttpServletResponse response) throws Exception{
        String userName=request.getParameter("userName");
        String userPassword=request.getParameter("userPassword");
        Optional.ofNullable(userName)
                .orElseThrow(()->new Exception("用户名为空"));
        Optional.ofNullable(userPassword)
                .orElseThrow(()->new Exception("密码为空"));
        if (userService.findUserByUserName(userName)==null){
            String salt=UUID.randomUUID().toString();
            String password=MD5Util.getMd5(userPassword,salt);
            User user=new User(){{
                this.setUsername(userName);
                this.setSalt(salt);
                this.setUserPassword(password);
            }};
            System.out.println(user);
            userService.InsertUser(user);
            return Result.commonSuccessResult("注册成功");
        }else{
            return Result.commonErrorResult("账户已存在");
        }
    }
}
