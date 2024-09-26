import java.io.*;
import java.util.*;

public class WSSolver{
    public static void main(String[] args){
        // The file's name should be decalred in the terminal;
        String[] soupLines, wordsToFindLines;
        String line;
        int numSoupLines = 0, numWordsToFindLines = 0;
        
        try{
            File f = new File(args[0]);
            Scanner sc = new Scanner(f), sc2 = new Scanner(f);
            
            // Check the height of the Word Search first;
            while (sc.hasNext()) {
                line = sc.nextLine();
                if (line.equals(line.toUpperCase())){
                    numSoupLines++;
                }   
                else{
                    numWordsToFindLines++;
                }
            }
            sc.close();

            soupLines = new String[numSoupLines];
            wordsToFindLines = new String[numWordsToFindLines];
            
            for (int i = 0; i < numSoupLines; i++){
                line = sc2.nextLine();
                soupLines[i] = line;
            }

            for (int i = 0; i < numWordsToFindLines; i++){
                line = sc2.nextLine();
                wordsToFindLines[i] = line;
            }
            sc2.close();

            
            List<String> wordsToFind = linesToWords(wordsToFindLines);
            Word[] wordsSolved =  findWordsAlgorithm(soupLines, wordsToFind, numSoupLines);

            output(wordsSolved, soupLines, numSoupLines);

        }
        catch (FileNotFoundException e){
            System.err.println("Error opening file!!");
            System.exit(1);
        }
    }

    public static List<String> linesToWords(String[] lines) {
        List<String> palavras = new ArrayList<String>();
        for (String line : lines){
            String[] splitWords = line.split("[, ;]");
            palavras.addAll(Arrays.asList(splitWords));

        }
        return palavras;

    }

    public static boolean validSoup(String[] linhas, String[] palavras){
        if (linhas.length == 0) {  // se nao houver linhas return false
            return false;
        }

        int length = linhas[0].length(); // tamanho da primeira linha

        if (linhas.length != length) {  // se o array nao tiver lenght elementos return false
            return false;
        }

        for (String item : linhas) {  // itera sobre o array
            if (item.length() != length || !item.equals(item.toUpperCase())) {  // ver se o tamanho de item é igual ao primeiro elemnento e ver se item é igual a item capitalizado
                return false;
            }
        }

        for (String palavra: palavras){
            if (palavra.equals(palavra.toUpperCase()) || !palavra.matches("a-zA-Z]+")){
                return false;
            }
        }

        return true;
    }
    public static Word[] findWordsAlgorithm(String[] soupLines, List<String> wordsToFind, int windowSize){

        /*  Basicamente, ver as letras todas da sopa, quando uma delas for igual à primeira letra da palavra
            procurar em todas as direções, outras palavras com o tamanho da que estamos à procura */

        int lenOfWord;
        String aux, word;
        Word[] wordsSolved = new Word[wordsToFind.size()];
        boolean found;

        for (int w = 0; w < wordsToFind.size(); w++) {
            word = wordsToFind.get(w);
            lenOfWord = word.length();
            found = false;
            for (int i = 0; i < windowSize; i++){
                // i -> line;
                for (int j = 0; j < windowSize; j++){
                    // j -> column;
                    if (Character.toUpperCase(word.charAt(0)) == soupLines[i].charAt(j)){
                        aux = checkAllDirections(soupLines, word, i, j, windowSize, lenOfWord);
                        if (!aux.equals("")){
                            wordsSolved[w] = new Word(word, i + 1, j + 1, aux);
                            if (found){
                                System.err.printf("ERROR: The word \"%s\" was found twice!!\n", word);
                                System.exit(1);
                            }
                            found = true;                            
                        }
                    }
                }
            }
        }
        return wordsSolved;
    }
    public static String checkAllDirections(String[] soupLines, String word, int i, int j, int windowSize, int lenOfWord){
        String wordTest = "";
        if (j + lenOfWord <= windowSize){
            if (soupLines[i].substring(j, j + lenOfWord).equals(word.toUpperCase()))
                return "Right";
        }
        if (j + 1 >= lenOfWord){
            for (int k = j; j - k  < lenOfWord; k--){
                wordTest += soupLines[i].charAt(k);
            }
            if (word.toUpperCase().equals(wordTest))
                return "Left";
            wordTest = "";
        }
        if (i + lenOfWord <= windowSize){
            for (int k = i; k - i  < lenOfWord; k++){
                wordTest += soupLines[k].charAt(j);
            }
            if (word.toUpperCase().equals(wordTest))
                return "Down";
            wordTest = "";
        }
        if (i + 1 >= lenOfWord){
            for (int k = i; i - k  < lenOfWord; k--){
                wordTest += soupLines[k].charAt(j);
            }
            if (word.toUpperCase().equals(wordTest))
                return "Up";
            wordTest = "";
        }
        if (j + lenOfWord <= windowSize && i + 1 >= lenOfWord){
            for (int k = i,  l = j; i - k  < lenOfWord; k--, l++){
                wordTest += soupLines[k].charAt(l);
            }
            if (word.toUpperCase().equals(wordTest))
                return "UpRight";
            wordTest = "";
        }
        if (j + lenOfWord <= windowSize && i + lenOfWord <= windowSize){
            for (int k = i,  l = j; k - i  < lenOfWord; k++, l++){
                wordTest += soupLines[k].charAt(l);
            }
            if (word.toUpperCase().equals(wordTest))
                return "DownRight";
            wordTest = "";
        }
        if (j + 1 >= lenOfWord && i + 1 >= lenOfWord){
            for (int k = i,  l = j; i - k  < lenOfWord; k--, l--){
                wordTest += soupLines[k].charAt(l);
            }
            if (word.toUpperCase().equals(wordTest))
                return "UpLeft";
            wordTest = "";
        }
        if (j + 1 >= lenOfWord && i + lenOfWord <= windowSize){
            for (int k = i,  l = j; k - i  < lenOfWord; k++, l--){
                wordTest += soupLines[k].charAt(l);
            }
            if (word.toUpperCase().equals(wordTest))
                return "DownLeft";
            wordTest = "";
        }
        return "";

    }
    public static void output(Word[] wordsSolved, String[] soupLines, int windowSize){
        // See which indexs have a letter that should be impressed;
        List<int[]> allCharIndexs = new ArrayList<>();
        boolean isEqual = false;
        
        for (Word word : wordsSolved){
            for (int[] is : word.getCharIndexs()){
                allCharIndexs.add(is);
            }
        }
        for (Word word : wordsSolved) {
            System.out.println(word);
        }
        System.out.println();
        for (int i = 0; i < windowSize; i++) {
            for (int j = 0; j < windowSize; j++){
                for (int[] is : allCharIndexs) {
                    if (is[0] == i + 1 && is[1] == j + 1){
                        isEqual = true;
                    }
                }
                if (isEqual)
                    System.out.print(soupLines[i].charAt(j) + " ");
                else{
                    System.out.print(". ");
                }
                isEqual = false;
            }
            System.out.println();
        }
    }
}