package com.example.transactionregister.brain;

import java.util.Random;

public class Transaction {

    public enum Status{
        PRE_CONFIRMED,
        CONFIRMED,
        IN_PROGRESS,
        COMPLETED,
        REJECTED
    }

    private Status status = Status.PRE_CONFIRMED;

    private ICustomer getCustomer(){return customer;}

    private ICustomer customer;

    public IProvider getProvider(){return provider;}

    private IProvider provider;
    private int amountOfGoods;

    public Transaction(ICustomer customer, IProvider provider){
        this.customer = customer;
        this.provider = provider;
    }

    public Transaction(int quantity, ICustomer customer, IProvider provider){
        this(customer, provider);
        this.setAmountOfGoods(quantity);
    }

    public void confirm() {status = Status.CONFIRMED;}

    public void reject() {status = Status.REJECTED;}

    public int getAmountOfGoods(){return amountOfGoods;}

    public void setAmountOfGoods(int amountOfGoods){this.amountOfGoods = amountOfGoods;}

    public Status getStatus(){return status;}

    public void initiateTransaction(){
        if (status == Status.CONFIRMED){
            provider.dispatchGoods(getAmountOfGoods());
            status = Status.IN_PROGRESS;
        }
    }

    public void completeTransaction(){
        customer.receiveGoods(amountOfGoods);
        status = Status.COMPLETED;
    }

    // ciag dalszy ?
}
