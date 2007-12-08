/*
 * Created on 2004/05/25
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.nullfish.app.jfd2.ui.labels;

import java.text.DecimalFormat;

import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import com.nullfish.app.jfd2.JFDModel;
import com.nullfish.app.jfd2.JFDModelListener;
import com.nullfish.lib.vfs.exception.VFSException;

/**
 * ファイルの合計サイズを表示するラベル。
 * 
 * @author shunji
 */
public class FileSumLabel extends JLabel {
	private DecimalFormat format = new DecimalFormat("##,###,###,##0");
	
	JFDModel model;
	
	private CaliculationThread thread;
	
	public void setJFDModel(JFDModel model) {
		this.model = model;
		model.addJFDModelListener(new JFDModelListenerImpl());
	}
	
	private void startCaliculationg() {
		CaliculationThread t = new CaliculationThread();
		t.setPriority(Thread.NORM_PRIORITY - 1);
		setThread(t);
		t.start();
	}
	
	private class JFDModelListenerImpl implements JFDModelListener {
		public void dataChanged(JFDModel model) {
			startCaliculationg();
		}

		public void directoryChanged(JFDModel model) {
			startCaliculationg();
		}

		public void cursorMoved(JFDModel model) {
		}

		/* (non-Javadoc)
		 * @see com.nullfish.app.jfd2.JFDModelListener#markChanged(com.nullfish.app.jfd2.JFDModel)
		 */
		public void markChanged(JFDModel model) {
		}
	}
	
	private synchronized void setThread(CaliculationThread thread) {
		this.thread = thread;
	}
	
	private synchronized Thread getThread() {
		return thread;
	}
	
	private class CaliculationThread extends Thread {
		public void run() {
			try {
				int filesCount = model.getFilesCount();
				long sum = 0;
				for(int i=0; getThread() == (CaliculationThread)this && i<filesCount; i++) {
					sum += model.getFileAt(i).getLength();
				}
				
				if(getThread() != (CaliculationThread)this) {
					return;
				}
				
				final long sumToSet = sum;
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						setText(format.format(sumToSet));
					}
				});
			} catch (VFSException e) {
				setText("N/A");
			}
		}
	}
}
