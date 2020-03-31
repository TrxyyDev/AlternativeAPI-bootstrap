package fr.trxyy.alternative.bootstrap.util;

import java.io.File;

public class GameUtils {

	public static File getWorkingDirectory(String workDir) {
		String userHome = System.getProperty("user.home", ".");
		File workingDirectory;
		switch (getPlatform()) {
		case 1:
			workingDirectory = new File(userHome + "/." + workDir);
		case 2:
			workingDirectory = new File(userHome + "/." + workDir);
			break;
		case 3:
			workingDirectory = new File(userHome + "\\AppData\\Roaming\\." + workDir);
			break;
		case 4:
			workingDirectory = new File(userHome + "/Library/Application Support/" + workDir);
			break;
		default:
			workingDirectory = new File(userHome + "/." + workDir);
		}
		return workingDirectory;
	}

	private static int getPlatform() {
		String osName = System.getProperty("os.name").toLowerCase();
		if (osName.contains("linux"))
			return 1;
		if (osName.contains("unix"))
			return 1;
		if (osName.contains("solaris"))
			return 2;
		if (osName.contains("sunos"))
			return 2;
		if (osName.contains("win"))
			return 3;
		if (osName.contains("mac"))
			return 4;
		return 5;
	}
}
