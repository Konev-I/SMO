package graphics.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import functional.Simulator;
import functional.statistic.BufferStatistic;
import functional.statistic.DeviceStatistic;
import functional.statistic.RequestStatistic;
import functional.statistic.StatisticCollector;
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
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class StepModeController {

  private ObservableList<RequestStatistic> reqData = FXCollections.observableArrayList();
  private ObservableList<DeviceStatistic> devData = FXCollections.observableArrayList();
  private ObservableList<BufferStatistic> bufData = FXCollections.observableArrayList();

  @FXML
  private ResourceBundle resources;

  @FXML
  private URL location;

  @FXML
  private Button b_back;

  @FXML
  private Button b_step;

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
  private TableColumn<DeviceStatistic, Boolean> c_IsWorking;

  @FXML
  private TableColumn<DeviceStatistic, Double> c_UsingFactor;

  @FXML
  private Text text_CurrTime;

  @FXML
  private Text text_CurrEvent;

  @FXML
  private Text text_CurrSource;

  @FXML
  private TableView<BufferStatistic> t_Buffer;

  @FXML
  private TableColumn<BufferStatistic, Integer> c_Queue;

  @FXML
  private TableColumn<BufferStatistic, Integer> c_SourceNum;

  @FXML
  private TableColumn<BufferStatistic, Double> c_TimeOfGeneration;

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
    c_IsWorking.setCellValueFactory(new PropertyValueFactory<>("isWorking"));
    c_UsingFactor.setCellValueFactory(new PropertyValueFactory<>("usingFactor"));

    c_Queue.setCellValueFactory(new PropertyValueFactory<>("numOfPlace"));
    c_SourceNum.setCellValueFactory(new PropertyValueFactory<>("numOfSource"));
    Simulator.runSimulation(0);

    b_step.setOnAction(event -> {
      Simulator.runSimulation(2);
      reqData = StatisticCollector.calculateRequestStatistic();
      t_Sources.setItems(reqData);
      devData = StatisticCollector.calculateDeviceStatistic();
      t_Devices.setItems(devData);
      bufData = StatisticCollector.calculateBufferStatistic();
      t_Buffer.setItems(bufData);
      text_CurrTime.setText(String.valueOf(Simulator.getCurrentTime()));
      text_CurrEvent.setText(StatisticCollector.getEvent().toString());
      text_CurrSource.setText(String.valueOf(StatisticCollector.getEvent().getSourceOfEvent()));
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