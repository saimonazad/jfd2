/*
 * Created on 2004/05/08
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.nullfish.lib.ui.grid;

import java.awt.Graphics;

/**
 * @author shunji
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class VerticalLineGrid extends LineGrid {
	public VerticalLineGrid() {
		
	}
	
	public VerticalLineGrid(Object groupKey) {
		super(groupKey);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		int center = getWidth() / 2;

		if(doubleLine) {
			g.drawLine(center - 2, 0, center - 2, getHeight());
			g.drawLine(center + 2, 0, center + 2, getHeight());
		} else {
			g.drawLine(center, 0, center, getHeight());
		}
	}
}
