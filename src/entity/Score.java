package entity;

public class Score {
	private int id;
	private Student student;
	private Subject subject;
	private int score;

	public Score() {
		super();
	}

	public Score(int id, Student student, Subject subject, int score) {
		super();
		this.id = id;
		this.student = student;
		this.subject = subject;
		this.score = score;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return "Score [id=" + id + ", student=" + student + ", subject="
				+ subject + ", score=" + score + "]";
	}

}
