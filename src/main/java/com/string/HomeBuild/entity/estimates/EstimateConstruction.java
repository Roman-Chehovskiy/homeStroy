package com.string.HomeBuild.entity.estimates;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "estimateconstruction")
public class EstimateConstruction {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private long id;
    @Column(name = "construction")
    private String name;
    @OneToMany(mappedBy = "estimateConstruction", cascade = {CascadeType.ALL})
    private List<EstimateMaterial> materialsList;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "estimate_id")
    private Estimate estimate;

    public EstimateConstruction() {

    }

    public EstimateConstruction(String name) {
        this.name = name;
    }

    public EstimateConstruction(String name, List<EstimateMaterial> materialsList) {
        this.name = name;
        this.materialsList = materialsList;
    }

    public void addMaterialToConstruction(EstimateMaterial estimateMaterial) {
        if (materialsList == null) {
            materialsList = new ArrayList<>();
        }
        materialsList.add(estimateMaterial);
        estimateMaterial.setEstimateConstruction(this);
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

    public List<EstimateMaterial> getMaterialsList() {
        return materialsList;
    }

    public void setMaterialsList(List<EstimateMaterial> materialsList) {
        this.materialsList = materialsList;
    }

    public Estimate getEstimate() {
        return estimate;
    }

    public void setEstimate(Estimate estimate) {
        this.estimate = estimate;
    }


}
