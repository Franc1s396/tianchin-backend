package org.francis.tianchin.channel.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.extern.slf4j.Slf4j;
import org.francis.tianchin.channel.enums.ChannelCodeEnum;
import org.francis.tianchin.channel.model.Channel;
import org.francis.tianchin.channel.mapper.ChannelMapper;
import org.francis.tianchin.channel.model.request.ChannelCreateRequest;
import org.francis.tianchin.channel.model.request.ChannelImportRequest;
import org.francis.tianchin.channel.model.request.ChannelSearchRequest;
import org.francis.tianchin.channel.model.request.ChannelUpdateRequest;
import org.francis.tianchin.channel.service.ChannelService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.francis.tianchin.common.core.page.PageRequest;
import org.francis.tianchin.common.exception.ServiceException;
import org.francis.tianchin.common.utils.SecurityUtils;
import org.francis.tianchin.common.utils.bean.BeanValidators;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.validation.Validator;
import java.util.*;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author francis
 * @since 2022-12-18
 */
@Service
@Slf4j
public class ChannelServiceImpl extends ServiceImpl<ChannelMapper, Channel> implements ChannelService {
    @Autowired
    private ChannelMapper channelMapper;

    @Autowired
    protected Validator validator;

    @Override
    public IPage<Channel> selectChannelPage(PageRequest pageRequest, ChannelSearchRequest channelSearchRequest) {
        return channelMapper.selectChannelPage(pageRequest, channelSearchRequest);
    }

    @Override
    public Channel selectChannelByName(String channelName) {
        return channelMapper.selectChannelByName(channelName);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createChannel(ChannelCreateRequest channelCreateRequest) {
        if (Objects.nonNull(selectChannelByName(channelCreateRequest.getChannelName()))) {
            throw new ServiceException(ChannelCodeEnum.CHANNEL_NAME_TAKEN.getName(), 500);
        }
        Channel channel = new Channel();
        BeanUtils.copyProperties(channelCreateRequest, channel);
        channel.setCreateBy(SecurityUtils.getUsername());
        channelMapper.insert(channel);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void editChannel(ChannelUpdateRequest channelUpdateRequest) {
        Channel channel = Optional
                .ofNullable(channelMapper.selectById(channelUpdateRequest.getChannelId()))
                .orElseThrow(() -> new ServiceException(ChannelCodeEnum.CHANNEL_NOT_FOUND.getName()));
        BeanUtils.copyProperties(channelUpdateRequest, channel);
        channel.setUpdateBy(SecurityUtils.getUsername());
        channelMapper.updateById(channel);
    }

    @Override
    public Channel selectChannelById(Integer channelId) {
        return Optional
                .ofNullable(channelMapper.selectById(channelId))
                .orElseThrow(() -> new ServiceException(ChannelCodeEnum.CHANNEL_NOT_FOUND.getName()));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void removeChannel(Integer[] channelIds) {
        channelMapper.deleteBatchIds(Arrays.asList(channelIds));
    }

    @Override
    public List<Channel> selectChannelList(ChannelSearchRequest channelSearchRequest) {
        return channelMapper.selectChannelList(channelSearchRequest);
    }

    @Override
    public String importChannel(List<ChannelImportRequest> channelRequestList, boolean updateSupport, String operName) {
        // 校验导入的渠道列表是否为空
        if (CollectionUtils.isEmpty(channelRequestList)) {
            throw new ServiceException(ChannelCodeEnum.CHANNEL_LIST_EMPTY.getName());
        }
        // 操作人
        String username = SecurityUtils.getUsername();
        // 计算成功与失败的数据数量
        int successTotal = 0;
        int failureTotal = 0;
        int total = 1;
        // 成功与失败的消息构建
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        // 校验导入的渠道名称是否已存在
        for (ChannelImportRequest channelImportRequest : channelRequestList) {
            try {
                String channelName = channelImportRequest.getChannelName();
                Channel validChannel = channelMapper.selectChannelByName(channelName);
                // 当渠道不存在相同名称时
                if (Objects.isNull(validChannel)) {
                    BeanValidators.validateWithException(validator, channelImportRequest);
                    Channel channel = new Channel();
                    BeanUtils.copyProperties(channelImportRequest, channel);
                    channel.setCreateBy(username);
                    channelMapper.insert(channel);
                    ++successTotal;
                    successMsg.append("<br/>第" + total++ + "条渠道数据导入成功");
                // 渠道存在相同名称则查看是否开启了更新支持
                } else if (updateSupport) {
                    BeanValidators.validateWithException(validator, channelImportRequest);
                    Channel channel = new Channel();
                    BeanUtils.copyProperties(channelImportRequest, channel);
                    channelMapper.updateById(channel);
                    ++successTotal;
                    successMsg.append("<br/>第" + total++ + "条渠道数据导入成功");
                // 以上都不符合条件则此条渠道数据导入失败
                } else {
                    ++failureTotal;
                    successMsg.append("<br/>第" + total++ + "条渠道数据导入失败: 该渠道数据已存在");
                }
            } catch (Exception e) {
                ++failureTotal;
                String message = "<br/>第" + total++ + "条渠道数据导入失败:";
                failureMsg.append(message)
                        .append(e.getMessage());
                log.error(message, e);
            }
        }
        if (failureTotal > 0) {
            failureMsg.insert(0, "很抱歉，导入失败！共 " + failureTotal + " 条数据格式不正确，错误如下：");
            throw new ServiceException(failureMsg.toString());
        } else {
            successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successTotal + " 条，数据如下：");
        }
        return successMsg.toString();
    }

    @Override
    public List<Channel> selectChannelList(String channelName) {
        return channelMapper.selectChannelList(channelName);
    }
}
