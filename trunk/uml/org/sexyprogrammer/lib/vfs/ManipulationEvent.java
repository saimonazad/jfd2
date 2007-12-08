package org.sexyprogrammer.lib.vfs;

import java.util.EventObject;
import org.sexyprogrammer.lib.vfs.exception.VFSException;


/**
 *    @author shunji 
 *   
 *   To change the template for this generated type comment go to 
 *   Window - Preferences - Java - Code Generation - Code and Comments 
 *  
 */
public class ManipulationEvent extends EventObject {
	 VFSException exception;
	public ManipulationEvent(Manipulation source) {
	}
	/**
	 *    @return Returns the exception. 
	 *  
	 */
	public VFSException getException() {
		return null;
	}
	/**
	 *    @param exception The exception to set. 
	 *  
	 */
	public void setException(VFSException exception) {
	}
}
