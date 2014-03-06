package timeliner;



import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import org.joda.time.DateTime;

public class EditGUI {

	
	/**
	 * @author stevenzc
	 * developed using the Eclipse IDE and Google WindowBuilder Plug-In
	 * 
	 */
	
	
	
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
		btnEditAnEvent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean foundEvent = false;
				for(Event e1: timeline.events)
				{
					if(txtName.getText().equals(e1.getName()))
					{
						foundEvent = true;
						e1.setDate(new DateTime(Integer.parseInt(txtTime.getText()), 1, 1, 1, 1));
						e1.setDescription(txtDescription.getText());
						
						statusConsole.append("\nEvent \"" + e1.getName() + "\" has been edited. Total number of events in current timeline: " + timeline.events.size() + ".\n");
						txtName.setText("");
						txtTime.setText("");
						txtDescription.setText("");
					}
				}
				if(!foundEvent)
				{
					statusConsole.append("\nEvent \"" + txtName.getText() + "\" was not found. The existing events in the timeline are: " + timeline.toString() + ".\n");
					txtName.setText("");
					txtTime.setText("");
					txtDescription.setText("");
				}
			}
		});
		btnEditAnEvent.setBounds(29, 250, 158, 29);
		frmTimeliner.getContentPane().add(btnEditAnEvent);
		
		JButton btnSave = new JButton("Save Current Timeline");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					FileOutputStream saveFile = new FileOutputStream(txtTimeline.getText() + ".sav");
					ObjectOutputStream save = new ObjectOutputStream(saveFile);
					save.writeObject(timeline);
					save.close();

					statusConsole.append("\nTimeline \"" + txtTimeline.getText() + "\" saved successfully." + "\n");

					}
					catch(Exception ef){ }
			}
		});
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
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					FileInputStream saveFile = new FileInputStream(txtTimeline.getText() + ".sav");
					ObjectInputStream save = new ObjectInputStream(saveFile);
					timeline = (Timeline)save.readObject();
					save.close();
					statusConsole.append("\nTimeline \"" + txtTimeline.getText() + "\" has been loaded. The existing events in the timeline are: " + timeline.toString() + ".\n");

				}
				catch(Exception ef){
					txtTimeline.setText("Timeline save file was not found.");
				}
			}
		});
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
					Artist artist = new Artist(timeline);
					artist.setVisible(true);
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
