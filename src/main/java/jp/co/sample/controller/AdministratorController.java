package jp.co.sample.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
@RequestMapping("/")
public class AdministratorController {

	@Autowired
	private AdministratorService service;
	
	@Autowired
	private HttpSession session;

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
		return "redirect:/";
	}

	/**
	 * 管理者登録画面へ遷移.
	 * 
	 * @return 管理者登録画面
	 */
	@RequestMapping("/")
	public String toLogin(Model model) {
//		model.addAttribute("isError", false);
		return "administrator/login.html";
	}
	
	/**
	 * 従業員一覧へ遷移、従業員がいなければエラーメッセージ.
	 * 
	 * @param form
	 * @param エラーメッセージ 
	 * @return 従業員一覧
	 */
	@RequestMapping("/login")
	public String login(LoginForm form, BindingResult result) {
		Administrator administorator = service.login(form.getMailAddress(), form.getPassword());
		if (administorator == null) {
			result.rejectValue("mailAddress", null, "メールアドレスまたはパスワードが不正です。");
			return "/administrator/login.html";
		} else {
			session.setAttribute("administratorName", administorator);
			return "forward:/employee/showlist";
		} 
	}

}
