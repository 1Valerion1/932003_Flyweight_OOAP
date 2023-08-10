package org.example;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.*;
import javax.swing.border.Border;


public class Window extends JFrame {
    private final Random random = new Random();
    public Window(String title) {
        SpriteMap.init();
        this.setTitle(title);
        this.setDefaultCloseOperation(3);
        this.addComponents();
        this.pack();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public static void main(String[] args) {
       new Window("Generating a 2d pixel map");
    }

    private void addComponents() {
        //creating the necessary components
        this.setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        final JPanel panel = new JPanel();
        //creating buttons
        JButton slow = new JButton("Без паттерна");
        JButton fast = new JButton("С использованием паттерна Flyweight");

        final JTextField slowText = new JTextField(15){
            @Override
            public void setBorder(Border border) {
            }
        };

        slowText.setBackground(Color.LIGHT_GRAY);
        slowText.setHorizontalAlignment(0);

        final JTextField fastText = new JTextField(15){
            @Override
            public void setBorder(Border border) {
            }
        };

        fastText.setBackground(Color.LIGHT_GRAY);
        fastText.setHorizontalAlignment(0);

        panel.setBackground(Color.LIGHT_GRAY);
        panel.setPreferredSize(new Dimension(704, 640));

        //in the usual way generation map
        slow.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                Graphics g = panel.getGraphics();
                long startTime = System.currentTimeMillis();

                for (int y = 0; y < panel.getHeight(); y += 32) {
                    for (int x = 0; x < panel.getWidth(); x += 32) {
                        MapTile t = new MapTile(Window.this.createRandomSprite(), x, y);
                        t.drawUsually(g);
                    }
                }

                long time = System.currentTimeMillis() - startTime;
                slowText.setText(String.valueOf(time) + " миллисекунд");
            }
        });

        //fluweigth pattern generation map
        fast.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                Graphics g = panel.getGraphics();
                long startTime = System.currentTimeMillis();

                for (int y = 0; y < panel.getHeight(); y += 32) {
                    for (int x = 0; x < panel.getWidth(); x += 32) {
                        MapTile t = TileFactory.getTile(Window.this.getRandomSprite());
                        t.drawPattern(g, x, y);
                    }
                }

                long time = System.currentTimeMillis() - startTime;
                fastText.setText(String.valueOf(time) + " миллисекунд");
            }
        });

        //setting up the location
        gc.gridx = 0;
        gc.gridy = 0;
        gc.gridwidth = 4;
        this.getContentPane().add((Component)panel, gc);
        gc.gridy = 1;
        gc.insets = new Insets(5, 5, 3, 5);
        gc.gridwidth = 1;
        this.getContentPane().add((Component)slowText, gc);
        gc.gridx = 1;
        this.getContentPane().add((Component)slow, gc);
        gc.gridx = 2;
        this.getContentPane().add((Component)fast, gc);
        gc.gridx = 3;
        this.getContentPane().add((Component)fastText, gc);
    }

    public SpriteMap getRandomSprite() {
        int i = this.random.nextInt(5);
        switch (i) {
            case 0: {
                return SpriteMap.dirt;
            }
            case 1: {
                return SpriteMap.water;
            }
            case 2: {
                return SpriteMap.mushrooms;
            }
            case 3: {
                return SpriteMap.tree;
            }
            case 4: {
                return SpriteMap.grass;
            }
        }
        return SpriteMap.dirt;
    }

    public SpriteMap createRandomSprite() {
        int i = this.random.nextInt(5);
        switch (i) {
            case 0: {
                return new SpriteMap(SpriteMap.loadImage("/grass.png"));
            }
            case 1: {
                return new SpriteMap(SpriteMap.loadImage("/dirt.png"));
            }
            case 2: {
                return new SpriteMap(SpriteMap.loadImage("/water.png"));
            }
            case 3: {
                return new SpriteMap(SpriteMap.loadImage("/mushrooms.png"));
            }
            case 4: {
                return new SpriteMap(SpriteMap.loadImage("/tree.png"));
            }
        }
        return new SpriteMap(SpriteMap.loadImage("/grass.png"));
    }
}

