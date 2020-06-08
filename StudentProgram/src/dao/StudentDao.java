package dao;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import dto.Student;
import fileClass.FileDataClass;

public class StudentDao {
	ArrayList<Student> student = new ArrayList<>();
	private int studentNum;
	FileDataClass fileProcess;

	public StudentDao() throws Exception {
		studentNum = 1;
		fileProcess = new FileDataClass();
		fileProcess.createFile();
		loadFile();
	}

	public void insert() {
		int number = studentNum;
		String name = input("이름 : ");
		String age = input("나이 : ");
		String eng = input("영어 : ");
		String math = input("수학 : ");

		// Student 멤버 변수 값에 대입해서 인스턴스화하기
		Student s = new Student(number, name, age, eng, math);
		// student 배열에 s값을 담아 배열 추가
		student.add(s);

		System.out.println((student.get(number - 1)).getName() + " 학생의 정보가 추가되었습니다." + "\n");
		studentNum++;
	}

	public void delete() {
		String name = input("삭제할 이름 : ");

		int findIndex = search(name);
		if (findIndex == -1) {
			System.out.println("학생 목록에 없습니다.");
		} else {
			student.remove(findIndex);
			System.out.println("삭제 되었습니다.");
		}
	}

	public void select() {
		String name = input("검색할 이름 : ");

		int findIndex = search(name);
		if (findIndex == -1) {
			System.out.println("학생 목록에 없습니다.");
		} else {
			Student s = student.get(findIndex);
			System.out.println("번호\t이름\t나이\t영어\t수학\t");
			System.out.println("------------------------------------");
			System.out.print(s.getNumber() + "\t");
			System.out.print(s.getName() + "\t");
			System.out.print(s.getAge() + "\t");
			System.out.print(s.getEng() + "\t");
			System.out.print(s.getMath() + "\t\n");
			System.out.println();
		}
	}

	public void update() {
		String name = input("수정할 이름 : ");

		int findIndex = search(name);
		if (findIndex == -1) {
			System.out.println("학생 목록에 없습니다.");
		} else {
			Student s = student.get(findIndex);
			String eng = input("영어 : ");
			String math = input("수학 : ");

			s.setEng(eng);
			s.setMath(math);
		}
	}

	public void allPrint() {
		for (Student s : student) {
			System.out.println(s.toString());
		}
	}

	public void sumSubject() {
		String choice = input("총점을 확인할 과목을 선택해 주세요 (1. 영어, 2. 수학)");
		int n = Integer.parseInt(choice);

		// 특정 과목 성적 합산하기 : 합산 함수를 호출해 n번 과목의 성적 합산하기
		int sumResult = sum(n);

		// 선택한 번호에 따라 수학, 영어 과목인지를 파악해 결과를 출력한다.
		if (n == 1)
			System.out.println("영어 과목의 총점 :" + sumResult + "\n");
		else if (n == 2)
			System.out.println("수학 과목의 총점 :" + sumResult + "\n");
	}

	public void avgSubject() {
		double avg = 0;
		String choice = input("평균을 확인할 과목을 선택해 주세요 (1. 영어, 2. 수학)");
		int n = Integer.parseInt(choice);

		int sumResult = sum(n);
		// 평균값을 구한다.
		avg = sumResult / student.size();

		// 선택한 번호에 따라 수학, 영어 과목인지를 파악해 결과를 출력한다.
		if (n == 1)
			System.out.println("영어 과목의 총점 :" + avg + "\n");
		else if (n == 2)
			System.out.println("수학 과목의 총점 :" + avg + "\n");
	}

	public void sorting() {
//		int gradeSum[] = new int[student.size()];
		ArrayList<Student> sortList = new ArrayList<Student>();

		for (int i = 0; i < student.size(); i++) {
			sortList.add(student.get(i));
		}

		//내림 차순 정렬
		Student temp = null;
		for (int i = 0; i < student.size() - 1; i++) {
			for (int j = i + 1; j < student.size(); j++) {
				Student s1 = sortList.get(i);
				Student s2 = sortList.get(j);
				int num1 = Integer.parseInt(s1.getEng() + s1.getMath());
				int num2 = Integer.parseInt(s2.getEng() + s2.getMath());
				if (num1 < num2) {
					temp = sortList.get(i);
					sortList.set(i, sortList.get(j));
					sortList.set(j, temp);
				}
			}
		}
		// 출력
		for (Student s : sortList) {
			System.out.println(s.toString());
		}
	}

	public void saveFile() throws Exception {
		String datas[] = new String[student.size()];

		// 배열에 학생 목록 담기
		for (int i = 0; i < datas.length; i++) {
			Student s = student.get(i);
			datas[i] = s.toString();
		}

		// 담아서 파일에 저장하기
		fileProcess.saveData(datas);
	}

	public void loadFile() throws Exception {
		// 파일 안에 있는 데이터를 담은 배열을 새로운 배열에 대입한다.
		String datas[] = fileProcess.loadData();

		// datas 배열은 현재 000-000-000-00 상태이므로 '-'토큰 단위로 잘라서 임시 배열에 넣어준다.
		// 잘라진 배열을 list에 추가한다.
		String str = "";
		for (int i = 0; i < datas.length; i++) {
			str = datas[i];
			String tempArr[] = str.split("-");
			student.add(new Student(Integer.parseInt(tempArr[0]), tempArr[1], tempArr[2], tempArr[3], tempArr[4]));
		}

	}

	// method for method ----------------------------
	public String input(String msg) {
		Scanner scanner = new Scanner(System.in);
		System.out.print(msg);
		String input = scanner.next();

		return input;
	}

	public int search(String name) {
		int findIndex = -1;

		for (int i = 0; i < student.size(); i++) {
			Student s = student.get(i);
			if ((s.getName()).equals(name)) {
				findIndex = i;
				break;
			}
		}
		return findIndex;
	}

	public int sum(int n) {
		int sum = 0;
		for (int i = 0; i < student.size(); i++) {
			Student s = student.get(i);
			if (n == 1) {
				String str = s.getEng();
				int num = Integer.parseInt(str); // 숫자로 바꿔주기
				sum += num;
			} else if (n == 2) {
				String str = s.getMath();
				int num = Integer.parseInt(str); // 숫자로 바꿔주기
				sum += num;
			}
		}
		return sum;
	}
}
