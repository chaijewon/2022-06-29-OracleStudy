package com.sist.main;
import java.util.*;
import com.sist.dao.*;
public class MainClass2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        BooksDAO dao=BooksDAO.newInstance();
        Scanner scan=new Scanner(System.in);
        System.out.print("페이지 입력:");
        int page=scan.nextInt();
        int totalpage=dao.booksTotalPage(); // 총페이지 구하기
        
        final int BLOCK=5;
        int startPage=((page-1)/BLOCK*BLOCK)+1;
        // page = 5 ==> startPage = 1 4/5*5=0 
        //        6 ==> 5/5*5 => 5+1 => 6
        // ==> page = 1~5 = 1
        // ==> page = 6~10 = 6
        // 1 6 11 16 
        int endPage=((page-1)/BLOCK*BLOCK)+BLOCK;
        // 5 10 15...
        if(endPage>totalpage)
        	endPage=totalpage;
        
        for(int i=startPage;i<=endPage;i++)
        {
        	System.out.print(i+"\t");
        }
        System.out.println("\n");
        List<BooksVO> list=dao.booksListData2(page);
        
        for(BooksVO vo:list)
        {
        	System.out.println(vo.getNo()+"."+vo.getTitle());
        }
	}

}
