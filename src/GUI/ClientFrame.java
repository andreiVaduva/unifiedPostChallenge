package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.*;

import Backend.Administrator;
import Backend.Category;
import Backend.Pair;
import Backend.Product;
import Backend.User;

import java.awt.Font;
import java.awt.Color;

public class ClientFrame extends JFrame {

	private JPanel contentPane;
	private JPanel panel;
	private JPanel panel_1;
	private JPanel panel_2;
	private static User thisUser;
	DefaultListModel<Product> listProductModel ;
	JList listProductuser ;
	JScrollPane listProduct;
	Pair[] listProductVotes ;
	JList listJVotes ;
	JScrollPane listVotes;
	JComboBox comboBox;
	JComboBox comboBox_1;
	JLabel lblPoints;
	User ahelp;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientFrame frame = new ClientFrame(thisUser);
					frame.setPreferredSize(new Dimension(620, 500));
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
	public ClientFrame(User u) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 870, 465);
		setVisible(true);
		this.thisUser = u;
		ahelp = new Administrator(thisUser.username,thisUser.passwd,thisUser.firstname,thisUser.lastname,thisUser.groupId,thisUser.isAdmin);
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		BufferedImage img = null;
		try {
		    img = ImageIO.read(new File("res/logo_unifiedpost.png"));
		} catch (IOException e) {
		    e.printStackTrace();
		}
		
		Image simg = img.getScaledInstance(195, 33, Image.SCALE_SMOOTH);
		JLabel image_label = new JLabel(new ImageIcon(simg));
		image_label.setLocation(12, 0);
		image_label.setSize(195,33);
		contentPane.add(image_label);
		
		panel_2 = new JPanel();
		panel_2.setBounds(0, 53, 864, 385);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		//comboBox_1 = new JComboBox();
		//comboBox_1.setBounds(253, 55, 183, 24);
		//panel_2.add(comboBox_1);
		
		
		comboBox = new JComboBox(((Administrator)ahelp).listCategory().toArray());
		comboBox.setBounds(28, 55, 183, 24);
		panel_2.add(comboBox);
		
		comboBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("DAP");
				Category c = (Category)comboBox.getSelectedItem();
				System.out.println(c.id);
				DefaultListModel<Product> prod = ((Administrator)ahelp).listProductCategory(c.id);
				comboBox_1 = new JComboBox(prod.toArray());
				comboBox_1.setBounds(253, 55, 183, 24);
				panel_2.add(comboBox_1);
				
				//panel_2.add(comboBox_1);
			}
		});
		JLabel lblChooseFromCategories = new JLabel("Choose from categories");
		lblChooseFromCategories.setBounds(31, 28, 180, 24);
		panel_2.add(lblChooseFromCategories);
		
		JLabel lblCategoryProducts = new JLabel("Products from category");
		lblCategoryProducts.setHorizontalAlignment(SwingConstants.CENTER);
		lblCategoryProducts.setBounds(253, 28, 180, 24);
		panel_2.add(lblCategoryProducts);
		
		
		JLabel lblMostPopularProducts = new JLabel("Most popular products");
		lblMostPopularProducts.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMostPopularProducts.setBounds(606, 28, 183, 24);
		panel_2.add(lblMostPopularProducts);
		
		//JList list_8 = new JList();
		//list_8.setBounds(606, 55, 217, 318);
		
		listProductVotes = ((Administrator)ahelp).calc_voted_products();
		listJVotes = new JList(listProductVotes);
		listVotes = new JScrollPane(listJVotes);
		listVotes.setBounds(623, 62, 200, 311);
		panel_2.add(listVotes);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(478, 55, 117, 25);
		panel_2.add(btnSubmit);
		
		btnSubmit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Product p = (Product)comboBox_1.getSelectedItem();
				int points = thisUser.calcPoints() - p.cost;
				if(points > 0) {
					thisUser.addProductUser(p.name);
					lblPoints = new JLabel(points + "");
				}
				else
					JOptionPane.showMessageDialog(panel_2, "You don't have enough points!");
			}
		});
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(28, 119, 566, 254);
		panel_2.add(panel_3);
		panel_2.setVisible(false);
		
		panel_1 = new JPanel();
		panel_1.setBounds(0, 53, 864, 385);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblAllCategories = new JLabel("All Categories");
		lblAllCategories.setBounds(115, 28, 107, 25);
		panel_1.add(lblAllCategories);
		
		JList list_5 = new JList();
		list_5.setBounds(46, 62, 228, 311);
		panel_1.add(list_5);
		
		JLabel lblMostPopularCategories = new JLabel("Most popular categories");
		lblMostPopularCategories.setBounds(326, 28, 184, 25);
		panel_1.add(lblMostPopularCategories);
		
		JList list_6 = new JList();
		list_6.setBounds(307, 62, 228, 311);
		panel_1.add(list_6);
		
		JLabel lblYourCategories = new JLabel("Your categories");
		lblYourCategories.setBounds(623, 29, 123, 23);
		panel_1.add(lblYourCategories);
		
		JList list_7 = new JList();
		list_7.setBounds(574, 62, 228, 311);
		panel_1.add(list_7);
		panel_1.setVisible(false);
		
		panel = new JPanel();
		panel.setBounds(0, 53, 864, 385);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblCategoryYouVote = new JLabel("Category you vote for");
		lblCategoryYouVote.setBounds(96, 28, 174, 22);
		panel.add(lblCategoryYouVote);
		
		JList list = new JList();
		list.setBounds(70, 62, 200, 226);
		panel.add(list);
		
		JLabel lblProductsYouVote = new JLabel("Products you vote for");
		lblProductsYouVote.setBounds(292, 28, 174, 22);
		panel.add(lblProductsYouVote);
		
		listProductModel = thisUser.listProductUser();
		listProductuser = new JList(listProductModel);
		listProduct = new JScrollPane(listProductuser);
		
		listProduct.setBounds(274, 62, 200, 226);
		panel.add(listProduct);
		
		JLabel lblCost = new JLabel("Cost");
		lblCost.setBounds(496, 28, 42, 22);
		panel.add(lblCost);
		
		JList list_2 = new JList();
		list_2.setBounds(479, 62, 70, 226);
		panel.add(list_2);
		
		JLabel lblSeeMoreProducts = new JLabel("See more products");
		lblSeeMoreProducts.setBounds(96, 315, 143, 28);
		panel.add(lblSeeMoreProducts);
		
		JList list_3 = new JList();
		list_3.setBounds(250, 300, 299, 73);
		panel.add(list_3);
		
		JLabel lblMostVotedProducts = new JLabel("Most voted products");
		lblMostVotedProducts.setBounds(645, 28, 154, 22);
		panel.add(lblMostVotedProducts);
		
		panel.add(listVotes);
		
		panel.setVisible(true);
		
		
		
		
