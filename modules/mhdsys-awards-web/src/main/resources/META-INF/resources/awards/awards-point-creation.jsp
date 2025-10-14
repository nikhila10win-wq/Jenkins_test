<%@page import="com.mhdsys.awards.web.constants.AwardsWebPortletKeys"%>
<%@ include file="/init.jsp" %>

<portlet:resourceURL id="<%=AwardsWebPortletKeys.CREATE_AWARDS_POINTS%>" var="createAwardsPointURL" />

<form id="awards_point_creation" method="POST" enctype="multipart/form-data">
<div class="common-forms-div">
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="border-0 card shadow">
				
					<div class="align-items-center back-btn-cn card-header d-flex justify-content-between" style="">
						<h5 class="mb-0"> <liferay-ui:message key="awards-point-creation" /></h5>
							<div><a href="/group/guest/awards-points-list" class="btn btn-primary btn-sm rounded-pill back-btn-cn"><i class="bi bi-arrow-left-circle"></i>  <liferay-ui:message key="back" /></a></div>
					</div>
					
					<div class="card-body">
					<div class="card card-background p-0">
					<div class="card-header header-card"><liferay-ui:message key="awards-point-creation" /></div>
					<input type="hidden" id="awardsPointsId" name="awardsPointsId" value="${awardsPointsId}"/>
					<input type="hidden" id="mode" name="mode" value="${mode}"/>
					<div class="card-body">
						
						<div class="row">
							<div class="col-md-6">
								<div class="form-group">
									<label><liferay-ui:message key="competition-level"/><sup class="text-danger">*</sup></label>
									<select class="form-control" name ="competitionLevel" id="competitionLevel" <c:if test="${mode == 'view'}">disabled</c:if>>
										<option value=""><liferay-ui:message key="select"/></option>
										<c:forEach var="competitionLevel" items="${competitionLevels}">
											<option value="${competitionLevel.competitionLevelMasterId}"
											 <c:if test="${competitionLevel.competitionLevelMasterId == awardsPoints.competitionLevelId}">selected="selected"</c:if>>${competitionLevel.name_en}</option>
										</c:forEach>
									</select>
								</div>
							</div>
							
							<div class="col-md-6">
								<div class="form-group">
									<label><liferay-ui:message key="award-year"/><sup class="text-danger">*</sup></label>
									<select class="form-control" name="awardYear" id="awardYear" <c:if test="${mode == 'view'}">disabled</c:if>>
										<option value=""><liferay-ui:message key="select"/></option>
											<option value="2009-10" <c:if test="${'2009-10' == awardsPoints.awardYear}">selected="selected"</c:if>>2009-10</option>
											<option value="2010-11" <c:if test="${'2010-11' == awardsPoints.awardYear}">selected="selected"</c:if>>2010-11</option>
											<option value="2011-12" <c:if test="${'2011-12' == awardsPoints.awardYear}">selected="selected"</c:if>>2011-12</option>
											<option value="2012-13" <c:if test="${'2012-13' == awardsPoints.awardYear}">selected="selected"</c:if>>2012-13</option>
											<option value="2013-14" <c:if test="${'2013-14' == awardsPoints.awardYear}">selected="selected"</c:if>>2013-14</option>
											<option value="2014-15" <c:if test="${'2014-15' == awardsPoints.awardYear}">selected="selected"</c:if>>2014-15</option>
											<option value="2015-16" <c:if test="${'2015-16' == awardsPoints.awardYear}">selected="selected"</c:if>>2015-16</option>
											<option value="2016-17" <c:if test="${'2016-17' == awardsPoints.awardYear}">selected="selected"</c:if>>2016-17</option>
											<option value="2017-18" <c:if test="${'2017-18' == awardsPoints.awardYear}">selected="selected"</c:if>>2017-18</option>
											<option value="2018-19" <c:if test="${'2018-19' == awardsPoints.awardYear}">selected="selected"</c:if>>2018-19</option>
											<option value="2019-20" <c:if test="${'2019-20' == awardsPoints.awardYear}">selected="selected"</c:if>>2019-20</option>
											<option value="2020-21" <c:if test="${'2020-21' == awardsPoints.awardYear}">selected="selected"</c:if>>2020-21</option>
											<option value="2021-22" <c:if test="${'2021-22' == awardsPoints.awardYear}">selected="selected"</c:if>>2021-22</option>
											<option value="2022-23" <c:if test="${'2022-23' == awardsPoints.awardYear}">selected="selected"</c:if>>2022-23</option>
											<option value="2023-24" <c:if test="${'2023-24' == awardsPoints.awardYear}">selected="selected"</c:if>>2023-24</option>
									</select>
								</div>
							</div>
						</div>
						
						<div class="row">
						<div class="col-md-6">
								<div class="form-group">
									<label><liferay-ui:message key="award-name"/><sup class="text-danger">*</sup></label>
									<select class="form-control" name ="awardName" id="awardName" <c:if test="${mode == 'view'}">disabled</c:if>>
										<option value=""><liferay-ui:message key="select"/></option>
										<c:forEach var="awardName" items="${awardsNames}">
											<option value="${awardName.awardNameMasterId}" 
											 <c:if test="${awardName.awardNameMasterId == awardsPoints.awardNameId}">selected="selected"</c:if>>${awardName.name_en}</option>
										</c:forEach>
									</select>
								</div>
							</div>
							
							<div class="col-md-6">
								<div class="form-group">
									<label><liferay-ui:message key="sports-competition-level"/><sup class="text-danger">*</sup></label>
									<select class="form-control" name ="sportsCompetitionLevel" id="sportsCompetitionLevel"
									 <c:if test="${mode == 'view'}">disabled</c:if>>
										<option value=""><liferay-ui:message key="select"/></option>
										<c:forEach var="sportsCompLevel" items="${sportsCompLevels}">
											<option value="${sportsCompLevel.id}"
												<c:if test="${sportsCompLevel.id == awardsPoints.sportsCompetitionLevelId}">selected="selected"</c:if>>${sportsCompLevel.name_en}</option>
										</c:forEach>
									</select>
								</div>
							</div>
							</div>
							
							<div class="row">
							<div class="col-md-6">
								<div class="form-group">
									<label><liferay-ui:message key="sports-name"/><sup class="text-danger">*</sup></label>
									<select class="form-control" name ="sportsName" id="sportsName" <c:if test="${mode == 'view'}">disabled</c:if>>
										<option value=""><liferay-ui:message key="select"/></option>
										<c:forEach var="sportsName" items="${sportsMaster}">
											<option value="${sportsName.sportMasterId}"
												<c:if test="${sportsName.sportMasterId == awardsPoints.sportsNameId}">selected="selected"</c:if>>${sportsName.name_en}</option>
										</c:forEach>
									</select>
								</div>
							</div>
							
							<div class="col-md-6">
								<div class="form-group">
									<label><liferay-ui:message key="sports-type"/><sup class="text-danger">*</sup></label>
									<select class="form-control" name ="sportsType" id="sportsType"
									 <c:if test="${mode == 'view'}">disabled</c:if>>
										<option value=""><liferay-ui:message key="select"/></option>
										<c:forEach var="sportsType" items="${sportsTypes}">
											<option value="${sportsType.sportsTypeMasterId}"
											 <c:if test="${sportsType.sportsTypeMasterId == awardsPoints.sportsTypeId}">selected="selected"</c:if>>${sportsType.name_en}</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</div>
						
						<div class="row">
							<!-- <div class="col-md-4">
								<div class="form-group">
									<label><liferay-ui:message
											key="category" /><sup class="text-danger">*</sup></label>
									<div>
										<input type="radio" name="category" id="indivisual"
											value="1"  /> <label for="indivisual"><liferay-ui:message key="yes"/></label>
									</div>
									<div>
										<input type="radio" name="category" id="team"
											value="0" /> <label for="team"><liferay-ui:message key="no"/></label>
									</div>
								</div>
							</div> -->
							
							<div class="col-md-6">
								<div class="form-group">
									<label><liferay-ui:message key="category"/><sup class="text-danger">*</sup></label>
									<select class="form-control" name="category" <c:if test="${mode == 'view'}">disabled</c:if>>
										<option value="">Select</option>
										<c:forEach var="category" items="${categories}">
											<option value="${category.categoryMasterId}"
												<c:if test="${category.categoryMasterId == awardsPoints.category}">selected="selected"</c:if>>${category.name}</option>
										</c:forEach>
									</select>
								</div>
							</div>
							
							
							<div class="col-md-6">
								<div class="form-group">
									<label><liferay-ui:message key="winner"/><sup class="text-danger">*</sup></label>
									<input type="text" class="form-control" id="winner" name="winner" value="${awardsPoints.winner}" <c:if test="${mode == 'view'}">disabled</c:if> />
								</div>
							</div>
						</div>
						
						<div class="row">
							<div class="col-md-6">
								<div class="form-group">
									<label><liferay-ui:message key="first-runner-up"/><sup class="text-danger">*</sup></label>
									<input type="text" class="form-control" id="firstRunnerUp" name="firstRunnerUp" value="${awardsPoints.firstRunnerUp}" <c:if test="${mode == 'view'}">disabled</c:if> />
								</div>
							</div>
							
							<div class="col-md-6">
								<div class="form-group">
									<label><liferay-ui:message key="second-runner-up"/><sup class="text-danger">*</sup></label>
									<input type="text" class="form-control" name="secondRunnerUp" id="secondRunnerUp" value="${awardsPoints.secondRunnerUp}" <c:if test="${mode == 'view'}">disabled</c:if>/>
								</div>
							</div>
						</div>
							
						<div class="row">
						<div class="col-md-6">
							<div class="form-group">
								<label><liferay-ui:message key="participant"/><sup class="text-danger">*</sup></label>
								<input type="text" class="form-control" name="participant" id="participant" value="${awardsPoints.participant}" <c:if test="${mode == 'view'}">disabled</c:if>/>
							</div>
						</div>
						</div>
						
						</div>
					</div>
				</div>
					
					<c:if test="${mode != 'view'}">
						<div class="card-footer bg-transparent text-right p-4">
								<button type="button" class="btn btn-primary" onclick="location.href='/group/guest/awards-points-list';">
	                        		<liferay-ui:message key="cancel" />
	                    		</button>
	                            		
		                         <button class="btn btn-primary" type="button" onclick="location.reload();">
									<liferay-ui:message key="reset" />
								 </button>
						
								<button class="btn btn-primary" type="button" onclick="createAwardsPoint(event)">
									<c:if test="${mode eq 'edit'}">
											<liferay-ui:message key="update"/>
									</c:if>
									<c:if test="${mode ne 'edit'}">
											<liferay-ui:message key="save"/>
									</c:if>
								</button>
						</div>
					</c:if>
					
				</div>
			</div>
		</div>
	</div>
