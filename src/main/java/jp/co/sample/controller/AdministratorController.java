package jp.co.sample.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
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
	 * 管理者情報登録画面へ遷移.
	 * 
	 * @return 管理者情報登録画面
	 */
	@RequestMapping("/toInsert")
	public String toInsert() {
		return "administrator/insert";
	}

	/**
	 * 管理者情報を登録する.
	 * 
	 * @param form　リクエストパラメータを受け取るフォーム
	 * @return ログイン画面へリダイレクト
	 */
	@RequestMapping("/insert")
	public String insert(InsertAdministratorForm form) {
		Administrator administrator = new Administrator();
		BeanUtils.copyProperties(form, administrator);
		service.insert(administrator);
		return "redirect:/";
	}

	/**
	 * ログイン画面へ遷移.
	 * 
	 * @return ログイン画面
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
	public String login(@Validated LoginForm form, BindingResult result) {
		Administrator administorator = service.login(form.getMailAddress(), form.getPassword());
		if (administorator == null) {
			result.rejectValue("mailAddress", null, "メールアドレスまたはパスワードが不正です。");
			return "/administrator/login.html";
		} else {
			session.setAttribute("administratorName", administorator.getName());
			return "forward:/employee/showList";
		} 
	}
	
	/**
	 * ログアウトをする.
	 * 
	 * @return ログイン画面を表示
	 */
	@RequestMapping("/logout")
	public String logout() {
		session.invalidate();
		return "redirect:/";
	}

}
