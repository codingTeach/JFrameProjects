import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;

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

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.MouseMotionAdapter;
import java.awt.Toolkit;

public class Usuarios extends JDialog {

	private final JPanel contentPanel = new JPanel();
	public static JTable TablaUsuario;
	public static DefaultTableModel ModeloT = new DefaultTableModel();
	public static Usuarios dialog = new Usuarios();;
	private int mouseX, mouseY;
	
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
	
	ResultSet Rs;//Atrapar
	
	String myString = "xd";
	
	
	
	public Usuarios() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Usuarios.class.getResource("/Img/user.png")));
		setUndecorated(true);
		setResizable(false);
		setTitle("Colitas - Usuarios");
		setBounds(100, 100, 1306, 853);
		contentPanel.setBackground(new Color(0, 153, 153));
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(41, 65, 1206, 718);
		contentPanel.add(scrollPane);
		
		TablaUsuario = new JTable(){
			  @Override
			    public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
			      Component component = super.prepareRenderer(renderer, row, column);
			      int rendererWidth = component.getPreferredSize().width;
			      TableColumn tableColumn = getColumnModel().getColumn(column);
			      tableColumn.setPreferredWidth(Math.max(rendererWidth + getIntercellSpacing().width, tableColumn.getPreferredWidth()));
			      return component;
			    }
			  };
	
			  TablaUsuario.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
        ModeloT.addColumn("Id");
		ModeloT.addColumn("Usuario");
		ModeloT.addColumn("Contrase\u00f1a");
		ModeloT.addColumn("Tipo");
		
		TablaUsuario = new JTable();
		TablaUsuario.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if (e.getClickCount() == 2 && !e.isConsumed()) {
				     e.consume();
				     JOptionPane.showMessageDialog(null, "Id: " + TablaUsuario.getValueAt(TablaUsuario.getSelectedRow(), 0) + "\n" + "\nUsuario: " +
				    		 TablaUsuario.getValueAt(TablaUsuario.getSelectedRow(), 1) + "\n" + "\nClave: " +
				    		 TablaUsuario.getValueAt(TablaUsuario.getSelectedRow(), 2) + "\n" + "\nTipo de usuario: " +
				    		 TablaUsuario.getValueAt(TablaUsuario.getSelectedRow(), 3));
				     //Evento de 2 click sobre un renglon
				}
				
			}
		});
		TablaUsuario.setBackground(Color.WHITE);
		TablaUsuario.setModel(ModeloT);
		TablaUsuario.setDefaultEditor(Object.class, null);
		TablaUsuario.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		TablaUsuario.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		
		TablaUsuario.getTableHeader().setResizingAllowed(false);
		
		TablaUsuario.setRowHeight(25);
		TablaUsuario.setToolTipText("Doble click sobre una fila para mostrar sus datos");
		TablaUsuario.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		
		TablaUsuario.setSelectionBackground(new Color(51, 204, 204));
		TablaUsuario.setShowHorizontalLines(false);
		TablaUsuario.setIntercellSpacing(new Dimension(0, 1));
		TablaUsuario.setOpaque(false);
		TablaUsuario.setFocusable(false);
		TablaUsuario.setShowVerticalLines(false);

		scrollPane.setViewportView(TablaUsuario);
		
		TableRowSorter<DefaultTableModel> ordena = new TableRowSorter<>(ModeloT);
		TablaUsuario.setRowSorter(ordena);
		
		TablaUsuario.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);	
		TablaUsuario.getTableHeader().setResizingAllowed(false);
		TablaUsuario.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
		TablaUsuario.getTableHeader().setOpaque(false);
		TablaUsuario.getTableHeader().setBackground(new Color (0, 102, 102));
		TablaUsuario.getTableHeader().setForeground(new Color (255, 255, 255));
		TablaUsuario.setRowHeight(25);
			
	try {
		
		LaConexion = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/loginuser", "root", "LFMG2004");
		
		Sentencia = LaConexion.createStatement();
		
		String sql = "SELECT id, username, contra, tipo FROM usuarios";
		Rs = Sentencia.executeQuery(sql);
	
		while(Rs.next())
		{
			Object [] fila = new Object[4]; 

			  
			   for (int i=0;i<4;i++) {
				   fila[i] = Rs.getObject(i+1); 
				   
				   }
			     
			   ModeloT.addRow(fila);
	
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
		
		scrollPane.setViewportView(TablaUsuario);
		
		JPanel PanelDown = new JPanel();
		PanelDown.setBorder(new LineBorder(new Color(0, 0, 0)));
		PanelDown.setBackground(new Color (0, 102, 102));
		PanelDown.setBounds(0, 794, 1306, 59);
		contentPanel.add(PanelDown);
		PanelDown.setLayout(null);
		
		JLabel BtnAgregar = new JLabel("");
		BtnAgregar.setToolTipText("Agregar registro");
		BtnAgregar.setBounds(47, 5, 50, 50);
		PanelDown.add(BtnAgregar);
		BtnAgregar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				String usuario = JOptionPane.showInputDialog("Ingresa el nombre del nuevo usuario: ");
                if(usuario != null){

                    String contra = JOptionPane.showInputDialog("Ingresa la contrase\u00f1a del nuevo usuario: ");
                    if(contra != null){

                    	String tipo = JOptionPane.showInputDialog("Ingresa el tipo de usuario: ");
                        if(tipo != null){
                        	
                        	try {
    							
    							LaConexion = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/loginuser", "root", "LFMG2004");
    						
    							//Insertar datos
    							int Registros;
    							
    							PreparedStatement SentenciaP = LaConexion.prepareStatement("INSERT INTO usuarios (username,contra,tipo) VALUES(?,?,?)");
    							
    							SentenciaP.setString(1,usuario);
    							SentenciaP.setString(2,contra);
    							SentenciaP.setString(3,tipo);
    							
    							
    							Registros = SentenciaP.executeUpdate();
    							
    							
    							if(Registros>=1)
    							{
    								
    								Principal.jopre = "exito";
        							JOPRe.dialog = new JOPRe();
        							JOPRe.dialog.setLocationRelativeTo(null);
        							JOPRe.dialog.setVisible(true);
    								//JOptionPane.showMessageDialog(null,"Se Registro Correctamente", "Aviso", JOptionPane.INFORMATION_MESSAGE);
    								
    		                         //Sentencia
    		                           
    		                         //Borra Las filas de las tabla
    			    					int a, filascount = ModeloT.getRowCount();
    			  					  
    			    					for(a=0; a<filascount; a++) 
    			    					{
    			    						ModeloT.removeRow(0);
    			    					}
    		               			
    		               			Sentencia = LaConexion.createStatement();
    		               											
    		               			String sql = ("SELECT id, username, contra, tipo FROM usuarios");
    								Rs = Sentencia.executeQuery(sql);
    		               			
    		               			while(Rs.next())
    		               			{
    		               				Object [] fila = new Object[4]; 

    		               				  
    		               				   for (int i=0;i<4;i++) {
    		               					   fila[i] = Rs.getObject(i+1); 
    		               					   
    		               					   }
    		               				     
    		               				   ModeloT.addRow(fila);
    		               				
    		               				  

    		               			}              
    		                           
    							}
    							else
    								
    								Principal.jopre = "vacio";
    								JOPRe.dialog = new JOPRe();
    								JOPRe.dialog.setLocationRelativeTo(null);
    								JOPRe.dialog.setVisible(true);
    								//JOptionPane.showMessageDialog(null,"No se pudo Registrar", "Error", JOptionPane.ERROR_MESSAGE);
    			
    							
    							
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
                
                }
				
			}
			@Override
			public void mouseExited(MouseEvent e) {
				
				BtnAgregar.setBounds(47, 5, 50, 50);
				BtnAgregar.setIcon(new ImageIcon(Usuarios.class.getResource("/Img/boton-agregar.png")));
				
				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				
				BtnAgregar.setBounds(47, 0, 60, 60);
				BtnAgregar.setIcon(new ImageIcon(Usuarios.class.getResource("/Img/boton-agregar - copia.png")));
				
			}
		});
		BtnAgregar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		BtnAgregar.setIcon(new ImageIcon(Usuarios.class.getResource("/Img/boton-agregar.png")));
		{
			JLabel BtnEliminar = new JLabel("");
			BtnEliminar.setToolTipText("Eliminar registro");
			BtnEliminar.setBounds(124, 5, 50, 50);
			PanelDown.add(BtnEliminar);
			BtnEliminar.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					
					String ubicacion;
                    int respuesta, Registros = 0, id = 0, filas = TablaUsuario.getSelectedRow();
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
                            
                                ubicacion = String.valueOf(ModeloT.getValueAt(filas,0));
                                Rs = sentencia.executeQuery("SELECT id FROM usuarios WHERE id = '"+ubicacion+"'");
                                while(Rs.next()){
                                
                                    id = Integer.parseInt(Rs.getString("id"));
                                    
                                }
                                
                                PreparedStatement SentenciaP = LaConexion.prepareStatement("DELETE FROM usuarios WHERE id = ?");
                                SentenciaP.setInt(1, id);
                                Registros = SentenciaP.executeUpdate();
                                
                                if(Registros >= 1){
                                
                                	
                                	Principal.jopre = "exito";
        							JOPRe.dialog = new JOPRe();
        							JOPRe.dialog.setLocationRelativeTo(null);
        							JOPRe.dialog.setVisible(true);
                                    //JOptionPane.showMessageDialog(null, "Registro eliminado", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                                    int i, filascount = ModeloT.getRowCount();
                                    
                                    for(i = 0; i < filascount; i++){
                                    
                                        ModeloT.removeRow(0);
                                        
                                    }
                                    
                                    Rs = Sentencia.executeQuery("SELECT id, username, contra FROM usuarios");
                                    
                                    while(Rs.next()){
                                    
                                        String registros[] = {Rs.getString("id"), Rs.getString("username"), Rs.getString("contra")};
                                        ModeloT.addRow(registros);
                                        
                                    }
                                    
                                }else{
                                
                                	Principal.jopre = "vacio";
        							JOPRe.dialog = new JOPRe();
        							JOPRe.dialog.setLocationRelativeTo(null);
        							JOPRe.dialog.setVisible(true);
                                   // JOptionPane.showMessageDialog(null, "No se pudo modificar", "Error", JOptionPane.ERROR_MESSAGE);
                                    
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
                       // JOptionPane.showMessageDialog(null,el.toString());
                        
                    }
					
				}
				@Override
				public void mouseEntered(MouseEvent e) {
					
					BtnEliminar.setIcon(new ImageIcon(Usuarios.class.getResource("/Img/basura - copia.png")));
					BtnEliminar.setBounds(124, 0, 60, 60);
					
					
				}
				@Override
				public void mouseExited(MouseEvent e) {
				
					BtnEliminar.setIcon(new ImageIcon(Usuarios.class.getResource("/Img/basura.png")));
					BtnEliminar.setBounds(124, 5, 50, 50);
				
				}
			});
			BtnEliminar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			BtnEliminar.setIcon(new ImageIcon(Usuarios.class.getResource("/Img/basura.png")));
		}
		
		JLabel BtnAtras = new JLabel("");
		BtnAtras.setToolTipText("Ir atr\u00E1s");
		BtnAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				Usuarios.dialog.setVisible(false);
				Menu.dialog.setLocationRelativeTo(null);
				Menu.dialog.setVisible(true);
				
			}
			@Override
			public void mouseExited(MouseEvent e) {
				
				BtnAtras.setIcon(new ImageIcon(Usuarios.class.getResource("/Img/resta.png")));
				BtnAtras.setBounds(1225, 5, 50, 50);
				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				
				BtnAtras.setIcon(new ImageIcon(Usuarios.class.getResource("/Img/resta - copia.png")));
				BtnAtras.setBounds(1225, 0, 60, 60);
				
			}
			
		});
		BtnAtras.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		BtnAtras.setIcon(new ImageIcon(Usuarios.class.getResource("/Img/resta.png")));
		BtnAtras.setBounds(1225, 5, 50, 50);
		PanelDown.add(BtnAtras);
		
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
		PanelUp.setBackground(new Color (0, 102, 102));
		PanelUp.setBounds(0, 0, 1306, 49);
		contentPanel.add(PanelUp);
		PanelUp.setLayout(null);
	}
}
