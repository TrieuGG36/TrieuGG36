package com.example.smartphones.entity;

import javax.persistence.*;


@Entity
@Table(name = "cart", schema = "smart_phone", catalog = "")
public class CartEntity extends BaseEntity{
    @Id
    @Column( name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "QUANTITY")
    @Basic
    private Long quantity;

    @ManyToOne
    @JoinColumn(name = "ID_PRODUCT_PROROPERTY", referencedColumnName = "ID")
    private ProductPropertyEntity idProduct;

    @Column(name = "ID_CUSTOMER")
    @Basic
    private Long idCustomer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public ProductPropertyEntity getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(ProductPropertyEntity idProduct) {
        this.idProduct = idProduct;
    }

    public Long getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(Long idCustomer) {
        this.idCustomer = idCustomer;
    }
}
