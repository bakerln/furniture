package com.example.util;

import com.example.model.Furniture;
import com.example.model.User;
import com.example.model.Vote;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
/**
 * 有改动
 * @author n
 *	11.20
 */
public class Json {
	public static User parseUser(String jsonData){
		Gson gson = new Gson();
		User user = gson.fromJson(jsonData, User.class);
		return user;
		
	}
	public static Vote parseVote(String jsonData){
		Gson gson = new Gson();
		Vote vote = gson.fromJson(jsonData, Vote.class);
		return vote;
	}
	//Gson解析时Date数据
	public static List<Vote> parseVoteList(String jsonData){
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		List<Vote> voteList = gson.fromJson(jsonData, new TypeToken<List<Vote>>(){}.getType());
		return voteList;
	}
	public static Furniture parseFur(String jsonData){
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		Furniture furniture = gson.fromJson(jsonData, Furniture.class);
		return furniture;
	}
	public static List<Furniture> parseFurList(String jsonData){
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		List<Furniture> furList = gson.fromJson(jsonData, 
				new TypeToken<List<Furniture>>() {}.getType());
		return furList;
	}
}
