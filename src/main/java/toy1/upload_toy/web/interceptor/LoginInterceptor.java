package toy1.upload_toy.web.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import static toy1.upload_toy.web.session.SessionConst.LOGIN_IDENTIFIER;

@Slf4j
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 인증 실패 시 로그인을 하면 기존에 요청했던 곳으로 redirect 해줘야 함.
        String uri = request.getRequestURI();

        // 세션이 없다면 생성 X
        HttpSession session = request.getSession(false);

        // 인증 실패 시 -> 로그인 화면으로 돌려보내고 컨트롤러로의 호출 흐름을 끊는다.
        if (session == null || session.getAttribute(LOGIN_IDENTIFIER) == null) {
            response.sendRedirect("/login?redirectURI=" + uri);

            // for debugging
            log.debug("로그인 실패");

            return false;
        }
        return true;
    }
}
