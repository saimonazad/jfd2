/*
 * Created on 2004/05/18
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.nullfish.app.jfd2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.nullfish.app.jfd2.command.Command;
import com.nullfish.app.jfd2.command.JFDManipulationListener;
import com.nullfish.app.jfd2.comparator.JFDComparator;
import com.nullfish.app.jfd2.resource.JFDResource;
import com.nullfish.app.jfd2.util.FileHistory;
import com.nullfish.lib.vfs.FileListener;
import com.nullfish.lib.vfs.FileSystem;
import com.nullfish.lib.vfs.UpdateManager;
import com.nullfish.lib.vfs.VFile;
import com.nullfish.lib.vfs.exception.ManipulationStoppedException;
import com.nullfish.lib.vfs.exception.VFSException;

/**
 * @author shunji
 * 
 * To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Generation - Code and Comments
 */
public class JFDModel implements FileListener {

	/**
	 * �\���t�@�C���̔z��B �J�����g�f�B���N�g���A�e�f�B���N�g���A�}�E���g�|�C���g�� �܂܂Ȃ��B
	 */
	private FileMarker[] markedFiles;

	private boolean autoNotify = true;

	private boolean hasChanged = false;

	/**
	 * �I���t�@�C���̃C���f�b�N�X
	 */
	private int selectedIndex = 0;

	/**
	 * �J�����g�f�B���N�g��
	 */
	private VFile currentDirectory;

	/**
	 * �e�f�B���N�g��
	 */
	private VFile parentDirectory;

	/**
	 * �t�@�C���V�X�e���̃}�E���g�|�C���g
	 */
	private VFile mountPoint;

	/**
	 * �t�@�C����r�N���X
	 */
	private JFDComparator comparator;

	/**
	 * �J�����g�f�B���N�g���A�e�f�B���N�g���A�}�E���g�|�C���g���̓���t�@�C��
	 */
	private List specialFiles = new ArrayList();

	/**
	 * �f�B���N�g���q�X�g��
	 */
	private FileHistory history = new FileHistory(50, false);

	/**
	 * �ړ��f�B���N�g�������ȊO���܂ށA�d�������̃t�@�C���q�X�g��
	 */
	private FileHistory noOverwrapHistory = new FileHistory(50, true);

	/**
	 * ���f���̃��X�i
	 */
	private List listeners = new ArrayList();

	/**
	 * �t�@�C���X�V�ɂ���Ď����I�ɍs���郂�f���̍X�V�̃��b�N�Ǘ��N���X
	 */
	private AutoUpdateLockManager lock = new AutoUpdateLockManager();

	/**
	 * �f�B���N�g�����X�V����A�\�������t���b�V������K�v�����邩�ǂ����̃t���O
	 */
	private boolean needsRefresh = false;

	/**
	 * �\���X�V�^�C�}�[
	 */
	private Timer updateTimer = new Timer();

	/**
	 * �\���X�V�Ԋu
	 */
	private static final long UPDATE_PERIOD = 1000;

	/**
	 * �f�B���N�g���I�[�v���R�}���h�B �񓯊��ŊJ���ۂɎg�p�����B
	 */
	private SetDirectoryCommand setDirectoryCommand = null;

	/**
	 * "."�Ŏn�܂�t�@�C�����t�B���^���邩�ǂ����̃t���O
	 */
	private boolean filtersDotFile = true;
	
	/**
	 * �t�B���^�[���K�\��
	 */
	private String filter = "^\\..*";
	
	/**
	 * �f�t�H���g�R���X�g���N�^
	 * 
	 */
	public JFDModel() {
		updateTimer.schedule(new UpdateTimerTask(), 0, UPDATE_PERIOD);
	}

	/**
	 * �J�����g�f�B���N�g�����擾����B
	 * 
	 * @return �J�����g�f�B���N�g��
	 */
	public VFile getCurrentDirectory() {
		return currentDirectory;
	}

	/**
	 * �\�����̃t�@�C�����擾����B �������A����t�@�C���͊܂܂Ȃ��B
	 * 
	 * @return
	 */
	public VFile[] getFiles() {
		VFile[] rtn = new VFile[markedFiles.length];
		for (int i = 0; i < markedFiles.length; i++) {
			rtn[i] = markedFiles[i].getFile();
		}

		return rtn;
	}

