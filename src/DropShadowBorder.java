import java.awt.*;
import javax.swing.border.*;

public class DropShadowBorder implements Border {
    private final int radius;
    private final int shadowSize;
    private final Color shadowColor;
    private final Color textColor;

    public DropShadowBorder() {
        this(5, 5, new Color(180, 220, 250), Color.WHITE);
    }

    public DropShadowBorder(int radius, int shadowSize, Color shadowColor, Color textColor) {
        this.radius = radius;
        this.shadowSize = shadowSize;
        this.shadowColor = shadowColor;
        this.textColor = textColor;
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        Color oldColor = g.getColor();
        int x0 = x + radius;
        int y0 = y + radius;
        int x1 = x + width - radius;
        int y1 = y + height - radius;

        // Draw top shadow
        g.setColor(shadowColor);
        for (int i = 0; i < shadowSize; i++) {
            g.drawRoundRect(x0 - i, y - shadowSize + i, x1 - x0 + 2 * i, radius, radius, radius);
        }

        // Draw left shadow
        for (int i = 0; i < shadowSize; i++) {
            g.drawRoundRect(x - shadowSize + i, y0 - i, radius, y1 - y0 + 2 * i, radius, radius);
        }

        // Draw bottom shadow
        for (int i = 0; i < shadowSize; i++) {
            g.drawRoundRect(x0 - i, y + height - i, x1 - x0 + 2 * i, radius, radius, radius);
        }

        // Draw right shadow
        for (int i = 0; i < shadowSize; i++) {
            g.drawRoundRect(x + width - i, y0 - i, radius, y1 - y0 + 2 * i, radius, radius);
        }

        g.setColor(textColor);
        g.drawRoundRect(x0, y - radius, x1 - x0, radius, radius, radius);
        g.setColor(oldColor);
    }

    @Override
    public Insets getBorderInsets(Component c) {
        return new Insets(shadowSize, shadowSize, shadowSize, shadowSize);
    }

    @Override
    public boolean isBorderOpaque() {
        return false;
    }
}
