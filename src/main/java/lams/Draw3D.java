/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lams;

import com.aparapi.Kernel;
import com.aparapi.Range;
import converter.CartesianCoordinate;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import javax.swing.JPanel;

/**
 *
 * @author tyjen
 */
public class Draw3D extends JPanel {
    public int height;
    public int width;
    public ArrayList<CartesianCoordinate> points;
    public ArrayList<Point> pointsRef;
    public ArrayList<Line> lines;
    public ArrayList<Face> faces;
    public CartesianCoordinate2D[] screenData;
    public Color backgroundColor;
    public CartesianCoordinate eyePosition;
    public boolean vertexView;
    public boolean wireframeView;
    public boolean faceView;
    public boolean GPUProcessingEnabled;
    public boolean xyGridEnable;
    public boolean xAxisEnable;
    public boolean yAxisEnable;
    public boolean zAxisEnable;
            
    public Color xyGridColor;
    public Color xAxisColor;
    public Color yAxisColor;
    public Color zAxisColor;
    
    public double eyeDistanceFromOrigin = 50.0;
    public double scale;
    private float sx;
    private float sy;
    private double fov;
    private double zRemap1;
    private double zRemap2;
    private double aspectRatio;
    private double farClipping;
    private double nearClipping;
    private double xAxisRotation;
    private double yAxisRotation;
    private double zAxisRotation;
    private float mouseClickX;
    private float mouseClickY;
    private boolean ctrlDown = false;
   
    
    
    public Draw3D(){
        this.points = new ArrayList<CartesianCoordinate>();
        this.pointsRef = new ArrayList<Point>();
        this.lines = new ArrayList<Line>();
        this.faces = new ArrayList<Face>();
        this.scale = 1.0f;
        this.fov = 90.0;
        this.farClipping = 20.0;
        this.nearClipping = 0.1;
        //this.calculateS(90.0f);
        //this.eyeDistanceFromOrigin = -50;
        
        this.eyePosition = new CartesianCoordinate(0.0,0.0,0.1);
        this.initMouseListener();
        this.initKeyListener();
        //this.setFarAndNearClippingPlanes(1000.0, 0.1);
    }
    
    public Draw3D(int width, int height){
        this.points = new ArrayList<CartesianCoordinate>();
        this.pointsRef = new ArrayList<Point>();
        this.lines = new ArrayList<Line>();
        this.faces = new ArrayList<Face>();
        this.scale = 1.0f;
        this.fov = 90.0;
        this.farClipping = 20.0;
        this.nearClipping = 0.1;
        this.eyeDistanceFromOrigin = 50;
        this.eyePosition = new CartesianCoordinate(0.0,0.0,0.1);
        this.initMouseListener();
        this.initKeyListener();
        setSize(width, height);
    }
    
    public void clearData(){
        this.points = new ArrayList<CartesianCoordinate>();
        this.pointsRef = new ArrayList<Point>();
        this.lines = new ArrayList<Line>();
        this.faces = new ArrayList<Face>();
        this.refresh();
    }
    
