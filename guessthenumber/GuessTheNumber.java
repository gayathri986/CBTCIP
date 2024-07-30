package projects;
import java.util.Random;
import java.util.Scanner;

public class GuessTheNumber 
{
	public static void main(String[]args)
	{
		int failCount=0;int score=0;
		boolean status=false;
		while(failCount<3)
		{
		Scanner sc=new Scanner(System.in);
		System.out.println("Guess the number between 1 to 100:");
		int guessNumber=sc.nextInt();
		int generatedNumber;
		Random random=new Random();
		generatedNumber=random.nextInt(1,100);
		System.out.println("generated number:"+generatedNumber);
		
	    if(guessNumber==generatedNumber)
	    {
	    	System.out.println("Entered guess number matches the generated number:"+guessNumber+","+generatedNumber);
	    	status=true;
	    }
	    else if(guessNumber>generatedNumber)
	    {
	       System.out.println("Entered guess number is higher than the generated number:"+guessNumber+","+generatedNumber);
	       status=false;
	        
	    }
	    else if(guessNumber<generatedNumber)
	    {
	    	System.out.println("Enterd guess number is lower than the generated number:"+guessNumber+","+generatedNumber);
	    	status=false;
	    
	    }
		
	 if(status==true)
	 {
		score++;
		System.out.println("score count:"+score);
		System.out.println("failcount:"+failCount);
	 }
	 else
	 {
		 failCount++;
		 System.out.println("score count:"+score);
		 System.out.println("failcount:"+failCount);
		
	 }
		}
	 System.out.println("Displayed score:"+score);
	}
	
}
	

		
		
		
