/*
 * Created on 2004/08/27
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.nullfish.app.jfd2.command.progress;

import java.text.DecimalFormat;

import javax.swing.JProgressBar;

import com.nullfish.lib.vfs.Manipulation;

/**
 * ManipulataionÇÃèÛãµÇï`âÊâ¬î\Ç»JProgressBar
 * 
 * @author shunji
 */
public class ManipulationProgressBar extends JProgressBar {
	StringBuffer buffer = new StringBuffer();
	
	DecimalFormat format = new DecimalFormat("##,###,###,##0");
	
	public void apply(Manipulation manipulation) {
		if(manipulation == null) {
			setIndeterminate(true);
			return;
		}
		
		long min = manipulation.getProgressMin();
		long max = manipulation.getProgressMax();
		long progress = manipulation.getProgress();
		
		if(min == Manipulation.PROGRESS_INDETERMINED
			|| max == Manipulation.PROGRESS_INDETERMINED
			|| progress == Manipulation.PROGRESS_INDETERMINED ) {
			setIndeterminate(true);
			return;
		}
		setIndeterminate(false);
		
		setMinimum(0);
		setMaximum(1000);
		setValue((int)((progress-min) * 1000 / (max-min)));
				
		buffer.setLength(0);

		buffer.append(format.format(progress));
		buffer.append(" / ");
		buffer.append(format.format(max));
		buffer.append(" ");
		if((max / 100) != 0) {
			buffer.append('(');
			buffer.append(progress / (max / 100));
			buffer.append("%");
			buffer.append(')');
		} else {
			buffer.append("(0%)");
		}
		
		setString(buffer.toString());
	}
	
	public boolean isStringPainted() {
		return true;
	}
}
