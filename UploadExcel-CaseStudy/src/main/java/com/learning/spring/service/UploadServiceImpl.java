package com.learning.spring.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.learning.spring.util.UploadUtil;

@Service
public class UploadServiceImpl implements UploadService{
	
	private UploadUtil uploadUtil;
	
	public UploadServiceImpl(UploadUtil uploadUtil) {
		super();
		this.uploadUtil = uploadUtil;
	}


	@Override
	public List<Map<String, String>> upload(MultipartFile file) throws IOException {
		
		
		
		Path tempDir = Files.createTempDirectory("");

//		Creating a temp file copy of the orignal file
		File tempFile = tempDir.resolve(file.getOriginalFilename()).toFile();
//		transfering MultiPartfile to tempFile
		file.transferTo(tempFile);
//		Creating a workbook of the tempfile which is basically the full excel/ it's an iterable sheet
		Workbook workbook = WorkbookFactory.create(tempFile);
//		Taking the first sheet from the excel/ A sheet is basically an iterable rows.
		Sheet sheet = workbook.getSheetAt(0);
		
//		Making a Stream of Rows
		Supplier<Stream<Row>> rowStreamSupplier = uploadUtil.getRowStreamSupplier(sheet);
		
		Row headerRow = rowStreamSupplier.get().findFirst().get();
//		Get the header row for column names for creating maps
		List<String> headerCells = StreamSupport.stream(headerRow.spliterator(), false).map(Cell::getStringCellValue).collect(Collectors.toList());
		
		System.out.println(headerCells);
		
		int colCount = headerCells.size();
		
		return rowStreamSupplier.get().skip(1).map(row->{
//			given a row get a Cell stream out of it
			
			List<String> cellList = StreamSupport.stream(row.spliterator(), false).map(Cell::getStringCellValue).collect(Collectors.toList());

			return IntStream.range(0, colCount)
					 .boxed()
					 .collect(Collectors.toMap(index -> headerCells.get(index),
							 				   index ->cellList.get(index)));
			 
			
		})
		.collect(Collectors.toList());
		
	}

}
