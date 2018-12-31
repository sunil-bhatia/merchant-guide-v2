package com.thoughtworks.merchant.lines;

public abstract class AssignmentLine extends GenericLine {

	public AssignmentLine() {
		super();
	}

	protected String processValidData() {
		String outputLine = "";

		calculateAssignedData();
		addAssignedData();

		return outputLine;
	}
	
	protected abstract void calculateAssignedData();

	protected abstract void addAssignedData();
}
