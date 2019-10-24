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
    /**
     * Creates new form mainWindow
     */
    
    private void loadProcesses(){
        DefaultListModel<String> model = new DefaultListModel<>();
        for(int i=0;i<code.size();i++){
            String[] actual=code.get(i);
            model.addElement((i+1)+". "+actual[0]);
        }
        listProcesses.setModel(model);
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
        
    }private void changeListVirColor(JList lista){
        
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
                for(int k=weight;k<pageSize;k++){
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
                        }                    
                        index1++;
                        listSpace1++;
                        memoryData.addElement(index1+". FREE SPACE");
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
            for(int i=0;i<memoryInfo.virtualProcesses.size();i++){
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
                        }                    
                        index2++;
                        listSpace2++;
                        virtualData.addElement(index2+". "+memoryInfo.virtualProcesses.get(i)[0]);
                        if(tipo2==0){
                            indexes2.add(listSpace2);
                        }
                    }
                    for(int k=weight;k<pageSize;k++){
                        if((index2%pageSize)==0){                        
                            virtualData.addElement("------Partition: "+pageCount2+" ------");
                            pageCount2++;
                        }                    
                        index2++;
                        listSpace2++;
                        virtualData.addElement(index2+". FREE SPACE");
                        if(tipo2==0){
                            indexes2.add(listSpace2);
                        }
                    }
                    if(tipo2==0){
                        tipo2=1;
                    }else{
                        tipo2=0;
                    }  
                }
            }
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
            changeListVirColor(listVirtual);
            listMemory.setModel(memoryData);
            listVirtual.setModel(virtualData);
    }
    private ArrayList<Integer> segmentsMemoryMain= new ArrayList<>();
    private ArrayList<Integer> segmentsVirtualMain= new ArrayList<>();
    private void setMemorySegments(Data memoryInfo,ArrayList<Integer> pPartsList,ArrayList<Integer> pPartsListVirtual){
        int index1=0;
        int index2=0;     
        int tipo1=0;
        int tipo2=0;
        int listSpace1=0;
        int listSpace2=0;
        ArrayList<String[]> memory = memoryInfo.segmentedMemory;
        ArrayList<String[]> virtual = memoryInfo.segmentedVirtual;        
        int inicio=0;
        for(int i=0;i<segmentsMemoryMain.size();i++){
            memoryData.addElement("-----Segment "+(i+1)+"-----");  
            listSpace1++;
            if(tipo1==0){
                indexes1.add(listSpace1-1);
            } 
            if(i>=memoryInfo.usedMemorySegs){                
                break;
            }else{
                int counter=0;
                int procesos=0;
                for(int k=0;k<memory.size();k++){                    
                    
                    if(counter>=segmentsMemoryMain.get(i)){
                        //System.out.println("Entra");                        
                        inicio+=procesos;                                                
                        break;
                    }
                    procesos++;                    
                    int weight=Integer.parseInt(memory.get(k)[1]);                    
                    if(weight==0){
                        counter+=segmentsMemoryMain.get(i);
                    }
                    counter+=weight;
                    //System.out.println("Counter: "+counter);
                    for(int j=0;j<weight;j++){
                        index1++;
                        listSpace1++;
                        memoryData.addElement(index1+". "+memory.get(k)[0]);
                        if(tipo1==0){
                            indexes1.add(listSpace1-1);
                        } 
                    }                    
                }
                for(int w=memorySize;w>=0;w--){
                    if(w==(procesos-1)){
                        memory.remove(w);
                        procesos--;
                    }
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
            if(i>=memoryInfo.usedMemorySegs){                
                break;
            }else{
                int counter=0;
                int procesos=0;
                for(int k=0;k<virtual.size();k++){                    
                    
                    if(counter>=segmentsVirtualMain.get(i)){
                        //System.out.println("Entra");                        
                        inicio+=procesos;                                                
                        break;
                    }
                    procesos++; 
                    
                    int weight=Integer.parseInt(virtual.get(k)[1]);
                    if(weight==0){
                        counter+=segmentsVirtualMain.get(i);
                    }
                    
                    counter+=weight;
                    //System.out.println("Counter: "+counter);
                    for(int j=0;j<weight;j++){
                        index2++;
                        listSpace2++;
                        virtualData.addElement(index2+". "+virtual.get(k)[0]);
                        if(tipo2==0){
                            indexes2.add(listSpace2-1);
                        } 
                    }                    
                }
                for(int w=virtualSize;w>=0;w--){
                    if(w==(procesos-1)){
                        virtual.remove(w);
                        procesos--;
                    }
                }
            }
            if(tipo2==0){
                tipo2=1;
            }else{
                tipo2=0;
            }            
        }
        changeListMemColor(listMemory);
        changeListVirColor(listVirtual);
        listMemory.setModel(memoryData);
        listVirtual.setModel(virtualData);
    }
    public mainWindow(List<String[]> pCode,int pPageSize,String pMemoryType,ArrayList<Integer> pPartsList,String pCpuType,int pQuantum,int pMemorySize,int pVirtualSize){
        initComponents();
        JTableHeader head1 = jTable1.getTableHeader();
        jTable1.getColumnModel().getColumn(0).setMinWidth(70);
        jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        head1.setFont(new Font("Segoe UI",Font.PLAIN,10));
        JTableHeader head2 = jTable2.getTableHeader();
        jTable2.getColumnModel().getColumn(0).setMinWidth(70);
        jTable2.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        head2.setFont(new Font("Segoe UI",Font.PLAIN,10));
        Processs p1 = new Processs("p1",3,0,1,150,0);
        Processs p2 = new Processs("p2",6,1,1,150,1);
        Processs p3 = new Processs("p3",4,3,1,150,2);
        Processs p4 = new Processs("p4",5,7,1,150,3);
        Processs p5 = new Processs("p5",2,10,1,150,4);

        cargados = new ArrayList<Processs>();
        cargados.add(p1);
        cargados.add(p2);
        cargados.add(p3);
        cargados.add(p4);
        cargados.add(p5);
        
        
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
        startN1();
    }
    ArrayList<Processs> cargados;
    int[][] result;
    public mainWindow(List<String[]> pCode,int pPageSize,String pMemoryType,ArrayList<Integer> pPartsList,ArrayList<Integer> pPartsListVirtual,String pCpuType,int pQuantum,int pMemorySize,int pVirtualSize){
        initComponents();
        code=pCode;memoryType=pMemoryType;partsList=pPartsList;partsListVirtual=pPartsListVirtual;
        cpuType=pCpuType;quantum= pQuantum;memorySize=pMemorySize;virtualSize=pVirtualSize;
        pageSize=pPageSize;
        segmentsMemoryMain = pPartsList;
        segmentsVirtualMain = pPartsListVirtual;
        
        loadProcesses();
        Data memoryInfo = new Data();        
        memoryInfo.loadMemorySegments(code,pageSize,sizeOfMemory,memorySize,partsList,partsListVirtual);
        
        setMemorySegments(memoryInfo,pPartsList,pPartsListVirtual);
    }
    
    public void startN1(){
        CpuAlgorithms algs = new CpuAlgorithms();
        algs.FCFS_Alg(cargados);
        result = algs.tabla;
        Thread t = new Thread(){
            public void run(){
                for(int i = 0; i<70;i++){
                    for(int j = 1; j<i+1;j++){
                        jTable1.getColumnModel().getColumn(j).setCellRenderer(new DefaultTableCellRenderer() {
                            @Override
                            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {
                                JLabel l = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
                                if(result[row][col-1] == 1){
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
                }
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

        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        listMemory = new javax.swing.JList<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        listVirtual = new javax.swing.JList<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        listProcesses = new javax.swing.JList<>();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        btnReset = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator1 = new javax.swing.JSeparator();
        lblBG = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Memory");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 10, -1, -1));

        jScrollPane2.setViewportView(listMemory);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 40, 190, 160));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Virtual Memory");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 10, -1, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Nucleo 2");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 390, 110, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Nucleo 1");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 240, 110, -1));

        jScrollPane3.setViewportView(listVirtual);

        getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 40, 200, 160));

        jScrollPane1.setViewportView(listProcesses);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 40, 180, 160));

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Proceso1", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {"Proceso2", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {"Proceso3", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {"Proceso4", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {"Proceso5", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {"Proceso6", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Procesos", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59", "60", "61", "62", "63", "64", "65", "66", "67", "68", "69", "70"
            }
        ));
        jScrollPane5.setViewportView(jTable2);

        getContentPane().add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 390, 1130, 120));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Proceso1", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {"Proceso2", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {"Proceso3", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {"Proceso4", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {"Proceso5", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {"Proceso6", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Procesos", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59", "60", "61", "62", "63", "64", "65", "66", "67", "68", "69", "70"
            }
        ));
        jScrollPane4.setViewportView(jTable1);

        getContentPane().add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 240, 1130, 120));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Processes");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 10, 150, -1));

        btnReset.setBackground(new java.awt.Color(255, 102, 102));
        btnReset.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnReset.setForeground(new java.awt.Color(255, 255, 255));
        btnReset.setText("Reset");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });
        getContentPane().add(btnReset, new org.netbeans.lib.awtextra.AbsoluteConstraints(1165, 20, 90, 40));

        btnBack.setBackground(new java.awt.Color(255, 102, 102));
        btnBack.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnBack.setForeground(new java.awt.Color(255, 255, 255));
        btnBack.setText("Back");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });
        getContentPane().add(btnBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 70, 40));
        getContentPane().add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 520, 1330, 20));
        getContentPane().add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 370, 1330, 20));
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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JLabel lblBG;
    private javax.swing.JList<String> listMemory;
    private javax.swing.JList<String> listProcesses;
    private javax.swing.JList<String> listVirtual;
    // End of variables declaration//GEN-END:variables
}
