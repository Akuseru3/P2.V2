/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p2.v2;

/**
 *
 * @author Kevin
 */
public class Process {
    public String name;
    public int servTime;
    public int arrivTime;
    public int priority;
    public int size;
    Process(String name,int sTime,int aTime,int priority,int size){
        this.name = name;
        servTime = sTime;
        arrivTime = aTime;
        this.priority = priority;
        this.size = size;
    }
}
