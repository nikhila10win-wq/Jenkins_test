<%@ include file="/init.jsp"%>
<div class="card-footer bg-transparent text-right p-4">
<div class="d-flex justify-content-end">
<!-- <div class="d-flex justify-content-between"> -->
<c:if test="${mode ne 'view'}">
	<button type="button" class="btn btn-outline-danger cancel-tab-btn">
        <liferay-ui:message key="cancel" />
    </button>
    <button type="button" class="btn btn-warning reset-tab-btn">
        <liferay-ui:message key="reset" />
    </button>
</c:if>	    
  <button type="button" class="btn btn-outline-secondary save-draft-btn" onclick="saveAsDraft()" <c:if test="${mode eq 'view' || mode eq 'edit'}">style="display:none;"</c:if>>
    <liferay-ui:message key="save-as-draft" />
  </button>
  <div>
    <button type="button" class="btn btn-secondary prev-tab-btn me-2">
      <liferay-ui:message key="previous" />
    </button>
    <button type="button" class="btn btn-primary next-tab-btn">
      <liferay-ui:message key="next" />
    </button>
  </div>
</div>
</div>