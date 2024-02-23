package com.ndz.app.entity;

/*
    author: NMDuc
    created_at: 2/21/2024
    github: https://github.com/NDZwei
*/

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tbl_administrative_unit")
@Getter
@Setter
@NoArgsConstructor
public class AdministrativeUnit extends BaseEntity {
    @Column(name = "code", unique = true, length = 20, nullable = false)
    private String code;

    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "level")
    private Integer level;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private AdministrativeUnit parent;

    @Column(name = "parent_id", insertable = false, updatable = false)
    private Long parentId;

    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("name")
    private Set<AdministrativeUnit> children = new HashSet<>();
}
