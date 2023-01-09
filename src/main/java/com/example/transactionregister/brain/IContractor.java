package com.example.transactionregister.brain;

public interface IContractor {

    enum ContractorType{
        CUSTOMER,
        VENDOR,
        BROKER
    }

    String getName();

    int getId();

    String getIndustry();

    int getInStoreAmount();

    void dispatch();

    void makeOffer();

    void receiveGoods(int amount);

    void distributeToShops();

    void setInStoreAmount(int inStoreAmount);

    ContractorType getType();
}

// wszystko
