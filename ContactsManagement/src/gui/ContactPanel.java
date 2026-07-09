package gui;

import contactsmanagement.Contact;
import contactsmanagement.ContactsManagement;

public class ContactPanel extends javax.swing.JPanel {

    private Contact contactoActual;

    public ContactPanel() {
        initComponents();

    }

    public void setPanelData(Contact contacto) {
        this.contactoActual = contacto;
        Nombre.setText(contacto.getNombre());
        Numero.setText(contacto.getNumero());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ContactContainer = new javax.swing.JPanel();
        Nombre = new javax.swing.JLabel();
        Numero = new javax.swing.JLabel();
        Editar = new javax.swing.JButton();
        Eliminar = new javax.swing.JButton();

        ContactContainer.setBackground(new java.awt.Color(255, 227, 85));
        ContactContainer.setPreferredSize(new java.awt.Dimension(244, 86));

        Nombre.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        Nombre.setForeground(new java.awt.Color(0, 0, 102));
        Nombre.setText("Cristo");

        Numero.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Numero.setForeground(new java.awt.Color(0, 0, 102));
        Numero.setText("301 295865489");

        Editar.setText("Editar");
        Editar.setMinimumSize(new java.awt.Dimension(77, 27));
        Editar.addActionListener(this::EditarActionPerformed);

        Eliminar.setText("Eliminar");
        Eliminar.addActionListener(this::EliminarActionPerformed);

        javax.swing.GroupLayout ContactContainerLayout = new javax.swing.GroupLayout(ContactContainer);
        ContactContainer.setLayout(ContactContainerLayout);
        ContactContainerLayout.setHorizontalGroup(
            ContactContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ContactContainerLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(ContactContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ContactContainerLayout.createSequentialGroup()
                        .addComponent(Numero)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(ContactContainerLayout.createSequentialGroup()
                        .addComponent(Nombre)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 150, Short.MAX_VALUE)
                        .addComponent(Editar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Eliminar)
                        .addGap(16, 16, 16))))
        );
        ContactContainerLayout.setVerticalGroup(
            ContactContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ContactContainerLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(ContactContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Nombre)
                    .addComponent(Editar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Eliminar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Numero)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(ContactContainer, javax.swing.GroupLayout.PREFERRED_SIZE, 389, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(ContactContainer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void EliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EliminarActionPerformed
        if (contactoActual != null) {
            int confirmacion = javax.swing.JOptionPane.showConfirmDialog(
                    this,
                    "¿Seguro que deseas eliminar a " + contactoActual.getNombre() + "?",
                    "Confirmar Eliminación",
                    javax.swing.JOptionPane.YES_NO_OPTION
            );

            if (confirmacion == javax.swing.JOptionPane.YES_OPTION) {

                contactsmanagement.ContactsManagement.deleteContact(contactoActual.getNombre());

                java.awt.Window win = javax.swing.SwingUtilities.getWindowAncestor(this);
                if (win instanceof ContactsList) {
                    ((ContactsList) win).showContactsList();
                }
            }
        }
    }//GEN-LAST:event_EliminarActionPerformed

    private void EditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditarActionPerformed
        if (contactoActual != null) {

            String nuevoNombre = javax.swing.JOptionPane.showInputDialog(this, "Editar Nombre:", contactoActual.getNombre());
            if (nuevoNombre == null) {
                return;
            }
            nuevoNombre = nuevoNombre.trim();

            if (nuevoNombre.isEmpty()) {
                javax.swing.JOptionPane.showMessageDialog(this, "El nombre no puede estar vacío.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
                return;
            }

            String nuevoNumero = javax.swing.JOptionPane.showInputDialog(this, "Editar Teléfono (Solo números):", contactoActual.getNumero());
            if (nuevoNumero == null) {

            }
            nuevoNumero = nuevoNumero.trim();

            if (nuevoNumero.isEmpty()) {
                javax.swing.JOptionPane.showMessageDialog(this, "El teléfono no puede estar vacío.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!nuevoNumero.matches("\\d+")) {
                javax.swing.JOptionPane.showMessageDialog(this, "Error: El teléfono debe contener únicamente números.", "Formato Incorrecto", javax.swing.JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            if(!ContactsManagement.checkVariableSize(nuevoNombre) || !ContactsManagement.checkVariableSize(nuevoNumero)){
                javax.swing.JOptionPane.showMessageDialog(this,
                    "Error: Tamaño máximo de digitos:10 . Tamaño máximo de caracteres:17",
                    "Tamaño de cadena mayor a requerido",
                    javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }

            Contact contactoEditado = new Contact(nuevoNombre, nuevoNumero);
            contactsmanagement.ContactsManagement.updateContact(contactoActual.getNombre(), contactoEditado);

            java.awt.Window win = javax.swing.SwingUtilities.getWindowAncestor(this);
            if (win instanceof ContactsList) {
                ((ContactsList) win).showContactsList();
            }
        }
    }//GEN-LAST:event_EditarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel ContactContainer;
    private javax.swing.JButton Editar;
    private javax.swing.JButton Eliminar;
    private javax.swing.JLabel Nombre;
    private javax.swing.JLabel Numero;
    // End of variables declaration//GEN-END:variables
}
