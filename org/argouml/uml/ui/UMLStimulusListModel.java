// $Id$
// Copyright (c) 1996-99 The Regents of the University of California. All
// Rights Reserved. Permission to use, copy, modify, and distribute this
// software and its documentation without fee, and without a written
// agreement is hereby granted, provided that the above copyright notice
// and this paragraph appear in all copies.  This software program and
// documentation are copyrighted by The Regents of the University of
// California. The software program and documentation are supplied "AS
// IS", without any accompanying services from The Regents. The Regents
// does not warrant that the operation of the program will be
// uninterrupted or error-free. The end-user understands that the program
// was developed for research purposes and is advised not to rely
// exclusively on the program for any reason.  IN NO EVENT SHALL THE
// UNIVERSITY OF CALIFORNIA BE LIABLE TO ANY PARTY FOR DIRECT, INDIRECT,
// SPECIAL, INCIDENTAL, OR CONSEQUENTIAL DAMAGES, INCLUDING LOST PROFITS,
// ARISING OUT OF THE USE OF THIS SOFTWARE AND ITS DOCUMENTATION, EVEN IF
// THE UNIVERSITY OF CALIFORNIA HAS BEEN ADVISED OF THE POSSIBILITY OF
// SUCH DAMAGE. THE UNIVERSITY OF CALIFORNIA SPECIFICALLY DISCLAIMS ANY
// WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
// MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE. THE SOFTWARE
// PROVIDED HEREUNDER IS ON AN "AS IS" BASIS, AND THE UNIVERSITY OF
// CALIFORNIA HAS NO OBLIGATIONS TO PROVIDE MAINTENANCE, SUPPORT,
// UPDATES, ENHANCEMENTS, OR MODIFICATIONS.

package org.argouml.uml.ui;
import javax.swing.*;
import java.util.*;
import org.argouml.model.ModelFacade;

/**
 * @deprecated as of ArgoUml 0.13.5 (10-may-2003),
 *             replaced by ?,
 *             this class is part of the 'old'(pre 0.13.*) 
 *             implementation of proppanels
 *             that used reflection a lot.
 */
public class UMLStimulusListModel extends UMLModelElementListModel  {

    // list of sent or received stimuli
    private String theStimulusType;

    /**
     * The constructor.
     * 
     * @param container the container of UML user interface components
     * @param property the property
     * @param showNone true if we have to show "none" when there is no name
     * @param stimulusType the type of the stimulus
     */
    public UMLStimulusListModel(UMLUserInterfaceContainer container, 
            String property, boolean showNone, String stimulusType) {
	super(container, property, showNone);
	this.theStimulusType = stimulusType;

    }

    /**
     * @see org.argouml.uml.ui.UMLModelElementListModel#open(int)
     */
    public void open(int index) {
        Object/*MModelElement*/ stimulus = getModelElementAt(index);
        if (stimulus != null) {
            navigateTo(stimulus);
        }
    }


    /**
     * @see org.argouml.uml.ui.UMLModelElementListModel#recalcModelElementSize()
     */
    protected int recalcModelElementSize() {
        int size = 0;
        Collection stimuli = getStimuli();
        if (stimuli != null) {
            size = stimuli.size();
        }
        return size;
    }

    /**
     * @see org.argouml.uml.ui.UMLModelElementListModel#getModelElementAt(int)
     */
    protected Object getModelElementAt(int index) {
        Object/*MModelElement*/ elem = null;
        Collection stimuli = getStimuli();
        if (stimuli != null) {
            elem = elementAtUtil(stimuli, index, (Class) ModelFacade.STIMULUS);
        }
        return /*(MModelElement)*/ elem;
    }

    private Collection getStimuli() {
	Object/*MObject*/ obj = getTarget();
	Collection stimuli = null;
        if (theStimulusType.equals("sent") ) {
	    stimuli = ModelFacade.getStimuli3(obj);
	} else if (theStimulusType.equals("received") ) {
	    stimuli = ModelFacade.getStimuli2(obj);
  	}
	return stimuli;

    }




    /**
     * @see org.argouml.uml.ui.UMLModelElementListModel#buildPopup(
     * JPopupMenu, int)
     */
    public boolean buildPopup(JPopupMenu popup, int index) {
	return false;
    }

}


