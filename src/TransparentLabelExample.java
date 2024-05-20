
import javax.swing.*;
import java.awt.*;

public class TransparentLabelExample {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Transparent Label Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 800);

        // Panneau de fond pour afficher ce qu'il y a derrière le label
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.LIGHT_GRAY);
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        backgroundPanel.setLayout(new BorderLayout());

        // Image pour le label (remplacer par votre image)
        ImageIcon image = new ImageIcon("files/eldenring.jpg");
        JLabel imageLabel = new JLabel(image);
        imageLabel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        imageLabel.setOpaque(false); // Rend le label non opaque
        imageLabel.setBackground(Color.BLACK);

        // Ajout du label au panneau de fond
        backgroundPanel.add(imageLabel, BorderLayout.CENTER);

        frame.setContentPane(backgroundPanel);
        frame.setVisible(true);
    }
}

