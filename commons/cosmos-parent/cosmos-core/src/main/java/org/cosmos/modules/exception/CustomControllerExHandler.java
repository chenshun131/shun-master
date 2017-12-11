package org.cosmos.modules.exception;

import com.alibaba.fastjson.JSON;
import org.cosmos.modules.web.RestResultDTO;
import org.cosmos.modules.web.StatusCode;
import org.cosmos.modules.web.utils.ServletUtils;
import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

/**
 * User: mew <p />
 * Time: 17/11/6 14:18  <p />
 * Version: V1.0  <p />
 * Description: 控制器层面的自定义异常处理器 <p />
 */
public class CustomControllerExHandler implements HandlerExceptionResolver {

    private static Logger logger = LoggerFactory.getLogger(CustomControllerExHandler.class);

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
                                         Exception ex) {
        RestResultDTO restResultDTO = new RestResultDTO(StatusCode.INTERNAL_SERVER_ERROR);
        // 显示抛出的异常信息
        if (ex instanceof UnloginException) {
            restResultDTO.setStatusCode("10001");
            restResultDTO.setMessage(ex.getMessage());
        } else if (ex instanceof ServiceException || ex instanceof IllegalArgumentException) {
            restResultDTO.setMessage(ex.getMessage());
        } else {
            logger.error("程序执行过程中发生了未知的异常:" + ex.getMessage());
            // 打印异常堆栈信息
            ex.printStackTrace();
        }
        try {
            String jsonString = JSON.toJSONString(restResultDTO);
            byte[] arr = jsonString.getBytes("UTF-8");
            // TO FIX CHROME ERROR : ERR_INCOMPLETE_CHUNKED_ENCODING
            response.setHeader("Content-Length", String.valueOf(arr.length));
            ServletUtils.renderJson(response, restResultDTO);
        } catch (UnsupportedEncodingException e) {
            logger.error(e.getMessage());
        }
        return null;
    }

}
