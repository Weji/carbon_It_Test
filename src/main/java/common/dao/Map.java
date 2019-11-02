package common.dao;

import common.dao.map_items.MapItem;

public class Map{
    private int height;
    private int width;
    private MapItem[][] content;

    public Map() {
    }

    @Override
    public String toString() {
        return "C - " + getWidth() + " - " + getHeight();
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public void initializeMapItemArray(){
        this.content = new MapItem[getWidth()][getHeight()];
    }
    public void attachItemToMap(MapItem item){
        try {
            content[item.getX()][item.getY()] = item;
        } catch (NullPointerException e){
            throw new NullPointerException("MapItem array isnt initialized");
        }
    }

    public void setContent(MapItem[][] content) {
        this.content = content;
    }

    public MapItem[][] getContent() {
        return content;
    }
}
