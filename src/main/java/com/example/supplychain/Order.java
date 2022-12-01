package com.example.supplychain;

import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Order { // add order to the database

    void placeOrder(String productID) throws SQLException {
        ResultSet res = Main.connection.executeQuery("Select max(orderID) from orders");
        int orderID = 0;
        if(res.next()){
            orderID = res.getInt("max(orderID)")+1;
        }
        String query = String.format("Insert into orders values(%s ,%S,'%s')", orderID,productID, Main.emailId); // here the userId is the mailID which id initialized in main globally

        int response = Main.connection.executeUpdate(query);
        if(response > 0){
            Dialog<String> dialog  = new Dialog<>();
            dialog.setTitle("Order");
            ButtonType type = new ButtonType("OK" , ButtonBar.ButtonData.OK_DONE);
            dialog.setContentText("Your Order is Placed");
            dialog.getDialogPane().getButtonTypes().add(type);
            dialog.showAndWait();
            System.out.println("order is placed");
        }

    }

}
