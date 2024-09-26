package lab02;

import java.util.ArrayList;
import java.util.Random;

public class Soup {
    private int size;
    private char[][] soup;
    private boolean[][] marked;
    private ArrayList<Word> words_to_find;
    private final int MINIMUM_SIZE = 3;
    private final int MAXIMUM_SIZE = 40;

    public Soup(int size) {
        this.size = size;
        soup = new char[size][size];
        marked = new boolean[size][size];
        words_to_find = new ArrayList<Word>();

        // inicializar marked
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                marked[i][j] = false;
            }
        }
    }

    public int getSize() {
        return size;
    }

    public boolean verifySize(int size) {
        return (size >= MINIMUM_SIZE && size <= MAXIMUM_SIZE);
    }

    public void setMarked(int i, int j) {
        marked[i][j] = true;
    }

    public boolean isMarked(int i, int j) {
        return marked[i][j];
    }

    public char getSoup(int i, int j) {
        return soup[i][j];
    }

    public void setSoup(int i, int j, char c) {
        soup[i][j] = c;
    }

    public void addWord(Word word) {
        words_to_find.add(word);
    }

    public int getNumberOfWords() {
        return words_to_find.size();
    }

    public Word getWord(int i) {
        return words_to_find.get(i);
    }

    public ArrayList<Word> getWords() {
        return words_to_find;
    }

    public void generateRandomSoup() {
        final int ALPHABET_LENGTH = 26;
        final int FIRST_LETTER_ASCII = 65;

        Random random = new Random();

        for (int i=0; i<this.size; i++) {
            for (int j=0; j<this.size; j++) {
                //gerar de forma aleatória o código ascii para uma letra maiúscula
                int letter_ascii = random.nextInt(ALPHABET_LENGTH) + FIRST_LETTER_ASCII;
                char letter = (char) letter_ascii;
                setSoup(i, j, letter);
            }
        }
    }

    public void insertWords() {
        final int MAXIMUM_TRIES = (this.size * this.size) * 8;
        Random random = new Random();

        for (int i=0; i<getNumberOfWords(); i++) {
            Word word = words_to_find.get(i);

            //contador de tentativas
            int tries = 0;

            while (!word.isInserted()) {
                if (tries > MAXIMUM_TRIES) {
                    System.out.println("Erro, não foi possível inserir a palavra " + word.getWord());
                    System.exit(1);
                }
                //incrementar o contador de tentativas
                tries++;

                int x = random.nextInt(this.size-1);
                int y = random.nextInt(this.size-1);
                Way direction = Way.values()[random.nextInt(Way.values().length)];

                if (canInsertWord(word, x, y, direction)) {
                    insertWord(word, x, y, direction);
                }
            }
        }
    }
    
    public void displaySoup() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(soup[i][j] + " ");
            }
            System.out.println();
        }

        //contador para saber se é a última palavra
        int counter = 0;

        for (Word word : words_to_find) {
            counter++;

            //se for a última palavra, não imprimir a vírgula
            if (counter == words_to_find.size()) {
                System.out.printf("%s", word.getWord());
                break;
            }
            System.out.print(word.getWord() + ",");
        }
        System.out.println("\n");
    }

    private void insertWord(Word word, int line, int col, Way direction) {
        word.setStart(new Position(line, col));
        word.setDirection(direction);

        switch (direction) {
            case Right:
                for (int i = 0; i < word.getSize() -1; i++) {
                    setMarked(line, col + i);
                    setSoup(line, col + i, Character.toUpperCase(word.getWord().charAt(i)));
                }
                break;
        
            case Left:
                for (int i = 0; i < word.getSize()-1; i++) {
                    setMarked(line, col - i);
                    setSoup(line, col - i, Character.toUpperCase(word.getWord().charAt(i)));
                }
                break;

            case Down:
                for (int i = 0; i < word.getSize(); i++) {
                    setMarked(line + i, col);
                    setSoup(line + i, col, Character.toUpperCase(word.getWord().charAt(i)));
                }
                break;

            case Up:
                for (int i = 0; i < word.getSize(); i++) {
                    setMarked(line - i, col);
                    setSoup(line - i, col, Character.toUpperCase(word.getWord().charAt(i)));
                }
                break;

            case DownRight:
                for (int i = 0; i < word.getSize(); i++) {
                    setMarked(line + i, col + i);
                    setSoup(line + i, col + i, Character.toUpperCase(word.getWord().charAt(i)));
                }
                break;

            case DownLeft:
                for (int i = 0; i < word.getSize(); i++) {
                    setMarked(line + i, col - i);
                    setSoup(line + i, col - i, Character.toUpperCase(word.getWord().charAt(i)));
                }
                break;

            case UpRight:
                for (int i = 0; i < word.getSize(); i++) {
                    setMarked(line - i, col + i);
                    setSoup(line - i, col + i, Character.toUpperCase(word.getWord().charAt(i)));
                }
                break;

            case UpLeft:
                for (int i = 0; i < word.getSize(); i++) {
                    setMarked(line - i, col - i);
                    setSoup(line - i, col - i, Character.toUpperCase(word.getWord().charAt(i)));
                }
                break;

        }
        word.setInserted();
    }

    private boolean canInsertWord(Word word, int line, int col, Way direction) {
        boolean result = false;

        switch (direction) {
            case Right:
                if (col + word.getSize() -1 < this.size) {
                    for (int i = 0; i < word.getSize(); i++) {
                        if (isMarked(line, col + i) && soup[line][col + i] != Character.toUpperCase(word.getWord().charAt(i))) {
                            result = false;
                            break;
                        }
                        result = true;
                    }
                }
                break;
        
            case Left:
                if (col - word.getSize() +1 >= 0) {
                    for (int i = 0; i < word.getSize(); i++) {
                        if (isMarked(line, col - i) && soup[line][col - i] != Character.toUpperCase(word.getWord().charAt(i))) {
                            result = false;
                            break;
                        }
                        result = true;
                    }
                }
                break;

            case Down:
                if (line + word.getSize() -1 < this.size) {
                    for (int i = 0; i < word.getSize(); i++) {
                        if (isMarked(line + i, col) && soup[line + i][col] != Character.toUpperCase(word.getWord().charAt(i))) {
                            result = false;
                            break;
                        }
                        result = true;
                    }
                }
                break;

            case Up:
                if (line - word.getSize() +1 >= 0) {
                    for (int i = 0; i < word.getSize(); i++) {
                        if (isMarked(line - i, col) && soup[line - i][col] != Character.toUpperCase(word.getWord().charAt(i))) {
                            result = false;
                            break;
                        }
                        result = true;
                    }
                }
                break;

            case DownRight:
                if (line + word.getSize() -1 < this.size && col + word.getSize() -1 < this.size) {
                    for (int i = 0; i < word.getSize(); i++) {
                        if (isMarked(line + i, col + i) && soup[line + i][col + i] != Character.toUpperCase(word.getWord().charAt(i))) {
                            result = false;
                            break;
                        }
                        result = true;
                    }
                }
                break;

            case DownLeft:
                if (line + word.getSize() -1 < this.size && col - word.getSize() -1 >= 0) {
                    for (int i = 0; i < word.getSize(); i++) {
                        if (isMarked(line + i, col - i) && soup[line + i][col - i] != Character.toUpperCase(word.getWord().charAt(i))) {
                            result = false;
                            break;
                        }
                        result = true;
                    }
                }
                break;

            case UpRight:
                if (line - word.getSize() +1 >= 0 && col + word.getSize() -1 < this.size) {
                    for (int i = 0; i < word.getSize(); i++) {
                        if (isMarked(line - i, col + i) && soup[line - i][col + i] != Character.toUpperCase(word.getWord().charAt(i))) {
                            result = false;
                            break;
                        }
                        result = true;
                    }
                }
                break;

            case UpLeft:
                if (line - word.getSize() +1 >= 0 && col - word.getSize() -1 >= 0) {
                    for (int i = 0; i < word.getSize(); i++) {
                        if (isMarked(line - i, col - i) && soup[line - i][col - i] != Character.toUpperCase(word.getWord().charAt(i))) {
                            result = false;
                            break;
                        }
                        result = true;
                    }
                }
                break;

        }
        return result;
    }

    public String gridString(){
        String grid = "";
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (isMarked(i, j)) {
                    grid += soup[i][j] + " ";
                }
                else {
                    grid += ". ";
                }
            }
            grid += "\n";
        }
        return grid;
    }

    public String WsSolver_output(){
        String output = "";

        for (int i = 0; i < getNumberOfWords(); i++) {
            Word word = getWord(i);
            if (word.isFound()) {
                output += word;
            }
        }
        
        output += "\n"+gridString();

        return output;
    }
}