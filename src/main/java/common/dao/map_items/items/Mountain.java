package common.dao.map_items.items;

import common.dao.map_items.MapItem;

public class Mountain extends MapItem implements Impassable{
    public Mountain(int x, int y) {
        super(x, y);
    }

    @Override
    public String getLabel() {
        return "M";
    }

    @Override
    public String toString() {
        return "M - " + getX() + " - " + getY() + '\n';
    }
}
