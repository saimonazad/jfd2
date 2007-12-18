/*
 * Created on 2005/01/13
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.nullfish.app.jfd2.ext_command_panel;

import javax.swing.JTable;

import com.nullfish.app.jfd2.Initable;
import com.nullfish.app.jfd2.JFD;
import com.nullfish.lib.vfs.VFile;
import com.nullfish.lib.vfs.exception.VFSException;

/**
 * @author shunji
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ExternalCommandTable extends JTable implements Initable {
	private JFD jfd;
	
	private ExternalCommandTableModel model = new ExternalCommandTableModel();
	
	private ExternalCommandTableCellRenderer renderer;
	
	public ExternalCommandTable(JFD jfd) {
		this.jfd = jfd;
		setModel(model);
		renderer = new ExternalCommandTableCellRenderer(jfd);
		setDefaultRenderer(Object.class, renderer);
		setShowGrid(false);
		setOpaque(false);
	}
	
	public void increaseSetNumber() {
//		model.setSetNumber(model.getSetNumber() == 0 ? 1 : 0);
		model.setSetNumber(1);
	}
	
	public void initSetNumber() {
		model.setSetNumber(0);
	}

	public int getSetNumber() {
		return model.getSetNumber();
	}
	
	/* (non-Javadoc)
	 * @see com.nullfish.app.jfd2.Initable#init(com.nullfish.lib.vfs.VFile)
	 */
	public void init(VFile baseDir) throws VFSException {
		renderer.init(baseDir);
		setRowHeight(renderer.getPreferredSize().height);
	}
}
