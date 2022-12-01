package com.example.supplychain;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import javafx.scene.text.Font;

import java.io.IOException;
import java.sql.SQLException;

public class HeaderController {
    @FXML
    Button logoutButton;
    @FXML
    TextField searchText;

    @FXML
    Label email;

 // we can use constructor for removing the button when the email id which is passed in the main function which contains
 // the buyers email id

    @FXML  // here it is declared for addressing the login button to decide when to appear and disappear
    Button LoginButton;   // if we call the constructor we cannot access this fxml button obj so there is a inbuild function
                          // called initialize which provided by fxml to access those objects

     public void initialize() {
         if(!Main.emailId.equals("")){
             LoginButton.setOpacity(0);
             email.setText(Main.emailId);
             email.setStyle("-fx-text-fill: red; -fx-background-color: white");
             email.setFont(new Font("Bold", 20));
         }
     }


    @FXML
    public void Login(MouseEvent event) throws IOException {
        AnchorPane loginPage = FXMLLoader.load(getClass().getResource("LoginPage.fxml"));

        Main.root.getChildren().add(loginPage);
    }

    public void search(MouseEvent event) throws IOException, SQLException {

         productPage products = new productPage();
         Header header = new Header();

         AnchorPane productPane = new AnchorPane();
         productPane.getChildren().addAll(products.showProductByName(searchText.getText()));
         Main.root.getChildren().clear();
         Main.root.getChildren().addAll(header.root,productPane );
        productPane.setLayoutX(150);
        productPane.setLayoutY(100);
    }
    @FXML
    public void logout(MouseEvent event) throws IOException{
         if(logoutButton.getOpacity()==0) {
             logoutButton.setOpacity(1);
             logoutButton.setOnAction(new EventHandler<ActionEvent>() {
                 @Override
                 public void handle(ActionEvent event) {
                     Main.emailId = "";
                     logoutButton.setOpacity(0);
                     email.setOpacity(0);
                     try {
                         Header header = new Header();
                         Main.root.getChildren().add(header.root);
                     } catch (IOException e) {
                         throw new RuntimeException(e);
                     }

                 }
             });
         }else {
             logoutButton.setOpacity(0);
         }
    }
}
