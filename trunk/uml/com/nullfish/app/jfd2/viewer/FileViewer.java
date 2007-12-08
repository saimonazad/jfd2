package com.nullfish.app.jfd2.viewer;

import org.sexyprogrammer.lib.vfs.VFile;

public abstract class FileViewer {
	public void open(VFile file) {
	}
	public void close() {
	}
	public void dispose() {
	}
	public FileViewerComponent getComponent() {
		return null;
	}
}
