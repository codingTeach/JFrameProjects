import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Cursor;
import javax.swing.JTextField;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.CaretEvent;
import java.awt.Toolkit;

public class Inyecta extends JDialog {

	private final JPanel contentPanel = new JPanel();
	public static Inyecta dialog = new Inyecta();
	private int mouseX, mouseY;
	public static JTabbedPane tabbedPane;
	
	Connection LaConexion = null;
	
	Statement Sentencia; //lanzar sql
	
	ResultSet Rs;
	public static JTable TablaVacunas;
	public static JTable TablaDesparacitado;
	private JTextField TxtBuscador;
	public static DefaultTableModel M_Vax; //ERROR POR COMPARTIRLO CON DIALOG DE AGREGADO
	public static DefaultTableModel M_Desparacito; //ERROR POR COMPARTIRLO CON DIALOG DE AGREGADO
	
	public static void main(String[] args) {
		try {
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Inyecta() {
		setTitle("Colitas - Inyecciones");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Inyecta.class.getResource("/Img/jeringuilla.png")));
		setUndecorated(true);
		setBounds(100, 100, 1299, 736);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(0, 128, 128));
		contentPanel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel_2_1 = new JPanel();
		panel_2_1.setBackground(new Color(0, 128, 128));
		panel_2_1.setBounds(38, 65, 68, 21);
		contentPanel.add(panel_2_1);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(38, 65, 1200, 557);
		contentPanel.add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("1", null, panel, null);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 1195, 529);
		panel.add(scrollPane);
		
		TablaVacunas = new JTable(){
			  @Override
			    public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
			      Component component = super.prepareRenderer(renderer, row, column);
			      int rendererWidth = component.getPreferredSize().width;
			      TableColumn tableColumn = getColumnModel().getColumn(column);
			      tableColumn.setPreferredWidth(Math.max(rendererWidth + getIntercellSpacing().width, tableColumn.getPreferredWidth()));
			      return component;
			    }
			  };
		TablaVacunas.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		TablaVacunas.setBorder(null);
		TablaVacunas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		TablaVacunas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if (e.getClickCount() == 2 && !e.isConsumed()) {
				     e.consume();
				     JOptionPane.showMessageDialog(null, "Id: " + TablaVacunas.getValueAt(TablaVacunas.getSelectedRow(), 0) + "\n" + "\nNombre: " +
				    		 TablaVacunas.getValueAt(TablaVacunas.getSelectedRow(), 1) + "\n" + "\nSexo: " +
				    		 TablaVacunas.getValueAt(TablaVacunas.getSelectedRow(), 2) + "\n" + "\nEspecie: " +
				    		 TablaVacunas.getValueAt(TablaVacunas.getSelectedRow(), 4) + "\n" + "\nPeso: " +
				    		 TablaVacunas.getValueAt(TablaVacunas.getSelectedRow(), 5) + "\n" + "\nEdad(Meses): " +
				    		 TablaVacunas.getValueAt(TablaVacunas.getSelectedRow(), 5) + "\n" + "\nAplicación: " +
				    		 TablaVacunas.getValueAt(TablaVacunas.getSelectedRow(), 6) + "\n" + "\nBiológico: " +
				    		 TablaVacunas.getValueAt(TablaVacunas.getSelectedRow(), 7) + "\n" + "\nPróxima Cita: " +
				    		 TablaVacunas.getValueAt(TablaVacunas.getSelectedRow(), 8) + "\n" + "\nFolio receta: " +
				    		 TablaVacunas.getValueAt(TablaVacunas.getSelectedRow(), 9) + "\n" + "\nTeléfono: " +
				    		 TablaVacunas.getValueAt(TablaVacunas.getSelectedRow(), 10));
				     //Evento de 2 click sobre un renglon
				}
				
			}
		});
		TablaVacunas.setToolTipText("Doble click sobre una columna para visualizarla");
		TablaVacunas.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		TablaVacunas.setShowVerticalLines(false);
		TablaVacunas.setFocusable(false);
		TablaVacunas.setSelectionBackground(new Color(51, 204, 204));
		TablaVacunas.setOpaque(false);
		TablaVacunas.setIntercellSpacing(new Dimension(0, 1));
		TablaVacunas.setShowHorizontalLines(false);
		TablaVacunas.setDefaultEditor(Object.class, null);
		
		TablaVacunas.getTableHeader().setResizingAllowed(false);
		TablaVacunas.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
		TablaVacunas.getTableHeader().setOpaque(false);
		TablaVacunas.getTableHeader().setBackground(new Color (0, 102, 102));
		TablaVacunas.getTableHeader().setForeground(new Color (255, 255, 255));
		
		TablaVacunas.setRowHeight(25);
		
		scrollPane.setColumnHeaderView(TablaVacunas);
		scrollPane.setViewportView(TablaVacunas);
		
		M_Vax = new DefaultTableModel();
		M_Desparacito = new DefaultTableModel();
		
		M_Vax.addColumn("Id");
		M_Vax.addColumn("Nombre");
		M_Vax.addColumn("Sexo");
		M_Vax.addColumn("Especie");
		M_Vax.addColumn("Peso");
		M_Vax.addColumn("Edad (Meses)");
		M_Vax.addColumn("Aplicación");
		M_Vax.addColumn("Biológico");
		M_Vax.addColumn("Próxima Cita");
		M_Vax.addColumn("Folio receta");
		M_Vax.addColumn("Teléfono");
		
		TablaVacunas.setModel(M_Vax);
		
		TablaVacunas.setModel(M_Vax);
		
		TableRowSorter<DefaultTableModel> ordena = new TableRowSorter<>(M_Vax);
		TablaVacunas.setRowSorter(ordena);
		
		while(TablaVacunas.getRowCount() > 0)
			{
			M_Vax.removeRow(0);
			M_Vax.setRowCount(0);
			M_Vax.setColumnCount(11);
			}
		
		try {
			
			LaConexion = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/loginuser", "root", "LFMG2004");
			
			Sentencia = LaConexion.createStatement();
			
			String sql = "SELECT id, nombre, sexo, raza, peso, edadmeses, aplicacion, biologico, citaproxima, folioreceta, tel FROM inyecciones";
			Rs = Sentencia.executeQuery(sql);
		
			while(Rs.next())
			{
				Object[] fila = new Object[11]; 

				  
				   for (int i=0;i<11;i++) {
					   fila[i] = Rs.getObject(i+1); 
					   
					   }
				     
				   M_Vax.addRow(fila);
		
			}
			
			
			LaConexion.close();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("2", null, panel_1, null);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 0, 1195, 529);
		panel_1.add(scrollPane_1);
		
		TablaDesparacitado = new JTable(){
			  @Override
			    public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
			      Component component = super.prepareRenderer(renderer, row, column);
			      int rendererWidth = component.getPreferredSize().width;
			      TableColumn tableColumn = getColumnModel().getColumn(column);
			      tableColumn.setPreferredWidth(Math.max(rendererWidth + getIntercellSpacing().width, tableColumn.getPreferredWidth()));
			      return component;
			    }
			  };
		TablaDesparacitado.setRowHeight(25);
		TablaDesparacitado.setIntercellSpacing(new Dimension(0, 1));
		TablaDesparacitado.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			
				if (e.getClickCount() == 2 && !e.isConsumed()) {
				     e.consume();
				     JOptionPane.showMessageDialog(null, "Id: " + TablaDesparacitado.getValueAt(TablaDesparacitado.getSelectedRow(), 0) + "\n" + "\nNombre: " +
				    		 TablaDesparacitado.getValueAt(TablaDesparacitado.getSelectedRow(), 1) + "\n" + "\nSexo: " +
				    		 TablaDesparacitado.getValueAt(TablaDesparacitado.getSelectedRow(), 2) + "\n" + "\nEspecie: " +
				    		 TablaDesparacitado.getValueAt(TablaDesparacitado.getSelectedRow(), 4) + "\n" + "\nPeso: " +
				    		 TablaDesparacitado.getValueAt(TablaDesparacitado.getSelectedRow(), 5) + "\n" + "\nEdad(Meses): " +
				    		 TablaDesparacitado.getValueAt(TablaDesparacitado.getSelectedRow(), 5) + "\n" + "\nAplicación: " +
				    		 TablaDesparacitado.getValueAt(TablaDesparacitado.getSelectedRow(), 6) + "\n" + "\nBiológico: " +
				    		 TablaDesparacitado.getValueAt(TablaDesparacitado.getSelectedRow(), 7) + "\n" + "\nPróxima Cita: " +
				    		 TablaDesparacitado.getValueAt(TablaDesparacitado.getSelectedRow(), 8) + "\n" + "\nFolio receta: " +
				    		 TablaDesparacitado.getValueAt(TablaDesparacitado.getSelectedRow(), 9) + "\n" + "\nTeléfono: " +
				    		 TablaDesparacitado.getValueAt(TablaDesparacitado.getSelectedRow(), 10));
				     //Evento de 2 click sobre un renglon
				}
			
			}
		});
		TablaDesparacitado.setBorder(null);
		TablaDesparacitado.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		TablaDesparacitado.setFocusable(false);
		TablaDesparacitado.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		TablaDesparacitado.setOpaque(false);
		TablaDesparacitado.setSelectionBackground(new Color(51, 204, 204));
		TablaDesparacitado.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		TablaDesparacitado.setShowHorizontalLines(false);
		TablaDesparacitado.setShowVerticalLines(false);
		TablaDesparacitado.setToolTipText("Doble click para visualizar una columna");
		TablaDesparacitado.setDefaultEditor(Object.class, null);
		
		TablaDesparacitado.getTableHeader().setResizingAllowed(false);
		TablaDesparacitado.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
		TablaDesparacitado.getTableHeader().setOpaque(false);
		TablaDesparacitado.getTableHeader().setBackground(new Color (0, 102, 102));
		TablaDesparacitado.getTableHeader().setForeground(new Color (255, 255, 255));
		
		scrollPane_1.setColumnHeaderView(TablaDesparacitado);
		scrollPane_1.setViewportView(TablaDesparacitado);
		
		M_Desparacito.addColumn("Id");
		M_Desparacito.addColumn("Nombre");
		M_Desparacito.addColumn("Sexo");
		M_Desparacito.addColumn("Especie");
		M_Desparacito.addColumn("Peso");
		M_Desparacito.addColumn("Edad (Meses)");
		M_Desparacito.addColumn("Aplicación");
		M_Desparacito.addColumn("Biológico");
		M_Desparacito.addColumn("Próxima Cita");
		M_Desparacito.addColumn("Folio receta");
		M_Desparacito.addColumn("Teléfono");
		
		TablaDesparacitado.setModel(M_Desparacito);
		
		TableRowSorter<DefaultTableModel> ordena2 = new TableRowSorter<>(M_Desparacito);
		TablaDesparacitado.setRowSorter(ordena2);
		
		JPanel PanelDown = new JPanel();
		PanelDown.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		PanelDown.setBackground(Color.LIGHT_GRAY);
		PanelDown.setBounds(0, 667, 1299, 69);
		contentPanel.add(PanelDown);
		PanelDown.setLayout(null);
		
		JLabel LblBuscar = new JLabel("");
		LblBuscar.setIcon(new ImageIcon(Inyecta.class.getResource("/Img/lupa (2).png")));
		LblBuscar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		LblBuscar.setBounds(10, 11, 50, 50);
		PanelDown.add(LblBuscar);
		
		JLabel LblAtras = new JLabel("");
		LblAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				Inyecta.dialog.setVisible(false);
				Menu.dialog.setLocationRelativeTo(null);
				Menu.dialog.setVisible(true);
				
			}
			@Override
			public void mouseExited(MouseEvent e) {
				
				LblAtras.setIcon(new ImageIcon(Inyecta.class.getResource("/Img/resta.png")));
				LblAtras.setBounds(1225, 11, 50, 50);
				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				
				LblAtras.setIcon(new ImageIcon(Inyecta.class.getResource("/Img/resta - copia.png")));
				LblAtras.setBounds(1225, 5, 60, 60);
				
			}
		});
		LblAtras.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		LblAtras.setIcon(new ImageIcon(Inyecta.class.getResource("/Img/resta.png")));
		LblAtras.setToolTipText("Ir atr\u00E1s");
		LblAtras.setBounds(1225, 11, 50, 50);
		PanelDown.add(LblAtras);
		
		TxtBuscador = new JTextField();
		TxtBuscador.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				
				if(tabbedPane.getSelectedIndex() == 0) {
					
					try {
						
						try 
						{

						Connection LaConexion;
						ResultSet Rs = null;

							String BusquedaTexto = TxtBuscador.getText();
							int i, filascount = M_Vax.getRowCount();
							  
							for(i=0; i<filascount; i++) 
							{
								M_Vax.removeRow(0);
							}
							
							LaConexion = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/loginuser", "root", "LFMG2004");
                    
                    Statement sentenciap;
                    sentenciap = LaConexion.createStatement();

                    Rs = sentenciap.executeQuery("SELECT id, nombre, sexo, raza, peso, edadmeses, aplicacion, biologico, citaproxima, folioreceta, tel FROM inyecciones"
                    		+ " WHERE nombre LIKE '%"+BusquedaTexto+"%'");
                    
    					while(Rs.next())
                        {
                            String registros[] = {Rs.getString("id"), Rs.getString("nombre"), Rs.getString("sexo"), Rs.getString("raza")
                            		, Rs.getString("peso"), Rs.getString("edadmeses"), Rs.getString("aplicacion"), Rs.getString("biologico")
                            		, Rs.getString("citaproxima"), Rs.getString("folioreceta"), Rs.getString("tel")};
                            M_Vax.addRow(registros);
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
						
						int F = M_Vax.getRowCount();

				        if(F == 0) {
				        	
				        	Principal.jopre = "datos";
							JOPRe.dialog = new JOPRe();
							JOPRe.dialog.setLocationRelativeTo(null);
							JOPRe.dialog.setVisible(true);
				        	//JOptionPane.showMessageDialog(null,"No se encontr\u00f3 ningun dato", "ERROR", JOptionPane.ERROR_MESSAGE);

			                while(TablaVacunas.getRowCount() > 0)
			                {
			                	M_Vax.removeRow(0);
			                }

			                try {

			    				LaConexion = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/loginuser", "root", "LFMG2004");
			                    Sentencia = LaConexion.createStatement();

			                    String sql = "SELECT id, nombre, sexo, raza, peso, edadmeses, aplicacion, biologico, citaproxima, folioreceta, tel FROM inyecciones";
    	    					Rs = Sentencia.executeQuery(sql);
    	    					while(Rs.next())
    	                        {
    	                            String registros[] = {Rs.getString("id"), Rs.getString("nombre"), Rs.getString("sexo"), Rs.getString("raza")
    	                            		, Rs.getString("peso"), Rs.getString("edadmeses"), Rs.getString("aplicacion"), Rs.getString("biologico")
    	                            		, Rs.getString("citaproxima"), Rs.getString("folioreceta"), Rs.getString("tel")};
    	                            M_Vax.addRow(registros);
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
					
				}else if(tabbedPane.getSelectedIndex() == 1) {
					
					try {
						
						try 
						{

						Connection LaConexion;
						ResultSet Rs = null;

							String BusquedaTexto = TxtBuscador.getText();
							int i, filascount = M_Desparacito.getRowCount();
							  
							for(i=0; i<filascount; i++) 
							{
								M_Desparacito.removeRow(0);
							}
							
							LaConexion = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/loginuser", "root", "LFMG2004");
                    
                    Statement sentenciap;
                    sentenciap = LaConexion.createStatement();

                    Rs = sentenciap.executeQuery("SELECT id, nombre, sexo, raza, peso, edadmeses, aplicacion, biologico, citaproxima, folioreceta, tel FROM desparacita"
                    		+ " WHERE nombre LIKE '%"+BusquedaTexto+"%'");
                    
    					while(Rs.next())
                        {
                            String registros[] = {Rs.getString("id"), Rs.getString("nombre"), Rs.getString("sexo"), Rs.getString("raza")
                            		, Rs.getString("peso"), Rs.getString("edadmeses"), Rs.getString("aplicacion"), Rs.getString("biologico")
                            		, Rs.getString("citaproxima"), Rs.getString("folioreceta"), Rs.getString("tel")};
                            M_Desparacito.addRow(registros);
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
						
						int F = M_Desparacito.getRowCount();

				        if(F == 0) {
				        	
				        	Principal.jopre = "datos";
							JOPRe.dialog = new JOPRe();
							JOPRe.dialog.setLocationRelativeTo(null);
							JOPRe.dialog.setVisible(true);
				        	//JOptionPane.showMessageDialog(null,"No se encontr\u00f3 ningun dato", "ERROR", JOptionPane.ERROR_MESSAGE);

			                while(TablaDesparacitado.getRowCount() > 0)
			                {
			                	M_Desparacito.removeRow(0);
			                }

			                try {

			    				LaConexion = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/loginuser", "root", "LFMG2004");
			                    Sentencia = LaConexion.createStatement();

			                    String sql = "SELECT id, nombre, sexo, raza, peso, edadmeses, aplicacion, biologico, citaproxima, folioreceta, tel FROM desparacita";
    	    					Rs = Sentencia.executeQuery(sql);
    	    					while(Rs.next())
    	                        {
    	                            String registros[] = {Rs.getString("id"), Rs.getString("nombre"), Rs.getString("sexo"), Rs.getString("raza")
    	                            		, Rs.getString("peso"), Rs.getString("edadmeses"), Rs.getString("aplicacion"), Rs.getString("biologico")
    	                            		, Rs.getString("citaproxima"), Rs.getString("folioreceta"), Rs.getString("tel")};
    	                            M_Desparacito.addRow(registros);
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
			
		});
		TxtBuscador.setToolTipText("Escribe el producto a buscar");
		TxtBuscador.setOpaque(false);
		TxtBuscador.setForeground(Color.BLACK);
		TxtBuscador.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		TxtBuscador.setColumns(10);
		TxtBuscador.setBorder(null);
		TxtBuscador.setBounds(70, 22, 275, 25);
		PanelDown.add(TxtBuscador);
		
		JLabel LblLinea = new JLabel("");
		LblLinea.setBorder(new LineBorder(new Color(0, 0, 0)));
		LblLinea.setBounds(70, 50, 275, 2);
		PanelDown.add(LblLinea);
		
		JLabel BtnAgregar = new JLabel("");
		BtnAgregar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				
				BtnAgregar.setIcon(new ImageIcon(Clientes.class.getResource("/Img/boton-agregar.png")));
				BtnAgregar.setBounds(410, 11, 50, 50);
				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				
				BtnAgregar.setIcon(new ImageIcon(Clientes.class.getResource("/Img/boton-agregar - copia.png")));
				BtnAgregar.setBounds(410, 7, 60, 60);
				
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				
				Menu.x = 1;
				AMInyeccion ami = new AMInyeccion();
				AMInyeccion.BtnModificar.setEnabled(false);
				ami.setLocationRelativeTo(null);
				ami.setVisible(true);
				
			}
		});
		BtnAgregar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		BtnAgregar.setIcon(new ImageIcon(Inyecta.class.getResource("/Img/boton-agregar.png")));
		BtnAgregar.setToolTipText("Agregar registro");
		BtnAgregar.setBounds(410, 11, 50, 50);
		PanelDown.add(BtnAgregar);
		
		JLabel BtnModificar = new JLabel("");
		BtnModificar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				
				BtnModificar.setIcon(new ImageIcon(Clientes.class.getResource("/Img/editar.png")));
				BtnModificar.setBounds(498, 11, 50, 50);
				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				
					BtnModificar.setIcon(new ImageIcon(Clientes.class.getResource("/Img/editar - copia.png")));
					BtnModificar.setBounds(498, 7, 60, 60);
				
			}
			@Override
			public void mouseClicked(MouseEvent e) {

				if(tabbedPane.getSelectedIndex() < 1) {
					
					Menu.x = 4;
					int filas = TablaVacunas.getSelectedRow();

			if (filas >=0) 
			{
				
				Menu.x = 4;
				AMInyeccion ami = new AMInyeccion();
				AMInyeccion.BtnModificar.setEnabled(true);
				ami.setLocationRelativeTo(null);
				ami.setVisible(true);
				
			}
			else
			{
				Principal.jopre = "eliminar";
				JOPRe.dialog = new JOPRe();
				JOPRe.dialog.setLocationRelativeTo(null);
				JOPRe.dialog.setVisible(true);
				//JOptionPane.showMessageDialog(null, "No se ha seleccionado un registro", "ERROR", JOptionPane.ERROR_MESSAGE);
			}
					
			}else if(tabbedPane.getSelectedIndex() == 1) {
					
					Menu.x = 5;
					int filas = TablaDesparacitado.getSelectedRow();

			if (filas >=0) 
			{
				AMInyeccion ami = new AMInyeccion();
				AMInyeccion.BtnModificar.setEnabled(true);
				ami.setLocationRelativeTo(null);
				ami.setVisible(true);
				
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
				
			}
		});
		BtnModificar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		BtnModificar.setIcon(new ImageIcon(Inyecta.class.getResource("/Img/editar.png")));
		BtnModificar.setToolTipText("Modificar registro");
		BtnModificar.setBounds(498, 11, 50, 50);
		PanelDown.add(BtnModificar);
		
		JLabel BtnEliminar = new JLabel("");
		BtnEliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				
				BtnEliminar.setIcon(new ImageIcon(Inyecta.class.getResource("/Img/basura.png")));
				BtnEliminar.setBounds(596, 11, 50, 50);
				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				
				BtnEliminar.setIcon(new ImageIcon(Inyecta.class.getResource("/Img/basura - copia.png")));
				BtnEliminar.setBounds(596, 11, 50, 50);
				
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(tabbedPane.getSelectedIndex() < 1) {
					
					String Ubicacion;
					int respuesta, Registros = 0, Id = 0, filas = TablaVacunas.getSelectedRow();
            try {
            	
				LaConexion = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/loginuser", "root", "LFMG2004");
                
                Sentencia = LaConexion.createStatement();

				if (filas >=0) 
				{
					Principal.input = "eliminar";
					Input.dialog = new Input();
					Input.dialog.setLocationRelativeTo(null);
					Input.dialog.setVisible(true);
					
					if(Input.yn.equals("yes")) {
						Ubicacion = String.valueOf(TablaVacunas.getValueAt(filas, 0));	
    					Rs = Sentencia.executeQuery("SELECT id FROM inyecciones WHERE id = '"+Ubicacion+"' ");
    					while(Rs.next())
    	                {
    	                	Id = Integer.parseInt(Rs.getString("id"));
    	                }
    					
    					 PreparedStatement SentenciaP = LaConexion.prepareStatement("DELETE FROM inyecciones WHERE id = ?");
    					 SentenciaP.setInt(1, Id);
    					 
    					 Registros = SentenciaP.executeUpdate();
    					 if(Registros >= 1)
    	    				{
    	    					
    	    					//Borra Las filas de las tabla
    	    					int i, filascount = M_Vax.getRowCount();
    	  					  
    	    					for(i=0; i<filascount; i++) 
    	    					{
    	    						M_Vax.removeRow(0);
    	    					}
    	    					//Se consulta para actualizar los datos
    	    					//Sentencia
    	    					
    	    					String sql = "SELECT id, nombre, sexo, raza, peso, edadmeses, aplicacion, biologico, citaproxima, folioreceta, tel FROM inyecciones";
    	    					Rs = Sentencia.executeQuery(sql);
    	    					while(Rs.next())
    	                        {
    	                            String registros[] = {Rs.getString("id"), Rs.getString("nombre"), Rs.getString("sexo"), Rs.getString("raza")
    	                            		, Rs.getString("peso"), Rs.getString("edadmeses"), Rs.getString("aplicacion"), Rs.getString("biologico")
    	                            		, Rs.getString("citaproxima"), Rs.getString("folioreceta"), Rs.getString("tel")};
    	                            M_Vax.addRow(registros);
    	                           }
    	    					
    	    					Principal.jopre = "exito";
    							JOPRe.dialog = new JOPRe();
    							JOPRe.dialog.setLocationRelativeTo(null);
    							JOPRe.dialog.setVisible(true);
    	    					//JOptionPane.showMessageDialog(null, "Registro Eliminado", "Aviso", JOptionPane.INFORMATION_MESSAGE);

    	    				}
    	    				else
    	    				{
    	    					Principal.jopre = "vacio";
    							JOPRe.dialog = new JOPRe();
    							JOPRe.dialog.setLocationRelativeTo(null);
    							JOPRe.dialog.setVisible(true);
    	    					//JOptionPane.showMessageDialog(null, "No se pudo modificar", "Error", JOptionPane.ERROR_MESSAGE);
    	    				}
					}
					
				}
				else
				{
					Principal.jopre = "eliminar";
					JOPRe.dialog = new JOPRe();
					JOPRe.dialog.setLocationRelativeTo(null);
					JOPRe.dialog.setVisible(true);
					//JOptionPane.showMessageDialog(null, "No se ha seleccionado un registro", "ERROR", JOptionPane.ERROR_MESSAGE);
				}

					}catch(Exception el)
					{
						Principal.jopre = "error";
						JOPRe.dialog = new JOPRe();
						JOPRe.dialog.setLocationRelativeTo(null);
						JOPRe.dialog.setVisible(true);
						//JOptionPane.showMessageDialog(null,el.toString(), "Error", JOptionPane.ERROR_MESSAGE);
					}
					
				}else if(tabbedPane.getSelectedIndex() == 1) {
					
					String Ubicacion;
					int respuesta, Registros = 0, Id = 0, filas = TablaDesparacitado.getSelectedRow();
	                try {
	                	
	    				LaConexion = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/loginuser", "root", "LFMG2004");
	                    
	                    Sentencia = LaConexion.createStatement();

	    				if (filas >=0) 
	    				{
	    					Principal.input = "eliminar";
							Input.dialog = new Input();
							Input.dialog.setLocationRelativeTo(null);
							Input.dialog.setVisible(true);
							
	    					if(Input.yn.equals("yes")) {
	    						Ubicacion = String.valueOf(TablaDesparacitado.getValueAt(filas, 0));	
	        					Rs = Sentencia.executeQuery("SELECT id FROM desparacita WHERE id = '"+Ubicacion+"' ");
	        					while(Rs.next())
	        	                {
	        	                	Id = Integer.parseInt(Rs.getString("id"));
	        	                }
	        					
	        					 PreparedStatement SentenciaP = LaConexion.prepareStatement("DELETE FROM desparacita WHERE id = ?");
	        					 SentenciaP.setInt(1, Id);
	        					 
	        					 Registros = SentenciaP.executeUpdate();
	        					 if(Registros >= 1)
	        	    				{
	        	    					
	        	    					//Borra Las filas de las tabla
	        	    					int i, filascount = M_Desparacito.getRowCount();
	        	  					  
	        	    					for(i=0; i<filascount; i++) 
	        	    					{
	        	    						M_Desparacito.removeRow(0);
	        	    					}
	        	    					//Se consulta para actualizar los datos
	        	    					//Sentencia
	        	    					
	        	    					String sql = "SELECT id, nombre, sexo, raza, peso, edadmeses, aplicacion, biologico, citaproxima, folioreceta, tel FROM desparacita";
	        	    					Rs = Sentencia.executeQuery(sql);
	        	    					while(Rs.next())
	        	                        {
	        	                            String registros[] = {Rs.getString("id"), Rs.getString("nombre"), Rs.getString("sexo"), Rs.getString("raza")
	        	                            		, Rs.getString("peso"), Rs.getString("edadmeses"), Rs.getString("aplicacion"), Rs.getString("biologico")
	        	                            		, Rs.getString("citaproxima"), Rs.getString("folioreceta"), Rs.getString("tel")};
	        	                            M_Desparacito.addRow(registros);
	        	                           }
	        	    					
	        	    					Principal.jopre = "exito";
	        							JOPRe.dialog = new JOPRe();
	        							JOPRe.dialog.setLocationRelativeTo(null);
	        							JOPRe.dialog.setVisible(true);
	        	    					//JOptionPane.showMessageDialog(null, "Registro Eliminado", "Aviso", JOptionPane.INFORMATION_MESSAGE);

	        	    				}
	        	    				else
	        	    				{
	        	    					Principal.jopre = "vacio";
	        							JOPRe.dialog = new JOPRe();
	        							JOPRe.dialog.setLocationRelativeTo(null);
	        							JOPRe.dialog.setVisible(true);
	        	    					//JOptionPane.showMessageDialog(null, "No se pudo modificar", "Error", JOptionPane.ERROR_MESSAGE);
	        	    				}
	    					}
	    					
	    				}
	    				else
	    				{
	    					Principal.jopre = "eliminar";
							JOPRe.dialog = new JOPRe();
							JOPRe.dialog.setLocationRelativeTo(null);
							JOPRe.dialog.setVisible(true);
	    					//JOptionPane.showMessageDialog(null, "No se ha seleccionado un registro", "ERROR", JOptionPane.ERROR_MESSAGE);
	    				}
		
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
		BtnEliminar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		BtnEliminar.setIcon(new ImageIcon(Inyecta.class.getResource("/Img/basura.png")));
		BtnEliminar.setToolTipText("Eliminar registro");
		BtnEliminar.setBounds(596, 11, 50, 50);
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
		
		JPanel PanelUp = new JPanel();
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
		PanelUp.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		PanelUp.setBackground(new Color(32, 178, 170));
		PanelUp.setBounds(0, 0, 1299, 62);
		contentPanel.add(PanelUp);
		PanelUp.setLayout(null);
		
		JLabel BtnVacuna = new JLabel("");
		BtnVacuna.setToolTipText("Click para ir a la vista de vacunas");
		BtnVacuna.setBounds(5, 7, 229, 50);
		PanelUp.add(BtnVacuna);
		BtnVacuna.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				tabbedPane.setSelectedIndex(0);
				
			}
			@Override
			public void mouseExited(MouseEvent e) {

				BtnVacuna.setIcon(new ImageIcon(Inyecta.class.getResource("/Img/vacuna.png")));
				BtnVacuna.setBounds(5, 7, 229, 50);
				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				
				BtnVacuna.setIcon(new ImageIcon(Inyecta.class.getResource("/Img/vacuna - copia.png")));
				BtnVacuna.setBounds(5, 3, 229, 60);
				
			}
		});
		BtnVacuna.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		BtnVacuna.setIcon(new ImageIcon(Inyecta.class.getResource("/Img/boton-web (1) - copia - copia.png")));
		
		JLabel BtnDesparasita = new JLabel("");
		BtnDesparasita.setToolTipText("Click para ir a la vista de desparasitaciones");
		BtnDesparasita.setBounds(250, 7, 229, 50);
		PanelUp.add(BtnDesparasita);
		BtnDesparasita.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {

				BtnDesparasita.setIcon(new ImageIcon(Inyecta.class.getResource("/Img/vacuna (1).png")));
				BtnDesparasita.setBounds(250, 7, 229, 50);
				
			}
			@Override
			public void mouseEntered(MouseEvent e) {

				BtnDesparasita.setIcon(new ImageIcon(Inyecta.class.getResource("/Img/vacuna (1) - copia.png")));
				BtnDesparasita.setBounds(250, 3, 229, 60);
				
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				
				tabbedPane.setSelectedIndex(1);
				
			}
		});
		BtnDesparasita.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		BtnDesparasita.setIcon(new ImageIcon(Inyecta.class.getResource("/Img/vacuna (1).png")));
		
		while(TablaDesparacitado.getRowCount() > 0)
        {
			M_Desparacito.removeRow(0);
			M_Desparacito.setRowCount(0);
			M_Desparacito.setColumnCount(11);
        }
		
		try {
			
			LaConexion = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/loginuser", "root", "LFMG2004");
			
			Sentencia = LaConexion.createStatement();
			
			String sql = "SELECT id, nombre, sexo, raza, peso, edadmeses, aplicacion, biologico, citaproxima, folioreceta, tel FROM desparacita";
			Rs = Sentencia.executeQuery(sql);
		
			while(Rs.next())
			{
				Object [] fila = new Object[11]; 

				  
				   for (int i=0;i<11;i++) {
					   fila[i] = Rs.getObject(i+1); 
					   
					   }
				     
				   M_Desparacito.addRow(fila);
		
			}
			
			
			LaConexion.close();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
}
