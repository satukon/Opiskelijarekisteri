package com.satumaarit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Tietokannan luominen ja käsittely
 * @author satu
 */
public class Database {

    Connection con;
    
    /**
     * 
     * Avaa tietokantayhteyden
     * @return tietokantayhteys
     * @throws SQLException
     */
    public static Connection openConnection() throws SQLException {
        Connection con = DriverManager.getConnection("jdbc:h2:mem:karelia_satumako");
        System.out.println("\t>> Tietokantayhteys muodostettiin");
        return con;
    }

    /**
     * 
     * Sulkee tietokantayhteyden
     * @param c käytettävä tietokantayhteys joka välitetään metodille
     * @throws SQLException
     */
    public static void closeConnection(Connection c) throws SQLException {
        if (c != null) {
            c.close();
        }
        System.out.println("\t>> Tietokantayhteys suljettiin");
    }

    /**
     * 
     * Luo taulut tietokantaan
     * @param c käytettävä tietokantayhteys
     * @throws SQLException
     */    
    public static void createTables(Connection c) throws SQLException {
        PreparedStatement ps = c.prepareStatement(
        "CREATE TABLE Opiskelija ("
                + "OpiskelijaId INT NOT NULL PRIMARY KEY AUTO_INCREMENT,"
                + "Etunimi VARCHAR(40) NOT NULL,"
                + "Sukunimi VARCHAR(40) NOT NULL,"
                + "Kaupunki VARCHAR(40) NOT NULL,"
                + "Mail VARCHAR(40) NOT NULL)"
            );

        ps.execute();
        System.out.println("\t>> Luotu uusi taulu tietokantaan: Opiskelija");

        ps = c.prepareStatement(
                "CREATE TABLE Kurssi ("
                        + "KurssiId INT NOT NULL PRIMARY KEY AUTO_INCREMENT,"
                        + "Nimi VARCHAR(50) NOT NULL,"
                        + "Opintopisteet INT NOT NULL,"
                        + "Kuvaus VARCHAR(500) NOT NULL)"
        );

        ps.execute();
        System.out.println("\t>> Luotu uusi taulu tietokantaan: Kurssi");

        ps = c.prepareStatement(
                "CREATE TABLE Suoritus ("
                        + "SuoritusId INT NOT NULL PRIMARY KEY AUTO_INCREMENT,"
                        + "OpiskelijaId INT NOT NULL,"
                        + "KurssiId INT NOT NULL,"
                        + "Arvosana INT NOT NULL,"
                        + "Pvm DATE NOT NULL)"
        );

        ps.execute();
        System.out.println("\t>> Luotu uusi taulu tietokantaan: Suoritus");
    }
    
    /**
     * 
     * Lisää foreign keyt Suoritus-tauluun
     * @param c käytettävä tietokantayhteys
     * @throws SQLException
     */      
     public static void alterTables(Connection c) throws SQLException {
        PreparedStatement ps = c.prepareStatement(
            "ALTER TABLE Suoritus "
            + "ADD CONSTRAINT Suoritus_ibfk_1 "
            + "FOREIGN KEY (OpiskelijaId) REFERENCES Opiskelija(OpiskelijaId) "
            + "ON DELETE CASCADE;"
        );
         ps.execute();

        ps = c.prepareStatement(
            "ALTER TABLE Suoritus "
            + "ADD CONSTRAINT Suoritus_ibfk_2 "
            + "FOREIGN KEY (KurssiId) REFERENCES Kurssi(KurssiId) "
            + "ON DELETE CASCADE;"
        );
        ps.execute();
        System.out.println("\t>> Foreign key constraintit lisätty tietokannan tauluun: Suoritus");
    }

