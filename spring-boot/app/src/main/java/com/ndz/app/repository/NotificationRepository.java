package com.ndz.app.repository;

import com.ndz.app.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
    author: NMDuc
    created_at: 2/26/2024
    github: https://github.com/NDZwei
*/
@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
