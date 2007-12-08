/*
 * Created on 2004/08/25
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.nullfish.app.jfd2.dialog;

import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.util.List;

import com.nullfish.app.jfd2.JFD;
import com.nullfish.app.jfd2.resource.JFDResource;

/**
 * �_�C�A���O�������[�e�B���e�B�N���X�B �悭�g���^�C�v�̃_�C�A���O�͂�����g���Ɗy�B
 * 
 * @author shunji
 */
public class DialogUtilities {
	/**
	 * ���b�Z�[�W�_�C�A���O��\������B
	 * 
	 * @param jfd
	 * @param message
	 *            ���b�Z�[�W
	 * @param title 
	 */
	public static void showMessageDialog(JFD jfd, String message, String title) {
		String[] messages = { message };
		showMessageDialog(jfd, messages, "");
	}

	/**
	 * ���b�Z�[�W�_�C�A���O��\������B
	 * 
	 * @param jfd
	 * @param messages
	 * @param title
	 *            ���b�Z�[�W
	 */
	public static void showMessageDialog(JFD jfd, String[] messages, String title) {
		JFDDialog dialog = jfd.createDialog();

		dialog.addButton(JFDDialog.OK, JFDResource.LABELS.getString("ok"), 'o', true);
		dialog.setMessage(messages);
		dialog.setTitle(title);

		dialog.pack();
		dialog.setVisible(true);
		dialog.dispose();
	}

	/**
	 * ���b�Z�[�W�_�C�A���O��\������B
	 * 
	 * @param jfd
	 * @param messagesList
	 * @param title
	 *            ���b�Z�[�W
	 */
	public static void showMessageDialog(JFD jfd, List messagesList, String title) {
		String[] messages = new String[messagesList.size()];
		messages = (String[])messagesList.toArray(messages);
		
		showMessageDialog(jfd, messages, title);
	}

	/**
	 * �e�L�X�g�G���A���g�p�������b�Z�[�W�_�C�A���O��\������B
	 * 
	 * @param jfd
	 * @param message
	 *            ���b�Z�[�W
	 */
//	public static void showTextAreaMessageDialog(JFDView jfd, String message, boolean editable) {
//		showTextAreaMessageDialog(jfd, message, editable, "");
//	}
	
	/**
	 * �e�L�X�g�G���A���g�p�������b�Z�[�W�_�C�A���O��\������B
	 * 
	 * @param jfd
	 * @param message
	 *            ���b�Z�[�W
	 * @param title �^�C�g��
	 */
	public static void showTextAreaMessageDialog(JFD jfd, String message, boolean editable, String title) {
		JFDDialog dialog = jfd.createDialog();
		dialog.setTitle(title);

		dialog.addButton(JFDDialog.OK, JFDResource.LABELS.getString("ok"), 'o', true);
		dialog.addButton("copy", JFDResource.LABELS.getString("copy"), 'p', true);

		dialog.addTextArea("message", message, editable);

		dialog.pack();
		dialog.setVisible(true);
		
		if("copy".equals(dialog.getButtonAnswer())) {
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(
					new StringSelection(dialog.getTextFieldAnswer("message")),
					null);
		}
		
		dialog.dispose();
	}

	/**
	 * �uOK�v�Ɓu�L�����Z���v�̃{�^�������_�C�A���O���쐬����B
	 * �f�t�H���g��OK
	 * @param jfd
	 * @return
	 */
	public static JFDDialog createOkCancelDialog(JFD jfd) {
		return createOkCancelDialog(jfd, "");
	}
	
	/**
	 * �uOK�v�Ɓu�L�����Z���v�̃{�^�������_�C�A���O���쐬����B
	 * �f�t�H���g��OK
	 * @param jfd
	 * @return
	 */
	public static JFDDialog createOkCancelDialog(JFD jfd, String title) {
		JFDDialog dialog = jfd.createDialog();

		dialog.setTitle(title);
		
		dialog.addButton(JFDDialog.OK, JFDResource.LABELS.getString("ok"), 'o', true);
		dialog.addButton(JFDDialog.CANCEL, JFDResource.LABELS.getString("cancel"), 'c', false);
		
		return dialog;
	}

	/**
	 * �uYes�v�ƁuNo�v�̃{�^�������_�C�A���O���쐬����B
	 * @param jfd
	 * @return
	 */
	public static JFDDialog createYesNoDialog(JFD jfd, String defaultValue) {
		return createYesNoDialog(jfd, defaultValue, "");
	}
	
	/**
	 * �uYes�v�ƁuNo�v�̃{�^�������_�C�A���O���쐬����B
	 * @param jfd
	 * @return
	 */
	public static JFDDialog createYesNoDialog(JFD jfd, String defaultValue, String title) {
		JFDDialog dialog = jfd.createDialog();
		
		dialog.setTitle(title);

		dialog.addButton(JFDDialog.YES, JFDResource.LABELS.getString("ok"), 'y', JFDDialog.YES.equals(defaultValue));
		dialog.addButton(JFDDialog.NO, JFDResource.LABELS.getString("cancel"), 'n', !JFDDialog.YES.equals(defaultValue));
		
		return dialog;
	}
}