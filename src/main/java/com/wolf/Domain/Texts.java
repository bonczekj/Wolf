package com.wolf.Domain;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by Jirka on 05.01.2017.
 */
@Entity(name = "wolf_texts")
public class Texts {
    @Id
    private String deu;
    private String eng;
    private String csy;

    public String getDeu() {
        return deu;
    }

    public void setDeu(String deu) {
        this.deu = deu;
    }

    public String getEng() {
        return eng;
    }

    public void setEng(String eng) {
        this.eng = eng;
    }

    public String getCsy() {
        return csy;
    }

    public void setCsy(String csy) {
        this.csy = csy;
    }
}
