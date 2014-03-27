import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Game extends JFrame {

	static Level level[] = new Level[5];
	static int iterator;
	static ImageIcon time[] = new ImageIcon[5];

	static JLabel timer[] = new JLabel[5];


	public Game(int i) {
		 
		time[0] = new ImageIcon("img/bar0.gif");
		time[1] = new ImageIcon("img/bar1.gif");
		time[2] = new ImageIcon("img/bar2.gif");
		time[3] = new ImageIcon("img/bar3.gif");
		time[4] = new ImageIcon("img/bar4.gif");

		iterator = i;

		timer[iterator] = new JLabel(time[iterator]);

		setUndecorated(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setLayout(new BorderLayout());
		setResizable(false);

		level[iterator] = new Level(iterator + 2, iterator + 3, this);

		add(new JPanel(), BorderLayout.NORTH);
		add(new JPanel(), BorderLayout.EAST);
		add(new JPanel(), BorderLayout.WEST);
		add(level[iterator], BorderLayout.CENTER);
		add(timer[iterator], BorderLayout.SOUTH);
		pack();
		setLocationRelativeTo(null);
	}

	public void nextLevel() {

		iterator++;

		level[iterator] = new Level(iterator + 2, iterator + 3, this);
		level[iterator - 1].timer.stop();
		remove(level[iterator - 1]);
		add(level[iterator], BorderLayout.CENTER);

		timer[iterator] = new JLabel(time[iterator]);
		remove(timer[iterator - 1]);
		add(timer[iterator], BorderLayout.SOUTH);

		pack();
		setLocationRelativeTo(null);

		saveGame();

	}

	public static void saveGame() {
		try {
			PrintWriter write = new PrintWriter(new File("savedGame.txt"));
			write.println(iterator);
			write.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	

}
