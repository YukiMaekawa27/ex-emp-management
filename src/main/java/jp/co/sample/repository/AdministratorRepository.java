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
 * 管理者テーブルを操作するリポジトリ.
 * 
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
	 * 管理者情報を登録する.
	 * 
	 * @param administrator 管理者情報　
	 */
	public void insert(Administrator administrator) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(administrator);
		String sql = "insert into administrators (name, mail_address, password) "
				   + "values ( :name,:mailAddress,:password)";
		template.update(sql, param);
	}

	/**
	 * メールアドレス、パスワードから管理者情報を検索.
	 * 
	 * @param mailAddress メールアドレス
	 * @param password パスワード
	 * @return administrator 管理者情報
	 */
	public Administrator findByMailAddressAndPassword(String mailAddress, String password) {
		String sql = "select id,name,mail_address,password "
				   + "from administrator "
				   + "where mail_address=:mailAddress and password=:password;";
		SqlParameterSource param = new MapSqlParameterSource()
				.addValue("mailAddress", mailAddress)
				.addValue("password", password);
		Administrator administorator 
				= template.queryForObject(sql, param, ADMINISTRATOR_ROW_MAPPER);
		return administorator;
	}
}
