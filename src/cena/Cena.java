package cena;


import com.jogamp.newt.event.KeyEvent;
import com.jogamp.newt.event.KeyListener;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.util.awt.TextRenderer;
import com.jogamp.opengl.util.gl2.GLUT;

import java.awt.*;


public class Cena extends Bastao implements GLEventListener, KeyListener {

    public TextRenderer textRenderer;
    public Menu menu;
    public Obstaculo obst;

    private static final int FASE_1 = 200;
    private static final int FASE_2 = 200;
    public int pontuacao;
    public static int vida;

    public float xMin, xMax, yMin, yMax, zMin, zMax;

    //direções da bola
    public float posicaoX = 0, posicaoY = 0;//posição da bola
    public float direcaoX, direcaoY;//direção da bola
    public float velocidade = 0.15f;
    public float cX, cY;
    public float raioX = 0.05f;
    public float raioY = 0.075f;

    public int opcao;
    public boolean pausa;
    public boolean obstaculo;

    @Override
    public void init(GLAutoDrawable drawable) {
        //dados iniciais da cena
        GL2 gl = drawable.getGL().getGL2();
        //Estabelece as coordenadas do SRU (Sistema de Referencia do Universo)
        xMin = yMin = zMin = -1;
        xMax = yMax = zMax = 1;
        pontuacao = opcao = 0;
        vida = 5;
        pausa = false;
        obstaculo = false;
        direcaoX = 0.0f; // direção da bola
        direcaoY = -0.01f;// direção da bola
        menu = new Menu();
        obst = new Obstaculo();
        cX = 0;
        cY = 0;

        //Liga iluminacao
        //gl.glEnable(GL2.GL_LIGHT0);
        //gl.glEnable(GL2.GL_LIGHTING);

        textRenderer = new TextRenderer(new Font("Comic Sans MS Negrito", Font.BOLD, 15));
        //Habilita o buffer de profundidade
        gl.glEnable(GL2.GL_DEPTH_TEST);//Para conseguir fazer com que o eixo Z seja ativado dando profundidade a imagem
    }

    public void reset(){
        direcaoX = 0.0f; // direção da bola
        direcaoY = -0.01f;// direção da bola
        cX = 0;
        cY = 0;
    }

    @Override
    public void display(GLAutoDrawable drawable) {

        GL2 gl = drawable.getGL().getGL2();
        gl.glClearColor(0,0,0, 1);

        //projecao ortogonal sem a correcao do aspecto
        gl.glOrtho(-100, 100, -100, 100, -100, 100);
        GLUT glut = new GLUT();

        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
        gl.glLoadIdentity(); //ler a matriz identidade

        gl.glPolygonMode(GL2.GL_FRONT_AND_BACK, GL2.GL_FILL);

        if (pontuacao >= FASE_1) {
            obstaculo = true;
            reset();
            pontuacao = 0;
            opcao = 6;
        }

        switch (opcao) {
            case 0:
                menu.telaDeInicio();
                break;
            case 1:
                menu.instrucoes();
                break;
            case 2:
                menu.pontos(pontuacao);

                if (obstaculo){
                    obst.obstaculo(drawable);
                    menu.jogo("2ª Fase");
                } else {
                    menu.jogo("1ª Fase");
                }
                vida(gl,glut);
                bastao(drawable);
                bola(drawable);
                cX += direcaoX;
                cY += direcaoY;
                break;
            case 3:
                menu.pausa();//TODO mexer nisso pra não ficar igual
                break;
            case 4:
                menu.perdeu();//TODO mexer nisso pra não ficar igual
                break;
            case 5:
                menu.fase2();//TODO mexer nisso pra não ficar igual
                break;
            case 6:
                menu.venceu();//TODO mexer nisso pra não ficar igual
                break;
        }
        gl.glFlush();
    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        //obtem o contexto grafico Opengl
        GL2 gl = drawable.getGL().getGL2();
        //ativa a matriz de projecao
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity(); //ler a matriz identidade
        //projecao ortogonal sem a correcao do aspecto
        gl.glOrtho(xMin, xMax, yMin, yMax, zMin, zMax);
        //ativa a matriz de modelagem
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity(); //ler a matriz identidade
        System.out.println("Reshape: " + width + ", " + height);
    }

    @Override
    public void dispose(GLAutoDrawable drawable) {}

    public void bola(GLAutoDrawable drawable) {
        final GL2 gl = drawable.getGL().getGL2();

        posicaoY = cY + raioY;
       // posicaoX = cX + raioX;
        posicaoX = cX;

        colisao();

        gl.glPushMatrix();
        gl.glColor3f(1, 1, 0);
        gl.glBegin(GL2.GL_POLYGON);
        for (int i = 0; i <= 180; i++) {
            double angulo = 2 * ((Math.PI * i) / 180);
            double x = Math.cos(angulo) * raioX;
            double y = Math.sin(angulo) * raioY;
            gl.glVertex2d(x + cX, y + cY);
        }
        gl.glEnd();
        gl.glPopMatrix();
    }

