package com.neb.nebotools.controller.enumeration;

public enum State {

	ADD(1,"state.add"), DETAIL(2,"state.detail"), MANAGE(2,"state.manage"), MODIFY(3,"state.modify"),
	MORE(4,"state.more"), OVERVIEW(5,"state.overview"), NONE(6,"state.none");

	int id;
	String name;
	State(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public String getName() {
		return name;
	}
	public int getId() {
		return id;
	}
}
