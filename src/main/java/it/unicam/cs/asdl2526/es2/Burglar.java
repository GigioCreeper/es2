package it.unicam.cs.asdl2526.es2;

/**
 * Uno scassinatore è un oggetto che prende una certa cassaforte e trova la
 * combinazione utilizzando la "forza bruta".
 * 
 * @author Luca Tesei
 *
 */
public class Burglar {
    // TODO inserire le variabili istanza che servono

    private CombinationLock safe;
    private long attempts;
    private String result;

    /**
     * Costruisce uno scassinatore per una certa cassaforte.
     * 
     * @param aCombinationLock la cassaforte da scassinare
     * @throws NullPointerException se la cassaforte passata è nulla
     */
    public Burglar(CombinationLock aCombinationLock) {
        // TODO implementare
        if (aCombinationLock == null) {throw new NullPointerException("aCombinationLock cannot be null");}
        else {
            this.safe = aCombinationLock;
        }
    }

    /**
     * Forza la cassaforte e restituisce la combinazione.
     * 
     * @return la combinazione della cassaforte forzata.
     */
    public String findCombination() {
        // TODO implementare
        for (char c = 'A'; c <= 'Z'; c++) {
            for (char c1 = 'A'; c1 <= 'Z'; c1++) {
                for (char c2 = 'A'; c2 <= 'Z'; c2++) {
                    safe.setPosition(c);
                    safe.setPosition(c1);
                    safe.setPosition(c2);
                    attempts++;
                    safe.open();

                    if (safe.isOpen()){
                        result = ""+c+c1+c2;
                        return  result;
                    }
                }
            }
        }
        return result;
    }

    /**
     * Restituisce il numero di tentativi che ci sono voluti per trovare la
     * combinazione. Se la cassaforte non è stata ancora forzata restituisce -1.
     * 
     * @return il numero di tentativi che ci sono voluti per trovare la
     *         combinazione, oppure -1 se la cassaforte non è stata ancora
     *         forzata.
     */
    public long getAttempts() {
        // TODO implementare
        if (safe.isOpen()) return attempts;
        else return -1;
    }
}
