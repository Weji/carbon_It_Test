package common.dao.map_items.items;

import common.dao.map_items.MapItem;

public class SimpleSlot extends MapItem {
    public SimpleSlot(int x, int y) {
        super(x, y);
    }

    @Override
    public String getLabel() {
        return ".";
    }
}
