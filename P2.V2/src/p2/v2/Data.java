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
                remaining[0] = "Remaining from  "+actual[0]+" --> "+strLeft;
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
    ArrayList<String[]> segmentedMemory = new ArrayList<>();
    ArrayList<String[]> segmentedVirtual = new ArrayList<>();
    
    private void addProcess(int k,String[] process){        
        try{
            segmentedMemory.add(k, process);
        }catch(Exception e){
            System.out.println("Se cayo");
            for (int j = 0; j < k; j++) {
                String[] process1 = {"asdsa","0"};
                segmentedMemory.add(j,process1);
            }
            segmentedMemory.add(k, process);
        }
    }
    
    int usedMemorySegs =0;
    int usedVirtualSegs =0;
    int[] segmentsVirtual;
    public void loadMemorySegments(List<String[]> code,int pageSize,int sizeOfMemory,int memorySize,ArrayList<Integer> memorySegments,ArrayList<Integer> virtualSegments){
        int[] segmentsMemoryFunc = new int[memorySegments.size()];
        int[] segmentsVirtualFunc = new int[virtualSegments.size()];
        
        for(int z=0;z<memorySegments.size();z++){
            segmentsMemoryFunc[z] = memorySegments.get(z);
        }
        for(int z=0;z<virtualSegments.size();z++){
            segmentsVirtualFunc[z] = virtualSegments.get(z);
        }
        
        for(int i=0;i<code.size();i++){            
            int flag=0;
            String[] actual = code.get(i);
            int weight = Integer.parseInt(actual[4]);
            
            for(int k=0;k<memorySegments.size();k++){                
                int actualMemorySegment= segmentsMemoryFunc[k];                
                if((actualMemorySegment-weight)>=0){
                    usedMemorySegs+=1;
                    segmentsMemoryFunc[k] = actualMemorySegment-weight;
                    String[] process = {actual[0],actual[4]};
                    addProcess(k,process);                    
                    flag=1;
                    break; 
                }
                
            }
            
            if(flag==0){     
                
                for(int k=0;k<virtualSegments.size();k++){
                    
                    int actualMemorySegment= segmentsVirtualFunc[k];
                    if((actualMemorySegment-weight)>=0){
                        usedVirtualSegs+=1;
                        segmentsVirtualFunc[k] = actualMemorySegment-weight;
                        String[] process = {actual[0],actual[4]};
                        //System.out.println("Entra en segmento:"+k);
                        try{
                            segmentedVirtual.add(k, process);
                            break;
                        }catch(Exception e){
                            System.out.println("Se cayo");
                            for (int j = 0; j < k; j++) {
                                String[] process1 = {"asdsa","0"};
                                segmentedVirtual.add(j,process1);
                            }
                            segmentedVirtual.add(k, process);
                            break;
                        }
                        
                    }
                }
            }            
        }
        
        /*System.out.println("SegMem "+Arrays.toString(memorySegments.toArray()));
        System.out.println("SegVirt "+Arrays.toString(virtualSegments.toArray()));*/
        for(int i=0;i<segmentedMemory.size();i++){
            //System.out.println("----------SEGM: "+(i+1));
            System.out.println("SegMem "+Arrays.toString(segmentedMemory.get(i)));                   
        }
        for(int i=0;i<segmentedVirtual.size();i++){
            //System.out.println("----------SEGM: "+(i+1));
            System.out.println("SegVir "+Arrays.toString(segmentedVirtual.get(i)));                   
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
