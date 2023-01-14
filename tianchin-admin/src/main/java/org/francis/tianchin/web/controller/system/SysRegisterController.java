package org.francis.tianchin.web.controller.system;

import org.francis.tianchin.common.core.domain.AjaxResult;
import org.francis.tianchin.framework.web.service.SysRegisterService;
import org.francis.tianchin.system.service.ISysConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.francis.tianchin.common.core.controller.BaseController;
import org.francis.tianchin.common.core.domain.model.RegisterBody;
import org.francis.tianchin.common.utils.StringUtils;

/**
 * 注册验证
 *
 * @author tianchin
 */
@RestController
public class SysRegisterController extends BaseController {
    @Autowired
    private SysRegisterService registerService;

    @Autowired
    private ISysConfigService configService;

    @PostMapping("/register")
    public AjaxResult register(@RequestBody RegisterBody user) {
        if (!("true".equals(configService.selectConfigByKey("sys.account.registerUser")))) {
            return error("当前系统没有开启注册功能！");
        }
        String msg = registerService.register(user);
        return StringUtils.isEmpty(msg) ? success() : error(msg);
    }
}
