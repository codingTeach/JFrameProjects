import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Cursor;
import java.awt.Toolkit;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class Ventas extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable TablaVentas;
	public static DefaultTableModel M_Ventas = new DefaultTableModel();
	public static Ventas dialog = new Ventas();
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
	
	public Ventas() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Ventas.class.getResource("/Img/refugio-de-animales.png")));
		setUndecorated(true);
		setResizable(false);
		setTitle("Colitas - Ventas");
		setBounds(100, 100, 1298, 848);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(0, 153, 153));
		contentPanel.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(55, 71, 1178, 705);
		contentPanel.add(scrollPane);
		
		TablaVentas = new JTable(){
			  @Override
			    public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
			      Component component = super.prepareRenderer(renderer, row, column);
			      int rendererWidth = component.getPreferredSize().width;
			      TableColumn tableColumn = getColumnModel().getColumn(column);
			      tableColumn.setPreferredWidth(Math.max(rendererWidth + getIntercellSpacing().width, tableColumn.getPreferredWidth()));
			      return component;
			    }
			  };
		TablaVentas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if (e.getClickCount() == 2 && !e.isConsumed()) {
				     e.consume();
				     JOptionPane.showMessageDialog(null, "Id: " + TablaVentas.getValueAt(TablaVentas.getSelectedRow(), 0) + "\n" + "\nProducto: " +
				    		 TablaVentas.getValueAt(TablaVentas.getSelectedRow(), 1) + "\n" + "\nPrecio: " +
				    		 TablaVentas.getValueAt(TablaVentas.getSelectedRow(), 2) + "\n" + "\nCantidad: " +
				    		 TablaVentas.getValueAt(TablaVentas.getSelectedRow(), 3) + "\n" + "\nVendedor: " +
				    		 TablaVentas.getValueAt(TablaVentas.getSelectedRow(), 4));
				     //Evento de 2 click sobre un renglon
				}
				
			}
		});

		TablaVentas.setRowHeight(25);
		TablaVentas.setToolTipText("Doble click sobre una fila para mostrar sus datos");
		TablaVentas.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		
		TablaVentas.setBorder(new LineBorder(new Color(0, 0, 0)));
		TablaVentas.setSelectionBackground(new Color(51, 204, 204));
		TablaVentas.setShowHorizontalLines(false);
		TablaVentas.setIntercellSpacing(new Dimension(0, 1));
		TablaVentas.setOpaque(false);
		TablaVentas.setFocusable(false);
		TablaVentas.setShowVerticalLines(false);

		scrollPane.setViewportView(TablaVentas);
		
		TablaVentas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		TablaVentas.setDefaultEditor(Object.class, null);
		
		TablaVentas.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		M_Ventas.addColumn("Id");
		M_Ventas.addColumn("Producto");
		M_Ventas.addColumn("Precio");
		M_Ventas.addColumn("Cantidad");
		M_Ventas.addColumn("Vendedor");
		
		TablaVentas.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);	
		TablaVentas.getTableHeader().setResizingAllowed(false);
		TablaVentas.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
		TablaVentas.getTableHeader().setOpaque(false);
		TablaVentas.getTableHeader().setBackground(new Color (0, 102, 102));
		TablaVentas.getTableHeader().setForeground(new Color (255, 255, 255));
		TablaVentas.setRowHeight(25);
		
		TableRowSorter<DefaultTableModel> ordena = new TableRowSorter<>(M_Ventas);
		
		TablaVentas.setModel(M_Ventas);
		TablaVentas.setRowSorter(ordena);
		
		JPanel panel = new JPanel();
		panel.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				
				dialog.setLocation(dialog.getX() + e.getX() - mouseX, dialog.getY() + e.getY() - mouseY);
				
			}
		});
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
			
				mouseX = e.getX();
				mouseY = e.getY();
				
			}
		});
		panel.setOpaque(false);
		panel.setBounds(0, 0, 1298, 42);
		contentPanel.add(panel);
		
		JLabel BtnAtras = new JLabel("");
		BtnAtras.setToolTipText("Ir Atr\u00E1s");
		BtnAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				Ventas.dialog.setVisible(false);
				Menu.dialog.setVisible(true);
				Menu.dialog.setLocationRelativeTo(null);
				
			}
			@Override
			public void mouseExited(MouseEvent e) {
				
				BtnAtras.setIcon(new ImageIcon(Ventas.class.getResource("/Img/resta.png")));
				BtnAtras.setBounds(1208, 775, 50, 50);
				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				
				BtnAtras.setIcon(new ImageIcon(Ventas.class.getResource("/Img/resta - copia.png")));
				BtnAtras.setBounds(1208, 770, 60, 60);
				
			}
		});
		BtnAtras.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		BtnAtras.setIcon(new ImageIcon(Ventas.class.getResource("/Img/resta.png")));
		BtnAtras.setBounds(1210, 787, 50, 50);
		contentPanel.add(BtnAtras);
		
		JComboBox CmbFechas = new JComboBox();
		CmbFechas.setFont(new Font("Segoe UI", Font.BOLD, 28));
		CmbFechas.setModel(new DefaultComboBoxModel(new String[] {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"}));
		CmbFechas.setBounds(55, 787, 325, 50);
		contentPanel.add(CmbFechas);
		
		JButton BtnReporte = new JButton("Reporte");
		BtnReporte.setBounds(921, 787, 258, 50);
		contentPanel.add(BtnReporte);
		
		//Variable de la conexicon
		Connection LaConexion = null;
		
		Statement Sentencia; //lanzar sql
		
		ResultSet Rs;//Atrapar
		
		while(TablaVentas.getRowCount() > 0)
        {
            M_Ventas.removeRow(0);
            M_Ventas.setRowCount(0);
            M_Ventas.setColumnCount(4);
        }
		
		try {
			
			LaConexion = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/loginuser", "root", "LFMG2004");
			
			Sentencia = LaConexion.createStatement();
			
			String sql = "SELECT id, producto, precio, cantidad, vendedor FROM ventas";
			Rs = Sentencia.executeQuery(sql);
		
			while(Rs.next())
			{
				Object [] fila = new Object[5]; 
	  
				   for (int i=0;i<5;i++) {
					   fila[i] = Rs.getObject(i+1); 
					   
					   }
				     
				   M_Ventas.addRow(fila);
		
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
