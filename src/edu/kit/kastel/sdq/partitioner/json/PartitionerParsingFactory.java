package edu.kit.kastel.sdq.partitioner.json;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import edu.kit.kastel.sdq.partitioner.Partitioner;

public class PartitionerParsingFactory {

	private String configFilePath;

	public PartitionerParsingFactory(String configFilePath) {
		this.configFilePath = configFilePath;
	}

	/**
	 * Loads all Partitioners from the JSon File, specified by the constructor
	 * 
	 * @return a list of the loaded partitioners which may be empty if no
	 *         partitioners are in the file
	 */
	public List<Partitioner> loadPartitioners() {
		PartitionersForJSON partitionersFromJSON = loadFromJSONFile(this.configFilePath);
		return partitionersFromJSON.getPartitioners();
	}

	/**
	 * Saves all Partitioners to the JSon File, specified by the constructor
	 * 
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

	private void writeToJSONFile(final String uri, PartitionersForJSON partitionersForJSON) throws IOException {
		ObjectMapper jacksonMapper = new ObjectMapper();
		jacksonMapper.writeValue(new File(uri), partitionersForJSON);
	}

	private static PartitionersForJSON loadFromJSONFile(String uri) {
		PartitionersForJSON partitionersForJSON = new PartitionersForJSON();

		try {
			ObjectMapper jacksonMapper = new ObjectMapper();
			partitionersForJSON = jacksonMapper.readValue(new File(uri), PartitionersForJSON.class);
		} catch (Exception e) {
			// e.printStackTrace();
		}

		return partitionersForJSON;
	}
}
