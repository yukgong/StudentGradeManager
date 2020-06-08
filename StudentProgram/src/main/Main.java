package main;

import java.util.Scanner;

import dao.StudentDao;

public class Main {

	public static void main(String[] args) throws Exception {
		/*
		 * 학생 관리 프로그램 객체 지향 + ArrayList로 업그레이드하기 DTO, DAO, File, Main
		 */
		Scanner scanner = new Scanner(System.in);
		int choice;
		StudentDao student = new StudentDao();

		out: while (true) {

			// 파일과 동기화
//			fileLoad(student);

			// 선택할 메뉴 보여주기
			System.out.println("------메뉴------");
			System.out.println("1. 학생 정보 추가");
			System.out.println("2. 학생 정보 삭제 ");
			System.out.println("3. 학생 정보 검색");
			System.out.println("4. 학생 정보 수정");
			System.out.println("5. 학생 정보 모두 출력");
			System.out.println("6. 과목의 총점");
			System.out.println("7. 과목의 평균");
			System.out.println("8. 성적순으로 정렬 출력");
			System.out.println("9. 저장");
			System.out.println("10. 종료");
			System.out.println("--------------\n");
			System.out.println("메뉴 번호를 입력해주세요");
			System.out.print(">>>");
			choice = scanner.nextInt();

			if (choice > 9) {
				System.out.println("다시 입력해주세요.");
				continue;
			}

			switch (choice) {
			case 1: // 학생 추가
				student.insert();
				break;
				
			case 2: // 학생 삭제
				student.delete();
				break;
				
			case 3:
				student.select();
				break;
				
			case 4:
				student.update();
				break;
				
			case 5:
				student.allPrint();
				break;
				
			case 6:
				student.sumSubject();
				break;

			case 7:
				student.avgSubject();
				break;

			case 8:
				student.sorting();
				break;

			case 9:
				student.saveFile();
				break;
				
			case 10:
				break out;
			}
		}
	}
}
