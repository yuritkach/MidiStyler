package com.xamarin.java_interop.internal;

import com.xamarin.java_interop.GCUserPeerable;
import com.xamarin.java_interop.ManagedPeer;
import java.util.ArrayList;

final class JavaProxyThrowable extends Error implements GCUserPeerable {
    static final String assemblyQualifiedName = "Java.Interop.JavaProxyThrowable, Java.Interop, Version=0.1.0.0, Culture=neutral, PublicKeyToken=null";
    ArrayList<Object> managedReferences = new ArrayList<>();

    static {
        ManagedPeer.registerNativeMembers(JavaProxyThrowable.class, assemblyQualifiedName, "");
    }

    public JavaProxyThrowable() {
    }

    public JavaProxyThrowable(String str) {
        super(str);
    }

    public void jiAddManagedReference(Object obj) {
        this.managedReferences.add(obj);
    }

    public void jiClearManagedReferences() {
        this.managedReferences.clear();
    }
}
