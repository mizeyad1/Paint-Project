
package paintproject.controller;

import static com.oracle.nio.BufferSecrets.instance;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import static jdk.nashorn.internal.objects.Global.instance;

public class PaintProject extends Application {
    

    public void start(Stage stage) throws Exception {
        stage.setTitle("Paint application");
       stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"))));
       stage.show();
       
    }

    public static void main(String[] args) {
       //  SingletonClass object = SingletonClass.getInstance();
        launch(args);
       
    }
    
}




