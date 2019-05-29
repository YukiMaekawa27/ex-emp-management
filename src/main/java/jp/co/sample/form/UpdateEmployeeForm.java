package jp.co.sample.form;

import java.sql.Date;

/**
 * 従業員一覧表示に使われるフォーム
 * 
 * @author yuki
 *
 */
public class UpdateEmployeeForm {
	/** ID */
	private String id;
	/** 名前 */
	private String name;
	/** 画像 */
	private String image;
	/** 性別 */
	private String gender;
	/** 入社日 */
	private Date hireDate;
	/** メールアドレス */
	private String mailAddress;
	/** 郵便番号 */
	private String zipCode;
	/** 住所 */
	private String address;
	/** 電話番号 */
	private String telephone;
	/** 給与 */
	private String salary;
	/** 性格 */
	private String characteristics;
	/** 不要人数 */
	private String dependentsCount;
	
	public Integer getIntId() {
		return Integer.parseInt(id);
	}
	@Override
	public String toString() {
		return "UpdateEmployeeForm [id=" + id + ", name=" + name + ", image=" + image + ", gender=" + gender
				+ ", hireDate=" + hireDate + ", mailAddress=" + mailAddress + ", zipCode=" + zipCode + ", address="
				+ address + ", telephone=" + telephone + ", salary=" + salary + ", characteristics=" + characteristics
				+ ", dependentsCount=" + dependentsCount + "]";
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Date getHireDate() {
		return hireDate;
	}
	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}
	public String getMailAddress() {
		return mailAddress;
	}
	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getSalary() {
		return salary;
	}
	public void setSalary(String salary) {
		this.salary = salary;
	}
	public String getCharacteristics() {
		return characteristics;
	}
	public void setCharacteristics(String characteristics) {
		this.characteristics = characteristics;
	}
	public String getDependentsCount() {
		return dependentsCount;
	}
	public void setDependentsCount(String dependentsCount) {
		this.dependentsCount = dependentsCount;
	}
	
}