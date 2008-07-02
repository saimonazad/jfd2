/*
 * Created on 2004/08/23
 * 
 * TODO To change the template for this generated file go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
package com.nullfish.app.jfd2.command.embed;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;

import com.nullfish.app.jfd2.command.Command;
import com.nullfish.app.jfd2.dialog.JFDDialog;
import com.nullfish.app.jfd2.resource.JFDResource;
import com.nullfish.lib.ui.Choice;
import com.nullfish.lib.vfs.VFile;
import com.nullfish.lib.vfs.exception.VFSException;
import com.nullfish.lib.vfs.manipulation.OverwritePolicy;

/**
 * jFD2�p�㏑���|���V�[�N���X�B �_�C�A���O��\�����ă��[�U�[�Ɍ��肳����B
 * 
 * @author shunji
 */
public class CopyOverwritePolicy implements OverwritePolicy {
	VFile newDest = null;

	boolean okForever = false;

	private String decidedAnswer;

	private Command command;

	private VFile from;

	private VFile to;

	private SimpleDateFormat dateFormat = new SimpleDateFormat(
			" yyyy-MM-dd HH:mm:ss  ");

	public static final String OK = "ok";

	public static final String CANCEL = "cancel";

	private static final String OK_ALL = "ok_all";

	private static final String OVERWRITE_POLICY = "policy";

	private static final String NEW_NAME = "new_name";

	private static final String COPY_IF_NEWER = "copy_newer";

	private static final String COPY_IF_OLDER = "copy_older";

	private static final String DO_OVERWRITE = "overwrite";

	private static final String DONT_OVERWRITE = "no_overwrite";

	/**
	 * �R���X�g���N�^
	 * 
	 * @param command
	 *            ������g�p����R�}���h�I�u�W�F�N�g
	 */
	public CopyOverwritePolicy(Command command) {
		this.command = command;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nullfish.lib.vfs.manipulation.OverwritePolicy#isDoOverwrite(com.nullfish.lib.vfs.VFile,
	 *      com.nullfish.lib.vfs.VFile)
	 */
	public int getOverwritePolicy(VFile srcFile, VFile dest) {
		JFDDialog dialog = null;
		try {
			if (okForever) {
				return decidePolicy(srcFile, dest, decidedAnswer);
			}

			boolean sameFile = srcFile.equals(dest);
			dialog = showDialog(srcFile, dest);

			String buttonAnswer = dialog.getButtonAnswer();
			if (buttonAnswer == null || CANCEL.equals(buttonAnswer)) {
				return STOP_ALL;
			}

			String chooserAnswer = dialog.getChooserAnswer(OVERWRITE_POLICY);
			if (OK_ALL.equals(buttonAnswer)) {
				okForever = true;
				decidedAnswer = chooserAnswer;
			}

			return decidePolicy(srcFile, dest, chooserAnswer);
		} finally {
			if (dialog != null) {
				dialog.dispose();
			}
		}
	}

	/**
	 * �㏑�����A�㏑�����ݒ肷��B
	 * 
	 * @param from
	 *            �㏑����
	 * @param to
	 *            �㏑����
	 */
	public void setFiles(VFile from, VFile to) {
		this.from = from;
		this.to = to;
	}

