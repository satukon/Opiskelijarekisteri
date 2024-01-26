package com.satumaarit;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Suoritus-luokka
 * @author satu
 */
public class Grade {

    /**
     * 
     * Suorituksen attribuutit ovat suoritusId, opiskelijaId, kurssiId, arvosana ja arviointipäivämäärä
     */
    private int suoritusId;
    private int opiskelijaId;
    private int kurssiId;
    private int arvosana;
    private String suoritus_pvm;

    /**
     * 
     * Suoritus-olion konstruktori
     * @param suoritusId
     * @param kurssId
     * @param opiskelijaId
     * @param suoritus_pvm
     * @param arvosana
     */
    public void Grade(int suoritusId, int opiskelijaId, int kurssId, int arvosana, String suoritus_pvm) {
        this.suoritusId = suoritusId;
        this.opiskelijaId = opiskelijaId;
        this.kurssiId = kurssiId;
        this.arvosana = arvosana;
        this.suoritus_pvm = suoritus_pvm;
    }

    /**
     * 
     * @return palauttaa suorituksen Id:n
     */
    public int getSuoritusId() {
        return this.suoritusId;
    }
    
    /**
     *
     * @return palauttaa suorituksen suorittaneen opiskelijan Id:n
     */
    public int getOpiskelijaId() {
        return this.opiskelijaId;
    }

    /**
     *
     * @return palauttaa suoritetun kurssin Id:n
     */
    public int getKurssiId() {
        return this.kurssiId;
    }

    /**
     *
     * @return palauttaa suorituksen arvosanan
     */
    public int getArvosana() {
        return this.arvosana;
    }

    /**
     *
     * @return palauttaa suorituksen arviointipäivämäärän
     */
    public String getSuoritus_pvm() {
        return this.suoritus_pvm;
    }

    /**
     * 
     * @param suoritusIdInt asettaa suorituksen Id:ksi metodille välitetyn integer arvon
     */
    public void setSuoritusId(int suoritusIdInt) {
        suoritusId = suoritusIdInt;
    }
    
    /**
     *
     * @param opiskelijaIdInt asettaa suorituksen suorittaneen opiskelijan Id:ksi metodille välitetyn integer arvon
     */
    public void setOpiskelijaId(int opiskelijaIdInt) {
        opiskelijaId = opiskelijaIdInt;
    }

    /**
     *
     * @param kurssiIdInt asettaa suoritetun kurssin Id:ksi metodille välitetyn integer arvon
     */
    public void setKurssiId(int kurssiIdInt) {
        kurssiId = kurssiIdInt;
    }

    /**
     *
     * @param arvosanaInt asettaa suorituksen arvosanaksi metodille välitetyn integer arvon
     */
    public void setArvosana(int arvosanaInt) {
        arvosana = arvosanaInt;
    }

    /**
     *
     * @param suoritus_pvmStr asettaa suorituksen arviointipäivämääräksi metodille välitetyn merkkijonon
     */
    public void setSuoritus_pvm(String suoritus_pvmStr) {
        suoritus_pvm = suoritus_pvmStr;
    }

    /**
     *
     * ID-haku. Metodi käy läpi suoritukset ja palauttaa suoritus-olion haettavan Id:n perusteella
     * @param grades ArrayList Suoritus-olioita
     * @param id haettavan suorituksen Id
     * @return Id:llä haettu Suoritus-olio
     * @throws SQLException
     */
    public static Grade getGrade(ArrayList<Grade> grades, int id) {
        Grade grade = new Grade();

        for (Grade g : grades) {
            if (id == g.getSuoritusId()) {
                grade.setSuoritusId(g.getSuoritusId());
                grade.setOpiskelijaId(g.getOpiskelijaId());
                grade.setKurssiId(g.getKurssiId());
                grade.setArvosana(g.getArvosana());
                grade.setSuoritus_pvm(g.getSuoritus_pvm());
            }
        }
        return grade;
    }
}
