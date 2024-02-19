package com.mystudy.student.dao_answer;

import com.mystudy.student.vo.StudentVO;

public class StudentDAO_Test {

	public static void main(String[] args) {
		StudentDAO dao = new StudentDAO();
		StudentVO vo = dao.selectOne("2024001");
		System.out.println("vo : " + vo);
		System.out.println(vo.getId() + ", " + vo.getName() + ", " + vo.getKor());
/*	
		System.out.println("--- selectOne(vo) --------");
		StudentVO vo2 = new StudentVO("2024002", "");
		vo = dao.selectOne(vo2);
		System.out.println("vo : " + vo);
		
		//----- selectAll() 호출후 받은 리스트 데이터 화면 출력 --------
		System.out.println("--- selectAll() -------");
		List<StudentVO> list = dao.selectAll();
		System.out.println("list : " + list);
		
		for (int i = 0; i < list.size(); i++) {
			//System.out.println(list.get(i));
			StudentVO student = list.get(i);
			System.out.println(student.getId() + ", " +
					student.getName() + ", " +
					student.getKor() + ", " +
					student.getEng() + ", " +
					student.getMath() + ", " +
					student.getTot() + ", " +
					student.getAvg());
		}
		System.out.println("------");
		
		for (StudentVO student : list) {
			System.out.println(student.getId() + ", " +
					student.getName() + ", " +
					student.getTot() + ", " +
					student.getAvg());
		}
		System.out.println("========================");
		System.out.println("---- insert(vo) --------");
		StudentVO insertVo = new StudentVO("2024112", "테스트112", 100, 90, 82);
		System.out.println("insertVo : " + insertVo);
		int result = dao.insert(insertVo);
		System.out.println("처리건수(result) : " + result);
		
		vo = dao.selectOne(insertVo);
		System.out.println("DB vo : " + vo);
*/
	}

}







