import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.SystemColor;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.CaretEvent;
import java.awt.Toolkit;

public class Estetica extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField TxtBuscador;
	private int mouseX, mouseY;
	public static Estetica dialog = new Estetica();
	public static JTable TablaEstetica;
	public static DefaultTableModel M_Estetica;
	
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
	
	public Estetica() {
		setTitle("Colitas - Estetica");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Estetica.class.getResource("/Img/seguro-de-mascotas - copia.png")));
		setUndecorated(true);
		setBounds(100, 100, 1202, 851);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(32, 178, 170));
		contentPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JPanel PanelUp = new JPanel();
		PanelUp.setBorder(new LineBorder(new Color(0, 0, 0)));
		PanelUp.setBackground(new Color(51, 102, 102));
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
		PanelUp.setBounds(0, 0, 1202, 36);
		contentPanel.add(PanelUp);
		
		JPanel PanelDown = new JPanel();
		PanelDown.setBorder(new LineBorder(new Color(0, 0, 0)));
		PanelDown.setBackground(new Color(0, 139, 139));
		PanelDown.setBounds(0, 789, 1202, 62);
		contentPanel.add(PanelDown);
		PanelDown.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(41, 45, 1104, 733);
		contentPanel.add(scrollPane);
		
		TablaEstetica = new JTable(){
			  @Override
			    public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
			      Component component = super.prepareRenderer(renderer, row, column);
			      int rendererWidth = component.getPreferredSize().width;
			      TableColumn tableColumn = getColumnModel().getColumn(column);
			      tableColumn.setPreferredWidth(Math.max(rendererWidth + getIntercellSpacing().width, tableColumn.getPreferredWidth()));
			      return component;
			    }
			  };
			  TablaEstetica.setRowHeight(25);
			  TablaEstetica.setFont(new Font("Tahoma", Font.PLAIN, 18));
			  TablaEstetica.setToolTipText("Doble click sobre una fila para mostrar sus datos");
			  TablaEstetica.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				if (e.getClickCount() == 2 && !e.isConsumed()) {
				     e.consume();
				     JOptionPane.showMessageDialog(null, "Id: " + TablaEstetica.getValueAt(TablaEstetica.getSelectedRow(), 0) + "\n" + "\nCliente: " +
				    		 TablaEstetica.getValueAt(TablaEstetica.getSelectedRow(), 1) + "\n" + "\nPrecio: " +
				    		 TablaEstetica.getValueAt(TablaEstetica.getSelectedRow(), 2) + "\n" + "\nObservaciones: " +
				    		 TablaEstetica.getValueAt(TablaEstetica.getSelectedRow(), 3));
				     //Detectar doble click
				}

			}
		});
			  
			  TablaEstetica.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			  
			  TablaEstetica.setBorder(new LineBorder(new Color(0, 0, 0)));
			  TablaEstetica.setSelectionBackground(new Color(51, 204, 204));
			  TablaEstetica.setShowHorizontalLines(false);
			  TablaEstetica.setIntercellSpacing(new Dimension(0, 1));
			  TablaEstetica.setOpaque(false);
			  TablaEstetica.setFocusable(false);
			  TablaEstetica.setShowVerticalLines(false);
			  
			  TablaEstetica.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			  TablaEstetica.setDefaultEditor(Object.class, null);
				
			  TablaEstetica.setBorder(new LineBorder(new Color(0, 0, 0)));
		
			  TablaEstetica.setDefaultEditor(Object.class, null);
		
		scrollPane.setViewportView(TablaEstetica);
		TablaEstetica.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		TablaEstetica.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);	
		TablaEstetica.getTableHeader().setResizingAllowed(false);
		TablaEstetica.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
		TablaEstetica.getTableHeader().setOpaque(false);
		TablaEstetica.getTableHeader().setBackground(new Color (0, 102, 102));
		TablaEstetica.getTableHeader().setForeground(new Color (255, 255, 255));
		TablaEstetica.setRowHeight(25);
		
		M_Estetica = new DefaultTableModel();
		
		M_Estetica.addColumn("Id");
		M_Estetica.addColumn("Cliente");
		M_Estetica.addColumn("Precio");
		M_Estetica.addColumn("Observaciones");
		
		TablaEstetica.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		
		TablaEstetica.setModel(M_Estetica);
		
		TablaEstetica.getTableHeader().setResizingAllowed(false);
		
		TableRowSorter<DefaultTableModel> ordena = new TableRowSorter<>(M_Estetica);
		TablaEstetica.setRowSorter(ordena);
		
		//Variable de la conexicon
				Connection LaConexion = null;
				
				Statement Sentencia; //lanzar sql
				
				ResultSet Rs;//Atrapar
				
				int filas; //modificar y actualizar tabla
				
				while(TablaEstetica.getRowCount() > 0)
              {
					M_Estetica.removeRow(0);
					M_Estetica.setRowCount(0);
					M_Estetica.setColumnCount(4);
              }
				
				try {
					
					LaConexion = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/loginuser", "root", "LFMG2004");
					
					Sentencia = LaConexion.createStatement();
					
					String sql = "SELECT id, cliente, precio, observaciones FROM estetica";
					Rs = Sentencia.executeQuery(sql);
				
					while(Rs.next())
					{
						Object [] fila = new Object[4]; 
						  
						   for (int i=0;i<4;i++) {
							   fila[i] = Rs.getObject(i+1); 
							   
							   }
						     
						   M_Estetica.addRow(fila);
				
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
		TxtBuscador.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
			
				try {
					
					try 
					{

					Connection LaConexion;
					ResultSet Rs = null;

						String BusquedaTexto = TxtBuscador.getText();
						int i, filascount = M_Estetica.getRowCount();
						  
						for(i=0; i<filascount; i++) 
						{
							M_Estetica.removeRow(0);
						}
						
						LaConexion = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/loginuser", "root", "LFMG2004");
                
                Statement sentenciap;
                sentenciap = LaConexion.createStatement();

                	Rs = sentenciap.executeQuery("SELECT id, cliente, precio, observaciones FROM estetica WHERE cliente LIKE '%"+BusquedaTexto+"%'");
                
                	while(Rs.next()){
                        
                        String registros[] = {Rs.getString("id"), Rs.getString("cliente"), Rs.getString("precio"), Rs.getString("observaciones")};
                        M_Estetica.addRow(registros);
                        
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
					
					int F = M_Estetica.getRowCount();

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

		                while(TablaEstetica.getRowCount() > 0)
		                {
		                	M_Estetica.removeRow(0);
		                }

		                try {

		    				LaConexion = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/loginuser", "root", "LFMG2004");
		                    Sentencia = LaConexion.createStatement();

		                    String sql = "SELECT id, cliente, precio, observaciones FROM estetica";
					Rs = Sentencia.executeQuery(sql);
					while(Rs.next()){
                        
                        String registros[] = {Rs.getString("id"), Rs.getString("cliente"), Rs.getString("precio"), Rs.getString("observaciones")};
                        M_Estetica.addRow(registros);
                        
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
					
				}catch(Exception el) {

					//Nada para que no se bugée
					
				}
			
			}
		});
		TxtBuscador.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				
				Character c = e.getKeyChar();
				
				if(!Character.isLetter(c) && c != KeyEvent.VK_SPACE) {
					
					e.consume();	
					
				}
				
				 if(TxtBuscador.getText().length() >= 25)
				    {
				        e.consume();
				        
				    }
				
			}
			@Override
			public void keyPressed(KeyEvent e) {
				
				if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {

	                while(TablaEstetica.getRowCount() > 0)
	                {
	                    M_Estetica.removeRow(0);
	                }

	                try {
	                	
	                	Connection LaConexion = null;
	                	
	                	Statement Sentencia; //lanzar sql
	                	
	                	ResultSet Rs;
	                	
	                	LaConexion = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/loginuser", "root", "LFMG2004");
								
								Sentencia = LaConexion.createStatement();

								String sql = "SELECT id, cliente, precio, observaciones FROM estetica";
								Rs = Sentencia.executeQuery(sql);
								while(Rs.next()){
	                                
                                    String registros[] = {Rs.getString("id"), Rs.getString("cliente"), Rs.getString("precio"), Rs.getString("observaciones")};
                                    M_Estetica.addRow(registros);
                                    
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

		TxtBuscador.setToolTipText("Escribe el producto a buscar");
		TxtBuscador.setForeground(SystemColor.text);
		TxtBuscador.setOpaque(false);
		TxtBuscador.setBorder(null);
		TxtBuscador.setBounds(86, 23, 230, 22);
		PanelDown.add(TxtBuscador);
		TxtBuscador.setColumns(10);
		
		JLabel BtnAtras = new JLabel("");
		BtnAtras.setToolTipText("Ir atr\u00E1s");
		BtnAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				Estetica.dialog.setVisible(false);
				Menu.dialog.setVisible(true);
				Menu.dialog.setLocationRelativeTo(null);
				
			}
			@Override
			public void mouseExited(MouseEvent e) {
				
				BtnAtras.setIcon(new ImageIcon(Estetica.class.getResource("/Img/resta.png")));
				BtnAtras.setBounds(1129, 3, 50, 50);
				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				
				BtnAtras.setIcon(new ImageIcon(Estetica.class.getResource("/Img/resta - copia.png")));
				BtnAtras.setBounds(1129, -1, 60, 60);
				
			}
		});
		BtnAtras.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		BtnAtras.setIcon(new ImageIcon(Estetica.class.getResource("/Img/resta.png")));
		BtnAtras.setBounds(1129, 3, 50, 50);
		PanelDown.add(BtnAtras);
		
		JLabel BtnAgregar = new JLabel("");
		BtnAgregar.setToolTipText("Agregar registro");
		BtnAgregar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				Menu.x = 0;
				ModAgEstetica mae = new ModAgEstetica();
				ModAgEstetica.BtnModificar.setEnabled(false);
				mae.setLocationRelativeTo(null);
				mae.setVisible(true);
				
			}
			@Override
			public void mouseExited(MouseEvent e) {
				
				BtnAgregar.setIcon(new ImageIcon(Estetica.class.getResource("/Img/boton-agregar.png")));
				BtnAgregar.setBounds(383, 5, 50, 50);
				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				
				BtnAgregar.setIcon(new ImageIcon(Estetica.class.getResource("/Img/boton-agregar - copia.png")));
				BtnAgregar.setBounds(383, 0, 60, 60);
				
			}
		});
		BtnAgregar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		BtnAgregar.setIcon(new ImageIcon(Estetica.class.getResource("/Img/boton-agregar.png")));
		BtnAgregar.setBounds(383, 5, 50, 50);
		PanelDown.add(BtnAgregar);
		
		JLabel BtnModificar = new JLabel("");
		BtnModificar.setToolTipText("Modificar registro");
		BtnModificar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		BtnModificar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				Menu.x = 1;
				int filas = TablaEstetica.getSelectedRow();

				if (filas >=0) 
				{
					ModAgEstetica mae = new ModAgEstetica();
					ModAgEstetica.BtnModificar.setEnabled(true);
					mae.setLocationRelativeTo(null);
					mae.setVisible(true);
					
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
				
				BtnModificar.setIcon(new ImageIcon(Estetica.class.getResource("/Img/editar.png")));
				BtnModificar.setBounds(454, 5, 50, 50);
				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
			
				BtnModificar.setIcon(new ImageIcon(Estetica.class.getResource("/Img/editar - copia.png")));
				BtnModificar.setBounds(454, 0, 60, 60);
				
			}
		});
		BtnModificar.setIcon(new ImageIcon(Estetica.class.getResource("/Img/editar.png")));
		BtnModificar.setBounds(454, 5, 50, 50);
		PanelDown.add(BtnModificar);
		
		JLabel BtnEliminar = new JLabel("");
		BtnEliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				
				BtnEliminar.setIcon(new ImageIcon(Estetica.class.getResource("/Img/basura.png")));
				BtnEliminar.setBounds(532, 3, 50, 50);
				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				
				BtnEliminar.setIcon(new ImageIcon(Estetica.class.getResource("/Img/basura - copia.png")));
				BtnEliminar.setBounds(532, 0, 60, 60);
				
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				
				String ubicacion;
				Connection LaConexion = null;
					
				ResultSet Rs;
                int respuesta, Registros = 0, id = 0;
                int filas = TablaEstetica.getSelectedRow();
                
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
                        
                            ubicacion = String.valueOf(M_Estetica.getValueAt(filas,0));
                            Rs = sentencia.executeQuery("SELECT id FROM estetica WHERE id = '"+ubicacion+"'");
                            while(Rs.next()){
                            
                                id = Integer.parseInt(Rs.getString("id"));
                                
                            }
                            
                            PreparedStatement SentenciaP = LaConexion.prepareStatement("DELETE FROM estetica WHERE id = ?");
                            SentenciaP.setInt(1, id);
                            Registros = SentenciaP.executeUpdate();
                            
                            if(Registros >= 1){
                            
                            	Principal.jopre = "exito";
    							JOPRe.dialog = new JOPRe();
    							JOPRe.dialog.setLocationRelativeTo(null);
    							JOPRe.dialog.setVisible(true);
                                //JOptionPane.showMessageDialog(null, "Registro eliminado", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                                int i, filascount = M_Estetica.getRowCount();
                                
                                for(i = 0; i < filascount; i++){
                                
                                	M_Estetica.removeRow(0);
                                    
                                }
                                
                                String sql = "SELECT id, cliente, precio, observaciones FROM estetica";
    	    					Rs = sentencia.executeQuery(sql);
                                
                                while(Rs.next()){
                                
                                    String registros[] = {Rs.getString("id"), Rs.getString("cliente"), Rs.getString("precio"), Rs.getString("observaciones")};
                                    M_Estetica.addRow(registros);
                                    
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
                       // JOptionPane.showMessageDialog(null, "No se ha seleccionado un registro", "ERROR", JOptionPane.ERROR_MESSAGE);
                        
                    }
                    
                }catch(Exception el){
                
                	Principal.jopre = "error";
					JOPRe.dialog = new JOPRe();
					JOPRe.dialog.setLocationRelativeTo(null);
					JOPRe.dialog.setVisible(true);
                    //JOptionPane.showMessageDialog(null,el.toString());
                    
                }
				
			}
		});
		BtnEliminar.setToolTipText("Eliminar registro");
		BtnEliminar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		BtnEliminar.setIcon(new ImageIcon(Estetica.class.getResource("/Img/basura.png")));
		BtnEliminar.setBounds(532, 3, 50, 50);
		PanelDown.add(BtnEliminar);
		
		if(Principal.tipo.equals("empleado")) {
			
			BtnAgregar.setVisible(false);
			BtnModificar.setVisible(false);
			BtnEliminar.setVisible(false);
			
			
		}else {
			
			BtnAgregar.setVisible(true);
			BtnModificar.setVisible(true);
			BtnEliminar.setVisible(true);
			
		}
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblNewLabel_4.setBounds(86, 45, 230, 2);
		PanelDown.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setIcon(new ImageIcon(Estetica.class.getResource("/Img/lupa (2).png")));
		lblNewLabel_5.setBounds(10, 3, 50, 50);
		PanelDown.add(lblNewLabel_5);

	}
}
