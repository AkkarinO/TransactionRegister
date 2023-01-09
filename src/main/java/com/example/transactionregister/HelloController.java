package com.example.transactionregister;

import com.example.transactionregister.brain.DataModel;
import com.example.transactionregister.brain.ICustomer;
import com.example.transactionregister.brain.IProvider;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import java.io.File;
import com.example.transactionregister.brain.IContractor;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.Buffer;
import java.util.Objects;

public class HelloController {
    @FXML
    private MenuItem openItem;

    @FXML
    private MenuItem closeItem;

    @FXML
    private TableColumn<IContractor, Integer> idCol;

    @FXML
    private TableColumn<IContractor, String> nameCol;

    @FXML
    private TableColumn<IContractor, IContractor.ContractorType> typeCol;

    @FXML
    private TableView<IContractor> contractorsTable;

    @FXML
    private TextField tfProvider;

    @FXML
    private  TextField tfClient;

    @FXML
    private Button btProvider;

    @FXML
    private Button btClient;

    @FXML
    private Button btDeal;

    @FXML
    private TextField tfName;  //label

    @FXML
    private RadioButton rbCustomer;

    @FXML
    private RadioButton rbVendor;

    @FXML
    private RadioButton rbBroker;

    @FXML
    private Slider slAmount;

    @FXML
    private ComboBox<String> cbIndustry;

    @FXML
    private ToggleGroup tgTypeGroup;

    @FXML
    private Button btAdd;

    private DataModel model;
    private IProvider selectedProvider;
    private ICustomer selectedCustomer;

    @FXML
    public void fileMenuAction(ActionEvent e) throws IOException {
        if(e.getSource().equals(openItem)){
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open Contractors File");
            File file = fileChooser.showOpenDialog((openItem.getParentPopup().getScene().getWindow()));
            if(!Objects.isNull(file)){
                model = new DataModel();
                model.parseFile(file.toPath());

                idCol.setCellValueFactory(
                        new PropertyValueFactory<>("id")
                );

                nameCol.setCellValueFactory(
                        new PropertyValueFactory<>("name")
                );

                typeCol.setCellValueFactory(
                        new PropertyValueFactory<>("type")
                );

                contractorsTable.setItems(model.getModel());
            }
        }

        if(e.getSource().equals(closeItem)){
            System.exit(0);
        }
    }

    @FXML
    void addContractorToModel(ActionEvent e){
        String name = tfName.getText();
        int amount = (int)slAmount.getValue();
        String industry = cbIndustry.getValue();
        String type = ((RadioButton) tgTypeGroup.getSelectedToggle()).getText();

        String json = String.format("{\"name\": \"%s\", \"inStoreAmount\": %d," +
                "\"industry\": \"%s\", \"contractorType\": \"%s\"}", name, amount, industry, type);

        model.addNewContractor(json);
    }

    @FXML
    void selectContractor(ActionEvent e){
        IContractor contractor = contractorsTable.getSelectionModel().getSelectedItem();
        String name = contractor.getName();
        if(e.getSource().equals(btProvider)){
            if(contractor.getType() == IContractor.ContractorType.VENDOR ||
            contractor.getType() == IContractor.ContractorType.BROKER) {
                tfProvider.setText(name);
                this.selectedProvider = (IProvider) contractor;
            }
        } else if (e.getSource().equals((btClient))) {
            if (contractor.getType() == IContractor.ContractorType.CUSTOMER ||
                    contractor.getType() == IContractor.ContractorType.BROKER) {
                    tfClient.setText(name);
                    this.selectedCustomer = (ICustomer) contractor;
            }
        }
    }

    @FXML
    void openTransactionDialog() throws IOException {
        int idTransaction = model.prepareTransaction(selectedProvider, selectedCustomer);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("transaction.fxml"));
        Parent parent = fxmlLoader.load();
        TransactionController trController = fxmlLoader.<TransactionController>getController();
        trController.setModel(model);
        trController.setTransactionId(idTransaction);
        trController.fillContractors(selectedProvider, selectedCustomer);

        Scene scene = new Scene(parent, 600, 300);
        Stage stage = new Stage();
        stage.setTitle("Transaction dialog");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();

    }
}
