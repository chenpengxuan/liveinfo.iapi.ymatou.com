package com.ymatou.liveinfo.domain.repository.sqlserver;

import com.ymatou.liveinfo.infrastructure.db.mapper.CountryMapper;
import com.ymatou.liveinfo.infrastructure.db.model.CountryExample;
import com.ymatou.liveinfo.infrastructure.db.model.CountryPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gejianhua on 2017/4/17.
 */
@Component
public class CountrySqlRepository {

    @Autowired
    private CountryMapper mapper;

    /**
     * 获取国家Id列表
     * @param countryGroupId
     * @return
     */
    public List<Integer> getCountryIds(int countryGroupId){

        CountryExample example = new CountryExample();
        example.createCriteria().andCountryGroupIdEqualTo(countryGroupId);
        List<CountryPo> countryPos = this.mapper.selectByExample(example);

        List<Integer> countryIds = new ArrayList<>();

        countryPos.forEach((countryPo)->{
            countryIds.add(countryPo.getCountryId());
        });

        return countryIds;
    }
}














































