package org.sexyprogrammer.lib.vfs;

import java.net.URLClassLoader;
import java.io.File;


/**
 *    @author shunji 
 *   
 *   To change the template for this generated type comment go to 
 *   Window - Preferences - Java - Code Generation - Code and Comments 
 *  
 */
public class FileSystemClassLoader extends ClassLoader {
	public static final String ZIP_EXTENSION = ".zip";
	public static final String JAR_EXTENSION = ".jar";
	 URLClassLoader loader;
	public FileSystemClassLoader(File directory) {
	}
}
