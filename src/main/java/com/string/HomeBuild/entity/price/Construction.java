package com.string.HomeBuild.entity.price;


import com.string.HomeBuild.entity.estimates.EstimateConstruction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "constructions")
public class Construction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "construction")
    private String construction;
    @OneToMany(mappedBy = "construction", cascade = CascadeType.ALL)
    private List<Material> materialsList;

    public Construction() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getConstruction() {
        return construction;
    }

    public void setConstruction(String construction) {
        this.construction = construction;
    }

    public List<Material> getMaterialsList() {
        return materialsList;
    }

    public void setMaterialsList(List<Material> materialsList) {
        this.materialsList = materialsList;
    }

    public void addMaterialToConstruction(Material material) {
        if (materialsList == null) {
            materialsList = new ArrayList<>();
        }
        materialsList.add(material);
        material.setConstruction(this);
    }
}

