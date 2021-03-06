import java.awt.*;

/**
 * Operator "<"
 *
 * @author Arvind
 * @author Karandeep Singh Grewal
 * @since March 13, 2020
 */
public class OpLesserThan extends Op {
    static Color GREEN = new Color(52,199,89);

    OpLesserThan() {
        super();
        label = "<";
        opLabel.setText(label);
        color = GREEN;
        packOperator();
        addToConnector("I", FactoryConnector.getOp("Dot", this));
        addToConnector("O", FactoryConnector.getOp("Dot", this));
        addToConnector("O", FactoryConnector.getOp("Dot", this));
    }

}
