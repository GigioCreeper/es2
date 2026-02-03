package it.unicam.cs.asdl2526.es2;

import java.util.regex.*;

/**
 * Un oggetto cassaforte con combinazione ha una manopola che può essere
 * impostata su certe posizioni contrassegnate da lettere maiuscole. La
 * serratura si apre solo se le ultime tre lettere impostate sono uguali alla
 * combinazione segreta.
 * 
 * @author Luca Tesei
 */
public class CombinationLock {

    // TODO inserire le variabili istanza che servono
        private char aPosition;
        private String currentCombination;
        private String aCombination;
        boolean locked;
        boolean justlocked;
    /**
     * Costruisce una cassaforte <b>aperta</b> con una data combinazione
     * 
     * @param aCombination
     *                         la combinazione che deve essere una stringa di 3
     *                         lettere maiuscole dell'alfabeto inglese
     * @throws IllegalArgumentException se la combinazione fornita non è una
     *        stringa di 3 lettere maiuscole dell'alfabeto inglese
     * @throws NullPointerException se la combinazione fornita è nulla
     */
    public CombinationLock(String aCombination) {
        // TODO implementare
        if (aCombination != null && aCombination.matches("[A-Z]{3}")){
                this.aCombination = aCombination;
                this.currentCombination = "";
                this.locked = false;
                this.justlocked = false;
                this.aPosition = aCombination.charAt(0);
            }
            else if(aCombination == null || aCombination.isEmpty()) throw new NullPointerException("aCombination cannot be null");
            else  {throw new IllegalArgumentException("Invalid combination");}


    }

    /**
     * Imposta la manopola su una certa posizione.
     * 
     * @param aPosition
     *                      un carattere lettera maiuscola su cui viene
     *                      impostata la manopola
     * @throws IllegalArgumentException
     *                                      se il carattere fornito non è una
     *                                      lettera maiuscola dell'alfabeto
     *                                      inglese
     */
    public void setPosition(char aPosition) {
        // TODO implementare
        if(aPosition >= 'A' && aPosition <= 'Z'){
            this.justlocked = false;
            this.aPosition = aPosition;
            if (currentCombination.length() < 3){
                currentCombination += aPosition;
            } else {

                for (int i = 0 ; i < currentCombination.length()-2; i++){
                    currentCombination = currentCombination.replace(currentCombination.charAt(i), currentCombination.charAt(i+1));
                }
                currentCombination += aPosition;
            }

        }
        else throw new IllegalArgumentException("aPosition must be in range ['A','Z']");
    }

    /**
     * Tenta di aprire la serratura considerando come combinazione fornita le
     * ultime tre posizioni impostate. Se l'apertura non va a buon fine le
     * lettere impostate precedentemente non devono essere considerate per i
     * prossimi tentativi di apertura.
     */
    public void open() {
        if (this.locked) {
            if (justlocked && currentCombination.equals(aCombination)) {
                return;
            }
            if (currentCombination.equals(aCombination)) {
                locked = false;
            } else currentCombination = "";
        }
    }

    /**
     * Determina se la cassaforte è aperta.
     *
     * @return true se la cassaforte è attualmente aperta, false altrimenti
     */
    public boolean isOpen() {
        // TODO implementare
        return !this.locked;
    }

    /**
     * Chiude la cassaforte senza modificare la combinazione attuale. Fa in modo
     * che se si prova a riaprire subito senza impostare nessuna nuova posizione
     * della manopola la cassaforte non si apre. Si noti che se la cassaforte
     * era stata aperta con la combinazione giusta le ultime posizioni impostate
     * sono proprio la combinazione attuale.
     */
    public void lock() {
        // TODO implementare
        if(isOpen()) {
            justlocked = true;
            locked = true;
            currentCombination = "";
        }
    }

    /**
     * Chiude la cassaforte e modifica la combinazione. Funziona solo se la
     * cassaforte è attualmente aperta. Se la cassaforte è attualmente chiusa
     * rimane chiusa e la combinazione non viene cambiata, ma in questo caso le
     * le lettere impostate precedentemente non devono essere considerate per i
     * prossimi tentativi di apertura.
     *
     * @param aCombination
     *                         la nuova combinazione che deve essere una stringa
     *                         di 3 lettere maiuscole dell'alfabeto inglese
     * @throws IllegalArgumentException se la combinazione fornita non è una
     *        stringa di 3 lettere maiuscole dell'alfabeto inglese
     * @throws NullPointerException se la combinazione fornita è nulla
     */
    public void lockAndChangeCombination(String aCombination) {
        // TODO implementare
        if (aCombination == null || aCombination.isEmpty()) {
            currentCombination = "";
            throw new NullPointerException("aCombination cannot be null");
        }
        else {
            if (isOpen()) {
                if (aCombination.matches("[A-Z]{3}")) {
                    this.aCombination = aCombination;
                    this.locked = true;
                    this.justlocked = true;
                    this.currentCombination = "";
                } else {
                    throw new IllegalArgumentException("Invalid combination");
                }
            }
        }
    }
}
