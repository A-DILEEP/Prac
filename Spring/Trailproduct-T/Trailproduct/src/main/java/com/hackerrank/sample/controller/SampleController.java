package com.hackerrank.sample.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.hackerrank.sample.dto.FilteredProducts;
import com.hackerrank.sample.dto.SortedProducts;

@RestController
public class SampleController {

	final String uri = "https://jsonmock.hackerrank.com/api/inventory";
	RestTemplate restTemplate = new RestTemplate();
	String result = restTemplate.getForObject(uri, String.class);
	JSONObject root = new JSONObject(result);

	JSONArray data = root.getJSONArray("data");

//	@CrossOrigin
//	@GetMapping("/filter/discount/{discount_percentage}")
//	private ResponseEntity<ArrayList<FilteredProducts>> filtered_books_by_discount(@PathVariable("discount_percentage") int discount_percentage) {
//		try {
//
//			ArrayList<FilteredProducts> books = new ArrayList<FilteredProducts>();
//			for(int i=0; i< data.length(); i++) {
//				JSONObject jobj = data.getJSONObject(i);
//				int discount = jobj.getInt("discount");
//				if(discount > discount_percentage ) {
//					String barcode = jobj.getString("barcode");
//					books.add( new FilteredProducts(barcode) );
//				}
//			}
//			return new ResponseEntity<ArrayList<FilteredProducts>>(books, HttpStatus.OK);
//		} catch (Exception E) {
//			System.out.println("Error encountered : " + E.getMessage());
//			return new ResponseEntity<ArrayList<FilteredProducts>>(HttpStatus.NOT_FOUND);
//		}
//}

	@CrossOrigin
	@GetMapping("/filter/price/{initial_price}/{final_price}")
	private ResponseEntity<ArrayList<FilteredProducts>> filtered_books(@PathVariable("initial_price") int init_price,
			@PathVariable("final_price") int final_price) {

		try {

			ArrayList<FilteredProducts> books = new ArrayList<FilteredProducts>();
			for(int i=0; i< data.length(); i++) {
				JSONObject jobj = data.getJSONObject(i);
				int price = jobj.getInt("price");
				if(price > init_price && price < final_price) {
					String barcode = jobj.getString("barcode");
					books.add( new FilteredProducts(barcode) );
				}
			}
			if(books.size() > 0)
				return new ResponseEntity<ArrayList<FilteredProducts>>(books, HttpStatus.OK);
			else return new ResponseEntity<ArrayList<FilteredProducts>>(HttpStatus.BAD_REQUEST);

		} catch (Exception E) {
			System.out.println("Error encountered : " + E.getMessage());
			return new ResponseEntity<ArrayList<FilteredProducts>>(HttpStatus.NOT_FOUND);
		}

	}

//	@CrossOrigin
//	@GetMapping("/sort/price")
//	private ResponseEntity<SortedProducts[]> sorted_books() {
//
//		try {
//
//	        JSONObject[] arr = new JSONObject[data.length()];
//	        for (int i = 0; i < data.length(); i++) {
//	            arr[i] = data.getJSONObject(i);
//	        }
//
//	        Arrays.sort(  arr, (o1, o2) -> Double.compare(o1.getDouble("price"), o2.getDouble("price")   )
//	        );
//
//	        SortedProducts[] ans = new SortedProducts[data.length()];
//			for (int i = 0; i < arr.length; i++) {
//				ans[i] = new SortedProducts(arr[i].getString("barcode") );
//			}
//			return new ResponseEntity<SortedProducts[]>(ans, HttpStatus.OK);
//
//		} catch (Exception E) {
//			System.out.println("Error encountered : " + E.getMessage());
//			return new ResponseEntity<SortedProducts[]>(HttpStatus.NOT_FOUND);
//		}
//
//	}
	
	/* 
	 			List<JSONObject> sorted =
	                IntStream.range(0, data.length())
	                         .mapToObj(data::getJSONObject)
	                         .sorted(Comparator.comparingDouble(o -> o.getDouble("price")))
	                         .collect(Collectors.toList()); 
	 */

}
