package com.example.transactionregister.brain;

public class EndCustomer implements ICustomer {
    private String name;
    private int id;
    private String industry;
    private int inStoreAmount = 0;

    public EndCustomer(String name, int id, String industry){
        this.name = name;
        this.id = id;
        this.industry = industry;
    }
    //ciag dalszy jeszcze troche
    //EDIT: juz chyba wszystko, ale co z klasa EndCustomer?

    @Override
    public String getName(){return name;}

    @Override
    public int getId(){return id;}

    @Override
    public String getIndustry(){return industry;}

    @Override
    public int getInStoreAmount(){return inStoreAmount;}
    public void setName(String name){this.name = name;}
    public void setIndustry(String industry){this.industry = industry;}

    @Override
    public void dispatch() {

    }

    @Override
    public void makeOffer() {
        System.out.println(
                "I want to sell " + getIndustry());
    }

    @Override
    public void setInStoreAmount(int inStoreAmount) {
        this.inStoreAmount = inStoreAmount;
    }

    @Override
    public ContractorType getType() {
        return ContractorType.CUSTOMER;
    }

    @Override
    public void receiveGoods(int amount) {

    }

    @Override
    public void distributeToShops() {

    }
}

