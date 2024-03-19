package com.ndz.app.repository;

import com.ndz.app.dto.ParameterDto;
import com.ndz.app.entity.Parameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
    author: NMDuc
    created_at: 3/16/2024
    github: https://github.com/NDZwei
*/
@Repository
public interface ParameterRepository extends JpaRepository<Parameter, Long> {
    @Query(value = "select new com.ndz.app.dto.ParameterDto(e) from Parameter e " +
                   "where (e.voided is null or e.voided is false) and e.type = :type")
    List<ParameterDto> getAllByType(String type);

    Parameter getAllByCode(String code);
}
