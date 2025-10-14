<%@page import="mhdys.kreedapith.sports.facility.constants.MhdysKreedapithSportsFacilityPortletKeys"%>
<%@ include file="/init.jsp"%>
<!-- Include Leaflet CSS & JS -->


<portlet:resourceURL id="<%=MhdysKreedapithSportsFacilityPortletKeys.SAVE_SPORTSFACILITY_RATING_RESOURCECOMMAND %>" var="saveFacilityRatingUrl" />

<portlet:renderURL var="redirectUrl">
    <portlet:param name="mvcRenderCommandName" value="<%=MhdysKreedapithSportsFacilityPortletKeys.REDIRECT %>" />
</portlet:renderURL>

<style>
/*   #stars span {
    font-size: 1.8rem;
    cursor: pointer;
    color: #ccc;
    transition: color 0.2s;
  }
  #stars span.hover,
  #stars span.active {
    color: #ffc107; 
  } */
  
#stars span {
  font-size: 2rem;
  cursor: pointer;
  opacity: 0.5;
  transition: opacity 0.2s, transform 0.2s;
}

#stars span:hover {
  opacity: 1;
  transform: scale(1.3); /* Slightly more for emphasis */
}

/* Keep active rating effect (after selection) */
#stars span.active {
  opacity: 1;
  transform: scale(1.2);
}


</style>

