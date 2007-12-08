/*
 * Created on 2004/05/08
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.nullfish.lib.ui.grid;

import java.awt.Color;
import java.awt.Graphics;

/**
 * @author shunji
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class HorizontalLineGrid extends LineGrid {
	public HorizontalLineGrid() {
		
	}
	
	public HorizontalLineGrid(Object groupKey) {
		super(groupKey);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Color color = getForeground();
		int center = getHeight() / 2;
		if(doubleLine) {
			g.drawLine(0, center - 2, getWidth(), center - 2);
			g.drawLine(0, center + 2, getWidth(), center + 2);
		} else {
			g.drawLine(0, center, getWidth(), center);
		}
	}
}
