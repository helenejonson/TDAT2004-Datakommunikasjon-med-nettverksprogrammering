import static javax.swing.JOptionPane.*;

public class Klient {
    public static void main(String[] args) {
        String [] operasjoner = {"Addere", "Sub", "Gange", "Dele", "Avslutt"};
        int valg = showOptionDialog(null, "Velg regneoperasjon", "Kalkulator", 0, PLAIN_MESSAGE, null, operasjoner, operasjoner[0]);
        while(valg >= 0 && valg < 4){
            int sum = 0;
            boolean sjekk1 = false;
            boolean sjekk2 = false;
            int tall1 = 0;
            int tall2 = 0;
            while(!sjekk1){
                String tall = "";
                if(valg == 3){
                    tall = showInputDialog("Sett inn teller");
                }else{
                    tall = showInputDialog("Sett inn første tall");
                }
                boolean matches = tall.matches("\\d+");
                if(matches){
                    tall1 = Integer.parseInt(tall);
                    sjekk1 = true;
                }else{
                    showMessageDialog(null, "Sett inn kun tall");
                }
            }
            while(!sjekk2){
                String tall = "";
                if(valg == 3){
                    tall = showInputDialog("Sett inn nevner");
                }else{
                    tall = showInputDialog("Sett inn andre tall");
                }
                boolean matches = tall.matches("\\d+");
                if(matches){
                    tall2 = Integer.parseInt(tall);
                    sjekk2 = true;
                }else{
                    showMessageDialog(null, "Sett inn kun tall");
                }
            }
            if(valg == 0){
                sum = tall1 + tall2;
            }
            else if(valg == 1){
                sum = tall1 - tall2;
            }
            else if(valg == 2){
                sum = tall1 * tall2;
            }
            else if(valg == 3){
                sum = tall1 / tall2;
            }

            showMessageDialog(null, "Summen er " + sum);

            valg = showOptionDialog(null, "Velg regneoperasjon", "Kalkulator", 0, PLAIN_MESSAGE, null, operasjoner, operasjoner[0]);
        }
    }
}
