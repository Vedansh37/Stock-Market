package com.learning.spring.util;

import java.util.function.Supplier;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Component;



@Component
public class UploadUtil {

	public Supplier<Stream<Row>> getRowStreamSupplier(Iterable<Row> rows){
		
		return ()->getStream(rows);
	}
	
	public <T> Stream<Row> getStream(Iterable<Row> iterable){
		return StreamSupport.stream(iterable.spliterator(), false);
	}
	
//	public Stream<Cell> getCellStream(Iterable<Cell> cells){
//		return StreamSupport.stream(cells.spliterator(), false);
//	}
}
