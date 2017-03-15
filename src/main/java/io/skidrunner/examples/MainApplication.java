package io.skidrunner.examples;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("MainApplication.fxml"));
        fxmlLoader.setController(this);
        Scene scene = new Scene(fxmlLoader.load(), 800, 450, true, SceneAntialiasing.BALANCED);

        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private TreeView<String> projectTreeView;
    @FXML
    private Button infoButton;
    @FXML
    private Button githubButton;
    @FXML
    private Button runButton;
    @FXML
    private Label titleLabel;
    @FXML
    private Pane contentPane;
    @FXML
    private TextArea outputTextArea;

    @FXML
    public void initialize() {
        githubButton.setOnMouseClicked((event) -> {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("BasicBrowser.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 800, 450);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } );

    }

    public static void main(String[] args) {
        launch();
    }
}
