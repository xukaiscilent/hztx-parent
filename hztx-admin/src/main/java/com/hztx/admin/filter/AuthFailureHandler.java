package com.hztx.admin.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * @auther xukai
 * @date 2020/3/20.
 */
public interface AuthFailureHandler {

    void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException;

}
