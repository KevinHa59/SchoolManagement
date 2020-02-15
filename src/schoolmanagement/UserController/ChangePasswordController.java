/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schoolmanagement.UserController;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import schoolmanagement.DatabaseConnection;

/**
 * FXML Controller class
 *
 * @author KevinHa
 */
public class ChangePasswordController implements Initializable {

    DatabaseConnection database;
    @FXML
    private TextField txt_currentpassword;
    @FXML
    private Pane btn_container;
    @FXML
    private Button btn_update;
    @FXML
    private TextField txt_newpassword1;
    @FXML
    private TextField txt_newpassword2;

    String UserID;
    String UserRole;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        database = new DatabaseConnection();
    }    

    @FXML
    private void OnUpdatePasswordClicked(MouseEvent event) {
        Boolean updateSuccess = false;
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle("Change Password");
        if(txt_newpassword1.getText().toString().equals(txt_newpassword2.getText().toString())){
            updateSuccess = database.ChangeStudentPassword(UserID, UserRole, txt_currentpassword.getText().toString(), txt_newpassword1.getText().toString());
            if(updateSuccess == true){
                txt_currentpassword.setText("");
                txt_newpassword1.setText("");
                txt_newpassword2.setText("");
                a.setContentText("Change Password Successfully!");
            }else{
                a.setContentText("Current Password is Incorrect!");
            }
        }else{
            a.setContentText("New Passwords are missmatch!");
        }
        
        a.show();
    }
    
    public void getUserInformation(String UserID, String UserRole){
        this.UserID = UserID;
        this.UserRole = UserRole;
    }
    
}
