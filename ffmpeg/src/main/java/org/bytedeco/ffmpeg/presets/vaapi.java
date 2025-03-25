package org.bytedeco.ffmpeg.presets;

import org.bytedeco.javacpp.annotation.Platform;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.javacpp.presets.javacpp;
import org.bytedeco.javacpp.tools.Info;
import org.bytedeco.javacpp.tools.InfoMap;
import org.bytedeco.javacpp.tools.InfoMapper;

/**
 * @author silenium-dev
 */
@Properties(
    inherit = javacpp.class,
    target = "org.bytedeco.ffmpeg.vaapi",
    global = "org.bytedeco.ffmpeg.global.vaapi",
    value = {
        @Platform(
            value = "linux-x86_64",
            define = {"__STDC_CONSTANT_MACROS", "__STDC_FORMAT_MACROS", "STRING_BYTES_CHARSET \"UTF-8\""},
            cinclude = {"<va/va.h>", "<va/va_drm.h>", "<va/va_drmcommon.h>", "<va/va_version.h>", "<va/va_str.h>"},
            includepath = {"/usr/include/"}
        ),
        @Platform(
            value = "linux-arm64",
            define = {"__STDC_CONSTANT_MACROS", "__STDC_FORMAT_MACROS", "STRING_BYTES_CHARSET \"UTF-8\""},
            cinclude = {"<va/va.h>", "<va/va_drm.h>", "<va/va_drmcommon.h>", "<va/va_version.h>", "<va/va_str.h>"},
            includepath = {"/usr/include/"}
        ),
        @Platform(extension = "-gpl"),
    }
)
public class vaapi implements InfoMapper {
    @Override
    public void map(InfoMap infoMap) {
        infoMap
            .put(new Info("__COVERITY__").define(true))
            .put(new Info("VAEncMiscParameterType", "VA_VERSION").skip(true))
            .put(new Info("va_deprecated", "va_deprecated_enum").skip(true));
    }
}
