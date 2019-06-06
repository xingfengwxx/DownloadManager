# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
-dontusemixedcaseclassnames
-dontskipnonpubliclibraryclasses
-verbose
-ignorewarnings
-dontoptimize
-dontpreverify
-keepattributes SourceFile,LineNumberTable

#bugly
-dontwarn com.tencent.bugly.**
-keep public class com.tencent.bugly.**{*;}
-keep class android.support.**{*;}

#utilcode
-keep class com.blankj.utilcode.** { *; }
-keepclassmembers class com.blankj.utilcode.** { *; }
-dontwarn com.blankj.utilcode.**
-dontwarn javax.annotation.**
-dontwarn javax.inject.**
# OkHttp3
-dontwarn okhttp3.**
-dontwarn okio.**
-dontwarn javax.annotation.**
# A resource is loaded with a relative path so the package of this class must be preserved.
-keepnames class okhttp3.internal.publicsuffix.PublicSuffixDatabase

# Retrofit
-dontwarn retrofit2.**
-keep class retrofit2.** { *; }
-keepattributes Signature-keepattributes


#EventBus
-keepattributes *Annotation*
-keepclassmembers class * {
    @org.greenrobot.eventbus.Subscribe <methods>;
}
-keep enum org.greenrobot.eventbus.ThreadMode { *; }

# Only required if you use AsyncExecutor
-keepclassmembers class * extends org.greenrobot.eventbus.util.ThrowableFailureEvent {
    <init>(java.lang.Throwable);
}


# RxJava RxAndroid
-dontwarn sun.misc.**
-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
    long producerIndex;
    long consumerIndex;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode producerNode;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueConsumerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode consumerNode;
}

# Gson
-keep class com.google.gson.stream.** { *; }
-keepattributes EnclosingMethod
-keep class org.xz_sale.entity.**{*;}
# agentWeb
-keep class com.just.agentweb.** {
    *;
}
-dontwarn com.just.agentweb.**
-keepclassmembers class com.just.agentweb.sample.common.AndroidInterface{ *; }
#litepal
-keep class org.litepal.** {
    *;
}

-keep class * extends org.litepal.crud.DataSupport {
    *;
}

-keep class * extends org.litepal.crud.LitePalSupport {
    *;
}
-dontwarn com.cyl.musiclake.api.doupan.DoubanMusic.**
-keep class com.cyl.musiclake.api.doupan.DoubanMusic {
    *;
}

# 第三方jar包jaudiotagger
-dontwarn org.jaudiotagger.**
-keep class org.jaudiotagger.** {*;}

# AutoSize
-keep class me.jessyan.autosize.** { *; }
-keep interface me.jessyan.autosize.** { *; }

# 七牛
-keep class com.qiniu.**{*;}
-keep class com.qiniu.**{public <init>();}

# Glide
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}

# http
-keep class com.ashd.music.http.**{ *; }

#bean
-keep class com.ashd.music.bean.**{ *; }

#PictureSelector 2.0
-keep class com.luck.picture.lib.** { *; }

-dontwarn com.yalantis.ucrop**
-keep class com.yalantis.ucrop** { *; }
-keep interface com.yalantis.ucrop** { *; }

 #rxjava
-dontwarn sun.misc.**
-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
 long producerIndex;
 long consumerIndex;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
 rx.internal.util.atomic.LinkedQueueNode producerNode;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueConsumerNodeRef {
 rx.internal.util.atomic.LinkedQueueNode consumerNode;
}

#rxandroid
-dontwarn sun.misc.**
-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
   long producerIndex;
   long consumerIndex;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode producerNode;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueConsumerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode consumerNode;
}

#glide
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public class * extends com.bumptech.glide.AppGlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}

# Aliyun OSS
-keep class com.alibaba.sdk.android.oss.** { *; }
-dontwarn okio.**
-dontwarn org.apache.commons.codec.binary.**

