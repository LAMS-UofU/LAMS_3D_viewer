/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

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
    public boolean isTriangle = false;
    
    public Face(){
        
    }
    
    public Face(Integer v1,Integer v2, Integer v3){
        this.setTriangleFace(v1, v2, v3);
    }
    
    public Face(Integer v1,Integer v2, Integer v3, boolean isZeroBased){
        if(isZeroBased){
            this.setTriangleFace(v1+1, v2+1, v3+1);
        }
        else{
            this.setTriangleFace(v1, v2, v3);
        }
    }
    
    public Face(FaceVertex v1, FaceVertex v2, FaceVertex v3){
        this.v1 = v1;
        this.v2 = v2;
        this.v3 = v3;
    }
    
    public Face(FaceVertex v1, FaceVertex v2, FaceVertex v3, FaceVertex v4){
        this.v1 = v1;
        this.v2 = v2;
        this.v3 = v3;
        this.v4 = v4;
    }
    
    public Face(ArrayList<Edge> list, int endpoint, boolean isZeroBased){
        Set<Integer> tempList = new HashSet<Integer>();
        tempList.add(endpoint);
        for(Edge e:list){
            tempList.add(e.a);
            tempList.add(e.b);
        }
        Integer[] vertexList = new Integer[4];
        tempList.toArray(vertexList);
        if(isZeroBased){
            if(tempList.size()==3){
                this.v1.vertex = vertexList[0]+1;
                this.v2.vertex = vertexList[1]+1;
                this.v3.vertex = vertexList[2]+1;
                this.isTriangle = true;
            }
            else{
                this.v1.vertex = vertexList[0]+1;
                this.v2.vertex = vertexList[1]+1;
                this.v3.vertex = vertexList[2]+1;
                this.v4.vertex = vertexList[3]+1;
                this.isTriangle = false;
            }
        }
        else{
            if(tempList.size()==3){
                this.v1.vertex = vertexList[0];
                this.v2.vertex = vertexList[1];
                this.v3.vertex = vertexList[2];
                this.isTriangle = true;
            }
            else{
                this.v1.vertex = vertexList[0];
                this.v2.vertex = vertexList[1];
                this.v3.vertex = vertexList[2];
                this.v4.vertex = vertexList[3];
                this.isTriangle = false;
            }
        }
    }
    
    public Face(Edge e1, Edge e2, Edge e3, boolean isZeroBased){
        int a=e1.a;
        int b =e1.b;
        int c = e2.a;
        int d = e2.b;
        int e = e3.a;
        int f = e3.b;
        int v1 = a;
        int v2 = b;
        int v3 = 0;
        if(a==c || b == c){
            v3 = d;
        }
        else if(a==d || b==d){
            v3 = c;
        }
        if(isZeroBased){
            this.setTriangleFace(v1+1, v2+1, v3+1);
        }
        else{
            this.setTriangleFace(v1, v2, v3);
        }
    }
    
    /*public Face(Edge e1, Edge e2, Edge e3, Edge e4, boolean isZeroBased){
        int a=e1.a;
        int b =e1.b;
        int c = e2.a;
        int d = e2.b;
        int e = e3.a;
        int f = e3.b;
        int g = e4.a;
        int h = e4.b;
        int v1 = a;
        int v2 = b;
        int v3 = 0;
        int v4 = 0;
        if(a==c || b == c){
            v3 = d;
        }
        else if(a==d || b==d){
            v3 = c;
        }
        if(g!=v1 && g!=v2 && g!=v3){
            v4=g;
        }
        else if(h!=v1 && h!=v2 && h!=v3)
        if(isZeroBased){
            this.setPolygonFace(v1+1, v2+1, v3+1, v4+1);
        }
        else{
            this.setPolygonFace(v1, v2, v3, v4);
        }
    }*/
    
    public Face(Integer v1,Integer v2, Integer v3, Integer v4){
        this.setPolygonFace(v1, v2, v3, v4);
    }
    
    public void setTriangleFace(int v1,int v2, int v3){
        isTriangle = true;
        this.v1.vertex=v1;
        this.v2.vertex = v2;
        this.v3.vertex = v3;
    }
    
    public void setPolygonFace(int v1, int v2, int v3, int v4){
        isTriangle = false;
        this.v1.vertex = v1;
        this.v2.vertex = v2;
        this.v3.vertex = v3;
        this.v4.vertex = v4;
    }
    
    public boolean equals(Face f){
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
                
                
    }
    
    public String toString(){
        return this.v1 + "-" + this.v2 + "-" + this.v3 +"-"+ this.v4;
    }
    
    public void flipNormal(){
        if(this.isTriangle){
            int temp = this.v1.vertex;
            this.v1.vertex = this.v3.vertex;
            this.v3.vertex = temp;
        }
        else{
            int temp = this.v1.vertex;
            this.v1.vertex=this.v4.vertex;
            temp = this.v2.vertex;
            this.v2.vertex = this.v3.vertex;
            this.v3.vertex = temp;
        }
    }
}
