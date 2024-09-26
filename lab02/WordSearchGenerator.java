package lab02;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;

public class WordSearchGenerator {
    public static void main(String[] args) {
        final int MINIMUM_ARGS = 4;
        boolean use_i = false, use_s = false, use_o = false;
        String list_file = null, save_file = null;
        int size = 0;
        
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
                        break;
                }
            }
        }

        // criar a sopa
        Soup soup = new Soup(size);

        if (!soup.verifySize(size)) {
            System.out.println("Tamanho inválido -> " + size);
            System.exit(1);
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
        readFile(file, soup);

        soup.generateRandomSoup();

        // inserir as palavras na sopa
        soup.insertWords();

        //escrever a soupa de letras no ficheiro
        if (save_file != null) {
            writeFile(save_file, soup);
        }
        //escrever a soupa de letras no terminal
        else {
            soup.displaySoup();
        }
    }

    public static void writeFile(String save_file, Soup soup) {
        try {
            FileWriter writer = new FileWriter(save_file);

            //print da soupa de letras
            for (int i = 0; i < soup.getSize(); i++) {
                for (int j = 0; j < soup.getSize(); j++) {
                    writer.write(soup.getSoup(i, j));
                }
                writer.write("\n");
            }

            //contador para saber se é a última palavra
            int counter = 0;

            //print das palavras a encontrar
            for (int i = 0; i < soup.getNumberOfWords(); i++) {
                Word word = soup.getWord(i);
                counter++;

                //se for a última palavra, não imprimir a vírgula
                if (counter == soup.getNumberOfWords()) {
                    writer.write(word.getWord());
                    break;
                }
                writer.write(word.getWord() + ",");
            }
            writer.close();
        }
        catch (Exception e) {
            System.out.println("Erro " + e);
            System.exit(1);
        }
    }

    public static void readFile(File file, Soup soup) {
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

                for (int i=0; i<words.length; i++) {
                    //cirar uma palavra
                    Word word = new Word(words[i]);

                    if (!verificarWords(word, soup.getSize())) {
                        System.out.println("Erro, palavra inválida -> " + word + "\nPalavra tem de ter entre 2 e " + soup.getSize() + " caracteres");
                        System.exit(1);
                    }

                    if (!verificarChar(word)) {
                        System.out.println("Erro, palavra inválida -> " + word + "\nPalavra tem de ser composta por letras");
                        System.exit(1);
                    }
                    //guardar palavra válida
                    soup.addWord(word);
                }
            }
            sc.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("Erro " + e);
            System.exit(1);
        }  
    }

    public static boolean verificarChar(Word word) {
        for (int i=0; i<word.getSize(); i++) {
            if(!Character.isAlphabetic(word.getWord().charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean verificarWords(Word word, int size) {
        final int MINIMUM_LENGTH = 2;
        if (word.getSize() < MINIMUM_LENGTH || word.getSize() > size ) {
            return false;
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
}
