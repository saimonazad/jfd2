import com.nullfish.lib.vfs.FileUtil
import com.nullfish.lib.vfs.impl.DefaultOverwritePolicy

def fileSystem = null
def dialog = null
try{
def current = jfd.getModel().getCurrentDirectory()

dialog = jfd.createDialog()
dialog.addMessage('ファイル名を入力してください')
dialog.addTextField("file", "", true)
dialog.addButton('ok', 'OK', 'o'.charAt(0), true)
dialog.addButton("cancel", "cancel", 'c'.charAt(0), false)
dialog.pack()
dialog.setVisible(true)

def button = dialog.getButtonAnswer()
if(button == "cancel" || button == null) {
	return
}

def file = vfs.getFile(dialog.getTextFieldAnswer("file"))
println(file)
fileSystem = file.getFileSystem()
fileSystem.registerUser(command)

command.showProgress()

def getContent = FileUtil.prepareGetContent(file, command)
command.setCurrentManipulation(getContent)
getContent.start();

def text = new String(getContent.getContent())

def tagMatcher = text =~ /<[^\"'<>]*(?:\"[^\"]*\"[^\"'<>]*|'[^']*'[^\"'<>]*)*(?:>|(?=<)|$(?!\n))/

while(tagMatcher.find()) {
	def found = tagMatcher.group()

		def tagName = getTagName(found)

//println found
//println tagName
	if("a".equals(tagName.toLowerCase())) {
		def hrefValue = getAttr(found, "href")
		
		if(hrefValue.toLowerCase().endsWith(".jpg")
			|| hrefValue.toLowerCase().endsWith(".jpeg")
			|| hrefValue.toLowerCase().endsWith(".gif")
			|| hrefValue.toLowerCase().endsWith(".png")
			|| hrefValue.toLowerCase().endsWith(".mpg")
			|| hrefValue.toLowerCase().endsWith(".mpeg")
			|| hrefValue.toLowerCase().endsWith(".mov")
			|| hrefValue.toLowerCase().endsWith(".wmv")
			|| hrefValue.toLowerCase().endsWith(".avi")
			|| hrefValue.toLowerCase().endsWith(".wma")
			|| hrefValue.toLowerCase().endsWith(".mp3")) {
			def linkedFile = null;
			try {
				linkedFile = vfs.getFile(hrefValue);
			} catch (Exception e) {}

			if(linkedFile == null) {
				try {linkedFile = file.getRelativeFile(hrefValue)} catch (Exception e){
					e.printStackTrace()
				}
			}

			try {
				if(linkedFile != null) {
					linkedFile.copyTo(current.getChild(linkedFile.getName()), DefaultOverwritePolicy.OVERWRITE,  command)
				}
			} catch (Exception e) {
				e.printStackTrace()
			}
		}
	}
}

} catch (Exception e) {
	e.printStackTrace()
} finally {
	dialog.dispose()
}

//	タグ名取得
def getTagName(tag) {
	def m1 = tag =~ /[^<>\s]+/
	if(!m1.find()) {
		return ""
	}

	return m1.group()
}

//	属性取得
def getAttr(tag, attrName) {
	def m1 = tag =~ /[A-Za-z0-9_-]+\s*=\s*\"?[^\"]*?\"?(?=[\s>])/

	while(m1.find()) {
		def found = m1.group()
		def nameMatcher = found =~ /[A-Za-z0-9_-]+/

		if(!nameMatcher.find()) {
			return ""
		}

		def name = nameMatcher.group()

		if(attrName.toLowerCase().equals(name.toLowerCase())) {
			def value = found.substring(found.indexOf("=") + 1).trim()

			if(value.charAt(0) == "\"" && value.charAt(value.length() - 1) == "\"") {
				value = value.substring(1, value.length() - 1)
			}

			return value;
		}
	}

	return ""
}

