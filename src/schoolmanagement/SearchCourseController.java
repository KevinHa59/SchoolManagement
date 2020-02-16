/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schoolmanagement;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author KevinHa
 */
public class SearchCourseController implements Initializable {

    DatabaseConnection database;
    @FXML
    private TextField txt_courseno;
    @FXML
    private CheckBox cbx_showopenclass;
    @FXML
    private Pane btn_container;
    @FXML
    private Button btn_search;
    @FXML
    private Pane btn_container1;
    @FXML
    private Button btn_clear;
    @FXML
    private CheckBox cbx_mon;
    @FXML
    private CheckBox cbx_tues;
    @FXML
    private CheckBox cbx_wed;
    @FXML
    private CheckBox cbx_thurs;
    @FXML
    private CheckBox cbx_fri;
    @FXML
    private CheckBox cbx_sat;
    @FXML
    private CheckBox cbx_sun;
    @FXML
    private ComboBox combo_subject;
    @FXML
    private ComboBox combo_courseno;
    @FXML
    private ComboBox combo_campus;
    @FXML
    private ComboBox combo_classmode;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        database = new DatabaseConnection();
        LoadDataList();
    }    

    @FXML
    private void OnCreateClicked(MouseEvent event) {
    }

    @FXML
    private void OnClearClicked(MouseEvent event) {
    }
    
    void LoadDataList(){
        combo_subject.getItems().addAll(database.LoadAllSubject());
        combo_campus.getItems().addAll(database.LoadAllSchoolLocation());
        combo_campus.getSelectionModel().selectFirst();
        LoadCourseNo_Option();
        LoadClassMode_Option();
    }
    ArrayList<String> CourseNo_Range;
    void LoadCourseNo_Option(){
        CourseNo_Range = new ArrayList<>();
        CourseNo_Range.add("All");
        CourseNo_Range.add("Greater Than");
        CourseNo_Range.add("Exact");
        CourseNo_Range.add("Less Than");
        
        combo_courseno.getItems().addAll(CourseNo_Range);
        combo_courseno.getSelectionModel().selectFirst();
    }
    ArrayList<String> Class_mode;
    void LoadClassMode_Option(){
        Class_mode = new ArrayList<>();
        Class_mode.add("All");
        Class_mode.add("Face to Face");
        Class_mode.add("Hybrid");
        Class_mode.add("Online");
        
        combo_classmode.getItems().addAll(Class_mode);
        combo_classmode.getSelectionModel().selectFirst();
    }
    
}
