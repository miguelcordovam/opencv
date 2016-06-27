package blur;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.opencv.core.Core;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("imageBlur.fxml"));
        primaryStage.setTitle("Blur Machu Picchu");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.initStyle(StageStyle.UTILITY);
        primaryStage.show();
    }


    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        launch(args);
    }
}
