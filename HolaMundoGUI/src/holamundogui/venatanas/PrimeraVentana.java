/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package holamundogui.venatanas;

import java.io.File;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author Philip
 */
public class PrimeraVentana extends javax.swing.JFrame
{
    private File editFile;

    /**
     * Creates new form PrimeraVentana
     */
    public PrimeraVentana()
    {
        editFile = null;
        initComponents();

        setTitle(getTitle() + " [Archivo Nuevo]");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        pnlToolBar = new javax.swing.JPanel();
        btnCargar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtTexto = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Mi Editor");
        setAlwaysOnTop(true);

        pnlToolBar.setPreferredSize(new java.awt.Dimension(640, 68));
        pnlToolBar.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        btnCargar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Edit.png"))); // NOI18N
        btnCargar.setToolTipText("Cargar Archivo");
        btnCargar.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnCargarActionPerformed(evt);
            }
        });
        pnlToolBar.add(btnCargar);

        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Save.png"))); // NOI18N
        btnGuardar.setToolTipText("Guardar Archivo");
        btnGuardar.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnGuardarActionPerformed(evt);
            }
        });
        pnlToolBar.add(btnGuardar);

        getContentPane().add(pnlToolBar, java.awt.BorderLayout.PAGE_START);

        txtTexto.setColumns(20);
        txtTexto.setRows(22);
        jScrollPane1.setViewportView(txtTexto);

        getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnGuardarActionPerformed
    {//GEN-HEADEREND:event_btnGuardarActionPerformed
        if (editFile == null)
        {
            JFileChooser fc = new JFileChooser();
            if (fc.showDialog(this, "Guardar") != JFileChooser.CANCEL_OPTION)
                editFile = fc.getSelectedFile();
        }
        if (editFile != null)
        {
            FileWriter fw = null;
            try
            {
                fw = new FileWriter(editFile);
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(txtTexto.getText());
                bw.close();
                setTitle("Mi Editor [" + editFile.getName() + "]");
            }
            catch (IOException ex)
            {
                Logger.getLogger(PrimeraVentana.class.getName()).log(Level.SEVERE, null, ex);
            }
            finally
            {
                try
                {
                    fw.close();
                }
                catch (IOException ex)
                {
                    Logger.getLogger(PrimeraVentana.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnCargarActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnCargarActionPerformed
    {//GEN-HEADEREND:event_btnCargarActionPerformed
        JFileChooser fc = new JFileChooser();
        fc.setFileFilter(new javax.swing.filechooser.FileFilter()
        {
            @Override
            public boolean accept(File f)
            {
                return (f.isFile() && f.getName().endsWith(".txt"))||f.isDirectory();
            }

            @Override
            public String getDescription()
            {
                return "Archivos de Texto";
            }
        });

        if (fc.showDialog(this, "Abrir") != JFileChooser.CANCEL_OPTION)
        {
            FileReader fr = null;
            try
            {
                editFile = fc.getSelectedFile();
                fr = new FileReader(editFile);
                BufferedReader br = new BufferedReader(fr);
                StringBuffer buff = new StringBuffer();
                String aux = br.readLine();
                while (aux != null)
                {
                    buff.append(aux + "\n");
                    aux = br.readLine();
                }
                br.close();
                txtTexto.setText(buff.toString());
                setTitle("Mi Editor [" + editFile.getName() + "]");
            }
            catch (FileNotFoundException ex)
            {
                JOptionPane.showMessageDialog(this, "El archivo no existe!!!", "Error", JOptionPane.ERROR_MESSAGE);
                Logger.getLogger(PrimeraVentana.class.getName()).log(Level.SEVERE, null, ex);
            }
            catch (IOException ex)
            {
                Logger.getLogger(PrimeraVentana.class.getName()).log(Level.SEVERE, null, ex);
            }
            finally
            {
                try
                {
                    fr.close();
                }
                catch (IOException ex)
                {
                    Logger.getLogger(PrimeraVentana.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_btnCargarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[])
    {
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try
        {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels())
            {
                if ("Nimbus".equals(info.getName()))
                {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        }
        catch (ClassNotFoundException ex)
        {
            java.util.logging.Logger.getLogger(PrimeraVentana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(PrimeraVentana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(PrimeraVentana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(PrimeraVentana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                new PrimeraVentana().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCargar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel pnlToolBar;
    private javax.swing.JTextArea txtTexto;
    // End of variables declaration//GEN-END:variables
}