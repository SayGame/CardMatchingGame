import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.LineBorder;

public class Level extends JPanel implements ActionListener {

	static Buton buton[][];
	static ImageIcon icon[];
	static int counter, finish;
	static String open1, open2;
	static Game game;
	Timer timer = new Timer(1000, this);
	int tick = 0;
	int finishTime = 0;

	public Level(int row, int col, Game g) {

		game = g;
		finishTime = (int) Math.pow((row * col / 2), 1.5);

		buton = new Buton[row][col];
		icon = new ImageIcon[row * col / 2];
		counter = 0;
		finish = 0;

		createButons();

		randomIcons();
		setLayout(new GridLayout(row, col, 20, 20));

		addButons();

		timer.start();

	}

	public void createButons() {
		for (int i = 0; i < buton.length; i++) {
			for (int j = 0; j < buton[i].length; j++) {
				buton[i][j] = new Buton();
			}
		}
	}

	public void randomIcons() {
		for (int i = 0; i < icon.length; i++) {
			icon[i] = new ImageIcon("img/" + i + ".jpg");
		}

		ImageIcon newIcon[] = new ImageIcon[icon.length * 2];

		for (int i = 0; i < newIcon.length; i++) {
			newIcon[i] = icon[i / 2];
		}

		for (int i = 0; i < buton.length; i++) {

			for (int j = 0; j < buton[i].length; j++) {
				int random = (int) (Math.random() * newIcon.length);
				while (newIcon[random] == null)
					random = (int) (Math.random() * newIcon.length);

				buton[i][j].icon = newIcon[random];
				newIcon[random] = null;

			}

		}

	}

	public void addButons() {
		for (int i = 0; i < buton.length; i++) {
			for (int j = 0; j < buton[0].length; j++) {
				add(buton[i][j]);
			}
		}

	}

	public static void setIconsBack() {
		for (int i = 0; i < buton.length; i++) {
			for (int j = 0; j < buton[0].length; j++) {
				if (buton[i][j].isEnable) {
					buton[i][j].setIcon(buton[i][j].back);
					buton[i][j].isOn = false;

				}
			}
		}
		counter = 1;
	}

	public static void found() {

		for (int i = 0; i < buton.length; i++) {
			for (int j = 0; j < buton[i].length; j++) {
				if (buton[i][j].icon.toString() == open1) {
					buton[i][j].isEnable = false;
					buton[i][j].setBorder(new LineBorder(Color.BLACK, 3));
				}
			}
		}
		finish++;
	}

	public void actionPerformed(ActionEvent e) {

		if (finish == icon.length) {
			timer.stop();
			game.remove(game.timer[Game.iterator]);
		} else if (tick++ == finishTime) {
			game.remove(game.timer[Game.iterator]);
			game.disable();

			new Retry(this);
		}

	}

}
