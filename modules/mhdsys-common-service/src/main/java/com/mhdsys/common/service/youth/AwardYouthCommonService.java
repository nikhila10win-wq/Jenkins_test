package com.mhdsys.common.service.youth;
import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.bean.BeanPropertiesUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.mhdsys.common.api.youth.AwardYouthCommonApi;
import com.mhdsys.common.pojo.AwardYouthCommonDTO;
import com.mhdsys.schema.model.AwardYouth;
import com.mhdsys.schema.service.AwardYouthLocalServiceUtil;

import org.osgi.service.component.annotations.Component;

@Component(
    immediate = true,
    service = AwardYouthCommonApi.class
)
public class AwardYouthCommonService implements AwardYouthCommonApi {

    private static final Log _log = LogFactoryUtil.getLog(AwardYouthCommonService.class);

    @Override
    public AwardYouth saveAwardYouth(AwardYouthCommonDTO awardYouthCommonDTO) {
        try {
            _log.info("AWARD YOUTH SERVICE ::::::");

            if (awardYouthCommonDTO.getAwardYouthId() > 0) {
                _log.info("UPDATE / EDIT ::::::::::::");
                AwardYouth awardYouth = AwardYouthLocalServiceUtil.getAwardYouth(awardYouthCommonDTO.getAwardYouthId());

                long awardYouthId = awardYouthCommonDTO.getAwardYouthId();
                _log.info("awardYouthId : " + awardYouthId);
                BeanPropertiesUtil.copyProperties(awardYouthCommonDTO, awardYouth);
                awardYouth.setAwardYouthId(awardYouthId);

                return AwardYouthLocalServiceUtil.updateAwardYouth(awardYouth);
            } else {
                AwardYouth awardYouth = AwardYouthLocalServiceUtil.createAwardYouth(
                    CounterLocalServiceUtil.increment(AwardYouth.class.getName())
                );

                long awardYouthId = awardYouth.getAwardYouthId();
                _log.info("awardYouthId : " + awardYouthId);
                BeanPropertiesUtil.copyProperties(awardYouthCommonDTO, awardYouth);
                awardYouth.setAwardYouthId(awardYouthId);

                return AwardYouthLocalServiceUtil.addAwardYouth(awardYouth);
            }
        } catch (Exception e) {
            _log.error(e.getMessage(), e);
            throw new RuntimeException("Failed to save award youth", e);
        }
    }
}