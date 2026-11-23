package com.mycompany.Main;
import com.mycompany.Visual.VentanaDatos;
import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        VentanaDatos Vent2 = new VentanaDatos();
        Vent2.setVisible(true);
        Vent2.setLocationRelativeTo(null);
        Vent2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    
        Vent2.setResizable(false);
    }
}