	/**
	 * �J�����g�f�B���N�g�����Z�b�g����B
	 * 
	 * @param currentDirectory
	 */
	public synchronized void setDirectory(VFile currentDirectory,
			VFile selectedFile) throws VFSException {
		stopSetDirectoryCommand();

		VFile[] files = currentDirectory.getChildren();
		doSetFiles(currentDirectory, files, selectedFile);
	}

	/**
	 * �J�����g�f�B���N�g�����Z�b�g����B
	 * 
	 * @param currentDirectory
	 */
	public synchronized void setDirectory(VFile currentDirectory,
			int selectedIndex) throws VFSException {
		stopSetDirectoryCommand();

		VFile[] files = currentDirectory.getChildren();
		doSetFiles(currentDirectory, files, selectedIndex);
	}

	/**
	 * �J�����g�f�B���N�g����K�v�Ȃ�񓯊��ŃZ�b�g����B
	 * 
	 * @param currentDirectory
	 */
	public synchronized void setDirectoryAsynchIfNecessary(
			VFile currentDirectory, VFile selectedFile, JFD jfd)
			throws VFSException {
		stopSetDirectoryCommand();

		if (currentDirectory.getFileSystem().isLocal()) {
			setDirectory(currentDirectory, selectedFile);
		} else {
			setDirectoryAsynch(currentDirectory, selectedFile, jfd);
		}
	}

	/**
	 * �J�����g�f�B���N�g����K�v�Ȃ�񓯊��ŃZ�b�g����B
	 * 
	 * @param currentDirectory
	 */
	public synchronized void setDirectoryAsynchIfNecessary(
			VFile currentDirectory, int selectedIndex, JFD jfd)
			throws VFSException {
		stopSetDirectoryCommand();

		if (currentDirectory.getFileSystem().isLocal()) {
			setDirectory(currentDirectory, selectedIndex);
		} else {
			setDirectoryAsynch(currentDirectory, selectedIndex, jfd);
		}
	}

	/**
	 * �J�����g�f�B���N�g����񓯊��ŃZ�b�g����B
	 * 
	 * @param currentDirectory
	 */
	public synchronized void setDirectoryAsynch(
			VFile currentDirectory, VFile selectedFile, JFD jfd)
			throws VFSException {
		stopSetDirectoryCommand();

		setDirectoryCommand = new SetDirectoryCommand(currentDirectory,
				selectedFile);
		setDirectoryCommand.setJFD(jfd);
		setDirectoryCommand.addManipulationListener(new JFDManipulationListener(setDirectoryCommand));
		setDirectoryCommand.startAsync();
	}

	/**
	 * �J�����g�f�B���N�g����񓯊��ŃZ�b�g����B
	 * 
	 * @param currentDirectory
	 */
	public synchronized void setDirectoryAsynch(
			VFile currentDirectory, int selectedIndex, JFD jfd)
			throws VFSException {
		stopSetDirectoryCommand();

		setDirectoryCommand = new SetDirectoryCommand(currentDirectory,
				selectedIndex);
		setDirectoryCommand.addManipulationListener(new JFDManipulationListener(setDirectoryCommand));
		setDirectoryCommand.setJFD(jfd);
		jfd.setPrimaryCommand(setDirectoryCommand);
		setDirectoryCommand.startAsync();
	}

	/**
	 * �J�����g�f�B���N�g���ƕ\���t�@�C�����Z�b�g����B
	 * 
	 * @param currentDirectory
	 * @param files
	 * @throws VFSException
	 */
	public synchronized void setFiles(VFile currentDirectory, VFile[] files,
			int selectedIndex) throws VFSException {
		stopSetDirectoryCommand();

		doSetFiles(currentDirectory, files, selectedIndex);
	}

	/**
	 * �J�����g�f�B���N�g���ƕ\���t�@�C�����Z�b�g����B
	 * 
	 * @param currentDirectory
	 * @param files
	 * @throws VFSException
	 */
	private void doSetFiles(VFile currentDirectory, VFile[] files,
			int selectedIndex) throws VFSException {
		sortVFileArray(files);
		initFiles(currentDirectory, files);

		doSetSelectedIndex(selectedIndex);

		fireDirectoryChanged();
	}

