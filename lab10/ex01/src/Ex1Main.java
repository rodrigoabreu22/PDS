package ex01.src;

import java.util.Iterator;
import java.util.ListIterator;

public class Ex1Main {
    public static void main(String[] args){
        VectorGeneric<String> v = new VectorGeneric<>();

        v.addElem("um");
        v.addElem("dois");
        v.addElem("tres");
        v.addElem("quatro");
        v.addElem("cinco");
        v.addElem("seis");
        v.addElem("sete");
        v.addElem("oito");
        v.addElem("nove");
        v.addElem("dez");

        Vector<String> it = v;
        Iterator<String> iterator;
        ListIterator<String> listIterator;
        
        System.out.println("\n\nTeste iterator");
        iterator = it.Iterator();
        while (iterator.hasNext())
            System.out.println(iterator.next());
        iterator.remove();
        System.out.println(iterator.hasNext());

        System.out.println("\n\nTeste list iterator");
        listIterator = it.listIterator();
        System.out.println(listIterator.hasPrevious());
        while (listIterator.hasNext())
            System.out.println(listIterator.next());

        System.out.println("\n\nTeste list iterator com indice");
        listIterator = it.listIterator(4);
        while (listIterator.hasNext())
            System.out.println(listIterator.next());
        System.out.println(listIterator.previousIndex());
        System.out.println(listIterator.previous());
        System.out.println(listIterator.hasPrevious());
        listIterator.add("gelado");
        System.out.println(listIterator.next());
        System.out.println(listIterator.next());
        listIterator.remove();
        System.out.println(listIterator.previous());
    }
}
