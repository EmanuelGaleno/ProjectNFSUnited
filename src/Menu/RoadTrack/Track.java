package Menu.RoadTrack;

import java.awt.*;

public class Track {
    public void draw(Graphics g, int width, int height) {
        // Fundo da pista
        g.setColor(Color.DARK_GRAY);
        g.fillRect(100, 0, 200, height);

        // Linhas centrais da pista
        g.setColor(Color.WHITE);
        for (int i = 0; i < height; i += 40) {
            g.fillRect(195, i, 10, 20);
        }
    }
}
