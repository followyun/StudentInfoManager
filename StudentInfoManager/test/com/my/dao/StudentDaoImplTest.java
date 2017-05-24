package com.my.dao;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import com.my.entity.Students;

/**
 * ����StudentDaoImpl��
 * 
 * @author yun
 *
 */
public class StudentDaoImplTest {
	@Test
	public void testAddStudent() {
		StudentsDao studentsDao = new StudentsDaoImpl();
		@SuppressWarnings("deprecation")
		//���java���Ǵ�1900�꿪ʼ���㵽���ڵ�����
		Date date = new Date(2015 - 1900, 6 - 1, 24);
		
		Students student = new Students("S0000001", "������", "��", date, "�䵱ɽ");
		
		assertEquals(true, studentsDao.addStudent(student));
	}
	
	//���Ը���ѧ�����ݷ���
	@Test
	public void testUpdateStudent() {
		StudentsDao studentsDao = new StudentsDaoImpl();
		@SuppressWarnings("deprecation")
		Date date = new Date(2015 - 1900, 6 - 1, 24);
		
		Students student = new Students("S0000001", "������", "��", date, "�䵱ɽ");
		
		assertEquals(true, studentsDao.updateStudent(student));
	}
	
	//����ɾ��ѧ�����ݷ���
	@Test
	public void testDeleteStudent() {
		StudentsDao studentsDao = new StudentsDaoImpl();
		@SuppressWarnings("deprecation")
		Date date = new Date(2015 - 1900, 6 - 1, 24);
		
		Students student = new Students("S0000001", "������", "��", date, "�䵱ɽ");
		
		assertEquals(true, studentsDao.deleteStudent(student.getSid()));
	}
	
	@Test
	public void testDeleteStudent1() {
		StudentsDao studentsDao = new StudentsDaoImpl();
	
		Students student = new Students();
		student.setSid("S0000001");
		
		assertEquals(true, studentsDao.deleteStudent(student.getSid()));
	}
	
}
