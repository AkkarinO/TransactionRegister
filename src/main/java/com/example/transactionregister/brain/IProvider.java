package com.example.transactionregister.brain;

public interface IProvider extends IContractor {

    boolean hasEnoughGoods(int amount);

    void dispatchGoods(int amount);

    void setPackagingMethod(Packaging packagingMethod);

    // wszystko
}
