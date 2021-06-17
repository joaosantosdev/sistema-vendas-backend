package br.com.ourmind.cursomc.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StringUtil {
	
	public static String decodeParam(String value) {
		try {
			return URLDecoder.decode(value, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}
	
	public static List<Integer> splitInteger(String value){
		if(value == null || value.equals("")) {
			return new ArrayList<Integer>();
		}
		
		return Arrays.stream(value.split(","))
				.map((String v) -> Integer.valueOf(v))
				.collect(Collectors.toList());
	}

}
