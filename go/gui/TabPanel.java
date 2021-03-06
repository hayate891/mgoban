/*
 *  mGoban - GUI for Go
 *  Copyright (C) 2007, 2009, 2010  sanpo
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */   

package go.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TabPanel extends javax.swing.JPanel implements MouseListener{
    private ArrayList<JPanel> fixedPanelList;
    
    public TabPanel() {
        fixedPanelList = new ArrayList<JPanel>();
        
        initComponents();
    }
    
    public void addTab(String name, JPanel panel, boolean fixed){
        JLabel label = new JLabel(name);
        label.addMouseListener(this);
        
        pane.addTab(null, panel);
        int index = pane.indexOfComponent(panel);
        pane.setTabComponentAt(index, label);
        
        if(fixed){
            fixedPanelList.add(panel);
        }
    }
    
    public Component getComponent(String name){
        int n = pane.getTabCount();
        for(int i = 0; i < n; ++i){
            JLabel label = (JLabel) pane.getTabComponentAt(i);
            if(name.equals(label.getText())){
                Component c = pane.getComponentAt(i);
                return c;
            }
        }
        return null;
    }
    
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mouseClicked(MouseEvent e) {
        JLabel label = (JLabel)e.getSource();
        int index = pane.indexOfTabComponent(label);
        JPanel panel = (JPanel) pane.getComponentAt(index);
        
        if(e.getButton() == MouseEvent.BUTTON1){
            System.out.println("TabPanel:mouseClicked: button1:" +  e);
            pane.setSelectedIndex(index);
        }else if(e.getButton() == MouseEvent.BUTTON2){
            System.out.println("TabPanel:mouseClicked: button2:" +  e);
            label.removeMouseListener(this);
            if(isFixedTab(panel) == false){
                pane.remove(index);
            }
        }
    }
    
    public void tabColorChange(JPanel p){
        int index = pane.indexOfComponent(p);
        JLabel label = (JLabel) pane.getTabComponentAt(index);
        if(label != null){
            label.setForeground(Color.RED);
        }
    }
    
    private boolean isFixedTab(JPanel panel){
        for(JPanel p : fixedPanelList){
            if(panel == p){
                return true;
            }
        }
        return false;
    }
    
    
    public void select(Component c){
        pane.setSelectedComponent(c);
    }

    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        pane = new javax.swing.JTabbedPane();

        pane.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);
        pane.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                paneStateChanged(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pane, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pane, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    
    private void paneStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_paneStateChanged
        int index = pane.getSelectedIndex();
        JLabel label = (JLabel) pane.getTabComponentAt(index);
        if(label != null){
            label.setForeground(Color.BLACK);
        }
    }//GEN-LAST:event_paneStateChanged
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane pane;
    // End of variables declaration//GEN-END:variables
}