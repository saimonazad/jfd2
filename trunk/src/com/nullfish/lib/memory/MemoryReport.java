/*
 * Created on 2004/06/09
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.nullfish.lib.memory;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;
/**
 * É^ÉCÉgÉã:  JFD
 * ê‡ñæ:    A FD like file manager software written in Java.
 * íòçÏå†:   Copyright (c) Shunji Yamaura
 * âÔé–ñº:
 * @author Shunji Yamaura
 * @version 1.0
 */

public class MemoryReport implements ActionListener {
    Timer timer;

    public MemoryReport(int interval) {
        timer = new Timer(interval, this);
        timer.start();
    }

    public void showMemory() {
        long total = Runtime.getRuntime().totalMemory();
        System.out.println(
            "Total Memory: " + total + "\n" +
            "Spent Memory : " + (total - Runtime.getRuntime().freeMemory()) + "\n"
        );
    }

    public void actionPerformed(ActionEvent e) {
        showMemory();
    }
}
