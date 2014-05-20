import java.text.DecimalFormat;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
/* Calculation.java
 * 
 * The purpose of this class is to retrieve the necessary February & March price data to calculate
 * the predict close price of April for these three stocks: Lennar Corp, San Disk, and QualComm. 
 * The following steps explain how the predict close price is calculated for all stocks:
 * 	1. Calculate the slope--this would allow us to know if the beginning of April's close stock is
 * 		increasing or decreasing.
 * 	2. Calculate the highest price of April--this would allow us to know what is the highest price
 * 		 that the stock could reach.
 * 	3. Calculate the lowest price of April--this would allow us to know what is the highest price
 * 		that the stock could reach.
 * 	4. Calculate the average price difference of uptrend--this would allow us to know how much is the
 * 		stock going upward from the previous close price
 * 	5. Calculate the average price difference of downtrend--this would allow us to know how much is the
 * 		stock going downward from the previous close price
 * Once the project gathered all the calculations, April's close price can be calculated for different
 * stocks.
 */

public class Calculation {

	//Declare class variables
	Lennar lennar = new Lennar();
	SanDisk sanDisk = new SanDisk();
	QualComm qualComm = new QualComm();

	//Declare variables
	private double low, high;
	private double largeBBDiff = 0, bbDiff = 0, smallBBDiff, bbDiffL, bbDiffS, bbDiffQ;;
	private double lowHighAvg;
	private double lowBeforeApril, lowBeforeAprilL,lowBeforeAprilS,lowBeforeAprilQ;
	private double firstHighApril, firstHighAprilL, firstHighAprilS, firstHighAprilQ;
	private double firstLowApril, firstLowAprilL, firstLowAprilS, firstLowAprilQ;
	private double lastClosePriceL, lastClosePriceS,lastClosePriceQ;
	private double avgBeforeHDiffL, avgAfterHDiffL,avgBeforeHDiffS, avgAfterHDiffS,avgBeforeHDiffQ, avgAfterHDiffQ;
	private double totalBeforeHDiff, totalAfterHDiff, avgBeforeHDiff, avgAfterHDiff, lowSMA, highSMA;
	private int lowSMAIndex, highSMAIndex;

