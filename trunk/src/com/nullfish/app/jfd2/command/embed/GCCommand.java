/*
 * Created on 2004/05/31
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.nullfish.app.jfd2.command.embed;

import com.nullfish.app.jfd2.command.Command;
import com.nullfish.lib.vfs.exception.VFSException;

/**
 * ファイルマークコマンド
 * 
 * @author shunji
 */
public class GCCommand extends Command {
	/* (non-Javadoc)
	 * @see com.nullfish.lib.vfs.manipulation.abst.AbstractManipulation#doExecute()
	 */
	public void doExecute() throws VFSException {
        System.out.println("Before");
        showMemory();

		Runtime.getRuntime().gc();
		Runtime.getRuntime().runFinalization();
		Runtime.getRuntime().gc();
		
        System.out.println("After");
        showMemory();
    }

    public void showMemory() {
        long total = Runtime.getRuntime().totalMemory();
        System.out.println(
            "Total Memory : " + total + "\n" +
            "Spent Memory : " + (total - Runtime.getRuntime().freeMemory()) + "\n"
        );
    }

	/* (non-Javadoc)
	 * @see com.nullfish.app.jfd2.command.Command#closesUnusingFileSystem()
	 */
	public boolean closesUnusingFileSystem() {
		return false;
	}
}
