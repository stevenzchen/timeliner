package timeliner;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import org.joda.time.DateTime;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EditGUI {

	public JFrame frmTimeliner;
	public JTextField txtName;
	public JTextField txtTime;
	public JEditorPane txtDescription;
	public JTextArea statusConsole;
	public Timeline timeline = new Timeline("Timeline");
	private JTextField txtTimeline;

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

	
	public Timeline getTimeline()
	{
		return timeline;
	}
	public void setTimeline(Timeline t)
	{
		timeline = t;
	}
	
	public JFrame getFrmTimeliner() {
		return frmTimeliner;
	}

	public void setFrmTimeliner(JFrame frmTimeliner) {
		this.frmTimeliner = frmTimeliner;
	}

	public JTextField getTxtName() {
		return txtName;
	}

	public void setTxtName(JTextField txtName) {
		this.txtName = txtName;
	}

	public JTextField getTxtTime() {
		return txtTime;
	}

	public void setTxtTime(JTextField txtTime) {
		this.txtTime = txtTime;
	}

	public JEditorPane getTxtDescription() {
		return txtDescription;
	}

	public void setTxtDescription(JEditorPane txtDescription) {
		this.txtDescription = txtDescription;
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
		
		JLabel lblTime = new JLabel("Year");
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
		
		txtDescription = new JEditorPane();
		scrollPane.setColumnHeaderView(txtDescription);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.BLACK);
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(236, 6, 12, 366);
		frmTimeliner.getContentPane().add(separator);
		
		JButton btnAddEvent = new JButton("Add Event");
		btnAddEvent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = txtName.getText();
				DateTime d = new DateTime(Integer.parseInt(txtTime.getText()), 1, 1, 1, 1);
				String description = txtDescription.getText();
				timeline.addEvent(new Event(name, description, d));
				statusConsole.append("\nEvent \"" + name + "\" has been added. Total number of events in current timeline: " + timeline.events.size() + ".\n");
				txtName.setText("");
				txtTime.setText("");
				txtDescription.setText("");
			}
		});
		btnAddEvent.setBounds(29, 211, 158, 29);
		frmTimeliner.getContentPane().add(btnAddEvent);
		
		JButton btnEditAnEvent = new JButton("Edit Existing Event\n");
		btnEditAnEvent.setBounds(29, 250, 158, 29);
		frmTimeliner.getContentPane().add(btnEditAnEvent);
		
		JButton btnSave = new JButton("Save Current Timeline");
		btnSave.setBounds(29, 314, 181, 29);
		frmTimeliner.getContentPane().add(btnSave);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane_1.setBounds(260, 37, 211, 176);
		frmTimeliner.getContentPane().add(scrollPane_1);
		
		statusConsole = new JTextArea();
		statusConsole.setEditable(false);
		statusConsole.setLineWrap(true);
		statusConsole.setWrapStyleWord(true);
		statusConsole.setText("Welcome to Timeliner! You are currently editing a new timeline. Please enter your first event's information to the left.\n");
		scrollPane_1.setViewportView(statusConsole);
		
		JLabel lblStatusConsole = new JLabel("Status Console");
		lblStatusConsole.setBounds(260, 9, 99, 16);
		frmTimeliner.getContentPane().add(lblStatusConsole);
		
		JButton btnNewButton_1 = new JButton("Load Existing Timeline\n");
		btnNewButton_1.setBounds(29, 343, 181, 29);
		frmTimeliner.getContentPane().add(btnNewButton_1);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(Color.BLACK);
		separator_1.setBounds(6, 281, 227, 12);
		frmTimeliner.getContentPane().add(separator_1);
		
		JButton btnCreate = new JButton("Create Timeline");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(timeline.size() == 0)
				{
					statusConsole.append("\nThere are no events in your timeline.\n");
				}
				else
				{
					timeline.sort();
					for(Event ev: timeline.events)
					{
						statusConsole.append("\n" + ev.date.getYear() + ": " + ev.name + ", " + ev.description + "\n");
					}
				}
			}
		});
		btnCreate.setBounds(289, 264, 161, 29);
		frmTimeliner.getContentPane().add(btnCreate);
		
		txtTimeline = new JTextField();
		txtTimeline.setText("Timeline");
		txtTimeline.setBounds(53, 287, 134, 28);
		frmTimeliner.getContentPane().add(txtTimeline);
		txtTimeline.setColumns(10);
	}
}
