package jp.co.sample.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sample.domain.Administrator;
import jp.co.sample.form.InsertAdministratorForm;
import jp.co.sample.form.LoginForm;
import jp.co.sample.service.AdministratorService;

/**
 * パスの受け渡しを行うクラス.
 * 
 * @author yuki
 *
 */
@Controller
@RequestMapping("/administrator")
public class AdministratorController {

	@Autowired
	private AdministratorService service;

	@ModelAttribute
	public InsertAdministratorForm setUpInsertAdministratorForm() {
		return new InsertAdministratorForm();
	}

	@ModelAttribute
	public LoginForm setUpLoginForm() {
		return new LoginForm();
	}

	/**
	 * 最初の入力画面へ遷移.
	 * @return 入力画面
	 */
	@RequestMapping("/toInsert")
	public String toInsert() {
		return "administrator/insert";
	}

	/**
	 * データベースへ挿入.
	 * 
	 * @param form
	 * @return リダイレクト処理
	 */
	@RequestMapping("/insert")
	public String insert(InsertAdministratorForm form) {
		Administrator administrator = new Administrator();
		BeanUtils.copyProperties(form, administrator);
		service.insert(administrator);
		return "redirect:/administrator/";
	}

	/**
	 * 管理者登録画面へ遷移.
	 * 
	 * @return 管理者登録画面
	 */
	@RequestMapping("/")
	public String toLogin() {
		return "administrator/login.html";
	}

}
