/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p2.v2;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Akuseru
 */
public class Data {
    List<String[]> memoryProcesses = new ArrayList<>();
    List<String[]> virtualProcesses = new ArrayList<>();
    public void loadMemoryDynamic(List<String[]> code,int sizeOfMemory,int memorySize,int sizeOfVirtual,int virtualSize){
        
        for(int i=0;i<code.size();i++){
            String[] actual = code.get(i);
            int weight = Integer.parseInt(actual[4]);
            String[] process = {actual[0],actual[4]};
            if((sizeOfMemory+weight)<=memorySize){
                sizeOfMemory+=weight;   
                memoryProcesses.add(process);                
            }else if((sizeOfVirtual+weight)<=virtualSize){
                sizeOfVirtual+=weight;
                virtualProcesses.add(process);
            }
            else{
                System.out.println("Proceso "+(i+1)+" no puede entrar");                
            }
        }
    }
    public void loadMemoryPaging(List<String[]> code,int pageSize,int sizeOfMemory,int memorySize,int sizeOfVirtual,int virtualSize){
        
        for(int i=0;i<code.size();i++){
            String[] actual = code.get(i);
            int weight = Integer.parseInt(actual[4]);
            String[] process = {actual[0],actual[4]};
            if((sizeOfMemory+weight)<=memorySize){
                sizeOfMemory+=weight;   
                memoryProcesses.add(process);                
            }else if((sizeOfVirtual+weight)<=virtualSize){
                sizeOfVirtual+=weight;
                virtualProcesses.add(process);
            }
            else{
                System.out.println("Proceso "+(i+1)+" no puede entrar");                
            }
        }
    }
}
