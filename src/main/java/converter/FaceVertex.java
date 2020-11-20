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
    public Integer vertex;
    public Integer texture;
    public Integer normal;
    
    public FaceVertex(){}
    
    public FaceVertex(Integer vertex, Integer texture, Integer normal){
        this.vertex = vertex;
        this.texture = texture;
        this.normal = normal;
    }
    
    public FaceVertex(Integer vertex){
        this.vertex = vertex;
    }
    
    public FaceVertex(Integer vertex, Integer normal){
        this.vertex = vertex;
        this.normal = normal;
    }
    
}
