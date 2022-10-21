import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class JOPRe extends JDialog {

	private final JPanel contentPanel = new JPanel();
	public static JOPRe dialog;
	public static JLabel LblError, LblTexto;

	public static void main(String[] args) {
		try {
			dialog = new JOPRe();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public JOPRe() {
		setTitle("Error");
		setModalityType(ModalityType.APPLICATION_MODAL);
		setUndecorated(true);
		setBounds(100, 100, 378, 230);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel LblAceptar = new JLabel("Aceptar");
		LblAceptar.setToolTipText("Click para aceptar");
		LblAceptar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				
				Character c = e.getKeyChar();
				
				if(c == KeyEvent.VK_ENTER) {
					
					dispose();
					
				}
				
			}
		});
		JLabel BtnAceptar = new JLabel("");
		LblAceptar.setHorizontalAlignment(SwingConstants.CENTER);
		LblAceptar.setForeground(Color.WHITE);
		LblAceptar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				dispose();
				
			}
			@Override
			public void mouseExited(MouseEvent e) {
				
				BtnAceptar.setIcon(new ImageIcon(JOPRe.class.getResource("/Img/768px-Minus_font_awesome.svg.png")));
				BtnAceptar.setBounds(140, 169, 100, 35);
				LblAceptar.setBounds(140, 169, 100, 35);
				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				
				BtnAceptar.setIcon(new ImageIcon(JOPRe.class.getResource("/Img/768px-Minus_font_awesome.svg - copia2.png")));
				BtnAceptar.setBounds(140, 167, 105, 37);
				LblAceptar.setBounds(140, 167, 105, 37);
				
			}
		});
		
		LblError = new JLabel("");
		LblError.setIcon(new ImageIcon(JOPRe.class.getResource("/Img/borrar.png")));
		LblError.setBounds(170, 38, 40, 40);
		contentPanel.add(LblError);
		LblAceptar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		LblAceptar.setFont(new Font("SansSerif", Font.BOLD, 11));
		LblAceptar.setBounds(140, 169, 100, 35);
		contentPanel.add(LblAceptar);
		
		BtnAceptar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				dispose();
				
			}
		});
		BtnAceptar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		BtnAceptar.setIcon(new ImageIcon(JOPRe.class.getResource("/Img/768px-Minus_font_awesome.svg.png")));
		BtnAceptar.setBounds(140, 169, 100, 35);
		contentPanel.add(BtnAceptar);
		
		JLabel LblFondo = new JLabel("");
		LblFondo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				
				Character c = e.getKeyChar();
				
				if(c == KeyEvent.VK_ENTER) {
					
					dispose();
					
				}
				
			}
		});
		LblFondo.setBorder(null);
		LblFondo.setBackground(Color.WHITE);
		LblFondo.setIcon(new ImageIcon(JOPRe.class.getResource("/Img/marcos de luz simples png (1).png")));
		LblFondo.setBounds(0, 0, 378, 230);
		contentPanel.add(LblFondo);
		
		LblTexto = new JLabel("");
		LblTexto.setForeground(Color.BLACK);
		LblTexto.setHorizontalAlignment(SwingConstants.CENTER);
		LblTexto.setFont(new Font("Segoe UI", Font.BOLD, 18));
		LblTexto.setBounds(10, 101, 358, 25);
		contentPanel.add(LblTexto);
		
		if(Principal.jopre == ("eliminar")) {
			
			LblTexto.setText("No se ha seleccionado un registro");
			
		}else if(Principal.jopre == ("login")) {
			
			LblTexto.setText("Usuario o Clave Incorrectos.");
			
		}else if(Principal.jopre == ("invalido")) {
			
			LblTexto.setText("No ingresaste datos validos.");
			
		}else if(Principal.jopre == ("datos")) {
			
			LblTexto.setText("No se encontr\u00f3 ningun dato");
			
		}else if(Principal.jopre == ("error")) {
			
			LblTexto.setText("Error, fallo inesperado.");
			
		}else if(Principal.jopre == ("fallo")) {
			
			LblTexto.setText("No se pudo Eliminar.");
			
		}else if(Principal.jopre == ("exito")) {
			
			LblError.setBounds(170, 38, 45, 45);
			LblError.setIcon(new ImageIcon(JOPRe.class.getResource("/Img/aceptar - copia.png")));
			LblTexto.setText("Se aplicaron los cambios.");
			
		}else if(Principal.jopre == ("vacio")) {
			
			LblTexto.setText("No se pudo realizar el cambio.");
			
		}else if(Principal.jopre == ("repetido")) {
			
			LblTexto.setText("Registro repetido. Se agregó al stock.");
			
		}
		
		
		
	}
}
