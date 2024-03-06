package com.ndz.app.repository;

import com.ndz.app.entity.AdministrativeUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
    author: NMDuc
    created_at: 3/6/2024
    github: https://github.com/NDZwei
*/
@Repository
public interface AdministrativeUnitRepository extends JpaRepository<AdministrativeUnit, Long> {
}
