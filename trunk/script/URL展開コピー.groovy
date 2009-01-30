import com.nullfish.lib.vfs.FileUtil
import com.nullfish.lib.vfs.Manipulation
import com.nullfish.lib.vfs.impl.DefaultOverwritePolicy

fileSystem = null
try{
dialog = jfd.createDialog()
dialog.addMessage('ƒtƒ@ƒCƒ‹–¼‚ð“ü—Í‚µ‚Ä‚­‚¾‚³‚¢')
dialog.addTextField("file", "", true)
dialog.addButton('ok', 'OK', 'o'.charAt(0), true)
dialog.addButton("cancel", "cancel", 'c'.charAt(0), false)
dialog.pack()
dialog.setVisible(true)

button = dialog.getButtonAnswer()
if(button == "cancel" || button == null) {
	return
}

current = jfd.getModel().getCurrentDirectory()
fileNames = extract(dialog.getTextFieldAnswer("file"))

command.showProgress()
children = []

for(fileName in fileNames) {
	try {
		file = vfs.getFile(fileName)

		if(file != null) {
			fileSystem = file.getFileSystem()
			fileSystem.registerUser(command)

			copyCommand = FileUtil.prepareCopyTo(file, 
				current.getChild(file.getName()), DefaultOverwritePolicy.OVERWRITE,  command)
			copyCommand.prepare()
			copyCommand.execute()
		}
	} catch (Exception e) {
		e.printStackTrace()
	}
}

command.setChildManipulations((Manipulation[])children.toArray(new Manipulation[0]))

for(manipulation in children) {
	try {
			command.setCurrentManipulation(manipulation)
			manipulation.start()
	} catch (Exception e) {
		e.printStackTrace()
	}
}

} catch (Exception e) {
	e.printStackTrace()
} finally {
	dialog.dispose()
	if(fileSystem != null) {
		fileSystem.removeUser(command)
	}
}

def extract(file) {
	startPos = file.indexOf("[")
	endPos = file.indexOf("]", startPos)
	minusPos = file.indexOf('-', startPos)

	if(startPos == -1) {
		return [file]
	}
	pre = file.substring(0, startPos)
	suf = file.substring(endPos + 1)

	rtn = []

	startStr = file.substring(startPos + 1, minusPos)
	endStr = file.substring(minusPos + 1, endPos)

	fills = startStr.length() == endStr.length()
	length = startStr.length()
	
	startNum = Integer.parseInt(startStr)
	endNum = Integer.parseInt(endStr)
	range = startNum..endNum

	for(i in range) {
		toAdd = Integer.toString(i)
		while(fills && toAdd.length() < length) {
			toAdd = "0" + toAdd
		}

		rtn.add(pre + toAdd + suf)
	}

	return rtn
}