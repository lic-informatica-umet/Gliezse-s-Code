package com.gliezse.graphic;

import com.gliezse.clases.Empleado;
import com.gliezse.clases.Empresa;
import com.gliezse.clases.Operario;
import com.gliezse.clases.Vendedor;
import com.gliezse.intefaces.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class EmpleadosManager extends Container {
    public JPanel panelMain;
    private JTextField apellidoField;
    private JComboBox<ComboItem> sexoField;
    private JTextField dniField;
    private JRadioButton operarioRadioButton;
    private JRadioButton vendedorRadioButton;
    private JTextField cantHorasField;
    private JTextField montoVendidoField;
    private JTextField valorHoraField;
    private JTextField porcentajeField;
    private JTextField sueldoBasicoField;
    private JButton registrarEmpleadoButton;
    private JLabel sueldoNetoField;
    private JButton mostrarEmpleadosRegistradosButton;
    private JTextArea empleadosTextArea;
    private JTextField legajoField;
    private JLabel sueldoAcumuladoTotalLabel;

    public EmpleadosManager(Empresa empresa) {
        sexoField.addItem(new ComboItem(Constants.sexoMasculinoKey, Constants.sexoMasculinoValue));
        sexoField.addItem(new ComboItem(Constants.sexoFemeninoKey, Constants.sexoFemeninoValue));

        //Validadores
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
            montoVendidoField.setText("");
            sueldoBasicoField.setEnabled(false);
            sueldoBasicoField.setText("");
            porcentajeField.setEnabled(false);
            porcentajeField.setText("");
        });
        vendedorRadioButton.addActionListener(e -> {
            operarioRadioButton.setSelected(false);

            cantHorasField.setEnabled(false);
            cantHorasField.setText("");
            valorHoraField.setEnabled(false);
            valorHoraField.setText("");

            montoVendidoField.setEnabled(true);
            sueldoBasicoField.setEnabled(true);
            porcentajeField.setEnabled(true);
        });

        //Submit y display empleados
        registrarEmpleadoButton.addActionListener(e -> {
            if (!emptyFields()){
                String legajo = legajoField.getText();
                String apellido = apellidoField.getText();
                String sexo = sexoField.getSelectedItem().toString().equals(Constants.sexoMasculinoKey) ? Constants.sexoMasculinoValue : Constants.sexoFemeninoValue;
                String dni = dniField.getText();

                double sueldo;

                try {
                    if (operarioRadioButton.isSelected()) {
                        double cantidadHoras = Double.parseDouble(cantHorasField.getText());
                        double valorHora = Double.parseDouble(valorHoraField.getText());

                        Operario operario = new Operario(apellido, dni, sexo, legajo, cantidadHoras, valorHora);
                        sueldo = operario.getSueldo();
                        sueldoNetoField.setText("$" + sueldo);

                        empresa.addEmpleados(operario);
                    } else {
                        double sueldoBase = Double.parseDouble(sueldoBasicoField.getText());
                        double montoVenta = Double.parseDouble(montoVendidoField.getText());
                        double comisionVenta = Double.parseDouble(porcentajeField.getText());

                        Vendedor vendedor = new Vendedor(apellido, dni, sexo, legajo, sueldoBase, montoVenta, comisionVenta);
                        sueldo = vendedor.getSueldo();
                        sueldoNetoField.setText("$" + sueldo);

                        empresa.addEmpleados(vendedor);
                    }
                    sueldoAcumuladoTotalLabel.setText("$" + getSueldoTotal(empresa));
                    JOptionPane.showMessageDialog(panelMain, "El empleado ha sido agregado exitosamente.");

                    cleanFields();
                } catch (NumberFormatException ex){
                    JOptionPane.showMessageDialog(panelMain, "Los datos ingresados fueron invalidos");
                }
            } else {
                JOptionPane.showMessageDialog(panelMain, "Aún hay campos vacios");
            }
        });
        mostrarEmpleadosRegistradosButton.addActionListener(e -> {
            List<Empleado> lista = empresa.getListaEmpleados();

            if (lista.size() == 0){
                empleadosTextArea.setText("Aún no se han registrado empleados");
            } else {
                empleadosTextArea.setText("Empleados de " + empresa.getNombre() + "\n\n");
                lista.forEach(empleado -> {
                    empleadosTextArea.append(
                            "Legajo: " + empleado.getLegajo() +
                            "\nDNI: " + empleado.getDni() +
                            "\nApellido: " + empleado.getApellido() +
                            "\nSexo: " + empleado.getSexo() +
                            "\nPuesto: "
                    );
                    if (empleado instanceof Vendedor){
                        empleadosTextArea.append(
                            "Vendedor" +
                            "\nSueldo: $" +
                            ((Vendedor) empleado).getSueldo()
                        );
                    } else {
                        empleadosTextArea.append(
                            "Operario" +
                            "\nSueldo: $" +
                            ((Operario) empleado).getSueldo()+""
                        );
                    }
                    empleadosTextArea.append("\n\n");
                });
            }

        });
    }

    public void checkNumeric(KeyEvent e){
        if (!Character.isDigit(e.getKeyChar()) && e.getKeyChar() != '.'){
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

    public void cleanFields(){
        legajoField.setText("");
        apellidoField.setText("");
        dniField.setText("");

        operarioRadioButton.setSelected(false);
        vendedorRadioButton.setSelected(false);

        cantHorasField.setText("");
        valorHoraField.setText("");

        sueldoBasicoField.setText("");
        montoVendidoField.setText("");
        porcentajeField.setText("");
    }

    public double getSueldoTotal(Empresa empresa){
        final double[] sueldoTotal = {0};

        empresa.getListaEmpleados().forEach(empleado -> {
            if (empleado instanceof Vendedor){
                sueldoTotal[0] += ((Vendedor) empleado).getSueldo();
            } else {
                sueldoTotal[0] += ((Operario) empleado).getSueldo();
            }
        });

        //Se limita a 2 decimales
        int sueldoEntero = (int)(sueldoTotal[0]*100);
        return (double)(sueldoEntero)/100;
    }
}
