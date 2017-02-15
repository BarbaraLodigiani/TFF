package twitter.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.lucene.document.Document;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ir.IndexTweet;
import searchengine.Queries;
import twitter.model.OAuthToken;
import twitter.model.Ricerca;
import twitter.model.TFFER;
import twitter.validator.RicercaValidator;
import twitter4j.IDs;
import twitter4j.JSONArray;
import twitter4j.JSONException;
import twitter4j.JSONObject;
import twitter4j.PagableResponseList;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

@Controller
public class BaseController {

	private static Queries q = new Queries();
	@Autowired
	private OAuthToken oauthToken;

	@Autowired
	private TFFER user;

	private String authUrl;

	private long userId;
	private String screenName;
	private String name;
	private String avatarUrl;
	private String selfDescription;
	/**
	 * @uml.property name="ricercaValidator"
	 * @uml.associationEnd readOnly="true"
	 */
	@Autowired
	RicercaValidator ricercaValidator;




	@SuppressWarnings("null")
	@RequestMapping(value = { "/" }, method = { RequestMethod.GET,
			RequestMethod.POST })
	public String index(Model model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		model.addAttribute("FormRicerca", new Ricerca());
		// returns the view name

		if (request.getSession().getAttribute("requestToken") != null) {
			Twitter twitter = (Twitter) request.getSession().getAttribute(
					"twitter");
			RequestToken requestToken = (RequestToken) request.getSession()
					.getAttribute("requestToken");
			String verifier = request.getParameter("oauth_verifier");
			AccessToken accessToken = null;
			try {
				accessToken = twitter.getOAuthAccessToken(requestToken,
						verifier);
				request.getSession().removeAttribute("requestToken");

			} catch (TwitterException twitterException) {
				twitterException.printStackTrace();
			}
			userId = accessToken.getUserId();
			user.setUser(twitter.showUser(userId));
			long cursor = -1;
			PagableResponseList<User> followings;
			ArrayList<User> followingsTot = new ArrayList<User>();
			ArrayList<String> followingNames = new ArrayList<String>();
			try {
				do {
					followings = twitter.getFriendsList(user.getUser().getId(),
							cursor, 200);
					followingsTot.addAll(followings);
				} while ((cursor = followings.getNextCursor()) != 0);
				for (User i : followingsTot) {
					followingNames.add(i.getScreenName());
				}
				user.setFollowingList(followingNames);

			} catch (TwitterException twitterException) {
				System.out.println("Failed to get friends' ids: "
						+ twitterException.getMessage());

			}
		}

		if (user.getUser() != null) {
			model.addAttribute("TFFER", user.getUser().getName());
			model.addAttribute("TFFERFollowing", user.getFollowingList()
					.toString());
		}

		return "index";
	}

	
	
