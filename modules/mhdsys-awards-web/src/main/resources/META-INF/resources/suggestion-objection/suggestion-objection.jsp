<%@page import="com.mhdsys.awards.web.constants.AwardsWebPortletKeys"%>
<%@ include file="/init.jsp" %>

<portlet:resourceURL id="<%=AwardsWebPortletKeys.SUGGESTION_OBJECTION%>"  var="saveSuggestionObjectionURL" />

<form id="suggestion_objection" method="POST" enctype="multipart/form-data">
<div class="common-forms-div">
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="border-0 card shadow">
					
					<div class="align-items-center back-btn-cn card-header d-flex justify-content-between" style="">
						<h5 class="mb-0"> <liferay-ui:message key="suggestion-objection-form" /></h5>
						<div>
						<c:if test="${!isPtTeacher}">
							<a href="/group/guest/objection-list" class="btn btn-primary btn-sm rounded-pill back-btn-cn"><i class="bi bi-arrow-left-circle"></i>  <liferay-ui:message key="back" /></a>
						</c:if>
						<c:if test="${isPtTeacher && (cmd == 'view' || cmd == 'edit')}">
							<a href="/group/guest/objection-list" class="btn btn-primary btn-sm rounded-pill back-btn-cn"><i class="bi bi-arrow-left-circle"></i>  <liferay-ui:message key="back" /></a>
						</c:if>
						<c:if test="${isPtTeacher && cmd == 'add'}">
							<a href="/group/guest/dashboard" class="btn btn-primary btn-sm rounded-pill back-btn-cn"><i class="bi bi-arrow-left-circle"></i>  <liferay-ui:message key="back" /></a>
						</c:if>
						</div>
					</div>
					
					<div class="card-body">
					<div class="card card-background p-0">
                               <div class="card-header header-card">
                                   <liferay-ui:message key="objection" />
                               </div>
					<div class="card-body">
						<div id="dynamicFields">
							<div class="row">
								<div class="col-md-6">
									<div class="form-group">
										<label><liferay-ui:message key="player-name"/><sup class="text-danger">*</sup></label>
										<input type="hidden" class="form-control" name="objectionId" id="objectionId" value="${objectionId}"/>
										<input type="text" class="form-control" name="playerName" id="playerName" value="${objection.playerName}" 
										<c:if test="${cmd == 'view' or isDeskOfficer or isDeputyDirector}">disabled</c:if>/>
									</div>
								</div>
								
								<div class="col-md-6">
									<div class="form-group">
										<label><liferay-ui:message key="name-of-objector"/><sup class="text-danger">*</sup></label>
										<c:if test="${cmd == 'edit' || cmd == 'view'}">
										<input type="text" class="form-control" name="objectorName" id="objectorName" value="${objection.objectorName}"
										<c:if test="${cmd == 'view' or isDeskOfficer or isDeputyDirector}">disabled</c:if>/>
										</c:if>
										<c:if test="${cmd != 'edit' && cmd != 'view'}">
										<input type="text" class="form-control" name="objectorName" id="objectorName" value="${objector}"/>
										</c:if>
									</div>
								</div>
								</div>
								<div class="row">

								<div class="col-md-6">
									<div class="form-group">
										<label><liferay-ui:message key="email"/><sup class="text-danger">*</sup></label>
										<c:if test="${cmd == 'edit' || cmd == 'view'}">
										<input type="text" class="form-control" name="email" id="email" value="${objection.email}" 
										<c:if test="${cmd == 'view' or isDeskOfficer or isDeputyDirector}">disabled</c:if>/>
										</c:if>
										<c:if test="${cmd != 'edit' && cmd != 'view'}">
										<input type="text" class="form-control" name="email" id="email" value="${email}" />
										</c:if>
									</div>
								</div>

								<div class="col-md-6">
									<div class="form-group">
										<label><liferay-ui:message key="mobile-no"/><sup class="text-danger">*</sup></label>
										<c:if test="${cmd == 'edit' || cmd == 'view'}">
										<input type="text" class="form-control" name="mobileNo" id="mobileNo" value="${objection.mobileNo}"
										<c:if test="${cmd == 'view' or isDeskOfficer or isDeputyDirector}">disabled</c:if>/>
										</c:if>
										<c:if test="${cmd != 'edit' && cmd != 'view'}">
										<input type="text" class="form-control" name="mobileNo" id="mobileNo" value="${mobileNo}"/>
										</c:if>
									</div>
								</div>
                                </div>
                                <div class="row">

								<div class="col-md-6">
									<div class="form-group">
										<label><liferay-ui:message key="category"/><sup class="text-danger">*</sup></label>
										<select class="form-control" name="category" id="category" <c:if test="${cmd == 'view' or isDeskOfficer or isDeputyDirector}">disabled</c:if>>
											<option value=""><liferay-ui:message key="select"/></option>
											<option value="Athelete" <c:if test="${'Athelete' == objection.category}">selected="selected"</c:if>>Athelete</option>
											<option value="Coach" <c:if test="${'Coach' == objection.category}">selected="selected"</c:if>>Coach</option>
											<option value="Para Athelete" <c:if test="${'Para Athelete' == objection.category}">selected="selected"</c:if>>Para Athelete</option>
											<option value="Bravery" <c:if test="${'Bravery' == objection.category}">selected="selected"</c:if>>Bravery</option>
										</select>
									</div>
								</div>
								
								<div class="col-md-6">
									<div class="form-group">
										<label><liferay-ui:message key="sports-number"/><sup class="text-danger">*</sup></label>
										<input type="text" class="form-control" name="sportNo" id="sportNo" value="${objection.sportNo}" <c:if test="${cmd == 'view' or isDeskOfficer or isDeputyDirector}">disabled</c:if>>
										<%-- <select class="form-control" name="sportNo" id="sportNo" <c:if test="${cmd == 'view' || isHOAdmin}">disabled</c:if>>
											<option value=""><liferay-ui:message key="select"/></option>
											<option value="1" <c:if test="${1 == objection.sportNo}">selected="selected"</c:if>>1</option>
											<option value="2" <c:if test="${2 == objection.sportNo}">selected="selected"</c:if>>2</option>
											<option value="3" <c:if test="${3 == objection.sportNo}">selected="selected"</c:if>>3</option>
											<option value="4" <c:if test="${4 == objection.sportNo}">selected="selected"</c:if>>4</option>
										</select> --%>
									</div>
								</div>
                                 </div>
                                 <div class="row">
								<div class="col-md-6">
									<div class="form-group">
										<label><liferay-ui:message key="achievement-year"/><sup class="text-danger">*</sup></label>
										
										<select class="form-control" name="awardYear" id="awardYear" <c:if test="${cmd == 'view' or isDeskOfficer or isDeputyDirector}">disabled</c:if>>
											<option value=""><liferay-ui:message key="select"/></option>
											<option value="2019-20" <c:if test="${'2019-20' == objection.awardYear}">selected="selected"</c:if>>2019-20</option>
											<option value="2020-21" <c:if test="${'2020-21' == objection.awardYear}">selected="selected"</c:if>>2020-21</option>
											<option value="2021-22" <c:if test="${'2021-22' == objection.awardYear}">selected="selected"</c:if>>2021-22</option>
											<option value="2022-23" <c:if test="${'2022-23' == objection.awardYear}">selected="selected"</c:if>>2022-23</option>
											<option value="2023-24" <c:if test="${'2023-24' == objection.awardYear}">selected="selected"</c:if>>2023-24</option>
										</select>
										
										<%-- <input type="text" class="form-control" name="awardYear" id="awardYear" value="${objection.awardYear}"
										<c:if test="${cmd == 'view' || isHOAdmin}">disabled</c:if>/> --%>
									</div>
								</div>

								<div class="col-md-6">
									<div class="form-group">
										<label><liferay-ui:message key="sport-name"/><sup class="text-danger">*</sup></label>
										<select class="form-control" name="sportName" id="sportName" <c:if test="${cmd == 'view' or isDeskOfficer or isDeputyDirector}">disabled</c:if>>
											<option value=""><liferay-ui:message key="select"/></option>
											<c:forEach var="sport" items="${sportsNames}">
											<option value="${sport.sportMasterId}"
											 <c:if test="${sport.sportMasterId == objection.sportName}">selected="selected"</c:if>>${sport.name_en}</option>
										</c:forEach>
										</select>
									</div>
								</div>
                               </div>
                               <div class="row">
								<div class="col-md-6">
									<div class="form-group">
										<label><liferay-ui:message key="award-type"/><sup class="text-danger">*</sup></label>
										<select class="form-control" name="awardType" id="awardType" <c:if test="${cmd == 'view' or isDeskOfficer or isDeputyDirector}">disabled</c:if>>
											<option value=""><liferay-ui:message key="select"/></option>
											<c:forEach var="award" items="${awardsNames}">
											<option value="${award.awardNameMasterId}"
												<c:if test="${award.awardNameMasterId == objection.awardType}">selected="selected"</c:if>>${award.name_en}</option>
										</c:forEach>
										</select>
									</div>
								</div>
								
								
								<div class="col-md-6">
									<div class="form-group">
										<label><liferay-ui:message key="district"/><sup class="text-danger">*</sup></label>
										<select class="form-control" name="district" id="district" <c:if test="${cmd == 'view' or isDeskOfficer or isDeputyDirector}">disabled</c:if>>
											<option value=""><liferay-ui:message key="select"/></option>
											<c:forEach var="district" items="${districts}">
											<option value="${district.districtId}" 
											 <c:if test="${district.districtId == objection.district}">selected="selected"</c:if>>${district.districtName_en}</option>
										</c:forEach>
										</select>
									</div>
								</div>
								</div>
								<div class="row">
								
								<div class="col-md-6">
									<div class="form-group">
										<label><liferay-ui:message key="place"/><sup class="text-danger">*</sup></label>
										<input type="text" class="form-control" name="place" id="place" value="${objection.place}"
										<c:if test="${cmd == 'view' or isDeskOfficer or isDeputyDirector}">disabled</c:if>/>
									</div>
								</div>
								
								<div class="col-md-6">
									<div class="form-group">
										<label><liferay-ui:message key="summary-of-objection"/><sup class="text-danger">*</sup></label>
										<input type="text" class="form-control" name="objectionSummary" id="objectionSummary" value="${objection.objectionSummary}"
										<c:if test="${cmd == 'view' or isDeskOfficer or isDeputyDirector}">disabled</c:if>/>
									</div>
								</div>
								</div>
								
								
								<%-- <c:if test="${cmd == 'edit' && isHOAdmin}">
								<div class="col-md-4">
									<div class="form-group">
										<label><liferay-ui:message key="final-points"/></label>
										<input type="text" class="form-control" name="finalPointsByHo" id="finalPointsByHo" value=""/>
									</div>
								</div>
								
									<div class="col-md-4">
									<div class="form-group">
										<label><liferay-ui:message key="final-remarks"/></label>
										<input type="text" class="form-control" name="finalRemarksByHO" id="finalRemarksByHO" value=""/>
									</div>
								</div>
								</c:if> --%>
								
							<%-- <c:if test="${(not empty objection.doVerification && not empty objection.doRemarks) || isDeskOfficer}">
							    <div class="card card-background p-0 mb-4">
							        <div class="card-header header-card">
							            <liferay-ui:message key="desk-officer-remarks" />
							        </div>
							        <div class="card-body">
							            <div class="row">
							                <div class="col-md-6">
							                    <div class="form-group">
							                        <label><liferay-ui:message key="approve-reject" /><sup class="text-danger">*</sup></label>
							                        <div>
							                            <label> 
							                                <input type="radio" name="doVerification" id="doVerification" value="Approve"
							                                    <c:if test="${(not empty objection.doVerification || not isDeskOfficer) || mode eq 'view'}">disabled</c:if>
							                                    <c:if test="${objection.doVerification == 'Approve'}">checked</c:if>>
							                                <liferay-ui:message key="approve" />
							                            </label> 
							                            <label> 
							                                <input type="radio" name="doVerification" id="doVerification" value="Reject"
							                                    <c:if test="${(not empty objection.doVerification || not isDeskOfficer) || mode eq 'view'}">disabled</c:if>
							                                    <c:if test="${objection.doRemarks == 'Reject'}">checked</c:if>>
							                                <liferay-ui:message key="reject" />
							                            </label>
							                        </div>
							                    </div>
							                </div>
							                
							                <div class="col-md-6">
							                    <div class="form-group">
							                        <label><liferay-ui:message key="remarks" /><sup class="text-danger">*</sup></label> 
							                        <input type="text" class="form-control" id="doRemarks" name="doRemarks" 
							                            value="${objection.doRemarks}"
							                            <c:if test="${(not empty objection.doRemarks || not isDeskOfficer) || mode eq 'view'}">disabled</c:if> />
							                    </div>
							                </div>
							            </div>
							        </div>
							    </div>
							</c:if>	 --%>
							<c:if test="${(not empty objection.doVerification && not empty objection.doRemarks) || isDeskOfficer || isDeputyDirector}">
							    <div class="card card-background p-0 mb-4">
							        <div class="card-header header-card">
							            <liferay-ui:message key="desk-officer-remarks" />
							        </div>
							        <div class="card-body">
							            <div class="row">
							                <div class="col-md-6">
							                    <div class="form-group">
							                        <label><liferay-ui:message key="approve-reject" /><sup class="text-danger">*</sup></label>
							                        <div>
							                            <label> 
							                                <input type="radio" name="doVerification" id="doVerification" value="Approve"
							                                    <c:if test="${not empty objection.doVerification || mode eq 'view'}">disabled</c:if>
							                                    <c:if test="${objection.doVerification == 'Approve'}">checked</c:if>>
							                                <liferay-ui:message key="approve" />
							                            </label> 
							                            <label> 
							                                <input type="radio" name="doVerification" id="doVerification" value="Reject"
							                                    <c:if test="${not empty objection.doVerification || mode eq 'view'}">disabled</c:if>
							                                    <c:if test="${objection.doVerification == 'Reject'}">checked</c:if>>
							                                <liferay-ui:message key="reject" />
							                            </label>
							                        </div>
							                    </div>
							                </div>
							                
							                <div class="col-md-6">
							                    <div class="form-group">
							                        <label id="remarksLabelDO">
							                        	<liferay-ui:message key="remarks" />
							                        </label> 
							                        <input type="text" class="form-control" id="doRemarks" name="doRemarks" 
							                            value="${objection.doRemarks}"
							                            <c:if test="${not empty objection.doVerification || mode eq 'view'}">disabled</c:if> />
							                    </div>
							                </div>
							            </div>
							        </div>
							    </div>
							</c:if>
							
								
								
							<%-- <c:if test="${(not empty objection.ddVerification && not empty objection.ddRemarks) || isDeputyDirector}">
							    <div class="card card-background p-0 mb-4">
							        <div class="card-header header-card">
							            <liferay-ui:message key="desk-officer-remarks" />
							        </div>
							        <div class="card-body">
							            <div class="row">
							                <div class="col-md-6">
							                    <div class="form-group">
							                        <label><liferay-ui:message key="approve-reject" /><sup class="text-danger">*</sup></label>
							                        <div>
							                            <label> 
							                                <input type="radio" name="ddVerification" id="ddVerification" value="Approve"
							                                    <c:if test="${(not empty objection.ddVerification || not isDeputyDirector) || mode eq 'view'}">disabled</c:if>
							                                    <c:if test="${objection.ddVerification == 'Approve'}">checked</c:if>>
							                                <liferay-ui:message key="approve" />
							                            </label> 
							                            <label> 
							                                <input type="radio" name="ddVerification" id="ddVerification" value="Reject"
							                                    <c:if test="${(not empty objection.ddVerification || not isDeputyDirector) || mode eq 'view'}">disabled</c:if>
							                                    <c:if test="${objection.ddVerification == 'Reject'}">checked</c:if>>
							                                <liferay-ui:message key="reject" />
							                            </label>
							                        </div>
							                    </div>
							                </div>
							                
							                <div class="col-md-6">
							                    <div class="form-group">
							                        <label><liferay-ui:message key="remarks" /><sup class="text-danger">*</sup></label> 
							                        <input type="text" class="form-control" id="ddRemarks" name="ddRemarks" 
							                            value="${objection.ddRemarks}"
							                            <c:if test="${(not empty objection.ddRemarks || not isDeputyDirector) || mode eq 'view'}">disabled</c:if> />
							                    </div>
							                </div>
							            </div>
							        </div>
							    </div>
							</c:if> --%>
							
							<c:if test="${(not empty objection.ddVerification && not empty objection.ddRemarks) || isDeputyDirector}">
							    <div class="card card-background p-0 mb-4">
							        <div class="card-header header-card">
							            <liferay-ui:message key="deputy-director-remarks" />
							        </div>
							        <div class="card-body">
							            <div class="row">
							                <div class="col-md-6">
							                    <div class="form-group">
							                        <label><liferay-ui:message key="approve-reject" /><sup class="text-danger">*</sup></label>
							                        <div>
							                            <label> 
							                                <input type="radio" name="ddVerification" id="ddVerification" value="Approve"
							                                    <c:if test="${not empty objection.ddVerification || mode eq 'view'}">disabled</c:if>
							                                    <c:if test="${objection.ddVerification == 'Approve'}">checked</c:if>>
							                                <liferay-ui:message key="approve" />
							                            </label> 
							                            <label> 
							                                <input type="radio" name="ddVerification" id="ddVerification" value="Reject"
							                                    <c:if test="${not empty objection.ddVerification || mode eq 'view'}">disabled</c:if>
							                                    <c:if test="${objection.ddVerification == 'Reject'}">checked</c:if>>
							                                <liferay-ui:message key="reject" />
							                            </label>
							                        </div>
							                    </div>
							                </div>
							                
							                <div class="col-md-6">
							                    <div class="form-group">
							                        <label id="remarksLabelDD">
							                        	<liferay-ui:message key="remarks" />
							                        </label> 
							                        <input type="text" class="form-control" id="ddRemarks" name="ddRemarks" 
							                            value="${objection.ddRemarks}"
							                            <c:if test="${not empty objection.ddVerification || mode eq 'view'}">disabled</c:if> />
							                    </div>
							                </div>
							            </div>
							        </div>
							    </div>
							</c:if>
					
						</div>
								
								</div>
							</div>
							
						</div>
						
					<c:if test="${(isDeskOfficer && empty objection.doVerification) || (isDeputyDirector && empty objection.ddVerification) ||
							!isDeskOfficer && !isDeputyDirector && (cmd eq 'add' || cmd eq 'edit')}">
						<div class="card-footer bg-transparent text-right p-4">
						        
						    <c:if test="${isPtTeacher && cmd == 'add'}">
						    	<button type="button" class="btn btn-primary" onclick="location.href='/group/guest/dashboard';">
						            <liferay-ui:message key="cancel" />
						        </button> 
						    </c:if>
						    
						    <c:if test="${isPtTeacher && cmd == 'edit'}">
						    	<button type="button" class="btn btn-primary" onclick="location.href='/group/guest/objection-list';">
						            <liferay-ui:message key="cancel" />
						        </button> 
						    </c:if>
						    
						    <c:if test="${!isPtTeacher}">
						    	<button type="button" class="btn btn-primary" onclick="location.href='/group/guest/objection-list';">
						            <liferay-ui:message key="cancel" />
						        </button> 
						    </c:if>
						    
					        <button class="btn btn-primary" type="button" onclick="location.reload();">
					            <liferay-ui:message key="reset" />
					        </button>
					    
					        <button class="btn btn-primary" type="button" onclick="saveSuggestionObjection(event)">
					        <c:if test="${cmd eq 'edit' && (isDeskOfficer || isDeputyDirector)}">
					            <liferay-ui:message key="submit"/>
					        </c:if>
					        <c:if test="${cmd eq 'edit' && (!isDeskOfficer && !isDeputyDirector)}">
					            <liferay-ui:message key="update"/>
					        </c:if>
					        <c:if test="${cmd ne 'edit'}">
					            <liferay-ui:message key="submit"/>
					        </c:if>
					        </button>
						</div>
					</c:if>
						
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
</form>

