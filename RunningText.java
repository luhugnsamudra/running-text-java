/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.itcs;

/**
 *
 * @author h4r1p
 */
import javax.swing.*;
import java.awt.*;

public class RunningText extends JPanel {

    private int posisiX = 600;
    private final String teks = "AKU BANGGA MENJADI MAHASISWA IT";
    private Timer timer;
    private int kecepatan = 5;

    public RunningText() {
        setBackground(Color.BLACK);

        timer = new Timer(30, e -> {
            posisiX -= kecepatan;

            int lebar = getFontMetrics(
                    new Font("Arial", Font.BOLD, 32)
            ).stringWidth(teks);

            if (posisiX < -lebar) {
                posisiX = getWidth();
            }

            repaint();
        });

        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        // Background gradasi
        GradientPaint bg = new GradientPaint(
                0, 0, new Color(10, 10, 40),
                0, getHeight(), Color.BLACK
        );

        g2.setPaint(bg);
        g2.fillRect(0, 0, getWidth(), getHeight());

        // Judul
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Arial", Font.BOLD, 22));
        g2.drawString("RUNNING TEXT JAVA", 160, 45);

        // Garis dekorasi
        g2.setColor(Color.CYAN);
        g2.drawLine(20, 65, getWidth() - 20, 65);

        // Efek cahaya tulisan
        g2.setFont(new Font("Arial", Font.BOLD, 32));

        int lebar = g2.getFontMetrics().stringWidth(teks);

        GradientPaint warna = new GradientPaint(
                posisiX, 0, Color.CYAN,
                posisiX + lebar, 0, Color.MAGENTA
        );

        g2.setPaint(warna);
        g2.drawString(teks, posisiX, 125);
    }

    public static void main(String[] args) {

        JFrame frame = new JFrame("Running Text Mahasiswa IT");

        RunningText panel = new RunningText();

        JSlider slider = new JSlider(1, 15, 5);
        slider.setMajorTickSpacing(2);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);

        JLabel label = new JLabel("Atur Kecepatan:");

        slider.addChangeListener(e -> {
            panel.kecepatan = slider.getValue();
        });

        JPanel kontrol = new JPanel();
        kontrol.add(label);
        kontrol.add(slider);

        frame.setLayout(new BorderLayout());
        frame.add(panel, BorderLayout.CENTER);
        frame.add(kontrol, BorderLayout.SOUTH);

        frame.setSize(650, 260);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
