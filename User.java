package kadai4.beans;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
	private String login_id;
	private String password;
	private String name;
	private int branch_id;
	private int department_id;
	private int available;
	private Date created;
	private Date modified;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;

	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLogin_Id() {
		return login_id;
	}

	public void setLogin_Id(String login_id) {
		this.login_id = login_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getBranch_Id() {
		System.out.println("getB: " + branch_id);
		return branch_id;
	}

	public void setBranch_Id (int branch_id) {
		System.out.println("setB: " + branch_id);
		this.branch_id = branch_id;
	}

	public int getDepartment_Id() {
		System.out.println("getD: " + department_id);
		return department_id;
	}

	public void setDepartment_Id (int department_id) {
		System.out.println("setD: " + department_id);
		this.department_id = department_id;
	}

	public int getAvailale() {
		return available;
	}

	public void setAvailable(int available) {
		this.available = available;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getModified() {
		return modified;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}
}