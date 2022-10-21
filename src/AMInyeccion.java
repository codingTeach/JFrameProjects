import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Cursor;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.Toolkit;

public class AMInyeccion extends JDialog {

	private final JPanel contentPanel = new JPanel();
	public static AMInyeccion dialog = new AMInyeccion();
	private int mouseX, mouseY;
	public static JLabel BtnModificar;
	private JTextField TxtNombre;
	private JTextField TxtRaza;
	private JTextField TxtKg;
	private JTextField TxtEdad;
	private JTextField TxtBiologico;
	public static JPanel PanelUp;
	
	Connection LaConexion = null;
	
	Statement Sentencia; //lanzar sql
	
	ResultSet Rs;
	private JTextField TxtFolio;
	private JTextField TxtTel;
	
	public static void main(String[] args) {
		try {
			dialog = new AMInyeccion();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public AMInyeccion() {
		setTitle("Agregar inyecciones");
		setIconImage(Toolkit.getDefaultToolkit().getImage(AMInyeccion.class.getResource("/Img/anadir.png")));
		setUndecorated(true);
		setBounds(100, 100, 749, 484);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		PanelUp = new JPanel();
		PanelUp.setBorder(new LineBorder(new Color(0, 0, 0)));
		PanelUp.setBackground(new Color(0, 139, 139));
		PanelUp.setBounds(0, 0, 749, 48);
		contentPanel.add(PanelUp);
		PanelUp.setLayout(null);
		
		JPanel PanelDown = new JPanel();
		PanelDown.setBackground(new Color(0, 139, 139));
		PanelDown.setBorder(new LineBorder(new Color(0, 0, 0)));
		PanelDown.setBounds(0, 419, 749, 65);
		contentPanel.add(PanelDown);
		PanelDown.setLayout(null);
		
		JLabel BtnCerrar = new JLabel("");
		BtnCerrar.setToolTipText("Cerrar");
		BtnCerrar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		BtnCerrar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				
				BtnCerrar.setIcon(new ImageIcon(AMInyeccion.class.getResource("/Img/resta.png")));
				BtnCerrar.setBounds(689, 5, 50, 50);
				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				
				BtnCerrar.setIcon(new ImageIcon(AMInyeccion.class.getResource("/Img/resta - copia.png")));
				BtnCerrar.setBounds(689, 0, 60, 60);
				
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				
				Menu.x = 0;
				dispose();
				
			}
		});
		BtnCerrar.setIcon(new ImageIcon(AMInyeccion.class.getResource("/Img/resta.png")));
		BtnCerrar.setBounds(689, 5, 50, 50);
		PanelDown.add(BtnCerrar);
		
		JComboBox CmbDiaA = new JComboBox();
		CmbDiaA.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
		CmbDiaA.setBounds(261, 125, 50, 22);
		contentPanel.add(CmbDiaA);
		
