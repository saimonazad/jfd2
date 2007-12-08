package com.nullfish.app.jfd2.ext_command.window;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.Writer;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.BadLocationException;

import com.nullfish.app.jfd2.ext_command.ProcessReader;
import com.nullfish.app.jfd2.ext_command.StreamReaderThread;
import com.nullfish.app.jfd2.resource.JFDResource;
import com.nullfish.lib.keymap.KeyStrokeMap;

public class WindowProcessReader extends JFrame implements ProcessReader {
    public static final int READER_ID = 3;

	private JTextArea textArea = new JTextArea();
	
	private JScrollPane scroll = new JScrollPane(textArea);
	
	private JButton closeButton = new JButton(JFDResource.LABELS.getString("exit") + "(C)");
	private JButton clearButton = new JButton(JFDResource.LABELS.getString("clear") + "(L)");
	
    private boolean showsAutomatic;

    private static final WindowProcessReader instance = new WindowProcessReader();
	
	public WindowProcessReader() {
		initGui();
		pack();
		setLocationRelativeTo(null);
	}
	
	public static WindowProcessReader getInstance() {
		return instance;
	}
	
	private void initGui() {
		setTitle("jFD2 - Console");
		scroll.setPreferredSize(new Dimension(640,400));
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		textArea.setLineWrap(true);
		
		JPanel basePanel = new JPanel();
		this.setContentPane(basePanel);
		basePanel.setLayout(new BorderLayout());
		
		basePanel.add(scroll, BorderLayout.CENTER);
		
		basePanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStrokeMap.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "close");
		basePanel.getActionMap().put("close", new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		
		JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		buttonsPanel.add(closeButton);
		buttonsPanel.add(clearButton);
		
		basePanel.add(buttonsPanel, BorderLayout.SOUTH);
		
		closeButton.setMnemonic('c');
		closeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		
		clearButton.setMnemonic('l');
		clearButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText("");
			}
		});
	}
	
	public void addProcess(Process process) {
		StreamReaderThread outReaderThread = new StreamReaderThread(process
				.getInputStream(), new TextAreaWriter());
		outReaderThread.start();
		StreamReaderThread errReaderThread = new StreamReaderThread(process
				.getErrorStream(), new TextAreaWriter());
		errReaderThread.start();
	}

	public int getReaderID() {
		return READER_ID;
	}

	public void showConsole(boolean visible) {
		setVisible(visible);
	}

	private class TextAreaWriter extends Writer {
		
		
		public TextAreaWriter() {
		}

		public void write(char[] cbuf, int off, int len) throws IOException {
			if(showsAutomatic && !isVisible()) {
				showConsole(true);
			}
			
			String str = new String(cbuf, off, len);
			try {
				textArea.getDocument().insertString(textArea.getDocument().getLength(), str, null);
				textArea.setCaretPosition(textArea.getDocument().getLength());
			} catch (BadLocationException e) {
				e.printStackTrace();
			}
		}

		public void flush() throws IOException {
		}

		public void close() throws IOException {
		}
	}

	public void setShowsAutomatic(boolean shows) {
		this.showsAutomatic = shows;
	}
}
