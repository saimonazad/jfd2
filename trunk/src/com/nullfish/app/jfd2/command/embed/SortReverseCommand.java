/*
 * Created on 2004/05/31
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.nullfish.app.jfd2.command.embed;


import com.nullfish.app.jfd2.JFD;
import com.nullfish.app.jfd2.JFDModel;
import com.nullfish.app.jfd2.command.Command;
import com.nullfish.app.jfd2.comparator.FileComparator;
import com.nullfish.app.jfd2.comparator.JFDComparator;
import com.nullfish.app.jfd2.config.Configulation;
import com.nullfish.app.jfd2.resource.JFDResource;
import com.nullfish.app.jfd2.util.SortUtility;
import com.nullfish.lib.vfs.exception.VFSException;

/**
 * �\�[�g�t�]�R�}���h
 * 
 * @author shunji
 */
public class SortReverseCommand extends Command {
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nullfish.lib.vfs.manipulation.abst.AbstractManipulation#doExecute()
	 */
	public void doExecute() throws VFSException {
		JFD jfd = getJFD();
		JFDModel model = jfd.getModel();

		Configulation config = jfd.getLocalConfigulation();
		String order = (String)config.getParam(SortUtility.ORDER, SortUtility.ASCEND);
		if(SortUtility.ASCEND.equals(order)) {
			order = SortUtility.DESCEND;
		} else {
			order = SortUtility.ASCEND;
		}
		config.setParam(SortUtility.ORDER, order);
		
		FileComparator[] comparators = SortUtility.createComparators(jfd);

		model.setComparator(new JFDComparator(comparators));
		model.setFiles(model.getCurrentDirectory(), model.getFiles(), model
				.getSelectedFile());
	}

	/**
	 * ��ƌo�߃��b�Z�[�W���擾����B
	 * 
	 * @return
	 */
	public String getProgressMessage() {
		return JFDResource.MESSAGES.getString("progress_sort");
	}

	/* (non-Javadoc)
	 * @see com.nullfish.app.jfd2.command.Command#closesUnusingFileSystem()
	 */
	public boolean closesUnusingFileSystem() {
		return false;
	}
}