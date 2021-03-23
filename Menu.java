package cena;

import com.jogamp.opengl.util.awt.TextRenderer;
import java.awt.*;

public class Menu {
    private TextRenderer textRenderer;//Letreiro
    private int H1 = 40;
    private int H2 = 20;
    private int H3 = 15;



    private void texto(int x, int y, Color cor, String string, int size) {
        textRenderer = new TextRenderer(new Font("Arial Negrito", Font.PLAIN, size));
        textRenderer.beginRendering(Renderer.screenWidth, Renderer.screenHeight);
        textRenderer.setColor(cor);
        textRenderer.draw(string, x, y);
        textRenderer.endRendering();
    }

    public void telaDeInicio() {
        texto(150, 300, Color.WHITE, "PONG", 100);
        texto(10, 580, Color.RED, "(Esc) Sair", H3);
        texto(10, 40, Color.GREEN, "(Enter) Iniciar", H3);
        texto(10, 20, Color.BLUE, "(Espaco) Instrucoes", H3);
    }

    public void instrucoes() {
        int y = 500;
        texto(200, y, Color.WHITE,"Instrucoes:", H1);
        texto(5, y -= 70, Color.WHITE,"Comandos:", H2);
        texto(5, y -= 20, Color.WHITE," Utilize as teclas de esquerda e direita, respectivamente para movimentar.", H3);
        texto(5, y -= 15, Color.WHITE,"(Espaco) Vai para as instrucoes do jogo.", H3);
        texto(5, y -= 15, Color.WHITE,"(Enter) Comeca o jogo.", H3);
        texto(5, y -= 15, Color.WHITE,"(P) Pausa o jogo.", H3);
        texto(5, y -= 15, Color.WHITE,"(BackSpace) Volta ao menu principal.", H3);
        texto(5, y -= 15, Color.WHITE,"(Esc) Sai do jogo.", H3);
        texto(5, y -= 50, Color.WHITE,"O Jogo:", H2);
        texto(5, y -= 20, Color.WHITE,"Cada rebatida voce ganha 50 pontos.", H3);
        texto(5, y -= 15, Color.WHITE,"Voce tem um total de 5 vidas", H3);
        texto(5, y -= 15, Color.WHITE,"cada vez que a bola passar do bastao, voce perde 1 vida.", H3);
        texto(5, y -= 50, Color.WHITE,"Primeira Etapa", H2);
        texto(5, y -= 20, Color.WHITE,"Faca 200 pontos para avancar de fase.", H3);
        texto(5, y -= 50, Color.WHITE,"Segunda Etapa", H2);
        texto(5, y -= 20, Color.WHITE,"A velocidade sera maior.", H3);
        texto(5, y -= 15, Color.WHITE,"E aparecera um obstaculo na tela", H3);
        texto(5, y -= 15, Color.WHITE,"no qual quando a boa tocar nela mudara a trajetoria.", H3);
        volta();
    }

    public void jogo(String string) {
        texto(540, 580, Color.BLUE, string, H3);
    }

    public void fase2() {
        texto(230, 300, Color.RED, "2ª FASE", H1);
    }

    public void pontos(int pontuacao) {
        texto(270, 585, Color.WHITE, "Score " + pontuacao, H2);
    }

    public void pausa() {
        texto(225, 300, Color.GRAY, "PAUSE", H1);
    }

    public void perdeu() {
        texto(175, 300, Color.PINK, "GAME OVER", H1);
    }

    public void venceu() {
        texto(220, 300, Color.PINK, "Ganhou!", H1);
    }
    
    private void volta() {
        texto(465, 10, Color.RED, "Voltar (BackSpace)", H3);
    }
}
