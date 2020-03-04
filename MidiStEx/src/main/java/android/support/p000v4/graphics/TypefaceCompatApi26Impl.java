package android.support.p000v4.graphics;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.fonts.FontVariationAxis;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.p000v4.content.res.FontResourcesParserCompat;
import android.util.Log;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;

@RequiresApi(26)
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* renamed from: android.support.v4.graphics.TypefaceCompatApi26Impl */
public class TypefaceCompatApi26Impl extends TypefaceCompatApi21Impl {
    private static final String ABORT_CREATION_METHOD = "abortCreation";
    private static final String ADD_FONT_FROM_ASSET_MANAGER_METHOD = "addFontFromAssetManager";
    private static final String ADD_FONT_FROM_BUFFER_METHOD = "addFontFromBuffer";
    private static final String CREATE_FROM_FAMILIES_WITH_DEFAULT_METHOD = "createFromFamiliesWithDefault";
    private static final String FONT_FAMILY_CLASS = "android.graphics.FontFamily";
    private static final String FREEZE_METHOD = "freeze";
    private static final int RESOLVE_BY_FONT_TABLE = -1;
    private static final String TAG = "TypefaceCompatApi26Impl";
    private static final Method sAbortCreation;
    private static final Method sAddFontFromAssetManager;
    private static final Method sAddFontFromBuffer;
    private static final Method sCreateFromFamiliesWithDefault;
    private static final Class sFontFamily;
    private static final Constructor sFontFamilyCtor;
    private static final Method sFreeze;

    static {
        Class fontFamilyClass;
        Constructor fontFamilyCtor;
        Method addFontMethod;
        Method addFromBufferMethod;
        Method freezeMethod;
        Method abortCreationMethod;
        Method createFromFamiliesWithDefaultMethod;
        try {
            fontFamilyClass = Class.forName(FONT_FAMILY_CLASS);
            fontFamilyCtor = fontFamilyClass.getConstructor(new Class[0]);
            addFontMethod = fontFamilyClass.getMethod(ADD_FONT_FROM_ASSET_MANAGER_METHOD, new Class[]{AssetManager.class, String.class, Integer.TYPE, Boolean.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE, FontVariationAxis[].class});
            addFromBufferMethod = fontFamilyClass.getMethod(ADD_FONT_FROM_BUFFER_METHOD, new Class[]{ByteBuffer.class, Integer.TYPE, FontVariationAxis[].class, Integer.TYPE, Integer.TYPE});
            freezeMethod = fontFamilyClass.getMethod(FREEZE_METHOD, new Class[0]);
            abortCreationMethod = fontFamilyClass.getMethod(ABORT_CREATION_METHOD, new Class[0]);
            createFromFamiliesWithDefaultMethod = Typeface.class.getDeclaredMethod(CREATE_FROM_FAMILIES_WITH_DEFAULT_METHOD, new Class[]{Array.newInstance(fontFamilyClass, 1).getClass(), Integer.TYPE, Integer.TYPE});
            createFromFamiliesWithDefaultMethod.setAccessible(true);
        } catch (ClassNotFoundException | NoSuchMethodException e) {
            Log.e(TAG, "Unable to collect necessary methods for class " + e.getClass().getName(), e);
            fontFamilyClass = null;
            fontFamilyCtor = null;
            addFontMethod = null;
            addFromBufferMethod = null;
            freezeMethod = null;
            abortCreationMethod = null;
            createFromFamiliesWithDefaultMethod = null;
        }
        sFontFamilyCtor = fontFamilyCtor;
        sFontFamily = fontFamilyClass;
        sAddFontFromAssetManager = addFontMethod;
        sAddFontFromBuffer = addFromBufferMethod;
        sFreeze = freezeMethod;
        sAbortCreation = abortCreationMethod;
        sCreateFromFamiliesWithDefault = createFromFamiliesWithDefaultMethod;
    }

    private static boolean isFontFamilyPrivateAPIAvailable() {
        if (sAddFontFromAssetManager == null) {
            Log.w(TAG, "Unable to collect necessary private methods. Fallback to legacy implementation.");
        }
        return sAddFontFromAssetManager != null;
    }

