package common.dao.map_items.items;

import common.dao.map_items.MapItem;

public class Adventurer extends MapItem implements Impassable{
    private int treasureFounded;
    private String actions;
    private String name;
    private char orientation;

    public Adventurer(String name, int x, int y, char orientation, String actions) {
        super(x, y);
        this.actions = actions;
        this.name = name;
        this.orientation = orientation;
        this.treasureFounded = 0;
    }


    @Override
    public String toString() {
        return "A - " + this.name + " - " + getX() + " - " + getY() + " - " + orientation + " - " + treasureFounded + '\n';
    }

    public String getActions() {
        return actions;
    }

    public void setActions(String actions) {
        this.actions = actions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getOrientation() {
        return orientation;
    }

    public void setOrientation(char orientation) {
        this.orientation = orientation;
    }

    public int getTreasureFounded() {
        return treasureFounded;
    }

    @Override
    public String getLabel() {
        return "A";
    }

    public void pickUpTreasure() {
        this.treasureFounded = this.treasureFounded + 1;
    }
}
