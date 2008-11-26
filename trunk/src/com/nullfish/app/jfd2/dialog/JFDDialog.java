/*
 * Created on 2004/06/07
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.nullfish.app.jfd2.dialog;

import java.awt.Dialog;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.nullfish.app.jfd2.JFD;
import com.nullfish.app.jfd2.JFDComponent;
import com.nullfish.app.jfd2.JFDModel;
import com.nullfish.app.jfd2.config.DefaultConfig;
import com.nullfish.app.jfd2.dialog.components.ConfigCheckBox;
import com.nullfish.app.jfd2.dialog.components.ConfigChooserPanel;
import com.nullfish.app.jfd2.dialog.components.DialogComboBox;
import com.nullfish.app.jfd2.dialog.components.FileComboBox;
import com.nullfish.app.jfd2.dialog.components.FileCompletionComboBoxTextField;
import com.nullfish.app.jfd2.dialog.components.FileCompletionTextField;
import com.nullfish.app.jfd2.dialog.focus.JFDDialogFocusTraversalPolicy;
import com.nullfish.app.jfd2.ui.container2.ContainerPosition;
import com.nullfish.app.jfd2.ui.container2.JFDOwner;
import com.nullfish.lib.keymap.KeyStrokeMap;
import com.nullfish.lib.ui.Choice;
import com.nullfish.lib.ui.FocusAndSelectAllTextField;
import com.nullfish.lib.ui.FocusAndSelectExceptExtensionTextField;
import com.nullfish.lib.ui.ThreadSafeUtilities;
import com.nullfish.lib.ui.combobox.ComboBoxTextField;
import com.nullfish.lib.ui.document.RestrictedDocument;
import com.nullfish.lib.ui.document.Restriction;
import com.nullfish.lib.vfs.VFS;
import com.nullfish.lib.vfs.VFile;
import com.nullfish.lib.vfs.exception.VFSException;

/**
 * @author shunji
 * 
 * To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Generation - Code and Comments
 */
public class JFDDialog extends JDialog {
	private JFD jfd;

	private JPanel basePanel = new JPanel();

	private JPanel messagePanel = new JPanel();

	private MainContainerPanel mainPanel = new MainContainerPanel();

	private ChoosersContainerPanel chooserPanel = new ChoosersContainerPanel();

	private CheckBoxPanel checkBoxPanel = new CheckBoxPanel();

	private ButtonsPanel buttonsPanel = new ButtonsPanel();

	/**
	 * �_�C�A���O�̃N���[�Y�A�N�V����
	 */
	private static final String CLOSE_ACTION = "close";

	/**
	 * OK�{�^��
	 */
	public static final String OK = "ok";

	/**
	 * �L�����Z���{�^��
	 */
	public static final String CANCEL = "cancel";

	/**
	 * YES�{�^��
	 */
	public static final String YES = "yes";

	/**
	 * NO�{�^��
	 */
	public static final String NO = "no";

	/**
	 * �f�t�H���g�̃e�L�X�g�t�B�[���h������
	 */
	public static final int DEFAULT_TEXT_WIDTH = 40;

	/**
	 * �R���X�g���N�^
	 * 
	 * @param owner
	 *            �I�[�i�[�t���[��
	 * @param modal
	 *            ���[�_��
	 * @param jfd
	 *            JFD2
	 */
	public JFDDialog(Frame owner, boolean modal, JFD jfd) {
		super(owner, modal);
		this.jfd = jfd;
		initGUI();
	}

	/**
	 * �R���X�g���N�^
	 * 
	 * @param owner
	 *            �I�[�i�[�t���[��
	 * @param modal
	 *            ���[�_��
	 * @param jfd
	 *            JFD2
	 */
	public JFDDialog(Dialog owner, boolean modal, JFD jfd) {
		super(owner, modal);
		this.jfd = jfd;
		initGUI();
	}

