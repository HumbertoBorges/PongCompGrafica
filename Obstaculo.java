package cena;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;

public class Obstaculo {

    private static final float X1 = -0.5f;
    private static final float X2 = 0.5f;
    private static final float Y1 = 0.6f;
    private static final float Y2 = 0.4f;

    public final float esquerda;
    public final float direita;
    public final float cima;
    public final float baixo;

    public Obstaculo() {
        this.esquerda = X1;
        this.direita = X2;
        this.cima = Y1;
        this.baixo = Y2;
    }

    public void obstaculo(GLAutoDrawable drawable){
        final GL2 gl = drawable.getGL().getGL2();

        gl.glPushMatrix();
        gl.glColor3f(0, 1, 0);
        gl.glBegin(GL2.GL_QUADS);
        gl.glVertex2f(X1,Y1);
        gl.glVertex2f(X2,Y1);
        gl.glVertex2f(X2,Y2);
        gl.glVertex2f(X1,Y2);
        gl.glEnd();
        gl.glPopMatrix();
    }

    public float getEsquerda() {
        return esquerda;
    }

    public float getDireita() {
        return direita;
    }

    public float getCima() {
        return cima  + 0.15f;
    }

    public float getBaixo() {
        return baixo;
    }
}
