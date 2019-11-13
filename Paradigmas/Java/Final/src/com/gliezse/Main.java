package com.gliezse;

import com.gliezse.clases.Empresa;
import com.gliezse.graphic.EmpleadosManager;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        Empresa empresa = new Empresa(JOptionPane.showInputDialog("Bienvenido al sistema de managing de empleados.\n\nIngrese el nombre de la empresa."));

        if (empresa.getNombre() != null && !empresa.getNombre().isEmpty()){
            JFrame frame = new JFrame("Empleados Manager - " + empresa.getNombre());
            frame.setContentPane(new EmpleadosManager(empresa).panelMain);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setPreferredSize(new Dimension(1000, 600));
            frame.pack();
            frame.setVisible(true);
        }
    }
}