    private static Object newFamily() {
        try {
            return sFontFamilyCtor.newInstance(new Object[0]);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    private static boolean addFontFromAssetManager(Context context, Object family, String fileName, int ttcIndex, int weight, int style) {
        try {
            return ((Boolean) sAddFontFromAssetManager.invoke(family, new Object[]{context.getAssets(), fileName, 0, false, Integer.valueOf(ttcIndex), Integer.valueOf(weight), Integer.valueOf(style), null})).booleanValue();
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    private static boolean addFontFromBuffer(Object family, ByteBuffer buffer, int ttcIndex, int weight, int style) {
        try {
            return ((Boolean) sAddFontFromBuffer.invoke(family, new Object[]{buffer, Integer.valueOf(ttcIndex), null, Integer.valueOf(weight), Integer.valueOf(style)})).booleanValue();
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    private static Typeface createFromFamiliesWithDefault(Object family) {
        try {
            Object familyArray = Array.newInstance(sFontFamily, 1);
            Array.set(familyArray, 0, family);
            return (Typeface) sCreateFromFamiliesWithDefault.invoke((Object) null, new Object[]{familyArray, -1, -1});
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    private static boolean freeze(Object family) {
        try {
            return ((Boolean) sFreeze.invoke(family, new Object[0])).booleanValue();
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    private static void abortCreation(Object family) {
        try {
            sAbortCreation.invoke(family, new Object[0]);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    public Typeface createFromFontFamilyFilesResourceEntry(Context context, FontResourcesParserCompat.FontFamilyFilesResourceEntry entry, Resources resources, int style) {
        int i;
        if (!isFontFamilyPrivateAPIAvailable()) {
            return super.createFromFontFamilyFilesResourceEntry(context, entry, resources, style);
        }
        Object fontFamily = newFamily();
        for (FontResourcesParserCompat.FontFileResourceEntry fontFile : entry.getEntries()) {
            String fileName = fontFile.getFileName();
            int weight = fontFile.getWeight();
            if (fontFile.isItalic()) {
                i = 1;
            } else {
                i = 0;
            }
            if (!addFontFromAssetManager(context, fontFamily, fileName, 0, weight, i)) {
                abortCreation(fontFamily);
                return null;
            }
        }
        if (!freeze(fontFamily)) {
            return null;
        }
        return createFromFamiliesWithDefault(fontFamily);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0062, code lost:
        r15 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0063, code lost:
        r19 = r15;
        r15 = r14;
        r14 = r19;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x00e5, code lost:
        r14 = th;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.graphics.Typeface createFromFontInfo(android.content.Context r21, @android.support.annotation.Nullable android.os.CancellationSignal r22, @android.support.annotation.NonNull android.support.p000v4.provider.FontsContractCompat.FontInfo[] r23, int r24) {
        /*
            r20 = this;
            r0 = r23
            int r14 = r0.length
            r15 = 1
            if (r14 >= r15) goto L_0x0008
            r14 = 0
        L_0x0007:
            return r14
        L_0x0008:
            boolean r14 = isFontFamilyPrivateAPIAvailable()
            if (r14 != 0) goto L_0x0079
            r0 = r20
            r1 = r23
            r2 = r24
            android.support.v4.provider.FontsContractCompat$FontInfo r4 = r0.findBestInfo(r1, r2)
            android.content.ContentResolver r10 = r21.getContentResolver()
            android.net.Uri r14 = r4.getUri()     // Catch:{ IOException -> 0x0059 }
            java.lang.String r15 = "r"
            r0 = r22
            android.os.ParcelFileDescriptor r9 = r10.openFileDescriptor(r14, r15, r0)     // Catch:{ IOException -> 0x0059 }
            r15 = 0
            android.graphics.Typeface$Builder r14 = new android.graphics.Typeface$Builder     // Catch:{ Throwable -> 0x0060, all -> 0x00e5 }
            java.io.FileDescriptor r16 = r9.getFileDescriptor()     // Catch:{ Throwable -> 0x0060, all -> 0x00e5 }
            r0 = r16
            r14.<init>(r0)     // Catch:{ Throwable -> 0x0060, all -> 0x00e5 }
            int r16 = r4.getWeight()     // Catch:{ Throwable -> 0x0060, all -> 0x00e5 }
            r0 = r16
            android.graphics.Typeface$Builder r14 = r14.setWeight(r0)     // Catch:{ Throwable -> 0x0060, all -> 0x00e5 }
            boolean r16 = r4.isItalic()     // Catch:{ Throwable -> 0x0060, all -> 0x00e5 }
            r0 = r16
            android.graphics.Typeface$Builder r14 = r14.setItalic(r0)     // Catch:{ Throwable -> 0x0060, all -> 0x00e5 }
            android.graphics.Typeface r14 = r14.build()     // Catch:{ Throwable -> 0x0060, all -> 0x00e5 }
            if (r9 == 0) goto L_0x0007
            if (r15 == 0) goto L_0x005c
            r9.close()     // Catch:{ Throwable -> 0x0054 }
            goto L_0x0007
        L_0x0054:
            r16 = move-exception
            r15.addSuppressed(r16)     // Catch:{ IOException -> 0x0059 }
            goto L_0x0007
        L_0x0059:
            r5 = move-exception
            r14 = 0
            goto L_0x0007
        L_0x005c:
            r9.close()     // Catch:{ IOException -> 0x0059 }
            goto L_0x0007
        L_0x0060:
            r14 = move-exception
            throw r14     // Catch:{ all -> 0x0062 }
        L_0x0062:
            r15 = move-exception
            r19 = r15
            r15 = r14
            r14 = r19
        L_0x0068:
            if (r9 == 0) goto L_0x006f
            if (r15 == 0) goto L_0x0075
            r9.close()     // Catch:{ Throwable -> 0x0070 }
        L_0x006f:
            throw r14     // Catch:{ IOException -> 0x0059 }
        L_0x0070:
            r16 = move-exception
            r15.addSuppressed(r16)     // Catch:{ IOException -> 0x0059 }
            goto L_0x006f
        L_0x0075:
            r9.close()     // Catch:{ IOException -> 0x0059 }
            goto L_0x006f
        L_0x0079:
            r0 = r21
            r1 = r23
            r2 = r22
            java.util.Map r13 = android.support.p000v4.provider.FontsContractCompat.prepareFontData(r0, r1, r2)
            java.lang.Object r8 = newFamily()
            r3 = 0
            r0 = r23
            int r0 = r0.length
            r16 = r0
            r14 = 0
            r15 = r14
        L_0x008f:
            r0 = r16
            if (r15 >= r0) goto L_0x00c8
            r6 = r23[r15]
            android.net.Uri r14 = r6.getUri()
            java.lang.Object r7 = r13.get(r14)
            java.nio.ByteBuffer r7 = (java.nio.ByteBuffer) r7
            if (r7 != 0) goto L_0x00a5
        L_0x00a1:
            int r14 = r15 + 1
            r15 = r14
            goto L_0x008f
        L_0x00a5:
            int r17 = r6.getTtcIndex()
            int r18 = r6.getWeight()
            boolean r14 = r6.isItalic()
            if (r14 == 0) goto L_0x00c4
            r14 = 1
        L_0x00b4:
            r0 = r17
            r1 = r18
            boolean r11 = addFontFromBuffer(r8, r7, r0, r1, r14)
            if (r11 != 0) goto L_0x00c6
            abortCreation(r8)
            r14 = 0
            goto L_0x0007
        L_0x00c4:
            r14 = 0
            goto L_0x00b4
        L_0x00c6:
            r3 = 1
            goto L_0x00a1
        L_0x00c8:
            if (r3 != 0) goto L_0x00d0
            abortCreation(r8)
            r14 = 0
            goto L_0x0007
        L_0x00d0:
            boolean r14 = freeze(r8)
            if (r14 != 0) goto L_0x00d9
            r14 = 0
            goto L_0x0007
        L_0x00d9:
            android.graphics.Typeface r12 = createFromFamiliesWithDefault(r8)
            r0 = r24
            android.graphics.Typeface r14 = android.graphics.Typeface.create(r12, r0)
            goto L_0x0007
        L_0x00e5:
            r14 = move-exception
            goto L_0x0068
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p000v4.graphics.TypefaceCompatApi26Impl.createFromFontInfo(android.content.Context, android.os.CancellationSignal, android.support.v4.provider.FontsContractCompat$FontInfo[], int):android.graphics.Typeface");
    }

    @Nullable
    public Typeface createFromResourcesFontFile(Context context, Resources resources, int id, String path, int style) {
        if (!isFontFamilyPrivateAPIAvailable()) {
            return super.createFromResourcesFontFile(context, resources, id, path, style);
        }
        Object fontFamily = newFamily();
        if (!addFontFromAssetManager(context, fontFamily, path, 0, -1, -1)) {
            abortCreation(fontFamily);
            return null;
        } else if (!freeze(fontFamily)) {
            return null;
        } else {
            return createFromFamiliesWithDefault(fontFamily);
        }
    }
}