// TODO: AICI TREBUIE ADUS DIN BAZA DE DATE USER-UL + POINTS_NUMBER
		
		JLabel lblHidenisse = new JLabel("Hi, " + thisUser.firstname);
		lblHidenisse.setHorizontalAlignment(SwingConstants.RIGHT);
		lblHidenisse.setBounds(751, 8, 101, 27);
		contentPane.add(lblHidenisse);
// TODO: si aici la fel
		int points = thisUser.calcPoints();
		lblPoints = new JLabel("" + points);
		lblPoints.setFont(new Font("Dialog", Font.BOLD, 16));
		lblPoints.setForeground(new Color(255, 140, 0));
		lblPoints.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPoints.setBounds(789, 27, 63, 27);
		contentPane.add(lblPoints);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(Color.LIGHT_GRAY);
		menuBar.setBounds(240, 0, 500, 41);
		contentPane.add(menuBar);
		
		JMenuItem mntmYourCart = new JMenuItem("Your cart");
		mntmYourCart.setVerticalAlignment(SwingConstants.BOTTOM);
		mntmYourCart.setBackground(Color.LIGHT_GRAY);
		menuBar.add(mntmYourCart);
		mntmYourCart.setForeground(Color.WHITE);
		mntmYourCart.setHorizontalAlignment(SwingConstants.CENTER);
		mntmYourCart.setFont(new Font("DejaVu Sans", Font.BOLD, 18));
		mntmYourCart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				panel.setVisible(true);
				panel_1.setVisible(false);
				panel_2.setVisible(false);
			}
		});
		
		JMenuItem mntmCategories = new JMenuItem("Categories");
		mntmCategories.setBackground(Color.LIGHT_GRAY);
		mntmCategories.setForeground(Color.WHITE);
		mntmCategories.setHorizontalAlignment(SwingConstants.CENTER);
		mntmCategories.setVerticalAlignment(SwingConstants.BOTTOM);
		mntmCategories.setFont(new Font("DejaVu Sans", Font.BOLD, 18));
		mntmCategories.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				panel_1.setVisible(true);
				panel.setVisible(false);
				panel_2.setVisible(false);
			}
		});
		menuBar.add(mntmCategories);
		
		JMenuItem mntmProducts = new JMenuItem("Products");
		mntmProducts.setBackground(Color.LIGHT_GRAY);
		menuBar.add(mntmProducts);
		mntmProducts.setForeground(Color.WHITE);
		mntmProducts.setFont(new Font("DejaVu Sans", Font.BOLD, 18));
		mntmProducts.setHorizontalAlignment(SwingConstants.CENTER);
		mntmProducts.setVerticalAlignment(SwingConstants.BOTTOM);
		mntmProducts.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				panel_2.setVisible(true);
				panel.setVisible(false);
				panel_1.setVisible(false);
			}
		});
		
	}
}