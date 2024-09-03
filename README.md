# Auction System

This auction system is a client-server application that allows users to interact with an auction server. The client sends commands to the server to bid on items, view current items, and manage auction functionalities. The server processes these commands and maintains the state of the auction, logging all requests to a file.

## Features

- **Show Items**: Retrieve and display all items currently available for bidding.
- **Add Item**: Add a new item to the auction.
- **Place Bid**: Place a bid on a specific item.
- **Request Logging**: All client requests are logged in a file for auditing purposes.

## Project Structure

- **Client**: The client application that connects to the server and sends commands.
- **Server**: The server application that handles requests and manages auction state.
- **Log File**: A log file (`log.txt`) that records all client requests.

### Folder Structure

```
/auction-system
    ├── client
    │   └── Client.java
    ├── server
    │   ├── AuctionProtocol.java
    │   ├── ClientHandler.java
    │   └── Server.java
    └── log.txt
```

## Client Usage

### Client Class

The `Client` class connects to the server and handles user input. It supports the following commands:

- `show`: Display all items in the auction.
- `item <item-name>`: Add an item to the auction.
- `bid <item-name> <bid-value>`: Place a bid on an item.

#### Example Commands

1. **Show All Items**
   ```bash
   java Client show
   ```

2. **Add an Item**
   ```bash
   java Client item "New Item"
   ```

3. **Place a Bid**
   ```bash
   java Client bid "New Item" 100.50
   ```

## Server Usage

### Server Class

The `Server` class manages the auction items and handles incoming client requests. It creates a thread for each client to ensure simultaneous connections.

### Starting the Server

To start the server, run the `Server` class:

```bash
java Server
```

The server will listen on port **6789** by default.

## Logging

All client requests are logged in a file named `log.txt` in the following format:

```
<date>|<time>|<client-ip>|<request>
```

## Exception Handling

Both the client and server have basic exception handling to manage connection issues and invalid commands. If an error occurs, appropriate error messages are printed to the console.

## Requirements

- Java Development Kit (JDK) 8 or higher.
- An IDE or text editor for Java development (optional).

## How to Run

1. Compile the Java files:
   ```bash
   javac client/Client.java server/AuctionProtocol.java server/ClientHandler.java server/Server.java
   ```

2. Start the server in one terminal:
   ```bash
   java server.Server
   ```

3. In another terminal, run the client with the desired command:
   ```bash
   java client.Client <command>
   ```


## How It Works

### Architecture

The auction system is based on a client-server architecture. The **Client** application interacts with users, while the **Server** application manages the auction's state and processes client requests. 

- **Client**: Written in Java, this application connects to the auction server and allows users to send commands related to auction operations.
- **Server**: Also written in Java, it listens for incoming client connections, processes requests, and manages auction items.

### Communication

The communication between the client and server occurs over TCP sockets:

1. **Client Connection**: When the client starts, it establishes a connection to the server using a socket on a predefined port (default is **6789**).
2. **Request Handling**: The client sends commands to the server. The server listens for incoming connections and uses a separate thread to handle each client. This allows multiple clients to connect and interact with the server simultaneously.

### Command Processing

The system supports three main commands: `show`, `item`, and `bid`. Here’s how each command is processed:

1. **Show Command (`show`)**:
   - **Client Side**: When the user enters the `show` command, the client sends this command to the server.
   - **Server Side**: The server checks for existing auction items. If there are items, it formats a response listing all items and their current bid values, along with the highest bidder's IP address if any bids exist.
   - **Response**: The server sends the list of items back to the client, which displays it to the user.

2. **Add Item Command (`item <item-name>`)**:
   - **Client Side**: The client sends a command to add a new item to the auction.
   - **Server Side**: The server checks if the item already exists. If not, it adds the item to its internal list of items and confirms the action.
   - **Response**: The server sends back a success message or a failure notification if the item already exists.

3. **Place Bid Command (`bid <item-name> <bid-value>`)**:
   - **Client Side**: The client sends a bid command specifying the item name and the bid value.
   - **Server Side**: The server verifies the following:
     - The item exists.
     - The bid value is greater than the current bid for that item and is a valid number.
   - If both conditions are met, the server updates the bid and records the client's IP address as the highest bidder.
   - **Response**: The server informs the client whether the bid was accepted or rejected.

### Exception Handling

The system incorporates basic exception handling:

- **Client**: Catches exceptions related to network issues or invalid commands, providing feedback to the user about what went wrong.
- **Server**: Handles exceptions that occur during client communication, logging errors appropriately and ensuring the server remains running for other clients.

### Logging

Every request made by clients is logged in a file named `log.txt` with the following format:

```
<date>|<time>|<client-ip>|<request>
```

This log helps in tracking all actions taken within the auction system, which can be useful for debugging or auditing purposes.

