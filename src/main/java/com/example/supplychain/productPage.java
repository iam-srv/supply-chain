package com.example.supplychain;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;

import java.sql.ResultSet;
import java.sql.SQLException;


public class productPage {  // creating gui without fxml file

    ListView<HBox> products; // list view of Horizontal box




    ListView<HBox> showProducts() throws SQLException {
        ObservableList<HBox> productList = FXCollections.observableArrayList();
        ResultSet res = Main.connection.executeQuery("Select * from product");
        products = new ListView<>();
        products.setPrefSize(300 , 300);


        Label PName = new Label();
        Label PPrice = new Label();
        Label Pid = new Label();
        HBox PDetails = new HBox();

        PName.setMinWidth(70);
        Pid.setMinWidth(20);
        PPrice.setMinWidth(70);

        PName.setText("Name ");
        PPrice.setText("Price ");
        Pid.setText("ID ");

        PDetails.getChildren().addAll(Pid,PName,PPrice);
        productList.add(PDetails);


        while(res.next()){ // for passing into the list view we need a observable list used for
                           // if any value changes in the database at that instance it will be changed in the screen accordingly

            Label productName = new Label();
            Label productPrice = new Label();
            Label productID = new Label();
            Button buy = new Button(); // buy button
            HBox productDetails = new HBox(); // creating horizontal box


            productName.setMinWidth(70);
            productPrice.setMinWidth(70);
            productID.setMinWidth(20);
            buy.setText("Buy");

            buy.setOnAction(new EventHandler<ActionEvent>() { // action for clicking the buy button
                @Override
                public void handle(ActionEvent actionEvent) {
                    if(Main.emailId.equals(""))
                    {
                        Dialog<String> dialog = new Dialog<>();
                        dialog.setTitle("Login");
                        ButtonType type = new ButtonType("OK" , ButtonBar.ButtonData.OK_DONE);
                        dialog.setContentText("Login First  before placing the order");
                        dialog.getDialogPane().getButtonTypes().add(type);
                        dialog.showAndWait();

                    }else {

                        try {
                            Order place = new Order();
                            place.placeOrder(productID.getText());
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                        System.out.println(" you clicked on buy button");
                    }
                }
            });

            // filling the labels
            productName.setText(res.getString("productName"));
            productPrice.setText((res.getString("price"))); // here it will automatically caste float to string
            productID.setText("" + res.getInt("productID"));//or else we can do by type caste it by this way

            // passing the filled values into the hbox
            productDetails.getChildren().addAll(productID , productName ,productPrice,buy );
            productList.add(productDetails);// adding the hbox to observable list

        }
        products.setItems(productList); // directly passing the observable list into the list view


        return products;
    }

    ListView<HBox> showProductByName(String search) throws SQLException {
        {
            ObservableList<HBox> productList = FXCollections.observableArrayList();
            ResultSet res = Main.connection.executeQuery("Select * from product");
            products = new ListView<>();
            products.setPrefSize(300 , 300);


            Label PName = new Label();
            Label PPrice = new Label();
            Label Pid = new Label();
            HBox PDetails = new HBox();

            PName.setMinWidth(70);
            Pid.setMinWidth(20);
            PPrice.setMinWidth(70);

            PName.setText("Name ");
            PPrice.setText("Price ");
            Pid.setText("ID ");

            PDetails.getChildren().addAll(Pid,PName,PPrice);
            productList.add(PDetails);


            while(res.next()){

                if(res.getString("productName").toLowerCase().contains(search.toLowerCase())) {


                    Label productName = new Label();
                    Label productPrice = new Label();
                    Label productID = new Label();
                    Button buy = new Button();
                    HBox productDetails = new HBox();


                    productName.setMinWidth(70);
                    productPrice.setMinWidth(70);
                    productID.setMinWidth(20);
                    buy.setText("Buy");

                    buy.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent actionEvent) {
                            if (Main.emailId.equals("")) {
                                Dialog<String> dialog = new Dialog<>();
                                dialog.setTitle("Login");
                                ButtonType type = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
                                dialog.setContentText("Login First  before placing the order");
                                dialog.getDialogPane().getButtonTypes().add(type);
                                dialog.showAndWait();

                            } else {

                                try {
                                    Order place = new Order();
                                    place.placeOrder(productID.getText());
                                } catch (SQLException e) {
                                    throw new RuntimeException(e);
                                }
                                System.out.println(" you clicked on buy button");
                            }
                        }
                    });

                    // filling the labels
                    productName.setText(res.getString("productName"));
                    productPrice.setText((res.getString("price"))); // here it will automatically caste float to string
                    productID.setText("" + res.getInt("productID"));//or else we can do by type caste it by this way

                    // passing the filled values into the hbox
                    productDetails.getChildren().addAll(productID, productName, productPrice, buy);
                    productList.add(productDetails);// adding the hbox to observable list
                }
            }
            if(productList.size() == 1){
                Dialog<String> dialog= new Dialog<>();
                dialog.setTitle("Search Result");
                ButtonType type = new ButtonType("OK" , ButtonBar.ButtonData.OK_DONE);
                dialog.setContentText("No product Found");
                dialog.getDialogPane().getButtonTypes().add(type);
                dialog.showAndWait();
            }
            products.setItems(productList); // directly passing the observable list into the list view


            return products;
        }
    }

}
