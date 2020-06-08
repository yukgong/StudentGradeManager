package dto;

public class Student {
	private int number;
	private String name;
	private String age;
	private String eng;
	private String math;

	public Student() {

	}

	public Student(int number, String name, String age, String eng, String math) {
		super();
		this.number = number;
		this.name = name;
		this.age = age;
		this.eng = eng;
		this.math = math;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getEng() {
		return eng;
	}

	public void setEng(String eng) {
		this.eng = eng;
	}

	public String getMath() {
		return math;
	}

	public void setMath(String math) {
		this.math = math;
	}

	@Override
	public String toString() {
		return number + "-" + name + "-" + age + "-" + eng + "-" + math;
	}
}
