package springMVC.bean;

import java.util.List;

public class IPage {
	private int total;
	private List<?> rows;

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<?> getRows() {
		return rows;
	}

	public void setRows(List<?> rows) {
		this.rows = rows;
	}

	public void initPage(List<?> result, int total) {
		this.total = total;
		this.rows = result;
	}
}
