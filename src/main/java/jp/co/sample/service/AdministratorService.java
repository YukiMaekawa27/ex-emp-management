package jp.co.sample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.sample.domain.Administrator;
import jp.co.sample.repository.AdministratorRepository;

/**
 * @author yuki
 *
 */
@Service
@Transactional
public class AdministratorService {
	
	@Autowired
	private AdministratorRepository repository;
	
	/**
	 * データベースに情報を登録
	 * @param administrator
	 */
	public void insert(Administrator administrator) {
		repository.insert(administrator);
	}
	
	/**
	 * 受け取ったmailAddress、passwordからadministratorを検索する
	 * @param mailAddress
	 * @param password
	 * @return administrator
	 */
	public Administrator findByMailAddressAndPassword (String mailAddress, String password) {
		Administrator administrator = repository.findByMailAddressAndPassword(mailAddress, password);
		return administrator;
	}

}
