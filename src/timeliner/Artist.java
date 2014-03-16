package timeliner;

import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

public class Artist extends JFrame {


	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JSplitPane panel;
	private JPanel leftpanel;
	private JPanel rightpanel;
	private Timeline t;

	public Artist(Timeline timeline) {
		
		
		
		t = timeline;
		setTitle(timeline.name);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 501, 640);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		

		leftpanel = new JPanel();
		leftpanel.setLayout(new BoxLayout(leftpanel, BoxLayout.Y_AXIS));
		
		rightpanel = new JPanel();
		rightpanel.setLayout(new BoxLayout(rightpanel, BoxLayout.Y_AXIS));
		
		panel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftpanel, rightpanel);
		panel.setOneTouchExpandable(false);
		panel.setEnabled(false);
		panel.setResizeWeight(.5d);



			boolean left = true;
			for(int i = t.start.getYear(); i <= t.end.getYear(); i++)
			{
				ArrayList<Event> e = t.getEventsInYear(i);
				if(e.size() == 0)
				{
					JLabel l = new JLabel("-");
					JLabel r = new JLabel("-");
					if(i % 5 == 0 && left)
					{
						l.setText("(" + Integer.toString(i) + ")-");
					}
					else if(i % 5 == 0 && !left)
					{
						r.setText("-(" + Integer.toString(i) + ")");
					}
					l.setAlignmentX(RIGHT_ALIGNMENT);
					r.setAlignmentX(LEFT_ALIGNMENT);
					leftpanel.add(l);
					rightpanel.add(r);
				}
				else
				{
					for(int j = 0; j < e.size(); j++)
					{
						JLabel content = new JLabel(e.get(j).name);
						JLabel filler = new JLabel("-");
						if(left)
						{
							content.setAlignmentX(RIGHT_ALIGNMENT);
							filler.setAlignmentX(LEFT_ALIGNMENT);
							content.setText(content.getText() + "  - " + i + "-----");
							content.setToolTipText(e.get(j).description);
							
							leftpanel.add(content);
							rightpanel.add(filler);
							
							left = false;
						}
						else
						{
							content.setAlignmentX(LEFT_ALIGNMENT);
							filler.setAlignmentX(RIGHT_ALIGNMENT);
							content.setText("-----" + i + " -  " + content.getText());
							content.setToolTipText(e.get(j).description);
							
							rightpanel.add(content);
							leftpanel.add(filler);
							
							left = true;
						}
					}
				}
			}


	
		JScrollPane scrollPane = new JScrollPane(panel);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(6, 6, 489, 564);
		contentPane.add(scrollPane);
		
		JButton btnImage = new JButton("Save as Image");
		btnImage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					File file = new File(t.getName() + ".png");
					
					   
				    int w = panel.getWidth();
				    int h = panel.getHeight();
				    BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
				    Graphics2D g = bi.createGraphics();
				    panel.paint(g);
				        
				    
					
					ImageIO.write(bi, "png", file);
				}
				catch(Exception exc) {
					exc.printStackTrace();
				}
			}
		});
		btnImage.setBounds(6, 582, 140, 29);
		contentPane.add(btnImage);
		
		JButton btnClose = new JButton("Close Window");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnClose.setBounds(355, 582, 140, 29);
		contentPane.add(btnClose);
		
		repaint();

	}
}
