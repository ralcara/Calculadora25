/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.calculadora25;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 *
 * @author rocio
 */
public class PanelPrincipal extends JPanel implements ActionListener {

    private Botones botonera;
    private JTextArea areaTexto;
    private double resultado;
    private String operacion;
    private boolean nuevaOperacion;

    // Constructor
    public PanelPrincipal() {
        initComponents();
        nuevaOperacion = true; // No hay operaciones en la calculadora
    }

    // colocar los componentes
    private void initComponents() {
        // Crear botones
        botonera = new Botones();
        // creamos huecos para escribir
        areaTexto = new JTextArea(10, 50);
        areaTexto.setEditable(false);
        areaTexto.setBackground(Color.white);

        this.setLayout(new BorderLayout());
        // añadir botones y huecos de texto
        this.add(areaTexto, BorderLayout.NORTH);
        this.add(botonera, BorderLayout.SOUTH);
        for (JButton boton : this.botonera.getgrupoBotones()) {
            boton.addActionListener(this);
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String textoBoton = ((JButton) e.getSource()).getText();

        switch (textoBoton) {
            case "=":
                calcular(Double.parseDouble(areaTexto.getText()));
                operacion = "";
                nuevaOperacion = true;
                break;

            case "C":
                areaTexto.setText("0");
                operacion = "";
                resultado = 0;
                nuevaOperacion = true;
                break;

            default:
                if (esNumero(textoBoton)) {
                    if (nuevaOperacion) {
                        areaTexto.setText(textoBoton);
                        nuevaOperacion = false;
                    } else {
                        areaTexto.append(textoBoton);
                    }
                } else {
                    resultado = Double.parseDouble(areaTexto.getText());
                    operacion = textoBoton;
                    nuevaOperacion = true;
                }
        }
    }

    private void calcular(double numero) {
        switch (operacion) {
            case "+":
                resultado += numero;
                break;
            case "-":
                resultado -= numero;
                break;
            case "*":
                resultado *= numero;
                break;
            case "/":
                if (numero != 0) {
                    resultado /= numero;
                } else {
                    JOptionPane.showMessageDialog(this, "No se puede dividir por cero");
                }
                break;
            default:
                resultado = numero;
        }

        areaTexto.setText(String.valueOf(resultado));
    }

    private boolean esNumero(String texto) {
        try {
            Double.parseDouble(texto);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
