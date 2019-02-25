package com.wb.weibao.utils.Timeline;

/**
 * Created by akshaykale on 2017/08/22.
 */

public class TestO implements TimelineObject {

    public TestO(String timeline,String timeline2) {
        this.timeline = timeline;
        this.timeline2 = timeline2;
    }

    String timeline;

    String timeline2;
    @Override
    public String getTimestamp() {
        return timeline;
    }

    @Override
    public String getTimestamp2() {
        return timeline2;
    }
}
