/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 *
 * @author Elkamel
 */
public class Game extends Application
{
 FXMLDocumentController cont;


DashboardController contDash;

    @Override
    public void start(Stage stage) throws Exception
    {


      Parent root,root1;

      Stage dashStage = new Stage();
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/FXMLDocument.fxml"));

    FXMLLoader fxmlLoader2 = new FXMLLoader(getClass().getResource("/fxml/Dashboard.fxml"));

    root = (Parent) fxmlLoader.load();
    root1 = (Parent) fxmlLoader2.load();

    cont = (FXMLDocumentController) fxmlLoader.getController();
    contDash = (DashboardController) fxmlLoader2.getController();
    CarInterface pr = new Proxy(cont);
    Context.setMyController(cont);
    root.setOnKeyPressed(new EventHandler<KeyEvent>(){
            public void handle(KeyEvent ke){
                    if (ke.getCode() == KeyCode.UP ) pr.increaseASC();
                    if (ke.getCode() == KeyCode.DOWN) pr.decreaseASC();
                    if(ke.getText().toUpperCase().equals("A")) pr.increaseSpeed();
                    if(ke.getText().toUpperCase().equals("B")) pr.decreaseSpeed();
                    if(ke.getText().toUpperCase().equals("S")) pr.ASC();
                    if(ke.getText().toUpperCase().equals("P")) pr.playVideo();

                }

        });
   root.setOnKeyReleased(new EventHandler<KeyEvent>(){
            public void handle(KeyEvent ke){

                    if(ke.getText().toUpperCase().equals("A"))
                   pr.accelerationReleased();
                }

        });
      Scene scene = new Scene(root);
       Scene dashScene = new Scene(root1);
       dashStage.setScene(dashScene);
       stage.setScene(scene);

       stage.show();
       dashStage.show();

cont.plusMinusTile.setValue(90);
    }


    /* public static void main(String[] args)
    {
        launch(args);
    }
    */

}
