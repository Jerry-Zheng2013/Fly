package com.ticketsystem.intercetor;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.ticketsystem.model.User;

@Component
public class UserInterceptor implements HandlerInterceptor {
	
	Logger log = LogManager.getLogger(UserInterceptor.class);

//     * 进入controller层之前拦截请求
//     * 返回值：表示是否将当前的请求拦截下来  false：拦截请求，请求别终止。true：请求不被拦截，继续执行
//     * Object obj:表示被拦的请求的目标对象（controller中方法）


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        //log.info("执行到了preHandle方法");
        //log.info(handler);
        User user = (User) request.getSession().getAttribute("session_user");
        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/index.html");//拦截后跳转的方法
            log.info("已成功拦截并转发跳转");
            return false;
        }
        //log.info("合格不需要拦截，放行");
        return true;
    }

//     * 处理请求完成后视图渲染之前的处理操作
//     * 通过ModelAndView参数改变显示的视图，或发往视图的方法


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        //log.info("执行了postHandle方法");
    }

//     * 视图渲染之后的操作


    @Override
    public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3) throws Exception {
        //log.info("执行到了afterCompletion方法");
    }

}
