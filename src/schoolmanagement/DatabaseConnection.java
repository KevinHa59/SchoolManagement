/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schoolmanagement;

import com.mysql.cj.xdevapi.PreparableStatement;
import java.io.Console;
import java.sql.Connection;
import java.util.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.layout.Pane;
import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;
import javafx.util.Callback;
import javax.swing.text.TableView;
import schoolmanagement.Administrator.user;

/**
 *
 * @author KevinHa
 */

public class DatabaseConnection {
    Connection connection;
    public Connection getConnection(){
        String dbName="cs_db";
        String userName="root";
        String password="root";
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+dbName,userName,password);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        return connection;
    }
    
    //Signin
    public String Signin(String Username, String Password, String Role){
        String UserID = "";
        String _role = null;
        
        if(Role.equals("Student")){
            _role = "student_information";
            
        }else if(Role.equals("Staff")) {
            _role = "staff_information";
        }else if(Role.equals("Administrator")) {
            _role = "administrator";
        }
        
        System.out.println(Role + " + " + _role);
        
        
        String password = "";
        Statement stm;
        Connection con = getConnection();
        
        String query = "SELECT password,id FROM "+_role+" WHERE username = '"+Username+"'";
        try {
            stm = con.createStatement();
            ResultSet rs = stm.executeQuery(query);
            while(rs.next()){
                password = rs.getString("password");
                if(password.equals(Password)){
                    UserID = rs.getString("id");
                }else {
                    UserID = "";
                }
                
            }
            System.out.println(Role+ " " + Username + " " + Password + " " + password);
            
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return UserID;
    }
    // Load Basic Information
    public ArrayList<String> LoadUserBasicInformation(String UserID, String Role){
        ArrayList<String> user_information = new ArrayList<String>();
        String _role = null;

        
        if(Role.equals("Student")){
            _role = "student_information";
            
        }else if(Role.equals("Staff")) {
            _role = "staff_information";
        }else if(Role.equals("Administrator")) {
            _role = "administrator";
        }
        
        Connection con = getConnection();
        
        String SQL = "SELECT firstname,lastname FROM "+_role+" WHERE id = '"+UserID+"'";
        
        Statement statement;
        
        try {
            statement = con.createStatement();
            ResultSet rs = statement.executeQuery(SQL);
            
            while (rs.next()) {
                user_information.add(rs.getString("firstname"));
                user_information.add(rs.getString("lastname"));
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user_information;
        
    }
    
    
    // Load Full Information
    public ArrayList<String> LoadUserFullInformation(String UserID, String Role){
        ArrayList<String> user_information = new ArrayList<>();
        String _role = null;
        
        if(Role.equals("Student")){
            _role = "student_information";
            
        }else if(Role.equals("Staff")) {
            _role = "staff_information";
        }else if(Role.equals("Administrator")) {
            _role = "administrator";
        }
        
        Connection con = getConnection();
        
        //String SQL = "SELECT * FROM "+_role+" WHERE id = '"+UserID+"'";
        
        String SQL = "SELECT * FROM "+_role;
        
        Statement statement;
        
        try {
            statement = con.createStatement();
            ResultSet rs = statement.executeQuery(SQL);
            
            while (rs.next()) {
                user_information.add(rs.getString("id")); // id
                user_information.add(rs.getString("firstname")); // firstname
                user_information.add(rs.getString("lastname")); // lastname
                user_information.add(rs.getString("email")); // email
                user_information.add(rs.getString("phone")); // phone
                user_information.add(rs.getString("street")); // street
                user_information.add(rs.getString("city")); // city
                user_information.add(rs.getString("state")); // state
                user_information.add(rs.getString("zip")); // zip
                user_information.add(rs.getString("birthday")); // birthday
                user_information.add(rs.getString("degree")); // birthday
                user_information.add(rs.getString("subject")); // birthday
            }
           
            
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user_information;
        
    }
    // Update Student Information
    public void UpdateStudentInformation(String UserID, String Role, int phone, String street, String city, String State, int zip){
        Connection con = getConnection();
        
        String SQL = "UPDATE student_information set phone = "+phone+", street = '"+street+"', city = '"+city+"', state = '"+State+"', zip = '"+zip+"' WHERE id = '"+UserID+"'";
        
        Statement statement;
        try {
            statement = con.createStatement();
            statement.executeUpdate(SQL);
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // Change Student Password
    public Boolean ChangeStudentPassword(String UserID, String Role,String CurrentPassword, String NewPassword){
        Connection con = getConnection();
        String _role = "";
        String _currentPassword = "";
        Boolean equal = false;
        if(Role.equals("Student")){
            _role = "Student_information";
        }        
        
        // get current password
        String SQL = "SELECT password FROM "+_role+" WHERE id = '"+UserID+"'";
        
        Statement statement;
        
        try{
            statement = con.createStatement();
            ResultSet rs = statement.executeQuery(SQL);
            
            while(rs.next()){
                _currentPassword = rs.getString("password");
            }
                    
        }catch(SQLException ex){
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        //check password to user current password
        if(_currentPassword.equals(CurrentPassword)){
            equal =true;
        }
        // Update New Password
        if(equal == true){
            String USQL = "UPDATE "+_role+"  SET password = '"+NewPassword+"' WHERE id = '"+UserID+"'";
            
            Statement Ustatement;
            
            try{      
                Ustatement = con.createStatement();
                Ustatement.executeUpdate(USQL);
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
        
        return equal;
    }
    // Administrator Role---------------------------------------------------------------
    
    // Create Student Account
    public void CreateStudentAccount(String FirstName, String LastName, String PhoneNumber, String Address, String City, String State, String Zip,LocalDate Birthday, String degree, String Major){
        Calendar cal = Calendar.getInstance(TimeZone.getDefault());
        Date date = cal.getTime();
        String ID = (Birthday.getYear()+"").charAt(2) + "" + + (Birthday.getYear()+"").charAt(3)+ (cal.get(Calendar.DAY_OF_MONTH))+cal.get(Calendar.MINUTE)+cal.get(Calendar.SECOND);
        
        int BirthYear = Birthday.getYear();
        int BirthDate = Birthday.getDayOfMonth();
        char DateNo = 0;
        if(BirthDate > 9){
            DateNo = (BirthDate+"").charAt(1);
        }else{
            DateNo = (BirthDate+"").charAt(0);
        }
        Connection con = getConnection();
        System.out.println(degree +" - "+ Major);
        
        String query = 
                "INSERT INTO student_information" +
                "(id,username,firstname,lastname,password,email,phone,street,city,state,zip,birthday,subject,degree)" +
                "VALUES" +
                "('"+ID+"','"+LastName.toLowerCase()+FirstName.toLowerCase().charAt(0)+DateNo+"','"+FirstName+"','"+LastName+"','"+FirstName.toLowerCase()+LastName.toLowerCase()+ BirthYear +"','"+LastName.toLowerCase()+FirstName.toLowerCase().charAt(0)+DateNo+"@gator.uhd.edu','"+PhoneNumber+"','"+Address+"','"+City+"','"+State+"','"+Zip+"','"+Birthday+"','"+degree+"','"+Major+"')";
        
        Statement statement;
        try {
            statement = con.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    // Get Degree List
    public ArrayList<String> GetDegreeList(){
        ArrayList<String> list = new ArrayList<>();
        
        Connection con = getConnection();
        
        String SQL = "SELECT name FROM degree ";
        
        try {
            ResultSet rs = con.createStatement().executeQuery(SQL);
            while(rs.next()){
                list.add(rs.getString("name"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
    }
    
    // Get Major List
    public ArrayList<String> GetMajorList(){
        ArrayList<String> list = new ArrayList<>();
        
        Connection con = getConnection();
        
        String SQL = "SELECT subject_name FROM course_subject";
        
        try {
            ResultSet rs = con.createStatement().executeQuery(SQL);
            while(rs.next()){
                list.add(rs.getString("subject_name"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
    }
    
    // Create Professor Account
    public void CreateProfessorAccount(String FirstName, String LastName, String PhoneNumber, String Address, String City, String State, String Zip,LocalDate Birthday, Integer salary ){
        Calendar cal = Calendar.getInstance(TimeZone.getDefault());
        Date date = cal.getTime();
        String ID = (Birthday.getYear()+"").charAt(2) + "" + + (Birthday.getYear()+"").charAt(3)+ (cal.get(Calendar.DAY_OF_MONTH))+cal.get(Calendar.MINUTE)+cal.get(Calendar.SECOND);
        
        int BirthYear = Birthday.getYear();
        int BirthDate = Birthday.getDayOfMonth();
        char DateNo = 0;
        if(BirthDate > 9){
            DateNo = (BirthDate+"").charAt(1);
        }else{
            DateNo = (BirthDate+"").charAt(0);
        }
        Connection con = getConnection();
        
        
        String query = 
                "INSERT INTO staff_information" +
                "(id,username,firstname,lastname,password,email,phone,street,city,state,zip,birthday,job_id,salary)" +
                "VALUES" +
                "('"+ID+"','"+LastName.toLowerCase()+FirstName.toLowerCase().charAt(0)+DateNo+"','"+FirstName+"','"+LastName+"','"+FirstName.toLowerCase()+LastName.toLowerCase()+ BirthYear +"','"+LastName.toLowerCase()+FirstName.toLowerCase().charAt(0)+DateNo+"@uhd.edu','"+PhoneNumber+"','"+Address+"','"+City+"','"+State+"','"+Zip+"','"+Birthday+"',1,"+salary+")";
        
        Statement statement;
        try {
            statement = con.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    public ObservableList LoadAllUser(){
        ObservableList<schoolmanagement.Administrator.user> oblist = FXCollections.observableArrayList();
        Connection con = getConnection();
        String sql = "SELECT id, firstname, lastname, phone, email, birthday, street, city,state, zip FROM student_information";
        try {
            ResultSet rs = con.createStatement().executeQuery(sql);
            while(rs.next()){
            oblist.add(new schoolmanagement.Administrator.user(rs.getString("id"), rs.getString("firstname"),rs.getString("lastname"),rs.getString("email"),rs.getInt("phone"),rs.getDate("birthday"),rs.getString("street")+ " " + rs.getString("city")+ ", " + rs.getString("state")+ " " + rs.getString("zip")));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return oblist;

    }
    
    // Load All Subject
    public ArrayList<String> LoadAllSubject(){
        ArrayList<String> list = new ArrayList<>();
        Connection con = getConnection();
        String SQL = "SELECT * FROM course_subject";
        
        Statement statement;
        try {
            statement = con.createStatement();
            ResultSet rs = statement.executeQuery(SQL);
            
            while(rs.next()){
                list.add(rs.getString("subject_name"));
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list; 
    }
    // Load All Subject
    public ArrayList<String> LoadAllSchoolLocation(){
        ArrayList<String> list = new ArrayList<>();
        Connection con = getConnection();
        String SQL = "SELECT * FROM school_location";
        
        Statement statement;
        try {
            statement = con.createStatement();
            ResultSet rs = statement.executeQuery(SQL);
            
            while(rs.next()){
                list.add(rs.getString("location_name"));
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list; 
    }
}
