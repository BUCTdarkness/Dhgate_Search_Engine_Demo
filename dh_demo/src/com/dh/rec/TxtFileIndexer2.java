package com.dh.rec;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Date;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.core.WhitespaceAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

public class TxtFileIndexer2 {
	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws Exception {

		File indexDir = new File("H:\\RecUserIndex");

		File dataDir = new File("H:\\RecUserData");

		Analyzer luceneAnalyzer = new WhitespaceAnalyzer();
		File[] dataFiles = dataDir.listFiles();
		IndexWriterConfig indexWriterConfig = new IndexWriterConfig(
				Version.LUCENE_4_10_3, luceneAnalyzer);
		// 创建索引
		IndexWriter indexWriter = new IndexWriter(FSDirectory.open(indexDir),
				indexWriterConfig);

		long startTime = new Date().getTime();
		
		for (int i = 0; i < dataFiles.length; i++) {
			if (dataFiles[i].isFile()
					&& dataFiles[i].getName().endsWith(".txt")) {
				System.out.println("Indexing file "
						+ dataFiles[i].getCanonicalPath());
				// 封装document对象
				
				BufferedReader cin= new BufferedReader(new FileReader(dataFiles[i]));
				
				String tmpString = null;
				while ((tmpString = cin.readLine()) != null){
					String sarray[]=tmpString.split(":");
					
					String key = sarray[0];
					String value = sarray[1];
					
					
					Document document = new Document();
					document.add(new Field("key", key, Store.YES,Field.Index.ANALYZED));
					document.add(new Field("value", value, Store.YES,Field.Index.NOT_ANALYZED));

					indexWriter.addDocument(document);
				}
				cin.close();
				
			}
		}
		indexWriter.commit();
		// indexWriter.optimize();
		indexWriter.close();
		long endTime = new Date().getTime();
		System.out.println("It takes " + (endTime - startTime)
				+ " milliseconds to create index for the files in directory "
				+ dataDir.getPath());
	}
}
