import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.Icon;
import javax.swing.JButton;
/**
 * 
 * @author Tarun Snehith Kishore Reddy Karna
 * @since 04-27-2021
 * @version 1.0
 *
 */
public class Button6 extends JButton {
	private SelectionEvent event;
	private static Icon label;

	public Button6() {
		super("||");
		Dimension size = getPreferredSize();
		setPreferredSize(size);
		setContentAreaFilled(false);
		event = new SelectionEvent(this, 6);
	}

	protected void paintComponent(Graphics g) {
		if (getModel().isArmed()) {
			g.setColor(Color.lightGray);
		} else {
			g.setColor(getBackground());
		}
		g.fillRect(20, 10, getWidth()-40, getHeight()-25);
		super.paintComponent(g);
	}

	
}