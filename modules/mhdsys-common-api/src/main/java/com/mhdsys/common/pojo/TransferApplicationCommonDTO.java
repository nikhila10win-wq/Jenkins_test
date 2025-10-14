package com.mhdsys.common.pojo;

import java.util.Date;

public class TransferApplicationCommonDTO {
	 private long transferApplicationId;
	    private long grievanceId;
	    private String solutionByHo;
	    private String reviewByHo;

	    private String solutionByDSO;
	    private String reviewByDSO;

	    private String solutionByTSO;
	    private String reviewByTSO;
	    private long transferTo;
	    private String transfer;
	    private long district;
	    private long taluka;
	    private long transferApplication;
	    
	    private long userId;
	    private Date createDate;
	    private Date modifiedDate;
	    
		public long getTransferApplication() {
			return transferApplication;
		}
		public void setTransferApplication(long transferApplication) {
			this.transferApplication = transferApplication;
		}
		public long getDistrict() {
			return district;
		}
		public void setDistrict(long district) {
			this.district = district;
		}
		public long getTaluka() {
			return taluka;
		}
		public void setTaluka(long taluka) {
			this.taluka = taluka;
		}
		public String getTransfer() {
			return transfer;
		}
		public void setTransfer(String transfer) {
			this.transfer = transfer;
		}
		public long getTransferApplicationId() {
			return transferApplicationId;
		}
		public void setTransferApplicationId(long transferApplicationId) {
			this.transferApplicationId = transferApplicationId;
		}
		public long getGrievanceId() {
			return grievanceId;
		}
		public void setGrievanceId(long grievanceId) {
			this.grievanceId = grievanceId;
		}
		
		public String getSolutionByHo() {
			return solutionByHo;
		}
		public void setSolutionByHo(String solutionByHo) {
			this.solutionByHo = solutionByHo;
		}
		public String getReviewByHo() {
			return reviewByHo;
		}
		public void setReviewByHo(String reviewByHo) {
			this.reviewByHo = reviewByHo;
		}
		public String getSolutionByDSO() {
			return solutionByDSO;
		}
		public void setSolutionByDSO(String solutionByDSO) {
			this.solutionByDSO = solutionByDSO;
		}
		public String getReviewByDSO() {
			return reviewByDSO;
		}
		public void setReviewByDSO(String reviewByDSO) {
			this.reviewByDSO = reviewByDSO;
		}
		public String getSolutionByTSO() {
			return solutionByTSO;
		}
		public void setSolutionByTSO(String solutionByTSO) {
			this.solutionByTSO = solutionByTSO;
		}
		public String getReviewByTSO() {
			return reviewByTSO;
		}
		public void setReviewByTSO(String reviewByTSO) {
			this.reviewByTSO = reviewByTSO;
		}
		public long getTransferTo() {
			return transferTo;
		}
		public void setTransferTo(long transferTo) {
			this.transferTo = transferTo;
		}
		public long getUserId() {
			return userId;
		}
		public void setUserId(long userId) {
			this.userId = userId;
		}
		public Date getCreateDate() {
			return createDate;
		}
		public void setCreateDate(Date createDate) {
			this.createDate = createDate;
		}
		public Date getModifiedDate() {
			return modifiedDate;
		}
		public void setModifiedDate(Date modifiedDate) {
			this.modifiedDate = modifiedDate;
		}

}
