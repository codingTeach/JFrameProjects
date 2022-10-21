import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Iterator;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;
import com.mysql.cj.*;
import java.sql.*;

import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.CaretListener;
import javax.swing.event.CaretEvent;
import javax.swing.ImageIcon;
import java.awt.Cursor;
import javax.swing.JSeparator;
import java.awt.Dimension;
import java.awt.Insets;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import java.awt.Toolkit;

public class Productos extends JDialog {

	private final JPanel contentPanel = new JPanel();
	public static JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
	public static JTable TablaAccesorios;
	public static JTable TablaMedicamentos;
	public static JTextField TxtBuscador;
	public static JTable TablaAlimentos;
	public static DefaultTableModel M_Accesorios = new DefaultTableModel();
	public static DefaultTableModel M_Medicamentos;
	public static DefaultTableModel M_Alimentos = new DefaultTableModel();
	public static Productos dialog = new Productos();
	private int mouseX, mouseY;
	public static int az;
	
	Connection LaConexion = null;
	
	Statement Sentencia; //lanzar sql
	
	ResultSet Rs;
	
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		try {
			Menu.dialog.setVisible(false);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			dialog.setLocationRelativeTo(null);
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			Connection LaConexion = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/loginuser", "root", "LFMG2004");
			Principal.jopre = "eliminar";
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	int xMouse, yMouse;
	private JLabel LblArrastrar_1;
	
	public Productos() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Productos.class.getResource("/Img/carro-de-la-carretilla.png")));
		setUndecorated(true);
		setResizable(false);
		setTitle("Colitas - Productos");
		setBounds(100, 100, 1290, 850);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(7, 200, 255));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JOPRe.dialog = new JOPRe();
		Principal.jopre = "eliminar";
		
		JOPRe.LblTexto.setVisible(true);
		
		JLabel LblArrastrar = new JLabel("");
		LblArrastrar.setIcon(new ImageIcon(Productos.class.getResource("/Img/1346.png")));
		LblArrastrar.setBorder(new LineBorder(new Color(0, 0, 0)));
		LblArrastrar.setBackground(new Color(47, 79, 79));
		LblArrastrar.setOpaque(true);
		LblArrastrar.setBounds(23, 11, 1250, 32);
		LblArrastrar.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
			
				dialog.setLocation(dialog.getX() + e.getX() - mouseX, dialog.getY() + e.getY() - mouseY);
			
			}
		});
		LblArrastrar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				
				mouseX = e.getX();
				mouseY = e.getY();
				
			}
		});
		contentPanel.setLayout(null);
		
		JLabel LblAccesorios = new JLabel("");
		LblAccesorios.setToolTipText("Click para ir a la vista de accesorios");
		LblAccesorios.setHorizontalAlignment(SwingConstants.CENTER);
		LblAccesorios.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				Productos.tabbedPane.setSelectedIndex(0);
				
			}
			@Override
			public void mouseExited(MouseEvent e) {
				
				LblAccesorios.setIcon(new ImageIcon(Productos.class.getResource("/Img/boton-web.png")));
				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				
				LblAccesorios.setBounds(20, 35, 285, 70);
				LblAccesorios.setIcon(new ImageIcon(Productos.class.getResource("/Img/boton-web - copia.png")));
				
			}
		});
		
		JLabel LblAlimentos = new JLabel("");
		LblAlimentos.setToolTipText("Click para ir a la vista de alimentos");
		LblAlimentos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				Productos.tabbedPane.setSelectedIndex(2);
				
			}
			@Override
			public void mouseExited(MouseEvent e) {
				
				LblAlimentos.setBounds(985, 40, 275, 60);
				LblAlimentos.setIcon(new ImageIcon(Productos.class.getResource("/Img/boton-web (1).png")));
				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				
				LblAlimentos.setBounds(985, 35, 285, 70);
				LblAlimentos.setIcon(new ImageIcon(Productos.class.getResource("/Img/boton-web (1) - copia.png")));
				
			}
		});
		LblAlimentos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		LblAlimentos.setIcon(new ImageIcon(Productos.class.getResource("/Img/boton-web (1).png")));
		LblAlimentos.setHorizontalAlignment(SwingConstants.CENTER);
		LblAlimentos.setBorder(null);
		LblAlimentos.setBounds(985, 40, 275, 60);
		contentPanel.add(LblAlimentos);
		
		JLabel LblMedicamentos = new JLabel("");
		LblMedicamentos.setToolTipText("Click para ir a la vista de medicamentos");
		LblMedicamentos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				Productos.tabbedPane.setSelectedIndex(1);
				
			}
			@Override
			public void mouseExited(MouseEvent e) {
				
				LblMedicamentos.setBounds(500, 40, 275, 60);
				LblMedicamentos.setIcon(new ImageIcon(Productos.class.getResource("/Img/boton-web (2).png")));
				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				
				LblMedicamentos.setBounds(500, 36, 285, 70);
				LblMedicamentos.setIcon(new ImageIcon(Productos.class.getResource("/Img/boton-web (2) - copia.png")));
				
			}
		});
		LblMedicamentos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		LblMedicamentos.setIcon(new ImageIcon(Productos.class.getResource("/Img/boton-web (2).png")));
		LblMedicamentos.setHorizontalAlignment(SwingConstants.CENTER);
		LblMedicamentos.setBorder(null);
		LblMedicamentos.setBounds(500, 40, 275, 60);
		contentPanel.add(LblMedicamentos);
		LblAccesorios.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		LblAccesorios.setBorder(null);
		LblAccesorios.setIcon(new ImageIcon(Productos.class.getResource("/Img/boton-web.png")));
		LblAccesorios.setBounds(25, 40, 275, 60);
		contentPanel.add(LblAccesorios);
		
		LblArrastrar_1 = new JLabel("");
		LblArrastrar_1.setBackground(new Color(7, 200, 254));
		LblArrastrar_1.setOpaque(true);
		LblArrastrar_1.setBounds(23, 33, 1250, 75);
		contentPanel.add(LblArrastrar_1);
		contentPanel.add(LblArrastrar);
		
		JScrollPane ScrollAccesorios = new JScrollPane();
		ScrollAccesorios.setBackground(Color.GRAY);
		ScrollAccesorios.setBorder(new LineBorder(new Color(130, 135, 144)));
		
				ScrollAccesorios.setToolTipText("Doble click sobre una fila para mostrar sus datos");
				ScrollAccesorios.setBounds(10, 10, 1225, 644);
		
		JPanel panel_2 = new JPanel();
		JPanel panel_3 = new JPanel();
		JPanel panel_4 = new JPanel();
		
		tabbedPane.addTab("Accesorios", null, panel_2, null);
		panel_2.setLayout(null);
		panel_2.add(ScrollAccesorios);
		
		tabbedPane.addTab("Medicamentos", null, panel_3, null);
		panel_3.setLayout(null);
		
		tabbedPane.addTab("Alimentos", null, panel_4, null);
		panel_4.setLayout(null);
		
		JScrollPane ScrollAlimentos = new JScrollPane();
		ScrollAlimentos.setBounds(10, 10, 1225, 644);
		panel_4.add(ScrollAlimentos);

		tabbedPane.setBounds(23, 85, 1250, 688);
		contentPanel.add(tabbedPane);
		
				TablaAccesorios = new JTable(){
					  @Override
					    public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
					      Component component = super.prepareRenderer(renderer, row, column);
					      int rendererWidth = component.getPreferredSize().width;
					      TableColumn tableColumn = getColumnModel().getColumn(column);
					      tableColumn.setPreferredWidth(Math.max(rendererWidth + getIntercellSpacing().width, tableColumn.getPreferredWidth()));
					      return component;
					    }
					  };
				TablaAccesorios.setShowHorizontalLines(false);
				TablaAccesorios.setIntercellSpacing(new Dimension(0, 1));
				TablaAccesorios.setOpaque(false);
				TablaAccesorios.setFocusable(false);
				TablaAccesorios.setShowVerticalLines(false);
				TablaAccesorios.setSelectionBackground(new Color(51, 204, 204));
				TablaAccesorios.setFont(new Font("Segoe UI", Font.PLAIN, 18));
				TablaAccesorios.setToolTipText("Doble click sobre una fila para mostrar sus datos");
				
				TablaAccesorios.addMouseListener(new MouseAdapter() {
					@Override
					
					public void mouseClicked(MouseEvent e) {

						if (e.getClickCount() == 2 && !e.isConsumed()) {
						     e.consume();
						     JOptionPane.showMessageDialog(null, "Id: " + TablaAccesorios.getValueAt(TablaAccesorios.getSelectedRow(), 0) + "\n" + "\nProducto: " +
						    		 TablaAccesorios.getValueAt(TablaAccesorios.getSelectedRow(), 1) + "\n" + "\nPrecio: " +
						    		 TablaAccesorios.getValueAt(TablaAccesorios.getSelectedRow(), 2) + "\n" + "\nDetalles: " +
						    		 TablaAccesorios.getValueAt(TablaAccesorios.getSelectedRow(), 3) + "\n" + "\nCantidad: " +
						    		 TablaAccesorios.getValueAt(TablaAccesorios.getSelectedRow(), 4));
						     //Evento de 2 click sobre un renglon
						}
						
					}
				});
				
		TablaAccesorios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		TablaAccesorios.setDefaultEditor(Object.class, null);
		
		ScrollAccesorios.setViewportView(TablaAccesorios);
		TablaAccesorios.setBorder(null);
		TablaAccesorios.setBackground(Color.WHITE);
		
		TablaAccesorios.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		
		TablaAccesorios.getTableHeader().setResizingAllowed(false);
		TablaAccesorios.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
		TablaAccesorios.getTableHeader().setOpaque(false);
		TablaAccesorios.getTableHeader().setBackground(new Color (0, 102, 102));
		TablaAccesorios.getTableHeader().setForeground(new Color (255, 255, 255));
		TablaAccesorios.setRowHeight(25);
		
		M_Accesorios.addColumn("Id");
		M_Accesorios.addColumn("Producto");
		M_Accesorios.addColumn("Precio");
		M_Accesorios.addColumn("Detalles");
		M_Accesorios.addColumn("Cantidad");
		
		TablaAccesorios.setModel(M_Accesorios);
		
		TableRowSorter<DefaultTableModel> ordena = new TableRowSorter<>(M_Accesorios);
		TablaAccesorios.setRowSorter(ordena);
		
		while(TablaAccesorios.getRowCount() > 0)
        {
            M_Accesorios.removeRow(0);
            M_Accesorios.setRowCount(0);
            M_Accesorios.setColumnCount(5);
        }
		
		try {
			
			LaConexion = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/loginuser", "root", "LFMG2004");
			
			Sentencia = LaConexion.createStatement();
			
			String sql = "SELECT id, nombre, precio, detalles, cantidad FROM accesorios";
			Rs = Sentencia.executeQuery(sql);
		
			while(Rs.next())
			{
				Object [] fila = new Object[5]; 

				  
				   for (int i=0;i<5;i++) {
					   fila[i] = Rs.getObject(i+1); 
					   
					   }
				     
				   M_Accesorios.addRow(fila);
		
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
		
		ScrollAlimentos.setViewportView(TablaAlimentos);
		
		JScrollPane ScrollMedicamentos = new JScrollPane();
		ScrollMedicamentos.setToolTipText("Doble click sobre una fila para mostrar sus datos");
		ScrollMedicamentos.setBorder(new LineBorder(new Color(130, 135, 144)));
		ScrollMedicamentos.setBackground(Color.GRAY);
		ScrollMedicamentos.setBounds(10, 10, 1225, 644);
		panel_3.add(ScrollMedicamentos);
		
		M_Medicamentos = new DefaultTableModel();
		TablaMedicamentos = new JTable(){
			  @Override
			    public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
			      Component component = super.prepareRenderer(renderer, row, column);
			      int rendererWidth = component.getPreferredSize().width;
			      TableColumn tableColumn = getColumnModel().getColumn(column);
			      tableColumn.setPreferredWidth(Math.max(rendererWidth + getIntercellSpacing().width, tableColumn.getPreferredWidth()));
			      return component;
			    }
			  };
		TablaMedicamentos.setToolTipText("Doble click sobre una fila para mostrar sus datos");
		TablaMedicamentos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		TablaMedicamentos.setRowHeight(25);
		TablaMedicamentos.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		TablaMedicamentos.setBorder(new LineBorder(new Color(0, 0, 0)));
		TablaMedicamentos.setBackground(Color.LIGHT_GRAY);
		TablaMedicamentos.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		ScrollMedicamentos.setViewportView(TablaMedicamentos);
		TablaMedicamentos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				if (e.getClickCount() == 2 && !e.isConsumed()) {
				     e.consume();
				     JOptionPane.showMessageDialog(null, "Id: " + TablaMedicamentos.getValueAt(TablaMedicamentos.getSelectedRow(), 0) + "\n" + "\nNombre: " +
				    		 TablaMedicamentos.getValueAt(TablaMedicamentos.getSelectedRow(), 1) + "\n" + "\nDosis: " +
				    		 TablaMedicamentos.getValueAt(TablaMedicamentos.getSelectedRow(), 2) + "\n" + "\nPrecio: " +
				    		 TablaMedicamentos.getValueAt(TablaMedicamentos.getSelectedRow(), 3) + "\n" + "\nCaducidad: " +
				    		 TablaMedicamentos.getValueAt(TablaMedicamentos.getSelectedRow(), 4) + "\n" + "\nCantidad: " +
				    		 TablaMedicamentos.getValueAt(TablaMedicamentos.getSelectedRow(), 5));
				     //Detectar doble click
				}
				
			}
		});
		
		TablaMedicamentos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		TablaMedicamentos.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		TablaMedicamentos.getTableHeader().setResizingAllowed(false);
		TablaMedicamentos.setDefaultEditor(Object.class, null);
		
		M_Medicamentos.addColumn("Id");
		M_Medicamentos.addColumn("Producto");
		M_Medicamentos.addColumn("Dosis");
		M_Medicamentos.addColumn("Precio");
		M_Medicamentos.addColumn("Caducidad");
		M_Medicamentos.addColumn("Cantidad");
		
		TablaMedicamentos.setModel(M_Medicamentos);
		
		TableRowSorter<DefaultTableModel> Ordenar = new TableRowSorter<>(M_Medicamentos);
		TablaMedicamentos.setRowSorter(Ordenar);
		
		TablaMedicamentos.setShowHorizontalLines(false);
		TablaMedicamentos.setIntercellSpacing(new Dimension(0, 1));
		TablaMedicamentos.setOpaque(false);
		TablaMedicamentos.setFocusable(false);
		TablaMedicamentos.setShowVerticalLines(false);
		TablaMedicamentos.setSelectionBackground(new Color(51, 204, 204));
		
		TablaMedicamentos.setBorder(null);
		TablaMedicamentos.setBackground(Color.WHITE);
		
		TablaMedicamentos.getTableHeader().setResizingAllowed(false);
		TablaMedicamentos.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
		TablaMedicamentos.getTableHeader().setOpaque(false);
		TablaMedicamentos.getTableHeader().setBackground(new Color (0, 102, 102));
		TablaMedicamentos.getTableHeader().setForeground(new Color (255, 255, 255));
		TablaMedicamentos.setRowHeight(25);
		
		while(TablaMedicamentos.getRowCount() > 0)
        {
            M_Medicamentos.removeRow(0);
            M_Medicamentos.setRowCount(0);
            M_Medicamentos.setColumnCount(6);
        }
		
		try {
			
			LaConexion = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/loginuser", "root", "LFMG2004");
			
			Sentencia = LaConexion.createStatement();
			
			String sql = "SELECT id, nombre, dosis, precio, caducidad, cantidad FROM medicamentos";
			Rs = Sentencia.executeQuery(sql);
		
			while(Rs.next())
			{
				Object [] fila = new Object[6]; 

				  
				   for (int i=0;i<6;i++) {
					   fila[i] = Rs.getObject(i+1); 
					   
					   }
				     
				   M_Medicamentos.addRow(fila);
		
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
				
				TablaAlimentos = new JTable(){
					  @Override
					    public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
					      Component component = super.prepareRenderer(renderer, row, column);
					      int rendererWidth = component.getPreferredSize().width;
					      TableColumn tableColumn = getColumnModel().getColumn(column);
					      tableColumn.setPreferredWidth(Math.max(rendererWidth + getIntercellSpacing().width, tableColumn.getPreferredWidth()));
					      return component;
					    }
					  };
				TablaAlimentos.setRowHeight(25);
				TablaAlimentos.setFont(new Font("Segoe UI", Font.PLAIN, 18));
					  TablaAlimentos.setToolTipText("Doble click sobre una fila para mostrar sus datos");
					  TablaAlimentos.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {

						if (e.getClickCount() == 2 && !e.isConsumed()) {
						     e.consume();
						     JOptionPane.showMessageDialog(null, "Id: " + TablaAlimentos.getValueAt(TablaAlimentos.getSelectedRow(), 0) + "\n" + "\nProducto: " +
						    		 TablaAlimentos.getValueAt(TablaAlimentos.getSelectedRow(), 1) + "\n" + "\nMarca: " +
						    		 TablaAlimentos.getValueAt(TablaAlimentos.getSelectedRow(), 2) + "\n" + "\nPrecio: " +
						    		 TablaAlimentos.getValueAt(TablaAlimentos.getSelectedRow(), 3) + "\n" + "\nKilogramos: " +
						    		 TablaAlimentos.getValueAt(TablaAlimentos.getSelectedRow(), 4) + "\n" + "\nCaducidad: " + 
						    		 TablaAlimentos.getValueAt(TablaAlimentos.getSelectedRow(), 5) + "\n" + "\nCantidad: " + 
						    		 TablaAlimentos.getValueAt(TablaAlimentos.getSelectedRow(), 6));
						     //Detectar doble click
						}

					}
				});
					  
					  TablaAlimentos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				
					  TablaAlimentos.setDefaultEditor(Object.class, null);
				
				ScrollAlimentos.setViewportView(TablaAlimentos);
				TablaAlimentos.setBorder(new LineBorder(new Color(0, 0, 0)));
				TablaAlimentos.setBackground(Color.LIGHT_GRAY);
				
				M_Alimentos.addColumn("Id");
				M_Alimentos.addColumn("Producto");
				M_Alimentos.addColumn("Marca");
				M_Alimentos.addColumn("Precio");
				M_Alimentos.addColumn("Kilogramos");
				M_Alimentos.addColumn("Caducidad");
				M_Alimentos.addColumn("Cantidad");
				
				TablaAlimentos.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
				
				TablaAlimentos.setModel(M_Alimentos);
				
				TablaAlimentos.getTableHeader().setResizingAllowed(false);
				
				TableRowSorter<DefaultTableModel> OrdenaAlimentos = new TableRowSorter<>(M_Alimentos);
				TablaAlimentos.setRowSorter(OrdenaAlimentos);

				TablaAlimentos.setShowHorizontalLines(false);
				TablaAlimentos.setIntercellSpacing(new Dimension(0, 1));
				TablaAlimentos.setOpaque(false);
				TablaAlimentos.setFocusable(false);
				TablaAlimentos.setShowVerticalLines(false);
				TablaAlimentos.setSelectionBackground(new Color(51, 204, 204));
				
				TablaAlimentos.setBorder(null);
				TablaAlimentos.setBackground(Color.WHITE);
				
				TablaAlimentos.getTableHeader().setResizingAllowed(false);
				TablaAlimentos.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
				TablaAlimentos.getTableHeader().setOpaque(false);
				TablaAlimentos.getTableHeader().setBackground(new Color (0, 102, 102));
				TablaAlimentos.getTableHeader().setForeground(new Color (255, 255, 255));
				TablaAlimentos.setRowHeight(25);
						
						while(TablaAlimentos.getRowCount() > 0)
		                {
		                    M_Alimentos.removeRow(0);
		                    M_Alimentos.setRowCount(0);
		                    M_Alimentos.setColumnCount(7);
		                }
						
						try {
							
							LaConexion = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/loginuser", "root", "LFMG2004");
							
							Sentencia = LaConexion.createStatement();
							
							String sql = "SELECT id, producto, marca, precio, kilogramos, caducidad, cantidad FROM alimentos";
							Rs = Sentencia.executeQuery(sql);
						
							while(Rs.next())
							{
								Object [] fila = new Object[7]; 
								  
								   for (int i=0;i<7;i++) {
									   fila[i] = Rs.getObject(i+1); 
									   
									   }
								     
								   M_Alimentos.addRow(fila);
						
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
		
		JPanel PandelDown = new JPanel();
		PandelDown.setBounds(10, 779, 1274, 60);
		PandelDown.setBackground(new Color(7, 200, 254));
		contentPanel.add(PandelDown);
		PandelDown.setLayout(null);
		
		JLabel LblModificar = new JLabel("");
		LblModificar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(tabbedPane.getSelectedIndex() < 1) {
					
					Menu.x = 4;
					int filas = TablaAccesorios.getSelectedRow();

			if (filas >=0) 
			{
				
				Agregar Agregar = new Agregar();
	        	Agregar.BtnModificar2.setEnabled(true);
				Agregar.setLocationRelativeTo(null);
				Agregar.setVisible(true);
				
			}
			else
			{
				//JOptionPane.showMessageDialog(null, "No se ha seleccionado un registro", "ERROR", JOptionPane.ERROR_MESSAGE);
				JOPRe.dialog = new JOPRe();
				Principal.jopre = "eliminar";
				JOPRe.dialog.setLocationRelativeTo(null);
				JOPRe.dialog.setVisible(true);
			
			}
					
				}else if(tabbedPane.getSelectedIndex() == 1) {
					
					int filas = TablaMedicamentos.getSelectedRow();

			if (filas >=0) 
			{
				Menu.x = 5;
				Agregar Agregar = new Agregar();
	        	Agregar.BtnModificar2.setEnabled(true);
				Agregar.setLocationRelativeTo(null);
				Agregar.setVisible(true);
				
			}
			else
			{
				//JOptionPane.showMessageDialog(null, "No se ha seleccionado un registro", "ERROR", JOptionPane.ERROR_MESSAGE);
				
				Principal.jopre = "eliminar";
				JOPRe.dialog = new JOPRe();
				JOPRe.dialog.setLocationRelativeTo(null);
				JOPRe.dialog.setVisible(true);
			
			}
					
				}else if(tabbedPane.getSelectedIndex() == 2) {
					
					int filas = TablaAlimentos.getSelectedRow();

			if (filas >=0) 
			{
				Menu.x = 6;
				Agregar Agregar = new Agregar();
	        	Agregar.BtnModificar2.setEnabled(true);
				Agregar.setLocationRelativeTo(null);
				Agregar.setVisible(true);
				
			}
			else
			{
				JOPRe.dialog = new JOPRe();
				Principal.jopre = "eliminar";
				JOPRe.dialog.setLocationRelativeTo(null);
				JOPRe.dialog.setVisible(true);
				//JOptionPane.showMessageDialog(null, "No se ha seleccionado un registro", "ERROR", JOptionPane.ERROR_MESSAGE);
			}
					
				}
				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				
				LblModificar.setBounds(626, 1, 55, 55);
				LblModificar.setIcon(new ImageIcon(Productos.class.getResource("/Img/editar - copia.png")));
				
			}
			@Override
			public void mouseExited(MouseEvent e) {

				LblModificar.setIcon(new ImageIcon(Productos.class.getResource("/Img/editar.png")));
				
			}
		});
		
		JLabel LblEliminar = new JLabel("");
		
		LblEliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(Productos.tabbedPane.getSelectedIndex() < 1) {
					
					String Ubicacion;
					int respuesta, Registros = 0, Id = 0, filas = TablaAccesorios.getSelectedRow();
            try {
            	
				LaConexion = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/loginuser", "root", "LFMG2004");
                
                Sentencia = LaConexion.createStatement();

				if (filas >=0) 
				{
					Principal.input = "eliminar";
					Input.dialog = new Input();
					Input.dialog.setLocationRelativeTo(null);
					Input.dialog.setVisible(true);
					
					if(Input.yn.equals("yes"))
					{
						Ubicacion = String.valueOf(TablaAccesorios.getValueAt(filas, 0));	
    					Rs = Sentencia.executeQuery("SELECT id FROM accesorios WHERE id = '"+Ubicacion+"' ");
    					while(Rs.next())
    	                {
    	                	Id = Integer.parseInt(Rs.getString("id"));
    	                }
    					
    					 PreparedStatement SentenciaP = LaConexion.prepareStatement("DELETE FROM accesorios WHERE id = ?");
    					 SentenciaP.setInt(1, Id);
    					 
    					 Registros = SentenciaP.executeUpdate();
    					 if(Registros >= 1)
    	    				{
    	    					
    	    					//Borra Las filas de las tabla
    	    					int i, filascount = M_Accesorios.getRowCount();
    	  					  
    	    					for(i=0; i<filascount; i++) 
    	    					{
    	    						M_Accesorios.removeRow(0);
    	    					}
    	    					//Se consulta para actualizar los datos
    	    					//Sentencia
    	    					
    	    					String sql = "SELECT id, nombre, precio, detalles, cantidad FROM accesorios";
    	    					Rs = Sentencia.executeQuery(sql);
    	    					while(Rs.next())
    	                        {
    	                            String registros[] = {Rs.getString("id"), Rs.getString("nombre"), Rs.getString("precio"), Rs.getString("detalles")
    	                            		, Rs.getString("cantidad")};
    	                            M_Accesorios.addRow(registros);
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
					//JOptionPane.showMessageDialog(null, "No se ha seleccionado un registro", "ERROR", JOptionPane.ERROR_MESSAGE);
					
					JOPRe.dialog = new JOPRe();
					Principal.jopre = "eliminar";
					JOPRe.LblError.setVisible(true);
					JOPRe.dialog.setLocationRelativeTo(null);
					JOPRe.dialog.setVisible(true);
				}

					}catch(Exception el)
					{
						Principal.jopre = "error";
						JOPRe.dialog = new JOPRe();
						JOPRe.dialog.setLocationRelativeTo(null);
						JOPRe.dialog.setVisible(true);
						//JOptionPane.showMessageDialog(null,el.toString(), "Error", JOptionPane.ERROR_MESSAGE);
					}
					
				}else if(Productos.tabbedPane.getSelectedIndex() == 1) {
					
					String Ubicacion;
					int respuesta, Registros = 0, Id = 0, filas = TablaMedicamentos.getSelectedRow();
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
	    						Ubicacion = String.valueOf(TablaMedicamentos.getValueAt(filas, 0));	
	        					Rs = Sentencia.executeQuery("SELECT id FROM medicamentos WHERE id = '"+Ubicacion+"' ");
	        					while(Rs.next())
	        	                {
	        	                	Id = Integer.parseInt(Rs.getString("id"));
	        	                }
	        					
	        					 PreparedStatement SentenciaP = LaConexion.prepareStatement("DELETE FROM medicamentos WHERE id = ?");
	        					 SentenciaP.setInt(1, Id);
	        					 
	        					 Registros = SentenciaP.executeUpdate();
	        					 if(Registros >= 1)
	        	    				{
	        	    					
	        	    					//Borra Las filas de las tabla
	        	    					int i, filascount = M_Medicamentos.getRowCount();
	        	  					  
	        	    					for(i=0; i<filascount; i++) 
	        	    					{
	        	    						M_Medicamentos.removeRow(0);
	        	    					}
	        	    					//Se consulta para actualizar los datos
	        	    					//Sentencia
	        	    					
	        	    					String sql = "SELECT id, nombre, dosis, precio, caducidad, cantidad FROM medicamentos";
	        	    					Rs = Sentencia.executeQuery(sql);
	        	    					while(Rs.next())
	        	                        {
	        	                            String registros[] = {Rs.getString("id"), Rs.getString("nombre"), Rs.getString("dosis"), Rs.getString("precio"), 
	        	                            		Rs.getString("caducidad"), Rs.getString("cantidad")};
	        	                            M_Medicamentos.addRow(registros);
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
	    					//JOptionPane.showMessageDialog(null, "No se ha seleccionado un registro", "ERROR", JOptionPane.ERROR_MESSAGE);
	    					JOPRe.dialog = new JOPRe();
	    					Principal.jopre = "eliminar";
	    					JOPRe.LblError.setVisible(true);
	    					JOPRe.dialog.setLocationRelativeTo(null);
	    					JOPRe.dialog.setVisible(true);
	    				
	    				}
		
					}catch(Exception el)
					{
						Principal.jopre = "error";
						JOPRe.dialog = new JOPRe();
						JOPRe.dialog.setLocationRelativeTo(null);
						JOPRe.dialog.setVisible(true);
						//JOptionPane.showMessageDialog(null,el.toString(), "Error", JOptionPane.ERROR_MESSAGE);
					}
					
				}else if(Productos.tabbedPane.getSelectedIndex() == 2) {
					
					String Ubicacion;
					int respuesta, Registros = 0, Id = 0, filas = TablaAlimentos.getSelectedRow();
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
	    						Ubicacion = String.valueOf(TablaAlimentos.getValueAt(filas, 0));	
	        					Rs = Sentencia.executeQuery("SELECT id FROM alimentos WHERE id = '"+Ubicacion+"' ");
	        					while(Rs.next())
	        	                {
	        	                	Id = Integer.parseInt(Rs.getString("id"));
	        	                }
	        					
	        					 PreparedStatement SentenciaP = LaConexion.prepareStatement("DELETE FROM alimentos WHERE id = ?");
	        					 SentenciaP.setInt(1, Id);
	        					 
	        					 Registros = SentenciaP.executeUpdate();
	        					 if(Registros >= 1)
	        	    				{
	        	    					
	        	    					//Borra Las filas de las tabla
	        	    					int i, filascount = M_Alimentos.getRowCount();
	        	  					  
	        	    					for(i=0; i<filascount; i++) 
	        	    					{
	        	    						M_Alimentos.removeRow(0);
	        	    					}
	        	    					//Se consulta para actualizar los datos
	        	    					//Sentencia
	        	    					
	        	    					String sql = "SELECT id, producto, marca, precio, kilogramos, caducidad, cantidad FROM alimentos";
	        	    					Rs = Sentencia.executeQuery(sql);
	        	    					while(Rs.next())
	        	                        {
	        	                            String registros[] = {Rs.getString("id"), Rs.getString("producto"), Rs.getString("marca"), Rs.getString("precio")
	        	                            		, Rs.getString("kilogramos"), Rs.getString("caducidad"), Rs.getString("cantidad")};
	        	                            M_Alimentos.addRow(registros);
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
	    					//JOptionPane.showMessageDialog(null, "No se ha seleccionado un registro", "ERROR", JOptionPane.ERROR_MESSAGE);
	    					JOPRe.dialog = new JOPRe();
	    					Principal.jopre = "eliminar";
	    					JOPRe.LblError.setVisible(true);
	    					JOPRe.dialog.setLocationRelativeTo(null);
	    					JOPRe.dialog.setVisible(true);
	    					
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
			@Override
			public void mouseExited(MouseEvent e) {
				
				LblEliminar.setIcon(new ImageIcon(Productos.class.getResource("/Img/basura.png")));
				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				
				LblEliminar.setBounds(725, 1, 55, 55);
				LblEliminar.setIcon(new ImageIcon(Productos.class.getResource("/Img/basura - copia.png")));
				
			}
		});
		LblEliminar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		LblEliminar.setIcon(new ImageIcon(Productos.class.getResource("/Img/basura.png")));
		LblEliminar.setToolTipText("Eliminar registro");
		LblEliminar.setBounds(725, 4, 50, 50);
		PandelDown.add(LblEliminar);
		LblModificar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		LblModificar.setIcon(new ImageIcon(Productos.class.getResource("/Img/editar.png")));
		LblModificar.setToolTipText("Modificar registro");
		LblModificar.setBounds(626, 4, 50, 50);
		PandelDown.add(LblModificar);
		
		JLabel LblAgregar = new JLabel("");
		if(Principal.tipo.equals("empleado")) {
			
			LblAgregar.setVisible(false);
			LblModificar.setVisible(false);
			LblEliminar.setVisible(false);
			
			
		}else {
			
			LblAgregar.setVisible(true);
			LblModificar.setVisible(true);
			LblEliminar.setVisible(true);
			
		}
				LblAgregar.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						
						if(LblAgregar.isEnabled()==true) {
							
						Menu.x = 0;
						Agregar ag = new Agregar();
						Agregar.BtnModificar2.setEnabled(false);
						ag.setLocationRelativeTo(null);
						ag.setVisible(true);
						
						}

					}
					@Override
					public void mouseEntered(MouseEvent e) {
						
						LblAgregar.setBounds(521, 1, 55, 55);
						LblAgregar.setIcon(new ImageIcon(Productos.class.getResource("/Img/boton-agregar - copia.png")));
						
					}
					@Override
					public void mouseExited(MouseEvent e) {
						
						LblAgregar.setIcon(new ImageIcon(Productos.class.getResource("/Img/boton-agregar.png")));
						
					}
				});
				LblAgregar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				LblAgregar.setIcon(new ImageIcon(Productos.class.getResource("/Img/boton-agregar.png")));
				LblAgregar.setBounds(521, 4, 50, 50);
				PandelDown.add(LblAgregar);
				LblAgregar.setToolTipText("Agregar registro");
				
				TxtBuscador = new JTextField();
				TxtBuscador.setToolTipText("Escribe el producto a buscar");
				TxtBuscador.setForeground(Color.BLACK);
				TxtBuscador.setFont(new Font("Segoe UI", Font.PLAIN, 18));
				TxtBuscador.setBorder(null);
				TxtBuscador.setOpaque(false);
				TxtBuscador.setBounds(75, 20, 275, 25);
				PandelDown.add(TxtBuscador);
				TxtBuscador.addCaretListener(new CaretListener() {
					public void caretUpdate(CaretEvent e) {
						
						if(tabbedPane.getSelectedIndex() == 0) {
							
							try {
								
								try 
								{

								Connection LaConexion;
								ResultSet Rs = null;

									String BusquedaTexto = TxtBuscador.getText();
									int i, filascount = M_Accesorios.getRowCount();
									  
									for(i=0; i<filascount; i++) 
									{
										M_Accesorios.removeRow(0);
									}
									
									LaConexion = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/loginuser", "root", "LFMG2004");
		                    
		                    Statement sentenciap;
		                    sentenciap = LaConexion.createStatement();

		                    	Rs = sentenciap.executeQuery("SELECT id, nombre, precio, detalles, cantidad FROM accesorios WHERE nombre LIKE '%"+BusquedaTexto+"%'");
		                    
		                    while(Rs.next())
		                    {
		                        String registros[] = {Rs.getString("id"), Rs.getString("nombre"), Rs.getString("precio"), Rs.getString("detalles")
		                        		, Rs.getString("cantidad")};
		                        M_Accesorios.addRow(registros);
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
								
								int F = M_Accesorios.getRowCount();

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

					                while(TablaAccesorios.getRowCount() > 0)
					                {
					                	M_Accesorios.removeRow(0);
					                }

					                try {

					    				LaConexion = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/loginuser", "root", "LFMG2004");
					                    Sentencia = LaConexion.createStatement();

					                    String sql = "SELECT id, nombre, precio, detalles, cantidad FROM accesorios";
		    					Rs = Sentencia.executeQuery(sql);
		    					while(Rs.next())
		                        {
		                            String registros[] = {Rs.getString("id"), Rs.getString("nombre"), Rs.getString("precio"), Rs.getString("detalles")
		                            		, Rs.getString("cantidad")};
		                            M_Accesorios.addRow(registros);
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
									int i, filascount = M_Medicamentos.getRowCount();
									  
									for(i=0; i<filascount; i++) 
									{
										M_Medicamentos.removeRow(0);
									}
									
									LaConexion = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/loginuser", "root", "LFMG2004");
		                    
		                    Statement sentenciap;
		                    sentenciap = LaConexion.createStatement();

		                    Rs = sentenciap.executeQuery("SELECT id, nombre, dosis, precio, caducidad, cantidad FROM medicamentos WHERE nombre LIKE '%"+BusquedaTexto+"%'");
		                    
    	    					while(Rs.next())
    	                        {
    	                            String registros[] = {Rs.getString("id"), Rs.getString("nombre"), Rs.getString("dosis"), Rs.getString("precio"), 
    	                            		Rs.getString("caducidad"), Rs.getString("cantidad")};
    	                            M_Medicamentos.addRow(registros);
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
								
								int F = M_Medicamentos.getRowCount();

						        if(F == 0) {
						        	
						        	Principal.jopre = "datos";
        							JOPRe.dialog = new JOPRe();
        							JOPRe.dialog.setLocationRelativeTo(null);
        							JOPRe.dialog.setVisible(true);
						        	//JOptionPane.showMessageDialog(null,"No se encontr\u00f3 ningun dato", "ERROR", JOptionPane.ERROR_MESSAGE);

					                while(TablaMedicamentos.getRowCount() > 0)
					                {
					                	M_Medicamentos.removeRow(0);
					                }

					                try {

					    				LaConexion = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/loginuser", "root", "LFMG2004");
					                    Sentencia = LaConexion.createStatement();

					                    String sql = "SELECT id, nombre, dosis, precio, caducidad, cantidad FROM medicamentos";
	        	    					Rs = Sentencia.executeQuery(sql);
	        	    					while(Rs.next())
	        	                        {
	        	                            String registros[] = {Rs.getString("id"), Rs.getString("nombre"), Rs.getString("dosis"), Rs.getString("precio"), 
	        	                            		Rs.getString("caducidad"), Rs.getString("cantidad")};
	        	                            M_Medicamentos.addRow(registros);
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
							
						}else if(tabbedPane.getSelectedIndex() == 2) {
							
							try {
								
								try 
								{

								Connection LaConexion;
								ResultSet Rs = null;

									String BusquedaTexto = TxtBuscador.getText();
									int i, filascount = M_Alimentos.getRowCount();
									  
									for(i=0; i<filascount; i++) 
									{
										M_Alimentos.removeRow(0);
									}
									
									LaConexion = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/loginuser", "root", "LFMG2004");
		                    
		                    Statement sentenciap;
		                    sentenciap = LaConexion.createStatement();

		                    Rs = sentenciap.executeQuery("SELECT id, producto, marca, precio, kilogramos, caducidad, cantidad FROM alimentos WHERE producto LIKE '%"+BusquedaTexto+"%'");
		                    
	                    	while(Rs.next())
	                        {
	                            String registros[] = {Rs.getString("id"), Rs.getString("producto"), Rs.getString("marca"), Rs.getString("precio")
	                            		, Rs.getString("kilogramos"), Rs.getString("caducidad"), Rs.getString("cantidad")};
	                            M_Alimentos.addRow(registros);
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
								
								int F = M_Alimentos.getRowCount();

						        if(F == 0) {
						        	
						        	Principal.jopre = "datos";
        							JOPRe.dialog = new JOPRe();
        							JOPRe.dialog.setLocationRelativeTo(null);
        							JOPRe.dialog.setVisible(true);
						        	//JOptionPane.showMessageDialog(null,"No se encontr\u00f3 ningun dato", "ERROR", JOptionPane.ERROR_MESSAGE);

					                while(TablaAlimentos.getRowCount() > 0)
					                {
					                	M_Alimentos.removeRow(0);
					                }

					                try {

					    				LaConexion = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/loginuser", "root", "LFMG2004");
					                    Sentencia = LaConexion.createStatement();

					                    String sql = "SELECT id, producto, marca, precio, kilogramos, caducidad, cantidad FROM alimentos";
				    					Rs = Sentencia.executeQuery(sql);
				    					while(Rs.next())
				                        {
				                            String registros[] = {Rs.getString("id"), Rs.getString("producto"), Rs.getString("marca"), Rs.getString("precio")
				                            		, Rs.getString("kilogramos"), Rs.getString("caducidad"), Rs.getString("cantidad")};
				                            M_Alimentos.addRow(registros);
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
					@Override
					public void keyPressed(KeyEvent e) {
						
						if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {

                while(TablaAccesorios.getRowCount() > 0)
                {
                    M_Accesorios.removeRow(0);
                }

                try {

                	LaConexion = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/loginuser", "root", "LFMG2004");
							
							Sentencia = LaConexion.createStatement();

							String sql = "SELECT id, nombre, precio, detalles, cantidad FROM accesorios";
							Rs = Sentencia.executeQuery(sql);
							while(Rs.next())
                        {
                            String registros[] = {Rs.getString("id"), Rs.getString("nombre"), Rs.getString("precio"), Rs.getString("detalles")
                            		, Rs.getString("cantidad")};
                            M_Accesorios.addRow(registros);
                           }


                    LaConexion.close();

                }catch(Exception el)
                {
                	
                	Principal.jopre = "error";
					JOPRe.dialog = new JOPRe();
					JOPRe.dialog.setLocationRelativeTo(null);
					JOPRe.dialog.setVisible(true);
                   // JOptionPane.showMessageDialog(null,el.toString(), "Error", JOptionPane.ERROR_MESSAGE);

                }
						
					}
						
					}
				});
				TxtBuscador.setColumns(10);
				
				JLabel LblBuscar = new JLabel("");
				LblBuscar.setIcon(new ImageIcon(Productos.class.getResource("/Img/lupa (2).png")));
				LblBuscar.setBounds(10, 4, 50, 50);
				PandelDown.add(LblBuscar);
				LblBuscar.setFont(new Font("Tahoma", Font.PLAIN, 12));
				
				JLabel LblVender = new JLabel("");
				LblVender.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseExited(MouseEvent e) {
						
						LblVender.setIcon(new ImageIcon(Productos.class.getResource("/Img/carro-de-la-carretilla.png")));
						
					}
					@Override
					public void mouseEntered(MouseEvent e) {
						
						LblVender.setBounds(384, 2, 55, 55);
						LblVender.setIcon(new ImageIcon(Productos.class.getResource("/Img/carro-de-la-carretilla - copia.png")));
						
					}
					@Override
					public void mouseClicked(MouseEvent e) {

						PtoVenta pv = new PtoVenta();
						pv.setLocationRelativeTo(null);
						pv.setVisible(true);
						
					}
				});
				LblVender.setIcon(new ImageIcon(Productos.class.getResource("/Img/carro-de-la-carretilla.png")));
				LblVender.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				LblVender.setToolTipText("Nuevo registro");
				LblVender.setBounds(384, 4, 50, 50);
				PandelDown.add(LblVender);
				
				JLabel LblLinea = new JLabel("");
				LblLinea.setBorder(new LineBorder(new Color(0, 0, 0)));
				LblLinea.setBounds(75, 45, 275, 2);
				PandelDown.add(LblLinea);
				
				JLabel LblAtras = new JLabel("");
				LblAtras.setToolTipText("Ir atr\u00E1s");
				LblAtras.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {

//						Productos.dialog.setVisible(false);
						dispose();
						Menu.dialog.setLocationRelativeTo(null);
						Menu.dialog.setVisible(true);
						
					}
					@Override
					public void mouseEntered(MouseEvent e) {
						
						LblAtras.setBounds(1199, 2, 55, 55);
						LblAtras.setIcon(new ImageIcon(Productos.class.getResource("/Img/resta - copia.png")));
						
					}
					@Override
					public void mouseExited(MouseEvent e) {
						
						LblAtras.setIcon(new ImageIcon(Productos.class.getResource("/Img/resta.png")));
						
					}
				});
				LblAtras.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				LblAtras.setIcon(new ImageIcon(Productos.class.getResource("/Img/resta.png")));
				LblAtras.setBounds(1199, 4, 50, 50);
				PandelDown.add(LblAtras);
				
				
		JLabel LblOverlay = new JLabel("");
		LblOverlay.setBounds(0, 0, 1305, 850);
		LblOverlay.setForeground(Color.BLACK);
		LblOverlay.setIcon(new ImageIcon(Productos.class.getResource("/Img/rectangulo - copia - copia.png")));
		contentPanel.add(LblOverlay);
		
	}
}
