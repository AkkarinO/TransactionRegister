package com.example.transactionregister.brain;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.stream.JsonReader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.util.ArrayList;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class DataModel {

    private ObservableList<IContractor> contractorsList;
    private Map<Integer, Transaction> transactionRegister;
    public static int TRANSACTION_ID = 0;

    public DataModel(){
        transactionRegister = new HashMap<>();
    }

    public void parseFile(Path file) throws IOException {
        contractorsList = FXCollections.observableArrayList();

        Gson gson = new Gson();

        Reader reader = Files.newBufferedReader(file);

        JsonArray array = gson.fromJson(reader, JsonArray.class);

        for (JsonElement item : array) {
            contractorsList.add(ContractorFactory.create(item));
        }
        reader.close();
    }

    public ObservableList<IContractor> getModel() {
        return contractorsList;
    }

    public void addNewContractor(String jsonContractor) {
        JsonElement anotherContractor = new Gson().fromJson(jsonContractor, JsonElement.class);
        if (!anotherContractor.getAsJsonObject().has("id")) {
            int another_id = contractorsList.stream().mapToInt(IContractor::getId).max().orElse(-1) + 1;
            anotherContractor
                    .getAsJsonObject()
                    .add("id",
                            new Gson().fromJson(String.valueOf(another_id), JsonElement.class));
        }
        contractorsList.add(ContractorFactory.create(anotherContractor));
    }

    public int prepareTransaction(IProvider provider, ICustomer customer) {
        Transaction deal = new Transaction(customer, provider);
        transactionRegister.put(TRANSACTION_ID, deal);
        return TRANSACTION_ID++;
    }

    public void confirmTransaction(int transactionId, int amount) {
        Transaction t = transactionRegister.get(transactionId);
        t.setAmountOfGoods(amount);
        t.getProvider().setPackagingMethod(new Packaging("items", Packaging.Container.CASE, 50));
        t.confirm();
        System.out.println("Transaction with id = " + transactionId + "is: " + t.getStatus());
    }

    public void rejectTransaction(int transactionId) {
        Transaction t = transactionRegister.get(transactionId);
        t.reject();
        System.out.println("Transaction with id = " + transactionId + "is: " + t.getStatus());
    }

    public void initiateTransaction(int transactionId) {
        Transaction t = transactionRegister.get(transactionId);
        t.initiateTransaction();
        System.out.println("Transaction with id = " + transactionId + "is: " + t.getStatus());
    }

    public void completeTransaction(int transactionId) {
        Transaction t = transactionRegister.get(transactionId);
        t.completeTransaction();
        System.out.println("Transaction with id = " + transactionId + "is: " + t.getStatus());
    }
}

class ContractorFactory {

    public static IContractor create(JsonElement element) {
        int id = element.getAsJsonObject().get("id").getAsInt();
        int amount = element.getAsJsonObject().get("inStoreAmount").getAsInt();
        String name = element.getAsJsonObject().get("name").getAsString();
        String industry = element.getAsJsonObject().get("industry").getAsString();
        IContractor contractor = null;
        switch (IContractor.ContractorType.valueOf(element.getAsJsonObject()
                .get("contractorType").getAsString()
                .toUpperCase())){
            case VENDOR:
                contractor = new Vendor(name, id, industry);
                break;
            case CUSTOMER:
                contractor = new EndCustomer(name, id, industry );
                break;
            case BROKER:
                contractor = new Broker(name, id, industry);
                break;
        }
        contractor.setInStoreAmount(amount);
        return contractor;
    }
}


//    class ContractorFactory {
//        public static IContractor create(JsonElement element) {
//            int id = element.getAsJsonObject().get("id").getAsInt();
//            int amount = element.getAsJsonObject().get("inStoreAmount").getAsInt();
//            String name = element.getAsJsonObject().get("name").getAsString();
//            String industry = element.getAsJsonObject().get("industry").getAsString();
//            IContractor contractor = null;
//            switch (IContractor.ContractorType.valueOf(element.getAsJsonObject()
//                    .get("contractorType").getAsString()
//                    .toUpperCase())) {
//                case VENDOR:
//                    contractor = new Vendor(name, id, industry);
//                    break;
//                case CUSTOMER:
//                    contractor = new EndCustomer(name, id, industry);
//                    break;
//                case BROKER:
//                    contractor = new Broker(name, id, industry);
//                    break;
//            }
//            contractor.setInStoreAmount(amount);
//            return contractor;
//        }
//    }


//        public void confirmTransaction(int transactionId, int amount) {
//            Transaction t = transactionRegister.get(transactionId);
//            t.setAmountOfGoods(amount);
//            t.getProvider().setPackagingMethod(new Packaging("items", Packaging.Container.CASE, 50));
//            t.confirm();
//            System.out.println("Transaction with id = " + transactionId + "is: " + t.getStatus());
//        }
//
//        public void rejectTransaction(int transactionId) {
//            Transaction t = transactionRegister.get(transactionId);
//            t.reject();
//            System.out.println("Transaction with id = " + transactionId + "is: " + t.getStatus());
//        }
//
//        public void initiateTransaction(int transactionId) {
//            Transaction t = transactionRegister.get(transactionId);
//            t.initiateTransaction();
//            System.out.println("Transaction with id = " + transactionId + "is: " + t.getStatus());
//        }
//
//        public void completeTransaction(int transactionId) {
//            Transaction t = transactionRegister.get(transactionId);
//            t.completeTransaction();
//            System.out.println("Transaction with id = " + transactionId + "is: " + t.getStatus());
//        }
//}


