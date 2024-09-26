package ex02.src;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TxtFormat extends Format {
    private File file;

    public TxtFormat(File file) {
        super(file);
        this.file = file;
    }

    @Override
    public boolean saveContacts(List<Contact> list) {
        try {
            PrintWriter output = new PrintWriter(file);
            for (Contact contact : list) {
                output.println(contact.toString());
            }
            output.close();
            return true;

        } catch (Exception e) {
            System.out.println("Error " + e);
            return false;
        }
    }

    @Override
    public List<Contact> loadContacts() {
        List<Contact> list = new ArrayList<Contact>();

        try {
            Scanner input = new Scanner(file);
            while (input.hasNextLine()) {
                String[] parts = input.nextLine().split(" ");
                // verify if the line has the correct format
                if (parts.length != 2) {
                    continue;
                }
                Contact contact = Contact.createContact(parts[0], Integer.parseInt(parts[1]));
                list.add(contact);
            }
            input.close();
            
        } catch (Exception e) {
            System.out.println("Error " + e);
        }
        return list;
    }
}
