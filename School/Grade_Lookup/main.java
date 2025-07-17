package School.Grade_Lookup;


/**
 * Write a description of class main here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class main
{
    public void textWert(int note) {
    System.out.println("Das bedeutet die Note:");
    switch (note) {
        case 6:
            System.out.println("Die Leistung entspricht nicht den Anforderungen und selbst die Grundkenntnisse sind so lückenhaft, " +
                    "dass die Mängel in absehbarer Zeit nicht behoben werden können: ungenügend");
            break;
        case 5:
            System.out.println("Die Leistung entspricht den Anforderungen nicht, jedoch lässt sie erkennen, " +
                    "dass die notwendigen Grundkenntnisse vorhanden sind und die Mängel in " +
                    "absehbarer Zeit behoben werden können: mangelhaft");
            break;
        case 4:
            System.out.println("Die Leistung weist zwar Mängel auf, aber im Ganzen entspricht sie den " +
                    "Anforderungen noch: ausreichend");
            break;
        case 3:
            System.out.println("Die Leistung entspricht im Allgemeinen den Anforderungen: " +
                    "befriedigend");
            break;
        case 2:
            System.out.println("Die Leistung entspricht den Anforderungen voll: gut");
            break;
        case 1:
            System.out.println("Die Leistung entspricht den Anforderungen im " +
                    "besonderen Maße: sehr gut");
            break;
        default:
            System.out.println("Diese Note gibt es im Zeugnis nicht!");
    }

    System.out.println("\nDiese Tipps können wir geben:");
    switch (note) {
        case 6:
            System.out.println("Hole in den Ferien den Stoff nach, lass dich ausgiebig abhören.");
        case 5:
            System.out.println("Gehe immer wieder die Aufgaben und Lösungen durch.");
        case 4:
            System.out.println("Sprich über das, was Du nicht verstanden hast!");
        case 3:
            System.out.println("Bereite den Unterricht nach! Schaue dir die Arbeiten an und lerne aus den Fehlern - nur Mut!");
        case 2:
            System.out.println("Sei dem Unterricht voraus!");
        case 1:
            System.out.println("Wir hoffen, Du hast immer wieder Spaß an der Schule.\n\n");
            break;
        default:
            System.out.println("Schaue Dir die Noten nochmals an!");
    }
}

}