	private void initGUI() {
		messagePanel.setVisible(false);
		buttonsPanel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});

		this.setContentPane(basePanel);
		basePanel.setLayout(new GridBagLayout());
		basePanel.add(messagePanel, new GridBagConstraints(0, 0, 1, 1, 1, 0,
				GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(2,
						5, 2, 5), 0, 0));
		basePanel.add(mainPanel, new GridBagConstraints(0, 1, 1, 1, 1, 0,
				GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(2,
						5, 2, 5), 0, 0));
		basePanel.add(chooserPanel, new GridBagConstraints(0, 2, 1, 1, 1, 0,
				GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(2,
						5, 2, 5), 0, 0));
		basePanel.add(checkBoxPanel, new GridBagConstraints(0, 3, 1, 1, 1, 0,
				GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(2,
						5, 2, 5), 0, 0));
		basePanel.add(buttonsPanel, new GridBagConstraints(0, 5, 1, 1, 1, 0,
				GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(2,
						5, 2, 5), 0, 0));

		JFDDialogFocusTraversalPolicy policy = new JFDDialogFocusTraversalPolicy(
				mainPanel);
		policy.addPanel(mainPanel, buttonsPanel, chooserPanel);
		policy.addPanel(chooserPanel, mainPanel, buttonsPanel);
		policy.addPanel(checkBoxPanel, chooserPanel, buttonsPanel);
		policy.addPanel(buttonsPanel, chooserPanel, mainPanel);

		this.setFocusTraversalPolicy(policy);

		messagePanel.setLayout(new GridBagLayout());
		messagePanel.setBorder(BorderFactory.createLoweredBevelBorder());

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				buttonsPanel.canceled();
			}
			
			public void windowOpened(WindowEvent e) {
				focusDefaultComponent();
			}
		});
	}

	public void applyConfig() {
		checkBoxPanel.applyConfig();
		chooserPanel.applyConfig();
	}

	/**
	 * ���b�Z�[�W���Z�b�g����B
	 * 
	 * @param messages
	 */
	public void setMessage(String[] messages) {
		for (int i = 0; i < messages.length; i++) {
			addMessage(messages[i]);
		}
	}

	/**
	 * ���b�Z�[�W��ǉ�����B
	 * 
	 * @param message
	 */
	public void addMessage(String message) {
		messagePanel.setVisible(true);
		int messagesCount = messagePanel.getComponentCount();
		JLabel label = new JLabel(message);
		messagePanel.add(label, new GridBagConstraints(0, messagesCount, 1, 1,
				1, 0, GridBagConstraints.WEST, GridBagConstraints.BOTH,
				new Insets(0, 2, 0, 2), 0, 0));
	}

	/**
	 * �{�^����ǉ�����B
	 * 
	 * @param name
	 *            �{�^������
	 * @param label
	 *            ���x��������
	 * @param mnemonic
	 *            �j�[���j�b�N�L�[
	 * @param selected
	 *            true�Ȃ�
	 */
	public void addButton(String name, String label, char mnemonic,
			boolean defaultButton) {
		buttonsPanel.addButton(name, label, mnemonic, defaultButton);
	}

	/**
	 * �{�^���̑I����Ԃ��B
	 * 
	 * @return
	 */
	public String getButtonAnswer() {
		return buttonsPanel.getAnswer();
	}

	/**
	 * �`�F�b�N�{�b�N�X��ǉ�����B
	 * 
	 * @param name
	 * @param label
	 * @param mnemonic
	 * @param defaultValue
	 */
	public void addCheckBox(String name, String label, char mnemonic,
			boolean defaultValue, ConfigulationInfo config,
			boolean closeOnDecision) {
		ConfigCheckBox check = new ConfigCheckBox(label, defaultValue);
		check.setConfigulationInfo(config);
		check.setMnemonic(mnemonic);
		if (closeOnDecision) {
			check.getActionMap().put(CLOSE_ACTION, new CloseAction());
			check.getInputMap(JComponent.WHEN_FOCUSED).put(
					KeyStrokeMap.getKeyStroke(KeyEvent.VK_ENTER, 0), CLOSE_ACTION);
		}

		checkBoxPanel.addCheckBox(name, check);
	}

	/**
	 * �`�F�b�N�{�b�N�X���`�F�b�N����Ă邩���肷��B
	 * 
	 * @param name
	 *            �`�F�b�N�{�b�N�X����
	 * @return �`�F�b�N����Ă�Ȃ�true��Ԃ��B
	 */
	public boolean isChecked(String name) {
		return checkBoxPanel.isChecked(name);
	}

	/**
	 * �I���p�l����ǉ�����B
	 * 
	 * @param name
	 * @param title
	 * @param choice
	 * @param cols
	 * @param defaultChoice
	 * @param config
	 */
	public void addChooser(String name, String title, Choice[] choice,
			int cols, String defaultChoice, ConfigulationInfo config,
			boolean closeOnDecision) {
		ConfigChooserPanel chooser = new ConfigChooserPanel(title, choice,
				cols, defaultChoice);
		chooser.setConfigulationInfo(config);

		if (closeOnDecision) {
			chooser.getActionMap().put(CLOSE_ACTION, new CloseAction());
			chooser.getInputMap(JComponent.WHEN_FOCUSED).put(
					KeyStrokeMap.getKeyStroke(KeyEvent.VK_ENTER, 0), CLOSE_ACTION);
		}

		chooserPanel.addChooser(name, chooser);
	}

	/**
	 * �I���p�l���̉񓚂�Ԃ��B
	 * 
	 * @param name
	 * @return
	 */
	public String getChooserAnswer(String name) {
		return chooserPanel.getAnswer(name);
	}

	public void addLabel(String text) {
		mainPanel.addLabel(text);
	}

	public void addTextField(String name, String defaultText,
			boolean closeOnDecision) {
		addTextField(name, defaultText, closeOnDecision, null);
	}
	
	public void addTextField(String name, String defaultText,
			boolean closeOnDecision, String title) {
		addTextField(name, defaultText,
				closeOnDecision, title, null);
	}
	
	public void addTextField(String name, String defaultText,
			boolean closeOnDecision, String title, Restriction restriction) {
		FocusAndSelectAllTextField editor = new FocusAndSelectAllTextField();
		
		if(restriction != null) {
			RestrictedDocument doc = new RestrictedDocument();
			doc.addRestriction(restriction);
			editor.setDocument(doc);
		}
		
		editor.setColumns(DEFAULT_TEXT_WIDTH);
		editor.setText(defaultText);

		if (closeOnDecision) {
			editor.getActionMap().put(CLOSE_ACTION, new CloseAction());
			editor.getInputMap(JComponent.WHEN_FOCUSED).put(
					KeyStrokeMap.getKeyStroke(KeyEvent.VK_ENTER, 0), CLOSE_ACTION);
		}

		mainPanel.add(name, (TextEditor) editor, title);
	}

	public void addRenameTextField(String name, String defaultText,
			boolean closeOnDecision, String title) {
		FocusAndSelectExceptExtensionTextField editor = new FocusAndSelectExceptExtensionTextField();
		
		editor.setColumns(DEFAULT_TEXT_WIDTH);
		editor.setText(defaultText);

		if (closeOnDecision) {
			editor.getActionMap().put(CLOSE_ACTION, new CloseAction());
			editor.getInputMap(JComponent.WHEN_FOCUSED).put(
					KeyStrokeMap.getKeyStroke(KeyEvent.VK_ENTER, 0), CLOSE_ACTION);
		}

		mainPanel.add(name, (TextEditor) editor, title);
	}

	public void addDateTextField(String name, Date date,
			boolean closeOnDecision, String title) {
/*
		FocusAndSelectExceptExtensionTextField editor = new FocusAndSelectExceptExtensionTextField();
		
		editor.setColumns(DEFAULT_TEXT_WIDTH);
		editor.setText(date);

		if (closeOnDecision) {
			editor.getActionMap().put(CLOSE_ACTION, new CloseAction());
			editor.getInputMap(JComponent.WHEN_FOCUSED).put(
					KeyStrokeMap.getKeyStroke(KeyEvent.VK_ENTER, 0), CLOSE_ACTION);
		}

		mainPanel.add(name, (TextEditor) editor, title);
*/
	}

	public void addSpritFileNameField(String name, String defaultText,
			boolean closeOnDecision, String title) {
		ExtensionSpritFileNameEditor editor = new ExtensionSpritFileNameEditor();
		
		editor.setColumns(DEFAULT_TEXT_WIDTH);
		editor.setAnswer(defaultText);

		if (closeOnDecision) {
			editor.getActionMap().put(CLOSE_ACTION, new CloseAction());
			editor.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(
					KeyStrokeMap.getKeyStroke(KeyEvent.VK_ENTER, 0), CLOSE_ACTION);
		}

		mainPanel.add(name, (TextEditor) editor, title);
	}

	public void addTextArea(String name, String defaultText, boolean editable) {
		addTextArea(name, defaultText, editable, null);
	}
	
	public void addTextArea(String name, String defaultText, boolean editable, String title) {
		final JTextArea textArea = new JTextArea(6, 30);
		final JScrollPane scroll = new JScrollPane(textArea);
		scroll
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		TextEditor editor = new TextEditor() {

			public String getAnswer() {
				return textArea.getText();
			}

			public JComponent getComponent() {
				return scroll;
			}

			public void setAnswer(String text) {
				textArea.setText(text);
			}
		};
		textArea.setText(defaultText);
		textArea.setEditable(editable);
		textArea.setColumns(DEFAULT_TEXT_WIDTH);

		mainPanel.add(name, (TextEditor) editor, title);
	}

	public void addPasswordField(String name, String defaultText,
			boolean closeOnDecision) {
		addPasswordField(name, defaultText, closeOnDecision, null);
	}
	
	public void addPasswordField(String name, String defaultText,
			boolean closeOnDecision, String title) {
		JFDPasswordField editor = new JFDPasswordField();
		editor.setText(defaultText);
		editor.setColumns(DEFAULT_TEXT_WIDTH);

		if (closeOnDecision) {
			editor.getActionMap().put(CLOSE_ACTION, new CloseAction());
			editor.getInputMap(JComponent.WHEN_FOCUSED).put(
					KeyStrokeMap.getKeyStroke(KeyEvent.VK_ENTER, 0), CLOSE_ACTION);
		}

		mainPanel.add(name, (TextEditor) editor, title);
	}

	/**
	 * �t�@�C���p�X���̓e�L�X�g�t�B�[���h��ǉ�����B
	 * 
	 * @param name
	 * @param file
	 *            �f�t�H���g�̃t�@�C��
	 */
	public void addFileTextField(String name, VFile file,
			boolean closeOnDecision) {
		addFileTextField(name, file, closeOnDecision, null);
	}
	
	/**
	 * �t�@�C���p�X���̓e�L�X�g�t�B�[���h��ǉ�����B
	 * 
	 * @param name
	 * @param file
	 *            �f�t�H���g�̃t�@�C��
	 */
	public void addFileTextField(String name, VFile file,
			boolean closeOnDecision, String title) {
		addFileTextField(name, file, FileCompletionTextField.MODE_DIRECTORY,
				closeOnDecision, title);
	}

	/**
	 * �t�@�C���p�X���̓e�L�X�g�t�B�[���h��ǉ�����B
	 * 
	 * @param name
	 * @param file
	 *            �f�t�H���g�̃t�@�C��
	 * @param mode
	 *            �⊮���[�h
	 */
	public void addFileTextField(String name, VFile file, int mode,
			boolean closeOnDecision, String title) {
		addFileTextField(name, file, mode, closeOnDecision, getShortcutRoot(), title);
	}

	/**
	 * �t�@�C���p�X���̓e�L�X�g�t�B�[���h��ǉ�����B
	 * 
	 * @param name
	 * @param file
	 *            �f�t�H���g�̃t�@�C��
	 * @param mode
	 *            �⊮���[�h
	 */
	public void addFileTextField(String name, VFile file, int mode,
			boolean closeOnDecision, VFile shortCutRoot, String title) {
		FocusAndSelectAllTextField editor = new FileCompletionTextField(jfd,
				mode);
		editor.setText(file.getAbsolutePath());
		editor.setColumns(DEFAULT_TEXT_WIDTH);

		if (closeOnDecision) {
			editor.getActionMap().put(CLOSE_ACTION, new CloseAction());
			editor.getInputMap(JComponent.WHEN_FOCUSED).put(
					KeyStrokeMap.getKeyStroke(KeyEvent.VK_ENTER, 0), CLOSE_ACTION);
		}

		if (shortCutRoot != null) {
			TextEditorTreeExtension extension = new TextEditorTreeExtension(
					editor, editor, this, shortCutRoot, jfd);
			mainPanel.add(name, (TextEditor) extension, title);
		} else {
			mainPanel.add(name, (TextEditor) editor, title);
		}
	}

	public String getTextFieldAnswer(String name) {
		return mainPanel.getAnswer(name);
	}

	public void addComboBox(String name, List textsList, String selected,
			boolean editable, boolean closeOnDecision) {
		addComboBox(name, textsList, selected,
				editable, closeOnDecision, null);
	}
	
	/**
	 * �R���{�{�b�N�X��ǉ�����B
	 * 
	 * @param name
	 *            ����
	 * @param textsList
	 *            �I�����̃��X�g
	 * @param selected
	 *            �f�t�H���g�I�𕶎���
	 * @param editable
	 *            �ҏW��
	 * @param closeOnDecision
	 *            true�Ȃ猈��i�G���^�[�L�[�����j���Ƀ_�C�A���O�����B
	 */
	public void addComboBox(String name, List textsList, String selected,
			boolean editable, boolean closeOnDecision, String title) {
		String[] texts = new String[textsList.size()];
		texts = (String[]) textsList.toArray(texts);

		addComboBox(name, texts, selected, editable, closeOnDecision, title);
	}

	public void addComboBox(String name, String[] texts, String selected,
			boolean editable, boolean closeOnDecision) {
		addComboBox(name, texts, selected,
				editable, closeOnDecision, null);
	}
	
	public void addComboBox(String name, String[] texts, String selected,
			boolean editable, boolean closeOnDecision, String title) {
		if (!DefaultConfig.getDefaultConfig().isUseCustomComboBox()) {
			DialogComboBox combo = new DialogComboBox(this);
			for (int i = 0; i < texts.length; i++) {
				combo.addItem(texts[i]);
			}
			combo.setClosesOnDecision(closeOnDecision);
			combo.setEditable(editable);
			if (selected != null) {
				combo.setSelectedItem(selected);
			}

			mainPanel.add(name, (TextEditor) combo, title);
		} else {
			ComboBoxTextField editor = new ComboBoxTextField();
			editor.setEditable(editable);
			for (int i = 0; i < texts.length; i++) {
				editor.addItem(texts[i]);
			}
			if (selected != null) {
				editor.setText(selected);
			} else if(texts.length > 0){
				editor.setText(texts[0]);
			}
			editor.setColumns(DEFAULT_TEXT_WIDTH);

			if (closeOnDecision) {
				editor.getActionMap().put(CLOSE_ACTION, new CloseAction());
				editor.getInputMap(JComponent.WHEN_FOCUSED).put(
						KeyStrokeMap.getKeyStroke(KeyEvent.VK_ENTER, 0),
						CLOSE_ACTION);
			}
			mainPanel.add(name, editor, title);
		}
	}

	public void addFileComboBox(String name, List filesList, boolean editable,
			boolean closeOnDecision, JFD jfd) {
		addFileComboBox(name, filesList, editable,
				closeOnDecision, jfd, null);
	}
	
	public void addFileComboBox(String name, List filesList, boolean editable,
			boolean closeOnDecision, JFD jfd, String title) {
		VFile[] files = new VFile[filesList.size()];
		files = (VFile[]) filesList.toArray(files);

		addFileComboBox(name, files, editable, closeOnDecision, jfd, title);
	}


	public void addFileComboBox(String name, VFile[] files, boolean editable,
			boolean closeOnDecision, JFD jfd) {
		addFileComboBox(name, files, editable,
				closeOnDecision, jfd, (String)null);
	}
	
	public void addFileComboBox(String name, VFile[] files, boolean editable,
			boolean closeOnDecision, JFD jfd, String title) {
		VFile shortCutDir = null;
		try {
			shortCutDir = VFS.getInstance(jfd).getFile(
					(String) jfd.getCommonConfigulation().getParam(
							"shortcut_dir", null));
		} catch (VFSException e) {
		}
		addFileComboBox(name, files, editable, closeOnDecision, jfd,
				shortCutDir, title);
	}

	public void addFileComboBox(String name, VFile[] files, boolean editable,
			boolean closeOnDecision, JFD jfd, final VFile shortCutRoot) {
		addFileComboBox(name, files, editable,
				closeOnDecision, jfd, shortCutRoot, null);
	}
	
	public void addFileComboBoxWithHistory(String name, boolean selfFirst, 
			boolean editable, boolean closeOnDecision, JFD jfd, String title) {
		JFDModel model = jfd.getModel();
		List history = model.getNoOverwrapHistory().toList();
		JFDOwner owner = jfd.getJFDOwner();
		if(owner != null) {
			ContainerPosition oponentPos = owner.getComponentPosition(jfd).getOpenent();
			JFDComponent oponent = owner.getComponent(oponentPos);
			if(oponent instanceof JFD) {
				history.remove(((JFD)oponent).getModel().getCurrentDirectory() );
				history.add(0, ((JFD)oponent).getModel().getCurrentDirectory() );
			}
			
			if(selfFirst) {
				history.remove(model.getCurrentDirectory() );
				history.add(0, model.getCurrentDirectory() );
			}
		}
		
		VFile[] files = new VFile[history.size()];
		files = (VFile[])history.toArray(files);
		addFileComboBox(name, files, editable, closeOnDecision, jfd, getShortcutRoot(), title);
	}
	
	public void addFileComboBox(String name, VFile[] files, boolean editable,
			boolean closeOnDecision, JFD jfd, final VFile shortCutRoot, String title) {
		if (!DefaultConfig.getDefaultConfig().isUseCustomComboBox()) {
			final FileComboBox combo = new FileComboBox(this, jfd);
			for (int i = 0; i < files.length; i++) {
				combo.addItem(files[i].getSecurePath());
			}
			combo.setClosesOnDecision(closeOnDecision);
			combo.setEditable(editable);

			if (shortCutRoot != null) {
				TextEditorTreeExtension extension = new TextEditorTreeExtension(
						combo, (JComponent) combo.getEditor()
								.getEditorComponent(), this, shortCutRoot, jfd);
				mainPanel.add(name, (TextEditor) extension, title);
			} else {
				mainPanel.add(name, (TextEditor) combo, title);
			}
		} else {
			FileCompletionComboBoxTextField editor = new FileCompletionComboBoxTextField(
					jfd, FileCompletionComboBoxTextField.MODE_DIRECTORY);
			editor.setEditable(editable);

			for (int i = 0; i < files.length; i++) {
				editor.addItem(files[i].getSecurePath());
			}
			if(files.length > 0) {
				editor.setText(files[0].getSecurePath());
			}
			editor.setColumns(DEFAULT_TEXT_WIDTH);

			if (closeOnDecision) {
				editor.getActionMap().put(CLOSE_ACTION, new CloseAction());
				editor.getInputMap(JComponent.WHEN_FOCUSED).put(
						KeyStrokeMap.getKeyStroke(KeyEvent.VK_ENTER, 0),
						CLOSE_ACTION);
			}

			if (shortCutRoot != null) {
				TextEditorTreeExtension extension = new TextEditorTreeExtension(
						editor, editor, this, shortCutRoot, jfd);
				mainPanel.add(name, extension, title);
			} else {
				mainPanel.add(name, editor, title);
			}
		}
	}

	/**
	 * �_�C�A���O�̉�����ݒ肷��B �X���b�h�Z�[�t�B
	 */
	public void setVisible(final boolean bool) {
		Runnable runnable = new Runnable() {
			public void run() {
				JFDDialog.this.setLocationRelativeTo(getOwner());
				JFDDialog.super.setVisible(bool);
				if(bool) {
					dispose();
				}
			}
		};

		ThreadSafeUtilities.executeRunnable(runnable);
	}
	
	private void  focusDefaultComponent() {
		if(mainPanel.focusFirstComponent()) {
			return;
		}
		if(chooserPanel.focusFirstComponent()) {
			return;
		}
		if(checkBoxPanel.focusFirstComponent()) {
			return;
		}
		if(buttonsPanel.focusFirstComponent()) {
			return;
		}
	}

	/**
	 * �_�C�A���O�̃N���[�Y�A�N�V����
	 * 
	 * @author shunji
	 */
	class CloseAction extends AbstractAction {
		/*
		 * (non-Javadoc)
		 * 
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		public void actionPerformed(ActionEvent e) {
			setVisible(false);
		}
	}
	
	private VFile getShortcutRoot() {
		try {
		return VFS.getInstance(jfd).getFile(
				(String) jfd.getCommonConfigulation().getParam(
						"shortcut_dir", null));
		} catch (VFSException e) {
			return null;
		}
	}
}