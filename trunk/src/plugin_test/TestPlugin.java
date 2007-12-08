/*
 * Created on 2005/02/03
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package plugin_test;

import com.nullfish.app.jfd2.JFD;
import com.nullfish.app.jfd2.ui.container2.JFDOwner;
import com.nullfish.lib.plugin.AbstractPlugin;
import com.nullfish.lib.vfs.VFile;
import com.nullfish.lib.vfs.exception.VFSException;

/**
 * @author shunji
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TestPlugin extends AbstractPlugin {
	/**
	 * �V�X�e���J�n���ɌĂяo�����B
	 * 
	 * @throws VFSException
	 */
	public void systemStarted() {
		System.out.println("systemStarted");
	}
	
	/**
	 * �V�X�e���I�����ɌĂяo�����B
	 * 
	 * @throws VFSException
	 */
	public void systemExited() {
		System.out.println("systemExited");
	}
	
	/**
	 * JFD2�C���X�^���X���������ꂽ�ۂɌĂяo�����B
	 * 
	 * @param jfd
	 */
	public void jfdCreated(JFD jfd) {
		System.out.println("jfdCreated : " + jfd);
	}
	
	/**
	 * JFD2�C���X�^���X�����������ꂽ�ۂɌĂяo�����B
	 * 
	 * @param jfd
	 */
	public void jfdInited(JFD jfd, VFile baseDir) {
		System.out.println("jfdInited : " + jfd);
	}
	
	/**
	 * JFD2�C���X�^���X���p�����ꂽ�ۂɌĂяo�����B
	 * 
	 * @param jfd
	 */
	public void jfdDisposed(JFD jfd) {
		System.out.println("jfdDisposed : " + jfd);
	}
	
	/**
	 * JFD�I�[�i�[�C���X�^���X���������ꂽ�ۂɌĂяo�����B
	 * 
	 * @param jfd
	 */
	public void jfdOwnerCreated(JFDOwner owner) {
		System.out.println("jfdOwnerCreated : " + owner);
	}
	
	/**
	 * JFD2�I�[�i�[�C���X�^���X���p�����ꂽ�ۂɌĂяo�����B
	 * 
	 * @param jfd
	 */
	public void jfdOwnerDisposed(JFDOwner owner) {
		System.out.println("jfdOwnerDisposed : " + owner);
	}
	
	
}
