package com.ndz.app.repository;

import com.ndz.app.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
    author: NMDuc
    created_at: 2/26/2024
    github: https://github.com/NDZwei
*/
@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    @Query("select e from Notification e where (e.voided is null or e.voided is false) and e.email like :email and e.emailToken like :emailToken")
    List<Notification> getByEmailAndToken(String email, String emailToken);
}
