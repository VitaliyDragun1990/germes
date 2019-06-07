package org.itsimulator.germes.app.model.search.criteria.range;

import org.itsimulator.germes.app.infra.util.Checks;

/**
 * Pagination parameters for data retrieval operations
 * 
 * @author Vitaly Dragun
 *
 */
public class RangeCriteria {

	/**
	 * Page index(0-based)
	 */
	private final int page;

	/**
	 * Number of elements per page
	 */
	private final int rowCount;

	public RangeCriteria(int page, int rowCount) {
		Checks.checkParam(page >= 0, "Incorrect page index: " + page);
		Checks.checkParam(rowCount >= 0, "Incorrect row count: " + rowCount);

		this.page = page;
		this.rowCount = rowCount;
	}

	public int getPage() {
		return page;
	}

	public int getRowCount() {
		return rowCount;
	}

}
