package com.dh.search;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.core.SimpleAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.apache.lucene.search.SortField.Type;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import com.dh.domain.SearchItem;

public class Searcher {
	
	
	private static IndexReader indexReader=null;
	static{
		try {
			File indexDir = new File("H://luceneIndex");
			Directory directory = FSDirectory.open(indexDir);
			indexReader = DirectoryReader.open(directory);
		} catch (CorruptIndexException e) {
			// TODO: handle exception
			e.printStackTrace();
		}catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	
	private static IndexSearcher getSearcher(){
		try {
			if(indexReader==null){
				File indexDir = new File("H://luceneIndex");
				Directory directory = FSDirectory.open(indexDir);
				indexReader = DirectoryReader.open(directory);
			}
			return new IndexSearcher(indexReader);
		} catch (CorruptIndexException e) {
			// TODO: handle exception
			e.printStackTrace();
		}catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	
	
	private  static List<SearchItem>  SearchBySort(String querystr,String domain,Sort sort){
		
		List<SearchItem> resultlist = new ArrayList<SearchItem>();
		TopDocs topDocs=null;
		IndexSearcher indexSearcher = getSearcher();
		try {
			QueryParser parserKey = new QueryParser(domain, new StandardAnalyzer());
			Query query = parserKey.parse(querystr);
			if(sort==null){
				topDocs = indexSearcher.search(query, 1000);
			}else{
				topDocs=indexSearcher.search(query, 1000,sort);
			}
			ScoreDoc[] scoreDocs = topDocs.scoreDocs;
			if (scoreDocs == null || scoreDocs.length == 0) {
				System.out.println("The Lucene index is not exist");
			}
			for (int i = 0; i < scoreDocs.length; i++) {
				Document document = indexSearcher.doc(scoreDocs[i].doc);
				SearchItem tmpItem = new SearchItem();
				tmpItem.setKey(document.get("key"));
				tmpItem.setSupplierid(document.get("supplierid"));
				tmpItem.setImgurl(document.get("imgurl"));
				tmpItem.setMaxcost(Double.parseDouble(document.get("maxcost")));
				tmpItem.setMincost(Double.parseDouble(document.get("mincost")));
				tmpItem.setItemname(document.get("itemname"));
				if(!resultlist.contains(tmpItem))
					resultlist.add(tmpItem);
			}				
			return resultlist;

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	public static List<SearchItem> search(String key) {
		// This is the directory that hosts the Lucene index
		List<SearchItem> resultlist = new ArrayList<SearchItem>();
		
		try{
			
			List<SearchItem> tmplist=null;
			
			Sort sort=null;
			
			sort = new Sort(new SortField("itemname",SortField.Type.SCORE),new SortField("maxcost", Type.DOUBLE));
			
			//itemname
			tmplist=SearchBySort(key+"* OR ("+key+"~)", "itemname", sort);
			for(SearchItem tmp:tmplist){
				if(!resultlist.contains(tmp)){
					resultlist.add(tmp);
				}
			}
			
			//key
			tmplist=SearchBySort(key+"* OR ("+key+"~)", "key", null);
			for(SearchItem tmp:tmplist){
				if(!resultlist.contains(tmp)){
					resultlist.add(tmp);
				}
			}
			
			//supplierid
			tmplist=SearchBySort(key+"* OR ("+key+"~)", "supplierid", null);
			for(SearchItem tmp:tmplist){
				if(!resultlist.contains(tmp)){
					resultlist.add(tmp);
				}
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return resultlist;
	}
}
