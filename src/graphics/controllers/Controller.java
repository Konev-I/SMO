package graphics.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Controller {

  @FXML
  private ResourceBundle resources;

  @FXML
  private URL location;

  @FXML
  private Button b_SetParameters;

  @FXML
  private Button b_AutoMode;

  @FXML
  private Button b_StepMode;

  @FXML
  private Button b_Exit;

  @FXML
  void initialize() {
    b_SetParameters.setOnAction(event -> {
      Stage stage = ((Stage) b_SetParameters.getScene().getWindow());
      stage.close();
      try {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("view/setParameters.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        stage = new Stage();
        stage.setTitle("Set parameters");
        stage.setScene(new Scene(root1));
        stage.show();
      }
      catch (IOException e) {
        e.printStackTrace();
      }
    });

    b_AutoMode.setOnAction(event -> {
      Stage stage = ((Stage) b_AutoMode.getScene().getWindow());
      stage.close();
      try {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("view/autoMode.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        stage = new Stage();
        stage.setTitle("Auto mode");
        stage.setScene(new Scene(root1));
        stage.show();
      }
      catch (IOException e) {
        e.printStackTrace();
      }
    });

    b_StepMode.setOnAction(event -> {
      Stage stage = ((Stage) b_StepMode.getScene().getWindow());
      stage.close();
      try {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("view/stepMode.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        stage = new Stage();
        stage.setTitle("Step mode");
        stage.setScene(new Scene(root1));
        stage.show();
      }
      catch (IOException e) {
        e.printStackTrace();
      }
    });

    b_Exit.setOnAction(event -> {
      ((Stage) b_Exit.getScene().getWindow()).close();
    });
  }
}
