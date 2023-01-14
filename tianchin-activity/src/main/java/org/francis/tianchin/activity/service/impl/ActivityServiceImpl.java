package org.francis.tianchin.activity.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.francis.tianchin.activity.enums.ActivityCodeEnum;
import org.francis.tianchin.activity.model.Activity;
import org.francis.tianchin.activity.mapper.ActivityMapper;
import org.francis.tianchin.activity.model.dto.ActivityDTO;
import org.francis.tianchin.activity.model.request.ActivityRequest;
import org.francis.tianchin.activity.model.request.ActivitySearchRequest;
import org.francis.tianchin.activity.service.ActivityService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.francis.tianchin.common.core.page.PageRequest;
import org.francis.tianchin.common.exception.ServiceException;
import org.francis.tianchin.common.utils.SecurityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author francis
 * @since 2023-01-03
 */
@Service
@Slf4j
public class ActivityServiceImpl extends ServiceImpl<ActivityMapper, Activity> implements ActivityService {
    @Autowired
    ActivityMapper activityMapper;

    @Override
    public IPage<ActivityDTO> selectActivityDTOPage(PageRequest pageRequest, ActivitySearchRequest activitySearchRequest) {
        Page<ActivityDTO> activityDTOPage = new Page<>(pageRequest.getPageNo(), pageRequest.getPageSize());
        // 查询活动分页
        IPage<ActivityDTO> activityDTOIPage = activityMapper.selectActivityDTOPage(activityDTOPage, activitySearchRequest);
        // 更新过期的活动状态
        updateExpireStatus(activityDTOIPage.getRecords());
        // 更新已查询的分页状态
        return convertStatus(activityDTOIPage);
    }

    @Override
    public void createActivity(ActivityRequest activityRequest) {
        String activityName = activityRequest.getActivityName();
        Activity activity = activityMapper.selectActivityByName(activityName);
        if (Objects.nonNull(activity)) {
            throw new ServiceException(ActivityCodeEnum.ACTIVITY_EXISTS.getName());
        }
        activity = new Activity();
        BeanUtils.copyProperties(activityRequest, activity);
        activity.setCreateBy(SecurityUtils.getUsername());
        activity.setStatus(1);
        activityMapper.insert(activity);
        log.info("添加活动" + activity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void editActivity(ActivityRequest activityRequest) {
        Activity activity = Optional.ofNullable(activityMapper.selectById(activityRequest.getActivityId()))
                .orElseThrow(() -> new ServiceException("活动不存在"));
        BeanUtils.copyProperties(activityRequest, activity);
        activity.setUpdateBy(SecurityUtils.getUsername());
        activityMapper.updateById(activity);
        log.info("更新活动" + activity);
    }

    @Override
    public ActivityDTO selectActivityById(Integer activityId) {
        return activityMapper.selectActivityById(activityId);
    }

    @Override
    public void removeActivity(Integer[] activityIds) {
        activityMapper.deleteBatchIds(Arrays.asList(activityIds));
    }

    @Override
    public List<ActivityDTO> selectActivityList(ActivitySearchRequest activitySearchRequest) {
        return activityMapper.selectActivityList(activitySearchRequest);
    }

    public void updateExpireStatus(List<ActivityDTO> activityDTOList) {
        // 获取所有已经过期的id
        List<Integer> activityIds = activityDTOList.stream()
                .filter(activityDTO -> activityDTO.getStatus() == 1)
                .filter(activityDTO -> LocalDateTime.now().isAfter(activityDTO.getEndTime()))
                .map(ActivityDTO::getActivityId)
                .collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(activityIds)) {
            activityMapper.updateExpireStatusByIds(activityIds);
            log.info("更新活动过期状态,ids:{}", activityIds);
        }
    }

    private IPage<ActivityDTO> convertStatus(IPage<ActivityDTO> activityDTOIPage) {
        return activityDTOIPage.convert(activityDTO -> {
            if (LocalDateTime.now().isAfter(activityDTO.getEndTime())) {
                activityDTO.setStatus(0);
            }
            return activityDTO;
        });
    }
}
