package com.example.supplychain;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginPageController {
    @FXML
    TextField email;

    @FXML
    TextField password;

    @FXML
    public void login(MouseEvent event) throws SQLException, IOException {
        // query to check whether the login id and password is present or not
        String query = String.format("Select * from user where emailId ='%s' and pass = '%s' " ,email.getText(), password.getText());
        // result set is used  to store the values that are executed by the query
       ResultSet res = Main.connection.executeQuery(query);

        if(res.next()) { // if login id is present
            String userType = res.getString("userType"); // fetching the user type

            Main.emailId = res.getString("emailId"); // store the mailid in to gloably declared emailid for
                                                               // removing the login button for product list page
            if (userType.equals("buyer")) { // if its buyer
                  System.out.println("Logged in as Buyer");

                productPage products = new productPage(); // object of productpage
                Header header = new Header();

                ListView<HBox>  productList = products.showProducts(); // storing the returned list

                // add the list into an anchorPane
                AnchorPane productPane = new AnchorPane();
                productPane.setLayoutX(150);
                productPane.setLayoutY(100);
                productPane.getChildren().add(productList); // adding the list view into the anchorPane
                Main.root.getChildren().clear(); //  removing all  added objects from the window
                Main.root.getChildren().addAll( header.root,productPane); // adding the productPane


            } else { // if its seller
                AnchorPane sellerPage = FXMLLoader.load((getClass().getResource("sellerPage.fxml")));
                Main.root.getChildren().add(sellerPage);
                System.out.println("Logged in as seller");

            }
        }else {
            Dialog<String> dialog = new Dialog<>();
            dialog.setTitle("Login");
            dialog.setContentText("Login Failed ! Please Try Again");
            ButtonType type = new ButtonType("OK" , ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().add(type);
            dialog.showAndWait();
        }

    }
    public void siginIN(MouseEvent event) throws IOException {

        Header header = new Header();

        AnchorPane signIn = FXMLLoader.load((getClass().getResource("signIN.fxml")));
        Main.root.getChildren().clear();
        Main.root.getChildren().addAll( header.root,signIn);


    }
}
