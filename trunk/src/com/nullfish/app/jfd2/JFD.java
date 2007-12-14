/*
 * Created on 2004/05/31
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.nullfish.app.jfd2;

import java.io.File;

import org.monazilla.migemo.Migemo;

import com.nullfish.app.jfd2.aliase.AliaseManager;
import com.nullfish.app.jfd2.command.Command;
import com.nullfish.app.jfd2.command.CommandManager;
import com.nullfish.app.jfd2.command.progress.ProgressViewer;
import com.nullfish.app.jfd2.config.Configulation;
import com.nullfish.app.jfd2.dialog.JFDDialog;
import com.nullfish.app.jfd2.ui.table.RendererMode;
import com.nullfish.app.jfd2.util.IncrementalSearcher;
import com.nullfish.lib.vfs.VFile;
import com.nullfish.lib.vfs.exception.VFSException;

/**
 * @author shunji
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public interface JFD extends JFDComponent {
	/**
	 * ���ʐݒ�t�@�C����
	 */
	public static final String COMMON_PARAM_FILE = "common_config.xml";
	/**
	 * �ʐݒ�t�@�C����
	 */
	public static final String LOCAL_PARAM_FILE = "local_config.xml";

	/**
	 * ���f�����擾����B
	 * @return
	 */
	public JFDModel getModel();

	/**
	 * �\���J��������ݒ肷��B
	 * @param columnCount
	 */
	public void setColumnCount(int columnCount);
	
	/**
	 * �\���J���������擾����B
	 * @return
	 */
	public int getColumnCount();
	
	/**
	 * �\���s�����擾����B
	 * @return
	 */
	public int getRowCount();
	
	/**
	 * �y�[�W�ԍ����擾����B
	 * @return
	 */
	public int getPage();
	
	/**
	 * ��������b�N�A��������B
	 * @param locked	true�Ȃ烍�b�N
	 */
	public void setLocked(boolean locked);
	
	/**
	 * �C���N�������^���T�[�`���[�h��ݒ肷��B
	 * @param bool	true�Ȃ�C���N�������^���T�[�`
	 */
	public void setIncrementalSearchMode(boolean bool);
	
	/**
	 * �R�}���h�}�l�[�W�����擾����B
	 *
	 */
	public CommandManager getCommandManager();
	
	/**
	 * ���ʐݒ���擾����B
	 * @return
	 */
	public Configulation getCommonConfigulation();

	/**
	 * �ʐݒ���擾����B
	 * @return
	 */
	public Configulation getLocalConfigulation();

	/**
	 * ��ۑ��ݒ���擾����B
	 * @return
	 */
	public Configulation getTemporaryConfigulation();

	/**
	 * ���݂̃t�@�C���\�����[�h��ݒ肷��B
	 * 
	 */
	public void setRendererMode(RendererMode mode);

	/**
	 * ��̃_�C�A���O�𐶐�����B
	 * @return
	 */
	public JFDDialog createDialog();
	
	/**
	 * ���ɕ\�����郁�b�Z�[�W���Z�b�g����B
	 * @param message
	 */
	public void setMessage(String message);
	
	/**
	 * ���ɕ\�����郁�b�Z�[�W�𐧌����ԕt���ŃZ�b�g����B
	 * @param message
	 * @param time
	 */
	public void setMessage(String message, long time);
	
	/**
	 * �呀���ݒ肷��B
	 * @param command
	 */
	public void setPrimaryCommand(Command command);
	
	/**
	 * �呀����擾����B
	 */
	public Command getPrimaryCommand();
	
	/**
	 * �o�ߕ\���r���[�A���擾����B
	 * @return
	 */
	public ProgressViewer getProgressViewer();
	
	/**
	 * ����������
	 * @param baseDir
	 * @throws VFSException
	 */
	public void init(VFile baseDir) throws VFSException;

	/**
	 * �ݒ�ۑ�
	 * @param baseDir
	 * @throws VFSException
	 */
	public void save(VFile baseDir) throws VFSException;
	
	public void dispose();
	
	public AliaseManager getAliaseManager();
	
	public void setShowsRelativePath(boolean bool);
	
	public boolean showsRelativePath();
	
	public IncrementalSearcher getIncrementalSearcher();
	
}
