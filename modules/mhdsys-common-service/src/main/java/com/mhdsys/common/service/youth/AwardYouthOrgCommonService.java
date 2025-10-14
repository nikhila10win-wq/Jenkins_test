package com.mhdsys.common.service.youth;

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.bean.BeanPropertiesUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.mhdsys.common.api.youth.AwardYouthOrgCommonApi;
import com.mhdsys.common.pojo.AwardYouthOrgCommonDTO;
import com.mhdsys.schema.model.AwardYouthOrg;
import com.mhdsys.schema.service.AwardYouthOrgLocalServiceUtil;

import org.osgi.service.component.annotations.Component;

@Component(
    immediate = true,
    service = AwardYouthOrgCommonApi.class
)
public class AwardYouthOrgCommonService implements AwardYouthOrgCommonApi {

    private static final Log _log = LogFactoryUtil.getLog(AwardYouthOrgCommonService.class);

    @Override
    public AwardYouthOrg saveAwardYouthOrg(AwardYouthOrgCommonDTO awardYouthOrgCommonDTO) {
        try {
            _log.info("AWARD YOUTH ORG SERVICE ::::::");

            if (awardYouthOrgCommonDTO.getAwardYouthOrgId() > 0) {
                _log.info("UPDATE / EDIT ::::::::::::");
                AwardYouthOrg awardYouthOrg = AwardYouthOrgLocalServiceUtil.getAwardYouthOrg(awardYouthOrgCommonDTO.getAwardYouthOrgId());

                long awardYouthOrgId = awardYouthOrgCommonDTO.getAwardYouthOrgId();
                _log.info("awardYouthOrgId : " + awardYouthOrgId);
                BeanPropertiesUtil.copyProperties(awardYouthOrgCommonDTO, awardYouthOrg);
                awardYouthOrg.setAwardYouthOrgId(awardYouthOrgId);

                return AwardYouthOrgLocalServiceUtil.updateAwardYouthOrg(awardYouthOrg);
            } else {
                AwardYouthOrg awardYouthOrg = AwardYouthOrgLocalServiceUtil.createAwardYouthOrg(
                    CounterLocalServiceUtil.increment(AwardYouthOrg.class.getName())
                );

                long awardYouthOrgId = awardYouthOrg.getAwardYouthOrgId();
                _log.info("awardYouthOrgId : " + awardYouthOrgId);
                BeanPropertiesUtil.copyProperties(awardYouthOrgCommonDTO, awardYouthOrg);
                awardYouthOrg.setAwardYouthOrgId(awardYouthOrgId);

                return AwardYouthOrgLocalServiceUtil.addAwardYouthOrg(awardYouthOrg);
            }
        } catch (Exception e) {
            _log.error(e.getMessage(), e);
            throw new RuntimeException("Failed to save award youth org", e);
        }
    }
}
