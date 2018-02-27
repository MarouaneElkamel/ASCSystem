/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import de.uni.freiburg.iig.telematik.sepia.petrinet.pt.PTNet;
import de.uni.freiburg.iig.telematik.sepia.petrinet.pt.PTTransition;
import de.uni.freiburg.iig.telematik.sepia.serialize.PNSerialization;
import de.uni.freiburg.iig.telematik.sepia.serialize.SerializationException;
import de.uni.freiburg.iig.telematik.sepia.serialize.formats.PNSerializationFormat;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Elkamel
 */
public class EventInPanic
    {
    double notIn = 0;
    double action = 1;
    static PTNet normalNet = new PTNet();

    static PTNet panicnet = new PTNet();
    private boolean previousIncrease = false;
    private boolean previousdecrease = false;

    {
    if (panicnet.getPlaceCount() == 0)
        {
        panicnet.addPlace("P1");
        panicnet.addPlace("P2");
        panicnet.addTransition("T1");
        panicnet.addFlowRelationPT("P1", "T1");
        panicnet.addFlowRelationTP("T1", "P2");
        }
    if (normalNet.getPlaceCount() == 0)
        {
        normalNet.addPlace("P1");
        normalNet.addPlace("P2");
        normalNet.addTransition("T1");
        normalNet.addTransition("T2");
        normalNet.addFlowRelationPT("P1", "T1");
        normalNet.addFlowRelationTP("T1", "P2");
        normalNet.addFlowRelationPT("P2", "T2");
        normalNet.addFlowRelationTP("T2", "P1");
        normalNet.addPlace("P5");
        normalNet.addTransition("T5");
            normalNet.addTransition("T3.2");
            normalNet.addFlowRelationPT("P3.2", "T3.2");
            normalNet.addFlowRelationTP("T3.2", "P2");
            normalNet.addTransition("T4.2");
            normalNet.addFlowRelationPT("P4.2", "T4.2");
            normalNet.addFlowRelationTP("T4.2", "P2");
        normalNet.addFlowRelationPT("P2", "T5");
        normalNet.addFlowRelationTP("T5", "P5");
        normalNet.addTransition("T5.1");
        normalNet.addFlowRelationPT("P5", "T5.1");
        normalNet.addFlowRelationTP("T5.1", "P2");
        }
    }

    public void removeAsc()
    {
        normalNet.removeTransition("T3.2");
        normalNet.removeTransition("T4.2");
    }
    public double similarityTaux()
        {
        double notther = 0;
        for (PTTransition p : panicnet.getTransitions())
            {
            if (!normalNet.containsTransition(p.getName())) notther++;
            }
        return ((((double) panicnet.getTransitionCount() - notther) / (double) panicnet.getTransitionCount()) * 100);
        }

    public void RecordASCDesactivation()
        {
        if (Context.isPanic() && panicnet.containsPlace("P1") && panicnet.containsPlace("P2"))
            {
            action++;
            System.out.println("break added");
            panicnet.addTransition("T2");
            panicnet.addFlowRelationPT("P2", "T2");
            panicnet.addFlowRelationTP("T2", "P1");
            System.out.print("dsc");
            this.save();
            this.previousdecrease = false;
            this.previousIncrease = false;
            System.out.println("break in panic");
            }
        }

    public void RecordASCIncrease()
        {
        if (Context.isPanic())
            {
            if (panicnet.containsPlace("P3.2"))
                {
                if (this.previousIncrease == true)
                    {
                    panicnet.addTransition("T3.2");
                    panicnet.addFlowRelationPT("P3.2", "T3.2");
                    panicnet.addFlowRelationTP("T3.2", "P2");
                    this.save();
                    System.out.println("Asc + in panic");
                    }
                } else
                {
                notIn++;
                action++;
                System.out.println("ASCIn added");
                System.out.println("ASC ++ panic");
                panicnet.addPlace("P3.2");
                panicnet.addTransition("T3.1");
                panicnet.addFlowRelationPT("P2", "T3.1");
                panicnet.addFlowRelationTP("T3.1", "P3.2");
                this.save();
                }
            this.previousdecrease = false;
            this.previousIncrease = true;
            }
        }

    public void RecordASCDecrease()
        {
        if (Context.isPanic())
            {
            if (panicnet.containsPlace("P4.2"))
                {
                if (this.previousdecrease == true)
                    {
                    System.out.println("ACS - in panic");
                    panicnet.addTransition("T4.2");
                    panicnet.addFlowRelationPT("P4.2", "T4.2");
                    panicnet.addFlowRelationTP("T4.2", "P2");
                    this.save();
                    }
                } else
                {
                notIn++;
                action++;
                System.out.println("ASCDE added");
                System.out.println("Asc -- panic");
                panicnet.addPlace("P4.2");
                panicnet.addTransition("T4.1");
                panicnet.addFlowRelationPT("P2", "T4.1");
                panicnet.addFlowRelationTP("T4.1", "P4.2");
                this.save();
                }
            this.previousdecrease = true;
            this.previousIncrease = false;
            }
        }

    public void RecordSpeedIncreased()
        {
        if (!panicnet.containsPlace("P5"))
            {
            action++;
            System.out.println("Speed added");
            panicnet.addPlace("P5");
            panicnet.addTransition("T5");
            panicnet.addFlowRelationPT("P2", "T5");
            panicnet.addFlowRelationTP("T5", "P5");
            panicnet.addTransition("T5.1");
            panicnet.addFlowRelationPT("P5", "T5.1");
            panicnet.addFlowRelationTP("T5.1", "P2");
            this.save();
            System.out.println("speed ++panic");
            }
        }

    public void save()
        {
        Context.setSimilarity(similarityTaux());
        try
            {
            PNSerialization.serialize(panicnet, PNSerializationFormat.PNML, "Panic.pnml");
            } catch (SerializationException ex)
            {
            Logger.getLogger(EventRecorder.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex)
            {
            Logger.getLogger(EventRecorder.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
