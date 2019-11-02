package common;

public class LineReader {
    public String[] getParams(String line){

        return line.split(" - ");
    }
}
