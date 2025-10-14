<style>
table td, table th {
 min-width:180px;
}

.modal-body {
  overflow-x: auto;
  margin-right: 1rem;
}


</style>

<div class="tab-pane fade" id="coach-detail" role="tabpanel" aria-labelledby="coach-detail-tab">
            <input type="hidden" value="${coachIDs}" name="coachIds" id="coachIDs">
            
            <div class="card card-background p-0">
                <div class="coach_details">
                    <div class="card-header header-card"><liferay-ui:message key="coach-detail" /></div>
                    <div class="card-body">
                        <!-- <div class="form-section"> -->
                        <div class="">
					          
					        <%--  <div class="flex justify-between items-center">
					       
					           <c:if test="${mode eq 'edit' or mode eq 'add'}"> <button type="button" id="addCoachBtn" class="btn btn-secondary">+ Add More</button></c:if>
					          </div>  --%>
					         <%-- <div>
					         	<label for="coach" class="form-label"><liferay-ui:message key="coach" /></label>
	                            <select  id="coach" name="coach" class="form-control" <c:if test="${mode == 'view'}">disabled</c:if>>
	                                <option value=""><liferay-ui:message key="select-coach" /></option>
	                               	  <c:forEach items="${coachList}" var="coach">
	                            			<option value="${coach.schoolCollegeOfficerRegistrationId}">${coach.firstName} ${coach.lastName}</option>
	                            		</c:forEach>	
	                            </select>
					         </div> --%>
					         
					         
					         
					   <c:if test="${mode eq 'edit' or mode eq 'view'}">
		        		 <div id="coachesContainer" class="">
		        		 	<c:if test="${not empty coachDTO}">
			        		 		<c:forEach items="${coachDTO}" var="coachc" varStatus="coachdetailCounter">
		                    			 <div class="coach-entry "> 
					                        <%-- <h3 class="font-semibold text-lg mb-4 text-gray-700">Coach # ${coachdetailCounter.count}</h3>  --%>
					                        <input type="hidden" value="${coachc.coachId}" name="coachId_${coachdetailCounter.count}"/>
					                        <div class="row">
					                        <%-- <c:forEach items="${coachList}" var="coa" varStatus="cCounter">
					                        	<c:if test="${coachc.mobileNumber == coa.mobileNumber}">truee</c:if> 
					                        	<c:if test="${coachc.mobileNumber eq coa.mobileNumber}">truee</c:if> 
					                        </c:forEach> --%>
					                        	<div class="col-md-6">
				                					 <div class="form-group">
				                					 <label for="coachselect_${coachdetailCounter.count}" class="form-label"><liferay-ui:message key="select-coach" /><sup class="text-danger">*</sup></label>
				                					 <select class="form-control coachcls coachselect testcls" id="coachselect_${coachdetailCounter.count}" name="coachselect_${coachdetailCounter.count}" <c:if test="${mode == 'view'}">disabled</c:if>>
				                					 	<option value="">select</option>
				                					 	<c:forEach items="${coachList}" var="coa" varStatus="cCounter">
				                					 		<option value="${coa.userId}" <c:if test="${coachc.email eq coa.email}">selected</c:if> >${coa.firstName} ${coa.lastName}</option>
				                					 	</c:forEach>
				                					 </select>
				                					  <p class="error-container" id="coach-error_${coachdetailCounter.count}"></p>
			                					 </div>
			                					</div>
					                        	<div class="col-md-6">
					                        		<div class="form-group">
						                        	 	<label class="form-label"><liferay-ui:message key="type" /><sup class="text-danger">*</sup></label> 
						                                <div class="flex gap-4"> 
						                                   <label class="flex items-center"><input <c:if test="${mode == 'view'}">disabled</c:if> type="radio" name="coach_type_${coachdetailCounter.count}" id="government_${coachdetailCounter.count}" value="government" <c:if test="${coachc.coachType =='government'}">checked</c:if> class="mr-2 coachtypecls"><liferay-ui:message key="government" /> </label> 
						                                   <label class="flex items-center"><input <c:if test="${mode == 'view'}">disabled</c:if> type="radio" name="coach_type_${coachdetailCounter.count}" id="private_${coachdetailCounter.count}" value="private" <c:if test="${coachc.coachType =='private'}">checked</c:if> class="mr-2 coachtypecls"><liferay-ui:message key="private" /> </label> 
						                                </div>
						                                <p class="error-container" id="coachtype-error_${coachdetailCounter.count}"></p> 
					                               </div>
					                        	</div>
					                        	
					                        	<div class="col-md-6">
					                        		<div class="form-group">
					                        		  <label for="coach_name_${coachdetailCounter.count}" class="form-label"><liferay-ui:message key="coach-full-name" /><sup class="text-danger">*</sup></label> 
					                                <input <c:if test="${mode == 'view'}">disabled</c:if> type="text" id="coach_name_${coachdetailCounter.count}" name="coach_name_${coachdetailCounter.count}" class="form-input coachNamecls" placeholder="Enter full name" value="${coachc.fullName}">
					                        	 	 <p class="error-container" id="coachname-error_${coachdetailCounter.count}"></p>
					                        	 </div>
					                        	</div>
					                        	
					                        	<div class="col-md-6">
					                        		<div class="form-group">
					                        		    <label for="coach_mobile_${coachdetailCounter.count}" class="form-label"><liferay-ui:message key="mobile-number" /><sup class="text-danger">*</sup></label> 
					                                <input <c:if test="${mode == 'view'}">disabled</c:if> type="tel" id="coach_mobile_${coachdetailCounter.count}" name="coach_mobile_${coachdetailCounter.count}" class="form-input mobcls" placeholder="Enter mobile number" value="${coachc.mobileNumber}">
					                        	 	<p class="error-container" id="coachmobile-error_${coachdetailCounter.count}"></p>
					                        	 </div>
					                        	</div>
					                        	
					                        	
					                        	<div class="col-md-6">
					                        		<div class="form-group">
					                        	 		<label for="coach_email_${coachdetailCounter.count}" class="form-label"><liferay-ui:message key="email" /><sup class="text-danger">*</sup></label> 
					                                	<input <c:if test="${mode == 'view'}">disabled</c:if> type="email" id="coach_email_${coachdetailCounter.count}" name="coach_email_${coachdetailCounter.count}" class="form-input emailcls" placeholder="Enter email address" value="${coachc.email}">
					                        			<p class="error-container" id="coachemail-error_${coachdetailCounter.count}"></p>
					                        	</div>
					                        	</div>
					                        	
					                        	<div class="col-md-6">
					                        		<div class="form-group">
					                        			 <label for="coach_sports_name_${coachdetailCounter.count}" class="form-label"><liferay-ui:message key="sports-name" /><sup class="text-danger">*</sup></label> 
					                                	<input <c:if test="${mode == 'view'}">disabled</c:if> type="text" id="coach_sports_name_${coachdetailCounter.count}" name="coach_sports_name_${coachdetailCounter.count}" class="form-input coachsportsCls" placeholder="e.g., Archery, Boxing" value="${coachc.sportsName}">
					                        			<p class="error-container" id="coachsports-error_${coachdetailCounter.count}"></p>
					                        		</div>
					                        	</div>
					                        	
					                        	<div class="col-md-6">
					                        		<div class="form-group">
					                        		 	<label for="coach_address_${coachdetailCounter.count}" class="form-label"><liferay-ui:message key="residence-address" /><sup class="text-danger">*</sup></label> 
					                               		 <textarea <c:if test="${mode == 'view'}">disabled</c:if> id="coach_address_${coachdetailCounter.count}" name="coach_address_${coachdetailCounter.count}" rows="2" class="form-textarea coachaddresscls" placeholder="Enter residence address" >${coachc.address}</textarea>
					                        			<p class="error-container" id="coach_address_-error_${coachdetailCounter.count}"></p>
					                        		</div>
					                        	</div>
					                        	
					                        	<div class="col-md-6">
					                        		<div class="form-group">
					                                <label class="form-label"><liferay-ui:message key="sports-uniform-size" /><sup class="text-danger">*</sup><span class="ml-2"><i class="bi bi-chat-square-text"></i></span></label>
					                                <div class="grid grid-cols-2 md:grid-cols-4 gap-4"> 
					                                    <label class="form-label"><liferay-ui:message key="tracksuit-size"/><sup class="text-danger">*</sup></label>
					                                    <input <c:if test="${mode == 'view'}">disabled</c:if> type="text" name="tracksuit_size_${coachdetailCounter.count}" id="tracksuit_size_${coachdetailCounter.count}" class="form-input coachtracksuitcls" placeholder="Tracksuit" value="${coachc.tracksuitSize}"> 
					                                   	<p class="error-container" id="tracksuit_size_-error_${coachdetailCounter.count}"></p>
					                                    <label class="form-label"><liferay-ui:message key="tshirt-size"/><sup class="text-danger">*</sup></label>
					                                    <input <c:if test="${mode == 'view'}">disabled</c:if>  type="text" name="tshirt_size_${coachdetailCounter.count}" id="tshirt_size_${coachdetailCounter.count}" class="form-input coachtshirsscls" placeholder="T-shirt" value="${coachc.tshirtSize}"> 
					                                    <p class="error-container" id="tshirt_size_-error_${coachdetailCounter.count}"></p>
					                                    <label class="form-label"><liferay-ui:message key="shorts-size"/><sup class="text-danger">*</sup></label>
					                                    <input <c:if test="${mode == 'view'}">disabled</c:if> type="text" name="shorts_size_${coachdetailCounter.count}" id="shorts_size_${coachdetailCounter.count}" class="form-input coachshortscls" placeholder="Shorts" value="${coachc.shortsSize}"> 
					                                    <p class="error-container" id="shorts_size_-error_${coachdetailCounter.count}"></p>
					                                    <label class="form-label"><liferay-ui:message key="shoes-size"/><sup class="text-danger">*</sup></label>
					                                    <input <c:if test="${mode == 'view'}">disabled</c:if> type="text" name="shoes_size_${coachdetailCounter.count}" id="shoes_size_${coachdetailCounter.count}" class="form-input coachshoescls" placeholder="Shoes" value="${coachc.shoesSize}"> 
					                                	<p class="error-container" id="shoes_size-error_${coachdetailCounter.count}"></p>
					                                </div>
					                        	</div>
					                        	</div>
					                        	
					                        	
					                        	<div class="col-md-6">
					                        		<div class="form-group">
						                        	<label for="district" class="form-label"><liferay-ui:message key="district" /><sup class="text-danger">*</sup></label>
							                            <select name="coachdistrictselect_${coachdetailCounter.count}" id="coachdistrictselect_${coachdetailCounter.count}" class="form-control coachdistrictcls coachdistrictselect" <c:if test="${mode == 'view'}">disabled</c:if>>
							                                <option value=""><liferay-ui:message key="select-district" /></option>
							                                 <c:forEach items="${districts}" var="district">
							                            			<option value="${district.districtId}" <c:if test="${district.districtId == coachc.district}">selected</c:if> >${district.districtName_en}</option>
							                            		</c:forEach>	
							                            </select>
					                        		</div>
					                        		<p class="error-container" id="coachdistrictselect_-error_${coachdetailCounter.count}"></p>
					                        	</div>
					                        	
					                        	<div class="col-md-6">
					                        		<div class="form-group">
					                        	 <label for="coach_remarks_${coachdetailCounter.count}" class="form-label"><liferay-ui:message key="remarks-by-dso" /></label> 
					                                <textarea <c:if test="${mode == 'view'}">disabled</c:if> id="coach_remarks_${coachdetailCounter.count}" name="coach_remarks_${coachdetailCounter.count}" rows="2" class="form-textarea  coachremarkscls" placeholder="Enter any remarks">${coachc.remarks}</textarea>
					                        		  <p class="error-container" id="coachdsoremarks_error_${coachdetailCounter.count}"></p>
					                        	</div>
					                        	</div>
					                        </div>
					                        
					                    </div>
	                     	</c:forEach>
		        		</c:if>
		        		  <c:if test="${empty coachDTO}">
					              <!-- Coach Entry Template is in JS -->
					              <div class="coach-entry "> 
					                        <div class="row">
					                        	<div class="col-md-6">
				                					 <div class="form-group">
				                					 <label for="coachselect_1" class="form-label"><liferay-ui:message key="select-coach" /><sup class="text-danger">*</sup></label>
				                					 <select class="form-control coachcls coachselect testcls " id="coachselect_1" name="coachselect_1" <c:if test="${mode == 'view'}">disabled</c:if>>
				                					 	<option value="">select</option>
				                					 	<c:forEach items="${coachList}" var="coa" varStatus="cCounter">
				                					 		<option value="${coa.userId}">${coa.firstName} ${coa.lastName}</option>
				                					 	</c:forEach>
				                					 </select>
				                					  <p class="error-container" id="coach-error_1"></p>
			                					 </div>
			                					</div>
					                        	<div class="col-md-6">
					                        		<div class="form-group">
						                        	 	<label class="form-label"><liferay-ui:message key="type" /><sup class="text-danger">*</sup></label> 
						                                <div class="flex gap-4"> 
						                                   <label class="flex items-center"><input <c:if test="${mode == 'view'}">disabled</c:if> type="radio" name="coach_type_1" id="government_1" value="government" <c:if test="${coachc.coachType =='government'}">checked</c:if> class="mr-2 coachtypecls"><liferay-ui:message key="government" /> </label> 
						                                   <label class="flex items-center"><input <c:if test="${mode == 'view'}">disabled</c:if> type="radio" name="coach_type_1" id="private_1" value="private" <c:if test="${coachc.coachType =='private'}">checked</c:if> class="mr-2 coachtypecls"><liferay-ui:message key="private" /> </label> 
						                                </div>
						                                <p class="error-container" id="coachtype-error_1"></p> 
					                               </div>
					                        	</div>
					                        	
					                        	<div class="col-md-6">
					                        		<div class="form-group">
					                        		  <label for="coach_name_1" class="form-label"><liferay-ui:message key="coach-full-name" /><sup class="text-danger">*</sup></label> 
					                                <input <c:if test="${mode == 'view'}">disabled</c:if> type="text" id="coach_name_1" name="coach_name_1" class="form-input coachNamecls" placeholder="Enter full name" value="${coachc.fullName}">
					                        	 	 <p class="error-container" id="coachname-error_1"></p>
					                        	 </div>
					                        	</div>
					                        	
					                        	<div class="col-md-6">
					                        		<div class="form-group">
					                        		    <label for="coach_mobile_1" class="form-label"><liferay-ui:message key="mobile-number" /><sup class="text-danger">*</sup></label> 
					                                <input <c:if test="${mode == 'view'}">disabled</c:if> type="tel" id="coach_mobile_1" name="coach_mobile_1" class="form-input mobcls" placeholder="Enter mobile number" value="${coachc.mobileNumber}">
					                        	 	<p class="error-container" id="coachmobile-error_1"></p>
					                        	 </div>
					                        	</div>
					                        	
					                        	
					                        	<div class="col-md-6">
					                        		<div class="form-group">
					                        	 		<label for="coach_email_1" class="form-label"><liferay-ui:message key="email" /><sup class="text-danger">*</sup></label> 
					                                	<input <c:if test="${mode == 'view'}">disabled</c:if> type="email" id="coach_email_1" name="coach_email_1" class="form-input emailcls" placeholder="Enter email address" value="${coachc.email}">
					                        			<p class="error-container" id="coachemail-error_1"></p>
					                        	</div>
					                        	</div>
					                        	
					                        	<div class="col-md-6">
					                        		<div class="form-group">
					                        			 <label for="coach_sports_name_1" class="form-label"><liferay-ui:message key="sports-name" /><sup class="text-danger">*</sup></label> 
					                                	<input <c:if test="${mode == 'view'}">disabled</c:if> type="text" id="coach_sports_name_1" name="coach_sports_name_1" class="form-input coachsportsCls" placeholder="e.g., Archery, Boxing" value="${coachc.sportsName}">
					                        			<p class="error-container" id="coachsports-error_1"></p>
					                        		</div>
					                        	</div>
					                        	
					                        	<div class="col-md-6">
					                        		<div class="form-group">
					                        		 	<label for="coach_address_1" class="form-label"><liferay-ui:message key="residence-address" /><sup class="text-danger">*</sup></label> 
					                               		 <textarea <c:if test="${mode == 'view'}">disabled</c:if> id="coach_address_1" name="coach_address_1" rows="2" class="form-textarea coachaddresscls" placeholder="Enter residence address" >${coachc.address}</textarea>
					                        			<p class="error-container" id="coach_address_-error_1"></p>
					                        		</div>
					                        	</div>
					                        	
					                        	<div class="col-md-6">
					                        		<div class="form-group">
					                                <label class="form-label"><liferay-ui:message key="sports-uniform-size" /><sup class="text-danger">*</sup><span class="ml-2"><i class="bi bi-chat-square-text"></i></span></label>
					                                <div class="grid grid-cols-2 md:grid-cols-4 gap-4"> 
					                                    <label class="form-label"><liferay-ui:message key="tracksuit-size"/><sup class="text-danger">*</sup></label>
					                                    <input <c:if test="${mode == 'view'}">disabled</c:if> type="text" name="tracksuit_size_1" id="tracksuit_size_1" class="form-input coachtracksuitcls" placeholder="Tracksuit" value="${coachc.tracksuitSize}"> 
					                                   	<p class="error-container" id="tracksuit_size_-error_1"></p>
					                                    <label class="form-label"><liferay-ui:message key="tshirt-size"/><sup class="text-danger">*</sup></label>
					                                    <input <c:if test="${mode == 'view'}">disabled</c:if>  type="text" name="tshirt_size_1" id="tshirt_size_1" class="form-input coachtshirsscls" placeholder="T-shirt" value="${coachc.tshirtSize}"> 
					                                    <p class="error-container" id="tshirt_size_-error_1"></p>
					                                    <label class="form-label"><liferay-ui:message key="shorts-size"/><sup class="text-danger">*</sup></label>
					                                    <input <c:if test="${mode == 'view'}">disabled</c:if> type="text" name="shorts_size_1" id="shorts_size_1" class="form-input coachshortscls" placeholder="Shorts" value="${coachc.shortsSize}"> 
					                                    <p class="error-container" id="shorts_size_-error_1"></p>
					                                    <label class="form-label"><liferay-ui:message key="shoes-size"/><sup class="text-danger">*</sup></label>
					                                    <input <c:if test="${mode == 'view'}">disabled</c:if> type="text" name="shoes_size_1" id="shoes_size_1" class="form-input coachshoescls" placeholder="Shoes" value="${coachc.shoesSize}"> 
					                                	<p class="error-container" id="shoes_size-error_1"></p>
					                                </div>
					                        	</div>
					                        	</div>
					                        	
					                        	
					                        	<div class="col-md-6">
					                        		<div class="form-group">
						                        	<label for="district" class="form-label"><liferay-ui:message key="district" /><sup class="text-danger">*</sup></label>
							                            <select name="coachdistrictselect_1" id="coachdistrictselect_1" class="form-control coachdistrictcls coachdistrictselect" <c:if test="${mode == 'view'}">disabled</c:if>>
							                                <option value=""><liferay-ui:message key="select-district" /></option>
							                                 <c:forEach items="${districts}" var="district">
							                            			<option value="${district.districtId}">${district.districtName_en}</option>
							                            		</c:forEach>	
							                            </select>
					                        		</div>
					                        		<p class="error-container" id="coachdistrictselect_-error_1"></p>
					                        	</div>
					                        	
					                        	<div class="col-md-6">
					                        		<div class="form-group">
					                        	 <label for="coach_remarks_1" class="form-label"><liferay-ui:message key="remarks-by-dso" /></label> 
					                                <textarea <c:if test="${mode == 'view'}">disabled</c:if> id="coach_remarks_1" name="coach_remarks_1" rows="2" class="form-textarea coachremarkscls" placeholder="Enter any remarks">${coachc.remarks}</textarea>
					                        		  <p class="error-container" id="coachdsoremarks_error_1"></p>
					                        	</div>
					                        	</div>
					                        </div>
					                    </div>
					        
		        		  </c:if>
	          			</div>
					   </c:if>
			          <c:if test="${mode eq 'add'}">
			          <div id="coachesContainer" class="">
			              <!-- Coach Entry Template is in JS -->
			          </div>
			          </c:if>
					      <input type="hidden" value="1" id ="coachCount" name="coachCount"/>
					     
					      </div>
					<div class="text-right">
					    <c:if test="${mode eq 'edit' or mode eq 'add'}">
					        <button type="button" id="addCoachBtn" class="btn btn-secondary">
					            + Add More
					        </button>
					    </c:if>
					</div>
                    </div>
                </div>
            </div>
             <div class="card-footer bg-transparent text-right p-4">
               	   <c:if test="${mode eq 'add' or mode eq 'edit'}">
	               	  <button type="button" class="btn btn-primary" onclick="location.href='/group/guest/dashboard';">
	                       	<liferay-ui:message key="cancel" />
	                	</button>
	               		 <button class="btn btn-primary" type="button" onclick="location.reload();">
							<liferay-ui:message key="reset" />
						</button>
				 		  <button type="button" class="btn btn-primary" onclick="saveTrainingCenterDetails(event,'Draft','coach')">
	                            <liferay-ui:message key="save-as-draft" />
	                   </button> 
	                  <button type="button" class="btn btn-primary" onclick="setActiveTab('existingtrainingcentredetails','coach-detail','previous')">
	                       <liferay-ui:message key="previous" />
	                   </button>
	                  <button type="button" class="btn btn-primary" onclick="setActiveTab('sports-person','coach-detail','next')">
	                     <liferay-ui:message key="next" />
	                 </button>
                 </c:if>
                 <c:if test="${mode eq 'view'}">
                 		 <button type="button" class="btn btn-primary" onclick="setActiveTab('existingtrainingcentredetails','coach-detail','previous')">
	                       <liferay-ui:message key="previous" />
	                   </button>
                 		 <button type="button" class="btn btn-primary" onclick="setActiveTab('sports-person','coach-detail','next')">
                         	<liferay-ui:message key="next" />
                    	 </button>
                	</c:if>
                 
              </div>
