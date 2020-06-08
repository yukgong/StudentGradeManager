package fileClass;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FileDataClass {
	private File file;
	
	public FileDataClass() {
		file = new File("d:/tmp/studentMember.txt");
	}
	
	public void createFile(){
		try {
			if(file.createNewFile()) {
				System.out.println("파일이 생성 되었습니다.");
			}else {
				System.out.println("파일이 생성 되지 않았습니다.");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String[] loadData() throws Exception {
		String datas[] = null;
		// data의 갯수를 조사 -> 배열 ... 하지만 list를 사용하면, 이처리가 필요없다.

		BufferedReader br = new BufferedReader(new FileReader(file));
		int count = 0;
		String str = "";

		while ((str = br.readLine()) != null) {
			count++;
		}
		br.close();

		// datas를 할당
		datas = new String[count];
		System.out.println("datas.length = " + datas.length);
		// 개발을 잘하는 방법은 확인을 많이 하는 것이다.

		// 배열 저장
		int w = 0;
		br = new BufferedReader(new FileReader(file));
		while ((str = br.readLine()) != null) {
			datas[w] = str;
			w++;
		}
		br.close();
		return datas;
	}
	
	public void saveData(String[] datas) throws Exception {
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file)));

		for (int i = 0; i < datas.length; i++) {
			pw.println(datas[i]);
		}
		pw.close();
	}
	
}
