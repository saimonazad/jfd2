/*
 * Created on 2004/05/07
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.nullfish.lib.ui.grid;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.geom.Rectangle2D;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author shunji
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public abstract class LineGrid extends JPanel {
	protected static Map gridGroupMap = new WeakHashMap();
	
	protected boolean doubleLine = true;
	
	/**
	 * �f�t�H���g�R���X�g���N�^
	 *
	 */
	public LineGrid() {
		this(null);
		setOpaque(false);
	}

	/**
	 * �R���X�g���N�^
	 * @param groupKey	�O���b�h�̃O���[�v�̃L�[
	 */
	public LineGrid(Object groupKey) {
		Set group = (Set)gridGroupMap.get(groupKey);
		if(group == null) {
			group = new HashSet();
			gridGroupMap.put(groupKey, group);
		}
		group.add(this);
		setOpaque(false);
		
		setForeground(Color.WHITE);
	}

	public static void setGroupDoubleLine(boolean bool, Object groupKey) {
		Set group = (Set)gridGroupMap.get(groupKey);
		if(group == null) {
			return;
		}
		
		Iterator ite = group.iterator();
		while(ite.hasNext()) {
			LineGrid grid = (LineGrid)ite.next();
			grid.setDoubleLine(bool);
		}
	}
	
	public void setDoubleLine(boolean bool) {
		this.doubleLine = bool;
		repaint();
	}
	
	public static void setGroupColor(Color color, Object groupKey) {
		Set group = (Set)gridGroupMap.get(groupKey);
		if(group == null) {
			return;
		}
		
		Iterator ite = group.iterator();
		while(ite.hasNext()) {
			LineGrid grid = (LineGrid)ite.next();
			grid.setForeground(color);
			grid.repaint();
		}
	}
	
	public void setFont(Font font) {
		super.setFont(font);
		FontMetrics fontMetrics = getFontMetrics(font);
		Rectangle2D rect = fontMetrics.getStringBounds("m", getGraphics());
		
		this.setPreferredSize(new Dimension((int)rect.getWidth(), (int)rect.getHeight()));
		this.setMinimumSize(new Dimension((int)rect.getWidth(), (int)rect.getHeight()));
//		this.setMaximumSize(new Dimension((int)rect.getWidth(), (int)rect.getHeight()));
	}

	public static void removeGroup(Object groupKey) {
		gridGroupMap.remove(groupKey);
	}
	
	public static void setGroupFont(Font font, Object groupKey) {
		Set group = (Set)gridGroupMap.get(groupKey);
		if(group == null) {
			return;
		}
		
		Iterator ite = group.iterator();
		while(ite.hasNext()) {
			LineGrid grid = (LineGrid)ite.next();
			grid.setFont(font);
		}
	}
}
