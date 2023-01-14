package org.francis.tianchin.channel.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.francis.tianchin.channel.model.Channel;
import com.baomidou.mybatisplus.extension.service.IService;
import org.francis.tianchin.channel.model.request.ChannelCreateRequest;
import org.francis.tianchin.channel.model.request.ChannelImportRequest;
import org.francis.tianchin.channel.model.request.ChannelSearchRequest;
import org.francis.tianchin.channel.model.request.ChannelUpdateRequest;
import org.francis.tianchin.common.core.page.PageRequest;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author francis
 * @since 2022-12-18
 */
public interface ChannelService extends IService<Channel> {

    /**
     * 渠道管理分页查询
     *
     * @param pageRequest        分页参数
     * @param channelSearchRequest
     * @return 渠道分页
     */
    IPage<Channel> selectChannelPage(PageRequest pageRequest, ChannelSearchRequest channelSearchRequest);

    /**
     * 根据名称查询渠道
     * @param channelName 渠道名称
     * @return 渠道
     */
    Channel selectChannelByName(String channelName);

    /**
     * 添加渠道
     * @param channelCreateRequest 添加参数
     */
    void createChannel(ChannelCreateRequest channelCreateRequest);

    /**
     * 更新渠道
     * @param channelUpdateRequest 参数
     */
    void editChannel(ChannelUpdateRequest channelUpdateRequest);

    /**
     * 根据渠道id获取渠道
     * @param channelId 渠道id
     * @return 渠道信息
     */
    Channel selectChannelById(Integer channelId);

    /**
     * 批量删除渠道
     * @param channelIds 渠道id数组
     */
    void removeChannel(Integer[] channelIds);

    /**
     * 根据条件查询渠道
     * @param channelSearchRequest 查询条件
     * @return 渠道列表
     */
    List<Channel> selectChannelList(ChannelSearchRequest channelSearchRequest);

    /**
     * 导入渠道
     * @param channelRequestList 要导入的渠道参数列表
     * @param updateSupport 是否支持更新
     * @param operName 操作名称
     * @return 导入响应消息
     */
    String importChannel(List<ChannelImportRequest> channelRequestList, boolean updateSupport, String operName);

    /**
     * 根据渠道名称搜索列表
     * @param channelName 渠道名称
     * @return 渠道列表
     */
    List<Channel> selectChannelList(String channelName);
}
