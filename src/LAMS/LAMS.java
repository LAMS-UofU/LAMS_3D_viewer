/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LAMS;


import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.Animator;
import converter.CartesianCoordinate;
import converter.CartesianVector;
import converter.Face;
import converter.FaceVertex;
import converter.LamToObjConverter;
import converter.Obj3D;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
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
    private JFrame viewSettingsFrame;
    static GLCanvas canvas;
    private FileNameExtensionFilter lamFileFilter = new FileNameExtensionFilter("LAMS Output File (.lam)","lam");
    private FileNameExtensionFilter lampFileFilter = new FileNameExtensionFilter("LAMS Project File (.lamp)","lamp");
    private FileNameExtensionFilter objFileFilter = new FileNameExtensionFilter("3D Object File (.obj)","obj");
    private int x=50;
    private int y=50;
    private int z=-50;
    private int posX=50;
    private int posY=50;
    private int posZ=50;
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
    static Color backgroundColorObjViewer = Color.BLACK;
    static Color xAxisColorObjViewer = Color.GREEN;
    static Color yAxisColorObjViewer = Color.BLUE;
    static Color zAxisColorObjViewer = Color.RED;
    static Color xyGridColorObjViewer = Color.LIGHT_GRAY;
    static Color spineColorObjViewer = Color.ORANGE;
    static boolean xAxisEnableObjViewer = false;
    static boolean yAxisEnableObjViewer = false;
    static boolean zAxisEnableObjViewer = true;
    static boolean xyGridEnableObjViewer = true;
    static boolean spineEnableObjViewer = false;
    
    
    static Color backgroundColorPointCloudViewer = Color.BLACK;
    static Color pointColorPointCloudViewer = Color.RED;
    static Color xAxisColorPointCloudViewer = Color.GREEN;
    static Color yAxisColorPointCloudViewer = Color.BLUE;
    static Color zAxisColorPointCloudViewer = Color.RED;
    static Color xyGridColorPointCloudViewer = Color.LIGHT_GRAY;
    static boolean xAxisEnablePointCloudViewer = false;
    static boolean yAxisEnablePointCloudViewer = false;
    static boolean zAxisEnablePointCloudViewer = true;
    static boolean xyGridEnablePointCloudViewer = false;
    static boolean objectInteriorView = false;
    static Obj3D focusedObject;
    
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
        viewSettingsFrame = new JFrame("View Options");
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenu editMenu = new JMenu("Edit");
        JMenu helpMenu = new JMenu("Help");
        JMenu viewMenu = new JMenu("View");
        JMenuItem open = new JMenuItem("Open");
        JMenuItem save = new JMenuItem("Save");
        JMenuItem saveAs = new JMenuItem("Save As");
        JMenuItem importLam = new JMenuItem("Import .lam file");
        JMenuItem importObj = new JMenuItem("Import .obj file");
        JMenuItem exportObj = new JMenuItem("Export .obj file");
        JMenuItem editObjects = new JMenuItem("Objects");
        JMenuItem viewSettings = new JMenuItem("Settings");
        JMenuItem viewToggleInteriorView = new JMenuItem("Toggle Interior View");
        JMenuItem helpTest = new JMenuItem("test");
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
                    importObjFile(LAMS.fileChooser.getSelectedFile());
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
        viewToggleInteriorView.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                if(LAMS.objectInteriorView){
                    LAMS.objectInteriorView = false;
                    //LAMS.spineEnableObjViewer = false;
                    
                }
                else{
                    LAMS.objectInteriorView = true;
                    //LAMS.spineEnableObjViewer = true;
                }
            }
            
        });
        helpTest.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                System.out.println(transX + transY + transZ);
            }
            
        });
        viewSettings.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                String s = LAMS.backgroundColorObjViewer.getRed() + "," + LAMS.backgroundColorObjViewer.getGreen() + "," + LAMS.backgroundColorObjViewer.getBlue() + ':' + 
                        LAMS.xAxisColorObjViewer.getRed() + "," + LAMS.xAxisColorObjViewer.getGreen() + "," + LAMS.xAxisColorObjViewer.getBlue() + ':' + 
                        LAMS.yAxisColorObjViewer.getRed() + "," + LAMS.yAxisColorObjViewer.getGreen() + "," + LAMS.yAxisColorObjViewer.getBlue() + ':' +
                        LAMS.zAxisColorObjViewer.getRed() + "," + LAMS.zAxisColorObjViewer.getGreen() + "," + LAMS.zAxisColorObjViewer.getBlue() + ':' +
                        LAMS.xyGridColorObjViewer.getRed() + "," + LAMS.xyGridColorObjViewer.getGreen() + "," + LAMS.xyGridColorObjViewer.getBlue() + ':' +
                        LAMS.spineColorObjViewer.getRed() + "," + LAMS.spineColorObjViewer.getGreen() + "," + LAMS.spineColorObjViewer.getBlue() + ':' +
                        LAMS.backgroundColorPointCloudViewer.getRed() + "," + LAMS.backgroundColorPointCloudViewer.getGreen() + "," + LAMS.backgroundColorPointCloudViewer.getBlue() + ':' +
                        LAMS.pointColorPointCloudViewer.getRed() + "," + LAMS.pointColorPointCloudViewer.getGreen() + "," + LAMS.pointColorPointCloudViewer.getBlue() + ':' +
                        LAMS.xAxisColorPointCloudViewer.getRed() + "," + LAMS.xAxisColorPointCloudViewer.getGreen() + "," + LAMS.xAxisColorPointCloudViewer.getBlue() + ':' +
                        LAMS.yAxisColorPointCloudViewer.getRed() + "," + LAMS.yAxisColorPointCloudViewer.getGreen() + "," + LAMS.yAxisColorPointCloudViewer.getBlue() + ':' +
                        LAMS.zAxisColorPointCloudViewer.getRed() + "," + LAMS.zAxisColorPointCloudViewer.getGreen() + "," + LAMS.zAxisColorPointCloudViewer.getBlue() + ':' +
                        LAMS.xyGridColorPointCloudViewer.getRed() + "," + LAMS.xyGridColorPointCloudViewer.getGreen() + "," + LAMS.xyGridColorPointCloudViewer.getBlue();
                        
                java.awt.EventQueue.invokeLater(new Runnable() {
                    public void run() {
                        
                        new ViewSettings(s).setVisible(true);
                    }
                });
            }
        });
        LAMS.canvas = new GLCanvas();

        fileMenu.add(open);
        fileMenu.add(save);
        fileMenu.add(saveAs);
        fileMenu.addSeparator();
        fileMenu.add(importLam);
        fileMenu.addSeparator();
        fileMenu.add(importObj);
        fileMenu.add(exportObj);
        editMenu.add(editObjects);
        viewMenu.add(viewToggleInteriorView);
        viewMenu.add(viewSettings);
        helpMenu.add(helpTest);
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(viewMenu);
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
        
        GL2 gl = drawable.getGL().getGL2();
        
        //gl.
        //float pos[] = { 50.0f, 50.0f, 100.0f, 0.0f };
        //gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_POSITION, pos, 0);
        gl.glEnable(GL2.GL_CULL_FACE);
        //gl.glEnable(GL2.GL_LIGHTING);
        gl.glEnable(GL2.GL_LIGHT0);
        /*gl.glEnable(GL2.GL_DEPTH_TEST);*/
        
        gl.glDepthFunc(GL2.GL_LEQUAL);
        //gl.glShadeModel(GL2.GL_SMOOTH);
        gl.glEnable(GL2.GL_NORMALIZE);
        
        //gl.glHint(GL.GL_PERSPECTIVE_CORRECTION_HINT, GL.GL_NICEST);
        
        
        LAMS.canvas.addMouseListener(this);
        LAMS.canvas.addMouseMotionListener(this);
        LAMS.canvas.addKeyListener(this);
        
    }

    @Override
    public void display(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        GLU glu = new GLU();
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT);
        if(LAMS.pointCloudFlag){
            gl.glClearColor(this.backgroundColorPointCloudViewer.getRed(), this.backgroundColorPointCloudViewer.getGreen(), this.backgroundColorPointCloudViewer.getBlue(), 0f);
        }
        else{
            gl.glClearColor(this.backgroundColorObjViewer.getRed(), this.backgroundColorObjViewer.getGreen(), this.backgroundColorObjViewer.getBlue(), 0f);
        }
        
        
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        float widthHeightRatio = (float) this.width / (float)this.height;
        glu.gluPerspective(45, widthHeightRatio, 1, 10000);
        if(LAMS.objectInteriorView){
            if(LAMS.focusedObject!=null){
                glu.gluLookAt(LAMS.focusedObject.center.x,(LAMS.focusedObject.center.z-LAMS.focusedObject.minZ.z),LAMS.focusedObject.maxY.y-15,LAMS.focusedObject.center.x,LAMS.focusedObject.center.z-LAMS.focusedObject.minZ.z,LAMS.focusedObject.maxY.y, 0, 0.5, 0.5);
                gl.glDisable(GL2.GL_CULL_FACE);
                gl.glEnable( GL2.GL_LIGHT2 );  
                gl.glLightfv(GL2.GL_LIGHT2, GL2.GL_POSITION, new float[]{LAMS.focusedObject.center.x,LAMS.focusedObject.maxY.y,LAMS.focusedObject.maxZ.z-LAMS.focusedObject.minZ.z,1.0f}, 0);
                //gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_POSITION, new float[]{-posX,-posY,posZ,1.0f}, 0);
                gl.glLightfv(GL2.GL_LIGHT2, GL2.GL_SPECULAR, new float[]{1f, 1f, 1f, 0.5f}, 0);
                gl.glLightfv(GL2.GL_LIGHT2, GL2.GL_EMISSION, new float[]{1f, 1f, 1f, 0.5f}, 0);
                gl.glLightfv(GL2.GL_LIGHT2, GL2.GL_AMBIENT, new float[]{0.5f, 0.5f, 0.5f, 0.5f}, 0);
                gl.glLightfv(GL2.GL_LIGHT2, GL2.GL_DIFFUSE, new float[]{1f, 1f, 1f, 0.5f}, 0);
                //gl.glCullFace(GL2.GL_FRONT);
            }
            else{
                glu.gluLookAt(0, 0, this.distance, 0, 0, 0, 0, 1, 0);
                gl.glEnable(GL2.GL_CULL_FACE);
            }
        }
        else{
            glu.gluLookAt(0, 0, this.distance, 0, 0, 0, 0, 1, 0);
            gl.glEnable(GL2.GL_CULL_FACE);
        }
        
        //glu.gluLookAt(0, 0, this.distance, 0, 0, 0, 0, 1, 0);
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();
        
        gl.glPushMatrix();
        if(!LAMS.objectInteriorView){
            gl.glTranslatef(this.transX,-this.transY,this.transZ);
        }
        
        gl.glRotatef(rotx, 1.0f, 0.0f, 0.0f);
        gl.glRotatef(-roty, 0.0f, 1.0f, 0.0f);
        gl.glRotatef(rotz, 0.0f, 0.0f, 1.0f);
        
        //gl.glTranslatef(-this.transX,this.transY,this.transZ);
        
        posX+=transX;
        posY+=transY;
        posZ+=transZ;
        
        
        if(this.pointCloudFlag){
            gl.glDisable(GL2.GL_LIGHTING);
            createGrid(drawable.getGL().getGL2(),50,25);
            this.drawPointCloud(drawable.getGL().getGL2(), this.lamConverter.cartesian);
        }
        else{
            gl.glDisable(GL2.GL_LIGHTING);
            createGrid(drawable.getGL().getGL2(), 50,25);
            /*gl.glEnable( GL2.GL_LIGHTING );  
            gl.glEnable( GL2.GL_LIGHT0 );  
            gl.glEnable( GL2.GL_NORMALIZE );
            // weak RED ambient 
            float[] ambientLight = { 0.1f, 0.f, 0.f,0f };  
            gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_AMBIENT, ambientLight, 0);
            // multicolor diffuse 
            float[] diffuseLight = { 1f,2f,1f,0f };  
            gl.glLightfv( GL2.GL_LIGHT0, GL2.GL_DIFFUSE, diffuseLight, 0 ); */
            
            gl.glEnable( GL2.GL_LIGHTING );  
            gl.glEnable( GL2.GL_LIGHT0 );  
            gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_POSITION, new float[]{50f,-100f,75f,1.0f}, 0);
            //gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_POSITION, new float[]{-posX,-posY,posZ,1.0f}, 0);
            gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_SPECULAR, new float[]{1f, 1f, 1f, 0.5f}, 0);
            gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_EMISSION, new float[]{1f, 1f, 1f, 0.5f}, 0);
            gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_AMBIENT, new float[]{0.5f, 0.5f, 0.5f, 0.5f}, 0);
            gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_DIFFUSE, new float[]{1f, 1f, 1f, 0.5f}, 0);
            
            gl.glEnable( GL2.GL_LIGHT1 );  
            gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_POSITION, new float[]{-50f,100f,-75f,1.0f}, 0);
            //gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_POSITION, new float[]{-posX,-posY,posZ,1.0f}, 0);
            gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_SPECULAR, new float[]{1f, 1f, 1f, 0.5f}, 0);
            gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_EMISSION, new float[]{1f, 1f, 1f, 0.5f}, 0);
            gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_AMBIENT, new float[]{0.5f, 0.5f, 0.5f, 0.5f}, 0);
            gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_DIFFUSE, new float[]{1f, 1f, 1f, 0.5f}, 0);
            
            
            /**/
            this.displayObjects(gl);
        }
        gl.glPopMatrix();
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        GL2 gl = drawable.getGL().getGL2();
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT);
        
        this.height = height;
        this.width = width;
        
        float h = (float)height / (float)width;

        gl.glMatrixMode(GL2.GL_PROJECTION);

        System.err.println("GL_VENDOR: " + gl.glGetString(GL2.GL_VENDOR));
        System.err.println("GL_RENDERER: " + gl.glGetString(GL2.GL_RENDERER));
        System.err.println("GL_VERSION: " + gl.glGetString(GL2.GL_VERSION));
        gl.glLoadIdentity();
        gl.glFrustum(-1.0f, 1.0f, -h, h, 5.0f, 60.0f);
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();
        gl.glTranslatef(0.0f, 0.0f, -40.0f);
    }

