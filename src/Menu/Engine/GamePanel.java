package Menu.Engine;

import Menu.RoadTrack.Track;
import Menu.Veichles.Cars;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class GamePanel extends JPanel implements KeyListener {

    private Track track; // Usando a classe Track do pacote Menu.RoadTrack
    private Cars playerCar; // Usando a classe Cars do pacote Menu.Vehicles
    private List<Cars> obstacles;
    private int score = 0;

    public GamePanel() {
        this.setFocusable(true);
        this.addKeyListener(this);

        // Inicializando a pista
        track = new Track();

        // Carregando imagens
        Image playerImage = new ImageIcon(getClass().getResource("/resources/car_mustang_shelby_00.png")).getImage();
        Image obstacleImage = new ImageIcon(getClass().getResource("/resources/car_sedan_front_00.png")).getImage();

        // Inicializando o carro do jogador
        playerCar = new Cars(175, 500, playerImage);

        // Inicializando obstáculos
        obstacles = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            obstacles.add(new Cars(100 + (int) (Math.random() * 150), -150 * i, obstacleImage));
        }

        // Timer para mover obstáculos e verificar colisões
        Timer timer = new Timer(15, e -> {
            for (Cars obstacle : obstacles) {
                obstacle.move(0, 5); // Move o obstáculo para baixo

                // Reposiciona o obstáculo se ele sair da tela
                if (obstacle.getY() > getHeight()) {
                    obstacle.setY(-100);
                    score += 10; // Incrementa a pontuação
                }
            }

//            // Atualiza a posição do carro com base na velocidade. metodo ainda nao funcional
//            playerCar.updatePosition();

            // Checa colisão
            if (checkCollision()) {
                JOptionPane.showMessageDialog(this, "Você colidiu! Pontuação final: " + score);
                System.exit(0);
            }

            repaint(); // Atualiza a tela
        });
        timer.start(); // Inicia o timer apenas uma vez
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Desenhar pista
        track.draw(g, getWidth(), getHeight());

        // Desenhar carro do jogador (com rotação)
        playerCar.draw(g);

        // Desenhar obstáculos
        for (Cars obstacle : obstacles) {
            obstacle.draw(g);
        }

        // Exibir pontuação
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Pontuação: " + score, 10, 20);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_LEFT && playerCar.getX() > 50) {
            playerCar.move(-10, 0); // Move o carro para a esquerda
            playerCar.setRotation(-15); // Rotaciona o carro para a esquerda
        } else if (keyCode == KeyEvent.VK_RIGHT && playerCar.getX() < getWidth() - 150) {
            playerCar.move(10, 0); // Move o carro para a direita
            playerCar.setRotation(15); // Rotaciona o carro para a direita
        }

        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}

    // Método para verificar colisão
    private boolean checkCollision() {
        for (Cars obstacle : obstacles) {
            if (playerCar.getBounds().intersects(obstacle.getBounds())) {
                return true;
            }
        }
        return false;
    }
}
