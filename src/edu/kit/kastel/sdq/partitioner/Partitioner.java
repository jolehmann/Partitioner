package edu.kit.kastel.sdq.partitioner;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Interface for Partitioners. All implementations should be registered in
 * the @JsonSubTypes Annotation in the beginning of this, with a name for their
 * type. This is needed for Deserialization to the correct type.
 * 
 * @author Jonas Lehmann
 *
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type", defaultImpl = TrivialPartitioner.class)
@JsonSubTypes({ @JsonSubTypes.Type(value = TrivialPartitioner.class, name = "trivial"),
		@JsonSubTypes.Type(value = LinearSingleElementPartitioner.class, name = "linear") })

public interface Partitioner {

	public String getId();

	/**
	 * If hasNext(): Increments the Pointer of this Partitioner. Else: Resets
	 * isInitialized to False.
	 * 
	 * @return true if set to next, false if reset
	 */
	public boolean setToNextPartitionOrElseReset();

	/**
	 * Returns true if there are more partitions to iterate over.
	 * 
	 * @return true if has next, or false if all partitions are pointed once.
	 */
	public boolean hasNext();

	/**
	 * @return true if the Partitioner already has been in one iteration where it
	 *         has been initialized. false if it is the first iteration.
	 */
	public boolean isInitialized();

	/**
	 * Initializes the Partioner with the whole set of instances, that should be
	 * partitioned to several iteration-sets.
	 * 
	 * @param allInstances the List of all Instances.
	 */
	public void init(List<String> allInstances);

	/**
	 * Should only be called if isInitialized() == true. Else it returns null.
	 * 
	 * @return the partition for the current iteration as a list.
	 */
	public List<String> currentPartition();
}
