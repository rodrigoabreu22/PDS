package lab01.ex02.src;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.util.Random;

public class WordSearchGenerator {
    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<String> soupWords = new ArrayList<>();
        final int MINIMUM_ARGS = 4;
        boolean use_i = false, use_s = false, use_o = false;
        int size = 0;
        String list_file = null, save_file = null;

        //verificar se o número de argumentos está correto
        if (args.length < MINIMUM_ARGS) {
            System.out.println("Numero de argumentos insuficientes");
        }

        //verificar cada argumento
        for (int i=0; i<args.length; i++) {
            if (args[i].charAt(0) == '-') {
                //verificar se as flags são seguidas dos devidos parametros
                if (args.length - 1 == i) {
                    System.out.println("Faltam parâmetros nos argumentos passados");
                    System.exit(1);
                }

                if (!verificarArgs(args[i])) {
                    System.out.println(args[i] + " - Argumento inválido");
                    System.exit(1);
                }

                //index do parâmetro seguinte à flag
                int next_arg = -1;

                switch (args[i].charAt(1)) {
                    case 'i':
                        if (use_i) {
                            System.out.println("Cada flag só pode ser utilizada uma vez");
                            System.exit(1);
                        }
                        //alertar que a flag já foi utilizada
                        use_i = true;

                        //guardar nome do ficheiro de leitura
                        next_arg = i + 1;
                        list_file = args[next_arg];
                        break;
                    
                    case 'o':
                        if (use_o) {
                            System.out.println("Cada flag só pode ser utilizada uma vez");
                            System.exit(1);
                        }
                        //alertar que a flag já foi utilizada
                        use_o = true;

                        //guardar nome do ficheiro de escrita
                        next_arg = i + 1;
                        save_file = args[next_arg];
                        break;

                    case 's':
                        if (use_s) {
                            System.out.println("Cada flag só pode ser utilizada uma vez");
                            System.exit(1);
                        }
                        //alertar que a flag já foi utilizada
                        use_s = true;

                        //guardar tamanho da sopa
                        next_arg = i + 1;
                        size = Integer.parseInt(args[next_arg]);
                        
                        //verificar se é um parametro válido
                        if (!verificarSize(size)) {
                            System.out.println("Tamanho inválido");
                            System.exit(1);
                        } 
                        break;
                }
            }
        }
        
        //criar um ficheiro para leitura
        File file = null;
        try {
            file = new File(list_file);          
        }
        catch (Exception e) {
            System.out.println("Erro " + e);
            System.exit(1);
        }
        
        //ler as palavras do ficheiro
        readFile(file, size, soupWords);
        
        //gerar uma soupa de letras aleatória
        Character[][] soup_grid = creatGrid(size);

        //inserir as palavras lidas do ficheiro na soupa
        insertWords(soup_grid, soupWords, size);

