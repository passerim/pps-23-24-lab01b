package e2;

public interface Logics {

    boolean aMineWasFound(Pair<Integer, Integer> position);

    int getCounter(Pair<Integer, Integer> position);

    boolean isThereCounter(Pair<Integer, Integer> position);

    boolean isThereFlag(Pair<Integer, Integer> position);

    boolean isThereMine(Pair<Integer, Integer> position);

    boolean isThereVictory();

    void toggleFlag(Pair<Integer, Integer> position);
}
