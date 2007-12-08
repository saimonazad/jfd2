package com.nullfish.app.jfd2.dialog;

import java.awt.Dialog;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.nullfish.app.jfd2.resource.JFDResource;
import com.nullfish.app.jfd2.ui.shortcut_tree.ShortCutDialog;
import com.nullfish.app.jfd2.util.ShortCutFile;
import com.nullfish.lib.keymap.KeyStrokeMap;
import com.nullfish.lib.ui.FocusAndSelectAllTextField;
import com.nullfish.lib.vfs.VFS;
import com.nullfish.lib.vfs.VFile;

public class TextEditorTreeExtension extends JPanel implements TextEditor {
	public TextEditor editor;

	private JComponent inputComponent;

	private Dialog owner;

	private final VFile root;

	private JButton treeButton = new JButton();

	public TextEditorTreeExtension(final TextEditor editor,
			final JComponent inputComponent, final Dialog owner,
			final VFile root) {
		this.editor = editor;
		this.inputComponent = inputComponent;
		this.owner = owner;
		this.root = root;

		setFocusable(true);
		addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
				editor.getComponent().requestFocusInWindow();
			}

			public void focusLost(FocusEvent e) {
			}
		});
		
		setLayout(new GridBagLayout());

		add(editor.getComponent(), new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0,
				GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0,
						0, 0, 0), 0, 0));
		add(treeButton, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
				GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0,
						0, 0, 0), 0, 0));

		inputComponent.getActionMap().put("shortcut", new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				showDoalog();
			}
		});

		inputComponent.getInputMap().put(
				KeyStrokeMap.getKeyStroke(KeyEvent.VK_Q, KeyEvent.ALT_MASK),
				"shortcut");

		treeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showDoalog();
			}
		});

		treeButton.setToolTipText(JFDResource.LABELS
				.getString("choose_from_quickaccess"));
	}

	public String getAnswer() {
		return editor.getAnswer();
	}

	public void setAnswer(String text) {
		editor.setAnswer(text);
	}

	public JComponent getComponent() {
		return this;
	}

//	public void requestFocus() {
//		super.requestFocus();
//		editor.getComponent().requestFocus();
//	}
//
//	public boolean requestFocusInWindow() {
//		super.requestFocusInWindow();
//		return editor.getComponent().requestFocusInWindow();
//	}
//
	private void showDoalog() {
		ShortCutDialog dialog = null;
		try {
			dialog = new ShortCutDialog(owner, root, inputComponent);
			dialog.showDialog();
			VFile selectedFile = dialog.getSelectedFile();
			if (selectedFile != null
					&& "jfdlnk".equals(selectedFile.getFileName()
							.getExtension().toLowerCase())) {
				ShortCutFile shortCutFile = new ShortCutFile(selectedFile);
				shortCutFile.load(null);
				editor.setAnswer(shortCutFile.getTarget().getSecurePath());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (dialog != null) {
				dialog.dispose();
			}
		}
	}

	public static void main(String[] args) {
		try {
			JFrame frame = new JFrame();
			FocusAndSelectAllTextField editor = new FocusAndSelectAllTextField();
			TextEditorTreeExtension ext = new TextEditorTreeExtension(editor,
					editor, null, VFS.getInstance().getFile(
							"d:\\src\\java\\jfd2\\shortcut"));
			frame.getContentPane().add(ext);
			frame.pack();
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
