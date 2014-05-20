import java.util.ArrayList;
/* Stock.java
 * This is the parent class of different stocks. Each stock can store the close price on the weekdays,
 * the close price of April, the upper and lower Bollinger bands price, and simple moving average price 
 * of the past twenty days. Child classes can store different price data based on different stocks.
 * The program would use all these price data to analyze and predict stock. There are setters and getters that could set and retrieve these price dat.
 */

public class Stock {

	// Instance variables
	protected double slope;
	protected ArrayList<Double> closePriceList;
	protected ArrayList<Double> aprilClosePriceList;
	protected ArrayList<Double> predictList;
	protected ArrayList<Double> bbUpperList;
	protected ArrayList<Double> bbLowerList;
	protected ArrayList<Double> smaList;

	// Constructor
	public Stock() 
	{
	}

	//Setters
	protected void setSlope()
	{	
	}
	protected void setClosePrice()
	{	
	}
	protected void setAprilClosePriceList()
	{
	}
	protected void setPredictList()
	{
	}
	protected void setBBUpperList()
	{	
	}
	protected void setBBLowerList()
	{	
	}
	protected void setSMAList()
	{	
	}

	// Getters
	protected double getSlope()
	{
		return slope;
	}
	protected ArrayList<Double> getClosePriceList() 
	{
		return closePriceList;
	}
	protected ArrayList<Double> getAprilClosePriceList()
	{
		return aprilClosePriceList;
	}
	protected ArrayList<Double> getPredictList() 
	{
		return predictList;
	}
	protected ArrayList<Double> getBBUpperList() 
	{
		return bbUpperList;
	}
	protected ArrayList<Double> getBBLowerList() 
	{
		return bbLowerList;
	}
	protected ArrayList<Double> getSMAList() 
	{
		return smaList;
	}
}
