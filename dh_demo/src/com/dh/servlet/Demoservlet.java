package com.dh.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.lucene.analysis.core.SimpleAnalyzer;
import org.apache.lucene.analysis.core.WhitespaceAnalyzer;
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
import com.dh.search.Searcher;;

public class Demoservlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
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
		String key=req.getParameter("key");
		if(!ValidateUtil.isValid(key)){
	        RequestDispatcher rt = req.getRequestDispatcher("/rec");       
	        rt.forward(req,resp);
		}
		
		System.out.println(key);
		
		try {

			List<SearchItem> list = Searcher.search(key);
			String itemcode="";
			String imgurl = "";
			String supplierid = "";
			String maxcost = "";
			String mincost = "";
			String itemname = "";
			
			for(SearchItem item:list){
				
					itemcode +=item.getKey()+";";
					imgurl += item.getImgurl()+";";
					supplierid += item.getSupplierid()+";";
					maxcost += item.getMaxcost()+";";
					mincost += item.getMincost()+";";	
					itemname += item.getItemname()+";";
					
				}
			
	        RequestDispatcher rd = req.getRequestDispatcher("DefaultSearchShow.jsp");
	        req.setAttribute("searchkey", key);
	        req.setAttribute("itemcode",itemcode);//´æÖµ
	        req.setAttribute("imgurl", imgurl);
	        req.setAttribute("supplierid", supplierid);
	        req.setAttribute("maxcost", maxcost);
	        req.setAttribute("mincost", mincost);
	        req.setAttribute("itemname", itemname);	        
	        rd.forward(req,resp);		
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
