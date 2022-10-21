import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.Font;
import javax.swing.border.LineBorder;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ModAgEstetica extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField TxtCliente;
	private JTextField TxtPrecio;
	public static JLabel BtnModificar;
	private int mouseX, mouseY;
	public static ModAgEstetica dialog = new ModAgEstetica();
	public static int validador = 0;

	public static void main(String[] args) {
		try {
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	int xMouse, yMouse;
	
	public ModAgEstetica() {
		setTitle("Agregar Visita");
		setIconImage(Toolkit.getDefaultToolkit().getImage(ModAgEstetica.class.getResource("/Img/anadir.png")));
		setUndecorated(true);
		setBounds(100, 100, 819, 628);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		JTextArea TxtObservaciones = new JTextArea();
		{
			JLabel LblRaya1 = new JLabel("");
			LblRaya1.setBorder(new LineBorder(new Color(0, 0, 0)));
			LblRaya1.setBounds(120, 195, 86, 2);
			contentPanel.add(LblRaya1);
		}
		{
			JLabel LblRaya1 = new JLabel("");
			LblRaya1.setBorder(new LineBorder(new Color(0, 0, 0)));
			LblRaya1.setBounds(120, 104, 86, 2);
			contentPanel.add(LblRaya1);
		}
		{
			JPanel PanelDown = new JPanel();
			PanelDown.setBorder(new LineBorder(new Color(0, 0, 0)));
			PanelDown.setBackground(new Color(0, 102, 102));
			PanelDown.setBounds(0, 569, 819, 59);
			contentPanel.add(PanelDown);
			PanelDown.setLayout(null);
			{
				JLabel BtnAgregar = new JLabel("");
				BtnAgregar.setToolTipText("Agregar registro");
				BtnAgregar.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						
						Connection LaConexion = null;

		                Statement Sentencia; //lanzar sql

		                ResultSet Rs;
		                char v;
								
								if(TxtCliente.getText().length()==0 || TxtPrecio.getText().length()==0)
								{

									v = 'n';

									if(v == 'n') {

										Principal.jopre = "invalido";
		    							JOPRe.dialog = new JOPRe();
		    							JOPRe.dialog.setLocationRelativeTo(null);
		    							JOPRe.dialog.setVisible(true);
										//JOptionPane.showMessageDialog(null,"Ingrese datos a agregar", "Error", JOptionPane.ERROR_MESSAGE);
										TxtCliente.requestFocus();
										
									}
									
								}else {
									
									try {
										
										LaConexion = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/loginuser", "root", "LFMG2004");
										
										Sentencia = LaConexion.createStatement();

										//Insertar datos
										int Registros;
										
										PreparedStatement SentenciaP = LaConexion.prepareStatement("INSERT INTO estetica (cliente, precio, observaciones) VALUES(?,?,?)");
										
										SentenciaP.setString(1,TxtCliente.getText());
										SentenciaP.setString(2,TxtPrecio.getText());
										SentenciaP.setString(3,TxtObservaciones.getText());
										
										Registros = SentenciaP.executeUpdate();
										
										while(Estetica.TablaEstetica.getRowCount() > 0)
						                {
											Estetica.M_Estetica.removeRow(0);
						                }
										
										if(Registros>=1)
										{
											TxtCliente.setText("");
											TxtPrecio.setText("");
											TxtCliente.requestFocus();
											String sql = "SELECT id, cliente, precio, observaciones FROM estetica";
											Rs = Sentencia.executeQuery(sql);
											while(Rs.next())
											{
												Object [] fila = new Object[4]; 

												  
												   for (int i=0;i<4;i++) {
													   fila[i] = Rs.getObject(i+1); 
													   
													   }
												     
												   Estetica.M_Estetica.addRow(fila);
										
											}
											Principal.jopre = "exito";
			    							JOPRe.dialog = new JOPRe();
			    							JOPRe.dialog.setLocationRelativeTo(null);
			    							JOPRe.dialog.setVisible(true);
											//JOptionPane.showMessageDialog(null,"Se Registro Correctamente", "Aviso", JOptionPane.INFORMATION_MESSAGE);
											v = 's';
											
										}
										else
											Principal.jopre = "error";
		    								JOPRe.dialog = new JOPRe();
		    								JOPRe.dialog.setLocationRelativeTo(null);
		    								JOPRe.dialog.setVisible(true);
											//JOptionPane.showMessageDialog(null,"Fallo inesperado", "Error", JOptionPane.ERROR_MESSAGE);
										
										LaConexion.close();
										
									}catch(Exception el)
									{
										
										Principal.jopre = "error";
		    							JOPRe.dialog = new JOPRe();
		    							JOPRe.dialog.setLocationRelativeTo(null);
		    							JOPRe.dialog.setVisible(true);
										//JOptionPane.showMessageDialog(null,el.toString(), "Error", JOptionPane.ERROR_MESSAGE);
									
									}
									
								}
						
					}
					@Override
					public void mouseExited(MouseEvent e) {
						
						BtnAgregar.setIcon(new ImageIcon(ModAgEstetica.class.getResource("/Img/anadir.png")));
						BtnAgregar.setBounds(10, 10, 40, 40);
						
					}
					@Override
					public void mouseEntered(MouseEvent e) {
						
						BtnAgregar.setIcon(new ImageIcon(ModAgEstetica.class.getResource("/Img/anadir - copia.png")));
						BtnAgregar.setBounds(10, 5, 50, 50);
						
					}
				});
				BtnAgregar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				BtnAgregar.setIcon(new ImageIcon(ModAgEstetica.class.getResource("/Img/anadir.png")));
				BtnAgregar.setBounds(10, 10, 40, 40);
				PanelDown.add(BtnAgregar);
			}
			{
				BtnModificar = new JLabel("");
				BtnModificar.setToolTipText("Modificar registro");
				BtnModificar.setIcon(new ImageIcon(ModAgEstetica.class.getResource("/Img/lapiz.png")));
				BtnModificar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				BtnModificar.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						
						Connection LaConexion;
						ResultSet Rs; 
						int Id = 0, Registros=0;
						String Ubicacion = "";  
						int filas = Estetica.TablaEstetica.getSelectedRow();
						
						//Obtiene la longitud de cada campo de texto y lo compara si es 0 (es decir que no contiene nada, entonces no permitir� capturar)
						if(TxtCliente.getText().equals("") || TxtPrecio.getText().equals(""))
						{
							if(validador == 0) {
								
								Principal.jopre = "invalido";
    							JOPRe.dialog = new JOPRe();
    							JOPRe.dialog.setLocationRelativeTo(null);
    							JOPRe.dialog.setVisible(true);
								//JOptionPane.showMessageDialog(null,"Ingrese datos a modificar", "Error", JOptionPane.ERROR_MESSAGE);
								TxtCliente.requestFocus();
								
							}
							
						}
						else
						{
							try {
								
								LaConexion = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/loginuser", "root", "LFMG2004");
			                    
			                    Statement Sentenciap;
			                    Sentenciap = LaConexion.createStatement();

			    				if (filas >=0) 
			    				{
			    					Ubicacion = String.valueOf(Estetica.M_Estetica.getValueAt(filas, 0));	
			    				}
			    				else
			    				{
			    					if(validador == 0) {
			    						
			    						Principal.jopre = "eliminar";
		    							JOPRe.dialog = new JOPRe();
		    							JOPRe.dialog.setLocationRelativeTo(null);
		    							JOPRe.dialog.setVisible(true);
			    						//JOptionPane.showMessageDialog(null, "No se ha seleccionado un registro", "ERROR", JOptionPane.ERROR_MESSAGE);
			    						
			    					}
			    				}
			                    
			                   Rs = Sentenciap.executeQuery("SELECT id FROM estetica WHERE id = '"+Ubicacion+"' ");
			                   
			                   while(Rs.next())
			                   {
			                	   Id = Integer.parseInt(Rs.getString("id"));
			                   }
			                   
			                    PreparedStatement Sentencia = LaConexion.prepareStatement("UPDATE estetica SET cliente= ?, precio = ?, observaciones = ? WHERE id = ?");
							    
			                    Sentencia.setString(1,TxtCliente.getText());
								Sentencia.setString(2,TxtPrecio.getText());
								Sentencia.setString(3,TxtObservaciones.getText());
			                    Sentencia.setInt(4, Id);
			                    
			                    Registros = Sentencia.executeUpdate();
			                    
			    				if(Registros >= 1)
			    				{
		
			    					dispose();

			    					//Borra Las filas de las tabla
			    					int i, filascount = Estetica.M_Estetica.getRowCount();
			  					  
			    					for(i=0; i<filascount; i++) 
			    					{
			    						Estetica.M_Estetica.removeRow(0);
			    					}
			    					//Se consulta para actualizar los datos
			    					//Sentencia

			    					Sentencia=LaConexion.prepareStatement("SELECT id, cliente, precio, observaciones FROM estetica");
			    					Rs=Sentencia.executeQuery();
			    					
			    					while(Rs.next())
				                    {
				                        String registros[] = {Rs.getString("id"), Rs.getString("cliente"), Rs.getString("precio"), Rs.getString("observaciones")};
				                        Estetica.M_Estetica.addRow(registros);
				                       }

			    					Principal.jopre = "exito";
	    							JOPRe.dialog = new JOPRe();
	    							JOPRe.dialog.setLocationRelativeTo(null);
	    							JOPRe.dialog.setVisible(true);
			    					//JOptionPane.showMessageDialog(null, "Registro modificado", "Aviso", JOptionPane.INFORMATION_MESSAGE);
			    					validador = 1;
			    					Menu.x = 0;
			    					
			    				}
			    				else
			    				{
			    					if(validador == 0) {
			    						
			    						Principal.jopre = "vacio";
		    							JOPRe.dialog = new JOPRe();
		    							JOPRe.dialog.setLocationRelativeTo(null);
		    							JOPRe.dialog.setVisible(true);
			    						//JOptionPane.showMessageDialog(null, "No se pudo modificar", "Error", JOptionPane.ERROR_MESSAGE);
			    						
			    					}
			    				}
			    				LaConexion.close();
								
							}catch(Exception el)
							{
								
								Principal.jopre = "error";
    							JOPRe.dialog = new JOPRe();
    							JOPRe.dialog.setLocationRelativeTo(null);
    							JOPRe.dialog.setVisible(true);
								//JOptionPane.showMessageDialog(null,el.toString(), "Error", JOptionPane.ERROR_MESSAGE);

							}
						}
						
					}
					@Override
					public void mouseExited(MouseEvent e) {
						
						BtnModificar.setBounds(79, 10, 40, 40);
						BtnModificar.setIcon(new ImageIcon(ModAgEstetica.class.getResource("/Img/lapiz.png")));
						
					}
					@Override
					public void mouseEntered(MouseEvent e) {
						
						if(BtnModificar.isEnabled() == true) {
							
							BtnModificar.setBounds(79, 5, 50, 50);
							BtnModificar.setIcon(new ImageIcon(ModAgEstetica.class.getResource("/Img/lapiz - copia.png")));
							
						}
						
					}
				});
				BtnModificar.setBounds(79, 10, 40, 40);
				PanelDown.add(BtnModificar);
			}
			{
				JLabel BtnCerrar = new JLabel("");
				BtnCerrar.setToolTipText("Ir atr\u00E1s");
				BtnCerrar.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						
						dispose();
						
					}
					@Override
					public void mouseExited(MouseEvent e) {
						
						BtnCerrar.setIcon(new ImageIcon(ModAgEstetica.class.getResource("/Img/resta.png")));
						BtnCerrar.setBounds(725, 5, 50, 50);
						
					}
					@Override
					public void mouseEntered(MouseEvent e) {
						
						BtnCerrar.setIcon(new ImageIcon(ModAgEstetica.class.getResource("/Img/resta - copia.png")));
						BtnCerrar.setBounds(725, 0, 60, 60);
						
					}
				});
				BtnCerrar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				BtnCerrar.setIcon(new ImageIcon(ModAgEstetica.class.getResource("/Img/resta.png")));
				BtnCerrar.setBounds(725, 5, 50, 50);
				PanelDown.add(BtnCerrar);
			}
		}
		JLabel LblCliente = new JLabel("Cliente: ");
		{
			TxtCliente = new JTextField();
			TxtCliente.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					
					Character c = e.getKeyChar();
					
					if(!Character.isLetter(c) && c != KeyEvent.VK_SPACE) {
						
						e.consume();	
						
					}
					
					 if(TxtCliente.getText().length() >= 25)
					    {
					        e.consume();
					        
					    }
					
				}
			});
			TxtCliente.addFocusListener(new FocusAdapter() {
				@Override
				public void focusGained(FocusEvent e) {
					
					LblCliente.setText("");
					
				}
				@Override
				public void focusLost(FocusEvent e) {
					
					if(TxtCliente.getText().equals("")) {
						
						LblCliente.setText("Cliente: ");
						LblCliente.setForeground(Color.GRAY);
						
					}
					
				}
			});
			TxtCliente.setBorder(null);
			TxtCliente.setOpaque(false);
			TxtCliente.setBounds(120, 85, 86, 20);
			contentPanel.add(TxtCliente);
			TxtCliente.setColumns(10);
		}
		{
			
			LblCliente.setForeground(Color.GRAY);
			LblCliente.setBounds(120, 85, 46, 14);
			contentPanel.add(LblCliente);
		}
		JLabel LblPrecio = new JLabel("Precio: ");
		{
			TxtPrecio = new JTextField();
			TxtPrecio.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					
					char c = e.getKeyChar();
					
					if((c<'0' || c>'9')) e.consume();{
					
				}
					
					 if(TxtPrecio.getText().length() >= 12)
					    {
					        e.consume();
					    }
					
				}
			});
			TxtPrecio.addFocusListener(new FocusAdapter() {
				@Override
				public void focusGained(FocusEvent e) {
					
					LblPrecio.setText("");
					
				}
				
				@Override
				public void focusLost(FocusEvent e) {
					
					if(TxtPrecio.getText().equals("")) {
						
						LblPrecio.setText("Precio: ");
						LblPrecio.setForeground(Color.gray);
						
					}
					
				}
			});
			TxtPrecio.setOpaque(false);
			TxtPrecio.setBorder(null);
			TxtPrecio.setColumns(10);
			TxtPrecio.setBounds(120, 175, 86, 20);
			contentPanel.add(TxtPrecio);
		}
		{
			
			LblPrecio.setForeground(Color.GRAY);
			LblPrecio.setBounds(120, 175, 46, 14);
			contentPanel.add(LblPrecio);
		}
		{
			JLabel LblObservaciones = new JLabel("Observaciones: ");
			LblObservaciones.setBounds(365, 103, 103, 14);
			contentPanel.add(LblObservaciones);
		}
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(365, 149, 375, 296);
		contentPanel.add(scrollPane);
		
		
		TxtObservaciones.setWrapStyleWord(true);
		TxtObservaciones.setLineWrap(true);
		scrollPane.setViewportView(TxtObservaciones);
		
		JPanel PanelUp = new JPanel();
		PanelUp.setBorder(new LineBorder(new Color(0, 0, 0)));
		PanelUp.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				
				dialog.setLocation(dialog.getX() + e.getX() - mouseX, dialog.getY() + e.getY() - mouseY);
				
			}
		});
		PanelUp.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				
				mouseX = e.getX();
				mouseY = e.getY();
				
			}
		});
		PanelUp.setBackground(new Color(0, 102, 102));
		PanelUp.setBounds(0, 0, 819, 59);
		contentPanel.add(PanelUp);
		PanelUp.setLayout(null);
		
		JLabel LblTitulo = new JLabel("Agregar");
		LblTitulo.setBounds(10, 11, 146, 43);
		PanelUp.add(LblTitulo);
		
		{
			LblTitulo.setForeground(Color.WHITE);
			LblTitulo.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 36));
			
		}
		{
		}
		
		if(Menu.x == 0){
			
			TxtCliente.setText("");
			TxtPrecio.setText("");
			TxtObservaciones.setText("");
			
		}else if(Menu.x == 1) {
			
			LblCliente.setText("");
			LblPrecio.setText("");
			
			TxtCliente.setText(""+Estetica.TablaEstetica.getValueAt(Estetica.TablaEstetica.getSelectedRow(), 1));
			TxtPrecio.setText(""+Estetica.TablaEstetica.getValueAt(Estetica.TablaEstetica.getSelectedRow(), 2));
			TxtObservaciones.setText(""+Estetica.TablaEstetica.getValueAt(Estetica.TablaEstetica.getSelectedRow(), 3));
			
		}
	}
}
