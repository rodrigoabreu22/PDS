package lab01.ex01.src;
import java.util.Scanner;

import lab01.ex02.src.Way;

import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

class WordSearchSolver {
    public static void main(String[] args) throws IOException {

        ArrayList<String> words2find = new ArrayList<>();
        List<String> soup = new ArrayList<String>();
        final int MAXIMUM_SIZE = 40;

        File file = null;
        String file_name = args[0];
        int size = 0;

        try {
            file = new File(file_name);

            //verificar o size da sopa de letras
            Scanner sc = new Scanner(file);
            size = sc.nextLine().length();
            sc.close();
        } 
        catch (Exception erro) {
            System.out.println("File not valid");
            System.exit(1);
        }

        if (size > MAXIMUM_SIZE) {
            System.out.println("Maximum size equals 40");
            System.exit(1);
        }
        
        fileReader(file, words2find, soup, size);

        Character[][] soup_grid = soupGrid(soup,size);

        Boolean[][] soup_flags = soupFlags(size);

        //resolver a sopa de letra e escreve resultado no ficheiro de output
        soupSolver(words2find, soup_grid, size, soup_flags, file_name);
    }

    public static void fileReader(File file, ArrayList<String> words2find, List<String> soup, int size) throws FileNotFoundException{ 
        try{
            Scanner sc = new Scanner(file);
            String line;
            int counter = 0;

            while (sc.hasNextLine()){
                line = sc.nextLine();

                if (!soupWordVerifier(line)){
                    break;
                }
                soup.add(line.toLowerCase());
                counter++;
            }
            SoupSizeVerifier(counter,size);

            sc.close();

            Scanner sc2 = new Scanner(file);

            //ignorar as linhas que contêm a sopa de letras
            for (int i=0; i<size; i++){
                sc2.nextLine();
            }

            while(sc2.hasNextLine()){
                String line2 = sc2.nextLine();
                String[] sol = line2.split("[;, ]");

                for (int i=0; i<sol.length; i++){

                    String word = sol[i].toLowerCase();
                    words2find.add(word);
                }
            }
            sc2.close();
        } catch (FileNotFoundException e) {
            System.out.println("Erro");
            System.exit(1);
        }

    }

    public static Character[][] soupGrid(List<String> soup, int size) {
        Character[][] soup_grid = new Character[size][size];
        for (int i=0; i<size; i++){
            String word = soup.get(i);
            for (int j=0; j<size; j++){
                soup_grid[i][j] = Character.toUpperCase(word.charAt(j));
            }
        }
        return soup_grid;
    }

    public static Boolean[][] soupFlags(int size){
        Boolean[][] flags_grid = new Boolean[size][size];
        for (int i=0; i<size; i++){
            for (int j=0; j<size; j++){
                flags_grid[i][j] = false;
            }
        }
        return flags_grid;
    }

    public static boolean soupWordVerifier(String line) {
        if (line.isEmpty()) {
            return false;
        }
        for (int i=0; i<line.length(); i++) {
            char x = line.charAt(i);
            if (!Character.isLetter(x) && Character.isLowerCase(x)) {
                return false;
            }
        }
        return true;
    }

    public static void SoupSizeVerifier(int counter, int size){
        if (counter==size){
            error("A sopa de letras tem de ser quadrada!");
        }
    }

    //funcao que verifica se a palavra está na sopa de letras
    public static void soupSolver(ArrayList<String> words2find, Character[][] soup_grid, int size, Boolean[][] soup_flags, String file){
        //remover a extensão do ficheiro
        String save_file = file.replace(".txt", "");

        try {
            FileWriter writer = new FileWriter(save_file + "_output.txt");

            for (String word : words2find) {
                //cada palavra só pode ser encontrada uma vez
                boolean word_found = false;

                for (int i=0; i<size; i++) {
                    for (int j=0; j<size; j++) {
 
                        //verificar a orientação da palavra
                        Way way = checkSolutionOrientation(i, j, word.toUpperCase(), soup_grid, size, soup_flags);
        
                        if (way != null && !word_found) {
                            //palavra encontrada
                            word_found = true;
                            
                            //marcar a palavra na sopa de letras
                            soup_flags = checkFlagGrid(i, j, word.length(), way, soup_flags);

                            //escrever a palavra no ficheiro de output
                            String output = String.format("%-20s %-5d %-8s %-10s\n", word, word.length(), (i+1) + "," + (j+1), way.toString()); 
                            writer.write(output);
                            break;
                        }
                    }
                }
            }
            writer.write("\n");

            //eliminar as letras que não pertencem a nenhuma palavra
            for (int i=0; i<size; i++) {
                for (int j=0; j<size; j++) {

                    if (!soup_flags[i][j]) {
                        soup_grid[i][j] = '.';
                    }
                }
            }

            //escrever a sopa de letras no ficheiro de output
            for (int i=0; i<size; i++) {
                for (int j=0; j<size; j++) {
                    writer.write(soup_grid[i][j] + " ");
                }
                writer.write("\n");
            }

            writer.close();
        }
        catch (IOException e) {
            System.out.println("Erro " + e);
            System.exit(1);
        }
    }