</div>
</form>


<!-- modal popup for awards point creation -->
<div class="modal fade" id="saveAwardsPointModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
			<div class="modal-dialog modal-dialog-centered" role="document">
				<div class="modal-content modal-bg">
					<div class="modal-header justify-content-center align-items-center">
						<h5 class="modal-title" id="exampleModalLongTitle"><liferay-ui:message key="create-awards-points"/></h5>
						<button type="button" class="close d-none" data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body">
						<div class="row">
							<div class="col-md-12 text-center">
									<div> <p id="success-application"></p> </div>
							</div>
						</div>
					</div>
					<div class="modal-footer d-flex justify-content-end">
       					 <a href="/group/guest/awards-points-list" type="button" id="closeModal" class="btn btn-secondary maha-save-btn" data-bs-dismiss="modal" onclick="closeModal()">
       					 	<liferay-ui:message key="close"/>
       					 </a>
					</div>
				</div>
			</div>
		</div>
<!-- modal popup for awards point creation -->


<script>
$(document).ready(function () {
	// j-Query validation start
    $('#awards_point_creation').validate({
    	
		onkeyup: function (element) {
				$(element).valid();
		  },
		onchange: function (element) {
			    $(element).valid();
		  },
		  
		rules:{
			competitionLevel: {
				required:true,
			},
			awardYear: { 						
				required:true,
			},
			awardName: {
				required:true,
			},
			sportsCompetitionLevel: { 						
				required:true,
			},
			sportsName: {
				required:true,
			},
			sportsType: { 						
				required:true,
			},
			category: {
				required:true,
			},
			firstRunnerUp: { 						
				required:true,
				pattern: /^\d*\.?\d+$/,
			},
			winner: {
				required:true,
				pattern: /^\d*\.?\d+$/,
			},
			secondRunnerUp: { 						
				required:true,
				pattern: /^\d*\.?\d+$/,
			},
			participant: { 						
				required:true,
				pattern: /^\d*\.?\d+$/,
			}
			
		},messages:{
			competitionLevel: {
				required:"<liferay-ui:message key="please-select-competition-level"/>",
			},
			awardYear: { 						
				required:"<liferay-ui:message key="please-select-award-year"/>",
			},
			awardName: {
				required:"<liferay-ui:message key="please-select-award-name"/>",
			},
			sportsCompetitionLevel: { 						
				required:"<liferay-ui:message key="please-select-sports-comp-level"/>",
			},
			sportsName: {
				required:"<liferay-ui:message key="please-select-sports-name"/>",
			},
			sportsType: { 						
				required:"<liferay-ui:message key="please-select-sports-type"/>",
			},
			category: {
				required:"<liferay-ui:message key="please-select-category"/>",
			},
			firstRunnerUp: { 						
				required:"<liferay-ui:message key="please-enter-first-runner-up"/>",
				pattern:"<liferay-ui:message key="please-enter-valid-runner-up"/>",
			},
			winner: {
				required:"<liferay-ui:message key="please-enter-winner"/>",
				pattern:"<liferay-ui:message key="please-enter-valid-winner"/>",
			},
			secondRunnerUp: { 						
				required:"<liferay-ui:message key="please-enter-second-runner-up"/>",
				pattern:"<liferay-ui:message key="please-enter-valid-second-runner-up"/>",
			},
			participant: {
				required:"<liferay-ui:message key="please-enter-participant"/>",
				pattern:"<liferay-ui:message key="please-enter-valid-participant"/>",
			}
		},
	}); 
	// j-Query validation end
});


function createAwardsPoint(event){debugger
	var form = $("#awards_point_creation")[0];
	var formData = new FormData(form);
	
	if (event) {
        event.preventDefault(); 
    }
	
if($('#awards_point_creation').valid()){
 $.ajax({
        type: "POST",
        url: '${createAwardsPointURL}' ,
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
        	if(data.createAwardPoints === true){
        		var msg = "";
        			
        		if(data.mode === "edit"){
        			msg = "<liferay-ui:message key="awards-points-update-successfully"/>";
        	   		$('#success-application').html(msg);
        		} else {
        			msg = "<liferay-ui:message key="awards-points-created-successfully"/>";
        	   		$('#success-application').html(msg);
        		}
        		 $("#saveAwardsPointModal").modal('show');
        	}else{
        		var msg = "<liferay-ui:message key="awards-point-creation-is-unsuccessfull"/>";
        	    $('#success-application').html(msg);
        		 $("#saveAwardsPointModal").modal('show');
        	}
    	 }
       
    });
  }
};

function closeModal() {debugger
    $("#saveAwardsPointModal").modal('hide');
	$(".modal-backdrop").remove();
	$("body").removeClass("modal-open");
}
</script>