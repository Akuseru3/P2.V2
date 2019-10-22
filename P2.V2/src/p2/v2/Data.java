/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p2.v2;

import java.util.ArrayList;
import java.util.Arrays;
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
    public void loadMemoryFixed(List<String[]> code,int pageSize,int sizeOfMemory,int memorySize,int sizeOfVirtual,int virtualSize){
        String partSize = Integer.toString(pageSize);
        for(int i=0;i<code.size();i++){
            String[] actual = code.get(i);
            int weight = Integer.parseInt(actual[4]);
            int left = weight-pageSize;
            String[] process = {actual[0],actual[4]};
            String[] remaining = {"","RMNG"};
            if(left>0){                
                String strLeft = Integer.toString(left);
                process[1] = partSize;
                remaining[0] = "Remaining from process "+i+" --> "+strLeft;
            }
            
            if((sizeOfMemory+pageSize)<=memorySize){
                sizeOfMemory+=pageSize;   
                memoryProcesses.add(process);
                if(!(remaining[0].equals(""))){
                    memoryProcesses.add(remaining);
                }
            }else if((sizeOfVirtual+pageSize)<=virtualSize){
                sizeOfVirtual+=pageSize;
                virtualProcesses.add(process);
                if(!(remaining[0].equals(""))){
                    virtualProcesses.add(remaining);
                }
            }
            else{
                System.out.println("Proceso "+(i+1)+" no puede entrar");                
            }
            
        }
    }
    public void loadMemoryPaging(List<String[]> code,int pageSize,int sizeOfMemory,int memorySize,int sizeOfVirtual,int virtualSize){
        
        for(int i=0;i<code.size();i++){
            
            ArrayList<String> pages = new ArrayList<>();
            String[] actual = code.get(i);
            int weight = Integer.parseInt(actual[4]);
            int pagesWeight=Integer.parseInt(actual[4]);
            //System.out.println("Peso:"+pagesWeight);            
            String[] process ={"0","0"};
            process[0] = actual[0];           
            
            if(pagesWeight<=pageSize){
                //System.out.println("ININININ");
                pages.add(actual[4]);
                //process[1] = actual[4];
            }else{
                int usedCounter=1;
                for(int p=0;p<pagesWeight;p++){                    
                    if(usedCounter%pageSize==0){
                        //System.out.println("P: "+usedCounter);
                        pages.add(Integer.toString(usedCounter));
                        usedCounter=0;
                    }
                    usedCounter++;
                }
                //System.out.println(usedCounter);
                pages.add(Integer.toString(usedCounter-1));
                
            }
            
            if((sizeOfMemory+pageSize)<=memorySize){
                
                sizeOfMemory+=pageSize;
                for(int k=0;k<pages.size();k++){
                    process[1]=pages.get(k);
                    String[] realProcess = {process[0],pages.get(k)};
                    memoryProcesses.add(realProcess);
                }
                
                                
            }else if((sizeOfVirtual+pageSize)<=virtualSize){
                sizeOfVirtual+=pageSize;
                System.out.println(Arrays.toString(pages.toArray()));
                for(int w=0;w<pages.size();w++){
                    process[1]=pages.get(w);
                    String[] realProcess = {process[0],pages.get(w)};
                    virtualProcesses.add(realProcess);
                }
            }
            else{
                System.out.println("Proceso "+(i+1)+" no puede entrar");                
            }
            
        }   /*
        for(int z=0;z<forReal.size();z++){
             System.out.println("ASASAS: "+Arrays.toString(forReal.get(z)));
        }*/
    }
}