	/**
	 * �J�����g�f�B���N�g���ƕ\���t�@�C�����Z�b�g����B
	 * 
	 * @param currentDirectory
	 * @param files
	 * @throws VFSException
	 */
	public synchronized void setFiles(VFile currentDirectory, VFile[] files,
			VFile selectedFile) throws VFSException {
		stopSetDirectoryCommand();

		doSetFiles(currentDirectory, files, selectedFile);
	}

	/**
	 * �J�����g�f�B���N�g���ƕ\���t�@�C�����Z�b�g����B
	 * 
	 * @param currentDirectory
	 * @param files
	 * @throws VFSException
	 */
	private void doSetFiles(VFile currentDirectory, VFile[] files,
			VFile selectedFile) throws VFSException {
		sortVFileArray(files);
		initFiles(currentDirectory, files);

		doSetSelectedIndex(getIndexOfFile(selectedFile));

		fireDirectoryChanged();
	}

	/**
	 * �A�N�e�B�u�ȃt�@�C���V�X�e���̊Ǘ����s�Ȃ��B
	 * @param newDirectory
	 * @throws VFSException
	 */
	private void registerActiveFileSystem(VFile newDirectory) throws VFSException {
		if (currentDirectory != null) {
			if (currentDirectory.getFileSystem() == newDirectory
					.getFileSystem()) {
				return;
			}

			currentDirectory.getFileSystem().removeUser(this);
		}
		
		newDirectory.getFileSystem().registerUser(this);
		newDirectory.getFileSystem().getVFS().closeUnusedFileSystem();
	}

	/**
	 * ���������s���̃f�B���N�g���I�[�v�����삪����ꍇ�A���~����B
	 * 
	 */
	private void stopSetDirectoryCommand() {
		if (setDirectoryCommand != null) {
			setDirectoryCommand.stop();
			setDirectoryCommand = null;
		}
	}

	/**
	 * �J�����g�f�B���N�g���ƕ\���t�@�C��������������B
	 * 
	 * @param currentDirectory
	 * @param files
	 * @throws VFSException
	 */
	private void initFiles(VFile currentDirectory, VFile[] files) throws VFSException {
		registerActiveFileSystem(currentDirectory);
		if(filtersDotFile) {
			List filesList = new ArrayList();
			Pattern pattern = Pattern.compile(filter);
			for(int i=0; i<files.length; i++) {
				if(!pattern.matcher(files[i].getName()).matches()) {
					filesList.add(files[i]);
				}
			}
			files = new VFile[filesList.size()];
			files = (VFile[])filesList.toArray(files);
		}
		
		if (currentDirectory != this.currentDirectory) {
			if (this.currentDirectory != null) {
				this.currentDirectory.removeFileListener(this);
				this.currentDirectory.removeChildFileListener(this);
			}
			currentDirectory.addFileListener(this);
			currentDirectory.addChildFileListener(this);
		}

		// �q�X�g���ɒǉ�
		history.add(currentDirectory);
		noOverwrapHistory.add(currentDirectory);

		this.currentDirectory = currentDirectory;
		if (currentDirectory == null) {
			parentDirectory = null;
			mountPoint = null;
		} else {
			parentDirectory = currentDirectory.getParent();
			if (currentDirectory.isRoot()) {
				mountPoint = currentDirectory.getFileSystem().getMountPoint();
			} else {
				mountPoint = null;
			}
		}

		specialFiles.clear();
		try {
			if (currentDirectory != null && currentDirectory.exists()) {
				specialFiles.add(currentDirectory);
			}
		} catch (VFSException e) {
			// �J�����g�f�B���N�g���ɃA�N�Z�X�s�\�Ȃ̂Œǉ������B
		}

		if (parentDirectory != null) {
			specialFiles.add(parentDirectory);
		}

		if (mountPoint != null) {
			specialFiles.add(mountPoint);
		}

		markedFiles = new FileMarker[files.length];
		for (int i = 0; i < files.length; i++) {
			markedFiles[i] = new FileMarker(files[i]);
		}
	}

