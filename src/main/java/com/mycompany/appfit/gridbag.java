package com.mycompany.appfit;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;

    public class gridbag extends JFrame {

    private JLabel imagenLabel; // etiqueta donde se muestra la imagen

    public gridbag() {
        setTitle("Imagen redimensionable");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600); // tamaño inicial de la ventana
        setLocationRelativeTo(null); // centrar en pantalla

        // usa GridBagLayout para que el panel pueda expandirse
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // crea el JLabel que mostrará la imagen
        imagenLabel = new JLabel();
        imagenLabel.setHorizontalAlignment(JLabel.CENTER);
        imagenLabel.setVerticalAlignment(JLabel.CENTER);
        imagenLabel.setOpaque(true);
        imagenLabel.setBackground(Color.DARK_GRAY);

        // crea un JPanel que contiene el JLabel
        JPanel panelImagen = new JPanel(new BorderLayout());
        panelImagen.add(imagenLabel, BorderLayout.CENTER);

        // confugura el panel para que se expanda con la ventana
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH; // que se estire en ambas direcciones
        gbc.weightx = 1.0; // que ocupe todo el ancho disponible
        gbc.weighty = 1.0; // que ocupe todo el alto disponible
        add(panelImagen, gbc);

        // URL de la imagen que queremos mostrar
        String urlImagen = "https://img.freepik.com/vector-gratis/gym-doodles-set_78370-9697.jpg?semt=ais_hybrid&w=740&q=80";

        // carga la imagen inicial
        cargarImagen(urlImagen, 400, 300);

        // detecta cuando el JLabel cambia de tamaño para redimensionar la imagen
        imagenLabel.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                int ancho = imagenLabel.getWidth();
                int alto = imagenLabel.getHeight();
                cargarImagen(urlImagen, ancho, alto);
            }
        });

        setVisible(true);
    }

    // metodo para cargar y escalar la imagen desde internet
    private void cargarImagen(String urlImagen, int ancho, int alto) {
        try {
            URL url = new URL(urlImagen);
            ImageIcon iconoOriginal = new ImageIcon(url);
            Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
            imagenLabel.setIcon(new ImageIcon(imagenEscalada));
        } catch (Exception e) {
            imagenLabel.setText("No se pudo cargar la imagen");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(gridbag::new);
    }
}

