/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Jogo;

import Classe.*;
import com.jogamp.opengl.util.Animator;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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
            implements GLEventListener,
            KeyListener {

    GLU glu = new GLU();
    
    public static void main(String args[]) {
        new Jogo();
    }
    
    private boolean up = false;
    private boolean down = false;
    private boolean right = false;
    private boolean left = false;
    private boolean controlEnable = true; //Controla que o usuario nao devera fazer nenhum movimento, até acaba animação
    private boolean E = false;
    private boolean R = false;
    private boolean D = false;
    private boolean F = false;
    private Cubo c = new Cubo();
    private Mapa m = new Mapa();
    private int fase = 1;
    private boolean mostraMenu = false;
    
    public Jogo() {
        GLJPanel canvas = new GLJPanel();
        canvas.addGLEventListener(this);
        
        JFrame frame = new JFrame("Jogo");
        frame.setSize(700, 700); //define o tamanho da tela
        frame.getContentPane().add(canvas);
        frame.setVisible(true);
        frame.addKeyListener(this);
        
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
    
    @Override
    public void init(GLAutoDrawable glAuto) {
        Animator a = new Animator(glAuto);
        a.start();
        
        GL gl = glAuto.getGL(); 
        gl.glClearColor(0.4f, 0.4f, 0.4f, 0.4f); //define a cor de fundo
        gl.glEnable(GL.GL_DEPTH_TEST); //teste de profundidade
        
    }

    public void display(GLAutoDrawable glAuto) {
        
        GL2 gl = glAuto.getGL().getGL2();
        
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        
        gl.glLoadIdentity(); //Renicia todos acumulativos
        gl.glTranslated(0,0,-15); //Onde estara a camera em posição
        
        botoesCamera(gl); //rotaciona o mapa de acordo com o usuario (A,S,D,F,G)
        
        if (mostraMenu) // obs.: MostraMenu esta false, pois precisa dá parte da Eloa
        {
            //MENU
            //OBS.: Fase é qual mapa deve ser gerado
        }
        
        //OBS.: o 0,0,0 sempre estara no centro do quadrado azul (inicio) e sua distancia em outros quadrados é 1
        m.gerarMapa(fase, gl);
        c.gerarCubo(gl);
        
        if (controlEnable) 
        {
            VerificarDerrota();
            VerificarVitoria();
        }
        
        movimentacao(gl);
        
    }
    
    public void reshape(GLAutoDrawable gLAutoDrawable, int x, int y, int w, int h) 
    {  
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

    @Override
    public void keyTyped(KeyEvent e) {
    
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
        if (controlEnable) //Controle do jogador no cubo
        {
            controlEnable = false;
            switch (e.getKeyCode()) 
            {
                case KeyEvent.VK_UP:
                    up = true;
                    break;

                case KeyEvent.VK_DOWN:
                    down = true;
                    break;

                case KeyEvent.VK_RIGHT:
                    right = true;
                    break;

                case KeyEvent.VK_LEFT:
                    left = true;
                    break;

                default:
                    controlEnable = true;
                    break;
            }
        }
        
        switch (e.getKeyCode()) //Controle de camera do jogador
        {
            case KeyEvent.VK_R:
                restartCamera();
                R = true;
                break;

            case KeyEvent.VK_E:
                restartCamera();
                E = true;
                break;

            case KeyEvent.VK_D:
                restartCamera();
                D = true;
                break;

            case KeyEvent.VK_F:
                restartCamera();
                F = true;
                break;
                
            case KeyEvent.VK_G:
                restartCamera();
                break;
            default:
                break;
        }
        
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
    
    }
    
    //Renicia o boolean relacionado a camera
    private void restartCamera()
    {
        E = false;
        R = false;
        D = false;
        F = false;
    }
    
    //Renicia o boolean relacionado a controle
    private void restartControle()
    {
        up = false;
        down = false;
        left = false;
        right = false;
        controlEnable = true;
    }

    //Realiza as ações da camera 
    private void botoesCamera(GL2 gl) 
    {
        if (F) {
            gl.glRotated(-45, 1, 0, 0);
            gl.glRotated(-45, 0, 0, 1);
        } 
        else if (D){
            gl.glRotated(-45, 1, 0, 0);
            gl.glRotated(45, 0, 0, 1);
        }
        else if (E){
            gl.glRotated(-45, 1, 0, 0);
            gl.glRotated(135, 0, 0, 1);
        }
        else if (R){
            gl.glRotated(-45, 1, 0, 0);
            gl.glRotated(-135, 0, 0, 1);
        } 
    }

    private void movimentacao(GL2 gl) 
    {
        if (!controlEnable) 
        {
            if (up) 
            {
                c.moverFrente(gl);
            } 
            else if(right) 
            {
                c.moverDireita(gl);
            } 
            else if (left)
            {
                c.moverEsquerda(gl);
            }
            else if (down)
            {
                c.moverTras(gl);
            }
            else
            {
                throw new UnsupportedOperationException("ERRO - Controle inativo e nenhuma animação permetida");
            }
            
            restartControle();
        }
    }

    //Esta parte ira confirma se o jogador ganhou ou nao
    private void VerificarVitoria() 
    {
        
    }
    
    //Esta parte ira confirma se o jogador derrubou o cubo
    private void VerificarDerrota() 
    {
        
    }
}
