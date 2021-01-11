package Thread;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

interface convert{
	void conv(String str);
}

class mythread implements Runnable
{
	public String s;
	mythread(String str)
	{
		s = str;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		convert task = (String str)->{
			StringBuilder builder = new StringBuilder();
			for (char ch: s.toCharArray()) {
				if(ch!=' ')
				{
					builder.append(ch); 
				}
			}
			String s2=builder.toString();
			if(s2=="")
			{
			   System.out.println("no hex");
			return ;
			}
			int i=Integer.parseInt(s2);
			String hexString = Integer.toHexString(i);
			 
			System.out.println(s+"="+hexString);
			try{

				File file = new File("C:\\Users\\jasbir.singh\\Downloads\\prob2.txt");
				FileWriter fr = new FileWriter(file,true);
				BufferedWriter br = new BufferedWriter(fr);
				br.write(s+"="+hexString);
				br.newLine();
				br.close();
				fr.close();

				}
				catch (Exception e)
				{
				System.out.println(e);
				}
		};
		task.conv(s);
	}
}


public class thread {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		File file = new File("C:\\Users\\jasbir.singh\\Downloads\\prob1.txt");
		Scanner scan = new Scanner(file);
		
		ExecutorService pool = Executors.newFixedThreadPool(5);
		
		while( scan.hasNextLine() )
		{
			//System.out.println(scan.nextLine());
			Runnable thr = new mythread(scan.nextLine());
			pool.execute(thr);
		}
		
		scan.close();
		
	}

}
