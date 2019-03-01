import java.awt.BorderLayout;
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
import java.sql.*;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class bookcab extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					bookcab frame = new bookcab();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	Connection conn=null;
	String user;
	private JTable table;
	private JTextField textField_4;
	private JTextField textField_5;
	/**
	 * Create the frame.
	 */
	public bookcab() {
		initialize();
	}
	public bookcab(String string) {
		user=string;
		initialize();
	}
	private void refresh() {
		try {
			String query="select * from cabs where username=?";
			PreparedStatement pst=conn.prepareStatement(query);
			pst.setString(1, user);
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
		setBounds(100, 100, 450, 503);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblDestination = new JLabel("Destination");
		lblDestination.setBounds(70, 72, 83, 15);
		contentPane.add(lblDestination);
		
		JLabel lblStartingPoint = new JLabel("Starting Point");
		lblStartingPoint.setBounds(56, 99, 112, 15);
		contentPane.add(lblStartingPoint);
		
		JLabel lblEndPoint = new JLabel("End point");
		lblEndPoint.setBounds(70, 126, 83, 15);
		contentPane.add(lblEndPoint);
		
		JLabel lblSeatingSpace = new JLabel("Seating Space");
		lblSeatingSpace.setBounds(56, 45, 112, 15);
		contentPane.add(lblSeatingSpace);
		
		JLabel lblTiming = new JLabel("Timing");
		lblTiming.setBounds(84, 153, 70, 15);
		contentPane.add(lblTiming);
		
		textField = new JTextField();
		textField.setBounds(260, 43, 114, 19);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(260, 97, 114, 19);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(260, 122, 114, 19);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(260, 151, 114, 19);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		JButton btnBookTheCab = new JButton("Book The Cab For Sharing");
		btnBookTheCab.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query="insert into cabs(seatingspace, startingpoint, endingpoint, timing, availableseats, username) values(?,?,?,?,?,?)";
					PreparedStatement pst=conn.prepareStatement(query);
					pst.setString(1, textField.getText());
					pst.setString(2, textField_1.getText());
					pst.setString(3, textField_2.getText());
					pst.setString(4, textField_3.getText());
					pst.setInt(5, Integer.parseInt(textField.getText())-1);
					pst.setString(6, user);
					pst.executeUpdate();
					refresh();
					JOptionPane.showMessageDialog(null, "Cab Booked!");
					pst.close();
				}catch(Exception c) {
					JOptionPane.showMessageDialog(null, c);
				}
			}
		});
		btnBookTheCab.setBounds(70, 182, 304, 25);
		contentPane.add(btnBookTheCab);
		
		JLabel lblBooking = new JLabel("BOOKING");
		lblBooking.setBounds(183, 0, 70, 15);
		contentPane.add(lblBooking);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(55, 256, 336, 103);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		textField_4 = new JTextField();
		textField_4.setBounds(54, 382, 114, 19);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		
		JButton btnCancelBooking = new JButton("Cancel Booking");
		btnCancelBooking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query="delete from cabs where ID=?";
					PreparedStatement pst=conn.prepareStatement(query);
					pst.setString(1, textField_4.getText());
					pst.executeUpdate();
					pst.close();
					refresh();
					JOptionPane.showMessageDialog(null, "booking cancelled!");
				}catch(Exception c) {
					JOptionPane.showMessageDialog(null, c);
				}
			}
		});
		btnCancelBooking.setBounds(224, 379, 167, 25);
		contentPane.add(btnCancelBooking);
		
		textField_5 = new JTextField(user);
		textField_5.setEditable(false);
		textField_5.setBounds(260, 70, 114, 19);
		contentPane.add(textField_5);
		textField_5.setColumns(10);
		
		JButton btnShowRecords = new JButton("Show Records");
		btnShowRecords.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query="select * from cabs where username=?";
					PreparedStatement pst=conn.prepareStatement(query);
					pst.setString(1, user);
					ResultSet rs=pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
					rs.close();
					pst.close();
				}catch(Exception c) {
					JOptionPane.showMessageDialog(null, c);
				}
			}
		});
		btnShowRecords.setBounds(163, 219, 134, 25);
		contentPane.add(btnShowRecords);
		
		JButton btnGoToThe = new JButton("GO TO THE MIDDLEFRAME");
		btnGoToThe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				middleframe ob=new middleframe();
				ob.setVisible(true);
			}
		});
		btnGoToThe.setBounds(122, 423, 210, 25);
		contentPane.add(btnGoToThe);
	}
}
