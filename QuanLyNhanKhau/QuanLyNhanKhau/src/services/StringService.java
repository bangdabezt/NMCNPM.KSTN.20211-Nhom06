/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.text.Normalizer;
import java.util.regex.Pattern;

/**
 *
 * @author Hai
 */
public class StringService {
    
    public static String covertToString(String value) {
        try {
              String temp = Normalizer.normalize(value, Normalizer.Form.NFD);
              Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
              return pattern.matcher(temp).replaceAll("");
        } catch (Exception ex) {
            System.out.println("services.StringService.covertToString()");
            System.out.println(ex.getMessage());
        }
          return null;
    }
    
    public static boolean isNumeric(String string) {
    	int intValue = -1;
    	if(string == null || string.equals("")) {
        
            return false;
        }
        if (string.charAt(0) == '-' || string.charAt(0) == '+') return false;
        try {
            intValue = Integer.parseInt(string);
            return true;
        } catch (NumberFormatException e) {
           
        }
        return false;
    }
    
    public static boolean checkNamHoc(String namHoc) {
    	if (namHoc.length() != 9) return false;
    	if (!isNumeric(namHoc.substring(0, 4))) return false;
    	if (!isNumeric(namHoc.substring(5, 9))) return false;
    	if (namHoc.charAt(4) != '-') return false;
    	if (Integer.parseInt(namHoc.substring(5, 9)) - Integer.parseInt(namHoc.substring(0, 4)) != 1) return false;
		return true;
    	
    }

}
