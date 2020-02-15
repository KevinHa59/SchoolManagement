/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schoolmanagement.Administrator;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import schoolmanagement.DatabaseConnection;

/**
 * FXML Controller class
 *
 * @author KevinHa
 */
public class CreateProfessorAccountController implements Initializable {

    @FXML
    private TextField txt_firstname;
    @FXML
    private TextField txt_lastname;
    @FXML
    private TextField txt_phonenumber;
    @FXML
    private DatePicker txt_birthday;
    @FXML
    private TextField txt_street;
    @FXML
    private TextField txt_city;
    @FXML
    private TextField txt_state;
    @FXML
    private TextField txt_zipcode;
    @FXML
    private TextField txt_salary;
    @FXML
    private Pane btn_container;
    @FXML
    private Button btn_create;
    @FXML
    private Pane btn_container1;
    @FXML
    private Button btn_clear;
    
    DatabaseConnection con;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void OnCreateClicked(MouseEvent event) {
        con = new DatabaseConnection();
        con.CreateProfessorAccount(txt_firstname.getText().toString(), txt_lastname.getText().toString(), txt_phonenumber.getText().toString(), txt_street.getText().toString(), txt_city.getText().toString(), txt_state.getText().toString(), txt_zipcode.getText().toString(), txt_birthday.getValue(), Integer.parseInt(txt_salary.getText().toString()));
    }

    @FXML
    private void OnClearClicked(MouseEvent event) {
    }
    
}
