package com.dh.search;

import java.io.*;
import java.util.*;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import com.dh.domain.*;

public class TxtFileSearcher {
	
	private List<SearchItem> resultlist ;
	
	public TxtFileSearcher() {
		// TODO Auto-generated constructor stub
		resultlist = new ArrayList<SearchItem>();
	}
	
	
	public List<SearchItem> search(String key) throws IOException{
		// This is the directory that hosts the Lucene index
		File indexDir = new File("H:\\luceneIndex");
		Directory directory = FSDirectory.open(indexDir);
		IndexReader indexReader = DirectoryReader.open(directory);
		IndexSearcher indexSearcher = new IndexSearcher(indexReader);

		if (!indexDir.exists()) {
			System.out.println("The Lucene index is not exist");
			return null;
		}
		

		
		Term term = new Term("key", key);
		TermQuery luceneQuery = new TermQuery(term);

		TopDocs topDocs = indexSearcher.search(luceneQuery, 1000);
		ScoreDoc[] scoreDocs = topDocs.scoreDocs;
		if (scoreDocs == null || scoreDocs.length == 0) {
			System.out.println("The  Lucene index is not exist");
		}
		
		
		for (int i = 0; i < scoreDocs.length; i++) {
			Document document = indexSearcher.doc(scoreDocs[i].doc);
			SearchItem tmpItem = new SearchItem();
			tmpItem.setKey(document.get("key"));
			tmpItem.setSupplierid(document.get("supplierid"));
			tmpItem.setImgurl(document.get("imgurl"));
			tmpItem.setMaxcost(Double.parseDouble(document.get("maxcost")));
			tmpItem.setMincost(Double.parseDouble(document.get("mincost")));
			resultlist.add(tmpItem);
		}
		indexReader.close();
		return resultlist;
	}
	
}