	//Constructor
	public Calculation()
	{
		// ****** Lennar Corp. ******
		//Call the setters to set the arraylist of Lennar stock
		lennar.setAprilClosePriceList();
		lennar.setPredictList();
		lennar.setClosePrice();
		lennar.setBBLowerList();
		lennar.setBBUpperList();
		lennar.setSMAList();
		//Find the last close price in March
		lastClosePriceL = lennar.getClosePriceList().get(lennar.getClosePriceList().size()-1);
		//Call calcSlope method to set the slope of Lennar stock
		lennar.setSlope(calcSlope(lastClosePriceL,lennar.getClosePriceList()));
		//Get the lowest close price that is closest to April
		lowBeforeAprilL = getLowBeforeApril();
		//Find the highest close price of April
		firstHighAprilL = calcFirstHighApr(lowBeforeAprilL, lennar.getClosePriceList(),lennar.getBBUpperList(),lennar.getBBUpperList());
		//Get the difference of upper & lower Bollinger bands
		bbDiffL = getBBDiff();
		//Find the lowest close price of April
		firstLowAprilL = calcFirstLowApril(lowBeforeAprilL, firstHighAprilL,bbDiffL);
		//Find the average price difference of uptrend & downtrend
		calcAvgDiff(1,lennar.getSMAList());
		//Get the average price difference of uptrend
		avgBeforeHDiffL = getAvgBeforeHDiff();
		//Get the average price difference of downtrend
		avgAfterHDiffL = getAvgAfterHDiff();
		//Find the close price of April
		calcCloseApril(1,lennar.getSlope(),avgBeforeHDiffL, avgAfterHDiffL, lastClosePriceL,firstHighAprilL,firstLowAprilL,lennar.getPredictList());

		// ****** San Disk ******
		//Call the setters to set the arraylist of San Disk stock
		sanDisk.setAprilClosePriceList();
		sanDisk.setPredictList();
		sanDisk.setClosePrice();
		sanDisk.setBBLowerList();
		sanDisk.setBBUpperList();
		sanDisk.setSMAList();
		//Find the last close price in March
		lastClosePriceS = sanDisk.getClosePriceList().get(sanDisk.getClosePriceList().size()-1);
		//Call calcSlope method to set the slope of San Disk stock
		sanDisk.setSlope(calcSlope(lastClosePriceS,sanDisk.getClosePriceList()));
		//Get the lowest close price that is closest to April
		lowBeforeAprilS = getLowBeforeApril();
		//Find the highest close price of April
		firstHighAprilS = calcFirstHighApr(lowBeforeAprilS, sanDisk.getClosePriceList(),sanDisk.getBBUpperList(),sanDisk.getBBLowerList());
		//Get the difference of upper & lower Bollinger bands
		bbDiffS = getBBDiff();
		//Find the lowest close price of April
		firstLowAprilS = calcFirstLowApril(lowBeforeAprilS,firstHighAprilS,bbDiffS);
		//Find the average price difference of uptrend & downtrend
		calcAvgDiff(2, sanDisk.getSMAList());
		//Get the average price difference of uptrend
		avgBeforeHDiffS = getAvgBeforeHDiff();
		//Get the average price difference of downtrend
		avgAfterHDiffS = getAvgAfterHDiff();
		//Find the close price of April
		calcCloseApril(2,sanDisk.getSlope(),avgBeforeHDiffS, avgAfterHDiffS,lastClosePriceS,firstHighAprilS,firstLowAprilS,sanDisk.getPredictList());

		// ****** QualComm ******
		//Call the setters to set the arraylist of QualComm stock
		qualComm.setAprilClosePriceList();
		qualComm.setPredictList();
		qualComm.setClosePrice();
		qualComm.setBBLowerList();
		qualComm.setBBUpperList();
		qualComm.setSMAList();
		//Find the last close price in March
		lastClosePriceQ = qualComm.getClosePriceList().get(qualComm.getClosePriceList().size()-1);
		//Call calcSlope method to set the slope of Qual Comm stock
		qualComm.setSlope(calcSlope(lastClosePriceQ,qualComm.getClosePriceList()));
		//Get the lowest close price that is closest to April
		lowBeforeAprilQ = getLowBeforeApril();
		//Find the highest close price of April
		firstHighAprilQ = calcFirstHighApr(lowBeforeAprilQ, qualComm.getClosePriceList(),qualComm.getBBUpperList(),qualComm.getBBLowerList());
		//Get the difference of upper & lower Bollinger bands
		bbDiffQ = getBBDiff();
		//Find the lowest close price of April
		firstLowAprilQ = calcFirstLowApril(lowBeforeAprilQ,firstHighAprilQ,bbDiffQ);
		//Find the average price difference of uptrend & downtrend
		calcAvgDiff(2, qualComm.getSMAList());
		//Get the average price difference of uptrend
		avgBeforeHDiffQ = getAvgBeforeHDiff();
		//Get the average price difference of downtrend
		avgAfterHDiffQ = getAvgAfterHDiff();
		//Find the close price of April
		calcCloseApril(3,qualComm.getSlope(),avgBeforeHDiffQ, avgAfterHDiffQ,lastClosePriceQ,firstHighAprilQ,firstLowAprilQ,qualComm.getPredictList());

		//Retrieve all the close price of April for three stocks and output to excel
		generateCsvFile("/PredictPriceDataChart.csv",lennar.getAprilClosePriceList(), lennar.getPredictList(),
				sanDisk.getAprilClosePriceList(),sanDisk.getPredictList(),qualComm.getAprilClosePriceList(),
				qualComm.getPredictList());
	}

	//Round double variables to two decimal places
	double roundTwoDecimals(double d) 
	{
		DecimalFormat twoDecimals = new DecimalFormat("#.##");
		return Double.valueOf(twoDecimals.format(d));
	}

	//Calculate the slope of the stock using slope formula
	//This help to determine if the beginning of the April's close price is going upward or downward
	public double calcSlope(double lastClosePrice, ArrayList<Double> list)
	{
		//Declare variables
		double slope;
		double x1=0, y1=0, x2, y2;
		x2 = list.size();
		y2 = lastClosePrice;

		//Find the lowest price that is closest to April
		for(int i=list.size()-1; i>0; i--)
		{
			if(list.get(i-1)>list.get(i) && list.get(i+1)>list.get(i))
			{
				lowBeforeApril = list.get(i);
				setLowBeforeApril(lowBeforeApril);
				x1=i;
				y1 = lowBeforeApril;
				i=0;
			}
		}
		
		//Use slope formula to calculate the slope, positive slope = 1, negative slope = -1
		slope = (y2-y1) / (x2-x1);
		if(slope > 0)
			slope = 1.0;
		else
			slope = -1.0;
		return slope;		
	}
	
