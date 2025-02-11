// Targeted by JavaCPP version 1.5.9-SNAPSHOT: DO NOT EDIT THIS FILE

package org.bytedeco.pytorch;

import org.bytedeco.pytorch.Allocator;
import org.bytedeco.pytorch.Function;
import org.bytedeco.pytorch.Module;
import java.nio.*;
import org.bytedeco.javacpp.*;
import org.bytedeco.javacpp.annotation.*;

import static org.bytedeco.javacpp.presets.javacpp.*;
import static org.bytedeco.openblas.global.openblas_nolapack.*;
import static org.bytedeco.openblas.global.openblas.*;

import static org.bytedeco.pytorch.global.torch.*;


// SymInt represents either a regular int64_t, or a symbolic integer
// (represented in a type erased way as SymNode).  The intention is for SymInt
// to represent symbolic sizes that arise when doing shape computation in
// operator kernels. This allows for tracing through programs without baking in
// concrete sizes into kernel calls.
//
// SymInt has an API equivalent to int64_t.  In particular, it is a value type.
// Internally, SymInt is represented in a clever packed way, so that it only
// occupies one word of space; but morally, it is a union between an int64_t
// and an intrusive pointer to SymNodeImpl.
//
// Invariant: the referenced SymNodeImpl is guaranteed to be a SymNode where
// is_int() returns true

@Namespace("c10") @NoOffset @Properties(inherit = org.bytedeco.pytorch.presets.torch.class)
public class SymInt extends Pointer {
    static { Loader.load(); }

  public enum Unchecked {
    UNCHECKED(0);

      public final int value;
      private Unchecked(int v) { this.value = v; }
      private Unchecked(Unchecked e) { this.value = e.value; }
      public Unchecked intern() { for (Unchecked e : values()) if (e.value == value) return e; return this; }
      @Override public String toString() { return intern().name(); }
  }

  /*implicit*/ public SymInt(@Cast("int64_t") long d) { super((Pointer)null); allocate(d); }
private native void allocate(@Cast("int64_t") long d);
  public SymInt() { super((Pointer)null); allocate(); }
  private native void allocate();
  public SymInt(@ByVal @Cast("c10::SymNode*") Pointer n) { super((Pointer)null); allocate(n); }
  private native void allocate(@ByVal @Cast("c10::SymNode*") Pointer n);

  // unchecked c-tor accepting raw `data_`
  // One appropriate use for this is when you are constructing a symint
  // in a situation where you know it is non-negative (or, if it is negative,
  // the negative value is -1; i.e., not user controlled)
  public SymInt(Unchecked arg0, @Cast("int64_t") long d) { super((Pointer)null); allocate(arg0, d); }
  private native void allocate(Unchecked arg0, @Cast("int64_t") long d);
  public SymInt(@Cast("c10::SymInt::Unchecked") int arg0, @Cast("int64_t") long d) { super((Pointer)null); allocate(arg0, d); }
  private native void allocate(@Cast("c10::SymInt::Unchecked") int arg0, @Cast("int64_t") long d);

  // TODO: these implementations are not optimal because they allocate a
  // temporary and then use the move constructor/assignment
  public SymInt(@Const @ByRef SymInt s) { super((Pointer)null); allocate(s); }
  private native void allocate(@Const @ByRef SymInt s);

  public native @ByRef @Name("operator =") SymInt put(@Const @ByRef SymInt s);

  public native @ByVal SymInt clone();

  public native SymNodeImpl toSymNodeImplUnowned();

  public native void release_();

  

  public native @ByVal @Cast("c10::SymNode*") Pointer toSymNodeImpl();

  // Require the int to be non-symbolic, and if it is symbolic raise an
  // error.  This is safe to use for C++ code that doesn't work for symbolic
  // shapes, and you don't have time to fix it immediately, as if we
  // try to trigger the path in C++ you'll appropriately get an error
  public native @Cast("int64_t") long expect_int();

  // Insert a guard for the int to be its concrete value, and then return
  // that value.  This operation always works, even if the int is symbolic,
  // so long as we know what the underlying value is (e.g., this won't work
  // if you call it on the size of nonzero output).  Don't blindly put this
  // everywhere; you can cause overspecialization of PyTorch programs with
  // this method.
  //
  // It should be called as guard_int(__FILE__, __LINE__).  The file and line
  // number can be used to diagnose overspecialization.
  public native @Cast("int64_t") long guard_int(@Cast("const char*") BytePointer file, @Cast("int64_t") long line);
  public native @Cast("int64_t") long guard_int(String file, @Cast("int64_t") long line);

  // N.B. It's important to keep this definition in the header
  // as we expect if checks to be folded for mobile builds
  // where `is_symbolic` is always false and optimize dead code paths
  public native @Cast("bool") boolean is_symbolic();

  public native @ByVal @Name("operator +") SymInt add(@Const @ByRef SymInt sci);
  public native @ByVal @Name("operator -") SymInt subtract(@Const @ByRef SymInt sci);
  public native @ByVal @Name("operator *") SymInt multiply(@Const @ByRef SymInt sci);
  public native @ByVal @Name("operator /") SymInt divide(@Const @ByRef SymInt sci);
  public native @ByVal @Name("operator %") SymInt mod(@Const @ByRef SymInt sci);
  public native @Name("operator *=") void multiplyPut(@Const @ByRef SymInt sci);
  public native @Name("operator +=") void addPut(@Const @ByRef SymInt sci);
  public native @Name("operator /=") void dividePut(@Const @ByRef SymInt sci);

  public native @ByVal SymBool sym_eq(@Const @ByRef SymInt arg0);
  public native @ByVal SymBool sym_ne(@Const @ByRef SymInt arg0);
  public native @ByVal SymBool sym_lt(@Const @ByRef SymInt arg0);
  public native @ByVal SymBool sym_le(@Const @ByRef SymInt arg0);
  public native @ByVal SymBool sym_gt(@Const @ByRef SymInt arg0);
  public native @ByVal SymBool sym_ge(@Const @ByRef SymInt arg0);

  
  
  public native @Cast("bool") @Name("operator <") boolean lessThan(@Const @ByRef SymInt o);
  public native @Cast("bool") @Name("operator <=") boolean lessThanEquals(@Const @ByRef SymInt o);
  public native @Cast("bool") @Name("operator >") boolean greaterThan(@Const @ByRef SymInt o);
  public native @Cast("bool") @Name("operator >=") boolean greaterThanEquals(@Const @ByRef SymInt o);

  public native @ByVal SymInt min(@Const @ByRef SymInt sci);
  public native @ByVal SymInt max(@Const @ByRef SymInt sci);

  public native @ByVal @Name("operator *") SymInt multiply(@Cast("int64_t") long sci);
  public native @Cast("bool") @Name("operator <") boolean lessThan(@Cast("int64_t") long sci);
  
  
  public native @Cast("bool") @Name("operator <=") boolean lessThanEquals(@Cast("int64_t") long sci);
  public native @Cast("bool") @Name("operator >") boolean greaterThan(@Cast("int64_t") long sci);
  public native @Cast("bool") @Name("operator >=") boolean greaterThanEquals(@Cast("int64_t") long sci);

  public native @ByVal @Name("operator c10::SymFloat") SymFloat asSymFloat();

  public native @Cast("int64_t") long as_int_unchecked();

  // Return whether the integer is representable as a SymInt.
  public static native @Cast("bool") boolean check_range(@Cast("int64_t") long i);

  // Return the min represetable integer as a SymInt
  public static native @Cast("const int64_t") long min_representable_int();
}
