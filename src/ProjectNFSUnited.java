import Menu.GamePanel;

import javax.swing.*;

public class ProjectNFSUnited {
    public static void main(String[] args) {
        // Cria a janela do jogo
        JFrame frame = new JFrame("Project NFS United");
        frame.setSize(400, 600); // Define o tamanho da janela
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Fecha o programa ao fechar a janela
        frame.setResizable(true); // Impede que o jogador redimensione a janela

        // Adiciona o painel do jogo (GamePanel)
        GamePanel gamePanel = new GamePanel();
        frame.add(gamePanel);

        // Torna a janela visível
        frame.setVisible(true);
    }
}
