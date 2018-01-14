import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class InfoController implements Initializable
    {

@FXML
private ChoiceBox<?> AgeChoice;

@FXML
private ChoiceBox<?> drunkChoice;

@FXML
private ChoiceBox<?> weatherChoice;

@FXML
private ChoiceBox<?> TiredChoice;

@FXML
private ChoiceBox<?> AggressiveChoice;

@FXML
private ChoiceBox<?> rentedChoice;

@FXML
private ChoiceBox<?> roadChoice;

@FXML
private ChoiceBox<?> securityChoice;

@FXML
private Button submit;

@Override
public void initialize(URL url, ResourceBundle rb)
    {

    List<Integer> list = new ArrayList<Integer>();
    for (int i =16;i<91;i++)
        {
        list.add(i);
        }
    List<String> list1 = new ArrayList<String>();
    list1.add("Yes");
    list1.add("NO");
    ObservableList obList = FXCollections.observableList(list);
    ObservableList obList1 = FXCollections.observableList(list1);
    AgeChoice.getItems().clear();
    AgeChoice.setItems(obList);
    drunkChoice.getItems().clear();
    drunkChoice.setItems(obList1);
    TiredChoice.getItems().clear();
    TiredChoice.setItems(obList1);
    AggressiveChoice.getItems().clear();
    AggressiveChoice.setItems(obList1);
    rentedChoice.getItems().clear();
    rentedChoice.setItems(obList1);
    securityChoice.getItems().clear();
    securityChoice.setItems(obList1);
    List<String> list2 = new ArrayList<String>();
    list2.add("Dry");
    list2.add("Wet");
    list2.add("Sand/mud");
    list2.add("Snow covred");
    list2.add("Ice");
    ObservableList obList2 = FXCollections.observableList(list2);
    List<String> list3 = new ArrayList<String>();
    list3.add("No adverse conditions");
    list3.add("Rain");
    list3.add("Sleet");
    list3.add("Snow");
    list3.add("Fog");
    ObservableList obList3 = FXCollections.observableList(list3);
    roadChoice.getItems().clear();
    roadChoice.setItems(obList2);
    weatherChoice.getItems().clear();
    weatherChoice.setItems(obList3);

    }

    @FXML
    void onSubmit(ActionEvent event) throws Exception
        {

       if((Integer)AgeChoice.getValue()<20 || (Integer)AgeChoice.getValue()>60) Context.age_impact = 0;
       else Context.age_impact = 1;
       if(((String) TiredChoice.getValue()).equals("Yes")) Context.fatigue=1; else Context.fatigue=0;
    if(((String) rentedChoice.getValue()).equals("Yes")) Context.rented=1; else Context.rented=0;
    if(((String) drunkChoice.getValue()).equals("Yes")) Context.drunk=1; else Context.drunk=0;
    if(((String) AggressiveChoice.getValue()).equals("Yes")) Context.agressiv=1; else Context.agressiv=0;
    if(((String) securityChoice.getValue()).equals("Yes")) Context.security=1; else Context.security=0;
    if(((String) weatherChoice.getValue()).equals("No adverse conditions")) Context.weather=1;
    else   if(((String) weatherChoice.getValue()).equals("Rain")) Context.weather=2;
    else   if(((String) weatherChoice.getValue()).equals("Sleet")) Context.weather=3;
    else   if(((String) weatherChoice.getValue()).equals("Snow")) Context.weather=4;
    else   if(((String) weatherChoice.getValue()).equals("Fog")) Context.weather=5;

    if(((String) roadChoice.getValue()).equals("Dry")) Context.road=0;
    else   if(((String) roadChoice.getValue()).equals("Wet")) Context.road=1;
    else   if(((String) roadChoice.getValue()).equals("Sand/mud")) Context.road=2;
    else   if(((String)roadChoice.getValue()).equals("Snow covred")) Context.road=3;
    else   if(((String) roadChoice.getValue()).equals("Ice")) Context.road=5;
    Context.stageTest.close();
    Game T = new Game();
   Stage s = new Stage();
        try
            {
            T.start(s);
            } catch (Exception e)
            {
            e.printStackTrace();
            }
        }

}





