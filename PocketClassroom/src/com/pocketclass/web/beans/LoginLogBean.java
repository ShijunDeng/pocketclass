package com.pocketclass.web.beans;

/**
 * µÇÂ½ÈÕÖ¾bean
 * 
 * @author dengshijun
 * 
 */
public class LoginLogBean {
	private int order;
	private String username = "";
	private String loginTime = "";
	private String cancellationTime = "";
	private String loginIP = "";

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		if (username != null)
			this.username = username.trim();
	}

	public String getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(String loginTime) {
		if (loginTime != null)
			this.loginTime = loginTime.trim();
	}

	public String getCancellationTime() {
		return cancellationTime;
	}

	public void setCancellationTime(String cancellationTime) {
		if (cancellationTime != null)
			this.cancellationTime = cancellationTime.trim();
	}

	public String getLoginIP() {
		return loginIP;
	}

	public void setLoginIP(String loginIP) {
		if (loginIP != null)
			this.loginIP = loginIP.trim();
	}

}
