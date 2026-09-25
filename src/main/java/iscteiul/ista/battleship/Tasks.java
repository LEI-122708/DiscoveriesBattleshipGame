package iscteiul.ista.battleship;

import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Utility class containing execution logic for the various project tasks/stages (Tasks A, B, C, and D).
 * Facilitates testing for ship creation, fleet assembly, and combat loop simulations.
 *
 * @author Battleship Team
 * @version 1.0
 */
public class Tasks {

    /** Logger instance for logging application status and test outputs. */
    private static final Logger LOGGER = LogManager.getLogger();

    /** Fixed number of shots per firing volley. */
    private static final int NUMBER_SHOTS = 3;

    /** Farewell message displayed when quitting the program. */
    private static final String GOODBYE_MESSAGE = "Bons ventos!";

    /** Command string to initiate building a new fleet. */
    private static final String NOVAFROTA = "nova";

    /** Command string to forfeit/quit the game. */
    private static final String DESISTIR = "desisto";

    /** Command string to fire a volley of shots. */
    private static final String RAJADA = "rajada";

    /** Command string to display valid recorded shots. */
    private static final String VERTIROS = "ver";

    /** Command string to reveal the complete map/fleet layout (cheat mode). */
    private static final String BATOTA = "mapa";

    /** Command string to print the current status of the fleet. */
    private static final String STATUS = "estado";

    /**
     * Executes Task A: Tests individual ship building by reading ship data
     * and verifying whether generated ships occupy given test positions.
     */
    public static void taskA() {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            Ship s = readShip(in);
            if (s != null)
                for (int i = 0; i < NUMBER_SHOTS; i++) {
                    Position p = readPosition(in);
                    LOGGER.info("{} {}", p, s.occupies(p));
                }
        }
    }

    /**
     * Executes Task B: Tests fleet building and basic fleet status commands.
     */
    public static void taskB() {
        Scanner in = new Scanner(System.in);
        IFleet fleet = null;
        String command = in.next();
        while (!command.equals(DESISTIR)) {
            switch (command) {
                case NOVAFROTA:
                    fleet = buildFleet(in);
                    break;
                case STATUS:
                    if (fleet != null)
                        fleet.printStatus();
                    break;
                default:
                    LOGGER.info("Que comando é esse??? Repete lá ...");
            }
            command = in.next();
        }
        LOGGER.info(GOODBYE_MESSAGE);
    }

    /**
     * Executes Task C: Tests fleet building with added support for displaying
     * full board layout via the cheat command.
     */
    public static void taskC() {
        Scanner in = new Scanner(System.in);
        IFleet fleet = null;
        String command = in.next();
        while (!command.equals(DESISTIR)) {
            switch (command) {
                case NOVAFROTA:
                    fleet = buildFleet(in);
                    break;
                case STATUS:
                    if (fleet != null)
                        fleet.printStatus();
                    break;
                case BATOTA:
                    LOGGER.info(fleet);
                    break;
                default:
                    LOGGER.info("Que comando é esse??? Repete lá ...");
            }
            command = in.next();
        }
        LOGGER.info(GOODBYE_MESSAGE);
    }

    /**
     * Executes Task D: Simulates the complete game loop including fleet setup,
     * firing volleys, tracking hits, invalid/repeated shots, and remaining ships.
     */
    public static void taskD() {
        Scanner in = new Scanner(System.in);
        IFleet fleet = null;
        IGame game = null;
        String command = in.next();
        while (!command.equals(DESISTIR)) {
            switch (command) {
                case NOVAFROTA:
                    fleet = buildFleet(in);
                    game = new Game(fleet);
                    break;
                case STATUS:
                    if (fleet != null)
                        fleet.printStatus();
                    break;
                case BATOTA:
                    if (fleet != null)
                        game.printFleet();
                    break;
                case RAJADA:
                    if (game != null) {
                        firingRound(in, game);

                        LOGGER.info("Hits: {} Inv: {} Rep: {} Restam {} navios.", game.getHits(), game.getInvalidShots(),
                                game.getRepeatedShots(), game.getRemainingShips());
                        if (game.getRemainingShips() == 0)
                            LOGGER.info("Maldito sejas, Java Sparrow, eu voltarei, glub glub glub...");
                    }
                    break;
                case VERTIROS:
                    if (game != null)
                        game.printValidShots();
                    break;
                default:
                    LOGGER.info("Que comando é esse??? Repete ...");
            }
            command = in.next();
        }
        LOGGER.info(GOODBYE_MESSAGE);
    }

    /**
     * Builds a full fleet based on input provided via the scanner.
     *
     * @param in the {@link Scanner} instance used to read ship input data
     * @return the populated {@link Fleet} object
     */
    static Fleet buildFleet(Scanner in) {
        assert in != null;

        Fleet fleet = new Fleet();
        int i = 0; // i represents the total number of successfully created ships

        while (i <= Fleet.FLEET_SIZE) {
            IShip s = readShip(in);
            if (s != null) {
                boolean success = fleet.addShip(s);
                if (success)
                    i++;
                else
                    LOGGER.info("Falha na criacao de {} {} {}", s.getCategory(), s.getBearing(), s.getPosition());
            } else {
                LOGGER.info("Navio desconhecido!");
            }
        }
        LOGGER.info("{} navios adicionados com sucesso!", i);
        return fleet;
    }

    /**
     * Reads ship attributes from input, instantiates the corresponding ship, and returns it.
     *
     * @param in the {@link Scanner} instance to read from
     * @return the instantiated {@link Ship}, or {@code null} if inputs are invalid
     */
    static Ship readShip(Scanner in) {
        String shipKind = in.next();
        Position pos = readPosition(in);
        char c = in.next().charAt(0);
        Compass bearing = Compass.charToCompass(c);
        return Ship.buildShip(shipKind, bearing, pos);
    }

    /**
     * Reads row and column integers from input and creates a position object.
     *
     * @param in the {@link Scanner} instance to read from
     * @return the instantiated {@link Position} object
     */
    static Position readPosition(Scanner in) {
        int row = in.nextInt();
        int column = in.nextInt();
        return new Position(row, column);
    }

    /**
     * Fires a volley of shots (defined by {@link #NUMBER_SHOTS}) at the target game.
     *
     * @param in   the {@link Scanner} instance to read target coordinates from
     * @param game the active {@link IGame} instance being targeted
     */
    static void firingRound(Scanner in, IGame game) {
        for (int i = 0; i < NUMBER_SHOTS; i++) {
            IPosition pos = readPosition(in);
            IShip sh = game.fire(pos);
            if (sh != null)
                LOGGER.info("Mas... mas... {}s nao sao a prova de bala? :-(", sh.getCategory());
        }
    }
}