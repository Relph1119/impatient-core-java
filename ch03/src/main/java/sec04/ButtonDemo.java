package sec04;

import javax.swing.*;
import java.awt.*;

public class ButtonDemo {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            var frame = new JFrame();
            var button = new JButton("Click me!");
            button.addActionListener(event -> 
                System.out.println("Thanks for clicking!"));
            frame.add(button);
            frame.pack();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });       
    }
}
