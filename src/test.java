import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try
		{
			double[][] training = toMatrix("training.csv");
			regression.multipleRegression(training);
			
			double[] ans = regression.predict(toMatrix("testing.csv"));
			
			File file = new File("targetActual.csv");
			Scanner sc = new Scanner(file);
			
			sc.nextLine();
			
			double[] actual = new double[ans.length];
			int k = 0;
			
			while(sc.hasNextLine())
			{
				actual[k++] = Double.parseDouble(sc.nextLine());
			}
			sc.close();
			
			File file2 = new File("comparison.csv");
			
			PrintWriter pw = new PrintWriter(file2);
			StringBuilder sb = new StringBuilder();
			
			sb.append("RMSE");
			sb.append(',');
			sb.append("MAE");
			sb.append('\n');
			
			sb.append(RMSE(ans, actual));
			sb.append(',');
			sb.append(MAE(ans, actual));
			sb.append('\n');
			
			sb.append("target_close_nextday_actual");
			sb.append(',');
			sb.append("target_close_nextday_predict");
			sb.append('\n');
			
			for(int i = 0; i < ans.length; i++)
			{
				sb.append(actual[i]);
				sb.append(',');
				sb.append(ans[i]);
				sb.append('\n');
			}
			pw.println(sb.toString());
			pw.close();
			
		}
		
		catch(IOException e) {
			System.err.println("File not found");
			return;
		}
		
	}
	
	public static double[][] toMatrix(String path) throws IOException {
		
		double[][] matrix = null;
		String delim = ",";
		
		File file = new File(path);
		Scanner sc = new Scanner(file);
		
		String[] head = sc.nextLine().split(delim);
		
		int columnNumber = head.length;
		int rowNumber = countRowNumber(path);
		
		matrix = new double[rowNumber][columnNumber];
		
		int row = 0;

		while(sc.hasNextLine())
		{
//			String[] tmp = sc.nextLine().split(delim);
			
//			for(int j = 1; j < columnNumber; j++)
//			{
//				matrix[row][j - 1] = Double.parseDouble(tmp[j]);
//			}
//			row++;
			String[] tmp = sc.nextLine().split(delim, 2);
			
			matrix[row] = Arrays.stream(tmp[1].split(delim)).mapToDouble(Double::parseDouble).toArray();
			row++;
			
		}
		
		sc.close();
		
		return matrix;
	}
	
	public static int countRowNumber(String path) throws IOException{
		File file = new File(path);
		Scanner sc = new Scanner(file);
		
		int row = 0;
		
		sc.nextLine();
		
		while(sc.hasNextLine())
		{
			sc.nextLine();
			row++;
		}
		
		sc.close();
		
		System.out.println(row);
		return row;
	}
	
	public static double MAE(double[] predictValue, double[] actualValue)
	{
		double mae = 0;
		
		for(int i = 0; i < predictValue.length; i++)
		{
			mae += Math.abs(predictValue[i] - actualValue[i]);
		}
		
		return mae / predictValue.length;
	}
	
	public static double RMSE(double[] predictValue, double[] actualValue)
	{
		double rmse = 0;
		
		for(int i = 0; i < 50; i++)
		{
			rmse += Math.pow(predictValue[i] - actualValue[i], 2) / predictValue.length;
		}
		
		return Math.sqrt(rmse);
	}
	
	
	
	
}
