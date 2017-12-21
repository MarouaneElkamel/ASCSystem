/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * @author Elkamel
 */
public class Proxy implements CarInterface
    {
    public FXMLDocumentController myController;

    public Proxy(FXMLDocumentController Controller)
        {
        this.myController = Controller;
        }

    public void increaseSpeed()
        {
        myController.increaseSpeed();
        }

    public void decreaseSpeed()
        {
        myController.decreaseSpeed();
        }

    public void ASC()
        {
        myController.ASC();
        }

    public void playVideo()
        {
        myController.playVideo();
        }

    public void accelerationReleased()
        {
        myController.accelerationReleased();
        }

    public void increaseASC()
        {
        if (myController.plusMinusTile.getDescription().equals("System ASC Active"))
            {
            myController.increaseASC();
            }
        }

    public void decreaseASC()
        {
        if (myController.plusMinusTile.getDescription().equals("System ASC Active"))
            {
            myController.decreaseASC();
            }
        }
    }
