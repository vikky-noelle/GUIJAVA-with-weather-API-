import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class sharecab extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					sharecab frame = new sharecab();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	Connection conn=null;
	private JTable table;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	String user="null";
	private JTable table_1;
	private JTextField textField_5;
	/**
	 * Create the frame.
	 */
	public sharecab() {
		initialize();
	}
	public sharecab(String string) {
		user=string;
		initialize();
	}
	private void refresh(String a, String b) {
		try {
			String query="select * from cabs where startingpoint=? and endingpoint=?";
			PreparedStatement pst=conn.prepareStatement(query);
			pst.setString(1, a);
			pst.setString(2, b);
			ResultSet rs=pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
			rs.close();
			pst.close();
		}catch(Exception c) {
			JOptionPane.showMessageDialog(null, c);
		}
	}
	private void initialize() {
		conn=sqliteconnection.dbconnector();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 748, 482);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblEnterDestination = new JLabel("Enter Destination");
		lblEnterDestination.setBounds(143, 12, 146, 15);
		contentPane.add(lblEnterDestination);
		
		JLabel lblStartingPoint = new JLabel("Starting Point");
		lblStartingPoint.setBounds(86, 51, 120, 15);
		contentPane.add(lblStartingPoint);
		
		JLabel lblEndingPoint = new JLabel("Ending Point");
		lblEndingPoint.setBounds(96, 78, 96, 15);
		contentPane.add(lblEndingPoint);
		
		textField = new JTextField();
		textField.setBounds(228, 49, 114, 19);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(228, 76, 114, 19);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query="select * from cabs where startingpoint=? and endingpoint=?";
					PreparedStatement pst=conn.prepareStatement(query);
					pst.setString(1, textField.getText());
					pst.setString(2, textField_1.getText());
					ResultSet rs=pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
					rs.close();
					pst.close();
				}catch(Exception c) {
					JOptionPane.showMessageDialog(null, c);
				}
			}
		});
		btnSearch.setBounds(159, 105, 117, 25);
		contentPane.add(btnSearch);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(59, 143, 335, 115);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JLabel lblEnterTheId = new JLabel("enter the details");
		lblEnterTheId.setBounds(159, 275, 130, 15);
		contentPane.add(lblEnterTheId);
		
		textField_2 = new JTextField();
		textField_2.setBounds(162, 302, 114, 19);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField(user);
		textField_3.setEditable(false);
		textField_3.setBounds(162, 333, 114, 19);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(162, 364, 114, 19);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		
		JButton btnBookTheCab = new JButton("Book The Cab");
		btnBookTheCab.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int up=0;
					String query="insert into cabmembers(cabid, name, mobileno) values(?,?,?)";
					PreparedStatement pst=conn.prepareStatement(query);
					pst.setString(1, textField_2.getText());
					pst.setString(2, textField_3.getText());
					pst.setString(3, textField_4.getText());
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Cab Booked!");
					pst.close();
					query="select * from cabs where ID=?";
					pst=conn.prepareStatement(query);
					pst.setString(1, textField_2.getText());
					ResultSet rz=pst.executeQuery();
					while(rz.next()) {
						up=up+rz.getInt("availableseats");
					}
					JOptionPane.showMessageDialog(null, up);
					
					rz.close();
					pst.close();
					query="update cabs SET availableseats=? where ID=?";
					pst=conn.prepareStatement(query);
					pst.setString(2, textField_2.getText());
					pst.setInt(1, up-1);
					pst.executeUpdate();
					pst.close();
					refresh(textField.getText(), textField_1.getText());
				}catch(Exception c) {
					JOptionPane.showMessageDialog(null, c);
				}
			}
		});
		btnBookTheCab.setBounds(159, 403, 130, 25);
		contentPane.add(btnBookTheCab);
		
		JLabel lblCabId = new JLabel("Cab ID");
		lblCabId.setBounds(59, 304, 70, 15);
		contentPane.add(lblCabId);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(59, 335, 70, 15);
		contentPane.add(lblName);
		
		JLabel lblMobileNumber = new JLabel("mobile No");
		lblMobileNumber.setBounds(59, 366, 85, 15);
		contentPane.add(lblMobileNumber);
		
		JButton btnCancelBooking = new JButton("Cancel booking");
		btnCancelBooking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int up=0;
					String query="delete from cabmembers where cabid=?";
					PreparedStatement pst=conn.prepareStatement(query);
					pst.setString(1, textField_5.getText());
					pst.executeUpdate();
					pst.close();
					query="select * from cabs where ID=?";
					pst=conn.prepareStatement(query);
					pst.setString(1, textField_5.getText());
					ResultSet rz=pst.executeQuery();
					while(rz.next()) {
						up=up+rz.getInt("availableseats");
					}
					JOptionPane.showMessageDialog(null, up);
					
					rz.close();
					pst.close();
					query="update cabs SET availableseats=? where ID=?";
					pst=conn.prepareStatement(query);
					pst.setString(2, textField_5.getText());
					pst.setInt(1, up+1);
					pst.executeUpdate();
					pst.close();
					refresh(textField.getText(), textField_1.getText());
				}catch(Exception c) {
					JOptionPane.showMessageDialog(null, c);
				}
			}
		});
		btnCancelBooking.setBounds(521, 388, 150, 25);
		contentPane.add(btnCancelBooking);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(439, 143, 284, 115);
		contentPane.add(scrollPane_1);
		
		table_1 = new JTable();
		scrollPane_1.setViewportView(table_1);
		
		JLabel lblShowTheCabs = new JLabel("Show the cabs you are a part of");
		lblShowTheCabs.setBounds(484, 51, 239, 15);
		contentPane.add(lblShowTheCabs);
		
		JButton btnShow = new JButton("Show");
		btnShow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query="select * from cabmembers where name=?";
					PreparedStatement pst=conn.prepareStatement(query);
					pst.setString(1, user);
					ResultSet rs=pst.executeQuery();
					table_1.setModel(DbUtils.resultSetToTableModel(rs));
					rs.close();
					pst.close();
				}catch(Exception c) {
					JOptionPane.showMessageDialog(null, c);
				}
			}
		});
		btnShow.setBounds(534, 84, 117, 25);
		contentPane.add(btnShow);
		
		textField_5 = new JTextField();
		textField_5.setBounds(534, 333, 114, 19);
		contentPane.add(textField_5);
		textField_5.setColumns(10);
		
		JLabel lblEnterTheCabid = new JLabel("Enter the cabid to be deleted");
		lblEnterTheCabid.setBounds(492, 288, 231, 15);
		contentPane.add(lblEnterTheCabid);
		
		JButton btnGoToThe = new JButton("GO TO THE MIDDLE FRAME");
		btnGoToThe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				middleframe ob=new middleframe();
				ob.setVisible(true);
			}
		});
		btnGoToThe.setBounds(307, 7, 239, 25);
		contentPane.add(btnGoToThe);
	}
}
