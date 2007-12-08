/*
 * Created on 2004/05/25
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.nullfish.app.jfd2.command;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import javax.swing.SwingUtilities;

import com.nullfish.app.jfd2.JFD;
import com.nullfish.app.jfd2.dialog.DialogUtilities;
import com.nullfish.app.jfd2.dialog.JFDDialog;
import com.nullfish.app.jfd2.resource.JFDResource;
import com.nullfish.lib.vfs.FileSystem;
import com.nullfish.lib.vfs.Manipulation;
import com.nullfish.lib.vfs.VFS;
import com.nullfish.lib.vfs.VFile;
import com.nullfish.lib.vfs.exception.ManipulationStoppedException;
import com.nullfish.lib.vfs.exception.VFSException;
import com.nullfish.lib.vfs.manipulation.abst.AbstractManipulation;

/**
 * @author shunji
 * 
 * To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Generation - Code and Comments
 */
public abstract class Command extends AbstractManipulation {
	/**
	 * jFD�̃��f��
	 */
	private JFD jfd;

	/**
	 * �񓯊����s�t���O
	 */
	private boolean asynch = false;

	/**
	 * ���s����ʃ��b�N�t���O
	 */
	private boolean locks = true;

	/**
	 * ����
	 */
	private String name;

	/**
	 * �呀��Ƃ��ĉ�ʂɃZ�b�g����A�o�ߕ\���A���~�����t���s�Ȃ����̃t���O
	 */
	private boolean primary = false;

	/**
	 * ���~���Ƀ_�C�A���O�\�����s�Ȃ����̃t���O
	 */
	private boolean showsStopDialog = false;
	
	/**
	 * �p�����[�^�̃}�b�v
	 */
	private Map paramsMap;

	/**
	 * �R�}���h�^�O��
	 */
	public static final String COMMAND_TAG = "command";

	/**
	 * ���̑���
	 */
	public static final String ATTR_NAME = "name";

	/**
	 * �L���b�V���g�p����
	 */
	public static final String ATTR_CACHE = "cache";

	/**
	 * ���̑���
	 */
	public static final String ATTR_ASYNCH = "asynch";

	/**
	 * ���s����ʃ��b�N����
	 */
	public static final String ATTR_LOCKS = "locks";

	/**
	 * �呀�쑮��
	 */
	public static final String ATTR_PRIMARY = "primary";

	/**
	 * ���~���_�C�A���O�\������
	 */
	public static final String ATTR_SHOWS_STOP_DIALOG = "show_stopped";

	/**
	 * �p�����[�^�^�O
	 */
	public static final String PARAM_TAG = "param";

	/**
	 * �S�q����
	 */
	private Manipulation[] manipulations;

	/**
	 * �J�n���̃J�����g�f�B���N�g��
	 */
	protected VFile currentDir;
	
	/**
	 * �R���X�g���N�^
	 * 
	 * @param name
	 */
	public Command() {
		super(null);
	}

	/**
	 * Manipulation#start()�̃I�[�o�[���C�h�B
	 * 
	 */
	public void start() throws VFSException {
		try {
			currentDir = getJFD().getModel().getCurrentDirectory();
			
			registerUserToFileSystem();
			super.start();
		} catch (ManipulationStoppedException e) {
			if(showsStopDialog) {
				DialogUtilities.showMessageDialog(jfd, e.getErrorMessage(), "jFD2");
			}
			throw e;
		} catch (VFSException e) {
			DialogUtilities.showMessageDialog(jfd, e.getErrorMessage(), "jFD2");
			throw e;
		} catch (Exception e) {
			//	�{���������Ă͂����Ȃ�
			JFDDialog dialog = null;
			try {
				dialog = jfd.createDialog();
				dialog.setTitle(JFDResource.LABELS.getString("title_error"));
				dialog.addMessage(JFDResource.MESSAGES.getString("unexpected_exception"));
				StringWriter writer = new StringWriter();
				e.printStackTrace(new PrintWriter(writer));
				writer.flush();
				writer.close();
				dialog.addTextArea("stackTrace", writer.toString(), false);
				dialog.addButton("ok", JFDResource.LABELS.getString("ok"), 'o', true);
				dialog.pack();
				dialog.setVisible(true);
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				dialog.dispose();
			}
		} finally {
			unregisterUserToFileSystem();
			
			if(closesUnusingFileSystem()) {
				VFS.getInstance(jfd).closeUnusedFileSystem();
			}
		}
	}

	/**
	 * �t�@�C���V�X�e���ɂ��̑���𗘗p�҂Ƃ��ēo�^����B
	 *
	 */
	private void registerUserToFileSystem() {
		if(currentDir != null) {
			currentDir.getFileSystem().registerUser(this);
		}
		
		FileSystem[] usingFileSystems = getUsingFileSystem();
		for (int i = 0; usingFileSystems != null && i < usingFileSystems.length; i++) {
			usingFileSystems[i].registerUser(this);
		}
	}

	/**
	 * �t�@�C���V�X�e�����炱�̑���𗘗p�҂Ƃ��č폜����B
	 * 
	 * @throws VFSException
	 *
	 */
	private void unregisterUserToFileSystem() throws VFSException {
		if(currentDir != null) {
			currentDir.getFileSystem().removeUser(this);
		}

		FileSystem[] usingFileSystems = getUsingFileSystem();
		if(usingFileSystems == null || usingFileSystems.length == 0) {
			return;
		}
		
		for (int i = 0; usingFileSystems != null && i < usingFileSystems.length; i++) {
			usingFileSystems[i].removeUser(this);
		}
	}

