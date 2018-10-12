package com.gxk.shiro.shiro;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.web.filter.AccessControlFilter;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class OwnFilter extends AccessControlFilter {

    /**
     * 先执行：isAccessAllowed 再执行onAccessDenied
     * isAccessAllowed：表示是否允许访问；mappedValue就是[urls]配置中拦截器参数部分，
     * 如果允许访问返回true，否则false；
     * 如果返回true的话，就直接返回交给下一个filter进行处理。
     * 如果返回false的话，会往下执行onAccessDenied
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest
            , ServletResponse servletResponse, Object o) throws Exception {

        System.out.println("开始执行isAccessAllowed!!!!!!!!!!!!!");

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        try {

            //检测请求中是否包含token
            String authorization = request.getHeader("token");
            if (authorization == null) {
                log.error("[Token为空!]");
                throw new Exception("Token为空!");
            }
            //登入,转入打自定义的Realm的AuthenticationInfo进行验证
            SimpleToken simpleToken = new SimpleToken(authorization);
            // 提交给realm进行登入，如果错误他会抛出异常并被捕获
            getSubject(request, response).login(simpleToken);
            // 如果没有抛出异常则代表登入成功，返回true
            return true;

        } catch (Exception e) {

            e.printStackTrace();
            log.error(e.getMessage());
            onLoginFail(response);
            //跳到onAccessFilter处理
            return false;

        }
    }

    /**
     * onAccessDenied：表示当访问拒绝时是否已经处理了；如果返回true表示需要继续处理；
     * 如果返回false表示该拦截器实例已经处理了，将直接返回即可。
     * onAccessDenied：表示访问拒绝时是否自己处理，
     * 如果返回true表示自己不处理且继续拦截器链执行，返回false表示自己已经处理了（比如重定向到另一个页面）
     */
    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest
            , ServletResponse servletResponse) throws Exception {
        return false;
    }

    /**
     * 登录失败时默认返回401 状态码
     * @param response
     * @throws IOException
     */
    private void onLoginFail(ServletResponse response) throws IOException {
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    }

}
