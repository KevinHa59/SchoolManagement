/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schoolmanagement.UserController;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import sun.font.GraphicComponent;

/**
 * FXML Controller class
 *
 * @author KevinHa
 */
public class Main_dashboardController implements Initializable {

    ArrayList<String> coursecode;
    ArrayList<String>  coursename;
    ArrayList<String>  professorname;
    ArrayList<String>  meetingtime;
    ArrayList<String>  meetingday;
    @FXML
    private VBox vbox_sourselist;
    @FXML
    private ScrollPane scrollpane;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        AddCourseData();
        AddCourse_Dynamic(coursecode, coursename, professorname, meetingtime, meetingday);
        
    }    
    
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
        
        void AddCourse_Dynamic(ArrayList<String> code, ArrayList<String> name, ArrayList<String> proname, ArrayList<String> meettime, ArrayList<String> meetday){
            for(int i = 0; i < code.size(); i++){
                AddCourse_Item(code.get(i), name.get(i), proname.get(i), meettime.get(i), meetday.get(i));
            }
        }
        
        void AddCourse_Item(String coursecode, String coursename, String professorname, String meetingtime, String meetingday){
            AnchorPane container = new AnchorPane();
            container.setPrefHeight(30);
            container.setPrefWidth(container.USE_COMPUTED_SIZE);
            container.getStyleClass().add("Dashboard_course_item_pane");
            
            Label course_text = new Label();
            course_text.getStyleClass().add("Dashboard_course_item_text");
            course_text.setText(coursecode+": " + coursename+ " | " +professorname + " | " + meetingtime + " | " + meetingday);
            
            
            container.getChildren().add(course_text);
            
            vbox_sourselist.getChildren().add(container);
            
            AnchorPane.setLeftAnchor(course_text, 10.0);
            AnchorPane.setTopAnchor(course_text, 5.0);
            
        }
}
