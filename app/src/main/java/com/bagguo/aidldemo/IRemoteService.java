package com.bagguo.aidldemo;

import android.app.Service;
import android.content.Intent;
import android.os.RemoteException;

public class IRemoteService extends Service {
    public IRemoteService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
//        throw new UnsupportedOperationException("Not yet implemented");

        return new IBinder();
    }

    class IBinder extends IMyAidlInterface.Stub{

        @Override
        public int add(int num1, int num2) throws RemoteException {
            return num1 + num2;
        }
    }
}