	@RequestMapping(value = { "/search" }, method = {RequestMethod.POST})
	public String ricerca(@ModelAttribute("FormRicerca") Ricerca ricerca,
			BindingResult result, Model model, HttpServletRequest request) throws IOException,
			ParseException, InvalidTokenOffsetsException, InterruptedException {

		ricercaValidator.validate(ricerca, result);

		if (result.hasErrors()) {
			return "index";
		} else {
			JSONObject ris = q.search(ricerca.getLocation(),
					ricerca.getInterests(), ricerca.getRadioInt(),
					ricerca.getHashtags(), ricerca.getRadioHash(),
					ricerca.getAge(), ricerca.getGender());
			try {
				if (ris.getJSONArray("ResultList").length() != 0) {
					int endPage, startPage;
					JSONArray resultsList = ris.getJSONArray("ResultList");
					model.addAttribute("risultati",resultsList);
					request.getSession().setAttribute("resultsList", resultsList);
					if(resultsList.length() -1 > 30)
					{
						endPage = 30;
						startPage = 0;
					} else {
						endPage = resultsList.length() -1;
						startPage = 0;
					}
					model.addAttribute("endPage", endPage);
					model.addAttribute("startPage", startPage);
					model.addAttribute("page",1);
		//			int pages = (resultsList.length() + 30 - 1) / 30;
				} else{
						model.addAttribute("risultati", "");
				}

				model.addAttribute("risSize", ris.getJSONArray("ResultList")
						.length());
			} catch (JSONException e) {
			}
		}
		if (user.getUser() != null) {
			model.addAttribute("TFFER", user.getUser().getName());
			model.addAttribute("TFFERFollowing", user.getFollowingList()
					.toString());
		}
					
		return "index";
	}

	
	@RequestMapping(value = { "/search/{page}" }, method = {RequestMethod.GET})
	public String ricercaPage(@PathVariable("page") int page, @ModelAttribute("FormRicerca") Ricerca ricerca,
			BindingResult result, Model model, HttpServletRequest request) throws IOException,
			ParseException, InvalidTokenOffsetsException, InterruptedException, JSONException {

			JSONArray resultsList = (JSONArray) request.getSession().getAttribute("resultsList");

			int endPage, startPage;
			if(resultsList.length() -1 > ((page-1)*30 + 30))
			{
				endPage = (page-1)*30 + 30;
				startPage = (page-1)*30;
			} 
			else { 
				endPage = resultsList.length() -1;
				startPage = (page-1)*30;
			}
			
			model.addAttribute("risultati",resultsList);
			model.addAttribute("endPage", endPage);
			model.addAttribute("startPage", startPage);
			model.addAttribute("risSize", resultsList.length());
		    if (user.getUser() != null) {
				model.addAttribute("TFFER", user.getUser().getName());
				model.addAttribute("TFFERFollowing", user.getFollowingList()
						.toString());
		    }
		
			model.addAttribute("page",page);
		return "index";
	}

	
	
	
	@RequestMapping(value = { "/signin" }, method = RequestMethod.GET)
	public String login(Model model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		Twitter twitter = new TwitterFactory().getInstance();
		request.getSession().setAttribute("twitter", twitter);

		twitter.setOAuthConsumer(oauthToken.getConsumerKey(),
				oauthToken.getConsumerSecret());
		RequestToken requestToken;
		String redirectUrl = null;
		try {

			String callbackURL = "http://127.0.0.1:8080/TwitterFollowingFinder/";
			requestToken = twitter.getOAuthRequestToken(callbackURL);
			String token = requestToken.getToken();
			String tokenSecret = requestToken.getTokenSecret();
			request.getSession().setAttribute("requestToken", requestToken);
			authUrl = requestToken.getAuthorizationURL();
			redirectUrl = request.getScheme() + authUrl;
		} catch (TwitterException e) {
			e.printStackTrace();
		}
		return "redirect:" + authUrl;

	}

	@RequestMapping(value = "/follow/{name}", method = RequestMethod.GET)
	@ResponseBody
	public String follow(@PathVariable("name") String name, Model model,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Twitter twitter = (Twitter) request.getSession()
				.getAttribute("twitter");
		boolean success = false;
		try {
			User friend = twitter.createFriendship(name);
			ArrayList<String> followingList = user.getFollowingList();
			followingList.add(friend.getScreenName());
			user.setFollowingList(followingList);
			success = true;
		} catch (TwitterException te) {
			success = false;

		}
		return "" + success + "";
	}

	@RequestMapping(value = "/unfollow/{name}", method = RequestMethod.GET)
	@ResponseBody
	public String unfollow(@PathVariable("name") String name, Model model,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Twitter twitter = (Twitter) request.getSession()
				.getAttribute("twitter");
		boolean success = false;
		try {
			User friend = twitter.destroyFriendship(name);
			ArrayList<String> followingList = user.getFollowingList();
			followingList.remove(friend.getScreenName());
			user.setFollowingList(followingList);
			success = true;
		} catch (TwitterException te) {
			success = false;

		}
		return "" + success + "";
	}
	
	
	@RequestMapping(value = "/tweets/{id}", method = RequestMethod.GET)
	@ResponseBody
	public JSONArray tweets(@PathVariable("id") int id, Model model,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		JSONArray resultsList = (JSONArray) request.getSession().getAttribute("resultsList");
		String fragment = resultsList.getJSONObject(id).get("tweets").toString();
	    String fragments[] = fragment.split("%NUOVOTWEET%");

	    JSONArray mJSONArray = new JSONArray(Arrays.asList(fragments));


		return mJSONArray;
	}

	
	
	
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String admin(Model model,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {


			


		return "admin";
	}



@RequestMapping(value = "/admin/crawling", method = RequestMethod.GET)
@ResponseBody
public String crawling(Model model,
		HttpServletRequest request, HttpServletResponse response) 
		throws Exception {

	
	
	IndexTweet crawling = new IndexTweet();
	crawling.crawl();
		


	return "";
}


@RequestMapping(value = "/admin/readFile", method = RequestMethod.GET)
@ResponseBody
public String read(Model model,
		HttpServletRequest request, HttpServletResponse response) 
		throws Exception {
	Path pathx;
	pathx = FileSystems.getDefault().getPath("WebContent", "");

	File file = new File(""+pathx+"/resources/crawling.txt");
	FileInputStream fis = new FileInputStream(file);
	byte[] data = new byte[(int) file.length()];
	fis.read(data);
	fis.close();
	String str = new String(data, "UTF-8");

	return str;
}
}