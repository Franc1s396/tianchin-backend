package org.francis.tianchin.channel.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.francis.tianchin.channel.model.Channel;
import org.francis.tianchin.channel.model.request.ChannelSearchRequest;
import org.francis.tianchin.common.core.page.PageRequest;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;

/**
 * @author francis
 * @since 2022-12-18
 */
public interface ChannelMapper extends BaseMapper<Channel> {

    default IPage<Channel> selectChannelPage(PageRequest pageRequest, ChannelSearchRequest channelSearchRequest) {
        Page<Channel> channelPage = new Page<>(pageRequest.getPageNo(), pageRequest.getPageSize());
        String channelName = channelSearchRequest.getChannelName();
        Integer type = channelSearchRequest.getType();
        Integer status = channelSearchRequest.getStatus();
        String beginTime = (String) channelSearchRequest.getParams().get("beginTime");
        String endTime = (String) channelSearchRequest.getParams().get("endTime");

        boolean existsTimeParams = StringUtils.hasText(beginTime) && StringUtils.hasText(endTime);

        LambdaQueryWrapper<Channel> queryWrapper = Wrappers.lambdaQuery(Channel.class)
                .like(StringUtils.hasText(channelName), Channel::getChannelName, channelName)
                .eq(Objects.nonNull(type), Channel::getType, type)
                .eq(Objects.nonNull(status), Channel::getStatus, status)
                .between(existsTimeParams, Channel::getGmtCreate, beginTime, endTime);

        return selectPage(channelPage, queryWrapper);
    }

    default Channel selectChannelByName(String channelName) {
        return selectOne(Wrappers.lambdaQuery(Channel.class)
                .eq(Channel::getChannelName, channelName)
                .eq(Channel::getDelFlag, 0));
    }

    default List<Channel> selectChannelList(ChannelSearchRequest channelSearchRequest) {
        String channelName = channelSearchRequest.getChannelName();
        Integer type = channelSearchRequest.getType();
        Integer status = channelSearchRequest.getStatus();
        String beginTime = (String) channelSearchRequest.getParams().get("beginTime");
        String endTime = (String) channelSearchRequest.getParams().get("endTime");

        boolean existsTimeParams = StringUtils.hasText(beginTime) && StringUtils.hasText(endTime);

        LambdaQueryWrapper<Channel> queryWrapper = Wrappers.lambdaQuery(Channel.class)
                .like(StringUtils.hasText(channelName), Channel::getChannelName, channelName)
                .eq(Objects.nonNull(type), Channel::getType, type)
                .eq(Objects.nonNull(status), Channel::getStatus, status)
                .between(existsTimeParams, Channel::getGmtCreate, beginTime, endTime);

        return selectList(queryWrapper);
    }

    default List<Channel> selectChannelList(String channelName){
        return selectList(Wrappers.lambdaQuery(Channel.class)
                .like(StringUtils.hasText(channelName),Channel::getChannelName,channelName));
    }
}