	/**
	 * �R�s�[���A�R�s�[��A���[�U�[�̓��͂���㏑���|���V�[�����肵�ĕԂ��B
	 * 
	 * @param from
	 * @param to
	 * @param policyName
	 * @return
	 */
	private int decidePolicy(VFile from, VFile to, String policyName) {
		if (NEW_NAME.equals(policyName)) {
			try {
				String newName = showNameInputDialog(to.getName());
				if (newName == null) {
					return STOP_ALL;
				}
				newDest = to.getParent().getChild(newName);
			} catch (Exception e) {
				newDest = to;
			}
			return NEW_DEST;
		}

		if (COPY_IF_NEWER.equals(policyName)) {
			try {
				return from.getTimestamp().after(to.getTimestamp()) ? OVERWRITE
						: NO_OVERWRITE;
			} catch (VFSException e) {
				return NO_OVERWRITE;
			}
		}

		if (COPY_IF_OLDER.equals(policyName)) {
			try {
				return from.getTimestamp().before(to.getTimestamp()) ? OVERWRITE
						: NO_OVERWRITE;
			} catch (VFSException e) {
				return NO_OVERWRITE;
			}
		}

		if (DO_OVERWRITE.equals(policyName)) {
			return OVERWRITE;
		}

		return NO_OVERWRITE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nullfish.lib.vfs.manipulation.OverwritePolicy#getNewDestination()
	 */
	public VFile getNewDestination() {
		return newDest;
	}

	/**
	 * �m�F�_�C�A���O��\������B
	 * 
	 * @param srcFile
	 * @param dest
	 * @return
	 */
	private JFDDialog showDialog(VFile srcFile, VFile dest) {
		boolean sameFile = srcFile.equals(dest);
		JFDDialog dialog = command.getJFD().createDialog();

		// ���b�Z�[�W
		String orgMessage = JFDResource.MESSAGES
				.getString("copy_overwrite_confirm");
		String[] params = toParams(srcFile, dest);

		orgMessage = MessageFormat.format(orgMessage, params);
		String[] messages = orgMessage.split("\n");
		dialog.setMessage(messages);

		try {
			String fromMessage = JFDResource.LABELS.getString("copy_from")
					+ dateFormat.format(from.getTimestamp()) + from.getLength()
					+ " " + JFDResource.LABELS.getString("byte");
			dialog.addMessage(fromMessage);

			String toMessage = JFDResource.LABELS.getString("copy_to")
					+ dateFormat.format(to.getTimestamp()) + to.getLength()
					+ " " + JFDResource.LABELS.getString("byte");
			dialog.addMessage(toMessage);
		} catch (Exception e) {
			// �������Ȃ�
			e.printStackTrace();
		}

		// �I��
		Choice[] choice = {
				new Choice(COPY_IF_NEWER, JFDResource.LABELS
						.getString("copy_if_newer"), 'e', !sameFile),
				new Choice(COPY_IF_OLDER, JFDResource.LABELS
						.getString("copy_if_older"), 'l', !sameFile),
				new Choice(NEW_NAME, JFDResource.LABELS
						.getString("copy_new_name"), 'r'),
				new Choice(DO_OVERWRITE, JFDResource.LABELS
						.getString("copy_overwrite"), 'o', !sameFile),
				new Choice(DONT_OVERWRITE, JFDResource.LABELS
						.getString("copy_no_overwrite"), 'n', !sameFile) };

		dialog.addChooser(OVERWRITE_POLICY, null, choice, 1,
				(sameFile ? NEW_NAME : COPY_IF_NEWER), null, true);

		// �{�^��
		dialog.addButton(OK, JFDResource.LABELS.getString("ok"), 'y', true);
		dialog.addButton(OK_ALL, JFDResource.LABELS
				.getString("copy_overwrite_all"), 'a', false);
		dialog.addButton(CANCEL, JFDResource.LABELS.getString("cancel"), 'c',
				false);

		dialog.pack();
		dialog.setVisible(true);
		return dialog;
	}

	private String[] toParams(VFile from, VFile dest) {
		String[] rtn = new String[5];
		try {
			rtn[0] = dest.getName();
		} catch (Exception e) {
			rtn[0] = "";
		}
		try {
			rtn[1] = Long.toString(from.getLength());
		} catch (Exception e) {
			rtn[1] = "";
		}
		try {
			rtn[2] = from.getTimestamp().toString();
		} catch (Exception e) {
			rtn[2] = "";
		}
		try {
			rtn[3] = Long.toString(dest.getLength());
		} catch (Exception e) {
			rtn[3] = "";
		}
		try {
			rtn[4] = dest.getTimestamp().toString();
		} catch (Exception e) {
			rtn[4] = "";
		}

		return rtn;
	}

	/**
	 * �t�@�C�������̓_�C�A���O��\������B
	 * 
	 * @param name
	 *            ���t�@�C����
	 * @return �V�����t�@�C�����B�������L�����Z�����ꂽ�ꍇ��null
	 * 
	 */
	private String showNameInputDialog(String name) {
		JFDDialog dialog = null;
		String NAME = "name";
		try {
			dialog = command.getJFD().createDialog();

			// ���b�Z�[�W
			String message = JFDResource.MESSAGES.getString("message_rename");
			dialog.addMessage(message);

			dialog.addRenameTextField(NAME, name, true, "");

			// �{�^��
			dialog.addButton(OK, JFDResource.LABELS.getString("ok"), 'o', true);
			dialog.addButton(CANCEL, JFDResource.LABELS.getString("cancel"),
					'c', false);

			dialog.pack();
			dialog.setVisible(true);

			String buttonAnswer = dialog.getButtonAnswer();
			if (buttonAnswer == null || buttonAnswer.equals(CANCEL)) {
				return null;
			}

			return dialog.getTextFieldAnswer(NAME);
		} finally {
			if (dialog != null) {
				dialog.dispose();
			}
		}
	}
}