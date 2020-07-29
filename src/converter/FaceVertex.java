/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

/**
 *
 * @author tyjensen
 */
public class FaceVertex {
    public CartesianCoordinate vertex;
    public CartesianCoordinate texture;
    public CartesianCoordinate normal;
    
    public FaceVertex(){}
    
    public FaceVertex(CartesianCoordinate vertex, CartesianCoordinate texture, CartesianCoordinate normal){
        this.vertex = vertex;
        this.texture = texture;
        this.normal = normal;
    }
    
    public FaceVertex(CartesianCoordinate vertex){
        this.vertex = vertex;
    }
    
    public FaceVertex(CartesianCoordinate vertex, CartesianCoordinate normal){
        this.vertex = vertex;
        this.normal = normal;
    }
    
}
