package com.javalec.spring_mybatis.dao;

import java.util.ArrayList;

import com.javalec.spring_mybatis.dto.ContentDto;

public interface IDao {
 public ArrayList<ContentDto> listDao();
 public void writeDao(String mWriter, String mContent);
 public void deleteDao(String mId);
 
 
}
