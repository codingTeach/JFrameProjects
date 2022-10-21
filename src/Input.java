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
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Input extends JDialog {

	private final JPanel contentPanel = new JPanel();
	public static Input dialog;
	public static JLabel LblError, LblTexto, BtnAceptar;
	public static String yn = "";
	private JTextField TxtInput;

	public static void main(String[] args) {
		try {
			dialog = new Input();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Input() {
		setTitle("Informacion");
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
		LblAceptar.setHorizontalAlignment(SwingConstants.CENTER);
		LblAceptar.setForeground(Color.WHITE);
		LblAceptar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				Input.yn = "yes";
				
				if(TxtInput.getText().equals("benja123")) {
					
					yn = "usuarios";
					
				}
				dispose();
				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				
				BtnAceptar.setIcon(new ImageIcon(JOPRe.class.getResource("/Img/768px-Minus_font_awesome.svg - copia2.png")));
				BtnAceptar.setBounds(237, 166, 105, 37);
				LblAceptar.setBounds(237, 166, 105, 37);
				
			}
			@Override
			public void mouseExited(MouseEvent e) {
				
				BtnAceptar.setIcon(new ImageIcon(JOPRe.class.getResource("/Img/768px-Minus_font_awesome.svg.png")));
				BtnAceptar.setBounds(237, 168, 100, 35);
				LblAceptar.setBounds(237, 168, 100, 35);
				
			}
		});
		
		TxtInput = new JTextField();
		TxtInput.setVisible(false);
		TxtInput.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 18));
		TxtInput.setBorder(null);
		TxtInput.setBounds(60, 124, 262, 33);
		contentPanel.add(TxtInput);
		TxtInput.setColumns(10);
		
		JLabel LblLinea = new JLabel("");
		LblLinea.setBorder(new LineBorder(new Color(0, 0, 0)));
		LblLinea.setBounds(60, 156, 262, 2);
		contentPanel.add(LblLinea);
		
		LblTexto = new JLabel("");
		LblTexto.setForeground(Color.BLACK);
		LblTexto.setHorizontalAlignment(SwingConstants.CENTER);
		LblTexto.setFont(new Font("Segoe UI", Font.BOLD, 18));
		LblTexto.setBounds(50, 95, 287, 25);
		contentPanel.add(LblTexto);
		
		JLabel BtnDenegar = new JLabel("");
		JLabel LblDenegar = new JLabel("Cancelar");
		LblDenegar.setToolTipText("Click para cancelar");
		LblDenegar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				yn = "cancelar";
				dispose();
				
			}
			@Override
			public void mouseExited(MouseEvent e) {
				
				BtnDenegar.setIcon(new ImageIcon(JOPRe.class.getResource("/Img/768px-Minus_font_awesome.svg - copia.png")));
				BtnDenegar.setBounds(50, 168, 100, 35);
				LblDenegar.setBounds(50, 168, 100, 35);
				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				
				BtnDenegar.setIcon(new ImageIcon(JOPRe.class.getResource("/Img/768px-Minus_font_awesome.svg - copia - copia.png")));
				BtnDenegar.setBounds(50, 166, 105, 37);
				LblDenegar.setBounds(50, 166, 105, 37);
				
			}
		});
		LblDenegar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		LblDenegar.setHorizontalAlignment(SwingConstants.CENTER);
		LblDenegar.setForeground(Color.WHITE);
		LblDenegar.setFont(new Font("SansSerif", Font.BOLD, 11));
		LblDenegar.setBounds(50, 168, 100, 35);
		contentPanel.add(LblDenegar);
		
		BtnDenegar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				yn = "cancelar";
				dispose();
				
			}
		});
		BtnDenegar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		BtnDenegar.setIcon(new ImageIcon(Input.class.getResource("/Img/768px-Minus_font_awesome.svg - copia.png")));
		BtnDenegar.setBounds(50, 168, 100, 35);
		contentPanel.add(BtnDenegar);
		
		LblError = new JLabel("");
		LblError.setIcon(new ImageIcon(Input.class.getResource("/Img/warning.png")));
		LblError.setBounds(170, 38, 40, 40);
		contentPanel.add(LblError);
		LblAceptar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		LblAceptar.setFont(new Font("SansSerif", Font.BOLD, 11));
		LblAceptar.setBounds(237, 168, 100, 35);
		contentPanel.add(LblAceptar);
		
		BtnAceptar = new JLabel("");
		BtnAceptar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				yn = "yes";
				if(TxtInput.getText().equals("benja123")) {
					
					yn = "usuarios";
					
				}
				dispose();
				
			}
		});
		BtnAceptar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		BtnAceptar.setIcon(new ImageIcon(Input.class.getResource("/Img/768px-Minus_font_awesome.svg.png")));
		BtnAceptar.setBounds(237, 168, 100, 35);
		contentPanel.add(BtnAceptar);
		
		JLabel LblFondo = new JLabel("");
		LblFondo.setBorder(null);
		LblFondo.setBackground(Color.WHITE);
		LblFondo.setIcon(new ImageIcon(Input.class.getResource("/Img/marcos de luz simples png (1).png")));
		LblFondo.setBounds(0, 0, 378, 230);
		contentPanel.add(LblFondo);
		
		if(Principal.input == ("eliminar")) {
			
			TxtInput.setVisible(false);
			LblTexto.setText("¿Deseas eliminar el Registro?");
			
		}else if(Principal.input == ("clave")) {
			
			TxtInput.setVisible(true);
			LblTexto.setText("Ingresa la clave para continuar.");
			
		}else if(Principal.input == ("sesion")) {
			
			TxtInput.setVisible(false);
			LblTexto.setText("¿Deseas cerrar la sesión?");
			
		}else if(Principal.input == ("apagar")) {
			
			TxtInput.setVisible(false);
			LblTexto.setText("¿Deseas cerrar el programa?");
			
		}
		
	}
}
