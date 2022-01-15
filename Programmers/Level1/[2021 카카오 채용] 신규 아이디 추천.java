import java.util.stream.Collectors;
import java.util.stream.Stream;

class Solution {
	public String solution(String new_id) {
		new_id = new_id.toLowerCase(); //1단계
		new_id = Stream.of(new_id.split(""))  //2단계
			.filter(c -> ((Character.isAlphabetic(c.charAt(0)) && Character.isLowerCase(c.charAt(0)))
				|| Character.isDigit(c.charAt(0)) || c.equals("-") || c.equals("_") || c.equals(".")))
			.collect(Collectors.joining());
		while(new_id.contains("..")) //3단계
			new_id = new_id.replace("..", ".");
		if(new_id.length()>0 && new_id.charAt(0) == '.')  //4단계
			new_id = new_id.substring(1);
		if(new_id.length()>0 && new_id.charAt(new_id.length()-1) == '.')
			new_id = new_id.substring(0, new_id.length()-1);
		if(new_id.equals("")) //5단계
			new_id = "a";
		if(new_id.length() >= 16)  //6단계
			new_id = new_id.substring(0, 15);
		if(new_id.charAt(new_id.length()-1) == '.')
			new_id = new_id.substring(0, new_id.length()-1);
		StringBuilder new_idBuilder = new StringBuilder(new_id);  //7단계
		while(new_idBuilder.length() < 3)
			new_idBuilder.append(new_idBuilder.charAt(new_idBuilder.length() - 1));
		new_id = new_idBuilder.toString();
		return new_id;
	}
}