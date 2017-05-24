package com.my.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.my.entity.Students;
import com.my.util.HibernateSessionFactory;
/**
 * ����ѧ����ʵ����
 * ʵ��ѧ������ɾ�Ĳ�
 * @author yun
 *
 */
public class StudentsDaoImpl implements StudentsDao {

	private Session session;
	
	//���һ����¼��ѧ������ȥ
	@Override
	public boolean addStudent(Students student) {
		
		String sid = getNewSid();
		if(sid == null) {
			return false;
		}
		student.setSid(sid);
		
		Transaction transaction = null;
		try {
			this.session = HibernateSessionFactory.getSessionFactory().getCurrentSession();

			transaction = session.getTransaction();
			transaction.begin();
			
			session.save(student);
			
			transaction.commit();
			return true;
		} catch (Exception e) {
			System.out.println("����ѧ�����ݵ����ݿ�ʧ��");
			e.printStackTrace();
			if(transaction != null){
				transaction = null;
			}
			return false;
		}finally{
			if(transaction != null){
				transaction = null;
			}
		}	
	}
	
	//����һ��ѧ����¼
	@Override
	public boolean updateStudent(Students student) {
		// TODO Auto-generated method stub
		this.session = HibernateSessionFactory.getSessionFactory().getCurrentSession();

		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.update(student);
			
			transaction.commit();
		} catch (Exception e) {
			System.out.println("����ѧ�����ݵ����ݿ�ʧ��");
			e.printStackTrace();
			if(transaction != null){
				transaction = null;
			}
			return false;
		}
		if(transaction != null){
			transaction = null;
		}
		return true;	
	}
	
	//��students����ͨ��sidɾ��һ����¼
	@Override
	public boolean deleteStudent(String sid) {
		// TODO Auto-generated method stub
		Transaction transaction = null;
		try {
		this.session = HibernateSessionFactory.getSessionFactory().getCurrentSession();
		
		//Students student = new Students();  //my	
		
			transaction = session.getTransaction();
			transaction.begin();
			//get�����Ǹ���id��ȡ��һ����¼,ע����뿪�������������
			Students student = (Students) this.session.get(Students.class, sid);
			
			session.delete(student);
			
			transaction.commit();
		} catch (Exception e) {
			System.out.println("�����ݿ�ɾ��ѧ������ʧ��");
			e.printStackTrace();
			if(transaction != null){
				transaction = null;
			}
			return false;
		}
		if(transaction != null){
			transaction = null;
		}
		return true;	
	}
	
	//��ѯ��Students�������м�¼
	@Override
	public List<Students> queryStudents() {
		// TODO Auto-generated method stub
		this.session = HibernateSessionFactory.getSessionFactory().getCurrentSession();
		 Transaction tx = null;
		 List<Students> list = null;
			String hql = "";
			try {
				
				//tx = session.beginTransaction();
			
				//������ע��hql����в�ѯ�����Ǳ���Ǿ����ӳ���ʵ����
				tx = this.session.getTransaction();
				tx.begin();
				hql = "from Students ";
				Query query = this.session.createQuery(hql);	
				list = query.list();
				
				tx.commit();
				if(list !=null && list.size() > 0)	{
					return list;
				} else {
					return null;
				}				
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}finally{
				if(tx != null) {
					tx = null;	
				}	
			}
			
		
	}
	
	//��students���л�ȡ��һ����¼
	@Override
	public Students getOneStudent(String sid) {
		Transaction tx = null;
		Students student = null;
		try {
			this.session = HibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx = this.session.getTransaction();
			tx.begin();
			//ͨ��������ȡ��һ����¼
			student = (Students) this.session.get(Students.class, sid);
				
			tx.commit();
			return student;				
		} catch (Exception e) {
			e.printStackTrace();
			return student;
		}finally{
			if(tx != null) {
				tx = null;	
			}	
		}
	}
	
	
	//��ȡsid��ֵ��ʽΪSxxxxxxx
	private String getNewSid() {	
		 Transaction tx = null;
			String hql = "";
			String sid = null;
			try {
				this.session = HibernateSessionFactory.getSessionFactory().getCurrentSession();
				//������ע��hql����в�ѯ�����Ǳ���Ǿ����ӳ���ʵ����
				tx = this.session.getTransaction();
				tx.begin();
				hql = "select max(sid) from Students";
				Query query = this.session.createQuery(hql);	
				//�÷���Ϊ��ȡ��һ�����
				sid = (String) query.uniqueResult();
				if(sid == null || "".equals(sid)) {
					sid = "S0000001";
				} else {
					String tmp = sid.substring(1);//�����1��ʼȡ�ַ���
					int index = Integer.parseInt(tmp)+1;
					tmp = String.valueOf(index);
					int len = tmp.length();
					for(int i = len; i< 7; i++) {
						tmp = '0' + tmp; 
					}
					sid = 'S' + tmp;
				}
					
				tx.commit();
				return sid;				
			} catch (Exception e) {
				e.printStackTrace();
				return sid;
			}finally{
				if(tx != null) {
					tx = null;	
				}	
			}
	}
}
