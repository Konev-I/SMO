package graphics.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ErrorController {

  private static String error;

  public static void GetError(String err){
    error = err;
  }

  @FXML
  private ResourceBundle resources;

  @FXML
  private URL location;

  @FXML
  private Text f_errText;

  @FXML
  private Button b_OK;

  @FXML
  void initialize() {
    f_errText.setText(error);
    b_OK.setOnAction(event -> {
      Stage stage = ((Stage) b_OK.getScene().getWindow());
      stage.close();
    });
  }
}
