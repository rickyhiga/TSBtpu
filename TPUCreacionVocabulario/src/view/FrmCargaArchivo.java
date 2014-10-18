/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;

/**
 *
 * @author a5
 */
public class FrmCargaArchivo extends javax.swing.JFrame {

    private JPanel jContentPane = null;
    private JButton btnStartWorker = null;
    private JProgressBar bar = null;
    private JLabel jLabel = null;
    private JLabel lblCompletado = null;
    private JLabel lblFinish = null;
    private JButton btnCancelarWorker = null;
    private MiWorker trabajador;
    /**
     * Creates new form FrmCargaArchivo
     */
    public FrmCargaArchivo() {
        initComponents();
        initialize();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    private JButton getBtnStartWorker() {
		if (btnStartWorker == null) {
			btnStartWorker = new JButton();
			btnStartWorker.setBounds(new java.awt.Rectangle(11,11,117,29));
			btnStartWorker.setText("Start Worker");
			btnStartWorker.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(trabajador==null){
						trabajador = new MiWorker();
						
						//Agrego un Listener para el cambio de la propiedad "progress"
						trabajador.addPropertyChangeListener(new PropertyChangeListener(){
							public void propertyChange(PropertyChangeEvent evt) {
								if ("progress".equals(evt.getPropertyName())) {
					                 lblCompletado.setText(evt.getNewValue() + " %");
					            }
							}
						});
						}
                                        //TAMANO DEL ARCHIVO
                                                trabajador.setSize(10);
						trabajador.execute();
					}
			});
		}
		return btnStartWorker;
	}

	/**
	 * This method initializes bar	
	 * 	
	 * @return javax.swing.JProgressBar	
	 */
	private JProgressBar getBar() {
		if (bar == null) {
			bar = new JProgressBar();
			bar.setBounds(new java.awt.Rectangle(10,58,276,25));
		}
		return bar;
	}

	/**
	 * This method initializes btnCancelarWorker	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnCancelarWorker() {
		if (btnCancelarWorker == null) {
			btnCancelarWorker = new JButton();
			btnCancelarWorker.setBounds(new java.awt.Rectangle(137,11,136,29));
			btnCancelarWorker.setText("Cancel Worker");
			btnCancelarWorker.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(trabajador!=null){
						trabajador.cancel(false);
						trabajador = null;
					}
				}
			});
		}
		return btnCancelarWorker;
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(300, 200);
		this.setContentPane(getJContentPane());
		this.setTitle("Cargando de Archivos");
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			lblFinish = new JLabel();
			lblFinish.setBounds(new java.awt.Rectangle(93,134,153,28));
			lblFinish.setText("");
			lblCompletado = new JLabel();
			lblCompletado.setBounds(new java.awt.Rectangle(94,108,39,20));
			lblCompletado.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
			lblCompletado.setText("0%");
			jLabel = new JLabel();
			jLabel.setBounds(new java.awt.Rectangle(17,108,74,19));
			jLabel.setText("Completado:");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getBtnStartWorker(), null);
			
                        jContentPane.add(getBar(), null);
			jContentPane.add(jLabel, null);
			jContentPane.add(lblCompletado, null);
			jContentPane.add(lblFinish, null);
			jContentPane.add(getBtnCancelarWorker(), null);
		}
		return jContentPane;
	}

	/*
	 * Esta es mi inner class "MiWorker", si se fijan, estoy
	 * instrumentando Generics para decirle a esta clase, que deber�
	 * retornar del "doInBackGround" un String, y que los dem�s m�todos
	 * no deben retornar nada (void)
	 */
	class MiWorker extends SwingWorker<String, Void>{
                int size=0;
                protected void setSize(int size){
                    this.size=size;
                }
                protected int getSize(){
                    return size;
                }
		@Override
		protected String doInBackground() throws Exception {
			int i=0;
			
			while(i<=100 && !isCancelled()){
				bar.setValue(i);
				setProgress(i);
				i++;
				Thread.sleep(size);
			}
			
			return "Operacion finalizada";
		}
		
		@Override
		public void done(){
			try {
				lblFinish.setText(get());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmCargaArchivo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmCargaArchivo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmCargaArchivo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmCargaArchivo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmCargaArchivo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
