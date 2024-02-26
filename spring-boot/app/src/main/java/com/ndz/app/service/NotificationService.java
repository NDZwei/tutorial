package com.ndz.app.service;

import com.ndz.app.dto.BaseSearch;
import com.ndz.app.dto.NotificationDto;
import com.ndz.app.entity.Notification;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

/*
    author: NMDuc
    created_at: 2/26/2024
    github: https://github.com/NDZwei
*/
public interface NotificationService {
    List<NotificationDto> getAllByCondition(BaseSearch dto);

    NotificationDto getById(Long id);

    Boolean seenNotification(Long id);

    Notification saveNotification(NotificationDto dto);

    Boolean delete(Long id);

    Page<NotificationDto> getPage(BaseSearch dto);
}
