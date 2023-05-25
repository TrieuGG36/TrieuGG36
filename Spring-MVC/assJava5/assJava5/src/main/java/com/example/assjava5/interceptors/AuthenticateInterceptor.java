package com.example.assjava5.interceptors;

import com.example.assjava5.constant.SessionArr;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@Component
public class AuthenticateInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler
    ) throws IOException {
        HttpSession session = request.getSession();

        if ( session.getAttribute(SessionArr.CURRENT_USERAdmin) == null ) {
            response.sendRedirect("http://localhost:8080/login");
            return false;
        }

        return true;
    }
}
