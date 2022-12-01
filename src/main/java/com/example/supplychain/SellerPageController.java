package com.example.supplychain;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SellerPageController {
    @FXML
    TextField name;

    @FXML
    TextField price;

    @FXML
    TextField  sellerID; // email

    public void Add(MouseEvent event) throws SQLException {  // function to add into product table
        // executeQuery is used for selecting the data from table
        ResultSet res = Main.connection.executeQuery("Select max(ProductID) from product");
        int productID = 0;
        if(res.next()){
            productID = res.getInt("max(productID)")+1;
        }
        String query = String.format("Insert Into product values('%s' ,'%s' ,'%s' ,'%s')" ,productID, name.getText(),price.getText(),sellerID.getText());
        // executeUpdate is used for insertion in the table
        int response = Main.connection.executeUpdate(query);
        System.out.println(response);

        // creating a dialog box when the prodcut is added into the table
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Add product");
        ButtonType type = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        if(response > 0){
            dialog.setContentText("The Product Is Added Successfully");
        }else {
            dialog.setContentText("Cannot Add The Product");
        }
        dialog.getDialogPane().getButtonTypes().add(type);
        dialog.showAndWait();
    }
}
