package mono.android;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class Seppuku extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        Intent intent2 = new Intent("android.intent.action.MAIN");
        intent2.addCategory("android.intent.category.HOME");
        intent2.setFlags(268435456);
        context.startActivity(intent2);
        Runtime.getRuntime().exit(-1);
    }
}
