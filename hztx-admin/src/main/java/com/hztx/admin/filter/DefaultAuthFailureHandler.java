package com.hztx.admin.filter;




import com.hztx.util.json.Renderer;
import com.hztx.util.json.SimpleRenderer;
import com.hztx.util.web.ApiResponseUtils;
import com.hztx.util.web.ErrorCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


/**
 * @auther xukai
 * @date 2018/11/1.
 */
public class DefaultAuthFailureHandler implements AuthFailureHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultAuthFailureHandler.class);

    Renderer renderer=new SimpleRenderer();

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        LOGGER.error("用户状态异常,请检查登录状态", ErrorCode.USER_OPERATION_ERROR.getCode());
        httpServletResponse.setHeader("Content-type", "text/json;charset=UTF-8");
        httpServletResponse.setCharacterEncoding("UTF-8");
        PrintWriter writer = httpServletResponse.getWriter();
        writer.write(renderer.render(ApiResponseUtils.failure(ErrorCode.USER_OPERATION_ERROR.getText())));
        writer.flush();
        writer.close();
    }
}
