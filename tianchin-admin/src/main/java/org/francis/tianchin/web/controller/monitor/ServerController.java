package org.francis.tianchin.web.controller.monitor;

import org.francis.tianchin.common.core.domain.AjaxResult;
import org.francis.tianchin.framework.web.domain.Server;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 服务器监控
 *
 * @author tianchin
 */
@RestController
@RequestMapping("/monitor/server")
public class ServerController {
    @PreAuthorize("hasPermission('monitor:server:list')")
    @GetMapping()
    public AjaxResult getInfo() throws Exception {
        Server server = new Server();
        server.copyTo();
        return AjaxResult.success(server);
    }
}
