/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schoolmanagement.Administrator;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.table.TableColumn;
import schoolmanagement.DatabaseConnection;

/**
 * FXML Controller class
 *
 * @author KevinHa
 */
public class UserListController implements Initializable {
    
    DatabaseConnection connection;
    
    private SimpleStringProperty id;
    private SimpleStringProperty firstname;
    private SimpleStringProperty lastname;
    private SimpleStringProperty email;
    private SimpleStringProperty phone;
    private SimpleStringProperty birthday;
    private SimpleStringProperty address;
    
    @FXML
    private TableView<schoolmanagement.Administrator.user> UserTableView;
    @FXML
    private javafx.scene.control.TableColumn<schoolmanagement.Administrator.user, String> col_id;
    @FXML
    private javafx.scene.control.TableColumn<schoolmanagement.Administrator.user, String> col_firstname;
    @FXML
    private javafx.scene.control.TableColumn<schoolmanagement.Administrator.user, String> col_lastname;
    @FXML
    private javafx.scene.control.TableColumn<schoolmanagement.Administrator.user, Integer> col_phone;
    @FXML
    private javafx.scene.control.TableColumn<schoolmanagement.Administrator.user, String> col_email;
    @FXML
    private javafx.scene.control.TableColumn<schoolmanagement.Administrator.user, Date> col_birthday;
    @FXML
    private javafx.scene.control.TableColumn<schoolmanagement.Administrator.user, String> col_address;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        connection = new DatabaseConnection();
        // TODO
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_firstname.setCellValueFactory(new PropertyValueFactory<>("firstname"));
        col_lastname.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        col_phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        col_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        col_birthday.setCellValueFactory(new PropertyValueFactory<>("birthday"));
        col_address.setCellValueFactory(new PropertyValueFactory<>("address"));
        
        UserTableView.setItems(connection.LoadAllUser());
    }    
    
}
