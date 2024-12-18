package GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class InterfaceLoader extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/AddMembre.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Login page");
        stage.setScene(scene);
        stage.show();
    }
}
