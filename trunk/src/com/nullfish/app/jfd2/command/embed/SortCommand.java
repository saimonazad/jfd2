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
import com.nullfish.app.jfd2.dialog.ConfigulationInfo;
import com.nullfish.app.jfd2.dialog.DialogUtilities;
import com.nullfish.app.jfd2.dialog.JFDDialog;
import com.nullfish.app.jfd2.resource.JFDResource;
import com.nullfish.app.jfd2.util.SortUtility;
import com.nullfish.lib.ui.Choice;
import com.nullfish.lib.vfs.exception.VFSException;

/**
 * �\�[�g�R�}���h
 * 
 * @author shunji
 */
public class SortCommand extends Command {
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nullfish.lib.vfs.manipulation.abst.AbstractManipulation#doExecute()
	 */
	public void doExecute() throws VFSException {
		JFD jfd = getJFD();
		JFDModel model = jfd.getModel();

		JFDDialog dialog = DialogUtilities.createOkCancelDialog(jfd, "jFD2 - Sort");

		//	���b�Z�[�W
		dialog.addMessage(JFDResource.MESSAGES.getString("message_sort"));

		//	�\�[�g����
		Choice[] conditions = {
				new Choice(SortUtility.NAME, JFDResource.LABELS.getString("name"), 'n'),
				new Choice(SortUtility.EXTENSION,
						JFDResource.LABELS.getString("extension"), 'e'),
				new Choice(SortUtility.SIZE, JFDResource.LABELS.getString("size"), 's'),
				new Choice(SortUtility.DATE, JFDResource.LABELS.getString("date"), 't') };

		dialog.addChooser(SortUtility.CONDITION, JFDResource.LABELS.getString("condition"),
				conditions, 2, SortUtility.NAME, new ConfigulationInfo(jfd
						.getLocalConfigulation(), SortUtility.CONDITION), false);

		//	�����~��
		Choice[] orders = {
				new Choice(SortUtility.ASCEND, JFDResource.LABELS.getString("ascend"), 'u'),
				new Choice(SortUtility.DESCEND, JFDResource.LABELS.getString("descend"),
						'd') };

		dialog.addChooser(SortUtility.ORDER, JFDResource.LABELS.getString("order"), orders,
				1, SortUtility.ASCEND, new ConfigulationInfo(jfd.getLocalConfigulation(),
						SortUtility.ORDER), true);

		//	�`�F�b�N�{�b�N�X
		//	�����\�[�g
		dialog.addCheckBox(SortUtility.NO_ATTRIBUTE_SORT, JFDResource.LABELS
				.getString("no_attribute_sort"), 'a', false, new ConfigulationInfo(
				jfd.getLocalConfigulation(), SortUtility.NO_ATTRIBUTE_SORT), false);

		//	�f�B���N�g���D��
		dialog.addCheckBox(SortUtility.DIRECTORY_FIRST, JFDResource.LABELS
				.getString("directory_first"), 'p', false, new ConfigulationInfo(
				jfd.getLocalConfigulation(), SortUtility.DIRECTORY_FIRST), false);

		//	�t�@�C���ƃf�B���N�g���̋��
		dialog.addCheckBox(SortUtility.NO_DISTINCT_FILE_DIRECTORY, JFDResource.LABELS
				.getString("no_file_directory_distinction"), 'i', false, new ConfigulationInfo(
				jfd.getLocalConfigulation(), SortUtility.NO_DISTINCT_FILE_DIRECTORY), false);

		//	���l�\�[�g
		dialog.addCheckBox(SortUtility.SORT_NAME_NUMBER, JFDResource.LABELS
				.getString("sort_numbers"), 'm', false, new ConfigulationInfo(
				jfd.getLocalConfigulation(), SortUtility.SORT_NAME_NUMBER), false);

		//	�\�[�g���Ȃ�
		dialog.addCheckBox(SortUtility.NO_SORT, JFDResource.LABELS
				.getString("no_auto_sort"), 'r', false, new ConfigulationInfo(
				jfd.getLocalConfigulation(), SortUtility.NO_SORT), false);

		dialog.pack();
		dialog.setVisible(true);

		String answer = dialog.getButtonAnswer();
		if (answer == null || JFDDialog.CANCEL.equals(answer)) {
			return;
		}

		dialog.applyConfig();

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