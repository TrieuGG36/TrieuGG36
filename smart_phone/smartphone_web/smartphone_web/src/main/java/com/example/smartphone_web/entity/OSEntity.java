package com.example.smartphone_web.entity;

import javax.persistence.*;

@Entity
@Table(name = "os", schema = "smart_phone", catalog = "")
public class OSEntity extends BaseEntity{ // Hệ điều hành

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME")
    private String name;
    /*@ManyToOne
    @JoinColumn(name = "LOAI_OS", referencedColumnName = "ID")
    private LoaiOsEntity LoaiOsEntity;*/
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    /*public com.example.world_phone.entity.LoaiOsEntity getLoaiOsEntity() {
        return LoaiOsEntity;
    }

    public void setLoaiOsEntity(com.example.world_phone.entity.LoaiOsEntity loaiOsEntity) {
        LoaiOsEntity = loaiOsEntity;
    }*/
}
