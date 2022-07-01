package com.sist.main;
import java.util.*;
import com.sist.dao.*;
public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        EmpDeptDAO dao=EmpDeptDAO.newInstance();
        
        System.out.println("===== JOIN =====");
        List<EmpDeptVO> list=dao.empDeptListData1();
        for(EmpDeptVO vo:list)
        {
        	System.out.println(vo.getEname()+" "
        			+vo.getDname()+" "
        			+vo.getJob()+" "
        			+vo.getLoc()+" "
        			+vo.getGrade());
        	
        }
        
        System.out.println("===== View(복합뷰) =====");
        list=dao.empDeptListData2();
        for(EmpDeptVO vo:list)
        {
        	System.out.println(vo.getEname()+" "
        			+vo.getDname()+" "
        			+vo.getJob()+" "
        			+vo.getLoc()+" "
        			+vo.getGrade());
        	
        }
        
        System.out.println("===== 스칼라 서브쿼리 =====");
        list=dao.empDeptListData3();
        for(EmpDeptVO vo:list)
        {
        	System.out.println(vo.getEname()+" "
        			+vo.getDname()+" "
        			+vo.getJob()+" "
        			+vo.getLoc()+" "
        			+vo.getGrade());
        	
        }
	}

}
