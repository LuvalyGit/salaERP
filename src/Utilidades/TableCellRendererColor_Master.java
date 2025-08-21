/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilidades;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.font.TextAttribute;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author David Alcaman
 */
public class TableCellRendererColor_Master extends DefaultTableCellRenderer{
    int ColMaster;

    public void SetCol(int Col){
        ColMaster=Col;
    }
    @Override
    public Component getTableCellRendererComponent(JTable Grilla, Object o, boolean bln, boolean bln1, int Row, int Col) {
        Component componente = super.getTableCellRendererComponent(Grilla, o, bln, bln1, Row, Col); //To change body of generated methods, choose Tools | Templates.
        
        //Si separacion esta Completa
        if(Grilla.getValueAt(Row, 11).toString().trim().equals("2")){
            //Si Se Selecciona Fila
            if(Row==Grilla.getSelectedRow()){
                componente.setForeground(Color.white);
                componente.setBackground( new  Color(0, 100, 0));
                componente.setFont(new Font("Arial", Font.BOLD, 12));
            }
            //Si NO se Selecciona Fila
            else{
                componente.setForeground(Color.black);
                componente.setBackground(Color.green);
                componente.setFont(new Font("Arial", Font.PLAIN, 12));
            }
            
        }
        //Si separacion NO esta completa
        else{
            if(Grilla.getValueAt(Row, 11).toString().trim().equals("1")){
                
                if(Row==Grilla.getSelectedRow()){       //Si está Seleccionado
                    
                    componente.setForeground(Color.white);
                    componente.setBackground(new  Color(140, 90, 8));       
                    componente.setFont(new Font("Arial", Font.BOLD, 12));
                   
                
                
                }else{              //Si no está seleccionado
                   
                        componente.setForeground(Color.black);
                        componente.setBackground(Color.yellow);
                        componente.setFont(new Font("Arial", Font.PLAIN, 12));
                    
                }
                
            }else if(Grilla.getValueAt(Row, 11).toString().trim().equals("0")){
            
                
                if(Row==Grilla.getSelectedRow()){           //Si está Seleccionado
                 
                     if(Grilla.getValueAt(Row, 6).toString().trim().equals("Cerrado2")){       
                 
                   
                        componente.setForeground(Color.white);
                        componente.setBackground(new  Color(0, 204, 255));   //0,204,255
                        componente.setFont(new Font("Arial", Font.BOLD, 12));
                     
                    }else{
                    
                        componente.setForeground(Color.BLACK);
                        componente.setBackground(Color.gray);
                        componente.setFont(new Font("Arial", Font.BOLD, 12));
                         
                         
 
                    
                    }
                     
                 }else {            //Si No está Seleccionado
                    
                    
                    if(Grilla.getValueAt(Row, 6).toString().trim().equals("Cerrado2")){       
                 
                                                
                        componente.setForeground(Color.blue);
                        componente.setBackground(Color.white);
                        componente.setFont(new Font("Arial", Font.BOLD, 12));
                    
                     
                    }else {
                    
                        componente.setForeground(Color.BLACK);
                        componente.setBackground(Color.WHITE);
                        componente.setFont(new Font("Arial", Font.PLAIN, 12));
                    
                    }
                
                
                
                
                
                }  
                
            
            
            
            
            }
            
            
            
        }
        
        
        return componente;
    }

   
    
}
