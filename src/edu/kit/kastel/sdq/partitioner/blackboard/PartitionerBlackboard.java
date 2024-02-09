package edu.kit.kastel.sdq.partitioner.blackboard;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.kit.kastel.sdq.partitioner.Partitioner;

public class PartitionerBlackboard {
	
	private Map<String, Partitioner> partitionersMapped;
	private List<Partitioner> partitionersOrdered;

	/**
	 * The first Element in the List should be the one which is incremented until
	 * all partitions of it are visited once. Only after one full circle, the second
	 * Partitioner will be incremented and so on.
	 */
	public void initWithOrderedPartitioners(List<Partitioner> partitioners) {
		this.partitionersOrdered = partitioners;
		this.partitionersMapped = new HashMap<String, Partitioner>();
		this.partitionersOrdered.forEach(p -> this.partitionersMapped.put(p.getId(), p));
	}
	
	public List<Partitioner> getOrderedPartitioners() {
		return this.partitionersOrdered;
	}

	public Partitioner getPartitionerByID(String ID) {
		return this.partitionersMapped.get(ID);
	}
}
