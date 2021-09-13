package project_controller;

import project_dto.Member;

public abstract class Controller {
	
	public static Member loginedMember;
	
	public abstract void doAction(String command, String actionMethodName);
	
	public abstract void makeTestData();
}
