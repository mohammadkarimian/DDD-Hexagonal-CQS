# Simple Ride Hailing Application

This project is a demonstration of various software design principles and practices, including Domain-Driven Design (DDD), Clean Architecture (Hexagonal), Command-Query Separation (CQS), and Testing. The application is built using Java 17 and the Spring Boot v3 framework.

## Table of Contents

- [Project Overview](#project-overview)
- [Architecture](#architecture)
- [Domain Model](#domain-model)
- [Use Cases](#use-cases)
- [Getting Started](#getting-started)
- [Testing](#testing)
- [Contributing](#contributing)

## Project Overview

This project is a simple ride-hailing application that demonstrates best practices in software architecture and design. It uses Java 17 and Spring Boot v3 as the underlying framework. The key focus areas of this project are:

- **Domain-Driven Design (DDD)**: The project follows DDD principles to model the domain and design the architecture around it.

- **Clean Architecture (Hexagonal)**: Clean Architecture is employed to maintain a clear separation of concerns between different layers of the application.

- **Command-Query Separation (CQS)**: CQS is used to separate commands that modify the system's state from queries that retrieve data without modifying the state.

## Architecture

The architecture of this project follows the Clean Architecture (Hexagonal) pattern, which consists of the following layers:

1. **Domain Layer**: This layer defines the core business logic and entities of the application. It includes the Ride, Invoice, and Station domains.

2. **Application Layer**: This layer coordinates the use cases and interacts with the external world. It is responsible for handling commands.
   - Use Case Layer : This layer contains the application's use cases. In this project, there are four primary use cases:
       - `submitRide`: Create a new ride request.
       - `acceptRide`: Accept a ride request.
       - `cancelRide`: Cancel a ride request.
       - `finishRide`: Mark a ride as completed.

3. **Adapter Layer**: This layer provides implementations for external dependencies such as databases, external services, and frameworks like Spring Boot.

## Domain Model

The project defines three main domain entities:

- **Ride**: Represents a ride request or an ongoing ride.
- **Invoice**: Represents the invoice generated for a completed ride.
- **Station**: Represents a location where rides can be picked up or dropped off.

## Use Cases

The following use cases are implemented in the project:

1. **Submit Ride (`submitRide`)**
    - Description: Create a new ride request in the system.

2. **Accept Ride (`acceptRide`)**
    - Description: Accept a ride request by a driver.

3. **Cancel Ride (`cancelRide`)**
    - Description: Cancel a ride request.

4. **Finish Ride (`finishRide`)**
    - Description: Mark a ride as completed and generate an invoice.

## Getting Started

To run the project locally, follow these steps:

1. **Clone the Repository**: Clone this repository to your local machine.

2. **Build and Run**: Use Maven or your favorite IDE to build and run the application.

3. **Access the API**: Once the application is running, you can access the API endpoints for the use cases.

## Testing

The project includes unit tests and integration tests to ensure the correctness of the code. You can run the tests using the provided testing framework.

## Contributing

Contributions to this project are welcome. If you'd like to contribute, please follow the standard GitHub fork and pull request process.

---

## License

This project is licensed under the [MIT License](LICENSE). Feel free to use and modify the code as per the terms of the license.

---

Thank you for checking out this simple ride-hailing application. We hope it serves as a valuable resource for understanding domain-driven design, clean architecture, and CQS principles in software development. If you have any questions or suggestions, please don't hesitate to reach out.