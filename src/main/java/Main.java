import common.LineReader;
import common.MapManager;
import common.Orchestrator;

import java.io.IOException;

public class Main {



    public static void main(String[] args) throws Exception {
        Orchestrator orchestrator = new Orchestrator(new MapManager(new LineReader()));

        orchestrator.initialize();
        orchestrator.evaluate();
        //fileReader.readAllLines();
    }
}