    /**
     *
     * Lisää mallidataa tietokantaan
     * @param conn käytettävä tietokantayhteys
     * @throws SQLException
     */
    public static void addSampleData(Connection conn) throws SQLException {
         // Opiskelijoita:
        addStudent(conn, "Maija", "Meikäläinen", "Joensuu", "maija.meikalainen@gmail.com");
        addStudent(conn, "Matti", "Meikäläinen", "Joensuu", "matti.meikalainen@gmail.com");
        addStudent(conn, "Oona", "Opiskelija", "Kuopio", "oona.opiskelija@gmail.com");
        addStudent(conn, "Heikki", "Hikke", "Joensuu", "heikki.hikke@gmail.com");
        addStudent(conn, "Teppo", "Tavis", "Oulu", "teppo.tavis@gmail.com");

        // Kursseja:
        addCourse(conn, "Aivokirurgia", 5, "Sisältää käytännön harjoittelua pienryhmissä.");
        addCourse(conn, "Rakettitieteen peruskurssi", 3, "Otetaan perusasiat haltuun.");
        addCourse(conn, "Rakettitieteen jatkokurssi", 3, "Jatkokurssi.");
        addCourse(conn, "Keskiaikaiset kidutusmenetelmät", 5, "Nämä on hyvä osata.");
        addCourse(conn, "Ydinfysiikka", 10, "Tosi helppo kurssi!");

        // Suoritusmerkintöjä:
        addGrade(conn, 1, 1, 1, "2017-08-25");
        addGrade(conn, 1, 2, 3, "2018-03-03");
        addGrade(conn, 1, 3, 3, "2018-12-20");
        addGrade(conn, 1, 4, 2, "2019-04-20");
        addGrade(conn, 2, 2, 3, "2018-04-21");
        addGrade(conn, 2, 3, 4, "2018-04-21");
        addGrade(conn, 2, 1, 3, "2018-04-21");
        addGrade(conn, 3, 1, 3, "2022-01-25");
        addGrade(conn, 4, 1, 5, "2022-05-03");
        addGrade(conn, 5, 1, 5, "2017-08-25");
        addGrade(conn, 4, 4, 5, "2021-02-25");
        addGrade(conn, 4, 5, 5, "2022-05-25");
        addGrade(conn, 4, 2, 5, "2017-08-25");
        addGrade(conn, 4, 3, 5, "2018-08-25");

        System.out.println("\t>> Tietokantaan lisättiin esimerkkidataa.");
    }

    /**
     * Lukee tietokannasta kaikki opiskelija-talulun rivit ja tekee niistä Opiskelija-olioita
     * @param c käytettävä tietokantayhteys
     * @return ArrayList students - Lista Opiskelija-olioista
     * @throws SQLException
     */
    public static ArrayList<Student> readAllStudents(Connection c) throws SQLException {
        ArrayList<Student> students = new ArrayList<>();

        Statement stmt = c.createStatement();

        ResultSet rs = stmt.executeQuery(
                "SELECT OpiskelijaId, Etunimi, Sukunimi, Kaupunki, Mail FROM Opiskelija ORDER BY Sukunimi"
        );

        while (rs.next()) {
            Student student = new Student();

            int id = rs.getInt("OpiskelijaId");
            student.setOpiskelijaId(id);

            String etunim = rs.getString("Etunimi");
            student.setEtunimi(etunim);

            String sukunim = rs.getString("Sukunimi");
            student.setSukunimi(sukunim);

            String kaupunki = rs.getString("Kaupunki");
            student.setKaupunki(kaupunki);

            String mail = rs.getString("Mail");
            student.setMail(mail);

            students.add(student);
        }
        System.out.println("\t>> Opiskelijat luettiin tietokannasta");
        return students;
    }

    /**
     *
     * Lukee tietokannasta kaikki Kurssi-talulun rivit ja tekee niistä Kurssi-olioita
     * @param c käytettävä tietokantayhteys
     * @return ArrayList courses - Lista Kurssi-olioista
     * @throws SQLException
     */
    public static ArrayList<Course> readAllCourses(Connection c) throws SQLException {
        ArrayList<Course> courses = new ArrayList<>();

        Statement stmt = c.createStatement();

        ResultSet rs = stmt.executeQuery(
                "SELECT KurssiId, Nimi, Opintopisteet, Kuvaus FROM Kurssi ORDER BY Nimi"
        );

        while (rs.next()) {
            Course course = new Course();

            int id = rs.getInt("KurssiId");
            course.setKurssiId(id);

            String nimi = rs.getString("Nimi");
            course.setNimi(nimi);

            int op = rs.getInt("Opintopisteet");
            course.setOpintopisteet(op);

            String info = rs.getString("Kuvaus");
            course.setKuvaus(info);

            courses.add(course);
        }
        System.out.println("\t>> Kurssit luettiin tietokannasta");
        return courses;
    }

