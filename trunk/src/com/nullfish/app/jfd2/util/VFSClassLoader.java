/*
 * Created on 2004/09/07
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.nullfish.app.jfd2.util;

import com.nullfish.lib.vfs.VFile;
import com.nullfish.lib.vfs.exception.VFSException;

/**
 * @author shunji
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class VFSClassLoader extends ClassLoader {
	VFile baseFile;

	public VFSClassLoader(ClassLoader parent, VFile baseFile) {
		super(parent);
		this.baseFile = baseFile;
	}

	protected Class findClass(String name) throws ClassNotFoundException {
		try {
			VFile root = baseFile.getInnerRoot();
			if(root == null) {
				throw new ClassNotFoundException(name);
			}
			
			String path = name.replace('.', '/') + ".class";
			VFile classFile = root.getRelativeFile(path);
			if(classFile.exists()) {
				throw new ClassNotFoundException(name);
			}
			
			byte[] classByte = classFile.getContent();
			return defineClass(name, classByte, 0, classByte.length);
		} catch (VFSException e) {
			throw new ClassNotFoundException(name);
		}
	}
}