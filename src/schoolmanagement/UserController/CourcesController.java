/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schoolmanagement.UserController;

import java.awt.Color;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.TextFlow;
import schoolmanagement.DashboardController;

/**
 * FXML Controller class
 *
 * @author KevinHa
 */
public class CourcesController implements Initializable {

    ArrayList<String> coursecode;
    ArrayList<String>  coursename;
    ArrayList<String>  professorname;
    ArrayList<String>  meetingtime;
    ArrayList<String>  meetingday;
    
    ArrayList<String> lastcoursecode;
    ArrayList<String>  lastcoursename;
    ArrayList<String>  lastcoursesession;
    @FXML
    private VBox course_container;
    @FXML
    private AnchorPane root;
    @FXML
    private AnchorPane course_contain;
    @FXML
    private VBox vbox_lastcourse_list;
    @FXML
    private Label label_lastcourses;
    @FXML
    private Label lbl_currentcourse;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        AddCourseData();
        
        AddCourse_Dynamic(coursecode,coursename,professorname,meetingtime,meetingday);
        
        lbl_currentcourse.setText(lbl_currentcourse.getText() + " ("+coursecode.size()+")");
        
        AddLastCourseData();
        
        AddLastCourse_Dynamic(lastcoursecode, lastcoursename, lastcoursesession);
        
