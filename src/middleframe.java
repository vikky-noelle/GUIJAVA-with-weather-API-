import java.awt.BorderLayout;
import java.sql.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

public class middleframe extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					middleframe frame = new middleframe();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	String user="null";
	Connection conn=null;
	private JTextField textField_3;
	private JTextField textField_4;
	/**
	 * Create the frame.
	 * @param  
	 */
	public middleframe() {
		initialize();
	}
	
	public middleframe(String string) {
		user=string;
		initialize();
	}
	
	private void initialize() {
		conn=sqliteconnection.dbconnector();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 379);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAvailableCabs = new JLabel("Available cabs");
		lblAvailableCabs.setBounds(96, 58, 118, 15);
		contentPane.add(lblAvailableCabs);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(232, 56, 114, 19);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnCheckAvailibilityOf = new JButton("Check Availibility of a Cab");
		btnCheckAvailibilityOf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int count=0;
					String query="select * from cabs";
					PreparedStatement pst=conn.prepareStatement(query);
					ResultSet rs=pst.executeQuery();
					while(rs.next()) {
						count=count+1;
					}
					textField.setText(Integer.toString(count));
					rs.close();
					pst.close();
				}catch(Exception c) {
					JOptionPane.showMessageDialog(null, c);
				}
			}
		});
		btnCheckAvailibilityOf.setBounds(96, 98, 250, 25);
		contentPane.add(btnCheckAvailibilityOf);
		
		JButton btnBookACab = new JButton("Book A Cab");
		btnBookACab.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				bookcab ob=new bookcab(user);
				ob.setVisible(true);
			}
		});
		btnBookACab.setBounds(86, 190, 128, 25);
		contentPane.add(btnBookACab);
		
		JButton btnShareACab = new JButton("Share A Cab");
		btnShareACab.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				sharecab ob=new sharecab(user);
				ob.setVisible(true);
			}
		});
		btnShareACab.setBounds(231, 190, 128, 25);
		contentPane.add(btnShareACab);
		
		JButton btnNewButton = new JButton("?");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "share the already available cab, people who offer cabs for sharing are listed here.");
			}
		});
		btnNewButton.setBounds(371, 190, 50, 25);
		contentPane.add(btnNewButton);
		
		JButton button = new JButton("?");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "This option is to book a cab from scratch to offer your cab for sharing to others.");
			}
		});
		button.setBounds(24, 190, 50, 25);
		contentPane.add(button);
		
		JButton button_1 = new JButton("?");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Check avialibilty tells you about the number of cabs available for sharing");
			}
		});
		button_1.setBounds(166, 135, 117, 25);
		contentPane.add(button_1);
		
		JButton btnCheckTheWeather = new JButton("Check the weather");
		btnCheckTheWeather.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				APIreader bam = new APIreader();
				String ax = bam.fun(textField_3.getText(), textField_4.getText());
				JOptionPane.showMessageDialog(null, ax);
			}
		});
		btnCheckTheWeather.setBounds(138, 306, 174, 25);
		contentPane.add(btnCheckTheWeather);
		
		textField_3 = new JTextField();
		textField_3.setBounds(232, 244, 114, 19);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblEe = new JLabel("enter the city");
		lblEe.setBounds(86, 246, 118, 15);
		contentPane.add(lblEe);
		
		JLabel lblEnterTheCountry = new JLabel("enter the country");
		lblEnterTheCountry.setBounds(86, 279, 138, 15);
		contentPane.add(lblEnterTheCountry);
		
		textField_4 = new JTextField();
		textField_4.setBounds(232, 275, 114, 19);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		
		JButton btnGoToLogin = new JButton("GO TO LOGIN PAGE");
		btnGoToLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Login ob=new Login();
				ob.setVisible(true);
			}
		});
		btnGoToLogin.setBounds(127, 12, 193, 25);
		contentPane.add(btnGoToLogin);
	}
}
