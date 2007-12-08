/*
 * Created on 2005/01/08
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.nullfish.app.jfd2.ext_command_panel;

import java.io.IOException;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import com.nullfish.app.jfd2.Initable;
import com.nullfish.app.jfd2.JFD;
import com.nullfish.app.jfd2.command.JFDException;
import com.nullfish.lib.vfs.VFile;
import com.nullfish.lib.vfs.exception.VFSException;
import com.nullfish.lib.vfs.exception.VFSIOException;

/**
 * @author shunji
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ExternalCommandManager implements Initable {
	private static ExternalCommandManager instance = new ExternalCommandManager();
	
	private ExternalCommand[][] commands = new ExternalCommand[2][26];

	/**
	 * ê›íËÉtÉ@ÉCÉãñº
	 */
	public static final String CONFIG_FILE = "external_command.xml";
	
	public static final String EXT_COMMAND_SET ="extcommandset";
	
	private ExternalCommandManager() {
		for(int i=0; i<commands[0].length; i++) {
			commands[0][i] = new ExternalCommand();
			commands[0][i].setLabel(Character.toString((char)('A' + i)));
		}

		for(int i=0; i<commands[1].length; i++) {
			commands[1][i] = new ExternalCommand();
			commands[1][i].setLabel(Character.toString((char)('a' + i)));
		}
	}
	
	public static ExternalCommandManager getInstance() {
		return instance;
	}
	
	/* (non-Javadoc)
	 * @see com.nullfish.app.jfd2.Initable#init(com.nullfish.lib.vfs.VFile)
	 */
	public void init(VFile baseDir) throws VFSException {
		try {
			SAXBuilder builder = new SAXBuilder();
			Document doc = builder.build(baseDir.getChild(CONFIG_FILE).getInputStream());
			
			List commandSetList = doc.getRootElement().getChildren(EXT_COMMAND_SET);
			for(int i=0; i<commandSetList.size(); i++) {
				List commandsNode = ((Element)commandSetList.get(i)).getChildren(ExternalCommand.NODE_NAME);
				for(int j=0; j<commandsNode.size(); j++) {
					commands[i][j].init((Element)commandsNode.get(j));
				}
			}
		} catch (JDOMException e) {
			throw new JFDException(e.toString());
		} catch (IOException e) {
			throw new VFSIOException(e);
		}
	}
	
	public ExternalCommand getCommand(int set, int num) {
		return commands[set][num];
	}
	
	public void execute(int set, int num, JFD jfd) {
		try {
			commands[set][num].execute(jfd);
		} catch (VFSException e) {
			e.printStackTrace();
		}
	}
}