#ARouter
-keep public class com.alibaba.android.arouter.routes.**{*;}
-keep class * implements com.alibaba.android.arouter.facade.template.ISyringe{*;}
# 如果使用了 byType 的方式获取 Service，需添加下面规则，保护接口
-keep interface * implements com.alibaba.android.arouter.facade.template.IProvider
# 如果使用了 单类注入，即不定义接口实现 IProvider，需添加下面规则，保护实现
-keep class * implements com.alibaba.android.arouter.facade.template.IProvider

# Bugout
-dontwarn cn.testin.analysis.**
-keep class cn.testin.analysis.** {*;}
-keep public class * extends android.content.ContentProvider
-keepnames class * extends android.view.View

# 友盟统计============================================================================
-keep class com.umeng.** {*;}
-keepclassmembers class * {
   public <init> (org.json.JSONObject);
}
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}
-keep public class com.ashd.music.R$*{
public static final int *;
}
# 友盟推送
-dontwarn com.umeng.**
-dontwarn com.taobao.**
-dontwarn anet.channel.**
-dontwarn anetwork.channel.**
-dontwarn org.android.**
-dontwarn org.apache.thrift.**
-dontwarn com.xiaomi.**
-dontwarn com.huawei.**
-dontwarn com.meizu.**

-keepattributes *Annotation*

-keep class com.taobao.** {*;}
-keep class org.android.** {*;}
-keep class anet.channel.** {*;}
-keep class com.umeng.** {*;}
-keep class com.xiaomi.** {*;}
-keep class com.huawei.** {*;}
-keep class com.meizu.** {*;}
-keep class org.apache.thrift.** {*;}

-keep class com.alibaba.sdk.android.**{*;}
-keep class com.ut.**{*;}
-keep class com.ta.**{*;}

-keep public class **.R$*{
   public static final int *;
}
# 友盟统计============================================================================

# IjkPlayer
-keep class tv.danmaku.ijk.media.player.** {*;}
-keep class tv.danmaku.ijk.media.player.IjkMediaPlayer{*;}
-keep class tv.danmaku.ijk.media.player.ffmpeg.FFmpegApi{*;}

# GreenDao
-keepclassmembers class * extends org.greenrobot.greendao.AbstractDao {
public static java.lang.String TABLENAME;
}
-keep class **$Properties
-keep class com.ashd.music.db.**{ *; }

# GreenDaoUpgradeHelper
-keepclassmembers class * extends org.greenrobot.greendao.AbstractDao {
    public static void dropTable(org.greenrobot.greendao.database.Database, boolean);
    public static void createTable(org.greenrobot.greendao.database.Database, boolean);
}

# GSYVideoPlayer
-keep class com.shuyu.gsyvideoplayer.video.** { *; }
-dontwarn com.shuyu.gsyvideoplayer.video.**
-keep class com.shuyu.gsyvideoplayer.video.base.** { *; }
-dontwarn com.shuyu.gsyvideoplayer.video.base.**
-keep class com.shuyu.gsyvideoplayer.utils.** { *; }
-dontwarn com.shuyu.gsyvideoplayer.utils.**
-keep class tv.danmaku.ijk.** { *; }
-dontwarn tv.danmaku.ijk.**

-keep public class * extends android.view.View{
    *** get*();
    void set*(***);
    public <init>(android.content.Context);
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
}

# OpenInstall
-dontwarn com.fm.openinstall.**
-keep public class com.fm.openinstall.* {*; }
-keep public interface com.fm.openinstall.* {*; }

# for DexGuard only
#-keepresourcexmlelements manifest/application/meta-data@value=GlideModule

# GSYVideoPlayer
-keep class com.shuyu.gsyvideoplayer.video.** { *; }
-dontwarn com.shuyu.gsyvideoplayer.video.**
-keep class com.shuyu.gsyvideoplayer.video.base.** { *; }
-dontwarn com.shuyu.gsyvideoplayer.video.base.**
-keep class com.shuyu.gsyvideoplayer.utils.** { *; }
-dontwarn com.shuyu.gsyvideoplayer.utils.**
-keep class tv.danmaku.ijk.** { *; }
-dontwarn tv.danmaku.ijk.**

-keep public class * extends android.view.View{
    *** get*();
    void set*(***);
    public <init>(android.content.Context);
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
}