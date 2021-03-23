package cena;

import com.jogamp.opengl.util.awt.TextRenderer;
import java.awt.*;

public class Menu {
    private TextRenderer textRenderer;//Letreiro
    private int H1 = 40;
    private int H2 = 20;
    private int H3 = 15;


    private void volta() {
        texto(500, 10, Color.BLUE, "<- Voltar (BackSpace)", 15);
    }

    private void texto(int x, int y, Color cor, String string, int size) {
        textRenderer = new TextRenderer(new Font("Comic Sans MS Negrito", Font.BOLD, size));
        textRenderer.beginRendering(Renderer.screenWidth, Renderer.screenHeight);
        textRenderer.setColor(cor);
        textRenderer.draw(string, x, y);
        textRenderer.endRendering();
    }

    public void telaDeInicio() {
        texto(150, 300, Color.WHITE, "PONG", 100);
        texto(20, 580, Color.RED, "Sair (Esc)", H3);
        texto(20, 40, Color.GREEN, "Iniciar (Enter)", H3);
        texto(20, 20, Color.BLUE, "Instruções (Espaço)", H3);
    }

    public void instrucoes() {
        int Y = 500;
        texto(200, Y, Color.WHITE,"Instruções:", H1);
        texto(5, Y-=70, Color.WHITE,"Comandos:", H2);
        texto(5, Y-=20, Color.WHITE,"  Utilize as teclas de esquerda e direita, respectivamente para movimentar.", H3);
        texto(5, Y-=15, Color.WHITE," (Espaço) Vai para as instruções do jogo.", H3);
        texto(5, Y-=15, Color.WHITE," (Enter) Começa o jogo.", H3);
        texto(5, Y-=15, Color.WHITE," (P) Pausa o jogo.", H3);
        texto(5, Y-=15, Color.WHITE," (BackSpace) Volta ao menu principal.", H3);
        texto(5, Y-=15, Color.WHITE," (Esc) Sai do jogo.", H3);
        texto(5, Y-=50, Color.WHITE,"O Jogo:", H2);
        texto(5, Y-=20, Color.WHITE," .Cada rebatida você ganha 50 pontos.", H3);
        texto(5, Y-=15, Color.WHITE," .Você tem um total de 5 vidas, \ncada vez que a bola passar do bastão, você perde 1 vida", H3);
        texto(5, Y-=50, Color.WHITE," .Primeira Etapa", H2);
        texto(5, Y-=20, Color.WHITE," .Faça 200 pontos para avançar de fase.", H3);
        texto(5, Y-=50, Color.WHITE,"Segunda Etapa", H2);
        texto(5, Y-=20, Color.WHITE," .A velocidade será maior.", H3);
        texto(5, Y-=15, Color.WHITE," .E aparecerá um obstáculo na tela, no qual quando a boa tocar nela mudará a trajetória,", H3);
        volta();
    }

    public void jogo(String string) {
        texto(15, 560, Color.BLUE, string, H3);
    }

    public void fase2() {
        texto(230, 300, Color.RED, "2ª FASE", H1);
    }

    public void pontos(int pontuacao) {
        texto(280, 555, Color.WHITE, "Score" + pontuacao, H2);
    }

    public void pausa() {
        texto(225, 300, Color.GRAY, "PAUSE", H1);
    }

    public void perdeu() {
        texto(175, 300, Color.PINK, "GAME OVER 2", H1);
    }

    public void venceu() {
        texto(175, 300, Color.PINK, "Você venceu!", H1);
    }
}
