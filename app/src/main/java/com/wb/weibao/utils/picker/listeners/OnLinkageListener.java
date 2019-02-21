package com.wb.weibao.utils.picker.listeners;


import com.wb.weibao.utils.picker.entity.City;
import com.wb.weibao.utils.picker.entity.County;
import com.wb.weibao.utils.picker.entity.Province;

/**
 * @author matt
 * blog: addapp.cn
 */

public interface OnLinkageListener {
    /**
     * 选择地址
     *
     * @param province the province
     * @param city    the city
     * @param county   the county ，if {@code hideCounty} is true，this is null
     */
    void onAddressPicked(Province province, City city, County county);
}
