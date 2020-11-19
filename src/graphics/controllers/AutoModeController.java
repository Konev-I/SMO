package graphics.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import functional.Simulator;
import functional.statistic.DeviceStatistic;
import functional.statistic.StatisticCollector;
import functional.statistic.RequestStatistic;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class AutoModeController {

  private ObservableList<RequestStatistic> reqData = FXCollections.observableArrayList();
  private ObservableList<DeviceStatistic> devData = FXCollections.observableArrayList();

  @FXML
  private ResourceBundle resources;

  @FXML
  private URL location;

  @FXML
  private Button b_back;

  @FXML
  private Button b_start;

  @FXML
  private TableView<RequestStatistic> t_Sources;

  @FXML
  private TableColumn<RequestStatistic, Integer> c_Sources;

  @FXML
  private TableColumn<RequestStatistic, Integer> c_NumOfRequest;

  @FXML
  private TableColumn<RequestStatistic, Integer> c_Rejected;

  @FXML
  private TableColumn<RequestStatistic, Integer> c_Complete;

  @FXML
  private TableColumn<RequestStatistic, Double> c_ProbOfReject;

  @FXML
  private TableColumn<RequestStatistic, Double> c_AverageInSyst;

  @FXML
  private TableColumn<RequestStatistic, Double> c_AverageWaiting;

  @FXML
  private TableColumn<RequestStatistic, Double> c_AverageService;

  @FXML
  private TableColumn<RequestStatistic, Double> c_DispWaiting;

  @FXML
  private TableColumn<RequestStatistic, Double> c_DispService;

  @FXML
  private TableView<DeviceStatistic> t_Devices;

  @FXML
  private TableColumn<DeviceStatistic, Integer> c_Device;

  @FXML
  private TableColumn<DeviceStatistic, Double> c_UsingFactor;

  @FXML
  void initialize() {
    c_Sources.setCellValueFactory(new PropertyValueFactory<>("numOfSource"));
    c_NumOfRequest.setCellValueFactory(new PropertyValueFactory<>("numOfRequest"));
    c_Rejected.setCellValueFactory(new PropertyValueFactory<>("rejected"));
    c_Complete.setCellValueFactory(new PropertyValueFactory<>("complete"));
    c_ProbOfReject.setCellValueFactory(new PropertyValueFactory<>("probOfReject"));
    c_AverageInSyst.setCellValueFactory(new PropertyValueFactory<>("averageInSystem"));
    c_AverageWaiting.setCellValueFactory(new PropertyValueFactory<>("averageWaiting"));
    c_AverageService.setCellValueFactory(new PropertyValueFactory<>("averageService"));
    c_DispWaiting.setCellValueFactory(new PropertyValueFactory<>("dispWaiting"));
    c_DispService.setCellValueFactory(new PropertyValueFactory<>("dispService"));

    c_Device.setCellValueFactory(new PropertyValueFactory<>("numOfDevice"));
    c_UsingFactor.setCellValueFactory(new PropertyValueFactory<>("usingFactor"));

    b_start.setOnAction(event -> {
      Simulator.runSimulation(0);
      Simulator.runSimulation(1);
      reqData = StatisticCollector.calculateRequestStatistic();
      t_Sources.setItems(reqData);
      devData = StatisticCollector.calculateDeviceStatistic();
      t_Devices.setItems(devData);
      });

    b_back.setOnAction(event -> {
      Stage stage = ((Stage) b_back.getScene().getWindow());
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
  }
}
