package ex02.src;

import java.io.File;

public class ContactsMain {
    public static void main(String[] args) {
        // Create contact list
        ContactList list = ContactList.createContactList();

        Contact joao = Contact.createContact("Joao", 123456789);
        Contact maria = Contact.createContact("Maria", 987654321);
        Contact pedro = Contact.createContact("Pedro", 456789123);

        // Add contacts to list
        list.add(joao);
        list.add(maria);
        list.add(pedro);

        // verify if contacts exist
        System.out.println("Joao exists: " + list.exist(joao));

        // remove contact
        list.remove(joao);

        // save and close
        ContactsStorageInterface store1 = new Format(new File("lab07/Ex2/Contact.txt"));
        list.saveAndClose(store1);

        System.out.println("_____________________________________________________\n");

        // new contact list
        ContactList list2 = ContactList.createContactList();

        // open and load
        list2.openAndLoad(store1);

        Contact rita = Contact.createContact("Rita", 123456789);
        Contact miguel = Contact.createContact("Miguel", 987654321);
        Contact ana = Contact.createContact("Ana", 456789123);

        // Add contacts to list
        list2.add(rita);
        list2.add(miguel);
        list2.add(ana);

        // verify if contacts exist
        System.out.println("Rita exists: " + list2.exist(rita));
        System.out.println("Miguel exists: " + list2.exist(miguel));

        // print contacts
        System.out.println("\nContacts:");
        for (Contact contact : list2.getContacts()) {
            System.out.println(contact);
        }

        // save and close
        ContactsStorageInterface store2 = new Format(new File("lab07/Ex2/Contact2.bin"));
        list2.saveAndClose(store2);

        System.out.println("_____________________________________________________\n");
        
        // new contact list
        ContactList list3 = ContactList.createContactList();
        ContactsStorageInterface store3 = new Format(new File("lab07/Ex2/Contact2.bin"));

        // open and load
        list3.openAndLoad(store3);
    }
}
