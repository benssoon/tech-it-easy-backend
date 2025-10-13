package nl.benzelinsky.techiteasybackend.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "wall_brackets")
public class WallBracket {
    private Long id;
    private String size;
    private Boolean isAdjustable;
    private String name;
    private Double price;

    public Long getId() {
        return this.id;
    }

    public String getSize() {
        return this.size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Boolean getAdjustable() {
        return this.isAdjustable;
    }

    public void setAdjustable(Boolean adjustable) {
        isAdjustable = adjustable;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return this.price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
