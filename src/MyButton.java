import javax.swing.*;
import java.awt.*;

public class MyButton extends JButton {

    MyButton(String text) {
        this.setBackground(Color.BLACK);
        this.setForeground(Color.WHITE);
        this.setText(text);
        this.setFont(new Font("Verdana", Font.PLAIN, 30));
    }
}
