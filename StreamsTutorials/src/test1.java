import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.testng.Assert;
import org.testng.annotations.Test;

public class test1 {

	// Count the number of names starting with alphabet A in list
	@Test
	public void regular() {
	ArrayList<String> names = new ArrayList<String>();
	names.add("abhijeet");
	names.add("don");
	names.add("adam");
	names.add("alekyha");
	names.add("ram");
	int count = 0;
	
	for(int i=0;i<names.size();i++) {
		String actual = names.get(i);
		if(actual.startsWith("a"))
		{
			count++;
		}
	}
	System.out.println(count);
	
	}
	@Test
	public void streamFilter() {

		ArrayList<String> names = new ArrayList<String>();
		names.add("abhijeet");
		names.add("don");
		names.add("adam");
		names.add("alekyha");
		names.add("ram");
		//there is no life for intermediate operation if there is no terminal operation
		//terminal operation will execute only if there intermediate operation(filter) returns true
		//we can stream
		//how to use filter in stream API
		Long c = names.stream().filter(s -> s.startsWith("a")).count();
		System.out.println(c);
	    long d=	Stream.of("abhijeet","don","alekhya","adam","ram").filter(s -> 
		  {
			s.startsWith("a");
			return true;
		  }).count();
	    System.out.println(d);
	    
	    //print all the names of Arraylist
	    names.stream().filter(s->s.length()>4).forEach(s->System.out.println(s));
	    names.stream().filter(s->s.length()>4).limit(1).forEach(s->System.out.println(s));
	    

	}
	@Test
	public void streamMap() {
		
		ArrayList<String> names = new ArrayList<String>();
		names.add("man");
		names.add("don");
		names.add("woman");
		
		
		//print names which have last letter "a" with uppercase
		Stream.of("abhijeet","don","alekhya","adam","ram").filter(s->s.endsWith("a")).map(s->s.toUpperCase())
		.forEach(s->System.out.println(s));
		
		//print names which have first letter as "a" wih uppercase and sorted
		List<String> names1= Arrays.asList("azbhijeet","don","alekhya","adam","ram");
		names1.stream().filter(s->s.startsWith("a")).sorted().map(s->s.toUpperCase()).forEach(s->System.out.println(s));
		//merging 2 different lists
		Stream <String> newStream = Stream.concat(names.stream(), names1.stream());
		//newStream.sorted().forEach(s->System.out.println(s));
		boolean flag = newStream.anyMatch(s->s.equalsIgnoreCase("Adam"));
		System.out.println(flag);
		Assert.assertTrue(flag);		
	}
	@Test
	public void streamCollect() {
		List<String> ls = Stream.of("abhijeet","don","alekhya","adam","ram").filter(s->s.endsWith("a")).map(s->s.toUpperCase())
		.collect(Collectors.toList());	
		System.out.println(ls.get(0));//ALEKHYA
		
		List<Integer>values = Arrays.asList(3,2,2,7,5,1,9,7);
		//print unique number from this array
		//sort the array - 3rd index - 1,2,3,5,7,9
		values.stream().distinct().forEach(s->System.out.println(s));// 3 2 7 5 1 9
		List<Integer>li = values.stream().distinct().sorted().collect(Collectors.toList());
		System.out.println(li.get(2));// 3
	}
}
