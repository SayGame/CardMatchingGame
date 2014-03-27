import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Next extends JFrame {

	JPanel empty1 , empty2 ,empty3,empty4;
	public Next() {

		setLayout(new GridLayout(1, 3));

		JPanel startPanel = new JPanel();
		
		empty1 = new JPanel();
		empty2 = new JPanel();
		empty3 = new JPanel();
		empty4 = new JPanel();
		
		empty1.setBackground(new Color(0, 128, 128));
		empty2.setBackground(new Color(0, 128, 128));
		empty3.setBackground(new Color(0, 128, 128));
		empty4.setBackground(new Color(0, 128, 128));
		
		JButton next = new JButton("Next");
		next.setBackground(new Color(240, 255, 240));
		JButton exit = new JButton("Exit");
		exit.setBackground(new Color(240, 255, 240));
		add(empty1);

		startPanel.setLayout(new GridLayout(4, 1, 10, 10));

		startPanel.setBackground(new Color(0, 128, 128));
		
		startPanel.add(empty2);
		startPanel.add(next);
		startPanel.add(exit);
		startPanel.add(empty3);

		add(startPanel);

		add(empty4);

		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setVisible(true);
		pack();
		setLocationRelativeTo(null);

		next.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				Buton.next();
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
