/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p2.v2;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.ListModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author Akuseru
 */

public class mainWindow extends javax.swing.JFrame {
    int quantum,memorySize,virtualSize;
    String memoryType,cpuType;
    ArrayList<Integer> partsList;ArrayList<Integer> partsListVirtual;List<String[]> code;
    int sizeOfMemory = 0;int sizeOfVirtual=0;int pageSize;
    DefaultListModel<String> memoryData = new DefaultListModel<>();
    DefaultListModel<String> virtualData = new DefaultListModel<>();
    Nucleo nucleo1;
    Nucleo nucleo2;
    List<Integer> finishedProcesses = new ArrayList<>();
    Data mem;
    /**
     * Creates new form mainWindow
     */
    
    private void loadProcesses(){
        
        DefaultTableModel model = (DefaultTableModel) listProcesses.getModel();
        for(int i=0;i<code.size();i++){
            String[] actual=code.get(i);
            //model.addElement((i+1)+". "+actual[0]);
            model.addRow(new Object[]{actual[0], actual[1], actual[2],actual[3],actual[4]});
        }
        //listProcesses.setModel(model);
    }
    
    
    
    public mainWindow() {
        initComponents();
    }
    private void changeListMemColor(JList lista){
        
        //System.out.println("Donde pinto: "+());
        lista.setCellRenderer(new DefaultListCellRenderer() {
        @Override
        public Component getListCellRendererComponent(JList list, Object value, int index,
         boolean isSelected, boolean cellHasFocus) {
            Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            if(indexes1.contains(index)){
                setBackground(Color.LIGHT_GRAY);
            }/*
            else{
                setBackground(Color.GRAY);
            }*/
            return c;
        }
        });  
        lista.repaint();     
        
    }
    private void changeUnsusedColor(Data memoryData){
        mem = memoryData;
        listProcesses.getColumnModel().getColumn(5).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {
                JLabel l = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
                if(finishedProcesses.contains(row)){
                    l.setBackground(Color.decode("#ed3f45"));
                }
                else if(memoryData.unusedProcesses.contains(row)){
                    l.setBackground(Color.decode("#8d8d8d"));
                }
                else if(memoryData.core1Processes.contains(row)){
                    l.setBackground(Color.decode("#267f3d"));
                }
                else if(memoryData.core2Processes.contains(row)){
                    l.setBackground(Color.decode("#285e93"));
                }
                else if(memoryData.core1UnusedProcesses.contains(row)){
                    l.setBackground(Color.decode("#83c665"));
                }
                else if(memoryData.core2UnusedProcesses.contains(row)){
                    l.setBackground(Color.decode("#5595d3"));
                }
                else{
                    l.setBackground(Color.WHITE);
                }
                return l;
            }
        }); 
        jScrollPane8.repaint();
        //System.out.println("Donde pinto: "+());
        /*listProcesses.setCellRenderer(new DefaultListCellRenderer() {
        @Override
        public Component getListCellRendererComponent(JList list, Object value, int index,
         boolean isSelected, boolean cellHasFocus) {
            Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            if(memoryData.unusedProcesses.contains(index)){
                setBackground(Color.RED);
                
            }
            else{
                //setBackground(Color.GRAY);
            }
            return c;
        }
        });  
        listProcesses.repaint();  */   
        
    }
    private void changeListVirColor(JList lista){
        
        //System.out.println("Donde pinto: "+());
        lista.setCellRenderer(new DefaultListCellRenderer() {
        @Override
        public Component getListCellRendererComponent(JList list, Object value, int index,
         boolean isSelected, boolean cellHasFocus) {
            Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            if(indexes2.contains(index)){
                setBackground(Color.LIGHT_GRAY);
            }/*
            else{
                setBackground(Color.GRAY);
            }*/
            return c;
        }
        });  
        lista.repaint();       
        
    }
    private void setMemoriesPaging(Data memoryInfo){
            int index1=0;
            int index2=0;
            int tipo1=1;
            int tipo2=1;
            int pageCount1=1;
            int pageCount2=1;
            int listSpace1=0;
            int listSpace2=0;
            String lastProcess1="";
            String lastProcess2="";          
            for(int i=0;i<memoryInfo.memoryProcesses.size();i++){
                if(!memoryInfo.memoryProcesses.get(i)[0].equals(lastProcess1)){
                    lastProcess1=memoryInfo.memoryProcesses.get(i)[0];
                    pageCount1=1;
                    if(tipo1==0){
                        tipo1=1;
                    }else{
                        tipo1=0;
                    }
                }
                int weight = Integer.parseInt(memoryInfo.memoryProcesses.get(i)[1]);
                for(int y=0;y<weight;y++){
                    
                    if((index1%pageSize)==0){                        
                        memoryData.addElement("------Page: "+pageCount1+" ------");
                        pageCount1++;
                        listSpace1++;
                        if(tipo1==0){
                            indexes1.add(listSpace1-1);
                        }
                    }                    
                    index1++;
                    memoryData.addElement(index1+". "+memoryInfo.memoryProcesses.get(i)[0]);
                    listSpace1++;
                    if(tipo1==0){
                        indexes1.add(listSpace1-1);
                    }
                }
                if(weight!=0){
                    for(int k=weight;k<pageSize;k++){
                        System.out.println("KK: "+k);
                        if((index1%pageSize)==0){                        
                            memoryData.addElement("------Page: "+pageCount1+" ------");
                            pageCount1++;
                            listSpace1++;
                            if(tipo1==0){
                                indexes1.add(listSpace1-1);
                            }
                        }                    
                        index1++;
                        memoryData.addElement(index1+". FREE SPACE");
                        listSpace1++;
                        if(tipo1==0){
                            indexes1.add(listSpace1-1);
                        }
                    }
                }                
            }
            if(index1!=memorySize){
                memoryData.addElement("----- "+(memorySize-index1)+" unused spaces-----");                    
            }
            for(int i=0;i<memoryInfo.virtualProcesses.size();i++){
                if(!memoryInfo.virtualProcesses.get(i)[0].equals(lastProcess2)){
                    lastProcess2=memoryInfo.virtualProcesses.get(i)[0];
                    pageCount2=1;
                    if(tipo2==0){
                        tipo2=1;
                    }else{
                        tipo2=0;
                    }
                }                
                int weight = Integer.parseInt(memoryInfo.virtualProcesses.get(i)[1]);
                for(int y=0;y<weight;y++){                    
                    if((index2%pageSize)==0){                        
                        virtualData.addElement("------Page: "+pageCount2+" ------");
                        pageCount2++;
                        listSpace2++;
                        if(tipo2==0){
                            indexes2.add(listSpace2-1);
                        }
                    }                    
                    index2++;
                    virtualData.addElement(index2+". "+memoryInfo.virtualProcesses.get(i)[0]);
                    listSpace2++;
                    if(tipo2==0){
                        indexes2.add(listSpace2-1);
                    }
                }
                if(weight!=0){
                    for(int k=weight;k<pageSize;k++){
                        if((index2%pageSize)==0){                        
                            virtualData.addElement("------Page: "+pageCount2+" ------");
                            pageCount2++;
                            listSpace2++;
                            if(tipo2==0){
                                indexes2.add(listSpace2-1);
                            }
                        }                    
                        index2++;
                        virtualData.addElement(index2+". FREE SPACE");
                        listSpace2++;
                        if(tipo2==0){
                            indexes2.add(listSpace2-1);
                        }
                    }
                }
            }
            if(index2!=virtualSize){
                virtualData.addElement("----- "+(virtualSize-index2)+" unused spaces-----");                    
            }
            changeUnsusedColor(memoryInfo);
            changeListMemColor(listMemory);
            changeListVirColor(listVirtual);
            listMemory.setModel(memoryData);
            listVirtual.setModel(virtualData);
    }
    private void setMemoriesFixed(Data memoryInfo){
            int tipo1=0;
            int tipo2=0;
            int listSpace1=0;
            int listSpace2=0;
            int index1=0;
            int index2=0;
            int pageCount1=1;
            int pageCount2=1;      
            int memoryFree=0;
            int virtualFree=0;
            
            for(int i=0;i<memoryInfo.memoryProcesses.size();i++){
                                   
                if(memoryInfo.memoryProcesses.get(i)[1].equals("RMNG")){
                    memoryData.addElement(memoryInfo.memoryProcesses.get(i)[0]);
                    listSpace1++;
                    if(tipo1==1){
                        indexes1.add(listSpace1-1);
                    } 
                }else{
                    int weight = Integer.parseInt(memoryInfo.memoryProcesses.get(i)[1]);
                    for(int y=0;y<weight;y++){                    
                        if((index1%pageSize)==0){                        
                            memoryData.addElement("------Partition: "+pageCount1+" ------");
                            listSpace1++;
                            pageCount1++;
                            if(tipo1==0){
                                indexes1.add(listSpace1-1);
                            }
                        }                    
                        index1++;
                        listSpace1++;
                        memoryData.addElement(index1+". "+memoryInfo.memoryProcesses.get(i)[0]);
                        if(tipo1==0){
                            indexes1.add(listSpace1-1);
                        } 
                    }
                    for(int k=weight;k<pageSize;k++){
                        if((index1%pageSize)==0){                        
                            memoryData.addElement("------Partition: "+pageCount1+" ------");
                            listSpace1++;                            
                            pageCount1++;
                            if(tipo1==0){
                                indexes1.add(listSpace1-1);
                            }
                        }                    
                        index1++;
                        listSpace1++;
                        memoryData.addElement(index1+". FREE SPACE");
                        memoryFree+=1;
                        if(tipo1==0){
                            indexes1.add(listSpace1-1);
                        } 
                    }
                    if(tipo1==0){
                        tipo1=1;
                    }else{
                        tipo1=0;
                    }  
                }
            }
            if((index1)!=memorySize){
                memoryData.addElement("----- "+(memorySize-(index1))+" unused spaces left-----");                    
            }
            for(int i=0;i<memoryInfo.virtualProcesses.size();i++){
                System.out.println(listSpace2); 
                if(memoryInfo.virtualProcesses.get(i)[1].equals("RMNG")){
                    virtualData.addElement(memoryInfo.virtualProcesses.get(i)[0]);
                    listSpace2++;
                    if(tipo2==1){
                        indexes2.add(listSpace2-1);
                    } 
                }else{
                    int weight = Integer.parseInt(memoryInfo.virtualProcesses.get(i)[1]);
                    for(int y=0;y<weight;y++){                    
                        if((index2%pageSize)==0){                        
                            virtualData.addElement("------Partition: "+pageCount2+" ------");
                            pageCount2++;
                            listSpace2++;
                            if(tipo2==0){
                                indexes2.add(listSpace2-1);
                            } 
                        }                    
                        index2++;
                        listSpace2++;
                        virtualData.addElement(index2+". "+memoryInfo.virtualProcesses.get(i)[0]);
                        if(tipo2==0){
                            indexes2.add(listSpace2-1);
                        }
                    }
                    for(int k=weight;k<pageSize;k++){
                        if((index2%pageSize)==0){                        
                            virtualData.addElement("------Partition: "+pageCount2+" ------");
                            pageCount2++;
                            listSpace2++;
                            if(tipo2==0){
                                indexes2.add(listSpace2-1);
                            } 
                        }                    
                        index2++;
                        listSpace2++;
                        virtualData.addElement(index2+". FREE SPACE");
                        virtualFree+=1;
                        if(tipo2==0){
                            indexes2.add(listSpace2-1);
                        }
                    }
                    if(tipo2==0){
                        tipo2=1;
                    }else{
                        tipo2=0;
                    }
                    
                }
            }
            if(index2!=virtualSize){
                virtualData.addElement("----- "+(virtualSize-index2)+" unused spaces-----");                    
            }
            changeUnsusedColor(memoryInfo);
            changeListMemColor(listMemory);
            changeListVirColor(listVirtual);
            listMemory.setModel(memoryData);
            listVirtual.setModel(virtualData);
    }
    ArrayList<Integer> indexes1 = new ArrayList<>();
    ArrayList<Integer> indexes2 = new ArrayList<>();
    private void setMemoriesDynamic(Data memoryInfo){
            int index1=0;
            int index2=0;
            int tipo1=0;
            int tipo2=0;
            
            
            for(int i=0;i<memoryInfo.memoryProcesses.size();i++){                
                int weight = Integer.parseInt(memoryInfo.memoryProcesses.get(i)[1]);
                for(int y=0;y<weight;y++){                    
                    index1++;
                    memoryData.addElement(index1+". "+memoryInfo.memoryProcesses.get(i)[0]);
                    if(tipo1==0){
                        indexes1.add(index1-1);
                    } 
                }
                
                if(tipo1==0){
                    tipo1=1;
                }else{
                    tipo1=0;
                }                
            }
            if(index1!=memorySize){
                memoryData.addElement("----- "+(memorySize-index1)+" unused spaces-----");                    
            }
            changeListMemColor(listMemory);
            for(int i=0;i<memoryInfo.virtualProcesses.size();i++){                
                int weight = Integer.parseInt(memoryInfo.virtualProcesses.get(i)[1]);
                for(int y=0;y<weight;y++){                    
                    index2++;
                    virtualData.addElement(index2+". "+memoryInfo.virtualProcesses.get(i)[0]);
                    if(tipo2==0){
                        indexes2.add(index2-1);
                    } 
                }
                if(tipo2==0){
                    tipo2=1;
                }else{
                    tipo2=0;
                }
            }
            if(index2!=virtualSize){
                virtualData.addElement("----- "+(virtualSize-index2)+" unused spaces-----");                    
            }
            
            changeUnsusedColor(memoryInfo);
            changeListVirColor(listVirtual);
            listMemory.setModel(memoryData);
            listVirtual.setModel(virtualData);
    }
    private ArrayList<Integer> segmentsMemoryMain= new ArrayList<>();
    private ArrayList<Integer> segmentsVirtualMain= new ArrayList<>();
    private void setMemorySegments(Data memoryInfo){
        int index1=0;
        int index2=0;     
        int tipo1=0;
        int tipo2=0;
        int listSpace1=0;
        int listSpace2=0;
        
        ArrayList<String[]> memory = memoryInfo.segmentedMemory;
        ArrayList<String[]> virtual = memoryInfo.segmentedVirtual; 
        for(int i=0;i<segmentsMemoryMain.size();i++){
            memoryData.addElement("-----Segment "+(i+1)+"-----");
            listSpace1++;
            if(tipo1==0){
                indexes1.add(listSpace1-1);
            }
            int weights=0;
            int usedMemorySeg = segmentsMemoryMain.get(i)-memoryInfo.segmentsMemory[i];
            
            int procesos=0;
            if(usedMemorySeg!=0){
                for(int k=0;k<memory.size();k++){                
                    int weight=Integer.parseInt(memory.get(k)[1]);
                    weights+=weight;
                    procesos++;
                    for(int j=0;j<weight;j++){
                        index1++;
                        listSpace1++;
                        memoryData.addElement(index1+". "+memory.get(k)[0]);
                        if(tipo1==0){
                            indexes1.add(listSpace1-1);
                        }
                    }
                    if(weights == usedMemorySeg){
                        for(int w=memorySize;w>=0;w--){
                            if(w==(procesos-1)){
                                memory.remove(w);
                                procesos--;
                            }
                        }
                        break;
                    }
                }
            }
            if(memoryInfo.segmentsMemory[i]!=0){
                memoryData.addElement("----- "+(memoryInfo.segmentsMemory[i])+" unused spaces-----");
                listSpace1++;
                if(tipo1==0){
                    indexes1.add(listSpace1-1);
                }
            }
            if(tipo1==0){
                tipo1=1;
            }else{
                tipo1=0;
            }
        }
        for(int i=0;i<segmentsVirtualMain.size();i++){
            virtualData.addElement("-----Segment "+(i+1)+"-----");
            listSpace2++;
            if(tipo2==0){
                indexes2.add(listSpace2-1);
            }
            int weights=0;
            int usedVirtualSeg = segmentsVirtualMain.get(i)-memoryInfo.segmentsVirtual[i];
                        
            int procesos=0;
            if(usedVirtualSeg!=0){
                for(int k=0;k<virtual.size();k++){                
                    int weight=Integer.parseInt(virtual.get(k)[1]);
                    weights+=weight;
                    procesos++;
                    for(int j=0;j<weight;j++){
                        index2++;
                        listSpace2++;
                        virtualData.addElement(index2+". "+virtual.get(k)[0]);
                        if(tipo2==0){
                            indexes2.add(listSpace2-1);
                        }
                    }
                    if(weights == usedVirtualSeg){
                        for(int w=memorySize;w>=0;w--){
                            if(w==(procesos-1)){
                                virtual.remove(w);
                                procesos--;
                            }
                        }
                        break;
                    }
                }
            }
            if(memoryInfo.segmentsVirtual[i]!=0){
                virtualData.addElement("----- "+(memoryInfo.segmentsVirtual[i])+" unused spaces-----");
                listSpace2++;
                if(tipo2==0){
                    indexes2.add(listSpace2-1);
                }
            }
            if(tipo2==0){
                tipo2=1;
            }else{
                tipo2=0;
            }
        }
        changeUnsusedColor(memoryInfo);
        changeListMemColor(listMemory);
        changeListVirColor(listVirtual);
        listMemory.setModel(memoryData);
        listVirtual.setModel(virtualData);
    }
    
    public mainWindow(List<String[]> pCode,int pPageSize,String pMemoryType,ArrayList<Integer> pPartsList,String pCpuType,int pQuantum,int pMemorySize,int pVirtualSize){
        initComponents();
        listProcesses.getColumnModel().getColumn(5).setMaxWidth(25);
        listProcesses.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        JTableHeader head1 = jTable1.getTableHeader();
        jTable1.getColumnModel().getColumn(0).setMinWidth(70);
        jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        head1.setFont(new Font("Segoe UI",Font.PLAIN,10));
        JTableHeader head2 = jTable2.getTableHeader();
        jTable2.getColumnModel().getColumn(0).setMinWidth(70);
        jTable2.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        head2.setFont(new Font("Segoe UI",Font.PLAIN,10)); 
        code=pCode;memoryType=pMemoryType;partsList=pPartsList;cpuType=pCpuType;quantum= pQuantum;memorySize=pMemorySize;virtualSize=pVirtualSize;
        
        pageSize=pPageSize;
        loadProcesses();
        Data memoryInfo = new Data();
        if(pMemoryType.equals("Dynamic")){            
            memoryInfo.loadMemoryDynamic(code,sizeOfMemory,memorySize,sizeOfVirtual,virtualSize);
            setMemoriesDynamic(memoryInfo);
            
        }else if(pMemoryType.equals("Paging")){
            memoryInfo.loadMemoryPaging(code,pageSize,sizeOfMemory,memorySize,sizeOfVirtual,virtualSize);
            setMemoriesPaging(memoryInfo);
        }
        else if(pMemoryType.equals("Fixed")){
            memoryInfo.loadMemoryFixed(code,pageSize,sizeOfMemory,memorySize,sizeOfVirtual,virtualSize);
            setMemoriesFixed(memoryInfo);
        }
        else{
            System.out.println("NO ES DINAMICO");
        }
        nucleo1 = new Nucleo(pCpuType,memoryInfo.core1Procs,pQuantum);
        nucleo2 = new Nucleo(pCpuType,memoryInfo.core2Procs,pQuantum);
        startN1();
        startN2();
    }

    public mainWindow(List<String[]> pCode,int pPageSize,String pMemoryType,ArrayList<Integer> pPartsList,ArrayList<Integer> pPartsListVirtual,String pCpuType,int pQuantum,int pMemorySize,int pVirtualSize){
        initComponents();
        listProcesses.getColumnModel().getColumn(5).setMaxWidth(25);
        listProcesses.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        JTableHeader head1 = jTable1.getTableHeader();
        jTable1.getColumnModel().getColumn(0).setMinWidth(70);
        jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        head1.setFont(new Font("Segoe UI",Font.PLAIN,10));
        JTableHeader head2 = jTable2.getTableHeader();
        jTable2.getColumnModel().getColumn(0).setMinWidth(70);
        jTable2.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        head2.setFont(new Font("Segoe UI",Font.PLAIN,10)); 
        code=pCode;memoryType=pMemoryType;partsList=pPartsList;partsListVirtual=pPartsListVirtual;
        cpuType=pCpuType;quantum= pQuantum;memorySize=pMemorySize;virtualSize=pVirtualSize;
        
        pageSize=pPageSize;
        segmentsMemoryMain = pPartsList;
        segmentsVirtualMain = pPartsListVirtual;
        
        loadProcesses();
        Data memoryInfo = new Data();        
        memoryInfo.loadMemorySegments(code,pageSize,sizeOfMemory,memorySize,partsList,partsListVirtual);
        
        setMemorySegments(memoryInfo);
        nucleo1 = new Nucleo(pCpuType,memoryInfo.core1Procs,pQuantum);
        nucleo2 = new Nucleo(pCpuType,memoryInfo.core2Procs,pQuantum);
        startN1();
        startN2();
    }
    
    public void startN1(){
        DefaultTableModel model = (DefaultTableModel)jTable1.getModel();
        for(int i = 0; i<nucleo1.cargados.size();i++){
            model.setValueAt(nucleo1.cargados.get(i).name, i, 0);
        }
        DefaultTableModel modelStats = (DefaultTableModel)jTable3.getModel();
        for(int i = 0; i<nucleo1.cargados.size();i++){
            modelStats.setValueAt(nucleo1.cargados.get(i).name, 0, i+1);
        }
        Thread t = new Thread(){
            public void run(){
                System.out.println(nucleo1.results.size());
                int cont = 0;
                System.out.println("");
                for(int i = 0; i<80;i++){
                    for(int j = 0; j<nucleo1.results.size();j++){
                        ProcessResult result = nucleo1.results.get(j);
                        if(result.finalTime+1 == i){
                            DefaultTableModel modelStats = (DefaultTableModel)jTable3.getModel();
                            modelStats.setValueAt(result.finalTime, 1, result.tablePosition+1);
                            modelStats.setValueAt(result.turnAround, 2, result.tablePosition+1);
                            modelStats.setValueAt(result.turndService, 3, result.tablePosition+1);
                            cont+=1;
                            finishedProcesses.add(mem.core1Processes.get(nucleo1.cargados.get(result.tablePosition).corePos));
                            changeUnsusedColor(mem);
                            break;
                        }
                    }
                    for(int j = 1; j<i+1;j++){
                        jTable1.getColumnModel().getColumn(j).setCellRenderer(new DefaultTableCellRenderer() {
                            @Override
                            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {
                                JLabel l = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
                                if(nucleo1.coreTable[row][col-1] == 1){
                                    l.setBackground(Color.decode("#4169E1"));
                                }
                                else{
                                    l.setBackground(Color.WHITE);
                                }
                                return l;
                            }
                        }); 
                        jScrollPane4.repaint();
                    }
                    long initTime = System.currentTimeMillis();
                    while(System.currentTimeMillis()-initTime<1000){
                        
                    }
                    if(cont>=nucleo1.results.size()){
                        
                        System.out.println("salida a mano en momento ->"+i+"---Con contador:"+cont);
                        break;
                    }
                }
                float avgTr = 0;
                float avgTDS = 0; 
                for(int j = 0; j<nucleo1.results.size();j++){
                    avgTr += nucleo1.results.get(j).turnAround;
                    avgTDS += nucleo1.results.get(j).turndService;
                }
                avgTr = avgTr/nucleo1.results.size();
                avgTDS = avgTDS/nucleo1.results.size();
                avgTr = (float)Math.round(avgTr*100.0)/100;
                avgTDS = (float)Math.round(avgTDS*100.0)/100;
                DefaultTableModel modelStats = (DefaultTableModel)jTable3.getModel();
                modelStats.setValueAt(avgTr, 2, 7);
                modelStats.setValueAt(avgTDS, 3, 7);
            }
        };
        t.start();
    }
    public void startN2(){
        DefaultTableModel model = (DefaultTableModel)jTable2.getModel();
        for(int i = 0; i<nucleo2.cargados.size();i++){
            model.setValueAt(nucleo2.cargados.get(i).name, i, 0);
        }
        DefaultTableModel modelStats = (DefaultTableModel)jTable4.getModel();
        for(int i = 0; i<nucleo2.cargados.size();i++){
            modelStats.setValueAt(nucleo2.cargados.get(i).name, 0, i+1);
        }
        Thread t = new Thread(){
            public void run(){
                System.out.println(nucleo2.results.size());
                int cont = 0;
                System.out.println("");
                for(int i = 0; i<80;i++){
                    for(int j = 0; j<nucleo2.results.size();j++){
                        ProcessResult result = nucleo2.results.get(j);
                        if(result.finalTime+1 == i){
                            DefaultTableModel modelStats = (DefaultTableModel)jTable4.getModel();
                            modelStats.setValueAt(result.finalTime, 1, result.tablePosition+1);
                            modelStats.setValueAt(result.turnAround, 2, result.tablePosition+1);
                            modelStats.setValueAt(result.turndService, 3, result.tablePosition+1);
                            cont+=1;
                            finishedProcesses.add(mem.core2Processes.get(nucleo2.cargados.get(result.tablePosition).corePos));
                            changeUnsusedColor(mem);
                            break;
                        }
                    }
                    for(int j = 1; j<i+1;j++){
                        jTable2.getColumnModel().getColumn(j).setCellRenderer(new DefaultTableCellRenderer() {
                            @Override
                            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {
                                JLabel l = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
                                if(nucleo2.coreTable[row][col-1] == 1){
                                    l.setBackground(Color.decode("#4169E1"));
                                }
                                else{
                                    l.setBackground(Color.WHITE);
                                }
                                return l;
                            }
                        }); 
                        jScrollPane5.repaint();
                    }
                    long initTime = System.currentTimeMillis();
                    while(System.currentTimeMillis()-initTime<1000){
                        
                    }
                    if(cont>=nucleo2.results.size()){
                        
                        System.out.println("salida a mano en momento ->"+i+"---Con contador:"+cont);
                        break;
                    }
                }
                float avgTr = 0;
                float avgTDS = 0; 
                for(int j = 0; j<nucleo2.results.size();j++){
                    avgTr += nucleo2.results.get(j).turnAround;
                    avgTDS += nucleo2.results.get(j).turndService;
                }
                avgTr = avgTr/nucleo2.results.size();
                avgTDS = avgTDS/nucleo2.results.size();
                avgTr = (float)Math.round(avgTr*100.0)/100;
                avgTDS = (float)Math.round(avgTDS*100.0)/100;
                DefaultTableModel modelStats = (DefaultTableModel)jTable4.getModel();
                modelStats.setValueAt(avgTr, 2, 7);
                modelStats.setValueAt(avgTDS, 3, 7);
            }
        };
        t.start();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane8 = new javax.swing.JScrollPane();
        listProcesses = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        listMemory = new javax.swing.JList<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        listVirtual = new javax.swing.JList<>();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        btnReset = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator1 = new javax.swing.JSeparator();
        lblBG = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        listProcesses.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Process", "Service Time", "Arrival Time", "Priority", "Size", "⦾"
            }
        ));
        listProcesses.setEnabled(false);
        jScrollPane8.setViewportView(listProcesses);

        getContentPane().add(jScrollPane8, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 40, 440, 160));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Core 2 Result Table");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 570, 350, 30));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Core 1 Result Table");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 570, 350, 30));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Memory");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 10, -1, -1));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/p2/v2/cholors_mini.jpg"))); // NOI18N
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 123, 160));

        jScrollPane2.setViewportView(listMemory);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 40, 290, 160));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Virtual Memory");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 10, -1, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Core 2");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 390, 70, 30));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Core 1");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, -1, -1));

        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("?");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(1270, 80, 40, -1));

        jButton1.setForeground(new java.awt.Color(44, 47, 51));
        jButton1.setText("jButton1");
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1240, 100, -1, -1));

        jScrollPane3.setViewportView(listVirtual);

        getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 40, 290, 160));

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"----------", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {"----------", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {"----------", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {"----------", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {"----------", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {"----------", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Procesos", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59", "60", "61", "62", "63", "64", "65", "66", "67", "68", "69", "70", "71", "72", "73", "74", "75", "76", "77", "78", "79", "80"
            }
        ));
        jTable2.setEnabled(false);
        jScrollPane5.setViewportView(jTable2);

        getContentPane().add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 420, 1280, 120));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"----------", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {"----------", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {"----------", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {"----------", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {"----------", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {"----------", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Procesos", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59", "60", "61", "62", "63", "64", "65", "66", "67", "68", "69", "70", "71", "72", "73", "74", "75", "76", "77", "78", "79", "80"
            }
        ));
        jTable1.setEnabled(false);
        jScrollPane4.setViewportView(jTable1);

        getContentPane().add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 260, 1280, 120));

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, "--------", "--------", "--------", "--------", "--------", "--------", "Average"},
                {"TF", null, null, null, null, null, null, null},
                {"TR", null, null, null, null, null, null, null},
                {"TR/TS", null, null, null, null, null, null, null}
            },
            new String [] {
                "", "", "", "", "", "", "", ""
            }
        ));
        jTable3.setEnabled(false);
        jScrollPane6.setViewportView(jTable3);

        getContentPane().add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 610, 550, 75));

        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, "--------", "--------", "--------", "--------", "--------", "--------", "Promedio"},
                {"TF", null, null, null, null, null, null, null},
                {"TR", null, null, null, null, null, null, null},
                {"TR/TS", null, null, null, null, null, null, null}
            },
            new String [] {
                "", "", "", "", "", "", "", ""
            }
        ));
        jTable4.setEnabled(false);
        jScrollPane7.setViewportView(jTable4);

        getContentPane().add(jScrollPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 610, 550, 75));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Processes");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 10, 150, -1));

        btnReset.setBackground(new java.awt.Color(255, 102, 102));
        btnReset.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnReset.setForeground(new java.awt.Color(255, 255, 255));
        btnReset.setText("Reset");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });
        getContentPane().add(btnReset, new org.netbeans.lib.awtextra.AbsoluteConstraints(1230, 10, 90, 30));

        btnBack.setBackground(new java.awt.Color(255, 102, 102));
        btnBack.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnBack.setForeground(new java.awt.Color(255, 255, 255));
        btnBack.setText("Back");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });
        getContentPane().add(btnBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 70, -1));
        getContentPane().add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 550, 1330, 20));
        getContentPane().add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 390, 1330, 10));
        getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, 1330, 10));

        lblBG.setBackground(new java.awt.Color(44, 47, 51));
        lblBG.setOpaque(true);
        getContentPane().add(lblBG, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1350, 720));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        this.dispose();
        configWindow config = new configWindow();
        config.setVisible(true);
        
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        mainWindow newWindow =new mainWindow(code,pageSize,memoryType,partsList,cpuType,quantum,memorySize,virtualSize);
        newWindow.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnResetActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        jButton1.setOpaque(true);
        jButton1.setContentAreaFilled(true);
        jButton1.setBorderPainted(true);
        jLabel9.setText("wow!");
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(mainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(mainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(mainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(mainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new mainWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    private javax.swing.JLabel lblBG;
    private javax.swing.JList<String> listMemory;
    private javax.swing.JTable listProcesses;
    private javax.swing.JList<String> listVirtual;
    // End of variables declaration//GEN-END:variables
}
