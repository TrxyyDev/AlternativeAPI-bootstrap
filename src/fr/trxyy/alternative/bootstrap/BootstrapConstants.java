package fr.trxyy.alternative.bootstrap;

import java.awt.Color;
import java.awt.Paint;
import java.io.File;

import fr.trxyy.alternative.bootstrap.util.GameUtils;

public class BootstrapConstants {
	/** ========== DOSSIER D'INSTALLATION ========== **/
	public static File WORKING_DIRECTORY = GameUtils.getWorkingDirectory("customlauncher");
	/** ========== OU SE SITUERA LE LAUNCHER ========== **/
	public static File LAUNCHER = new File(WORKING_DIRECTORY, "Launcher.jar");
	/** ========== URL DU FICHIER launcher.cfg AVEC LE MD5 ========== **/
	public static String MD5_URL = "https://trxyy.chaun14.fr/datas/bootstrap/launcher.cfg";
	/** ========== URL DU LAUNCHER.JAR ========== **/
	public static String LAUNCHER_URL = "https://trxyy.chaun14.fr/datas/bootstrap/Launcher.jar";
	/** ========== COULEUR DU CERCLE REMPLIS ========== **/
	public static Paint color = Color.green;

	public static File getWorkingDirectory() {
		return WORKING_DIRECTORY;
	}

	public static File getLauncherFile() {
		return LAUNCHER;
	}

	public static String getHashUrl() {
		return MD5_URL;
	}

	public static String getLauncherUrl() {
		return LAUNCHER_URL;
	}

	public static Paint getFillColor() {
		return color ;
	}

}
