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

    @Query(value = "select new com.ndz.app.dto.AdministrativeUnitDto(e.id, e.code, e.name, e.level, e.parentId) " +
                " from AdministrativeUnit e where (e.voided is null and e.voided is false) " +
                " and e.parent is null ")
    List<AdministrativeUnitDto> getAllByProvince();

    @Query(value = "select new com.ndz.app.dto.AdministrativeUnitDto(e.id, e.code, e.name, e.level, e.parentId) " +
                 " from AdministrativeUnit e where (e.voided is null and e.voided is false) " +
                 " and e.parentId = :provinceId and e.level = 2")
    List<AdministrativeUnitDto> getAllDistrictByProvince(Long provinceId);

    @Query(value = "select new com.ndz.app.dto.AdministrativeUnitDto(e.id, e.code, e.name, e.level, e.parentId) " +
                " from AdministrativeUnit e where (e.voided is null and e.voided is false) " +
                " and e.parentId = :districtId and e.level = 3")
    List<AdministrativeUnitDto> getAllCommuneByDistrict(Long districtId);
}
