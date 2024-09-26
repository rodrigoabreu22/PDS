import java.io.*;
import java.util.*;

public class WSGenerator {
    private char[][] board;
    private char[][] boardCopy;
    private List<String> words = new ArrayList<>();
    private List<String> inputFileLines = new ArrayList<>();

    public WSGenerator(String inputFile, int size, String outputFile) throws IOException {
        
        board = new char[size][size];
        boardCopy = new char[size][size];
        Random random = new Random();

        for (char[] row : board) {
            for (int i = 0; i < row.length; i++) {
            row[i] = (char) ('A' + random.nextInt(26));
            }
        }

        for (char row[] : boardCopy) {
            for (int i = 0; i < row.length; i++) {
            row[i] = (char) ('0');
            }
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                inputFileLines.add(line);
            }
        }
        
        words = linesToWords(inputFileLines);

        

        placeWords(words, board, boardCopy, size);
        //System.out.println(board[0][0]);

        if (outputFile != null) {
            
        
            try (PrintWriter writer = new PrintWriter(new FileWriter(outputFile))) {
                for (char[] row : board) {
                    for (char c : row) {
                        writer.print(c);
                    }
                    writer.println();
                }
                for (String word : words) {
                    writer.print(word);
                    writer.print(";");
                }

            }

        }
        else{
            for (char[] row : board) {
                for (char c : row) {
                    System.out.print(c);
                }
                System.out.println();
            }
            for (String word : words) {
                System.out.print(word);
                System.out.print(";");
            }
        }

    }

    public static void placeWords(List<String> words, char[][] board, char[][] boardCopy, int size) {
        Random rand = new Random();

        for (String word : words){
            int restarter = 0;
            Boolean placed = false;
            Boolean available = true;
            while(placed == false){
                restarter++;
                if (restarter > 1000){
                    break;
                }
                Double prob = rand.nextDouble();
                String direcao_string = "";
                if (prob < 0.20) {
                    direcao_string = "right";
                } else if (prob < 0.40) {
                    direcao_string = "left";
                } else if (prob < 0.60) {
                    direcao_string = "down";
                } else if (prob < 0.80) {
                    direcao_string = "up";
                } else if (prob < 0.85) {
                    direcao_string = "downright";
                } else if (prob < 0.90) {
                    direcao_string = "downleft";
                } else if (prob < 0.95) {
                    direcao_string = "upright";
                } else {
                    direcao_string = "upleft";
                }
                int x = rand.nextInt(size);
                int y = rand.nextInt(size);
                switch (direcao_string) {
                    case "right": //righ
                        if (y + word.length() <= size){
                            for (int i = 0; i < word.length(); i++){
                                if (boardCopy[x][y+i] != '0'){
                                    available = false;
                                }
                            }
                            if (available == true){
                                for (int i = 0; i < word.length(); i++){
                                    board[x][y+i] = Character.toUpperCase(word.charAt(i));
                                    boardCopy[x][y+i] = '1';
                                }
                                placed = true;
                            }
                        }
                        break;
                    case "left": //left
                        if (y - word.length() + 1 >= 0){
                            for (int i = 0; i < word.length(); i++){
                                if (boardCopy[x][y-i] != '0'){
                                    available = false;
                                }
                            }
                            if (available == true){
                                for (int i = 0; i < word.length(); i++){
                                    board[x][y-i] = Character.toUpperCase(word.charAt(i));
                                    boardCopy[x][y-i] = '1';
                                }
                                placed = true;
                            }
                        }
                        break;
                    case "down": //down
                        if (x + word.length() <= size){
                            for (int i = 0; i < word.length(); i++){
                                if (boardCopy[x+i][y] != '0'){
                                    available = false;
                                }
                            }
                            if (available == true){
                                for (int i = 0; i < word.length(); i++){
                                    board[x+i][y] = Character.toUpperCase(word.charAt(i));
                                    boardCopy[x+i][y] = '1';
                                }
                                placed = true;
                            }
                        }
                        break;
                    case "up": //up
                        if (x - word.length() + 1 >= 0){
                            for (int i = 0; i < word.length(); i++){
                                if (boardCopy[x-i][y] != '0'){
                                    available = false;
                                }
                            }
                            if (available == true){
                                for (int i = 0; i < word.length(); i++){
                                    board[x-i][y] = Character.toUpperCase(word.charAt(i));
                                    boardCopy[x-i][y] = '1';
                                }
                                placed = true;
                            }
                        }
                        break;
                    case "downright": //downright
                        if (x + word.length() <= size && y + word.length() <= size){
                            for (int i = 0; i < word.length(); i++){
                                if (boardCopy[x+i][y+i] != '0'){
                                    available = false;
                                }
                            }
                            if (available == true){
                                for (int i = 0; i < word.length(); i++){
                                    board[x+i][y+i] = Character.toUpperCase(word.charAt(i));
                                    boardCopy[x+i][y+i] = '1';
                                }
                                placed = true;
                            }
                        }
                        break;
                    case "downleft": //downleft
                        if (x + word.length() <= size && y - word.length() + 1 >= 0){
                            for (int i = 0; i < word.length(); i++){
                                if (boardCopy[x+i][y-i] != '0'){
                                    available = false;
                                }
                            }
                            if (available == true){
                                for (int i = 0; i < word.length(); i++){
                                    board[x+i][y-i] = Character.toUpperCase(word.charAt(i));
                                    boardCopy[x+i][y-i] = '1';
                                }
                                placed = true;
                            }
                        }
                        break;
                    case "upright": //upright
                        if (x - word.length() + 1 >= 0 && y + word.length() <= size){
                            for (int i = 0; i < word.length(); i++){
                                if (boardCopy[x-i][y+i] != '0'){
                                    available = false;
                                }
                            }
                            if (available == true){
                                for (int i = 0; i < word.length(); i++){
                                    board[x-i][y+i] = Character.toUpperCase(word.charAt(i));
                                    boardCopy[x-i][y+i] = '1';
                                }
                                placed = true;
                            }
                        }
                        break;
                    case "upleft": //upleft
                        if (x - word.length() + 1 >= 0 && y - word.length() + 1 >= 0){
                            for (int i = 0; i < word.length(); i++){
                                if (boardCopy[x-i][y-i] != '0'){
                                    available = false;
                                }
                            }
                            if (available == true){
                                for (int i = 0; i < word.length(); i++){
                                    board[x-i][y-i] = Character.toUpperCase(word.charAt(i));
                                    boardCopy[x-i][y-i] = '1';
                                }
                                placed = true;
                            }
                        }
                        break;
                    default:
                        break;
                }
                
            }
            if (restarter > 1000){

                System.out.println("Starting over");
                //reset bord copy
                for (char row[] : boardCopy) {
                    for (int i = 0; i < row.length; i++) {
                    row[i] = (char) ('0');
                    }
                }
                //reset board
                for (char[] row : board) {
                    for (int i = 0; i < row.length; i++) {
                    row[i] = (char) ('A' + rand.nextInt(26));
                    }
                }
                
                placeWords(words, board, boardCopy, size);
            }

        }
        
    }

    public static List<String> linesToWords(List<String> lines) {
        List<String> palavras = new ArrayList<String>();
        for (String line : lines){
            String[] splitWords = line.split("[, ;]");
            palavras.addAll(Arrays.asList(splitWords));

        }
        return palavras;

    }


    public static void main(String[] args) throws IOException {
        
        System.out.println("Usage: java WSGenerator -i <inputfile> -s <size> -o <outputfile>");
        String inputFile = null;
        int size = 0;
        String outputFile = null;

        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-i":
                    i++;
                    if (i < args.length) {
                        inputFile = args[i];
                    } else {
                        System.out.println("Missing argument for -i");
                        return;
                    }
                    break;
                case "-s":
                    i++;
                    if (i < args.length) {
                        try {
                            size = Integer.parseInt(args[i]);
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid number for -s");
                            return;
                        }
                    } else {
                        System.out.println("Missing argument for -s");
                        return;
                    }
                    break;
                case "-o":
                    i++;
                    if (i < args.length) {
                        outputFile = args[i];
                    } else {
                        System.out.println("Missing argument for -o");
                        return;
                    }
                    break;
                default:
                    System.out.println("Unknown option: " + args[i]);
                    return;
            }
        }
                
        new WSGenerator(inputFile, size, outputFile);
        
    }
}