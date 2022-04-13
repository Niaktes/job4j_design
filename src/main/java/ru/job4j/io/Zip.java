package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {
	
	public void packFiles(List<File> sources, File target) {
		try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
			for (File source : sources) {
				zip.putNextEntry(new ZipEntry(source.getPath()));
				try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
					zip.write(out.readAllBytes());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void packSingleFile(File source, File target) {
		try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
			zip.putNextEntry(new ZipEntry(source.getPath()));
			try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
				zip.write(out.readAllBytes());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void argumentsValidation(Path directory, String exclude, File archive) {
		if (directory == null || exclude == null || archive == null) {
			throw new IllegalArgumentException("Some argument is missing. Please use next pattern: \"-d=directory -e=extension -o=output\".");
		}
		if (!directory.toFile().exists() || !directory.toFile().isDirectory()) {
			throw new IllegalArgumentException("Invalid argument! -d - is not a directory.");
		}
	}
	
	private static List<File> findAppropriateFiles(Path directory, String exclude) {
		List<File> sources = new ArrayList<>();
		try {
			sources = Search.search(directory, p -> !p.toFile().getName().endsWith(exclude)).stream()
					.map(Path::toFile)
					.toList();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sources;
	}

	public static void main(String[] args) {
		ArgsName arguments = ArgsName.of(args);
		Path directory = Path.of(arguments.get("d"));
		String exclude = arguments.get("e");
		File archive = new File(arguments.get("o"));
		argumentsValidation(directory, exclude, archive);
		Zip zip = new Zip();
		List<File> sources = findAppropriateFiles(directory, exclude);
		zip.packFiles(sources, archive);
		zip.packSingleFile(new File("./pom.xml"), new File("./pom.zip"));
	}

}
