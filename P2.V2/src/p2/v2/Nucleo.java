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
public class Nucleo {
    public int [][] coreTable;
    ArrayList<Processs> cargados;
    ArrayList<ProcessResult> results;
    Nucleo(String cpuType,ArrayList<Processs> cargados, int quantum){
        this.cargados = cargados;
        CpuAlgorithms procPlans = new CpuAlgorithms();
        System.out.println(cpuType);
        if(cpuType.equals("FCFS")){
            procPlans.FCFS_Alg(cargados);
        }
        else if(cpuType.equals("SJF")){
            procPlans.SJF_AP_Alg(cargados);
        }
        else if(cpuType.equals("RR")){
            procPlans.RR_Alg(cargados, quantum);
        }
        else if(cpuType.equals("Feedback")){
            procPlans.FeedBack_Alg(cargados, quantum);
        }
        else if(cpuType.equals("HRRN")){
            procPlans.HRRN_Alg(cargados);
        }
        else if(cpuType.equals("WAF")){
            procPlans.WAF_Alg(cargados);
        }
        else{
            procPlans.Priority_Alg(cargados);
        }
        coreTable = procPlans.tabla;
        results = procPlans.results;
    }
}
