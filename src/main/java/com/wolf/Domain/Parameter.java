package com.wolf.Domain;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by Jirka on 05.01.2017.
 */
@Entity(name = "parameters")
public class Parameter {
    @Id
    private Integer id;
    private String type;
    private String nameCs;
    private String nameDe;
    private String nameEn;

    private String unit;

    public Parameter(){
    }

    public Parameter(Integer id, String type, String nameCs, String nameDe, String nameEn, String unit){
        this.id = id;
        this.type = type;
        this.nameCs = nameCs;
        this.nameDe = nameDe;
        this.nameEn = nameEn;
        this.unit = unit;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNameCs() {
        return nameCs;
    }

    public void setNameCs(String nameCs) {
        this.nameCs = nameCs;
    }

    public String getNameDe() {
        return nameDe;
    }

    public void setNameDe(String nameDe) {
        this.nameDe = nameDe;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

}
