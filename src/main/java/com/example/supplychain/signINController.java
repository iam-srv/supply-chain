package com.example.supplychain;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.SQLException;

public class signINController {

    @FXML
    TextField emailID;

    @FXML
    TextField Name;

    @FXML
    TextField password;

    @FXML
    TextField userType;

    public void signUp(MouseEvent event) throws SQLException, IOException {
      System.out.println("Sign up Button Clicked");

        if(emailID.getText().trim().isEmpty() || Name.getText().trim().isEmpty() || password.getText().trim().isEmpty() || userType.getText().trim().isEmpty() ){
            Dialog<String> dialog= new Dialog<>();
            dialog.setTitle("NO DATA FOUND");
            ButtonType type = new ButtonType("OK" , ButtonBar.ButtonData.OK_DONE);
            dialog.setContentText("Enter your Data");
            dialog.getDialogPane().getButtonTypes().add(type);
            dialog.showAndWait();
            System.out.println("into function");
        }else{


            String query = String.format("Insert into user values('%s' ,'%s' ,'%s' ,'%s' )" , emailID.getText(),Name.getText(),password.getText(),userType.getText());
            Main.connection.executeUpdate(query);

            Dialog<String> dialog= new Dialog<>();
            dialog.setTitle("Data Enrolled");
            ButtonType type = new ButtonType("OK" , ButtonBar.ButtonData.OK_DONE);
            dialog.setContentText("You have signed in Successfully");
            dialog.getDialogPane().getButtonTypes().add(type);
            dialog.showAndWait();

            AnchorPane login = FXMLLoader.load(getClass().getResource("LoginPage.fxml"));
            Main.root.getChildren().add(login);
        }
    }
}
