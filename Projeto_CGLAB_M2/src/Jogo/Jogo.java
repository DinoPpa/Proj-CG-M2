/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Jogo;

import Classe.*;
import com.jogamp.opengl.util.Animator;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.awt.GLJPanel;
import javax.media.opengl.glu.GLU;
import javax.swing.JFrame;

/**
 *
 * @author Glauco
 */
public class Jogo
        implements GLEventListener {
    
    GLU glu = new GLU();
    
    public static void main(String args[]) {
        new Jogo();
    }
    private double r;
    private boolean mostra=true;
    
    public Jogo() {
        GLJPanel canvas = new GLJPanel();
        canvas.addGLEventListener(this);
        
        JFrame frame = new JFrame("Jogo");
        frame.setSize(700, 700); //define o tamanho da tela
        frame.getContentPane().add(canvas);
        frame.setVisible(true);
        
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                new Thread(new Runnable() {
                    public void run() {
                        System.exit(0);
                    }
                }).start();
            }
        });
        
    }
    
    public void init(GLAutoDrawable glAuto) {
          GL2 gl = glAuto.getGL().getGL2();     
        gl.glClearColor(0.4f, 0.4f, 0.4f, 0.4f); //define a cor de fundo
        gl.glEnable(GL.GL_DEPTH_TEST); //teste de profundidade
        
      

        Animator ani = new Animator(glAuto);
        ani.start();
    }
    Cubo c1 = new Cubo();

    public void display(GLAutoDrawable glAuto) {
        
        GL2 gl = glAuto.getGL().getGL2();
        
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        
        gl.glLoadIdentity(); //Renicia todos acumulativos
        gl.glTranslated(0, 0, -20); //Onde estara a camera em posição
        //Descobrir como deixa a camera de lado para gera um efeito 3D
        
        gl.glRotated(r, 1, 1, 1);
        r = r + 0.05;

        //Chamando o mapa
        //new Mapa().gerarMapa(1, gl);
        
        
        if(mostra)
        c1.cubo(gl);
        
    }
    
    public void reshape(GLAutoDrawable gLAutoDrawable, int x, int y, int w, int h) {
        
        GL2 gl = gLAutoDrawable.getGL().getGL2();        
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluPerspective(60, 1, 1, 30);
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();
        gl.glTranslated(0, 0, -5);
    }

    /*
     public void reshape(GLAutoDrawable drawable, int x, int y, int w, int h) {
        GL2 gl = drawable.getGL().getGL2();

        gl.glViewport(0, 0, w, h);
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        if (w <= h) {
            gl.glOrtho(-1.5, 1.5,
                    -1.5 * (float) h / (float) w,//
                    1.5 * (float) h / (float) w,//
                    -10.0, 10.0
            );
        } else {
            gl.glOrtho(-1.5 * (float) w / (float) h, //
                    1.5 * (float) w / (float) h, //
                    -1.5, 1.5, -10.0, 10.0
            );
        }

        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();
    }*/
    public void displayChanged(GLAutoDrawable arg0, boolean arg1, boolean arg2) {
        
    }
    
    public void dispose(GLAutoDrawable glad) {
        
    }
    
}
