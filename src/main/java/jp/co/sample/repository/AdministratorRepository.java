package jp.co.sample.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.sample.domain.Administrator;

/**
 * @author yuki
 *
 */
@Repository
public class AdministratorRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	private RowMapper<Administrator> ADMINISTRATOR_ROW_MAPPER = (rs, i) -> {
		Administrator administrator = new Administrator();
		administrator.setId(rs.getInt("id"));
		administrator.setName(rs.getString("name"));
		administrator.setMailAddress(rs.getString("mail_address"));
		administrator.setPassword(rs.getString("password"));
		return administrator;
	};

	/**
	 * insertメソッド
	 * 引数のadministratorをデータベースに挿入
	 * @param administrator　
	 */
	public void insert(Administrator administrator) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(administrator);
		String sql = "insert into administrators (name, mail_address, password) values ( :name,:mailAddress,:password)";
		template.update(sql, param);
	}

	/**
	 * findByMailAddressAndPasswordメソッド
	 * 引数で受け取ったmailAddressとpasswordから従業員情報を検索する
	 * @param mailAddress
	 * @param password
	 * @return administrator
	 */
	public Administrator findByMailAddressAndPassword(String mailAddress, String password) {
		String sql = "select id,name,mail_address,password from administrator where mail_address=:mailAddress and password=:password;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("mailAddress", mailAddress).addValue("password", password);
		Administrator administorator = template.queryForObject(sql, param, ADMINISTRATOR_ROW_MAPPER);
		return administorator;
	}
}