	/**
	 * �\���t�@�C�������擾����B
	 * 
	 * @return
	 */
	public int getFilesCount() {
		return specialFiles.size()
				+ (markedFiles != null ? markedFiles.length : 0);
	}

	/**
	 * �w��C���f�b�N�X�Ԗڂ̃t�@�C�����擾����B
	 * 
	 * @param index
	 * @return
	 */
	public VFile getFileAt(int index) {
		int specialFilesCount = specialFiles.size();
		if (index < specialFilesCount) {
			return (VFile) specialFiles.get(index);
		}

		return markedFiles[index - specialFilesCount].getFile();
	}

	/**
	 * �t�@�C�����}�[�N����Ă��邩���肷��B
	 * 
	 * @param index
	 * @return
	 */
	public boolean isMarked(int index) {
		int specialsLength = specialFiles.size();
		if (index < specialsLength) {
			return false;
		}

		if (index - specialsLength < markedFiles.length) {
			return markedFiles[index - specialsLength].isMarked();
		}

		return false;
	}

	/**
	 * �}�[�N�t�@�C�������擾����B
	 * 
	 * @return
	 */
	public int getMarkedCount() {
		int rtn = 0;
		for (int i = 0; i < markedFiles.length; i++) {
			if (markedFiles[i].isMarked()) {
				rtn++;
			}
		}

		return rtn;
	}

	/**
	 * �}�[�N���ꂽ�t�@�C���̔z����擾����B
	 * 
	 * @return
	 */
	public VFile[] getMarkedFiles() {
		List list = new ArrayList();

		for (int i = 0; i < markedFiles.length; i++) {
			if (markedFiles[i].isMarked()) {
				list.add(markedFiles[i].getFile());
			}
		}

		VFile[] rtn = new VFile[list.size()];
		rtn = (VFile[]) list.toArray(rtn);
		return rtn;
	}

	/**
	 * �}�[�N���ꂽ�t�@�C��������΂�����A�����łȂ��ꍇ�͑I�����ꂽ�t�@�C����
	 * �z��ŕԂ��B
	 * 
	 * @return
	 */
	public VFile[] getMarkedOrSelectedFiles() {
		if(getMarkedCount() == 0) {
			VFile[] rtn = {
					getSelectedFile()
			};
			
			return rtn;
		} else {
			return getMarkedFiles();
		}
	}
	
	public void setMarked(int index, boolean bool) {
		int specialsLength = specialFiles.size();
		if (index < specialsLength) {
			return;
		}

		if (index - specialsLength < markedFiles.length) {
			markedFiles[index - specialsLength].setMarked(bool);
		}

		fireFilesChanged();
	}

	/**
	 * �I�����ꂽ�C���f�b�N�X��Ԃ��B
	 * 
	 * @return
	 */
	public int getSelectedIndex() {
		return selectedIndex;
	}

	/**
	 * �I�����ꂽ�t�@�C����Ԃ��B
	 * 
	 * @return
	 */
	public VFile getSelectedFile() {
		if(selectedIndex < 0) {
			return null;
		}
		return getFileAt(selectedIndex);
	}

	/**
	 * �I���C���f�b�N�X��ݒ肷��B
	 * 
	 * @param index
	 */
	public void setSelectedIndex(int index) {
		int oldIndex = selectedIndex;
		doSetSelectedIndex(index);
		fireCursorMoved(oldIndex, selectedIndex);
	}

	private void doSetSelectedIndex(int index) {
		if (markedFiles == null || index < 0) {
			index = 0;
		}

		if (specialFiles.size() + (markedFiles != null ? markedFiles.length : 0) <= index ) {
			index = specialFiles.size() + (markedFiles != null ? markedFiles.length : 0) - 1;
		}

		selectedIndex = index;
	}

	/**
	 * �I���t�@�C�����w�肷��B
	 * 
	 * @param file
	 */
	public void setSelectedFile(VFile file) {
		setSelectedIndex(getIndexOfFile(file));
	}

