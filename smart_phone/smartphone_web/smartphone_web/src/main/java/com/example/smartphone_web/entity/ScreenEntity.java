package com.example.smartphone_web.entity;

import javax.persistence.*;

@Entity
@Table(name = "screen", schema = "smart_phone", catalog = "")
public class ScreenEntity extends BaseEntity{

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME")
    private String name;


    /*@ManyToOne
    @JoinColumn(name = "LOAI_SCREEN", referencedColumnName = "ID")
    private LoaiScreenEntity LoaiScreenEntity;*/
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

    /*public com.example.world_phone.entity.LoaiScreenEntity getLoaiScreenEntity() {
        return LoaiScreenEntity;
    }
    public void setLoaiScreenEntity(com.example.world_phone.entity.LoaiScreenEntity loaiScreenEntity) {
        LoaiScreenEntity = loaiScreenEntity;
    }*/
}
