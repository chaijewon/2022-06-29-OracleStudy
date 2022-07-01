package com.sist.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;
import java.util.*;
public class BooksDAO {
	private Connection conn;
    private PreparedStatement ps;
    private final String URL="jdbc:oracle:thin:@localhost:1521:XE";
    private static BooksDAO dao;
    
    public BooksDAO()
    {
    	try
    	{
    		Class.forName("oracle.jdbc.driver.OracleDriver");
    	}catch(Exception ex) {}
    }
    
    public void getConnection()
    {
    	try
    	{
    		conn=DriverManager.getConnection(URL,"hr","happy");
    	}catch(Exception ex){}
    }
    
    public void disConnection()
    {
    	try
    	{
    		if(ps!=null) ps.close();
    		if(conn!=null) conn.close();
    	}catch(Exception ex) {}
    }
    // 싱글턴 패턴 ==> Map
    public static BooksDAO newInstance()
    {
    	if(dao==null)
    		dao=new BooksDAO();
    	return dao;
    }
    // 페이징 ==> 인라인뷰 ==> 자바 
    public List<BooksVO> booksListData(int page)
    {
    	List<BooksVO> list=new ArrayList<BooksVO>();
    	long start=System.currentTimeMillis();
    	try
    	{
    		getConnection();
    		String sql="SELECT no,title "
    				  +"FROM books "
    				  +"ORDER BY no ASC";
    		ps=conn.prepareStatement(sql);
    		ResultSet rs=ps.executeQuery();
    		
    		int i=0;//10개씩 나눠주는 변수 
    		int j=0;// while 횟수 
    		int rowSize=10; // 갯수
    		int pagecnt=(page*rowSize)-rowSize; // 시작 
    		/*
    		 *     while , for => 0번부터 시작 
    		 *     1page ==> 0~9
    		 *     2page == 0~9 skip => 10~19
    		 *     3page == 0~19 skip => 20~29
    		 */
    		while(rs.next())
    		{
    			if(i<rowSize && j>=pagecnt)
    			{
    				BooksVO vo=new BooksVO();
    				vo.setNo(rs.getInt(1));
    				vo.setTitle(rs.getString(2));
    				list.add(vo);
    				i++;
    			}
    			j++;
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
    	long end=System.currentTimeMillis();
    	
    	System.out.println("걸린 시간:"+(end-start));
    	return list;
    }
    public int booksTotalPage()
    {
    	int total=0;
    	try
    	{
    		getConnection();
    		String sql="SELECT CEIL(COUNT(*)/10.0) FROM books";
    		ps=conn.prepareStatement(sql);
    		ResultSet rs=ps.executeQuery();
    		rs.next();
    		total=rs.getInt(1);
    		rs.close();
    	}catch(Exception ex)
    	{
    		ex.printStackTrace();
    	}
    	finally
    	{
    		disConnection();
    	}
    	return total;
    }
    
    //인라인뷰를 이용한 페이지 나누기 
    // => 1. 페이징, 원하는 갯수만큼 
    public List<BooksVO> booksListData2(int page)
    {
    	List<BooksVO> list=new ArrayList<BooksVO>();
    	long s=System.currentTimeMillis();
    	try
    	{
    		getConnection();
    		String sql="SELECT no,title,num "
    				  +"FROM (SELECT no,title,rownum as num "
    				  +"FROM (SELECT no , title "
    				  +"FROM books ORDER BY no ASC)) "
    				  +"WHERE num BETWEEN ? AND ?";
    		ps=conn.prepareStatement(sql);
    		int rowSize=10;
    		int start=(page*rowSize)-(rowSize-1); // 1 11
    		int end=rowSize*page; // 10 20
    		// 1page => 1~10
    		// 2page => 11~20
    		// rownum=> 1번부터 시작한다 , 자바는 0부터 
    		// MySQL => 0
    		ps.setInt(1, start);
    		ps.setInt(2, end);
    		
    		ResultSet rs=ps.executeQuery();
    		while(rs.next())
    		{
    			BooksVO vo=new BooksVO();
    		    vo.setNo(rs.getInt(1));
    			vo.setTitle(rs.getString(2));
    			list.add(vo);
    			
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
    	
    	long e=System.currentTimeMillis();
    	System.out.println("걸린 시간:"+(e-s));
    	return list;
    }
}








