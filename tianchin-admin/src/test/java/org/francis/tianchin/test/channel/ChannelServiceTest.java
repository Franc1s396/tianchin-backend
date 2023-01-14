package org.francis.tianchin.test.channel;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.francis.tianchin.channel.model.Channel;
import org.francis.tianchin.channel.service.ChannelService;
import org.francis.tianchin.common.core.page.PageRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Franc1s
 * @date 2022/12/23
 * @apiNote
 */
@SpringBootTest
public class ChannelServiceTest {
    @Autowired
    ChannelService channelService;
}