//    @Override
//    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
//        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
    
    public void displayObjects(GL2 gl){
        
        
        for(Obj3D ob:LAMS.objects){
            float offset = ob.minZ.z*-1;
            
            /*if(LAMS.objectInteriorView){
                gl.glDisable(GL2.GL_CULL_FACE);
            }
            else{
                gl.glEnable(GL2.GL_CULL_FACE);
            }*/
            gl.glEnable(GL2.GL_LIGHTING);
            CartesianCoordinate cc;
            CartesianVector cv;
            gl.glColor3f(1,1,1);
            
            for(Face f:ob.faces){
                
                if(f.isTriangle){
                    //gl.glCullFace(GL2.GL_FRONT);
                    gl.glBegin(GL2.GL_TRIANGLES);
                    cv = f.faceNormal;
                    gl.glNormal3f(cv.x,cv.y,cv.z);
                    //gl.glNormal3f(-cv.x,-cv.y,-cv.z);
                    cc = f.v1.vertex;
                    gl.glVertex3f(cc.x,cc.y,cc.z+offset);
                    gl.glNormal3f(cv.x,cv.y,cv.z);
                    //gl.glNormal3f(-cv.x,-cv.y,-cv.z);
                    cc = f.v2.vertex;
                    gl.glVertex3f(cc.x,cc.y,cc.z+offset);
                    gl.glNormal3f(cv.x,cv.y,cv.z);
                    //gl.glNormal3f(-cv.x,-cv.y,-cv.z);
                    cc = f.v3.vertex;
                    gl.glVertex3f(cc.x,cc.y,cc.z+offset);
                    gl.glEnd();
                    //gl.glCullFace(GL2.GL_BACK);
                }
                else{
                    //gl.glCullFace(GL2.GL_FRONT);
                    gl.glBegin(GL2.GL_QUADS);
                    cv = f.faceNormal;
                    gl.glNormal3f(cv.x,cv.y,cv.z);
                    //gl.glNormal3f(-cv.x,-cv.y,-cv.z);
                    cc = f.v1.vertex;
                    gl.glVertex3f(cc.x,cc.y,cc.z+offset);
                    gl.glNormal3f(cv.x,cv.y,cv.z);
                    //gl.glNormal3f(-cv.x,-cv.y,-cv.z);
                    cc = f.v2.vertex;
                    gl.glVertex3f(cc.x,cc.y,cc.z+offset);
                    gl.glNormal3f(cv.x,cv.y,cv.z);
                    //gl.glNormal3f(-cv.x,-cv.y,-cv.z);
                    cc = f.v3.vertex;
                    gl.glVertex3f(cc.x,cc.y,cc.z+offset);
                    gl.glNormal3f(cv.x,cv.y,cv.z);
                    //gl.glNormal3f(-cv.x,-cv.y,-cv.z);
                    cc = f.v4.vertex;
                    gl.glVertex3f(cc.x,cc.y,cc.z+offset);
                    gl.glEnd();
                    //gl.glCullFace(GL2.GL_BACK);
                }
            }
            gl.glDisable(GL2.GL_LIGHTING);
            gl.glEnable(GL2.GL_CULL_FACE);
            if(LAMS.spineEnableObjViewer){
                for(int i = 0;i<ob.spine.size();i++){
                    gl.glColor3f(LAMS.spineColorObjViewer.getRed()/255.0f,LAMS.spineColorObjViewer.getGreen()/255.0f,LAMS.spineColorObjViewer.getBlue()/255.0f);
                    gl.glPointSize(10);
                    gl.glBegin(GL2.GL_POINTS);
                    gl.glVertex3f(ob.spine.get(i).x, ob.spine.get(i).y, ob.spine.get(i).z+offset);
                    gl.glEnd();
                    if(ob.spine.size()>1 && i>0){
                        gl.glBegin(GL2.GL_LINES);
                            gl.glVertex3f(ob.spine.get(i).x, ob.spine.get(i).y, ob.spine.get(i).z+offset);
                            gl.glVertex3f(ob.spine.get(i-1).x, ob.spine.get(i-1).y, ob.spine.get(i-1).z+offset);
                        gl.glEnd();
                    }
                    
                }
            }
        }
    }
    
    public void drawPointCloud(GL2 gl, ArrayList<CartesianCoordinate> cartesian){
        gl.glColor3f(LAMS.pointColorPointCloudViewer.getRed()/255.0f,LAMS.pointColorPointCloudViewer.getGreen()/255.0f,LAMS.pointColorPointCloudViewer.getBlue()/255.0f);
        gl.glPointSize(10);
        gl.glBegin(GL2.GL_POINTS);
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
    
    public void createGrid(GL2 gl, int gridSize, int gridLineSize){
        if(LAMS.pointCloudFlag && LAMS.xyGridEnablePointCloudViewer){
            gl.glColor3f(LAMS.xyGridColorPointCloudViewer.getRed()/255.0f,LAMS.xyGridColorPointCloudViewer.getGreen()/255.0f,LAMS.xyGridColorPointCloudViewer.getBlue()/255.0f);
            for(int i = -gridSize/2;i<gridSize/2;i++){
                for(int j = -gridSize/2;j<gridSize/2;j++){
                    gl.glBegin (GL2.GL_LINES);
                    gl.glVertex3f(-gridLineSize, j, 0.0f);
                    gl.glVertex3f(gridLineSize, j, 0.0f);
                    gl.glEnd();
                    gl.glBegin (GL2.GL_LINES);
                    gl.glVertex3f(i, -gridLineSize, 0.0f);
                    gl.glVertex3f(i, gridLineSize, 0.0f);
                    gl.glEnd();
                }
            }
        }
        else if (LAMS.xyGridEnableObjViewer && !LAMS.pointCloudFlag){
            gl.glColor3f(LAMS.xyGridColorObjViewer.getRed()/255.0f,LAMS.xyGridColorObjViewer.getGreen()/255.0f,LAMS.xyGridColorObjViewer.getBlue()/255.0f);
            for(int i = -gridSize/2;i<gridSize/2;i++){
                for(int j = -gridSize/2;j<gridSize/2;j++){
                    gl.glBegin (GL2.GL_LINES);
                    gl.glVertex3f(-gridLineSize, j, 0.0f);
                    gl.glVertex3f(gridLineSize, j, 0.0f);
                    gl.glEnd();
                    gl.glBegin (GL2.GL_LINES);
                    gl.glVertex3f(i, -gridLineSize, 0.0f);
                    gl.glVertex3f(i, gridLineSize, 0.0f);
                    gl.glEnd();
                }
            }
        }
        if(LAMS.pointCloudFlag && LAMS.zAxisEnablePointCloudViewer){
            gl.glColor3f(LAMS.zAxisColorPointCloudViewer.getRed()/255.0f,LAMS.zAxisColorPointCloudViewer.getGreen()/255.0f,LAMS.zAxisColorPointCloudViewer.getBlue()/255.0f);
            gl.glBegin (GL2.GL_LINES);
            gl.glVertex3f(0,0,-1000);
            gl.glVertex3f(0,0,1000);
            gl.glEnd();
        }
        else if(LAMS.zAxisEnableObjViewer && !LAMS.pointCloudFlag){
            gl.glColor3f(LAMS.zAxisColorObjViewer.getRed()/255.0f,LAMS.zAxisColorObjViewer.getGreen()/255.0f,LAMS.zAxisColorObjViewer.getBlue()/255.0f);
            gl.glBegin (GL2.GL_LINES);
            gl.glVertex3f(0,0,-1000);
            gl.glVertex3f(0,0,1000);
            gl.glEnd();
        }
        if(LAMS.pointCloudFlag && LAMS.yAxisEnablePointCloudViewer){
            gl.glColor3f(LAMS.yAxisColorPointCloudViewer.getRed()/255.0f,LAMS.yAxisColorPointCloudViewer.getGreen()/255.0f,LAMS.yAxisColorPointCloudViewer.getBlue()/255.0f);
            gl.glBegin (GL2.GL_LINES);
            gl.glVertex3f(0,-1000,0);
            gl.glVertex3f(0,1000,0);
            gl.glEnd();
        }
        else if(LAMS.yAxisEnableObjViewer && !LAMS.pointCloudFlag){
            gl.glColor3f(LAMS.yAxisColorObjViewer.getRed()/255.0f,LAMS.yAxisColorObjViewer.getGreen()/255.0f,LAMS.yAxisColorObjViewer.getBlue()/255.0f);
            gl.glBegin (GL2.GL_LINES);
            gl.glVertex3f(0,-1000,0);
            gl.glVertex3f(0,1000,0);
            gl.glEnd();
        }
        if(LAMS.pointCloudFlag && LAMS.xAxisEnablePointCloudViewer){
            gl.glColor3f(LAMS.xAxisColorPointCloudViewer.getRed()/255.0f,LAMS.xAxisColorPointCloudViewer.getGreen()/255.0f,LAMS.xAxisColorPointCloudViewer.getBlue()/255.0f);
            gl.glBegin (GL2.GL_LINES);
            gl.glVertex3f(-1000,0,0);
            gl.glVertex3f(1000,0,0);
            gl.glEnd();
        }
        else if(LAMS.xAxisEnableObjViewer && !LAMS.pointCloudFlag){
            gl.glColor3f(LAMS.xAxisColorObjViewer.getRed()/255.0f,LAMS.xAxisColorObjViewer.getGreen()/255.0f,LAMS.xAxisColorObjViewer.getBlue()/255.0f);
            gl.glBegin (GL2.GL_LINES);
            gl.glVertex3f(-1000,0,0);
            gl.glVertex3f(1000,0,0);
            gl.glEnd();
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
                    if(LAMS.objectInteriorView){
                        this.rotx -=1;
                    }
                    else{
                        this.transZ -=1;
                    }
                    break;
                case 38:
                    if(LAMS.objectInteriorView){
                        this.rotx +=1;
                    }
                    else{
                        this.transZ +=1;
                    }
                    break;
                case 39:
                    this.rotz +=1;
                    break;
                case 37:
                    this.rotz -=1;
                    break;
                case 81:
                    this.distance+=1;
                    //this.transY+=1;
                    break;
                case 87:
                    this.distance-=1;
                    //this.transY-=1;
                    break;
                case 27:
                    System.out.println("esc");
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
            default:
                break;
        }
    }
    
    public void importObjFile(File objFile){
        try {
            Obj3D obj = new Obj3D();
            Map<Float,ArrayList<CartesianCoordinate>> xSlices = new HashMap<Float,ArrayList<CartesianCoordinate>>();
            String str;
            String [] temp;
            Scanner s = new Scanner(objFile);
            while(s.hasNextLine()){
                str = s.nextLine();
                if(!str.equals("")){
                    str = str.replace('\t', ' ');
                    str = str.replace("  ", " ");
                    str = str.replace("  ", " ");
                    temp = str.split(" ");
                    if(temp[0].equals("v")){
                        CartesianCoordinate vertexCC = new CartesianCoordinate(new Float(temp[1]), new Float(temp[2]), new Float(temp[3]));
                        Float xSliceRef = new Float(Math.floor(new Float(vertexCC.x).doubleValue() * 10) / 10);
                        ArrayList<CartesianCoordinate> xSlicesVal;
                        if(xSlices.containsKey(xSliceRef)){
                            xSlicesVal = xSlices.get(xSliceRef);
                            xSlicesVal.add(vertexCC);
                            xSlices.put(xSliceRef, xSlicesVal);
                        }
                        else{
                            xSlicesVal = new ArrayList<CartesianCoordinate>();
                            xSlicesVal.add(vertexCC);
                            xSlices.put(xSliceRef, xSlicesVal);
                        }
                        if(vertexCC.x>obj.maxX.x){
                            obj.maxX = vertexCC;
                        }
                        else if(vertexCC.x<obj.minX.x){
                            obj.minX = vertexCC;
                        }
                        else if(vertexCC.y>obj.maxY.y){
                            obj.maxY = vertexCC;
                        }
                        else if(vertexCC.y<obj.minY.y){
                            obj.minY = vertexCC;
                        }
                        else if(vertexCC.z>obj.maxZ.z){
                            obj.maxZ = vertexCC;
                        }
                        else if(vertexCC.z<obj.minZ.z){
                            obj.minZ = vertexCC;
                        }
                        obj.addVertex(vertexCC);
                    }
                    else if(temp[0].equals("vt")){
                        obj.addTexture(new Float(temp[1]), new Float(temp[2]));
                    }
                    else if(temp[0].equals("vn")){
                        obj.addNormalVertex(new Float(temp[1]), new Float(temp[2]), new Float(temp[3]));  
                    }
                    else if(temp[0].equals("f")){
                        ArrayList<FaceVertex> fv = new ArrayList<FaceVertex>();
                        for(int i=1;i<temp.length;i++){
                            String [] verString=temp[i].split("/");
                            if(verString.length==3){
                                
                                fv.add(new FaceVertex(obj.vertices.get(Integer.parseInt(verString[0])-1),obj.vertices.get(Integer.parseInt(verString[1])-1),obj.vertices.get(Integer.parseInt(verString[2])-1))); 
                            }
                            else if(verString.length == 2){
                                fv.add(new FaceVertex(obj.vertices.get(Integer.parseInt(verString[0])-1),obj.vertices.get(Integer.parseInt(verString[1])-1)));
                            }
                            else if(verString.length==1){
                                fv.add(new FaceVertex(obj.vertices.get(Integer.parseInt(verString[0])-1)));
                            }
                        }
                        if(fv.size()==4){
                            Face f = new Face(fv.get(0), fv.get(1), fv.get(2), fv.get(3));
                            f.calculateFaceNormal();
                            obj.addFace(f); 
                        }
                        else if(fv.size()==3){
                            Face f = new Face(fv.get(0), fv.get(1), fv.get(2));
                            f.calculateFaceNormal();
                            obj.addFace(f); 
                        }
                         
                    }
                }
            }
            obj.center = new CartesianCoordinate(obj.minX.x+(obj.maxX.x-obj.minX.x)/2, obj.minY.y+(obj.maxY.y-obj.minY.y)/2, obj.minZ.x+(obj.maxZ.z-obj.minZ.z)/2);
            s.close();
            for(Float f:xSlices.keySet()){
                ArrayList<CartesianCoordinate> slice = xSlices.get(f);
                float maxY = slice.get(0).y;
                float minY = slice.get(0).y;
                float maxZ = slice.get(0).z;
                float minZ = slice.get(0).z;
                for(CartesianCoordinate xSlicesCC:slice){
                    if(xSlicesCC.y>maxY){
                        maxY=xSlicesCC.y;
                    }
                    else if(xSlicesCC.y<minY){
                        minY=xSlicesCC.y;
                    }
                    if(xSlicesCC.z > maxZ){
                        maxZ = xSlicesCC.z;
                    }
                    else if(xSlicesCC.z < minZ){
                        minZ = xSlicesCC.z;
                    }
                }
                CartesianCoordinate tempSpineCC = new CartesianCoordinate(f,minY+(maxY-minY)/2,minZ+(maxZ-minZ)/2);
                obj.spine.add(tempSpineCC);
            }
            LAMS.objects.add(obj);
            LAMS.focusedObject = obj;
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void dispose(GLAutoDrawable glad) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String removeSpaces(String str){
        String result = "";
        for(int i=0;i<str.length();i++){
            if(str.charAt(i)!=' '){
                result+=str.charAt(i);
            }
        }
        return result;
    }
    
    public String getColorString(Color color){
        return color.getRed() + "," + color.getGreen() + "," + color.getBlue();
    }

}
