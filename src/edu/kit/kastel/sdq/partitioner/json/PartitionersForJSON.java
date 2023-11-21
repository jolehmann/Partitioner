package edu.kit.kastel.sdq.partitioner.json;

import java.util.ArrayList;
import java.util.List;

import edu.kit.kastel.sdq.partitioner.Partitioner;

public class PartitionersForJSON {
	
	List<Partitioner> partitioners;

	public PartitionersForJSON() {
		this.partitioners = new ArrayList<Partitioner>();
	}

	public PartitionersForJSON(List<Partitioner> partitioners) {
		this.partitioners = partitioners;
	}
	
	public List<Partitioner> getPartitioners() {
		return partitioners;
	}

	public void add(Partitioner partitioner) {
		this.partitioners.add(partitioner);
	}
}
