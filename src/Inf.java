import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.Cursor;
import java.awt.Dialog.ModalityType;
import java.awt.Color;
import javax.swing.border.LineBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;

public class Inf extends JDialog {

	private final JPanel contentPanel = new JPanel();
	public static Inf dialog = new Inf();
	public static JLabel LblContacto, LblNum, LblCorreo, LblUbicacion, LblEmpresa;
	private JLabel LblDFAV;

	public static void main(String[] args) {
		try {
			
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Inf() {
		setModalityType(ModalityType.APPLICATION_MODAL);
		setUndecorated(true);
		setBounds(100, 100, 378, 230);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel LblAceptar = new JLabel("Aceptar");
		LblAceptar.setForeground(Color.WHITE);
		LblAceptar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				dispose();
				
			}
		});
		
		LblContacto = new JLabel("Contacto:");
		LblContacto.setForeground(Color.BLACK);
		LblContacto.setFont(new Font("Segoe UI", Font.BOLD, 18));
		LblContacto.setBounds(35, 50, 175, 25);
		contentPanel.add(LblContacto);
	
		LblAceptar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		LblAceptar.setFont(new Font("SansSerif", Font.BOLD, 11));
		LblAceptar.setBounds(169, 169, 46, 35);
		contentPanel.add(LblAceptar);
		
		JLabel BtnAceptar = new JLabel("");
		BtnAceptar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				dispose();
				
			}
		});
		BtnAceptar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		BtnAceptar.setIcon(new ImageIcon(Inf.class.getResource("/Img/768px-Minus_font_awesome.svg.png")));
		BtnAceptar.setBounds(140, 169, 100, 35);
		contentPanel.add(BtnAceptar);
		
		JLabel LblFondo = new JLabel("");
		LblFondo.setBorder(null);
		LblFondo.setBackground(Color.WHITE);
		LblFondo.setIcon(new ImageIcon(Inf.class.getResource("/Img/marcos de luz simples png (1).png")));
		LblFondo.setBounds(0, 0, 378, 230);
		contentPanel.add(LblFondo);
		
		LblNum = new JLabel("+52 312 106 8217");
		LblNum.setForeground(Color.BLACK);
		LblNum.setFont(new Font("Segoe UI", Font.BOLD, 18));
		LblNum.setBounds(35, 75, 175, 25);
		contentPanel.add(LblNum);
		
		LblCorreo = new JLabel("luis2004_milanes@hotmail.com");
		LblCorreo.setForeground(Color.BLACK);
		LblCorreo.setFont(new Font("Segoe UI", Font.BOLD, 18));
		LblCorreo.setBounds(35, 100, 302, 25);
		contentPanel.add(LblCorreo);
		
		LblUbicacion = new JLabel("Tlahuelilpan, Hidalgo, M\u00E9xico.");
		LblUbicacion.setForeground(Color.BLACK);
		LblUbicacion.setFont(new Font("Segoe UI", Font.BOLD, 18));
		LblUbicacion.setBounds(35, 133, 302, 25);
		contentPanel.add(LblUbicacion);
		
		LblDFAV = new JLabel("D'FAV Inc. 2022");
		LblDFAV.setForeground(Color.BLACK);
		LblDFAV.setFont(new Font("Segoe UI", Font.BOLD, 18));
		LblDFAV.setBounds(35, 11, 204, 47);
		contentPanel.add(LblDFAV);
		
	}
}
