package jp.co.sample.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sample.domain.Employee;
import jp.co.sample.form.UpdateEmployeeForm;
import jp.co.sample.service.EmployeeService;

/**
 * 従業員に関するパスを受け渡しするクラス.
 * 
 * @author yuki
 *
 */
@Controller
@RequestMapping("/employee")
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeService;
	
	@ModelAttribute
	public UpdateEmployeeForm setUpUpdateEmployeeForm() {
		return new UpdateEmployeeForm();
	}
	
	/**
	 * 従業員一覧を出力するメソッド.
	 * 
	 * @param リクエストパラメータ
	 * @return 従業員一覧を表示
	 */
	@RequestMapping("/showList")
	public String showList(Model model) {
		List<Employee> employeeList = employeeService.showList();
		model.addAttribute("employeeList", employeeList);
		return "employee/list";
	}
	
	/**
	 * 従業員詳細を出力するメソッド.
	 * 
	 * @param 社員ID
	 * @param リクエストパラメータ
	 * @return 従業員一覧を表示
	 */
	@RequestMapping("/showDetail")
	public String showDetail(String id, Model model) {
		Employee employee = employeeService.showDetail(Integer.parseInt(id));
		model.addAttribute("employee", employee);
		System.out.println(employee);
		return "employee/detail";
	}
	
	/**
	 * 扶養人数を更新するクラス.
	 * 
	 * @param 従業員ID
	 * @param 新しい扶養人数が入ったフォーム
	 * @return 従業員一覧を表示
	 */
	@RequestMapping("/update")
	public String update(Integer id, UpdateEmployeeForm form) {
		Employee employee = employeeService.showDetail(id);
		employee.setDependentsCount(Integer.parseInt(form.getDependentsCount()));
		employeeService.update(employee);
		return "redirect:/employee/showList";
	}

}
