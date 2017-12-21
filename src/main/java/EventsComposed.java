/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import de.uni.freiburg.iig.telematik.sepia.petrinet.pt.PTNet;
import de.uni.freiburg.iig.telematik.sepia.serialize.PNSerialization;
import de.uni.freiburg.iig.telematik.sepia.serialize.SerializationException;
import de.uni.freiburg.iig.telematik.sepia.serialize.formats.PNSerializationFormat;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EventsComposed
    {
    PTNet Allptnet = new PTNet();
    private boolean previousIncrease = false;
    private boolean previousdecrease = false;

    public void composePetrinets(PTNet newNet)
        {
        if (newNet.containsPlace("P1.1") && newNet.containsPlace("P1.2")) RecordASCActivation();
        else if (newNet.containsPlace("P2.1") && newNet.containsPlace("P2.2")) RecordASCDesactivation();
        else if (newNet.containsPlace("P3.1") && newNet.containsPlace("P3.2")) RecordASCIncrease();
        else if (newNet.containsPlace("P4.1") && newNet.containsPlace("P4.2")) RecordASCDecrease();
        else if (newNet.containsPlace("P5.1") && newNet.containsPlace("P5.2")) RecordSpeedIncreased();
        }

    public void RecordASCActivation()
        {
        if (Allptnet.getPlaceCount() == 0)
            {
            Allptnet.addPlace("P1");
            Allptnet.addPlace("P2");
            Allptnet.addTransition("T1");
            Allptnet.addFlowRelationPT("P1", "T1");
            Allptnet.addFlowRelationTP("T1", "P2");
            this.save();
            this.previousdecrease = false;
            this.previousIncrease = false;
            }
        }

    public void RecordASCDesactivation()
        {
        if (Allptnet.containsPlace("P1") && Allptnet.containsPlace("P2"))
            {
            Allptnet.addTransition("T2");
            Allptnet.addFlowRelationPT("P2", "T2");
            Allptnet.addFlowRelationTP("T2", "P1");
            System.out.print("dsc");
            this.save();
            this.previousdecrease = false;
            this.previousIncrease = false;
            }
        }

    public void RecordASCIncrease()
        {
        if (Allptnet.containsPlace("P3.2"))
            {
            if (this.previousIncrease == true)
                {
                Allptnet.addTransition("T3.2");
                Allptnet.addFlowRelationPT("P3.2", "T3.2");
                Allptnet.addFlowRelationTP("T3.2", "P2");
                this.save();
                }
            } else
            {
            Allptnet.addPlace("P3.2");
            Allptnet.addTransition("T3.1");
            Allptnet.addFlowRelationPT("P2", "T3.1");
            Allptnet.addFlowRelationTP("T3.1", "P3.2");
            this.save();
            }
        this.previousdecrease = false;
        this.previousIncrease = true;
        }

    public void RecordASCDecrease()
        {
        if (Allptnet.containsPlace("P4.2"))
            {
            if (this.previousdecrease == true)
                {
                Allptnet.addTransition("T4.2");
                Allptnet.addFlowRelationPT("P4.2", "T4.2");
                Allptnet.addFlowRelationTP("T4.2", "P2");
                this.save();
                }
            } else
            {
            Allptnet.addPlace("P4.2");
            Allptnet.addTransition("T4.1");
            Allptnet.addFlowRelationPT("P2", "T4.1");
            Allptnet.addFlowRelationTP("T4.1", "P4.2");
            this.save();
            }
        this.previousdecrease = true;
        this.previousIncrease = false;
        }

    public void RecordSpeedIncreased()
        {
        if (!Allptnet.containsPlace("P5"))
            {
            Allptnet.addPlace("P5");
            Allptnet.addTransition("T5");
            Allptnet.addFlowRelationPT("P2", "T5");
            Allptnet.addFlowRelationTP("T5", "P5");
            Allptnet.addTransition("T5.1");
            Allptnet.addFlowRelationPT("P5", "T5.1");
            Allptnet.addFlowRelationTP("T5.1", "P2");
            this.save();
            }
        }

    public void save()
        {
        try
            {
            PNSerialization.serialize(Allptnet, PNSerializationFormat.PNML, "ALL.pnml");
            } catch (SerializationException ex)
            {
            Logger.getLogger(EventRecorder.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex)
            {
            Logger.getLogger(EventRecorder.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
