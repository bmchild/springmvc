package com.brettchild.springmvc.form;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class RoleForm {

	@NotNull
	@Min(value=1)
	private Integer roleId;
	
	@NotEmpty
	@Size(max=64)
	private String roleName;

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Override
	public String toString() {
		return "RoleForm [roleId=" + roleId + ", roleName=" + roleName + "]";
	}
	
	
}
