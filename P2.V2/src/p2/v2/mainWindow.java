/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p2.v2;

import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.ListModel;

/**
 *
 * @author Akuseru
 */

public class mainWindow extends javax.swing.JFrame {
    int quantum,memorySize,virtualSize;
    String memoryType,cpuType;
    ArrayList<Integer> partsList;List<String[]> code;
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
    private void setMemoriesPaging(Data memoryInfo){
            int index1=0;
            int index2=0;
            int pageCount1=1;
            int pageCount2=1;
            for(int i=0;i<memoryInfo.memoryProcesses.size();i++){                
                int weight = Integer.parseInt(memoryInfo.memoryProcesses.get(i)[1]);
                for(int y=0;y<weight;y++){
                    
                    if((index1%pageSize)==0){                        
                        memoryData.addElement("------Page: "+pageCount1+" ------");
                        pageCount1++;
                    }                    
                    index1++;
                    memoryData.addElement(index1+". "+memoryInfo.memoryProcesses.get(i)[0]);
                }
                for(int k=weight;k<pageSize;k++){
                    if((index1%pageSize)==0){                        
                        memoryData.addElement("------Page: "+pageCount1+" ------");
                        pageCount1++;
                    }                    
                    index1++;
                    memoryData.addElement(index1+". FREE SPACE");
                }
            }
            for(int i=0;i<memoryInfo.virtualProcesses.size();i++){                
                int weight = Integer.parseInt(memoryInfo.virtualProcesses.get(i)[1]);
                for(int y=0;y<weight;y++){                    
                    if((index2%pageSize)==0){                        
                        virtualData.addElement("------Page: "+pageCount2+" ------");
                        pageCount2++;
                    }                    
                    index2++;
                    virtualData.addElement(index2+". "+memoryInfo.virtualProcesses.get(i)[0]);
                }
                for(int k=weight;k<pageSize;k++){
                    if((index1%pageSize)==0){                        
                        memoryData.addElement("------Page: "+pageCount2+" ------");
                        pageCount2++;
                    }                    
                    index1++;
                    memoryData.addElement(index1+". FREE SPACE");
                }
            }
            listMemory.setModel(memoryData);
            listVirtual.setModel(virtualData);
    }
    
    private void setMemoriesDynamic(Data memoryInfo){
            int index1=0;
            int index2=0;
            
            for(int i=0;i<memoryInfo.memoryProcesses.size();i++){                
                int weight = Integer.parseInt(memoryInfo.memoryProcesses.get(i)[1]);
                for(int y=0;y<weight;y++){
                    
                    index1++;
                    memoryData.addElement(index1+". "+memoryInfo.memoryProcesses.get(i)[0]);
                }
            }
            for(int i=0;i<memoryInfo.virtualProcesses.size();i++){                
                int weight = Integer.parseInt(memoryInfo.virtualProcesses.get(i)[1]);
                for(int y=0;y<weight;y++){                    
                    index2++;
                    virtualData.addElement(index2+". "+memoryInfo.virtualProcesses.get(i)[0]);
                }
            }
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
        else
            System.out.println("NO ES DINAMICO");
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
        setPreferredSize(new java.awt.Dimension(1280, 720));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Memory");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 20, -1, -1));

        jScrollPane2.setViewportView(listMemory);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 60, 140, 220));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Virtual Memory");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 20, -1, -1));

        jScrollPane3.setViewportView(listVirtual);

        getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 60, 140, 220));

        jScrollPane1.setViewportView(listProcesses);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 60, 140, 220));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Processes");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 20, -1, -1));

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
