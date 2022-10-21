import java.awt.BorderLayout;

import java.awt.EventQueue;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class barcode extends JFrame {

	private JPanel contentPane;
	public static JLabel LblBarcode;
	public static Result result;
	public static BufferedImage bf;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					barcode frame = new barcode();
					frame.setVisible(true);
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public barcode() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1292, 831);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		LblBarcode = new JLabel("Barcode");
		LblBarcode.setFont(new Font("Tahoma", Font.PLAIN, 99));
		LblBarcode.setBounds(49, 57, 1193, 609);
		contentPane.add(LblBarcode);
		
		JButton BtnBarcode = new JButton("Detectar");
		BtnBarcode.setFont(new Font("Tahoma", Font.PLAIN, 99));
		BtnBarcode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String path = "D:\\EscritorioGrande\\Descargas\\barcode.gif";
				
				try {
					bf = ImageIO.read(new FileInputStream(path));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(bf)));
				
				try {
					
					result = new MultiFormatReader().decode(bitmap);
				} catch (NotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				LblBarcode.setText(result.getText());
				
			}
		});
		BtnBarcode.setBounds(226, 542, 990, 218);
		contentPane.add(BtnBarcode);
	}
}
