# Parking Lot System (Low-Level Design)

A Java implementation of a parking lot system, built as a Low-Level Design (LLD)
exercise. It demonstrates core OOP principles and common design patterns such as
**Singleton** and **Strategy**.

## Features

- Park and unpark vehicles across multiple floors
- Automatic spot allocation based on vehicle type
- Ticket generation at entry and fee calculation at exit
- Pluggable, extensible fee-calculation strategy (currently hourly)

## Tech Stack

- Java
- Maven

## Domain Model

| Class | Responsibility |
|-------|---------------|
| `ParkingLot` | Singleton orchestrator; issues tickets and payments |
| `ParkingFloor` | Manages a collection of spots on one floor |
| `ParkingSpot` | A single spot; parks/unparks a vehicle |
| `Vehicle` | A vehicle with an ID and type |
| `Ticket` | Immutable entry record (ID, vehicle, spot, entry time) |
| `Payment` | Exit record (ID, ticket, exit time, amount) |
| `FeeCalculationStrategy` | Interface for pluggable pricing |
| `HourlyFeeCalculation` | Concrete hourly-pricing strategy |

## Enums

- `VehicleType`: `BIKE`, `CAR`, `TRUCK`
- `SpotType`: `SMALL`, `MEDIUM`, `LARGE`

Vehicles map to spots as: BIKE → SMALL, CAR → MEDIUM, TRUCK → LARGE.

## Project Structure

```
src/main/java/com/parkinglot/
├── Main.java                 # Demo driver
├── enums/
│   ├── VehicleType.java
│   └── SpotType.java
├── model/
│   ├── ParkingLot.java
│   ├── ParkingFloor.java
│   ├── ParkingSpot.java
│   ├── Vehicle.java
│   ├── Ticket.java
│   └── Payment.java
└── strategy/
    ├── FeeCalculationStrategy.java
    └── HourlyFeeCalculation.java
```

## How to Run

- Open `src/main/java/com/parkinglot/Main.java`
- Click the green ▶ arrow next to `public static void main(...)` (or in the gutter next to the class)

Or run `Main.java` directly from IntelliJ IDEA.

## Design Highlights

- **Singleton** — Only one `ParkingLot` instance exists (private constructor + `getInstance()`).
- **Strategy Pattern** — Fee logic lives behind the `FeeCalculationStrategy` interface,
  so new pricing models can be added without modifying existing code (Open/Closed Principle).
- **Immutable `Ticket`** — All ticket fields are `final`; a ticket is a fixed record of entry.
- **Realistic modeling** — Entry time lives on the `Ticket`; exit time lives on the `Payment`.

## Future Enhancements

- `ParkingStrategy` for pluggable spot selection (nearest-first, etc.)
- `FixedPlusVariableFeeStrategy` (fixed first hour + variable after)
- Thread safety for concurrent access