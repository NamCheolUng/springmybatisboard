package com.javalec.spring_mybatis.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;

import com.javalec.spring_mybatis.dto.ContentDto;

public class ContentDao implements IDao{
	
	JdbcTemplate template;
	
	@Autowired
	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}
	
	public ContentDao() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public ArrayList<ContentDto> listDao() {
		// TODO Auto-generated method stub
		String query = "select * from board order by mId desc";
		ArrayList<ContentDto> dtos = (ArrayList<ContentDto>) template.query(query, new BeanPropertyRowMapper<ContentDto>(ContentDto.class));
		
		return dtos;
	}

	@Override
	public void writeDao(final String mWriter, final String mContent) {
		// TODO Auto-generated method stub
		System.out.println("writeDao()");
		this.template.update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				// TODO Auto-generated method stub
				String query = "insert into board (mId, mWriter, mContent) values (board_seq.nextval,?,?)";
				PreparedStatement psmt = con.prepareStatement(query);
				psmt.setString(1, mWriter);
				psmt.setString(2, mContent);
				return psmt;
			}
		});
		
	}

	@Override
	public void deleteDao(final String mId) {
		// TODO Auto-generated method stub
		System.out.println("deleteDao()");
		String query = "delete from board where mId = ?";
		this.template.update(query, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement psmt) throws SQLException {
				// TODO Auto-generated method stub
				psmt.setInt(1, Integer.parseInt(mId));
				
				
			}
		});
		
	}
	
	

}
