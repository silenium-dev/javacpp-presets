// Targeted by JavaCPP version 1.5.3-SNAPSHOT: DO NOT EDIT THIS FILE

package org.bytedeco.arrow_flight;

import java.nio.*;
import org.bytedeco.javacpp.*;
import org.bytedeco.javacpp.annotation.*;

import org.bytedeco.arrow.*;
import static org.bytedeco.arrow.global.arrow.*;

import static org.bytedeco.arrow.global.arrow_flight.*;


/** \brief An iterator to FlightInfo instances returned by ListFlights. */
@Namespace("arrow::flight") @Properties(inherit = org.bytedeco.arrow.presets.arrow_flight.class)
public class FlightListing extends Pointer {
    static { Loader.load(); }
    /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
    public FlightListing(Pointer p) { super(p); }


  /** \brief Retrieve the next FlightInfo from the iterator.
   *  @param info [out] A single FlightInfo. Set to \a nullptr if there
   *  are none left.
   *  @return Status */
  public native @ByVal Status Next(@UniquePtr FlightInfo info);
}