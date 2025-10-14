<div class="tab-pane fade" id="financial-details" role="tabpanel" aria-labelledby="financial-details-tab">
                            <div class="card card-background p-0">
                                <div class="personal_details">
                                     <div class="card-header header-card"><liferay-ui:message key="financial-details" /></div> 
                                    <div class="card-body">
                                         <!-- <div class="form-section"> -->
                                         <div class="">
							                    <h2 class="form-section-title"></h2>
							                    
							                     <c:if test="${mode eq 'edit' or mode eq 'view'}">
								                    <div id="financialDetailsContainer1" class="space-y-4">
									                    	 <c:if test="${not empty financialDetailsList}">
									                    		<div class="financial-item form-group grid grid-cols-1 md:grid-cols-12 gap-4 items-center" id="${fdcounter.count}">
									                    		<c:forEach items="${financialDetailsList}" var="fd" varStatus="fdcounter">
											                    	 <input type="hidden" value="${fd.financialDetailId}" name="fdId_${fdcounter.count}" id="fdId_${fdcounter.count}"/>
											                    	
														                  <div class="md:col-span-7">
														                      <label class="form-label text-sm">${fd.description}</label>
														                  </div>
														                  <div class="md:col-span-2">
														                      <label for="gr_amount_${fdcounter.count}" class="form-label md:hidden">Amount as per GR</label>
														                      <input oninput="this.value = this.value.replace(/\D+/g, '')" type="text" id="gr_amount_${fdcounter.count}" name="gr_amount_${fdcounter.count}" class="form-input bg-gray-100" value="${fd.grAmount}" readonly>
														                  </div>
														                  <div class="md:col-span-3">
														                      <label for="req_amount_${fdcounter.count}" class="form-label md:hidden">Requested Amount</label>
														                      <input oninput="this.value = this.value.replace(/\D+/g, '')" type="text" id="req_amount_${fdcounter.count}" name="req_amount_${fdcounter.count}" class="form-input" placeholder="Requested Amount" value="${fd.requestedAmount}" <c:if test="${mode == 'view'}">disabled</c:if>>
														                  </div>
														              
											                        </c:forEach>
											                          <div class="md:col-span-2">
														                      <label for="gr_amount_" class="form-label md:hidden">Total amount as per GR</label>
														                      <input type="text" id="total_gr_amount_" name="total_gr_amount_" class="form-input bg-gray-100" value="1448500" readonly>
														                  </div>
											                          <div class="md:col-span-3">
														                      <label for="total" class="form-label md:hidden">Total Requested Amount</label>
														                      <input oninput="this.value = this.value.replace(/\D+/g, '')" type="text" id="req_amount_13" name="req_amount_13" class="form-input" placeholder="Requested Amount" value="${totalRequestedAmount}" <c:if test="${mode == 'view'}">disabled</c:if>>
														                  </div>
											                        </div>
									                    	</c:if>
									                    	
									                    	<c:if test="${empty financialDetailsList}">
									                    		<div id="financialDetailsContainer" class="space-y-4">
											                        <!-- Financial items will be injected here -->
											                    </div>
									                    	</c:if>
									                 </div>
							                    </c:if>
							                    
							                    
							                    <c:if test="${mode eq 'add'}">
								                    <div id="financialDetailsContainer" class="space-y-4">
								                        <!-- Financial items will be injected here -->
								                    </div>
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
	                           		 <button class="btn btn-primary" type="button"
									onclick="location.reload();">
									<liferay-ui:message key="reset" />
								</button>
								 <!-- <button type="button" class="btn btn-primary" onclick="saveAwardYouthOrg(event,'Draft')"> -->
								   <button type="button" class="btn btn-primary" onclick="saveTrainingCenterDetails(event,'Draft','financial-details')">
	                                    <liferay-ui:message key="save-as-draft" />
	                                </button>   
	                               <button type="button" class="btn btn-primary" onclick="setActiveTab('servant','financial-details','previous')">
	                                    <liferay-ui:message key="previous" />
	                                </button>
	                                <!--  <button type="button" class="btn btn-primary" onclick="saveAwardYouthOrg(event,'Draft')"> -->
	                                 <button type="button" class="btn btn-primary" onclick="setActiveTab('reports-and-attachments','financial-details','next')">
	                                    <liferay-ui:message key="next" />
	                                </button>
                                </c:if>
                                <c:if test="${mode eq 'view'}">
                                	<button type="button" class="btn btn-primary" onclick="setActiveTab('servant','financial-details','previous')">
	                                    <liferay-ui:message key="previous" />
	                                </button>
                                	<button type="button" class="btn btn-primary" onclick="setActiveTab('reports-and-attachments','financial-details','next')">
	                                    <liferay-ui:message key="next" />
	                                </button>
                                </c:if>
                            </div>
</div>