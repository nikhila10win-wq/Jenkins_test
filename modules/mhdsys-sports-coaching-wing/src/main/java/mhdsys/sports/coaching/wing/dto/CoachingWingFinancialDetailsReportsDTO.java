package mhdsys.sports.coaching.wing.dto;

import java.util.List;

public class CoachingWingFinancialDetailsReportsDTO {
	
	private long districtId;
	private long trainingCenterId;
	private String districtname;
	List<FinancialDetailsDTO> financialTraingData;
	private double total;
	
	public CoachingWingFinancialDetailsReportsDTO(long districtId, long trainingCenterId, String districtname,
			List<FinancialDetailsDTO> financialTraingData) {
		super();
		this.districtId = districtId;
		this.trainingCenterId = trainingCenterId;
		this.districtname = districtname;
		this.financialTraingData = financialTraingData;
	}
	
	public CoachingWingFinancialDetailsReportsDTO() {
	}

	public long getDistrictId() {
		return districtId;
	}
	public void setDistrictId(long districtId) {
		this.districtId = districtId;
	}
	public long getTrainingCenterId() {
		return trainingCenterId;
	}
	public void setTrainingCenterId(long trainingCenterId) {
		this.trainingCenterId = trainingCenterId;
	}
	public String getDistrictname() {
		return districtname;
	}
	public void setDistrictname(String districtname) {
		this.districtname = districtname;
	}
	public List<FinancialDetailsDTO> getFinancialTraingData() {
		return financialTraingData;
	}
	public void setFinancialTraingData(List<FinancialDetailsDTO> financialTraingData) {
		this.financialTraingData = financialTraingData;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return "CoachingWingFinancialDetailsReports [districtId=" + districtId + ", trainingCenterId="
				+ trainingCenterId + ", districtname=" + districtname + ", financialTraingData=" + financialTraingData
				+ "]";
	}
}
