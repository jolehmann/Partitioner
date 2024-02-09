package edu.kit.kastel.sdq.partitioner.blackboard;

import java.util.List;

import edu.kit.kastel.sdq.partitioner.Partitioner;

public class PartitionerIncrementer {

	/**
	 * Increments the first Partitioner in the Blackboards List, which should be the most inner
	 * loop. If this Partitioner.hasNext() it is incremented and no other. Else, it
	 * is reset and the next outer Partitioner is incremented. The third Partitioner
	 * is only incremented if the second resets after a full loop, and so on. If the
	 * most outer Partitioner reaches the point where hasNext() returns false, this
	 * shows that all iterable partitions are visited once and the process would
	 * restart from the beginning.
	 * 
	 * @return true while not all partitions have been visited.
	 */
	public boolean incrementPartitionersOfBlackboard(PartitionerBlackboard blackboard) {

		boolean incrementNext = true;
		List<Partitioner> orderedPartitioners = blackboard.getOrderedPartitioners();

		for (int i = 0; i < orderedPartitioners.size(); i++) {
			if (incrementNext) {
				incrementNext = !orderedPartitioners.get(i).setToNextPartitionOrElseReset();
			} else {
				break;
			}
		}
		// If the most outer partitioner has reached its last partition
		// and sets incrementNext to true,
		// it is the only way the loop terminates with incrementNext == true.
		// In this case the negated value indicates the end with return false.

		return !incrementNext;
	}

}
