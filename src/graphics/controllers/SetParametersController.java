package graphics.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import functional.Simulator;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.text.Text;

public class SetParametersController {

  @FXML
  private ResourceBundle resources;

  @FXML
  private URL location;

  @FXML
  private Button b_Cancel;

  @FXML
  private Button b_Set;

  @FXML
  private TextField f_numOfSources;

  @FXML
  private TextField f_numOfDevices;

  @FXML
  private TextField f_sizeOfQueue;

  @FXML
  private TextField f_alpha;

  @FXML
  private TextField f_beta;

  @FXML
  private TextField f_lambda;

  @FXML
  private TextField f_finalTime;

  @FXML
  private Text f_status;

  private void callErrorWindow(String err){
    ErrorController.GetError("Вы ввели неверное значение в поле " + err);
    try{
      FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("view/errorWindow.fxml"));
      Parent root1 = (Parent) fxmlLoader.load();
      Stage stage = new Stage();
      stage.initModality(Modality.WINDOW_MODAL);
      stage.initOwner(b_Set.getScene().getWindow());
      stage.setTitle("Error!");
      stage.setScene(new Scene(root1));
      stage.show();
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }

  @FXML
  void initialize() {
    f_numOfSources.setText(String.valueOf(Simulator.getNumOfSources()));
    f_numOfDevices.setText(String.valueOf(Simulator.getNumOfDevices()));
    f_sizeOfQueue.setText(String.valueOf(Simulator.getSizeOfQueue()));
    f_alpha.setText(String.valueOf(Simulator.getAlpha()));
    f_beta.setText(String.valueOf(Simulator.getBeta()));
    f_lambda.setText(String.valueOf(Simulator.getLambda()));
    f_finalTime.setText(String.valueOf(Simulator.getFinalTime()));

    setChange(f_numOfSources);
    setChange(f_numOfDevices);
    setChange(f_sizeOfQueue);
    setChange(f_alpha);
    setChange(f_beta);
    setChange(f_lambda);
    setChange(f_finalTime);

    f_numOfSources.textProperty().addListener(event -> {
      f_status.setText("Изменений не было!");
    });

    b_Cancel.setOnAction(event -> {
      Stage stage = ((Stage) b_Cancel.getScene().getWindow());
      stage.close();
      try {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("view/mainMenu.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        stage = new Stage();
        stage.setTitle("Main menu");
        stage.setScene(new Scene(root1));
        stage.show();
      }
      catch (IOException e) {
        e.printStackTrace();
      }
    });

    b_Set.setOnAction(event -> {
      int numOfSources;
      int numOfDevices;
      int sizeOfQueue;
      double alpha;
      double beta;
      double gamma;
      double finalTime;
      if(NumParser.tryParseInt(f_numOfSources.getText())){
        numOfSources = Integer.parseInt(f_numOfSources.getText());
      }
      else{
        callErrorWindow("количество источников.");
        return;
      }
      if(NumParser.tryParseInt(f_numOfDevices.getText())){
        numOfDevices = Integer.parseInt(f_numOfDevices.getText());
      }
      else{
        callErrorWindow("количество приборов.");
        return;
      }
      if(NumParser.tryParseInt(f_sizeOfQueue.getText())){
        sizeOfQueue = Integer.parseInt(f_sizeOfQueue.getText());
      }
      else{
        callErrorWindow("размер очереди.");
        return;
      }
      if(NumParser.tryParseDouble(f_alpha.getText())){
        alpha = Double.parseDouble(f_alpha.getText());
      }
      else{
        callErrorWindow("коэффициент альфа.");
        return;
      }
      if(NumParser.tryParseDouble(f_beta.getText())){
        beta = Double.parseDouble(f_beta.getText());
      }
      else{
        callErrorWindow("коэффициент бета.");
        return;
      }
      if(NumParser.tryParseDouble(f_lambda.getText())){
        gamma = Double.parseDouble(f_lambda.getText());
      }
      else{
        callErrorWindow("коэффициент гамма.");
        return;
      }
      if(NumParser.tryParseDouble(f_finalTime.getText())){
        finalTime = Double.parseDouble(f_finalTime.getText());
      }
      else{
        callErrorWindow("время окончания работы.");
        return;
      }

      Simulator.setParameters(numOfSources, numOfDevices, sizeOfQueue, alpha, beta, gamma, finalTime);
      f_status.setText("Параметры обновлены!");
    });
  }

  private void setChange(TextField field){
    field.textProperty().addListener(event -> {
      f_status.setText("Изменений не было!");
    });
  }
}