</div>

<div class="modal fade" id="saveSizeChartModal" role="dialog" aria-labelledby="examplesaveSizeChartModal" aria-hidden="true" tabindex="-1" data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog modal-dialog-centered" role="document">
		<div class="modal-content modal-bg">
			<div class="modal-header justify-content-center align-items-center">
				<h5 class="modal-title"><liferay-ui:message key="sports-uniform-size" /></h5>
				<a href="#" class="maha-save-btn" id="modalCloseBtn" onclick="closeModal()">
					<i class="bi bi-x-circle text-danger"></i>
				</a>
			</div>
		<div class="modal-body">
			<div class="universal-table">
				<div class="">
				<table id="size-charts-data" class="table-bordered" width="100%">
				<thead>
					<tr>
					<th><liferay-ui:message key="Product In Inches" /></th>
					<th><liferay-ui:message key="Small" /></th>
					<th><liferay-ui:message key="Medium" /></th>
					<th><liferay-ui:message key="Large" /></th>
					<th><liferay-ui:message key="X-Large" /></th>
					<th><liferay-ui:message key="XX-Large" /></th>
					</tr>
				</thead>
			<tbody>
	           	<tr>
					<th>Tracksuit</th>
					<td>48 inch (121.9 cm)</td>
					<td>51 inch ( 129.5 cm)</td>
					<td>55 inch (139.7 cm)</td>
					<td>59 inch (149.9 cm)</td>
					<td>63 inch (160.0 cm)</td>
				</tr>
				<tr>
					<th>Tshirt</th>
					<td>48 inch (121.9 cm)</td>
					<td>51 inch (129.5 cm)</td>
					<td>55 inch (139.7 cm)</td>
					<td>59 inch (149.9 cm)</td>
					<td>63 inch (160.0 cm)</td>
				</tr>
				<tr>
					<th>Shorts</th>
					<td>30 inch (76.2 cm)</td>
					<td>32 inch (81.3 cm)</td>
					<td>34 inch (86.4 cm)</td>
					<td>37 inch (94.0 cm)</td>
					<td>40 inch (101.6 cm)</td>
				</tr>
				<tr>
					<th>Shoes</th>
					<td>8 inch (20.3 cm)</td>
					<td>9 inch (22.9 cm)</td>
					<td>10 inch (25.4 cm)</td>
					<td>11 inch (27.9 cm)</td>
					<td>12 inch (30.5 cm)</td>
				</tr>
				</tbody>
			</table>
		</div>
	</div>
