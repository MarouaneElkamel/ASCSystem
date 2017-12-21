/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import eu.hansolo.medusa.Gauge;
import eu.hansolo.medusa.GaugeBuilder;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * FXML Controller class
 *
 * @author Elkamel
 */
public class DashboardController implements Initializable
    {
    @FXML
    private StackPane MyPane;

    @FXML
    void openPnml(ActionEvent event)
        {
        try
            {
            Desktop.getDesktop().open(new File("./Panic.pnml"));
            } catch (IOException ex)
            {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    public Gauge similarity;
    Timeline fiveSecondsWonder = new Timeline(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>()
        {
        @Override
        public void handle(ActionEvent event)
            {
            similarity.setValue(Context.getSimilarity());
            if (similarity.getValue() > 0) similarity.setBarColor(Color.GREEN);
            }
        }));

    public void initsimilarity()
        {
        GaugeBuilder builder = GaugeBuilder.create();
        similarity = builder.decimals(2).maxValue(100).unit("%").skinType(Gauge.SkinType.SLIM).build();
        similarity.setBarColor(Color.RED);
        similarity.setValue(0);
        similarity.setValueColor(Color.BLACK);
        similarity.setTitle("Similarity");
        similarity.setTitleColor(Color.BLACK);
        similarity.setUnitColor(Color.BLACK);
        }

    @Override
    public void initialize(URL url, ResourceBundle rb)
        {
        initsimilarity();
        fiveSecondsWonder.setCycleCount(Timeline.INDEFINITE);
        fiveSecondsWonder.play();
        MyPane.getChildren().add(similarity);
        MyPane.setAlignment(Pos.CENTER);
        }
    }
