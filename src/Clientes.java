import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;

import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JLabel;
import java.awt.event.MouseMotionAdapter;
import javax.swing.ImageIcon;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Toolkit;

public class Clientes extends JDialog {

	private final JPanel contentPanel = new JPanel();
	public static JTable TablaClientes;
	private JTextField TxtBuscador;
	public static DefaultTableModel M_Clientes = new DefaultTableModel();
	public static Clientes dialog = new Clientes();
	private int mouseX, mouseY;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			dialog.setLocationRelativeTo(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	int xMouse, yMouse;
	
	Connection LaConexion = null;
	
	Statement Sentencia; //lanzar sql
	
	ResultSet Rs;
	
	public Clientes() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Clientes.class.getResource("/Img/d641af49873982095ee6c363ffc5144c.png")));
		setUndecorated(true);
		setTitle("Colitas - Clientes");
		setBounds(100, 100, 1299, 850);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(64, 224, 208));
		contentPanel.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		Menu.x = 0;
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel_1.setBackground(new Color(0, 102, 102));
		panel_1.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				
				dialog.setLocation(dialog.getX() + e.getX() - mouseX, dialog.getY() + e.getY() - mouseY);

				
			}
		});
		panel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				
				mouseX = e.getX();
				mouseY = e.getY();
				
			}
		});
		panel_1.setBounds(0, 0, 1299, 40);
		contentPanel.add(panel_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 51, 1279, 737);
		contentPanel.add(scrollPane);
		
		JPanel panel = new JPanel();
		
		TablaClientes = new JTable(){
			  @Override
			    public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
			      Component component = super.prepareRenderer(renderer, row, column);
			      int rendererWidth = component.getPreferredSize().width;
			      TableColumn tableColumn = getColumnModel().getColumn(column);
			      tableColumn.setPreferredWidth(Math.max(rendererWidth + getIntercellSpacing().width, tableColumn.getPreferredWidth()));
			      return component;
			    }
			  };
		TablaClientes.setRowHeight(25);
		TablaClientes.setBorder(new LineBorder(new Color(0, 0, 0)));
		TablaClientes.setSelectionBackground(new Color(51, 204, 204));
		TablaClientes.setToolTipText("Doble click sobre una fila para mostrar sus datos");
		TablaClientes.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		TablaClientes.setShowHorizontalLines(false);
		TablaClientes.setIntercellSpacing(new Dimension(0, 1));
		TablaClientes.setOpaque(false);
		TablaClientes.setFocusable(false);
		TablaClientes.setShowVerticalLines(false);
		
		TablaClientes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				if (e.getClickCount() == 2 && !e.isConsumed()) {
				     e.consume();
				     JOptionPane.showMessageDialog(null, "Id: " + TablaClientes.getValueAt(TablaClientes.getSelectedRow(), 0) + "\n" + "\nCliente: " +
				    		 TablaClientes.getValueAt(TablaClientes.getSelectedRow(), 1) + "\n" + "\nEspecie: " +
				    		 TablaClientes.getValueAt(TablaClientes.getSelectedRow(), 2) + "\n" + "\nDue\u00f1o: " +
				    		 TablaClientes.getValueAt(TablaClientes.getSelectedRow(), 3) + "\n" + "\nDescripci\u00f3n: " +
				    		 TablaClientes.getValueAt(TablaClientes.getSelectedRow(), 4) + "\n" + "\nFecha: " +
				    		 TablaClientes.getValueAt(TablaClientes.getSelectedRow(), 5) + "\n" + "\nFolio Receta: " + 
				    		 TablaClientes.getValueAt(TablaClientes.getSelectedRow(), 6) + "\n" + "\nTelefono: " +
				    		 TablaClientes.getValueAt(TablaClientes.getSelectedRow(), 7) + "\n" + "\nSexo: " +
				    		 TablaClientes.getValueAt(TablaClientes.getSelectedRow(), 8) + "\n" + "\nEdad: " +
				    		 TablaClientes.getValueAt(TablaClientes.getSelectedRow(), 9) + "\n" + "\nPrecio: " +
				    		 TablaClientes.getValueAt(TablaClientes.getSelectedRow(), 10));
				     //Evento de 2 click sobre un renglon
				}
				
			}
		});
		
		TablaClientes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		TablaClientes.setDefaultEditor(Object.class, null);
		
		TablaClientes.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		scrollPane.setViewportView(TablaClientes);
		
		M_Clientes.addColumn("Id");
		M_Clientes.addColumn("Cliente");
		M_Clientes.addColumn("Especie");
		M_Clientes.addColumn("Due\u00F1o");
		M_Clientes.addColumn("Descripcion");
		M_Clientes.addColumn("Fecha");
		M_Clientes.addColumn("Folio Receta");
		M_Clientes.addColumn("Telefono");
		M_Clientes.addColumn("Sexo");
		M_Clientes.addColumn("Edad");
		M_Clientes.addColumn("Precio");
		
		TablaClientes.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);	
		TablaClientes.getTableHeader().setResizingAllowed(false);
		TablaClientes.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
		TablaClientes.getTableHeader().setOpaque(false);
		TablaClientes.getTableHeader().setBackground(new Color (0, 102, 102));
		TablaClientes.getTableHeader().setForeground(new Color (255, 255, 255));
		TablaClientes.setRowHeight(25);
		
		TableRowSorter<DefaultTableModel> ordena = new TableRowSorter<>(M_Clientes);
		
		TablaClientes.setModel(M_Clientes);
		TablaClientes.setRowSorter(ordena);
		
		while(TablaClientes.getRowCount() > 0)
        {
            
            M_Clientes.setRowCount(0);
            M_Clientes.setColumnCount(11);
        }
		
		try {
			
			LaConexion = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/loginuser", "root", "LFMG2004");
			
			Sentencia = LaConexion.createStatement();
			
			String sql = "SELECT id, cliente, raza, dueno, descripcion, fechahora, folioreceta, telefono, sexo, edad, precio FROM clientes";
			Rs = Sentencia.executeQuery(sql);
		
			while(Rs.next())
			{
				Object [] fila = new Object[11]; 

				  
				   for (int i=0;i<11;i++) {
					   fila[i] = Rs.getObject(i+1); 
					   
					   }
				     
				   M_Clientes.addRow(fila);
		
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
		
		TxtBuscador = new JTextField();
		TxtBuscador.setToolTipText("Escribe el producto a buscar");
		TxtBuscador.setBorder(null);
		TxtBuscador.setOpaque(false);
		
		TxtBuscador.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				
				try {
					
					try 
					{

					Connection LaConexion;
					ResultSet Rs = null;

						String BusquedaTexto = TxtBuscador.getText();
						int i, filascount = M_Clientes.getRowCount();
						  
						for(i=0; i<filascount; i++) 
						{
							M_Clientes.removeRow(0);
						}
						
						LaConexion = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/loginuser", "root", "LFMG2004");
	                    
	                    Statement sentenciap;
	                    sentenciap = LaConexion.createStatement();

	                    Rs = sentenciap.executeQuery("SELECT id, cliente, raza, dueno, descripcion, fechahora, folioreceta, telefono, sexo, edad, precio FROM clientes "
                    			+ "WHERE folioreceta LIKE '%"+BusquedaTexto+"%'");
	                    
	                    	while(Rs.next())
	                        {
	                            String registros[] = {Rs.getString("id"), Rs.getString("cliente"), Rs.getString("raza"), Rs.getString("dueno")
	                            		, Rs.getString("descripcion"), Rs.getString("fechahora"), Rs.getString("folioreceta"), Rs.getString("telefono")
	                            		, Rs.getString("sexo"), Rs.getString("edad"), Rs.getString("precio")};
	                            M_Clientes.addRow(registros);
	                           }
					}
					catch(Exception el)
					{
						
						Principal.jopre = "invalido";
						JOPRe.dialog = new JOPRe();
						JOPRe.dialog.setLocationRelativeTo(null);
						JOPRe.dialog.setVisible(true);
						//JOptionPane.showMessageDialog(null,"No ingresaste datos validos.", "ERROR", JOptionPane.ERROR_MESSAGE);
						TxtBuscador.setText("");
						TxtBuscador.requestFocus();
					}
					
					TxtBuscador.setText("");
					TxtBuscador.requestFocus();
					
					int F = M_Clientes.getRowCount();

			        if(F == 0) {
			        	
			        	Principal.jopre = "datos";
						JOPRe.dialog = new JOPRe();
						JOPRe.dialog.setLocationRelativeTo(null);
						JOPRe.dialog.setVisible(true);
			        	//JOptionPane.showMessageDialog(null,"No se encontr\u00f3 ningun dato", "ERROR", JOptionPane.ERROR_MESSAGE);
			        	//Variable de la conexicon
		                Connection LaConexion = null;

		                Statement Sentencia; //lanzar sql

		                ResultSet Rs;//Atrapar

		                while(TablaClientes.getRowCount() > 0)
		                {
		                	M_Clientes.removeRow(0);
		                }

		                try {

		    				LaConexion = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/loginuser", "root", "LFMG2004");
		                    Sentencia = LaConexion.createStatement();

		                    String sql = "SELECT id, cliente, raza, dueno, descripcion, fechahora, folioreceta, telefono, sexo, edad, precio FROM clientes";
							Rs = Sentencia.executeQuery(sql);
	    					while(Rs.next())
	                        {
	                            String registros[] = {Rs.getString("id"), Rs.getString("cliente"), Rs.getString("raza"), Rs.getString("dueno")
	                            		, Rs.getString("descripcion"), Rs.getString("fechahora"), Rs.getString("folioreceta"), Rs.getString("telefono")
	                            		, Rs.getString("sexo"), Rs.getString("edad"), Rs.getString("precio")};
	                            M_Clientes.addRow(registros);
	                           }


		                    LaConexion.close();

		                }catch(Exception el)
		                {
		                    //JOptionPane.showMessageDialog(null,el.toString(), "Error", JOptionPane.ERROR_MESSAGE);
		                	Principal.jopre = "error";
							JOPRe.dialog = new JOPRe();
							JOPRe.dialog.setLocationRelativeTo(null);
							JOPRe.dialog.setVisible(true);


		                }
			        	
			        }
					
				}catch(Exception el) {
					
					
					
				}
				
			}
		});
		TxtBuscador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try 
				{

				Connection LaConexion;
				ResultSet Rs = null;

					String BusquedaTexto = TxtBuscador.getText();
					int i, filascount = M_Clientes.getRowCount();
					  
					for(i=0; i<filascount; i++) 
					{
						M_Clientes.removeRow(0);
					}
					
					LaConexion = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/loginuser", "root", "LFMG2004");
                    
                    Statement sentenciap;
                    sentenciap = LaConexion.createStatement();

                    	Rs = sentenciap.executeQuery("SELECT id, cliente, raza, dueno, descripcion, fechahora, folioreceta, telefono, sexo, edad, precio FROM clientes "
                    			+ "WHERE folioreceta LIKE '%"+BusquedaTexto+"%'");
                    
                    	while(Rs.next())
                        {
                            String registros[] = {Rs.getString("id"), Rs.getString("cliente"), Rs.getString("raza"), Rs.getString("dueno")
                            		, Rs.getString("descripcion"), Rs.getString("fechahora"), Rs.getString("folioreceta"), Rs.getString("telefono")
                            		, Rs.getString("sexo"), Rs.getString("edad"), Rs.getString("precio")};
                            M_Clientes.addRow(registros);
                           }
				}
				catch(Exception el)
				{
					Principal.jopre = "invalido";
					JOPRe.dialog = new JOPRe();
					JOPRe.dialog.setLocationRelativeTo(null);
					JOPRe.dialog.setVisible(true);
					//JOptionPane.showMessageDialog(null,"No ingresaste datos validos.", "ERROR", JOptionPane.ERROR_MESSAGE);
					TxtBuscador.setText("");
					TxtBuscador.requestFocus();
				}
				
				TxtBuscador.setText("");
				TxtBuscador.requestFocus();
				
				int F = M_Clientes.getRowCount();

		        if(F == 0) {
		        	
		        	Principal.jopre = "datos";
					JOPRe.dialog = new JOPRe();
					JOPRe.dialog.setLocationRelativeTo(null);
					JOPRe.dialog.setVisible(true);
		        	//JOptionPane.showMessageDialog(null,"No se encontr\u00f3 ningun dato", "ERROR", JOptionPane.ERROR_MESSAGE);
		        	//Variable de la conexicon
	                Connection LaConexion = null;

	                Statement Sentencia; //lanzar sql

	                ResultSet Rs;//Atrapar

	                while(TablaClientes.getRowCount() > 0)
	                {
	                	M_Clientes.removeRow(0);
	                }

	                try {

	    				LaConexion = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/loginuser", "root", "LFMG2004");
	                    Sentencia = LaConexion.createStatement();

	                    String sql = "SELECT id, cliente, raza, dueno, descripcion, fechahora, folioreceta, telefono, sexo, edad, precio FROM clientes";
						Rs = Sentencia.executeQuery(sql);
    					while(Rs.next())
                        {
                            String registros[] = {Rs.getString("id"), Rs.getString("cliente"), Rs.getString("raza"), Rs.getString("dueno")
                            		, Rs.getString("descripcion"), Rs.getString("fechahora"), Rs.getString("folioreceta"), Rs.getString("telefono")
                            		, Rs.getString("sexo"), Rs.getString("edad"), Rs.getString("precio")};
                            M_Clientes.addRow(registros);
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
		});
		TxtBuscador.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {

				char c = e.getKeyChar();
				
				if((c<'0' || c>'9')) e.consume();{
					
				}
				if(TxtBuscador.getText().length() >= 25)
			    {
			        e.consume();
			    }
				
			}
			@Override
			public void keyPressed(KeyEvent e) {
				
				if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
				
				//Variable de la conexicon
                Connection LaConexion = null;

                Statement Sentencia; //lanzar sql

                ResultSet Rs;//Atrapar



                while(TablaClientes.getRowCount() > 0)
                {
                	M_Clientes.removeRow(0);
                }

                try {

                	LaConexion = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/loginuser", "root", "LFMG2004");
					
					Sentencia = LaConexion.createStatement();

					String sql = "SELECT id, cliente, raza, dueno, descripcion, fechahora, folioreceta, telefono, sexo, edad, precio FROM clientes";
					Rs = Sentencia.executeQuery(sql);
					while(Rs.next())
                        {
                            String registros[] = {Rs.getString("id"), Rs.getString("cliente"), Rs.getString("raza"), Rs.getString("dueno")
                            		, Rs.getString("descripcion"), Rs.getString("fechahora"), Rs.getString("folioreceta"), Rs.getString("telefono")
                            		, Rs.getString("sexo"), Rs.getString("edad"), Rs.getString("precio")};
                            M_Clientes.addRow(registros);
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
		});
		
		TxtBuscador.setBounds(127, 810, 286, 23);
		contentPanel.add(TxtBuscador);
		TxtBuscador.setColumns(10);
		
		JLabel LblBuscar = new JLabel("");
		LblBuscar.setIcon(new ImageIcon(Clientes.class.getResource("/Img/lupa (2).png")));
		LblBuscar.setBounds(32, 793, 50, 50);
		contentPanel.add(LblBuscar);
		
		JLabel BtnAtras = new JLabel("");
		BtnAtras.setToolTipText("Ir atr\u00E1s");
		BtnAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			
				Clientes.dialog.setVisible(false);
				Menu.dialog.setLocationRelativeTo(null);
				Menu.dialog.setVisible(true);
				
			}
			@Override
			public void mouseExited(MouseEvent e) {
				
				BtnAtras.setIcon(new ImageIcon(Clientes.class.getResource("/Img/resta.png")));
				BtnAtras.setBounds(1192, 793, 50, 50);
				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				
				BtnAtras.setIcon(new ImageIcon(Clientes.class.getResource("/Img/resta - copia.png")));
				BtnAtras.setBounds(1192, 787, 60, 60);
				
			}
		});
		BtnAtras.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		BtnAtras.setIcon(new ImageIcon(Clientes.class.getResource("/Img/resta.png")));
		BtnAtras.setBounds(1192, 793, 50, 50);
		contentPanel.add(BtnAtras);
		
		JLabel BtnEliminar = new JLabel("");
		BtnEliminar.setToolTipText("Eliminar registro");
		BtnEliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				String ubicacion;
                int respuesta, Registros = 0, id = 0, filas = TablaClientes.getSelectedRow();
                try{
                
                    LaConexion = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/loginuser", "root", "LFMG2004");
                    Statement sentencia;
                    sentencia = LaConexion.createStatement();
                    
                    if(filas >= 0){
                    
                    	Principal.input = "eliminar";
						Input.dialog = new Input();
						Input.dialog.setLocationRelativeTo(null);
						Input.dialog.setVisible(true);
						
    					if(Input.yn.equals("yes")) {
                        
                            ubicacion = String.valueOf(M_Clientes.getValueAt(filas,0));
                            Rs = sentencia.executeQuery("SELECT id FROM clientes WHERE id = '"+ubicacion+"'");
                            while(Rs.next()){
                            
                                id = Integer.parseInt(Rs.getString("id"));
                                
                            }
                            
                            PreparedStatement SentenciaP = LaConexion.prepareStatement("DELETE FROM clientes WHERE id = ?");
                            SentenciaP.setInt(1, id);
                            Registros = SentenciaP.executeUpdate();
                            
                            if(Registros >= 1){
                            
                            	Principal.jopre = "exito";
    							JOPRe.dialog = new JOPRe();
    							JOPRe.dialog.setLocationRelativeTo(null);
    							JOPRe.dialog.setVisible(true);
                               // JOptionPane.showMessageDialog(null, "Registro eliminado", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                                int i, filascount = M_Clientes.getRowCount();
                                
                                for(i = 0; i < filascount; i++){
                                
                                    M_Clientes.removeRow(0);
                                    
                                }
                                
                                String sql = "SELECT id, cliente, raza, dueno, descripcion, fechahora, folioreceta, telefono, sexo, edad, precio FROM clientes";
    	    					Rs = sentencia.executeQuery(sql);
                                
                                while(Rs.next()){
                                
                                    String registros[] = {Rs.getString("id"), Rs.getString("cliente"), Rs.getString("raza"), Rs.getString("dueno")
                                    		, Rs.getString("descripcion"), Rs.getString("fechahora"), Rs.getString("folioreceta"), Rs.getString("telefono")
                                    		, Rs.getString("sexo"), Rs.getString("edad"), Rs.getString("precio")};
                                    M_Clientes.addRow(registros);
                                    
                                }
                                
                            }else{
                            
                            	Principal.jopre = "vacio";
    							JOPRe.dialog = new JOPRe();
    							JOPRe.dialog.setLocationRelativeTo(null);
    							JOPRe.dialog.setVisible(true);
                                //JOptionPane.showMessageDialog(null, "No se pudo Eliminar", "Error", JOptionPane.ERROR_MESSAGE);
                                
                            }
                            
                        }
                        
                    }else{
                    
                    	Principal.jopre = "eliminar";
						JOPRe.dialog = new JOPRe();
						JOPRe.dialog.setLocationRelativeTo(null);
						JOPRe.dialog.setVisible(true);
                        //JOptionPane.showMessageDialog(null, "No se ha seleccionado un registro", "ERROR", JOptionPane.ERROR_MESSAGE);
                        
                    }
                    
                }catch(Exception el){
                
                	Principal.jopre = "error";
					JOPRe.dialog = new JOPRe();
					JOPRe.dialog.setLocationRelativeTo(null);
					JOPRe.dialog.setVisible(true);
                    //JOptionPane.showMessageDialog(null,el.toString());
                    
                }
				
			}
			@Override
			public void mouseExited(MouseEvent e) {
				
				BtnEliminar.setIcon(new ImageIcon(Clientes.class.getResource("/Img/basura.png")));
				BtnEliminar.setBounds(710, 793, 50, 50);
				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				
				BtnEliminar.setIcon(new ImageIcon(Clientes.class.getResource("/Img/basura - copia.png")));
				BtnEliminar.setBounds(710, 787, 60, 60);
				
			}
		});
		BtnEliminar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		BtnEliminar.setIcon(new ImageIcon(Clientes.class.getResource("/Img/basura.png")));
		BtnEliminar.setBounds(710, 793, 50, 50);
		contentPanel.add(BtnEliminar);
		
		JLabel BtnAgregar = new JLabel("");
		BtnAgregar.setToolTipText("Agregar registro");
		BtnAgregar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				Menu.x = 0;
				AgModCliente amc = new AgModCliente();
				AgModCliente.BtnModificar.setEnabled(false);
				amc.setLocationRelativeTo(null);
				amc.setVisible(true);
				
			}
			@Override
			public void mouseExited(MouseEvent e) {
				
				BtnAgregar.setIcon(new ImageIcon(Clientes.class.getResource("/Img/boton-agregar.png")));
				BtnAgregar.setBounds(502, 793, 50, 50);
				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				
				BtnAgregar.setIcon(new ImageIcon(Clientes.class.getResource("/Img/boton-agregar - copia.png")));
				BtnAgregar.setBounds(502, 787, 60, 60);
				
			}
		});
		BtnAgregar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		BtnAgregar.setIcon(new ImageIcon(Clientes.class.getResource("/Img/boton-agregar.png")));
		BtnAgregar.setBounds(502, 793, 50, 50);
		contentPanel.add(BtnAgregar);
		
		JLabel BtnModificar = new JLabel("");
		BtnModificar.setToolTipText("Modificar registro");
		BtnModificar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		BtnModificar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				Menu.x = 1;
				int filas = TablaClientes.getSelectedRow();

				if (filas >=0) 
				{
					AgModCliente amc = new AgModCliente();
					AgModCliente.BtnModificar.setEnabled(true);
					amc.setLocationRelativeTo(null);
					amc.setVisible(true);
					
				}
				else
				{
					
					Principal.jopre = "eliminar";
					JOPRe.dialog = new JOPRe();
					JOPRe.dialog.setLocationRelativeTo(null);
					JOPRe.dialog.setVisible(true);
					//JOptionPane.showMessageDialog(null, "No se ha seleccionado un registro", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
				
			}
			@Override
			public void mouseExited(MouseEvent e) {
				
				BtnModificar.setIcon(new ImageIcon(Clientes.class.getResource("/Img/editar.png")));
				BtnModificar.setBounds(610, 791, 50, 50);
				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				
				BtnModificar.setIcon(new ImageIcon(Clientes.class.getResource("/Img/editar - copia.png")));
				BtnModificar.setBounds(610, 786, 60, 60);
				
			}
		});
		BtnModificar.setIcon(new ImageIcon(Clientes.class.getResource("/Img/editar.png")));
		BtnModificar.setBounds(610, 791, 50, 50);
		contentPanel.add(BtnModificar);
		
		if(Principal.tipo.equals("empleado")) {
			
			BtnAgregar.setVisible(false);
			BtnModificar.setVisible(false);
			BtnEliminar.setVisible(false);
			
			
		}else {
			
			BtnAgregar.setVisible(true);
			BtnModificar.setVisible(true);
			BtnEliminar.setVisible(true);
			
		}
		
		JLabel LblLinea = new JLabel("");
		LblLinea.setBorder(new LineBorder(new Color(0, 0, 0)));
		LblLinea.setBounds(127, 832, 286, 2);
		contentPanel.add(LblLinea);

	}
}
