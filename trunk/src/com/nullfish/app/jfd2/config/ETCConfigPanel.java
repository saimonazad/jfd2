package com.nullfish.app.jfd2.config;

import java.awt.BorderLayout;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.jdom.JDOMException;

import com.nullfish.app.jfd2.JFD;
import com.nullfish.app.jfd2.resource.JFDResource;
import com.nullfish.lib.tablelayout.HtmlTablePanel;
import com.nullfish.lib.vfs.VFS;
import com.nullfish.lib.vfs.VFile;
import com.nullfish.lib.vfs.exception.VFSException;

public class ETCConfigPanel extends JPanel implements ConfigPanel {
	private VFile configDir;
	public static final String ETC_LAYOUT = "classpath:///resources/option_layout_etc_tab.xml";
	//
	//	その他タブ
	private HtmlTablePanel etcPanel;
	private JLabel grepEncodeLabel = new JLabel(JFDResource.LABELS.getString("grep_encode"));
	private JTextField grepEncodeTextField = new JTextField(20);
	private JLabel grepAllEncodeLabel = new JLabel(JFDResource.LABELS.getString("grep_all_encode"));
	private ListConfigTextField grepAllEncodeTextArea = new ListConfigTextField("grep_encode_all");
	
	private JCheckBox cursorLoopsCheckBox = new JCheckBox(JFDResource.LABELS.getString("cursor_loops"));
	
	private JCheckBox afxStyleCursorCheckBox = new JCheckBox(JFDResource.LABELS.getString("afx_style_cursor"));
	
	private PathConfig keyMapPathConfig = new PathConfig("key_map", "key_map", JFileChooser.FILES_ONLY);
	
	public ETCConfigPanel() {
		super(new BorderLayout());
		try {
			initGui();
			layoutComponent();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void initGui() throws Exception {
		etcPanel = new HtmlTablePanel(VFS.getInstance().getFile(
				ETC_LAYOUT).getInputStream());
		grepAllEncodeTextArea.setRows(5);
		grepAllEncodeTextArea.setColumns(20);
		grepAllEncodeTextArea.setBorder(BorderFactory.createEtchedBorder());
		
	}

	public void layoutComponent() {
		add(etcPanel);
//		etcPanel.addComponent(grepEncodeLabel, "grep_encode_label");
//		etcPanel.addComponent(grepEncodeTextField, "grep_encode_input");
		etcPanel.addComponent(grepAllEncodeLabel, "grep_encode_all_label");
		etcPanel.addComponent(grepAllEncodeTextArea, "grep_encode_all_input");
		
		etcPanel.addComponent(cursorLoopsCheckBox, "cursor_loops_check");
		etcPanel.addComponent(afxStyleCursorCheckBox, "afx_style_cursor_check");

		etcPanel.addComponent(keyMapPathConfig, "key_map");
	}

	/**
	 * 設定を読み込む
	 * @param configDir
	 * @throws JDOMException
	 * @throws IOException
	 * @throws VFSException
	 */
	public void loadPreference(VFile configDir) throws Exception {
		this.configDir = configDir;
		Configulation commonConfig = Configulation.getInstance(configDir
				.getChild(JFD.COMMON_PARAM_FILE));

		grepEncodeTextField.setText((String)commonConfig.getParam("grep_encode", "UTF-8"));
		grepAllEncodeTextArea.setConfigulation(commonConfig);
		cursorLoopsCheckBox.setSelected(((Boolean)commonConfig.getParam("cursor_loops", Boolean.FALSE)).booleanValue());
		afxStyleCursorCheckBox.setSelected(((Boolean)commonConfig.getParam("multi_window_cursor", Boolean.FALSE)).booleanValue());

		keyMapPathConfig.setConfigulation(commonConfig);
	}

	/***
	 * 入力を設定に反映する。
	 * @throws JDOMException
	 * @throws IOException
	 * @throws VFSException
	 */
	public void apply() throws JDOMException, IOException, VFSException {
		Configulation commonConfig = Configulation.getInstance(configDir
				.getChild(JFD.COMMON_PARAM_FILE));

		commonConfig.setParam("grep_encode", grepEncodeTextField.getText());
		grepAllEncodeTextArea.apply(commonConfig);
		commonConfig.setParam("cursor_loops", Boolean.valueOf(cursorLoopsCheckBox.isSelected()));
		commonConfig.setParam("multi_window_cursor", Boolean.valueOf(afxStyleCursorCheckBox.isSelected()));
		keyMapPathConfig.apply(commonConfig);
	}
	
	public 	String getTitle() {
		return JFDResource.LABELS.getString("etc");
	}
}
