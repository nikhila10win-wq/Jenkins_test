package com.mhdsys.common.pojo;

import java.util.Date;

public class AspiringAthleteCommonDTO {
	  // Personal Details
	private long aspiringAthleteId;
    private String nameOfApplicant;
    private String address;
    private long pinCode;
    private long contactNumber;
    private String emailId;
    private long domicileFile;
    private String domicileFileName;
	private String domicileFileURL;

    // Sport Details
    private String sportName;
    private String sportType;
    private String competitionLevel;
    private String competitionName;
    private String placeOfCompetition;
    private Date competitionDate;
    private String competitionRank;
    private String medalAchieved;

    // Previous Competition Details
    private String prevCompetitionName;
    private String prevPlace;
    private long pastCompetitionYear;
    private Date pastCompetitionDate;
    private String pastCompetitionRank;
    private long selectionLetterFile;
    private String selectionLetterFileName;
	private String selectionLetterFileURL;
    private long selectionCertificateFile;
    private String selectionCertificateFileName;
	private String selectionCertificateFileURL;
    private long newRecordCertificateFile;
    private String newRecordCertificateFileName;
	private String newRecordCertificateFileURL;
    private String grantedSport;
    private String grantedSportName;

    // Financial Details
    private String travelCost;
    private String entryFees;
    private String hotelFees;
    private String mealCost;
    private String preCompetitionTrainingFees;
    private String trainingEquipmentCost;
    private String guidanceFeeFromExpert;
    private String trainingFee;
    private String costOfImportSportEquipment;
    private String totalCost;

    // Bank Details
    private String bankName;
    private String ifscCode;
    private long accountNumber;
    private long bankStatement;
    private String bankStatementName;
	private String bankStatementURL;

    // Declaration
    private long affidavit;
    private String affidavitName;
	private String affidavitURL;
    private long affidavitBondpaper;
    private String affidavitBondpaperName;
	private String affidavitBondpaperURL;
    private boolean isDeclarationAccepted;
    
    private long userId;
    private Date createDate;
    private Date modifiedDate;
    private String createDateStr;
    private String modifiedDateStr;
    
