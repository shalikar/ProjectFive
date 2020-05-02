import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Listeners for the ToolbarTop
 *
 * @author Aditya Bajaj
 * @since April 26, 2020
 *
 */
public class ListenersToolbarTop {
    final static Cursor DEFAULT_CURSOR = new Cursor(Cursor.DEFAULT_CURSOR);

    public static void addShapeListeners(Op op) {
        op.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                if (Database.selectedOp != null) {
                    Database.selectedOp.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
                }
                Database.selectedOp = (Op) mouseEvent.getSource();
                Database.selectedOp.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {
            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                op.setCursor(DEFAULT_CURSOR);
            }
        });

    }

}
