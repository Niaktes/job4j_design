package ru.job4j.io.duplicates;

import java.util.Objects;

public class FileProperty {

	private long size;
	private String name;
	
	public FileProperty(long size, String name) {
		this.size = size;
		this.name = name;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		FileProperty other = (FileProperty) obj;
		return size == other.size && Objects.equals(name, other.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(size, name);
	}
	
}
