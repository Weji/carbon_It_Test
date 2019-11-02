package common;

import common.dao.Map;
import common.dao.map_items.MapItem;
import common.dao.map_items.items.Adventurer;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Orchestrator {
    private static final String PATH = "input2.txt";
    private MapManager mapManager;

    public Orchestrator(MapManager mapManager) {
        this.mapManager = mapManager;
    }

    public Map initialize() throws Exception {
        Path file_path = Paths.get(PATH);

        Map map = mapManager.build(Files.readAllLines(file_path));
        //mapManager.printMap();

        return map;
    }

    public void evaluate() throws Exception {
        ArrayList<Adventurer> adventurers = mapManager.getAdventurerList();
        int j = 0;
        int finished = 0;
        mapManager.printMap();

        while(finished != adventurers.size()){
            System.out.println("\n" + "turn " + (j++));
            finished = 0;

            for (Adventurer adventurer: adventurers){
                if(adventurer.getActions().length() > 0){

                    char action = adventurer.getActions().charAt(0);
                    System.out.println(adventurer.getName() + " " + adventurer.getActions().charAt(0));

                    if(action == 'A'){
                        mapManager.updateAdventurerPosition(adventurer);
                    } else {
                        mapManager.updateOrientation(adventurer, action);
                    }

                    mapManager.updateMap();
                    adventurer.setActions(adventurer.getActions().substring(1));
                } else {
                    finished++;
                }
            }
            mapManager.printMap();
        }

        writeOutPut(mapManager.getMapItemsList());
    }

    private void writeOutPut(ArrayList<MapItem> mapItemsList) throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter writer = new PrintWriter("output_" + PATH, "UTF-8");

        StringBuilder res = new StringBuilder("");
        for (MapItem mapItem: mapItemsList) {
            res.append(mapItem.toString());
        }

        writer.println(res);
        writer.close();

        System.out.println(res.toString());


    }

}
