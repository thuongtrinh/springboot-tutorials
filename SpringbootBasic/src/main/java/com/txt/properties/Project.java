package com.txt.properties;

public class Project {

	private String projectName;
	private int size;
	private String manager;

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public String toString() {
		return "Project:" + projectName + " - " + size + " - " + manager;
	}
}
