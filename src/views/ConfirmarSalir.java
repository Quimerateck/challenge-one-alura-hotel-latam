package views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import java.awt.Toolkit;

public class ConfirmarSalir extends JDialog{
    
    private JPanel exitPanel = new JPanel();

    /*
     * lanza la aplicacion
     */
    public static void main(String[] args) {
		try {
			ConfirmarSalir dialog = new ConfirmarSalir();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

    /*
     * genera el dialogo de salida del programa
     */
    public ConfirmarSalir(){
        setIconImage(Toolkit.getDefaultToolkit().getImage(Exito.class.getResource("/imagenes/aH-40px.png")));
		setBounds(100, 100, 394, 226);
		getContentPane().setLayout(new BorderLayout());
		exitPanel.setBackground(SystemColor.control);
		exitPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(exitPanel, BorderLayout.CENTER);
		setLocationRelativeTo(null);
		exitPanel.setLayout(null);
        {
			JLabel lblNewLabel = new JLabel("");
			lblNewLabel.setIcon(new ImageIcon(Exito.class.getResource("/imagenes/Ha-100px.png")));
			lblNewLabel.setBounds(140, 11, 100, 100);
			exitPanel.add(lblNewLabel);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("¿Desea cerrar el programa?");
			lblNewLabel_1.setForeground(new Color (12, 138, 199));
			lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 18));
			lblNewLabel_1.setBounds(65, 122, 322, 21);
			exitPanel.add(lblNewLabel_1);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{   
                /*
                 * boton aceptar
                 */
				JButton okButton = new JButton("Confirmar");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();//sirve para cerrar la ventana actual
                        System.exit(0);
					}
				});
				okButton.setActionCommand("Confirmar");
				buttonPane.add(okButton);
                getRootPane().setDefaultButton(okButton);
			}
			{
                /* 
                 * boton cancelar
                 */
                JButton cancelButton = new JButton("Cancel");
                cancelButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e){
                        dispose();
                    }
                });
                cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
    }

}   
