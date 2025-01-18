package Menu.Veichles;

import java.awt.*;


public class Cars {
    private int x, y;
    private Image image;
    private int speedY = 5; // Velocidade de movimento no eixo Y (para baixo), pode ser alterado conforme necessário
    private double rotation = 0; // Ângulo de rotação do carro (em graus)

    // Construtor
    public Cars(int x, int y, Image image) {
        this.x = x;
        this.y = y;
        this.image = image;
    }

    // Desenha o carro com rotação
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.rotate(Math.toRadians(rotation), x + image.getWidth(null) / 2, y + image.getHeight(null) / 2);
        g2d.drawImage(image, x, y, null); // Desenha a imagem do carro
        g2d.rotate(-Math.toRadians(rotation), x + image.getWidth(null) / 2, y + image.getHeight(null) / 2); // Desfaz a rotação
    }

    // Movimenta o carro no eixo X (esquerda/direita)
    public void move(int deltaX, int deltaY) {
        x += deltaX; // Atualiza a posição no eixo X
        y += deltaY; // Atualiza a posição no eixo Y (pode ser usado para movimento vertical, se necessário)
    }

    // Atualiza a posição do carro (movimento contínuo no eixo Y com velocidade) puxa o carro pra baixo e fodase, metodo nao funcional
//    public void updatePosition() {
//        y += speedY; // Movimenta o carro para baixo com uma velocidade constante
//}

    // Retorna as bordas do carro (usado para detectar colisões)
    public Rectangle getBounds() {
        return new Rectangle(x, y, image.getWidth(null), image.getHeight(null)); // Cria um retângulo baseado na posição e tamanho da imagem
    }

    // Métodos para acessar e modificar posição
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    // Método para ajustar a rotação do carro
    public void setRotation(double rotation) {
        this.rotation = rotation;
    }

    public double getRotation() {
        return rotation;
    }
}
