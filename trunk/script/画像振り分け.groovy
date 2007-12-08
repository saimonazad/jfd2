import com.nullfish.app.jfd2.dialog.DialogUtilities
import com.nullfish.app.jfd2.viewer.FileViewerManager
import com.nullfish.app.jfd2.command.embed.CopyOverwritePolicy

model = jfd.getModel()
currentDir = model.getCurrentDirectory()
policy = new CopyOverwritePolicy(command);

try {
	jfd.getJFDOwner().setExtendedState(javax.swing.JFrame.ICONIFIED)

	files = model.getMarkedFiles()
	if(files == null || files.length == 0) {
		files = model.getFiles()
	}

	dests = getSubDirectories()
	destChecked = [:]
	for(dest in dests) {
		destChecked[dest] = false
	}

	for(file in files) {
		if(file.isDirectory(command)) {
			continue
		}

		jfd.getModel().setSelectedFile(file);
		FileViewerManager.getInstance().openFile(jfd, file);
		button = showDialog(file)
		if(button == null || "cancel".equals(button)) {
			break
		}

		if("skip".equals(button)) {
			continue
		}

		for(dest in dests) {
			if(destChecked[dest]) {
				file.copyTo(currentDir.getChild(dest).getChild(file.getName()), policy,	 command)
			}
		}
	}

	model.setFiles(currentDir, currentDir.getChildren(command), currentDir.getParent());
} finally {
	jfd.getJFDOwner().setExtendedState(javax.swing.JFrame.NORMAL)
	viewer = FileViewerManager.getInstance().getTopViewer(jfd);
	if(viewer != null) {
		viewer.close()
	}
}

def showDialog(file) {
	while(true) {
		dialog = null;
		try {
			dialog = DialogUtilities.createOkCancelDialog(jfd, "jFD2 - 振り分け")
			dialog.addMessage(file.getName())
			dialog.addMessage("振り分け先を入力してください。チェックボックスが追加されます。")
			dialog.addMessage("振り分け先を入力しないでエンター、OKボタンで振り分けられます。")

			dialog.addButton("skip", "スキップ", "s".charAt(0), false)
			dialog.addButton("selectAll", "全選択", "a".charAt(0), false)
			dialog.addButton("clearAll", "全解除", "l".charAt(0), false)
			dialog.addTextField("dest", "", true)
			initCheckBox(dialog)
			dialog.pack()
			dialog.setVisible(true)

			button = dialog.getButtonAnswer()
			inputDest = dialog.getTextFieldAnswer("dest")

			if("cancel".equals(button) || "skip".equals(button)) {
				return button
			} else if("selectAll".equals(button)) {
				for(dest in dests) {
					destChecked[dest] = true
				}
			} else if("clearAll".equals(button)) {
				for(dest in dests) {
					destChecked[dest] = false
				}
			} else if(inputDest.length() > 0) {
				if(!dests.contains(inputDest)) {
					dests.add(inputDest)
					Collections.sort(dests)
				}
				destChecked[inputDest] = true
			} else {
				for(dest in dests) {
					destChecked[dest] = dialog.isChecked(dest)
				}
				return button
			}
		} finally {
			dialog.dispose()
		}
	}
}

def initCheckBox(dialog) {
	for(check in dests) {
		dialog.addCheckBox(check, check, " ".charAt(0),
			destChecked[check].booleanValue(), null, false);
	}
}

def getSubDirectories() {
	subDirs = []
	subFiles = currentDir.getChildren(command)
	for(file in subFiles) {
     	if(file.isDirectory(command)) {
			subDirs.add(file.getName())
		}
	}

	return subDirs
}