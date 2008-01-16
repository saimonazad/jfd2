package com.nullfish.lib.ui.filetree;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;

import com.nullfish.lib.ui.ThreadSafeUtilities;
import com.nullfish.lib.vfs.VFile;
import com.nullfish.lib.vfs.exception.VFSException;

public class VFileTreeNode implements TreeNode {
	private VFile file;

	private boolean childInit = false;

	private List childNodes = new ArrayList();

	private List tempChildNodes = new ArrayList();

	private VFileTreeNode parent;

	private boolean showsFile = true;
	
	private boolean showsArchive = true;

	private VFileTree tree;

	private int status = STATUS_NOT_LOADED;

	public static final int STATUS_NOT_LOADED = 0;

	public static final int STATUS_LOADING = 1;

	public static final int STATUS_LOADED = 2;

	public VFileTreeNode(VFileTree tree, VFileTreeNode parent,
			VFile file) {
		this.tree = tree;
		this.parent = parent;
		this.file = file;
	}

	private void initChild() {
		if (childInit) {
			return;
		}

		childInit = true;
		try {
			if (file.isDirectory()) {
				if (file.getFileSystem().isLocal()) {
					VFile[] children = file.getChildren();
					for (int i = 0; i < children.length; i++) {
						if (showsFile || children[i].isDirectory() || (showsArchive && children[i].getInnerRoot() != null)) {
							childNodes.add(new VFileTreeNode(tree, this,
									children[i]));
						}
					}
				} else {
					Thread thread = new Thread() {
						public void run() {
							setStatus(STATUS_LOADING);
							Runnable runnable = new Runnable() {
								public void run() {
									tree.getModel().reload(VFileTreeNode.this);
								}
							};
							ThreadSafeUtilities.executeRunnable(runnable);

							try {
								filterChildren(file.getChildren());
								setStatus(STATUS_LOADED);
								ThreadSafeUtilities.executeRunnable(runnable);
							} catch (VFSException e) {
								e.printStackTrace();
							}
						}
					};
					thread.start();
				}
			} else if (file.getInnerRoot() != null) {
				Thread thread = new Thread() {
					public void run() {
						setStatus(STATUS_LOADING);
						Runnable runnable = new Runnable() {
							public void run() {
								tree.getModel().reload(VFileTreeNode.this);
							}
						};
						ThreadSafeUtilities.executeRunnable(runnable);

						try {
							filterChildren(file.getInnerRoot().getChildren());
							setStatus(STATUS_LOADED);
							ThreadSafeUtilities.executeRunnable(runnable);
						} catch (VFSException e) {
							e.printStackTrace();
						}

					}
				};
				thread.start();
			}
		} catch (VFSException e) {
			e.printStackTrace();
		}
	}

	private void filterChildren(VFile[] children) throws VFSException {
		for (int i = 0; i < children.length; i++) {
			if (showsFile || children[i].isDirectory() || (showsArchive && children[i].getInnerRoot() != null)) {
				VFileTreeNode child = new VFileTreeNode(
						tree, VFileTreeNode.this,
						children[i]);
				int index = tempChildNodes.indexOf(child);
				if (index != -1) {
					child = (VFileTreeNode) tempChildNodes
							.get(index);
				}
				childNodes.add(child);
			}
		}


	}
	public Enumeration children() {
		initChild();
		return new Enumeration() {
			int index = -1;

			public boolean hasMoreElements() {
				return index + 1 < childNodes.size();
			}

			public Object nextElement() {
				index++;
				return childNodes.get(index);
			}
		};
	}

	public boolean getAllowsChildren() {
		try {
			return file.isDirectory();
		} catch (VFSException e) {
			return false;
		}
	}

	public TreeNode getChildAt(int childIndex) {
		initChild();
		return (TreeNode) childNodes.get(childIndex);
	}

	public int getChildCount() {
		initChild();
		return childNodes.size();
	}

	public int getIndex(TreeNode node) {
		initChild();
		return childNodes.indexOf(node);
	}

	public TreeNode getParent() {
		return parent;
	}

	public boolean isLeaf() {
		try {
			if (file.isDirectory()) {
				return false;
			}
			if (file.getInnerRoot() != null) {
				return false;
			}

			return true;
		} catch (VFSException e) {
			e.printStackTrace();
			return true;
		}
	}

	public String toString() {
		return (file.isRoot() ? file.getAbsolutePath() : file.getName())
				+ (status == STATUS_LOADING ? "(loading)" : "");
	}

	public boolean equals(Object o) {
		if (!(o instanceof VFileTreeNode)) {
			return false;
		}

		return file.equals(((VFileTreeNode) o).file);
	}

	public int hashCode() {
		return file.hashCode();
	}

	public VFile getFile() {
		return file;
	}

	private synchronized int getStatus() {
		return status;
	}

	private synchronized void setStatus(int status) {
		this.status = status;
	}
}
