/*
 * Created on 2004/05/31
 *
 */
package com.nullfish.app.jfd2.command.embed;


import com.nullfish.app.jfd2.JFD;
import com.nullfish.app.jfd2.JFDModel;
import com.nullfish.app.jfd2.command.Command;
import com.nullfish.app.jfd2.comparator.FileComparator;
import com.nullfish.app.jfd2.comparator.JFDComparator;
import com.nullfish.app.jfd2.resource.JFDResource;
import com.nullfish.app.jfd2.util.SortUtility;
import com.nullfish.lib.vfs.exception.VFSException;

/**
 * ソート条件変更コマンド
 * 
 * @author shunji
 */
public class ChangeSortKeyCommand extends Command {
	public static final String SORT_KEY = "sort_key";
	
	public static final String LABEL = "label";
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nullfish.lib.vfs.manipulation.abst.AbstractManipulation#doExecute()
	 */
	public void doExecute() throws VFSException {
		JFD jfd = getJFD();
		JFDModel model = jfd.getModel();

		String sortKey = (String)getParameter(SORT_KEY);
		if(sortKey.equals(jfd.getLocalConfigulation().getParam(SortUtility.CONDITION, SortUtility.NAME))) {
			String order = (String)jfd.getLocalConfigulation().getParam(SortUtility.ORDER, SortUtility.ASCEND);
			if(SortUtility.ASCEND.equals(order)) {
				jfd.getLocalConfigulation().setParam(SortUtility.ORDER, SortUtility.DESCEND);
			} else {
				jfd.getLocalConfigulation().setParam(SortUtility.ORDER, SortUtility.ASCEND);
			}
		}
		
		jfd.getLocalConfigulation().setParam(SortUtility.CONDITION, (String)getParameter(SORT_KEY));
		FileComparator[] comparators = SortUtility.createComparators(jfd);

		model.setComparator(new JFDComparator(comparators));
		model.setFiles(model.getCurrentDirectory(), model.getFiles(), model
				.getSelectedFile());
		
		boolean ascend = SortUtility.ASCEND.equals(jfd.getLocalConfigulation().getParam(SortUtility.ORDER, SortUtility.ASCEND));
		String message = 
			JFDResource.LABELS.getString((String)getParameter(LABEL)) + " "
					+ (ascend ? JFDResource.LABELS.getString("ascend") : JFDResource.LABELS.getString("descend"));
		jfd.setMessage(message, 3000);
	}

	/**
	 * 作業経過メッセージを取得する。
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