	//Calculate the highest price of April
	//This can help to determine how high the close price can be for April
	//Median is calculated and Bollinger Band's data is used in the calculation
	public double calcFirstHighApr(double lowBeforeApril, ArrayList<Double> list, ArrayList<Double> listU, ArrayList<Double> listL)
	{
		//Declare variables
		low = list.get(0);
		high = list.get(0);

		//Find the lowest & highest close price from Feb to March
		for(int i=0; i<list.size(); i++)
		{
			if(list.get(i)<low)
				low = list.get(i);
			if(list.get(i)>high)
				high = list.get(i);
		}

		//Find the median of highest & lowest close price
		lowHighAvg = (low + high) / 2;
		
		//Find the highest price of April
		if(lowBeforeApril < lowHighAvg)
		{
			//The lowest price closest to April is smaller than the median
			//Set the median to be the highest price of April
			//Lennar stock used this
			firstHighApril = lowHighAvg;
		}
		else
		{
			//Find the largest upper & lower bands difference from Feb to March
			//SanDisk and QualComm used this
			for(int i=0; i<list.size(); i++)
			{
				bbDiff = roundTwoDecimals(listU.get(i) - listL.get(i));

				if(bbDiff > largeBBDiff)
				{
					largeBBDiff = bbDiff;
				}

			}
			
			//Find the last upper & lower bands difference of March
			bbDiff = listU.get(listU.size()-1) - listL.get(listL.size()-1);
			//Find the difference of largest bands difference & last bands difference of March
			smallBBDiff = Math.abs(largeBBDiff - bbDiff);
			//Set the highest price of April to be the addtion of last upper trend price in March 
			//and the difference found on the top
			firstHighApril = roundTwoDecimals(listU.get(listU.size()-1) + smallBBDiff);
			//Set the bands difference and reset largest bands difference
			setBBDiff(bbDiff);
			largeBBDiff = 0;
		}
		return firstHighApril;
	}
	
	//Calculate the lowest close price of April
	//This can help to determine how high the close price can be for April
	public double calcFirstLowApril(double lowBeforeApril, double firstHighApril, double bbDiff)
	{
		//Set the lowest price of April to be the lowest price closest to April
		if(bbDiff==0.00)
			this.firstLowApril = lowBeforeApril;
		//Set the lowest price of April to be the difference of highest price and bands difference
		else
			this.firstLowApril = roundTwoDecimals(firstHighApril - bbDiff);

		return this.firstLowApril;

	}
	
