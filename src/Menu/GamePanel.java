package Menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class GamePanel extends JPanel implements KeyListener {

    private int carX = 175; // Posição inicial do carro no eixo X
    private int carY = 500; // Posição inicial do carro no eixo Y
    private int score = 0; // Pontuação do jogador

    // Lista de obstáculos
    private List<Rectangle> obstacles = new ArrayList<>();

    public GamePanel() {
        this.setFocusable(true);
        this.addKeyListener(this);

        // Timer para mover os obstáculos e atualizar a pontuação
        Timer timer = new Timer(30, e -> {
            // Atualiza a posição de cada obstáculo
            for (int i = 0; i < obstacles.size(); i++) {
                Rectangle obstacle = obstacles.get(i);
                obstacle.y += 5; // Move o obstáculo para baixo

                // Remove o obstáculo se ele sair da tela e cria um novo
                if (obstacle.y > getHeight()) {
                    obstacles.set(i, new Rectangle(100 + (int) (Math.random() * 150), -100, 25, 50));
                    score += 10; // Incrementa a pontuação
                }
            }

            // Verifica colisão
            if (checkCollision()) {
                JOptionPane.showMessageDialog(this, "Você colidiu! Pontuação final: " + score);
                System.exit(0); // Encerra o jogo
            }

            repaint(); // Atualiza a tela
        });
        timer.start();

        // Cria os obstáculos iniciais
        for (int i = 0; i < 3; i++) { // Começa com 3 obstáculos
            obstacles.add(new Rectangle(100 + (int) (Math.random() * 150), -150 * i, 50, 100));
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Fundo da tela
        g.setColor(Color.GRAY);
        g.fillRect(0, 0, getWidth(), getHeight());

        // Fundo da pista
        g.setColor(Color.DARK_GRAY);
        g.fillRect(100, 0, 200, getHeight());

        // Linhas centrais da pista
        g.setColor(Color.WHITE);
        for (int i = 0; i < getHeight(); i += 40) {
            g.fillRect(195, i, 10, 20);
        }

        // Carro do jogador
        g.setColor(Color.BLUE);
        g.fillRect(carX, carY, 50, 100);

        // Obstáculos
        g.setColor(Color.RED);
        for (Rectangle obstacle : obstacles) {
            g.fillRect(obstacle.x, obstacle.y, obstacle.width, obstacle.height);
        }

        // Pontuação
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Pontuação: " + score, 10, 20);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_LEFT && carX > 100) {
            carX -= 10;
        } else if (keyCode == KeyEvent.VK_RIGHT && carX < 250) {
            carX += 10;
        }

        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}

    // Método para verificar colisão
    private boolean checkCollision() {
        for (Rectangle obstacle : obstacles) {
            if (new Rectangle(carX, carY, 50, 100).intersects(obstacle)) {
                return true; // Colisão detectada
            }
        }
        return false;
    }
}
