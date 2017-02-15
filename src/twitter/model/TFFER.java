package twitter.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.context.annotation.ScopedProxyMode;

import twitter4j.IDs;
import twitter4j.JSONArray;
import twitter4j.User;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class TFFER implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private User user;
    private String token;
    private String tokensecret;
    private ArrayList<String> followingList;
    private JSONArray searchResults;
	
	public JSONArray getSearchResults() {
		return searchResults;
	}

	public void setSearchResults(JSONArray searchResults) {
		this.searchResults = searchResults;
	}

	public ArrayList<String> getFollowingList() {
		return followingList;
	}

	public void setFollowingList(ArrayList<String> followingList) {
		this.followingList = followingList;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getTokensecret() {
		return tokensecret;
	}

	public void setTokensecret(String tokensecret) {
		this.tokensecret = tokensecret;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
