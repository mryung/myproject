package com.myproject.util.log;

import java.io.InputStream;
import java.util.Arrays;
import java.util.Properties;

import org.bson.Document;
import org.springframework.core.io.ClassPathResource;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.WriteConcern;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;


public class MongoUtile {
	private static MongoClient mongoClient;
	private static Properties properties;
	static{
		try {
			properties = new Properties();
			ClassPathResource classPathResource = new ClassPathResource("datasource.properties");
			InputStream inputStream =  classPathResource.getInputStream();
			properties.load(inputStream);
			
			System.err.println(properties.getProperty("mongo.username"));
			//用户认证   
			MongoCredential credential = MongoCredential
					.createCredential(properties.getProperty("mongo.username")
							,properties.getProperty("mongo.dbname")
							, properties.getProperty("mongo.password").toCharArray());
			
			MongoClientOptions clientOptions = MongoClientOptions.builder()
//						.sslEnabled(true) 		//加密传输
						.connectionsPerHost(20) //每个目标数据库能够建立的最大connections的数量
						.minConnectionsPerHost(2) //最小连接对每个主机
						.maxConnectionIdleTime(1000*60*10) //连接的最大空闲时间
						.maxConnectionLifeTime(1000*60*20) //连接的最大生命时间
						.maxWaitTime(1000*60*1)  //连接的最大 等待时间
						.connectTimeout(1000*60*1) //与数据库超时时间
						.socketTimeout(1000*60*1) //socket超时时间
						.writeConcern(new WriteConcern(1))
						.build();
			
		 mongoClient = new MongoClient(new ServerAddress(properties.getProperty("mongo.host")
				 	,Integer.parseInt(properties.getProperty("mongo.port")))
				 	, Arrays.asList(credential)
				 	, clientOptions);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static MongoDatabase getConection(){
		return mongoClient.getDatabase(properties.getProperty("mongo.dbname"));
	}
	
	public static MongoCollection<Document> getCollection(String tableName){
		return mongoClient
				.getDatabase(properties.getProperty("mongo.dbname"))
				.getCollection(tableName);
		
	}
	public static void main(String[] args) {
		MongoCollection<Document> collection = getCollection("hello");
		Document document = new Document()
				.append("name", "yulang")
				.append("sex", "manqqqq")
				.append("birthday", "1994-9-01");
		collection.insertOne(document);
//		collection.
	}
}
