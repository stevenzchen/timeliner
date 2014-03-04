package timeliner;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JEditorPane;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JButton;

public class EditGUI {

	private JFrame frmTimeliner;
	private JTextField txtName;
	private JTextField txtTime;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditGUI window = new EditGUI();
					window.frmTimeliner.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public EditGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmTimeliner = new JFrame();
		frmTimeliner.setTitle("Timeliner");
		frmTimeliner.setBounds(100, 100, 500, 400);
		frmTimeliner.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTimeliner.getContentPane().setLayout(null);
		
		JLabel lblEventEditor = new JLabel("Event Editor");
		lblEventEditor.setBounds(6, 25, 99, 16);
		frmTimeliner.getContentPane().add(lblEventEditor);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(6, 53, 61, 16);
		frmTimeliner.getContentPane().add(lblName);
		
		JLabel lblTime = new JLabel("Time");
		lblTime.setBounds(6, 81, 61, 16);
		frmTimeliner.getContentPane().add(lblTime);
		
		JLabel lblDescription = new JLabel("Description");
		lblDescription.setBounds(6, 109, 78, 16);
		frmTimeliner.getContentPane().add(lblDescription);
		
		txtName = new JTextField();
		txtName.setBounds(90, 47, 134, 28);
		frmTimeliner.getContentPane().add(txtName);
		txtName.setColumns(10);
		
		txtTime = new JTextField();
		txtTime.setBounds(90, 75, 134, 28);
		frmTimeliner.getContentPane().add(txtTime);
		txtTime.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(90, 109, 134, 90);
		frmTimeliner.getContentPane().add(scrollPane);
		
		JEditorPane editorPane = new JEditorPane();
		scrollPane.setColumnHeaderView(editorPane);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.BLACK);
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(236, 6, 12, 366);
		frmTimeliner.getContentPane().add(separator);
		
		JButton btnAddEvent = new JButton("Add Event");
		btnAddEvent.setBounds(29, 211, 158, 29);
		frmTimeliner.getContentPane().add(btnAddEvent);
		
		JButton btnEditAnEvent = new JButton("Edit Existing Event\n");
		btnEditAnEvent.setBounds(29, 250, 158, 29);
		frmTimeliner.getContentPane().add(btnEditAnEvent);
		
		JButton btnNewButton = new JButton("Save Current Timeline");
		btnNewButton.setBounds(26, 291, 181, 29);
		frmTimeliner.getContentPane().add(btnNewButton);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(260, 37, 211, 176);
		frmTimeliner.getContentPane().add(scrollPane_1);
		
		JLabel lblStatusConsole = new JLabel("Status Console");
		lblStatusConsole.setBounds(260, 9, 99, 16);
		frmTimeliner.getContentPane().add(lblStatusConsole);
		
		JButton btnNewButton_1 = new JButton("Load Existing Timeline\n");
		btnNewButton_1.setBounds(26, 332, 181, 29);
		frmTimeliner.getContentPane().add(btnNewButton_1);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(Color.BLACK);
		separator_1.setBounds(6, 281, 227, 12);
		frmTimeliner.getContentPane().add(separator_1);
	}
}
