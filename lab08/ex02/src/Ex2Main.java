package ex02.src;

import java.io.FileNotFoundException;

public class Ex2Main {
    public static void main(String[] args) throws FileNotFoundException {

        System.out.println("-----------Original text-----------");

        ReaderInterface reader = new TextReader("./lab08/Ex2/input.txt");
        while (reader.hasNext()) {
            System.out.println(reader.next());
        }

        System.out.println("-----------Normalized Text without vowels-----------");

        ReaderInterface reader1 = new VowelFilter(new NormalizationFilter(new TextReader("./lab08/Ex2/input.txt")));
        while (reader1.hasNext()) {
            System.out.println(reader1.next());
        }

        System.out.println("-----------Term Filter with Capitalization-----------");

        ReaderInterface reader2 = new CapitalizationFilter(new TermFilter(new TextReader("./lab08/Ex2/input.txt")));
        while (reader2.hasNext()) {
            System.out.println(reader2.next());
        }
    }
}
