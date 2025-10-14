<%@ include file="/init.jsp"%>

<c:if test="${isDeskOfficer || isDeputyDirector)}">
    <div class="card card-background p-0 mb-4">
        <div class="card-header header-card">
            <liferay-ui:message key="ho-remarks" />
        </div>
        <div class="card-body">
            <div class="row">
                <div class="col-md-6">
                    <div class="form-group">
                        <label><liferay-ui:message key="approve-reject" /><sup class="text-danger">*</sup></label>
                        <div>
                            <label> 
                                <input type="radio" name="hoVerification" id="hoVerification" value="Approve"
                                    <c:if test="${(not empty application.hoVerification || not isDeskOfficer) || mode eq 'view'}">disabled</c:if>
                                    <c:if test="${application.hoVerification == 'Approve'}">checked</c:if>>
                                <liferay-ui:message key="approve" />
                            </label> 
                            <label> 
                                <input type="radio" name="hoVerification" id="hoVerification" value="Reject"
                                    <c:if test="${(not empty application.hoVerification || not isDeskOfficer) || mode eq 'view'}">disabled</c:if>
                                    <c:if test="${application.hoVerification == 'Reject'}">checked</c:if>>
                                <liferay-ui:message key="reject" />
                            </label>
                        </div>
                    </div>
                </div>
                
                <div class="col-md-6">
                    <div class="form-group">
                        <label><liferay-ui:message key="remarks" /><sup class="text-danger">*</sup></label> 
                        <input type="text" class="form-control" id="hoRemarks" name="hoRemarks" 
                            value="${application.hoRemarks}"
                            <c:if test="${(not empty application.hoRemarks || not isDeskOfficer) || mode eq 'view'}">disabled</c:if> />
                    </div>
                </div>
            </div>
        </div>
    </div>
</c:if>

	
<c:if test="${isDeputyDirector)}">
    <div class="card card-background p-0 mb-4">
        <div class="card-header header-card">
            <liferay-ui:message key="director-remarks" />
        </div>
        <div class="card-body">
            <div class="row">
                <div class="col-md-6">
                    <div class="form-group">
                        <label><liferay-ui:message key="approve-reject" /><sup class="text-danger">*</sup></label>
                        <div>
                            <label> 
                                <input type="radio" name="ddVerification" id="ddVerification" value="Approve"
                                    <c:if test="${not empty application.ddVerification || !isDeputyDirector}">disabled</c:if>
                                    <c:if test="${application.ddVerification == 'Approve'}">checked</c:if>>
                                <liferay-ui:message key="approve" />
                            </label> 
                            <label> 
                                <input type="radio" name="ddVerification" id="ddVerification" value="Reject"
                                    <c:if test="${not empty application.ddVerification || !isDeputyDirector}">disabled</c:if>
                                    <c:if test="${application.ddVerification == 'Reject'}">checked</c:if>>
                                <liferay-ui:message key="reject" />
                            </label>
                        </div>
                    </div>
                </div>
                
                <div class="col-md-6">
                    <div class="form-group">
                        <label><liferay-ui:message key="remarks" /><sup class="text-danger">*</sup></label> 
                        <input type="text" class="form-control" id="ddRemarks" name="ddRemarks" 
                            value="${application.ddRemarks}"
                            <c:if test="${not empty application.ddRemarks || !isDeputyDirector}">disabled</c:if> />
                    </div>
                </div>
            </div>
        </div>
    </div>
</c:if>