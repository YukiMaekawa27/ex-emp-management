package jp.co.sample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.sample.domain.Administrator;
import jp.co.sample.repository.AdministratorRepository;

/**
 * 管理者情報を操作するクラス.
 * 
 * @author yuki
 *
 */
@Service
@Transactional
public class AdministratorService {
	
	@Autowired
	private AdministratorRepository repository;
	
	/**
	 * データベースに情報を登録.
	 * 
	 * @param administrator 管理者情報
	 */
	public void insert(Administrator administrator) {
		repository.insert(administrator);
	}
	
	/**
	 * mailAddress、passwordからadministratorを検索.
	 * 
	 * @param mailAddress メールアドレス
	 * @param password パスワード
	 * @return administrator  管理者情報
	 */
	public Administrator findByMailAddressAndPassword (String mailAddress, String password) {
		Administrator administrator = repository.findByMailAddressAndPassword(mailAddress, password);
		return administrator;
	}

}