</div>
	<!-- <div class="card-footer bg-transparent text-right p-4">
	<div class="d-flex justify-content-end">
	<a href="#" class="btn btn-secondary maha-save-btn" id="modalCloseBtn" onclick="closeModal()">
	<liferay-ui:message key="close"/>
	</a>
	</div>
	</div> -->
	</div>
	</div>
</div>
<script>                        

	/* function closeModal(){
		console.log("Close model clicked ::");
		$('#saveSizeChartModal').addClass("fade");
		$('#saveSizeChartModal').hide();
	} */
	
	$(document).ready(function(){
		$('#modalCloseBtn').click(function(){
			$('#saveSizeChartModal').addClass("fade");
			$('#saveSizeChartModal').hide();
		});
		
		
		$(document).on('click','.bi-chat-square-text',function(){
			$('#saveSizeChartModal').removeClass("fade");
			$('#saveSizeChartModal').show();
		});
			
		$(document).on('change','.coachselect',function(e){
			console.log("coach :: change function called ::");
			var selectid=$(this).prop('id');
			var idcounter=selectid.split('_');
			console.log("idcounter ::"+idcounter[1]);
			var idvalue=idcounter[1];
			
			console.log("idvalue :::" +idvalue);
			
			var id=$(this).val();
			console.log("id :::"+id);
			if(id){
				document.getElementById("coach-error_"+idvalue).textContent ='';
				$.ajax({
	     			type: "POST",
	     			datatype:"json",
	     	        url: '${getCoachURL}', 
	     	        data: {
	     	        	id:id
	     	        },
	     			async : false,
	     	        success: function(data) {
	     	        	console.log("Data :::" +data);
	     	        	var response=JSON.parse(data);
	     	        	//console.log("Data.name :::" +response.name);
	     	        	if(response!=null && response!= ''){
	     	        		$('#coach_name_'+idvalue).val(response.name);
	     	        		$('#coach_mobile_'+idvalue).val(response.mobile);
	     	        		$('#coach_email_'+idvalue).val(response.email);
	     	        		if(response.type=='Government'){
	     	        			console.log("inside government");
	     	        			$('#government_'+idvalue).prop("checked", true);
	     	        		}else if(response.type=='Private'){
	     	        			console.log("inside private");
	     	        			$('#private_'+idvalue).prop("checked", true);
	     	        		}
	     	        		document.getElementById("coachname-error_"+idvalue).textContent ='';
	     	        		document.getElementById("coachmobile-error_"+idvalue).textContent ='';
	     	        		document.getElementById("coachemail-error_"+idvalue).textContent ='';
	     	        	}
	     	        },error:function(resp){
	     	        	console.log("Error in getting coach details :: ");
	     	        }
	     		});
			}else{
				document.getElementById("coach-error_"+idvalue).textContent ='<liferay-ui:message key="please-select-coach" />';
			}
		});
		
		//change event of coachtype
		$(document).on('change','.coachtypecls',function() {
            var selectedValue = $(this).val();
            console.log("Selected radio button value: " + selectedValue);
            // Perform actions based on the selected value
            if(selectedValue){
            	var currentName=$(this).attr("name");
            	console.log("currentName ::"+currentName);
            	var currentCounter=currentName.split("_");
            	document.getElementById("coachtype-error_"+currentCounter[2]).textContent = "";
            }
        });
		 
		//keyup event of name
		$(document).on('keyup','.coachNamecls',function(){
			var coachNameVal=$.trim($(this).val().replace(/\s{2,}/g, ' '));
			var countCurrentId=$(this).attr('id');
			var currentCounter=countCurrentId.split("_");
			var regex =/^[a-zA-Z .]*$/; 
			
			if(coachNameVal){
				if(coachNameVal.length<3){
					document.getElementById("coachname-error_"+currentCounter[2]).textContent ='<liferay-ui:message key="please-enter-atleast-3-character" />';
				}else if(!regex.test(coachNameVal)){
					console.log("Error 2");
					document.getElementById("coachname-error_"+currentCounter[2]).textContent ='<liferay-ui:message key="please-enter-alphabates-only" />';
				}else if(/([.\/ ])\1+/.test(coachNameVal)){
					document.getElementById("coachname-error_"+currentCounter[2]).textContent ='<liferay-ui:message key="two-special-character-not-allowed" />';
				}else{
					console.log("No error ");
					document.getElementById("coachname-error_"+currentCounter[2]).textContent ='<liferay-ui:message key="" />';
				}
			}else{
				console.log("No error ");
				document.getElementById("coachname-error_"+currentCounter[2]).textContent ='<liferay-ui:message key="please-enter-name" />';
			}
			
		});
		//blur event of name
		$(document).on('blur','.coachNamecls',function(){
			var coachNameVal=$.trim($(this).val().replace(/\s{2,}/g, ' '));
			$(this).val(coachNameVal);
			var countCurrentId=$(this).attr('id');
			var currentCounter=countCurrentId.split("_");
			var regex =/^[a-zA-Z .]*$/; 
			if(coachNameVal){
				if(coachNameVal.length<3){
					document.getElementById("coachname-error_"+currentCounter[2]).textContent ='<liferay-ui:message key="please-enter-atleast-3-character" />';
				}else if(!regex.test(coachNameVal)){
					console.log("Error 2");
					document.getElementById("coachname-error_"+currentCounter[2]).textContent ='<liferay-ui:message key="please-enter-alphabates-only" />';
				}else if(/([.\/ ])\1+/.test(coachNameVal)){
					document.getElementById("coachname-error_"+currentCounter[2]).textContent ='<liferay-ui:message key="two-special-character-not-allowed" />';
				}else{
					console.log("No error ");
					document.getElementById("coachname-error_"+currentCounter[2]).textContent ='<liferay-ui:message key="" />';
				}
			}else{
				console.log("No error ");
				document.getElementById("coachname-error_"+currentCounter[2]).textContent ='<liferay-ui:message key="please-enter-name" />';
			}
		});
		
		$(document).on('keyup','.mobcls',function(){
			//var mobileNumberPattern = /^[0-9]{10}$/; 
			var mobileNumberPattern = /^[6-9][0-9]{0,9}$/; 
			var mobValue=$(this).val();
			//console.log("mobValue :::"+mobValue);
			var countCurrentId=$(this).attr('id');
			var currentCounter=countCurrentId.split("_");
			if(mobValue){
				if(!mobileNumberPattern.test(mobValue)){
					document.getElementById("coachmobile-error_"+currentCounter[2]).textContent ='<liferay-ui:message key="please-enter-a-valid-mobile" />';
				}else if(mobValue.length<10){
					document.getElementById("coachmobile-error_"+currentCounter[2]).textContent ='<liferay-ui:message key="please-enter-10-digit-mobile" />';
				}else{
					document.getElementById("coachmobile-error_"+currentCounter[2]).textContent ='';
				}
			}else{
				document.getElementById("coachmobile-error_"+currentCounter[2]).textContent ='<liferay-ui:message key="please-enter-mobile" />';
			}
		});
		 
		
		$(document).on('keyup','.emailcls',function(){
			var pattern = /^\b[A-Z0-9._%-]+@[A-Z0-9.-]+\.[A-Z]{2,4}\b$/i;
			var countCurrentId=$(this).attr('id');
			var currentCounter=countCurrentId.split("_");
			var emailValue=$(this).val();
			if(emailValue){
				if(!pattern.test(emailValue)){
					document.getElementById("coachemail-error_"+currentCounter[2]).textContent ='<liferay-ui:message key="please-enter-a-valid-email" />';
				}else{
					document.getElementById("coachemail-error_"+currentCounter[2]).textContent ='';
				}
			}else{
				document.getElementById("coachemail-error_"+currentCounter[2]).textContent ='<liferay-ui:message key="please-enter-email" />';
			}
		});
		 
		$(document).on('keyup','.coachsportsCls',function(){
			var countCurrentId=$(this).attr('id');
			var currentCounter=countCurrentId.split("_");
			var coachSportsVal=$.trim($(this).val().replace(/\s{2,}/g, ' '));
			var regex =/^[a-zA-Z -]*$/; 
			if(coachSportsVal){
				if(coachSportsVal.length<1){
					document.getElementById("coachsports-error_"+currentCounter[3]).textContent ='<liferay-ui:message key="please-enter-atleast-1-character" />';
				}else if(!regex.test(coachSportsVal)){
					console.log("Error 2");
					document.getElementById("coachsports-error_"+currentCounter[3]).textContent ='<liferay-ui:message key="please-enter-alphabates-only" />';
				}else if(/([-\/ ])\1+/.test(coachSportsVal)){
					document.getElementById("coachsports-error_"+currentCounter[3]).textContent ='<liferay-ui:message key="two-special-character-not-allowed" />';
				}else{
					console.log("No error ");
					document.getElementById("coachsports-error_"+currentCounter[3]).textContent ='<liferay-ui:message key="" />';
				}
			}else{
				console.log("No error ");
				document.getElementById("coachsports-error_"+currentCounter[3]).textContent ='<liferay-ui:message key="please-enter-sports-name" />';
			}
		});
		
		$(document).on('keyup','.coachaddresscls',function(){
			var addressValue=$(this).val();
   			var currentId=$(this).attr("id");
   			var trimmedValue = $.trim(addressValue.replace(/\s{2,}/g, ' '));
   			var counterAddress=currentId.split("_");
   			var regex =/^[a-zA-Z0-9 .,/-]*$/; 
   			var actualValue1=$(this).val();
   			var actualValue=actualValue1.replace(/[^a-zA-Z0-9\s.]|(?<!\.)$/g, '');
   			$(this).val(actualValue);
			console.log("actualValue.length :::" +actualValue.length);
			if(actualValue){
				if(actualValue.length<10){
					document.getElementById("coach_address_-error_"+counterAddress[2]).textContent = "<liferay-ui:message key='please-enter-minimum-10-character' />";
				}else if (!regex.test(actualValue)) {
       	            console.log("1 not allowed special character  ::");
       	         	document.getElementById("coach_address_-error_"+counterAddress[2]).textContent = "<liferay-ui:message key='given-special-character-not-allowed' />";
       			}else if(/([.\/ -,])\1+/.test(actualValue)){
       	         	console.log("2 not allowed special character  ::");
       	      		document.getElementById("coach_address_-error_"+counterAddress[2]).textContent = "<liferay-ui:message key='consecutive-special-characters-not-allowed' />";
       	        }else {
       	         	console.log("allowed special character  ::");
       	         document.getElementById("coach_address_-error_"+counterAddress[2]).textContent = "";
       	        }
			}else {
       	         document.getElementById("coach_address_-error_"+counterAddress[2]).textContent = "";
   	        }
		});
		
		$(document).on('blur','.coachaddresscls',function(){
			var addressValue=$(this).val();
   			var currentId=$(this).attr("id");
   			$(this).val($.trim(addressValue.replace(/\s{2,}/g, ' ')));	
   			var trimmedValue = $.trim(addressValue.replace(/\s{2,}/g, ' '));
   			var counterAddress=currentId.split("_");
   			var regex =/^[a-zA-Z0-9 .,/-]*$/; 
   			var actualValue=$(this).val();
			console.log("actualValue.length :::" +actualValue.length);
			
			if(actualValue){
				if(actualValue.length<10){
					document.getElementById("coach_address_-error_"+counterAddress[2]).textContent = "<liferay-ui:message key='please-enter-minimum-10-character' />";
				}else if (!regex.test(actualValue)) {
       	            console.log("1 not allowed special character  ::");
       	         	document.getElementById("coach_address_-error_"+counterAddress[2]).textContent = "<liferay-ui:message key='given-special-character-not-allowed' />";
       			}else if(/([.\/ -,])\1+/.test(actualValue)){
       	         	console.log("2 not allowed special character  ::");
       	      		document.getElementById("coach_address_-error_"+counterAddress[2]).textContent = "<liferay-ui:message key='consecutive-special-characters-not-allowed' />";
       	        }else {
       	         	console.log("allowed special character  ::");
       	         document.getElementById("coach_address_-error_"+counterAddress[2]).textContent = "";
       	        }
			}else {
       	         document.getElementById("coach_address_-error_"+counterAddress[2]).textContent = "";
   	        }
		});
		
		$(document).on('blur','.coachshoescls',function(){
			var shoeValue=$(this).val();
			var currentId=$(this).attr("id");
			var countershoe=currentId.split("_");
			if(!shoeValue){
				 document.getElementById("shoes_size-error_"+countershoe[2]).textContent = "<liferay-ui:message key='please-enter-shoes-size' />";
			}else{
				 document.getElementById("shoes_size-error_"+countershoe[2]).textContent = "";
			}
		});
		
		$(document).on('keyup blur','.coachtracksuitcls',function(){
			  $(this).val($(this).val().replace(/[^a-zA-Z0-9]/g, '')); 
			  var trachsuitValue=$(this).val();
			  var currentId=$(this).attr("id");
				var counter=currentId.split("_");
			  if(!trachsuitValue){
				  document.getElementById("tracksuit_size_-error_"+counter[2]).textContent = "<liferay-ui:message key='please-enter-track-suit-size' />";
			  }else{
				  document.getElementById("tracksuit_size_-error_"+counter[2]).textContent = "";
			  }
		});
		
		$(document).on('keyup blur','.coachtshirsscls',function(){
			  $(this).val($(this).val().replace(/[^a-zA-Z0-9]/g, '')); 
			  var tshirtsValue=$(this).val();
			  var currentId=$(this).attr("id");
				var counter=currentId.split("_");
			  if(!tshirtsValue){
				  document.getElementById("tshirt_size_-error_"+counter[2]).textContent = "<liferay-ui:message key='please-enter-tshirts-size' />";
			  }else{
				  document.getElementById("tshirt_size_-error_"+counter[2]).textContent = "";
			  }
		});
		
		$(document).on('keyup blur','.coachshortscls',function(){
			  $(this).val($(this).val().replace(/[^a-zA-Z0-9]/g, '')); 
			  var shortsValue=$(this).val();
			  var currentId=$(this).attr("id");
				var counter=currentId.split("_");
			  if(!shortsValue){
				  document.getElementById("shorts_size_-error_"+counter[2]).textContent = "<liferay-ui:message key='please-enter-shorts-size' />";
			  }else{
				  document.getElementById("shorts_size_-error_"+counter[2]).textContent = "";
			  }
		});
		
		$(document).on('keyup','.coachremarkscls',function(){
			var addressValue=$(this).val();
   			var currentId=$(this).attr("id");
   			var trimmedValue = $.trim(addressValue.replace(/\s{2,}/g, ' '));
   			var counterAddress=currentId.split("_");
   			var regex =/^[a-zA-Z0-9 .,/-]*$/; 
   			var actualValue1=$(this).val();
   			var actualValue=actualValue1.replace(/[^a-zA-Z0-9\s.]|(?<!\.)$/g, '');
   			$(this).val(actualValue);
			console.log("actualValue.length :::" +actualValue.length);
			if(actualValue){
				if(actualValue.length<10){
					document.getElementById("coachdsoremarks_error_"+counterAddress[2]).textContent = "<liferay-ui:message key='please-enter-minimum-10-character' />";
				}else if (!regex.test(actualValue)) {
       	            console.log("1 not allowed special character  ::");
       	         	document.getElementById("coachdsoremarks_error_"+counterAddress[2]).textContent = "<liferay-ui:message key='given-special-character-not-allowed' />";
       			}else if(/([.\/ -,])\1+/.test(actualValue)){
       	         	console.log("2 not allowed special character  ::");
       	      		document.getElementById("coachdsoremarks_error_"+counterAddress[2]).textContent = "<liferay-ui:message key='consecutive-special-characters-not-allowed' />";
       	        }else {
       	         	console.log("allowed special character  ::");
       	         document.getElementById("coachdsoremarks_error_"+counterAddress[2]).textContent = "";
       	        }
			}else {
       	         document.getElementById("coachdsoremarks_error_"+counterAddress[2]).textContent = "";
   	        }
		});
		
		$(document).on('change','.coachdistrictselect',function(){
				var coachDistrictValue=$(this).val();
				 var currentId=$(this).attr("id");
					var counter=currentId.split("_");
				if(!coachDistrictValue){
					 document.getElementById("coachdistrictselect_-error_"+counter[1]).textContent = "<liferay-ui:message key='please-select-district' />";
				}else{
					 document.getElementById("coachdistrictselect_-error_"+counter[1]).textContent = "";
				}
		});
		
		
		
	});

</script>   