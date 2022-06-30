package com.sist.dao;
import java.util.*;
public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        EmpJoinDAO dao=EmpJoinDAO.newInstance();
        /*List<Emp> list=dao.empJoinData();
        for(Emp e:list)
        {
        	System.out.println(e.getEmpno()+" "
        			+e.getEname()+" "
        			+e.getJob()+" "
        			+e.getDbday()+" "
        			+e.getSal()+" "
        			+e.getDvo().getDname()+" "
        			+e.getDvo().getLoc());
        }*/
        /*List<Emp> list=dao.empNonEquiJoinData();
        for(Emp e:list)
        {
        	System.out.println(e.getEmpno()+" "
        			+e.getEname()+" "
        			+e.getJob()+" "
        			+e.getDbday()+" "
        			+e.getSal()+" "
        			+e.getDvo().getDname()+" "
        			+e.getDvo().getLoc()+" "
        			+e.getSvo().getGrade());
        }*/
        List<Emp> list=dao.empOuterJoin();
        for(Emp e:list)
        {
        	System.out.println(e.getEmpno()+" "
        			+e.getEname()+" "
        			+e.getJob()+" "
        			+e.getDvo().getDname()+" "
        			+e.getDvo().getLoc());
        }
        
	}

}
