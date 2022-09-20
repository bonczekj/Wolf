package com.wolf.Domain;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Jirka on 25.01.2017.
 */
@Entity
public class Defdata {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer defid;
    private String descr;
    @OneToMany(mappedBy = "defid", cascade = CascadeType.ALL)
    private Set<Chartdata> charts;

    public Integer getDefid() {
        return defid;
    }

    public void setDefid(Integer defid) {
        this.defid = defid;
    }

    public String getDesc() {
        return descr;
    }

    public void setDesc(String descr) {
        this.descr = descr;
    }

    public Set<Chartdata> getCharts() {
        return charts;
    }

    public void setCharts(Set<Chartdata> charts) {
        this.charts = charts;
    }
}
