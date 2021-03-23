package cena;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.gl2.GLUT;

public class Vida {

  /*  private float SPHERE = 0.022f;
    private float CONE = 0.05f;*/

    float incr = 0;

    public void vida(GL2 gl, GLUT glut, int vida){
        for(int i = 0; i < vida; i++) {
            vidas(gl, glut);
            //incr += 0.1f;
        }
    }


    /*public void desenhaVida(GL2 gl, GLUT glut, float incr) { // Desenha as vidas, separar em outra classe ******
        SPHERE += 0.0001f;
        CONE += 0.0001f;

        if (SPHERE > 0.030f) {
            SPHERE = 0.022f;
            CONE = 0.05f;
        }

        gl.glPopMatrix();

        gl.glPushMatrix();
        gl.glColor3f(1, 0, 0);
        gl.glTranslatef(POSICAO_VIDA_ESQ_X + incr, POSICAO_VIDA_ESQ_Y, POSICAO_VIDA_Z);
        glut.glutSolidSphere(SPHERE, 10, 10);
        gl.glPopMatrix();

        gl.glPushMatrix();
        gl.glTranslatef(POSICAO_VIDA_DIR_X + incr, POSICAO_VIDA_DIR_Y, POSICAO_VIDA_Z);
        glut.glutSolidSphere(SPHERE, 10, 10);
        gl.glPopMatrix();

        gl.glPushMatrix();
        gl.glRotated(90, 1, 0, 0);
        gl.glTranslatef(POSICAO_VIDA_BASE_X + incr, POSICAO_VIDA_BASE_Y, POSICAO_VIDA_BASE_Z);
        glut.glutSolidCone(CONE-0.02f, CONE, 10, 10);
        gl.glPopMatrix();

        gl.glPopMatrix();

    }*/

    private void vidas(GL2 gl, GLUT glut){

        gl.glColor3f(0,0,0.8f); //cor do objeto
        gl.glPushMatrix();
       // gl.glRotated(ang, 1, 0, 1);
        gl.glTranslatef(0.5125f,0.3f,-0.89f);
        glut.glutSolidTorus(3, 7, 35, 45);
        gl.glPopMatrix();

    }
}
