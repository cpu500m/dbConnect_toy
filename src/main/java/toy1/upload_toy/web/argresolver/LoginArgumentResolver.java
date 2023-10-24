package toy1.upload_toy.web.argresolver;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import toy1.upload_toy.domain.Member;
import toy1.upload_toy.web.annotation.Login;
import toy1.upload_toy.web.session.SessionConst;

import static toy1.upload_toy.web.session.SessionConst.LOGIN_IDENTIFIER;

/**
 * @login annotation을 이용해서 Member 객체로 파라미터 바인딩을 하기위한 리졸버.
 */
public class LoginArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(Login.class)
                && Member.class.isAssignableFrom(parameter.getParameterType());
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        HttpSession session = request.getSession(false);

        // 세션 저장소에 해당 회원이 없으면 null반환
        if (session == null || session.getAttribute(LOGIN_IDENTIFIER) == null) {
            return null;
        }

        return session.getAttribute(LOGIN_IDENTIFIER);
    }
}
