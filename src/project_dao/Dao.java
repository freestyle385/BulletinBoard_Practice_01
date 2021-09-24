package project_dao;

public class Dao {
	protected int lastId;

	Dao() {
		lastId = 0;
	}

	public int getNewId() {
		return lastId + 1;
	}

}
