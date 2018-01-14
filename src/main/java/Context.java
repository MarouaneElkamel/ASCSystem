/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javafx.stage.Stage;

/**
 * @author Elkamel
 */
public class Context
    {
    static int webservicecall =0;

    private static boolean panic = false;
    private static double similarity = 0;
    static int age_impact=1;
    static int fatigue=1;
    static  int rented=1;
    static  int drunk=1;
    static int weather;
    static  int road=1;
    static  int agressiv=1;
    static  int security=1;
    static int distance = 0;
    static int speed_diff=0;
    static int speedlimit=0;
    static int illumination =1;
    static int curved=1;
    static int distraction =0;
    static Stage stageTest;
    public static EventRecorder ER = new EventRecorder();

    public static FXMLDocumentController getMyController()
        {
        return myController;
        }

    public static void setMyController(FXMLDocumentController myController)
        {
        Context.myController = myController;
        }

    public static FXMLDocumentController myController;

    /**
     * @return the panic
     */
    public static boolean isPanic()
        {
        return panic;
        }

    /**
     * @param aPanic the panic to set
     */
    public static void setPanic(boolean aPanic)
        {
        panic = aPanic;
        }


    static int callAI(){
    return 1;
    }
    /**
     * @return the similarity
     */
    public static double getSimilarity()
        {
        return similarity;
        }

    /**
     * @param aSimilarity the similarity to set
     */
    public static void setSimilarity(double aSimilarity)
        {
        similarity = aSimilarity;
        }
    }
