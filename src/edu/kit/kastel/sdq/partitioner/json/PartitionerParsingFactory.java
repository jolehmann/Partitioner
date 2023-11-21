package edu.kit.kastel.sdq.partitioner.json;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import edu.kit.kastel.sdq.partitioner.LinearSingleElementPartitioner;
import edu.kit.kastel.sdq.partitioner.Partitioner;
import edu.kit.kastel.sdq.partitioner.TrivialPartitioner;

public class PartitionerParsingFactory {

	public static void main (String [] args) {
		
		// for testing purposes
		
		final String URI_OF_RESULT_JSON_FILE = "partitioners.json";
		
		PartitionerParsingFactory ppf = new PartitionerParsingFactory(URI_OF_RESULT_JSON_FILE);
		
		List <Partitioner> partitioners = new ArrayList<Partitioner>();
		
		Partitioner p1 = new TrivialPartitioner("firstP");
		p1.init(new ArrayList<String>(Arrays.asList("Hello", " ", "World")));
		partitioners.add(p1);
		
		Partitioner p2 = new LinearSingleElementPartitioner("secondP");
		p2.init(new ArrayList<String>(Arrays.asList("Hello", " ", "World")));
		p2.setToNextPartitionOrElseReset();
		p2.setToNextPartitionOrElseReset();
		partitioners.add(p2);
		
		ppf.savePartitioners(partitioners);
		List <Partitioner> partitioners2 = ppf.loadPartitioners();
		System.out.println(partitioners2.get(0).currentPartition());
		System.out.println(partitioners2.get(1).currentPartition());
	}
	
	private String configFilePath;

	public PartitionerParsingFactory(String configFilePath) {
		this.configFilePath = configFilePath;
	}

	public List<Partitioner> loadPartitioners() {
		PartitionersForJSON partitionersFromJSON = loadFromJSONFile(this.configFilePath);
		return partitionersFromJSON.getPartitioners();
	}

	/**
	 * @param partitioners a list of Partitioner.
	 * @return true if save-operation was successful.
	 */
	public boolean savePartitioners(List<Partitioner> partitioners) {
		PartitionersForJSON partitionersForJSON = new PartitionersForJSON();
		partitioners.forEach(item -> partitionersForJSON.add(item));
		try {
			this.writeToJSONFile(this.configFilePath, partitionersForJSON);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	private void writeToJSONFile(final String uri, PartitionersForJSON partitionersForJSON)
			throws IOException {
		ObjectMapper jacksonMapper = new ObjectMapper();
		jacksonMapper.writeValue(new File(uri), partitionersForJSON);
	}

	private static PartitionersForJSON loadFromJSONFile(String uri) {
		PartitionersForJSON partitionersForJSON = new PartitionersForJSON();

		try {
			ObjectMapper jacksonMapper = new ObjectMapper();
			partitionersForJSON = jacksonMapper.readValue(new File(uri), PartitionersForJSON.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return partitionersForJSON;
	}
}
