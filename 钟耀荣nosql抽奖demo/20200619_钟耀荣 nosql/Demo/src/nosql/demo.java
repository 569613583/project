package nosql;

import redis.clients.jedis.Jedis;

public class demo {
	public static void main(String[] args) {
	
		try (Jedis jedis = new Jedis("localhost",6379)) {
		jedis.flushAll();
		
		
		//30个抽奖人名单录入
				for(int i=1; i<=30;i++) {
					String  participator =("candidate"+i);
					jedis.sadd("candidates_list", participator);
					System.out.println("已添加抽奖人"+participator);
			   
			  }		
				System.out.println("candidates_list(候选人)"+jedis.smembers("candidates_list"));
				
				
		//随机选取一个一等奖；		
				String pop1=jedis.spop("candidates_list");
				jedis.sadd("price_first", pop1);
				System.out.println("price_first(一等奖):"+jedis.smembers("price_first"));
					
				
		//随机选取三个二等奖；		
				for(int j=1;j<=3;j++) {
					String  pop2 =jedis.spop("candidates_list");
					jedis.sadd("price_second", pop2);
				}	
				System.out.println("price_second(二等奖):"+jedis.smembers("price_second"));
				
				
		//随机选取五个三等奖；		
				for(int k=1;k<=5;k++) {
					String  pop3 =jedis.spop("candidates_list");
					jedis.sadd("price_third", pop3);
				}	
				System.out.println("price_third(三等奖):"+jedis.smembers("price_third"));
		}			
	}		
}


	

