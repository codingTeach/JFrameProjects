import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.border.LineBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.ImageIcon;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.Toolkit;

public class AgModCliente extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField TxtCliente;
	private JTextField TxtRaza;
	private JTextField TxtDueno;
	private JTextField TxtFolioReceta;
	private JTextField TxtTelefono;
	public static JLabel BtnModificar;
	public static int validador = 0;
	public static AgModCliente dialog = new AgModCliente();
	private int mouseX, mouseY;
	
	public static void main(String[] args) {
		try {
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	int xMouse, yMouse;
	private JTextField TxtEdad;
	private JTextField TxtPrecio;
	
	public AgModCliente() {
		setTitle("Agregar Cliente");
		setIconImage(Toolkit.getDefaultToolkit().getImage(AgModCliente.class.getResource("/Img/anadir.png")));
		setUndecorated(true);
		setBounds(100, 100, 781, 613);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JLabel LblCliente = new JLabel("Cliente:");
		
		TxtCliente = new JTextField();
		TxtCliente.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				
				LblCliente.setText("");
				
			}
			@Override
			public void focusLost(FocusEvent e) {
				
				if(TxtCliente.getText().equals("")) {
					
					LblCliente.setText("Cliente: ");
					LblCliente.setForeground(Color.gray);
					
				}
				
			}
		});
		
		JLabel LblRaza = new JLabel("Especie:");
		
		TxtRaza = new JTextField();
		TxtRaza.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				
				LblRaza.setText("");
				
			}
			@Override
			public void focusLost(FocusEvent e) {
				
				if(TxtRaza.getText().equals("")) {
					
					LblRaza.setText("Raza: ");
					LblRaza.setForeground(Color.gray);
					
				}
				
			}
		});
		JLabel LblDueno = new JLabel("Due\u00F1o:");
		TxtDueno = new JTextField();
		TxtDueno.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				
				LblDueno.setText("");
				
			}
			@Override
			public void focusLost(FocusEvent e) {
				
				if(TxtDueno.getText().equals("")) {
					
					LblDueno.setText("Due\u00F1o: ");
					LblDueno.setForeground(Color.gray);
					
				}
				
			}
		});
		
		JLabel LblTelefono = new JLabel("Telefono: ");
		TxtTelefono = new JTextField();
		TxtTelefono.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				
				LblTelefono.setText("");
				
			}
			@Override
			public void focusLost(FocusEvent e) {
				
				if(TxtTelefono.getText().equals("")) {
					
					LblTelefono.setText("Telefono: ");
					LblTelefono.setForeground(Color.gray);
					
				}
				
			}
		});
		TxtTelefono.setOpaque(false);
		TxtTelefono.setBorder(null);
		TxtTelefono.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				
				char c = e.getKeyChar();
				
				if((c < '0' || c > '9')) e.consume();{
				
			}
				
				 if(TxtTelefono.getText().length() >= 12)
				    {
				        e.consume();
				    }
				
			}
		});

		JLabel LblFolioReceta = new JLabel("Folio receta:");
		
		TxtFolioReceta = new JTextField();
		TxtFolioReceta.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				
				LblFolioReceta.setText("");
				
			}
			@Override
			public void focusLost(FocusEvent e) {
				
				if(TxtFolioReceta.getText().equals("")) {
					
					LblFolioReceta.setText("Folio receta: ");
					LblFolioReceta.setForeground(Color.gray);
					
				}
				
			}
		});
		TxtFolioReceta.setOpaque(false);
		TxtFolioReceta.setBorder(null);
		TxtFolioReceta.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				
				char c = e.getKeyChar();
				
				if((c < '0' || c > '9')) e.consume();{
				
			}
				
				 if(TxtFolioReceta.getText().length() >= 12)
				    {
				        e.consume();
				    }
				 
			}
			
			
		});
		TxtFolioReceta.setColumns(10);
		TxtFolioReceta.setBounds(84, 395, 86, 20);
		contentPanel.add(TxtFolioReceta);
		TxtTelefono.setColumns(10);
		TxtTelefono.setBounds(84, 241, 86, 20);
		contentPanel.add(TxtTelefono);
		TxtDueno.setBorder(null);
		TxtDueno.setOpaque(false);
		TxtDueno.setColumns(10);
		TxtDueno.setBounds(84, 178, 86, 20);
		contentPanel.add(TxtDueno);
		
		JLabel LblDueno_1 = new JLabel("");
		LblDueno_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		LblDueno_1.setBounds(84, 197, 86, 2);
		contentPanel.add(LblDueno_1);
		TxtRaza.setBorder(null);
		TxtRaza.setOpaque(false);
		TxtRaza.setColumns(10);
		TxtRaza.setBounds(84, 117, 86, 20);
		contentPanel.add(TxtRaza);
		
		JLabel LblRaza_1 = new JLabel("");
		LblRaza_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		LblRaza_1.setBounds(84, 136, 86, 2);
		contentPanel.add(LblRaza_1);
		TxtCliente.setOpaque(false);
		TxtCliente.setBorder(null);
		TxtCliente.setBounds(84, 67, 86, 20);
		contentPanel.add(TxtCliente);
		TxtCliente.setColumns(10);
		
		LblCliente.setForeground(Color.GRAY);
		LblCliente.setBounds(84, 70, 46, 14);
		contentPanel.add(LblCliente);
		
		LblRaza.setForeground(Color.GRAY);
		LblRaza.setBounds(84, 120, 86, 14);
		contentPanel.add(LblRaza);
		
		LblDueno.setForeground(Color.GRAY);
		LblDueno.setBounds(84, 181, 46, 14);
		contentPanel.add(LblDueno);
		
		JLabel LblDescripcion = new JLabel("Descripcion:");
		LblDescripcion.setBounds(322, 70, 86, 14);
		contentPanel.add(LblDescripcion);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(419, 65, 260, 173);
		contentPanel.add(scrollPane);
		
		JTextArea TxtDescripcion = new JTextArea();
		scrollPane.setViewportView(TxtDescripcion);
		
		JLabel LblFecha = new JLabel("Fecha:");
		LblFecha.setBounds(28, 315, 88, 23);
		contentPanel.add(LblFecha);
		
		JComboBox CmbDia = new JComboBox();
		CmbDia.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
		CmbDia.setBounds(99, 315, 50, 22);
		contentPanel.add(CmbDia);
		
		JComboBox CmbMes = new JComboBox();
		CmbMes.setModel(new DefaultComboBoxModel(new String[] {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"}));
		CmbMes.setBounds(159, 315, 109, 22);
		contentPanel.add(CmbMes);
		
		JComboBox CmbYear = new JComboBox();
		CmbYear.setModel(new DefaultComboBoxModel(new String[] {"2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030", "2031", "2032"}));
		CmbYear.setBounds(278, 315, 64, 22);
		contentPanel.add(CmbYear);
		
		LblFolioReceta.setForeground(Color.GRAY);
		LblFolioReceta.setBounds(84, 398, 88, 14);
		contentPanel.add(LblFolioReceta);

		JTextField TxtSexo = new JTextField();
		contentPanel.setLayout(null);
		{
			JPanel PanelDown = new JPanel();
			PanelDown.setBorder(new LineBorder(new Color(0, 0, 0)));
			PanelDown.setBackground(new Color(0, 139, 139));
			PanelDown.setBounds(0, 547, 781, 66);
			contentPanel.add(PanelDown);
			PanelDown.setLayout(null);

			JLabel BtnAgregar = new JLabel("");
			BtnAgregar.setToolTipText("Agregar nuevo cliente");
			PanelDown.add(BtnAgregar);
			BtnAgregar.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					
					Connection LaConexion = null;

                Statement Sentencia; //lanzar sql

                ResultSet Rs;
                char v;
						
						if(TxtCliente.getText().length()==0 || TxtRaza.getText().length()==0 || TxtDueno.getText().length()==0
								 || TxtFolioReceta.getText().length()==0)
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
								String noMes = null;
								
								String dia = (String) CmbDia.getSelectedItem();
								String mes = (String) CmbMes.getSelectedItem();
								switch(mes) {
								
								case "Enero":
									
									noMes = "1";
									break;
									
								case "Febrero":
									
									noMes = "2";
									break;
									
								case "Marzo":
									
									noMes = "3";
									break;
									
								case "Abril":
									
									noMes = "4";
									break;
								
								case "Mayo":
									
									noMes = "5";
									break;
									
								case "Junio":
									
									noMes = "6";
									break;
									
								case "Julio":
									
									noMes = "7";
									break;
									
								case "Agosto":
									
									noMes = "8";
									break;
									
								case "Septiembre":
									
									noMes = "9";
									break;
									
								case "Octubre":
									
									noMes = "10";
									break;
									
								case "Noviembre":
									
									noMes = "11";
									break;
									
								case "Diciembre":
									
									noMes = "12";
									break;
								
								}
								String year = (String) CmbYear.getSelectedItem();
								String fecha = year + "-" + noMes + "-" + dia;
								
								PreparedStatement SentenciaP = LaConexion.prepareStatement("INSERT INTO clientes (cliente, raza, dueno, descripcion, fechahora, folioreceta, telefono, sexo, edad, precio) VALUES(?,?,?,?,?,?,?,?,?,?)");
								
								SentenciaP.setString(1,TxtCliente.getText());
								SentenciaP.setString(2,TxtRaza.getText());
								SentenciaP.setString(3,TxtDueno.getText());
								SentenciaP.setString(4,TxtDescripcion.getText());
								SentenciaP.setString(5,fecha);	
								SentenciaP.setString(6,TxtFolioReceta.getText());
								SentenciaP.setString(7,TxtTelefono.getText());
								SentenciaP.setString(8,TxtSexo.getText());
								SentenciaP.setString(9,TxtEdad.getText());
								SentenciaP.setString(10,TxtPrecio.getText());
								
								Registros = SentenciaP.executeUpdate();
								
								while(Clientes.TablaClientes.getRowCount() > 0)
				                {
									Clientes.M_Clientes.removeRow(0);
				                }
								
								if(Registros>=1)
								{
									TxtCliente.setText("");
									TxtRaza.setText("");
									TxtDueno.setText("");
									TxtDescripcion.setText("");
									TxtFolioReceta.setText("");
									TxtTelefono.setText("");
									TxtSexo.setText("");
									TxtEdad.setText("");
									TxtPrecio.setText("");
									TxtCliente.requestFocus();
									String sql = "SELECT id, cliente, raza, dueno, descripcion, fechahora, folioreceta, telefono, sexo, edad, precio FROM clientes";
									Rs = Sentencia.executeQuery(sql);
									while(Rs.next())
									{
										Object [] fila = new Object[11]; 

										  
										   for (int i=0;i<11;i++) {
											   fila[i] = Rs.getObject(i+1); 
											   
											   }
										     
										   Clientes.M_Clientes.addRow(fila);
								
									}
									
									Principal.jopre = "exito";
	    							JOPRe.dialog = new JOPRe();
	    							JOPRe.dialog.setLocationRelativeTo(null);
	    							JOPRe.dialog.setVisible(true);
									
									//JOptionPane.showMessageDialog(null,"Se Registro Correctamente", "Aviso", JOptionPane.INFORMATION_MESSAGE);
									v = 's';
									dispose();
									
			                           
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
					
					BtnAgregar.setIcon(new ImageIcon(AgModCliente.class.getResource("/Img/anadir.png")));
					BtnAgregar.setBounds(10, 11, 40, 40);
					
				}
				@Override
				public void mouseEntered(MouseEvent e) {
					
					BtnAgregar.setIcon(new ImageIcon(AgModCliente.class.getResource("/Img/anadir - copia.png")));
					BtnAgregar.setBounds(10, 6, 50, 50);
					
				}
			});
			BtnAgregar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			BtnAgregar.setIcon(new ImageIcon(AgModCliente.class.getResource("/Img/anadir.png")));
			BtnAgregar.setBounds(10, 11, 40, 40);
			
			BtnModificar = new JLabel("");
			BtnModificar.setToolTipText("Modificar registro de cliente");
			BtnModificar.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					
					Connection LaConexion;
					ResultSet Rs; 
					int Id = 0, Registros=0;
					String Ubicacion = "";  
					int filas = Clientes.TablaClientes.getSelectedRow();
					
					String noMes = null;
					
					String dia = (String) CmbDia.getSelectedItem();
					String mes = (String) CmbMes.getSelectedItem();
					switch(mes) {
					
					case "Enero":
						
						noMes = "1";
						break;
						
					case "Febrero":
						
						noMes = "2";
						break;
						
					case "Marzo":
						
						noMes = "3";
						break;
						
					case "Abril":
						
						noMes = "4";
						break;
					
					case "Mayo":
						
						noMes = "5";
						break;
						
					case "Junio":
						
						noMes = "6";
						break;
						
					case "Julio":
						
						noMes = "7";
						break;
						
					case "Agosto":
						
						noMes = "8";
						break;
						
					case "Septiembre":
						
						noMes = "9";
						break;
						
					case "Octubre":
						
						noMes = "10";
						break;
						
					case "Noviembre":
						
						noMes = "11";
						break;
						
					case "Diciembre":
						
						noMes = "12";
						break;
					
					}
					String year = (String) CmbYear.getSelectedItem();
					String fecha = year + "-" + noMes + "-" + dia;
					
					//Obtiene la longitud de cada campo de texto y lo compara si es 0 (es decir que no contiene nada, entonces no permitir� capturar)
					if(TxtCliente.getText().equals("") || TxtRaza.getText().equals("") || TxtDueno.getText().equals("")
							 || TxtFolioReceta.getText().equals(""))
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
		    					Ubicacion = String.valueOf(Clientes.TablaClientes.getValueAt(filas, 0));	
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
		                    
		                   Rs = Sentenciap.executeQuery("SELECT id FROM clientes WHERE id = '"+Ubicacion+"' ");
		                   
		                   while(Rs.next())
		                   {
		                	   Id = Integer.parseInt(Rs.getString("id"));
		                   }
		                   
		                    PreparedStatement Sentencia = LaConexion.prepareStatement("UPDATE clientes SET cliente= ?, raza = ?, "
		                    		+ "dueno= ?, descripcion = ?, fechahora = ?, folioreceta = ?, telefono = ?, sexo = ?, edad = ?, precio = ? WHERE id = ?");
						    
		                    Sentencia.setString(1,TxtCliente.getText());
							Sentencia.setString(2,TxtRaza.getText());
							Sentencia.setString(3,TxtDueno.getText());
							Sentencia.setString(4,TxtDescripcion.getText());
							Sentencia.setString(5,fecha);	
							Sentencia.setFloat(6, Float.parseFloat(TxtFolioReceta.getText()));
							Sentencia.setString(7, TxtTelefono.getText());
							Sentencia.setString(8, TxtSexo.getText());
							Sentencia.setString(9, TxtEdad.getText());
							Sentencia.setString(10, TxtPrecio.getText());
		                    Sentencia.setInt(11, Id);
		                    
		                    Registros = Sentencia.executeUpdate();
		                    
		    				if(Registros >= 1)
		    				{
	
		    					dispose();

		    					//Borra Las filas de las tabla
		    					int i, filascount = Clientes.M_Clientes.getRowCount();
		  					  
		    					for(i=0; i<filascount; i++) 
		    					{
		    						Clientes.M_Clientes.removeRow(0);
		    					}
		    					//Se consulta para actualizar los datos
		    					//Sentencia

		    					Sentencia=LaConexion.prepareStatement("SELECT id, cliente, raza, dueno, descripcion, fechahora, folioreceta, telefono, sexo, edad, precio FROM clientes");
		    					Rs=Sentencia.executeQuery();
		    					
		    					while(Rs.next())
			                    {
			                        String registros[] = {Rs.getString("id"), Rs.getString("cliente"), Rs.getString("raza"), Rs.getString("dueno")
			                        		, Rs.getString("descripcion"), Rs.getString("fechahora"), Rs.getString("folioreceta"), Rs.getString("telefono")
			                        		, Rs.getString("sexo"), Rs.getString("edad"), Rs.getString("precio")};
			                        Clientes.M_Clientes.addRow(registros);
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
					
					BtnModificar.setIcon(new ImageIcon(AgModCliente.class.getResource("/Img/lapiz.png")));
					BtnModificar.setBounds(73, 11, 40, 40);
					
				}
				@Override
				public void mouseEntered(MouseEvent e) {
					
					if(BtnModificar.isEnabled() == true) {
						
						BtnModificar.setIcon(new ImageIcon(AgModCliente.class.getResource("/Img/lapiz - copia.png")));
						BtnModificar.setBounds(73, 6, 50, 50);
						
					}
					
				}
			});
			BtnModificar.setIcon(new ImageIcon(AgModCliente.class.getResource("/Img/lapiz.png")));
			BtnModificar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			BtnModificar.setBounds(73, 11, 40, 40);
			PanelDown.add(BtnModificar);
			
			JLabel BtnCerrar = new JLabel("");
			BtnCerrar.setToolTipText("Cerrar la ventana");
			BtnCerrar.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					
					Menu.x = 0;
					dispose();
					
				}
				@Override
				public void mouseExited(MouseEvent e) {
				
					BtnCerrar.setIcon(new ImageIcon(AgModCliente.class.getResource("/Img/resta.png")));
					BtnCerrar.setBounds(700, 7, 50, 50);
				
				}
				@Override
				public void mouseEntered(MouseEvent e) {
					
					BtnCerrar.setIcon(new ImageIcon(AgModCliente.class.getResource("/Img/resta - copia.png")));
					BtnCerrar.setBounds(700, 2, 60, 60);
					
				}
			});
			BtnCerrar.setIcon(new ImageIcon(AgModCliente.class.getResource("/Img/resta.png")));
			BtnCerrar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			BtnCerrar.setBounds(700, 7, 50, 50);
			PanelDown.add(BtnCerrar);
			
		}
		
		JPanel PanelUp = new JPanel();
		PanelUp.setBorder(new LineBorder(new Color(0, 0, 0)));
		PanelUp.setBackground(new Color(0, 139, 139));
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
		PanelUp.setBounds(0, 0, 781, 48);
		contentPanel.add(PanelUp);
		PanelUp.setLayout(null);
		
		JLabel LblTitulo = new JLabel("Agregar/Modificar");
		LblTitulo.setBounds(7, 1, 352, 43);
		LblTitulo.setForeground(Color.WHITE);
		LblTitulo.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 36));
		PanelUp.add(LblTitulo);
		
		LblTelefono.setForeground(Color.GRAY);
		LblTelefono.setBounds(84, 244, 72, 14);
		contentPanel.add(LblTelefono);
		
		JLabel LblCliente_1 = new JLabel("");
		LblCliente_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		LblCliente_1.setBounds(84, 86, 86, 2);
		contentPanel.add(LblCliente_1);
		
		JLabel LblTelefono_1 = new JLabel("");
		LblTelefono_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		LblTelefono_1.setBounds(84, 260, 86, 2);
		contentPanel.add(LblTelefono_1);
		
		JLabel LblFolioReceta_1 = new JLabel("");
		LblFolioReceta_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		LblFolioReceta_1.setBounds(84, 414, 86, 2);
		contentPanel.add(LblFolioReceta_1);
		
		JLabel LblSexo = new JLabel("Sexo:");
		TxtSexo.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				
				LblSexo.setText("");
				
			}
			@Override
			public void focusLost(FocusEvent e) {
				
				if(TxtSexo.getText().length() == 0) {
					
					LblSexo.setText("Sexo");
					
				}
				
			}
		});
		TxtSexo.setOpaque(false);
		TxtSexo.setColumns(10);
		TxtSexo.setBorder(null);
		TxtSexo.setBounds(84, 459, 86, 20);
		contentPanel.add(TxtSexo);
		
		LblSexo.setForeground(Color.GRAY);
		LblSexo.setBounds(84, 462, 88, 14);
		contentPanel.add(LblSexo);
		
		JLabel LblSexoLinea = new JLabel("");
		LblSexoLinea.setBorder(new LineBorder(new Color(0, 0, 0)));
		LblSexoLinea.setBounds(84, 478, 86, 2);
		contentPanel.add(LblSexoLinea);
		
		JLabel LblEdad = new JLabel("Edad");
		TxtEdad = new JTextField();
		TxtEdad.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				
				char c = e.getKeyChar();
				
				if(c<'0' || c>'9' && (c != KeyEvent.VK_BACK_SPACE)){
					
					e.consume();
				
			}
				
				 if(TxtEdad.getText().length() >= 12)
				    {
				        e.consume();
				        
				    }
				
			}
		});
		TxtEdad.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				
				LblEdad.setText("");
				
			}
			@Override
			public void focusLost(FocusEvent e) {
				
				if(TxtEdad.getText().length() == 0) {
					
					LblEdad.setText("Edad");
					
				}
				
			}
		});
		TxtEdad.setOpaque(false);
		TxtEdad.setColumns(10);
		TxtEdad.setBorder(null);
		TxtEdad.setBounds(254, 395, 86, 20);
		contentPanel.add(TxtEdad);
		
		LblEdad.setForeground(Color.GRAY);
		LblEdad.setBounds(254, 398, 88, 14);
		contentPanel.add(LblEdad);
		
		JLabel LblEdadLinea = new JLabel("");
		LblEdadLinea.setBorder(new LineBorder(new Color(0, 0, 0)));
		LblEdadLinea.setBounds(254, 414, 86, 2);
		contentPanel.add(LblEdadLinea);

		JLabel LblPrecio = new JLabel("Precio");
		TxtPrecio = new JTextField();
		TxtPrecio.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				
				char c = e.getKeyChar();
				
				if(c<'0' || c>'9' && (c != KeyEvent.VK_BACK_SPACE)){
					
					e.consume();
				
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
				
				if(TxtPrecio.getText().length() == 0) {
					
					LblPrecio.setText("Precio");
					
				}
				
			}
		});
		TxtPrecio.setOpaque(false);
		TxtPrecio.setColumns(10);
		TxtPrecio.setBorder(null);
		TxtPrecio.setBounds(254, 458, 86, 20);
		contentPanel.add(TxtPrecio);
		
		LblPrecio.setForeground(Color.GRAY);
		LblPrecio.setBounds(254, 461, 88, 14);
		contentPanel.add(LblPrecio);
		
		JLabel LblPrecioLinea = new JLabel("");
		LblPrecioLinea.setBorder(new LineBorder(new Color(0, 0, 0)));
		LblPrecioLinea.setBounds(254, 477, 86, 2);
		contentPanel.add(LblPrecioLinea);
		
		//Agregar
		if(Menu.x == 0) {
			
			TxtCliente.setText("");
			TxtRaza.setText("");
			TxtDueno.setText("");
			TxtFolioReceta.setText("");
			TxtDescripcion.setText("");
			TxtTelefono.setText("");
			TxtSexo.setText("");
			TxtEdad.setText("");
			TxtPrecio.setText("");
			
		}
		//Modificar Cliente
		else if(Menu.x == 1) {
			
			TxtCliente.setText(""+Clientes.TablaClientes.getValueAt(Clientes.TablaClientes.getSelectedRow(), 1));
			TxtRaza.setText(""+Clientes.TablaClientes.getValueAt(Clientes.TablaClientes.getSelectedRow(), 2));
			TxtDueno.setText(""+Clientes.TablaClientes.getValueAt(Clientes.TablaClientes.getSelectedRow(), 3));
			TxtDescripcion.setText(""+Clientes.TablaClientes.getValueAt(Clientes.TablaClientes.getSelectedRow(), 4));
			TxtFolioReceta.setText(""+Clientes.TablaClientes.getValueAt(Clientes.TablaClientes.getSelectedRow(), 6));
			TxtTelefono.setText(""+Clientes.TablaClientes.getValueAt(Clientes.TablaClientes.getSelectedRow(), 7));
			TxtSexo.setText(""+Clientes.TablaClientes.getValueAt(Clientes.TablaClientes.getSelectedRow(), 8));
			TxtEdad.setText(""+Clientes.TablaClientes.getValueAt(Clientes.TablaClientes.getSelectedRow(), 9));
			TxtPrecio.setText(""+Clientes.TablaClientes.getValueAt(Clientes.TablaClientes.getSelectedRow(), 10));
			
			LblCliente.setText("");
			LblRaza.setText("");
			LblDueno.setText("");
			LblTelefono.setText("");
			LblFolioReceta.setText("");
			LblSexo.setText("");
			LblEdad.setText("");
			LblPrecio.setText("");
			
			
		}
		
	}
}
