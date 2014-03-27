import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Buton extends JButton implements ActionListener {

	public boolean isEnable = true;
	public boolean isOn = false;
	public ImageIcon back;
	public ImageIcon icon;

	public Buton() {
		back = new ImageIcon("img/arka.jpg");
		setIcon(back);
		setPreferredSize(new Dimension(50, 50));
		addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (!isOn) {
			Level.counter++;
			if (Level.counter == 3) {
				Level.setIconsBack();
				Level.open1 = icon.toString();
				Level.open2 = null;
			} else if (Level.counter == 1)
				Level.open1 = icon.toString();
			else if (Level.counter == 2) {
				Level.open2 = icon.toString();
				if (Level.open1 == Level.open2)
					Level.found();
			}
			isOn = true;
			setIcon(icon);
		}

		if (Level.finish == Level.icon.length) {
			if (Level.finish >16) {
				Level.game.dispose();
				new FinishScreen();
			} else
				new Next();
		}

	}

	public static void next() {

		Level.game.nextLevel();

	}
}