<div class="common-forms-div">
    <div class="container px-4">
        <div class="row">
            <div class="card shadow border-0 w-100">
                <div class="card-header d-flex justify-content-between align-items-center back-btn-cn">
                    <h5 class="mb-0">
                        <liferay-ui:message key="rating-and-review-facility" />
                    </h5>
                    <div>
                        <a href="#" class="btn btn-primary btn-sm rounded-pill back-btn-cn" onclick="backOrClearUrl()"><i class="bi bi-arrow-left-circle mr-1"></i><liferay-ui:message key="back" /></a>
                    </div>
                </div>
                <form id="facilityRatingForm" method="POST" enctype="multipart/form-data">
                
               <input type="hidden" id="facilityRatingId" value="">
                <div class="card-body">
                
                <c:if test="${empty filteredList}">
				 <div class="col-md-12">
					 	<p class="text-center"> <liferay-ui:message key="no-facilities-available-to-review" /></p>
					</div>
				 </c:if>
				 
				<c:if test="${!empty filteredList}">			 
                    <div class="card card-background p-0">
                        <div class="personal_details">
                            <div class="card-header header-card">
                                <liferay-ui:message key="facility-details" />
                            </div>
                            <div class="card-body">
                               <div class="row">
									<div class="col-md-6">
										<div class="form-group">
											<label><liferay-ui:message key="facility-Name"/><sup class="text-danger">*</sup></label>
											<select class="form-control" name="facilityMasterId" id="facilityMasterId" <c:if test="${empty filteredList}">disabled</c:if>>
													<option value=""><liferay-ui:message key="select"/></option>
													<c:forEach var="facility" items="${filteredList}" varStatus="status">
												        <c:if test="${status.first}">
												            <option value="${facility.sportsFacilityId}"
												                <c:if test="${bookingDetails.selectedFacility == facility.sportsFacilityId}">selected</c:if>>
												                ${facility.facilityName}
												            </option>
												        </c:if>
												    </c:forEach>
											</select>
										</div>
									</div>
							</div>
                            </div> <!-- .card-body (inner) -->
                        </div> <!-- .personal_details -->
                    </div> <!-- .card-background -->
                    
                    
                    <div class="card card-background p-0">
                        <div class="personal_details">
                            <div class="card-header header-card">
                                <liferay-ui:message key="review" />
                            </div>
	                            <div class="card-body">
	                               <div class="row">
										<div class="col-md-12">
										  <div class="form-group">
										    <label> <liferay-ui:message key="rating" /> <sup class="text-danger">*</sup> </label>
										    <!-- <div id="stars">
										      <span data-val="1">&#9733;</span>
										      <span data-val="2">&#9733;</span>
										      <span data-val="3">&#9733;</span>
										      <span data-val="4">&#9733;</span>
										      <span data-val="5">&#9733;</span>
										    </div> -->
										    <div id="stars" class="emoji-rating d-flex gap-2 p-1 ">
											  <span data-val="1" title="Bad" class=""><img  class="img-small image-width-emoji" alt="" src="<%=request.getContextPath()%>/images/sad.png" /></span>
											  <span data-val="2" title="Poor" class=""><img  class="img-small image-width-emoji" alt="" src="<%=request.getContextPath()%>/images/sad-face.png" /></span>
											  <span data-val="3" title="Average" class=""><img  class="img-small image-width-emoji" alt="" src="<%=request.getContextPath()%>/images/neutral-face.png" /></span>
											  <span data-val="4" title="Good" class=""><img  class="img-small image-width-emoji" alt="" src="<%=request.getContextPath()%>/images/happy-face.png" /></span>
											  <span data-val="5" title="Excellent" class=""><img  class="img-small image-width-emoji" alt="" src="<%=request.getContextPath()%>/images/smiley.png" /></span>
											</div>
										    <input type="hidden" id="rating" name="rating" />
										  </div>
										</div>
										
										<div class="col-md-12">
											<div class="form-group">
												<label><liferay-ui:message key="comment"/><sup class="text-danger">*</sup></label>
												<textarea class="form-control" name="comment" id="comment" rows="5"></textarea>
											</div>
										</div>
								</div>
                            </div> <!-- .card-body (inner) -->
                        </div> <!-- .personal_details -->
                    </div> <!-- .card-background -->
                  </c:if>  
                </div> <!-- .card-body (outer) -->
                 <c:if test="${!empty filteredList}"> 
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
		    facilityMasterId: "required",
		    rating: "required",	
		    comment: {
		      required: true,
		      minlength: 3,
		      maxlength: 250,
		      alphanumericOnly:true,
		      singleSpaceOnly:true, noEdgeSpaces:true,noConsecutiveSpecials:true,
		    }
		  },
		  messages: {
		    rating: "<liferay-ui:message key='please-enter-rating' />",
		    comment: {
		      required: "<liferay-ui:message key='please-enter-remarks' />",
		      minlength: "<liferay-ui:message key='please-enter-min-3-chars' />",
		      maxlength: "<liferay-ui:message key='please-enter-max-250-chars' />"
		    }
		  }
	});

	const ratingLabels = {
			  1: "Bad",
			  2: "Poor",
			  3: "Average",
			  4: "Good",
			  5: "Excellent"
			};
	
	 const $stars = $('#stars span');
	   /*  $stars.on('click', function () {
	    	  const val = $(this).data('val');
	    	  $('#rating').val(val).valid(); // Trigger validation after setting value
	    	  $stars.removeClass('active');
	    	  $stars.each(function () {
	    	    if ($(this).data('val') <= val) $(this).addClass('active');
	    	  });
	    	  console.log("Selected Rating:", val);
	    	}); */
	    	
	    	/*  $stars.on('mouseenter', function () {
	   	      const val = $(this).data('val');
	   	      $stars.removeClass('hover');
	   	      $stars.each(function () {
	   	        if ($(this).data('val') <= val) $(this).addClass('hover');
	   	      });
	   	    });

	   	    $stars.on('mouseleave', function () {
	   	      $stars.removeClass('hover');
	   	    }); */
	   	    
	   	 $stars.on('click', function () {
	   	  const val = $(this).data('val');
	   	  $('#rating').val(val).valid();
	   	  $stars.removeClass('active');
	   	  $(this).addClass('active'); // only add active class to the clicked emoji
	   	  console.log("Selected Rating:",val );
	   	});

	   	$stars.on('mouseenter', function () {
	   	  $stars.removeClass('hover');
	   	  $(this).addClass('hover'); // only hover the emoji under the cursor
	   	});

	   	$stars.on('mouseleave', function () {
	   	  $stars.removeClass('hover');
	   	});


	    $.validator.addMethod("alphanumericOnly", function(value, element) {
	    	// alpha numeric with .,
	        return this.optional(element) || /^[A-Za-z0-9., ]+$/.test(value);
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



$("#reset-btn").on("click", function () {
    $form[0].reset(); // Reset all fields
    $form.validate().resetForm(); // Clear validation
    $("#stars span").removeClass("active hover");
    $("#rating").val(""); //  Clear hidden rating value
});

$("#submitBtn").on("click", function () {
    if ($("#facilityRatingForm").valid()) {
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