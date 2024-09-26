package ex02.src;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ContactList implements ContactsInterface {
    private List<Contact> list;

    private ContactList() {
        this.list = new ArrayList<Contact>();
    }

    public static ContactList createContactList() {
        return new ContactList();
    }

    @Override
    public void saveAndClose(ContactsStorageInterface store) {
        if (list.size() == 0) {
            System.out.println("Empty list");
            return;
        }
        store.saveContacts(list);
    }

    @Override
    public void openAndLoad(ContactsStorageInterface store) {
        list = store.loadContacts();
        System.out.println("Contacts loaded:");
        for (Contact contact : list) {
            System.out.println(contact);
        }
    }

    @Override
    public void saveAndClose() {
        if (list.size() == 0) {
            System.out.println("Empty list");
            return;
        }
        // get the file name to save the contacts
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the file name: ");
        String fileName = input.nextLine().trim();

        File file = new File(fileName);
        // create storage format based on the file extension
        ContactsStorageInterface store = new Format(file);
        store.saveContacts(list);
        input.close();
    }

    @Override
    public boolean exist(Contact contact) {
        return list.contains(contact);
    }

    @Override
    public Contact getByName(String name) {
        for (Contact contact : list) {
            if (contact.getName().equals(name)) {
                return contact;
            }
        }
        return null;
    }

    @Override
    public boolean add(Contact contact) {
        if (list.contains(contact)) {
            return false;
        }
        list.add(contact);
        return true;
    }

    @Override
    public boolean remove(Contact contact) {
        return list.remove(contact);
    }

    public List<Contact> getContacts() {
        return list;
    }
}