        label_lastcourses.setText(label_lastcourses.getText() + " ("+lastcoursecode.size()+")");
    }    
    // Main Course - Current Courses
    void AddCourseData(){
        coursecode = new ArrayList<>();
        coursename = new ArrayList<>();
        professorname = new ArrayList<>();
        meetingtime = new ArrayList<>();
        meetingday = new ArrayList<>();
        
        coursecode.add("CS 3100");
        coursecode.add("MATH 1301");
        coursecode.add("SPY 2306");
        coursecode.add("BIOL 1301");
        
        coursename.add("Software Engineer");
        coursename.add("Calculus I");
        coursename.add("Humanity Spychology");
        coursename.add("Human Anatomy I");
        
        professorname.add("Johny Dept");
        professorname.add("Juliet");
        professorname.add("Alex Fabulous");
        professorname.add("Jonathan Vin");
        
        meetingtime.add("10:30 - 11:45");
        meetingtime.add("14:00 - 15:15");
        meetingtime.add("13:00 - 14:15");
        meetingtime.add("9:00 - 10:15");
        
        meetingday.add("Monday - Wednesday");
        meetingday.add("Monday - Wednesday");
        meetingday.add("Tuesday - Thirday");
        meetingday.add("Tuesday");
    }
    
    void AddCourse_Dynamic(ArrayList<String>  coursecode, ArrayList<String>  coursename, ArrayList<String>  professor, ArrayList<String>  meetingtime, ArrayList<String>  meetingday){
        for(int i = 0; i < coursecode.size();i++){
            AddCourse_Item(coursecode.get(i),coursename.get(i), professor.get(i), meetingtime.get(i), meetingday.get(i));
        }
    }
    
    void AddCourse_Item(String coursecode, String coursename, String professor, String meetingtime, String meetingday){
        AnchorPane pane = new AnchorPane();
        pane.setId(coursecode);
        pane.setPrefSize(300, 140);
        pane.getStyleClass().add("Courses_Panel");
        pane.setEffect(new DropShadow(10, (javafx.scene.paint.Color) Paint.valueOf("#666666")));

        //Color _color = new Color(0, 0, 0, 0);
        Paint color = new javafx.scene.paint.Color(Math.random(), Math.random(), Math.random(), 1);
        //String  color_text = String.format( "#%02X%02X%02X", (int)( _color.getRed() * 255 ), (int)( _color.getGreen() * 255 ),(int)( _color.getBlue() * 255 ) );
        Pane _ColorLeft = new Pane();
        _ColorLeft.setPrefWidth(10);
        _ColorLeft.setBackground(new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY)));

        // Course code & Name
        Label c_codename = new Label();
        c_codename.getStyleClass().add("Courses_Panel_Title");
        c_codename.setText(coursecode + ": " + coursename);
        
        // Course Professor Name
        Label c_professor = new Label();
        c_professor.getStyleClass().add("Courses_Panel_Content");
        c_professor.setText("Professor: " + professor);
        
        // Course Meeting time
        Label c_meetingtime = new Label();
        c_meetingtime.getStyleClass().add("Courses_Panel_Content");
        c_meetingtime.setText("Meeting Time: " + meetingtime);
        
        // Course Meeting day
        Label c_meetingday = new Label();
        c_meetingday.getStyleClass().add("Courses_Panel_Content");
        c_meetingday.setText("Meeting Day: " + meetingday);

        pane.getChildren().add(c_codename);
        pane.getChildren().add(c_professor);
        pane.getChildren().add(c_meetingtime);
        pane.getChildren().add(c_meetingday);
        pane.getChildren().add(_ColorLeft);
        
        pane.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
        
                public void handle(MouseEvent e){
                    System.out.println(pane.getId());
                }
        });
        
        
        course_container.getChildren().add(pane);
        AnchorPane.setTopAnchor(c_codename, 5.0);
        AnchorPane.setLeftAnchor(c_codename, 10.0);
        AnchorPane.setTopAnchor(c_professor, 40.0);
        AnchorPane.setLeftAnchor(c_professor, 25.0);
        AnchorPane.setTopAnchor(c_meetingtime, 70.0);
        AnchorPane.setLeftAnchor(c_meetingtime, 25.0);
        AnchorPane.setTopAnchor(c_meetingday, 100.0);
        AnchorPane.setLeftAnchor(c_meetingday, 25.0);
        AnchorPane.setBottomAnchor(c_meetingday, 5.0);
        AnchorPane.setTopAnchor(_ColorLeft, 0.0);
        AnchorPane.setLeftAnchor(_ColorLeft, 0.0);
        AnchorPane.setBottomAnchor(_ColorLeft, 0.0);
       
    }

    // last Courses
    
    void AddLastCourseData(){
        lastcoursecode = new ArrayList<>();
        lastcoursename = new ArrayList<>();
        lastcoursesession = new ArrayList<>();
        
        lastcoursecode.add("ACC 1301");
        lastcoursename.add("Accounting for Non-Business Majors");
        lastcoursesession.add("Fall 2016");
        
        lastcoursecode.add("ACC 3300");
        lastcoursename.add("Intermediate Accounting I");
        lastcoursesession.add("Fall 2016");
        
        lastcoursecode.add("ACC 4303");
        lastcoursename.add("Advanced Accounting");
        lastcoursesession.add("Fall 2016");
        
        lastcoursecode.add("ANTH 2301");
        lastcoursename.add("Physical Anthropology");
        lastcoursesession.add("Fall 2016");
        
        lastcoursecode.add("ART 1301");
        lastcoursename.add("History of Art: Paleolithic to the Middle Ages");
        lastcoursesession.add("Spring 2017");
        
        lastcoursecode.add("ART 1305");
        lastcoursename.add("Drawing I");
        lastcoursesession.add("Spring 2017");
        
        lastcoursecode.add("ART 2303");
        lastcoursename.add("Painting I");
        lastcoursesession.add("Spring 2017");
        
        lastcoursecode.add("BIOL 1101");
        lastcoursename.add("General Biology Laboratory I");
        lastcoursesession.add("Fall 2017");
        
        lastcoursecode.add("BIOL 1301");
        lastcoursename.add("General Biology I");
        lastcoursesession.add("Fall 2017");
        
        lastcoursecode.add("BA 3300");
        lastcoursename.add("Business Cornerstone");
        lastcoursesession.add("Fall 2017");
        
        lastcoursecode.add("CHEM 1105");
        lastcoursename.add("Introductory Chemistry Laboratory");
        lastcoursesession.add("Fall 2017");
    }
    
    void AddLastCourse_Dynamic (ArrayList<String> coursecode, ArrayList<String> coursename, ArrayList<String> coursesession){
        for(int i = 0; i < coursecode.size();i++){
            AddlastCourse_Item(coursecode.get(i), coursename.get(i), coursesession.get(i));
        }
    }
    
    void AddlastCourse_Item(String coursecode, String coursename, String courcesession){
        AnchorPane container = new AnchorPane();
        container.getStyleClass().add("lastcourst_container");
        container.setPrefHeight(60);
        container.setEffect(new DropShadow(3, (javafx.scene.paint.Color) Paint.valueOf("#666666")));
        
        Label course_title = new Label();
        course_title.getStyleClass().add("lastcourst_title_detail");
        course_title.setPrefWidth(320);
        course_title.setText(coursecode + ": " + coursename);
        
        Label course_session = new Label();
        course_session.getStyleClass().add("lastcourst_session");
        course_session.setText(courcesession);
        
        container.getChildren().add(course_title);
        container.getChildren().add(course_session);
        
        vbox_lastcourse_list.getChildren().add(container);
        
        AnchorPane.setLeftAnchor(course_title, 5.0);
        AnchorPane.setRightAnchor(course_session, 5.0);
        AnchorPane.setBottomAnchor(course_session, 5.0);
            
        
    }
}
