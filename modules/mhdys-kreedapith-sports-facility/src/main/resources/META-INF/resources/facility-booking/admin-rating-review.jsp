<%@page import="mhdys.kreedapith.sports.facility.constants.MhdysKreedapithSportsFacilityPortletKeys"%>
<%@ include file="/init.jsp"%>
<!-- Include Leaflet CSS & JS -->
<link rel="stylesheet" href="https://unpkg.com/leaflet@1.9.4/dist/leaflet.css" />
<script src="https://unpkg.com/leaflet@1.9.4/dist/leaflet.js"></script>

<portlet:resourceURL id="<%=MhdysKreedapithSportsFacilityPortletKeys.SAVE_SPORTSFACILITY_RATING_RESOURCECOMMAND %>" var="saveFacilityRatingUrl" />

<portlet:renderURL var="redirectUrl">
    <portlet:param name="mvcRenderCommandName" value="<%=MhdysKreedapithSportsFacilityPortletKeys.REDIRECT %>" />
</portlet:renderURL>

<div class="common-forms-div">
    <div class="container px-4">
        <div class="row">
            <div class="card shadow border-0 w-100">
                <div class="card-header d-flex justify-content-between align-items-center back-btn-cn">
                    <h5 class="mb-0">
                        <liferay-ui:message key="view-comments-by-ho" />
                    </h5>
                    <div>
                        <a href="#" class="btn btn-primary btn-sm rounded-pill back-btn-cn" onclick="backOrClearUrl()"><i class="bi bi-arrow-left-circle mr-1"></i><liferay-ui:message key="back" /></a>
                    </div>
                </div>
                <form id="facilityRatingForm" method="POST" enctype="multipart/form-data">
                
               <input type="hidden" id="facilityRatingId" value="">
                <div class="card-body">
                    <div class="card card-background p-0">
                        <div class="personal_details">
                            <div class="card-header header-card">
                                <liferay-ui:message key="review" />
                            </div>
                            <div class="card-body">
                             <c:if test="${!empty facilitRatingList}">
                               <div class="row">
									<div class="col-md-6">
										<div class="form-group">
											<label><liferay-ui:message key="select-review"/><sup class="text-danger">*</sup></label>
											<%-- <select class="form-control" name="facilityRatingId" id="facilityRatingId" <c:if test="${empty facilitRatingList}">disabled</c:if>>
													<option value=""><liferay-ui:message key="select"/></option>
													<c:forEach var="facility" items="${facilitRatingList}">
													    <option value="${facility.facilityRatingId}">${facilityMaster[facility.facilityMasterId]} - ${facility.userName}</option>
													</c:forEach>
											</select> --%>
											<select class="form-control" name="facilityRatingId" id="facilityRatingId" <c:if test="${empty facilitRatingList}">disabled</c:if>>
											    <option value=""><liferay-ui:message key="select"/></option>
											    <c:forEach var="facility" items="${facilitRatingList}">
											        <c:set var="serialNo" value="${ratingIdToSerialNo[facility.facilityRatingId]}" />
											        <option value="${facility.facilityRatingId}">
											            ${serialNo}. ${facilityMaster[facility.facilityMasterId]} - ${facility.userName}
											        </option>
											    </c:forEach>
											</select>
										</div>
									</div>
									
									<div class="col-md-12">
											<div class="form-group">
												<label><liferay-ui:message key="reply-to-reviews"/><sup class="text-danger">*</sup></label>
												<textarea class="form-control" name="replyToReview" id="replyToReview" rows="5"></textarea>
											</div>
									</div>
							</div>
							</c:if>
							
							 <c:if test="${empty facilitRatingList}">
							 
							 <div class="col-md-12">
								 	<p class="text-center"> <liferay-ui:message key="no-ratings-to-review" /> </p>
								</div>
							 </c:if>
							
                            </div> <!-- .card-body (inner) -->
                        </div> <!-- .personal_details -->
                    </div> <!-- .card-background -->
                </div> <!-- .card-body (outer) -->
                
                <c:if test="${!empty facilitRatingList}">
                <div class="card-footer bg-transparent text-right p-4">
					 <div class="d-flex justify-content-end">
					     <a href="#" class="btn btn-secondary maha-save-btn" id="modalCloseBtn" onclick="window.location.href='<%= MhdysKreedapithSportsFacilityPortletKeys.HOMEURL %>?clear=true';">
			                <liferay-ui:message key="cancel"/>
			            </a>
					    <button type="button" class="btn btn-primary reset-btn" id="reset-btn">
					      <liferay-ui:message key="reset" />
					    </button>
					    <button type="button" class="btn btn-primary submit-btn" id="submitBtn">
					      <liferay-ui:message key="submit" />
					    </button>
					</div>
				</div>
				</c:if>
           </form>   
           
           
           
           <!-- Validation modal -->
			<div class="modal fade" id="facilityRatingFormModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true" data-backdrop="static" data-keyboard="false">
			  <div class="modal-dialog modal-dialog-centered" role="document">
			    <div class="modal-content modal-bg">
			      <div class="modal-header justify-content-center align-items-center">
			        <h5 class="modal-title"><liferay-ui:message key="form" /></h5>
			      </div>
			      <div class="modal-body">
			        <div class="text-center">
			          <p id="success-message">
			           <!--  <liferay-ui:message key="please-fill-all-required-fields-before-proceeding" /> -->
			          </p>
			        </div>
			      </div>
			      <div class="modal-footer d-flex justify-content-end">
					     <a href="#" class="btn btn-secondary maha-save-btn" id="modalCloseBtn" onclick="backOrClearUrl()">
			                <liferay-ui:message key="close"/>
			            </a>
					</div>
			
			    </div>
			  </div>
			</div>
  
            </div> <!-- .card -->
        </div> <!-- .row -->
    </div> <!-- .container -->
