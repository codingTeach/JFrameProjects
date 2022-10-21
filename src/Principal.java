import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.Shape;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.ImageIcon;
import javax.swing.UIManager;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Cursor;
import java.awt.SystemColor;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.RoundRectangle2D;
import java.awt.Frame;

public class Principal extends JFrame {

	private JPanel contentPane;
	private JTextField TxtUsuario;
	private JPasswordField TxtPassword;
	public static Principal frame;
	private int mouseX, mouseY;
	public static String Usuario;
	public static String jopre, input;
	public static String tipo;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new Principal();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
					Principal.jopre = "login";
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	int xMouse, yMouse;
	
	public Principal() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Principal.class.getResource("/Img/admin.png")));
		/*Toolkit tk = Toolkit.getDefaultToolkit();
		Image img = tk.getImage("/Img/cursor.png");
		Cursor c = Toolkit.getDefaultToolkit().createCustomCursor(img, new Point(this.getX(), this.getY()), "img");
		setCursor(c);*/
		setUndecorated(true);
		setBackground(new Color(0, 0, 0, 0));
		setTitle("Colitas");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1057, 535);
		contentPane = new JPanel();
		contentPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		contentPane.setBackground(new Color(0, 0, 0));
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		
		Principal.jopre = "login";
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		UIManager.put("ToolTip.background", Color.black);
		UIManager.put("ToolTip.foreground", Color.white);
		UIManager.put("ToolTip.font", new Font("Century Gothic", Font.PLAIN, 18 ));
		
		JLabel LblIngresar = new JLabel("");
		LblIngresar.setToolTipText("Ingresar al programa");
		LblIngresar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				//Variable de la conexicon
				Connection LaConexion = null;
				
				Statement Sentencia; //lanzar sql
				
				ResultSet Rs;//Atrapar
				
				int Resultado=0;
				
				jopre = "login";
		
				try {
					
					LaConexion = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/loginuser", "root", "LFMG2004");
					
					Sentencia = LaConexion.createStatement();
					
					String sql = "SELECT username, contra FROM usuarios";
					Rs = Sentencia.executeQuery(sql);
					
					Usuario=TxtUsuario.getText();
					String Contra =String.valueOf(TxtPassword.getPassword());
					
					Rs=Sentencia.executeQuery("SELECT username, contra FROM usuarios WHERE username='"+Usuario+"' AND  contra='"+Contra+"'");
					
					if(Rs.next())
					{
						Resultado=1;
					
						if(Resultado==1)
						{

							Principal.frame.dispose();
							
							
							LaConexion = null;
							try {
								LaConexion = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/loginuser", "root", "LFMG2004");
							} catch (SQLException e2) {
								// TODO Auto-generated catch block
								e2.printStackTrace();
							}
							
							try {
								Sentencia = LaConexion.createStatement();
								Rs = Sentencia.executeQuery("SELECT tipo FROM usuarios WHERE username='"+Principal.Usuario+"'");
								
								while(Rs.next()) {
									
									tipo = Rs.getString("tipo");
								}
								Menu.dialog.setLocationRelativeTo(null);
								Menu.dialog.setVisible(true);
								Menu.dialog.setEnabled(true);
								
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}

						}else {
							
							jopre = "login";
							JOPRe.dialog = new JOPRe();
							JOPRe.dialog.setLocationRelativeTo(null);
							JOPRe.dialog.setVisible(true);
							//JOptionPane.showMessageDialog(null,"Usuario o Contrase\u00f1a Inv\u00e1lidos");
								TxtUsuario.setText("Usuario");
							TxtUsuario.setForeground(Color.DARK_GRAY);
							TxtUsuario.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 58));
							TxtPassword.setText("hola");
							TxtPassword.setForeground(Color.DARK_GRAY);
							TxtPassword.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 72));
							TxtUsuario.requestFocus();
							
						}
						
					}else {
						
						jopre = "login";
						JOPRe.dialog = new JOPRe();
						JOPRe.dialog.setLocationRelativeTo(null);
						JOPRe.dialog.setVisible(true);
						TxtUsuario.setText("Usuario");
						TxtUsuario.setForeground(Color.DARK_GRAY);
						TxtUsuario.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 58));
						TxtPassword.setText("hola");
						TxtPassword.setForeground(Color.DARK_GRAY);
						TxtPassword.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 72));
						TxtUsuario.requestFocus();
						
					}
				
					LaConexion.close();
					
				}catch(Exception el){
					
					jopre = "login";
					JOPRe.dialog = new JOPRe();
					JOPRe.dialog.setVisible(true);
					JOPRe.dialog.setLocationRelativeTo(null);
					TxtUsuario.setText("Usuario");
					TxtUsuario.setForeground(Color.DARK_GRAY);
					TxtUsuario.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 58));
					TxtPassword.setText("hola");
					TxtPassword.setForeground(Color.DARK_GRAY);
					TxtPassword.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 72));
					TxtUsuario.requestFocus();
				
				}
				
			}
			@Override
			public void mouseExited(MouseEvent e) {
				
				LblIngresar.setIcon(new ImageIcon(Principal.class.getResource("/Img/login - copia.png")));
				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				
				LblIngresar.setIcon(new ImageIcon(Principal.class.getResource("/Img/login2.png")));
				LblIngresar.setBounds(470, 415, 95, 95);
				
			}
		});
		
		TxtPassword = new JPasswordField();
		TxtPassword.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				
				Character c = e.getKeyChar();
				
				if(c == KeyEvent.VK_ENTER) {
					
					//Variable de la conexicon
					Connection LaConexion = null;
					
					Statement Sentencia; //lanzar sql
					
					ResultSet Rs;//Atrapar
					
					int Resultado=0;
			
					try {
						
						LaConexion = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/loginuser", "root", "LFMG2004");
						
						Sentencia = LaConexion.createStatement();
						
						String sql = "SELECT username, contra FROM usuarios";
						Rs = Sentencia.executeQuery(sql);
						
						Usuario=TxtUsuario.getText();
						String Contra =String.valueOf(TxtPassword.getPassword());
						
						Rs=Sentencia.executeQuery("SELECT username, contra FROM usuarios WHERE username='"+Usuario+"' AND  contra='"+Contra+"'");
						
						if(Rs.next())
						{
							Resultado=1;
						
							if(Resultado==1)
							{

								Principal.frame.dispose();
								
								
								
								LaConexion = null;
								try {
									LaConexion = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/loginuser", "root", "LFMG2004");
								} catch (SQLException e2) {
									// TODO Auto-generated catch block
									e2.printStackTrace();
								}
								
								try {
									Sentencia = LaConexion.createStatement();
									Rs = Sentencia.executeQuery("SELECT tipo FROM usuarios WHERE username='"+Principal.Usuario+"'");
									
									while(Rs.next()) {
										
										tipo = Rs.getString("tipo");
									}
									Menu.dialog.setLocationRelativeTo(null);
									Menu.dialog.setVisible(true);
									Menu.dialog.setEnabled(true);
									
								} catch (SQLException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}

							}else {
								
								jopre = "login";
								JOPRe.dialog = new JOPRe();
								JOPRe.dialog.setLocationRelativeTo(null);
								JOPRe.dialog.setVisible(true);
								TxtUsuario.setText("Usuario");
								TxtUsuario.setForeground(Color.DARK_GRAY);
								TxtUsuario.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 58));
								TxtPassword.setText("hola");
								TxtPassword.setForeground(Color.BLACK);
								TxtPassword.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 72));
								TxtUsuario.requestFocus();
								
							}
							
						}else {
							
							jopre = "login";
							JOPRe.dialog = new JOPRe();
							JOPRe.dialog.setLocationRelativeTo(null);
							JOPRe.dialog.setVisible(true);
							TxtUsuario.setText("Usuario");
							TxtUsuario.setForeground(Color.DARK_GRAY);
							TxtUsuario.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 58));
							TxtPassword.setText("hola");
							TxtPassword.setForeground(Color.BLACK);
							TxtPassword.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 72));
							TxtUsuario.requestFocus();
							
						}
					
						LaConexion.close();
						
					}catch(Exception el){
						
						jopre = "login";
						JOPRe.dialog = new JOPRe();
						JOPRe.dialog.setLocationRelativeTo(null);
						JOPRe.dialog.setVisible(true);
						TxtUsuario.setText("Usuario");
						TxtUsuario.setForeground(Color.DARK_GRAY);
						TxtUsuario.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 58));
						TxtPassword.setText("hola");
						TxtPassword.setForeground(Color.BLACK);
						TxtPassword.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 72));
						TxtUsuario.requestFocus();
					
					}
					
				}
				
			}
		});
		TxtPassword.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				
				if(String.valueOf(TxtPassword.getPassword()).equals("hola") || 
						String.valueOf(TxtPassword.getPassword()).equals("")) {
					
					TxtPassword.setText("hola");
					TxtPassword.setForeground(Color.DARK_GRAY);
					TxtPassword.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 72));
					
				}
				
			}
			@Override
			public void focusGained(FocusEvent e) {
				
				if(String.valueOf(TxtPassword.getPassword()).equals("hola")) {
					
					TxtPassword.setText("");
					TxtPassword.setForeground(Color.BLACK);
					TxtPassword.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 72));
					
				}
				
			}
		});
		TxtPassword.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

				if(String.valueOf(TxtPassword.getPassword()).equals("hola")) {
					
					TxtPassword.setText("");
					TxtPassword.setForeground(Color.BLACK);
					TxtPassword.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 72));
					
				}

			}
		});
		
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		panel.setBorder(null);
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				
				mouseX = e.getX();
				mouseY = e.getY();
				
			}
		});
		panel.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				
				frame.setLocation(frame.getX() + e.getX() - mouseX, frame.getY() + e.getY() - mouseY);
				
			}
		});
		
		JLabel LblSalir = new JLabel("");
		LblSalir.setToolTipText("Click para salir del programa");
		LblSalir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				System.exit(0);
				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				
				LblSalir.setBounds(1000, 7, 45, 45);
				LblSalir.setIcon(new ImageIcon(Principal.class.getResource("/Img/apagar - copia.png")));
				
			}
			@Override
			public void mouseExited(MouseEvent e) {
				
				LblSalir.setIcon(new ImageIcon(Principal.class.getResource("/Img/93627.png")));
				
			}
		});
		
		JLabel LblVersion = new JLabel("V 1.0.0");
		LblVersion.setForeground(Color.BLACK);
		LblVersion.setFont(new Font("Segoe UI", Font.BOLD, 18));
		LblVersion.setBounds(10, 495, 65, 25);
		contentPane.add(LblVersion);
		
		LblSalir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		LblSalir.setIcon(new ImageIcon(Principal.class.getResource("/Img/93627.png")));
		LblSalir.setBounds(1000, 9, 40, 40);
		contentPane.add(LblSalir);
		panel.setBounds(0, 0, 1057, 53);
		contentPane.add(panel);
		
		TxtPassword.setText("hola");
		TxtPassword.setForeground(Color.DARK_GRAY);
		TxtPassword.setOpaque(false);
		TxtPassword.setBorder(null);
		TxtPassword.setBounds(395, 268, 347, 88);
		contentPane.add(TxtPassword);
		TxtPassword.setToolTipText("Ingresa la clave");
		TxtPassword.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 72));
		
		TxtUsuario = new JTextField();
		TxtUsuario.setText("Usuario");
		TxtUsuario.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				
				if(TxtUsuario.getText().equals("Usuario") || TxtUsuario.getText().equals("")) {
					
					TxtUsuario.setText("Usuario");
					TxtUsuario.setForeground(Color.DARK_GRAY);
					TxtUsuario.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 58));
					
				}
				
			}

		});
		TxtUsuario.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				
				if(TxtUsuario.getText().equals("Usuario")) {
					
					TxtUsuario.setText("");
					TxtUsuario.setForeground(Color.BLACK);
					TxtUsuario.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 72));
					
				}
				Character c = e.getKeyChar();
				
				if(c == KeyEvent.VK_ENTER) {
					
					//Variable de la conexicon
					Connection LaConexion = null;
					
					Statement Sentencia; //lanzar sql
					
					ResultSet Rs;//Atrapar
					
					int Resultado=0;
			
					try {
						
						LaConexion = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/loginuser", "root", "LFMG2004");
						
						Sentencia = LaConexion.createStatement();
						
						String sql = "SELECT username, contra FROM usuarios";
						Rs = Sentencia.executeQuery(sql);
						
						Usuario=TxtUsuario.getText();
						String Contra =String.valueOf(TxtPassword.getPassword());
						
						Rs=Sentencia.executeQuery("SELECT username, contra FROM usuarios WHERE username='"+Usuario+"' AND  contra='"+Contra+"'");
						
						if(Rs.next())
						{
							Resultado=1;
						
							if(Resultado==1)
							{

								Principal.frame.dispose();
								

							
								
								LaConexion = null;
								try {
									LaConexion = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/loginuser", "root", "LFMG2004");
								} catch (SQLException e2) {
									// TODO Auto-generated catch block
									e2.printStackTrace();
								}
								
								try {
									Sentencia = LaConexion.createStatement();
									Rs = Sentencia.executeQuery("SELECT tipo FROM usuarios WHERE username='"+Principal.Usuario+"'");
									
									while(Rs.next()) {
										
										tipo = Rs.getString("tipo");
									}
									Menu.dialog.setLocationRelativeTo(null);
									Menu.dialog.setVisible(true);
									Menu.dialog.setEnabled(true);
									
								} catch (SQLException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								
							}else {
								
								jopre = "login";
								JOPRe.dialog = new JOPRe();
								JOPRe.dialog.setLocationRelativeTo(null);
								JOPRe.dialog.setVisible(true);
								TxtUsuario.setText("Usuario");
								TxtUsuario.setForeground(Color.DARK_GRAY);
								TxtUsuario.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 58));
								TxtPassword.setText("hola");
								TxtPassword.setForeground(Color.DARK_GRAY);
								TxtPassword.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 72));
								TxtUsuario.requestFocus();
								
							}
							
						}else {
							
							jopre = "login";
							JOPRe.dialog = new JOPRe();
							JOPRe.dialog.setLocationRelativeTo(null);
							JOPRe.dialog.setVisible(true);
							TxtUsuario.setText("Usuario");
							TxtUsuario.setForeground(Color.DARK_GRAY);
							TxtUsuario.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 58));
							TxtPassword.setText("hola");
							TxtPassword.setForeground(Color.DARK_GRAY);
							TxtPassword.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 72));
							TxtUsuario.requestFocus();
							
						}
					
						LaConexion.close();
						
					}catch(Exception el){
						jopre = "login";
						JOPRe.dialog = new JOPRe();
						JOPRe.dialog.setLocationRelativeTo(null);
						JOPRe.dialog.setVisible(true);
						TxtUsuario.setText("Usuario");
						TxtUsuario.setForeground(Color.DARK_GRAY);
						TxtUsuario.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 58));
						TxtPassword.setText("hola");
						TxtPassword.setForeground(Color.DARK_GRAY);
						TxtPassword.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 72));
						TxtUsuario.requestFocus();
					
					}
					
				}
				
			}
		});
		TxtUsuario.setDisabledTextColor(SystemColor.scrollbar);
		TxtUsuario.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				
				if(TxtUsuario.getText().equals("Usuario")) {
					
					TxtUsuario.setText("");
					TxtUsuario.setForeground(Color.BLACK);
					TxtUsuario.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 72));
					
				}
				
			}
		});
		TxtUsuario.setForeground(Color.DARK_GRAY);
		TxtUsuario.setBorder(null);
		TxtUsuario.setOpaque(false);
		TxtUsuario.setBounds(398, 107, 347, 88);
		contentPane.add(TxtUsuario);
		TxtUsuario.setToolTipText("Ingresa tu nombre de usuario");
		TxtUsuario.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 58));
		TxtUsuario.setColumns(10);
		
		LblIngresar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		LblIngresar.setIcon(new ImageIcon(Principal.class.getResource("/Img/login - copia.png")));
		LblIngresar.setBounds(470, 418, 90, 90);
		contentPane.add(LblIngresar);
		
		JLabel LblPassword = new JLabel("");
		LblPassword.setToolTipText("Ingresa la clave");
		LblPassword.setIcon(new ImageIcon(Principal.class.getResource("/Img/398351a51aef0fa651e20e1781ecb5e92.png")));
		LblPassword.setForeground(Color.BLACK);
		LblPassword.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 72));
		LblPassword.setBounds(275, 223, 483, 174);
		contentPane.add(LblPassword);
		
		JLabel LblUsuario = new JLabel("");
		LblUsuario.setToolTipText("Ingresa tu nombre de usuario");
		LblUsuario.setIcon(new ImageIcon(Principal.class.getResource("/Img/398351a51aef0fa651e20e1781ecb5e9.png")));
		LblUsuario.setForeground(Color.BLACK);
		LblUsuario.setBounds(275, 63, 483, 174);
		contentPane.add(LblUsuario);
		LblUsuario.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 72));
		
		JLabel LblOverlay = new JLabel("");
		LblOverlay.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		LblOverlay.setIcon(new ImageIcon(Principal.class.getResource("/Img/menu1.jpg")));
		LblOverlay.setBounds(0, 0, 1057, 524);
		contentPane.add(LblOverlay);
		
		JLabel LblLogo = new JLabel("LOGO");
		LblLogo.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		LblLogo.setIcon(new ImageIcon(Principal.class.getResource("/Img/def - copia.jpg")));
		LblLogo.setBounds(0, 0, 1057, 524);
		contentPane.add(LblLogo);
		
	}
}
