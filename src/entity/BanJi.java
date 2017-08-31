package entity;

import java.util.List;

public class BanJi {
	private int id;
	private String name;
	private List<Subject> subList;

	public BanJi() {
		super();
	}

	public BanJi(int id, String name, List<Subject> subList) {
		super();
		this.id = id;
		this.name = name;
		this.subList = subList;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Subject> getSubList() {
		return subList;
	}

	public void setSubList(List<Subject> subList) {
		this.subList = subList;
	}

	@Override
	public String toString() {
		return "BanJi [id=" + id + ", name=" + name + ", subList=" + subList
				+ "]";
	}

}
