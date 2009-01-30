package com.nullfish.app.jfd2.config.key_config;

import java.io.IOException;

import javax.swing.table.AbstractTableModel;

import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import com.nullfish.app.jfd2.command.CommandManager;
import com.nullfish.lib.vfs.VFile;
import com.nullfish.lib.vfs.exception.VFSException;

/**
 * TODO:書きかけ
 * @author shunji
 *
 */
public class KeyConfigTableModel extends AbstractTableModel {

//	private List commands = new ArrayList();
	
//	private Map commandDefaultKeysMap = new HashMap();
	
//	private Map commandCustomKeysMap = new HashMap();
	
	public KeyConfigTableModel(VFile baseDir) throws JDOMException, IOException, VFSException {
//		Document defaultCommandDoc = new Document();
		SAXBuilder builder = new SAXBuilder();
		builder.build(baseDir.getChild(CommandManager.COMMAND_FILE).getInputStream());
	}
	
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getRowCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return null;
	}

}
