package com.satumaarit;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Käyttöliittymä
 * @author satu
*/
public class OpiskelijaRekisteriFXMLController implements Initializable {

    Database db = new Database();
    Connection conn;

    ArrayList<Student> students;
    ArrayList<String> studentnames;

    ArrayList<Course> courses;
    ArrayList<String> coursenames;

    ArrayList<Grade> grades;

    String get_firstname;
    String get_lastname;
    String get_city;
    String get_mail;
    String get_studentid;

    String getcourseid;
    String getcoursename;
    String getpoints;
    String getdescription;

    int selectedStudentId;
    String selectedStudentFirstName;
    String selectedStudentLastName;

    int selectedCourseId;
    String courseName;

    Grade selectedGrade;
    int gradeId;

    @FXML
    private Label lblShowCourseID;
    @FXML
    private Label lblShowStudentID;
    @FXML
    private MenuItem btnAddStudents;
    @FXML
    private MenuItem btnAddCourses;
    @FXML
    private Pane paneStartView;
    @FXML
    private Pane paneAddStudents;
    @FXML
    private Pane paneAddCourses;
    @FXML
    private Pane paneAddGrades;
    @FXML
    private ToggleGroup gradesGroup;
    @FXML
    private MenuItem btnAddGrades;
    @FXML
    private Label btnReturn;
    @FXML
    private TextField txtFieldFirstName;
    @FXML
    private TextField txtFieldLastName;
    @FXML
    private TextField txtFieldCity;
    @FXML
    private TextField txtFieldMail;
    @FXML
    private Button btnAddNewStudent;
    @FXML
    private Button btnEmptySfields;
    @FXML
    private TextField txtFieldCourseName;
    @FXML
    private TextField txtFieldPoints;
    @FXML
    private Button btnAddNewCourse;
    @FXML
    private Button btnEmptyCFields;
    @FXML
    private TextArea textAreaDescription;
    @FXML
    private Label lblShowCourseID2;
    @FXML
    private ComboBox<String> comboBoxStudents;
    @FXML
    private ComboBox<String> comboBoxCourses;
    @FXML
    private DatePicker datePicker;
    @FXML
    private RadioButton radioBtn5;
    @FXML
    private RadioButton radioBtn4;
    @FXML
    private RadioButton radioBtn3;
    @FXML
    private RadioButton radioBtn2;
    @FXML
    private RadioButton radioBtn1;
    @FXML
    private Button btnEmptyGFields;
    @FXML
    private Button btnAddNewGrade;
    @FXML
    private Label lblAddGradeInfo;
    @FXML
    private MenuItem btnEditStudents;
    @FXML
    private MenuItem btnEditCourses;
    @FXML
    private MenuItem btnEditGrades;
    @FXML
    private Pane paneEditStudents;
    @FXML
    private ComboBox<String> chooseStudent;
    @FXML
    private Pane paneShowEditableStudent;
    @FXML
    private TextField textFieldFirstName;
    @FXML
    private TextField textFieldLastName;
    @FXML
    private TextField textFieldCity;
    @FXML
    private TextField textFieldMail;
    @FXML
    private Button btnEditStudent;
    @FXML
    private Button btnCancelEditStudent;
    @FXML
    private Button btnSaveStudentChanges;
    @FXML
    private TextField textFieldStudentId;
    @FXML
    private Pane paneEditCourses;
    @FXML
    private Pane paneShowEditableCourse;
    @FXML
    private TextField textFieldCourseId;
    @FXML
    private TextField textFieldCourseName;
    @FXML
    private TextField textFieldPoints;
    @FXML
    private TextArea textAreaCourseDescription;
    @FXML
    private Button btnSaveCourseChanges;
    @FXML
    private Button btnEditCourse;
    @FXML
    private Button btnCancelEditCourse;
    @FXML
    private ComboBox<String> chooseCourse;
    @FXML
    private Label lblEditStudentInfo;
    @FXML
    private Label lblEditCourseInfo;
    @FXML
    private Button btnDeleteStudent;
    @FXML
    private Label lblDeleteStudentInfo;
    @FXML
    private Label lblCourseDeletedInfo;
    @FXML
    private Button btnDeleteCourse;
    @FXML
    private Label lblEditStudentInfo2;
    @FXML
    private MenuItem btnGetStudentRecords;
    @FXML
    private MenuItem btnGetCourseRecords;
    @FXML
    private Pane paneStudentRecords;
    @FXML
    private ComboBox<String> comboBoxSelectStudent;
    @FXML
    private Pane paneStudetsGradesList;
    @FXML
    private Label lblStudentRecordsInfo;
    @FXML
    private Pane paneCourseRecords;
    @FXML
    private Pane paneCourseGradesList;
    @FXML
    private Label lblCourseRecordsInfo;
    @FXML
    private ComboBox<String> comboBoxSelectCourse;
    @FXML
    private BorderPane primaryPane;
    @FXML
    private Pane paneEditGrades;
    @FXML
    private ComboBox<String> selectStudentToEdit;
    @FXML
    private VBox paneShowEditableGrade;
    @FXML
    private DatePicker datePicker2;
    @FXML
    private RadioButton radioButton5;
    @FXML
    private ToggleGroup gradeGroup;
    @FXML
    private RadioButton radioButton4;
    @FXML
    private RadioButton radioButton3;
    @FXML
    private RadioButton radioButton2;
    @FXML
    private RadioButton radioButton1;
    @FXML
    private Button btnEditGrade;
    @FXML
    private Button btnCancelEditGrade;
    @FXML
    private Button btnSaveEditedGrade;
    @FXML
    private Label lbllGradeSavedInfo;
    @FXML
    private Button btnDeleteGrade;
    @FXML
    private Label lblGradeDeletedInfo;
    @FXML
    private Pane paneShowGradeList;
    @FXML
    private Label lblSelectGradeInfo;
    @FXML
    private Label lblsuorituspvm;
    @FXML
    private Label lbltest;
    @FXML
    private Label lblShowKA;
    @FXML
    private Label lblShowOP;
    @FXML
    private Label btnQuit;

    /**
     * 
     * Initialisoinnissa luodaan muistinvarainen tietokanta ja
     * Asetetaan sovelluksen aloitusnäkymä
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // Tietokantayhteyden muodostaminen
        try {
            conn = db.openConnection();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        // Taulujen luominen
        try {
            db.createTables(conn);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        // Foreign key constraintien lisääminen taululle: Suoritus
        try {
            db.alterTables(conn);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        // Mallidatan lisääminen
        try {
             db.addSampleData(conn);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        //Haetaan opiskelijat, kurssit ja suoritukset tietokannasta
        try {
            students = db.readAllStudents(conn);
            courses = db.readAllCourses(conn);
            grades = db.readAllGrades(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Piilotetaan näkymät
        hideAllPanes();

        // Asetetaan näkyville aloitusnäkymä
        paneStartView.setVisible(true);
    }

    /**
     * 
     * Päävalikon painike "Palaa alkuun", jonka painaminen palauttaa käyttäjän
     * ohjelman etusivulle
     */
    @FXML
    private void btnReturnClicked(MouseEvent event) {
        // Piilotetaan näkymät
        hideAllPanes();
        // Asetetaan näkyville aloitusnäkymä
        paneStartView.setVisible(true);
    }
    
    /**
     * 
     * Päävalikon painike "lopeta ohjelma", jonka painaminen katkaisee tietokantayhteyden
     * ja lopettaa ohjelman.
     */
    @FXML
    private void btnQuitClicked(MouseEvent event) throws SQLException {
        db.closeConnection(conn);
        System.exit(0);
    }
    
