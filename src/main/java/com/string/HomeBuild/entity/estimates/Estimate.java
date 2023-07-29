package com.string.HomeBuild.entity.estimates;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "estimates")
public class Estimate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
//    @NotBlank(message = "Название должно быть заполненно")
    private String name;
    @Column(name = "money")
//    @NotEmpty(message = "не выбрано не одно значение")
//    @Min(value = 1, message = "не выбрано не одно значение")
    private double money;
    //    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, mappedBy = "estimate")
    @OneToMany(mappedBy = "estimate", cascade = {CascadeType.ALL})
    private List<EstimateConstruction> constructionList;


    public Estimate() {
    }

    public Estimate(String name, double money) {
        this.name = name;
        this.money = money;
    }

    public Estimate(String name, double money, List<EstimateConstruction> constructionList) {
        this.name = name;
        this.money = money;
        this.constructionList = constructionList;
    }

    public void addConstructionToEstimate(EstimateConstruction estimateConstruction) {
        if (constructionList == null) {
            constructionList = new ArrayList<>();
        }
        constructionList.add(estimateConstruction);
        estimateConstruction.setEstimate(this);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public List<EstimateConstruction> getConstructionList() {
        return constructionList;
    }

    public void setConstructionList(List<EstimateConstruction> constructionList) {
        this.constructionList = constructionList;
    }


}
