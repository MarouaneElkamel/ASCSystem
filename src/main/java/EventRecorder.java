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
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Elkamel
 */
public class EventRecorder
    {
    int compteur = 1;
    private boolean test_ASC = false;
    Timestamp timestampDebut, timestampFin;

    public void recordDebute()
        {
        timestampDebut = new Timestamp(System.currentTimeMillis());
        }

    EventsComposed EC = new EventsComposed();
    EventInPanic EP = new EventInPanic();
    PTNet activationNet, ASCIncreaseNet, ASCDecreaseNet, speedDecreaseNet, speedIncreaseNet;

    public void RecordASCActivationBefore()
        {
        recordDebute();
        activationNet = new PTNet();
        activationNet.addPlace("P1.1");
        activationNet.addTransition("T1");
        activationNet.addFlowRelationPT("P1.1", "T1");
        }

    public void RecordASCActivationAfter()
        {
        activationNet.addPlace("P1.2");
        activationNet.addFlowRelationTP("T1", "P1.2");
        save(activationNet);
        compteur++;
        test_ASC = true;
        EC.composePetrinets(activationNet);
        }

    public void RecordASCIncreaseBefore()
        {
        recordDebute();
        if (this.test_ASC == true)
            {
            ASCIncreaseNet = new PTNet();
            ASCIncreaseNet.addPlace("P3.1");
            ASCIncreaseNet.addTransition("T3.1");
            ASCIncreaseNet.addFlowRelationPT("P3.1", "T3.1");
            }
        }

    public void RecordASCIncreaseAfter()
        {
        if (this.test_ASC == true)
            {
            ASCIncreaseNet.addPlace("P3.2");
            ASCIncreaseNet.addFlowRelationTP("T3.1", "P3.2");
            save(ASCIncreaseNet);
            compteur++;
            EC.composePetrinets(ASCIncreaseNet);
            EP.RecordASCIncrease();
            }
        }

    public void RecordASCDecreaseBefore()
        {
        recordDebute();
        if (this.test_ASC == true)
            {
            ASCDecreaseNet = new PTNet();
            ASCDecreaseNet.addPlace("P4.1");
            ASCDecreaseNet.addTransition("T4.1");
            ASCDecreaseNet.addFlowRelationPT("P4.1", "T4.1");
            }
        }

    public void RecordASCDecreaseAfter()
        {
        if (this.test_ASC == true)
            {
            ASCDecreaseNet.addPlace("P4.2");
            ASCDecreaseNet.addFlowRelationTP("T4.1", "P4.2");
            save(ASCDecreaseNet);
            compteur++;
            EC.composePetrinets(ASCDecreaseNet);
            EP.RecordASCDecrease();
            }
        }

    public void RecordASCDesactivationBefore()
        {
        recordDebute();
        if (this.test_ASC == true)
            {
            speedDecreaseNet = new PTNet();
            speedDecreaseNet.addPlace("P2.1");
            speedDecreaseNet.addTransition("T2");
            speedDecreaseNet.addFlowRelationPT("P2.1", "T2");
            }
        }

    public void RecordASCDesactivationAfter()
        {
        if (this.test_ASC == true)
            {
            speedDecreaseNet.addPlace("P2.2");
            speedDecreaseNet.addFlowRelationTP("T2", "P2.2");
            save(speedDecreaseNet);
            compteur++;
            this.test_ASC = false;
            EC.composePetrinets(speedDecreaseNet);
            EP.RecordASCDesactivation();
            }
        }

    public void RecordSpeedIncreasedBefore()
        {
        recordDebute();
        if (this.test_ASC == true)
            {
            speedIncreaseNet = new PTNet();
            speedIncreaseNet.addPlace("P5.1");
            speedIncreaseNet.addTransition("T5.1");
            speedIncreaseNet.addFlowRelationPT("P5.1", "T5.1");
            }
        }

    public void RecordSpeedIncreasedAfter()
        {
        if (this.test_ASC == true)
            {
            PTNet ptnet = new PTNet();
            speedIncreaseNet.addPlace("P5.2");
            speedIncreaseNet.addTransition("T5.1");
            speedIncreaseNet.addFlowRelationTP("T5.1", "P5.2");
            save(speedIncreaseNet);
            compteur++;
            EC.composePetrinets(speedIncreaseNet);
            EP.RecordSpeedIncreased();
            }
        }

    public void save(PTNet allptnet)
        {
        timestampFin = new Timestamp(System.currentTimeMillis());
        try
            {
            PNSerialization.serialize(allptnet, PNSerializationFormat.PNML, compteur + "_" + timestampDebut.toString() + "_" + timestampFin.toString() + ".pnml");
            } catch (SerializationException ex)
            {
            Logger.getLogger(EventRecorder.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex)
            {
            Logger.getLogger(EventRecorder.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
