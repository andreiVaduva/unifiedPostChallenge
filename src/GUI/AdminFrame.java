package GUI;
import javax.imageio.ImageIO;
import javax.swing.*;

import Backend.Administrator;
import Backend.Category;
import Backend.Group;
import Backend.Product;
import Backend.User;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class AdminFrame extends JPanel {
	private static Administrator user;
	private JTextField firstName;
	private JTextField lastName;
	private JTextField username;
	private JTextField password;
	//private JTextField groupName4User;
	private JComboBox groupName4User;
	private JTextField userTotalPoints;
	private DefaultListModel<User> listUserModel;
	private JList listJListUser;
	private JScrollPane listUsers;
	private JPanel panelUser;
	
	private DefaultListModel<Group> listGroupModel;
	private JList listJListGroup;
	private JScrollPane listGroups;
	
	private DefaultListModel<Category> listCategoryModel;
	private JList listJListCategory;
	private JScrollPane listCategory;
	
	private DefaultListModel<Product> listProductModel;
	private JList listJListProduct;
	private JScrollPane listProduct;
	
	private JButton btnSubmit,btnSubmit1;
	private JPanel productPanel;
	private JButton btnSubmit_1;
	
	private JTextField textField_1;
	private JTextField textField_2;
	private JComboBox comboBox;
	public AdminFrame(Administrator u) {
		super(new GridLayout(1, 2));
		this.user = u;
		JTabbedPane tabbedPane = new JTabbedPane();

		JComponent panel1 = makeTextPanel("Panel #1");
		JComponent panel2 = makeTextPanel("Panel #2");
		JComponent panel3 = makeTextPanel("Panel #3");
		JComponent panel4 = makeTextPanel("Panel #4");

		JPanel panelOneList = new JPanel();
		panelOneList.setBackground(Color.LIGHT_GRAY);
		JPanel panelOneBut = new JPanel();
		panelOneBut.setBackground(Color.LIGHT_GRAY);
		JPanel panelTwoList = new JPanel();
		panelTwoList.setBackground(Color.LIGHT_GRAY);
		JPanel panelTwoBut = new JPanel();
		panelTwoBut.setBackground(Color.LIGHT_GRAY);
		JPanel panelThreeList = new JPanel();
		panelThreeList.setBackground(Color.LIGHT_GRAY);
		JPanel panelThreeBut = new JPanel();
		panelThreeBut.setBackground(Color.LIGHT_GRAY);
		JPanel panelFourList = new JPanel();
		panelFourList.setBackground(Color.LIGHT_GRAY);
		JPanel panelFourBut = new JPanel();
		panelFourBut.setBackground(Color.LIGHT_GRAY);

		panelOneList.setLayout(null);
		panelOneBut.setLayout(null);
		panelTwoList.setLayout(null);
		panelTwoBut.setLayout(null);
		panelThreeList.setLayout(null);
		panelThreeBut.setLayout(null);
		panelFourList.setLayout(null);
		panelFourBut.setLayout(null);

		JButton btnNewGroup = new JButton("Add new group");

		panel1.add(panelOneList);
		
		listGroupModel = user.listGroups();
		listJListGroup = new JList(listGroupModel);
		listGroups = new JScrollPane(listJListGroup);
		listGroups.setBounds(12, 12, 292, 421);
		
		panelOneList.add(listGroups);
		panel1.add(panelOneBut);

		btnNewGroup.setBounds(58, 34, 198, 42);
		btnNewGroup.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub

				JTextField groupName = new JTextField(20);
				JTextField groupPoints = new JTextField(7);
				JPanel newGroupPane = new JPanel();
				
				newGroupPane.add(new JLabel("Group name:"));
				newGroupPane.add(groupName);
				newGroupPane.add(Box.createHorizontalStrut(15));
				newGroupPane.add(new JLabel("Points"));
				newGroupPane.add(groupPoints);
				// TODO: save in array
				// TODO: add suplimentar validations
				int result = JOptionPane.showConfirmDialog(null, newGroupPane,
						"Please enter the new group",
						JOptionPane.OK_CANCEL_OPTION);
				if (groupName.getText() != "") {
					if (groupPoints.getText() != "" && isNumeric(groupPoints.getText())) {
						if (result == JOptionPane.OK_OPTION) {
							String gname = groupName.getText();
							int gpoints = Integer.parseInt(groupName.getText());
							if(gname.compareTo("") != 0 && groupPoints.getText().compareTo("") != 0)
								user.addGroup(gname, gpoints);
						}		
					}
				}
			}
		});

		JButton btnDelGroup = new JButton("Delete group");
		btnDelGroup.setBounds(58, 88, 200, 42);

		btnDelGroup.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Group g = (Group)listJListGroup.getSelectedValue();
				user.removeGroup(g.name);
				panelUser.setVisible(false);
			}
		});

		JButton btnModifyGroup = new JButton("Modify group");
		btnModifyGroup.setBounds(58, 145, 200, 42);
		btnModifyGroup.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Group toChange = (Group)listJListGroup.getSelectedValue();
				
				JTextField groupName = new JTextField(20);
				groupName.setText("name_from_DB");
				JTextField groupPoints = new JTextField(7);
				groupPoints.setText("points_from_DB");
				JPanel newGroupPane = new JPanel();
				
				newGroupPane.add(new JLabel("Group name:"));
				newGroupPane.add(groupName);
				newGroupPane.add(Box.createHorizontalStrut(15));
				newGroupPane.add(new JLabel("Points"));
				newGroupPane.add(groupPoints);
				// TODO: save in array
				// TODO: add suplimentar validations
				int result = JOptionPane.showConfirmDialog(null, newGroupPane,
						"Now you can modify group specifications",
						JOptionPane.OK_CANCEL_OPTION);
				if (result == JOptionPane.OK_OPTION) {
					String gname = groupName.getText();
					if(groupName.getText().compareTo("") != 0) {
						int gpoints = Integer.parseInt(groupName.getText());
						user.modifyGrouppoints(toChange.points, gpoints);
					}
					
					if(gname.compareTo("") != 0)
						user.modifyGroupname(toChange.name, gname);
					
				}
			}
		});

		panelOneBut.add(btnNewGroup);
		panelOneBut.add(btnDelGroup);
		panelOneBut.add(btnModifyGroup);

		JButton btnLogout = new JButton("Logout");
		btnLogout.setBounds(99, 243, 117, 25);
		panelOneBut.add(btnLogout);
		
		btnLogout.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setVisible(false);
				LoginFrame lf = new LoginFrame();
				lf.setVisible(true);
			}
		});
		
		tabbedPane.addTab("GROUP", panel1);
		tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);

		panel2.add(panelTwoList);
		
		listUserModel = user.listUsers();
		listJListUser = new JList(listUserModel);
		listUsers = new JScrollPane(listJListUser);
		listUsers.setBounds(12, 12, 292, 421);
		panelTwoList.add(listUsers);
		panelUser = new JPanel();
		panelUser.setBounds(12, 12, 320, 426);
		panelUser.setVisible(false);
		panelTwoList.add(panelUser);
		panelUser.setLayout(null);

		JLabel lblFirstName = new JLabel("First name");
		lblFirstName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFirstName.setBounds(29, 80, 92, 17);
		panelUser.add(lblFirstName);

		firstName = new JTextField();
		firstName.setBounds(139, 75, 150, 28);
		panelUser.add(firstName);
		firstName.setColumns(10);

		JLabel lblLastName = new JLabel("Last name");
		lblLastName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLastName.setBounds(11, 114, 110, 28);
		panelUser.add(lblLastName);

		lastName = new JTextField();
		lastName.setBounds(139, 115, 150, 28);
		panelUser.add(lastName);
		lastName.setColumns(10);

		JLabel lblUsername = new JLabel("Username");
		lblUsername.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUsername.setBounds(14, 154, 107, 28);
		panelUser.add(lblUsername);

		username = new JTextField();
		username.setBounds(139, 155, 150, 28);
		panelUser.add(username);
		username.setColumns(10);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPassword.setBounds(29, 197, 92, 22);
		panelUser.add(lblPassword);

		password = new JTextField();
		password.setBounds(139, 195, 150, 28);
		panelUser.add(password);
		password.setColumns(10);

		JLabel lblGrupName = new JLabel("Group name");
		lblGrupName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblGrupName.setBounds(29, 11, 92, 28);
		panelUser.add(lblGrupName);

		groupName4User = new JComboBox(user.listGroups().toArray());
		groupName4User.setBounds(139, 12, 150, 28);
		panelUser.add(groupName4User);
	
		JRadioButton rdbtnYes = new JRadioButton("YES");
		rdbtnYes.setBounds(139, 255, 149, 23);
		panelUser.add(rdbtnYes);
		
		JRadioButton rdbtnNo = new JRadioButton("NO");
		rdbtnNo.setBounds(139, 282, 149, 23);
		panelUser.add(rdbtnNo);
		ButtonGroup tz = new ButtonGroup();
		tz.add(rdbtnYes);
		tz.add(rdbtnNo);
		
		JLabel lblIsAdmin = new JLabel("Is admin:");
		lblIsAdmin.setBounds(51, 258, 70, 17);
		panelUser.add(lblIsAdmin);
		 
		panel2.add(panelTwoBut);

		btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(152, 374, 117, 25);
		btnSubmit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				//panelUser.setVisible(false);
				//listUsers.setVisible(true);
				Group gUser = (Group)groupName4User.getSelectedItem();
				if(username.getText().compareTo("") == 0 || password.getText().compareTo("") == 0 || firstName.getText().compareTo("") == 0 || lastName.getText().compareTo("") == 0) {
					JOptionPane.showMessageDialog(listUsers, "You have to complete all the fields!");
					System.out.println("nop");
				}
				else 
					user.addUser(username.getText(), password.getText(), firstName.getText(), lastName.getText(), gUser.id,0);
			}
		});
		panelUser.add(btnSubmit);
		
		
		JButton btnAddNewUser = new JButton("Add new user");
		btnAddNewUser.setBounds(58, 34, 198, 42);
		btnAddNewUser.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				listUsers.setVisible(false);
				panelUser.setVisible(true);
				btnSubmit.setVisible(true);
				btnSubmit1.setVisible(false);
			}
		});

		panelTwoBut.add(btnAddNewUser);

		JButton btnDeleteUser = new JButton("Delete user");
		btnDeleteUser.setBounds(58, 88, 198, 42);
		btnDeleteUser.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				//listUsers.setVisible(true);
				User u = (User)listJListUser.getSelectedValue();
				user.removeUser(u.username);
				panelUser.setVisible(false);
			}
		});
		panelTwoBut.add(btnDeleteUser);

		JButton btnModifyUser = new JButton("Modify user");
		btnModifyUser.setBounds(58, 145, 198, 42);
		btnModifyUser.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				listUsers.setVisible(false);
				panelUser.setVisible(true);
				btnSubmit1.setVisible(true);
				btnSubmit.setVisible(false);
			}
		});
		
		
		panelTwoBut.add(btnModifyUser);
		
		btnSubmit1 = new JButton("Submit");
		btnSubmit1.setBounds(152, 374, 117, 25);
		btnSubmit1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				panelUser.setVisible(false);
				listUsers.setVisible(true);
				User u = (User)listJListUser.getSelectedValue();
				Group gUser = (Group)groupName4User.getSelectedItem();
				if(username.getText().compareTo("") != 0)
					user.modifyUser("Username", u.username, username.getText());
				if(password.getText().compareTo("") != 0)
					user.modifyUser("Password", u.passwd, password.getText());
				if(firstName.getText().compareTo("") != 0)
					user.modifyUser("Firstname", u.firstname, firstName.getText());
				if(lastName.getText().compareTo("") != 0)
					user.modifyUser("Lastname", u.lastname, lastName.getText());
				user.modifyGroupUser(u.groupId,gUser.id);
			}
		});
		panelUser.add(btnSubmit1);
		
		JButton button = new JButton("Logout");
		button.setBounds(99, 243, 117, 25);
		panelTwoBut.add(button);
		tabbedPane.addTab("USER", panel2);
		tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);

		JButton btnNewCategory = new JButton("Add new category");
		btnNewCategory.setLocation(58, 34);
		btnNewCategory.setSize(198, 42);
		btnNewCategory.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String s = (String) JOptionPane.showInputDialog(new Frame(),
						"Add new category", "Category",
						JOptionPane.PLAIN_MESSAGE, null, null, null);
				if(s.compareTo("") != 0) {
					user.addCategory(s);
				}
			}
		});
		JButton btnDelCategory = new JButton("Delete category");
		btnDelCategory.setSize(198, 42);
		btnDelCategory.setLocation(58, 88);
		btnDelCategory.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Category c = (Category)listJListCategory.getSelectedValue();
				user.removeCategory(c.name);
			}
		});
		JButton btnModifyCategory = new JButton("Modify category");
		btnModifyCategory.setSize(198, 42);
		btnModifyCategory.setLocation(58, 145);
		btnModifyCategory.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String fromDB = "you have to read from DB";
				String s = (String) JOptionPane.showInputDialog(new Frame(),
						"Add new category", "Customized Dialog",
						JOptionPane.PLAIN_MESSAGE, null, null, fromDB);
			}
		});

		panel3.add(panelThreeList);

		panelThreeBut.add(btnNewCategory);
		panelThreeBut.add(btnDelCategory);
		panelThreeBut.add(btnModifyCategory);

		listCategoryModel = user.listCategory();
		listJListCategory = new JList(listCategoryModel);
		listCategory = new JScrollPane(listJListCategory);
		listCategory.setBounds(12, 12, 292, 421);
		panelThreeList.add(listCategory);
		panel3.add(panelThreeBut);

		JButton button_1 = new JButton("Logout");
		button_1.setBounds(99, 243, 117, 25);
		panelThreeBut.add(button_1);
		tabbedPane.addTab("CATEGORY", panel3);
		tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);

		panel4.add(panelFourList);

		listProductModel = user.listProduct();
		listJListProduct = new JList(listProductModel);
		listProduct = new JScrollPane(listJListProduct);
		
		listProduct.setBounds(12, 12, 292, 421);
		panelFourList.add(listProduct);

		JButton btnNewProduct = new JButton("Add new product");
		btnNewProduct.setSize(198, 42);
		btnNewProduct.setLocation(58, 34);
		panelFourBut.add(btnNewProduct);
		
		productPanel = new JPanel();
		productPanel.setBounds(12, 12, 292, 421);
		panelFourList.add(productPanel);
		productPanel.setLayout(null);
		
		JLabel lblCategory = new JLabel("Category");
		lblCategory.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCategory.setBounds(29, 28, 92, 17);
		productPanel.add(lblCategory);
		
		JLabel lblProduct = new JLabel("Product");
		lblProduct.setHorizontalAlignment(SwingConstants.RIGHT);
		lblProduct.setBounds(11, 81, 110, 28);
		productPanel.add(lblProduct);
		
		textField_1 = new JTextField();
		textField_1.setBounds(139, 82, 150, 28);
		productPanel.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblCost = new JLabel("Cost");
		lblCost.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCost.setBounds(14, 121, 107, 28);
		productPanel.add(lblCost);
		
		textField_2 = new JTextField();
		textField_2.setBounds(139, 122, 150, 28);
		productPanel.add(textField_2);
		textField_2.setColumns(10);
		
		comboBox = new JComboBox(user.listCategory().toArray());
		comboBox.setBounds(139, 22, 150, 28);
		productPanel.add(comboBox);
		
		JButton btnSubmit_1 = new JButton("Submit");
		btnSubmit_1.setBounds(163, 232, 117, 25);
		productPanel.add(btnSubmit_1);
		
		btnSubmit_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int comboInt = ((Category)comboBox.getSelectedItem()).id;
				//Product p = new Product(comboInt,Integer.parseInt(textField_1.getText()),textField_2.getText());
				if(textField_1.getText().compareTo("")!= 0 && textField_2.getText().compareTo("") != 0)
					user.addProduct(comboInt, textField_1.getText(), Integer.parseInt(textField_2.getText()));
				listProduct.setVisible(true);
				productPanel.setVisible(false);
			}
		});
		
		btnNewProduct.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				productPanel.setVisible(true);
				listProduct.setVisible(false);
			}
		});
		JButton btnDelProduct = new JButton("Delete product");
		btnDelProduct.setSize(198, 42);
		btnDelProduct.setLocation(58, 88);
		panelFourBut.add(btnDelProduct);
		btnDelProduct.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				productPanel.setVisible(false);
				listProduct.setVisible(true);
			}
		});
		JButton btnModifyProduct = new JButton("Modify product");
		
		btnModifyProduct.setSize(198, 42);
		btnModifyProduct.setLocation(58, 145);
		panelFourBut.add(btnModifyProduct);
		btnModifyProduct.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				productPanel.setVisible(false);
				listProduct.setVisible(true);
				Product p = (Product)listJListProduct.getSelectedValue();
				JTextField productName = new JTextField(20);
				
				JTextField productCost = new JTextField(7);
				JPanel newProductPane = new JPanel();
				newProductPane.add(Box.createHorizontalStrut(15));
				newProductPane.add(new JLabel("Points"));
				newProductPane.add(productCost);
				// TODO: save in array
				// TODO: add suplimentar validations
				int result = JOptionPane.showConfirmDialog(null,
						newProductPane,
						"Now you can modify the product specifications",
						JOptionPane.OK_CANCEL_OPTION);
				if (result == JOptionPane.OK_OPTION) {
					if(productCost.getText().compareTo("") != 0)
						user.modifyProduct(Integer.parseInt(productCost.getText()),p.cost);
				}
			}
		});
		panel4.add(panelFourBut);

		JButton button_2 = new JButton("Logout");
		button_2.setBounds(99, 243, 117, 25);
		panelFourBut.add(button_2);
		tabbedPane.addTab("PRODUCT", panel4);
		tabbedPane.setMnemonicAt(3, KeyEvent.VK_4);

		// Add the tabbed pane to this panel.
		add(tabbedPane);

		// The following line enables to use scrolling tabs.
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		productPanel.setVisible(false);
		listProduct.setVisible(true);
	
		
		BufferedImage img = null;
		try {
		    img = ImageIO.read(new File("res/logo_unifiedpost.png"));
		} catch (IOException e) {
		    e.printStackTrace();
		}
		Image simg = img.getScaledInstance(195, 33, Image.SCALE_SMOOTH);
		JLabel image_label = new JLabel(new ImageIcon(simg));
		image_label.setLocation(58, 312);
		image_label.setSize(195, 33);
		panelOneBut.add(image_label);
		
		
		BufferedImage img_1 = null;
		try {
		    img_1 = ImageIO.read(new File("res/logo_unifiedpost.png"));
		} catch (IOException e) {
		    e.printStackTrace();
		}
		Image simg_1 = img_1.getScaledInstance(195, 33, Image.SCALE_SMOOTH);
		JLabel image_label_1 = new JLabel(new ImageIcon(simg_1));
		image_label_1.setLocation(58, 312);
		image_label_1.setSize(195, 33);
		panelTwoBut.add(image_label_1);
		
		
		BufferedImage img_2 = null;
		try {
		    img_2 = ImageIO.read(new File("res/logo_unifiedpost.png"));
		} catch (IOException e) {
		    e.printStackTrace();
		}
		Image simg_2 = img_2.getScaledInstance(195, 33, Image.SCALE_SMOOTH);
		JLabel image_label_2 = new JLabel(new ImageIcon(simg_2));
		image_label_2.setLocation(58, 312);
		image_label_2.setSize(195, 33);
		panelThreeBut.add(image_label_2);
		
		
		BufferedImage img_3 = null;
		try {
		    img_3 = ImageIO.read(new File("res/logo_unifiedpost.png"));
		} catch (IOException e) {
		    e.printStackTrace();
		}
		Image simg_3 = img_3.getScaledInstance(195, 33, Image.SCALE_SMOOTH);
		JLabel image_label_3 = new JLabel(new ImageIcon(simg_3));
		image_label_3.setLocation(58, 312);
		image_label_3.setSize(195, 33);
		panelFourBut.add(image_label_3);
	}

	
	
	
	
	protected JComponent makeTextPanel(String text) {
		JPanel panel = new JPanel(false);
		panel.setLayout(new GridLayout(1, 1));
		return panel;
	}

	private static void createAndShowGUI() {
		// Create and set up the window.
		JFrame frame = new JFrame("TabbedPaneDemo");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Add content to the window.
		frame.getContentPane().add(new AdminFrame(user), BorderLayout.CENTER);

		// Display the window.
		frame.pack();
		frame.setVisible(true);
	}

	//TODO: cred ca tre' pusa la numeric
	public static boolean isNumeric(String str) {  
	  try {  
	    int d = Integer.parseInt(str);  
	  }  
	  catch(NumberFormatException nfe) {  
	    return false;  
	  }  
	  return true;
	}
	
	/*
	 * //TODO: center point to center public static void center(Window w) {
	 * Dimension ws = w.getSize(); Dimension ss =
	 * Toolkit.getDefaultToolkit().getScreenSize(); int newX = (ss.width -
	 * ws.width) / 2; int newY = (ss.height - ws.height) / 2;
	 * w.setLocation(newX, newY); }
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrame frame = new JFrame("Administration session");
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame.setPreferredSize(new Dimension(620, 500));
					frame.getContentPane().add(new AdminFrame(user),
							BorderLayout.CENTER);
					// center(frame);

					frame.pack();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/
}