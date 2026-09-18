# Battleship

Basic academic version of Battleship game to build upon.

---

## Group LEI

| Hugo | LEI_122708 |
| Mafalda | LEI_122714 |
| Kseniya | LEI_145883 |
| Lukasz | LEI_145884 |

---

# Battleships — Game Rules

## Objective

The objective of Battleships is to destroy all of your opponent's ships before they destroy yours.

## Game Setup

Each player has two grids:

* **Own grid** — used to place and track your ships.
* **Opponent grid** — used to track your attacks.

Each player secretly places their ships on their own grid. Ships can be placed horizontally or vertically and cannot overlap.

### Fleet

Each player has the following fleet:

* 1 × Battleship — 4 squares
* 2 × Cruisers — 3 squares each
* 2 × Destroyers — 2 squares each
* 1 × Submarine — 1 square

## Gameplay

Players take turns attacking one square of the opponent's grid.

On a turn, a player:

1. Selects a coordinate on the opponent's grid.
2. The opponent determines whether the square contains a ship.
3. The result is recorded as either a **hit** or a **miss**.
4. If all squares occupied by a ship have been hit, that ship is **sunk**.
5. The turn then passes to the other player.

A player may not attack the same square more than once.

## Hit and Miss

* **Hit** — the selected square contains part of an opponent's ship.
* **Miss** — the selected square does not contain a ship.
* **Sunk** — every square of a particular ship has been hit.

## Winning

The game ends when one player has sunk **all of the opponent's ships**.

The player who sinks the entire opposing fleet first wins the game.