    /**
     * 
     * Päävalikon valinta "Lisää uusi opiskelija", jonka painaminen vie käyttäjän
     * sivulle, jolla voidaan lisätä uuden opiskelijan tiedot järjestelmään.
     * Näkymän alustus.
     */
    @FXML
    private void btnAddStudentsClicked(Event event) {
        // Piilotetaan näkymät
        hideAllPanes();

        // Asetetaan näkyville haluttu näkymä
        paneAddStudents.setVisible(true);

        // Asetetaan tähän näkymään kuuluvat elementit alkutilaan
        txtFieldFirstName.clear();
        txtFieldLastName.clear();
        txtFieldCity.clear();
        txtFieldMail.clear();
        btnAddNewStudent.setDisable(false);
        lblShowStudentID.setVisible(false);
    }

    /**
     * 
     * Päävalikon valinta "Lisää uusi kurssi", jonka painaminen vie käyttäjän
     * sivulle, jolla voidaan lisätä uuden kurssin tiedot järjestelmään.
     * Näkymän alustus.
     */
    @FXML
    private void btnAddCoursesClicked(Event event) {
        // Piilotetaan näkymät
        hideAllPanes();

        // Asetetaan näkyville haluttu näkymä
        paneAddCourses.setVisible(true);

        // Asetetaan tähän näkymään kuuluvat elementit alkutilaan
        txtFieldCourseName.clear();
        txtFieldPoints.clear();
        textAreaDescription.clear();
        btnAddNewCourse.setDisable(false);
        lblShowCourseID.setVisible(false);
    }

    /**
     * 
     * Päävalikon valinta "Lisää uusi suoritus", jonka painaminen vie käyttäjän
     * sivulle, jolla voidaan lisätä uuden kurssisuorituksen tiedot
     * järjestelmään.
     * Näkymän alustus.
     */
    @FXML
    private void btnAddGradesClicked(ActionEvent event) {
        // Piilotetaan näkymät
        hideAllPanes();
        // Asetetaan näkyville haluttu näkymä
        paneAddGrades.setVisible(true);

        // Asetetaan näkymään kuuluvat elementit alkutilaan
        comboBoxStudents.getItems().clear();
        comboBoxCourses.getItems().clear();
        datePicker.setValue(null);
        gradesGroup.selectToggle(null);
        btnAddNewGrade.setDisable(false);
        lblAddGradeInfo.setVisible(false);

        //Luetaan alkutilassa tarvittavat tiedot
        //ja sijoitetaan näytettäväksi tarvittaviin elementteihin
        studentnames = Student.getStudentNames(students);
        comboBoxStudents.getItems().addAll(studentnames);

        coursenames = Course.getCourseNames(courses);
        comboBoxCourses.getItems().addAll(coursenames);
    }

    /**
     * 
     * Päävalikon valinta "Muokkaa opiskelijan tietoja", jonka painaminen vie
     * käyttäjän sivulle, jossa voidaan muokata opiskelijoiden tietoja.
     * Näkymän alustus.
     */
    @FXML
    private void btnEditStudentsClicked(ActionEvent event) {
        // Piilotetaan näkymät
        hideAllPanes();
        // Asetetaan näkyville haluttu näkymä
        paneEditStudents.setVisible(true);

        // Asetetaan näkymään kuuluvat elementit alkutilaan
        textFieldStudentId.clear();
        textFieldFirstName.clear();
        textFieldLastName.clear();
        textFieldCity.clear();
        textFieldMail.clear();
        paneShowEditableStudent.setVisible(false);

        //Luetaan alkutilassa tarvittavat tiedot
        //ja sijoitetaan näytettäväksi elementtiin
        studentnames = Student.getStudentNames(students);
        chooseStudent.getItems().clear();
        chooseStudent.getItems().addAll(studentnames);
        chooseStudent.getSelectionModel().clearSelection();
    }

    /**
     * 
     * Päävalikon valinta "Muokkaa kurssin tietoja", jonka painaminen vie käyttäjän
     * sivulle jolla voidaan muokata kurssien tietoja.
     * Näkymän alustus.
     */
    @FXML
    private void btnEditCoursesClicked(ActionEvent event) {
        // Piilotetaan näkymät
        hideAllPanes();
        // Asetetaan näkyville haluttu näkymä
        paneEditCourses.setVisible(true);

        // Asetetaan näkymään kuuluvat elementit alkutilaan
        textFieldCourseId.clear();
        textFieldCourseName.clear();
        textFieldPoints.clear();
        textAreaDescription.clear();
        paneShowEditableCourse.setVisible(false);

        //Luetaan alkutilassa tarvittavat tiedot
        //ja sijoitetaan näytettäväksi elementtiin
        coursenames = Course.getCourseNames(courses);
        chooseCourse.getItems().clear();
        chooseCourse.getItems().addAll(coursenames);
        chooseCourse.getSelectionModel().clearSelection();
    }

    /**
     * 
     * Päävalikon valinta "Muokkaa suoritusmerkintää", jonka painaminen vie
     * käyttäjän sivulle jolla voidaan muokata opiskelijoiden
     * suoritusmerkintöjä.
     * Näkymän alustus.
     */
    @FXML
    private void btnEditGradesClicked(ActionEvent event) {
        // Piilotetaan näkymät
        hideAllPanes();
        // Asetetaan näkyville haluttu näkymä
        paneEditGrades.setVisible(true);

        // Asetetaan näkymään kuuluvat elementit alkutilaan
        paneShowEditableGrade.setVisible(false);
        paneShowGradeList.setVisible(false);
        paneShowGradeList.getChildren().removeAll(paneShowGradeList.getChildren());
        paneShowGradeList.getChildren().clear();
        lblSelectGradeInfo.setVisible(false);
        datePicker2.setValue(null);
        datePicker2.setDisable(true);
        gradeGroup.selectToggle(null);
        radioButton1.setDisable(true);
        radioButton2.setDisable(true);
        radioButton3.setDisable(true);
        radioButton4.setDisable(true);
        radioButton5.setDisable(true);
        btnSaveEditedGrade.setDisable(false);
        btnDeleteGrade.setDisable(false);
        lbllGradeSavedInfo.setVisible(false);
        lblGradeDeletedInfo.setVisible(false);

        //Luetaan alkutilassa tarvittavat tiedot
        //ja sijoitetaan näytettäväksi elementtiin
        studentnames = Student.getStudentNames(students);
        selectStudentToEdit.getItems().clear();
        selectStudentToEdit.getItems().addAll(studentnames);
        selectStudentToEdit.getSelectionModel().clearSelection();
    }

    /**
     * 
     * Pävalikon valinta "Hae opiskelijan suoritusmerkinnät", jonka painaminen vie
     * käyttäjän sivulle jolla voidaan hakea luettelo kullekin opiskelijalle
     * tallennetuista suoritusmerkinnöistä.
     * Näkymän alustus.
     */
    @FXML
    private void btnGetStudentRecordsClicked(ActionEvent event) {
        // Piilotetaan näkymät
        hideAllPanes();
        // Asetetaan näkyville halutttu näkymä
        paneStudentRecords.setVisible(true);

        // Asetetaan näkymään kuuluvat elementit alkutilaan
        paneStudetsGradesList.setVisible(false);
        paneStudetsGradesList.getChildren().removeAll(paneStudetsGradesList.getChildren());
        paneStudetsGradesList.getChildren().clear();
        lblStudentRecordsInfo.setVisible(false);
        lblShowKA.setVisible(false);
        lblShowOP.setVisible(false);

        //Luetaan alkutilassa tarvittavat tiedot
        //ja sijoitetaan näytettäväksi elementtiin
        studentnames = Student.getStudentNames(students);
        comboBoxSelectStudent.getItems().clear();
        comboBoxSelectStudent.getItems().addAll(studentnames);
        comboBoxSelectStudent.getSelectionModel().clearSelection();
    }

