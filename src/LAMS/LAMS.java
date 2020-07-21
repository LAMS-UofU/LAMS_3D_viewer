/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LAMS;

import java.awt.*;
import java.awt.event.*;

import javax.media.opengl.*;
import com.sun.opengl.util.*;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import converter.CartesianCoordinate;
import converter.Face;
import converter.LamToObjConverter;
import converter.Obj3D;
import java.io.File;
import java.util.ArrayList;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author tyjensen
 */
public class LAMS implements GLEventListener, MouseListener, MouseMotionListener,KeyListener{
    ArrayList<CartesianCoordinate> vertices = new ArrayList<CartesianCoordinate>();
    static LamToObjConverter lamConverter = new LamToObjConverter();
    static JFileChooser fileChooser = new JFileChooser();
    static JFrame frame;
    private FileNameExtensionFilter lamFileFilter = new FileNameExtensionFilter("LAMS Output File (.lam)","lam");
    private FileNameExtensionFilter lampFileFilter = new FileNameExtensionFilter("LAMS Project File (.lamp)","lamp");
    private FileNameExtensionFilter objFileFilter = new FileNameExtensionFilter("3D Object File (.obj)","obj");
    private int x=50;
    private int y=50;
    private int z=-50;
    private float transX=0;
    private float transY=0;
    private float transZ=0;
    private float rotx=92.0f;
    private float roty=180.0f;
    private float rotz=0.0f;
    private int mouseButton = 0;
    private GLU glu;
    private float height;
    private float width;
    private float distance=100;
    static boolean pointCloudFlag = false;
    static ArrayList<Obj3D> objects;
    
