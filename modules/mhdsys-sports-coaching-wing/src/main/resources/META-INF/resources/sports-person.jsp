<div class="tab-pane fade" id="sports-person" role="tabpanel" aria-labelledby="sports-person-tab">
   	<div class="card card-background p-0">
                                <div class="personal_details">
                                     <div class="card-header header-card"><liferay-ui:message key="sports-person-details" /></div> 
                                    <div class="card-body">
                                       <!-- <div class="form-section"> -->
                                       <div class="">
					                <%--      <div class="flex justify-between items-center">
					                     <div class="mb-1"></div>
					                      <!--   <h2 class="form-section-title !border-0 !mb-0">Sports Person Details</h2> -->
					                       <c:if test="${mode eq 'edit' or mode eq 'add'}"> <button type="button" id="addSportsPersonBtn" class="btn btn-secondary">+ <liferay-ui:message key="add-more" /></button></c:if>
					                    </div> --%>
			                    <c:if test="${mode eq 'edit' or mode eq 'view'}">
					                    <div id="sportsPersonsContainer" class="">
					                        <!-- Sports Person Entry Template is in JS -->
					                        
					                        <c:if test="${not empty sportsPersonDTOs}">
						                        <c:forEach items="${sportsPersonDTOs}" var="sportsp" varStatus="spCounter">
				                        			 <div class="sp-entry <c:if test="${spCounter.count>1}">sportsPersonOther d-none</c:if>" id="sperDiv_${spCounter.count}">
				                        			  <input type="hidden" value="${sportsp.sportsPersonId}" name="sportsPersonId_${spCounter.count}"/>
								                    	
								                    	<div class="row">
								                        	<div class="col-md-6">
											 					 <div class="form-group">
											 						<label for="sportspersonselect_${spCounter.count}" class="form-label"><liferay-ui:message key="select-sports-person" /><sup class="text-danger">*</sup></label>
											 						<select class="form-control soprts-personscls sportspersonselect" id="sportspersonselect_${spCounter.count}" name="sportspersonselect_${spCounter.count}" <c:if test="${mode == 'view'}">disabled</c:if>>
											 							<option value="">select</option>
											 							 <c:forEach items="${sportsPersonsList}" var="sp" varStatus="spC">
											 							 	<option value="${sp.userId}" <c:if test="${sp.mobileNumber eq sportsp.mobileNumber}">selected</c:if>>${sp.firstName} ${sp.lastName}</option>
											 							 </c:forEach>
											 						</select>
											 						<p class="error-container" id="sportspersonselect_-error_${spCounter.count}"></p>
											 						</div>
															</div>
								                        	
								                        	<div class="col-md-6">
								                        		<div class="form-group">
								                        		 	<label for="sp_name_${spCounter.count}" class="form-label"><liferay-ui:message key="full-name" /><sup class="text-danger">*</sup></label>
									                                <input type="text" id="sp_name_${spCounter.count}" name="sp_name_${spCounter.count}" class="form-input spfnamecls" placeholder="Enter full name" value="${sportsp.fullName}" <c:if test="${mode == 'view'}">disabled</c:if>>
								                        			<p class="error-container" id="sportspersonfullname_-error_${spCounter.count}"></p>
								                        		</div>
								                        	</div>
								                        	
								                        	<div class="col-md-6">
								                        		<div class="form-group">
								                        		 	<label for="sp_dob_${spCounter.count}" class="form-label"><liferay-ui:message key="dob" /><sup class="text-danger">*</sup></label>
									                                <input type="date" id="sp_dob_${spCounter.count}" name="sp_dob_${spCounter.count}" class="form-input spdobcls" value="${sportsp.dateOfBirthString}" <c:if test="${mode == 'view'}">disabled</c:if>>
								                        			<p class="error-container" id="sportspersondob_-error_${spCounter.count}"></p>
								                        		</div>
								                        	</div>
								                        	
								                        	
								                        	
								                        	<div class="col-md-6">
								                        		<div class="form-group">
								                        		 	<label for="sp_school_${spCounter.count}" class="form-label"><liferay-ui:message key="school-name" /><sup class="text-danger">*</sup></label>
									                                <input type="text" id="sp_school_${spCounter.count}" name="sp_school_${spCounter.count}" class="form-input spschoolcls" placeholder="Enter school name" value="${sportsp.schoolName}" <c:if test="${mode == 'view'}">disabled</c:if>>
								                        			<p class="error-container" id="sportspersonschoolname_-error_${spCounter.count}"></p>
								                        		</div>
								                        	</div>
								                        	
								                        	
								                        	<div class="col-md-6">
								                        		<div class="form-group">
								                        		 	 <label for="sp_mobile_${spCounter.count}" class="form-label"><liferay-ui:message key="mobile-number" /><sup class="text-danger">*</sup></label>
									                                <input type="tel" id="sp_mobile_${spCounter.count}" name="sp_mobile_${spCounter.count}" class="form-input spemobcls" placeholder="Enter mobile number" value="${sportsp.mobileNumber}" <c:if test="${mode == 'view'}">disabled</c:if>>
								                        			<p class="error-container" id="sportspersonmobile_-error_${spCounter.count}"></p>
								                        		</div>
								                        	</div>
								                        	
								                        	<div class="col-md-6">
								                        		<div class="form-group">
								                        		 	 <label for="sp_address_${spCounter.count}" class="form-label"><liferay-ui:message key="residence-address" /><sup class="text-danger">*</sup></label>
									                               <textarea id="sp_address_${spCounter.count}" name="sp_address_${spCounter.count}" rows="2" class="form-textarea spaddresscls" placeholder="Enter residence address" <c:if test="${mode == 'view'}">disabled</c:if>>${sportsp.address}</textarea>
								                        			<p class="error-container" id="sportspersonaddress_-error_${spCounter.count}"></p>
								                        		</div>
								                        	</div>
								                        	
								                        	
								                        	<div class="col-md-6">
								                        		<div class="form-group">
								                        		  <label for="sp_entry_date_${spCounter.count}" class="form-label"><liferay-ui:message key="entry-date" /><sup class="text-danger">*</sup></label>
									                                <input type="date" id="sp_entry_date_${spCounter.count}" name="sp_entry_date_${spCounter.count}" class="form-input spedcls" value="${sportsp.entryDateStr}" <c:if test="${mode == 'view'}">disabled</c:if>>
								                        			<p class="error-container" id="sportspersonentrydate_-error_${spCounter.count}"></p>
								                        		</div>
								                        	</div>
								                        	
								                        	<div class="col-md-6">
								                        		<div class="form-group">
								                        			<label for="sp_ranking_${spCounter.count}" class="form-label"><liferay-ui:message key="ranking" /><sup class="text-danger">*</sup></label>
									                        		 <%-- <input type="text" id="sp_ranking_${spCounter.count}" name="sp_ranking_${spCounter.count}" class="form-input sprankingcls" placeholder="Enter ranking" value="${sportsp.ranking}" <c:if test="${mode == 'view'}">disabled</c:if>> --%>
									                        				<!-- Update changes -->
									                        			<select class="form-control soprts-personscls rankingselect" id="sp_ranking_${spCounter.count}" name="sp_ranking_${spCounter.count}" <c:if test="${mode == 'view'}">disabled</c:if>>
												 							<option value=""><liferay-ui:message key="select-ranking" /></option>
												 							<option value="gold" <c:if test="${sportsp.ranking=='gold'}">selected</c:if>>Gold</option>
												 							<option value="silver" <c:if test="${sportsp.ranking=='silver'}">selected</c:if>>Silver</option>
												 							<option value="bronze" <c:if test="${sportsp.ranking=='bronze'}">selected</c:if>>Bronze</option>
											 							</select>
									                        			<!-- Update changes -->
									                        			<p class="error-container" id="sportspersonranking_-error_${spCounter.count}"></p>
									                        			
									                        	</div>
								                        	</div>
								                        	
								                        	<div class="col-md-6">
								                        		<div class="form-group">
								                        	 		<label for="sp_achievement_${spCounter.count}" class="form-label"><liferay-ui:message key="achievement-level" /><sup class="text-danger">*</sup></label>
									                          		<%-- <textarea id="sp_achievement_${spCounter.count}" name="sp_achievement_${spCounter.count}" rows="2" class="form-textarea spachievementcls" placeholder="e.g., district, division, state, national, international" <c:if test="${mode == 'view'}">disabled</c:if>>${sportsp.achievementLevel}</textarea> --%>
								                        			<!-- Update changes -->
									                        			<select class="form-control spachievementcls achievementselect" id="sp_achievement_${spCounter.count}" name="sp_achievement_${spCounter.count}" rows="2" <c:if test="${mode == 'view'}">disabled</c:if>>
												 							<option value=""><liferay-ui:message key="select-achievement" /></option>
												 							<option value="Taluka" <c:if test="${sportsp.achievementLevel=='Taluka'}">selected</c:if>>Taluka</option>
												 							<option value="district" <c:if test="${sportsp.achievementLevel=='district'}">selected</c:if>>District</option>
												 							<option value="divisonal" <c:if test="${sportsp.achievementLevel=='divisonal'}">selected</c:if>>Divisonal</option>
												 							<option value="state" <c:if test="${sportsp.achievementLevel=='state'}">selected</c:if>>State</option>
												 							<option value="national" <c:if test="${sportsp.achievementLevel=='national'}">selected</c:if>>National</option>
												 							<option value="international" <c:if test="${sportsp.achievementLevel=='international'}">selected</c:if>>International</option>
											 							</select>
									                        			<!-- Update changes -->
								                        			
								                        			
								                        			<p class="error-container" id="sp_achievement_-error_${spCounter.count}"></p>
								                        		</div>
								                        	</div>
								                        	<div class="col-md-6">
								                        		<div class="form-group">
								                        			 <label for="sp_remarks_${spCounter.count}" class="form-label"><liferay-ui:message key="remarks-by-coach" /></label>
									                         		<textarea id="sp_remarks_${spCounter.count}" name="sp_remarks_${spCounter.count}" rows="2" class="form-textarea spcremarkscls" placeholder="Enter coach remarks" <c:if test="${mode == 'view'}">disabled</c:if>>${sportsp.remarks}</textarea>
								                        			<p class="error-container" id="spc_remarks_error_${spCounter.count}"></p>
								                        	</div>
								                        	</div>
						                        		</div>
									                    </div>
						                        </c:forEach>
					                        </c:if>
					                        <c:if test="${empty sportsPersonDTOs}">
									              <!-- Coach Entry Template is in JS -->
									              <div class="sp-entry">
								                    	<div class="row">
								                        	<div class="col-md-6">
											 					 <div class="form-group">
											 						<label for="sportspersonselect_1" class="form-label"><liferay-ui:message key="select-sports-person" /><sup class="text-danger">*</sup></label>
											 						<select class="form-control soprts-personscls sportspersonselect" id="sportspersonselect_1" name="sportspersonselect_1" <c:if test="${mode == 'view'}">disabled</c:if>>
											 							<option value="">select</option>
											 							 <c:forEach items="${sportsPersonsList}" var="sp" varStatus="spC">
											 							 	<option value="${sp.userId}">${sp.firstName} ${sp.lastName}</option>
											 							 </c:forEach>
											 						</select>
											 						<p class="error-container" id="sportspersonselect_-error_1"></p>
											 						</div>
															</div>
								                        	
								                        	<div class="col-md-6">
								                        		<div class="form-group">
								                        		 	<label for="sp_name_1" class="form-label"><liferay-ui:message key="full-name" /><sup class="text-danger">*</sup></label>
									                                <input type="text" id="sp_name_1" name="sp_name_1" class="form-input spfnamecls" placeholder="Enter full name" value="${sportsp.fullName}" <c:if test="${mode == 'view'}">disabled</c:if>>
								                        			<p class="error-container" id="sportspersonfullname_-error_1"></p>
								                        		</div>
								                        	</div>
								                        	
								                        	<div class="col-md-6">
								                        		<div class="form-group">
								                        		 	<label for="sp_dob_1" class="form-label"><liferay-ui:message key="dob" /><sup class="text-danger">*</sup></label>
									                                <input type="date" id="sp_dob_1" name="sp_dob_1" class="form-input spdobcls" value="${sportsp.dateOfBirthString}" <c:if test="${mode == 'view'}">disabled</c:if>>
								                        			<p class="error-container" id="sportspersondob_-error_1"></p>
								                        		</div>
								                        	</div>
								                        	
								                        	
								                        	
								                        	<div class="col-md-6">
								                        		<div class="form-group">
								                        		 	<label for="sp_school_1" class="form-label"><liferay-ui:message key="school-name" /><sup class="text-danger">*</sup></label>
									                                <input type="text" id="sp_school_1" name="sp_school_1" class="form-input spschoolcls" placeholder="Enter school name" value="${sportsp.schoolName}" <c:if test="${mode == 'view'}">disabled</c:if>>
								                        			<p class="error-container" id="sportspersonschoolname_-error_1"></p>
								                        		</div>
								                        	</div>
								                        	
								                        	
								                        	<div class="col-md-6">
								                        		<div class="form-group">
								                        		 	 <label for="sp_mobile_1" class="form-label"><liferay-ui:message key="mobile-number" /><sup class="text-danger">*</sup></label>
									                                <input type="tel" id="sp_mobile_1" name="sp_mobile_1" class="form-input spemobcls" placeholder="Enter mobile number" value="${sportsp.mobileNumber}" <c:if test="${mode == 'view'}">disabled</c:if>>
								                        			<p class="error-container" id="sportspersonmobile_-error_1"></p>
								                        		</div>
								                        	</div>
								                        	
								                        	<div class="col-md-6">
								                        		<div class="form-group">
								                        		 	 <label for="sp_address_1" class="form-label"><liferay-ui:message key="residence-address" /><sup class="text-danger">*</sup></label>
									                               <textarea id="sp_address_1" name="sp_address_1" rows="2" class="form-textarea spaddresscls" placeholder="Enter residence address" <c:if test="${mode == 'view'}">disabled</c:if>>${sportsp.address}</textarea>
								                        			<p class="error-container" id="sportspersonaddress_-error_1"></p>
								                        		</div>
								                        	</div>
								                        	
								                        	
								                        	<div class="col-md-6">
								                        		<div class="form-group">
								                        		  <label for="sp_entry_date_1" class="form-label"><liferay-ui:message key="entry-date" /><sup class="text-danger">*</sup></label>
									                                <input type="date" id="sp_entry_date_1" name="sp_entry_date_1" class="form-input spedcls" value="${sportsp.entryDateStr}" <c:if test="${mode == 'view'}">disabled</c:if>>
								                        			<p class="error-container" id="sportspersonentrydate_-error_1"></p>
								                        		</div>
								                        	</div>
								                        	
								                        	<div class="col-md-6">
								                        		<div class="form-group">
								                        			<label for="sp_ranking_1" class="form-label"><liferay-ui:message key="ranking" /><sup class="text-danger">*</sup></label>
									                        		 <%-- <input type="text" id="sp_ranking_1" name="sp_ranking_1" class="form-input sprankingcls" placeholder="Enter ranking" value="${sportsp.ranking}" <c:if test="${mode == 'view'}">disabled</c:if>> --%>
									                        		 
									                        		 	<!-- Update changes -->
									                        			<select class="form-control sprankingcls rankingselect" id="sp_ranking_1" name="sp_ranking_1" <c:if test="${mode == 'view'}">disabled</c:if>>
											 							<option value=""><liferay-ui:message key="select-ranking" /></option>
											 							<option value="gold">Gold</option>
											 							<option value="silver">Silver</option>
											 							<option value="bronze">Bronze</option>
											 							</select>
									                        			<!-- Update changes -->
									                        		 
									                        		 
									                        		 <p class="error-container" id="sportspersonranking_-error_1"></p>
									                        	</div>
								                        	</div>
								                        	
								                        	<div class="col-md-6">
								                        		<div class="form-group">
								                        	 		<label for="sp_achievement_1" class="form-label"><liferay-ui:message key="achievement-level" /><sup class="text-danger">*</sup></label>
									                          		<%-- <textarea id="sp_achievement_1" name="sp_achievement_1" rows="2" class="form-textarea spachievementcls" placeholder="e.g., district, division, state, national, international" <c:if test="${mode == 'view'}">disabled</c:if>>${sportsp.achievementLevel}</textarea> --%>
								                        			
								                        			<!-- Update changes -->
									                        			<select class="form-control spachievementcls achievementselect" id="sp_achievement_1" name="sp_achievement_1" rows="2" <c:if test="${mode == 'view'}">disabled</c:if>>
												 							<option value=""><liferay-ui:message key="select-achievement" /></option>
												 							<option value="Taluka">Taluka</option>
												 							<option value="district">District</option>
												 							<option value="divisonal">Divisonal</option>
												 							<option value="state">State</option>
												 							<option value="national">National</option>
												 							<option value="international">International</option>
											 							</select>
									                        			<!-- Update changes -->
								                        			
								                        			<p class="error-container" id="sp_achievement_-error_1"></p>
								                        		</div>
								                        	</div>
								                        	
								                        	<div class="col-md-6">
								                        		<div class="form-group">
								                        			 <label for="sp_remarks_1" class="form-label"><liferay-ui:message key="remarks-by-coach" /></label>
									                         		<textarea id="sp_remarks_1" name="sp_remarks_1" rows="2" class="form-textarea spcremarkscls" placeholder="Enter coach remarks" <c:if test="${mode == 'view'}">disabled</c:if>>${sportsp.remarks}</textarea>
								                        			<p class="error-container" id="spc_remarks_error_1"></p>
								                        	</div>
								                        	</div>
								                        	
						                        		</div>
									                    </div>
									          
						        		  </c:if>
					                    </div>
					                    
					                    </c:if>
					                     <c:if test="${mode eq 'add'}">
						                    <div id="sportsPersonsContainer" class="">
						                        <!-- Sports Person Entry Template is in JS -->
						                    </div>
					                    </c:if>
					                    <input type="hidden" value="1" id ="sportsPersonCount" name="sportsPersonCount"/>
					                   
					                </div>
					                <div class="text-right">
					                   <c:if test="${(mode eq 'edit' or mode eq 'view') && sportsPersoncount>1}"><button type="button" id="hideAllSportsPersonBtn" class="btn btn-secondary d-none">Hide All</button></c:if>
					  				 <c:if test="${(mode eq 'edit' or mode eq 'view') && sportsPersoncount>1}"><button type="button" id="showAllSportsPersonBtn" class="btn btn-secondary">Show All</button></c:if>
					                  <c:if test="${mode eq 'edit' or mode eq 'add'}"> <button type="button" id="addSportsPersonBtn" class="btn btn-secondary">+ <liferay-ui:message key="add-more" /></button></c:if>
									</div>
                                    </div>
                                </div>
                            </div>
	                 <div class="card-footer bg-transparent text-right p-4">
	                 	 <c:if test="${mode eq 'add' or mode eq 'edit'}">
	                 	<button type="button" class="btn btn-primary" onclick="location.href='/group/guest/dashboard';">
	                        	<liferay-ui:message key="cancel" />
	                   		 </button>
	                   		 <button class="btn btn-primary" type="button"
								onclick="location.reload();">
									<liferay-ui:message key="reset" />
								</button>
						 <!-- <button type="button" class="btn btn-primary" onclick="saveAwardYouthOrg(event,'Draft')"> -->
						  <button type="button" class="btn btn-primary" onclick="saveTrainingCenterDetails(event,'Draft','sports-person')">
	                         <liferay-ui:message key="save-as-draft" />
	                     </button>  
	                    <button type="button" class="btn btn-primary" onclick="setActiveTab('coach-detail','sports-person','previous')">
	                         <liferay-ui:message key="previous" />
	                     </button>
	                     <!--  <button type="button" class="btn btn-primary" onclick="saveAwardYouthOrg(event,'Draft')"> -->
	                      <button type="button" class="btn btn-primary" onclick="setActiveTab('servant','sports-person','next')">
	                         <liferay-ui:message key="next" />
	                     </button>
	                 	</c:if>
	                 	<c:if test="${mode eq 'view'}">
	                 		 <button type="button" class="btn btn-primary" onclick="setActiveTab('coach-detail','sports-person','previous')">
	                        	 <liferay-ui:message key="previous" />
	                    	 </button>
	                 		 <button type="button" class="btn btn-primary" onclick="setActiveTab('servant','sports-person','next')">
	                         	<liferay-ui:message key="next" />
	                    	 </button>
	                 	</c:if>
					</div>
     
