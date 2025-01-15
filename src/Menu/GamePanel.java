package Menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePanel extends JPanel implements KeyListener {

    private int carX = 175; // Posição inicial do carro no eixo X
    private int carY = 500; // Posição inicial do carro no eixo Y

    public GamePanel() {
        this.setFocusable(true); // Permite que o painel receba entradas de teclado
        this.addKeyListener(this); // Registra o KeyListener
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Cor de fundo da tela
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
        g.setColor(Color.BLUE); // Cor do carro
        g.fillRect(carX, carY, 50, 100); // Desenha o carro como um retângulo
    }

    // Método que será chamado quando uma tecla for pressionada
    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode(); // Obtém o código da tecla pressionada

        // Movimenta o carro para a esquerda ou direita
        if (keyCode == KeyEvent.VK_LEFT && carX > 100) {
            carX -= 10; // Move o carro para a esquerda
        } else if (keyCode == KeyEvent.VK_RIGHT && carX < 250) {
            carX += 10; // Move o carro para a direita
        }

        repaint(); // Re-desenha o painel com a nova posição do carro
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Não estamos lidando com isso, mas o método é obrigatório
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Não estamos lidando com isso, mas o método é obrigatório
    }
}
