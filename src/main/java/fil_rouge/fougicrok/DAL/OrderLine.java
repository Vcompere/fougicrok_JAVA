package fil_rouge.fougicrok.DAL;

public class OrderLine
{
    int id;
    int prod_id;
    double uPrice;
    double tPrice;
    int quantity;
    int ordId;
    Products prod;

    public OrderLine() {}

    public OrderLine(int id, int prod_id, int quantity, double up, double tp) {
        this.id = id;
        this.prod_id = prod_id;
        this.quantity = quantity;
        this.uPrice = up;
        this.tPrice = tp;
    }

    public OrderLine(int prod_id, double uPrice, int quantity, Products prod) {
        this.prod_id = prod_id;
        this.uPrice = uPrice;
        this.quantity = quantity;
        this.tPrice = uPrice * quantity;
        this.prod = prod;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProd_id() {
        return prod_id;
    }

    public void setProd_id(int prod_id) {
        this.prod_id = prod_id;
    }

    public double getuPrice() {
        return uPrice;
    }

    public void setuPrice(double uPrice) {
        this.uPrice = uPrice;
    }

    public double gettPrice() {
        return tPrice;
    }

    public void settPrice(double tPrice) {
        this.tPrice = tPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getOrdId() {
        return ordId;
    }

    public void setOrdId(int ordId) {
        this.ordId = ordId;
    }

    public Products getProd() {
        return prod;
    }

    public void setProd(Products prod) {
        this.prod = prod;
    }

    @Override
    public String toString()
    {
        return "x"+quantity+" • "+prod.getRef()+" • "+prod.getLabel()+" • "+tPrice+"€ ("+uPrice+")";
    }
}
