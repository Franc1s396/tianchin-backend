package org.francis.tianchin.activity.mapper;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.francis.tianchin.activity.model.Activity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.francis.tianchin.activity.model.dto.ActivityDTO;
import org.francis.tianchin.activity.model.request.ActivitySearchRequest;

import java.util.List;

/**
 * @author francis
 * @since 2023-01-03
 */
public interface ActivityMapper extends BaseMapper<Activity> {

    IPage<ActivityDTO> selectActivityDTOPage(@Param("activityDTOPage") Page<ActivityDTO> activityDTOPage,
                                             @Param("request") ActivitySearchRequest activitySearchRequest);

    List<ActivityDTO> selectActivityList(@Param("request") ActivitySearchRequest activitySearchRequest);

    ActivityDTO selectActivityById(@Param("activityId") Integer activityId);

    default void updateExpireStatusByIds(List<Integer> activityIds){
        LambdaUpdateWrapper<Activity> updateWrapper = Wrappers.lambdaUpdate(Activity.class)
                .set(Activity::getStatus, 0)
                .in(Activity::getActivityId, activityIds);
        update(null, updateWrapper);
    }

    default Activity selectActivityByName(String activityName){
        return selectOne(Wrappers.lambdaQuery(Activity.class).eq(Activity::getActivityName, activityName));
    }
}
