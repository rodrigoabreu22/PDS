package lab02;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;


public class WordSearchSolver {
    public static void main(String[] args) {
        File file = null;
        String file_name = args[0];
        file = new File(file_name);

        int size = VerifySizeByFirstLine(file);
        Soup soup = new Soup(size);
        soup.verifySize(size);
        FileReader(file, soup);
        soupSolver(soup);
        System.out.println(soup.WsSolver_output());
        generateOutput(soup.WsSolver_output(), file_name);
    }

    public static int VerifySizeByFirstLine(File file){
        int size=0;
        try {
            Scanner sc = new Scanner(file);
            size = sc.nextLine().length();
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not valid");
            System.exit(1);
        }

        return size;
    }

    public static void FileReader(File file, Soup soup){
        try{
            Scanner sc = new Scanner(file);
            String line;
            int counter = 0;

            while (sc.hasNextLine()){
                line = sc.nextLine();

                if (!soupWordVerifier(line)){
                    break;
                }

                addWordToSoup(soup, line, counter);
                counter++;
            }

            SoupSizeVerifier(counter,soup.getSize());

            sc.close();

            Scanner sc2 = new Scanner(file);

            //ignorar as linhas que contêm a sopa de letras
            for (int i=0; i<soup.getSize(); i++){
                sc2.nextLine();
            }

            while(sc2.hasNextLine()){
                String line2 = sc2.nextLine();
                StoreSolutionWord(soup, line2);
            }

            sc2.close();
        } catch (FileNotFoundException e) {
            System.out.println("Erro");
            System.exit(1);
        }
    }

    public static boolean soupWordVerifier(String line) {
        if (line.isEmpty()) {
            error("Empty line in soup file");
        }

        for (int i=0; i<line.length(); i++) {
            char character = line.charAt(i);
            if (!Character.isLetter(character) || Character.isLowerCase(character)) {
                return false;
            }
        }

        return true;
    }

    public static void addWordToSoup(Soup soup, String line, int counter) {
        for (int i=0; i<line.length(); i++) {
            soup.setSoup(counter, i, line.charAt(i));
        }
    }

    public static void SoupSizeVerifier(int counter, int size){

        if (counter!=size){
            error("A sopa de letras tem de ser quadrada!");
        }
    }

    public static void StoreSolutionWord(Soup soup, String line){
        String[] sol = line.split("[;, ]");

        for (int i=0; i<sol.length; i++){

            String word = sol[i];
            Word word_to_find = new Word(word);
            soup.addWord(word_to_find);
        }
    }


    public static void soupSolver(Soup soup){
        for (Word word : soup.getWords()){

            if (!SearchWordInSoup(soup, word)){
                error("Word not found: " + word.getWord());
            }

        }
    }

    public static Boolean SearchWordInSoup(Soup soup, Word word){

        for (int i=0; i<soup.getSize(); i++){
            for (int j=0; j<soup.getSize(); j++){
                
                if (CheckIfFound(soup, word, i, j)){
                    return true;
                };
            }
        }
        return false;

    }

    public static Boolean CheckIfFound(Soup soup, Word word, int i, int j){

        String word_str = word.getWord().toUpperCase();

        if (soup.getSoup(i,j) == word_str.charAt(0)){
            Way orientation = FindOrientation(soup, word_str, i, j);

            if (orientation != null){
                word.setStart(new Position(i,j));
                word.setDirection(orientation);
                word.Found();
                return true;
            }
        }
        return false;
    }

    public static Way FindOrientation(Soup soup, String word, int i, int j){
        int word_char_index = 0;
        
        //verificar RIGTH
        if (j + word.length()-1 < soup.getSize() ) {
            if (FullSearch(soup, word, i, j, word_char_index, 0, 1)){
                soup.setMarked(i, j);
                return Way.Right;
            };
        }
        
        //verificar LEFT
        if (j - word.length()+1 >= 0) {
            if (FullSearch(soup, word, i, j, word_char_index, 0, -1)){
                soup.setMarked(i, j);
                return Way.Left;
            };
        }

        //verificar DOWN
        if (i + word.length()-1 < soup.getSize()) {
            if (FullSearch(soup, word, i, j, word_char_index, 1, 0)){
                soup.setMarked(i, j);
                return Way.Down;
            };
        }

        //verificar UP
        if (i - word.length()+1 >= 0) {
            if (FullSearch(soup, word, i, j, word_char_index, -1, 0)){
                soup.setMarked(i, j);
                return Way.Up;
            };
        }

        //verificar DOWNRIGHT
        if (i + word.length()-1 < soup.getSize() && j + word.length()-1 < soup.getSize()) {
            if (FullSearch(soup, word, i, j, word_char_index, 1, 1)){
                soup.setMarked(i, j);
                return Way.DownRight;
            };
        }

        //verificar DOWNLEFT taaaaa mallll
        if (i + word.length()-1 < soup.getSize() && j - word.length()-1 >= 0) {
            if (FullSearch(soup, word, i, j, word_char_index, 1, -1)){
                soup.setMarked(i, j);
                return Way.DownLeft;
            };
        }

        //verificar UPRIGH
        if (i - word.length()+1 >= 0 && j + word.length()-1 < soup.getSize()) {
            if (FullSearch(soup, word, i, j, word_char_index, -1, 1)){
                soup.setMarked(i, j);
                return Way.UpRight;
            };
        }

        //verificar UPLEFT
        if (i - word.length()+1 >= 0 && j - word.length()-1 >= 0) {
            if (FullSearch(soup, word, i, j, word_char_index, -1, -1)){
                soup.setMarked(i, j);
                return Way.UpLeft;
            };
        }

        return null;
    }


    public static Boolean FullSearch(Soup soup, String word, int i, int j, int word_char_index, int inc_x, int inc_y){
        if (word_char_index == word.length()-1){
            return true;
        }

        if (soup.getSoup(i,j) == word.charAt(word_char_index)){
            i = i+inc_x;
            j = j+inc_y;
            if (FullSearch(soup, word, i, j, word_char_index+1, inc_x, inc_y)){
                soup.setMarked(i, j);
                return true;
            }    
        }
        return false;
    }

    public static void generateOutput(String output, String file){
        String save_file = file.replace(".txt", "");

        try {
            FileWriter writer = new FileWriter(save_file + "_output.txt");
            writer.write(output);
            writer.close();
        } catch (Exception e) {
            error("Error writing to file");
        }
    }

    public static void error(String str) {
	    System.err.println("ERROR " + str);
	    System.exit(1);
	}
}
