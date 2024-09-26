import java.util.List;
import java.util.ArrayList;

public class Word {
    private String name;
    private int line;
    private int column;
    private int lenght;
    private String direction;
    private List<int[]> charIndexs = new ArrayList<>();

    Word(String n, int l, int c, String d){
        name = n;
        line = l;
        column = c;
        direction = d;
        lenght = n.length();
        this.findCharIndexes();
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getLine() {
        return line;
    }
    public void setLine(int line) {
        this.line = line;
    }
    public int getColumn() {
        return column;
    }
    public void setColumn(int column) {
        this.column = column;
    }
    public String getDirection() {
        return direction;
    }
    public void setDirection(String direction) {
        this.direction = direction;
    }
    public List<int[]> getCharIndexs() {
        return charIndexs;
    }
    public void setCharIndexs(List<int[]> charIndexs) {
        this.charIndexs = charIndexs;
    }
    public String toString(){
        return String.format("%-15s %-7s %s,%-15s %s", this.name, this.lenght, this.line, this.column, this.direction);
    }
    public void findCharIndexes(){
        int i = line;
        int j = column;
        int iterations;

        switch (this.direction) {
            case "Up":
                for (iterations = 0; iterations < this.lenght; iterations++){
                    int[] indexes = {i, j};
                    this.charIndexs.add(indexes);
                    i--;
                }
                break;
                
            case "Down":
                for (iterations = 0; iterations < this.lenght; iterations++){
                    int[] indexes = {i, j};
                    this.charIndexs.add(indexes);
                    i++;
                }
                break;
            case "Left":
                for (iterations = 0; iterations < this.lenght; iterations++){
                    int[] indexes = {i, j};
                    this.charIndexs.add(indexes);
                    j--;
                }
                break;
            case "Right":
                for (iterations = 0; iterations < this.lenght; iterations++){
                    int[] indexes = {i, j};
                    this.charIndexs.add(indexes);
                    j++;
                }
                break;
            case "UpLeft":
                for (iterations = 0; iterations < this.lenght; iterations++){
                    int[] indexes = {i, j};
                    this.charIndexs.add(indexes);
                    i--;
                    j--;
                }
                break;
            case "UpRight":
                for (iterations = 0; iterations < this.lenght; iterations++){
                    int[] indexes = {i, j};
                    this.charIndexs.add(indexes);
                    i--;
                    j++;
                }
                break;
            case "DownLeft":
                for (iterations = 0; iterations < this.lenght; iterations++){
                    int[] indexes = {i, j};
                    this.charIndexs.add(indexes);
                    i++;
                    j--;
                }
                break;
            case "DownRight":
                for (iterations = 0; iterations < this.lenght; iterations++){
                    int[] indexes = {i, j};
                    this.charIndexs.add(indexes);
                    i++;
                    j++;
                }
                break;
            
            
        }   
    }
}