	/**
	 * �w�肳�ꂽ�t�@�C���̏��Ԃ����߂�B ���������݂��Ȃ��ꍇ��-1��Ԃ��B
	 * 
	 * @param file
	 * @return
	 */
	private int getIndexOfFile(VFile file) {
		for (int i = 0; i < specialFiles.size(); i++) {
			VFile f = (VFile) specialFiles.get(i);
			if (f.equals(file)) {
				return i;
			}
		}

		for (int i = 0; markedFiles != null && i < markedFiles.length; i++) {
			if (markedFiles[i].getFile().equals(file)) {
				return i + specialFiles.size();
			}
		}

		return -1;
	}

	/**
	 * ���f�����X�i��ǉ�����B
	 * 
	 * @param listener
	 *            ���X�i
	 */
	public void addJFDModelListener(JFDModelListener listener) {
		listeners.add(listener);
	}

	/**
	 * ���f�����X�i���폜����B
	 * 
	 * @param listener
	 *            ���X�i
	 */
	public void removeJFDModelListener(JFDModelListener listener) {
		listeners.remove(listener);
	}

	/**
	 * �t�@�C�����ύX���ꂽ�ۂɌĂяo����A ���X�i�ɒʒm����B
	 * 
	 */
	public void fireFilesChanged() {
		if (!autoNotify) {
			hasChanged = true;
			return;
		}

		for (int i = 0; i < listeners.size(); i++) {
			JFDModelListener listener = (JFDModelListener) listeners.get(i);
			listener.dataChanged(this);
		}
	}

	/**
	 * �f�B���N�g�����ړ������ۂɌĂяo����A���X�i�ɒʒm����B
	 * 
	 */
	public void fireDirectoryChanged() {
		for (int i = 0; i < listeners.size(); i++) {
			JFDModelListener listener = (JFDModelListener) listeners.get(i);
			listener.directoryChanged(this);
		}
	}

