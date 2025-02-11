// Targeted by JavaCPP version 1.5.9-SNAPSHOT: DO NOT EDIT THIS FILE

package org.bytedeco.spinnaker.Spinnaker_C;

import java.nio.*;
import org.bytedeco.javacpp.*;
import org.bytedeco.javacpp.annotation.*;

import static org.bytedeco.javacpp.presets.javacpp.*;

import static org.bytedeco.spinnaker.global.Spinnaker_C.*;


/**
 * Handle for image list event handler functionality. Created by calling
 * spinImageListEventHandlerCreate(), which requires a call to spinImageListEventHandlerDestroy()
 * to destroy.
 */
@Namespace @Name("void") @Opaque @Properties(inherit = org.bytedeco.spinnaker.presets.Spinnaker_C.class)
public class spinImageListEventHandler extends Pointer {
    /** Empty constructor. Calls {@code super((Pointer)null)}. */
    public spinImageListEventHandler() { super((Pointer)null); }
    /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
    public spinImageListEventHandler(Pointer p) { super(p); }
}
