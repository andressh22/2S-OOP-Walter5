package contactsmanagement;

public class Contact {

    private String nombre;
    private String numero;

    public Contact(String nombre, String numero) {
        this.nombre = nombre;
        this.numero = numero;
    }

    public String getNombre() {
        return nombre;
    }

    public String getNumero() {
        return numero;
    }

    public void setNombre(String nombre) {
        if (nombre != null && !nombre.isBlank()) {
            this.nombre = nombre;
        } else {
            System.out.println("Error:El nombre no puede estar vacio.");
        }
    }

    public void setNumero(String numero) {
        if (numero != null && !numero.isBlank()) {
            this.numero = numero;
        } else {
            System.out.println("Error:El numero no puede estar vacio.");
        }
    }
}