    /**
     * Lukee tietokannasta kaikki Suoritus-talulun rivit ja tekee niistä Suoritus-olioita
     * @param c käytettävä tietokantayhteys
     * @return  ArrayList grades - Lista Suoritus-olioista
     * @throws SQLException
     */
    public static ArrayList<Grade> readAllGrades(Connection c) throws SQLException {
        ArrayList<Grade> grades = new ArrayList<>();
        Statement stmt = c.createStatement();
        ResultSet rs = stmt.executeQuery(
                "SELECT SuoritusId, OpiskelijaId, KurssiId, Arvosana, Pvm FROM Suoritus ORDER BY Pvm"
        );

        while (rs.next()) {
            Grade grade = new Grade();

            int suoritusId = rs.getInt("SuoritusId");
            grade.setSuoritusId(suoritusId);

            int opiskelijaId = rs.getInt("OpiskelijaId");
            grade.setOpiskelijaId(opiskelijaId);

            int kurssiId = rs.getInt("KurssiId");
            grade.setKurssiId(kurssiId);
            
            int arvosana = rs.getInt("Arvosana");
            grade.setArvosana(arvosana);
            
            String pvm = rs.getString("Pvm");
            grade.setSuoritus_pvm(pvm);
            
            grades.add(grade);
        }
        System.out.println("\t>> Suoritukset luettiin tietokannasta");
        return grades;
    }

    /**
     *
     * Lisää uuden opiskelijan tiedot tietokantaan
     * @param c käytettävä tietokantayhteys
     * @param etunimi
     * @param sukunimi
     * @param kaupunki
     * @param mail
     * @throws SQLException
     */
    public static void addStudent(Connection c, String etunimi, String sukunimi, String kaupunki, String mail) throws SQLException {
        PreparedStatement ps = c.prepareStatement(
                "INSERT INTO Opiskelija (Etunimi, Sukunimi, Kaupunki, Mail) "
                        + "VALUES(?, ?, ?, ?)");

        ps.setString(1, etunimi);
        ps.setString(2, sukunimi);
        ps.setString(3, kaupunki);
        ps.setString(4, mail);
        ps.execute();
        System.out.println("\t>> Tietokantaan lisättiin uusi opiskelija: " + etunimi + " " + sukunimi + " Kaupunki: " + kaupunki + " Email: " + mail);
    }

    /**
     *
     * Lisää uuden kurssin tiedot tietokantaan
     * @param c käytettävä tietokantayhteys
     * @param kurssi
     * @param opintopisteet
     * @param kuvaus
     * @throws SQLException
     */
    public static void addCourse(Connection c, String kurssi, int opintopisteet, String kuvaus) throws SQLException {
        PreparedStatement ps = c.prepareStatement(
                "INSERT INTO Kurssi (Nimi, Opintopisteet, Kuvaus) "
                        + "VALUES(?, ?, ?)");

        ps.setString(1, kurssi);
        ps.setInt(2, opintopisteet);
        ps.setString(3, kuvaus);
        ps.execute();
        System.out.println("\t>> Tietokantaan lisättiin uusi kurssi: " + kurssi + " opintopisteet: " + opintopisteet + " kuvaus: " + kuvaus);
    }

    /**
     *
     * Lisää uuden suorituksen tiedot tietokantaan
     * @param c käytettävä tietokantayhteys
     * @param opiskelijaId
     * @param kurssiId
     * @param arvosana
     * @param pvm
     * @throws SQLException
     */
    public static void addGrade(Connection c, int opiskelijaId, int kurssiId, int arvosana, String pvm) throws SQLException {
        PreparedStatement ps = c.prepareStatement(
                "INSERT INTO Suoritus (OpiskelijaId, KurssiId, Arvosana, Pvm) "
                        + "VALUES(?, ?, ?, ?)");

        ps.setInt(1, opiskelijaId);
        ps.setInt(2, kurssiId);
        ps.setInt(3, arvosana);
        ps.setString(4, pvm);
        ps.execute();
        System.out.println("\t>> Tietokantaan lisättiin uusi suoritus. Opiskelijan id: " + opiskelijaId + " kurssin id: " + kurssiId + " arvosana: " + arvosana + " suorituspvm: " + pvm);

    }

