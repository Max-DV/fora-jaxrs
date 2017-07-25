package io.robusta.demo.jaxb;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("loop")
public class LoopResource {

	@GET
	public Loop getLoop(){
		
		Loop l1 = new Loop();
		l1.setName("loop1");
		Loop l2 = new Loop();
		l2.setName("loop2");
		l1.setFriend(l2);
		l2.setFriend(l1);
		
		return l1;
	}
}
