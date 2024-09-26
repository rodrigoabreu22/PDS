package ex02.src;

import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.io.FileOutputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.io.DataInputStream;
import java.io.IOException;

public class BinFormat extends Format {

    public BinFormat(File file) {
        super(file);
    }
    
    @Override
    public List<Contact> loadContacts() {
        ArrayList<Contact> myList = new ArrayList<Contact>();
        try {
            FileInputStream inputStream = new FileInputStream(super.getFileName());
            DataInputStream dataStream = new DataInputStream(inputStream);
            try {
                while(dataStream.available() > 0) {
                    byte[] nameBytes = new byte[dataStream.readInt()];
                    dataStream.readFully(nameBytes);
                    String name = new String(nameBytes, "UTF-8");
                    Integer phoneNumber = dataStream.readInt();
                    myList.add(Contact.createContact(name, phoneNumber));
                }

                dataStream.close();
                inputStream.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
            
        } catch (FileNotFoundException e) {
            System.err.println("File not found. Please try again");
            System.exit(1);
        }
        return myList;
    }

    @Override
    public boolean saveContacts(List<Contact> list) {
        try {
            FileOutputStream file = new FileOutputStream(super.getFileName());
            DataOutputStream printer = new DataOutputStream(file);
            for (Contact contact : list){
                convertNextContact(printer, contact);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return true;
    }

    private void convertNextContact(DataOutputStream printer, Contact contact) {
        byte[] nameInBytes;
        try {
            nameInBytes = contact.getName().getBytes("UTF-8");
            printer.writeInt(nameInBytes.length);
            printer.write(nameInBytes);
            printer.writeInt(contact.getNumero());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
