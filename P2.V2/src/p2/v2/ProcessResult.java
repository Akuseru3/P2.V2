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
public class ProcessResult {
    public int finalTime;
    public int turnAround;
    public float turndService;
    public int tablePosition;
    ProcessResult(int fTime, int arrTime, int serviceTime, int tb){
        tablePosition = tb;
        finalTime = fTime;
        turnAround = fTime - arrTime;
        float semiTDS = (float)turnAround/(float)serviceTime;
        turndService = (float)Math.round(semiTDS*100.0)/100;
    }
}
