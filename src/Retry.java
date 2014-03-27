import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Retry extends JFrame {

	Level level;
	JPanel empty1 , empty2 ,empty3, empty4 ;


	public Retry(Level l) {

		level = l;

		setLayout(new GridLayout(1, 3));
		
		
		empty1 = new JPanel();
		empty2 = new JPanel();
		empty3 = new JPanel();
		empty4 = new JPanel();
		
		empty1.setBackground(new Color(0, 128, 128));
		empty2.setBackground(new Color(0, 128, 128));
		empty3.setBackground(new Color(0, 128, 128));
		empty4.setBackground(new Color(0, 128, 128));
		
	
		

		JPanel startPanel = new JPanel();
		startPanel.setBackground(new Color(0, 128, 128));
		JButton retry = new JButton("Retry");
		retry.setBackground(new Color(240, 255, 240));
		JButton exit = new JButton("Exit");
		exit.setBackground(new Color(240, 255, 240));

		add(empty1);

		startPanel.setLayout(new GridLayout(4, 1, 10, 10));

		startPanel.add(empty2);
		startPanel.add(retry);
		startPanel.add(exit);
		startPanel.add(empty3);

		add(startPanel);

		add(empty4);

		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setVisible(true);
		pack();
		setLocationRelativeTo(null);

		retry.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				level.game.dispose();

				Game.time[0] = new ImageIcon("img/bar0.gif");
				Game.time[1] = new ImageIcon("img/bar1.gif");
				Game.time[2] = new ImageIcon("img/bar2.gif");
				Game.time[3] = new ImageIcon("img/bar3.gif");

				Game.timer[Game.iterator] = new JLabel(Game.time[Game.iterator]);

				Game g =new Game(Game.iterator);

				g.remove(Game.timer[Game.iterator]);
				g.add(Game.timer[Game.iterator], BorderLayout.SOUTH);

				g.revalidate();
				dispose();

			}
		});
		exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int choice = JOptionPane.showConfirmDialog(null,
						"Are you sure?", "Exit?", JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE);

				if (choice == JOptionPane.YES_OPTION)
					System.exit(1);
			}
		});
	}
}
