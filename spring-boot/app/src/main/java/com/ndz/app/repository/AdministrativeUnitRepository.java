package com.ndz.app.repository;

import com.ndz.app.dto.AdministrativeUnitDto;
import com.ndz.app.entity.AdministrativeUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
    author: NMDuc
    created_at: 3/6/2024
    github: https://github.com/NDZwei
*/
@Repository
public interface AdministrativeUnitRepository extends JpaRepository<AdministrativeUnit, Long> {
    @Query(value = "select new com.ndz.app.dto.AdministrativeUnitDto(e) " +
            " from AdministrativeUnit e where (e.voided is null  or e.voided is false) and e.parent is null ")
    List<AdministrativeUnitDto> getAllProvince();
    List<AdministrativeUnit> getAllByParentId(Long parentId);
    AdministrativeUnit getByCode(String code);
}
