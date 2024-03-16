package com.ndz.app.scheduled;

/*
    author: NMDuc
    created_at: 3/15/2024
    github: https://github.com/NDZwei
*/

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;

@Configuration
@EnableScheduling
@ConditionalOnProperty(name = "scheduler.enabled", matchIfMissing = false)
public class NDZScheduled {

    // fixedRate: Chỉ định thời gian giữa các lần thực thi của scheduled
    // fixedDelay: Chỉ định khoảng thời gian giữa lần kết thúc của scheduled và bắt đầu scheduled tiếp theo, tính bằng mili giây.
    // initialDelay: Chỉ định thời gian chờ trước khi bắt đầu thực hiện lần đầu tiên scheduled tính bằng mili giây
    // cron = "* * * * * * *": giây(0 - 59) - phút(0 - 59) - giờ(0 - 23) - ngày(0-7), tháng(1-12), năm (không bắt buộc)
    // Thực thi vào 12 giờ trưa (12:00:00) vào ngày 1 và ngày 15 của mỗi tháng: cron = "0 0 12 1,15 * ?":
        // giây - 0, phút - 0 - giờ 12, 1,15 - ngày 1 & 15, ngày trong tuần không xác định (dấu ?), Năm không xác định (dấu ?)
    @Scheduled(fixedRate = 2000L)
    public void firstJob() {
        System.out.println("Job current: " +  new Date());
    }
}
