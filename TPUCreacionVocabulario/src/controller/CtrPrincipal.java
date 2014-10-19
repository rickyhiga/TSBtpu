/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import view.FrmPrincipal;

/**
 *
 * @author a5
 */
public class CtrPrincipal implements ActionListener {

    private File file;
    private FrmPrincipal j;
    
    public CtrPrincipal(){
        try {
            j=new FrmPrincipal();
            j.show();
        } catch (SQLException ex) {
            Logger.getLogger(CtrPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void itemCargarArchivoActionPerformed(java.awt.event.ActionEvent evt) {                                                  
       windowSelect();
    }   
    public void windowSelect() {
        JFileChooser fc = new JFileChooser();
        if (fc.showDialog(j, "Guardar") != JFileChooser.CANCEL_OPTION) {
            file = fc.getSelectedFile();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
                
    }
}
