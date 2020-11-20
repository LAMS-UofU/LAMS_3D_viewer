/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import converter.CartesianCoordinate;
import converter.Face;
import java.util.ArrayList;

/**
 *
 * @author tyjensen
 */
public class Obj3D {
    public ArrayList<CartesianCoordinate> vertices;
    public ArrayList<Face> faces;
    public ArrayList<Edge> edges;
    public ArrayList<CartesianVector> normals;
    public ArrayList<CartesianCoordinate> textures;
    public ArrayList<CartesianCoordinate> spine;
    public CartesianCoordinate center;
    public CartesianCoordinate maxX=new CartesianCoordinate(0.0f,0.0f,0.0f);
    public CartesianCoordinate minX=new CartesianCoordinate(0.0f,0.0f,0.0f);
    public CartesianCoordinate minY=new CartesianCoordinate(0.0f,0.0f,0.0f);
    public CartesianCoordinate maxY=new CartesianCoordinate(0.0f,0.0f,0.0f);
    public CartesianCoordinate maxZ=new CartesianCoordinate(0.0f,0.0f,0.0f);
    public CartesianCoordinate minZ=new CartesianCoordinate(0.0f,0.0f,0.0f);
    
    public Obj3D(){
        vertices = new ArrayList<CartesianCoordinate>();
        faces = new ArrayList<Face>();
        edges = new ArrayList<Edge>();
        normals = new ArrayList<CartesianVector>();
        textures = new ArrayList<CartesianCoordinate>();
        spine = new ArrayList<CartesianCoordinate>();
        
    }
    
    public Obj3D(ArrayList<CartesianCoordinate> vertices, ArrayList<Face> faces){
        this.vertices = vertices;
        this.faces = faces;
        edges = new ArrayList<Edge>();
        normals = new ArrayList<CartesianVector>();
        textures = new ArrayList<CartesianCoordinate>();
        spine = new ArrayList<CartesianCoordinate>();
    }
    
    public Obj3D(ArrayList<CartesianCoordinate> vertices, ArrayList<CartesianCoordinate> textures, ArrayList<CartesianVector> normals, ArrayList<Face> faces, ArrayList<Edge> edges){
        this.vertices = vertices;
        this.textures = textures;
        this.normals = normals;
        this.faces = faces;
        this.edges = edges;
        spine = new ArrayList<CartesianCoordinate>();
    }
    
    public void addVertex(float x, float y, float z){
        vertices.add(new CartesianCoordinate(x,y,z));
    }
    
    public void addVertex(CartesianCoordinate cc){
        vertices.add(cc);
    }
    
    public void addTexture(float x, float y){
        textures.add(new CartesianCoordinate(x,y,0));
    }
    
    public void addNormalVertex(float x, float y, float z){
        normals.add(new CartesianVector(x,y,z));
    }
    
    public void calculateNormalVertex(CartesianVector v1p, CartesianVector v2p, CartesianVector v3p){
        CartesianVector vec1 = v2p.sub(v1p);
        CartesianVector vec2 = v3p.sub(v1p);
        normals.add(vec1.cross(vec2).normalize());
    }
    
    public void addFace(Face f){
        faces.add(f);
    }
    
    public void addEdge(Edge e){
        edges.add(e);
    }
    
    
}
