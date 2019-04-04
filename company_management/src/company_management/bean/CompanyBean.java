package company_management.bean;

import java.util.ArrayList;

public class CompanyBean implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8405457781559423830L;

	private String idCompany;
	private String companyName;
	private String phone;
	private String email;
	private ArrayList<EmployeeBean> companyEmployees;
	
	
	
	public ArrayList<EmployeeBean> getCompanyEmployees() {
		return companyEmployees;
	}
	public void setCompanyEmployees(ArrayList<EmployeeBean> companyEmployees) {
		this.companyEmployees = companyEmployees;
	}
	public String getIdCompany() {
		return idCompany;
	}
	public void setIdCompany(String idCompany) {
		this.idCompany = idCompany;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
}
