/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import eu.hansolo.medusa.Gauge;
import eu.hansolo.medusa.GaugeBuilder;
import eu.hansolo.medusa.Section;
import eu.hansolo.medusa.skins.ModernSkin;
import eu.hansolo.tilesfx.Tile;
import eu.hansolo.tilesfx.Tile.SkinType;
import eu.hansolo.tilesfx.TileBuilder;
import javafx.beans.Observable;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Elkamel
 */
public class FXMLDocumentController implements Initializable, CarInterface
    {
    private boolean firstTime = false;
    @FXML
    public HBox downBox;
    public Gauge speedGauge;
    public Tile plusMinusTile;
    private double previousValue = 90;
    @FXML
    private MediaView mediaView;
    MediaPlayer mp;

    public void increaseASC()
        {
        if (plusMinusTile.getValue() < plusMinusTile.getMaxValue() - 4)
            plusMinusTile.setValue(plusMinusTile.getValue() + 5);
        }

    public void decreaseASC()
        {
        if (plusMinusTile.getValue() > 4) plusMinusTile.setValue(plusMinusTile.getValue() - 5);
        }

    public void increaseSpeed()
        {
        if (firstTime == false)
            {
            playVideo();
            firstTime = true;
            }
        if (speedGauge.getValue() < speedGauge.getMaxValue())
            {
            speedGauge.setValue(speedGauge.getValue() + 1);
            if (speedGauge.getValue()>110) Context.speedlimit=1;
            }
        }

    public void decreaseSpeed()
        {
        if (speedGauge.getValue() > 0)
            {
            speedGauge.setValue(speedGauge.getValue() - 1);
            }
        desactivateSystem();
        }

    public void activateSystem()
        {
        if (firstTime == false)
            {
            playVideo();
            firstTime = true;
            }
        plusMinusTile.setDescription("System ASC Active");
        plusMinusTile.setValueColor(Color.GREEN);
        plusMinusTile.setUnitColor(Color.GREEN);
        speedGauge.setValue(plusMinusTile.getValue());
        }

    public void desactivateSystem()
        {
        plusMinusTile.setDescription("System ASC Inactive");
        plusMinusTile.setValueColor(Color.RED);
        plusMinusTile.setUnitColor(Color.RED);
        }

    public void accelerationReleased()
        {
        if (plusMinusTile.getDescription().equals("System ASC Active"))
            {
            speedGauge.setValue(plusMinusTile.getValue());
            }
        }

    public void ASC()
        {
        if (plusMinusTile.getDescription().equals("System ASC Inactive") && speedGauge.getValue() > 0) activateSystem();
        }

    void initSpeedGauge()
        {
        speedGauge = GaugeBuilder.create().decimals(0).sections(new Section(85, 90, "", Color.rgb(204, 0, 0, 0.5)), new Section(90, 95, "", Color.rgb(204, 0, 0, 0.75)), new Section(95, 100, "", Color.rgb(204, 0, 0))).minValue(0).threshold(120).thresholdVisible(true).maxValue(240).animated(true).title("Speed Meter").unit("KM/H").build();
        speedGauge.setSkin(new ModernSkin(speedGauge));
        speedGauge.setValueColor(Color.WHITE);
        speedGauge.setTitleColor(Color.WHITE);
        speedGauge.setSubTitleColor(Color.WHITE);
        speedGauge.setSubTitleColor(Color.WHITE);
        speedGauge.setBarColor(Color.rgb(0, 214, 215));
        speedGauge.setNeedleColor(Color.WHITE);
        speedGauge.setThresholdColor(Color.rgb(204, 0, 0));
        speedGauge.setTickLabelColor(Color.WHITE);
        speedGauge.setTickMarkColor(Color.rgb(204, 0, 0));
        speedGauge.setUnitColor(Color.WHITE);
        speedGauge.setBarBackgroundColor(Color.rgb(204, 0, 0));
        speedGauge.setValue(0);
        }

    public void valueIncreased()
        {
        if (plusMinusTile.getDescription().equals("System ASC Active"))
            {
            speedGauge.setValue(speedGauge.getValue() + 5);
            }
        }

    public void valueDecreased()
        {
        if (plusMinusTile.getDescription().equals("System ASC Active")) speedGauge.setValue(speedGauge.getValue() - 5);
        }

    public void valueChanged()
        {
        if (previousValue != plusMinusTile.getValue())
            {
            if (plusMinusTile.getValue() > previousValue) valueIncreased();
            else valueDecreased();
            previousValue = plusMinusTile.getValue();
            }
        }

    void initPlusMinus()
        {
        plusMinusTile = TileBuilder.create().skinType(SkinType.PLUS_MINUS).decimals(0).onValueChanged((Observable o) ->
        {
        valueChanged();
        }).increment(5).maxValue(240).minValue(0).animated(true).title("System ASC").description("System ASC Inactive").unit(" KM/H").descriptionColor(Color.RED).build();
        plusMinusTile.setValueColor(Color.RED);
        plusMinusTile.setUnitColor(Color.RED);
        plusMinusTile.setBackgroundColor(Color.GRAY);
        }

    public void playVideo()
        {
        mp.play();
        new java.util.Timer().schedule(new java.util.TimerTask()
            {
            @Override
            public void run()
                {
                Context.setPanic(true);

                System.out.println("panic time");
                }
            }, 7000);
        new java.util.Timer().schedule(new java.util.TimerTask()
            {
            @Override
            public void run()
                {
                Context.setPanic(false);
                System.out.println("panic time over");
                }
            }, 16000);
        }

    @Override
    public void initialize(URL url, ResourceBundle rb)
        {
        try
            {
            initSpeedGauge();
            initPlusMinus();
            downBox.getChildren().addAll(speedGauge, plusMinusTile);
            downBox.setPadding(new Insets(20));
            downBox.setSpacing(20);
            downBox.setAlignment(Pos.CENTER);
            Media m = new Media(this.getClass().getResource("A.mp4").toURI().toURL().toString());
            mp = new MediaPlayer(m);
            mediaView.setMediaPlayer(mp);
            mediaView.setPreserveRatio(true);
            System.out.println("working");
            } catch (URISyntaxException ex)
            {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (MalformedURLException ex)
            {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
