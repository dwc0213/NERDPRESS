package NERDPRESS.NERDPRESS.interceptor;

import NERDPRESS.NERDPRESS.controller.LoginAccount;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        LoginAccount loginAccount = (LoginAccount)request.getSession().getAttribute("loginAccount");

        if(loginAccount != null) {   // 로그인 이력이 있으면,
            return true;            // 가던 길 간다.
        } else {                    // 로그인 이력이 없으면,
            System.out.println("로그인이 필요한 페이지입니다. 로그인 화면으로 이동합니다.");
            String destURI = request.getRequestURI();   // 가던 길이 어딘지
            request.getSession().setAttribute("destURI", destURI);  // Session에 적어두고
            response.sendRedirect("/login");    // 로그인 페이지로 길 바꿈
            return false;
        }
    }
    /**
     * Controller의 Handler가 호출되고 난 후에 들어오는 메소드
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           @Nullable ModelAndView modelAndView) throws Exception {
    }
}

