package pl.awkwieicn.testing;

class Account {

    private boolean active;
    private Address defaultDeliveryAddress;

    Account() {
        this.active = false;
    }

    public Account(Address defaultDeliveryAddress) {
        this.defaultDeliveryAddress = defaultDeliveryAddress;
        if (defaultDeliveryAddress!=null)
            activate();
        else
            this.active = false;
    }

    void activate() {
        this.active=true;
    }

    boolean isActive() {
        return this.active;
    }

    Address getDefaultDeliveryAddress() {
        return defaultDeliveryAddress;
    }

    void setDefaultDeliveryAddress(Address defaultDeliveryAddress) {
        this.defaultDeliveryAddress = defaultDeliveryAddress;
    }
}
