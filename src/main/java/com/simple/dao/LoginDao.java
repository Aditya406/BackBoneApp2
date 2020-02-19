package com.simple.dao;

import java.util.List;

import javax.sql.DataSource;

import org.json.simple.JSONArray;
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
		String sql = "insert into \"Account\" values(?,?,?)";

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
		String sql = "select \"UserName\",\"Password\",\"Email\" from \"Account\";";
		List<Account> account = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Account.class));
		JSONArray array = new JSONArray();
		for (Account str : account) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("username", (str.getUserName()));
			jsonObject.put("email", (str.getEmail()));
			jsonObject.put("password", (str.getPassword()));
			array.add(jsonObject);
		}
		return array;
	}

	@SuppressWarnings("unchecked")
	public String getuser(String username) {
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
	    String sql = "SELECT * FROM \"Account\" WHERE \"UserName\"=?";
	    JSONObject jsonObject = new JSONObject();
	    List<Account> account = jdbcTemplate.query(sql,new Object[]{username},BeanPropertyRowMapper.newInstance(Account.class));
	    for (Account str : account) {
			jsonObject.put("username", (str.getUserName()));
			jsonObject.put("email", (str.getEmail()));
			jsonObject.put("password", (str.getPassword()));
		}
	    return jsonObject.toJSONString();
	}

	public String updateuser(String username) {
		  String query = "UPDATE \"Account\" SET \"UserName\"=?, \"Email\"=?,\"Password\"=? WHERE \"UserName\"=?";
		  JSONObject result = new JSONObject();
		  int i =jdbcTemplate.update(query, Account.class);
			if (i == 1) {
				result.put("success", true);
			} else {
				result.put("success", false);
			}
			return result.toJSONString();
	}
}
