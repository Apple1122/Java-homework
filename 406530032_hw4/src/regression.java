import org.apache.commons.math3.stat.regression.*;
/**
 * 
 * @author kylehuang
 *
 */
public class regression {
	 
	private static double[] beta;
	/**
	 * create regression
	 * @param test
	 */
	public static void multipleRegression(double[][] test)
	{
		final  OLSMultipleLinearRegression regression = new OLSMultipleLinearRegression();
		
		double[] y = new double[test.length];
		double[][] x = new double[test.length][test[0].length - 1];
		
		for(int i = 0; i < test.length; i++)
			for(int j = 0; j < test[0].length; j++)
			{
				if(j == test[0].length - 1)
					y[i] = test[i][j];
				else
					x[i][j] = test[i][j];
			}
		regression.setNoIntercept(true);
		regression.newSampleData(y, x);
		
		beta = regression.estimateRegressionParameters();
	}
	/**
	 * predict the value
	 * @param data
	 * @return
	 */
	public static double[] predict(double[][] data)
	{
		double[] result = new double[data.length];
		
		for(int i = 0; i < data.length; i++)
			for(int j = 0; j < beta.length; j++)
			{
				result[i] += beta[j] * data[i][j];
			}
		
		return result;
	}
}
