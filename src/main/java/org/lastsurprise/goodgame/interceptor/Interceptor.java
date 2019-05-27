package org.lastsurprise.goodgame.interceptor;

import org.lastsurprise.goodgame.annotation.Check;
import org.lastsurprise.goodgame.model.User;
import org.lastsurprise.goodgame.service.UserService;
import org.lastsurprise.goodgame.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Optional;


public class Interceptor implements HandlerInterceptor {

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //请求静态资源跳过拦截
        if (!(handler instanceof HandlerMethod)){
            return true;
        }

        HandlerMethod handlerMethod= (HandlerMethod) handler;
        Method method=handlerMethod.getMethod();
        Cookie[] cookies=request.getCookies();
        //判断是否有check注解
        if (method.isAnnotationPresent(Check.class)){
            //判断cookie中是否有证书
            if (cookies!=null){
                for (Cookie cookie:cookies){
                    //判断证书是否正确
                    if (cookie.getName().equals("token")){
                        String token=cookie.getValue();
                        User user= JwtUtil.unsignJwt(token,User.class);
                        if (user!=null){
                            User checkUser=userService.findUserById(user.getId());
                            if (checkUser!=null&&checkUser.getUserPassword().equals(user.getUserPassword())){
                                return true;
                            }
                        }
                    }
                }
            }
            String referTo=request.getRequestURI();
            HttpSession session=request.getSession();
            session.setAttribute("referTo",referTo);
            StringBuffer stringBuffer=request.getRequestURL();
            stringBuffer.delete(stringBuffer.length()-referTo.length(),stringBuffer.length())
                    .append("/toLogin");
            response.sendRedirect(stringBuffer.toString());
            return false;
        }else{
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
