import java.util.ArrayList;
import java.util.Arrays;
/* SanDisk.java
 * 
 * This is one of the stock that inherits from Stock class. The price data for February & March for
 * close price, upper & lower Bollinger bands, and simple moving average of 20 days of SanDisk are 
 * stored in different arraylist variables. The close price data of April is stored in the arraylist too.
 */

public class SanDisk extends Stock {

	//Declare and store variables
	private Double[] closePriceArray = new Double[] 
	        {67.39,68.24,68.78,70.24,71.61,71.67,72.29,72.085,74.4,74.59,74.59,74.53,75.31,74.8,
			75.81,75.85,76.54,76.28,74.3,75.12,76.26,76.49,75.45,74.41,74.44,74.84,75.01,73.63,
			73.28,74.74,77.82,78.79,80.72,80.1,80.1,80.73,79.86,78.48,80.61};

	private Double[] aprilClosePriceArray = new Double[]
	        {81.19,83.12,82.36,82.22,80.95,77.54,78.74,78.66,75.69,73.65,74.65,75.34,75.85,82.99,
			83.65,84.8,84.65,85.19,84.35,84.38,84.66,84.97,85.05,85.64};
	
	private Double [] bbUpperArray = new Double[] 
	        {74.62,74.63,74.66,74.33,74.20,74.07,74.19,74.17,74.55,74.66,75.05,75.46,75.97,76.35,
			76.93,77.44,78.01,78.40,78.52,78.64,78.53,78.40,78.02,77.68,77.50,77.26,77.06,76.76,
			76.57,76.87,77.39,78.06,79.19,79.91,80.51,81.18,81.58,81.75,82.20};

	private Double [] bbLowerArray = new Double[] 
	        {67.37,67.35,67.27,67.28,67.31,67.34,67.33,67.34,67.15,66.99,66.89,66.75,65.55,66.45,66.50,
			66.52,66.73,67.08,67.37,67.80,68.80,69.75,70.80,71.50,72.02,72.58,73.05,73.51,73.29,73.30,
			73.12,72.86,72.27,72.08,71.90,71.72,71.66,71.71,71.89};

	private Double [] smaArray = new Double[] 
	        {69.16,68.89,68.84,68.84,69.25,70.11,70.92,71.58,72.41,
			73.01,73.59,74.04,74.68,74.76,75.01,75.26,75.66,75.86,75.76,75.62,75.70,75.69,75.52,
			75.55,75.41,75.13,74.83,74.47,74.24,74.30,74.90,75.05,77.07,78.43,79.51,80.09,80.30,
			79.85,79.96};


	public SanDisk() 
	{
		super();
	}

	public void setSlope(double slope)
	{
		this.slope = slope;
	}
	public void setPredictList()
	{
		predictList = new ArrayList<Double>();

	}
	public void setClosePrice()
	{	
		closePriceList = new ArrayList<Double>(Arrays.asList(closePriceArray));
	}
	public void setAprilClosePriceList()
	{
		aprilClosePriceList = new ArrayList<Double>(Arrays.asList(aprilClosePriceArray));	
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
