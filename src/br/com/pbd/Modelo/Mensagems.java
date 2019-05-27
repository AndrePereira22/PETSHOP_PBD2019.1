/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd.Modelo;

import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class Mensagems extends JDialog implements ActionListener {

    private static long serialVersionUID = 1L;
    private JFrame frame;
    private Point p;
    private JPanel messagePane, buttonPane;
    private JButton sim, nao, ok;
    private int botao1 = 1, botao2 = 2, botao3 = 3;

    public void CaixaPergunta(String message, String icone) {
        setFrame(new JFrame());
        setP(new Point(400, 400));
        setLocation(getP().x, getP().y);
        setMessagePane(new JPanel());
        Icon image = new ImageIcon(getClass().getResource(icone));
        getMessagePane().add(new JLabel(message, image, 0));
        getContentPane().add(getMessagePane());
        setButtonPane(new JPanel());
        setSim(new JButton("Sim"));
        setNao(new JButton("Nao"));
        getButtonPane().add(getSim());
        getButtonPane().add(getNao());
        getSim().addActionListener(this);
        getNao().addActionListener(this);
        getContentPane().add(getButtonPane(), BorderLayout.PAGE_END);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
        setVisible(true);
    }

    public void CaixaMensagem(String message, String icone) {
        setFrame(new JFrame());
        setP(new Point(400, 400));
        setLocation(getP().x, getP().y);
        setMessagePane(new JPanel());
        Icon image = new ImageIcon(getClass().getResource(icone));
        getMessagePane().add(new JLabel(message, image, 0));
        getContentPane().add(getMessagePane());
        setButtonPane(new JPanel());
        setOk(new JButton("Ok"));
        getButtonPane().add(getOk());
        getOk().addActionListener(this);
        getContentPane().add(getButtonPane(), BorderLayout.PAGE_END);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == getNao()) {
            setVisible(false);
            dispose();
            yygu(botao1);

        }
        if (e.getSource() == getSim()) {
            setVisible(false);
            dispose();
            yygu(botao2);

        }

        if (e.getSource() == getOk()) {
            setVisible(false);
            dispose();
            yygu(botao2);

        }
    }

    public int yygu(int r) {
        return r;
    }

    /**
     * @return the serialVersionUID
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    /**
     * @param aSerialVersionUID the serialVersionUID to set
     */
    public static void setSerialVersionUID(long aSerialVersionUID) {
        serialVersionUID = aSerialVersionUID;
    }

    /**
     * @return the frame
     */
    public JFrame getFrame() {
        return frame;
    }

    /**
     * @param frame the frame to set
     */
    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    /**
     * @return the p
     */
    public Point getP() {
        return p;
    }

    /**
     * @param p the p to set
     */
    public void setP(Point p) {
        this.p = p;
    }

    /**
     * @return the messagePane
     */
    public JPanel getMessagePane() {
        return messagePane;
    }

    /**
     * @param messagePane the messagePane to set
     */
    public void setMessagePane(JPanel messagePane) {
        this.messagePane = messagePane;
    }

    /**
     * @return the buttonPane
     */
    public JPanel getButtonPane() {
        return buttonPane;
    }

    /**
     * @param buttonPane the buttonPane to set
     */
    public void setButtonPane(JPanel buttonPane) {
        this.buttonPane = buttonPane;
    }

    /**
     * @return the sim
     */
    public JButton getSim() {
        return sim;
    }

    /**
     * @param sim the sim to set
     */
    public void setSim(JButton sim) {
        this.sim = sim;
    }

    /**
     * @return the nao
     */
    public JButton getNao() {
        return nao;
    }

    /**
     * @param nao the nao to set
     */
    public void setNao(JButton nao) {
        this.nao = nao;
    }

    /**
     * @return the ok
     */
    public JButton getOk() {
        return ok;
    }

    /**
     * @param ok the ok to set
     */
    public void setOk(JButton ok) {
        this.ok = ok;
    }

    /**
     * @return the botao1
     */
    public int getBotao1() {
        return botao1;
    }

    /**
     * @param botao1 the botao1 to set
     */
    public void setBotao1(int botao1) {
        this.botao1 = botao1;
    }

    /**
     * @return the botao2
     */
    public int getBotao2() {
        return botao2;
    }

    /**
     * @param botao2 the botao2 to set
     */
    public void setBotao2(int botao2) {
        this.botao2 = botao2;
    }

    /**
     * @return the botao3
     */
    public int getBotao3() {
        return botao3;
    }

    /**
     * @param botao3 the botao3 to set
     */
    public void setBotao3(int botao3) {
        this.botao3 = botao3;
    }

}
