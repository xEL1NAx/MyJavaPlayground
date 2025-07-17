package School.Grade_Lookup;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Write a description of class main2 here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class main2
{
    public static void main(String[] args) {
        // Hauptfenster erstellen
        JFrame frame = new JFrame("Notenbewertung");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 500);
        frame.setLayout(new BorderLayout());

        // Oberes Panel für Eingabe und Button
        JPanel inputPanel = new JPanel(new FlowLayout());
        JLabel eingabeLabel = new JLabel("Gib eine Note ein (1-6): ");
        JTextField noteEingabe = new JTextField(10);
        JButton bewertenButton = new JButton("Bewerten");

        inputPanel.add(eingabeLabel);
        inputPanel.add(noteEingabe);
        inputPanel.add(bewertenButton);

        // Mittlerer Bereich für die Ausgabe
        JTextArea ausgabeText = new JTextArea(10, 50);
        ausgabeText.setEditable(false);
        ausgabeText.setLineWrap(true);
        ausgabeText.setWrapStyleWord(true);

        JTextArea tippsText = new JTextArea(10, 50);
        tippsText.setEditable(false);
        tippsText.setLineWrap(true);
        tippsText.setWrapStyleWord(true);

        // Scroll-Pane für Ausgabetexte
        JScrollPane ausgabeScroll = new JScrollPane(ausgabeText);
        JScrollPane tippsScroll = new JScrollPane(tippsText);

        // Panel für Ausgaben
        JPanel outputPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        outputPanel.add(ausgabeScroll);
        outputPanel.add(tippsScroll);

        // Button ActionListener hinzufügen
        bewertenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int note = Integer.parseInt(noteEingabe.getText().trim());
                    ausgabeText.setText(getBewertung(note));
                    tippsText.setText(getTipps(note));
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, 
                        "Bitte gib eine gültige Note (1-6) ein!", 
                        "Fehler", 
                        JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Komponenten zum Frame hinzufügen
        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(outputPanel, BorderLayout.CENTER);

        // Fenster sichtbar machen
        frame.setVisible(true);
    }

    // Bewertungslogik
    public static String getBewertung(int note) {
        switch (note) {
            case 6:
                return "Die Leistung entspricht nicht den Anforderungen und selbst die Grundkenntnisse sind so lückenhaft, " +
                        "dass die Mängel in absehbarer Zeit nicht behoben werden können: ungenügend.";
            case 5:
                return "Die Leistung entspricht den Anforderungen nicht, jedoch lässt sie erkennen, " +
                        "dass die notwendigen Grundkenntnisse vorhanden sind und die Mängel in " +
                        "absehbarer Zeit behoben werden können: mangelhaft.";
            case 4:
                return "Die Leistung weist zwar Mängel auf, aber im Ganzen entspricht sie den " +
                        "Anforderungen noch: ausreichend.";
            case 3:
                return "Die Leistung entspricht im Allgemeinen den Anforderungen: " +
                        "befriedigend.";
            case 2:
                return "Die Leistung entspricht den Anforderungen voll: gut.";
            case 1:
                return "Die Leistung entspricht den Anforderungen im " +
                        "besonderen Maße: sehr gut.";
            default:
                return "Diese Note gibt es im Zeugnis nicht!";
        }
    }

    // Tippslogik
    public static String getTipps(int note) {
        switch (note) {
            case 6:
                return "Hole in den Ferien den Stoff nach, lass dich ausgiebig abhören.";
            case 5:
                return "Gehe immer wieder die Aufgaben und Lösungen durch.";
            case 4:
                return "Sprich über das, was Du nicht verstanden hast!";
            case 3:
                return "Bereite den Unterricht nach! Schaue dir die Arbeiten an und lerne aus den Fehlern - nur Mut!";
            case 2:
                return "Sei dem Unterricht voraus!";
            case 1:
                return "Wir hoffen, Du hast immer wieder Spaß an der Schule.";
            default:
                return "Schaue Dir die Noten nochmals an!";
        }
    }
}