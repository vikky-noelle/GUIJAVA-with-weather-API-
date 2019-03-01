import java.awt.BorderLayout;
import java.sql.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Register extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Register frame = new Register();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	Connection conn=null;
	/**
	 * Create the frame.
	 */
	public Register() {
		conn=sqliteconnection.dbconnector();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblRegister = new JLabel("Register");
		lblRegister.setBounds(178, 0, 70, 15);
		contentPane.add(lblRegister);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(70, 53, 82, 15);
		contentPane.add(lblUsername);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(82, 97, 70, 15);
		contentPane.add(lblEmail);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(70, 143, 70, 15);
		contentPane.add(lblPassword);
		
		textField = new JTextField();
		textField.setBounds(240, 51, 114, 19);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(240, 95, 114, 19);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(240, 141, 114, 19);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query="insert into users(username, email, password) values(?,?,?)";
					PreparedStatement pst=conn.prepareStatement(query);
					pst.setString(1, textField.getText());
					pst.setString(2, textField_1.getText());
					pst.setString(3, textField_2.getText());
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "User Registered! Redirecting to the login page");
					pst.close();
					dispose();
					Login ob=new Login();
					ob.setVisible(true);
				}catch(Exception c) {
					JOptionPane.showMessageDialog(null, c);
				}
			}
		});
		btnRegister.setBounds(158, 196, 117, 25);
		contentPane.add(btnRegister);
		
		JButton btnGoToThe = new JButton("GO TO THE LOGIN PAGE");
		btnGoToThe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Login ob=new Login();
				ob.setVisible(true);
			}
		});
		btnGoToThe.setBounds(120, 237, 211, 25);
		contentPane.add(btnGoToThe);
	}
}
