package com.wb.weibao.common;

public class PlayNumService {


    private static PlayNumService mPlayNumService = null;

    private boolean mIsPlay = false;

    private PlayNumService() {

    }

    public static PlayNumService getIntance() {
        if (mPlayNumService == null) {
            mPlayNumService = new PlayNumService();
        }
        return mPlayNumService;
    }


    public boolean isIsPlay() {
        return mIsPlay;
    }

    public void setIsPlay(boolean mIsPlay) {
        this.mIsPlay = mIsPlay;
    }
}
