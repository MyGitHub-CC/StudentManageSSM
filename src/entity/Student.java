package entity;

public class Student {
	private int id;
	private String name;
	private int age;
	private String sex;
	private BanJi banJi;
	private String photo;

	public Student() {
		super();
	}

	public Student(int id, String name, int age, String sex, BanJi banJi,
			String photo) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.sex = sex;
		this.banJi = banJi;
		this.photo = photo;
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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public BanJi getBanJi() {
		return banJi;
	}

	public void setBanJi(BanJi banJi) {
		this.banJi = banJi;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", age=" + age
				+ ", sex=" + sex + ", banJi=" + banJi + ", photo=" + photo
				+ "]";
	}

}