    /**
     * 
     * Pävalikon nappi "Hae kurssin suoritusmerkinnät", jonka painaminen vie
     * käyttäjän sivulle jolla voidaan hakea luettelo kunkin kurssin
     * suorituksista.
     * Näkymän alustus.
     */
    @FXML
    private void btnGetCourseRecordsClicked(ActionEvent event) {
        // Piilotetaan näkymät
        hideAllPanes();
        // Asetetaan näkyville halutttu näkymä
        paneCourseRecords.setVisible(true);

        // Asetetaan näkymään kuuluvat elementit alkutilaan
        paneCourseGradesList.setVisible(false);
        paneCourseGradesList.getChildren().removeAll(paneCourseGradesList.getChildren());
        paneCourseGradesList.getChildren().clear();
        lblCourseRecordsInfo.setVisible(false);

        //Luetaan alkutilassa tarvittavat tiedot
        //ja sijoitetaan näytettäväksi elementtiin
        coursenames = Course.getCourseNames(courses);
        comboBoxSelectCourse.getItems().clear();
        comboBoxSelectCourse.getItems().addAll(coursenames);
        comboBoxSelectCourse.getSelectionModel().clearSelection();

    }

    /**
     * 
     * Painike, jonka painaminen lisää uuden opiskelijan.
     * Jos syötteet eivät ole oikeanlaisia, annetaan ilmoitus käyttäjälle.
     * Jos syötteet ok, lisätään uusi opiskelija järjestelmään.
     */
    @FXML
    private void btnAddNewStudentClicked(ActionEvent event) {
        // Syötteiden tarkastaminen ennen tietokanta-operaation suorittamista
        if (txtFieldFirstName.getText().contains(" ") || txtFieldLastName.getText().contains(" ")
                || txtFieldCity.getText().contains(" ") || txtFieldMail.getText().contains(" ")) {
            lblShowStudentID.setText("Älä käytä välilyöntejä.");
            lblShowStudentID.setVisible(true);

        } else if (txtFieldFirstName.getText().isEmpty() || txtFieldLastName.getText().isEmpty()
                || txtFieldCity.getText().isEmpty() || txtFieldMail.getText().isEmpty()) {
            lblShowStudentID.setText("Kaikki kentät on täytettävä ennen kuin tiedot voidaan tallentaa.");
            lblShowStudentID.setVisible(true);

        } else if (isNumeric(txtFieldFirstName.getText()) || isNumeric(txtFieldLastName.getText())
                || isNumeric(txtFieldCity.getText()) || isNumeric(txtFieldMail.getText())) {
            lblShowStudentID.setText("Kentät eivät saa koostua numeromerkeistä.");
            lblShowStudentID.setVisible(true);

        } else if (!txtFieldMail.getText().contains("@") || !txtFieldMail.getText().contains(".")) {
            lblShowStudentID.setText("Sähköpostiosoite ei ole kelvollinen.");
            lblShowStudentID.setVisible(true);

        } else {
            // Jos kentät OK, lisätään uusi opiskelija järjestelmään:

            // Selvitetään viimeisimmäksi lisätyn opiskelijan opiskelija-id
            int old_id = students.get(students.size() -1).getOpiskelijaId();

            // Annetaan tämän perusteella suurempi id uudelle opiskelijalle
            int new_id = old_id + 1;

            // Luodaan uusi opiskelija ja lisätään opiskelijoihin
            Student student = new Student();
            student.setOpiskelijaId(new_id);
            student.setEtunimi(txtFieldFirstName.getText());
            student.setSukunimi(txtFieldLastName.getText());
            student.setKaupunki(txtFieldCity.getText());
            student.setMail(txtFieldMail.getText());
            students.add(student);

            // Lisätään opiskelija tietokantaan
            try {
                db.addStudent(conn, student.getEtunimi(), student.getSukunimi(),
                        student.getKaupunki(), student.getMail());
            } catch (SQLException e) {
                e.printStackTrace();
            }

            // Ilmoitus käyttäjälle onnistuneesta operaatiosta
            lblShowStudentID.setText("Tallennus onnistui.");
            lblShowStudentID.setVisible(true);
            btnAddNewStudent.setDisable(true);
        }
    }

    /**
     * 
     * Painike, jonka painaminen tyhjentää tekstikentät sivulla, jolla lisätään
     * uusi opiskelija järjestelmään.
     */
    @FXML
    private void btnEmptySfields(ActionEvent event) {
        txtFieldFirstName.clear();
        txtFieldLastName.clear();
        txtFieldCity.clear();
        txtFieldMail.clear();
        lblShowStudentID.setVisible(false);
        btnAddNewStudent.setDisable(false);
    }

    /**
     *
     * Painike, jonka painaminen lisää uuden kurssin.
     * Jos syötteet eivät ole oikeanlaisia, annetaan ilmoitus käyttäjälle.
     * Jos syötteet ok, lisätään uusi kurssi järjestelmään.
     */
    @FXML
    private void btnAddNewCourseClicked(ActionEvent event) throws SQLException {
        // Syötteiden tarkastaminen ennen tietokanta-operaation suorittamista
        if (txtFieldCourseName.getText().startsWith(" ") || txtFieldPoints.getText().contains(" ")
                || textAreaDescription.getText().startsWith(" ")) {
            lblShowCourseID.setText("Kaikki kentät on täytettävä ennen kuin tiedot voidaan tallentaa.");
            lblShowCourseID.setVisible(true);
            lblShowCourseID2.setVisible(true);

        } else if (txtFieldCourseName.getText().isEmpty() || txtFieldPoints.getText().isEmpty()
                || textAreaDescription.getText().isEmpty()) {
            lblShowCourseID.setText("Kaikki kentät on täytettävä ennen kuin tiedot voidaan tallentaa.");
            lblShowCourseID.setVisible(true);
            lblShowCourseID2.setVisible(true);

        } else if (isNumeric(txtFieldCourseName.getText()) || !isNumeric(txtFieldPoints.getText())
                || isNumeric(textAreaDescription.getText())) {
            lblShowCourseID.setText("Kaikki kentät on täytettävä ennen kuin tiedot voidaan tallentaa.");
            lblShowCourseID2.setText("Käytä opintopisteiden ilmoittamiseen vain numeromerkkejä.");
            lblShowCourseID.setVisible(true);
            lblShowCourseID2.setVisible(true);

        } else {
            // Jos kentät OK, lisätään uusi kurssi järjestelmään

            // Selvitetään viimeisimmäksi lisätyn kurssin kurssi-id
            int old_id = courses.get(courses.size() -1).getKurssiId();

            // Annetaan tämän perusteella id uudelle kurssille
            int new_id = old_id + 1;

            // Opintopisteiden muuttaminen string -> int
            int points = Integer.parseInt(txtFieldPoints.getText());

            // Luodaan uusi kurssi ja lisätään kursseihin
            Course course = new Course();
            course.setKurssiId(new_id);
            course.setNimi(txtFieldCourseName.getText());
            course.setOpintopisteet(points);
            course.setKuvaus(textAreaDescription.getText());
            courses.add(course);

            // Lisätään kurssi tietokantaan
            try {
                db.addCourse(conn, course.getNimi(), course.getOpintopisteet(), course.getKuvaus());
            } catch (SQLException e) {
                e.printStackTrace();
            }

            // Ilmoitus käyttäjälle onnistuneesta operaatiosta
            lblShowCourseID.setText("Tallennus onnistui");
            lblShowCourseID.setVisible(true);
            lblShowCourseID2.setVisible(false);
            btnAddNewCourse.setDisable(true);
        }
    }

