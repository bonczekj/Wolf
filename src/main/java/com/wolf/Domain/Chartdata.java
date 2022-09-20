package com.wolf.Domain;

import javax.persistence.*;

/**
 * Created by Jirka on 25.01.2017.
 */
@Entity
public class Chartdata {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer chartid;
    private String descr;
    @ManyToOne
    private Defdata defid;

    public Integer getChartid() {
        return chartid;
    }

    public void setChartid(Integer chartid) {
        this.chartid = chartid;
    }

    public Defdata getDefid() {
        return defid;
    }

    public void setDefid(Defdata defid) {
        this.defid = defid;
    }

    public String getDesc() {
        return descr;
    }

    public void setDesc(String descr) {
        this.descr = descr;
    }
}
