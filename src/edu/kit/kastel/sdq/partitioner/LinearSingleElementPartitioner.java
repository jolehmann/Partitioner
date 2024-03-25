package edu.kit.kastel.sdq.partitioner;

import java.util.ArrayList;
import java.util.List;

public class LinearSingleElementPartitioner implements Partitioner {

	private String id;
	private List<String> instances;
	private boolean initialized;
	private int pointer;

	/**
	 * A Partitioner that returns only one element after another.
	 * Each partition consists of only one element.
	 * 
	 * @param id the name to reference this partitioner
	 */
	public LinearSingleElementPartitioner(String id) {
		this.id = id;
		this.reset();
	}
	
	@Override
	public String getId() {
		return this.id;
	}

	@Override
	public boolean setToNextPartitionOrElseReset() {
		if (this.hasNext()) {
			pointer++;
		} else {
			this.reset();
		}
		return this.initialized;
	}

	@Override
	public boolean hasNext() {
		return this.initialized && this.pointer < this.instances.size() - 1;
	}

	@Override
	public boolean isInitialized() {
		return this.initialized;
	}

	@Override
	public void init(List<String> allInstances) {
		this.instances = allInstances;
		this.pointer = 0;
		this.initialized = true;
	}

	@Override
	public List<String> currentPartition() {
		List<String> currentPartition = new ArrayList<String>();
		if (this.initialized) {
			currentPartition.add(this.instances.get(this.pointer));
		}
		return (this.initialized) ? currentPartition : null;
	}

	private void reset() {
		this.instances = null;
		this.initialized = false;
		this.pointer = 0;
	}
	
	@Override
	public int getNumberOfIncrementationsSinceLastInit() {
		if (!this.initialized) {
			return -1;
		}
		return this.pointer;
	}

	// For JSON Serialization ------------------------------------------
	public List<String> getInstances() {
		return this.instances;
	}
	
	public int getPointer() {
		return this.pointer;
	}

	@SuppressWarnings("unused")
	private LinearSingleElementPartitioner() {
		// JSON needs this empty constructor
	}
	
	public LinearSingleElementPartitioner(String id, List<String> instances, boolean initialized, int pointer) {
		this.id = id;
		this.instances = instances;
		this.initialized = initialized;
		this.pointer = pointer;
	}
}
