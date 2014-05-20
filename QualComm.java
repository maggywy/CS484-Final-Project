import java.util.ArrayList;
import java.util.Arrays;
/* QualComm.java
 * 
 * This is one of the stock that inherits from Stock class. The price data for February & March for
 * close price, upper & lower Bollinger bands, and simple moving average of 20 days of QualComm are 
 * stored in different arraylist variables. The close price data of April is stored in the arraylist too.
 */

public class QualComm extends Stock {

	private Double[] closePriceArray = new Double[] 
	       {73.32,72.51,72.37,73.24,74.41,74.69,75.62,76.42,76.44,76.28,75.6,75.77,75.95,75.61,75.43,
			74.91,75.05,75.19,75.29,73.63,76.11,76.67,77.00,76.79,77.07,76.71,76.97,75.63,74.74,77.02,
			77.45,76.805,78.1,78.19,77.74,78.56,78.31,79.05,79.28};
	  
	private Double[] aprilClosePriceArray = new Double[] 
	       {78.86,80.1,80.14,80.55,78.53,78.08,78.89,79.93,78.07,78.01,79.14,79.49,80.18,81.32,80.93,
			80.61,80.71,77.87,77.61,78.05,78.52,78.71,78.99,78.99};
	
	private Double[] bbUpperArray = new Double[] 
	        {76.13,76.14,76.17,76.16,76.20,76.27,76.50,76.85,77.13,77.44,77.34,77.44,77.48,77.50,77.57,
			77.62,77.57,77.30,77.31,77.33,77.41,77.42,77.35,77.31,77.46,77.53,77.65,77.61,77.57,77.68,
			77.89,77.98,78.29,78.58,78.75,79.02,79.20,79.49,79.78};
	                                          
	private Double[] bbLowerArray = new Double[]
	        {71.50,71.47,71.35,71.33,71.34,71.35,71.30,71.21,71.15,71.16,71.15,71.14,71.43,71.14,71.17,
			71.30,71.66,72.32,72.52,72.4,72.04,73.05,73.58,73.97,74.10,74.23,74.23,74.20,74.07,74.04,
			74.01,74.02,73.93,73.89,73.95,74.05,74.20,74.20,74.40};
	                                          
	private Double[] smaArray = new Double[]
	        {72.78,72.89,73.14,73.13,73.17,73.44,74.07,74.88,75.52,75.89,76.07,76.10,76.01,75.84,
			75.67,75.53,75.39,75.24,75.17,74.81,75.05,75.38,75.74,76.04,76.73,76.85,76.91,76.63,
			76.22,76.21,76.36,76.33,76.82,77.51,77.66,77.88,78.18,78.37,78.59};
	                                      
	                                   

	public QualComm() 
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
