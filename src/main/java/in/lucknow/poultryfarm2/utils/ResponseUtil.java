package in.lucknow.poultryfarm2.utils;

import org.springframework.http.ResponseEntity;

public class ResponseUtil {

	public static <T> ResponseEntity<ApiResponse<T>> success(T data) {
		return ResponseEntity.ok(new ApiResponse<>("SUCCESS", 200, "Data Fetched Successfully", data));
	}

	public static <T> ResponseEntity<ApiResponse<T>> failure() {
		// return ResponseEntity.status(204).body(new ApiResponse<>("FAILED", 204, "No
		// Data Found", null));
		return ResponseEntity.ok(new ApiResponse<>("FAILED", 204, "No Record Found", null));
	}
}
