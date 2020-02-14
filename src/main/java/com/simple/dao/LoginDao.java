package com.simple.dao;

import java.util.List;

import javax.sql.DataSource;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.simple.Account;
import com.simple.LoginBean;


@Repository
public class LoginDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@SuppressWarnings("unchecked")
	public String getUser(String username, String password) {
		
		JSONObject result = new JSONObject();
		
		String sql = "select count(*) from \"Login\" where username=? and password=?";
		LoginBean user = new LoginBean();
		try {
			Integer cunt= jdbcTemplate.queryForObject(sql, Integer.class, new Object[] {username,password});
			if(cunt>0) {
				result.put("success", true);
				
			}else {
				result.put("success", false);
			}
		} catch (Exception e) {
			result.put("success", false);
		}
		return result.toString();
	}

	@SuppressWarnings("unchecked")
	public String saveUser(String username, String email,String password) {
		JSONObject result = new JSONObject();
		jdbcTemplate = new JdbcTemplate(dataSource);
		String sql = "insert into \"Account\" values(?,?,?)";
		
		int i=jdbcTemplate.update(sql, username,email,password);
		if(i==1) {
			result.put("success", true); 
		}else 
		{ 
			result.put("success", false); 
		}
		return result.toJSONString();
	}

	public List<Account> getall() {
		String sql="select * from \"Account\""; 
		List<Account> account=jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Account.class));
		return account;	
		}

	
}
