package org.francis.tianchin.web.controller.channel;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.francis.tianchin.channel.model.Channel;
import org.francis.tianchin.channel.model.request.ChannelCreateRequest;
import org.francis.tianchin.channel.model.request.ChannelImportRequest;
import org.francis.tianchin.channel.model.request.ChannelSearchRequest;
import org.francis.tianchin.channel.model.request.ChannelUpdateRequest;
import org.francis.tianchin.channel.service.ChannelService;
import org.francis.tianchin.common.annotation.Log;
import org.francis.tianchin.common.core.domain.AjaxResult;
import org.francis.tianchin.common.core.page.PageRequest;
import org.francis.tianchin.common.enums.BusinessType;
import org.francis.tianchin.common.utils.SecurityUtils;
import org.francis.tianchin.common.utils.poi.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author Franc1s
 * @date 2022/12/23
 * @apiNote
 */
@Api(tags = "渠道管理")
@RestController
@RequestMapping("/tianchin/channel")
public class ChannelController {
    @Autowired
    private ChannelService channelService;

    @GetMapping("/page")
    @PreAuthorize("hasPermission('tianchin:channel:list')")
    @ApiOperation("渠道查询")
    public AjaxResult channelPage(PageRequest pageRequest, ChannelSearchRequest channelSearchRequest) {
        IPage<Channel> channelPage = channelService.selectChannelPage(pageRequest, channelSearchRequest);
        return AjaxResult.success(channelPage);
    }

    @GetMapping("/list")
    @PreAuthorize("hasPermission('tianchin:channel:list')")
    @ApiOperation("渠道查询")
    public AjaxResult channelList(@RequestParam(name = "渠道名称",required = false) String channelName) {
        List<Channel> channelPage = channelService.selectChannelList(channelName);
        return AjaxResult.success(channelPage);
    }


    @GetMapping("/{channelId}")
    @PreAuthorize("hasPermission('tianchin:channel:list')")
    @ApiOperation("渠道详情")
    public AjaxResult channelInfo(@PathVariable Integer channelId) {
        Channel channel = channelService.selectChannelById(channelId);
        return AjaxResult.success(channel);
    }

    @PostMapping("/export")
    @PreAuthorize("hasPermission('tianchin:channel:export')")
    @Log(title = "渠道管理", businessType = BusinessType.EXPORT)
    @ApiOperation("渠道导出")
    public void exportChannel(@ApiIgnore HttpServletResponse response, ChannelSearchRequest channelSearchRequest) {
        List<Channel> channelList = channelService.selectChannelList(channelSearchRequest);
        ExcelUtil<Channel> util = new ExcelUtil<>(Channel.class);
        util.exportExcel(response, channelList, "渠道数据");
    }

    @PostMapping("/importData")
    @Log(title = "渠道管理", businessType = BusinessType.IMPORT)
    @PreAuthorize("hasPermission('tianchin:channel:import')")
    @ApiOperation("渠道导入")
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception {
        ExcelUtil<ChannelImportRequest> util = new ExcelUtil<>(ChannelImportRequest.class);
        List<ChannelImportRequest> channelRequestList = util.importExcel(file.getInputStream());
        String operName = SecurityUtils.getUsername();
        String message = channelService.importChannel(channelRequestList, updateSupport, operName);
        return AjaxResult.success(message);
    }

    @PostMapping("/importTemplate")
    @ApiOperation("渠道导入模板下载")
    public void importTemplate(HttpServletResponse response) {
        ExcelUtil<Channel> util = new ExcelUtil<>(Channel.class);
        util.importTemplateExcel(response, "渠道数据");
    }

    @PostMapping("/create")
    @PreAuthorize("hasPermission('tianchin:channel:create')")
    @Log(title = "渠道管理", businessType = BusinessType.INSERT)
    @ApiOperation("添加渠道")
    public AjaxResult createChannel(@Validated @RequestBody ChannelCreateRequest channelCreateRequest) {
        channelService.createChannel(channelCreateRequest);
        return AjaxResult.success("添加成功");
    }

    @PutMapping("/edit")
    @PreAuthorize("hasPermission('tianchin:channel:edit')")
    @Log(title = "渠道管理", businessType = BusinessType.UPDATE)
    @ApiOperation("更新渠道")
    public AjaxResult editChannel(@Validated @RequestBody ChannelUpdateRequest channelUpdateRequest) {
        channelService.editChannel(channelUpdateRequest);
        return AjaxResult.success("更新成功");
    }

    @DeleteMapping("/{channelIds}")
    @PreAuthorize("hasPermission('tianchin:channel:remove')")
    @Log(title = "渠道管理", businessType = BusinessType.DELETE)
    @ApiOperation("删除渠道")
    public AjaxResult removeChannel(@PathVariable Integer[] channelIds) {
        channelService.removeChannel(channelIds);
        return AjaxResult.success("删除成功");
    }
}
