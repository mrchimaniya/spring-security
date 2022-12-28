package com.divergentsl.repo;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;


//imagine this is repostry data coming from D/B
@Component
public class TokenManagerRepo {
  
	private Set<String> tokenNoList;
	
	public TokenManagerRepo() {
	  tokenNoList=new HashSet<String>();
	}
	   
	  public void add(String token)
	  {
		  tokenNoList.add(token);
	  }
	  
	  public boolean contains(String token)
	  {
		  return tokenNoList.contains(token);
	  }
}
