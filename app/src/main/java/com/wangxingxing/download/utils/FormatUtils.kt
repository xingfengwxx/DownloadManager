package com.wangxingxing.download.utils

object FormatUtils {

    //文件格式=======================
    const val FORMAT_APK = "apk"
    //音频，有损
    const val FORMAT_MP3 = "mp3"
    const val FORMAT_AAC = "aac"
    //音频，无损
    const val FORMAT_WAV = "wav"
    const val FORMAT_FLAC = "flac"
    const val FORMAT_APE = "ape"
    const val FORMAT_ALAC = "alac"
    const val FORMAT_WV = "wv"
    //视频
    const val FORMAT_MP4 = "mp4"
    const val FORMAT_3GP = "3gp"
    const val FORMAT_FLV = "flv"
    const val FORMAT_AVI = "avi"
    const val FORMAT_WMV = "wmv"
    const val FORMAT_MKV = "mkv"
    const val FORMAT_RM = "rm"
    const val FORMAT_RMVB = "rmvb"
    const val FORMAT_MOV = "mov"
    const val FORMAT_MOD = "mod"
    //压缩文件
    const val FORMAT_RAR = "rar"
    const val FORMAT_ZIP = "zip"
    const val FORMAT_7Z = "7z"
    const val FORMAT_TAR = "tar"
    const val FORMAT_GZ = "gz"


    //文件类型=======================
    const val TYPE_APK = 0
    const val TYPE_MUSIC = 1
    const val TYPE_VIDEO = 2
    const val TYPE_ZIP = 3
    const val TYPE_OTHERS = 10

    fun getFormatType(fileName: String): Int {
        if (fileName.contains(".")) {
            val str = fileName.split(".")
            //格式
            var suf = str[str.size - 1]
            suf = suf.toLowerCase()
            when (suf) {
                FORMAT_APK -> return TYPE_APK

                FORMAT_MP3,
                FORMAT_AAC,
                FORMAT_WAV,
                FORMAT_FLAC,
                FORMAT_APE,
                FORMAT_ALAC,
                FORMAT_WV -> return TYPE_MUSIC

                FORMAT_MP4,
                FORMAT_3GP,
                FORMAT_FLV,
                FORMAT_AVI,
                FORMAT_WMV,
                FORMAT_MKV,
                FORMAT_RM,
                FORMAT_RMVB,
                FORMAT_MOV,
                FORMAT_MOD -> return TYPE_VIDEO

                FORMAT_RAR,
                FORMAT_ZIP,
                FORMAT_7Z,
                FORMAT_TAR,
                FORMAT_GZ -> return TYPE_ZIP

                else -> return TYPE_OTHERS
            }
        }
        return TYPE_OTHERS
    }
}