	/**
	 * �J�[�\�����ړ������ۂɌĂяo����A���X�i�ɒʒm����B
	 * 
	 * @param oldIndex
	 * @param newIndex
	 */
	public void fireCursorMoved(int oldIndex, int newIndex) {
		for (int i = 0; i < listeners.size(); i++) {
			JFDModelListener listener = (JFDModelListener) listeners.get(i);
			listener.cursorMoved(this);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nullfish.lib.vfs.FileListener#fileChanged(com.nullfish.lib.vfs.VFile)
	 */
	public void fileChanged(VFile file) {
		if (!lock.isLocked()) {
			needsRefresh = true;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nullfish.lib.vfs.FileListener#fileDeleted(com.nullfish.lib.vfs.VFile)
	 */
	public void fileDeleted(VFile file) {
		if (!lock.isLocked()) {
			needsRefresh = true;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nullfish.lib.vfs.FileListener#fileCreated(com.nullfish.lib.vfs.VFile)
	 */
	public void fileCreated(VFile file) {
		if (!lock.isLocked()) {
			needsRefresh = true;
		}
	}

	/**
	 * @return Returns the comparator.
	 */
	public JFDComparator getComparator() {
		return comparator;
	}

	/**
	 * @param comparator
	 *            The comparator to set.
	 */
	public void setComparator(JFDComparator comparator) {
		this.comparator = comparator;
	}

	/**
	 * �t�@�C���ύX���Afalse���Z�b�g���Ă����΁Atrue�ɃZ�b�g����܂Œʒm���s��Ȃ��B
	 * 
	 * @param autoNotify
	 */
	public void setAutoNotify(boolean autoNotify) {
		this.autoNotify = autoNotify;
		if (autoNotify && hasChanged) {
			fireFilesChanged();
			hasChanged = false;
		}
	}

	private void sortVFileArray(VFile[] files) {
		if (comparator == null || !comparator.compares()) {
			return;
		}

		Arrays.sort(files, comparator);
	}

	public FileHistory getHistory() {
		return history;
	}

	public FileHistory getNoOverwrapHistory() {
		return noOverwrapHistory;
	}

	/**
	 * �����X�V�𖳌��ɂ���B
	 * 
	 * @param o
	 *            ���b�N�I�u�W�F�N�g
	 */
	public void lockAutoUpdate(Object o) {
		lock.lock(o);
	}

	/**
	 * �����X�V��L���ɂ���B
	 * 
	 * @param o
	 *            ���b�N�I�u�W�F�N�g
	 */
	public void unlockAutoUpdate(Object o) {
		lock.unlock(o);
	}

	/**
	 * ���f�����X�V����B
	 * 
	 */
	public void refresh() {
		try {
			VFile[] files = currentDirectory.getChildren();
			VFile selectedFile = getSelectedFile();
			if (selectedFile.exists()) {
				setFiles(currentDirectory, files, getSelectedFile());
			} else {
				setFiles(currentDirectory, files, getSelectedIndex());
			}
		} catch (VFSException e) {
			e.printStackTrace();
		}
	}

	public void dispose() {
		listeners = null;
		UpdateManager.getInstance().removeListener(this);
		updateTimer.cancel();
	}

	/**
	 * �f�B���N�g���̍X�V���Ď����āA�K�v�Ȃ�\�����X�V����^�C�}�[�^�X�N
	 */
	private class UpdateTimerTask extends TimerTask {
		public void run() {
			if (!needsRefresh) {
				return;
			}
			needsRefresh = false;

			if (!lock.isLocked()) {
				refresh();
			}

			fireFilesChanged();
		}
	}

	private static String[] openingMessages = {
			JFDResource.LABELS.getString("opening_dir"),
			JFDResource.LABELS.getString("opening_dir") + ".",
			JFDResource.LABELS.getString("opening_dir") + "..",
			JFDResource.LABELS.getString("opening_dir") + "...", };

	private class SetDirectoryCommand extends Command {
		/**
		 * �J���f�B���N�g��
		 */
		private VFile dir;

		/**
		 * �I���t�@�C��
		 */
		private VFile selectedFile;

		/**
		 * �I���C���f�b�N�X
		 */
		private int selectedIndex = -1;

		public SetDirectoryCommand(VFile dir, VFile selectedFile) {
			this.dir = dir;
			this.selectedFile = selectedFile;
		}

		public SetDirectoryCommand(VFile dir, int selectedIndex) {
			this.dir = dir;
			this.selectedIndex = selectedIndex;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.nullfish.lib.vfs.manipulation.abst.AbstractManipulation#doExecute()
		 */
		public void doExecute() throws VFSException {
			getJFD().setPrimaryCommand(this);

			VFile[] children = dir.getChildren(this);
			//	�����O�ɐe�̑������擾���āA�C�x���g�f�B�X�p�b�`�X���b�h��
			//	���s����Ȃ��悤�ɂ��Ă���B
			dir.exists(this);
			dir.getPermission(this);
			VFile parent = dir.getParent();
			if(parent != null) {
				parent.exists(this);
				parent.getPermission(this);
			}
			
			if (isStopped()) {
				throw new ManipulationStoppedException(this);
			}

			if (selectedFile != null) {
				doSetFiles(dir, children, selectedFile);
			} else {
				doSetFiles(dir, children, selectedIndex);
			}
		}

		/**
		 * ��ƌo�߃��b�Z�[�W���擾����B
		 * 
		 * @return
		 */
		public String getProgressMessage() {
			return openingMessages[(int) (System.currentTimeMillis() / 600) % 4];
		}

		/* (non-Javadoc)
		 * @see com.nullfish.app.jfd2.command.Command#closesUnusingFileSystem()
		 */
		public boolean closesUnusingFileSystem() {
			return true;
		}

		/**
		 * 
		 * @return
		 */
		public FileSystem[] getUsingFileSystem() {
			FileSystem[] rtn = {
				dir.getFileSystem()
			};
			
			return rtn;
		}
		
		public boolean isPrimary() {
			return true;
		}
	}

	/**
	 * "."�Ŏn�܂�t�@�C�����\���ɂ��邩�ǂ�����ݒ肷��B
	 * @param filtersDotFile	true�Ȃ��\��
	 */
	public void setFiltersFile(boolean filtersDotFile) {
		this.filtersDotFile = filtersDotFile;
	}
	
	/**
	 * �t�B���^�[�������ݒ肷��B
	 * @param filter
	 */
	public void setFilter(String filter) {
		this.filter = filter;
	}
}