	//Calculate the close price of entire April
	//This method gathers all the previous calculation data to predict the entire April's close price
	public void calcCloseApril(int value, double slope, double closePriceH, double closePriceL,
			double lastClosePrice, double firstHighApril, double firstLowApril, ArrayList<Double> list)
	{
		//Declare variables
		double closePrice = lastClosePrice;
		double closePriceN = roundTwoDecimals((closePriceH + closePriceL)/2);
		boolean decrease = false, increase = false, decrease2 = false, increase2 = true;
		
		//Calculate Lennar Stock's April close price
		if(value ==1)
		{
			//Calculate if slope is positive
			if(slope == 1)
			{
				for(int i=0; i<24; i++)
				{
					//Find the 1st uptrend of the stock
					if(closePrice < firstHighApril && closePrice+closePriceN < firstHighApril && !decrease)
					{
						closePrice += closePriceN;
						list.add(roundTwoDecimals(closePrice));
					}
					else
					{
						//Find the 1st downtrend of the stock
						closePrice -= closePriceL;
						list.add(roundTwoDecimals(closePrice));
						decrease = true;
						//Find the 2nd downtrend of the stock
						if(closePrice < firstLowApril)
						{
							closePrice += closePriceH;

							list.add(roundTwoDecimals(closePrice));
							decrease2 = true;

						}
						//Find the 2nd uptrend of the stock
						else if(decrease && decrease2)
						{
							closePrice += closePriceH;
							list.add(roundTwoDecimals(closePrice));
						}
					}

				}
			}	
			//Calculate if slope is negative
			//The calculation is the opposite of the positive slope on the top
			if(slope == -1)
			{

				for(int i=0; i<24; i++)
				{
					if(closePrice > firstLowApril && closePrice+closePriceN < firstLowApril && !increase)
					{
						closePrice -= closePriceN;
						list.add(roundTwoDecimals(closePrice));
					}
					else
					{
						closePrice -= closePriceL;
						list.add(roundTwoDecimals(closePrice));
						increase = true;
						if(closePrice > firstHighApril)
						{
							closePrice -= closePriceH;

							list.add(roundTwoDecimals(closePrice));
							increase = true;

						}
						else if(increase && increase2)
						{
							closePrice -= closePriceH;
							list.add(roundTwoDecimals(closePrice));
						}
					}
				}
			}
		}
		
		//San Disk stock
		else if(value ==2)
		{

			//Calculate if the slope is positive
			if(slope == 1)
			{
				for(int i=0; i<24; i++)
				{
					//Find the 1st uptrend of the stock
					if(closePrice < firstHighApril && !decrease)
					{
						closePrice += closePriceH;
						list.add(roundTwoDecimals(closePrice));
					}
					else
					{
						//Find the 1st downtrend of the stock
						closePrice -= closePriceH+closePriceL;
						list.add(roundTwoDecimals(closePrice));
						decrease = true;
						//Check to see if it's lower than lowest price of April
						if(closePrice < firstLowApril)
						{
							closePrice += closePriceH+closePriceL;
							list.add(roundTwoDecimals(closePrice));
							decrease = false;
							decrease2 = true;

						}
						//Find the 2nd downtrend of the stock
						else if(decrease2)
						{
							closePrice += closePriceH+closePriceL;
							list.add(roundTwoDecimals(closePrice));
						}
						//Decrease the stock price if it's neither of above's choices
						else
						{
							closePrice -= closePriceH+closePriceL;
							list.add(roundTwoDecimals(closePrice));

						}
					}

				}
			}
			//Calculate if slope is negative
			//The calculation is the opposite of the positive slope on the top
			if(slope == -1)
			{
				for(int i=0; i<24; i++)
				{

					if(closePrice > firstLowApril && !increase)
					{
						closePrice -= closePriceH;
						list.add(roundTwoDecimals(closePrice));
					}
					else
					{
						closePrice += closePriceH+closePriceL;
						list.add(roundTwoDecimals(closePrice));
						increase = true;
						if(closePrice > firstHighApril)
						{
							closePrice -= closePriceH+closePriceL;

							list.add(roundTwoDecimals(closePrice));
							increase = false;
							increase2 = true;

						}
						else if(increase2)
						{


							closePrice -= closePriceH+closePriceL;
							list.add(roundTwoDecimals(closePrice));
						}
						else
						{
							closePrice += closePriceH+closePriceL;
							list.add(roundTwoDecimals(closePrice));

						}
					}
				}
			}
		}
		
		//QualComm Stock
		else if(value == 3)
		{
			if(slope == 1)
			{
				for(int i=0; i<24; i++)
				{
					//Find the 1st uptrend of the stock
					if(closePrice < firstHighApril && closePrice+closePriceL < firstHighApril && !decrease)
					{
						closePrice += closePriceL;
						list.add(roundTwoDecimals(closePrice));
					}
					else
					{
						//Find the 1st downtrend of the stock
						closePrice -= closePriceH;
						list.add(roundTwoDecimals(closePrice));
						decrease = true;
						//Increase the stock price if it's smaller than the lowest close price
						if(closePrice < firstLowApril)
						{
							closePrice += closePriceH;
							list.add(roundTwoDecimals(closePrice));

						}
						//Decrease the stock price otherwise
						else
						{
							closePrice -= closePriceL;
							list.add(roundTwoDecimals(closePrice));
							decrease = false;

						}
					}
				}
			}
		}
		//Calculate if slope is negative
		//The calculation is the opposite of the positive slope on the top
		if(slope == -1)
		{
			for(int i=0; i<24; i++)
			{

				if(closePrice > firstLowApril && closePrice+closePriceL > firstLowApril && !increase)
				{
					closePrice -= closePriceL;
					list.add(roundTwoDecimals(closePrice));
				}
				else
				{
					closePrice += closePriceH;
					list.add(roundTwoDecimals(closePrice));
					increase = true;
					if(closePrice > firstHighApril)
					{
						closePrice -= closePriceH;
						list.add(roundTwoDecimals(closePrice));

					}
					else
					{
						closePrice += closePriceL;
						list.add(roundTwoDecimals(closePrice));
						increase = false;
					}
				}
			}
		}
	}

