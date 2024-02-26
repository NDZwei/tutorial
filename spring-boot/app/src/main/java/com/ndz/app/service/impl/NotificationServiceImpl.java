package com.ndz.app.service.impl;

import com.ndz.app.dto.BaseSearch;
import com.ndz.app.dto.NotificationDto;
import com.ndz.app.entity.Notification;
import com.ndz.app.repository.NotificationRepository;
import com.ndz.app.service.NotificationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/*
    author: NMDuc
    created_at: 2/26/2024
    github: https://github.com/NDZwei
*/
@Service
@Transactional
public class NotificationServiceImpl implements NotificationService {
    @Resource
    private NotificationRepository repository;

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<NotificationDto> getAllByCondition(BaseSearch dto) {
        String hql = "select new com.ndz.app.dto.NotificationDto(e) from Notification e ";
        String whereClause = "where (e.voided is false or e.voided is null) ";
        String orderBy = " order by e.updatedAt desc";
        if(StringUtils.hasText(dto.getTextSearch())) {
            whereClause += " and e.subject like :textSearch or e.content like :textSearch";
        }
        if(StringUtils.hasText(dto.getEmail())) {
            whereClause += " and e.email like :email";
        }
        if(StringUtils.hasText(dto.getEmailType())) {
            whereClause += " and e.emailType like :emailType";
        }
        if(StringUtils.hasText(dto.getStatusSendMail())) {
            whereClause += " and e.statusSendMail like :statusSendMail";
        }
        hql += whereClause + orderBy;
        Query querySelect = manager.createQuery(hql, NotificationDto.class);
        if(StringUtils.hasText(dto.getTextSearch())) {
            querySelect.setParameter("textSearch", '%' + dto.getTextSearch() + '%');
        }
        if(StringUtils.hasText(dto.getEmail())) {
            querySelect.setParameter("email", dto.getEmail());
        }
        if(StringUtils.hasText(dto.getEmailType())) {
            querySelect.setParameter("emailType", dto.getEmailType());
        }
        if(StringUtils.hasText(dto.getStatusSendMail())) {
            querySelect.setParameter("statusSendMail", dto.getStatusSendMail());
        }
        int numberRecord = dto.getNumberRecord() != null ? 5 : Integer.MAX_VALUE;
        querySelect.setMaxResults(numberRecord);
        return querySelect.getResultList();
    }

    @Override
    public NotificationDto getById(Long id) {
        Notification notification = repository.findById(id).orElse(null);
        return notification != null ? new NotificationDto(notification) : null;
    }

    @Override
    public Boolean seenNotification(Long id) {
        Notification notification = repository.findById(id).orElse(null);
        if(notification != null) {
            notification.setIsSeen(true);
            repository.save(notification);
            return true;
        }
        return false;
    }

    @Override
    public Notification saveNotification(NotificationDto dto) {
        if(dto == null) {
            return null;
        }
        Notification notification = new Notification();
        notification.setSubject(dto.getSubject());
        notification.setContent(dto.getContent());
        notification.setEmail(dto.getEmail());
        notification.setDisplayName(dto.getDisplayName());
        notification.setEmailType(dto.getEmailType());
        notification.setStatusSendMail(dto.getStatusSendMail());
        notification.setIsSeen(false);
        notification = repository.save(notification);
        return notification;
    }

    @Override
    public Boolean delete(Long id) {
        Notification notification = repository.findById(id).orElse(null);
        if(notification != null) {
            repository.delete(notification);
            return true;
        }
        return false;
    }

    @Override
    public Page<NotificationDto> getPage(BaseSearch dto) {
        int pageIndex = dto.getPageIndex() != null ? dto.getPageIndex() -1 : 0;
        int pageSize = dto.getPageSize() != null ? dto.getPageSize() : 10;
        String hql = "select new com.ndz.app.dto.NotificationDto(e) from Notification e ";
        String hqlCount = "select count(e.id) from Notification e ";
        String whereClause = "where (e.voided is false or e.voided is null) ";
        String orderBy = " order by e.updatedAt desc";
        if(StringUtils.hasText(dto.getTextSearch())) {
            whereClause += " and e.subject like :textSearch or e.content like :textSearch";
        }
        if(StringUtils.hasText(dto.getEmail())) {
            whereClause += " and e.email like :email";
        }
        if(StringUtils.hasText(dto.getEmailType())) {
            whereClause += " and e.emailType like :emailType";
        }
        if(StringUtils.hasText(dto.getStatusSendMail())) {
            whereClause += " and e.statusSendMail like :statusSendMail";
        }
        hql += whereClause + orderBy;
        hqlCount += whereClause;
        Query querySelect = manager.createQuery(hql, NotificationDto.class);
        Query queryCount = manager.createQuery(hqlCount);
        if(StringUtils.hasText(dto.getTextSearch())) {
            querySelect.setParameter("textSearch", '%' + dto.getTextSearch() + '%');
            queryCount.setParameter("textSearch", '%' + dto.getTextSearch() + '%');
        }
        if(StringUtils.hasText(dto.getEmail())) {
            querySelect.setParameter("email", dto.getEmail());
            queryCount.setParameter("email", dto.getEmail());
        }
        if(StringUtils.hasText(dto.getEmailType())) {
            querySelect.setParameter("emailType", dto.getEmailType());
            queryCount.setParameter("emailType", dto.getEmailType());
        }
        if(StringUtils.hasText(dto.getStatusSendMail())) {
            querySelect.setParameter("statusSendMail", dto.getStatusSendMail());
            queryCount.setParameter("statusSendMail", dto.getStatusSendMail());
        }
        int startPosition = pageIndex * pageSize;
        querySelect.setFirstResult(startPosition);
        querySelect.setMaxResults(pageSize);
        List<NotificationDto> results = querySelect.getResultList();
        long count = (long) queryCount.getSingleResult();
        Pageable pageable = PageRequest.of(pageIndex, pageSize);
        return new PageImpl<>(results, pageable, count);
    }
}
