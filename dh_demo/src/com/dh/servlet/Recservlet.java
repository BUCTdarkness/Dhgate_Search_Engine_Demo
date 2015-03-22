package com.dh.servlet;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.lucene.analysis.core.SimpleAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import com.dh.domain.SearchItem;
import com.dh.util.ValidateUtil;
import com.dh.search.Searcher;


public class Recservlet extends HttpServlet{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String searchItemlist(String key,String path) {
		// This is the directory that hosts the Lucene index
		String result = "";
		try{

			File indexDir = new File(path);
			Directory directory = FSDirectory.open(indexDir);
			IndexReader indexReader = DirectoryReader.open(directory);
			IndexSearcher indexSearcher = new IndexSearcher(indexReader);
	
			if (!indexDir.exists()) {
				System.out.println("The Lucene index is not exist");
				return null;
			}
			
	
			
			Term term = new Term("key", key);
			TermQuery luceneQuery = new TermQuery(term);
			
//			QueryParser parser = new QueryParser("key", new SimpleAnalyzer());
//			
//			Query luceneQuery = parser.parse(key+"* OR ("+key+"~)");
			
	
			TopDocs topDocs = indexSearcher.search(luceneQuery, 1000);
			ScoreDoc[] scoreDocs = topDocs.scoreDocs;
			if (scoreDocs == null || scoreDocs.length == 0) {
				System.out.println("The  Lucene index is not exist");
			}
			
			
			for (int i = 0; i < scoreDocs.length; i++) {
				Document document = indexSearcher.doc(scoreDocs[i].doc);
				String tmpItem ="";
				tmpItem=document.get("value");
				result +=tmpItem+" ";
			}
			indexReader.close();
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("hello world!");
		String item =req.getParameter("itemcode");
		String user = req.getParameter("username");
		
		System.out.println(item);
		String itemcode="";
		String imgurl = "";
		String supplierid = "";
		String maxcost = "";
		String mincost = "";
		String itemname = "";
		
		if(ValidateUtil.isValid(item)){
			List<SearchItem> list =Searcher.search(item);
			for(SearchItem it:list){
				itemcode +=it.getKey()+";";
				imgurl += it.getImgurl()+";";
				supplierid += it.getSupplierid()+";";
				maxcost += it.getMaxcost()+";";
				mincost += it.getMincost()+";";	
				itemname += it.getItemname()+";";
			}	
			
			
			String result = searchItemlist(item,"H:\\RecItemIndex");
			System.out.println(result);
			
			String []items = result.split(" ");
			try {
	
				for(String key:items){
					list = Searcher.search(key);
					for(SearchItem it:list){
						itemcode +=it.getKey()+";";
						imgurl += it.getImgurl()+";";
						supplierid += it.getSupplierid()+";";
						maxcost += it.getMaxcost()+";";
						mincost += it.getMincost()+";";	
						itemname += it.getItemname()+";";
					}				
				}
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
		else if (ValidateUtil.isValid(user)&&!user.equals("tourists")){
			
			String result = searchItemlist(user,"H:\\RecUserIndex");
			System.out.println(result);
			
			String []items = result.split(",");
			try {
	
				for(String key:items){
					List<SearchItem> list = Searcher.search(key);
					for(SearchItem it:list){
						itemcode +=it.getKey()+";";
						imgurl += it.getImgurl()+";";
						supplierid += it.getSupplierid()+";";
						maxcost += it.getMaxcost()+";";
						mincost += it.getMincost()+";";	
						itemname += it.getItemname()+";";
					}				
				}
			}
			catch(Exception e){
				e.printStackTrace();
			}			
		}
		else{
			String result ="171242868,186419724,187877243,143140403,192704564,144622632,182594471,176463626,195071090,182720400,185218381,194745788,133490224,187903290,185927991,176462368,180543908,156153732,174843121,152121859,140867430,181844414,171867405,141091866,166046120,190287824,145196371,214762457,159856617,176062613,175832535,194746333,167995702,194701600,158239018,186719198,182588659,176981570,211641413,194933163,200021275,182615133,176689154,196919979,174995627,182517026,196886522,183759134,179241052,201858584,187112555,202956283,178320747,168430585,167635514,166504670,166657477,170190649,104595809,175962941,174240950,178555115,199953438,203664361,175101558,182117314,159512229,174375780,170135996,178127094,211850086,184148249,177127669,176779430,190669050,182782816,204019500,189138855,158239097,108393774,210314049,172182859,129210787,186788778,164492553,192786481,185240421,164910142,168755211,177273223,160606154,210175707,181247353,180686819,165277379,121091831,203464552,192247552,175357406,204741656,";
		//	String result = "171242868,186419724";
			System.out.println(result);
			
			String []items = result.split(",");
			try {
	
				for(String key:items){
					List<SearchItem> list = Searcher.search(key);
					for(SearchItem it:list){
						itemcode +=it.getKey()+";";
						imgurl += it.getImgurl()+";";
						supplierid += it.getSupplierid()+";";
						maxcost += it.getMaxcost()+";";
						mincost += it.getMincost()+";";	
						itemname += it.getItemname()+";";
					}				
				}
			}
			catch(Exception e){
				e.printStackTrace();
			}				
		}
		
		
		
		RequestDispatcher rd = req.getRequestDispatcher("ItembasedRecShow.jsp");
        req.setAttribute("searchkey", item);
        req.setAttribute("itemcode",itemcode);//´æÖµ
        req.setAttribute("imgurl", imgurl);
        req.setAttribute("supplierid", supplierid);
        req.setAttribute("maxcost", maxcost);
        req.setAttribute("mincost", mincost);
        req.setAttribute("itemname", itemname);	
        rd.forward(req,resp);
	}

}
