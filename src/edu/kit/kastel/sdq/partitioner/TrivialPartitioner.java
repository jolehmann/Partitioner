package edu.kit.kastel.sdq.partitioner;

import java.util.List;

public class TrivialPartitioner implements Partitioner {

	private String id;
	private List<String> instances;
	private boolean initialized;

	/**
	 * A Partitioner that returns every element at once.
	 * There is only one partition consisting of all elements.
	 * 
	 * @param id the name to reference this partitioner
	 */
	public TrivialPartitioner(String id) {
		this.id = id;
		this.instances = null;
		this.initialized = false;
	}
	
	@Override
	public String getId() {
		return this.id;
	}

	@Override
	public boolean setToNextPartitionOrElseReset() {
		// because there is only one partition this method equals reset().
		this.instances = null;
		this.initialized = false;
		return false;
	}

	@Override
	public boolean hasNext() {
		return false; // only one partition --> never has next.
	}

	@Override
	public boolean isInitialized() {
		return this.initialized;
	}

	@Override
	public void init(List<String> allInstances) {
		this.instances = allInstances;
		this.initialized = true;
	}

	@Override
	public List<String> currentPartition() {
		return this.instances; // could be null if not initialized.
	}
	
	@Override
	public int getNumberOfIncrementationsSinceLastInit() {
		if(!this.initialized) {
			return -1;
		}
		return 0;
	}

	// For JSON Serialization ------------------------------------------
	public List<String> getInstances() {
		return this.instances;
	}
	
	@SuppressWarnings("unused")
	private TrivialPartitioner() {
		// JSON needs this empty constructor
	}

	public TrivialPartitioner(String id, List<String> instances, boolean initialized) {
		this.id = id;
		this.instances = instances;
		this.initialized = initialized;
	}
}
