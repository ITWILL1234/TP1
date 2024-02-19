package com.mystudy.student.dao;

import java.util.ArrayList;

import com.mystudy.student.vo.StudentVO;

public class StudentDAO_Test {

	public static void main(String[] args) {
		StudentDAO dao = new StudentDAO();
		StudentVO vo = dao.selectOne("2024001");
		System.out.println("vo : " + vo);
		System.out.println(vo.getId() + ", " + vo.getName() + ", " + vo.getKor());
		
		StudentVO vo2 = new StudentVO("2024002", "");
		vo2 = dao.selectOne(vo2);
		System.out.println("vo2 : " + vo2);
		
		ArrayList<StudentVO> arr = new ArrayList<StudentVO>();
		
		arr.add(vo);
		arr.add(vo2);
		
		System.out.println(arr);
		
		arr = dao.selectAllVO(arr);
		
		System.out.println(arr);
		
		ArrayList<String> arr2 = new ArrayList<String>();
		arr2.add("2024001");
		arr2.add("2024002");
		
		ArrayList<StudentVO> arr3 = new ArrayList<StudentVO>();
		
		arr3 = dao.selectAllID(arr2);
		
		System.out.println(arr3);
		
		StudentVO vo3 = new StudentVO("2024003", "김김김", 50, 70, 90);
		
		//dao.insertOne(vo3);
		
		//dao.UpdateAll(new StudentVO("2024003", "박박박", 80, 80, 80));
		
		dao.UpdateKor("2024003", 30);
		dao.UpdateEng("2024003", 50);
		dao.UpdateMath("2024003", 70);
		
		System.out.println(dao.selectOne("2024003"));
		
	}

}
