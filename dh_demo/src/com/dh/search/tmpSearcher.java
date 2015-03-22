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
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import com.dh.domain.SearchItem;

public class tmpSearcher {
	
	private  List<SearchItem>  SearchBySort(String querystr,String domain,Sort sort){
		return null;
	}
	
	
	public static List<SearchItem> search(String key) {
		// This is the directory that hosts the Lucene index
		List<SearchItem> resultlist = new ArrayList<SearchItem>();
		try{

			File indexDir = new File("H://luceneIndex");
			if (!indexDir.exists()) {
				System.out.println("The Lucene index is not exist");
				return null;
			}
			Directory directory = FSDirectory.open(indexDir);
			IndexReader indexReader = DirectoryReader.open(directory);
			IndexSearcher indexSearcher = new IndexSearcher(indexReader);
	

			
	
			
//			Term term = new Term("key", key);
//			TermQuery luceneQuery = new TermQuery(term);
			
			QueryParser parserKey = new QueryParser("key", new SimpleAnalyzer());
			QueryParser parserItemname = new QueryParser("itemname",new StandardAnalyzer());
			QueryParser parserImgurl = new QueryParser("imgurl",new StandardAnalyzer());
			QueryParser parserSupplierid = new QueryParser("supplierid",new StandardAnalyzer()); 
			
			Query luceneQueryKey = parserKey.parse(key+"* OR ("+key+"~)");
			Query luceneItemname = parserItemname.parse(key+"* OR ("+key+"~)");
			Query luceneItemImgurl = parserImgurl.parse(key+"* OR ("+key+"~)");
			Query luceneSupplierid = parserSupplierid.parse(key+"* OR ("+key+"~)");
			
			TopDocs topDocsKey = indexSearcher.search(luceneQueryKey, 1000);
			ScoreDoc[] scoreDocsKey = topDocsKey.scoreDocs;
			if (scoreDocsKey == null || scoreDocsKey.length == 0) {
				System.out.println("The  Key Lucene index is not exist");
			}
			
			TopDocs topDocsItemname = indexSearcher.search(luceneItemname, 1000);
			ScoreDoc[] scoreItemname = topDocsItemname.scoreDocs;
			if (scoreItemname == null || scoreItemname.length == 0) {
				System.out.println("The  Itemname Lucene index is not exist");
			}
			
			TopDocs topDocsImgurl = indexSearcher.search(luceneItemImgurl, 1000);
			ScoreDoc[] scoreDocsImgurl = topDocsImgurl.scoreDocs;
			if (scoreDocsImgurl == null || scoreDocsImgurl.length == 0) {
				System.out.println("The  Imgurl Lucene index is not exist");
			}
			
			TopDocs topDocsSupplierid = indexSearcher.search(luceneSupplierid, 1000);
			ScoreDoc[] scoreDocsSupplierid = topDocsSupplierid.scoreDocs;
			if (scoreDocsSupplierid == null || scoreDocsSupplierid.length == 0) {
				System.out.println("The  Supplierid Lucene index is not exist");
			}
			
			
			
			for (int i = 0; i < scoreItemname.length; i++) {
				Document document = indexSearcher.doc(scoreItemname[i].doc);
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
			
			for (int i = 0; i < scoreDocsSupplierid.length; i++) {
				Document document = indexSearcher.doc(scoreDocsSupplierid[i].doc);
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
			
			for (int i = 0; i < scoreDocsImgurl.length; i++) {
				Document document = indexSearcher.doc(scoreDocsImgurl[i].doc);
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
			
			
			for (int i = 0; i < scoreDocsKey.length; i++) {
				Document document = indexSearcher.doc(scoreDocsKey[i].doc);
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
			indexReader.close();
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return resultlist;
	}
}
