import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		conn=sqliteconnection.dbconnector();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setBounds(195, 12, 70, 15);
		contentPane.add(lblLogin);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(80, 69, 81, 15);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(80, 129, 70, 15);
		contentPane.add(lblPassword);
		
		textField = new JTextField();
		textField.setBounds(233, 67, 135, 19);
		contentPane.add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(233, 127, 135, 19);
		contentPane.add(passwordField);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query="select * from users where username=? and password=?";
					PreparedStatement pst=conn.prepareStatement(query);
					pst.setString(1, textField.getText());
					pst.setString(2, passwordField.getText());
					String pass=textField.getText();
					ResultSet rs=pst.executeQuery();
					int count=0;
					while(rs.next()) {
						count++;
						
					}
					if(count==1) {
						JOptionPane.showMessageDialog(null, "username and password is correct, Redirecting to the next window!");
						dispose();
						middleframe ob=new middleframe(pass);
						ob.setVisible(true);
					}  
					else {
					JOptionPane.showMessageDialog(null, "username or password is incorrect");
					}
					rs.close();
					pst.close();
				}catch(Exception c) {
					JOptionPane.showMessageDialog(null, c);
				}
			}
		});
		btnLogin.setBounds(156, 195, 117, 25);
		contentPane.add(btnLogin);
	}
}
