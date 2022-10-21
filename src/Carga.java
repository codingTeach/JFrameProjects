import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JProgressBar;

public class Carga extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private static Thread tiempo = null;
	public static Timer t;

	public static JProgressBar ProgressBar = new JProgressBar();;
	
	public static void main(String[] args) {
		try {
			Carga dialog = new Carga();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			dialog.setLocationRelativeTo(null);
			
			//Bucle que aumenta el progreso de carga hasta que llegue a 99, de 2 en 2.
			int i = 0;
			while(i < 100) {

				Thread.sleep(i);
				ProgressBar.setValue(i);
				i+=2;
				
			}
			
				
				dialog.dispose();
				Principal.frame = new Principal();
				Principal.frame.setVisible(true);
				Principal.frame.setLocationRelativeTo(null);
				
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Carga() {
		setUndecorated(true);
		setBounds(100, 100, 450, 283);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		ProgressBar.setBorderPainted(false);
		ProgressBar.setBorder(new LineBorder(new Color(0, 0, 0)));
		ProgressBar.setBackground(Color.DARK_GRAY);
		ProgressBar.setBounds(0, 250, 450, 9);
		contentPanel.add(ProgressBar);
		
		JLabel LblBorde = new JLabel("");
		LblBorde.setBorder(new LineBorder(new Color(0, 0, 0)));
		LblBorde.setHorizontalAlignment(SwingConstants.CENTER);
		LblBorde.setIcon(new ImageIcon(Carga.class.getResource("/Img/cool-loading-animated-gif-1.gif")));
		LblBorde.setBounds(0, 0, 450, 283);
		contentPanel.add(LblBorde);
		
		JLabel LblFondo = new JLabel("");
		LblFondo.setBorder(new LineBorder(new Color(0, 0, 0)));
		LblFondo.setIcon(new ImageIcon(Carga.class.getResource("/Img/DgDlYGF.gif")));
		LblFondo.setBounds(0, 0, 450, 283);
		contentPanel.add(LblFondo);
	}
}