    /**
     * 
     * Painike, jonka painaminen tyhjentää tekstikentät sivulla jolla lisätään
     * uusi kurssi järjestelmään.
     */
    @FXML
    private void btnEmptyCFields(ActionEvent event) {
        txtFieldCourseName.clear();
        txtFieldPoints.clear();
        textAreaDescription.clear();
        lblShowCourseID.setVisible(false);
        lblShowCourseID2.setVisible(false);
        btnAddNewCourse.setDisable(false);
    }

    /**
     *
     * Painike, jonka painaminen lisää uuden suoritusmerkinnän.
     * Jos syötteet eivät ole oikeanlaisia, annetaan ilmoitus käyttäjälle.
     * Jos syötteet ok, lisätään uusi suoritusmerkintä järjestelmään.
     */
    @FXML
    private void btnAddNewGradeClicked(ActionEvent event) {
        // Syötteiden tarkastaminen ennen tietokanta-operaation suorittamista
        if (comboBoxStudents.getValue() == null || comboBoxStudents.getValue().equals("")
                || comboBoxCourses.getValue() == null || comboBoxCourses.getValue().equals("")
                || datePicker.getValue() == null || gradesGroup.getSelectedToggle() == null) {
            lblAddGradeInfo.setText("Kaikki kentät on täytettävä ennen kuin tiedot voidaan tallentaa.");
            lblAddGradeInfo.setVisible(true);
        }
        // Jos kentät OK, lisätään uusi kurssi järjestelmään
        else {
            LocalDate selectedDate_localdate = datePicker.getValue();
            String selectedDate = selectedDate_localdate.toString();

            RadioButton selectedRadioButton = (RadioButton) gradesGroup.getSelectedToggle();
            int selectedGrade = Integer.parseInt(selectedRadioButton.getText());

            String selectedStudent = comboBoxStudents.getValue();
            int studentId = 0;
            String stringtosplit = selectedStudent;
            String[] parts = stringtosplit.split(" ");
            studentId = Integer.parseInt(parts[3]);

            String selectedCourse = comboBoxCourses.getValue();
            int courseId = 0;
            for (Course course : courses) {
                if (course.getNimi().equals(selectedCourse)) {
                    courseId = course.getKurssiId();
                }
            }

            // Luodaan uusi suoritus ja lisätään suorituksiin
            Grade grade = new Grade();
            grade.setOpiskelijaId(studentId);
            grade.setKurssiId(courseId);
            grade.setArvosana(selectedGrade);
            grade.setSuoritus_pvm(selectedDate);
            grades.add(grade);

            // Lisätään kurssi tietokantaan
            try {
                db.addGrade(conn, studentId, courseId, selectedGrade, selectedDate);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            // Ilmoitus käyttäjälle onnistuneesta operaatiosta
            lblAddGradeInfo.setText("Suoritus tallennettiin.");
            lblAddGradeInfo.setVisible(true);
            btnAddNewGrade.setDisable(true);
        }
    }

    /**
     * 
     * Painike, jonka painaminen tyhjentää valikot ja tekstikentät sivulla jolla
     * lisätään uusi suoritusmerkintä järjestelmään.
     */
    @FXML
    private void btnEmptyGFields(ActionEvent event) {
        comboBoxStudents.getSelectionModel().clearSelection();
        comboBoxCourses.getSelectionModel().clearSelection();
        datePicker.setValue(null);
        gradesGroup.selectToggle(null);
        lblAddGradeInfo.setVisible(false);
        btnAddNewGrade.setDisable(false);
    }

    /**
     * 
     * chooseStudent on opiskelijoiden nimet ja ID:t sisältävä pudotusvalikko
     * sivulla, jolla käyttäjä voi muokata opiskelijan tietoja. Valikon
     * painallus hakee kulloinkin valityuna olevan opiskelijan tiedot ID:n perusteella
     * "Muokkaa opiskelijan tietoja" -näkymän tekstikenttiin, joissa käyttäjä voi muokata opiskelijan tietoja.
     */
    @FXML
    private void studentSelected(ActionEvent event) {
        // Näkymän alustamista
        lblDeleteStudentInfo.setVisible(false);
        lblEditStudentInfo2.setVisible(false);
        btnSaveStudentChanges.setDisable(false);
        btnDeleteStudent.setDisable(false);
        textFieldFirstName.setDisable(true);
        textFieldLastName.setDisable(true);
        textFieldCity.setDisable(true);
        textFieldMail.setDisable(true);

        // Valitun opiskelijan tietojen lukeminen muokattaviin tekstikenttiin
        if (!chooseStudent.getSelectionModel().isEmpty()) {
            String stringtosplit = chooseStudent.getValue();
            String[] parts = stringtosplit.split(" ");
            int id = Integer.parseInt(parts[3]);

            for (Student student : students) {
                if (id == student.getOpiskelijaId()) {
                    textFieldStudentId.setText(Integer.toString(student.getOpiskelijaId()));
                    textFieldFirstName.setText(student.getEtunimi());
                    textFieldLastName.setText(student.getSukunimi());
                    textFieldCity.setText(student.getKaupunki());
                    textFieldMail.setText(student.getMail());
                    paneShowEditableStudent.setVisible(true);
                }
            }

            // Talletetaan kenttien sisältö alkuperäinen muuttujiin
            // Jos tietojen muokkaaminen epäonnistuu, ne voidaan palauttaa
            get_studentid = textFieldStudentId.getText();
            get_firstname = textFieldFirstName.getText();
            get_lastname = textFieldLastName.getText();
            get_city = textFieldCity.getText();
            get_mail = textFieldMail.getText();
        }
    }

    /**
     * 
     * Muokkaa-painike sivulla, jolla käyttäjä voi muokata opiskelijan tietoja.
     * Klikkaaminen mahdollistaa tekstikenttien muokkaamisen.
     */
    @FXML
    private void btnEditStudentClicked(ActionEvent event) {
        textFieldFirstName.setDisable(false);
        textFieldLastName.setDisable(false);
        textFieldCity.setDisable(false);
        textFieldMail.setDisable(false);
    }

    /**
     * 
     * Peruuta-painike sivulla, jolla käyttäjä voi muokata opiskelijan tietoja.
     * Hakee alkuperäisen tiedon käyttäjän mahdollisesti muokkaaman tiedon
     * paikoille tekstikenttiin.
     */
    @FXML
    private void btnCancelEditStudentClicked(ActionEvent event) {
        textFieldFirstName.setText(get_firstname);
        textFieldLastName.setText(get_lastname);
        textFieldCity.setText(get_city);
        textFieldMail.setText(get_mail);
        textFieldFirstName.setDisable(true);
        textFieldLastName.setDisable(true);
        textFieldCity.setDisable(true);
        textFieldMail.setDisable(true);
    }

    /**
     * 
     * Tallenna-nappi sivulla, jolla käyttäjä voi muokata opiskelian tietoja.
     * Syötteiden tarkistamisen jälkeen kenttien tiedot talletetaan järjestelmään.
     */
    @FXML
    private void btnSaveStudentChanges(ActionEvent event) {

        if (textFieldFirstName.getText().isEmpty() || textFieldLastName.getText().isEmpty()
                || textFieldCity.getText().isEmpty() || textFieldMail.getText().isEmpty()) {
            lblEditStudentInfo2.setText("Kentät eivät saa olla tyhjiä.");
            lblEditStudentInfo2.setVisible(true);

        } else if (textFieldFirstName.getText().contains(" ") || textFieldLastName.getText().contains(" ")
                || textFieldCity.getText().startsWith(" ") || textFieldMail.getText().contains(" ")) {
            lblEditStudentInfo2.setText("Älä käytä välilyöntejä.");
            lblEditStudentInfo2.setVisible(true);

        } else if (isNumeric(textFieldFirstName.getText()) || isNumeric(textFieldLastName.getText())
                || isNumeric(textFieldCity.getText()) || isNumeric(textFieldMail.getText())) {
            lblEditStudentInfo2.setText("Älä käytä numeromerkkejä.");
            lblEditStudentInfo2.setVisible(true);
            
        } else if (!textFieldMail.getText().contains("@")) {
            lblEditStudentInfo2.setText("Sähköpostiosoite ei ole kelvollinen.");
            lblEditStudentInfo2.setVisible(true);
            
        } else {
            Student studentToEdit = new Student();
            studentToEdit.setOpiskelijaId(Integer.parseInt(textFieldStudentId.getText()));
            studentToEdit.setEtunimi(textFieldFirstName.getText());
            studentToEdit.setSukunimi(textFieldLastName.getText());
            studentToEdit.setKaupunki(textFieldCity.getText());
            studentToEdit.setMail(textFieldMail.getText());

            //Students-listalle tehtävä opiskelijan tietojen päivitys
            for (Student student : students) {
                if (student.getOpiskelijaId() == studentToEdit.getOpiskelijaId()) {
                    student.setEtunimi(studentToEdit.getEtunimi());
                    student.setSukunimi(studentToEdit.getSukunimi());
                    student.setKaupunki(studentToEdit.getKaupunki());
                    student.setMail(studentToEdit.getMail());
                }
            }

            try {
                // Tietokantaan tehtävä opiskelijan tietojen päivitys
                db.updateStudent(conn, studentToEdit.getOpiskelijaId(), studentToEdit.getEtunimi(), studentToEdit.getSukunimi(), studentToEdit.getKaupunki(), studentToEdit.getMail());
            } catch (SQLException e) {
                e.printStackTrace();
            }
            //Ilmoitus käyttäjälle onnistuneesta operaatiosta
            lblEditStudentInfo2.setText("Muutokset tallennettiin.");

            // Muiden yttöliittymäelementtien päivittäminen näkymässä
            studentnames = Student.getStudentNames(students);
            chooseStudent.getItems().clear();
            chooseStudent.getItems().addAll(studentnames);
            lblEditStudentInfo2.setVisible(true);
            btnSaveStudentChanges.setDisable(true);
            textFieldFirstName.setDisable(true);
            textFieldLastName.setDisable(true);
            textFieldCity.setDisable(true);
            textFieldMail.setDisable(true);
        }
    }

    /**
     * 
     * Poista-nappi sivulla, jolla käyttäjä voi muokata opiskelian tietoja.
     * Klikkaaminen avaa varmistusta kysyvän popup ikkunan. Jos tähän vastataan
     * ok, poistetaan opiskelijan tiedot tietokannasta ja ilmoitus. Muutoin
     * annetaan ilmoitus, ettei tietoja poistettu.
     */
    @FXML
    private void btnDeleteStudentClicked(ActionEvent event) throws SQLException {
        // Poistossa varmistetaan käyttäjältä, halutaanko tiedot varmasti poistaa
        Alert a = new Alert(AlertType.CONFIRMATION);
        a.setHeaderText("Oletko varma, että haluat poistaa tiedot?");
        a.setContentText("Opiskelijan yhteystietoja ja kurssisuorituksia ei voida palauttaa.");
        a.setResult(ButtonType.CANCEL);
        a.setResult(ButtonType.OK);

        a.setOnCloseRequest(e -> {
            ButtonType result = a.getResult();
            // Jos käyttäjä vastasi OK:
            if (result == ButtonType.OK) {
                try {
                    int studentid = Integer.parseInt(textFieldStudentId.getText());
                    //Opiskelijat-listalta poistaminen
                    Iterator<Student> iterator = students.iterator();
                    while (iterator.hasNext()) {
                        Student student = iterator.next();
                        if (student.getOpiskelijaId() == studentid) {
                            iterator.remove();
                            break;
                        }
                    }
                    // opiskelijan poistaminen tietokannasta
                    db.deleteStudent(conn, studentid);
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                }
                //Ilmoitus käyttäjälle onnistuneesta operaatiosta
                lblDeleteStudentInfo.setText("Tiedot poistettiin.");

                //Näkymän päivittäminen:
                studentnames = Student.getStudentNames(students);
                chooseStudent.getItems().clear();
                chooseStudent.getItems().addAll(studentnames);
                chooseStudent.getSelectionModel().clearSelection();

                lblDeleteStudentInfo.setVisible(true);
                btnDeleteStudent.setDisable(true);
                lblEditStudentInfo2.setVisible(false);
                btnSaveStudentChanges.setDisable(true);
                textFieldStudentId.clear();
                textFieldFirstName.clear();
                textFieldLastName.clear();
                textFieldCity.clear();
                textFieldMail.clear();
            }
            //Jos käyttäjä perui poistamisen
            if (result == ButtonType.CANCEL) {
                lblDeleteStudentInfo.setText("Tietoja ei poistettu.");
                lblDeleteStudentInfo.setVisible(true);
            }
        });
        a.show();
    }

    /**
     * 
     * courseSelected on kurssien nimet sisältävä pudotusvalikko sivulla, jolla
     * käyttäjä voi muokata kurssin tietoja. Valikon painallus hakee kulloinkin
     * valitun kurssin tiedot tietokannasta tekstikenttiin, joissa käyttäjä voi
     * muokata tietoja.
     */
    @FXML
    private void courseSelected(ActionEvent event) {
        //Muokkaa kurssia -näkymän alustus
        lblEditCourseInfo.setVisible(false);
        lblCourseDeletedInfo.setVisible(false);
        btnSaveCourseChanges.setDisable(false);
        btnDeleteCourse.setDisable(false);

        // Käyttäjän valitseman kurssin tietojen hakeminen tekstikenttiin
        if (!chooseCourse.getSelectionModel().isEmpty()) {
            for (Course course : courses) {
                if (course.getNimi().equals(chooseCourse.getValue())) {
                    textFieldCourseId.setText(Integer.toString(course.getKurssiId()));
                    textFieldCourseName.setText(course.getNimi());
                    textFieldPoints.setText(Integer.toString(course.getOpintopisteet()));
                    textAreaCourseDescription.setText(course.getKuvaus());
                    paneShowEditableCourse.setVisible(true);
                }
            }
            // Kurssin tietojen tallentaminen muuttujiin
            // Jos tietojen muokkaaminen epäonnistuu, ne voidaan palauttaa
            getcourseid = textFieldCourseId.getText();
            getcoursename = textFieldCourseName.getText();
            getpoints = textFieldPoints.getText();
            getdescription = textAreaCourseDescription.getText();
        }
    }

    /**
     * 
     * Muokkaa-nappi sivulla, jolla käyttäjä voi muokata kurssin tietoja.
     * Klikkaaminen mahdollistaa viereisen tekstikentän muokkaamisen.
     */
    @FXML
    private void btnEditCourseClicked(ActionEvent event) {
        textFieldCourseName.setDisable(false);
        textFieldCourseName.setDisable(false);
        textFieldPoints.setDisable(false);
        textAreaCourseDescription.setDisable(false);
    }

    /**
     * 
     * Peruuta-nappi sivulla, jolla käyttäjä voi muokata kurssin tietoja. Hakee
     * alkuperäisen tiedon käyttäjän mahdollisesti muokkaamien tietojen paikalle.
     */
    @FXML
    private void btnCancelEditCourseClicked(ActionEvent event) {
        textFieldCourseName.setText(getcoursename);
        textFieldPoints.setText(getpoints);
        textAreaCourseDescription.setText(getdescription);
        textFieldCourseName.setDisable(true);
        textAreaCourseDescription.setDisable(true);
        textFieldPoints.setDisable(true);
    }

    /**
     * 
     * Tallenna-nappi sivulla, jolla käyttäjä voi muokata kurssin tietoja.
     * Klikkaaminen tallentaa kenttien tiedot tietokantaan, jos käyttäjä on
     * muokannut tietoja.
     */
    @FXML
    private void btnSaveCourseChangesClicked(ActionEvent event) {
        // Käyttäjän antamien syötteiden oikeellisuuden tarkastaminen
        if (textFieldCourseName.getText().isEmpty() || textFieldPoints.getText().isEmpty()
                || textFieldPoints.getText().isEmpty() || textAreaCourseDescription.getText().isEmpty()) {
            lblEditCourseInfo.setText("Kentät eivät saa olla tyhjiä.");
            lblEditCourseInfo.setVisible(true);

        } else if (textFieldCourseName.getText().startsWith(" ") || textFieldPoints.getText().contains(" ")
                || textAreaCourseDescription.getText().startsWith(" ")) {
            lblEditCourseInfo.setText("Kentät eivät saa olla tyhjiä.");
            lblEditCourseInfo.setVisible(true);

        } else if (!isNumeric(textFieldPoints.getText())) {
            lblEditCourseInfo.setText("Käytä opintopisteiden ilmoittamiseen vain numeromerkkejä.");
            lblEditCourseInfo.setVisible(true);
        }
        else {
            //Courses-listalle tehtävä opiskelijan tietojen päivitys
            Course courseToEdit = new Course();
            courseToEdit.setKurssiId(Integer.parseInt(textFieldCourseId.getText()));
            courseToEdit.setNimi(textFieldCourseName.getText());
            courseToEdit.setOpintopisteet(Integer.parseInt(textFieldPoints.getText()));
            courseToEdit.setKuvaus(textAreaCourseDescription.getText());

            //Courses-listalle tehtävä kurssin tietojen päivitys
            for (Course course : courses) {
                if (course.getKurssiId() == courseToEdit.getKurssiId()) {
                    course.setNimi(courseToEdit.getNimi());
                    course.setOpintopisteet(courseToEdit.getOpintopisteet());
                    course.setKuvaus(courseToEdit.getKuvaus());
                }
            }

            // Muutosten tallentaminen tietokantaan
            try {
                db.updateCourse(conn, courseToEdit.getKurssiId(), courseToEdit.getNimi(), courseToEdit.getOpintopisteet(), courseToEdit.getKuvaus());
            } catch (SQLException e) {
                e.printStackTrace();
            }
            //Ilmoitus käyttäjälle onnistuneesta operaatiosta
            lblEditCourseInfo.setText("Muutokset tallennettiin.");
            lblEditCourseInfo.setVisible(true);

            //Näkymän päivittäminen
            coursenames = Course.getCourseNames(courses);
            chooseCourse.getItems().clear();
            chooseCourse.getItems().addAll(coursenames);
            btnSaveCourseChanges.setDisable(true);
            textFieldCourseName.setDisable(true);
            textFieldPoints.setDisable(true);
            textAreaCourseDescription.setDisable(true);
        }
    }

    /**
     * 
     * Poista-nappi sivulla, jolla käyttäjä voi muokata kurssin tietoja.
     * Klikkaaminen avaa varmistusta kysyvän popup ikkunan. Jos tähän vastataan
     * ok, poistetaan kurssin tiedot tietokannasta ja annetaan ilmoitus. Muutoin
     * annetaan ilmoitus, ettei tietoja poistettu.
     */
    @FXML
    private void btnDeleteCourseClicked(ActionEvent event) throws SQLException {
        // Poistaessa pyydeyään varmistus käyttäjältä
        Alert b = new Alert(AlertType.CONFIRMATION);
        b.setHeaderText("Oletko varma, että haluat poistaa kurssin tiedot?");
        b.setContentText("Kurssin poistaminen poistaa myös sille kuuluvat suoritusmerkinnät. Tietoja ei voida palauttaa.");
        b.setResult(ButtonType.CANCEL);
        b.setResult(ButtonType.OK);
        b.setOnCloseRequest(e -> {
            ButtonType result = b.getResult();
            //Jos käyttäjä päätti poistaa tiedot:
            if (result == ButtonType.OK) {
                int courseid = Integer.parseInt(textFieldCourseId.getText());

                //Kurssit-listalta poistaminen
                Iterator<Course> iterator = courses.iterator();
                while (iterator.hasNext()) {
                    Course course = iterator.next();
                    if (course.getKurssiId() == courseid) {
                        iterator.remove();
                        break;
                    }
                }

                //Suoritukset-listalta poistaminen
                Iterator<Grade> iterator2 = grades.iterator();
                while (iterator2.hasNext()) {
                    Grade grade = iterator2.next();
                    if (grade.getKurssiId() == courseid) {
                        iterator2.remove();
                    }
                }

                try {
                    // kurssin poistaminen tietokannasta (poistaa myös kurssin suoritusmerkinnät)
                    db.deleteCourse(conn, courseid);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                // Ilmoitus käyttäjälle onnistuneesta operaatiosta
                lblCourseDeletedInfo.setText("Tiedot poistettiin.");

                //Näkymän päivittäminen
                coursenames = Course.getCourseNames(courses);
                chooseCourse.getItems().clear();
                chooseCourse.getItems().addAll(coursenames);
                chooseCourse.getSelectionModel().clearSelection();
                btnDeleteCourse.setDisable(true);
                btnSaveCourseChanges.setDisable(true);
                lblCourseDeletedInfo.setVisible(true);
                lblEditCourseInfo.setVisible(false);
                textFieldCourseId.clear();
                textFieldCourseName.clear();
                textFieldPoints.clear();;
                textAreaCourseDescription.clear();
            }
            //Jos käyttäjä peruutti poistamisen
            if (result == ButtonType.CANCEL) {
                lblCourseDeletedInfo.setText("Tietoja ei poistettu.");
                lblCourseDeletedInfo.setVisible(true);
            }
        });
        b.show();
    }

    /**
     * 
     * selectStudentToEdit on opiskelijoiden nimet ja ID-numerot sisältävä
     * pudotusvalikko sivulla, jolla käyttäjä voi muokata suoritusmerkintöjä.
     * Valikon painallus hakee ja näyttää käyttäjälle
     * listauksen kulloinkin valitttuna olevan opiskelijan kaikista
     * suoritusmerkinnöistä.
     * Kun jotakin listan objektia (= yksittäistä
     * suoritusmerkintää) klikataan, niin näytetään käyttäjälle ohjelman
     * toiminnallisuus joka mahdollistaa suoritusmerkinnän muokkaamisen.
     */
    @FXML
    private void selectStudentToEditClicked(ActionEvent event) {
        // Näkymän alustus:
        datePicker2.setValue(null);
        datePicker2.setDisable(true);
        gradeGroup.selectToggle(null);
        radioButton1.setDisable(true);
        radioButton2.setDisable(true);
        radioButton3.setDisable(true);
        radioButton4.setDisable(true);
        radioButton5.setDisable(true);
        btnSaveEditedGrade.setDisable(true);
        btnDeleteGrade.setDisable(true);
        lblGradeDeletedInfo.setVisible(false);
        lbllGradeSavedInfo.setVisible(false);
        lblSelectGradeInfo.setVisible(true);

        // Kurssilista-elementin alustus
        paneShowGradeList.getChildren().removeAll(paneShowGradeList.getChildren());
        paneShowGradeList.getChildren().clear();
        ListView<String> listView = new ListView<String>();
        ObservableList<String> items = FXCollections.observableArrayList();
        listView.getSelectionModel().clearSelection();
        listView.setPrefWidth(primaryPane.getWidth() - 300);
        listView.setPrefHeight(paneShowGradeList.getHeight());

        // Alustetun kurssilista-elementin lisääminen näkymään
        paneShowGradeList.getChildren().add(listView);

        // Käyttäjän valitseman opiskelijan suoritusten lisääminen kurssilista-elementtiin
        if (!selectStudentToEdit.getSelectionModel().isEmpty()) {
            String selectedStudent = selectStudentToEdit.getValue();
            String stringtoedit = selectedStudent;
            String[] parts = stringtoedit.split(" ");
            int studentidnum = Integer.parseInt(parts[3]);

            for (Student student : students) {
                if (studentidnum == student.getOpiskelijaId()) {
                    selectedStudentId = student.getOpiskelijaId();
                    selectedStudentFirstName = student.getEtunimi();
                    selectedStudentLastName = student.getSukunimi();
                }
            }

            for (Grade grade : grades) {
                if (selectedStudentId == grade.getOpiskelijaId()) {
                    gradeId = grade.getSuoritusId();
                    for (Course course : courses) {
                        if (grade.getKurssiId() == course.getKurssiId()) {
                            courseName = course.getNimi();
                            break;
                        }
                    }
                    String item = "ID# " + grade.getSuoritusId() + " \t" + courseName + "\n" + "Arvosana:  " + grade.getArvosana() + "\t \t Suoritettu:  " + grade.getSuoritus_pvm();
                    items.add(item);
                }
            }
            listView.setItems(items);

            btnSaveEditedGrade.setDisable(true);
            paneShowGradeList.setVisible(true);

            listView.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> ov, String old_val, String new_val) -> {
                btnEditGrade.setDisable(false);
                btnCancelEditGrade.setDisable(false);
                lblGradeDeletedInfo.setVisible(false);
                btnSaveEditedGrade.setDisable(false);
                lbllGradeSavedInfo.setVisible(false);

                String str = listView.getSelectionModel().getSelectedItem();
                String[] splitStr = str.split("\\s+");
                int suorituksenId = Integer.parseInt(splitStr[1]);
                selectedGrade = Grade.getGrade(grades, suorituksenId);
                datePicker2.setValue(LocalDate.parse(selectedGrade.getSuoritus_pvm()));
                if (selectedGrade.getArvosana() == 5) {
                    radioButton5.setSelected(true);
                } else if (selectedGrade.getArvosana() == 4) {
                    radioButton4.setSelected(true);
                } else if (selectedGrade.getArvosana() == 3) {
                    radioButton3.setSelected(true);
                } else if (selectedGrade.getArvosana() == 2) {
                    radioButton2.setSelected(true);
                } else if (selectedGrade.getArvosana() == 1) {
                    radioButton1.setSelected(true);
                }
                btnDeleteGrade.setDisable(false);
                paneShowEditableGrade.setVisible(true);

                if (datePicker2.getValue() != null && gradeGroup.getSelectedToggle() != null) {
                    btnSaveEditedGrade.setDisable(false);
                    btnDeleteGrade.setDisable(false);
                }
            });
        }
    }
    
