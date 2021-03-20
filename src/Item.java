/**
 *  Class representing basic functionality of an item
 *
 *  @author Zhi Wei Chen
 *  @version 1.0
 */

public class Item {
    private String name;
    private int sellPrice;
    private int buyPrice;
    private int quantity;

    public Item(String name, int sellPrice, int buyPrice, int quantity) {
        this.name = name;
        this.sellPrice = sellPrice;
        this.buyPrice = buyPrice;
        this.quantity = quantity;
    }

    public String toString() {
        return name + " - Buy Price:" + buyPrice + ", Sell Price:" + sellPrice
                + ", Quantity:" + quantity;
    }

    public String getName() {
        return this.name;
    }

    public int getSellPrice() {
        return this.sellPrice;
    }

    public int getBuyPrice() {
        return this.buyPrice;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSellPrice(int sellPrice) {
        this.sellPrice = sellPrice;
    }

    public void setBuyPrice(int buyPrice) {
        this.buyPrice = buyPrice;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
