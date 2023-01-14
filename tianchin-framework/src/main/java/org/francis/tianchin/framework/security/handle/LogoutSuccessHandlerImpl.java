package org.francis.tianchin.framework.security.handle;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.francis.tianchin.common.constant.Constants;
import org.francis.tianchin.common.constant.HttpStatus;
import org.francis.tianchin.common.core.domain.AjaxResult;
import org.francis.tianchin.common.core.domain.model.LoginUser;
import org.francis.tianchin.common.utils.ServletUtils;
import org.francis.tianchin.common.utils.StringUtils;
import org.francis.tianchin.framework.manager.AsyncManager;
import org.francis.tianchin.framework.manager.factory.AsyncFactory;
import org.francis.tianchin.framework.web.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import com.alibaba.fastjson.JSON;

/**
 * 自定义退出处理类 返回成功
 *
 * @author tianchin
 */
@Configuration
public class LogoutSuccessHandlerImpl implements LogoutSuccessHandler {
    @Autowired
    private TokenService tokenService;

    /**
     * 退出处理
     *
     * @return
     */
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
        LoginUser loginUser = tokenService.getLoginUser(request);
        if (StringUtils.isNotNull(loginUser)) {
            String userName = loginUser.getUsername();
            // 删除用户缓存记录
            tokenService.delLoginUser(loginUser.getToken());
            // 记录用户退出日志
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(userName, Constants.LOGOUT, "退出成功"));
        }
        ServletUtils.renderString(response, JSON.toJSONString(AjaxResult.error(HttpStatus.SUCCESS, "退出成功")));
    }
}
