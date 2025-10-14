package com.mhdsys.common.pojo;

import java.util.Date;

public class FacilityRatingCommonDTO {

	private long facilityRatingId;
	private String ratingUniqueId;
	private long facilityMasterId;
	private String rating;
	private String comment;
	private String hoReplyToReview;
	private String userName;
	// Audit Fields
	private long creatorUserId;
	private Date createDate;
	private Date modifiedDate;
	public long getFacilityRatingId() {
		return facilityRatingId;
	}
	public void setFacilityRatingId(long facilityRatingId) {
		this.facilityRatingId = facilityRatingId;
	}
	public String getRatingUniqueId() {
		return ratingUniqueId;
	}
	public void setRatingUniqueId(String ratingUniqueId) {
		this.ratingUniqueId = ratingUniqueId;
	}
	public long getFacilityMasterId() {
		return facilityMasterId;
	}
	public void setFacilityMasterId(long facilityMasterId) {
		this.facilityMasterId = facilityMasterId;
	}
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getHoReplyToReview() {
		return hoReplyToReview;
	}
	public void setHoReplyToReview(String hoReplyToReview) {
		this.hoReplyToReview = hoReplyToReview;
	}
	public long getCreatorUserId() {
		return creatorUserId;
	}
	public void setCreatorUserId(long creatorUserId) {
		this.creatorUserId = creatorUserId;
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
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	@Override
	public String toString() {
		return "FacilityRatingCommonDTO [facilityRatingId=" + facilityRatingId + ", ratingUniqueId=" + ratingUniqueId
				+ ", facilityMasterId=" + facilityMasterId + ", rating=" + rating + ", comment=" + comment
				+ ", hoReplyToReview=" + hoReplyToReview + ", userName=" + userName + ", creatorUserId=" + creatorUserId
				+ ", createDate=" + createDate + ", modifiedDate=" + modifiedDate + "]";
	}

}
