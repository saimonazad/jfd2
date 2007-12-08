package com.nullfish.app.jfd2.ui.table;

import java.awt.Font;
import java.awt.FontMetrics;

import javax.swing.JLabel;

import com.nullfish.lib.vfs.VFile;

public class FileNameLabel2 extends JLabel {
	private FontMetrics fontMetrics;
	private StringBuffer buffer = new StringBuffer();
	
	public void setFont(Font font) {
		super.setFont(font);
		fontMetrics = null;
	}
	
	public void setFile(VFile file, VFile current, boolean relative) {
		if(file == null) {
			setText("");
			return;
		}
		
		if (file.equals(current.getFileSystem().getMountPoint())) {
			setText("...");
			return;
		} 

/*
		if(current.equals(file)) {
			setText(".");
			return;
		}
		
		if (file.equals(current.getParent())) {
			setText("..");
			return;
		}
*/
		
		if(!relative || current.equals(file.getParent())) {
			setText(file.getName());
			return;
		}
System.out.println("relation");
		setText(current.getRelation(file));
/*
		if(relative && !current.equals(file.getParent())) {
			setText(current.getRelation(file));
			return;
		} else {
			setText(getTrimmedFileName(file));
			return;
		}
*/
	}
	
/*
	public String getTrimmedFileName(VFile file) {
System.out.println(getWidth() + " : " + file.getName());
		String fullName = file.getName();
		if(fontMetrics == null) {
			fontMetrics = getFontMetrics(getFont());
		}
		
		int width = getWidth() - fontMetrics.stringWidth("..");
		int lineWidth = fontMetrics.stringWidth(fullName);
		if(lineWidth <= width) {
			return fullName;
		}
		
		String extension = file.getFileName().getExtension();
		int extWidth = fontMetrics.stringWidth(extension);
		extWidth += fontMetrics.stringWidth("...");
		
		String name = file.getFileName().getExceptExtension();
		int nameWidth = 0;
		buffer.setLength(0);
		
		for(int i=0; i<name.length(); i++) {
			char c = name.charAt(i);
			nameWidth += fontMetrics.charWidth(c);
			if(nameWidth + extWidth > width) {
				break;
			}
			buffer.append(c);
		}
		
		buffer.append("...");
		buffer.append(extension);
		
System.out.println(fontMetrics.stringWidth(buffer.toString()) + " : " + buffer.toString());
		return buffer.toString();
	}
*/
}
