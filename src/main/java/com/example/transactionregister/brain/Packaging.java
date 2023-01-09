package com.example.transactionregister.brain;

public class Packaging {

    public enum Container{
        CARDBOX,
        TRAY,
        CASE
    }

    private Container containerType;
    private String unitOfMeasure; //e.g. piece, item, pair

    public int getItemsPerContainer() {return itemsPerContainer;}
    private int itemsPerContainer;

    public Container getContainerType() {return containerType;}

    public String getUnitOfMeasure() {return unitOfMeasure;}

    public Packaging(String unitOfMeasure, Container containerType, int itemsPerContainer){
        this.containerType = containerType;
        this.unitOfMeasure = unitOfMeasure;
        this.itemsPerContainer = itemsPerContainer;
    }

    // ciag dalszy chyba
}
