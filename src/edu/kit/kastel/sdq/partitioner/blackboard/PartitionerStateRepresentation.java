package edu.kit.kastel.sdq.partitioner.blackboard;

import java.util.List;

import edu.kit.kastel.sdq.partitioner.Partitioner;

public class PartitionerStateRepresentation {

	/**
	 * Returns a String representation in the format: "1.9.3" <br>
	 * <br>
	 * The first number stands for the outer partitioner and the last number for the
	 * most inner partitioner. <br>
	 * Before initialization the state should be "0.0.0" and then continues with the
	 * first valid iteration as "1.1.1".
	 * 
	 * @param blackboard
	 * @return a String consisting of all partitioner counts separated by dots.
	 */
	public String getCurrentPartitionerRepresentation(PartitionerBlackboard blackboard) {
		String representation = "";

		List<Partitioner> orderedPartitioners = blackboard.getOrderedPartitioners();
		for (Partitioner p : orderedPartitioners) {
			// for each partitioner add its state between 1 and n
			int naturalCount = p.getNumberOfIncrementationsSinceLastInit() + 1;
			representation = "_" + naturalCount + representation;
		}

		return representation.substring(1);
	}
}