    public static void main(String[] args){
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LAMS().initComponents();
            }
        });
    }
        
    public void initComponents(){
        objects = new ArrayList<Obj3D>();
        JFrame frame = new JFrame("LAMS 3D Viewer");
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenu helpMenu = new JMenu("Help");
        JMenuItem open = new JMenuItem("Open");
        JMenuItem save = new JMenuItem("Save");
        JMenuItem saveAs = new JMenuItem("Save As");
        JMenuItem importLam = new JMenuItem("Import .lam file");
        JMenuItem importObj = new JMenuItem("Import .obj file");
        JMenuItem exportObj = new JMenuItem("Export .obj file");
        importLam.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                File lamFile = null;
                
                fileChooser.addChoosableFileFilter(lamFileFilter);
                int res = fileChooser.showDialog(frame, "Import");
                if(res==JFileChooser.APPROVE_OPTION){
                    importLamFile(fileChooser.getSelectedFile());
                }
                fileChooser.removeChoosableFileFilter(lamFileFilter);
            }
        });
        open.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                File openFile = null;
                fileChooser.addChoosableFileFilter(lampFileFilter);
                int res = fileChooser.showOpenDialog(frame);
                if(res==JFileChooser.APPROVE_OPTION){
                    
                }
                fileChooser.removeChoosableFileFilter(lampFileFilter);
            }
            
        });
        
        save.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                File saveFile = null;
                fileChooser.addChoosableFileFilter(lampFileFilter);
                int res = fileChooser.showSaveDialog(frame);
                if(res==JFileChooser.APPROVE_OPTION){
                    
                }
                fileChooser.removeChoosableFileFilter(lampFileFilter);
            }
            
        });
        saveAs.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                File saveAsFile = null;
                fileChooser.addChoosableFileFilter(lampFileFilter);
                int res = fileChooser.showDialog(frame, "Save As");
                if(res==JFileChooser.APPROVE_OPTION){
                    
                }
                fileChooser.removeChoosableFileFilter(lampFileFilter);
            }
            
        });
        importObj.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                File lamFile = null;
                fileChooser.addChoosableFileFilter(objFileFilter);
                int res = fileChooser.showDialog(frame, "Import");
                if(res==JFileChooser.APPROVE_OPTION){
                    //importLamFile(lamFile);
                }
                fileChooser.removeChoosableFileFilter(objFileFilter);
            }
        });
        exportObj.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                File objFile = null;
                fileChooser.addChoosableFileFilter(objFileFilter);
                int res = fileChooser.showDialog(frame, "Export");
                if(res==JFileChooser.APPROVE_OPTION){
                    //importLamFile(objFile);
                }
                fileChooser.removeChoosableFileFilter(objFileFilter);
            }
        });
        GLCanvas canvas = new GLCanvas();

        fileMenu.add(open);
        fileMenu.add(save);
        fileMenu.add(saveAs);
        fileMenu.addSeparator();
        fileMenu.add(importLam);
        fileMenu.addSeparator();
        fileMenu.add(importObj);
        fileMenu.add(exportObj);
        menuBar.add(fileMenu);
        menuBar.add(helpMenu);
        canvas.addGLEventListener(new LAMS());
        frame.setJMenuBar(menuBar);
        frame.add(canvas);
        frame.setSize(800, 800);
        this.height=800.0f;
        this.width = 800.0f;
        final Animator animator = new Animator(canvas);
        frame.addWindowListener(new WindowAdapter() {
        public void windowClosing(WindowEvent e) {
          // Run this on another thread than the AWT event queue to
          // make sure the call to Animator.stop() completes before
          // exiting
          new Thread(new Runnable() {
              public void run() {
                animator.stop();
                System.exit(0);
              }
            }).start();
        }
      });
        animator.start();
        frame.show();
    }

    @Override
    public void init(GLAutoDrawable drawable) {
        //float pos[] = { 5.0f, 5.0f, 10.0f, 0.0f };
        
        GL gl = drawable.getGL();
        
        //gl.
        
        //gl.glEnable(GL.GL_DEPTH_TEST);
        gl.glDepthFunc(GL.GL_LEQUAL);
        gl.glShadeModel(GL.GL_SMOOTH);
        gl.glHint(GL.GL_PERSPECTIVE_CORRECTION_HINT, GL.GL_NICEST);
        
        
        drawable.addMouseListener(this);
        drawable.addMouseMotionListener(this);
        drawable.addKeyListener(this);
        
    }

    @Override
    public void display(GLAutoDrawable drawable) {
        GL gl = drawable.getGL();
        GLU glu = new GLU();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);
                
        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();
        float widthHeightRatio = (float) this.width / (float)this.height;
        glu.gluPerspective(45, widthHeightRatio, 1, 10000);
        glu.gluLookAt(0, 0, this.distance, 0, 0, 0, 0, 1, 0);
        gl.glMatrixMode(GL.GL_MODELVIEW);
        gl.glLoadIdentity();
        
        gl.glPushMatrix();
        gl.glRotatef(rotx, 1.0f, 0.0f, 0.0f);
        gl.glRotatef(-roty, 0.0f, 1.0f, 0.0f);
        gl.glRotatef(rotz, 0.0f, 0.0f, 1.0f);
        
        gl.glTranslatef(-this.transX,this.transY,this.transZ);
        
        
        if(this.pointCloudFlag){
            createGrid(drawable.getGL(),1.0f,1.0f,1.0f, 0,100);
            this.drawPointCloud(drawable.getGL(), this.lamConverter.cartesian);
        }
        else{
            createGrid(drawable.getGL(),1.0f,0.0f,0.0f, 100,50);
            this.displayObjects(gl);
        }
        gl.glPopMatrix();
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        GL gl = drawable.getGL();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);
        
        this.height = height;
        this.width = width;
        
        float h = (float)height / (float)width;

        gl.glMatrixMode(GL.GL_PROJECTION);

        System.err.println("GL_VENDOR: " + gl.glGetString(GL.GL_VENDOR));
        System.err.println("GL_RENDERER: " + gl.glGetString(GL.GL_RENDERER));
        System.err.println("GL_VERSION: " + gl.glGetString(GL.GL_VERSION));
        gl.glLoadIdentity();
        gl.glFrustum(-1.0f, 1.0f, -h, h, 5.0f, 60.0f);
        gl.glMatrixMode(GL.GL_MODELVIEW);
        gl.glLoadIdentity();
        gl.glTranslatef(0.0f, 0.0f, -40.0f);
    }

    @Override
    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void displayObjects(GL gl){
        for(Obj3D ob:LAMS.objects){
            CartesianCoordinate cc;
            gl.glColor3f(1,1,1);
            
            for(Face f:ob.faces){
                if(f.isTriangle){
                    gl.glBegin(GL.GL_TRIANGLES);
                    cc = ob.vertices.get(f.v1-1);
                    gl.glVertex3f(cc.x,cc.y,cc.z);
                    cc = ob.vertices.get(f.v2-1);
                    gl.glVertex3f(cc.x,cc.y,cc.z);
                    cc = ob.vertices.get(f.v3-1);
                    gl.glVertex3f(cc.x,cc.y,cc.z);
                    gl.glEnd();
                }
                else{
                    gl.glBegin(GL.GL_QUADS);
                    cc = ob.vertices.get(f.v1-1);
                    gl.glVertex3f(cc.x,cc.y,cc.z);
                    cc = ob.vertices.get(f.v2-1);
                    gl.glVertex3f(cc.x,cc.y,cc.z);
                    cc = ob.vertices.get(f.v3-1);
                    gl.glVertex3f(cc.x,cc.y,cc.z);
                    cc = ob.vertices.get(f.v4-1);
                    gl.glVertex3f(cc.x,cc.y,cc.z);
                    gl.glEnd();
                }
            }
        }
    }
    
    public void drawPointCloud(GL gl, ArrayList<CartesianCoordinate> cartesian){
        gl.glColor3f(1,0,0);
        gl.glPointSize(10);
        gl.glBegin(GL.GL_POINTS);
        for(CartesianCoordinate cc:cartesian){
            gl.glVertex3f(cc.x, cc.y, cc.z);
        }
        gl.glEnd();
    }
    
    public void importLamFile(File lamFile){
        if(!lamFile.equals(null)){
            this.pointCloudFlag=true;
            LAMS.lamConverter.convert(lamFile);
            this.vertices = lamConverter.cartesian;
            JOptionPane.showMessageDialog(frame, "When point cloud review is complete, hit the Enter key to continue.");
        }
    }
    
    public void createGrid(GL gl, float r, float g, float b, int gridSize, int gridLineSize){
        gl.glColor3f(r,g,b);
        gl.glBegin (GL.GL_LINES);
        gl.glVertex3f(0,0,-1000);
        gl.glVertex3f(0,0,1000);
        gl.glEnd();
        gl.glBegin (GL.GL_LINES);
        gl.glVertex3f(0,-1000,0);
        gl.glVertex3f(0,1000,0);
        gl.glEnd();
        gl.glBegin (GL.GL_LINES);
        gl.glVertex3f(-1000,0,0);
        gl.glVertex3f(1000,0,0);
        gl.glEnd();
        
        for(int i = -gridSize/2;i<gridSize/2;i++){
            for(int j = -gridSize/2;j<gridSize/2;j++){
                gl.glBegin (GL.GL_LINES);
                gl.glVertex3f(-gridLineSize, j, 0.0f);
                gl.glVertex3f(gridLineSize, j, 0.0f);
                gl.glEnd();
                gl.glBegin (GL.GL_LINES);
                gl.glVertex3f(i, -gridLineSize, 0.0f);
                gl.glVertex3f(i, gridLineSize, 0.0f);
                gl.glEnd();
            }
        }
        gl.glFlush();
    }
    
    
    
    
  // Methods required for the implementation of MouseMotionListener
  public void mouseDragged(MouseEvent e) {
    if(this.mouseButton==1){
        int newX = e.getX();
        int newY = e.getY();
        Dimension size = e.getComponent().getSize();

        float thetaY = 360.0f * ( (float)(newX-this.x)/((float)size.width));
        float thetaX = 360.0f * ( (float)(newY-this.y)/((float)size.height));

        this.x = newX;
        this.y = newY;

        rotx += thetaX;
        roty += thetaY;
    }
    else if(this.mouseButton==3){
        int newX = e.getX();
        int newY = e.getY();
        this.transX += ((float)(newX-this.x))/10;
        this.transY += ((float)(newY-this.y))/10;
        this.x = newX;
        this.y = newY;
    }
  }
    
    public void mouseMoved(MouseEvent e) {
       
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mousePressed(MouseEvent me) {
        this.mouseButton = me.getButton();
        this.x=me.getX();
        this.y=me.getY();
        this.z=me.getY();
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent me) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyTyped(KeyEvent ke) {
        //System.out.println(ke.getKeyCode());
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        switch(ke.getKeyCode()){
                case 40:
                    this.transZ -=1;
                    break;
                case 38:
                    this.transZ +=1;
                    break;
                case 39:
                    this.rotz +=1;
                    break;
                case 37:
                    this.rotz -=1;
                    break;
                case 81:
                    this.distance+=1;
                    break;
                case 87:
                    this.distance-=1;
                    break;
                    
        }
                
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        switch(ke.getKeyCode()){
            case 10:
                if(LAMS.pointCloudFlag){
                    LAMS.pointCloudFlag = false;
                    LAMS.lamConverter.createFaces();
                    LAMS.objects.add(LAMS.lamConverter.getObj());
                }
                
                break;
        }
    }
    
    
}
