package common;

import common.dao.Map;
import common.dao.map_items.MapItem;
import common.dao.map_items.items.*;

import java.util.ArrayList;
import java.util.List;

public class MapManager {

    private LineReader lineReader;
    private Map map;
    private ArrayList<MapItem> mapItemsList;

    public MapManager(LineReader lineReader) {
        this.lineReader = lineReader;
        this.mapItemsList = new ArrayList<>();
    }

    public ArrayList<MapItem> getMapItemsList() {
        return mapItemsList;
    }

    protected Map build(List<String> lines){
        this.map = new Map();

        for (String line : lines) {
            System.out.println(line);
            String [] params = lineReader.getParams(line);
            switch (params[0].charAt(0)){
                case 'C':
                    map.setWidth(Integer.parseInt(params[1]));
                    map.setHeight(Integer.parseInt(params[2]));
                    map.initializeMapItemArray();
                    break;
                case 'A':
                    mapItemsList.add(new Adventurer(params[1],
                            Integer.parseInt(params[2]), Integer.parseInt(params[3]),
                            params[4].charAt(0), params[5]));
                    break;
                case 'T':
                    mapItemsList.add(new Treasure(Integer.parseInt(params[1]),
                            Integer.parseInt(params[2]), Integer.parseInt(params[3].trim())));
                    break;
                case 'M':
                    mapItemsList.add(new Mountain(Integer.parseInt(params[1]), Integer.parseInt(params[2])));
                    break;
            }
        }

        setMapItems();
        return map;
    }

    private void setMapItems(){
        for (int i = 0; i < getMap().getWidth(); i++) {
            for (int j = 0; j < getMap().getHeight(); j++) {
                map.attachItemToMap(new SimpleSlot(i, j));
            }
        }

        for (int i = 0; i < mapItemsList.size(); i++) {
            map.attachItemToMap(mapItemsList.get(i));
        }
    }

    public Map getMap() {
        return map;
    }

    public void printMap(){
        MapItem[][] items = map.getContent();
        StringBuilder res = new StringBuilder("\n\n");


        for (int x = 0; x <= map.getHeight(); x++) {
            res.append("---");
        }

        for (int i = 0; i <  map.getHeight(); i++) {
            res.append("\n| ");


            for (int j = 0; j < map.getWidth(); j++) {
                MapItem mapItem = items[j][i];

                if(mapItem instanceof Treasure){
                    if((((Treasure) mapItem).getStock() > 0)){
                        res.append(mapItem.getLabel());
                        res.append(" | ");
                    } else {
                        res.append(".");
                        res.append(" | ");
                    }
                } else {
                    res.append(mapItem.getLabel());
                    res.append(" | ");
                }
            }
            res.append("\n");
        }

        for (int x = 0; x <= map.getHeight(); x++) {
            res.append("---");
        }

        System.out.println(res.toString());
    }


    public ArrayList<Adventurer> getAdventurerList() {
        ArrayList<Adventurer> adventurers = new ArrayList<>();
        for(MapItem mapItem : mapItemsList){
            if(mapItem instanceof Adventurer)
                adventurers.add((Adventurer) mapItem);
        }

        return adventurers;
    }

    private void resetMapContent(){
        MapItem[][] mapItems = map.getContent();
        for (int i = 0; i < mapItems.length; i++) {
            for (int j = 0; j < mapItems[0].length ; j++) {
                mapItems[i][j] = new SimpleSlot(i, j);
            }
        }
    }

    public void updateAdventurerPosition(Adventurer adventurer) throws Exception {
        char orientation = adventurer.getOrientation();
        MapItem[][] mapContent = map.getContent();
        int x = adventurer.getX();
        int y = adventurer.getY();
        int newX = 0;
        int newY = 0;

        switch (orientation){
            case 'N':
                newX = x;
                newY = y - 1;
                break;
            case 'E':
                newX = x + 1;
                newY = y;
                break;
            case 'S':
                newX = x;
                newY = y + 1;
                break;
            case 'O':
                newX = x - 1;
                newY = y;
                break;
            default:
                throw new Exception("Invalid orientation Token");
        }

        if(isInTheMap(newX, newY)){
            if(!(mapContent[newX][newY] instanceof Impassable)){
                adventurer.setX(newX);
                adventurer.setY(newY);

                if(mapContent[newX][newY] instanceof Treasure){
                    for (MapItem item: mapItemsList) {
                        if((item.getX() == newX) && (item.getY() == newY) && !(item instanceof Adventurer)){
                            Treasure treasure = (Treasure) item;
                            if(treasure.getStock() > 0){
                                treasure.setStock(treasure.getStock() - 1);
                                adventurer.pickUpTreasure();
                            }
                        }
                    }
                }
            }
        }
    }

    public boolean isInTheMap(int x, int y){
        if(x >= 0 && x < map.getWidth()){
            if(y >= 0 && y < map.getHeight()){
                return true;
            }
        }

        return false;
    }

    public Map updateMap() {
        resetMapContent();

        MapItem[][] mapContent = map.getContent();
        for (MapItem mapItem : mapItemsList) {
            if(mapItem instanceof Treasure){
                if(((Treasure) mapItem).getStock() > 0){
                    mapContent[mapItem.getX()][mapItem.getY()] = mapItem;
                }
            } else {
                mapContent[mapItem.getX()][mapItem.getY()] = mapItem;
            }
        }

        return map;
    }

    public void updateOrientation(Adventurer adventurer, char action) {

        if(action == 'D'){
            if(adventurer.getOrientation() == 'N'){
                adventurer.setOrientation('E');
            } else if(adventurer.getOrientation() == 'E'){
                adventurer.setOrientation('S');
            } else if(adventurer.getOrientation() == 'S'){
                adventurer.setOrientation('O');
            } else if(adventurer.getOrientation() == 'O'){
                adventurer.setOrientation('N');
            }
        }

        if(action == 'G'){
            if(adventurer.getOrientation() == 'N'){
                adventurer.setOrientation('O');
            } else if(adventurer.getOrientation() == 'E'){
                adventurer.setOrientation('N');
            } else if(adventurer.getOrientation() == 'S'){
                adventurer.setOrientation('E');
            } else if(adventurer.getOrientation() == 'O'){
                adventurer.setOrientation('S');
            }
        }
    }
}
