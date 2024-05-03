package com.springboot.RailwayTicket.thread;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

import jakarta.persistence.criteria.CriteriaBuilder.In;

public class SampleThreadClas {

	public static void main1(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService ex = Executors.newFixedThreadPool(10);
		
		System.out.println("qwes");
		Future<?>  i= ex.submit(() ->{
			System.out.println("Executors thread "+ Thread.currentThread().getName());	
		});
		
		//ExecutorService exs = Executors.newFixedThreadPool(10);
		
		System.out.println(i.isCancelled());
		System.out.println(i.isDone());
		System.out.println("qwes");
		System.out.println("qwes");
		System.out.println("qwes");
		System.out.println("qwes");
		System.out.println("qwes");
		System.out.println("qwes");
		System.out.println("qwes");
		
		
		i.cancel(true);
		if(i.isDone()) {
			System.out.println("***********");
		}
	
		ex.shutdown();
	}
	
	public static void main2(String[] args) {
		List<Integer> a = new ArrayList<>();
		a.add(1);
		a.add(2);
		a.add(2);
		a.add(2);
		a.add(3);
		a.add(2);
		a.add(3);
		a.add(2);
		a.add(2);
		a.add(3);
		a.add(2);
		a.add(3); //2 =8  3 =4
		Collections.sort(a);
		System.out.println(a);
		System.out.println(majorityElement2(a));
	}
	
	public static int majorityElement2(final List<Integer> A) {
		int majorCount =1, value =0, currentCount =1;
		
		for(int i = 1 ;i < A.size(); i++) {
			
			if(A.get(i).equals(A.get(i-1))) {
				System.out.println("A.get(i) - "+A.get(i));
				System.out.println("A.get(i-1) - " +A.get(i-1));
				
				++currentCount;
			}else {
				currentCount =1;
			}
			if (majorCount<currentCount) {
					 majorCount = currentCount;
					value = A.get(i-1);
				}
			
		}
		System.out.println(" majorCount -> "+ majorCount);
		System.out.println(" value -> "+ value);
		
		return value;
	}
	
	public static int majorityElement(final List<Integer> A) {
		 Map<Integer, Integer> hashMap = new HashMap<Integer, Integer>();
	        for(Integer as : A){
	            
	            if(hashMap.containsKey(as)){
	                hashMap.put(as, hashMap.get(as)+1);
	            }else{
	            	hashMap.put(as, 1);
	                
	            }
	        }
	        
	        int max = Collections.max(hashMap.values());
	        
	
	        List<Integer>  i = hashMap.entrySet().stream().filter(ent -> ent.getValue() == max).map(en -> en.getKey()).collect(Collectors.toList());
		return i.get(0);
		
	}
	
	
	public static void main3(String[] args) {
		prettyPrint(4);
	}
	 public static ArrayList<ArrayList<Integer>> prettyPrint(int A) {
		 
		 for(int i =0 ; i<=A*2 ; i++) {
			 
			 System.out.print(A + " ");
			 
		 }
		 
		return null;
	    }
	
	
}
