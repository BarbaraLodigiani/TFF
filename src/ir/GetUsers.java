package ir;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import geo.Geocode;
//import geo.Geocode;
import twitter4j.*;

public class GetUsers {

		private static int MAX_NUM_USER; //max 179!
//		private static HashMap<Long, ArrayList<Status>> map;		
		private static ArrayList<User> check_users;
		
		private static TweetMap tmap;
		/**
		 * @uml.property  name="working"
		 */
		private boolean working = true;
//		private static ArrayList<Status> statuses;
		// TODO Auto-generated method stub

		public GetUsers() {
            
            Properties prop = new Properties();
            
            InputStream is = getClass().getClassLoader().getResourceAsStream("twitter4j.properties");
            try {
                prop.load(is);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            
            String num = prop.getProperty("max_num_user");
            
            this.MAX_NUM_USER = 5;
        }
		
		
		public TweetMap getTweetMap() {
			
			check_users = new ArrayList<User>();
			tmap = new TweetMap();

			
	        TwitterStream twitterStream = new TwitterStreamFactory().getInstance();
	        
	        StatusListener listener = getStatusListener(twitterStream);
	        
	        twitterStream.addListener(listener);
	        
	        // Filter
	        FilterQuery filtre = new FilterQuery();
	        filtre.locations(new double[][]{new double[]{-126.562500,30.448674},
                new double[]{-61.171875,44.087585
                }});
	        filtre.language(new String[]{"en"});
	        twitterStream.filter(filtre);
		        
	       
	        
	        return tmap;

	    }

		/**
		 * @return
		 * @uml.property  name="working"
		 */
		public boolean isWorking()
		{
			return working;
		}
		
		public static int getMAX_NUM_USER() 
		{ 
			return MAX_NUM_USER; 
		}
		
		
		public static ArrayList<Status> getTweetListByUser(User user) throws TwitterException {
			// TODO Auto-generated method stub

			ArrayList<Status> stats = new ArrayList<Status>();
			
			Twitter twitter = new TwitterFactory().getInstance();
			

				System.out.println("******** user -- " + user.getScreenName() + "******* location = " + user.getLocation());
				
				int p = 1;
				while (stats.size()<=200){
					Paging page = new Paging(p++,50);
					List<Status> stat = twitter.getUserTimeline(user.getScreenName(),page);
					stats.addAll(stat);
				} 
				
				stats.subList(200, stats.size()).clear();
				
//				System.out.println("NUMERO TWEETS:"+stats.size());
			

			return stats;
			
		}
		
		private StatusListener getStatusListener(TwitterStream twitterStream)
		{
			StatusListener listener = new StatusListener() {
		           
	        	int c=1;
	        	int i=0;
	        	@Override
	            public void onStatus(Status status) {
	        		
	        		ArrayList<Status> statuses = new ArrayList<Status>();	        		
	        		
	            	User user = status.getUser();
	            	
	            	if(!check_users.contains(user) && check_users.size() < MAX_NUM_USER)	    
	            		if(user.isGeoEnabled())
	            			if (user.getLocation() != null && !user.getLocation().isEmpty()) 
	            				if(user.getLocation().length()>1)
		            				if(user.getStatusesCount() > 199)
			            				if( new Geocode().checkLocation(user.getLocation()) )	            						

		            					{
		            						try {
												statuses = getTweetListByUser(user);
											} catch (TwitterException e) {
												// TODO Auto-generated catch block
	//											e.printStackTrace();											
												
												try {
													System.out.println("***** statusize = "+ statuses.size() +" ******* sto dormendo per "+ e.getRateLimitStatus().getSecondsUntilReset() +" secondi **********");
													Thread.sleep(e.getRateLimitStatus().getSecondsUntilReset()*1000);
												} catch (InterruptedException e1) {
													// TODO Auto-generated catch block
													e1.printStackTrace();
												}
											}
		            						
		            						if(statuses.size() == 200)
		            						{
		            							check_users.add(user);
			            						
			            						tmap.add(user, statuses);
			            						
			            		            	System.out.println("*******"+(c++)+":  @" + status.getUser().getScreenName() + " - " + status.getText());
			            		            	System.out.println("NUMERO TWEETS:"+statuses.size()+"\n\n");
		            						}
		            						else
		            							System.out.println("- - - saltooooo - - -" + "\n\n");
		            						
		            					}
	            				

	            
	    	        if(check_users.size() == MAX_NUM_USER){

	    	        	 working = false;
	    				twitterStream.cleanUp();
	    				twitterStream.shutdown();
//	    				return tmap;
	    				
	            	}
	            	

	            }

	            @Override
	            public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
	                System.out.println("Got a status deletion notice id:" + statusDeletionNotice.getStatusId());
	            }

	            @Override
	            public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
	                System.out.println("Got track limitation notice:" + numberOfLimitedStatuses);
	            }

	            @Override
	            public void onScrubGeo(long userId, long upToStatusId) {
	                System.out.println("Got scrub_geo event userId:" + userId + " upToStatusId:" + upToStatusId);
	            }

	            @Override
	            public void onStallWarning(StallWarning warning) {
	                System.out.println("Got stall warning:" + warning);
	            }

	            @Override
	            public void onException(Exception ex) {
	                ex.printStackTrace();
	            }
	        };
	        
	        return listener;
		}
}
