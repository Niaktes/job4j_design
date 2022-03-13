package ru.job4j.generics;

public class Role extends Base {
	
	private final String title;

	public Role(String id, String title) {
		super(id);
		this.title = title;
	}
	
	public String getTitle() {
		return title;
	}

}
