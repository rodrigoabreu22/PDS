package ex02.src;

import java.io.File;
import java.util.List;

public class Format implements ContactsStorageInterface {
    private File file;
    private String tipo_format;

    public Format(File file) {
        this.file = file;
        String[] parts = file.getName().split("\\.");
        this.tipo_format = parts[1];
    }

    @Override
    public boolean saveContacts(List<Contact> list) {
        ContactsStorageInterface format = null;

        switch (tipo_format) {
            case "txt":
                format = new TxtFormat(file);
                break;
        
            case "bin":
                format = new BinFormat(file);
                break;
        }
        return format.saveContacts(list);
    }

    @Override
    public List<Contact> loadContacts() {
        ContactsStorageInterface format = null;

        switch (tipo_format) {
            case "txt":
                format = new TxtFormat(file);
                break;
        
            case "bin":
                format = new BinFormat(file);
                break;
        }
        return format.loadContacts();
    }

    public String getFileName() {
        return file.getName();
    }
}
