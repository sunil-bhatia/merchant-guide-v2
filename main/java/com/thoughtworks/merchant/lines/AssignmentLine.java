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
	
	protected boolean isDataValid() {
		return isGalacticNumValid();
	}
	
	protected void calculateAssignedData() {
		// Empty method - Will be implemented by derived classes, only if required
	}

	protected abstract void addAssignedData();
}