    public void initKeyListener(){
        
        this.addKeyListener(new KeyListener(){
            @Override
            public void keyTyped(KeyEvent ke) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void keyPressed(KeyEvent ke) {
                switch(ke.getKeyCode()){
                    case 17:
                        ctrlDown = true;
                        break;
                    case 37:
                        rotate(0.0,-5.0,0.0);
                        refresh();
                        break;
                    case 38:
                        rotate(5.0,0.0,0.0);
                        refresh();
                        break;
                    case 39:
                        rotate(0.0,5.0,0.0);
                        refresh();
                        break;
                    case 40:
                        rotate(-5.0,0.0,0.0);
                        refresh();
                        break;
                    case 82:
                        if(ctrlDown){
                            rotate(0.0,89.0,0.0);
                            refresh();
                        }
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void keyReleased(KeyEvent ke) {
                System.out.println(ke.getKeyCode());
                switch(ke.getKeyCode()){
                    case 17:
                        ctrlDown = false;
                        break;
                    default:
                        break;
                            
                }
            }
        });
    }
    
    public void drawXYGrid(int gridSize, int gridLineSize, Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        CartesianCoordinate2D c1;
        CartesianCoordinate2D c2;
        CartesianCoordinate2D c3;
        CartesianCoordinate2D c4;
        for(int i = 0;i<=10;i++){
            for(int j = 0;j<10;j++){
                c1 = project(this.applyRotation(new CartesianCoordinate(10*(i-5),0.0f,50)));
                c2 = project(this.applyRotation(new CartesianCoordinate(10*(i-5),0.0f,-50)));
                c3 = project(this.applyRotation(new CartesianCoordinate(50,0.0f,10*(i-5))));
                c4 = project(this.applyRotation(new CartesianCoordinate(-50,0.0f,10*(i-5))));
                if(c1.intX<=this.width || c2.intX <=this.width || c1.intY<=this.height || c2.intY<=this.height){
                    g2d.setColor(this.xyGridColor);
                    g2d.drawLine(c1.intX, c1.intY, c2.intX, c2.intY);
                    g2d.drawLine(c3.intX, c3.intY, c4.intX, c4.intY);
                }
            }
        }
    }
    
    public void drawXAxis(int length, boolean halfAxis, Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        
        CartesianCoordinate2D c1 = project(this.applyRotation(new CartesianCoordinate(200,0.0,0.0)));
        CartesianCoordinate2D c2 = project(this.applyRotation(new CartesianCoordinate(-200,0,0)));
        if(halfAxis){
            c2 = project(this.applyRotation(new CartesianCoordinate(0.0,0,0)));
        }
        g2d.setColor(this.xAxisColor);
        g2d.drawLine(c1.intX, c1.intY, c2.intX, c2.intY);
    }
    
    public void drawYAxis(int length, boolean halfAxis, Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        
        CartesianCoordinate2D c1 = project(this.applyRotation(new CartesianCoordinate(0.0,200.0,0.0)));
        CartesianCoordinate2D c2 = project(this.applyRotation(new CartesianCoordinate(0.0,-200.0,0)));
        if(halfAxis){
            c2 = project(this.applyRotation(new CartesianCoordinate(0.0,0,0)));
        }
        g2d.setColor(this.yAxisColor);
        g2d.drawLine(c1.intX, c1.intY, c2.intX, c2.intY);
    }
    
    public void drawZAxis(int length, boolean halfAxis, Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        
        CartesianCoordinate2D c1 = project(this.applyRotation(new CartesianCoordinate(0.0,0.0,200.0)));
        CartesianCoordinate2D c2 = project(this.applyRotation(new CartesianCoordinate(0.0,0,-200.0)));
        if(halfAxis){
            c2 = project(this.applyRotation(new CartesianCoordinate(0.0,0,0)));
        }
        g2d.setColor(this.zAxisColor);
        g2d.drawLine(c1.intX, c1.intY, c2.intX, c2.intY);
    }
    
    public void initMouseListener(){
        this.addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent e) {
                mouseClickX = e.getX();
                mouseClickY = e.getY();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                
            }
        });
        
        this.addMouseMotionListener(new MouseAdapter(){
             public void mouseMoved(MouseEvent e) {
                
             }

             public void mouseDragged(MouseEvent e) {
                rotate(0.0,(mouseClickX-e.getX())/100,0.0);
                rotate(-(mouseClickY-e.getY())/100,0.0,0.0);
                refresh();
             }
        });
        
