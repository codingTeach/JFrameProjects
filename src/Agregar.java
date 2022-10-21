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
import java.awt.event.ActionEvent;
import java.awt.Dialog.ModalityType;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.Cursor;
import javax.swing.JSpinner;
import javax.swing.JFormattedTextField;
import java.time.LocalDate;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import java.awt.event.MouseMotionAdapter;
import javax.swing.ImageIcon;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.Toolkit;

public class Agregar extends JDialog{

	private final JPanel contentPanel = new JPanel();
	public JTextField TxtProductos;
	private JTextField TxtMarca;
	private JTextField TxtPrecio;
	private JTextField TxtDosis;
	private JTextField TxtKg;
	public static Agregar dialog = new Agregar();
	public static JLabel BtnModificar2;
	public static int valido = 0;
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
	JLabel LblPrecio, LblDosis, LblMl, LblMarca, LblKg;
	private JTextField TxtCantidad;
	
	public Agregar() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Agregar.class.getResource("/Img/anadir.png")));
		setUndecorated(true);
		setResizable(false);
		setTitle("Agregar producto");
		setModalityType(ModalityType.APPLICATION_MODAL);
		setBounds(100, 100, 820, 619);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		TxtDosis = new JTextField();
		TxtDosis.setToolTipText("");
		
		TxtDosis.setOpaque(false);
		TxtDosis.setBorder(null);
		TxtDosis.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				
				char c = e.getKeyChar();
				
				if((c<'0' || c>'9')) e.consume();{
				
			}
				
				 if(TxtDosis.getText().length() >= 12)
				    {
				        e.consume();
				    }
				 
			}
			
		});

		JLabel LblCantidad = new JLabel("Cantidad");
		TxtCantidad = new JTextField();
		TxtCantidad.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				
					LblCantidad.setText("");
				
				
			}
			@Override
			public void focusLost(FocusEvent e) {
				
				if(TxtCantidad.getText().length() == 0) {
					
					LblCantidad.setText("Cantidad");
					
				}
				
			}
		});
		TxtCantidad.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				
				char c = e.getKeyChar();
				
				if((c<'0' || c>'9')) e.consume();{
				
			}
				
				 if(TxtCantidad.getText().length() >= 12)
				    {
				        e.consume();
				    }
				
			}
		});
		TxtCantidad.setOpaque(false);
		TxtCantidad.setColumns(10);
		TxtCantidad.setBorder(null);
		TxtCantidad.setBounds(472, 380, 86, 20);
		contentPanel.add(TxtCantidad);
		TxtDosis.setColumns(10);
		TxtDosis.setBounds(66, 216, 86, 20);
		contentPanel.add(TxtDosis);
		TxtDosis.setEnabled(false);
		
		TxtDosis.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				
				if(TxtDosis.getText().length() == 0) {
					
					LblDosis.setText("Dosis");
					
				}
				
			}
			@Override
			public void focusGained(FocusEvent e) {
				
				if(TxtDosis.isEnabled() == true) {
					
					LblDosis.setText("");
					
				}
				
			}
		});
		TxtDosis.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(TxtDosis.isEnabled() == true) {
					
					LblDosis.setText("");
					
				}else {
					
					
				}
				
			}
		});

		JLabel LblProducto = new JLabel("Productos");
		LblProducto.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		
		LblProducto.setForeground(Color.DARK_GRAY);
		LblProducto.setBounds(66, 67, 86, 20);
		contentPanel.add(LblProducto);
		
		TxtProductos = new JTextField();
		TxtProductos.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				
				if(TxtProductos.getText().length() == 0) {
					
					LblProducto.setText("Producto");
					
				}
				
			}
			@Override
			public void focusGained(FocusEvent e) {
				
				LblProducto.setText("");
				
			}
		});
		TxtProductos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				LblProducto.setText("");
				
			}
		});
		TxtProductos.setForeground(Color.BLACK);
		TxtProductos.setOpaque(false);
		TxtProductos.setBorder(null);
		TxtProductos.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				
				Character c = e.getKeyChar();
				
				if(!Character.isLetter(c) && c != KeyEvent.VK_SPACE) {
					
					e.consume();
							
				}
				
				 if(TxtProductos.getText().length() >= 25)
				    {
				        e.consume();
				    }
				
			}
		});
		TxtProductos.setBounds(66, 67, 86, 20);
		contentPanel.add(TxtProductos);
		TxtProductos.setColumns(10);
		
		JPanel PanelDown = new JPanel();
		PanelDown.setBorder(new LineBorder(new Color(0, 0, 0)));
		PanelDown.setBackground(new Color(0, 139, 139));
		PanelDown.setBounds(0, 560, 820, 59);
		contentPanel.add(PanelDown);
		PanelDown.setLayout(null);

		JComboBox CmbProductos = new JComboBox();
		CmbProductos.setToolTipText("Seleccionar el tipo de producto");
		CmbProductos.setModel(new DefaultComboBoxModel(new String[] {"Accesorios", "Medicamentos", "Alimentos"}));

		CmbProductos.setBounds(493, 63, 251, 29);
		contentPanel.add(CmbProductos);
		
		JLabel LblDosisLinea = new JLabel("");
		LblDosisLinea.setBorder(new LineBorder(new Color(0, 0, 0)));
		LblDosisLinea.setBounds(66, 234, 86, 2);
		contentPanel.add(LblDosisLinea);
		LblDosisLinea.setEnabled(false);
		
		JLabel LblCaducidad = new JLabel("Caducidad:");
		LblCaducidad.setEnabled(false);
		LblCaducidad.setBounds(20, 304, 88, 23);
		contentPanel.add(LblCaducidad);
		
		JLabel LblLineaMarca = new JLabel("");
		LblLineaMarca.setBorder(new LineBorder(new Color(0, 0, 0)));
		LblLineaMarca.setBounds(67, 398, 85, 2);
		contentPanel.add(LblLineaMarca);
		LblLineaMarca.setEnabled(false);
		
		JLabel LblKgLinea = new JLabel("");
		LblKgLinea.setBorder(new LineBorder(new Color(0, 0, 0)));
		LblKgLinea.setBounds(66, 473, 86, 2);
		contentPanel.add(LblKgLinea);
		LblKgLinea.setEnabled(false);
		
		TxtMarca = new JTextField();
		
		TxtMarca.setBorder(null);
		TxtMarca.setOpaque(false);
		TxtMarca.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				
				Character c = e.getKeyChar();
				
				if(!Character.isLetter(c) && c != KeyEvent.VK_SPACE) {
					
					e.consume();
							
				}
				
				 if(TxtMarca.getText().length() >= 25)
				    {
				        e.consume();
				    }
				
			}
		});
		TxtMarca.setColumns(10);
		TxtMarca.setBounds(66, 380, 86, 20);
		contentPanel.add(TxtMarca);
		TxtMarca.setEnabled(false);
		
		JLabel LblDetalles = new JLabel("Detalles:");
		LblDetalles.setBounds(364, 219, 86, 14);
		contentPanel.add(LblDetalles);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(472, 214, 272, 92);
		contentPanel.add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 272, 92);
		panel_1.add(scrollPane);
		
		JTextArea TxtDetalles = new JTextArea();
		scrollPane.setViewportView(TxtDetalles);
		TxtDetalles.setWrapStyleWord(true);
		TxtDetalles.setLineWrap(true);
		
		TxtPrecio = new JTextField();
		TxtPrecio.setForeground(Color.BLACK);
		TxtPrecio.setBorder(null);
		TxtPrecio.setOpaque(false);
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
		TxtPrecio.setBounds(66, 135, 86, 20);
		contentPanel.add(TxtPrecio);
		TxtPrecio.setColumns(10);
		
		TxtKg = new JTextField();
		
		TxtKg.setOpaque(false);
		TxtKg.setBorder(null);
		TxtKg.setEnabled(false);
		TxtKg.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				
				char c = e.getKeyChar();
				
				if((c < '0' || c > '9')) e.consume();{
				
			}
				
				 if(TxtKg.getText().length() >= 12)
				    {
				        e.consume();
				    }
				 
			}
			
			
		});
		TxtKg.setBounds(66, 455, 86, 20);
		contentPanel.add(TxtKg);
		TxtKg.setColumns(10);
		
		JComboBox CmbDia = new JComboBox();
		CmbDia.setEnabled(false);
		CmbDia.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
		CmbDia.setBounds(91, 304, 50, 22);
		contentPanel.add(CmbDia);
		
		JComboBox CmbMes = new JComboBox();
		CmbMes.setEnabled(false);
		CmbMes.setModel(new DefaultComboBoxModel(new String[] {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"}));
		CmbMes.setBounds(151, 304, 109, 22);
		contentPanel.add(CmbMes);
		
		JComboBox CmbYear = new JComboBox();
		CmbYear.setEnabled(false);
		CmbYear.setModel(new DefaultComboBoxModel(new String[] {"2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030", "2031", "2032"}));
		CmbYear.setBounds(270, 304, 64, 22);
		contentPanel.add(CmbYear);
		
		JLabel BtnAgregar = new JLabel("");
		BtnAgregar.setToolTipText("Agregar un producto");
		BtnAgregar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				Connection LaConexion = null;

                Statement Sentencia; //lanzar sql

                ResultSet Rs;
				
				if(CmbProductos.getSelectedItem().equals("Accesorios")) {
					
					if(TxtProductos.getText().length()==0 || TxtPrecio.getText().length()==0 || TxtDetalles.getText().length()==0
							 || TxtCantidad.getText().length() == 0)
					{
						Principal.jopre = "invalido";
						JOPRe.dialog = new JOPRe();
						JOPRe.dialog.setLocationRelativeTo(null);
						JOPRe.dialog.setVisible(true);
						//JOptionPane.showMessageDialog(null,"Ingrese datos en los campos", "Error", JOptionPane.ERROR_MESSAGE);
						TxtProductos.requestFocus();
						valido = 0;
						
					}else {
						
						valido = 1;
						
					}
					
				}else if(CmbProductos.getSelectedItem().equals("Medicamentos")) {
					
					if(TxtProductos.getText().length()==0 || TxtPrecio.getText().length()==0 || TxtDosis.getText().length()==0
							 || TxtCantidad.getText().length() == 0)
					{
						
						Principal.jopre = "invalido";
						JOPRe.dialog = new JOPRe();
						JOPRe.dialog.setLocationRelativeTo(null);
						JOPRe.dialog.setVisible(true);
						//JOptionPane.showMessageDialog(null,"Ingrese datos en los campos", "Error", JOptionPane.ERROR_MESSAGE);
						TxtProductos.requestFocus();
						valido = 0;
						
					}else {
						
						valido = 2;
						
					}
					
				}else if(CmbProductos.getSelectedItem().equals("Alimentos")) {
					
					if(TxtProductos.getText().length() == 0 || TxtPrecio.getText().length() == 0 || TxtMarca.getText().length() == 0 
							|| TxtKg.getText().length() == 0 || TxtCantidad.getText().length() == 0)
					{
						
						Principal.jopre = "invalido";
						JOPRe.dialog = new JOPRe();
						JOPRe.dialog.setLocationRelativeTo(null);
						JOPRe.dialog.setVisible(true);
						//JOptionPane.showMessageDialog(null,"Ingrese datos en los campos", "Error", JOptionPane.ERROR_MESSAGE);
						TxtProductos.requestFocus();
						valido = 0;
						
					}else {
						
						valido = 3;
						
					}
					
				}
				
				if(valido == 1) {
					
					try {
						
						LaConexion = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/loginuser", "root", "LFMG2004");
						
						Sentencia = LaConexion.createStatement();

						//Insertar datos
						int Registros;
						
						for(int i = 0; i < 5; i++) {
							
							String TablaMinuscula = (String) Productos.TablaAccesorios.getValueAt(i, 1);
							TablaMinuscula = TablaMinuscula.toLowerCase();
							String TextoMinuscula = TxtProductos.getText().toLowerCase();
							
							int Id = 0;
							String Ubicacion = "";  
							int filas = Productos.TablaAccesorios.getSelectedRow();
							
							if(TextoMinuscula.equals(TablaMinuscula)) {

								
								int Cantidad = (int) Productos.TablaAccesorios.getValueAt(i, 4);
								Cantidad++;
								
								LaConexion = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/loginuser", "root", "LFMG2004");
			                    
			                    Statement Sentenciap;
			                    Sentenciap = LaConexion.createStatement();

			    				if (filas >=0) 
			    				{
			    					Ubicacion = String.valueOf(Productos.TablaAccesorios.getValueAt(filas, 0));	
			    				}
			    				else
			    				{
			    					Principal.jopre = "eliminar";
	    							JOPRe.dialog = new JOPRe();
	    							JOPRe.dialog.setLocationRelativeTo(null);
	    							JOPRe.dialog.setVisible(true);
			    					//JOptionPane.showMessageDialog(null, "No se ha seleccionado un registro", "ERROR", JOptionPane.ERROR_MESSAGE);
			    				}
			                    
			                   Rs = Sentenciap.executeQuery("SELECT id FROM accesorios WHERE id = '"+Ubicacion+"' ");
			                   
			                   while(Rs.next())
			                   {
			                	   Id = Integer.parseInt(Rs.getString("id"));
			                   }
			                   
			                    PreparedStatement Sentenciar = LaConexion.prepareStatement("UPDATE accesorios SET cantidad = ? WHERE id = ?");
							    
			                    Sentenciar.setInt(1, Cantidad);
			                    Sentenciar.setInt(2, Id);
			                    
			                    Registros = Sentenciar.executeUpdate();
			                    
			    				if(Registros >= 1)
			    				{
			    					
			    					dispose();

			    					//Borra Las filas de las tabla
			    					int filascount = Productos.M_Accesorios.getRowCount();
			  					  
			    					for(i = 0; i < filascount; i++) 
			    					{
			    						Productos.M_Accesorios.removeRow(0);
			    					}
			    					
			    					//Se consulta para actualizar los datos
			    					//Sentencia
			    					
			    					Sentenciar=LaConexion.prepareStatement("SELECT id, nombre, precio, detalles, cantidad FROM accesorios");
			    					Rs=Sentenciar.executeQuery();
			    					
			    					while(Rs.next())
				                    {
				                        String registros[] = {Rs.getString("id"), Rs.getString("nombre"), Rs.getString("precio"), Rs.getString("detalles")
				                        		, Rs.getString("cantidad")};
				                        Productos.M_Accesorios.addRow(registros);
				                       }

			    					Principal.jopre = "repetido";
        							JOPRe.dialog = new JOPRe();
        							JOPRe.dialog.setLocationRelativeTo(null);
        							JOPRe.dialog.setVisible(true);
			    					//JOptionPane.showMessageDialog(null, "Registro repetido. Se agregó al stock", "Aviso", JOptionPane.INFORMATION_MESSAGE);
			    					Menu.x = 1;
			    					
			    				}
			    				else
			    				{
			    					Principal.jopre = "vacio";
	    							JOPRe.dialog = new JOPRe();
	    							JOPRe.dialog.setLocationRelativeTo(null);
	    							JOPRe.dialog.setVisible(true);
			    					//JOptionPane.showMessageDialog(null, "No se pudo modificar", "Error", JOptionPane.ERROR_MESSAGE);
			    					TxtProductos.requestFocus();
			    				}
			    				LaConexion.close();
								
								/*
								 * 
								 * AL REPETIRSE SE PREGUNTA SI DESEA AÑADIR UNA UNIDAD AL PRODUCTO DE ESE NOMBRE
								 * 
								 * BTW, QUE SE PUEDA AÑADIR UNIDADES DE UN PRODUCTO CON UN SPINNER O BIEN BOTONES DE + Y -
								 * 
								 * */
								
							}
							
						}
						
						if(Menu.x != 1) {
						
						PreparedStatement SentenciaP = LaConexion.prepareStatement("INSERT INTO accesorios (nombre, precio, detalles, cantidad) VALUES(?,?,?,?)");
						
						SentenciaP.setString(1,TxtProductos.getText());
						SentenciaP.setString(2,TxtPrecio.getText());
						SentenciaP.setString(3,TxtDetalles.getText());
						SentenciaP.setString(4,TxtCantidad.getText());
						
						Registros = SentenciaP.executeUpdate();
						
						while(Productos.TablaAccesorios.getRowCount() > 0)
		                {
							Productos.M_Accesorios.removeRow(0);
		                }
						
						if(Registros>=1)
						{
							
							TxtProductos.setText("");
							TxtPrecio.setText("");
							TxtCantidad.setText("");
							LblPrecio.setText("Precio");
							LblCantidad.setText("Cantidad");
							TxtDetalles.setText("");
							TxtProductos.requestFocus();
							String sql = "SELECT id, nombre, precio, detalles, cantidad FROM accesorios";
							Rs = Sentencia.executeQuery(sql);
							while(Rs.next())
							{
								Object [] fila = new Object[5]; 

								  
								   for (int i=0;i<5;i++) {
									   fila[i] = Rs.getObject(i+1); 
									   
									   }
								   
								   Productos.M_Accesorios.addRow(fila);
						
							}
							
							Principal.jopre = "exito";
							JOPRe.dialog = new JOPRe();
							JOPRe.dialog.setLocationRelativeTo(null);
							JOPRe.dialog.setVisible(true);
								//JOptionPane.showMessageDialog(null,"Se Registro Correctamente", "Aviso", JOptionPane.INFORMATION_MESSAGE);
							   
						}else {
							
							Principal.jopre = "error";
							JOPRe.dialog = new JOPRe();
							JOPRe.dialog.setLocationRelativeTo(null);
							JOPRe.dialog.setVisible(true);
							//JOptionPane.showMessageDialog(null,"Fallo inesperado", "Error", JOptionPane.ERROR_MESSAGE);
						
						LaConexion.close();
						
						}
						
					}
						
					}catch(Exception el)
					{
						Principal.jopre = "error";
						JOPRe.dialog = new JOPRe();
						JOPRe.dialog.setLocationRelativeTo(null);
						JOPRe.dialog.setVisible(true);
						//JOptionPane.showMessageDialog(null,el.toString(), "Error", JOptionPane.ERROR_MESSAGE);
					
					
					}
					
				}else if(valido == 2) {
					
					try {
						
						LaConexion = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/loginuser", "root", "LFMG2004");
						
						Sentencia = LaConexion.createStatement();

						//Insertar datos
						int Registros;
						
						for(int i = 0; i < 6; i++) {
							
							String TablaMinuscula = (String) Productos.TablaMedicamentos.getValueAt(i, 1);
							TablaMinuscula = TablaMinuscula.toLowerCase();
							String TextoMinuscula = TxtProductos.getText().toLowerCase();
							
							int Id = 0;
							String Ubicacion = "";  
							int filas = Productos.TablaMedicamentos.getSelectedRow();
							
							if(TablaMinuscula.equals(TextoMinuscula)) {

								int Cantidad = (int) Productos.TablaMedicamentos.getValueAt(i, 5);
								Cantidad++;
								
								Menu.x = 1;
								
								LaConexion = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/loginuser", "root", "LFMG2004");
			                    
			                    Statement Sentenciap;
			                    Sentenciap = LaConexion.createStatement();

			    				if (filas >=0) 
			    				{
			    					Ubicacion = String.valueOf(Productos.TablaMedicamentos.getValueAt(filas, 0));	
			    				}
			    				else
			    				{
			    					Principal.jopre = "eliminar";
	    							JOPRe.dialog = new JOPRe();
	    							JOPRe.dialog.setLocationRelativeTo(null);
	    							JOPRe.dialog.setVisible(true);
			    					//JOptionPane.showMessageDialog(null, "No se ha seleccionado un registro", "ERROR", JOptionPane.ERROR_MESSAGE);
			    				}
			                    
			                   Rs = Sentenciap.executeQuery("SELECT id FROM medicamentos WHERE id = '"+Ubicacion+"' ");
			                   
			                   while(Rs.next())
			                   {
			                	   Id = Integer.parseInt(Rs.getString("id"));
			                   }
			                   
			                    PreparedStatement Sentenciar = LaConexion.prepareStatement("UPDATE medicamentos SET cantidad = ? WHERE id = ?");
							    
			                    Sentenciar.setInt(1, Cantidad);
			                    Sentenciar.setInt(2, Id);
			                    
			                    Registros = Sentenciar.executeUpdate();
			                    
			    				if(Registros >= 1)
			    				{
			    					
			    					dispose();

			    					//Borra Las filas de las tabla
			    					int filascount = Productos.M_Medicamentos.getRowCount();
			  					  
			    					for(i = 0; i < filascount; i++) 
			    					{
			    						Productos.M_Medicamentos.removeRow(0);
			    					}
			    					
			    					//Se consulta para actualizar los datos
			    					//Sentencia
			    					
			    					Sentenciar=LaConexion.prepareStatement("SELECT id, nombre, dosis, precio, caducidad, cantidad FROM medicamentos");
			    					Rs=Sentenciar.executeQuery();
			    					
			    					while(Rs.next())
				                    {
			    						String registros[] = {Rs.getString("id"), Rs.getString("nombre"), Rs.getString("dosis"), Rs.getString("precio")
				                        		, Rs.getString("caducidad"), Rs.getString("cantidad")};
				                        Productos.M_Medicamentos.addRow(registros);
				                       }

			    					Principal.jopre = "repetido";
        							JOPRe.dialog = new JOPRe();
        							JOPRe.dialog.setLocationRelativeTo(null);
        							JOPRe.dialog.setVisible(true);
			    					//JOptionPane.showMessageDialog(null, "Registro repetido. Se agregó al stock", "Aviso", JOptionPane.INFORMATION_MESSAGE);
			    					
			    				}
			    				else
			    				{
			    					Principal.jopre = "vacio";
	    							JOPRe.dialog = new JOPRe();
	    							JOPRe.dialog.setLocationRelativeTo(null);
	    							JOPRe.dialog.setVisible(true);
			    					//JOptionPane.showMessageDialog(null, "No se pudo modificar", "Error", JOptionPane.ERROR_MESSAGE);
			    					TxtProductos.requestFocus();
			    				}
			    				LaConexion.close();
								
								/*
								 * 
								 * AL REPETIRSE SE PREGUNTA SI DESEA AÑADIR UNA UNIDAD AL PRODUCTO DE ESE NOMBRE
								 * 
								 * BTW, QUE SE PUEDA AÑADIR UNIDADES DE UN PRODUCTO CON UN SPINNER O BIEN BOTONES DE + Y -
								 * 
								 * */
								
							}
							
						}
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
						
						if(Menu.x != 1) {
							
							PreparedStatement SentenciaP = LaConexion.prepareStatement("INSERT INTO medicamentos (nombre, dosis, precio, caducidad, cantidad) VALUES(?,?,?,?,?)");
							
							SentenciaP.setString(1,TxtProductos.getText());
							SentenciaP.setString(2,TxtDosis.getText());
							SentenciaP.setString(3,TxtPrecio.getText());
							SentenciaP.setString(4,fecha);
							SentenciaP.setString(5,TxtCantidad.getText());
							
							Registros = SentenciaP.executeUpdate();
							
							while(Productos.TablaMedicamentos.getRowCount() > 0)
			                {
								Productos.M_Medicamentos.removeRow(0);
			                }
							
							if(Registros>=1)
							{
								
								TxtProductos.setText("");
								TxtPrecio.setText("");
								LblPrecio.setText("Precio");
								TxtDosis.setText("");
								LblDosis.setText("Dosis");
								TxtCantidad.setText("");
								LblCantidad.setText("Cantidad");
								TxtProductos.requestFocus();
								
								String sql = "SELECT id, nombre, dosis, precio, caducidad, cantidad FROM medicamentos";
								Rs = Sentencia.executeQuery(sql);
								while(Rs.next())
								{
									Object [] fila = new Object[6]; 

									  
									   for (int i=0;i<6;i++) {
										   fila[i] = Rs.getObject(i+1); 
										   
										   }
									     
									   Productos.M_Medicamentos.addRow(fila);
									   
								}
							
								Principal.jopre = "exito";
    							JOPRe.dialog = new JOPRe();
    							JOPRe.dialog.setLocationRelativeTo(null);
    							JOPRe.dialog.setVisible(true);
								//JOptionPane.showMessageDialog(null,"Se Registro Correctamente", "Aviso", JOptionPane.INFORMATION_MESSAGE);
		                           
							}
							else {
								
								Principal.jopre = "error";
    							JOPRe.dialog = new JOPRe();
    							JOPRe.dialog.setLocationRelativeTo(null);
    							JOPRe.dialog.setVisible(true);
								//JOptionPane.showMessageDialog(null,"Fallo inesperado", "Error", JOptionPane.ERROR_MESSAGE);
							
							LaConexion.close();
							
							}
							
						}
						
					}catch(Exception el)
					{
						
						Principal.jopre = "error";
						JOPRe.dialog = new JOPRe();
						JOPRe.dialog.setLocationRelativeTo(null);
						JOPRe.dialog.setVisible(true);
					//	JOptionPane.showMessageDialog(null,el.toString(), "Error", JOptionPane.ERROR_MESSAGE);
					
					}
					
				}else if(valido == 3) {
					
					try {
						
						LaConexion = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/loginuser", "root", "LFMG2004");
						
						Sentencia = LaConexion.createStatement();

						//Insertar datos
						int Registros;
						
						for(int i = 0; i < 7; i++) {
							
							String TablaMinuscula = (String) Productos.TablaAlimentos.getValueAt(i, 1);
							TablaMinuscula = TablaMinuscula.toLowerCase();
							String TextoMinuscula = TxtProductos.getText().toLowerCase();
							
							int Id = 0;
							String Ubicacion = "";  
							int filas = Productos.TablaAlimentos.getSelectedRow();
							
							if(TablaMinuscula.equals(TextoMinuscula)) {

								int Cantidad = (int) Productos.TablaAlimentos.getValueAt(i, 5);
								Cantidad++;
								
								Menu.x = 1;
								
								LaConexion = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/loginuser", "root", "LFMG2004");
			                    
			                    Statement Sentenciap;
			                    Sentenciap = LaConexion.createStatement();

			    				if (filas >=0) 
			    				{
			    					Ubicacion = String.valueOf(Productos.TablaAlimentos.getValueAt(filas, 0));	
			    				}
			    				else
			    				{
			    					Principal.jopre = "eliminar";
	    							JOPRe.dialog = new JOPRe();
	    							JOPRe.dialog.setLocationRelativeTo(null);
	    							JOPRe.dialog.setVisible(true);
			    					//JOptionPane.showMessageDialog(null, "No se ha seleccionado un registro", "ERROR", JOptionPane.ERROR_MESSAGE);
			    				}
			                    
			                   Rs = Sentenciap.executeQuery("SELECT id FROM alimentos WHERE id = '"+Ubicacion+"' ");
			                   
			                   while(Rs.next())
			                   {
			                	   Id = Integer.parseInt(Rs.getString("id"));
			                   }
			                   
			                    PreparedStatement Sentenciar = LaConexion.prepareStatement("UPDATE alimentos SET cantidad = ? WHERE id = ?");
							    
			                    Sentenciar.setInt(1, Cantidad);
			                    Sentenciar.setInt(2, Id);
			                    
			                    Registros = Sentenciar.executeUpdate();
			                    
			    				if(Registros >= 1)
			    				{
			    					
			    					dispose();

			    					//Borra Las filas de las tabla
			    					int filascount = Productos.TablaAlimentos.getRowCount();
			  					  
			    					for(i = 0; i < filascount; i++) 
			    					{
			    						Productos.M_Alimentos.removeRow(0);
			    					}
			    					
			    					//Se consulta para actualizar los datos
			    					//Sentencia
			    					
			    					Sentenciar=LaConexion.prepareStatement("SELECT id, producto, marca, precio, kilogramos, caducidad, cantidad FROM alimentos");
			    					Rs=Sentenciar.executeQuery();
			    					
			    					while(Rs.next())
				                    {
			    						String registros[] = {Rs.getString("id"), Rs.getString("producto"), Rs.getString("marca"), Rs.getString("precio")
				                        		, Rs.getString("kilogramos"), Rs.getString("caducidad"), Rs.getString("cantidad")};
				                        Productos.M_Alimentos.addRow(registros);
				                       }

			    					Principal.jopre = "repetido";
        							JOPRe.dialog = new JOPRe();
        							JOPRe.dialog.setLocationRelativeTo(null);
        							JOPRe.dialog.setVisible(true);
			    					//JOptionPane.showMessageDialog(null, "Registro repetido. Se agregó al stock", "Aviso", JOptionPane.INFORMATION_MESSAGE);
			    					
			    				}
			    				else
			    				{
			    					Principal.jopre = "vacio";
	    							JOPRe.dialog = new JOPRe();
	    							JOPRe.dialog.setLocationRelativeTo(null);
	    							JOPRe.dialog.setVisible(true);
			    					//JOptionPane.showMessageDialog(null, "No se pudo modificar", "Error", JOptionPane.ERROR_MESSAGE);
			    					TxtProductos.requestFocus();
			    				}
			    				LaConexion.close();
								
								/*
								 * 
								 * AL REPETIRSE SE PREGUNTA SI DESEA AÑADIR UNA UNIDAD AL PRODUCTO DE ESE NOMBRE
								 * 
								 * BTW, QUE SE PUEDA AÑADIR UNIDADES DE UN PRODUCTO CON UN SPINNER O BIEN BOTONES DE + Y -
								 * 
								 * */
								
							}
							
						}
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
						
						if(Menu.x != 1) {
							
							PreparedStatement SentenciaP = LaConexion.prepareStatement("INSERT INTO alimentos (producto, marca, precio, kilogramos, caducidad, cantidad) VALUES(?,?,?,?,?,?)");
							
							SentenciaP.setString(1,TxtProductos.getText());
							SentenciaP.setString(2,TxtMarca.getText());
							SentenciaP.setString(3,TxtPrecio.getText());
							SentenciaP.setString(4,TxtKg.getText());
							SentenciaP.setString(5,fecha);
							SentenciaP.setString(6,TxtCantidad.getText());
							
							Registros = SentenciaP.executeUpdate();
							
							while(Productos.TablaAlimentos.getRowCount() > 0)
			                {
								Productos.M_Alimentos.removeRow(0);
			                }
							
							if(Registros>=1)
							{
								
								TxtProductos.setText("");
								TxtPrecio.setText("");
								LblPrecio.setText("Precio");
								TxtKg.setText("");
								LblKg.setText("Kilogramos");
								TxtCantidad.setText("");
								LblCantidad.setText("Cantidad");
								TxtProductos.requestFocus();
								
								String sql = "SELECT id, producto, marca, precio, kilogramos, caducidad, cantidad FROM alimentos";
								Rs = Sentencia.executeQuery(sql);
								while(Rs.next())
								{
									Object [] fila = new Object[7]; 

									  
									   for (int i=0;i<7;i++) {
										   fila[i] = Rs.getObject(i+1); 
										   
										   }
									     
									   Productos.M_Alimentos.addRow(fila);
									   
								}
							
								Principal.jopre = "exito";
    							JOPRe.dialog = new JOPRe();
    							JOPRe.dialog.setLocationRelativeTo(null);
    							JOPRe.dialog.setVisible(true);
								//JOptionPane.showMessageDialog(null,"Se Registro Correctamente", "Aviso", JOptionPane.INFORMATION_MESSAGE);
		                           
							}
							else {
								
								Principal.jopre = "error";
    							JOPRe.dialog = new JOPRe();
    							JOPRe.dialog.setLocationRelativeTo(null);
    							JOPRe.dialog.setVisible(true);
								//JOptionPane.showMessageDialog(null,"Fallo inesperado", "Error", JOptionPane.ERROR_MESSAGE);
							
							LaConexion.close();
							
							}
							
						}
						
					}catch(Exception el)
					{
						
						Principal.jopre = "error";
						JOPRe.dialog = new JOPRe();
						JOPRe.dialog.setLocationRelativeTo(null);
						JOPRe.dialog.setVisible(true);
					//	JOptionPane.showMessageDialog(null,el.toString(), "Error", JOptionPane.ERROR_MESSAGE);
					
					}
					
				}
				
			}
			@Override
			public void mouseExited(MouseEvent e) {
				
				BtnAgregar.setIcon(new ImageIcon(Agregar.class.getResource("/Img/anadir.png")));
				BtnAgregar.setBounds(15, 10, 40, 40);
				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				
				BtnAgregar.setIcon(new ImageIcon(Agregar.class.getResource("/Img/anadir - copia.png")));
				BtnAgregar.setBounds(15, 5, 50, 50);
				
			}
		});
		BtnAgregar.setIcon(new ImageIcon(Agregar.class.getResource("/Img/anadir.png")));
		BtnAgregar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		BtnAgregar.setBounds(15, 10, 40, 40);
		PanelDown.add(BtnAgregar);

		BtnModificar2 = new JLabel("");
		BtnModificar2.setToolTipText("Modificar un producto");
		BtnModificar2.setEnabled(false);
		BtnModificar2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(Menu.x == 1 || Menu.x == 4) {
					
					Connection LaConexion;
					ResultSet Rs; 
					int Id = 0, Registros=0;
					String Ubicacion = "";  
					int filas = Productos.TablaAccesorios.getSelectedRow();
					
					//Obtiene la longitud de cada campo de texto y lo compara si es 0 (es decir que no contiene nada, entonces no permitirï¿½ capturar)
					if(TxtProductos.getText().length()==0 || TxtPrecio.getText().length()==0 || TxtDetalles.getText().length()==0
							 || TxtCantidad.getText().length() == 0)
					{
						Principal.jopre = "invalido";
						JOPRe.dialog = new JOPRe();
						JOPRe.dialog.setLocationRelativeTo(null);
						JOPRe.dialog.setVisible(true);
						//JOptionPane.showMessageDialog(null,"Ingrese los datos", "Error", JOptionPane.ERROR_MESSAGE);
						TxtProductos.requestFocus();
						
					}
					else
					{
						try {
							
							LaConexion = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/loginuser", "root", "LFMG2004");
		                    
		                    Statement Sentenciap;
		                    Sentenciap = LaConexion.createStatement();

		    				if (filas >=0) 
		    				{
		    					Ubicacion = String.valueOf(Productos.TablaAccesorios.getValueAt(filas, 0));	
		    				}
		    				else
		    				{
		    					
		    					Principal.jopre = "eliminar";
    							JOPRe.dialog = new JOPRe();
    							JOPRe.dialog.setLocationRelativeTo(null);
    							JOPRe.dialog.setVisible(true);
		    					//JOptionPane.showMessageDialog(null, "No se ha seleccionado un registro", "ERROR", JOptionPane.ERROR_MESSAGE);
		    				}
		                    
		                   Rs = Sentenciap.executeQuery("SELECT id FROM accesorios WHERE id = '"+Ubicacion+"' ");
		                   
		                   while(Rs.next())
		                   {
		                	   Id = Integer.parseInt(Rs.getString("id"));
		                   }
		                   
		                    PreparedStatement Sentencia = LaConexion.prepareStatement("UPDATE accesorios SET nombre= ?, precio = ?, detalles= ?, cantidad = ? WHERE id = ?");
						    
		                    Sentencia.setString(1, TxtProductos.getText());
		                    Sentencia.setFloat(2, Float.parseFloat(TxtPrecio.getText()));
		                    Sentencia.setString(3, TxtDetalles.getText());
		                    Sentencia.setString(4, TxtCantidad.getText());
		                    Sentencia.setInt(5, Id);
		                    
		                    Registros = Sentencia.executeUpdate();
		                    
		    				if(Registros >= 1)
		    				{
	
		    					dispose();

		    					//Borra Las filas de las tabla
		    					int i, filascount = Productos.M_Accesorios.getRowCount();
		  					  
		    					for(i=0; i<filascount; i++) 
		    					{
		    						Productos.M_Accesorios.removeRow(0);
		    					}
		    					//Se consulta para actualizar los datos
		    					//Sentencia
		    					
		    					Sentencia=LaConexion.prepareStatement("SELECT id, nombre, precio, detalles, cantidad FROM accesorios");
		    					Rs=Sentencia.executeQuery();
		    					
		    					while(Rs.next())
			                    {
			                        String registros[] = {Rs.getString("id"), Rs.getString("nombre"), Rs.getString("precio"), Rs.getString("detalles")
			                        		, Rs.getString("cantidad")};
			                        Productos.M_Accesorios.addRow(registros);
			                       }

		    					Principal.jopre = "exito";
    							JOPRe.dialog = new JOPRe();
    							JOPRe.dialog.setLocationRelativeTo(null);
    							JOPRe.dialog.setVisible(true);
		    					//JOptionPane.showMessageDialog(null, "Registro modificado", "Aviso", JOptionPane.INFORMATION_MESSAGE);
		    					Menu.x = 1;
		    					
		    				}
		    				else
		    				{
		    					Principal.jopre = "vacio";
    							JOPRe.dialog = new JOPRe();
    							JOPRe.dialog.setLocationRelativeTo(null);
    							JOPRe.dialog.setVisible(true);
		    					//JOptionPane.showMessageDialog(null, "No se pudo modificar", "Error", JOptionPane.ERROR_MESSAGE);
		    					TxtProductos.requestFocus();
		    				}
		    				LaConexion.close();
							
						}catch(Exception el)
						{
							Principal.jopre = "invalido";
							JOPRe.dialog = new JOPRe();
							JOPRe.dialog.setLocationRelativeTo(null);
							JOPRe.dialog.setVisible(true);
							//JOptionPane.showMessageDialog(null, "Datos no válidos", "Error", JOptionPane.ERROR_MESSAGE);
							TxtProductos.requestFocus();

						}
					}
					
				}else if(Menu.x == 2 || Menu.x == 5) {
					
					Connection LaConexion;
					ResultSet Rs; 
					int Id = 0, Registros=0;
					String Ubicacion = "";  
					int filas = Productos.TablaMedicamentos.getSelectedRow();
					
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
					
					//Obtiene la longitud de cada campo de texto y lo compara si es 0 (es decir que no contiene nada, entonces no permitirï¿½ capturar)
					if(TxtProductos.getText().length()==0 || TxtDosis.getText().length()==0 || TxtPrecio.getText().length()==0
							 || TxtCantidad.getText().length() == 0)
					{
						Principal.jopre = "invalido";
						JOPRe.dialog = new JOPRe();
						JOPRe.dialog.setLocationRelativeTo(null);
						JOPRe.dialog.setVisible(true);
						//JOptionPane.showMessageDialog(null,"Ingrese los datos", "Error", JOptionPane.ERROR_MESSAGE);
						TxtProductos.requestFocus();
						
					}
					else
					{
						try {
							
							LaConexion = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/loginuser", "root", "LFMG2004");
		                    
		                    Statement Sentenciap;
		                    Sentenciap = LaConexion.createStatement();

		    				if (filas >=0) 
		    				{
		    					Ubicacion = String.valueOf(Productos.TablaMedicamentos.getValueAt(filas, 0));	
		    				}
		    				else
		    				{
		    					Principal.jopre = "eliminar";
    							JOPRe.dialog = new JOPRe();
    							JOPRe.dialog.setLocationRelativeTo(null);
    							JOPRe.dialog.setVisible(true);
		    					//JOptionPane.showMessageDialog(null, "No se ha seleccionado un registro", "ERROR", JOptionPane.ERROR_MESSAGE);
		    				}
		                    
		                   Rs = Sentenciap.executeQuery("SELECT id FROM medicamentos WHERE id = '"+Ubicacion+"' ");
		                   
		                   while(Rs.next())
		                   {
		                	   Id = Integer.parseInt(Rs.getString("id"));
		                   }
		                   
		                    PreparedStatement Sentencia = LaConexion.prepareStatement("UPDATE medicamentos SET nombre= ?, dosis = ?, precio= ?"
		                    		+ ", caducidad = ?, cantidad = ? WHERE id = ?");
						    
		                    Sentencia.setString(1, TxtProductos.getText());
		                    Sentencia.setFloat(2, Float.parseFloat(TxtDosis.getText()));
		                    Sentencia.setFloat(3, Float.parseFloat(TxtPrecio.getText()));
		                    Sentencia.setString(4, fecha);
		                    Sentencia.setString(5, TxtCantidad.getText());
		                    Sentencia.setInt(6, Id);
		                    
		                    Registros = Sentencia.executeUpdate();
		                    
		    				if(Registros >= 1)
		    				{
	
		    					dispose();

		    					//Borra Las filas de las tabla
		    					int i, filascount = Productos.M_Medicamentos.getRowCount();
		  					  
		    					for(i=0; i<filascount; i++) 
		    					{
		    						Productos.M_Medicamentos.removeRow(0);
		    					}
		    					//Se consulta para actualizar los datos
		    					//Sentencia
		    					
		    					Sentencia=LaConexion.prepareStatement("SELECT id, nombre, dosis, precio, caducidad, cantidad FROM medicamentos");
		    					Rs=Sentencia.executeQuery();
		    					
		    					while(Rs.next())
			                    {
			                        String registros[] = {Rs.getString("id"), Rs.getString("nombre"), Rs.getString("dosis"), Rs.getString("precio")
			                        		, Rs.getString("caducidad"), Rs.getString("cantidad")};
			                        Productos.M_Medicamentos.addRow(registros);
			                       }

		    					Principal.jopre = "exito";
    							JOPRe.dialog = new JOPRe();
    							JOPRe.dialog.setLocationRelativeTo(null);
    							JOPRe.dialog.setVisible(true);
		    					//JOptionPane.showMessageDialog(null, "Registro modificado", "Aviso", JOptionPane.INFORMATION_MESSAGE);
		    					Menu.x = 2;
		    					TxtProductos.requestFocus();
		    					
		    				}
		    				else
		    				{
		    					Principal.jopre = "vacio";
    							JOPRe.dialog = new JOPRe();
    							JOPRe.dialog.setLocationRelativeTo(null);
    							JOPRe.dialog.setVisible(true);
		    					//JOptionPane.showMessageDialog(null, "No se pudo modificar", "Error", JOptionPane.ERROR_MESSAGE);
		    					TxtProductos.requestFocus();
		    				}
		    				LaConexion.close();
							
						}catch(Exception el)
						{
							Principal.jopre = "error";
							JOPRe.dialog = new JOPRe();
							JOPRe.dialog.setLocationRelativeTo(null);
							JOPRe.dialog.setVisible(true);
							//JOptionPane.showMessageDialog(null,el.toString(), "Error", JOptionPane.ERROR_MESSAGE);
							TxtProductos.requestFocus();

						}
					}
					
				}else if(Menu.x == 3 || Menu.x == 6) {
					
					Connection LaConexion;
					ResultSet Rs; 
					int Id = 0, Registros=0;
					String Ubicacion = "";  
					int filas = Productos.TablaAlimentos.getSelectedRow();
					
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
					
					//Obtiene la longitud de cada campo de texto y lo compara si es 0 (es decir que no contiene nada, entonces no permitirï¿½ capturar)
					if(TxtProductos.getText().length()==0 || TxtPrecio.getText().length()==0 || TxtMarca.getText().length()==0
							|| TxtKg.getText().length() == 0 || TxtCantidad.getText().length() == 0)
					{
						Principal.jopre = "invalido";
						JOPRe.dialog = new JOPRe();
						JOPRe.dialog.setLocationRelativeTo(null);
						JOPRe.dialog.setVisible(true);
						//JOptionPane.showMessageDialog(null,"Ingrese los datos", "Error", JOptionPane.ERROR_MESSAGE);
						TxtProductos.requestFocus();
						
					}
					else
					{
						try {
							
							LaConexion = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/loginuser", "root", "LFMG2004");
		                    
		                    Statement Sentenciap;
		                    Sentenciap = LaConexion.createStatement();

		    				if (filas >=0) 
		    				{
		    					Ubicacion = String.valueOf(Productos.TablaAlimentos.getValueAt(filas, 0));	
		    				}
		    				else
		    				{
		    					Principal.jopre = "eliminar";
    							JOPRe.dialog = new JOPRe();
    							JOPRe.dialog.setLocationRelativeTo(null);
    							JOPRe.dialog.setVisible(true);
		    					//JOptionPane.showMessageDialog(null, "No se ha seleccionado un registro", "ERROR", JOptionPane.ERROR_MESSAGE);
		    				}
		                    
		                   Rs = Sentenciap.executeQuery("SELECT id FROM alimentos WHERE id = '"+Ubicacion+"' ");
		                   
		                   while(Rs.next())
		                   {
		                	   Id = Integer.parseInt(Rs.getString("id"));
		                   }
		                   
		                    PreparedStatement Sentencia = LaConexion.prepareStatement("UPDATE alimentos SET producto= ?, marca = ?, "
		                    		+ "precio= ?, kilogramos = ?, caducidad = ?, cantidad = ? WHERE id = ?");
						    
		                    Sentencia.setString(1, TxtProductos.getText());
		                    Sentencia.setString(2, TxtMarca.getText());
		                    Sentencia.setFloat(3, Float.parseFloat(TxtPrecio.getText()));
		                    Sentencia.setFloat(4, Float.parseFloat(TxtKg.getText()));
		                    Sentencia.setString(5, fecha);
		                    Sentencia.setString(6, TxtCantidad.getText());
		                    Sentencia.setInt(7, Id);
		                    
		                    Registros = Sentencia.executeUpdate();
		                    
		    				if(Registros >= 1)
		    				{
	
		    					dispose();

		    					//Borra Las filas de las tabla
		    					int i, filascount = Productos.M_Alimentos.getRowCount();
		  					  
		    					for(i=0; i<filascount; i++) 
		    					{
		    						Productos.M_Alimentos.removeRow(0);
		    					}
		    					//Se consulta para actualizar los datos
		    					//Sentencia
		    					
		    					Sentencia=LaConexion.prepareStatement("SELECT id, producto, marca, precio, kilogramos, caducidad, cantidad "
		    							+ "FROM alimentos");
		    					Rs=Sentencia.executeQuery();
		    					
		    					while(Rs.next())
			                    {
			                        String registros[] = {Rs.getString("id"), Rs.getString("producto"), Rs.getString("marca"), Rs.getString("precio")
			                        		, Rs.getString("kilogramos"), Rs.getString("caducidad"), Rs.getString("cantidad")};
			                        Productos.M_Alimentos.addRow(registros);
			                       }

		    					Principal.jopre = "exito";
    							JOPRe.dialog = new JOPRe();
    							JOPRe.dialog.setLocationRelativeTo(null);
    							JOPRe.dialog.setVisible(true);
		    					//JOptionPane.showMessageDialog(null, "Registro modificado", "Aviso", JOptionPane.INFORMATION_MESSAGE);
		    					Menu.x = 3;
		    					
		    				}
		    				else
		    				{
		    					Principal.jopre = "vacio";
    							JOPRe.dialog = new JOPRe();
    							JOPRe.dialog.setLocationRelativeTo(null);
    							JOPRe.dialog.setVisible(true);
		    					//JOptionPane.showMessageDialog(null, "No se pudo modificar", "Error", JOptionPane.ERROR_MESSAGE);
		    					TxtProductos.requestFocus();
		    				}
		    				LaConexion.close();
							
						}catch(Exception el)
						{
							
							Principal.jopre = "error";
							JOPRe.dialog = new JOPRe();
							JOPRe.dialog.setLocationRelativeTo(null);
							JOPRe.dialog.setVisible(true);
							//JOptionPane.showMessageDialog(null,el.toString(), "Error", JOptionPane.ERROR_MESSAGE);
							TxtProductos.requestFocus();

						}
					}
					
				}
				
			}
			@Override
			public void mouseExited(MouseEvent e) {
				
				BtnModificar2.setIcon(new ImageIcon(Agregar.class.getResource("/Img/lapiz.png")));
				BtnModificar2.setBounds(81, 10, 40, 40);
				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				
				if(BtnModificar2.isEnabled() == true) {
					
					BtnModificar2.setIcon(new ImageIcon(Agregar.class.getResource("/Img/lapiz - copia.png")));
					BtnModificar2.setBounds(81, 5, 50, 50);
					
				}
				
			}
		});
		BtnModificar2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		BtnModificar2.setIcon(new ImageIcon(Agregar.class.getResource("/Img/lapiz.png")));
		BtnModificar2.setBounds(81, 10, 40, 40);
		PanelDown.add(BtnModificar2);
		
		JLabel label = new JLabel("New label");
		label.setBounds(752, 5, -43, 34);
		PanelDown.add(label);
		
		JLabel LblCerrar = new JLabel("");
		LblCerrar.setToolTipText("Cerrar la ventana");
		LblCerrar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				Menu.x = 1;
				dispose();
				
			}
			@Override
			public void mouseExited(MouseEvent e) {
				
				LblCerrar.setIcon(new ImageIcon(Agregar.class.getResource("/Img/resta.png")));
				LblCerrar.setBounds(745, 5, 50, 50);
				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				
				LblCerrar.setIcon(new ImageIcon(Agregar.class.getResource("/Img/resta - copia.png")));
				LblCerrar.setBounds(745, 0, 60, 60);
				
			}
		});
		LblCerrar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		LblCerrar.setIcon(new ImageIcon(Agregar.class.getResource("/Img/resta.png")));
		LblCerrar.setBounds(745, 5, 50, 50);
		PanelDown.add(LblCerrar);
		
		JPanel PanelArrastrar = new JPanel();
		PanelArrastrar.setBorder(new LineBorder(new Color(0, 0, 0)));
		PanelArrastrar.setBackground(new Color(0, 139, 139));
		PanelArrastrar.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				
				dialog.setLocation(dialog.getX() + e.getX() - mouseX, dialog.getY() + e.getY() - mouseY);
				
			}
		});
		PanelArrastrar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				
				mouseX = e.getX();
				mouseY = e.getY();
				
			}
		});
		PanelArrastrar.setBounds(0, 0, 820, 47);
		contentPanel.add(PanelArrastrar);
		PanelArrastrar.setLayout(null);
		
		JLabel LblTitulo = new JLabel("Agregar/Modificar");
		LblTitulo.setForeground(Color.WHITE);
		LblTitulo.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 36));
		LblTitulo.setBounds(10, 0, 331, 45);
		PanelArrastrar.add(LblTitulo);
		
		JLabel LblPrecio_1 = new JLabel("");
		LblPrecio_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		LblPrecio_1.setBounds(65, 86, 90, 2);
		contentPanel.add(LblPrecio_1);
		
		JLabel LblPrecio_1_1 = new JLabel("");
		LblPrecio_1_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		LblPrecio_1_1.setBounds(62, 155, 90, 2);
		contentPanel.add(LblPrecio_1_1);
		
		LblPrecio = new JLabel("Precio");
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
		TxtPrecio.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				LblPrecio.setText("");
				
			}
		});
		LblPrecio.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		LblPrecio.setForeground(Color.DARK_GRAY);
		LblPrecio.setBounds(66, 135, 86, 20);
		contentPanel.add(LblPrecio);
		
		LblDosis = new JLabel("Dosis");
		LblDosis.setToolTipText("");
		LblDosis.setForeground(Color.GRAY);
		LblDosis.setBounds(66, 216, 86, 20);
		contentPanel.add(LblDosis);
		
		LblMl = new JLabel("Mml.");
		LblMl.setBounds(160, 219, 46, 14);
		contentPanel.add(LblMl);
		
		JLabel LblDinero = new JLabel("$");
		LblDinero.setFont(new Font("Tahoma", Font.PLAIN, 18));
		LblDinero.setBounds(45, 135, 14, 20);
		contentPanel.add(LblDinero);
		
		LblMarca = new JLabel("Marca");
		LblMarca.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		LblMarca.setForeground(Color.GRAY);
		LblMarca.setBounds(66, 380, 86, 20);
		contentPanel.add(LblMarca);
		
		LblKg = new JLabel("Kg.");
		LblKg.setForeground(Color.GRAY);
		LblKg.setBounds(66, 455, 86, 20);
		contentPanel.add(LblKg);
		
		JLabel LblLineaMarca_1 = new JLabel("");
		LblLineaMarca_1.setEnabled(false);
		LblLineaMarca_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		LblLineaMarca_1.setBounds(472, 400, 85, 2);
		contentPanel.add(LblLineaMarca_1);
		
		LblCantidad.setForeground(Color.DARK_GRAY);
		LblCantidad.setBounds(472, 380, 86, 20);
		contentPanel.add(LblCantidad);
		
		TxtKg.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(TxtKg.isEnabled() == true){
					
					LblKg.setText("");
					
				}
				
			}
		});
		TxtKg.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				
				if(TxtKg.getText().length() == 0) {
					
					LblKg.setText("Kg.");
					
				}
				
				
			}
			@Override
			public void focusGained(FocusEvent e) {
				
				LblKg.setText("");
				
			}
		});
		
		TxtMarca.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				
				if(TxtMarca.getText().length() == 0) {
					
					LblMarca.setText("Marca");
					
				}
				
			}
			@Override
			public void focusGained(FocusEvent e) {
				
				if(TxtMarca.isEnabled() == true) {
					
					LblMarca.setText("");
					
				}
				
			}
		});
		TxtMarca.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(TxtMarca.isEnabled() == true) {
					
					LblMarca.setText("");
					
				}
				
			}
		});
		
		//Modificar Accesorios
		if(Menu.x == 4) {
			
			BtnModificar2.setEnabled(true);
			CmbProductos.setSelectedIndex(0);
			LblDetalles.setEnabled(true);
			TxtDetalles.setVisible(true);
			LblDosisLinea.setEnabled(false);
			LblCaducidad.setEnabled(false);
			LblLineaMarca.setEnabled(false);
			LblKgLinea.setEnabled(false);
			TxtDosis.setEnabled(false);
			CmbDia.setEnabled(false);
			CmbMes.setEnabled(false);
			CmbYear.setEnabled(false);
			TxtMarca.setEnabled(false);
			TxtKg.setEnabled(false);

			TxtProductos.setText(""+Productos.TablaAccesorios.getValueAt(Productos.TablaAccesorios.getSelectedRow(), 1));
			TxtPrecio.setText(""+Productos.TablaAccesorios.getValueAt(Productos.TablaAccesorios.getSelectedRow(), 2));
			LblPrecio.setText("");
			LblCantidad.setText("");
			TxtDetalles.setText(""+Productos.TablaAccesorios.getValueAt(Productos.TablaAccesorios.getSelectedRow(), 3));
			TxtCantidad.setText(""+Productos.TablaAccesorios.getValueAt(Productos.TablaAccesorios.getSelectedRow(), 4));
			
		//Modificar Medicamentos
		}else if(Menu.x == 5) {
			
			BtnModificar2.setEnabled(true);
			CmbProductos.setSelectedIndex(1);
			TxtDetalles.setVisible(false);
			LblDetalles.setEnabled(false);
			LblDosisLinea.setEnabled(true);
			TxtDosis.setEnabled(true);
			CmbDia.setEnabled(true);
			CmbMes.setEnabled(true);
			CmbYear.setEnabled(true);
			LblCaducidad.setEnabled(true);
			LblLineaMarca.setEnabled(false);
			LblKgLinea.setEnabled(false);
			TxtMarca.setEnabled(false);
			TxtKg.setEnabled(false);
			
			TxtProductos.setText(""+Productos.TablaMedicamentos.getValueAt(Productos.TablaMedicamentos.getSelectedRow(), 1));
			TxtDosis.setText(""+Productos.TablaMedicamentos.getValueAt(Productos.TablaMedicamentos.getSelectedRow(), 2));
			TxtPrecio.setText(""+Productos.TablaMedicamentos.getValueAt(Productos.TablaMedicamentos.getSelectedRow(), 3));
			LblCantidad.setText("");
			TxtCantidad.setText(""+Productos.TablaMedicamentos.getValueAt(Productos.TablaMedicamentos.getSelectedRow(), 5));
			LblDosis.setText("");
			LblPrecio.setText("");
			
		//Modificar Alimentos
		}else if(Menu.x == 6) {
			
			BtnModificar2.setEnabled(true);
			CmbProductos.setSelectedIndex(2);
			TxtDetalles.setVisible(false);
			LblDetalles.setEnabled(false);
			LblDosisLinea.setEnabled(false);
			LblCaducidad.setEnabled(true);
			LblLineaMarca.setEnabled(true);
			LblKgLinea.setEnabled(true);
			TxtDosis.setEnabled(false);
			CmbDia.setEnabled(true);
			CmbMes.setEnabled(true);
			CmbYear.setEnabled(true);
			TxtMarca.setEnabled(true);
			TxtKg.setEnabled(true);
			
			TxtProductos.setText(""+Productos.TablaAlimentos.getValueAt(Productos.TablaAlimentos.getSelectedRow(), 1));
			TxtMarca.setText(""+Productos.TablaAlimentos.getValueAt(Productos.TablaAlimentos.getSelectedRow(), 2));
			TxtPrecio.setText(""+Productos.TablaAlimentos.getValueAt(Productos.TablaAlimentos.getSelectedRow(), 3));
			TxtKg.setText(""+Productos.TablaAlimentos.getValueAt(Productos.TablaAlimentos.getSelectedRow(), 4));
			LblCantidad.setText("");
			TxtCantidad.setText(""+Productos.TablaAlimentos.getValueAt(Productos.TablaAlimentos.getSelectedRow(), 6));
			LblMarca.setText("");
			LblPrecio.setText("");
			LblKg.setText("");

			//Agreagar ACCESORIOS
		}else if(Productos.tabbedPane.getSelectedIndex() < 1 && Menu.x == 0) {

			BtnModificar2.setEnabled(false);
			CmbProductos.setSelectedIndex(0);
			LblDetalles.setEnabled(true);
			TxtDetalles.setVisible(true);
			TxtDetalles.setText("");
			TxtPrecio.setText("");
			TxtProductos.setText("");
			LblDosisLinea.setEnabled(false);
			LblCaducidad.setEnabled(false);
			LblLineaMarca.setEnabled(false);
			LblKgLinea.setEnabled(false);
			TxtDosis.setEnabled(false);
			CmbDia.setEnabled(false);
			CmbMes.setEnabled(false);
			CmbYear.setEnabled(false);
			TxtMarca.setEnabled(false);
			TxtKg.setEnabled(false);
			TxtCantidad.setText("");
			LblCantidad.setText("Cantidad");
			
			//Agregar MEDICAMENTOS
		}else if(Productos.tabbedPane.getSelectedIndex() == 1 && Menu.x == 0) {
			
			BtnModificar2.setEnabled(false);
			CmbProductos.setSelectedIndex(1);
			TxtDetalles.setVisible(false);
			LblDetalles.setEnabled(false);
			LblDosisLinea.setEnabled(true);
			TxtDosis.setEnabled(true);
			CmbDia.setEnabled(true);
			CmbMes.setEnabled(true);
			CmbYear.setEnabled(true);
			LblCaducidad.setEnabled(true);
			LblLineaMarca.setEnabled(false);
			LblKgLinea.setEnabled(false);
			TxtMarca.setEnabled(false);
			TxtKg.setEnabled(false);
			TxtCantidad.setText("");
			LblCantidad.setText("Cantidad");
			
			//Agregar ALIMENTOS
		}else if(Productos.tabbedPane.getSelectedIndex() == 2 && Menu.x == 0) {
			
			CmbProductos.setSelectedIndex(2);
			TxtDetalles.setVisible(false);
			LblDetalles.setEnabled(false);
			LblDosisLinea.setEnabled(false);
			LblCaducidad.setEnabled(true);
			LblLineaMarca.setEnabled(true);
			LblKgLinea.setEnabled(true);
			TxtDosis.setEnabled(false);
			CmbDia.setEnabled(true);
			CmbMes.setEnabled(true);
			CmbYear.setEnabled(true);
			TxtMarca.setEnabled(true);
			TxtKg.setEnabled(true);
			TxtCantidad.setText("");
			LblCantidad.setText("Cantidad");
			
		}
		
		CmbProductos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(CmbProductos.getSelectedItem().equals("Medicamentos")) {
					
					TxtDetalles.setVisible(false);
					LblDetalles.setEnabled(false);
					LblDosisLinea.setEnabled(true);
					TxtDosis.setEnabled(true);
					CmbDia.setEnabled(true);
					CmbMes.setEnabled(true);
					CmbYear.setEnabled(true);
					LblCaducidad.setEnabled(true);
					LblLineaMarca.setEnabled(false);
					LblKgLinea.setEnabled(false);
					TxtMarca.setEnabled(false);
					TxtKg.setEnabled(false);
					TxtMarca.setText("");
					TxtKg.setText("");
					LblKg.setText("Kg.");
					LblMarca.setText("Marca");
					TxtCantidad.setText("");
					LblCantidad.setText("Cantidad");
					
					LblKg.setForeground(Color.GRAY);
					LblMarca.setForeground(Color.GRAY);
					LblDosis.setForeground(Color.DARK_GRAY);
					
				}else if(CmbProductos.getSelectedItem().equals("Alimentos")) {
					
					TxtDetalles.setVisible(false);
					LblDetalles.setEnabled(false);
					LblDosisLinea.setEnabled(false);
					LblCaducidad.setEnabled(true);
					LblLineaMarca.setEnabled(true);
					LblKgLinea.setEnabled(true);
					TxtDosis.setEnabled(false);
					CmbDia.setEnabled(true);
					CmbMes.setEnabled(true);
					CmbYear.setEnabled(true);
					TxtMarca.setEnabled(true);
					TxtKg.setEnabled(true);
					TxtDosis.setText("");
					LblDosis.setText("Dosis");
					TxtCantidad.setText("");
					LblCantidad.setText("Cantidad");
					
					LblKg.setForeground(Color.DARK_GRAY);
					LblMarca.setForeground(Color.DARK_GRAY);
					LblDosis.setForeground(Color.GRAY);
					
				}else if(CmbProductos.getSelectedItem().equals("Accesorios")) {
					
					LblDetalles.setEnabled(true);
					TxtDetalles.setVisible(true);
					LblDosisLinea.setEnabled(false);
					LblCaducidad.setEnabled(false);
					LblLineaMarca.setEnabled(false);
					LblKgLinea.setEnabled(false);
					TxtDosis.setEnabled(false);
					CmbDia.setEnabled(false);
					CmbMes.setEnabled(false);
					CmbYear.setEnabled(false);
					TxtMarca.setEnabled(false);
					TxtKg.setEnabled(false);
					TxtDosis.setText("");
					TxtMarca.setText("");
					TxtKg.setText("");
					LblKg.setText("Kg.");
					LblMarca.setText("Marca");
					LblDosis.setText("Dosis");
					TxtCantidad.setText("");
					LblCantidad.setText("Cantidad");
					
					LblKg.setForeground(Color.GRAY);
					LblMarca.setForeground(Color.GRAY);
					LblDosis.setForeground(Color.GRAY);
			
				}
				
			}
		});
		
	}
}
