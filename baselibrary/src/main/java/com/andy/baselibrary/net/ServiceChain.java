package com.andy.baselibrary.net;


import com.andy.baselibrary.utils.CommonUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andy on 2017/5/15.
 */

public class ServiceChain {
    private List<Service> servicesList;

    public void addService(Service service) {
        if (servicesList == null) {
            servicesList = new ArrayList<>();
        }
        servicesList.add(service);
    }

    public void initAllService(){
        if (CommonUtils.isListEmpty(servicesList)){
            return;
        }

        for (Service item: servicesList) {

        }
    }
}
