package fr.trxyy.alternative.bootstrap;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import fr.trxyy.alternative.bootstrap.util.Logger;

public class Downloader extends Thread {
	public JFrame frame;
	private int percentage;

	public Downloader(JFrame fra) {
		this.frame = fra;
	}

	public void run() {
		this.checkLocalMD5();
		this.launch();
	}

	public void update() {
		Logger.log("GET >>  " + BootstrapConstants.getLauncherFile().getAbsolutePath());
		Logger.log("FROM >> " + BootstrapConstants.getLauncherUrl());
		try {
			URL url = new URL(BootstrapConstants.getLauncherUrl().replace(" ", "%20"));
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.addRequestProperty("User-Agent",
					"Mozilla/5.0 AppleWebKit/537.36 (KHTML, like Gecko) Chrome/43.0.2357.124 Safari/537.36");
			if (!BootstrapConstants.getLauncherFile().exists()) {
				BootstrapConstants.getLauncherFile().getParentFile().mkdirs();
				BootstrapConstants.getLauncherFile().createNewFile();
			}
			BufferedInputStream in = new BufferedInputStream(connection.getInputStream());
			FileOutputStream fos = new FileOutputStream(BootstrapConstants.getLauncherFile());
			BufferedOutputStream bout = new BufferedOutputStream(fos, 1024);
			byte[] data = new byte[1024];
			int read = 0;

			long totalRead = 0;
			long fileSize = connection.getContentLength();

			while ((read = in.read(data, 0, 1024)) >= 0) {
				bout.write(data, 0, read);
				totalRead += read;
				percentage = (int) (totalRead * 100 / fileSize);
				BootPanel.getProgressBar().setValue(percentage);
				frame.repaint();

			}
			bout.close();
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void launch() {
		ProcessBuilder pb = new ProcessBuilder(new String[] { "java", "-jar", BootstrapConstants.getLauncherFile().getAbsolutePath() });
		try {
			pb.start();
			exitProperly();
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage());
			exitProperly();
		}
	}

	private static void exitProperly() {
		(new Timer()).schedule(new TimerTask() {
			public void run() {
				System.exit(0);
			}
		}, 1500L);
	}

	private void checkLocalMD5() {
		if (!BootstrapConstants.getLauncherFile().exists()) {
			System.out.println("Le fichier n'existe pas, telechargement..");
			update();
		} else {
			String localMD5 = getMD5(BootstrapConstants.getLauncherFile());
			String hostMD5 = readMD5();
			if (!localMD5.equals(hostMD5)) {
				System.out.println("Le fichier n'est pas valide, telechargement..");
				update();
			}
		}
	}

	private String readMD5() {
		String result = "";
		try {
			Scanner scan = new Scanner((new URL(BootstrapConstants.getHashUrl())).openStream(), "UTF-8");
			if (!scan.hasNextLine()) {
				scan.close();
			}
			result = scan.nextLine();
			scan.close();
			return result;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	public String getMD5(final File file) {
		DigestInputStream stream = null;
		try {
			stream = new DigestInputStream(new FileInputStream(file), MessageDigest.getInstance("MD5"));
			final byte[] buffer = new byte[65536];

			int read = stream.read(buffer);
			while (read >= 1)
				read = stream.read(buffer);
		} catch (final Exception ignored) {
			return null;
		} finally {
			closeSilently(stream);
		}

		return String.format("%1$032x", new Object[] { new BigInteger(1, stream.getMessageDigest().digest()) });
	}

	public void closeSilently(Closeable closeable) {
		if (closeable != null) {
			try {
				closeable.close();
			} catch (IOException localIOException) {
			}
		}
	}

	public int getProgress() {
		return percentage;
	}
}
