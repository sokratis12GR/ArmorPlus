package sokratis12gr.armorplus.common;

import sokratis12gr.armorplus.util.ArmorPlusUtils;

public class ThreadGetData extends Thread
{
    public ThreadGetData()
    {
        setDaemon(true);
        start();
    }

    @Override
    public void run()
    {
        ArmorPlusUtils.updateDonators();
    }
}