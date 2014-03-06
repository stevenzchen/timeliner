package timeliner;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

public class Artist extends JFrame {


	private static final long serialVersionUID = 1L;
	private JPanel contentPane;



	public Artist(Timeline timeline) {
		setTitle("Timeline by Timeliner\n");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 400, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		for(int i = 0; i < timeline.size(); i++)
		{
			Event event = timeline.events.get(i);
			panel.add(new JLabel("|"));
			panel.add(new JLabel("|"));
			JLabel lbl = new JLabel("|---- " + event.getDate().getYear() + ": " + event.getName());
			lbl.setToolTipText(event.getDescription());
			panel.add(lbl);
		}
		
		JScrollPane scrollPane = new JScrollPane(panel);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(6, 6, 388, 566);
		contentPane.add(scrollPane);
		
		repaint();

	}

}
