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
    public int[][] tabla = new int[6][80];
    public ArrayList<ProcessResult> results;
    CpuAlgorithms(){
        results = new ArrayList<ProcessResult>();
        for(int i = 0; i<6; i++){
            for(int j = 0; j<80; j++){
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
        for(int i = 0;i<80;i++){
            if(actQuantum == 0){
                actQuantum = quantum;
                Processs toChange = enEjec.get(0);
                enEjec.remove(0);
                enEjec.add(toChange);
            }
            for(int j = 0; j<cargados.size();j++){
                if(cargados.get(j).arrivTime!=0 && i == cargados.get(j).arrivTime-1){
                    if(enEjec.size()!=0){
                        enEjec.add(1,cargados.get(j));
                    }
                    else{
                        enEjec.add(cargados.get(j));
                    }
                }
            }
            if(enEjec.size()!=0){
                tabla[enEjec.get(0).corePos][i] = 1;
                enEjec.get(0).actTime -= 1;
                actQuantum -= 1;
                if(enEjec.get(0).actTime == 0){
                    results.add(new ProcessResult(i+1,enEjec.get(0).arrivTime,enEjec.get(0).servTime,enEjec.get(0).corePos));
                    enEjec.remove(0);
                    actQuantum = quantum;
                }
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
        for(int i = 0;i<80;i++){
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
                    results.add(new ProcessResult(i+1,enEjec.get(0).arrivTime,enEjec.get(0).servTime,enEjec.get(0).corePos));
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
        for(int i = 0;i<80;i++){
            if(enEjec.size()!=0){
                tabla[enEjec.get(0).corePos][i] = 1;
                enEjec.get(0).actTime -= 1;
                if(enEjec.get(0).actTime == 0){
                    results.add(new ProcessResult(i+1,enEjec.get(0).arrivTime,enEjec.get(0).servTime,enEjec.get(0).corePos));
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
        for(int i = 0;i<80;i++){
            if(enEjec.size()!=0){
                tabla[enEjec.get(0).corePos][i] = 1;
                enEjec.get(0).actTime -= 1;
                if(enEjec.get(0).actTime == 0){
                    results.add(new ProcessResult(i+1,enEjec.get(0).arrivTime,enEjec.get(0).servTime,enEjec.get(0).corePos));
                    enEjec.remove(0);
                }
            } 
            for(int j = 0; j<cargados.size();j++){
                if(cargados.get(j).arrivTime!=0 && i == cargados.get(j).arrivTime-1){
                    enEjec.add(cargados.get(j));
                }
            }
        }
        
    }
    public void Priority_Alg(ArrayList<Processs> cargados){
        ArrayList<Processs> enEjec = new ArrayList<Processs>();
        for(int j = 0; j<cargados.size();j++){
            if(cargados.get(j).arrivTime==0){
                enEjec = priorityArrange(enEjec,cargados.get(j));
            }
        }
        for(int i = 0;i<80;i++){
            if(enEjec.size()!=0){
                tabla[enEjec.get(0).corePos][i] = 1;
                enEjec.get(0).actTime -= 1;
                if(enEjec.get(0).actTime == 0){
                    results.add(new ProcessResult(i+1,enEjec.get(0).arrivTime,enEjec.get(0).servTime,enEjec.get(0).corePos));
                    enEjec.remove(0);
                }
            }
            //poner este if arriba dependiendo de lo que diga el prof
            for(int j = 0; j<cargados.size();j++){
                if(cargados.get(j).arrivTime!=0 && i == cargados.get(j).arrivTime-1){
                    enEjec = priorityArrange(enEjec,cargados.get(j));
                }
            }
        }
        
    }
    public ArrayList<Processs> priorityArrange(ArrayList<Processs> executed, Processs newProces){
        if(executed.size() == 0){
            executed.add(newProces);
        }
        else{
            for(int i = 0; i<executed.size();i++){
                if(newProces.priority < executed.get(i).priority){
                    executed.add(i, newProces);
                    return executed;
                }
            }
            executed.add(newProces);
        }
        return executed;
    }
    
    public void HRRN_Alg(ArrayList<Processs> cargados){
        ArrayList<Processs> enEjec = new ArrayList<Processs>();
        for(int j = 0; j<cargados.size();j++){
            if(cargados.get(j).arrivTime==0){
                enEjec.add(cargados.get(j));
            }
        }
        for(int i = 0;i<80;i++){
            if(enEjec.size()!=0){
                tabla[enEjec.get(0).corePos][i] = 1;
                enEjec.get(0).actTime -= 1;
                if(enEjec.get(0).actTime == 0){
                    results.add(new ProcessResult(i+1,enEjec.get(0).arrivTime,enEjec.get(0).servTime,enEjec.get(0).corePos));
                    enEjec.remove(0);
                    enEjec = hrrnArrange(enEjec,i+1);
                }
            }
            //poner este if arriba dependiendo de lo que diga el prof
            for(int j = 0; j<cargados.size();j++){
                if(cargados.get(j).arrivTime!=0 && i == cargados.get(j).arrivTime-1){
                    enEjec.add(cargados.get(j));
                }
            }
        }
    }
    public ArrayList<Processs> hrrnArrange(ArrayList<Processs> executed,int actTime){
        ArrayList<Processs> newList = new ArrayList<Processs>();
        for(int j = 0; j<executed.size(); j++){
            Processs newProces = executed.get(j);
            if(newList.size() == 0){
                newList.add(newProces);
            }
            else{
                for(int i = 0; i<newList.size();i++){
                    float npHRRN = (((float)actTime - (float)newProces.arrivTime)+(float)newProces.servTime)/(float)newProces.servTime;
                    float lastHRRN = (((float)actTime - (float)newList.get(i).arrivTime)+(float)newList.get(i).servTime)/(float)newList.get(i).servTime;
                    System.out.println(actTime);
                    System.out.println("Primero; "+newProces.name);
                    System.out.println("Segundo; "+newList.get(i).name);
                    System.out.println(npHRRN);
                    System.out.println(lastHRRN);
                    System.out.println("-----------");
                    if(npHRRN > lastHRRN){
                        newList.add(i, newProces);
                        break;
                    }
                    else if(i == newList.size()-1){
                        newList.add(newProces);
                        break;
                    }
                }
            }
        }
        return newList;
    }
    public void WAF_Alg(ArrayList<Processs> cargados){
        ArrayList<Processs> enEjec = new ArrayList<Processs>();
        ArrayList<Processs> enEjecLenta = new ArrayList<Processs>();
        int actQuantum = 0;
        for(int j = 0; j<cargados.size();j++){
            if(cargados.get(j).arrivTime==0){
                if(cargados.get(j).servTime>10){
                    enEjecLenta.add(cargados.get(j));
                }
                else{
                    enEjec.add(cargados.get(j));
                }    
            }
        }
        for(int i = 0;i<80;i++){
            if(actQuantum == 0){
                if(enEjec.size()!=0){
                    Processs toChange = enEjec.get(0);
                    enEjec.remove(0);
                    enEjec.add(toChange);
                    actQuantum = enEjec.get(0).servTime - (enEjec.get(0).servTime/2);
                }
                else{
                    actQuantum = 1;
                }
            }
            if(enEjec.size()!=0){
                tabla[enEjec.get(0).corePos][i] = 1;
                enEjec.get(0).actTime -= 1;
                actQuantum -= 1;
                if(enEjec.get(0).actTime == 0){
                    results.add(new ProcessResult(i+1,enEjec.get(0).arrivTime,enEjec.get(0).servTime,enEjec.get(0).corePos));
                    enEjec.remove(0);
                    if(enEjec.size()!=0){
                        actQuantum = enEjec.get(0).servTime - (enEjec.get(0).servTime/2);
                    }
                }
            }
            else if(enEjecLenta.size()!=0){
                tabla[enEjecLenta.get(0).corePos][i] = 1;
                enEjecLenta.get(0).actTime -= 1;
                actQuantum -= 1;
                if(enEjecLenta.get(0).actTime == 0){
                    results.add(new ProcessResult(i+1,enEjecLenta.get(0).arrivTime,enEjecLenta.get(0).servTime,enEjecLenta.get(0).corePos));
                    enEjecLenta.remove(0);
                }
            }
            for(int j = 0; j<cargados.size();j++){
                if(cargados.get(j).arrivTime!=0 && i == cargados.get(j).arrivTime-1){
                    if(cargados.get(j).servTime>10){
                        enEjecLenta.add(cargados.get(j));
                    }
                    else{
                        enEjec.add(cargados.get(j));
                    }
                }
            }
        }
    }
}