	//Calculate the average difference of uptrend & downtrend
	//This help to determine how much is the stock price going up or down for the next day
	//Simple moving average (SMA) data is used
	public void calcAvgDiff(int value, ArrayList<Double> list)
	{
		//Declare variables
		highSMA = list.get(0);
		lowSMA = 100;

		//Lennar stock
		if(value == 1)
		{
			//Find the highest SMA price
			for(int i=0; i<list.size(); i++)
			{
				if(highSMA<list.get(i))
				{
					highSMA = list.get(i);
					highSMAIndex = i;
				}
			}
			//Find the lowest SMA price
			for(int i=highSMAIndex; i<list.size(); i++)
			{

				if(lowSMA>list.get(i))
				{
					lowSMA = list.get(i);
					lowSMAIndex = i;
				}
			}
		}
		
		//San Disk and QualComm stock
		if(value == 2)
		{
			//Find the 1st highest SMA price
			for(int i=0; i<list.size(); i++)
			{
				if(highSMA<list.get(i) && list.get(i) > list.get(i+1) && list.get(i+1)>list.get(i+2))
				{
					highSMA = list.get(i);
					highSMAIndex = i;
					i = list.size();
				}
			}
			//Find the 1st lowest SMA price after the highest SMA price
			for(int i=highSMAIndex; i<list.size(); i++)
			{
				if(lowSMA>list.get(i) && list.get(i) < list.get(i+1) && list.get(i+1)<list.get(i+2))
				{
					lowSMA = list.get(i);
					lowSMAIndex = i;
					i = list.size();
				}
			}			
		}

		//Accumulate the difference of SMA price
		for(int i=0; i<list.size()-1; i++)
		{
			//Find the accumulated SMA price before the highest SMA price
			if(i<highSMAIndex)
			{
				totalBeforeHDiff += roundTwoDecimals(Math.abs(list.get(i+1) - list.get(i)));

			}
			//Find the accumulated SMA price after the highest SMA price and before the lowest
			//SMA price
			if(i>highSMAIndex && i<lowSMAIndex)
			{
				totalAfterHDiff += roundTwoDecimals(Math.abs(list.get(i+1) - list.get(i)));
			}
		}
		//Find the average of the accumulated SMA price for before the highest price
		avgBeforeHDiff = roundTwoDecimals(totalBeforeHDiff/(highSMAIndex/2));
		//Find the average of the accumulated SMA price for after the highest SMA price and before 
		//the lowest SMA price
		avgAfterHDiff = roundTwoDecimals(totalAfterHDiff/(lowSMAIndex/2));
	}
	
