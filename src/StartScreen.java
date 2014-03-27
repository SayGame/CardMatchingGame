import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class StartScreen extends JFrame {

	Scanner scan;

	JPanel empty1, empty2, empty3, empty4;

	public StartScreen() {

		empty1 = new JPanel();
		empty2 = new JPanel();
		empty3 = new JPanel();
		empty4 = new JPanel();

		empty1.setBackground(new Color(0, 128, 128));
		empty2.setBackground(new Color(0, 128, 128));
		empty3.setBackground(new Color(0, 128, 128));
		empty4.setBackground(new Color(0, 128, 128));

		setLayout(new GridLayout(1, 3));

		JPanel startPanel = new JPanel();
		startPanel.setBackground(new Color(0, 128, 128));
		JButton Continue = new JButton("Continue");
		Continue.setBackground(new Color(240, 255, 240));
		JButton NewGame = new JButton("NewGame");
		NewGame.setBackground(new Color(240, 255, 240));
		JButton Instructions = new JButton("Instructions");
		Instructions.setBackground(new Color(240, 255, 240));
		JButton Exit = new JButton("Exit");
		Exit.setBackground(new Color(240, 255, 240));

		if (!contiunable())
			Continue.setEnabled(false);

		add(empty1);

		startPanel.setLayout(new GridLayout(6, 1, 10, 10));

		startPanel.add(empty2);
		startPanel.add(Continue);
		startPanel.add(NewGame);
		startPanel.add(Instructions);
		startPanel.add(Exit);
		startPanel.add(empty3);

		add(startPanel);

		add(empty4);

		setVisible(true);
		pack();
		setLocationRelativeTo(null);

		Continue.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new Game(savedLevel());
			}
		});

		NewGame.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (!contiunable()) {
					new Game(0);
					Game.saveGame();
					dispose();
				}

				else {

					int choice = JOptionPane
							.showConfirmDialog(
									null,
									"Your saved game will be lost! Wanna continue anyway?",
									"", JOptionPane.YES_NO_OPTION,
									JOptionPane.QUESTION_MESSAGE);

					if (choice == JOptionPane.YES_OPTION) {
						new Game(0);
						Game.saveGame();
						dispose();
					}
				}
			}
		});

		Exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int choice = JOptionPane.showConfirmDialog(null,
						"Are you sure?", "Exit?", JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE);

				if (choice == JOptionPane.YES_OPTION)
					System.exit(1);
			}
		});
		Instructions.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				final JFrame fr = new JFrame("Instructions");

				fr.setLayout(new BorderLayout());
				fr.setSize(getWidth(), getHeight());

				fr.setVisible(true);
				fr.setLocationRelativeTo(null);
				JPanel p = new JPanel(new FlowLayout(FlowLayout.CENTER));
				p.setBackground(new Color(0, 128, 128));
				JButton play = new JButton("Play");
				play.setBackground(new Color(240, 255, 240));
				JButton back = new JButton("Back");
				back.setBackground(new Color(240, 255, 240));

				play.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {

						if (!contiunable()) {
							fr.dispose();
							dispose();
							new Game(savedLevel());
							return;
						}
						int choice = JOptionPane.showConfirmDialog(null,
								"Do you want to play your saved game?", "",
								JOptionPane.YES_NO_OPTION);

						if (choice == JOptionPane.NO_OPTION) {
							fr.dispose();
							dispose();
							new Game(0);
						} else if (choice == JOptionPane.YES_OPTION) {
							fr.dispose();
							dispose();
							new Game(savedLevel());
						}

					}
				});

				back.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {

						setVisible(true);
						fr.dispose();

					}
				});

				p.add(play);
				p.add(back);
				fr.add(p, BorderLayout.SOUTH);
				fr.add(new JLabel(new ImageIcon("img/ins.jpg")), BorderLayout.CENTER);
				fr.setBackground(new Color(0, 128, 128));
				setVisible(false);
			}
		});

	}

	private boolean contiunable() {

		return savedLevel() != 0;
	}

	private int savedLevel() {
		int savedLevel = 0;

		try {
			scan = new Scanner(new File("savedGame.txt"));
			savedLevel = scan.nextInt();
			scan.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return savedLevel;
	}

}
