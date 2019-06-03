package com.tgi.sd.test;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class Sample {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		
		/*Calendar c = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy ");
		String strDate = sdf.format(c.getTime());
        System.out.println(".....Date..."+strDate);*/
		
//		double priceCalc = 0;
//		priceCalc = 1.5250f * Double.parseDouble("299.0");
//		priceCalc = priceCalc *1;
//		String amt = String.format("%.02f",priceCalc);
//		System.out.println(amt);
		//1019,299
		
		float priceCalc2 = 0.00f;
		priceCalc2 = 1.525f * Float.parseFloat("299");
		priceCalc2 = priceCalc2 *1f;
		String amt2 = String.format("%.02f",priceCalc2);
		System.out.println(amt2);
		
		String c = String.valueOf(new BigDecimal(priceCalc2).setScale(2, RoundingMode.CEILING));
		System.out.println(c);

		
//	    try {                                                                                                                                                                                                                                 
//	        final URL url = new URL("http://devmt01.gabrielny.com:7047/");                                                                                                                                                                                 
//	        final URLConnection conn = url.openConnection();                                                                                                                                                                                  
//	        conn.connect();  
//	        System.out.println("net connection ok...");
//	    } catch (MalformedURLException e) {                                                                                                                                                                                                   
//	        System.out.println("---exception :" + e.getMessage());                                                                                                                                                                                                  
//	    } catch (IOException ee) {            
//	    	System.out.println("---io exception :" + ee.getMessage());
//	    }                                        
//	    
        
//        double total = 150.750;
//        String total2 = "Total Value" + String.valueOf(total);
//        double amt = Double.valueOf(total2);
//        System.out.println( amt);
}
	
}
