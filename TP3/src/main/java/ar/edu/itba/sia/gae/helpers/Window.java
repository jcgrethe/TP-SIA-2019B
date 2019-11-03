package ar.edu.itba.sia.gae.helpers;

import apple.laf.JRSUIUtils;
import ar.edu.itba.sia.gae.models.GameCharacter;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Window extends JFrame {

    private class Board extends JPanel implements ActionListener{

        public Board(){

            initBoard();
        }

        private void initBoard() {

            setBackground(Color.black);
            setFocusable(true);

            final Timer timer = new Timer(100, new ActionListener() {
                @Override
                public void actionPerformed(final ActionEvent e) {
                    repaint();
                }
            });
            timer.start();
        }

        @Override
        public void paintComponent(Graphics g) {

            super.paintComponent(g);
            doDrawing(g);
            Toolkit.getDefaultToolkit().sync();

        }

        private void doDrawing(Graphics g) {

            GameCharacter character = FittestChar.getInstance().getChar();

            Graphics2D g2d = (Graphics2D) g;
            int x = (this.getWidth() - character.getImageWidth()) / 2;
            int y = (this.getHeight() - character.getImageHeight()) / 2;

            g2d.drawImage(character.getImage(), x,
                    y, this);

            int xVest = (this.getWidth() - 20) / 2;
            g.setColor(character.getVEST().getComponentColor());
            g.fillOval(xVest,140,20,20);

            int xHelmet = (this.getWidth() - 20) / 2;
            g.setColor(character.getHELMET().getComponentColor());
            g.fillOval(xHelmet,85,20,20);

            int xWeapon = (this.getWidth() - 20) / 2;
            g.setColor(character.getWEAPON().getComponentColor());
            g.fillOval(xWeapon - character.getImageWidth()/2 + 60,190,20,20);

            int xGloves = (this.getWidth() - 20) / 2;
            g.setColor(character.getGLOVES().getComponentColor());
            g.fillOval(xGloves + character.getImageWidth()/2 - 60,190,20,20);

            int xBoots = (this.getWidth() - 20) / 2;
            g.setColor(character.getBOOTS().getComponentColor());
            g.fillOval(xBoots + character.getImageWidth()/2 - 60,260,20,20);
            g.fillOval(xBoots - character.getImageWidth()/2 + 60,260,20,20);

        }

        @Override
        public void actionPerformed(ActionEvent e) {
            repaint();
        }
    }

    public Window(){

        add(new Board());

        initUI();
    }

    private void initUI() {

        setTitle("Genetic algorithm warrior");
        setSize(600, 400);

        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


}
