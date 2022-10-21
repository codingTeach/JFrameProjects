import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Dimension;
import java.awt.event.MouseMotionAdapter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import javax.swing.border.MatteBorder;

import com.mysql.jdbc.PreparedStatement;

import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Cursor;
import java.awt.SystemColor;
import java.awt.Toolkit;

import javax.swing.SwingConstants;
import javax.swing.UIManager;

public class Menu extends JDialog {

	private final JPanel contentPanel = new JPanel();
	public static int x = 0;	
	public static Menu dialog = new Menu();
	private int mouseX, mouseY;

	public static void main(String[] args) {
		try {
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

			dialog.setLocationRelativeTo(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("null")
	public Menu() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Menu.class.getResource("/Img/admin.png")));
		setUndecorated(true);
		setResizable(false);
		setModal(true);
		setTitle("Colitas - Men\u00FA");
		setBounds(100, 100, 1285, 805);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(230, 230, 250));
		contentPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		UIManager.put("ToolTip.background", Color.black);
		UIManager.put("ToolTip.foreground", Color.white);
		UIManager.put("ToolTip.font", new Font("Century Gothic", Font.PLAIN, 18 ));
		
		JLabel LblInfo = new JLabel("");
		LblInfo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				Inf.dialog.setLocationRelativeTo(null);
				Inf.dialog.setVisible(true);
				
			}
		});
		LblInfo.setToolTipText("Pulsa para m\u00E1s informaci\u00F3n");
		LblInfo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		LblInfo.setIcon(new ImageIcon(Menu.class.getResource("/Img/imagen_2022-08-17_191619518-removebg-preview - copia (2).png")));
		LblInfo.setBounds(10, 11, 80, 105);
		contentPanel.add(LblInfo);
		
		JLabel BtnVacunas = new JLabel("");
		BtnVacunas.setHorizontalAlignment(SwingConstants.CENTER);
		BtnVacunas.setToolTipText("Ingresar a la secci\u00F3n de inyecciones");
		BtnVacunas.setBounds(945, 325, 160, 160);
		contentPanel.add(BtnVacunas);
		BtnVacunas.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		BtnVacunas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				
				BtnVacunas.setIcon(new ImageIcon(Menu.class.getResource("/Img/jeringuilla - copia.png")));
				BtnVacunas.setBounds(945, 320, 160, 160);
				
			}
			@Override
			public void mouseExited(MouseEvent e) {
				
				BtnVacunas.setIcon(new ImageIcon(Menu.class.getResource("/Img/jeringuilla.png")));
				
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				
				Menu.dialog.setVisible(false);
				Inyecta.dialog.setVisible(true);
				Inyecta.dialog.setLocationRelativeTo(null);
				
			}
		});
		BtnVacunas.setIcon(new ImageIcon(Menu.class.getResource("/Img/jeringuilla.png")));
		
		JLabel BtnVentas = new JLabel("");
		BtnVentas.setHorizontalAlignment(SwingConstants.CENTER);
		BtnVentas.setBounds(565, 325, 160, 160);
		contentPanel.add(BtnVentas);
		BtnVentas.setToolTipText("Visualizar ventas realizadas");
		BtnVentas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				Menu.dialog.setVisible(false);
				Ventas.dialog.setEnabled(true);
				Ventas.dialog.setVisible(true);
				Ventas.dialog.setLocationRelativeTo(null);
				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				
				BtnVentas.setBounds(565, 320, 160, 160);
				BtnVentas.setIcon(new ImageIcon(Menu.class.getResource("/Img/refugio-de-animales - copia.png")));
				
			}
			@Override
			public void mouseExited(MouseEvent e) {
				
				BtnVentas.setIcon(new ImageIcon(Menu.class.getResource("/Img/refugio-de-animales.png")));
				
			}
		});
		BtnVentas.setIcon(new ImageIcon(Menu.class.getResource("/Img/refugio-de-animales.png")));
		BtnVentas.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		JLabel BtnClientes = new JLabel("");
		BtnClientes.setHorizontalAlignment(SwingConstants.CENTER);
		BtnClientes.setBounds(375, 325, 160, 160);
		contentPanel.add(BtnClientes);
		BtnClientes.setToolTipText("Ingresar el registro de clientes");
		BtnClientes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				Menu.dialog.setVisible(false);
				Clientes.dialog.setVisible(true);
				Clientes.dialog.setLocationRelativeTo(null);
				
			}
			@Override
			public void mouseExited(MouseEvent e) {
				
				BtnClientes.setIcon(new ImageIcon(Menu.class.getResource("/Img/d641af49873982095ee6c363ffc5144c.png")));
				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				
				BtnClientes.setBounds(375, 320, 160, 160);
				BtnClientes.setIcon(new ImageIcon(Menu.class.getResource("/Img/d641af49873982095ee6c363ffc5144c2.png")));
				
			}
		});
		BtnClientes.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		BtnClientes.setIcon(new ImageIcon(Menu.class.getResource("/Img/d641af49873982095ee6c363ffc5144c.png")));
		
		JLabel BtnProductos = new JLabel("");
		BtnProductos.setHorizontalAlignment(SwingConstants.CENTER);
		BtnProductos.setBounds(185, 325, 160, 160);
		contentPanel.add(BtnProductos);
		BtnProductos.setToolTipText("Ingresar al inventario de productos");
		BtnProductos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			
				Menu.dialog.setVisible(false);
				Productos.dialog.setEnabled(true);
				Productos.dialog.setVisible(true);
				Productos.TxtBuscador.requestFocus();
				Productos.dialog.setLocationRelativeTo(null);
				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				
				BtnProductos.setBounds(185, 320, 160, 160);
				BtnProductos.setIcon(new ImageIcon(Principal.class.getResource("/Img/medicina - copia.png")));
				
			}
			@Override
			public void mouseExited(MouseEvent e) {
			
				BtnProductos.setIcon(new ImageIcon(Principal.class.getResource("/Img/medicina.png")));
			
			}
		});
		BtnProductos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		BtnProductos.setIcon(new ImageIcon(Menu.class.getResource("/Img/medicina.png")));
		{
			
			JLabel BtnEstetica = new JLabel("");
			BtnEstetica.setBounds(755, 325, 160, 160);
			contentPanel.add(BtnEstetica);
			BtnEstetica.setToolTipText("Ingresar a la secci\u00F3n de est\u00E9tica");
			BtnEstetica.setIcon(new ImageIcon(Menu.class.getResource("/Img/seguro-de-mascotas.png")));
			BtnEstetica.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					
					Menu.dialog.setVisible(false);
					Estetica.dialog.setVisible(true);
					Estetica.dialog.setLocationRelativeTo(null);
					
				}
				@Override
				public void mouseEntered(MouseEvent e) {
					
					BtnEstetica.setBounds(755, 320, 160, 160);
					BtnEstetica.setIcon(new ImageIcon(Menu.class.getResource("/Img/seguro-de-mascotas - copia.png")));
					
				}
				@Override
				public void mouseExited(MouseEvent e) {
					
					BtnEstetica.setIcon(new ImageIcon(Menu.class.getResource("/Img/seguro-de-mascotas.png")));
					
				}
			});
			BtnEstetica.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			BtnEstetica.setHorizontalAlignment(SwingConstants.CENTER);
		}
		
		JPanel panel = new JPanel();
		
					panel.setBorder(new LineBorder(new Color(0, 0, 0)));
					panel.setBackground(new Color(95, 158, 160));
					panel.setBounds(5, 737, 1273, 62);
					contentPanel.add(panel);
					
					JLabel BtnSalir = new JLabel("");
					BtnSalir.setBounds(1218, 10, 45, 45);
					BtnSalir.setToolTipText("Click para cerrar el programa");
					BtnSalir.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							
							Principal.input = "apagar";
							Input.dialog = new Input();
							Input.dialog.setLocationRelativeTo(null);
							Input.dialog.setVisible(true);
							
							if(Input.yn.equals("yes")) {
	    						
								System.exit(0);
								
	    					}
							
						}
						@Override
						public void mouseEntered(MouseEvent e) {
							
							BtnSalir.setBounds(1214, 5, 50, 50);
							BtnSalir.setIcon(new ImageIcon(Principal.class.getResource("/Img/apagar - copia.png")));
							
						}
						@Override
						public void mouseExited(MouseEvent e) {
							
							BtnSalir.setIcon(new ImageIcon(Principal.class.getResource("/Img/93627.png")));
							BtnSalir.setBounds(1218, 10, 45, 45);
							
						}
					});
					panel.setLayout(null);
					BtnSalir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
					BtnSalir.setIcon(new ImageIcon(Menu.class.getResource("/Img/93627.png")));
					panel.add(BtnSalir);
					
					JLabel BtnUsuarios = new JLabel("");
					BtnUsuarios.setBounds(10, 5, 55, 55);
					panel.add(BtnUsuarios);
					BtnUsuarios.setToolTipText("Click para ver usuarios");
					BtnUsuarios.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							
							boolean correcto = false;
							do {
								
								//contra = JOptionPane.showInputDialog(null, "Ingresa la clave para entrar", "Acceso a usuarios", JOptionPane.WARNING_MESSAGE);
								Principal.input = "clave";
								Input.dialog = new Input();
								Input.dialog.setLocationRelativeTo(null);
								Input.dialog.setVisible(true);
								
								if(Input.yn.equals("usuarios")) {
								
									correcto = true;
									Menu.dialog.setVisible(false);
									Usuarios.dialog.setVisible(true);
									Usuarios.dialog.setLocationRelativeTo(null);
									Input.yn.equals("");
									
								}else if(Input.yn.equals("cancelar")) {
									
									correcto = true;
									
								}
								
									
							}while(correcto == false);
								
							
						}
						@Override
						public void mouseEntered(MouseEvent e) {
							
							BtnUsuarios.setIcon(new ImageIcon(Menu.class.getResource("/Img/user.png")));
							BtnUsuarios.setBounds(10, 0, 55, 55);
							
						}
						@Override
						public void mouseExited(MouseEvent e) {
							
							BtnUsuarios.setIcon(new ImageIcon(Menu.class.getResource("/Img/user.png")));
							BtnUsuarios.setBounds(10, 5, 55, 55);
							
						}
					});
					BtnUsuarios.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
					BtnUsuarios.setIcon(new ImageIcon(Menu.class.getResource("/Img/user.png")));
		
		
		{
			JLabel LblBorde = new JLabel("");
			LblBorde.addMouseMotionListener(new MouseMotionAdapter() {
				@Override
				public void mouseDragged(MouseEvent e) {
					
					dialog.setLocation(dialog.getX() + e.getX() - mouseX, dialog.getY() + e.getY() - mouseY);
					
				}
			});
			LblBorde.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					
					mouseX = e.getX();
					mouseY = e.getY();
					
				}
			});
			
			JLabel LblOverlay = new JLabel("");
			LblOverlay.setBorder(new LineBorder(new Color(0, 0, 0), 3));
			LblOverlay.setIcon(new ImageIcon(Menu.class.getResource("/Img/menu1 - copia.jpg")));
			LblOverlay.setBounds(0, 0, 1285, 805);
			contentPanel.add(LblOverlay);
			LblBorde.setIcon(new ImageIcon(Menu.class.getResource("/Img/menu1.jpg")));
			LblBorde.setBounds(0, 0, 1285, 805);
			contentPanel.add(LblBorde);
			
		}
	}
}
