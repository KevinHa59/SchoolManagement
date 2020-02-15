/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schoolmanagement.Administrator;

import java.net.URL;
import java.util.ArrayList;
import java.util.Observable;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import schoolmanagement.DatabaseConnection;
import schoolmanagement.DatabaseConnection;

/**
 * FXML Controller class
 *
 * @author KevinHa
 */
public class CreateStudentAccountController implements Initializable {
    DatabaseConnection database;
    @FXML
    private TextField txt_firstname;
    @FXML
    private TextField txt_lastname;
    @FXML
    private TextField txt_phonenumber;
    @FXML
    private TextField txt_street;
    @FXML
    private TextField txt_city;
    @FXML
    private TextField txt_state;
    @FXML
    private TextField txt_zipcode;
    @FXML
    private Pane btn_container;
    @FXML
    private Button btn_create;
    @FXML
    private Pane btn_container1;
    @FXML
    private Button btn_clear;
    @FXML
    private DatePicker txt_birthday;
    @FXML
    private ComboBox combo_degree;
    
    private ArrayList<String> DegreeList;
    private ArrayList<String> MajorList;
    @FXML
    private ComboBox combo_major;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        database = new DatabaseConnection();
        LoadDegreeList();
        LoadMajorList();
        

    }    

    private void LoadDegreeList(){
        DegreeList = new ArrayList<>();
        DegreeList = database.GetDegreeList();
        database = new DatabaseConnection();
        combo_degree.getItems().addAll(DegreeList);
    }
    private void LoadMajorList(){
        MajorList = new ArrayList<>();
        MajorList = database.GetMajorList();
        database = new DatabaseConnection();
        combo_major.getItems().addAll(MajorList);
    }
    
    @FXML
    private void OnCreateClicked(MouseEvent event) {
        //database = new DatabaseConnection();
        
        database.CreateStudentAccount(txt_firstname.getText(), txt_lastname.getText(), txt_phonenumber.getText(), txt_street.getText(), txt_city.getText(), txt_state.getText(), txt_zipcode.getText(), txt_birthday.getValue(),combo_degree.getValue().toString(), combo_major.getValue().toString());
    }

    @FXML
    private void OnClearClicked(MouseEvent event) {
        txt_firstname.setText("");
        txt_lastname.setText("");
        txt_phonenumber.setText("");
        txt_street.setText("");
        txt_city.setText("");
        txt_state.setText("");
        txt_zipcode.setText("");
        txt_birthday.setValue(null);
    }
    
}