		JComboBox CmbMesA = new JComboBox();
		CmbMesA.setModel(new DefaultComboBoxModel(new String[] {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"}));
		CmbMesA.setBounds(331, 125, 109, 22);
		contentPanel.add(CmbMesA);
		
		JComboBox CmbYearA = new JComboBox();
		CmbYearA.setModel(new DefaultComboBoxModel(new String[] {"2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030", "2031", "2032"}));
		CmbYearA.setBounds(462, 125, 64, 22);
		contentPanel.add(CmbYearA);
		
		JLabel LblCita = new JLabel("Cita:");
		LblCita.setBounds(261, 260, 88, 23);
		contentPanel.add(LblCita);
		
		JComboBox CmbDiaC = new JComboBox();
		CmbDiaC.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
		CmbDiaC.setBounds(261, 300, 50, 22);
		contentPanel.add(CmbDiaC);
		
		JComboBox CmbMesC = new JComboBox();
		CmbMesC.setModel(new DefaultComboBoxModel(new String[] {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"}));
		CmbMesC.setBounds(331, 300, 109, 22);
		contentPanel.add(CmbMesC);
		
		JComboBox CmbYearC = new JComboBox();
		CmbYearC.setModel(new DefaultComboBoxModel(new String[] {"2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030", "2031", "2032"}));
		CmbYearC.setBounds(462, 300, 64, 22);
		contentPanel.add(CmbYearC);
		
		JComboBox CmbSexo = new JComboBox();
		CmbSexo.setModel(new DefaultComboBoxModel(new String[] {"Macho", "Hembra"}));
		CmbSexo.setBounds(70, 140, 86, 22);
		contentPanel.add(CmbSexo);

		JLabel LblRaza = new JLabel("Especie");
		
		JLabel LblNombre = new JLabel("Nombre");

		JLabel LblKg = new JLabel("Peso (Kg)");

		JLabel LblEdad = new JLabel("Edad (Meses)");

		JLabel LblAplicacion = new JLabel("Caducidad:");

		JLabel LblTel = new JLabel("Tel\u00E9fono");

		JLabel LblFolio = new JLabel("Folio Receta");

		TxtTel = new JTextField();

		TxtFolio = new JTextField();
		
		JLabel BtnAgregar = new JLabel("");
		BtnAgregar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				
				BtnAgregar.setIcon(new ImageIcon(AMInyeccion.class.getResource("/Img/anadir.png")));
				BtnAgregar.setBounds(10, 15, 40, 40);
				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				
				BtnAgregar.setIcon(new ImageIcon(AMInyeccion.class.getResource("/Img/anadir - copia.png")));
				BtnAgregar.setBounds(10, 10, 50, 50);
				
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int valido = 0;
				
				if(Menu.x == 1 ) {
					
					if(TxtNombre.getText().length()==0 || TxtRaza.getText().length()==0 || TxtKg.getText().length()==0 || TxtEdad.getText().length()==0
							|| TxtBiologico.getText().length()==0)
					{
						Principal.jopre = "invalido";
						JOPRe.dialog = new JOPRe();
						JOPRe.dialog.setLocationRelativeTo(null);
						JOPRe.dialog.setVisible(true);
						//JOptionPane.showMessageDialog(null,"Ingrese datos en los campos", "Error", JOptionPane.ERROR_MESSAGE);
						TxtNombre.requestFocus();
						valido = 0;
						
					}else {
						
						valido = 1;
						
					}
					
				}else if(Menu.x == 2) {
					
					if(TxtNombre.getText().length()==0 || TxtRaza.getText().length()==0 || TxtKg.getText().length()==0 || TxtEdad.getText().length()==0
							|| TxtBiologico.getText().length()==0)
					{
						
						Principal.jopre = "invalido";
						JOPRe.dialog = new JOPRe();
						JOPRe.dialog.setLocationRelativeTo(null);
						JOPRe.dialog.setVisible(true);
						//JOptionPane.showMessageDialog(null,"Ingrese datos en los campos", "Error", JOptionPane.ERROR_MESSAGE);
						TxtNombre.requestFocus();
						valido = 0;
						
					}else {
						
						valido = 2;
						
					}
					
				}
				
				if(Inyecta.tabbedPane.getSelectedIndex() == 0) {
					
					try {
						
						LaConexion = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/loginuser", "root", "LFMG2004");
						
						Sentencia = LaConexion.createStatement();

						//Insertar datos
						int Registros;
						String noMes = null;
						
						String dia = (String) CmbDiaA.getSelectedItem();
						String mes = (String) CmbMesA.getSelectedItem();
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
						String year = (String) CmbYearA.getSelectedItem();
						
						String noMesC = null;
						
						String diaC = (String) CmbDiaC.getSelectedItem();
						String mesC = (String) CmbMesC.getSelectedItem();
						switch(mesC) {
						
						case "Enero":
							
							noMesC = "1";
							break;
							
						case "Febrero":
							
							noMesC = "2";
							break;
							
						case "Marzo":
							
							noMesC = "3";
							break;
							
						case "Abril":
							
							noMesC = "4";
							break;
						
						case "Mayo":
							
							noMesC = "5";
							break;
							
						case "Junio":
							
							noMesC = "6";
							break;
							
						case "Julio":
							
							noMesC = "7";
							break;
							
						case "Agosto":
							
							noMesC = "8";
							break;
							
						case "Septiembre":
							
							noMesC = "9";
							break;
							
						case "Octubre":
							
							noMesC = "10";
							break;
							
						case "Noviembre":
							
							noMesC = "11";
							break;
							
						case "Diciembre":
							
							noMesC = "12";
							break;
						
						}
						String yearC = (String) CmbYearC.getSelectedItem();
						
						
						String fechaC = yearC + "-" + noMesC + "-" + diaC;
						String fechaAplicacion = year + "-" + noMes + "-" + dia;
						String sexo = (String) CmbSexo.getSelectedItem();
						
						PreparedStatement SentenciaP = LaConexion.prepareStatement("INSERT INTO inyecciones (nombre, sexo, raza, peso, edadmeses,"
								+ "aplicacion, biologico, citaproxima, folioreceta, tel) VALUES(?,?,?,?,?,?,?,?,?,?)");
						
						SentenciaP.setString(1,TxtNombre.getText());
						SentenciaP.setString(2,sexo);
						SentenciaP.setString(3,TxtRaza.getText());//HAcer lo mismo q con fecha
						SentenciaP.setString(4,TxtKg.getText());
						SentenciaP.setString(5,TxtEdad.getText());
						SentenciaP.setString(6,fechaAplicacion);
						SentenciaP.setString(7,TxtBiologico.getText());
						SentenciaP.setString(8,fechaC);
						SentenciaP.setString(9,TxtFolio.getText());
						SentenciaP.setString(10,TxtTel.getText());
						
						/*for(int i = 0; i < 5; i++) {
							
							String TablaMinuscula = (String) Medicamentos.TablaMedicamentos.getValueAt(i, 1);
							TablaMinuscula = TablaMinuscula.toLowerCase();
							String TextoMinuscula = TxtProductos.getText().toLowerCase();
							
							if(TextoMinuscula.equals(TablaMinuscula)) {
								
								JOptionPane.showMessageDialog(null, TablaMinuscula + " " + TextoMinuscula);
								
								 * 
								 * AL REPETIRSE SE PREGUNTA SI DESEA AÑADIR UNA UNIDAD AL PRODUCTO DE ESE NOMBRE
								 * 
								 * BTW, QUE SE PUEDA AÑADIR UNIDADES DE UN PRODUCTO CON UN SPINNER O BIEN BOTONES DE + Y -
								 * 
								 * 
								
							}
							
						}*/
						
						Registros = SentenciaP.executeUpdate();
						
						while(Inyecta.TablaVacunas.getRowCount() > 0)
		                {
							Inyecta.M_Vax.removeRow(0);
		                }
						
						if(Registros>=1)
						{
							
							TxtNombre.setText("");
							LblNombre.setText("Nombre");
							CmbSexo.setSelectedItem(1);
							TxtRaza.setText("");
							LblRaza.setText("Raza");
							TxtKg.setText("");
							LblKg.setText("Peso (Kg)");
							TxtEdad.setText("");
							LblEdad.setText("Edad (Meses)");
							TxtFolio.setText("");
							LblFolio.setText("Folio receta");
							TxtTel.setText("");
							LblTel.setText("Teléfono");
							TxtNombre.requestFocus();
							
							String sql = "SELECT id, nombre, sexo, raza, peso, edadmeses, aplicacion, biologico, citaproxima, folioreceta, tel FROM inyecciones";
							Rs = Sentencia.executeQuery(sql);
							while(Rs.next())
							{
								Object [] fila = new Object[11]; 

								  
								   for(int i=0;i<11;i++) {
									   fila[i] = Rs.getObject(i+1); 
									   
									   }
								     
								   Inyecta.M_Vax.addRow(fila);
								   
							}
						
							Principal.jopre = "exito";
							JOPRe.dialog = new JOPRe();
							JOPRe.dialog.setLocationRelativeTo(null);
							JOPRe.dialog.setVisible(true);
							//JOptionPane.showMessageDialog(null,"Se Registro Correctamente", "Aviso", JOptionPane.INFORMATION_MESSAGE);
	                           
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
					
				}else if(Inyecta.tabbedPane.getSelectedIndex() == 1) {
					
					try {
						
						LaConexion = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/loginuser", "root", "LFMG2004");
						
						Sentencia = LaConexion.createStatement();

						//Insertar datos
						int Registros;
						String noMes = null;
						
						String dia = (String) CmbDiaA.getSelectedItem();
						String mes = (String) CmbMesA.getSelectedItem();
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
						String year = (String) CmbYearA.getSelectedItem();
						
						String noMesC = null;
						
						String diaC = (String) CmbDiaC.getSelectedItem();
						String mesC = (String) CmbMesC.getSelectedItem();
						switch(mesC) {
						
						case "Enero":
							
							noMesC = "1";
							break;
							
						case "Febrero":
							
							noMesC = "2";
							break;
							
						case "Marzo":
							
							noMesC = "3";
							break;
							
						case "Abril":
							
							noMesC = "4";
							break;
						
						case "Mayo":
							
							noMesC = "5";
							break;
							
						case "Junio":
							
							noMesC = "6";
							break;
							
						case "Julio":
							
							noMesC = "7";
							break;
							
						case "Agosto":
							
							noMesC = "8";
							break;
							
						case "Septiembre":
							
							noMesC = "9";
							break;
							
						case "Octubre":
							
							noMesC = "10";
							break;
							
						case "Noviembre":
							
							noMesC = "11";
							break;
							
						case "Diciembre":
							
							noMesC = "12";
							break;
						
						}
						String yearC = (String) CmbYearC.getSelectedItem();
						
						
						String fechaC = yearC + "-" + noMesC + "-" + diaC;
						String fechaAplicacion = year + "-" + noMes + "-" + dia;
						String sexo = (String) CmbSexo.getSelectedItem();
						
						PreparedStatement SentenciaP = LaConexion.prepareStatement("INSERT INTO desparacita (nombre, sexo, raza, peso, edadmeses,"
								+ "aplicacion, biologico, citaproxima, folioreceta, tel) VALUES(?,?,?,?,?,?,?,?,?,?)");
						
						SentenciaP.setString(1,TxtNombre.getText());
						SentenciaP.setString(2,sexo);
						SentenciaP.setString(3,TxtRaza.getText());//HAcer lo mismo q con fecha
						SentenciaP.setString(4,TxtKg.getText());
						SentenciaP.setString(5,TxtEdad.getText());
						SentenciaP.setString(6,fechaAplicacion);
						SentenciaP.setString(7,TxtBiologico.getText());
						SentenciaP.setString(8,fechaC);
						SentenciaP.setString(9,TxtFolio.getText());
						SentenciaP.setString(10,TxtTel.getText());
						
						/*for(int i = 0; i < 5; i++) {
							
							String TablaMinuscula = (String) Medicamentos.TablaMedicamentos.getValueAt(i, 1);
							TablaMinuscula = TablaMinuscula.toLowerCase();
							String TextoMinuscula = TxtProductos.getText().toLowerCase();
							
							if(TextoMinuscula.equals(TablaMinuscula)) {
								
								JOptionPane.showMessageDialog(null, TablaMinuscula + " " + TextoMinuscula);
								
								 * 
								 * AL REPETIRSE SE PREGUNTA SI DESEA AÑADIR UNA UNIDAD AL PRODUCTO DE ESE NOMBRE
								 * 
								 * BTW, QUE SE PUEDA AÑADIR UNIDADES DE UN PRODUCTO CON UN SPINNER O BIEN BOTONES DE + Y -
								 * 
								 * 
								
							}
							
						}*/
						
						Registros = SentenciaP.executeUpdate();
						
						while(Inyecta.TablaDesparacitado.getRowCount() > 0)
		                {
							Inyecta.M_Desparacito.removeRow(0);
		                }
						
						if(Registros>=1)
						{
							
							TxtNombre.setText("");
							LblNombre.setText("Nombre");
							CmbSexo.setSelectedItem(1);
							TxtRaza.setText("");
							LblRaza.setText("Raza");
							TxtKg.setText("");
							LblKg.setText("Peso (Kg)");
							TxtEdad.setText("");
							LblEdad.setText("Edad (Meses)");
							TxtFolio.setText("");
							LblFolio.setText("Folio receta");
							TxtTel.setText("");
							LblTel.setText("Teléfono");
							TxtNombre.requestFocus();
							
							String sql = "SELECT id, nombre, sexo, raza, peso, edadmeses, aplicacion, biologico, citaproxima, folioreceta, tel FROM desparacita";
							Rs = Sentencia.executeQuery(sql);
							while(Rs.next())
							{
								Object [] fila = new Object[11]; 

								  
								   for(int i=0;i<11;i++) {
									   fila[i] = Rs.getObject(i+1); 
									   
									   }
								     
								   Inyecta.M_Desparacito.addRow(fila);
								   
							}
						
							Principal.jopre = "exito";
							JOPRe.dialog = new JOPRe();
							JOPRe.dialog.setLocationRelativeTo(null);
							JOPRe.dialog.setVisible(true);
							//JOptionPane.showMessageDialog(null,"Se Registro Correctamente", "Aviso", JOptionPane.INFORMATION_MESSAGE);
	                           
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
		});
		BtnAgregar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		BtnAgregar.setIcon(new ImageIcon(AMInyeccion.class.getResource("/Img/anadir.png")));
		BtnAgregar.setToolTipText("Agregar un registro nuevo");
		BtnAgregar.setBounds(10, 15, 40, 40);
		PanelDown.add(BtnAgregar);
		
		BtnModificar = new JLabel("");
		BtnModificar.setEnabled(false);
		BtnModificar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				
				BtnModificar.setIcon(new ImageIcon(AMInyeccion.class.getResource("/Img/lapiz.png")));
				BtnModificar.setBounds(78, 15, 40, 40);
				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				
				if(BtnModificar.isEnabled() == true) {
					
					BtnModificar.setIcon(new ImageIcon(AMInyeccion.class.getResource("/Img/lapiz - copia.png")));
					BtnModificar.setBounds(78, 10, 50, 50);
					
				}
				
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(Menu.x == 1 || Menu.x == 4) {
					
					Connection LaConexion;
					ResultSet Rs; 
					int Id = 0, Registros=0;
					String Ubicacion = "";  
					int filas = Inyecta.TablaVacunas.getSelectedRow();
					
					//Obtiene la longitud de cada campo de texto y lo compara si es 0 (es decir que no contiene nada, entonces no permitirï¿½ capturar)
					if(TxtNombre.getText().length()==0 || TxtRaza.getText().length()==0 || TxtKg.getText().length()==0 || TxtEdad.getText().length()==0
							|| TxtBiologico.getText().length()==0)
					{
						
						Principal.jopre = "invalido";
						JOPRe.dialog = new JOPRe();
						JOPRe.dialog.setLocationRelativeTo(null);
						JOPRe.dialog.setVisible(true);
						//JOptionPane.showMessageDialog(null,"Ingrese los datos", "Error", JOptionPane.ERROR_MESSAGE);
						TxtNombre.requestFocus();
						
					}
					else
					{
						try {
							
							LaConexion = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/loginuser", "root", "LFMG2004");
		                    
		                    Statement Sentenciap;
		                    Sentenciap = LaConexion.createStatement();

							String noMes = null;
							
							String dia = (String) CmbDiaA.getSelectedItem();
							String mes = (String) CmbMesA.getSelectedItem();
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
							String year = (String) CmbYearA.getSelectedItem();
							
							String noMesC = null;
							
							String diaC = (String) CmbDiaC.getSelectedItem();
							String mesC = (String) CmbMesC.getSelectedItem();
							switch(mesC) {
							
							case "Enero":
								
								noMesC = "1";
								break;
								
							case "Febrero":
								
								noMesC = "2";
								break;
								
							case "Marzo":
								
								noMesC = "3";
								break;
								
							case "Abril":
								
								noMesC = "4";
								break;
							
							case "Mayo":
								
								noMesC = "5";
								break;
								
							case "Junio":
								
								noMesC = "6";
								break;
								
							case "Julio":
								
								noMesC = "7";
								break;
								
							case "Agosto":
								
								noMesC = "8";
								break;
								
							case "Septiembre":
								
								noMesC = "9";
								break;
								
							case "Octubre":
								
								noMesC = "10";
								break;
								
							case "Noviembre":
								
								noMesC = "11";
								break;
								
							case "Diciembre":
								
								noMesC = "12";
								break;
							
							}
							String yearC = (String) CmbYearC.getSelectedItem();
							
							String fechaC = yearC + "-" + noMesC + "-" + diaC;
							String fechaAplicacion = year + "-" + noMes + "-" + dia;
							String sexo = (String) CmbSexo.getSelectedItem();
		                    
		    				if (filas >=0) 
		    				{
		    					Ubicacion = String.valueOf(Inyecta.TablaVacunas.getValueAt(filas, 0));	
		    				}
		    				else
		    				{
		    					Principal.jopre = "eliminar";
    							JOPRe.dialog = new JOPRe();
    							JOPRe.dialog.setLocationRelativeTo(null);
    							JOPRe.dialog.setVisible(true);
		    					//JOptionPane.showMessageDialog(null, "No se ha seleccionado un registro", "ERROR", JOptionPane.ERROR_MESSAGE);
		    				}
		                    
		                   Rs = Sentenciap.executeQuery("SELECT id FROM inyecciones WHERE id = '"+Ubicacion+"' ");
		                   
		                   while(Rs.next())
		                   {
		                	   Id = Integer.parseInt(Rs.getString("id"));
		                   }
		                   
		                    PreparedStatement Sentencia = LaConexion.prepareStatement("UPDATE inyecciones SET nombre= ?, sexo = ?, raza= ?"
		                    		+ ", peso = ?, edadmeses = ?, aplicacion = ?, biologico = ?, citaproxima = ?, folioreceta = ?, tel = ? WHERE id = ?");
						    
		                    Sentencia.setString(1,TxtNombre.getText());
							Sentencia.setString(2,sexo);
							Sentencia.setString(3,TxtRaza.getText());//HAcer lo mismo q con fecha
							Sentencia.setString(4,TxtKg.getText());
							Sentencia.setString(5,TxtEdad.getText());
							Sentencia.setString(6,fechaAplicacion);
							Sentencia.setString(7,TxtBiologico.getText());
							Sentencia.setString(8,fechaC);
							Sentencia.setString(9,TxtFolio.getText());
							Sentencia.setString(10,TxtTel.getText());
							Sentencia.setInt(11, Id);
		                    
		                    Registros = Sentencia.executeUpdate();
		                    
		    				if(Registros >= 1)
		    				{
	
		    					dispose();

		    					//Borra Las filas de las tabla
		    					int i, filascount = Inyecta.M_Vax.getRowCount();
		  					  
		    					for(i=0; i<filascount; i++) 
		    					{
		    						Inyecta.M_Vax.removeRow(0);
		    					}
		    					//Se consulta para actualizar los datos
		    					//Sentencia
		    					
		    					Sentencia=LaConexion.prepareStatement("SELECT id, nombre, sexo, raza, peso, edadmeses, aplicacion, biologico, citaproxima, folioreceta, tel FROM inyecciones");
		    					Rs=Sentencia.executeQuery();
		    					
		    					while(Rs.next())
			                    {
		    						String registros[] = {Rs.getString("id"), Rs.getString("nombre"), Rs.getString("sexo"), Rs.getString("raza")
    	                            		, Rs.getString("peso"), Rs.getString("edadmeses"), Rs.getString("aplicacion"), Rs.getString("biologico")
    	                            		, Rs.getString("citaproxima"), Rs.getString("folioreceta"), Rs.getString("tel")};
			                        Inyecta.M_Vax.addRow(registros);
			                       }

		    					Principal.jopre = "exito";
    							JOPRe.dialog = new JOPRe();
    							JOPRe.dialog.setLocationRelativeTo(null);
    							JOPRe.dialog.setVisible(true);
		    					//JOptionPane.showMessageDialog(null, "Registro modificado", "Aviso", JOptionPane.INFORMATION_MESSAGE);
		    					Menu.x = 1;
		    					TxtNombre.requestFocus();
		    					
		    				}
		    				else
		    				{
		    					Principal.jopre = "vacio";
    							JOPRe.dialog = new JOPRe();
    							JOPRe.dialog.setLocationRelativeTo(null);
    							JOPRe.dialog.setVisible(true);
		    					//JOptionPane.showMessageDialog(null, "No se pudo modificar", "Error", JOptionPane.ERROR_MESSAGE);
		    					TxtNombre.requestFocus();
		    				}
		    				LaConexion.close();
							
						}catch(Exception el)
						{
							Principal.jopre = "error";
							JOPRe.dialog = new JOPRe();
							JOPRe.dialog.setLocationRelativeTo(null);
							JOPRe.dialog.setVisible(true);
							//JOptionPane.showMessageDialog(null,el.toString(), "Error", JOptionPane.ERROR_MESSAGE);
							TxtNombre.requestFocus();

						}
					}
					
				}else if(Menu.x == 2 || Menu.x == 5) {
					
					Connection LaConexion;
					ResultSet Rs; 
					int Id = 0, Registros=0;
					String Ubicacion = "";  
					int filas = Inyecta.TablaDesparacitado.getSelectedRow();
					
					//Obtiene la longitud de cada campo de texto y lo compara si es 0 (es decir que no contiene nada, entonces no permitirï¿½ capturar)
					if(TxtNombre.getText().length()==0 || TxtRaza.getText().length()==0 || TxtKg.getText().length()==0 || TxtEdad.getText().length()==0
							|| TxtBiologico.getText().length()==0)
					{
						Principal.jopre = "invalido";
						JOPRe.dialog = new JOPRe();
						JOPRe.dialog.setLocationRelativeTo(null);
						JOPRe.dialog.setVisible(true);
						//JOptionPane.showMessageDialog(null,"Ingrese los datos", "Error", JOptionPane.ERROR_MESSAGE);
						TxtNombre.requestFocus();
						
					}
					else
					{
						try {
							
							LaConexion = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/loginuser", "root", "LFMG2004");
		                    
		                    Statement Sentenciap;
		                    Sentenciap = LaConexion.createStatement();

							String noMes = null;
							
							String dia = (String) CmbDiaA.getSelectedItem();
							String mes = (String) CmbMesA.getSelectedItem();
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
							String year = (String) CmbYearA.getSelectedItem();
							
							String noMesC = null;
							
							String diaC = (String) CmbDiaC.getSelectedItem();
							String mesC = (String) CmbMesC.getSelectedItem();
							switch(mesC) {
							
							case "Enero":
								
								noMesC = "1";
								break;
								
							case "Febrero":
								
								noMesC = "2";
								break;
								
							case "Marzo":
								
								noMesC = "3";
								break;
								
							case "Abril":
								
								noMesC = "4";
								break;
							
							case "Mayo":
								
								noMesC = "5";
								break;
								
							case "Junio":
								
								noMesC = "6";
								break;
								
							case "Julio":
								
								noMesC = "7";
								break;
								
							case "Agosto":
								
								noMesC = "8";
								break;
								
							case "Septiembre":
								
								noMesC = "9";
								break;
								
							case "Octubre":
								
								noMesC = "10";
								break;
								
							case "Noviembre":
								
								noMesC = "11";
								break;
								
							case "Diciembre":
								
								noMesC = "12";
								break;
							
							}
							String yearC = (String) CmbYearC.getSelectedItem();
							
							String fechaC = yearC + "-" + noMesC + "-" + diaC;
							String fechaAplicacion = year + "-" + noMes + "-" + dia;
							String sexo = (String) CmbSexo.getSelectedItem();
		                    
		    				if (filas >=0) 
		    				{
		    					Ubicacion = String.valueOf(Inyecta.TablaDesparacitado.getValueAt(filas, 0));	
		    				}
		    				else
		    				{
		    					Principal.jopre = "eliminar";
    							JOPRe.dialog = new JOPRe();
    							JOPRe.dialog.setLocationRelativeTo(null);
    							JOPRe.dialog.setVisible(true);
		    					//JOptionPane.showMessageDialog(null, "No se ha seleccionado un registro", "ERROR", JOptionPane.ERROR_MESSAGE);
		    				}
		                    
		                   Rs = Sentenciap.executeQuery("SELECT id FROM desparacita WHERE id = '"+Ubicacion+"' ");
		                   
		                   while(Rs.next())
		                   {
		                	   Id = Integer.parseInt(Rs.getString("id"));
		                   }
		                   
		                    PreparedStatement Sentencia = LaConexion.prepareStatement("UPDATE desparacita SET nombre= ?, sexo = ?, raza= ?"
		                    		+ ", peso = ?, edadmeses = ?, aplicacion = ?, biologico = ?, citaproxima = ?, folioreceta = ?, tel = ? WHERE id = ?");
						    
		                    Sentencia.setString(1,TxtNombre.getText());
							Sentencia.setString(2,sexo);
							Sentencia.setString(3,TxtRaza.getText());//HAcer lo mismo q con fecha
							Sentencia.setString(4,TxtKg.getText());
							Sentencia.setString(5,TxtEdad.getText());
							Sentencia.setString(6,fechaAplicacion);
							Sentencia.setString(7,TxtBiologico.getText());
							Sentencia.setString(8,fechaC);
							Sentencia.setString(9,TxtFolio.getText());
							Sentencia.setString(10,TxtTel.getText());
							Sentencia.setInt(11, Id);
		                    
		                    Registros = Sentencia.executeUpdate();
		                    
		    				if(Registros >= 1)
		    				{
	
		    					dispose();

		    					//Borra Las filas de las tabla
		    					int i, filascount = Inyecta.M_Desparacito.getRowCount();
		  					  
		    					for(i=0; i<filascount; i++) 
		    					{
		    						Inyecta.M_Desparacito.removeRow(0);
		    					}
		    					//Se consulta para actualizar los datos
		    					//Sentencia
		    					
		    					Sentencia=LaConexion.prepareStatement("SELECT id, nombre, sexo, raza, peso, edadmeses, aplicacion, biologico, citaproxima, folioreceta, tel FROM desparacita");
		    					Rs=Sentencia.executeQuery();
		    					
		    					while(Rs.next())
			                    {
		    						String registros[] = {Rs.getString("id"), Rs.getString("nombre"), Rs.getString("sexo"), Rs.getString("raza")
    	                            		, Rs.getString("peso"), Rs.getString("edadmeses"), Rs.getString("aplicacion"), Rs.getString("biologico")
    	                            		, Rs.getString("citaproxima"), Rs.getString("folioreceta"), Rs.getString("tel")};
			                        Inyecta.M_Desparacito.addRow(registros);
			                       }

		    					Principal.jopre = "exito";
    							JOPRe.dialog = new JOPRe();
    							JOPRe.dialog.setLocationRelativeTo(null);
    							JOPRe.dialog.setVisible(true);
		    					//JOptionPane.showMessageDialog(null, "Registro modificado", "Aviso", JOptionPane.INFORMATION_MESSAGE);
		    					Menu.x = 2;
		    					TxtNombre.requestFocus();
		    					
		    				}
		    				else
		    				{
		    					Principal.jopre = "vacio";
    							JOPRe.dialog = new JOPRe();
    							JOPRe.dialog.setLocationRelativeTo(null);
    							JOPRe.dialog.setVisible(true);
		    					//JOptionPane.showMessageDialog(null, "No se pudo modificar", "Error", JOptionPane.ERROR_MESSAGE);
		    					TxtNombre.requestFocus();
		    				}
		    				LaConexion.close();
							
						}catch(Exception el)
						{
							Principal.jopre = "error";
							JOPRe.dialog = new JOPRe();
							JOPRe.dialog.setLocationRelativeTo(null);
							JOPRe.dialog.setVisible(true);
							//JOptionPane.showMessageDialog(null,el.toString(), "Error", JOptionPane.ERROR_MESSAGE);
							TxtNombre.requestFocus();

						}
					}
					
				}
				
			}
		});
		BtnModificar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		BtnModificar.setIcon(new ImageIcon(AMInyeccion.class.getResource("/Img/lapiz.png")));
		BtnModificar.setToolTipText("Modificar el registro");
		BtnModificar.setBounds(78, 15, 40, 40);
		PanelDown.add(BtnModificar);

		TxtNombre = new JTextField();
		TxtNombre.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				
				LblNombre.setText("");
				
			}
			@Override
			public void focusLost(FocusEvent e) {
				
				if(TxtNombre.getText().length() == 0) {
					
					LblNombre.setText("Nombre");
					
				}
				
			}
		});

		TxtNombre.setBorder(null);
		TxtNombre.setOpaque(false);
		TxtNombre.setBounds(70, 91, 86, 20);
		contentPanel.add(TxtNombre);
		TxtNombre.setColumns(10);
		
		JLabel LblNombreLinea = new JLabel("");
		LblNombreLinea.setBorder(new LineBorder(new Color(0, 0, 0)));
		LblNombreLinea.setBounds(70, 110, 86, 2);
		contentPanel.add(LblNombreLinea);
		
		LblNombre.setForeground(Color.GRAY);
		LblNombre.setBounds(70, 91, 86, 20);
		contentPanel.add(LblNombre);
		
		TxtRaza = new JTextField();
		TxtRaza.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				
				LblRaza.setText("");
				
			}
			@Override
			public void focusLost(FocusEvent e) {
				
				if(TxtRaza.getText().length() == 0) {
					
					LblRaza.setText("Raza");
					
				}
				
			}
		});
		TxtRaza.setOpaque(false);
		TxtRaza.setColumns(10);
		TxtRaza.setBorder(null);
		TxtRaza.setBounds(70, 194, 86, 20);
		contentPanel.add(TxtRaza);
		
