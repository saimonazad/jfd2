package org.sexyprogrammer.lib.vfs.manipulation;

import org.sexyprogrammer.lib.vfs.Manipulation;
import java.util.Date;


/**
 *     
 *   @author shunji 
 *   
 *   To change the template for this generated type comment go to 
 *   Window - Preferences - Java - Code Generation - Code and Comments 
 *  
 */
public interface SetTimestampManipulation extends Manipulation {
	/**
	 *    ���얼 
	 *  
	 */
	public static final String NAME = "set timestamp";
	/**
	 *    ���t���Z�b�g����B 
	 *   @param date 
	 *  
	 */
	public void setDate(Date date);
}
