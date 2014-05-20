import java.util.ArrayList;
import java.util.Arrays;
/* Lennar.java
 * 
 * This is one of the stock that inherits from Stock class. The price data for February & March for
 * close price, upper & lower Bollinger bands, and simple moving average of 20 days of Lennar Corp.
 * are stored in different arraylist variables. The close price data of April is stored in the 
 * arraylist too.
 */

public class Lennar extends Stock{

	//Declare and store variables
	private Double[] closePriceArray = new Double[] 
	        {31.89,32.3,31.98,33.87,33.72,33.24,33.48,33.08,
			33.69,33.89,33.73,33.4,33.86,34.25,34.44,34.74,35.96,36.49,36.22,36.00,35.88,35.3,35.09,
			34.45,33.82,33.87,34.2,33.69,33.24,33.35,33.76,34.31,33.11,32.02,32.5,32.33,32.19,32.31,32.59,};
	
	private Double[] aprilClosePriceArray = new Double[]
	        {32.64,33.21,33.6,33.7,33.5,32.78,33.15,33.27,32.9,32.56,32.74,32.39,32.65,32.26,32.12,
			32.5,32.17,33.2,32.33,32.54,32.55,32.52,33.00,33.59};

	private Double[] bbUpperArray = new Double[] 
	        {33.62,33.57,33.48,33.69,33.86,33.84,33.96,34.03,34.22,34.42,34.58,34.65,34.78,
			34.96,35.02,35.05,35.48,36.00,36.33,36.60,36.73,36.76,36.67,36.68,36.68,36.65,36.63,36.58,36.61,
			36.64,36.64,36.62,36.66,36.84,36.90,36.93,36.77,36.43,36.07};
	
	private Double[] bbLowerArray = new Double[] 
	        {30.51,30.52,30.52,30.40,30.32,30.33,30.29,30.28,30.23,30.17,30.26,30.40,30.53,30.61,
			30.93,31.32,31.33,31.27,31.37,31.42,31.69,31.96,32.36,32.41,32.42,32.52,32.61,32.71,32.64,
			32.56,32.56,32.68,32.55,32.15,31.90,31.63,31.41,31.33,31.33};
	
	private Double[] smaArray = new Double[]
	        {32.01,32.15,32.16,32.56,32.75,33.02,33.26,33.48,33.44,33.48,33.57,33.56,33.71,33.83,
			33.94,34.14,34.65,35.18,35.57,35.88,36.11,35.98,35.70,35.34,34.91,34.51,34.29,34.01,33.76,
			33.67,33.65,33.67,33.55,33.31,33.14,32.85,32.43,32.27,32.38};
	                                    
	//Constructor
	public Lennar() 
	{
		super();
	}
	
	//Setters
	public void setSlope(double slope)
	{
		this.slope = slope;
	}
	public void setAprilClosePriceList()
	{
		aprilClosePriceList = new ArrayList<Double>(Arrays.asList(aprilClosePriceArray));
	}
	public void setPredictList()
	{
		predictList = new ArrayList<Double>();
	}
	public void setClosePrice()
	{	
	    closePriceList = new ArrayList<Double>(Arrays.asList(closePriceArray));
	}
	
	public void setBBUpperList()
	{	
	    bbUpperList = new ArrayList<Double>(Arrays.asList(bbUpperArray));
	}
	public void setBBLowerList()
	{	
	    bbLowerList = new ArrayList<Double>(Arrays.asList(bbLowerArray));
	}
	public void setSMAList()
	{	
	    smaList = new ArrayList<Double>(Arrays.asList(smaArray));
	}
	
	// Getters
	public double getSlope()
	{
		return slope;
	}
	public ArrayList<Double> getClosePriceList() {
		return closePriceList;
	}
	public ArrayList<Double> getAprilClosePriceList(){
		return aprilClosePriceList;
	}
	public ArrayList<Double> getPredictList() {
		return predictList;
	}
	public ArrayList<Double> getBBUpperList() {
		return bbUpperList;
	}
	public ArrayList<Double> getBBLowerList() {
		return bbLowerList;
	}
	public ArrayList<Double> getSMAList() {
		return smaList;
	}
}
