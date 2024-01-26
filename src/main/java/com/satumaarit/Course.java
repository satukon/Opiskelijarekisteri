package com.satumaarit;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Kurssi-luokka
 * @author satu
 */
public class Course {

    /**
     * 
     * Kurssin attribuutit ovat kurssiId, kurssin nimi, opintopisteet ja kuvaus kurssista
    */
    private int kurssiId;
    private String nimi;
    private int opintopisteet;
    private String kuvaus;
    
    /**
     * 
     * Kurssi-olion konstruktori
     * @param kurssi_id
     * @param nimi
     * @param opintopisteet
     * @param kuvaus
    */
    public void Kurssi (int kurssi_id, String nimi, int opintopisteet, String kuvaus) {

        this.kurssiId = kurssi_id;
        this.nimi = nimi;
        this.opintopisteet = opintopisteet;
        this.kuvaus = kuvaus;
    }

    /**
     * 
     * @return palauttaa kurssin Id:n
    */
    public int getKurssiId() {
        return this.kurssiId;
    }

    /**
     *
     * @return palauttaa kurssin nimen
     */
    public String getNimi() {
        return this.nimi;
    }

    /**
     *
     * @return palauttaa kurssin opintopisttet
     */
    public int getOpintopisteet() {
        return this.opintopisteet;
    }

    /**
     *
     * @return palauttaa kurssin kuvauksen
     */
    public String getKuvaus() {
        return this.kuvaus;
    }

    /**
     * 
     * @param kurssiIdInt asettaa kurssin Id:ksi metodille välitetyn integer arvon
    */
    public void setKurssiId (int kurssiIdInt) {
        kurssiId = kurssiIdInt;
    }

    /**
     *
     * @param nimiStr asettaa kurssin nimeksi metodille välitetyn merkkijonon
     */
    public void setNimi (String nimiStr) {
        nimi = nimiStr;
    }

    /**
     *
     * @param opintopisteetInt asettaa kurssin opintopisteiden määräksi metodille välitetyn integer arvon
     */
    public void setOpintopisteet (int opintopisteetInt) {
        opintopisteet = opintopisteetInt;
    }

    /**
     *
     * @param kuvausStr asettaa kurssin kuvaukseksi metodille välitetyn merkkijonon
     */
    public void setKuvaus (String kuvausStr) {
        kuvaus = kuvausStr;
    }

    /**
     *
     * ID-haku. Metodi käy läpi kurssit ja palauttaa kurssi-olion haettavan Id:n perusteella
     * @param courses ArrayList Kurssi-olioita
     * @param id haettavan kurssin Id
     * @return Id:llä haettu Kurssi-olio
     * @throws SQLException
     */
    public static Course getCourse(ArrayList<Course> courses, int id) {
        Course course = new Course();

        for (Course c : courses) {
            if (course.getKurssiId() == id) {
                course.setKurssiId(c.getKurssiId());
                course.setNimi(c.getNimi());
                course.setOpintopisteet(c.getOpintopisteet());
                course.setKuvaus(c.getKuvaus());
            }
        }
        return course;
    }

    /**
     * Apumetodi
     * Metodille välitetään Kurssi-olioista koostuva ArrayList, josta muodostetaan String-muotoinen ArrayList.
     * Käytetään FXMLControllerissa pudotusvalikkojen tietojen täyttämiseen.
     * @param courses ArrayList Kurssi-olioita
     * @return String-muotoinen ArrayList kurssien nimistä
     */
    public static ArrayList<String> getCourseNames(ArrayList<Course> courses) {
        ArrayList<String> coursenames = new ArrayList<>();

        courses.forEach(course -> {
            coursenames.add(course.getNimi());
        });

        return coursenames;

    }
}