    /**
     *
     * Päivittää opiskelijan tiedot tietokantaan
     * @param c käytettävä tietokantayhteys
     * @param id opiskelijan id
     * @param etunimi
     * @param sukunimi
     * @param kaupunki
     * @param email
     * @throws SQLException
     */
    public static void updateStudent(Connection c, int id, String etunimi, String sukunimi, String kaupunki, String email) throws SQLException {
        PreparedStatement ps = c.prepareStatement(
                "UPDATE  Opiskelija SET Etunimi = ?, Sukunimi = ?, Kaupunki = ?, Mail = ? WHERE OpiskelijaId = " + id);

        ps.setString(1, etunimi);
        ps.setString(2, sukunimi);
        ps.setString(3, kaupunki);
        ps.setString(4, email);
        ps.executeUpdate();

        System.out.println("\t>> Opiskelijan " + etunimi + " " + sukunimi + " tiedot päivitettiin tietokantaan");
    }
    
    /**
     * 
     * Päivittää kurssin tiedot tietokantaan
     * @param c käytettävä tietokantayhteys
     * @param id kurssin id
     * @param nimi
     * @param op
     * @param kuvaus
     * @throws SQLException
     */
    public static void updateCourse(Connection c, int id, String nimi, int op, String kuvaus) throws SQLException {
        PreparedStatement ps = c.prepareStatement(
                "UPDATE  Kurssi SET Nimi = ?, Opintopisteet = ?, Kuvaus = ? WHERE KurssiId = " + id);

        ps.setString(1, nimi);
        ps.setInt(2, op);
        ps.setString(3, kuvaus);
        ps.executeUpdate();

        System.out.println("\t>> Kurssin " + nimi + " tiedot päivitettiin tietokantaan");
    }

    /**
     * 
     * Päivittää suorituksen tiedot tietokantaan
     * @param c käytettävä tietokantayhteys
     * @param suoritusid suorituksen id
     * @param arvosana
     * @param pvm
     * @throws SQLException
     */
    public static void updateGrade(Connection c, int suoritusid, int arvosana, String pvm) throws SQLException {
        PreparedStatement ps = c.prepareStatement(
                "UPDATE  Suoritus SET Arvosana = ?, Pvm = ? WHERE SuoritusId = " + suoritusid);

        ps.setInt(1, arvosana);
        ps.setString(2, pvm);
        ps.executeUpdate();

        System.out.println("\t>> Suorituksen ID #" + suoritusid + " tiedot päivitettiin tietokantaan");
    }
    
    /**
     * 
     * Poistaa opiskelijan tietokannasta (ja myös kaikki opiskelijan mahdolliset suoritukset)
     * @param c käytettävä tietokantayhteys
     * @param studentid
     * @throws SQLException
     */
    public static void deleteStudent(Connection c, int studentid) throws SQLException {
        PreparedStatement ps = c.prepareStatement(
                "DELETE FROM Opiskelija WHERE OpiskelijaId = " + studentid + ";");

        ps.execute();
        System.out.println("\t>> Opiskelijan ID# " + studentid + " tiedot poistettiin tietokannasta");
    }
    
    /**
     * 
     * Poistaa kurssin tietokannasta
     * @param c käytettävä tietokantayhteys
     * @param courseid
     * @throws SQLException
     */
    public static void deleteCourse(Connection c, int courseid) throws SQLException {

        PreparedStatement ps = c.prepareStatement(
                "DELETE FROM Kurssi WHERE KurssiId = " + courseid + ";");

        ps.execute();
        System.out.println("\t>> Kurssin ID# " + courseid + " tiedot poistettiin tietokannasta");
    }
    
    /**
     * 
     * Poistaa suorituksen tietokannasta
     * @param c
     * @param gradeid
     * @throws SQLException
     */
    public static void deleteGrade(Connection c, int gradeid) throws SQLException {
        PreparedStatement ps = c.prepareStatement(
                "DELETE FROM Suoritus WHERE SuoritusId = " + gradeid + ";");

        ps.execute();
        System.out.println("\t>> Suorituksen ID #" + gradeid + " tiedot poistettiin tietokannasta");
    }

}