	public long getAspiringAthleteId() {
		return aspiringAthleteId;
	}
	public void setAspiringAthleteId(long aspiringAthleteId) {
		this.aspiringAthleteId = aspiringAthleteId;
	}
	public String getNameOfApplicant() {
		return nameOfApplicant;
	}
	public void setNameOfApplicant(String nameOfApplicant) {
		this.nameOfApplicant = nameOfApplicant;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public long getPinCode() {
		return pinCode;
	}
	public void setPinCode(long pinCode) {
		this.pinCode = pinCode;
	}
	public long getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(long contactNumber) {
		this.contactNumber = contactNumber;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public long getDomicileFile() {
		return domicileFile;
	}
	public void setDomicileFile(long domicileFile) {
		this.domicileFile = domicileFile;
	}
	public String getDomicileFileName() {
		return domicileFileName;
	}
	public void setDomicileFileName(String domicileFileName) {
		this.domicileFileName = domicileFileName;
	}
	public String getDomicileFileURL() {
		return domicileFileURL;
	}
	public void setDomicileFileURL(String domicileFileURL) {
		this.domicileFileURL = domicileFileURL;
	}
	public String getSportName() {
		return sportName;
	}
	public void setSportName(String sportName) {
		this.sportName = sportName;
	}
	public String getSportType() {
		return sportType;
	}
	public void setSportType(String sportType) {
		this.sportType = sportType;
	}
	public String getCompetitionLevel() {
		return competitionLevel;
	}
	public void setCompetitionLevel(String competitionLevel) {
		this.competitionLevel = competitionLevel;
	}
	public String getCompetitionName() {
		return competitionName;
	}
	public void setCompetitionName(String competitionName) {
		this.competitionName = competitionName;
	}
	public String getPlaceOfCompetition() {
		return placeOfCompetition;
	}
	public void setPlaceOfCompetition(String placeOfCompetition) {
		this.placeOfCompetition = placeOfCompetition;
	}
	public Date getCompetitionDate() {
		return competitionDate;
	}
	public void setCompetitionDate(Date competitionDate) {
		this.competitionDate = competitionDate;
	}
	public String getCompetitionRank() {
		return competitionRank;
	}
	public void setCompetitionRank(String competitionRank) {
		this.competitionRank = competitionRank;
	}
	public String getMedalAchieved() {
		return medalAchieved;
	}
	public void setMedalAchieved(String medalAchieved) {
		this.medalAchieved = medalAchieved;
	}
	public String getPrevCompetitionName() {
		return prevCompetitionName;
	}
	public void setPrevCompetitionName(String prevCompetitionName) {
		this.prevCompetitionName = prevCompetitionName;
	}
	public String getPrevPlace() {
		return prevPlace;
	}
	public void setPrevPlace(String prevPlace) {
		this.prevPlace = prevPlace;
	}
	public long getPastCompetitionYear() {
		return pastCompetitionYear;
	}
	public void setPastCompetitionYear(long pastCompetitionYear) {
		this.pastCompetitionYear = pastCompetitionYear;
	}
	public Date getPastCompetitionDate() {
		return pastCompetitionDate;
	}
	public void setPastCompetitionDate(Date pastCompetitionDate) {
		this.pastCompetitionDate = pastCompetitionDate;
	}
	public String getPastCompetitionRank() {
		return pastCompetitionRank;
	}
	public void setPastCompetitionRank(String pastCompetitionRank) {
		this.pastCompetitionRank = pastCompetitionRank;
	}
	public long getSelectionLetterFile() {
		return selectionLetterFile;
	}
	public void setSelectionLetterFile(long selectionLetterFile) {
		this.selectionLetterFile = selectionLetterFile;
	}
	public String getSelectionLetterFileName() {
		return selectionLetterFileName;
	}
	public void setSelectionLetterFileName(String selectionLetterFileName) {
		this.selectionLetterFileName = selectionLetterFileName;
	}
	public String getSelectionLetterFileURL() {
		return selectionLetterFileURL;
	}
	public void setSelectionLetterFileURL(String selectionLetterFileURL) {
		this.selectionLetterFileURL = selectionLetterFileURL;
	}
	public long getSelectionCertificateFile() {
		return selectionCertificateFile;
	}
	public void setSelectionCertificateFile(long selectionCertificateFile) {
		this.selectionCertificateFile = selectionCertificateFile;
	}
	public String getSelectionCertificateFileName() {
		return selectionCertificateFileName;
	}
	public void setSelectionCertificateFileName(String selectionCertificateFileName) {
		this.selectionCertificateFileName = selectionCertificateFileName;
	}
	public String getSelectionCertificateFileURL() {
		return selectionCertificateFileURL;
	}
	public void setSelectionCertificateFileURL(String selectionCertificateFileURL) {
		this.selectionCertificateFileURL = selectionCertificateFileURL;
	}
	public long getNewRecordCertificateFile() {
		return newRecordCertificateFile;
	}
	public void setNewRecordCertificateFile(long newRecordCertificateFile) {
		this.newRecordCertificateFile = newRecordCertificateFile;
	}
	public String getNewRecordCertificateFileName() {
		return newRecordCertificateFileName;
	}
	public void setNewRecordCertificateFileName(String newRecordCertificateFileName) {
		this.newRecordCertificateFileName = newRecordCertificateFileName;
	}
	public String getNewRecordCertificateFileURL() {
		return newRecordCertificateFileURL;
	}
	public void setNewRecordCertificateFileURL(String newRecordCertificateFileURL) {
		this.newRecordCertificateFileURL = newRecordCertificateFileURL;
	}
	public String getGrantedSport() {
		return grantedSport;
	}
	public void setGrantedSport(String grantedSport) {
		this.grantedSport = grantedSport;
	}
	public String getGrantedSportName() {
		return grantedSportName;
	}
	public void setGrantedSportName(String grantedSportName) {
		this.grantedSportName = grantedSportName;
	}
	public String getTravelCost() {
		return travelCost;
	}
	public void setTravelCost(String travelCost) {
		this.travelCost = travelCost;
	}
	public String getEntryFees() {
		return entryFees;
	}
	public void setEntryFees(String entryFees) {
		this.entryFees = entryFees;
	}
	public String getHotelFees() {
		return hotelFees;
	}
	public void setHotelFees(String hotelFees) {
		this.hotelFees = hotelFees;
	}
	public String getMealCost() {
		return mealCost;
	}
	public void setMealCost(String mealCost) {
		this.mealCost = mealCost;
	}
	public String getPreCompetitionTrainingFees() {
		return preCompetitionTrainingFees;
	}
	public void setPreCompetitionTrainingFees(String preCompetitionTrainingFees) {
		this.preCompetitionTrainingFees = preCompetitionTrainingFees;
	}
	public String getTrainingEquipmentCost() {
		return trainingEquipmentCost;
	}
	public void setTrainingEquipmentCost(String trainingEquipmentCost) {
		this.trainingEquipmentCost = trainingEquipmentCost;
	}
	public String getGuidanceFeeFromExpert() {
		return guidanceFeeFromExpert;
	}
	public void setGuidanceFeeFromExpert(String guidanceFeeFromExpert) {
		this.guidanceFeeFromExpert = guidanceFeeFromExpert;
	}
	public String getTrainingFee() {
		return trainingFee;
	}
	public void setTrainingFee(String trainingFee) {
		this.trainingFee = trainingFee;
	}
	public String getCostOfImportSportEquipment() {
		return costOfImportSportEquipment;
	}
	public void setCostOfImportSportEquipment(String costOfImportSportEquipment) {
		this.costOfImportSportEquipment = costOfImportSportEquipment;
	}
	public String getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(String totalCost) {
		this.totalCost = totalCost;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getIfscCode() {
		return ifscCode;
	}
	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}
	public long getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}
	public long getBankStatement() {
		return bankStatement;
	}
	public void setBankStatement(long bankStatement) {
		this.bankStatement = bankStatement;
	}
	public String getBankStatementName() {
		return bankStatementName;
	}
	public void setBankStatementName(String bankStatementName) {
		this.bankStatementName = bankStatementName;
	}
	public String getBankStatementURL() {
		return bankStatementURL;
	}
	public void setBankStatementURL(String bankStatementURL) {
		this.bankStatementURL = bankStatementURL;
	}
	public long getAffidavit() {
		return affidavit;
	}
	public void setAffidavit(long affidavit) {
		this.affidavit = affidavit;
	}
	public String getAffidavitName() {
		return affidavitName;
	}
	public void setAffidavitName(String affidavitName) {
		this.affidavitName = affidavitName;
	}
	public String getAffidavitURL() {
		return affidavitURL;
	}
	public void setAffidavitURL(String affidavitURL) {
		this.affidavitURL = affidavitURL;
	}
	public long getAffidavitBondpaper() {
		return affidavitBondpaper;
	}
	public void setAffidavitBondpaper(long affidavitBondpaper) {
		this.affidavitBondpaper = affidavitBondpaper;
	}
	public String getAffidavitBondpaperName() {
		return affidavitBondpaperName;
	}
	public void setAffidavitBondpaperName(String affidavitBondpaperName) {
		this.affidavitBondpaperName = affidavitBondpaperName;
	}
	public String getAffidavitBondpaperURL() {
		return affidavitBondpaperURL;
	}
	public void setAffidavitBondpaperURL(String affidavitBondpaperURL) {
		this.affidavitBondpaperURL = affidavitBondpaperURL;
	}
	
	public boolean isDeclarationAccepted() {
		return isDeclarationAccepted;
	}
	public void setDeclarationAccepted(boolean isDeclarationAccepted) {
		this.isDeclarationAccepted = isDeclarationAccepted;
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
	public String getCreateDateStr() {
		return createDateStr;
	}
	public void setCreateDateStr(String createDateStr) {
		this.createDateStr = createDateStr;
	}
	public String getModifiedDateStr() {
		return modifiedDateStr;
	}
	public void setModifiedDateStr(String modifiedDateStr) {
		this.modifiedDateStr = modifiedDateStr;
	}
	@Override
	public String toString() {
		return "AspiringAthleteCommonDTO [aspiringAthleteId=" + aspiringAthleteId + ", nameOfApplicant="
				+ nameOfApplicant + ", address=" + address + ", pinCode=" + pinCode + ", contactNumber=" + contactNumber
				+ ", emailId=" + emailId + ", domicileFile=" + domicileFile + ", domicileFileName=" + domicileFileName
				+ ", domicileFileURL=" + domicileFileURL + ", sportName=" + sportName + ", sportType=" + sportType
				+ ", competitionLevel=" + competitionLevel + ", competitionName=" + competitionName
				+ ", placeOfCompetition=" + placeOfCompetition + ", competitionDate=" + competitionDate
				+ ", competitionRank=" + competitionRank + ", medalAchieved=" + medalAchieved + ", prevCompetitionName="
				+ prevCompetitionName + ", prevPlace=" + prevPlace + ", pastCompetitionYear=" + pastCompetitionYear
				+ ", pastCompetitionDate=" + pastCompetitionDate + ", pastCompetitionRank=" + pastCompetitionRank
				+ ", selectionLetterFile=" + selectionLetterFile + ", selectionLetterFileName="
				+ selectionLetterFileName + ", selectionLetterFileURL=" + selectionLetterFileURL
				+ ", selectionCertificateFile=" + selectionCertificateFile + ", selectionCertificateFileName="
				+ selectionCertificateFileName + ", selectionCertificateFileURL=" + selectionCertificateFileURL
				+ ", newRecordCertificateFile=" + newRecordCertificateFile + ", newRecordCertificateFileName="
				+ newRecordCertificateFileName + ", newRecordCertificateFileURL=" + newRecordCertificateFileURL
				+ ", grantedSport=" + grantedSport + ", grantedSportName=" + grantedSportName + ", travelCost="
				+ travelCost + ", entryFees=" + entryFees + ", hotelFees=" + hotelFees + ", mealCost=" + mealCost
				+ ", preCompetitionTrainingFees=" + preCompetitionTrainingFees + ", trainingEquipmentCost="
				+ trainingEquipmentCost + ", guidanceFeeFromExpert=" + guidanceFeeFromExpert + ", trainingFee="
				+ trainingFee + ", costOfImportSportEquipment=" + costOfImportSportEquipment + ", totalCost="
				+ totalCost + ", bankName=" + bankName + ", ifscCode=" + ifscCode + ", accountNumber=" + accountNumber
				+ ", bankStatement=" + bankStatement + ", bankStatementName=" + bankStatementName
				+ ", bankStatementURL=" + bankStatementURL + ", affidavit=" + affidavit + ", affidavitName="
				+ affidavitName + ", affidavitURL=" + affidavitURL + ", affidavitBondpaper=" + affidavitBondpaper
				+ ", affidavitBondpaperName=" + affidavitBondpaperName + ", affidavitBondpaperURL="
				+ affidavitBondpaperURL + ", isDeclarationAccepted=" + isDeclarationAccepted + ", userId=" + userId
				+ ", createDate=" + createDate + ", modifiedDate=" + modifiedDate + ", createDateStr=" + createDateStr
				+ ", modifiedDateStr=" + modifiedDateStr + "]";
	}

	
    
}
