package com.sist.dao;
import java.util.*;
/*
 *   1. VO  ===> 데이터베이스 (ROW)
 *         EmpVO
 *      DTO ===> 웹에서 데이터를 브라우저로 전송 
 *         EmpDTO
 *      ----------------------------------- 컬럼에 없는 데이터를 첨부
 *      Entity => Table 구성  ==> JPA
 *      ------- JOIN
 *         EmpEntity
 */
public class Emp {
    private int empno;
    private String ename;
    private String job;
    private int mgr;
    private Date hiredate;//TO_CHAR로 변환 
    private int sal;
    private int comm;
    private int deptno;
    private String dbday;
    
    public String getDbday() {
		return dbday;
	}
	public void setDbday(String dbday) {
		this.dbday = dbday;
	}
	// JOIN => 자바 포함 클래스 
	private Dept dvo=new Dept();
    
	public Dept getDvo() {
		return dvo;
	}
	public void setDvo(Dept dvo) {
		this.dvo = dvo;
	}
	
	private SalGrade svo=new SalGrade();
	
	public SalGrade getSvo() {
		return svo;
	}
	public void setSvo(SalGrade svo) {
		this.svo = svo;
	}
	public int getEmpno() {
		return empno;
	}
	public void setEmpno(int empno) {
		this.empno = empno;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public int getMgr() {
		return mgr;
	}
	public void setMgr(int mgr) {
		this.mgr = mgr;
	}
	public Date getHiredate() {
		return hiredate;
	}
	public void setHiredate(Date hiredate) {
		this.hiredate = hiredate;
	}
	public int getSal() {
		return sal;
	}
	public void setSal(int sal) {
		this.sal = sal;
	}
	public int getComm() {
		return comm;
	}
	public void setComm(int comm) {
		this.comm = comm;
	}
	public int getDeptno() {
		return deptno;
	}
	public void setDeptno(int deptno) {
		this.deptno = deptno;
	}
   
}
