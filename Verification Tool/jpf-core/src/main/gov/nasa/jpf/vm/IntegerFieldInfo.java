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
package gov.nasa.jpf.vm;

import gov.nasa.jpf.JPFException;



/**
 * type, name, mod info about integer fields
 */
public class IntegerFieldInfo extends SingleSlotFieldInfo {
  int init;

  public IntegerFieldInfo (String name, int modifiers) {
     super(name, "I", modifiers);
  }

  public void initialize (ElementInfo ei, ThreadInfo ti) {
    ei.getFields().setIntValue( storageOffset, init);
  }

  public void setConstantValue(Object constValue){
    if (constValue instanceof Integer){
      cv = constValue;
      init = (Integer)constValue;

    } else {
      throw new JPFException("illegal int ConstValue=" + constValue);
    }
  }


  public Class<? extends ChoiceGenerator<?>> getChoiceGeneratorType() {
    return IntChoiceGenerator.class;
  }

  public String valueToString (Fields f) {
    int i = f.getIntValue(storageOffset);
    return Integer.toString(i);
  }

  public Object getValueObject (Fields f){
    int i = f.getIntValue(storageOffset);
    return new Integer(i);
  }

  public boolean isIntField(){
    // booleans, byte, char and short are too
    return true;
  }

  public boolean isNumericField(){
    return true;
  }
}
