package com.example.transactionregister;

import com.example.transactionregister.brain.DataModel;
import com.example.transactionregister.brain.IContractor;
import com.example.transactionregister.brain.ICustomer;
import com.example.transactionregister.brain.IProvider;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class TransactionController {

    @FXML
    private TextField tfProviderName;

    @FXML
    private TextField tfProviderStore;

    @FXML
    private TextField tfClientName;

    @FXML
    private TextField tfClientStore;

    @FXML
    private Slider slTransactionVolume;

    @FXML
    private Button btConfirm;

    @FXML
    private Button btReject;

    @FXML
    private Button btInitiate;

    @FXML
    private Button btComplete;

    private ICustomer customer;
    private  IProvider provider;
    private DataModel model;
    private int transactionId;

    public void fillContractors(IProvider provider, ICustomer customer){
        tfProviderName.setText(provider.getName());
        tfProviderStore.setText(String.valueOf(provider.getInStoreAmount()));
        tfClientName.setText(customer.getName());
        tfClientStore.setText(String.valueOf(customer.getInStoreAmount()));
        slTransactionVolume.setMax(provider.getInStoreAmount());
        this.provider = provider;
        this.customer = customer;
    }

    public void setModel(DataModel model){
        this.model = model;
    }

    public void setTransactionId(int transactionId){
        this.transactionId = transactionId;
    }

    @FXML
    void onAmountChange(){
        int value = (int) slTransactionVolume.getValue();
        tfProviderStore.setText(String.valueOf(provider.getInStoreAmount() - value));
        tfClientStore.setText(String.valueOf(customer.getInStoreAmount() + value));
    }

    @FXML
    public void onTransactionAction(ActionEvent e){
        if(e.getSource().equals(btConfirm)){
            model.confirmTransaction(transactionId, (int)slTransactionVolume.getValue());
            btInitiate.setDisable(false);
            btReject.setDisable(true);
        }
        else if (e.getSource().equals(btReject)) {
            model.rejectTransaction(transactionId);
            Stage stage = (Stage) btReject.getScene().getWindow();
            stage.close();
        }
        else if (e.getSource().equals(btInitiate)) {
            model.initiateTransaction(transactionId);
            btComplete.setDisable(false);
        }
        else if (e.getSource().equals(btComplete)) {
            model.completeTransaction(transactionId);
            Stage stage = (Stage) btComplete.getScene().getWindow();
            stage.close();
        }
    }
}