    public static Boolean[][] checkFlagGrid(int i, int j, int size, Way orientation, Boolean[][] soup_flags) {
        int incrementer_row=0;
        int incrementer_col=0;

        switch (orientation){
            case Up:
                incrementer_row=-1;
                break;
            case Down:
                incrementer_row=1;
                break;
            case Left:
                incrementer_col=-1;
                break;
            case Right:
                incrementer_col=1;
                break;
            case UpLeft:
                incrementer_row=-1;
                incrementer_col=-1;
                break;
            case UpRight:
                incrementer_row=-1;
                incrementer_col=1;
                break;
            case DownLeft:
                incrementer_row=1;
                incrementer_col=-1;
                break;
            case DownRight:
                incrementer_row=1;
                incrementer_col=1;
                break;
            default:
                error("Unexpected error.");
                break;    
        }

        //marcar a palavra na sopa de letras
        for (int k=0; k<size;k++) {
            soup_flags[i][j] = true;
            i += incrementer_row;
            j += incrementer_col;
        }

        return soup_flags;
    }
    
    //funcao que verifica a orientação da palavra (linhas -> i, colunas -> j)
    public static Way checkSolutionOrientation(int i, int j, String word, Character[][] soup_grid, int size, Boolean[][] soup_flags){
        Way way = null;

        //verificar RIGTH
        if (j + word.length()-1 < size ) {
            boolean found = true;
            for (int k=0; k<word.length(); k++) {
                if (soup_grid[i][j+k] != word.charAt(k) || soup_flags[i][j+k]) {
                    found = false;
                    break;
                }
            }
            if (found) {
                way = Way.Right;
                return way;
            }
        }
        
        //verificar LEFT
        if (j - word.length()+1 >= 0) {
            boolean found = true;
            for (int k=0; k<word.length(); k++) {
                if (soup_grid[i][j-k] != word.charAt(k) || soup_flags[i][j-k]) {
                    found = false;
                    break;
                }
            }
            if (found) {
                way = Way.Left;
                return way;
            }
        }

        //verificar DOWN
        if (i + word.length()-1 < size) {
            boolean found = true;
            for (int k=0; k<word.length(); k++) {
                if (soup_grid[i+k][j] != word.charAt(k) || soup_flags[i+k][j]) {
                    found = false;
                    break;
                }
            }
            if (found) {
                way = Way.Down;
                return way;
            }
        }

        //verificar UP
        if (i - word.length()+1 >= 0) {
            boolean found = true;
            for (int k=0; k<word.length(); k++) {
                if (soup_grid[i-k][j] != word.charAt(k) || soup_flags[i-k][j]) {
                    found = false;
                    break;
                }
            }
            if (found) {
                way = Way.Up;
                return way;
            }
        }

        //verificar DOWNRIGHT
        if (i + word.length()-1 < size && j + word.length()-1 < size) {
            boolean found = true;
            for (int k=0; k<word.length(); k++) {
                if (soup_grid[i+k][j+k] != word.charAt(k) || soup_flags[i+k][j+k]) {
                    found = false;
                    break;
                }
            }
            if (found) {
                way = Way.DownRight;
                return way;
            }
        }

        //verificar DOWNLEFT
        if (i + word.length()-1 < size && j - word.length()-1 >= 0) {
            boolean found = true;
            for (int k=0; k<word.length(); k++) {
                if (soup_grid[i+k][j-k] != word.charAt(k) || soup_flags[i+k][j-k]) {
                    found = false;
                    break;
                }
            }
            if (found) {
                way = Way.DownLeft;
                return way;
            }
        }

        //verificar UPRIGH
        if (i - word.length()+1 >= 0 && j + word.length()-1 < size) {
            boolean found = true;
            for (int k=0; k<word.length(); k++) {
                if (soup_grid[i-k][j+k] != word.charAt(k) || soup_flags[i-k][j+k]) {
                    found = false;
                    break;
                }
            }
            if (found) {
                way = Way.UpRight;
                return way;
            }
        }

        //verificar UPLEFT
        if (i - word.length()+1 >= 0 && j - word.length()-1 >= 0) {
            boolean found = true;
            for (int k=0; k<word.length(); k++) {
                if (soup_grid[i-k][j-k] != word.charAt(k) || soup_flags[i-k][j-k]) {
                    found = false;
                    break;
                }
            }
            if (found) {
                way = Way.UpLeft;
                return way;
            }
        }

        return way;
    }

    public static void error(String str) {
		System.err.println("ERROR " + str);
		System.exit(1);
	}
}