package contactsmanagement;

import java.io.File;
import java.io.RandomAccessFile;
import java.io.IOException;
import java.util.ArrayList;

public class ContactsManagement {

    private static final String FILE_NAME = "friendsContact.txt";

    public static ArrayList<Contact> getContacts() {
        ArrayList<Contact> list = new ArrayList<>();

        try {
            File file = new File(FILE_NAME);
            if (!file.exists()) {
                return list;
            }

            RandomAccessFile raf = new RandomAccessFile(file, "rw");

            while (raf.getFilePointer() < raf.length()) {
                String lineaDelArchivo = raf.readLine();

                String[] lineSplit = lineaDelArchivo.split("!");

                String nombre = lineSplit[0];
                String numero = lineSplit[1];

                Contact c = new Contact(nombre, numero);

                list.add(c);
            }

            raf.close();

        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }

        return list;
    }

    public static void addContact(Contact newContact) {
        try {
            File file = new File(FILE_NAME);

            if (!file.exists()) {
                file.createNewFile();
            }

            RandomAccessFile raf = new RandomAccessFile(file, "rw");
            boolean found = false;

            while (raf.getFilePointer() < raf.length()) {
                String lineaDelArchivo = raf.readLine();

                String[] lineSplit = lineaDelArchivo.split("!");
                String nombreEnArchivo = lineSplit[0];
                String numeroEnArchivo = lineSplit[1];

                if (nombreEnArchivo.equals(newContact.getNombre()) || numeroEnArchivo.equals(newContact.getNumero())) {
                    found = true;
                    break;
                }
            }

            if (found == false) {
                String lineaNueva = newContact.getNombre() + "!" + newContact.getNumero();

                raf.writeBytes(lineaNueva);
                raf.writeBytes(System.lineSeparator());

                javax.swing.JOptionPane.showMessageDialog(null, "Contacto Creado");
            } else {
                javax.swing.JOptionPane.showMessageDialog(null, "Este Contacto ya existe");
            }

            raf.close();

        } catch (IOException e) {
            System.out.println("Error al manejar el archivo: " + e.getMessage());
        }
    }

}
