package cena;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;

public class Bastao  {

    private static final float X1 = -0.25f;
    private static final float X2 = 0.25f;
    private static final float Y1 = -0.85f;
    private static final float Y2 = -0.95f;
    private static final float CENTRO = 0.16f;

    public float esquerda;
    public float direita;
    public float centroDir;
    public float centroEsq;
    public float movimento;


    public Bastao() {
        this.esquerda = X1;
        this.direita = X2;
        this.centroEsq = esquerda + CENTRO;
        this.centroDir = direita - CENTRO;
    }


    public void centroDirBastao(){ centroDir = direita - CENTRO; }

    public void centroEsqBastao(){
        centroEsq = esquerda + CENTRO;
    }

    public float movAdd(){
        return movimento = movimento + 0.01f;
    }

    public float movSub(){
       return movimento = movimento - 0.01f;
    }

    public void movimentoDireita( ){
        if (direita < 1){
            esquerda = X1 + movAdd();
            direita = X2 + movAdd();
           centroDirBastao();
           centroEsqBastao();
        }
    }
    public void movimentoEsquerda( ){
        if (esquerda > -1){
            esquerda = X1 + movSub();
            direita = X2 + movSub();
            centroDirBastao();
            centroEsqBastao();
        }
    }

    public void bastao(GLAutoDrawable drawable) {
        final GL2 gl = drawable.getGL().getGL2();

        gl.glPushMatrix();
        gl.glColor3f(0, 0, 0.5f);
        gl.glTranslatef(movimento, 0, 0);
        gl.glBegin(GL2.GL_QUADS);
        gl.glVertex2f(X1, Y1);
        gl.glVertex2f(X2, Y1);
        gl.glVertex2f(X2, Y2);
        gl.glVertex2f(X1, Y2);
        gl.glEnd();
        gl.glPopMatrix();
    }

    public static float getY1() {
        return Y1  + 0.15f;
    }
    public static float getY2() {
        return Y2;
    }
}
