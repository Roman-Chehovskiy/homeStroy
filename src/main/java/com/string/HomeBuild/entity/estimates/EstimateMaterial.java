package com.string.HomeBuild.entity.estimates;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "estimatematerials")
public class EstimateMaterial {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "price")
    private double price;
    @Column(name = "countmaterial")
    private double countMaterial;
    @Column(name = "money")
    private double money;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "estimateconstruction_id")
    private EstimateConstruction estimateConstruction;

    public EstimateMaterial() {

    }

    public EstimateMaterial(String name, double price, double countMaterial, double money) {
        this.name = name;
        this.price = price;
        this.countMaterial = countMaterial;
        this.money = money;
    }

    public EstimateConstruction getEstimateConstruction() {
        return estimateConstruction;
    }

    public void setEstimateConstruction(EstimateConstruction estimateConstruction) {
        this.estimateConstruction = estimateConstruction;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getCountMaterial() {
        return countMaterial;
    }

    public void setCountMaterial(double countMaterial) {
        this.countMaterial = countMaterial;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }


}
