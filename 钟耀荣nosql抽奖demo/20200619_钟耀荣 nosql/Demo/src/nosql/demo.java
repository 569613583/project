package nosql;

import redis.clients.jedis.Jedis;

public class demo {
	public static void main(String[] args) {
	
		try (Jedis jedis = new Jedis("localhost",6379)) {
		jedis.flushAll();
		
		
		//30���齱������¼��
				for(int i=1; i<=30;i++) {
					String  participator =("candidate"+i);
					jedis.sadd("candidates_list", participator);
					System.out.println("����ӳ齱��"+participator);
			   
			  }		
				System.out.println("candidates_list(��ѡ��)"+jedis.smembers("candidates_list"));
				
				
		//���ѡȡһ��һ�Ƚ���		
				String pop1=jedis.spop("candidates_list");
				jedis.sadd("price_first", pop1);
				System.out.println("price_first(һ�Ƚ�):"+jedis.smembers("price_first"));
					
				
		//���ѡȡ�������Ƚ���		
				for(int j=1;j<=3;j++) {
					String  pop2 =jedis.spop("candidates_list");
					jedis.sadd("price_second", pop2);
				}	
				System.out.println("price_second(���Ƚ�):"+jedis.smembers("price_second"));
				
				
		//���ѡȡ������Ƚ���		
				for(int k=1;k<=5;k++) {
					String  pop3 =jedis.spop("candidates_list");
					jedis.sadd("price_third", pop3);
				}	
				System.out.println("price_third(���Ƚ�):"+jedis.smembers("price_third"));
		}			
	}		
}


	

