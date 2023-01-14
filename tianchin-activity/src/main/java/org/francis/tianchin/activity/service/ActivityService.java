package org.francis.tianchin.activity.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.francis.tianchin.activity.model.Activity;
import com.baomidou.mybatisplus.extension.service.IService;
import org.francis.tianchin.activity.model.dto.ActivityDTO;
import org.francis.tianchin.activity.model.request.ActivityRequest;
import org.francis.tianchin.activity.model.request.ActivitySearchRequest;
import org.francis.tianchin.common.core.page.PageRequest;

import java.util.List;

/**
 * <p>
 * 活动管理 服务类
 * </p>
 *
 * @author francis
 * @since 2023-01-03
 */
public interface ActivityService extends IService<Activity> {

    /**
     * 活动查询
     * @param pageRequest 分页参数
     * @param activitySearchRequest 活动查询参数
     * @return 活动分页列表
     */
    IPage<ActivityDTO> selectActivityDTOPage(PageRequest pageRequest, ActivitySearchRequest activitySearchRequest);


    void createActivity(ActivityRequest activityRequest);

    void editActivity(ActivityRequest activityRequest);

    ActivityDTO selectActivityById(Integer activityId);

    void removeActivity(Integer[] activityIds);

    List<ActivityDTO> selectActivityList(ActivitySearchRequest activitySearchRequest);
}
