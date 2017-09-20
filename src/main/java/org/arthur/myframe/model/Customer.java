package org.arthur.myframe.model;

public class Customer {
	
	/**
	 * ID
	 */
	private long id;
	
	/**
	 * �ͻ�����
	 */
	private String name;
	
	/**
	 * ��ϵ��
	 */
	
	private String contact;
	
	/**
	 * �绰����
	 */
	
	private String telephone;
	
	/**
	 * �����ַ
	 */
	
	private String email;
	
	/**
	 * ��ע
	 */
	private String remark;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", contact=" + contact + ", telephone=" + telephone
				+ ", email=" + email + ", remark=" + remark + "]";
	}
	
	
	
}
