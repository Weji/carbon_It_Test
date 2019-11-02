package common.dao.map_items.items;

import common.dao.map_items.MapItem;

public class Treasure extends MapItem {

    private int stock;

    public Treasure(int x, int y, int number_treasure) {
        super(x, y);
        this.stock = number_treasure;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String getLabel() {
        return "T";
    }

    @Override
    public String toString() {
        if(stock > 0)
            return "T - " + getX() + " - " + getY() + " - " + getStock() + '\n';
        else
            return "";
    }
}
