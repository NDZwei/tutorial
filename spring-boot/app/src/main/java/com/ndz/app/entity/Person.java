package com.ndz.app.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/*
    author: NMDuc
    created_at: 2/22/2024
    github: https://github.com/NDZwei
*/
@Entity
@Table(name = "tbl_person")
@Getter
@Setter
@NoArgsConstructor
public class Person extends BaseEntity {
    @Column(name = "display_name")
    private String displayName;

    @Column(name = "birth_date")
    private Date birthDate;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "address_detail")
    private String addressDetail;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "administrative_unit_id")
    private AdministrativeUnit administrativeUnit;
}
