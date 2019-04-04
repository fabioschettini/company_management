package company_management.bean;

import java.io.Serializable;

public class EmployeeBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1549250885233338087L;
	
	private String idEmployee;
	private String name;
	private String surname;
	private String companyBadge;
	private String fkCompany;
	public String getIdEmployee() {
		return idEmployee;
	}
	public void setIdEmployee(String idEmployee) {
		this.idEmployee = idEmployee;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getCompanyBadge() {
		return companyBadge;
	}
	public void setCompanyBadge(String companyBadge) {
		this.companyBadge = companyBadge;
	}
	public String getFkCompany() {
		return fkCompany;
	}
	public void setFkCompany(String fkCompany) {
		this.fkCompany = fkCompany;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
