package com.wolf.Domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import java.util.Date;

/**
 * Created by Jirka on 10.01.2017.
 */
@Entity
@NamedNativeQuery( name= "LogItemConv.getLastTS",
                   query= "SELECT * FROM log_item_conv order by timestamp desc limit 1",
                   resultClass = LogItemConv.class)
public class LogItemConv {
    @Id
    private Date timestamp;
    private Integer p00;
    private Integer p01;
    private Integer p02;
    private Integer p03;
    private Integer p04;
    private Integer p05;
    private Integer p06;
    private Integer p07;
    private Integer p08;
    private Integer p09;
    private Integer p10;
    private Integer p11;
    private Integer p12;
    private Integer p13;
    private Integer p14;
    private Integer p15;
    private Integer p16;
    private Integer p17;
    private Integer p18;
    private Integer p19;
    private Integer p20;
    private Integer p21;
    private Integer p22;
    private Integer p23;
    private Integer p24;
    private Integer p25;
    private Integer p26;
    private Integer p27;
    private Integer p28;
    private Integer p29;
    private Integer p30;
    private Integer p31;
    private Integer p32;
    private Integer p33;
    private Integer p34;
    private Integer p35;
    private Integer p36;
    private Integer p37;
    private Integer p38;

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getP00() {
        return p00;
    }

    public void setP00(Integer p00) {
        this.p00 = p00;
    }

    public Integer getP01() {
        return p01;
    }

    public void setP01(Integer p01) {
        this.p01 = p01;
    }

    public Integer getP02() {
        return p02;
    }

    public void setP02(Integer p02) {
        this.p02 = p02;
    }

    public Integer getP03() {
        return p03;
    }

    public void setP03(Integer p03) {
        this.p03 = p03;
    }

    public Integer getP04() {
        return p04;
    }

    public void setP04(Integer p04) {
        this.p04 = p04;
    }

    public Integer getP05() {
        return p05;
    }

    public void setP05(Integer p05) {
        this.p05 = p05;
    }

    public Integer getP06() {
        return p06;
    }

    public void setP06(Integer p06) {
        this.p06 = p06;
    }

    public Integer getP07() {
        return p07;
    }

    public void setP07(Integer p07) {
        this.p07 = p07;
    }

    public Integer getP08() {
        return p08;
    }

    public void setP08(Integer p08) {
        this.p08 = p08;
    }

    public Integer getP09() {
        return p09;
    }

    public void setP09(Integer p09) {
        this.p09 = p09;
    }

    public Integer getP10() {
        return p10;
    }

    public void setP10(Integer p10) {
        this.p10 = p10;
    }

    public Integer getP11() {
        return p11;
    }

    public void setP11(Integer p11) {
        this.p11 = p11;
    }

    public Integer getP12() {
        return p12;
    }

    public void setP12(Integer p12) {
        this.p12 = p12;
    }

    public Integer getP13() {
        return p13;
    }

    public void setP13(Integer p13) {
        this.p13 = p13;
    }

    public Integer getP14() {
        return p14;
    }

    public void setP14(Integer p14) {
        this.p14 = p14;
    }

    public Integer getP15() {
        return p15;
    }

    public void setP15(Integer p15) {
        this.p15 = p15;
    }

    public Integer getP16() {
        return p16;
    }

    public void setP16(Integer p16) {
        this.p16 = p16;
    }

    public Integer getP17() {
        return p17;
    }

    public void setP17(Integer p17) {
        this.p17 = p17;
    }

    public Integer getP18() {
        return p18;
    }

    public void setP18(Integer p18) {
        this.p18 = p18;
    }

    public Integer getP19() {
        return p19;
    }

    public void setP19(Integer p19) {
        this.p19 = p19;
    }

    public Integer getP20() {
        return p20;
    }

    public void setP20(Integer p20) {
        this.p20 = p20;
    }

    public Integer getP21() {
        return p21;
    }

    public void setP21(Integer p21) {
        this.p21 = p21;
    }

    public Integer getP22() {
        return p22;
    }

    public void setP22(Integer p22) {
        this.p22 = p22;
    }

    public Integer getP23() {
        return p23;
    }

    public void setP23(Integer p23) {
        this.p23 = p23;
    }

    public Integer getP24() {
        return p24;
    }

    public void setP24(Integer p24) {
        this.p24 = p24;
    }

    public Integer getP25() {
        return p25;
    }

    public void setP25(Integer p25) {
        this.p25 = p25;
    }

    public Integer getP26() {
        return p26;
    }

    public void setP26(Integer p26) {
        this.p26 = p26;
    }

    public Integer getP27() {
        return p27;
    }

    public void setP27(Integer p27) {
        this.p27 = p27;
    }

    public Integer getP28() {
        return p28;
    }

    public void setP28(Integer p28) {
        this.p28 = p28;
    }

    public Integer getP29() {
        return p29;
    }

    public void setP29(Integer p29) {
        this.p29 = p29;
    }

    public Integer getP30() {
        return p30;
    }

    public void setP30(Integer p30) {
        this.p30 = p30;
    }

    public Integer getP31() {
        return p31;
    }

    public void setP31(Integer p31) {
        this.p31 = p31;
    }

    public Integer getP32() {
        return p32;
    }

    public void setP32(Integer p32) {
        this.p32 = p32;
    }

    public Integer getP33() {
        return p33;
    }

    public void setP33(Integer p33) {
        this.p33 = p33;
    }

    public Integer getP34() {
        return p34;
    }

    public void setP34(Integer p34) {
        this.p34 = p34;
    }

    public Integer getP35() {
        return p35;
    }

    public void setP35(Integer p35) {
        this.p35 = p35;
    }

    public Integer getP36() {
        return p36;
    }

    public void setP36(Integer p36) {
        this.p36 = p36;
    }

    public Integer getP37() {
        return p37;
    }

    public void setP37(Integer p37) {
        this.p37 = p37;
    }

    public Integer getP38() {
        return p38;
    }

    public void setP38(Integer p38) {
        this.p38 = p38;
    }

}
