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

    public static void deleteContact(String nombreBorrar) {
        try {
            File file = new File(FILE_NAME);
            if (!file.exists()) {
                return;
            }

            RandomAccessFile raf = new RandomAccessFile(file, "rw");
            ArrayList<String> lineasGuardar = new ArrayList<>();

            // Leer todas las líneas excepto la que vamos a borrar
            while (raf.getFilePointer() < raf.length()) {
                String linea = raf.readLine();
                String[] split = linea.split("!");
                if (!split[0].equals(nombreBorrar)) {
                    lineasGuardar.add(linea);
                }
            }

            // Limpiar el archivo y reescribir
            raf.setLength(0);
            for (String l : lineasGuardar) {
                raf.writeBytes(l + System.lineSeparator());
            }
            raf.close();
            javax.swing.JOptionPane.showMessageDialog(null, "Contacto Eliminado");
        } catch (IOException e) {
            System.out.println("Error al eliminar: " + e.getMessage());
        }
    }

    public static void updateContact(String nombreViejo, Contact contactoEditado) {
        try {
            File file = new File(FILE_NAME);
            if (!file.exists()) {
                return;
            }

            RandomAccessFile raf = new RandomAccessFile(file, "rw");
            ArrayList<String> lineasGuardar = new ArrayList<>();

            // Leer y reemplazar los datos del contacto viejo
            while (raf.getFilePointer() < raf.length()) {
                String linea = raf.readLine();
                String[] split = linea.split("!");
                if (split[0].equals(nombreViejo)) {
                    // Metemos los datos nuevos
                    lineasGuardar.add(contactoEditado.getNombre() + "!" + contactoEditado.getNumero());
                } else {
                    lineasGuardar.add(linea);
                }
            }

            // Limpiar y reescribir
            raf.setLength(0);
            for (String l : lineasGuardar) {
                raf.writeBytes(l + System.lineSeparator());
            }
            raf.close();
            javax.swing.JOptionPane.showMessageDialog(null, "Contacto Actualizado");
        } catch (IOException e) {
            System.out.println("Error al editar: " + e.getMessage());
        }
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
    
        public static boolean checkVariableSize(String data){
        try{
            Long.parseLong(data);
            if(data.length() >10){
                return false;
            }else{
                return true;
            }
                
            }catch(NumberFormatException e){
                if(data.length() >17){
                    return false;
                }else{
                    return true;
                }
                
            }
        }
        

}
