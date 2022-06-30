package com.sist.dao;
import java.util.*;
import java.sql.*;
public class EmpJoinDAO {
    private Connection conn; 
    private PreparedStatement ps; //CallableStatement(함수)
    // ERP => CallableStatement(함수)
    private final String URL="jdbc:oracle:thin:@localhost:1521:XE";
    private static EmpJoinDAO dao;
    public EmpJoinDAO()
    {
    	try
    	{
    		Class.forName("oracle.jdbc.driver.OracleDriver");
    	}catch(Exception ex){}
    }
    // Singleton => 메모리 공간을 1개만 사용 => static
    public static EmpJoinDAO newInstance()
    {
    	if(dao==null)
    		dao=new EmpJoinDAO(); // 처음에 한번 수행 
    	return dao; // 기존에 생성된 것을 사용한다 
    }
    // thin 
    // JDBC => DBCP => ORM (MyBatis => JPA)
    // 연결 
    // DAO ==> null
    public void getConnection()
    {
    	try
    	{
    		conn=DriverManager.getConnection(URL,"hr","happy");
    	}catch(Exception ex) {}
    }
    // 해제 
    public void disConnection()
    {
    	try
    	{
    		if(ps!=null) ps.close();
    		if(conn!=null) conn.close();
    	}catch(Exception ex) {}
    }
    
    // 기능 
    public List<Emp> empJoinData()
    {
    	List<Emp> list=new ArrayList<Emp>();
    	try
    	{
    		getConnection();
    		/*String sql="SELECT empno,ename,job,TO_CHAR(hiredate,'YYYY-MM-DD') as dbday,"
    				  +"sal , dname, loc "
    				  +"FROM emp,dept "
    				  +"WHERE emp.deptno=dept.deptno";*/
    		/*String sql="SELECT empno,ename,job,TO_CHAR(hiredate,'YYYY-MM-DD') as dbday,"
    				  +"sal , dname, loc "
    				  +"FROM emp JOIN dept "
    				  +"ON emp.deptno=dept.deptno";*/
    		/*String sql="SELECT empno,ename,job,TO_CHAR(hiredate,'YYYY-MM-DD') as dbday,"
  				       +"sal , dname, loc "
  				       +"FROM emp NATURAL JOIN dept";*/
    		
    		String sql="SELECT empno,ename,job,TO_CHAR(hiredate,'YYYY-MM-DD') as dbday,"
				       +"sal , dname, loc "
				       +"FROM emp JOIN dept USING(deptno)";
  				       
    		// FROM emp JOIN dept  => , => JOIN , WHERE => ON
    		ps=conn.prepareStatement(sql);
    		ResultSet rs=ps.executeQuery();
    		while(rs.next())
    		{
    			Emp emp=new Emp();
    			emp.setEmpno(rs.getInt(1));
    			emp.setEname(rs.getString(2));
    			emp.setJob(rs.getString(3));
    			emp.setDbday(rs.getString(4));
    			emp.setSal(rs.getInt(5));
    			emp.getDvo().setDname(rs.getString(6));
    			emp.getDvo().setLoc(rs.getString(7));
    			list.add(emp);
    		}
    		rs.close();
    	}catch(Exception ex)
    	{
    		ex.printStackTrace();
    	}
    	finally
    	{
    		disConnection();
    	}
    	return list;
    }
    
    public List<Emp> empNonEquiJoinData()
    {
    	List<Emp> list=new ArrayList<Emp>();
    	try
    	{
    		getConnection();
    		/*String sql="SELECT empno,ename,job,TO_CHAR(hiredate,'YYYY/MM/DD') as dbday,"
    				  +"sal,dname,loc,grade "
    				  +"FROM emp,dept,salgrade "
    				  +"WHERE emp.deptno=dept.deptno "
    				  +"AND sal BETWEEN losal AND hisal";*/
    		// Ansi JOIN
    		String sql="SELECT empno,ename,job,TO_CHAR(hiredate,'YYYY/MM/DD') as dbday,"
  				  +"sal,dname,loc,grade "
  				  +"FROM emp JOIN dept "
  				  +"ON emp.deptno=dept.deptno "
  				  +"JOIN salgrade "
  				  +"ON sal BETWEEN losal AND hisal";
    		ps=conn.prepareStatement(sql);
    		ResultSet rs=ps.executeQuery();
    		while(rs.next())
    		{
    		    Emp emp=new Emp();
    		    emp.setEmpno(rs.getInt("empno"));
    		    emp.setEname(rs.getString("ename"));
    		    emp.setJob(rs.getString("job"));
    		    emp.setDbday(rs.getString("dbday"));
    		    // 함수 => 별칭을 사용한다 
    		    // MyBaits/JPA => 컬럼명으로 읽어온다 
    		    emp.setSal(rs.getInt("sal"));
    		    emp.getDvo().setDname(rs.getString("dname"));
    		    emp.getDvo().setLoc(rs.getString("loc"));
    		    emp.getSvo().setGrade(rs.getInt("grade"));
    		    
    		    list.add(emp);
    		}
    		rs.close();
    	}catch(Exception ex)
    	{
    		ex.printStackTrace();
    	}
    	finally
    	{
    		disConnection();
    	}
    	return list;
    }
    
    public List<Emp> empOuterJoin()
    {
    	List<Emp> list=
    			new ArrayList<Emp>();
    	try
    	{
    		getConnection();
    		/*String sql="SELECT empno,ename,job,dname,loc "
    				  +"FROM emp,dept "
    				  +"WHERE emp.deptno(+)=dept.deptno";*/
    		String sql="SELECT empno,ename,job,dname,loc "
  				  +"FROM emp RIGHT OUTER JOIN dept "
  				  +"ON emp.deptno=dept.deptno";
    		ps=conn.prepareStatement(sql);
    		ResultSet rs=ps.executeQuery();
    		while(rs.next())
    		{
    			Emp emp=new Emp();
    			emp.setEmpno(rs.getInt(1));
    			emp.setEname(rs.getString(2));
    			emp.setJob(rs.getString(3));
    			emp.getDvo().setDname(rs.getString(4));
    			emp.getDvo().setLoc(rs.getString(5));
    			list.add(emp);
    		}
    		rs.close();
    	}catch(Exception ex)
    	{
    		ex.printStackTrace();
    	}
    	finally
    	{
    		disConnection();
    	}
    	return list;
    }
    
}








