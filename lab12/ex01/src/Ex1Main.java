import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Ex1Main {
    public static void main(String[] args){

        Phone phone1 = new Phone("Snapdragon 888", 1000, 4, 108);
        Phone phone2 = new Phone("Snapdragon 865", 300, 8, 16);
        Phone phone3 = new Phone("Snapdragon 865", 250, 4, 64);
        Phone phone4 = new Phone("Snapdragon 865", 600, 6, 64);
        Phone phone5 = new Phone("Snapdragon 865", 550, 8, 32);
        Phone phone6 = new Phone("Snapdragon 865", 700, 2, 64);
        Phone phone7 = new Phone("Snapdragon 865", 1200, 6, 32);
        Phone phone8 = new Phone("Snapdragon 865", 200, 16, 64);
        Phone phone9 = new Phone("Snapdragon 865", 100, 4, 18);
        Phone phone10 = new Phone("Snapdragon 865", 150, 2, 16);

        List<Phone> phone_list = new ArrayList<>();
        phone_list.add(phone1);
        phone_list.add(phone2);
        phone_list.add(phone3);
        phone_list.add(phone4);
        phone_list.add(phone5);
        phone_list.add(phone6);
        phone_list.add(phone7);
        phone_list.add(phone8);
        phone_list.add(phone9);
        phone_list.add(phone10);

        System.out.println("Before sorting:");
        for (Phone phone : phone_list) {
            System.out.println(phone);
        }

        Comparator<Phone> price_comparator = Comparator.comparingDouble(phone -> phone.getPrice());
        Comparator<Phone> memory_comparator = Comparator.comparingInt(phone -> phone.getMemory());
        Comparator<Phone> camera_comparator = Comparator.comparingInt(phone -> phone.getCamera());

        Magazine magazine = new Magazine(new BubbleSort());
        magazine.sort(phone_list, price_comparator);

        System.out.println("\nAfter sorting by price:");
        for (Phone phone : phone_list) {
            System.out.println(phone);
        }

        magazine.setSortingType(new MergeSort());
        magazine.sort(phone_list, memory_comparator);

        System.out.println("\nAfter sorting by memory:");
        for (Phone phone : phone_list) {
            System.out.println(phone);
        }

        magazine.setSortingType(new InsertionSort());
        magazine.sort(phone_list, camera_comparator);

        System.out.println("\nAfter sorting by camera:");
        for (Phone phone : phone_list) {
            System.out.println(phone);
        }


    }
}