    /**
     * 
     * Muokkaa-nappi sivulla, jolla käyttäjä voi muokata suoritusten tietoja.
     * Klikkaaminen mahdollistaa suoritustietojen muokkaamisen.
     */
    @FXML
    private void btnEditGradeClicked(ActionEvent event) {
        if (gradeGroup.getSelectedToggle() != null) {
            radioButton1.setDisable(false);
            radioButton2.setDisable(false);
            radioButton3.setDisable(false);
            radioButton4.setDisable(false);
            radioButton5.setDisable(false);
        }
        if (datePicker2.getValue() != null) {
            datePicker2.setDisable(false);
        }
    }
    
    /**
     * 
     * Peruuta-nappi sivulla, jolla käyttäjä voi muokata suoritusten tietoja.
     * Palauttaa alkuperäisen valinnan käyttäjän mahdollisesti muokkaaman valinnan tilalle togglegroupiin. 
     */
    @FXML
    private void btnCancelEditGradeClicked(ActionEvent event) {

        if (gradeGroup.getSelectedToggle() != null) {

            if (selectedGrade.getArvosana() == 5) {
                radioButton5.setSelected(true);
            } else if (selectedGrade.getArvosana() == 4) {
                radioButton4.setSelected(true);
            } else if (selectedGrade.getArvosana() == 3) {
                radioButton3.setSelected(true);
            } else if (selectedGrade.getArvosana() == 2) {
                radioButton2.setSelected(true);
            } else if (selectedGrade.getArvosana() == 1) {
                radioButton1.setSelected(true);
            }
        }

        radioButton1.setDisable(true);
        radioButton2.setDisable(true);
        radioButton3.setDisable(true);
        radioButton4.setDisable(true);
        radioButton5.setDisable(true);

        if (datePicker2.getValue() != null) {
            datePicker2.setValue(LocalDate.parse(selectedGrade.getSuoritus_pvm()));
        }
        datePicker2.setDisable(true);
    }
    