</div> <!-- .common-forms-div -->


<jsp:include page="/facility-booking/user-rating-list.jsp"/>

<script>

var $form = $("#facilityRatingForm");
$(document).ready(function () {
	
	 $("#facilityRatingForm").validate({
		 onkeyup: function (element) {
	   	        $(element).valid();
	   	    },
	   	    onchange: function (element) {
	   	        $(element).valid();
	   	    },
		  ignore: [], // include hidden fields
		  rules: {
		    facilityRatingId: "required",
		    replyToReview: {
		      required: true,
		      minlength: 3,
		      maxlength: 250,
		      alphanumericWithSpecialChars:true,singleSpaceOnly:true, noEdgeSpaces:true,noConsecutiveSpecials:true,
		    }
		  },
		  messages: {
			  replyToReview: {
		      required: "<liferay-ui:message key='please-enter-remarks' />",
		      minlength: "<liferay-ui:message key='please-enter-min-3-chars' />",
		      maxlength: "<liferay-ui:message key='please-enter-max-250-chars' />"
		    }
		  }
	});

	 $.validator.addMethod("alphanumericWithSpecialChars", function(value, element) {
 		// Allows letters, numbers, dot (.), comma (,), hyphen (-), and space
 		    return this.optional(element) || /^[A-Za-z0-9.,\- ]+$/.test(value);
 	    }, "<liferay-ui:message key='please-enter-alphanumeric-characters' />");
		
		  $.validator.addMethod("noEdgeSpaces", function(value, element) {
	    	  return this.optional(element) || value === value.trim();
    	}, "<liferay-ui:message key='no-leading-trailing-spaces-allowed' />");

    	$.validator.addMethod("singleSpaceOnly", function(value, element) {
    	  return this.optional(element) || !/\s{2,}/.test(value);
    	}, "<liferay-ui:message key='only-one-space-between-words-allowed' />");
    	
    	$.validator.addMethod("noConsecutiveSpecials", function(value, element) {
      	return this.optional(element) || !/([.,/#-]\s*){2,}/.test(value);
      	}, "<liferay-ui:message key='no-consecutive-specials-allowed' />");
	  
});



function backOrClearUrl() {
	$("#facilityRatingFormModal").modal('hide');
    window.location.href = "<%=MhdysKreedapithSportsFacilityPortletKeys.HOMEURL%>?clear=true";
}

$("#reset-btn").on("click", function () {
    $form[0].reset(); // Reset all fields
    $form.validate().resetForm(); // Clear validation
});

$("#submitBtn").on("click", function () {
    if ( $("#facilityRatingForm").valid()) {
        savefacilityRatingForm(); 
    }
});

function backOrClearUrl() {
	$("#facilityRatingFormModal").modal('hide');
    window.location.href = "<%=MhdysKreedapithSportsFacilityPortletKeys.HOMEURL%>?clear=true";
}

function savefacilityRatingForm(){
	
		 console.log("All validations are passed and saving Form ------ ");
		 
			 const form = document.getElementById("facilityRatingForm");
		    const formData = new FormData(form); 
		    formData.append("actionType", "adminReply");
			 $.ajax({
			        type: "POST",
			        url: "${saveFacilityRatingUrl}",
			        data: formData,
			        contentType : false,
					cache : false,
					processData : false,
			        success: function(data){
			        console.log("data:: "+data)
			        if (data === "success") {
			            var msg = '<span class="text-success"><liferay-ui:message key="details-submitted-successfully"/></span>';
			            $('#success-message').html(msg);
			            $('#submitBtn').prop('disabled', true);
			            $("#facilityRatingFormModal").modal('show');
			        } else {
			            var msg = '<span class="text-danger"><liferay-ui:message key="the-details-are-failed-to-submit"/></span>';
			            $('#success-message').html(msg);
			            $("#facilityRatingFormModal").modal('show');
			        }
			    	 }
			    });
		 
}

</script>