package com.xamarin.java_interop;

public interface GCUserPeerable {
    void jiAddManagedReference(Object obj);

    void jiClearManagedReferences();
}
