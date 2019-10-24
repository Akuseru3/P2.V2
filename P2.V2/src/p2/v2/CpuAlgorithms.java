/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p2.v2;

import java.util.ArrayList;

/**
 *
 * @author Kevin
 */
public class CpuAlgorithms {
    public int[][] tabla = new int[6][70];
    CpuAlgorithms(){
        for(int i = 0; i<6; i++){
            for(int j = 0; j<70; j++){
                tabla[i][j] = 0;
            }
        }
    }
    public void FeedBack_Alg(ArrayList<Processs> cargados,int quantum){
        ArrayList<Processs> enEjec = new ArrayList<Processs>();
        for(int j = 0; j<cargados.size();j++){
            if(cargados.get(j).arrivTime==0){
                enEjec.add(cargados.get(j));
            }
        }
        int actQuantum = quantum;
        for(int i = 0;i<21;i++){
            for(int j = 0; j<cargados.size();j++){
                if(cargados.get(j).arrivTime!=0 && i == cargados.get(j).arrivTime-1){
                    enEjec.add(1,cargados.get(j));
                }
            }
            if(enEjec.size()!=0){
                tabla[enEjec.get(0).corePos][i] = 1;
                enEjec.get(0).actTime -= 1;
                actQuantum -= 1;
                if(enEjec.get(0).actTime == 0){
                    enEjec.remove(0);
                    actQuantum = quantum;
                }
            }
            if(actQuantum == 0){
                actQuantum = quantum;
                Processs toChange = enEjec.get(0);
                enEjec.remove(0);
                enEjec.add(toChange);
            }
        }
    }
    public void RR_Alg(ArrayList<Processs> cargados, int quantum){
        ArrayList<Processs> enEjec = new ArrayList<Processs>();
        for(int j = 0; j<cargados.size();j++){
            if(cargados.get(j).arrivTime==0){
                enEjec.add(cargados.get(j));
            }
        }
        int actQuantum = quantum;
        for(int i = 0;i<21;i++){
            for(int j = 0; j<cargados.size();j++){
                if(cargados.get(j).arrivTime!=0 && i == cargados.get(j).arrivTime-1){
                    enEjec.add(cargados.get(j));
                }
            }
            if(actQuantum == 0){
                actQuantum = quantum;
                Processs toChange = enEjec.get(0);
                enEjec.remove(0);
                enEjec.add(toChange);
            }
            if(enEjec.size()!=0){
                tabla[enEjec.get(0).corePos][i] = 1;
                enEjec.get(0).actTime -= 1;
                actQuantum -= 1;
                if(enEjec.get(0).actTime == 0){
                    enEjec.remove(0);
                    actQuantum = quantum;
                }
            }
        }
    }
    public void SJF_AP_Alg(ArrayList<Processs> cargados){
        ArrayList<Processs> enEjec = new ArrayList<Processs>();
        for(int j = 0; j<cargados.size();j++){
            if(cargados.get(j).arrivTime==0){
                enEjec.add(cargados.get(j));
            }
        }
        for(int i = 0;i<21;i++){
            if(enEjec.size()!=0){
                tabla[enEjec.get(0).corePos][i] = 1;
                enEjec.get(0).actTime -= 1;
                if(enEjec.get(0).actTime == 0){
                    enEjec.remove(0);
                }
            }
            //poner este if arriba dependiendo de lo que diga el prof
            for(int j = 0; j<cargados.size();j++){
                if(cargados.get(j).arrivTime!=0 && i == cargados.get(j).arrivTime-1){
                    enEjec = shortJobArrange(enEjec,cargados.get(j));
                }
            }
        }
    }
    public ArrayList<Processs> shortJobArrange(ArrayList<Processs> executed, Processs newProces){
        if(executed.size() == 0){
            executed.add(newProces);
        }
        else{
            for(int i = 0; i<executed.size();i++){
                if(newProces.servTime < executed.get(i).actTime){
                    executed.add(i, newProces);
                    return executed;
                }
            }
            executed.add(newProces);
        }
        return executed;
    }
    public void FCFS_Alg(ArrayList<Processs> cargados){
        ArrayList<Processs> enEjec = new ArrayList<Processs>();
        for(int j = 0; j<cargados.size();j++){
            if(cargados.get(j).arrivTime==0){
                enEjec.add(cargados.get(j));
            }
        }
        for(int i = 0;i<21;i++){
            for(int j = 0; j<cargados.size();j++){
                if(cargados.get(j).arrivTime!=0 && i == cargados.get(j).arrivTime-1){
                    enEjec.add(cargados.get(j));
                }
            }
            if(enEjec.size()!=0){
                tabla[enEjec.get(0).corePos][i] = 1;
                enEjec.get(0).actTime -= 1;
                if(enEjec.get(0).actTime == 0){
                    enEjec.remove(0);
                }
            }  
        }
    }
}
