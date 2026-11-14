package com.mycompany.Main;
import com.mycompany.Visual.VentanaPrincipal;
import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        VentanaPrincipal Vent1 = new VentanaPrincipal();
        Vent1.setVisible(true);
        Vent1.setLocationRelativeTo(null);
        Vent1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    
        Vent1.setResizable(false);
    }
}
