import com.nullfish.lib.vfs.VFS
import com.nullfish.lib.vfs.impl.DefaultOverwritePolicy

current = jfd.getModel().getCurrentDirectory()
files = jfd.getModel().getMarkedFiles();

fileSystem = null

dialog = jfd.createDialog()
try{
	dialog.addMessage('ˆÚ“®æ‚ð“ü—Í‚µ‚Ä‚­‚¾‚³‚¢')
	dialog.addFileComboBoxWithHistory("dest", true, true, true, jfd, null)

	dialog.addButton('ok', 'OK', 'o'.charAt(0), true)
	dialog.addButton("cancel", "cancel", 'c'.charAt(0), false)
	dialog.pack()
	dialog.setVisible(true)

	button = dialog.getButtonAnswer()
	if(button == "cancel" || button == null) {
		return
	}

	dest = vfs.getFile(dialog.getTextFieldAnswer("dest"))
	fileSystem = dest.getFileSystem()
	//fileSystem.registUser(command)

	command.showProgress()

	for(file in files) {
		fileName = file.getName()
		int start = fileName.indexOf("[");
		int end = fileName.indexOf("]");
		if(start >= 0 && end >= 0) {
			author = fileName.substring(start + 1, end).trim()

			file.moveTo(dest.getChild(author).getChild(fileName), command)
		}
	}

} finally {
	dialog.dispose()
}
