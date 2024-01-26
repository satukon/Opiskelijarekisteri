package com.satumaarit;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Opiskelija-luokka
 * @author satu
 */
public class Student {

    /**
     * 
     * Opiskelijan attribuutit ovat opiskelijaId, etunimi, sukunimi, kaupunki ja email
     */
    private int opiskelijaId;
    private String etunimi;
    private String sukunimi;
    private String kaupunki;
    private String mail;

    /**
     * 
     * Opiskelija-olion konstruktori
     * @param opiskelijaId 
     * @param etunimi
     * @param sukunimi
     * @param kaupunki
     * @param mail
     */
    public void Opiskelija(int opiskelijaId, String etunimi, String sukunimi, String kaupunki, String mail) {

        this.opiskelijaId = opiskelijaId;
        this.etunimi = etunimi;
        this.sukunimi = sukunimi;
        this.kaupunki = kaupunki;
        this.mail = mail;
    }

    /**
     * 
     * @return Palauttaa opiskelijan Id:n
     */
    public int getOpiskelijaId() {
        return this.opiskelijaId;
    }

    /**
     *
     * @return palauttaa opiskelijan etunimen
     */
    public String getEtunimi() {
        return this.etunimi;
    }

    /**
     *
     * @return palauttaa opiskelijan sukunimen
     */
    public String getSukunimi() {
        return this.sukunimi;
    }

    /**
     *
     * @return palauttaa opiskelijan kaupungin
     */
    public String getKaupunki() {
        return this.kaupunki;
    }

    /**
     *
     * @return palauttaa opiskelijan email-osoitteen
     */
    public String getMail() {
        return this.mail;
    }

    /**
     * 
     * @param opiskelijaIdInt asettaa opiskelijan id:ksi metodille välitetyn integer arvon
     */
    public void setOpiskelijaId(int opiskelijaIdInt) {
        opiskelijaId = opiskelijaIdInt;
    }

    /**
     * 
     * @param etunimiStr asettaa opiskelijan etunimeksi metodille välitetyn merkkijonon
     */
    public void setEtunimi(String etunimiStr) {
        etunimi = etunimiStr;
    }

    /**
     *
     * @param sukunimiStr asettaa opiskelijan sukunimeksi metodille välitetyn merkkijonon
     */
    public void setSukunimi(String sukunimiStr) {
        sukunimi = sukunimiStr;
    }

    /**
     *
     * @param kaupunkiStr asettaa opiskelijan kaupungiksi metodille välitetyn merkkijonon
     */
    public void setKaupunki(String kaupunkiStr) {
        kaupunki = kaupunkiStr;
    }

    /**
     *
     * @param mailStr asettaa opiskelijan email-osoitteeksi metodille välitetyn merkkijonon
     */
    public void setMail(String mailStr) {
        mail = mailStr;
    }

    /**
     *
     * ID-haku. Metodi käy läpi opiskelijat ja palauttaa opiskelija-olion haettavan Id:n perusteella
     * @param students ArrayList Opiskelija-olioita
     * @param id haettavan opiskelijan Id
     * @return Id:llä haettu Opiskelija-olio
     * @throws SQLException
     */
    public static Student getStudent(ArrayList<Student> students, int id) {
        Student student = new Student();

        for (Student s : students) {
            if (student.getOpiskelijaId() == id) {
                student.setOpiskelijaId(s.getOpiskelijaId());
                student.setEtunimi(s.getEtunimi());
                student.setSukunimi(s.getSukunimi());
                student.setKaupunki(s.getKaupunki());
                student.setMail(s.getMail());
            }
        }
        return student;
    }

    /**
     *
     * Apumetodi
     * Metodille välitetään Opiskelija-olioista koostuva ArrayList, josta muodostetaan String-muotoinen ArrayList.
     * Käytetään FXMLControllerissa pudotusvalikkojen tietojen täyttämiseen.
     * @param students ArrayList Opiskelija-olioita
     * @return ArrayList studentnames - String-muotoinen ArrayList (etunimi, sukunimi + opiskelijatunnus)
     */
    public static ArrayList<String> getStudentNames(ArrayList<Student> students) {
        ArrayList<String> studentnames = new ArrayList<>();
        students.stream().map(student -> student.getEtunimi() + " " + student.getSukunimi() +
                "\n Opiskelijatunnus: " + student.getOpiskelijaId() + " ").forEachOrdered(studentnames::add);
        return studentnames;
    }
}
