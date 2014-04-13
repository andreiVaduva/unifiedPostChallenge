package GUI;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.SwingConstants;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JTextField;
import javax.swing.JButton;

import Backend.Administrator;
import Backend.QueryDB;
import Backend.User;

import java.awt.Point;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;


public class LoginFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	public JPanel panel_1;
	public JPanel panel;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame frame = new LoginFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setSize(620,500);
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setBackground(Color.GRAY);
		
		BufferedImage img = null;
		try {
		    img = ImageIO.read(new File("res/logo_unifiedpost.png"));
		} catch (IOException e) {
		    e.printStackTrace();
		}
		Image dimg = img.getScaledInstance(200,50,Image.SCALE_SMOOTH);
		ImageIcon imageIcon = new ImageIcon(dimg);
		
		JLabel image_label = new JLabel(imageIcon); 
		
		panel.setLayout(new FlowLayout());
		panel.add(image_label);
		
		panel_1 = new JPanel();
		panel_1.setBackground(Color.LIGHT_GRAY);
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(null);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setFont(new Font("DejaVu Sans", Font.BOLD, 16));
		lblUsername.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUsername.setBounds(104, 93, 129, 40);
		panel_1.add(lblUsername);
		
		textField_1 = new JTextField();
		textField_1.setBounds(251, 102, 190, 26);
		panel_1.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("DejaVu Sans", Font.BOLD, 16));
		lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPassword.setSize(130, 40);
		lblPassword.setLocation(new Point(104, 131));
		panel_1.add(lblPassword);
		
		textField = new JPasswordField();
		textField.setBounds(251, 140, 190, 26);
		panel_1.add(textField);
		textField.setColumns(10);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setFont(new Font("DejaVu Sans", Font.BOLD, 13));
		btnLogin.setBounds(285, 178, 117, 26);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String user = textField_1.getText();
				String password = textField.getText();
				User u = QueryDB.isUser(user, password);
				if(u == null )
					JOptionPane.showMessageDialog(contentPane, "The user with the given username doesn't exist!");
				else {
					if (u.isAdmin == 1) {
						System.out.println("admin");
						AdminFrame f = new AdminFrame((Administrator)u);
						//contentPane.remove(panel_1);
						contentPane.add(f,BorderLayout.CENTER);
						//f.setVisible(true);
						contentPane.remove(panel);
						panel.setVisible(false);
						panel_1.setVisible(false);
						
						contentPane.remove(panel_1);
					}
					else {
						System.out.println("user");
						ClientFrame  clientf = new ClientFrame(u);
						setVisible(false);
					}
				}
					
					
				
			}
		});
		panel_1.add(btnLogin);
		
/*		BufferedImage img_1 = null;
		try {
		    img_1 = ImageIO.read(new File("logo_unifiedpost.png"));
		} catch (IOException e) {
		    e.printStackTrace();
		}
		Image simg_1 = img_1.getScaledInstance(195, 33, Image.SCALE_SMOOTH);
		JLabel image_label_1 = new JLabel(new ImageIcon(simg_1));
		image_label_1.setBackground(Color.LIGHT_GRAY);
		image_label_1.setLocation(12, 253);
		image_label_1.setSize(253, 60);
		panel_1.add(image_label_1);*/
	
		BufferedImage img_2 = null;
		try {
		    img_2 = ImageIO.read(new File("res/ligaac_logo.png"));
		} catch (IOException e) {
		    e.printStackTrace();
		}
		Image simg_2 = img_2.getScaledInstance(200, 50, Image.SCALE_SMOOTH);
		JLabel image_label_2 = new JLabel(new ImageIcon(simg_2));
		image_label_2.setBackground(Color.LIGHT_GRAY);
		image_label_2.setLocation(62, 322);
		image_label_2.setSize(253, 60);
		panel_1.add(image_label_2);
		
		BufferedImage img_3 = null;
		try {
		    img_3 = ImageIO.read(new File("res/itec-active.png"));
		} catch (IOException e) {
		    e.printStackTrace();
		}
		Image simg_3 = img_3.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
		JLabel image_label_3 = new JLabel(new ImageIcon(simg_3));
		image_label_3.setBackground(Color.LIGHT_GRAY);
		image_label_3.setLocation(382, 227);
		image_label_3.setSize(210, 189);
		panel_1.add(image_label_3);
	}

}