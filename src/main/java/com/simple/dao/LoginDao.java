package com.simple.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
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
			Integer cunt = jdbcTemplate.queryForObject(sql, Integer.class, new Object[] { username, password });
			if (cunt > 0) {
				result.put("success", true);

			} else {
				result.put("success", false);
			}
		} catch (Exception e) {
			result.put("success", false);
		}
		return result.toString();
	}

	@SuppressWarnings("unchecked")
	public String saveUser(String username, String email, String password) {
		JSONObject result = new JSONObject();
		jdbcTemplate = new JdbcTemplate(dataSource);
		String sql = "insert into \"Account\"(\"UserName\",\"Password\",\"Email\") values(?,?,?)";

		int i = jdbcTemplate.update(sql, username, email, password);
		if (i == 1) {
			result.put("success", true);
		} else {
			result.put("success", false);
		}
		return result.toJSONString();
	}

	@SuppressWarnings("unchecked")
	public JSONArray getall() {
		String sql = "select \"a_id\",\"UserName\",\"Password\",\"Email\" from \"Account\"";
		JSONArray array = new JSONArray();
		array = jdbcTemplate.query(sql, new ResultSetExtractor<JSONArray>() {
			@Override
			public JSONArray extractData(ResultSet rs) throws SQLException, DataAccessException {
				JSONArray array2 = new JSONArray();
				while(rs.next()) {
					JSONObject jsonObject = new JSONObject(); 
					jsonObject.put("a_id",(rs.getInt("a_id"))); 
					jsonObject.put("username", (rs.getString("UserName")));
					jsonObject.put("email", (rs.getString("Email"))); 
					jsonObject.put("password", (rs.getString("Password"))); 
					array2.add(jsonObject);
				}
				return array2;

			}

		});
		return array;
	}

	@SuppressWarnings("unchecked")
	public String getuser(String username) {
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
	    String sql = "SELECT * FROM \"Account\" WHERE \"a_id\"=?";
	    JSONObject jsonObject = new JSONObject();
	    List<Account> account = jdbcTemplate.query(sql,new Object[]{Integer.parseInt(username)},BeanPropertyRowMapper.newInstance(Account.class));
	    for (Account str : account) {
			jsonObject.put("username", (str.getUserName()));
			jsonObject.put("email", (str.getEmail()));
			jsonObject.put("password", (str.getPassword()));
		}
	    return jsonObject.toJSONString();
	}

	@SuppressWarnings("unchecked")
	public String updateuser(Integer a_id,String username,String email) {
		  String query = "UPDATE \"Account\" SET \"UserName\"=?,\"Email\"=? WHERE \"a_id\"=?";
		  JSONObject result = new JSONObject();
		  int i =jdbcTemplate.update(query, new Object[]{username,email,a_id});
		  if (i == 1) {
				result.put("success", true);
			} else {
				result.put("success", false);
			}
		 
			return result.toJSONString();
	}

	
}