		JLabel LblNombreLinea_1 = new JLabel("");
		LblNombreLinea_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		LblNombreLinea_1.setBounds(70, 215, 86, 2);
		contentPanel.add(LblNombreLinea_1);
		
		LblRaza.setForeground(Color.GRAY);
		LblRaza.setBounds(70, 195, 86, 20);
		contentPanel.add(LblRaza);
		
		TxtKg = new JTextField();
		TxtKg.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				
				LblKg.setText("");
				
			}
			@Override
			public void focusLost(FocusEvent e) {
				
				if(TxtKg.getText().length() == 0) {
					
					LblKg.setText("Peso (Kg)");
					
				}
				
			}
		});
		TxtKg.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				
				char c = e.getKeyChar();
				
				if(c<'0' || c>'9' && (c != KeyEvent.VK_BACK_SPACE)){
					
					e.consume();
				
			}
				
				 if(TxtKg.getText().length() >= 12)
				    {
				        e.consume();
				        
				    }
				
			}
		});
		TxtKg.setOpaque(false);
		TxtKg.setColumns(10);
		TxtKg.setBorder(null);
		TxtKg.setBounds(70, 247, 86, 20);
		contentPanel.add(TxtKg);
		
		JLabel LblNombreLinea_1_1 = new JLabel("");
		LblNombreLinea_1_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		LblNombreLinea_1_1.setBounds(70, 265, 86, 2);
		contentPanel.add(LblNombreLinea_1_1);
		
		LblKg.setForeground(Color.GRAY);
		LblKg.setBounds(70, 245, 86, 20);
		contentPanel.add(LblKg);
		
		TxtEdad = new JTextField();
		TxtEdad.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				
				LblEdad.setText("");
				
			}
			@Override
			public void focusLost(FocusEvent e) {
				
				if(TxtEdad.getText().length() == 0) {
					
					LblEdad.setText("Edad (Meses)");
					
				}
				
			}
		});
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
		TxtEdad.setOpaque(false);
		TxtEdad.setColumns(10);
		TxtEdad.setBorder(null);
		TxtEdad.setBounds(70, 301, 86, 20);
		contentPanel.add(TxtEdad);
		
		JLabel LblNombreLinea_1_1_1 = new JLabel("");
		LblNombreLinea_1_1_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		LblNombreLinea_1_1_1.setBounds(70, 319, 86, 2);
		contentPanel.add(LblNombreLinea_1_1_1);
		
		LblEdad.setForeground(Color.GRAY);
		LblEdad.setBounds(70, 301, 86, 20);
		contentPanel.add(LblEdad);
		
		JLabel LblBiologico = new JLabel("Biol\u00F3gico");
		TxtBiologico = new JTextField();
		TxtBiologico.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				
				LblBiologico.setText("");
				
			}
			@Override
			public void focusLost(FocusEvent e) {
				
				if(TxtBiologico.getText().length() == 0) {
					
					LblBiologico.setText("Biológico");
					
				}
				
			}
		});
		TxtBiologico.setOpaque(false);
		TxtBiologico.setColumns(10);
		TxtBiologico.setBorder(null);
		TxtBiologico.setBounds(322, 194, 86, 20);
		contentPanel.add(TxtBiologico);
		
		JLabel LblNombreLinea_1_1_1_1 = new JLabel("");
		LblNombreLinea_1_1_1_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		LblNombreLinea_1_1_1_1.setBounds(322, 212, 86, 2);
		contentPanel.add(LblNombreLinea_1_1_1_1);
		
		LblBiologico.setForeground(Color.GRAY);
		LblBiologico.setBounds(322, 194, 86, 20);
		contentPanel.add(LblBiologico);
		
		LblAplicacion.setForeground(Color.BLACK);
		LblAplicacion.setBounds(261, 91, 88, 23);
		contentPanel.add(LblAplicacion);
		
		JLabel LblTitulo = new JLabel("Agregar/Modificar");
		LblTitulo.setBounds(10, 0, 331, 43);
		PanelUp.add(LblTitulo);
		LblTitulo.setForeground(Color.WHITE);
		LblTitulo.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 36));

		TxtFolio = new JTextField();
		TxtFolio.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				
				char c = e.getKeyChar();
				
				if(c<'0' || c>'9' && (c != KeyEvent.VK_BACK_SPACE)){
					
					e.consume();
				
			}
				
				 if(TxtFolio.getText().length() >= 12)
				    {
				        e.consume();
				        
				    }
				
			}
		});
		TxtFolio.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				
				LblFolio.setText("");				
			}
			@Override
			public void focusLost(FocusEvent e) {
				
				if(TxtFolio.getText().length() == 0) {
					
					LblFolio.setText("Folio Receta");
					
				}
				
			}
		});
		TxtFolio.setOpaque(false);
		TxtFolio.setColumns(10);
		TxtFolio.setBorder(null);
		TxtFolio.setBounds(70, 360, 86, 20);
		contentPanel.add(TxtFolio);
		
		LblFolio.setForeground(Color.GRAY);
		LblFolio.setBounds(70, 360, 86, 20);
		contentPanel.add(LblFolio);
		
		JLabel LblNombreLinea_1_1_1_1_1 = new JLabel("");
		LblNombreLinea_1_1_1_1_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		LblNombreLinea_1_1_1_1_1.setBounds(70, 380, 86, 2);
		contentPanel.add(LblNombreLinea_1_1_1_1_1);

		TxtTel.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				
				char c = e.getKeyChar();
				
				if(c<'0' || c>'9' && (c != KeyEvent.VK_BACK_SPACE)){
					
					e.consume();
				
			}
				
				 if(TxtTel.getText().length() >= 12)
				    {
				        e.consume();
				        
				    }
				
			}
		});
		TxtTel.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				
				LblTel.setText("");
				
			}
			@Override
			public void focusLost(FocusEvent e) {
				
				if(TxtTel.getText().length() == 0) {
					
					LblTel.setText("Teléfono");
					
				}
				
			}
		});
		TxtTel.setOpaque(false);
		TxtTel.setColumns(10);
		TxtTel.setBorder(null);
		TxtTel.setBounds(263, 360, 86, 20);
		contentPanel.add(TxtTel);
		
		LblTel.setForeground(Color.GRAY);
		LblTel.setBounds(263, 360, 86, 20);
		contentPanel.add(LblTel);
		
		JLabel LblNombreLinea_1_1_1_1_1_1 = new JLabel("");
		LblNombreLinea_1_1_1_1_1_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		LblNombreLinea_1_1_1_1_1_1.setBounds(263, 380, 86, 2);
		contentPanel.add(LblNombreLinea_1_1_1_1_1_1);
		
		//Modificar Accesorios
		if(Menu.x == 4) {
			
			BtnModificar.setEnabled(true);

			TxtNombre.setText(""+Inyecta.TablaVacunas.getValueAt(Inyecta.TablaVacunas.getSelectedRow(), 1));
			LblNombre.setText("");
			TxtRaza.setText(""+Inyecta.TablaVacunas.getValueAt(Inyecta.TablaVacunas.getSelectedRow(), 3));
			LblRaza.setText("");
			TxtKg.setText(""+Inyecta.TablaVacunas.getValueAt(Inyecta.TablaVacunas.getSelectedRow(), 4));
			LblKg.setText("");
			TxtEdad.setText(""+Inyecta.TablaVacunas.getValueAt(Inyecta.TablaVacunas.getSelectedRow(), 5));
			LblEdad.setText("");
			TxtBiologico.setText(""+Inyecta.TablaVacunas.getValueAt(Inyecta.TablaVacunas.getSelectedRow(), 7));
			LblBiologico.setText("");
			TxtFolio.setText(""+Inyecta.TablaVacunas.getValueAt(Inyecta.TablaVacunas.getSelectedRow(), 8));
			LblFolio.setText("");
			TxtTel.setText(""+Inyecta.TablaVacunas.getValueAt(Inyecta.TablaVacunas.getSelectedRow(), 9));
			LblTel.setText("");
			
		//Modificar Medicamentos
		}else if(Menu.x == 5) {
			
			BtnModificar.setEnabled(true);
			
			TxtNombre.setText(""+Inyecta.TablaDesparacitado.getValueAt(Inyecta.TablaDesparacitado.getSelectedRow(), 1));
			LblNombre.setText("");
			TxtRaza.setText(""+Inyecta.TablaDesparacitado.getValueAt(Inyecta.TablaDesparacitado.getSelectedRow(), 3));
			LblRaza.setText("");
			TxtKg.setText(""+Inyecta.TablaDesparacitado.getValueAt(Inyecta.TablaDesparacitado.getSelectedRow(), 4));
			LblKg.setText("");
			TxtEdad.setText(""+Inyecta.TablaDesparacitado.getValueAt(Inyecta.TablaDesparacitado.getSelectedRow(), 5));
			LblEdad.setText("");
			TxtBiologico.setText(""+Inyecta.TablaDesparacitado.getValueAt(Inyecta.TablaDesparacitado.getSelectedRow(), 7));
			LblBiologico.setText("");
			TxtFolio.setText(""+Inyecta.TablaVacunas.getValueAt(Inyecta.TablaVacunas.getSelectedRow(), 8));
			LblFolio.setText("");
			TxtTel.setText(""+Inyecta.TablaVacunas.getValueAt(Inyecta.TablaVacunas.getSelectedRow(), 9));
			LblTel.setText("");
			
		//Modificar Alimentos
		}else if(Inyecta.tabbedPane.getSelectedIndex() < 1) {

			BtnModificar.setEnabled(false);
			
			
			//Agregar Desparasitasao
		}else if(Inyecta.tabbedPane.getSelectedIndex() == 1) {
			
			BtnModificar.setEnabled(false);
			
		
		}
		
		
	}
}
