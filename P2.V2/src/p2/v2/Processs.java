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
public class Processs {
    public String name;
    public int servTime;
    public int arrivTime;
    public int priority;
    public int size;
    public int actTime;
    public int corePos;
    Processs(String name,int sTime,int aTime,int priority,int size,int pos){
        this.name = name;
        servTime = sTime;
        arrivTime = aTime;
        actTime = sTime;
        this.priority = priority;
        this.size = size;
        corePos = pos;
    }
}
