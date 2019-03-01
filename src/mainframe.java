import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

import java.sql.*;
import javax.swing.*;
public class mainframe {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainframe window = new mainframe();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	Connection conn=null;
	/**
	 * Create the application.
	 */
	public mainframe() {
		initialize();
		conn=sqliteconnection.dbconnector();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblWelcomeToCab = new JLabel("Welcome to Cab Share");
		lblWelcomeToCab.setBounds(144, 0, 168, 15);
		frame.getContentPane().add(lblWelcomeToCab);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				Login obi=new Login();
				obi.setVisible(true);
			}
		});
		btnLogin.setBounds(167, 139, 117, 25);
		frame.getContentPane().add(btnLogin);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				Register obi=new Register();
				obi.setVisible(true);
			}
		});
		btnRegister.setBounds(167, 176, 117, 25);
		frame.getContentPane().add(btnRegister);
		
		JButton btnNewButton = new JButton("?");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "register to get cashback on every trip!");
			}
		});
		btnNewButton.setBounds(200, 213, 52, 25);
		frame.getContentPane().add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 23, 426, 104);
		frame.getContentPane().add(scrollPane);
		
		JLabel lblNewLabel = new JLabel("Cab Share is a service provided to people for sharing cabs \nand reduce their expense of the journey accordingly.");
		scrollPane.setViewportView(lblNewLabel);
	}
}
