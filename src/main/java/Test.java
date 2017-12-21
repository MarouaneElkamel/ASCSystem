import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Test extends Application
    {
    public static void main(String[] args)
        {
        launch(args);
        }

    @Override
    public void start(Stage primaryStage) throws Exception
        { Parent root = FXMLLoader.load(getClass().getResource("/fxml/Info.fxml"));

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        Context.stageTest=stage;
        stage.setTitle("Info");
        stage.setScene(scene);
        stage.show();


        }
    }