        //escrever a soupa de letras no ficheiro
        if (save_file != null) {
            writeFile(save_file, soup_grid, soupWords, size);
        }
        //escrever a soupa de letras no terminal
        else {
            displayGrid(soup_grid, soupWords, size);
        }
    }

    public static void writeFile(String save_file, Character[][] soup_grid, ArrayList<String> list, int size) {
        try {
            FileWriter writer = new FileWriter(save_file);

            //print da soupa de letras
            for (int i=0; i<size; i++) {
                for (int j=0; j<size; j++) {
                    writer.write(soup_grid[i][j]);
                }
                writer.write("\n");
            }

            //contador para saber se é a última palavra
            int counter = 0;

            //print das palavras a encontrar
            for (String word : list) {
                counter++;

                //se for a última palavra, não imprimir a vírgula
                if (counter == list.size()) {
                    writer.write(word);
                    break;
                }
                writer.write(word + ",");
            }
            writer.close();
        }
        catch (Exception e) {
            System.out.println("Erro " + e);
            System.exit(1);
        }
    }

    public static void displayGrid(Character[][] soup_grid, ArrayList<String> list, int size) {
        System.out.println();
        for (int i=0; i<size; i++) {
            for (int j=0; j<size; j++) {
                System.out.printf("%s ", soup_grid[i][j]);
            }
            System.out.println();
        }
        System.out.println();

        //contador para saber se é a última palavra
        int counter = 0;

        for (String word : list) {
            counter++;

            //se for a última palavra, não imprimir a vírgula
            if (counter == list.size()) {
                System.out.printf("%s", word);
                break;
            }
            System.out.printf("%s,", word);
        }
        System.out.println("\n");
    }

    public static void insertWords(Character[][] soup, ArrayList<String> list, int size) {
        final int MAXIMUM_TRIES = (size * size) * 8;
        int[][] use_coordinates = new int[size][size];
        //colunas
        int x = 0;
        //linhas
        int y = 0;
        Way direction = null;

        //inciar a matriz de coordenadas com falor default
        for (int i=0; i<size; i++) {
            for (int j=0; j<size; j++) {
                use_coordinates[i][j] = 0;
            }
        }

        for (String word : list) {
            boolean inserted = false;
            //contador de tentativas
            int tries = 0;

            while (!inserted) {
                if (tries == MAXIMUM_TRIES) {
                    System.out.println("Erro, não foi possível inserir todas as palavras na soupa");
                    System.exit(1);
                }

                Random random = new Random();

                //selecionar coordenadas e direção aleatória
                x = random.nextInt(size-1);
                y = random.nextInt(size-1);
                direction = Way.values()[random.nextInt(Way.values().length)];

                //incrementar o contador de tentativas
                tries++;

                //verificar se a palavra cabe na direção escolhida (verifica todas as letras da palavra)
                switch (direction) {
                    case Right:
                        if (x + word.length() <= size) {
                            for (int i=0; i<word.length(); i++) {

                                if (use_coordinates[x+i][y] == 1 && soup[x+i][y] != Character.toUpperCase(word.charAt(i))) {
                                    inserted = false;
                                    break;
                                }
                                inserted = true;
                            }
                        }
                        break;

                    case Left:
                        if (x - word.length() >= 0) {
                            for (int i=0; i<word.length(); i++) {

                                if (use_coordinates[x-i][y] == 1 && soup[x-i][y] != Character.toUpperCase(word.charAt(i))) {
                                    inserted = false;
                                    break;
                                }
                                inserted = true;
                            }
                        }
                        break;

                    case Down:
                        if (y + word.length() <= size) {
                            for (int i=0; i<word.length(); i++) {

                                if (use_coordinates[x][y+i] == 1 && soup[x][y+i] != Character.toUpperCase(word.charAt(i))) {
                                    inserted = false;
                                    break;
                                }
                                inserted = true;
                            }
                        }
                        break;

                    case Up:
                        if (y - word.length() >= 0) {
                            for (int i=0; i<word.length(); i++) {

                                if (use_coordinates[x][y-i] == 1 && soup[x][y-i] != Character.toUpperCase(word.charAt(i))) {
                                    inserted = false;
                                    break;
                                }
                                inserted = true;
                            }
                        }
                        break;

                    case DownRight:
                        if (y + word.length() <= size && x + word.length() <= size) {
                            for (int i=0; i<word.length(); i++) {

                                if (use_coordinates[x+i][y+i] == 1 && soup[x+i][y+i] != Character.toUpperCase(word.charAt(i))) {
                                    inserted = false;
                                    break;
                                }
                                inserted = true;
                            }
                        }
                        break;

                    case DownLeft:
                        if (y + word.length() <= size && x - word.length() >= 0) {
                            for (int i=0; i<word.length(); i++) {

                                if (use_coordinates[x-i][y+i] == 1 && soup[x-i][y+i] != Character.toUpperCase(word.charAt(i))) {
                                    inserted = false;
                                    break;
                                }
                                inserted = true;
                            }
                        }
                        break;

                    case UpRight:
                        if (y - word.length() >= 0 && x + word.length() <= size) {
                            for (int i=0; i<word.length(); i++) {

                                if (use_coordinates[x+i][y-i] == 1 && soup[x+i][y-i] != Character.toUpperCase(word.charAt(i))) {
                                    inserted = false;
                                    break;
                                }
                                inserted = true;
                            }
                        }
                        break;

                    case UpLeft:
                        if (y - word.length() >= 0 && x - word.length() >= 0) {
                            for (int i=0; i<word.length(); i++) {

                                if (use_coordinates[x-i][y-i] == 1 && soup[x-i][y-i] != Character.toUpperCase(word.charAt(i))) {
                                    inserted = false;
                                    break;
                                }
                                inserted = true;
                            }
                        }
                        break;
                }

                //se a palavra couber na direção escolhida, inserir na soupa
                if (inserted) {
                    switch (direction) {
                        case Right:
                            for (int i=0; i<word.length(); i++) {
                                soup[x+i][y] = Character.toUpperCase(word.charAt(i));
                                use_coordinates[x+i][y] = 1;
                            }
                            break;

                        case Left:
                            for (int i=0; i<word.length(); i++) {
                                soup[x-i][y] = Character.toUpperCase(word.charAt(i));
                                use_coordinates[x-i][y] = 1;
                            }
                            break;

                        case Down:
                            for (int i=0; i<word.length(); i++) {
                                soup[x][y+i] = Character.toUpperCase(word.charAt(i));
                                use_coordinates[x][y+i] = 1;
                            }
                            break;

                        case Up:
                            for (int i=0; i<word.length(); i++) {
                                soup[x][y-i] = Character.toUpperCase(word.charAt(i));
                                use_coordinates[x][y-i] = 1;
                            }
                            break;

                        case DownRight:
                            for (int i=0; i<word.length(); i++) {
                                soup[x+i][y+i] = Character.toUpperCase(word.charAt(i));
                                use_coordinates[x+i][y+i] = 1;
                            }
                            break;

                        case DownLeft:
                            for (int i=0; i<word.length(); i++) {
                                soup[x-i][y+i] = Character.toUpperCase(word.charAt(i));
                                use_coordinates[x-i][y+i] = 1;
                            }
                            break;

                        case UpRight:
                            for (int i=0; i<word.length(); i++) {
                                soup[x+i][y-i] = Character.toUpperCase(word.charAt(i));
                                use_coordinates[x+i][y-i] = 1;
                            }
                            break;

                        case UpLeft:
                            for (int i=0; i<word.length(); i++) {
                                soup[x-i][y-i] = Character.toUpperCase(word.charAt(i));
                                use_coordinates[x-i][y-i] = 1;
                            }
                            break;
                    }
                }
            }
        }
    }

    public static Character[][] creatGrid(int size) {
        Character[][] soup = new Character[size][size];
        final int ALPHABET_LENGTH = 26;
        final int FIRST_LETTER_ASCII = 65;

        Random random = new Random();

        for (int i=0; i<size; i++) {
            for (int j=0; j<size; j++) {
                //gerar de forma aleatória o código ascii para uma letra maiúscula
                int letter_ascii = random.nextInt(ALPHABET_LENGTH) + FIRST_LETTER_ASCII;
                char letter = (char) letter_ascii;
                soup[i][j] = letter;
            }
        }
        return soup;
    }

    public static void readFile(File file, int size, ArrayList<String> list) {
        try {
            Scanner sc = new Scanner(file);

            while (sc.hasNextLine()) {
                String line = sc.nextLine();

                //verificar se é linha vazia
                if (line.length() == 0) {
                    System.out.println("Erro, linha vazia");
                    System.exit(1);
                }

                String[] words = line.split("[,; ]");

                //verificar cada palavra e guardar num array
                for (int i=0; i<words.length; i++) {
                    String word = words[i].toLowerCase();

                    if (!verificarWords(word, size)) {
                        System.out.println("Erro, palavra inválida -> " + word + "\nPalavra tem de ter entre 2 e " + size + " caracteres");
                        System.exit(1);
                    }

                    if (!verificarChar(word)) {
                        System.out.println("Erro, palavra inválida -> " + word + "\nPalavra tem de ser composta por letras");
                        System.exit(1);
                    }
                    //guardar palavra válida
                    list.add(word);
                }
            }
            sc.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("Erro " + e);
            System.exit(1);
        }  
    }

    public static boolean verificarWords(String word, int size) {
        final int MINIMUM_LENGTH = 2;
        if (word.length() < MINIMUM_LENGTH || word.length() > size ) {
            return false;
        }
        return true;
    }

    public static boolean verificarChar(String word) {
        for (int i=0; i<word.length(); i++) {
            if(!Character.isAlphabetic(word.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean verificarArgs(String arg) {
        boolean bool = true;
        if (arg.length() < 2) {
            bool = false;
        }
        else if (!(arg.charAt(1) == 'i' || arg.charAt(1) == 'o' || arg.charAt(1) == 's')) {
            bool = false;
        }
        return bool;
    }

    public static boolean verificarSize(int size) {
        final int MAXIMUM_SIZE = 40;
        if (size > 0 && size <= MAXIMUM_SIZE) {
            return true;
        }
        return false;
    }
}
