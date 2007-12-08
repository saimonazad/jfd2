/*
 * Created on 2004/05/13
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.nullfish.lib.ui.xml_menu;

import java.io.File;

import javax.swing.JFrame;

import org.jdom.Document;
import org.jdom.input.SAXBuilder;

/**
 * @author shunji
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class XMLMenuTest {
	public static void main(String[] args) {
		try {
			Document doc = new SAXBuilder().build(new File(args[0]));
			XMLMenuBar menuBar = new XMLMenuBar();
			menuBar.convertFromNode(doc.getRootElement());
			
			JFrame frame = new JFrame();
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			frame.setJMenuBar(menuBar);
			
			frame.pack();
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
