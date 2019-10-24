/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p2.v2;

import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.ListModel;

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
        
    }
    private void changeUnsusedColor(Data memoryData){
        System.out.println(Arrays.toString(memoryData.unusedProcesses.toArray()));
        //System.out.println("Donde pinto: "+());
        listProcesses.setCellRenderer(new DefaultListCellRenderer() {
        @Override
        public Component getListCellRendererComponent(JList list, Object value, int index,
         boolean isSelected, boolean cellHasFocus) {
            Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            if(memoryData.unusedProcesses.contains(index)){
                setBackground(Color.RED);
                
            }/*
            else{
                setBackground(Color.GRAY);
            }*/
            return c;
        }
        });  
        listProcesses.repaint();     
        
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
                        virtualFree+=1;
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
        else
            System.out.println("NO ES DINAMICO");
    }
    
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
        
        setMemorySegments(memoryInfo);
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
        jScrollPane3 = new javax.swing.JScrollPane();
        listVirtual = new javax.swing.JList<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        listProcesses = new javax.swing.JList<>();
        jLabel1 = new javax.swing.JLabel();
        btnReset = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        lblBG = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Memory");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 20, -1, -1));

        jScrollPane2.setViewportView(listMemory);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 60, 250, 220));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Virtual Memory");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 20, -1, -1));

        jScrollPane3.setViewportView(listVirtual);

        getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 60, 250, 220));

        jScrollPane1.setViewportView(listProcesses);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 60, 250, 220));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Processes");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 20, 150, -1));

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
        getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, 1250, 10));

        lblBG.setBackground(new java.awt.Color(44, 47, 51));
        lblBG.setOpaque(true);
        getContentPane().add(lblBG, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 720));

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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblBG;
    private javax.swing.JList<String> listMemory;
    private javax.swing.JList<String> listProcesses;
    private javax.swing.JList<String> listVirtual;
    // End of variables declaration//GEN-END:variables
}
