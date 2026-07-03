package in.lucknow.poultryfarm2.utils;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ListContainer {
	@JsonProperty("Result")
	private List<?> report;

	public List<?> getReport() {
		return report;
	}

	public void setReport(List<?> report) {
		this.report = report;
	}
}