        this.addMouseWheelListener(new MouseAdapter(){
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                //if (e.isControlDown()) {
                    double fieldOfView = fov;
                    
                    if (e.getWheelRotation() < 0) {
                        //eyePosition.dz+=2;
                        scale++;
                        refresh();
                        fieldOfView--;
                        if(fieldOfView>0){
                            fov=fieldOfView;
                            refresh();
                            
                            //createProjectionMatrix(fieldOfView-1,aspectRatio,nearClipping,farClipping);
                            //calculateS(fieldOfView-1);
                        }
                    } else {
                        fieldOfView++;
                        scale--;
                        refresh();
                        if(fieldOfView<180){
                            fov=fieldOfView;
                            refresh();
                            //createProjectionMatrix(fieldOfView+1,aspectRatio,nearClipping,farClipping);
                            //calculateS(fieldOfView+1);
                        }
                    }
                //} else {
                    // pass the event on to the scroll pane
                //    getParent().dispatchEvent(e);
                //}
            }
        });
    }
    
    public void setBackgroundColor(Color c){
        this.backgroundColor = c;
        super.setBackground(c);
    }
    
  
    public void rotate(double xAxisRotation, double yAxisRotation, double zAxisRotation){
        this.xAxisRotation = (this.xAxisRotation + xAxisRotation)%360.0;
        this.yAxisRotation = (this.yAxisRotation + yAxisRotation)%360.0;
        this.zAxisRotation = (this.zAxisRotation + zAxisRotation)%360.0;
    }
    
    public void refresh(){
        this.repaint();
    }
    
    public CartesianCoordinate[] applyRotationGPU(CartesianCoordinate[] cc){
        CartesianCoordinate[] result =new CartesianCoordinate[this.points.size()];
        
        double sinThetaX = Math.sin(this.xAxisRotation*(Math.PI/180));
        double cosThetaX = Math.cos(this.xAxisRotation*(Math.PI/180));
        double sinThetaY = Math.sin(this.yAxisRotation*(Math.PI/180));
        double cosThetaY = Math.cos(this.yAxisRotation*(Math.PI/180));
        double sinThetaZ = Math.sin(this.zAxisRotation*(Math.PI/180));
        double cosThetaZ = Math.cos(this.zAxisRotation*(Math.PI/180));
 
        Kernel k = new Kernel(){
            @Override
            public void run() {
                int i = getGlobalId();
                result[i] = new CartesianCoordinate(cc[i].dx,cc[i].dy,cc[i].dz);
                if(xAxisRotation!=0){
                    result[i].setY(cc[i].dy*cosThetaX - sinThetaX*cc[i].dz);
                    result[i].setZ(cc[i].dy*sinThetaX + cosThetaX*cc[i].dz);
                }
                if(yAxisRotation!=0){
                    result[i].setX(cosThetaY*result[i].dx + sinThetaY*result[i].dz);
                    result[i].setZ(-sinThetaX*result[i].dx + cosThetaY*result[i].dz);
                }
                if(zAxisRotation!=0){
                    result[i].setX(cosThetaZ*result[i].dx + sinThetaZ*result[i].dy);
                    result[i].setY(-sinThetaZ*result[i].dx + cosThetaZ*result[i].dy);
                }
                if(Math.abs(result[i].dx)<0.00000001){
                    result[i].setX(0.0);
                }
                if(Math.abs(result[i].dy)<0.00000001){
                    result[i].setY(0.0);
                }
                if(Math.abs(result[i].dz)<0.00000001){
                    result[i].setZ(0.0);
                }
            }
        };
        Range range = Range.create(result.length);
        k.execute(range);

        
        return result;
        
    }
    
    public CartesianCoordinate applyRotation(CartesianCoordinate cc){
        CartesianCoordinate result =new CartesianCoordinate();
        
        double sinTheta = Math.sin(this.xAxisRotation*(Math.PI/180));
        double cosTheta = Math.cos(this.xAxisRotation*(Math.PI/180));
 
        if(this.xAxisRotation!=0){
            result.setX(cc.fx);
            result.setY(cc.dy*cosTheta - sinTheta*cc.dz);
            result.setZ(cc.dy*sinTheta + cosTheta*cc.dz);
        }
        else{
            result.setX(cc.dx);
            result.setY(cc.dy);
            result.setZ(cc.dz);
        }
        //y axis rotation
        if(this.yAxisRotation!=0){
            sinTheta = Math.sin(this.yAxisRotation*(Math.PI/180));
            cosTheta = Math.cos(this.yAxisRotation*(Math.PI/180));
            result.setX(cosTheta*result.dx + sinTheta*result.dz);
            result.setZ(-sinTheta*result.dx + cosTheta*result.dz);
            this.eyePosition.setX(cosTheta*this.eyeDistanceFromOrigin + sinTheta*this.eyeDistanceFromOrigin);
            this.eyePosition.setZ(-sinTheta*this.eyeDistanceFromOrigin + cosTheta*this.eyeDistanceFromOrigin);
        }
        else{
            result.setX(result.dx);
            result.setY(result.dy);
            result.setZ(result.dz);
        }
        //z axis rotation
        if(this.zAxisRotation!=0){
            sinTheta = Math.sin(this.zAxisRotation*(Math.PI/180));
            cosTheta = Math.cos(this.zAxisRotation*(Math.PI/180));
            result.setX(cosTheta*result.dx + sinTheta*result.dy);
            result.setY(-sinTheta*result.dx + cosTheta*result.dy);
        }
        else{
            result.setX(result.dx);
            result.setY(result.dy);
            result.setZ(result.dz);
        }
        if(Math.abs(result.dx)<0.00000001){
            result.setX(0.0);
        }
        if(Math.abs(result.dy)<0.00000001){
            result.setY(0.0);
        }
        if(Math.abs(result.dz)<0.00000001){
            result.setZ(0.0);
        }
        return result;
    }

    @Override
    public void setSize(int width, int height){
        this.height = height;
        this.width = width;
        this.aspectRatio = this.width/this.height;
        
        super.setSize(this.width,this.height);
    }
    
    public int addPoint(CartesianCoordinate cc, Color c, int size){
        points.add(cc);
        pointsRef.add(new Point(points.size()-1, c,size));
        return points.size()-1;
    }
    
    public void addLine(Integer a, Integer b, Color c){
        lines.add(new Line(a,b,c));
    }
    
    public void addFace(Integer a, Integer b, Integer c, Integer d, Color e){
        faces.add(new Face(a,b,c,d,e));
    }
    
    public void addFace(int a, int b, int c, Color e){
        faces.add(new Face(a,b,c,e));
    }
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        if(this.GPUProcessingEnabled){
            if(points.size()>0){
                 CartesianCoordinate[] pointArrResult = new CartesianCoordinate[points.size()];
                CartesianCoordinate[] pointArr = new CartesianCoordinate[points.size()];
                points.toArray(pointArr);
                pointArrResult = this.applyRotationGPU(pointArr);
                this.screenData = this.projectGPU(pointArrResult); 
                if(this.vertexView){
                    drawPointsGPU(g);
                }
                if(this.wireframeView){
                    drawLinesGPU(g);
                }
                if(this.faceView){
                    drawFacesGPU(g);
                }
            }
        }
        else{
            
            if(this.vertexView){
                drawPoints(g);
            }
            if(this.wireframeView){
                drawLines(g);
            }
            if(this.faceView){
                drawFaces(g);
            }
        }
        if(this.xyGridEnable){
            this.drawXYGrid(10, 10, g);
        }
        if(this.xAxisEnable){
            this.drawXAxis(10, true, g);
        }
        if(this.yAxisEnable){
            this.drawYAxis(10, true, g);
        }
        if(this.zAxisEnable){
            this.drawZAxis(10, true, g);
        }
        
        
        drawText(g);
    }
    
    public void drawPointsGPU(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        for(Point p:pointsRef){
                if(this.screenData[p.cc].intX<=this.width && this.screenData[p.cc].intY<=this.height){
                    g2d.setColor(p.c);
                    g2d.fillRect(this.screenData[p.cc].intX-p.size, this.screenData[p.cc].intY-p.size, p.size, p.size);
                }
        }
    }
    
    public void drawLines(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        CartesianCoordinate2D c1;
        CartesianCoordinate2D c2;
        for(Line l:this.lines){
            CartesianCoordinate l1 = this.applyRotation(this.points.get(this.pointsRef.get(l.a).cc));
            CartesianCoordinate l2 = this.applyRotation(this.points.get(this.pointsRef.get(l.b).cc));
            c1 = project(l1);
            c2 = project(l2);
            if(c1.intX<=this.width || c2.intX <=this.width || c1.intY<=this.height || c2.intY<=this.height){
                g2d.setColor(l.c);
                g2d.drawLine(c1.intX, c1.intY, c2.intX, c2.intY);
            }
        }
    }
    
    public void drawLinesGPU(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        for(Line l:this.lines){
            if(screenData[this.pointsRef.get(l.a).cc].intX<=this.width || screenData[this.pointsRef.get(l.b).cc].intX <=this.width || screenData[this.pointsRef.get(l.a).cc].intY<=this.height || screenData[this.pointsRef.get(l.b).cc].intY<=this.height){
                g2d.setColor(l.c);
                g2d.drawLine(screenData[this.pointsRef.get(l.a).cc].intX, screenData[this.pointsRef.get(l.a).cc].intY, screenData[this.pointsRef.get(l.b).cc].intX, screenData[this.pointsRef.get(l.b).cc].intY);
            }
        }
    }
    
    public void drawFaces(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        CartesianCoordinate2D c1;
        CartesianCoordinate2D c2;
        CartesianCoordinate2D c3;
        CartesianCoordinate2D c4;
        for(Face f:this.faces){
            g2d.setColor(f.e);
            c1 = project(this.applyRotation(this.points.get(this.pointsRef.get(f.a).cc)));
            c2 = project(this.applyRotation(this.points.get(this.pointsRef.get(f.b).cc)));
            c3 = project(this.applyRotation(this.points.get(this.pointsRef.get(f.c).cc)));
                    
            if(f.isTriangle){
                if(c1.intX<=this.width || c2.intX <=this.width || c3.intX <= this.width || c1.intY<=this.height || c2.intY<=this.height || c3.intY<=this.height){
                    g2d.fill(new Polygon(new int[] {c1.intX,c2.intX,c3.intX},new int[] {c1.intY,c2.intY,c3.intY},3));
                }
            }
            else{
                c4 = project(this.applyRotation(this.points.get(this.pointsRef.get(f.d).cc)));
                if(c1.intX<=this.width || c2.intX <=this.width || c3.intX <= this.width || c4.intX <= this.width || c1.intY<=this.height || c2.intY<=this.height || c3.intY<=this.height || c4.intY<=this.height){
                    g2d.fill(new Polygon(new int[] {c1.intX,c2.intX,c3.intX,c4.intX},new int[] {c1.intY,c2.intY,c3.intY,c4.intY},4));
                }
            }
        }
    }
    
    public void drawFacesGPU(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        CartesianCoordinate2D c1;
        CartesianCoordinate2D c2;
        CartesianCoordinate2D c3;
        CartesianCoordinate2D c4;
        for(Face f:this.faces){
            g2d.setColor(f.e);
            c1 = screenData[this.pointsRef.get(f.a).cc];
            c2 = screenData[this.pointsRef.get(f.b).cc];
            c3 = screenData[this.pointsRef.get(f.c).cc];
                    
            if(f.isTriangle){
                if(c1.intX<=this.width || c2.intX <=this.width || c3.intX <= this.width || c1.intY<=this.height || c2.intY<=this.height || c3.intY<=this.height){
                    g2d.fill(new Polygon(new int[] {c1.intX,c2.intX,c3.intX},new int[] {c1.intY,c2.intY,c3.intY},3));
                }
            }
            else{
                c4 = screenData[this.pointsRef.get(f.d).cc];
                if(c1.intX<=this.width || c2.intX <=this.width || c3.intX <= this.width || c4.intX <= this.width || c1.intY<=this.height || c2.intY<=this.height || c3.intY<=this.height || c4.intY<=this.height){
                    g2d.fill(new Polygon(new int[] {c1.intX,c2.intX,c3.intX,c4.intX},new int[] {c1.intY,c2.intY,c3.intY,c4.intY},4));
                }
            }
        } 
    }
    
    public void drawPoints(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        CartesianCoordinate2D c1;
        for(Point p:pointsRef){
                //c1 = applyProjectionMatrix(p.cc);
                c1 = project(this.applyRotation(points.get(p.cc)));
                if(c1.intX<=this.width && c1.intY<=this.height){
                    g2d.setColor(p.c);
                    g2d.fillRect(c1.intX-p.size, c1.intY-p.size, p.size, p.size);
                }
        }
    }
    
 
    public CartesianCoordinate2D project(CartesianCoordinate cc){
        CartesianCoordinate2D result = new CartesianCoordinate2D();
        this.sx = new Float(1/(this.aspectRatio*(Math.tan((fov/2)*(Math.PI/180)))));
        this.sy = new Float(1/(Math.tan((fov/2)*(Math.PI/180))));
        result.intX = new Double(cc.dx*this.scale).intValue()+this.width/2;
        result.intY = new Double(cc.dy*this.scale).intValue()+this.height/2;
        //result.intX = new Double((cc.dx/((cc.dz+50)/this.farClipping))*this.scale).intValue()+this.width/2;
        //result.intY = new Double((cc.dy/((cc.dz+50)/this.farClipping))*this.scale).intValue()+this.height/2;
        return result;
    }
    
    public CartesianCoordinate2D[] projectGPU(CartesianCoordinate[] cc){
        CartesianCoordinate2D[] result = new CartesianCoordinate2D[this.points.size()];
        this.sx = new Float(1/(this.aspectRatio*(Math.tan((fov/2)*(Math.PI/180)))));
        this.sy = new Float(1/(Math.tan((fov/2)*(Math.PI/180))));
        Kernel k = new Kernel(){
            @Override
            public void run() {
                int i = getGlobalId();
                CartesianCoordinate2D cc2 = new CartesianCoordinate2D();
                cc2.intX = new Double(cc[i].dx*scale).intValue()+width/2;
                cc2.intY = new Double(cc[i].dy*scale).intValue()+height/2;
                result[i] = cc2;
            }
        };
        Range range = Range.create(result.length);
        k.execute(range);
        //result.intX = new Double((cc.dx/((cc.dz+50)/this.farClipping))*this.scale).intValue()+this.width/2;
        //result.intY = new Double((cc.dy/((cc.dz+50)/this.farClipping))*this.scale).intValue()+this.height/2;
        return result;
    }

    private void drawText(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
    }
}
