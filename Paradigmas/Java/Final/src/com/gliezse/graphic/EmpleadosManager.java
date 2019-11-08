package com.gliezse.graphic;

import com.gliezse.clases.Empleado;
import com.gliezse.clases.Empresa;
import com.gliezse.clases.Operario;
import com.gliezse.clases.Vendedor;
import com.gliezse.intefaces.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;
import java.util.List;

public class EmpleadosManager {
    private JPanel panelMain;
    private JTextField apellidoField;
    private JComboBox sexoField;
    private JTextField dniField;
    private JRadioButton operarioRadioButton;
    private JRadioButton vendedorRadioButton;
    private JTextField cantHorasField;
    private JTextField montoVendidoField;
    private JTextField valorHoraField;
    private JTextField porcentajeField;
    private JTextField sueldoBasicoField;
    private JButton registrarEmpleadoButton;
    private JTextField sueldoNetoField;
    private JButton mostrarEmpleadosRegistradosButton;
    private JTextArea empleadosTextArea;
    private JLabel errorLegajo;
    private JTextField legajoField;
    private JFormattedTextField legajoFormattedTextField;

    private Empresa empresa = new Empresa("Horacio Records");

    public EmpleadosManager() {

        legajoField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                checkNumeric(e);
            }
        });
        apellidoField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                checkAlphabetic(e);
            }
        });
        dniField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                checkNumeric(e);
            }
        });
        cantHorasField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                checkNumeric(e);
            }
        });
        montoVendidoField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                checkNumeric(e);
            }
        });
        sueldoBasicoField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                checkNumeric(e);
            }
        });
        valorHoraField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
            }
        });
        porcentajeField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                checkNumeric(e);
            }
        });
        operarioRadioButton.addActionListener(e -> {
            vendedorRadioButton.setSelected(false);

            cantHorasField.setEnabled(true);
            valorHoraField.setEnabled(true);

            montoVendidoField.setEnabled(false);
            sueldoBasicoField.setEnabled(false);
            porcentajeField.setEnabled(false);
        });
        vendedorRadioButton.addActionListener(e -> {
            operarioRadioButton.setSelected(false);

            cantHorasField.setEnabled(false);
            valorHoraField.setEnabled(false);

            montoVendidoField.setEnabled(true);
            sueldoBasicoField.setEnabled(true);
            porcentajeField.setEnabled(true);
        });
        registrarEmpleadoButton.addActionListener(e -> {
            if (!emptyFields()){
                String legajo = legajoField.getText();
                String apellido = apellidoField.getText();
                char sexo = sexoField.getSelectedItem().toString() == "Masculino" ? Constants.sexoMasculino : Constants.sexoFemenino;
                String dni = dniField.getText();

                if (operarioRadioButton.isSelected()){
                    double cantidadHoras = Double.parseDouble(cantHorasField.getText());
                    double valorHora = Double.parseDouble(valorHoraField.getText());

                    Operario operario = new Operario(apellido, dni, sexo, legajo, cantidadHoras, valorHora);
                    sueldoNetoField.setText( "$" + operario.getSueldo());

                    empresa.addEmpleados(operario);
                } else {
                    double sueldoBase = Double.parseDouble(sueldoBasicoField.getText());
                    double montoVenta = Double.parseDouble(montoVendidoField.getText());
                    double comisionVenta = Double.parseDouble(porcentajeField.getText());

                    Vendedor vendedor = new Vendedor(apellido, dni, sexo, legajo, sueldoBase, montoVenta, comisionVenta);
                    sueldoNetoField.setText( "$" + vendedor.getSueldo());

                    empresa.addEmpleados(vendedor);
                }
            } else {
                JOptionPane.showMessageDialog(panelMain, "Aún hay campos vacios");
            }
        });
        mostrarEmpleadosRegistradosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Empleado> lista = empresa.getListaEmpleados();

                empleadosTextArea.setText("");

                if (lista.size() == 0){
                    empleadosTextArea.setText("gg bro");
                } else {
                    List<String> data = new LinkedList<>();

                    lista.forEach(empleado -> {
                        data.add(empleado.getApellido() + " " + empleado.getDni());
                    });

                    data.forEach(dato -> {
                        empleadosTextArea.append(dato + "\n");
                    });
                }

            }
        });
    }

    public void checkNumeric(KeyEvent e){
        if (!Character.isDigit(e.getKeyChar())){
            e.consume();
        }
    }

    public void checkAlphabetic(KeyEvent e){
        if (!Character.isAlphabetic(e.getKeyChar())){
            e.consume();
        }
    }

    public boolean emptyFields(){
        if (operarioRadioButton.isSelected()){
            return (
                    legajoField.getText().isEmpty() || apellidoField.getText().isEmpty() || dniField.getText().isEmpty()
                    || cantHorasField.getText().isEmpty() || valorHoraField.getText().isEmpty()
            );
        } else if (vendedorRadioButton.isSelected()){
            return (
                    legajoField.getText().isEmpty() || apellidoField.getText().isEmpty() || dniField.getText().isEmpty()
                    || montoVendidoField.getText().isEmpty() || sueldoBasicoField.getText().isEmpty() || porcentajeField.getText().isEmpty()
            );
        } else {
            return true;
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("EmpleadosManager");
        frame.setContentPane(new EmpleadosManager().panelMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(1000, 600));
        frame.pack();
        frame.setVisible(true);
    }
}
