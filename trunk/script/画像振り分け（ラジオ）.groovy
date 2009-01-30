import com.nullfish.app.jfd2.dialog.DialogUtilities
import com.nullfish.app.jfd2.viewer.FileViewerManager
import com.nullfish.app.jfd2.command.embed.CopyOverwritePolicy
import com.nullfish.lib.ui.Choice

model = jfd.getModel()
currentDir = model.getCurrentDirectory()
policy = new CopyOverwritePolicy(command);

try {
	jfd.getJFDOwner().setExtendedState(javax.swing.JFrame.ICONIFIED)

	files = model.getMarkedFiles()
	if(files == null || files.length == 0) {
		files = model.getFiles()
	}

	dests = []
	selected = null;

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

		file.copyTo(currentDir.getChild(selected).getChild(file.getName()), policy,	 command)
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
			dialog.addTextField("dest", "", true)
			initRadio(dialog)
			dialog.pack()
			dialog.setVisible(true)

			button = dialog.getButtonAnswer()
			inputDest = dialog.getTextFieldAnswer("dest")
			selected = dialog.getChooserAnswer("radio_dest")
			
			if("cancel".equals(button) || "skip".equals(button)) {
				return button
			} else if(inputDest.length() > 0) {
				if(!dests.contains(inputDest)) {
					dests.add(inputDest)
					Collections.sort(dests)
				}
				selected = inputDest
			} else {
				return button
			}
		} finally {
			dialog.dispose()
		}
	}
}

def initRadio(dialog) {
	choices = []
	i = 0
	for(dest in dests) {
		choices.add(new Choice(dest, dest, (i++).toString().charAt(0)))
	}
	dialog.addChooser("radio_dest", "コピー先", choices.toArray(new Choice[0]), 3, selected, null, true)
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