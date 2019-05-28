package jp.co.sample.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sample.domain.Employee;
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
	
	/**
	 * 従業員一覧を出力するメソッド.
	 * 
	 * @param model
	 * @return 従業員一覧を表示
	 */
	@RequestMapping("/showList")
	public String showList(Model model) {
		List<Employee> employeeList = new ArrayList<>();
		employeeList = employeeService.showList();
		model.addAttribute("employeeList", employeeList);
		return "/employee/list.html";
	}

}