    /**
     * 
     * Tallenna-nappi sivulla, jolla käyttäjä voi muokata suoritusten tietoja.
     * Klikkaaminen tallentaa kenttien tiedot järjestelmään.
     */
    @FXML
    private void btnSaveEditedGradeClicked(ActionEvent event) {
        int grade = 0;

        if (radioButton5.isSelected()) {
            grade = 5;
        } else if (radioButton4.isSelected()) {
            grade = 4;
        } else if (radioButton3.isSelected()) {
            grade = 3;
        } else if (radioButton2.isSelected()) {
            grade = 2;
        } else if (radioButton1.isSelected()) {
            grade = 1;
        }

        LocalDate chosenDate = datePicker2.getValue();
        String date = chosenDate.toString();
        int gradeid = selectedGrade.getSuoritusId();
        paneShowEditableGrade.setVisible(true);

        // Suoritus-listan päivittäminen
        for (Grade g : grades) {
            if (gradeid == g.getSuoritusId()) {
                g.setArvosana(grade);
                g.setSuoritus_pvm(date);
            }
        }
        //Päivitetyn suorituksen lisääminen tietokantaan
        try {
            db.updateGrade(conn, gradeid, grade, date);
            // Ilmoitus käyttäjälle onnistuneesta operaatiosta
            lbllGradeSavedInfo.setText("Muutokset tallennettiin.");
            lbllGradeSavedInfo.setVisible(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        btnDeleteGrade.setDisable(true);
        btnSaveEditedGrade.setDisable(true);
        btnEditGrade.setDisable(true);
        btnCancelEditGrade.setDisable(true);
        datePicker2.setValue(null);
        gradeGroup.selectToggle(null);


        paneShowGradeList.setVisible(false);
        selectStudentToEdit.getSelectionModel().clearSelection();
    }
    
    /**
     * 
     * Poista-nappi sivulla, jolla käyttäjä voi muokata suoritusten tietoja.
     * Klikkaaminen avaa varmistusta kysyvän popup ikkunan. Jos tähän vastataan
     * ok, poistetaan suorituksen tiedot.
     */
    @FXML
    private void btnDeleteGradeClicked(ActionEvent event) throws SQLException {
        Alert b = new Alert(AlertType.CONFIRMATION);
        b.setHeaderText("Oletko varma, että haluat poistaa tiedot?");
        b.setContentText("Tietoja ei voida palauttaa.");
        b.setResult(ButtonType.CANCEL);
        b.setResult(ButtonType.OK);
        b.setOnCloseRequest(e -> {
            ButtonType result = b.getResult();

            if (result == ButtonType.OK) {
                int gradeid = selectedGrade.getSuoritusId();

                //Suoritukset-listalta poistaminen
                Iterator<Grade> iterator = grades.iterator();
                while (iterator.hasNext()) {
                    Grade grade = iterator.next();
                    if (grade.getSuoritusId() == gradeid) {
                        iterator.remove();
                    }
                }
                try {
                    db.deleteGrade(conn, gradeid);
                    //Ilmoitus käyttäjälle onnistuneesta operaatiosta
                    lblGradeDeletedInfo.setText("Suoritusmerkintä poistettiin.");
                    lblGradeDeletedInfo.setVisible(true);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                lblSelectGradeInfo.setVisible(false);
                btnDeleteGrade.setDisable(true);
                btnSaveEditedGrade.setDisable(true);
                btnEditGrade.setDisable(true);
                btnCancelEditGrade.setDisable(true);
                datePicker2.setValue(null);
                gradeGroup.selectToggle(null);
                paneShowGradeList.setVisible(false);
                
                selectStudentToEdit.getSelectionModel().clearSelection();
            }
            if (result == ButtonType.CANCEL) {
                lblGradeDeletedInfo.setText("Tietoja ei poistettu.");
                lblGradeDeletedInfo.setVisible(true);
            }
        });
        b.show();
    }

    /**
     * 
     * comboBoxSelectStudentClicked on opiskelijoiden nimet ja ID-numerot sisältävä
     * pudotusvalikko sivulla, jolla käyttäjä voi hakea luettelon opiskelijoiden
     * suoritusmerkinnöistä. Valikon painallus hakee ja näyttää käyttäjälle
     * listauksen kulloinkin valitttuna olevan opiskelijan kaikista
     * suoritusmerkinnöistä.
     */
    @FXML
    private void comboBoxSelectStudentClicked(ActionEvent event) throws SQLException {
        lblStudentRecordsInfo.setVisible(false);
        paneStudetsGradesList.getChildren().removeAll(paneStudetsGradesList.getChildren());
        paneStudetsGradesList.getChildren().clear();

        ListView listView = new ListView();
        listView.setPrefWidth(primaryPane.getWidth() - 300);
        listView.setPrefHeight(paneStudetsGradesList.getHeight());
        paneStudetsGradesList.getChildren().add(listView);

        double sumofgrades = 0;
        int counter = 0;
        double ka = 0;

        int points = 0;
        int totalpoints = 0;

        if (!comboBoxSelectStudent.getSelectionModel().isEmpty()) {
            String chosenStudent = comboBoxSelectStudent.getValue();

            String stringtosplit = chosenStudent;
            String[] parts = stringtosplit.split(" ");
            String firstname = parts[0];
            String lastname = parts[1];
            String unused = parts[2];
            int studentid = Integer.parseInt(parts[3]);

            for (Student student : students) {
                if (studentid == student.getOpiskelijaId()) {
                    selectedStudentId = student.getOpiskelijaId();
                    selectedStudentFirstName = student.getEtunimi();
                    selectedStudentLastName = student.getSukunimi();
                }
            }
            lblStudentRecordsInfo.setText("Opiskelijan " + selectedStudentFirstName + " " + selectedStudentLastName + " suoritusote:");

            for (Grade grade : grades) {
                if (selectedStudentId == grade.getOpiskelijaId()) {
                    for (Course course : courses) {
                        if (grade.getKurssiId() == course.getKurssiId()) {
                            courseName = course.getNimi();
                            points = course.getOpintopisteet();
                            break;
                        }
                    }

                    Label label = new Label("Kurssin nimi:  " + courseName + "\n" + "Arvosana:  " + grade.getArvosana() + "\t" + "\t" + "Laajuus:  " + points + " op" + " \t " + " \t " + "Suoritettu:  " + grade.getSuoritus_pvm());
                    sumofgrades = sumofgrades + grade.getArvosana();
                    counter = counter + 1;
                    totalpoints = totalpoints + points;
                    listView.getItems().add(label);
                }
            }
        }
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        
        if (counter > 0) {
            ka = sumofgrades / counter;
            lblShowKA.setText("Opiskelijan keskiarvo on: " + (df.format(ka)));
            lblShowKA.setVisible(true);
        }
        
        if (counter == 0) {
            lblShowKA.setVisible(false);
        }

        lblShowOP.setText("Suoritettuja opintopisteitä on yhteensä: " + totalpoints + " op");
        
        if (totalpoints != 0) {
            lblShowOP.setVisible(true);
        }

        lblStudentRecordsInfo.setVisible(true);
        paneStudetsGradesList.setVisible(true);
    }
    
    /**
     * 
     * comboBoxSelectCourseClicked on kurssien nimet sisältävä pudotusvalikko sivulla,
     * jolla käyttäjä voi hakea luettelon kunkin kurssin suoritusmerkinnöistä.
     * Valikon painallus hakee ja näyttää käyttäjälle listauksen kulloinkin valitttuna
     * olevan kurssin kaikista suorituksista.
     */
    @FXML
    private void comboBoxSelectCourseClicked(ActionEvent event) throws SQLException {
        lblCourseRecordsInfo.setVisible(false);
        
        paneCourseGradesList.getChildren().removeAll(paneCourseGradesList.getChildren());
        paneCourseGradesList.getChildren().clear();

        ListView listView = new ListView();
        listView.setPrefWidth(primaryPane.getWidth() - 300);
        listView.setPrefHeight(primaryPane.getHeight() - 350);
        paneCourseGradesList.getChildren().add(listView);

        if (!comboBoxSelectCourse.getSelectionModel().isEmpty()) {
            for (Course course : courses) {

                if (comboBoxSelectCourse.getValue().equals(course.getNimi())) {
                    selectedCourseId = course.getKurssiId();
                    courseName = course.getNimi();
                }
            }

            lblCourseRecordsInfo.setText("Kurssin " + courseName + " suorittaneet opiskelijat:");

            for (Grade grade : grades) {
                if (selectedCourseId == grade.getKurssiId()) {
                    for (Student student : students) {
                        if (grade.getOpiskelijaId() == student.getOpiskelijaId()) {
                            selectedStudentFirstName = student.getEtunimi();
                            selectedStudentLastName = student.getSukunimi();
                            break;
                        }
                    }

                    Label label = new Label("Opiskelijan nimi:  " + selectedStudentFirstName + " " + selectedStudentLastName + "\n" + "Arvosana:  " + grade.getArvosana() + " \t " + " \t " + "Suoritettu:  " + grade.getSuoritus_pvm());
                    listView.getItems().add(label);
                }
            }
        }
        lblCourseRecordsInfo.setVisible(true);
        paneCourseGradesList.setVisible(true);
    }

    /**
     *
     * Apumetodi, jolla tutkitaan koostuuko merkkijono
     * pelkistä numeromerkeistä
     * @param str String
     * @return palauttaa boolean arvon true, jos metodille välitetty merkkijono
     * on numeerinen tai arvon false, jos merkkijono ei ole numeerinen.
     */
    public static boolean isNumeric(String str) {
        if (str == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    /**
     *
     * Apumetodi, joka piilottaa kaikki UI:n näkymät
     */
    public void hideAllPanes() {
        paneAddStudents.setVisible(false);
        paneAddCourses.setVisible(false);
        paneAddGrades.setVisible(false);
        paneEditStudents.setVisible(false);
        paneEditCourses.setVisible(false);
        paneEditGrades.setVisible(false);
        paneStudentRecords.setVisible(false);
        paneCourseRecords.setVisible(false);
        paneStartView.setVisible(false);
    }

}