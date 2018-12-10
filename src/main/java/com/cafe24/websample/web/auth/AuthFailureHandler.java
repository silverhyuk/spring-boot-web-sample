package com.cafe24.websample.web.auth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

/**
 * 로그인 실패 핸들러
 *
 */
@Component
public class AuthFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    protected Logger logger = LoggerFactory.getLogger(AuthFailureHandler.class);
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {

        logger.debug("onAuthenticationFailure 실행 [{}]",request.getParameter("userId"));
        // 실패 시 response를 json 형태로 결과값 전달
        /*JsonObject jsonObject = new JsonObject();
        Gson gson = new Gson();

        jsonObject.addProperty("success", false);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().print(gson.toJson(jsonObject));
        response.getWriter().flush();*/

        request.setAttribute("userId", request.getParameter("userId"));
        request.getRequestDispatcher("/login.ws?error=true").forward(request, response);
    }

}