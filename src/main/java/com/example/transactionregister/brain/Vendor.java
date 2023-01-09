package com.example.transactionregister.brain;

public class Vendor implements IProvider{

    private String name;
    private int id;
    private String industry;
    private int inStoreAmount = 0;

    private Packaging packagingMethod;

    public Vendor(String name, int id, String industry){
        this.name = name;
        this.id = id;
        this.industry = industry;
    }
    // cos dalej
    // EDIT: chyba wszytsko ale co z klasą Packaging
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
    public void receiveGoods(int amount) {

    }

    @Override
    public void distributeToShops() {

    }

    @Override
    public void setInStoreAmount(int inStoreAmount) {
        this.inStoreAmount = inStoreAmount;
    }

    @Override
    public ContractorType getType() {
        return ContractorType.VENDOR;
    }

    @Override
    public boolean hasEnoughGoods(int amount) {
        return amount <= getInStoreAmount();
    }

    public Packaging getPackagingMethod(){return packagingMethod;}

    @Override
    public void dispatchGoods(int amount) {
        System.out.println("Dispatching goods by" + Vendor.class.getSimpleName() + ": " + getName());
        setInStoreAmount(getInStoreAmount()-amount);
        makeFreight(amount);
    }

    @Override
    public void setPackagingMethod(Packaging packagingMethod) {
        this.packagingMethod = packagingMethod;
    }

    private void makeFreight(int amountOfGoods){
        Packaging.Container containerType = packagingMethod.getContainerType();
        int containerCapacity = packagingMethod.getItemsPerContainer();
        int numberOfContainers = (int) Math.ceil((double) amountOfGoods / containerCapacity);
        System.out.println("Provider " + getName()
                + "is distributing" + amountOfGoods + "goods in " + numberOfContainers + "containers (" + containerType + ")");
    }
    // cos jeszcze?
    //EDIT: chyba nie
}
