package com.mhdsys.common.pojo;

public class AdministrativeApprovalDTO {
    private String administrativeApproval;
    private String dateOfApproval;
    private String approvalAmount;

    // Getters and setters
    public String getAdministrativeApproval() {
        return administrativeApproval;
    }

    public void setAdministrativeApproval(String administrativeApproval) {
        this.administrativeApproval = administrativeApproval;
    }

    public String getDateOfApproval() {
        return dateOfApproval;
    }

    public void setDateOfApproval(String dateOfApproval) {
        this.dateOfApproval = dateOfApproval;
    }

    public String getApprovalAmount() {
        return approvalAmount;
    }

    public void setApprovalAmount(String approvalAmount) {
        this.approvalAmount = approvalAmount;
    }

	@Override
	public String toString() {
		return "AdministrativeApprovalDTO [administrativeApproval=" + administrativeApproval + ", dateOfApproval="
				+ dateOfApproval + ", approvalAmount=" + approvalAmount + "]";
	}
    
    
}