<!-- modal popup for awards application for sports person -->
<div class="modal fade" id="suggestionObjectionId" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
			<div class="modal-dialog modal-dialog-centered" role="document">
				<div class="modal-content modal-bg">
					<div class="modal-header justify-content-center align-items-center">
						<h5 class="modal-title" id="exampleModalLongTitle"><liferay-ui:message key="objection"/></h5>
						<button type="button" class="close d-none" data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body">
						<div class="row">
							<div class="col-md-12 text-center">
									<div>
                                    	<p id="success-application"></p>
									</div>
							</div>
						</div>
					</div>
					<div class="modal-footer d-flex justify-content-end">
       					 <a href="/group/guest/objection-list" type="button" id="closeModal" class="btn btn-secondary maha-save-btn" data-bs-dismiss="modal" onclick="closeModal()"><liferay-ui:message key="close"/></a>
					</div>
				</div>
			</div>
		</div>
<!-- modal popup for awards application for sports person -->

<script>

$(document).ready(function(){
	
	 $('input[name="doRemarks"]').prop('required', false);
	 $('input[name="ddRemarks"]').prop('required', false);
	 $('input[name="doVerification"]:checked').trigger('change');
	 $('input[name="ddVerification"]:checked').trigger('change');
		
	    $('input[name="doVerification"]').change(function() {
	        if ($(this).val() === "Reject") {
	            $('input[name="doRemarks"]').prop('required', true);
	            if($('#remarksLabelDO sup').length === 0){ 
	                $('#remarksLabelDO').append('<sup class="text-danger">*</sup>');
	            }
	        } else {
	            $('input[name="doRemarks"]').prop('required', false);
	            $('#remarksLabelDO sup').remove();
	        }
	    });
	    
	    $('input[name="ddVerification"]').change(function() {
	        if ($(this).val() === "Reject") {
	            $('input[name="ddRemarks"]').prop('required', true);
	            if($('#remarksLabelDD sup').length === 0){ 
	                $('#remarksLabelDD').append('<sup class="text-danger">*</sup>');
	            }
	        } else {
	            $('input[name="ddRemarks"]').prop('required', false);
	            $('#remarksLabelDD sup').remove();
	        }
	    });
	
	
	
	
	
	
	  var isDeskOfficer = ${isDeskOfficer};
	  var isDeputyDirector = ${isDeputyDirector};
	
    // jQuery validation start
    $('#suggestion_objection').validate({
        onkeyup: function (element){
            $(element).valid();
        },
        onchange: function (element){
            $(element).valid();
        },
        rules:{
            playerName: {
                required: true,
                minlength: 3,
                maxlength: 75,
                startEndSpace: true,
                validateName: true,
                singleSpaceBetweenWords: true,
                noConsecutiveSpecialChars: true
            },
            objectorName: {
                required: true,
                minlength: 3,
                maxlength: 75,
                startEndSpace: true,
                validateName: true,
                singleSpaceBetweenWords: true,
                noConsecutiveSpecialChars: true
            },
            email: {
                required: true,
                startEndSpace: true,
                validateEmail:true,
            },
            mobileNo: {
                required: true,
                digits: true,
                pattern: /^[6789]/,
                minlength: 10,
                maxlength: 10
            },
            category: {
                required: true,
            },
            sportNo: {
                required: true,
                pattern: /^[a-zA-Z0-9]+$/,
                minlength: 3,
                maxlength: 75,
                startEndSpace:true,
            },
            awardYear: {
                required: true,
            },
            sportName: {
                required: true,
            },
            awardType: {
                required: true,
            },
            district: {
                required: true,
            },
            place: {
                required: true,
                minlength: 3,
                maxlength: 75,
                startEndSpace: true,
                singleSpaceBetweenWords: true,
                noConsecutiveSpecialChars: true,
                pattern: /^(?!.*\s{2,})[a-zA-Z0-9\s,.\-/#]{3,250}$/,
            },
            objectionSummary: {
                required: true,
                minlength: 10,
                maxlength: 5000,
                startEndSpace: true,
                singleSpaceBetweenWords: true,
                noConsecutiveSpecialChars: true,
            },
            objectionFile: { 						
				accept: "application/pdf",
			},
			doVerification: {
				required: isDeskOfficer,
			},
			doRemarks: {
				validateRemarks: true,
				noConsecutiveSpecialChars: true,
				singleSpaceBetweenWords: true,
				startEndSpace: true,
			},
			ddVerification: {
				required: isDeputyDirector,
			},
			ddRemarks: {
				validateRemarks: true,
				noConsecutiveSpecialChars: true,
				singleSpaceBetweenWords: true,
				startEndSpace: true,
			}
        },
        messages:{
            playerName: {
                required: "<liferay-ui:message key='please-enter-player-name'/>",
                minlength: '<liferay-ui:message key="minimum-3-characters-required" />',
			    maxlength: '<liferay-ui:message key="maximum-75-characters-allowed" />',
            },
            objectorName: {
                required: "<liferay-ui:message key='please-enter-objector-name'/>",
                minlength: '<liferay-ui:message key="minimum-3-characters-required" />',
			    maxlength: '<liferay-ui:message key="maximum-75-characters-allowed" />',
            },
            email: {
                required: "<liferay-ui:message key='please-enter-email'/>",
                email: "<liferay-ui:message key='please-enter-valid-email-id' />"
            },
            mobileNo: {
                required: "<liferay-ui:message key='please-enter-mobile-number'/>",
                pattern: "<liferay-ui:message key='contact-number-must-start-with-678or9' />",
                digits: "<liferay-ui:message key='only-digits-allowed' />",
                minlength: "<liferay-ui:message key='please-enter-10-digit-number' />",
                maxlength: "<liferay-ui:message key='please-enter-10-digit-number' />"
            },
            category: {
                required: "<liferay-ui:message key='please-select-category'/>",
            },
            sportNo: {
                required: "<liferay-ui:message key='please-enter-sport-number'/>",
                pattern: "<liferay-ui:message key='please-enter-valid-sports-no' />",
                minlength: '<liferay-ui:message key="minimum-3-characters-required" />',
			    maxlength: '<liferay-ui:message key="maximum-75-characters-allowed" />',
            },
           awardYear: {
                required: "<liferay-ui:message key='please-enter-award-year'/>",
            },
            sportName: {
                required: "<liferay-ui:message key='please-select-sport-name'/>",
            },
            awardType: {
                required: "<liferay-ui:message key='please-select-award-type'/>",
            },
            district: {
                required: "<liferay-ui:message key='please-enter-district'/>",
            },
            place: {
                required: "<liferay-ui:message key='please-enter-place'/>",
                minlength: '<liferay-ui:message key="minimum-3-characters-required" />',
			    maxlength: '<liferay-ui:message key="maximum-75-characters-allowed" />',
			    pattern: '<liferay-ui:message key="please-enter-valid-place-details" />',
            },
            objectionSummary: {
                required: "<liferay-ui:message key='please-enter-objection-summary'/>",
                minlength: "<liferay-ui:message key='minimum-10-characters-required' />",
                maxlength: "<liferay-ui:message key='objection-summary-max-5000-characters'/>",
            },
            objectionFile: { 						
				accept: "<liferay-ui:message key="please-choose-valid-file"/>",
			},
			doVerification: {
				required: "<liferay-ui:message key='please-select-approve-reject'/>",
			},
			doRemarks: {
				required: "<liferay-ui:message key='please-enter-remarks'/>",
			},
			ddVerification: {
				required: "<liferay-ui:message key='please-select-approve-reject'/>",
			},
			ddRemarks: {
				required: "<liferay-ui:message key='please-enter-remarks'/>",
			}
        },
        
        errorPlacement: function(error, element) {
            if (element.attr("name") === "doVerification" || element.attr("name") === "ddVerification") {
                error.insertAfter(element.closest("div"));
            } else {
                error.insertAfter(element);
            }
        }
    });
    
    
    $.validator.addMethod("startEndSpace", function(value, element) {
        return this.optional(element) || /^[^\s].*[^\s]$/.test(value);
    }, "<liferay-ui:message key='leading-or-trailing-spaces-are-not-allowed' />");
    
    $.validator.addMethod("validateName", function(value, element) {
	    // Allow only letters (A-Z, a-z) and spaces
	    return this.optional(element) || /^[A-Za-z]+(?: [A-Za-z]+)*$/.test(value);
	}, "<liferay-ui:message key='only-alphabets-and-space-are-allowed'/>");
    
    $.validator.addMethod("validateRemarks", function(value, element) {
 	   const regex = /^(?!0+$)(?!.*  )[A-Za-z0-9]+(?:[ ]?[,.\- ]?[ ]?[A-Za-z0-9]+)*(?: \.|\.)?$/i;
 	    return this.optional(element) || regex.test(value);
 	}, "<liferay-ui:message key='please-enter-valid-place-details' />");
    
    $.validator.addMethod("singleSpaceBetweenWords", function(value) {
        return !/ {2,}/.test(value);
    }, '<liferay-ui:message key="only-one-space-allowed-between-words"/>');
    
    $.validator.addMethod("placeValidation", function(value) {
        return /^[A-Za-z0-9\-\.\/#, ]*$/.test(value);
    }, '<liferay-ui:message key="please-enter-valid-details"/>');

    $.validator.addMethod("validateEmail", function(value, element) {
 		const regex =  /^(?!.*\.\.)(?!.*__)(?!.*[._][._])(?![_\.])[a-zA-Z0-9._]*[a-zA-Z][a-zA-Z0-9._]*(?<![_\.])@[a-zA-Z0-9][a-zA-Z0-9-]*\.[a-zA-Z]{2,}$/
	    return this.optional(element) || regex.test(value);
	}, "<liferay-ui:message key='please-enter-valid-email-address'/>");
    
    $.validator.addMethod("validAddress", function (value, element) {
        value = $.trim(value); 
        return this.optional(element) || (/^(?!.*\s{2,})[a-zA-Z0-9\s,.\-/#]{3,250}$/.test(value));
    }, "<liferay-ui:message key='please-enter-valid-address' />");
    
    // Validate PDF extension
    $.validator.addMethod("pdfOnly", function (value, element) {
        if (element.files.length === 0) return true; // Don't block other required validation
        const fileName = element.files[0].name;
        const ext = fileName.split('.').pop().toLowerCase();
        return ext === "pdf";
    }, "<liferay-ui:message key='allowed-only-pdf-file' />");

    // Validate file size (max 2MB)
    $.validator.addMethod("maxFileSize", function (value, element) {
        if (element.files.length === 0) return true;
        return element.files[0].size <= 2 * 1024 * 1024; // 2 MB
    }, "<liferay-ui:message key='file-size-limit' />");
    
    $.validator.addMethod("generalFieldsValidation", function(value, element) {
  	   const regex = /^(?!0+$)(?!.* {2})(?!.*(\s[\/-]|[\/-]\s))(?!.*([,.\-/])\2)[A-Za-z0-9]+(?:[ ]?[,\.]?[ ]?[A-Za-z0-9]+|[\/-][A-Za-z0-9]+)*$/i;
  	    return this.optional(element) || regex.test(value);
  	}, "<liferay-ui:message key='only-letters-numbers-symbols-allowed' />");

	 $.validator.addMethod("noConsecutiveSpecialChars", function(value, element) {
	    var hasLetterOrDigit = /[A-Za-z0-9]/.test(value);

	    // Disallow repeated special characters like "..", "--", "//", "  "
	    var repeatedSpecials = /([.,\/\-])\1+/;

	    // Disallow invalid consecutive combinations like ".,", ",.", ",-", "./", etc.
	    var mixedSpecials = /[.,\/\-]{2,}/;

	    if (hasLetterOrDigit && (repeatedSpecials.test(value) || mixedSpecials.test(value))) {
	        return false;
	    }
	    return true;
	}, "<liferay-ui:message key='no-consecutive-special-characters-allowed' />");

	$.validator.addMethod("noSpaceAroundDashSlash", function (value, element) {
	    // Check for space before or after "-" or "/"
	    const regex = /(\s[-]|[-]\s|\/\s|\s\/)/;
	    return this.optional(element) || !regex.test(value);
	}, "<liferay-ui:message key='no-space-around-hyphen-or-slash-allowed' />");

	$.validator.addMethod("noStartEndSpecialChar", function (value, element) {
	    return this.optional(element) || !/^[.,\/\-]|[.,\/\-]$/.test(value);
	}, "<liferay-ui:message key='should-not-start-or-end-with-special-characters' />");
    
    
    
});


	function saveSuggestionObjection(event){debugger
		var form = $("#suggestion_objection")[0];
		var formData = new FormData(form);
		
		if (event) {
	        event.preventDefault();
	    }
		
		if($('#suggestion_objection').valid()){
	 $.ajax({
	        type: "POST",
	        url: '${saveSuggestionObjectionURL}' ,
	        data:  formData, 
	        contentType : false,
			cache : false,
			processData : false,
	        success: function(data){ 
	        	console.log("data: ", typeof data);
	        if (typeof data === 'string') {
	            try {
	                data = JSON.parse(data);
	            } catch (e) {
	                console.error("Failed to parse JSON response: ", e);
	                return; 
	            }
	        }
	        console.log("Parsed data: ", data);
	        	if(data.objection == true){
	        		var msg = "<liferay-ui:message key="suggestion-objection-form-submition-successfull"/>";
	        	    $('#success-application').html(msg);
	        		 $("#suggestionObjectionId").modal('show');
	        	}else{
	        		var msg = "<liferay-ui:message key="suggestion-objection-form-submition-unsuccessfull"/>";
	        	    $('#success-application').html(msg);
	        		 $("#suggestionObjectionId").modal('show'); 
	        	}
	    	 }
	       
	    });
	}
	};
	
	function handleFileUpload(event) {debugger
	    const fileInput = event.target;
	    const file = fileInput.files[0]; // Get the uploaded file
	    const previewContainer = document.getElementById('filePreviewContainer');
	    const previewLink = document.getElementById('filePreviewLink');
	    const deleteButton = document.getElementById('deleteButton');

	    if (file && $('#objectionFile').val() != '') {   
	    	const fileName = file.name;

	        // Show the preview container
	        previewContainer.classList.remove('d-none');
	        previewContainer.classList.add('d-flex');

	        // Set the link text and href
	        previewLink.textContent = fileName;
	        previewLink.href = URL.createObjectURL(file); // Generate a temporary object URL for preview/download
	        previewLink.target = "_blank";

	        // Attach the delete functionality
	        deleteButton.dataset.filename = fileName; // Store file name in button dataset
	    }
	}

	function deleteFile() {
	    const previewContainer = document.getElementById('filePreviewContainer');
	    const fileInput = document.getElementById('feesPaymentReciept');

	    // Reset file input
	    fileInput.value = "";
		$(".custom-file-input").siblings(".custom-file-label").addClass("selected").html("choose-file"); 
	    // Hide the preview container
	    previewContainer.classList.add('d-none');
	    previewContainer.classList.remove('d-flex');
	}
</script>