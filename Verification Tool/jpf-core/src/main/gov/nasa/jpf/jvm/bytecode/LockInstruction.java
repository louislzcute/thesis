//
// Copyright (C) 2006 United States Government as represented by the
// Administrator of the National Aeronautics and Space Administration
// (NASA).  All Rights Reserved.
// 
// This software is distributed under the NASA Open Source Agreement
// (NOSA), version 1.3.  The NOSA has been approved by the Open Source
// Initiative.  See the file NOSA-1.3-JPF at the top of the distribution
// directory tree for the complete NOSA document.
// 
// THE SUBJECT SOFTWARE IS PROVIDED "AS IS" WITHOUT ANY WARRANTY OF ANY
// KIND, EITHER EXPRESSED, IMPLIED, OR STATUTORY, INCLUDING, BUT NOT
// LIMITED TO, ANY WARRANTY THAT THE SUBJECT SOFTWARE WILL CONFORM TO
// SPECIFICATIONS, ANY IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR
// A PARTICULAR PURPOSE, OR FREEDOM FROM INFRINGEMENT, ANY WARRANTY THAT
// THE SUBJECT SOFTWARE WILL BE ERROR FREE, OR ANY WARRANTY THAT
// DOCUMENTATION, IF PROVIDED, WILL CONFORM TO THE SUBJECT SOFTWARE.
//
package gov.nasa.jpf.jvm.bytecode;

import gov.nasa.jpf.vm.Instruction;
import gov.nasa.jpf.vm.ElementInfo;
import gov.nasa.jpf.vm.MJIEnv;
import gov.nasa.jpf.vm.ThreadInfo;


/**
 * common root for MONITORENTER/EXIT
 */
public abstract class LockInstruction extends Instruction implements JVMInstruction {
  int lastLockRef = MJIEnv.NULL;

  /**
    * only useful post-execution (in an instructionExecuted() notification)
    */
  public int getLastLockRef () {
    return lastLockRef;
  }

  @Override
  public String toPostExecString(){
    StringBuilder sb = new StringBuilder();
    sb.append(getMnemonic());
    sb.append(" @");
    sb.append( Integer.toHexString(lastLockRef));
    
    return sb.toString();
  }
  
  public void accept(JVMInstructionVisitor insVisitor) {
	  insVisitor.visit(this);
  }
}
