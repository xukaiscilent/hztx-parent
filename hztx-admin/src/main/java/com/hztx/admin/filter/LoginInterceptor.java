package com.hztx.admin.filter;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @auther xukai
 * @date 2020/3/20.
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {

    public static final String CERTIFICATE_SESSION_KEY = "_certificate_session_key";

    private AuthFailureHandler authFailureHandler = new DefaultAuthFailureHandler();



    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod method = (HandlerMethod) handler;
            AuthorCheck classAnnotation = method.getBeanType().getAnnotation(AuthorCheck.class);
            AuthorCheck methodAnnotation = method.getMethodAnnotation(AuthorCheck.class);
            boolean needAuth = needAuth(classAnnotation, methodAnnotation);
            if (needAuth) {
                String token = httpServletRequest.getHeader("token");
                QueryWrapper wrapper = new QueryWrapper();
              /*  wrapper.eq("token", token);
                UserCustomer userRegInfo = customerMapper.selectOne(wrapper);
                if (token == null || userRegInfo == null) {
                    httpServletRequest.getMethod();
                    throw new ChainException(ResultCode.USER_NOT_LOGIN, httpServletRequest.getMethod()+":登录已失效，请用账号密码重新登录");
                }*/
                AuthContext.put(new Certificate(token, "phone"));
            }
        }
        return true;
    }

  /*  @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod method = (HandlerMethod) handler;
            AuthorCheck classAnnotation = method.getBeanType().getAnnotation(AuthorCheck.class);
            AuthorCheck methodAnnotation = method.getMethodAnnotation(AuthorCheck.class);
            boolean needAuth = needAuth(classAnnotation, methodAnnotation);
            if (needAuth){
                String token = httpServletRequest.getHeader("token");
                if (token==null || token.equals("")) throw  new UserOperationException("用户状态异常，请检查登录状态");
               // Customer customer=customerMapper.selectByToken(token);
               *//* if (customer==null){
                    throw new ChainException(ResultCode.USER_NOT_EXIST,"用户状态异常，请检查登录状态");
                }*//*
                Certificate certificate = null;

                       // new Certificate(customer.getUserName(),customer.getId());
                AuthContext.put(certificate);
            }
        }

        return true;
    }*/


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {


    }

    private boolean needAuth(AuthorCheck classAnnotation, AuthorCheck methodAnnotation) {
        if (classAnnotation == null) {
            return methodAnnotation != null && methodAnnotation.authorCheck();
        } else {
            if (methodAnnotation == null) {
                return classAnnotation.authorCheck();
            } else {
                return methodAnnotation.authorCheck();
            }
        }
    }

    public void setAuthFailureHandler(AuthFailureHandler authFailureHandler) {
        this.authFailureHandler = authFailureHandler;
    }


}