	/**
	 * �񓯊����s���s���Ȃ�true��Ԃ��
	 * 
	 * @return
	 */
	public boolean isAsynch() {
		return asynch;
	}

	/**
	 * �񓯊����s���s�����ݒ肷��B
	 * 
	 * @param asynch
	 */
	public void setAsynch(boolean asynch) {
		this.asynch = asynch;
	}

	/**
	 * ���s���A��ʂ����b�N����Ȃ�true��Ԃ��
	 * 
	 * @return
	 */
	public boolean isLocks() {
		return locks;
	}

	/**
	 * ���s���A��ʂ����b�N���邩�ݒ肷��B
	 * 
	 * @param locks
	 */
	public void setLocks(boolean locks) {
		this.locks = locks;
	}

	/**
	 * ����̖��̂�Ԃ��
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return Returns the jfdModel.
	 */
	public JFD getJFD() {
		return jfd;
	}

	/**
	 * @param jfdModel
	 *            The jfdModel to set.
	 */
	public void setJFD(JFD jfd) {
		this.jfd = jfd;
	}

	public Object getParameter(String name) {
		if (paramsMap == null) {
			return null;
		}

		return paramsMap.get(name);
	}

	public void setParameter(String name, Object value) {
		if (paramsMap == null) {
			paramsMap = new HashMap();
		}
		paramsMap.put(name, value);
	}

	/**
	 * 
	 * @param paramsMap
	 */
	public void setParamsMap(Map paramsMap) {
		this.paramsMap = paramsMap;
	}

	/**
	 * ��ƌo�߃��b�Z�[�W���擾����B
	 * 
	 * @return
	 */
	public String getProgressMessage() {
		Manipulation currentManipulation = getCurrentManipulation();
		
		if(currentManipulation == null) {
			return "";
		}
		
		if (currentManipulation != this) {
			return currentManipulation.getProgressMessage();
		}

		return "";
	}

	/**
	 * �q�����ݒ肷��B
	 * 
	 * @param children
	 */
	protected void setChildManipulations(Manipulation[] children) {
		manipulations = children;
	}

	public long getProgressMin() {
		if (manipulations == null) {
			return PROGRESS_INDETERMINED;
		}

		long sum = 0;
		for (int i = 0; i < manipulations.length; i++) {
			long p = manipulations[i].getProgressMin();
			if (p == PROGRESS_INDETERMINED) {
				return PROGRESS_INDETERMINED;
			}
			sum += p;
		}

		return sum;
	}

	public long getProgressMax() {
		if (manipulations == null) {
			return PROGRESS_INDETERMINED;
		}

		long sum = 0;
		for (int i = 0; i < manipulations.length; i++) {
			long p = manipulations[i].getProgressMax();
			if (p == PROGRESS_INDETERMINED) {
				return PROGRESS_INDETERMINED;
			}
			sum += p;
		}

		return sum;
	}

	public long getProgress() {
		if (manipulations == null) {
			return PROGRESS_INDETERMINED;
		}

		long sum = 0;
		for (int i = 0; i < manipulations.length; i++) {
			long p = manipulations[i].getProgress();
			if (p == PROGRESS_INDETERMINED) {
				return PROGRESS_INDETERMINED;
			}
			sum += p;
		}

		return sum;
	}

	/**
	 * @return Returns the primary.
	 */
	public boolean isPrimary() {
		return primary;
	}

	/**
	 * @param primary
	 *            The primary to set.
	 */
	public void setPrimary(boolean primary) {
		this.primary = primary;
	}

	/**
	 * �o�ߕ\�����s�Ȃ��B �X���b�h�Z�[�t
	 */
	public void showProgress() {
		showProgress(0);
	}

	/**
	 * �o�ߕ\�����s�Ȃ��B �X���b�h�Z�[�t
	 */
	public void showProgress(final int delay) {
		final Runnable opener = new Runnable() {
			public void run() {
				if (!Command.this.isFinished()) {
					getJFD().getProgressViewer().addCommand(Command.this);
				}
			}
		};

		Thread waiter = new Thread() {
			public void run() {
				try {
					if (delay > 0) {
						Thread.sleep(delay);
					}
				} catch (InterruptedException e) {
				}
				SwingUtilities.invokeLater(opener);
			}
		};

		waiter.start();
	}

	/**
	 * �g�p����t�@�C���V�X�e����Ԃ��B
	 * �������߂�l��null�ȊO���v�f����ȏ�̏ꍇ�A���̃R�}���h�̎��s��
	 * �t�@�C���V�X�e�������B
	 * �������J�����g�f�B���N�g���̃t�@�C���V�X�e���ȊO���g���ꍇ�A�I�[�o�[���C�h����B
	 * 
	 * @return
	 */
	public FileSystem[] getUsingFileSystem() {
		return null;
	}
	
	/**
	 * ���s��s�v�ȃt�@�C���V�X�e������邩���肷��B
	 * 
	 * @return	������true�Ȃ�start���\�b�h���s��A�s�v�ȃt�@�C���V�X�e�������B
	 */
	public abstract boolean closesUnusingFileSystem();

	public boolean isShowsStopDialog() {
		return showsStopDialog;
	}
	

	public void setShowsStopDialog(boolean showsStopDialog) {
		this.showsStopDialog = showsStopDialog;
	}
	
}
