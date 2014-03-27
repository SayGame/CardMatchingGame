

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

public class FinishScreen extends JFrame {

	public FinishScreen() {

		JPanel finishPanel = new JPanel();

		finishPanel.setBackground(new Color(0, 128, 128));
		JButton newGame = new JButton("New Game");
		newGame.setBackground(new Color(240, 255, 240));
		JButton exit = new JButton("Exit");
		exit.setBackground(new Color(240, 255, 240));

		finishPanel.add(newGame);
		finishPanel.add(exit);

		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setVisible(true);
		
		setLayout(new BorderLayout());
		setSize(getWidth(), getHeight());

		add(new JLabel(new ImageIcon("img/win.jpg")), BorderLayout.CENTER);

		add(finishPanel, BorderLayout.SOUTH);
		pack();
		setLocationRelativeTo(null);
		
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
		newGame.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new Game(0);
			}
		});


		
		

	}

}