	//This method retrieves all the data for both real and predicted April's stock price
	//Outputs are executed to csv file
	public void generateCsvFile(String sFileName, ArrayList<Double> lennarAList, 
			ArrayList<Double> lennarPList, ArrayList<Double> sanDiskAList,
			ArrayList<Double> sanDiskPList, ArrayList<Double> qualCommAList,
			ArrayList<Double> qualCommPList)
	{
		try
		{
			FileWriter writer = new FileWriter(sFileName);

			//Output Title for Lennar corp.
			writer.append("Lennar Corp");
			writer.append("\n");
			writer.append("Date");
			writer.append(",");
			writer.append("April Close Price");
			writer.append(",");
			writer.append("Predict April Close Price");
			writer.append(",");
			writer.append("% Error");
			writer.append("\n");

			//Output data for Lennar corp.
			for(int i=0;i<22; i++)
			{
				if(i==0)
				{
					writer.append("3/31");
					writer.append(",");
				}
				else if(i>0 && i<5)
				{
					writer.append("4/" + Integer.toString(i));
					writer.append(",");
				}
				else if(i>=5 && i<10)
				{
					writer.append("4/" + Integer.toString(i+2));
					writer.append(",");
				}
				else if(i>=10 && i<14)
				{
					writer.append("4/" + Integer.toString(i+4));
					writer.append(",");
				}
				else if(i>=14 && i<19)
				{
					writer.append("4/" + Integer.toString(i+7));
					writer.append(",");
				}
				else if(i>=19 && i<24)
				{
					writer.append("4/" + Integer.toString(i+9));
					writer.append(",");
				}
				writer.append(Double.toString(lennarAList.get(i)));
				writer.append(",");
				writer.append(Double.toString(lennarPList.get(i)));
				writer.append(",");
				writer.append(Double.toString(
						roundTwoDecimals(Math.abs((lennarPList.get(i)-lennarAList.get(i))/lennarAList.get(i))*100.00)));
				writer.append("\n");
			}

			//Output Title for San Disk
			writer.append("\n\nSanDisk");
			writer.append("\n");
			writer.append("Date");
			writer.append(",");
			writer.append("April Close Price");
			writer.append(",");
			writer.append("Predict April Close Price");
			writer.append(",");
			writer.append("% Error");
			writer.append("\n");

			//Output data for San Disk
			for(int i=0;i<22; i++)
			{
				if(i==0)
				{
					writer.append("3/31");
					writer.append(",");
				}
				else if(i>0 && i<5)
				{
					writer.append("4/" + Integer.toString(i));
					writer.append(",");
				}
				else if(i>=5 && i<10)
				{
					writer.append("4/" + Integer.toString(i+2));
					writer.append(",");
				}
				else if(i>=10 && i<14)
				{
					writer.append("4/" + Integer.toString(i+4));
					writer.append(",");
				}
				else if(i>=14 && i<19)
				{
					writer.append("4/" + Integer.toString(i+7));
					writer.append(",");
				}
				else if(i>=19 && i<24)
				{
					writer.append("4/" + Integer.toString(i+9));
					writer.append(",");
				}
				writer.append(Double.toString(sanDiskAList.get(i)));
				writer.append(",");
				writer.append(Double.toString(sanDiskPList.get(i)));
				writer.append(",");
				writer.append(Double.toString(
						roundTwoDecimals(Math.abs((sanDiskPList.get(i)-sanDiskAList.get(i))/sanDiskAList.get(i))*100.00)));
				writer.append("\n");
			}

			//Output Title for QualComm
			writer.append("\n\nQualComm");
			writer.append("\n");
			writer.append("Date");
			writer.append(",");
			writer.append("April Close Price");
			writer.append(",");
			writer.append("Predict April Close Price");
			writer.append(",");
			writer.append("% Error");
			writer.append("\n");

			//Output data for QualComm 
			for(int i=0;i<22; i++)
			{
				if(i==0)
				{
					writer.append("3/31");
					writer.append(",");
				}
				else if(i>0 && i<5)
				{
					writer.append("4/" + Integer.toString(i));
					writer.append(",");
				}
				else if(i>=5 && i<10)
				{
					writer.append("4/" + Integer.toString(i+2));
					writer.append(",");
				}
				else if(i>=10 && i<14)
				{
					writer.append("4/" + Integer.toString(i+4));
					writer.append(",");
				}
				else if(i>=14 && i<19)
				{
					writer.append("4/" + Integer.toString(i+7));
					writer.append(",");
				}
				else if(i>=19 && i<24)
				{
					writer.append("4/" + Integer.toString(i+9));
					writer.append(",");
				}
				writer.append(Double.toString(qualCommAList.get(i)));
				writer.append(",");
				writer.append(Double.toString(qualCommPList.get(i)));
				writer.append(",");
				writer.append(Double.toString(
						roundTwoDecimals(Math.abs((qualCommPList.get(i)-qualCommAList.get(i))/qualCommAList.get(i))*100.00)));
				writer.append("\n");
			}

			writer.flush();
			writer.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		} 
	}
	
	//Getters for the class
	public double getLowBeforeApril()
	{
		return lowBeforeApril;
	}
	public double getAvgBeforeHDiff()
	{
		return avgBeforeHDiff;
	}
	public double getAvgAfterHDiff()
	{
		return avgAfterHDiff;
	}
	public void setBBDiff(double bbDiff)
	{
		this.bbDiff = bbDiff;
	}
	public void setLowBeforeApril(double lowBeforeApril)
	{
		this.lowBeforeApril = lowBeforeApril;
	}
	public double getBBDiff()
	{
		return bbDiff;
	}
	public double getLow()
	{
		return low;
	}
	public double getHigh()
	{
		return high;
	}
	public double getLowBefore()
	{
		return firstLowAprilQ;
	}

}