    private void colisao() {

        if (posicaoY < getY2()) {
            vida = vida - 1;
            if (vida == 1){
                opcao = 4;
            } else {
                reset();
            }
        }

        if (posicaoY >= 1) {//inverte se bater no teto
            direcaoY *= -1;
            System.out.println("Bateu em cima na tela");
        }

        if (posicaoY <= getY1()){
            if (posicaoX >= esquerda && posicaoX < centroEsq){ // bola tem que ir pra diagonal esquerda
                if (posicaoX == 0) {
                    direcaoX = 0.01f;
                    System.out.println("Bateu no meioEsq do bastão");
                }
                direcaoX *= -1;
                direcaoY *= -1;
                pontuacao += 50;
                System.out.println("Bateu na ponta esquerda do bastão");
            } else if ( posicaoX >= centroEsq && posicaoX < centroDir){//bola sobe reto TODO precisa melhorar o calculo centroEsq e centroDir
               /* if (posicaoX == 0.05f){
                    direcaoX = 0;
                }*/
                direcaoX = posicaoX;
                direcaoY *= -1;
                pontuacao += 50;
                System.out.println("Bateu no centro do bastão");
            } else if (posicaoX >= centroDir && posicaoX <= direita){//bola diagonal direita
                if (posicaoX == 0) {
                    direcaoX = -0.01f;
                    System.out.println("Bateu no meioDir do bastão");
                }
                direcaoX *= -1;
                direcaoY *= -1;
                pontuacao += 50;
                System.out.println("Bateu na ponta direita do bastão");
            }
        }

        if (posicaoX >= 1 || posicaoX <= -1) {//se a bola tocar nas laterais inverte a direção da bola
            direcaoX *= -1;
            System.out.println("Bateu na lateral da tela");
        }

        if (obstaculo){
            if (posicaoY >= obst.getBaixo()  && posicaoY <= obst.getCima() &&  posicaoX >= obst.getEsquerda() && posicaoX <= obst.getDireita()){//bater/cima em baixo do obstaculo
                direcaoY *= -1;
                System.out.println("Bateu em baixo/cima no obstaculo");
            } else if (posicaoX <= obst.getDireita() && posicaoX >= obst.getDireita() - 0.01f && posicaoY > obst.getBaixo() && posicaoY < obst.getCima()){// bater na direita
                direcaoX *= -1;
                System.out.println("Bateu em direita no obstaculo");
            } else if (posicaoX >= obst.getEsquerda() && posicaoX <= obst.getEsquerda() + 0.01f && posicaoY > obst.getBaixo() && posicaoY < obst.getCima()) {// bater na esquerda
                direcaoX *= -1;
                System.out.println("Bateu em Esquerda no obstaculo");
            }
        }
        System.out.println("Pontuação " + pontuacao + "          Posição Y " + posicaoY + "             Posição X " + posicaoX + "             Direção Y " + direcaoY + "             Direção X " + direcaoX + "             " +
                "Bastão Esq " + esquerda + "             Bastão CE " + centroEsq + "             Bastão CD " + centroDir + "             Bastão Dir " + direita);
    }

    public void vida(GL2 gl, GLUT glut){
        gl.glPolygonMode(GL2.GL_FRONT_AND_BACK, GL2.GL_LINE);
        //for(int i = 0; i < vida; i++) {
            gl.glPushMatrix();
            gl.glColor3f(0,0,0.8f); //cor do objeto
            //gl.glRotated(ang, 1, 0, 1);
            //gl.glTranslatef(0.5f,0.9f,0.3f);
            //glut.glutSolidTorus(3, 7, 35, 45);
        /*gl.glBegin(GL2.GL_POLYGON);
        gl.glVertex2f(0.5f, 0.95f);
        gl.glVertex2f(0.55f, 0.95f);
        gl.glVertex2f(0.55f, 0.9f);
        gl.glVertex2f(0.5f, 0.9f);
        gl.glEnd();*/

            gl.glPopMatrix();
       // }
        gl.glPolygonMode(GL2.GL_FRONT_AND_BACK, GL2.GL_FILL);
    }

    private void placar() { // mudar para o letreiro final
        TextRenderer prompt = new TextRenderer(new Font("SansSerif", Font.BOLD, 72));
        String winner = "";

        if (pontuacao == FASE_1) {
            winner = "Você";
        }
        else {
            winner = "Perdeu";
        }

        String line1 = "Fim!";
        String line2 = String.format("%s venceu!", winner);

        prompt.beginRendering(1024, 768);
        prompt.setColor(1.0f, 1.0f, 1.0f, 1.0f);
        prompt.draw(line1, 300, 500);
        prompt.draw(line2, 200, 300);
        prompt.endRendering();
    }

    private boolean gameOver() {
       /* if (pontuacao == FASE_2 ) {// OU vidas > que 1
            return true;
        }
        else {
            return false;
        }*/
       return pontuacao == FASE_2;
    }

  /*  public float getLargura() {
        return largura;
    }

    public float getAltura() {
        return altura;
    }*/

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_ESCAPE:
                System.exit(0);
                break;
            case KeyEvent.VK_RIGHT:
                movimentoDireita();
                break;
            case KeyEvent.VK_LEFT:
                movimentoEsquerda();
                break;
            case KeyEvent.VK_SPACE:
                opcao = 1;
                break;
            case KeyEvent.VK_ENTER:
                opcao = 2;
                break;
            case KeyEvent.VK_P:
                pausa = !pausa;
                if (pausa) {
                    opcao = 3;
                } else {
                    opcao = 2;
                }
                break;
            case KeyEvent.VK_BACK_SPACE:
                opcao = 0;
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}
}
