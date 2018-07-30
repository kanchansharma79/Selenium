package testclasses;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class WindowsAwt {

	Robot rob;
	static Toolkit tool;
	
	public void WindowControlActions() throws IOException {
		StringSelection ss =new StringSelection("Some file path");
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
		rob.keyPress(KeyEvent.VK_CONTROL);
		rob.keyPress(KeyEvent.VK_V);
		
		Runtime.getRuntime().exec("abc.exe");
	}
	
}
