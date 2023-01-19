package miniproject1;

public class Student {

	private int rollno;
	private String name;
	private int score;

	public Student(int rollno, String name, int score) {
		super();
		this.rollno = rollno;
		this.name = name;
		this.score = score;
	}

	public int getRollno() {
		return rollno;
	}

	public void setRollno(int rollno) {
		this.rollno = rollno;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return "Student [rollno=" + rollno + ", name=" + name + ", score=" + score + "]";
	}

}
