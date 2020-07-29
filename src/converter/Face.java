/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

/** This class represents the face of a 3d object.
 * this method can represent a triangle or quad face.
 *
 * @author tyjensen
 */
public class Face {
    public FaceVertex v1;
    public FaceVertex v2;
    public FaceVertex v3;
    public FaceVertex v4;
    public CartesianVector faceNormal;
    public boolean isTriangle = false;
    
    public Face(){
        
    }
    
    public Face(CartesianCoordinate v1,CartesianCoordinate v2, CartesianCoordinate v3){
        this.setTriangleFace(new FaceVertex(v1), new FaceVertex(v2), new FaceVertex(v3));
    }
    
    public Face(FaceVertex v1,FaceVertex v2, FaceVertex v3){
        this.setTriangleFace(v1, v2, v3);
    }
    
    public Face(FaceVertex v1,FaceVertex v2, FaceVertex v3, FaceVertex v4){
        this.setPolygonFace(v1, v2, v3, v4);
    }
    
    public Face(CartesianCoordinate v1,CartesianCoordinate v2, CartesianCoordinate v3, CartesianCoordinate v4){
        this.setPolygonFace(new FaceVertex(v1), new FaceVertex(v2), new FaceVertex(v3), new FaceVertex(v4));
    }
    
    public void setTriangleFace(FaceVertex v1,FaceVertex v2, FaceVertex v3){
        isTriangle = true;
        this.v1=v1;
        this.v2 = v2;
        this.v3 = v3;
    }
    
    public void setPolygonFace(FaceVertex v1, FaceVertex v2, FaceVertex v3, FaceVertex v4){
        isTriangle = false;
        this.v1 = v1;
        this.v2 = v2;
        this.v3 = v3;
        this.v4 = v4;
    }
    
    public CartesianVector calculateFaceNormal(CartesianVector v1p, CartesianVector v2p, CartesianVector v3p, CartesianVector v4p){
        CartesianVector vec1 = v2p.sub(v1p);
        CartesianVector vec2 = v3p.sub(v1p);
        this.faceNormal = vec1.cross(vec2).normalize();
        return this.faceNormal;
    }
    
    public CartesianVector calculateFaceNormal(){
        this.faceNormal = (v2.vertex.sub(v1.vertex).getVector()).cross(v3.vertex.sub(v1.vertex).getVector()).normalize();
        return this.faceNormal;
    }
    
    public void calculateVertexNormal(){
        
    }
    
    /*public boolean equals(Face f){
        if(f.isTriangle && this.isTriangle){
            if((this.v1.vertex+this.v2.vertex+this.v3.vertex+this.v4.vertex) == (f.v1.vertex+f.v2.vertex+f.v3.vertex+f.v4.vertex)){
                return true;
            }
            else{
                return false;
            }
        }
        else{
            return false;
        }
                
                
    }*/
}
