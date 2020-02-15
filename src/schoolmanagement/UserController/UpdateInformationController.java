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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import schoolmanagement.DatabaseConnection;
import schoolmanagement.validation;

/**
 * FXML Controller class
 *
 * @author KevinHa
 */
public class UpdateInformationController implements Initializable {

    @FXML
    private Label txt_firstname;
    @FXML
    private Label txt_lastname;
    @FXML
    private Label txt_email;
    @FXML
    private TextField txt_phonenumber;
    @FXML
    private Label txt_birthday;
    @FXML
    private Pane btn_container;
    @FXML
    private Button btn_update;
    @FXML
    private Label txt_id;
    @FXML
    private TextField txt_street;
    @FXML
    private TextField txt_city;
    @FXML
    private TextField txt_state;
    @FXML
    private TextField txt_zipcode;

    ArrayList<String> userlist;
    DatabaseConnection database;
    
    String role;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        database = new DatabaseConnection();
        userlist = new ArrayList<String>();
        
    }    

    
    public void getUserInformation(String ID, String Role){
        userlist = database.LoadUserFullInformation(ID, Role);
        System.out.println("size: " + userlist.size());
        
        txt_id.setText(userlist.get(0));
        txt_firstname.setText(userlist.get(1));
        txt_lastname.setText(userlist.get(2));
        txt_email.setText(userlist.get(3));
        txt_phonenumber.setText(userlist.get(4));
        txt_street.setText(userlist.get(5));
        txt_city.setText(userlist.get(6));
        txt_state.setText(userlist.get(7));
        txt_zipcode.setText(userlist.get(8));
        txt_birthday.setText(userlist.get(9));
        role = Role;

    }

    @FXML
    private void OnUpdateClicked(MouseEvent event) {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle("Update User Information");
        
        validation val = new validation();
      
        val.TextField("Phone Number", txt_phonenumber);
        val.TextField("Street", txt_street);
        val.TextField("City", txt_city);
        val.TextField("State", txt_state);
        val.TextField("Zipcode", txt_zipcode);
        database.UpdateStudentInformation(userlist.get(0), role, Integer.parseInt(txt_phonenumber.getText().toString()) , txt_street.getText().toString(), txt_city.getText().toString(), txt_state.getText().toString(), Integer.parseInt(txt_zipcode.getText().toString()) );
        a.setContentText("Update User Information Successfully!");
        a.show();
    }
    
}
