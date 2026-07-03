package in.lucknow.poultryfarm2.service;

import java.util.List;

import in.lucknow.poultryfarm2.dto.LineMasterDTO;

public interface LineMasterService {
	List<LineMasterDTO> getLineName(String id);
}