</div>
                        
<script>                        
	$(document).ready(function(){
		
		 var today = new Date();
      	 var year = today.getFullYear();
      	 var month = String(today.getMonth() + 1).padStart(2, '0'); // Months are 0-indexed
      	 var day = String(today.getDate()).padStart(2, '0');
      	 var formattedDate = year+'-'+month+'-'+day;
         console.log("formattedDate :::" +formattedDate);
		
		$('.spdobcls').each(function(){
			$(this).attr('max',formattedDate);
		});
		
		
		$('.spedcls').each(function(){
			$(this).attr('max',formattedDate);
		});
		
		
		
		
		$(document).on('change','.sportspersonselect',function(e){
			console.log("sports person change function called ::");
			var selectid=$(this).prop('id');
			var idcounter=selectid.split('_');
			console.log("idcounter ::"+idcounter[1]);
			var idvalue=idcounter[1];
			
			var id=$(this).val();
			console.log("id :::"+id);
			if(id){
				document.getElementById("sportspersonselect_-error_"+idvalue).textContent = "";
				$.ajax({
	     			type: "POST",
	     			datatype:"json",
	     	        url: '${getSportsPersonsURL}', 
	     	        data: {
	     	        	id:id
	     	        },
	     			async : false,
	     	        success: function(data) {
	     	        	console.log("Data :::" +data);
	     	        	var response=JSON.parse(data);
	     	        	console.log("Data.name :::" +response.name);
	     	        	if(response!=null && response!= ''){
	     	        		$('#sp_name_'+idvalue).val(response.name);
	     	        		$('#sp_school_'+idvalue).val(response.schoolName);
	     	        		$('#sp_mobile_'+idvalue).val(response.mobile);
	     	        		$('#sp_dob_'+idvalue).val(response.dob);
	     	        		
	     	        		document.getElementById("sportspersonfullname_-error_"+idvalue).textContent = "";
	     	        		document.getElementById("sportspersonschoolname_-error_"+idvalue).textContent = "";
	     	        		document.getElementById("sportspersonmobile_-error_"+idvalue).textContent = "";
	     	        		document.getElementById("sportspersondob_-error_"+idvalue).textContent = "";
	     	        	}
	     	            	
	     	        },error:function(resp){
	     	        	console.log("inside  division error :: ");
	     	        }
	     		});
			}else{
				document.getElementById("sportspersonselect_-error_"+idvalue).textContent = "<liferay-ui:message key='please-select-sports-person' />";
			}
		});
		
		$(document).on('change','.spdobcls',function(){
				var dobVal=$(this).val();
				var dobid=$(this).prop('id');
				var idcounter=dobid.split('_');
				var idvalue=idcounter[2];
				if(dobVal){
					console.log("dobVal :::"+dobVal);
					 var dob = new Date(dobVal);
					 var today = new Date();
					 var tenYearsAgo = new Date(today.getFullYear() - 10, today.getMonth(), today.getDate());
					 if (dob > tenYearsAgo) {
						 document.getElementById("sportspersondob_-error_"+idvalue).textContent = "<liferay-ui:message key='age-must-be-more-than-10-year' />";
				         $(this).val(''); 
				     }else{
				    	 document.getElementById("sportspersondob_-error_"+idvalue).textContent = "";
				     }
				}else{
					document.getElementById("sportspersondob_-error_"+idvalue).textContent = "<liferay-ui:message key='please-select-dob' />";
				}
		});
	
		
		//Name change event
		$(document).on('keyup','.spfnamecls',function(){
			var coachNameVal=$.trim($(this).val().replace(/\s{2,}/g, ' '));
			var countCurrentId=$(this).attr('id');
			var currentCounter=countCurrentId.split("_");
			var regex =/^[a-zA-Z .]*$/; 
			
			if(coachNameVal){
				if(coachNameVal.length<3){
					document.getElementById("sportspersonfullname_-error_"+currentCounter[2]).textContent ='<liferay-ui:message key="please-enter-atleast-3-character" />';
				}else if(!regex.test(coachNameVal)){
					console.log("Error 2");
					document.getElementById("sportspersonfullname_-error_"+currentCounter[2]).textContent ='<liferay-ui:message key="special-character-not-allowed" />';
				}else if(/([.\/ ])\1+/.test(coachNameVal)){
					document.getElementById("sportspersonfullname_-error_"+currentCounter[2]).textContent ='<liferay-ui:message key="two-special-character-not-allowed" />';
				}else{
					console.log("No error ");
					document.getElementById("sportspersonfullname_-error_"+currentCounter[2]).textContent ='<liferay-ui:message key="" />';
				}
			}else{
				console.log("No error ");
				document.getElementById("sportspersonfullname_-error_"+currentCounter[2]).textContent ='<liferay-ui:message key="please-enter-name" />';
			}
		});
		
		//blur event of name
		$(document).on('blur','.spfnamecls',function(){
			var coachNameVal=$.trim($(this).val().replace(/\s{2,}/g, ' '));
			$(this).val(coachNameVal);
			var countCurrentId=$(this).attr('id');
			var currentCounter=countCurrentId.split("_");
			var regex =/^[a-zA-Z .]*$/; 
			if(coachNameVal){
				if(coachNameVal.length<3){
					document.getElementById("sportspersonfullname_-error_"+currentCounter[2]).textContent ='<liferay-ui:message key="please-enter-atleast-3-character" />';
				}else if(!regex.test(coachNameVal)){
					console.log("Error 2");
					document.getElementById("sportspersonfullname_-error_"+currentCounter[2]).textContent ='<liferay-ui:message key="special-character-not-allowed" />';
				}else if(/([.\/ ])\1+/.test(coachNameVal)){
					document.getElementById("sportspersonfullname_-error_"+currentCounter[2]).textContent ='<liferay-ui:message key="two-special-character-not-allowed" />';
				}else{
					console.log("No error ");
					document.getElementById("sportspersonfullname_-error_"+currentCounter[2]).textContent ='<liferay-ui:message key="" />';
				}
			}else{
				console.log("No error ");
				document.getElementById("sportspersonfullname_-error_"+currentCounter[2]).textContent ='<liferay-ui:message key="please-enter-name" />';
			}
		});
		
		
		
		//school change event
		$(document).on('keyup','.spschoolcls',function(){
			var schoolNameVal=$.trim($(this).val().replace(/\s{2,}/g, ' '));
			var countCurrentId=$(this).attr('id');
			var currentCounter=countCurrentId.split("_");
			var regex =/^[a-zA-Z .()&,]*$/; 
			
			if(schoolNameVal){
				if(schoolNameVal.length<3){
					document.getElementById("sportspersonschoolname_-error_"+currentCounter[2]).textContent ='<liferay-ui:message key="please-enter-atleast-3-character" />';
				}else if(!regex.test(schoolNameVal)){
					console.log("Error 2");
					document.getElementById("sportspersonschoolname_-error_"+currentCounter[2]).textContent ='<liferay-ui:message key="special-character-not-allowed" />';
				}else if(/([.\/ ])\1+/.test(schoolNameVal)){
					document.getElementById("sportspersonschoolname_-error_"+currentCounter[2]).textContent ='<liferay-ui:message key="two-special-character-not-allowed" />';
				}else{
					console.log("No error ");
					document.getElementById("sportspersonschoolname_-error_"+currentCounter[2]).textContent ='<liferay-ui:message key="" />';
				}
			}else{
				console.log("No error ");
				document.getElementById("sportspersonschoolname_-error_"+currentCounter[2]).textContent ='<liferay-ui:message key="please-enter-school-name" />';
			}
		});
		
		//blur event of school
		$(document).on('blur','.spschoolcls',function(){
			var schoolNameVal=$.trim($(this).val().replace(/\s{2,}/g, ' '));
			$(this).val(schoolNameVal);
			var countCurrentId=$(this).attr('id');
			var currentCounter=countCurrentId.split("_");
			var regex =/^[a-zA-Z .]*$/; 
			if(schoolNameVal){
				if(schoolNameVal.length<3){
					document.getElementById("sportspersonschoolname_-error_"+currentCounter[2]).textContent ='<liferay-ui:message key="please-enter-atleast-3-character" />';
				}else if(!regex.test(schoolNameVal)){
					console.log("Error 2");
					document.getElementById("sportspersonschoolname_-error_"+currentCounter[2]).textContent ='<liferay-ui:message key="special-character-not-allowed" />';
				}else if(/([.\/ ])\1+/.test(schoolNameVal)){
					document.getElementById("sportspersonschoolname_-error_"+currentCounter[2]).textContent ='<liferay-ui:message key="two-special-character-not-allowed" />';
				}else{
					console.log("No error ");
					document.getElementById("sportspersonschoolname_-error_"+currentCounter[2]).textContent ='<liferay-ui:message key="" />';
				}
			}else{
				console.log("No error ");
				document.getElementById("sportspersonschoolname_-error_"+currentCounter[2]).textContent ='<liferay-ui:message key="please-enter-school-name" />';
			}
		});
		
		
		//Mobile
		$(document).on('input','.spemobcls',function(){
			//console.log("on input mobile called ::");
			$(this).val($(this).val().replace(/\D+/g, ''));
		});
	
		//Mobile
		  $(document).on('keyup','.spemobcls',function(){
				//var mobileNumberPattern = /^[0-9]{10}$/; 
				var mobileNumberPattern = /^[6-9][0-9]{0,9}$/; 
				var mobValue=$(this).val();
				//console.log("mobValue :::"+mobValue);
				var countCurrentId=$(this).attr('id');
				var currentCounter=countCurrentId.split("_");
				if(mobValue){
					if(!mobileNumberPattern.test(mobValue)){
						document.getElementById("sportspersonmobile_-error_"+currentCounter[2]).textContent ='<liferay-ui:message key="please-enter-a-valid-mobile" />';
					}else if(mobValue.length<10){
						document.getElementById("sportspersonmobile_-error_"+currentCounter[2]).textContent ='<liferay-ui:message key="please-enter-10-digit-mobile" />';
					}else{
						document.getElementById("sportspersonmobile_-error_"+currentCounter[2]).textContent ='';
					}
				}else{
					document.getElementById("sportspersonmobile_-error_"+currentCounter[2]).textContent ='<liferay-ui:message key="please-enter-mobile" />';
				}
			}); 
		
		
		//Address
		  $(document).on('keyup','.spaddresscls',function(){
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
						document.getElementById("sportspersonaddress_-error_"+counterAddress[2]).textContent = "<liferay-ui:message key='please-enter-minimum-10-character' />";
					}else if (!regex.test(actualValue)) {
	       	            console.log("1 not allowed special character  ::");
	       	         	document.getElementById("sportspersonaddress_-error_"+counterAddress[2]).textContent = "<liferay-ui:message key='given-special-character-not-allowed' />";
	       			}else if(/([.\/ -,])\1+/.test(actualValue)){
	       	         	console.log("2 not allowed special character  ::");
	       	      		document.getElementById("sportspersonaddress_-error_"+counterAddress[2]).textContent = "<liferay-ui:message key='consecutive-special-characters-not-allowed' />";
	       	        }else {
	       	         	console.log("allowed special character  ::");
	       	         document.getElementById("sportspersonaddress_-error_"+counterAddress[2]).textContent = "";
	       	        }
				}else {
	       	         document.getElementById("sportspersonaddress_-error_"+counterAddress[2]).textContent = "<liferay-ui:message key='please-enter-address' />";
	   	        }
			});
			
			$(document).on('blur','.spaddresscls',function(){
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
						document.getElementById("sportspersonaddress_-error_"+counterAddress[2]).textContent = "<liferay-ui:message key='please-enter-minimum-10-character' />";
					}else if (!regex.test(actualValue)) {
	       	            console.log("1 not allowed special character  ::");
	       	         	document.getElementById("sportspersonaddress_-error_"+counterAddress[2]).textContent = "<liferay-ui:message key='given-special-character-not-allowed' />";
	       			}else if(/([.\/ -,])\1+/.test(actualValue)){
	       	         	console.log("2 not allowed special character  ::");
	       	      		document.getElementById("sportspersonaddress_-error_"+counterAddress[2]).textContent = "<liferay-ui:message key='consecutive-special-characters-not-allowed' />";
	       	        }else {
	       	         	console.log("allowed special character  ::");
	       	         document.getElementById("sportspersonaddress_-error_"+counterAddress[2]).textContent = "";
	       	        }
				}else {
	       	         document.getElementById("sportspersonaddress_-error_"+counterAddress[2]).textContent = "<liferay-ui:message key='please-enter-address' />";
	   	        }
			});
			
			//entry date
			$(document).on('change','.spedcls',function(){
				var edVal=$(this).val();
				var dobid=$(this).prop('id');
				var idcounter=dobid.split('_');
				var idvalue=idcounter[3];
				if(!edVal){
					document.getElementById("sportspersonentrydate_-error_"+idvalue).textContent = "<liferay-ui:message key='please-select-entry-date' />";
				}else{
					document.getElementById("sportspersonentrydate_-error_"+idvalue).textContent = "";
				}
		});
			
			
		$(document).on('keyup blur','.sprankingcls',function(){
			  $(this).val($(this).val().replace(/[^a-zA-Z0-9]/g, '')); 
			  var rankValue=$(this).val();
			  var currentId=$(this).attr("id");
				var counter=currentId.split("_");
			  if(!rankValue){
				  document.getElementById("sportspersonranking_-error_"+counter[2]).textContent = "<liferay-ui:message key='please-enter-ranking' />";
			  }else{
				  document.getElementById("sportspersonranking_-error_"+counter[2]).textContent = "";
			  }
		});
			
			
		$(document).on('keyup','.spachievementcls',function(){
			var addressValue=$(this).val();
   			var currentId=$(this).attr("id");
   			var trimmedValue = $.trim(addressValue.replace(/\s{2,}/g, ' '));
   			var counter=currentId.split("_");
   			var regex =/^[a-zA-Z .,]*$/; 
   			var actualValue1=$(this).val();
   			var actualValue=actualValue1.replace(/[^a-zA-Z0-9\s.]|(?<!\.)$/g, '');
   			$(this).val(actualValue);
			console.log("actualValue.length :::" +actualValue.length);
			if(actualValue){
				if(actualValue.length<5){
					document.getElementById("sp_achievement_-error_"+counter[2]).textContent = "<liferay-ui:message key='please-enter-minimum-5-character' />";
				}else if (!regex.test(actualValue)) {
       	            console.log("1 not allowed special character  ::");
       	         	document.getElementById("sp_achievement_-error_"+counter[2]).textContent = "<liferay-ui:message key='given-special-character-not-allowed' />";
       			}else if(/([.\/ -,])\1+/.test(actualValue)){
       	         	console.log("2 not allowed special character  ::");
       	      		document.getElementById("sp_achievement_-error_"+counter[2]).textContent = "<liferay-ui:message key='consecutive-special-characters-not-allowed' />";
       	        }else {
       	         	console.log("allowed special character  ::");
       	         document.getElementById("sp_achievement_-error_"+counter[2]).textContent = "";
       	        }
			}else {
       	         document.getElementById("sp_achievement_-error_"+counter[2]).textContent = "<liferay-ui:message key='please-enter-achievement-level' />";
   	        }
		});	
		
		$(document).on('blur','.spachievementcls',function(){
			var addressValue=$(this).val();
   			var currentId=$(this).attr("id");
   			var trimmedValue = $.trim(addressValue.replace(/\s{2,}/g, ' '));
   			var counter=currentId.split("_");
   			var regex =/^[a-zA-Z .,]*$/; 
   			var actualValue1=$(this).val();
   			var actualValue=actualValue1.replace(/[^a-zA-Z0-9\s.]|(?<!\.)$/g, '');
   			$(this).val(actualValue);
			console.log("actualValue.length :::" +actualValue.length);
			if(actualValue){
				if(actualValue.length<5){
					document.getElementById("sp_achievement_-error_"+counter[2]).textContent = "<liferay-ui:message key='please-enter-minimum-5-character' />";
				}else if (!regex.test(actualValue)) {
       	            console.log("1 not allowed special character  ::");
       	         	document.getElementById("sp_achievement_-error_"+counter[2]).textContent = "<liferay-ui:message key='given-special-character-not-allowed' />";
       			}else if(/([.\/ -,])\1+/.test(actualValue)){
       	         	console.log("2 not allowed special character  ::");
       	      		document.getElementById("sp_achievement_-error_"+counter[2]).textContent = "<liferay-ui:message key='consecutive-special-characters-not-allowed' />";
       	        }else {
       	         	console.log("allowed special character  ::");
       	         document.getElementById("sp_achievement_-error_"+counter[2]).textContent = "";
       	        }
			}else {
       	         document.getElementById("sp_achievement_-error_"+counter[2]).textContent = "<liferay-ui:message key='please-enter-achievement-level' />";
   	        }
		});	
			
		$(document).on('keyup','.spcremarkscls',function(){
			var addressValue=$(this).val();
   			var currentId=$(this).attr("id");
   			var trimmedValue = $.trim(addressValue.replace(/\s{2,}/g, ' '));
   			var counter=currentId.split("_");
   			var regex =/^[a-zA-Z0-9 .,/-]*$/; 
   			var actualValue1=$(this).val();
   			var actualValue=actualValue1.replace(/[^a-zA-Z0-9\s.]|(?<!\.)$/g, '');
   			$(this).val(actualValue);
			console.log("actualValue.length :::" +actualValue.length);
			if(actualValue){
				if(actualValue.length<10){
					document.getElementById("spc_remarks_error_"+counter[2]).textContent = "<liferay-ui:message key='please-enter-minimum-10-character' />";
				}else if (!regex.test(actualValue)) {
       	            console.log("1 not allowed special character  ::");
       	         	document.getElementById("spc_remarks_error_"+counter[2]).textContent = "<liferay-ui:message key='given-special-character-not-allowed' />";
       			}else if(/([.\/ -,])\1+/.test(actualValue)){
       	         	console.log("2 not allowed special character  ::");
       	      		document.getElementById("spc_remarks_error_"+counter[2]).textContent = "<liferay-ui:message key='consecutive-special-characters-not-allowed' />";
       	        }else {
       	         	console.log("allowed special character  ::");
       	         document.getElementById("spc_remarks_error_"+counter[2]).textContent = "";
       	        }
			}else {
       	         document.getElementById("spc_remarks_error_"+counter[2]).textContent = "";
   	        }
		});	
		
		
		
		
		$('#showAllSportsPersonBtn').click(function(){
			$('.sportsPersonOther').each(function(){
				$(this).removeClass("d-none");
			});
			$('#showAllSportsPersonBtn').addClass("d-none");
			$('#hideAllSportsPersonBtn').removeClass("d-none");
		});
				 
		$('#hideAllSportsPersonBtn').click(function(){
			$('.sportsPersonOther').each(function(){
				$(this).addClass("d-none");
			});
			$('#showAllSportsPersonBtn').removeClass("d-none");
			$('#hideAllSportsPersonBtn').addClass("d-none");
		});	
		
		
	});
	
	
</script>                        