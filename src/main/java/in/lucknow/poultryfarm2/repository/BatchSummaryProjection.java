package in.lucknow.poultryfarm2.repository;

public interface BatchSummaryProjection {
	String getBatchno();

	Integer getQty();

	Integer getTotal_mort();

	Integer getTotal_sold();
}
